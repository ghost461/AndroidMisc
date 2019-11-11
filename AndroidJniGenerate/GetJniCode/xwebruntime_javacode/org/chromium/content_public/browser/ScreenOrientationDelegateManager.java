package org.chromium.content_public.browser;

import org.chromium.content.browser.ScreenOrientationProvider;

public final class ScreenOrientationDelegateManager {
    private ScreenOrientationDelegateManager() {
        super();
    }

    public static void setOrientationDelegate(ScreenOrientationDelegate arg0) {
        ScreenOrientationProvider.setOrientationDelegate(arg0);
    }
}

