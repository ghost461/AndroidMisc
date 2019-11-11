package org.chromium.content.browser.input;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

class ThreadedInputConnection extends BaseInputConnection implements ChromiumBaseInputConnection {
    final class org.chromium.content.browser.input.ThreadedInputConnection$1 extends TextInputState {
        org.chromium.content.browser.input.ThreadedInputConnection$1(CharSequence arg1, Range arg2, Range arg3, boolean arg4, boolean arg5) {
            super(arg1, arg2, arg3, arg4, arg5);
        }

        public boolean shouldUnblock() {
            return 1;
        }
    }

    class org.chromium.content.browser.input.ThreadedInputConnection$2 implements Runnable {
        org.chromium.content.browser.input.ThreadedInputConnection$2(ThreadedInputConnection arg1) {
            ThreadedInputConnection.this = arg1;
            super();
        }

        public void run() {
            ThreadedInputConnection.this.processPendingInputStates();
        }
    }

    class org.chromium.content.browser.input.ThreadedInputConnection$3 implements Runnable {
        org.chromium.content.browser.input.ThreadedInputConnection$3(ThreadedInputConnection arg1) {
            ThreadedInputConnection.this = arg1;
            super();
        }

        public void run() {
            if(!ThreadedInputConnection.this.mImeAdapter.requestTextInputStateUpdate()) {
                ThreadedInputConnection.this.unblockOnUiThread();
            }
        }
    }

    class org.chromium.content.browser.input.ThreadedInputConnection$4 implements Runnable {
        org.chromium.content.browser.input.ThreadedInputConnection$4(ThreadedInputConnection arg1) {
            ThreadedInputConnection.this = arg1;
            super();
        }

        public void run() {
            ThreadedInputConnection.this.mImeAdapter.notifyUserAction();
        }
    }

    class org.chromium.content.browser.input.ThreadedInputConnection$5 implements Runnable {
        org.chromium.content.browser.input.ThreadedInputConnection$5(ThreadedInputConnection arg1) {
            ThreadedInputConnection.this = arg1;
            super();
        }

        public void run() {
            ThreadedInputConnection.this.finishComposingTextOnUiThread();
        }
    }

    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_Ime";
    private static final TextInputState UNBLOCKER;
    private TextInputState mCachedTextInputState;
    private int mCurrentExtractedTextRequestToken;
    private final Runnable mFinishComposingTextRunnable;
    private final Handler mHandler;
    private final ImeAdapterImpl mImeAdapter;
    private final Runnable mNotifyUserActionRunnable;
    private int mNumNestedBatchEdits;
    private int mPendingAccent;
    private final Runnable mProcessPendingInputStatesRunnable;
    private final BlockingQueue mQueue;
    private final Runnable mRequestTextInputStateUpdate;
    private boolean mShouldUpdateExtractedText;

    static {
        ThreadedInputConnection.UNBLOCKER = new org.chromium.content.browser.input.ThreadedInputConnection$1("", new Range(0, 0), new Range(-1, -1), false, false);
    }

    ThreadedInputConnection(View arg2, ImeAdapterImpl arg3, Handler arg4) {
        super(arg2, true);
        this.mProcessPendingInputStatesRunnable = new org.chromium.content.browser.input.ThreadedInputConnection$2(this);
        this.mRequestTextInputStateUpdate = new org.chromium.content.browser.input.ThreadedInputConnection$3(this);
        this.mNotifyUserActionRunnable = new org.chromium.content.browser.input.ThreadedInputConnection$4(this);
        this.mFinishComposingTextRunnable = new org.chromium.content.browser.input.ThreadedInputConnection$5(this);
        this.mQueue = new LinkedBlockingQueue();
        ImeUtils.checkOnUiThread();
        this.mImeAdapter = arg3;
        this.mHandler = arg4;
    }

    static void access$000(ThreadedInputConnection arg0) {
        arg0.processPendingInputStates();
    }

