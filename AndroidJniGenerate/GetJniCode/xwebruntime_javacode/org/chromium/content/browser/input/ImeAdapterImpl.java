package org.chromium.content.browser.input;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.SuggestionSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputConnection;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.WindowEventObserver;
import org.chromium.content.browser.picker.InputDialogContainer;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.ImeAdapter;
import org.chromium.content_public.browser.ImeEventObserver;
import org.chromium.content_public.browser.InputMethodManagerWrapper;
import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewUtils;

@JNINamespace(value="content") public class ImeAdapterImpl implements WindowEventObserver, ImeAdapter {
    @SuppressLint(value={"ParcelCreator"}) class ShowKeyboardResultReceiver extends ResultReceiver {
        private final WeakReference mImeAdapter;

        public ShowKeyboardResultReceiver(ImeAdapterImpl arg1, Handler arg2) {
            super(arg2);
            this.mImeAdapter = new WeakReference(arg1);
        }

        public void onReceiveResult(int arg1, Bundle arg2) {
            Object v2 = this.mImeAdapter.get();
            if(v2 == null) {
                return;
            }

            ((ImeAdapterImpl)v2).onShowKeyboardReceiveResult(arg1);
        }
    }

    final class UserDataFactoryLazyHolder {
        private static final UserDataFactory INSTANCE;

        static {
            UserDataFactoryLazyHolder.INSTANCE = ImeAdapterImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance;
        }

        private UserDataFactoryLazyHolder() {
            super();
        }

        static UserDataFactory access$000() {
            return UserDataFactoryLazyHolder.INSTANCE;
        }
    }

    public static final int COMPOSITION_KEY_CODE = 0xE5;
    private static final boolean DEBUG_LOGS = false;
    private static final int DEFAULT_SUGGESTION_SPAN_COLOR = -2000107320;
    private static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 0x1000000;
    private static final float SUGGESTION_HIGHLIGHT_BACKGROUND_TRANSPARENCY = 0.4f;
    private static final String TAG = "cr_Ime";
    private View mContainerView;
    private Configuration mCurrentConfig;
    private CursorAnchorInfoController mCursorAnchorInfoController;
    private final List mEventObservers;
    private final Rect mFocusPreOSKViewportRect;
    private ChromiumBaseInputConnection mInputConnection;
    private Factory mInputConnectionFactory;
    private InputMethodManagerWrapper mInputMethodManagerWrapper;
    private boolean mIsConnected;
    private int mLastCompositionEnd;
    private int mLastCompositionStart;
    private int mLastSelectionEnd;
    private int mLastSelectionStart;
    private String mLastText;
    private long mNativeImeAdapterAndroid;
    private boolean mNodeEditable;
    private boolean mNodePassword;
    private boolean mRestartInputOnNextStateUpdate;
    private ShowKeyboardResultReceiver mShowKeyboardResultReceiver;
    private int mTextInputFlags;
    private int mTextInputMode;
    private int mTextInputType;
    private final WebContentsImpl mWebContents;

    static {
    }

    public ImeAdapterImpl(WebContents arg2) {
        super();
        this.mEventObservers = new ArrayList();
        this.mTextInputType = 0;
        this.mTextInputMode = 0;
        this.mFocusPreOSKViewportRect = new Rect();
        this.mWebContents = ((WebContentsImpl)arg2);
    }

    static String access$100(ImeAdapterImpl arg0) {
        return arg0.mLastText;
    }

    static int access$200(ImeAdapterImpl arg0) {
        return arg0.mLastSelectionStart;
    }

    static int access$300(ImeAdapterImpl arg0) {
        return arg0.mLastSelectionEnd;
    }

    static int access$400(ImeAdapterImpl arg0) {
        return arg0.mLastCompositionStart;
    }

    static int access$500(ImeAdapterImpl arg0) {
        return arg0.mLastCompositionEnd;
    }

    public void addEventObserver(ImeEventObserver arg2) {
        this.mEventObservers.add(arg2);
    }

    private void advanceFocusInForm(int arg6) {
        if(this.mNativeImeAdapterAndroid == 0) {
            return;
        }

        this.nativeAdvanceFocusInForm(this.mNativeImeAdapterAndroid, arg6);
    }

    @CalledByNative private void cancelComposition() {
        if(this.mInputConnection != null) {
            this.restartInput();
        }
    }

