package org.chromium.content.browser.input;

import android.content.Context;
import android.os.Build$VERSION;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.InvocationTargetException;
import org.chromium.content_public.browser.InputMethodManagerWrapper;

public class InputMethodManagerWrapperImpl implements InputMethodManagerWrapper {
    private static final boolean DEBUG_LOGS = false;
    private static final String TAG = "cr_IMM";
    private final Context mContext;

    public InputMethodManagerWrapperImpl(Context arg1) {
        super();
        this.mContext = arg1;
    }

    private InputMethodManager getInputMethodManager() {
        return this.mContext.getSystemService("input_method");
    }

    public boolean hideSoftInputFromWindow(IBinder arg3, int arg4, ResultReceiver arg5) {
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskWrites();
        try {
            InputMethodManager v1 = this.getInputMethodManager();
            if(v1 == null) {
                goto label_7;
            }
            else if(!v1.hideSoftInputFromWindow(arg3, arg4, arg5)) {
                goto label_7;
            }
        }
        catch(Throwable v3) {
            StrictMode.setThreadPolicy(v0);
            throw v3;
        }

        boolean v3_1 = true;
        goto label_8;
    label_7:
        v3_1 = false;
    label_8:
        StrictMode.setThreadPolicy(v0);
        return v3_1;
    }

    public boolean isActive(View arg2) {
        InputMethodManager v0 = this.getInputMethodManager();
        boolean v2 = v0 == null || !v0.isActive(arg2) ? false : true;
        return v2;
    }

    public void notifyUserAction() {
        if(Build$VERSION.SDK_INT > 23) {
            return;
        }

        InputMethodManager v0 = this.getInputMethodManager();
        if(v0 == null) {
            return;
        }

        try {
            InputMethodManager.class.getMethod("notifyUserAction").invoke(v0);
            return;
        }
        catch(InvocationTargetException ) {
            return;
        }
    }

    public void restartInput(View arg2) {
        InputMethodManager v0 = this.getInputMethodManager();
        if(v0 == null) {
            return;
        }

        v0.restartInput(arg2);
    }

    public void showSoftInput(View arg3, int arg4, ResultReceiver arg5) {
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskWrites();
        try {
            InputMethodManager v1 = this.getInputMethodManager();
            if(v1 != null) {
                v1.showSoftInput(arg3, arg4, arg5);
            }
        }
        catch(Throwable v3) {
            StrictMode.setThreadPolicy(v0);
            throw v3;
        }

        StrictMode.setThreadPolicy(v0);
    }

    public void updateCursorAnchorInfo(View arg3, CursorAnchorInfo arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            InputMethodManager v0 = this.getInputMethodManager();
            if(v0 == null) {
                return;
            }
            else {
                v0.updateCursorAnchorInfo(arg3, arg4);
            }
        }
    }

    public void updateExtractedText(View arg2, int arg3, ExtractedText arg4) {
        InputMethodManager v0 = this.getInputMethodManager();
        if(v0 == null) {
            return;
        }

        v0.updateExtractedText(arg2, arg3, arg4);
    }

    public void updateSelection(View arg7, int arg8, int arg9, int arg10, int arg11) {
        InputMethodManager v0 = this.getInputMethodManager();
        if(v0 == null) {
            return;
        }

        v0.updateSelection(arg7, arg8, arg9, arg10, arg11);
    }
}

