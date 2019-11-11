package org.chromium.content.browser.input;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import org.chromium.base.VisibleForTesting;

public interface ChromiumBaseInputConnection extends InputConnection {
    public interface Factory {
        @VisibleForTesting Handler getHandler();

        ChromiumBaseInputConnection initializeAndGet(View arg1, ImeAdapterImpl arg2, int arg3, int arg4, int arg5, int arg6, int arg7, EditorInfo arg8);

        void onViewAttachedToWindow();

        void onViewDetachedFromWindow();

        void onViewFocusChanged(boolean arg1);

        void onWindowFocusChanged(boolean arg1);
    }

    @VisibleForTesting Handler getHandler();

    void onRestartInputOnUiThread();

    boolean sendKeyEventOnUiThread(KeyEvent arg1);

    void unblockOnUiThread();

    void updateStateOnUiThread(String arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7);
}