    static ImeAdapterImpl access$100(ThreadedInputConnection arg0) {
        return arg0.mImeAdapter;
    }

    static void access$200(ThreadedInputConnection arg0) {
        arg0.finishComposingTextOnUiThread();
    }

    static int access$302(ThreadedInputConnection arg0, int arg1) {
        arg0.mNumNestedBatchEdits = arg1;
        return arg1;
    }

    static int access$400(ThreadedInputConnection arg0) {
        return arg0.mPendingAccent;
    }

    static int access$402(ThreadedInputConnection arg0, int arg1) {
        arg0.mPendingAccent = arg1;
        return arg1;
    }

    static int access$502(ThreadedInputConnection arg0, int arg1) {
        arg0.mCurrentExtractedTextRequestToken = arg1;
        return arg1;
    }

    static boolean access$602(ThreadedInputConnection arg0, boolean arg1) {
        arg0.mShouldUpdateExtractedText = arg1;
        return arg1;
    }

    static void access$700(ThreadedInputConnection arg0, CharSequence arg1, int arg2, boolean arg3) {
        arg0.updateComposingTextOnUiThread(arg1, arg2, arg3);
    }

    static void access$800(ThreadedInputConnection arg0, CharSequence arg1, int arg2) {
        arg0.commitTextOnUiThread(arg1, arg2);
    }

    static boolean access$900(ThreadedInputConnection arg0, KeyEvent arg1) {
        return arg0.handleCombiningAccentOnUiThread(arg1);
    }

    private void addToQueueOnUiThread(TextInputState arg5) {
        ImeUtils.checkOnUiThread();
        try {
            this.mQueue.put(arg5);
        }
        catch(InterruptedException v5) {
            Log.e("cr_Ime", "addToQueueOnUiThread interrupted", new Object[]{v5});
        }
    }

    private void assertOnImeThread() {
        boolean v0 = this.mHandler.getLooper() == Looper.myLooper() ? true : false;
        ImeUtils.checkCondition(v0);
    }

    public boolean beginBatchEdit() {
        this.assertOnImeThread();
        this.assertOnImeThread();
        ++this.mNumNestedBatchEdits;
        return 1;
    }

    private TextInputState blockAndGetStateUpdate() {
        Object v3;
        this.assertOnImeThread();
        int v1;
        for(v1 = 0; true; v1 = 1) {
            TextInputState v2 = null;
            try {
                v3 = this.mQueue.take();
                if(!((TextInputState)v3).shouldUnblock()) {
                    goto label_9;
                }
            }
            catch(InterruptedException v1_1) {
                ThrowableExtension.printStackTrace(((Throwable)v1_1));
                ImeUtils.checkCondition(false);
                return v2;
            }

            return v2;
        label_9:
            if(((TextInputState)v3).replyToRequest()) {
                if(v1 != 0) {
                    this.updateSelection(((TextInputState)v3));
                }

                return ((TextInputState)v3);
            }
        }
    }

    private void cancelCombiningAccentOnUiThread() {
        this.mPendingAccent = 0;
    }

    public boolean clearMetaKeyStates(int arg1) {
        return 0;
    }

    @SuppressLint(value={"MissingSuperCall"}) public void closeConnection() {
    }

    private void commitCodePointOnUiThread(int arg4, int arg5) {
        StringBuilder v0 = new StringBuilder();
        v0.appendCodePoint(arg4);
        this.mImeAdapter.sendCompositionToNative(v0.toString(), 1, true, 0);
        this.setCombiningAccentOnUiThread(arg5);
    }

    public boolean commitCompletion(CompletionInfo arg1) {
        return 0;
    }

    public boolean commitCorrection(CorrectionInfo arg1) {
        return 0;
    }

