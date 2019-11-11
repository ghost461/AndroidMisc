package org.chromium.content.browser;

import android.graphics.SurfaceTexture;
import android.view.MotionEvent;

public interface ExtendPluginCallback {
    void onPluginDestroy(String arg1, int arg2);

    void onPluginReady(String arg1, int arg2, SurfaceTexture arg3);

    void onPluginTouch(String arg1, int arg2, MotionEvent arg3);

    void onPluginTouch(String arg1, int arg2, String arg3);

    void onSendJsonMessage(String arg1);
}