    public void cancelRequestToScrollFocusedEditableNodeIntoView() {
        this.mFocusPreOSKViewportRect.setEmpty();
    }

    public static ImeAdapterImpl create(WebContents arg2, View arg3, InputMethodManagerWrapper arg4) {
        Object v2 = arg2.getOrSetUserData(ImeAdapterImpl.class, UserDataFactoryLazyHolder.access$000());
        ((ImeAdapterImpl)v2).init(arg3, arg4);
        return ((ImeAdapterImpl)v2);
    }

    public static InputMethodManagerWrapper createDefaultInputMethodManagerWrapper(Context arg1) {
        return new InputMethodManagerWrapperImpl(arg1);
    }

    private void createInputConnectionFactory() {
        if(this.mInputConnectionFactory != null) {
            return;
        }

        this.mInputConnectionFactory = new ThreadedInputConnectionFactory(this.mInputMethodManagerWrapper);
    }

    boolean deleteSurroundingText(int arg14, int arg15) {
        this.onImeEvent();
        if(!this.isValid()) {
            return 0;
        }

        this.nativeSendKeyEvent(this.mNativeImeAdapterAndroid, null, 7, 0, SystemClock.uptimeMillis(), 0xE5, 0, false, 0);
        this.nativeDeleteSurroundingText(this.mNativeImeAdapterAndroid, arg14, arg15);
        this.nativeSendKeyEvent(this.mNativeImeAdapterAndroid, null, 9, 0, SystemClock.uptimeMillis(), 0xE5, 0, false, 0);
        return 1;
    }

    boolean deleteSurroundingTextInCodePoints(int arg14, int arg15) {
        this.onImeEvent();
        if(!this.isValid()) {
            return 0;
        }

        this.nativeSendKeyEvent(this.mNativeImeAdapterAndroid, null, 7, 0, SystemClock.uptimeMillis(), 0xE5, 0, false, 0);
        this.nativeDeleteSurroundingTextInCodePoints(this.mNativeImeAdapterAndroid, arg14, arg15);
        this.nativeSendKeyEvent(this.mNativeImeAdapterAndroid, null, 9, 0, SystemClock.uptimeMillis(), 0xE5, 0, false, 0);
        return 1;
    }

