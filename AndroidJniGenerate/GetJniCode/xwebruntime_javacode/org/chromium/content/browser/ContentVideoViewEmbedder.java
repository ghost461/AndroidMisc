package org.chromium.content.browser;

import android.view.View;

public interface ContentVideoViewEmbedder {
    void enterFullscreenVideo(View arg1, boolean arg2);

    void exitFullscreenVideo();

    void fullscreenVideoLoaded();

    void setSystemUiVisibility(boolean arg1);
}

