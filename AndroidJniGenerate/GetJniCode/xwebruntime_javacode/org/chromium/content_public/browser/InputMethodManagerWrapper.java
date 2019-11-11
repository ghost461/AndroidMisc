package org.chromium.content_public.browser;

import android.annotation.TargetApi;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;

public interface InputMethodManagerWrapper {
    boolean hideSoftInputFromWindow(IBinder arg1, int arg2, ResultReceiver arg3);

    boolean isActive(View arg1);

    void notifyUserAction();

    void restartInput(View arg1);

    void showSoftInput(View arg1, int arg2, ResultReceiver arg3);

    @TargetApi(value=21) void updateCursorAnchorInfo(View arg1, CursorAnchorInfo arg2);

    void updateExtractedText(View arg1, int arg2, ExtractedText arg3);

    void updateSelection(View arg1, int arg2, int arg3, int arg4, int arg5);
}