    public boolean commitText(CharSequence arg3, int arg4) {
        if(arg3 == null) {
            return 0;
        }

        if(TextUtils.equals(arg3, "\n")) {
            this.beginBatchEdit();
            this.commitText("", 1);
            ThreadUtils.postOnUiThread(new Runnable() {
                public void run() {
                    ThreadedInputConnection.this.mImeAdapter.sendSyntheticKeyPress(66, 6);
                }
            });
            this.endBatchEdit();
            return 1;
        }

        ThreadUtils.postOnUiThread(new Runnable(arg3, arg4) {
            public void run() {
                ThreadedInputConnection.this.commitTextOnUiThread(this.val$text, this.val$newCursorPosition);
            }
        });
        this.notifyUserAction();
        return 1;
    }

    private void commitTextOnUiThread(CharSequence arg4, int arg5) {
        this.cancelCombiningAccentOnUiThread();
        this.mImeAdapter.sendCompositionToNative(arg4, arg5, true, 0);
    }

    private ExtractedText convertToExtractedText(TextInputState arg3) {
        if(arg3 == null) {
            return null;
        }

        ExtractedText v0 = new ExtractedText();
        v0.text = arg3.text();
        v0.partialEndOffset = arg3.text().length();
        v0.partialStartOffset = -1;
        v0.selectionStart = arg3.selection().start();
        v0.selectionEnd = arg3.selection().end();
        v0.flags = arg3.singleLine();
        return v0;
    }

    public boolean deleteSurroundingText(int arg2, int arg3) {
        ThreadUtils.postOnUiThread(new Runnable(arg2, arg3) {
            public void run() {
                if(ThreadedInputConnection.this.mPendingAccent != 0) {
                    ThreadedInputConnection.this.finishComposingTextOnUiThread();
                }

                ThreadedInputConnection.this.mImeAdapter.deleteSurroundingText(this.val$beforeLength, this.val$afterLength);
            }
        });
        return 1;
    }

    public boolean deleteSurroundingTextInCodePoints(int arg2, int arg3) {
        ThreadUtils.postOnUiThread(new Runnable(arg2, arg3) {
            public void run() {
                if(ThreadedInputConnection.this.mPendingAccent != 0) {
                    ThreadedInputConnection.this.finishComposingTextOnUiThread();
                }

                ThreadedInputConnection.this.mImeAdapter.deleteSurroundingTextInCodePoints(this.val$beforeLength, this.val$afterLength);
            }
        });
        return 1;
    }

    public boolean endBatchEdit() {
        this.assertOnImeThread();
        boolean v1 = false;
        if(this.mNumNestedBatchEdits == 0) {
            return 0;
        }

        --this.mNumNestedBatchEdits;
        if(this.mNumNestedBatchEdits == 0) {
            this.updateSelection(this.requestAndWaitForTextInputState());
        }

        if(this.mNumNestedBatchEdits != 0) {
            v1 = true;
        }

        return v1;
    }

    public boolean finishComposingText() {
        ThreadUtils.postOnUiThread(this.mFinishComposingTextRunnable);
        return 1;
    }

    private void finishComposingTextOnUiThread() {
        this.mImeAdapter.finishComposingText();
    }

