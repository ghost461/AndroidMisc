package org.chromium.content.browser;

public interface ExtendCanvasCallback {
    void onAsycResultCallback(int arg1, int arg2, String arg3);

    void onCanvasTouch(String arg1, int arg2, String arg3);

    void onSendJsonMessage(String arg1);
}

