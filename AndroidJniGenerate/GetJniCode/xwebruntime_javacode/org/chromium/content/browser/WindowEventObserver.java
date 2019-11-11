package org.chromium.content.browser;

public interface WindowEventObserver {
    void onAttachedToWindow();

    void onDetachedFromWindow();

    void onWindowFocusChanged(boolean arg1);
}