    public int getCursorCapsMode(int arg3) {
        TextInputState v0 = this.requestAndWaitForTextInputState();
        return v0 != null ? TextUtils.getCapsMode(v0.text(), v0.selection().start(), arg3) : 0;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest arg3, int arg4) {
        this.assertOnImeThread();
        boolean v0 = true;
        int v1 = 0;
        if((arg4 & 1) > 0) {
        }
        else {
            v0 = false;
        }

        this.mShouldUpdateExtractedText = v0;
        if(this.mShouldUpdateExtractedText) {
            if(arg3 != null) {
                v1 = arg3.token;
            }

            this.mCurrentExtractedTextRequestToken = v1;
        }

        return this.convertToExtractedText(this.requestAndWaitForTextInputState());
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    BlockingQueue getQueueForTest() {
        return this.mQueue;
    }

    public CharSequence getSelectedText(int arg1) {
        TextInputState v1 = this.requestAndWaitForTextInputState();
        if(v1 == null) {
            return null;
        }

        return v1.getSelectedText();
    }

    public CharSequence getTextAfterCursor(int arg1, int arg2) {
        TextInputState v2 = this.requestAndWaitForTextInputState();
        if(v2 == null) {
            return null;
        }

        return v2.getTextAfterSelection(arg1);
    }

    public CharSequence getTextBeforeCursor(int arg1, int arg2) {
        TextInputState v2 = this.requestAndWaitForTextInputState();
        if(v2 == null) {
            return null;
        }

        return v2.getTextBeforeSelection(arg1);
    }

    private boolean handleCombiningAccentOnUiThread(KeyEvent arg4) {
        int v4;
        int v0 = arg4.getAction();
        int v1 = arg4.getUnicodeChar();
        if(v0 != 0) {
            return 0;
        }

        if(arg4.getKeyCode() == 67) {
            this.setCombiningAccentOnUiThread(0);
            return 0;
        }

        if((0x80000000 & v1) != 0) {
            v4 = 0x7FFFFFFF & v1;
            if(this.mPendingAccent != 0) {
                if(v4 == this.mPendingAccent) {
                    this.commitCodePointOnUiThread(this.mPendingAccent, 0);
                }
                else {
                    this.commitCodePointOnUiThread(this.mPendingAccent, v4);
                }

                return 1;
            }

            this.setCombiningAccentOnUiThread(v4);
            return 1;
        }

        if(this.mPendingAccent != 0 && v1 != 0) {
            v4 = KeyEvent.getDeadChar(this.mPendingAccent, v1);
            if(v4 != 0) {
                this.commitCodePointOnUiThread(v4, 0);
                return 1;
            }
            else {
                this.commitCodePointOnUiThread(this.mPendingAccent, 0);
                this.finishComposingTextOnUiThread();
            }
        }

        return 0;
    }

    private void notifyUserAction() {
        ThreadUtils.postOnUiThread(this.mNotifyUserActionRunnable);
    }

    public void onRestartInputOnUiThread() {
    }

    public boolean performContextMenuAction(int arg2) {
        ThreadUtils.postOnUiThread(new Runnable(arg2) {
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.performContextMenuAction(this.val$id);
            }
        });
        return 1;
    }

