package org.chromium.content_public.browser;

public interface GestureListenerManager {
    void addListener(GestureStateListener arg1);

    boolean isScrollInProgress();

    void removeListener(GestureStateListener arg1);
}

