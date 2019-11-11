package org.chromium.content.browser.accessibility.captioning;

public interface SystemCaptioningBridge {
    public interface SystemCaptioningBridgeListener {
        void onSystemCaptioningChanged(TextTrackSettings arg1);
    }

    void addListener(SystemCaptioningBridgeListener arg1);

    void removeListener(SystemCaptioningBridgeListener arg1);

    void syncToListener(SystemCaptioningBridgeListener arg1);
}