    public boolean performEditorAction(int arg2) {
        ThreadUtils.postOnUiThread(new Runnable(arg2) {
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.performEditorAction(this.val$actionCode);
            }
        });
        return 1;
    }

    public boolean performPrivateCommand(String arg1, Bundle arg2) {
        return 0;
    }

    private void processPendingInputStates() {
        this.assertOnImeThread();
        while(true) {
            Object v0 = this.mQueue.poll();
            if(v0 == null) {
                return;
            }

            if(((TextInputState)v0).shouldUnblock()) {
                continue;
            }

            this.updateSelection(((TextInputState)v0));
        }
    }

    public boolean reportFullscreenMode(boolean arg1) {
        return 0;
    }

    private TextInputState requestAndWaitForTextInputState() {
        if(this.runningOnUiThread()) {
            Log.w("cr_Ime", "InputConnection API is not called on IME thread. Returning cached result.", new Object[0]);
            return this.mCachedTextInputState;
        }

        this.assertOnImeThread();
        ThreadUtils.postOnUiThread(this.mRequestTextInputStateUpdate);
        return this.blockAndGetStateUpdate();
    }

    public boolean requestCursorUpdates(int arg2) {
        ThreadUtils.postOnUiThread(new Runnable(arg2) {
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.onRequestCursorUpdates(this.val$cursorUpdateMode);
            }
        });
        return 1;
    }

    void resetOnUiThread() {
        ImeUtils.checkOnUiThread();
        this.mHandler.post(new Runnable() {
            public void run() {
                ThreadedInputConnection.this.mNumNestedBatchEdits = 0;
                ThreadedInputConnection.this.mPendingAccent = 0;
                ThreadedInputConnection.this.mCurrentExtractedTextRequestToken = 0;
                ThreadedInputConnection.this.mShouldUpdateExtractedText = false;
            }
        });
    }

    @VisibleForTesting protected boolean runningOnUiThread() {
        return ThreadUtils.runningOnUiThread();
    }

    public boolean sendKeyEvent(KeyEvent arg2) {
        ThreadUtils.postOnUiThread(new Runnable(arg2) {
            public void run() {
                if(ThreadedInputConnection.this.handleCombiningAccentOnUiThread(this.val$event)) {
                    return;
                }

                ThreadedInputConnection.this.mImeAdapter.sendKeyEvent(this.val$event);
            }
        });
        this.notifyUserAction();
        return 1;
    }

    public boolean sendKeyEventOnUiThread(KeyEvent arg3) {
        ImeUtils.checkOnUiThread();
        this.mHandler.post(new Runnable(arg3) {
            public void run() {
                ThreadedInputConnection.this.sendKeyEvent(this.val$event);
            }
        });
        return 1;
    }

    @VisibleForTesting public void setCombiningAccentOnUiThread(int arg1) {
        this.mPendingAccent = arg1;
    }

    public boolean setComposingRegion(int arg2, int arg3) {
        ThreadUtils.postOnUiThread(new Runnable(arg2, arg3) {
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.setComposingRegion(this.val$start, this.val$end);
            }
        });
        return 1;
    }

    public boolean setComposingText(CharSequence arg2, int arg3) {
        if(arg2 == null) {
            return 0;
        }

        return this.updateComposingText(arg2, arg3, false);
    }

    public boolean setSelection(int arg2, int arg3) {
        ThreadUtils.postOnUiThread(new Runnable(arg2, arg3) {
            public void run() {
                ThreadedInputConnection.this.mImeAdapter.setEditableSelectionOffsets(this.val$start, this.val$end);
            }
        });
        return 1;
    }

    @VisibleForTesting public void unblockOnUiThread() {
        ImeUtils.checkOnUiThread();
        this.addToQueueOnUiThread(ThreadedInputConnection.UNBLOCKER);
        this.mHandler.post(this.mProcessPendingInputStatesRunnable);
    }

    @VisibleForTesting public boolean updateComposingText(CharSequence arg2, int arg3, boolean arg4) {
        ThreadUtils.postOnUiThread(new Runnable(arg2, arg3, arg4) {
            public void run() {
                ThreadedInputConnection.this.updateComposingTextOnUiThread(this.val$text, this.val$newCursorPosition, this.val$isPendingAccent);
            }
        });
        this.notifyUserAction();
        return 1;
    }

    private void updateComposingTextOnUiThread(CharSequence arg3, int arg4, boolean arg5) {
        int v5 = arg5 ? this.mPendingAccent | 0x80000000 : 0;
        this.cancelCombiningAccentOnUiThread();
        this.mImeAdapter.sendCompositionToNative(arg3, arg4, false, v5);
    }

    private void updateSelection(TextInputState arg5) {
        if(arg5 == null) {
            return;
        }

        this.assertOnImeThread();
        if(this.mNumNestedBatchEdits != 0) {
            return;
        }

        Range v0 = arg5.selection();
        Range v1 = arg5.composition();
        if(this.mShouldUpdateExtractedText) {
            this.mImeAdapter.updateExtractedText(this.mCurrentExtractedTextRequestToken, this.convertToExtractedText(arg5));
        }

        this.mImeAdapter.updateSelection(v0.start(), v0.end(), v1.start(), v1.end());
    }

    public void updateStateOnUiThread(String arg8, int arg9, int arg10, int arg11, int arg12, boolean arg13, boolean arg14) {
        ImeUtils.checkOnUiThread();
        this.mCachedTextInputState = new TextInputState(arg8, new Range(arg9, arg10), new Range(arg11, arg12), arg13, arg14);
        this.addToQueueOnUiThread(this.mCachedTextInputState);
        if(!arg14) {
            this.mHandler.post(this.mProcessPendingInputStatesRunnable);
        }
    }
}

