package org.chromium.ui;

import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="ui") class PlatformImeControllerAndroid {
    private int mCompositionEnd;
    private int mCompositionStart;
    private InputConnection mInputConnection;
    private int mInputFlags;
    private final InputMethodManager mInputMethodManager;
    private int mInputType;
    private final long mNativeHandle;
    private int mSelectionEnd;
    private int mSelectionStart;
    private String mText;
    private final PlatformWindowAndroid mWindow;

    static {
    }

    PlatformImeControllerAndroid(PlatformWindowAndroid arg3, long arg4) {
        super();
        this.mInputType = 0;
        this.mInputFlags = 0;
        this.mText = "";
        this.mSelectionStart = 0;
        this.mSelectionEnd = 0;
        this.mCompositionStart = 0;
        this.mCompositionEnd = 0;
        this.mWindow = arg3;
        this.mNativeHandle = arg4;
        this.mInputMethodManager = this.mWindow.getContext().getSystemService("input_method");
        this.nativeInit(this.mNativeHandle);
    }

    private void dismissInput() {
        this.mInputMethodManager.hideSoftInputFromWindow(this.mWindow.getWindowToken(), 0);
    }

    boolean isTextEditorType() {
        boolean v0 = this.mInputType != 0 ? true : false;
        return v0;
    }

    private native void nativeInit(long arg1) {
    }

    InputConnection onCreateInputConnection(EditorInfo arg4) {
        if(this.mInputType == 0) {
            arg4.imeOptions = 0x2000000;
        }

        this.mInputConnection = new BaseInputConnection(this.mWindow, false);
        arg4.actionLabel = null;
        arg4.inputType = 0xA1;
        arg4.imeOptions = 0x12000002;
        return this.mInputConnection;
    }

    @CalledByNative private void setImeVisibility(boolean arg2) {
        if(this.mInputType != 0) {
            if(arg2) {
                this.showKeyboard();
            }
            else {
                this.dismissInput();
            }
        }
    }

    private void showKeyboard() {
        this.mInputMethodManager.showSoftInput(this.mWindow, 0);
    }

    @CalledByNative private void updateTextInputState(int arg1, int arg2, String arg3, int arg4, int arg5, int arg6, int arg7) {
        this.mInputType = arg1;
        this.mInputFlags = arg2;
        this.mText = arg3;
        this.mSelectionStart = arg4;
        this.mSelectionEnd = arg5;
        this.mCompositionStart = arg6;
        this.mCompositionEnd = arg7;
        if(this.mInputType == 0) {
            this.dismissInput();
        }
    }
}