    @CalledByNative private void destroy() {
        this.resetAndHideKeyboard();
        this.mNativeImeAdapterAndroid = 0;
        this.mIsConnected = false;
        if(this.mCursorAnchorInfoController != null) {
            this.mCursorAnchorInfoController.focusedNodeChanged(false);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        if(this.mInputConnection != null) {
            return this.mInputConnection.sendKeyEventOnUiThread(arg2);
        }

        return this.sendKeyEvent(arg2);
    }

    @VisibleForTesting boolean finishComposingText() {
        if(!this.isValid()) {
            return 0;
        }

        this.nativeFinishComposingText(this.mNativeImeAdapterAndroid);
        return 1;
    }

    private boolean focusedNodeAllowsSoftKeyboard() {
        boolean v1 = true;
        if(this.mTextInputType == 0 || this.mTextInputMode == 1) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    @CalledByNative private void focusedNodeChanged(boolean arg2) {
        if(this.mCursorAnchorInfoController != null) {
            this.mCursorAnchorInfoController.focusedNodeChanged(arg2);
        }

        if(this.mTextInputType != 0 && this.mInputConnection != null && (arg2)) {
            this.mRestartInputOnNextStateUpdate = true;
        }
    }

    private boolean focusedNodeEditable() {
        boolean v0 = this.mTextInputType != 0 ? true : false;
        return v0;
    }

    public static ImeAdapterImpl fromWebContents(WebContents arg2) {
        return arg2.getOrSetUserData(ImeAdapterImpl.class, null);
    }

    public InputConnection getActiveInputConnection() {
        return this.mInputConnection;
    }

    @VisibleForTesting Factory getInputConnectionFactoryForTest() {
        return this.mInputConnectionFactory;
    }

    @VisibleForTesting public InputConnection getInputConnectionForTest() {
        return this.mInputConnection;
    }

    private static int getModifiers(int arg2) {
        int v0 = (arg2 & 1) != 0 ? 1 : 0;
        if((arg2 & 2) != 0) {
            v0 |= 4;
        }

        if((arg2 & 0x1000) != 0) {
            v0 |= 2;
        }

        if((0x100000 & arg2) != 0) {
            v0 |= 0x200;
        }

        if((arg2 & 0x200000) != 0) {
            v0 |= 0x400;
        }

        return v0;
    }

    public ResultReceiver getNewShowKeyboardReceiver() {
        if(this.mShowKeyboardResultReceiver == null) {
            this.mShowKeyboardResultReceiver = new ShowKeyboardResultReceiver(this, new Handler());
        }

        return this.mShowKeyboardResultReceiver;
    }

    private int getUnderlineColorForSuggestionSpan(SuggestionSpan arg6) {
        int v0 = -2000107320;
        try {
            return SuggestionSpan.class.getMethod("getUnderlineColor").invoke(arg6).intValue();
        }
        catch(NoSuchMethodException ) {
            return v0;
        }
        catch(InvocationTargetException ) {
            return v0;
        }
        catch(IllegalAccessException ) {
            return v0;
        }
    }

    private void hideKeyboard() {
        if(!this.isValid()) {
            return;
        }

        View v0 = this.mContainerView;
        if(this.mInputMethodManagerWrapper.isActive(v0)) {
            this.mInputMethodManagerWrapper.hideSoftInputFromWindow(v0.getWindowToken(), 0, null);
        }

        if(!this.focusedNodeEditable() && this.mInputConnection != null) {
            ChromiumBaseInputConnection v0_1 = this.mInputConnection;
            this.restartInput();
            v0_1.unblockOnUiThread();
        }
    }

    private void init(View arg2, InputMethodManagerWrapper arg3) {
        this.mContainerView = arg2;
        this.mInputMethodManagerWrapper = arg3;
        this.mCurrentConfig = new Configuration(this.mContainerView.getResources().getConfiguration());
        this.mCursorAnchorInfoController = Build$VERSION.SDK_INT >= 21 ? CursorAnchorInfoController.create(arg3, new ComposingTextDelegate() {
            public int getComposingTextEnd() {
                return ImeAdapterImpl.this.mLastCompositionEnd;
            }

            public int getComposingTextStart() {
                return ImeAdapterImpl.this.mLastCompositionStart;
            }

            public int getSelectionEnd() {
                return ImeAdapterImpl.this.mLastSelectionEnd;
            }

            public int getSelectionStart() {
                return ImeAdapterImpl.this.mLastSelectionStart;
            }

            public CharSequence getText() {
                return ImeAdapterImpl.this.mLastText;
            }
        }) : null;
        this.mNativeImeAdapterAndroid = this.nativeInit(this.mWebContents);
    }

    private boolean initialized() {
        boolean v0 = this.mNativeImeAdapterAndroid != 0 ? true : false;
        return v0;
    }

    private boolean isHardwareKeyboardAttached() {
        boolean v1 = true;
        if(this.mCurrentConfig.keyboard != 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private static boolean isTextInputType(int arg0) {
        boolean v0 = arg0 == 0 || (InputDialogContainer.isDialogInputType(arg0)) ? false : true;
        return v0;
    }

    private boolean isValid() {
        boolean v0 = this.mNativeImeAdapterAndroid == 0 || !this.mIsConnected ? false : true;
        return v0;
    }

    private native void nativeAdvanceFocusInForm(long arg1, int arg2) {
    }

    private static native void nativeAppendBackgroundColorSpan(long arg0, int arg1, int arg2, int arg3) {
    }

    private static native void nativeAppendSuggestionSpan(long arg0, int arg1, int arg2, boolean arg3, int arg4, int arg5, String[] arg6) {
    }

    private static native void nativeAppendUnderlineSpan(long arg0, int arg1, int arg2) {
    }

    private native void nativeCommitText(long arg1, CharSequence arg2, String arg3, int arg4) {
    }

    private native void nativeDeleteSurroundingText(long arg1, int arg2, int arg3) {
    }

    private native void nativeDeleteSurroundingTextInCodePoints(long arg1, int arg2, int arg3) {
    }

    private native void nativeFinishComposingText(long arg1) {
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native void nativeRequestCursorUpdate(long arg1, boolean arg2, boolean arg3) {
    }

    private native boolean nativeRequestTextInputStateUpdate(long arg1) {
    }

    private native boolean nativeSendKeyEvent(long arg1, KeyEvent arg2, int arg3, int arg4, long arg5, int arg6, int arg7, boolean arg8, int arg9) {
    }

    private native void nativeSetComposingRegion(long arg1, int arg2, int arg3) {
    }

    private native void nativeSetComposingText(long arg1, CharSequence arg2, String arg3, int arg4) {
    }

    private native void nativeSetEditableSelectionOffsets(long arg1, int arg2, int arg3) {
    }

    void notifyUserAction() {
        this.mInputMethodManagerWrapper.notifyUserAction();
    }

    public void onAttachedToWindow() {
        if(this.mInputConnectionFactory != null) {
            this.mInputConnectionFactory.onViewAttachedToWindow();
        }
    }

    public boolean onCheckIsTextEditor() {
        return ImeAdapterImpl.isTextInputType(this.mTextInputType);
    }

    @CalledByNative private void onConnectedToRenderProcess() {
        this.mIsConnected = true;
        this.createInputConnectionFactory();
        this.resetAndHideKeyboard();
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        boolean v0 = this.mWebContents == null || (this.mWebContents.isIncognito()) ? false : true;
        return this.onCreateInputConnection(arg2, v0);
    }

    public ChromiumBaseInputConnection onCreateInputConnection(EditorInfo arg11, boolean arg12) {
        arg11.imeOptions = 0x12000000;
        if(!arg12) {
            arg11.imeOptions |= 0x1000000;
        }

        ChromiumBaseInputConnection v0 = null;
        if(!this.focusedNodeEditable()) {
            this.setInputConnection(v0);
            return v0;
        }

        if(this.mInputConnectionFactory == null) {
            return v0;
        }

        this.setInputConnection(this.mInputConnectionFactory.initializeAndGet(this.mContainerView, this, this.mTextInputType, this.mTextInputFlags, this.mTextInputMode, this.mLastSelectionStart, this.mLastSelectionEnd, arg11));
        if(this.mCursorAnchorInfoController != null) {
            this.mCursorAnchorInfoController.onRequestCursorUpdates(false, false, this.mContainerView);
        }

        if(this.isValid()) {
            this.nativeRequestCursorUpdate(this.mNativeImeAdapterAndroid, false, false);
        }

        return this.mInputConnection;
    }

    public void onDetachedFromWindow() {
        this.resetAndHideKeyboard();
        if(this.mInputConnectionFactory != null) {
            this.mInputConnectionFactory.onViewDetachedFromWindow();
        }
    }

    private void onImeEvent() {
        Iterator v0 = this.mEventObservers.iterator();
        while(v0.hasNext()) {
            v0.next().onImeEvent();
        }

        if(this.mNodeEditable) {
            this.mWebContents.dismissTextHandles();
        }
    }

    public void onKeyboardConfigurationChanged(Configuration arg3) {
        if(!this.isValid()) {
            return;
        }

        if(this.mCurrentConfig.keyboard == arg3.keyboard && this.mCurrentConfig.keyboardHidden == arg3.keyboardHidden && this.mCurrentConfig.hardKeyboardHidden == arg3.hardKeyboardHidden) {
            return;
        }

        this.mCurrentConfig = new Configuration(arg3);
        if(this.focusedNodeAllowsSoftKeyboard()) {
            this.restartInput();
            this.showSoftKeyboard();
        }
        else if(this.focusedNodeEditable()) {
            this.restartInput();
            if(!this.isHardwareKeyboardAttached()) {
                this.hideKeyboard();
            }
            else {
                this.showSoftKeyboard();
            }
        }
    }

    public boolean onRequestCursorUpdates(int arg6) {
        boolean v2 = true;
        boolean v0 = (arg6 & 1) != 0 ? true : false;
        if((arg6 & 2) != 0) {
        }
        else {
            v2 = false;
        }

        if(this.isValid()) {
            this.nativeRequestCursorUpdate(this.mNativeImeAdapterAndroid, v0, v2);
        }

        if(this.mCursorAnchorInfoController == null) {
            return 0;
        }

        return this.mCursorAnchorInfoController.onRequestCursorUpdates(v0, v2, this.mContainerView);
    }

    public void onShowKeyboardReceiveResult(int arg2) {
        if(arg2 == 2) {
            this.mContainerView.getWindowVisibleDisplayFrame(this.mFocusPreOSKViewportRect);
        }
        else if((ViewUtils.hasFocus(this.mContainerView)) && arg2 == 0) {
            this.mWebContents.scrollFocusedEditableNodeIntoView();
        }
    }

    public void onViewFocusChanged(boolean arg1, boolean arg2) {
        if(!arg1 && (arg2)) {
            this.resetAndHideKeyboard();
        }

        if(this.mInputConnectionFactory != null) {
            this.mInputConnectionFactory.onViewFocusChanged(arg1);
        }
    }

    public void onWindowFocusChanged(boolean arg2) {
        if(this.mInputConnectionFactory != null) {
            this.mInputConnectionFactory.onWindowFocusChanged(arg2);
        }
    }

    boolean performContextMenuAction(int arg2) {
        switch(arg2) {
            case 16908319: {
                goto label_13;
            }
            case 16908320: {
                goto label_10;
            }
            case 16908321: {
                goto label_7;
            }
            case 16908322: {
                goto label_4;
            }
        }

        return 0;
    label_4:
        this.mWebContents.paste();
        return 1;
    label_7:
        this.mWebContents.copy();
        return 1;
    label_10:
        this.mWebContents.cut();
        return 1;
    label_13:
        this.mWebContents.selectAll();
        return 1;
    }

    boolean performEditorAction(int arg3) {
        if(!this.isValid()) {
            return 0;
        }

        if(arg3 == 5) {
            this.advanceFocusInForm(1);
        }
        else if(arg3 != 7) {
            this.sendSyntheticKeyPress(66, 22);
        }
        else {
            this.advanceFocusInForm(2);
        }

        return 1;
    }

    @CalledByNative private void populateImeTextSpansFromJava(CharSequence arg17, long arg18) {
        CharSequence v0 = arg17;
        long v8 = arg18;
        if(!(v0 instanceof SpannableString)) {
            return;
        }

        CharSequence v10 = v0;
        Object[] v12 = ((SpannableString)v10).getSpans(0, arg17.length(), CharacterStyle.class);
        int v13 = v12.length;
        int v14;
        for(v14 = 0; v14 < v13; ++v14) {
            Object v0_1 = v12[v14];
            if((v0_1 instanceof BackgroundColorSpan)) {
                ImeAdapterImpl.nativeAppendBackgroundColorSpan(v8, ((SpannableString)v10).getSpanStart(v0_1), ((SpannableString)v10).getSpanEnd(v0_1), ((BackgroundColorSpan)v0_1).getBackgroundColor());
            }
            else if((v0_1 instanceof UnderlineSpan)) {
                ImeAdapterImpl.nativeAppendUnderlineSpan(v8, ((SpannableString)v10).getSpanStart(v0_1), ((SpannableString)v10).getSpanEnd(v0_1));
            }
            else if((v0_1 instanceof SuggestionSpan)) {
                boolean v4 = (((SuggestionSpan)v0_1).getFlags() & 2) != 0 ? true : false;
                if(((SuggestionSpan)v0_1).getFlags() != 1 && !v4) {
                    goto label_58;
                }

                int v5 = this.getUnderlineColorForSuggestionSpan(((SuggestionSpan)v0_1));
                ImeAdapterImpl.nativeAppendSuggestionSpan(v8, ((SpannableString)v10).getSpanStart(v0_1), ((SpannableString)v10).getSpanEnd(v0_1), v4, v5, (0xFFFFFF & v5) + ((((int)((((float)Color.alpha(v5))) * 0.4f))) << 24), ((SuggestionSpan)v0_1).getSuggestions());
            }

        label_58:
        }
    }

    public void removeEventObserver(ImeEventObserver arg2) {
        this.mEventObservers.remove(arg2);
    }

    boolean requestTextInputStateUpdate() {
        if(!this.isValid()) {
            return 0;
        }

        if(this.mInputConnection == null) {
            return 0;
        }

        return this.nativeRequestTextInputStateUpdate(this.mNativeImeAdapterAndroid);
    }

    public void resetAndHideKeyboard() {
        this.mTextInputType = 0;
        this.mTextInputFlags = 0;
        this.mTextInputMode = 0;
        this.mRestartInputOnNextStateUpdate = false;
        this.hideKeyboard();
    }

    void restartInput() {
        if(!this.isValid()) {
            return;
        }

        this.mInputMethodManagerWrapper.restartInput(this.mContainerView);
        if(this.mInputConnection != null) {
            this.mInputConnection.onRestartInputOnUiThread();
        }
    }

    boolean sendCompositionToNative(CharSequence arg16, int arg17, boolean arg18, int arg19) {
        ImeAdapterImpl v12 = this;
        if(!v12.isValid()) {
            return 0;
        }

        v12.onImeEvent();
        long v13 = SystemClock.uptimeMillis();
        v12.nativeSendKeyEvent(v12.mNativeImeAdapterAndroid, null, 7, 0, v13, 0xE5, 0, false, arg19);
        if(arg18) {
            v12.nativeCommitText(v12.mNativeImeAdapterAndroid, arg16, arg16.toString(), arg17);
        }
        else {
            v12.nativeSetComposingText(v12.mNativeImeAdapterAndroid, arg16, arg16.toString(), arg17);
        }

        v12.nativeSendKeyEvent(v12.mNativeImeAdapterAndroid, null, 9, 0, v13, 0xE5, 0, false, arg19);
        return 1;
    }

    boolean sendKeyEvent(KeyEvent arg14) {
        int v5;
        if(!this.isValid()) {
            return 0;
        }

        int v0 = arg14.getAction();
        if(v0 == 0) {
            v5 = 8;
        }
        else if(v0 == 1) {
            v5 = 9;
        }
        else {
            return 0;
        }

        Iterator v0_1 = this.mEventObservers.iterator();
        while(v0_1.hasNext()) {
            v0_1.next().onBeforeSendKeyEvent(arg14);
        }

        this.onImeEvent();
        return this.nativeSendKeyEvent(this.mNativeImeAdapterAndroid, arg14, v5, ImeAdapterImpl.getModifiers(arg14.getMetaState()), arg14.getEventTime(), arg14.getKeyCode(), arg14.getScanCode(), false, arg14.getUnicodeChar());
    }

    public void sendSyntheticKeyPress(int arg17, int arg18) {
        long v13 = SystemClock.uptimeMillis();
        this.sendKeyEvent(new KeyEvent(v13, v13, 0, arg17, 0, 0, -1, 0, arg18));
        this.sendKeyEvent(new KeyEvent(v13, v13, 1, arg17, 0, 0, -1, 0, arg18));
    }

    @CalledByNative private void setCharacterBounds(float[] arg3) {
        if(this.mCursorAnchorInfoController == null) {
            return;
        }

        this.mCursorAnchorInfoController.setCompositionCharacterBounds(arg3, this.mContainerView);
    }

    boolean setComposingRegion(int arg3, int arg4) {
        if(!this.isValid()) {
            return 0;
        }

        if(arg3 <= arg4) {
            this.nativeSetComposingRegion(this.mNativeImeAdapterAndroid, arg3, arg4);
        }
        else {
            this.nativeSetComposingRegion(this.mNativeImeAdapterAndroid, arg4, arg3);
        }

        return 1;
    }

    public void setContainerView(View arg1) {
        this.mContainerView = arg1;
    }

    boolean setEditableSelectionOffsets(int arg3, int arg4) {
        if(!this.isValid()) {
            return 0;
        }

        this.nativeSetEditableSelectionOffsets(this.mNativeImeAdapterAndroid, arg3, arg4);
        return 1;
    }

    private void setInputConnection(ChromiumBaseInputConnection arg2) {
        if(this.mInputConnection == arg2) {
            return;
        }

        if(this.mInputConnection != null) {
            this.mInputConnection.unblockOnUiThread();
        }

        this.mInputConnection = arg2;
    }

    @VisibleForTesting void setInputConnectionFactory(Factory arg1) {
        this.mInputConnectionFactory = arg1;
    }

    public void setInputMethodManagerWrapper(InputMethodManagerWrapper arg2) {
        this.mInputMethodManagerWrapper = arg2;
        if(this.mCursorAnchorInfoController != null) {
            this.mCursorAnchorInfoController.setInputMethodManagerWrapper(arg2);
        }
    }

    private void showSoftKeyboard() {
        if(!this.isValid()) {
            return;
        }

        this.mInputMethodManagerWrapper.showSoftInput(this.mContainerView, 0, this.getNewShowKeyboardReceiver());
        if(this.mContainerView.getResources().getConfiguration().keyboard != 1) {
            this.mWebContents.scrollFocusedEditableNodeIntoView();
        }
    }

    @CalledByNative private void updateAfterViewSizeChanged() {
        if(!this.mFocusPreOSKViewportRect.isEmpty()) {
            Rect v0 = new Rect();
            this.mContainerView.getWindowVisibleDisplayFrame(v0);
            if(!v0.equals(this.mFocusPreOSKViewportRect)) {
                if(v0.width() == this.mFocusPreOSKViewportRect.width()) {
                    this.mWebContents.scrollFocusedEditableNodeIntoView();
                }

                this.cancelRequestToScrollFocusedEditableNodeIntoView();
            }
        }
    }

    void updateExtractedText(int arg3, ExtractedText arg4) {
        this.mInputMethodManagerWrapper.updateExtractedText(this.mContainerView, arg3, arg4);
    }

    @CalledByNative private void updateFrameInfo(float arg12, float arg13, boolean arg14, boolean arg15, float arg16, float arg17, float arg18) {
        ImeAdapterImpl v0 = this;
        if(v0.mCursorAnchorInfoController == null) {
            return;
        }

        v0.mCursorAnchorInfoController.onUpdateFrameInfo(arg12, arg13, arg14, arg15, arg16, arg17, arg18, v0.mContainerView);
    }

    @CalledByNative private void updateOnTouchDown() {
        this.cancelRequestToScrollFocusedEditableNodeIntoView();
    }

    void updateSelection(int arg7, int arg8, int arg9, int arg10) {
        this.mInputMethodManagerWrapper.updateSelection(this.mContainerView, arg7, arg8, arg9, arg10);
    }

    @CalledByNative private void updateState(int arg16, int arg17, int arg18, boolean arg19, String arg20, int arg21, int arg22, int arg23, int arg24, boolean arg25) {
        int v12;
        int v9;
        ImeAdapterImpl v1 = this;
        int v2 = arg16;
        int v3 = arg18;
        String v4 = arg20;
        int v5 = arg21;
        int v6 = arg22;
        int v7 = arg23;
        int v8 = arg24;
        TraceEvent.begin("ImeAdapter.updateState");
        try {
            boolean v10 = false;
            if(v1.mRestartInputOnNextStateUpdate) {
                v1.mRestartInputOnNextStateUpdate = false;
                v9 = arg17;
                v12 = 1;
            }
            else {
                v9 = arg17;
                v12 = 0;
            }

            v1.mTextInputFlags = v9;
            if(v1.mTextInputMode != v3) {
                v1.mTextInputMode = v3;
                v3 = v3 != 1 || (v1.isHardwareKeyboardAttached()) ? 0 : 1;
                v12 = 1;
            }
            else {
                v3 = 0;
            }

            if(v1.mTextInputType != v2) {
                v1.mTextInputType = v2;
                if(v2 == 0) {
                    v3 = 1;
                }
                else {
                    v12 = 1;
                }
            }

            boolean v9_1 = v1.focusedNodeEditable();
            boolean v2_1 = v2 == 2 ? true : false;
            if(v1.mNodeEditable != v9_1 || v1.mNodePassword != v2_1) {
                Iterator v13 = v1.mEventObservers.iterator();
                while(v13.hasNext()) {
                    v13.next().onNodeAttributeUpdated(v9_1, v2_1);
                }

                v1.mNodeEditable = v9_1;
                v1.mNodePassword = v2_1;
            }

            if(v1.mCursorAnchorInfoController != null && (!TextUtils.equals(v1.mLastText, ((CharSequence)v4)) || v1.mLastSelectionStart != v5 || v1.mLastSelectionEnd != v6 || v1.mLastCompositionStart != v7 || v1.mLastCompositionEnd != v8)) {
                v1.mCursorAnchorInfoController.invalidateLastCursorAnchorInfo();
            }

            v1.mLastText = v4;
            v1.mLastSelectionStart = v5;
            v1.mLastSelectionEnd = v6;
            v1.mLastCompositionStart = v7;
            v1.mLastCompositionEnd = v8;
            if(v3 != 0) {
                v1.hideKeyboard();
            }
            else {
                if(v12 != 0) {
                    v1.restartInput();
                }

                if(!arg19) {
                    goto label_88;
                }

                if(!v1.focusedNodeAllowsSoftKeyboard()) {
                    goto label_88;
                }

                v1.showSoftKeyboard();
            }

        label_88:
            if(v1.mInputConnection != null) {
                if(v1.mTextInputType != 14 && v1.mTextInputType != 15) {
                    v10 = true;
                }

                v1.mInputConnection.updateStateOnUiThread(v4, v5, v6, v7, v8, v10, arg25);
            }
        }
        catch(Throwable v0) {
            goto label_112;
        }

        TraceEvent.end("ImeAdapter.updateState");
        return;
    label_112:
        TraceEvent.end("ImeAdapter.updateState");
        throw v0;
    }
}

