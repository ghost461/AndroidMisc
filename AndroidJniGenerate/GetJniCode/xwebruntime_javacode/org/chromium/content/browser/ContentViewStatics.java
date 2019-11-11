package org.chromium.content.browser;

import org.chromium.net.ProxyChangeListener;

public class ContentViewStatics {
    public ContentViewStatics() {
        super();
    }

    public static void disablePlatformNotifications() {
        ProxyChangeListener.setEnabled(false);
    }

    public static void enablePlatformNotifications() {
        ProxyChangeListener.setEnabled(true);
    }

    private static native void nativeSetWebKitSharedTimersSuspended(boolean arg0) {
    }

    public static void setWebKitSharedTimersSuspended(boolean arg0) {
        ContentViewStatics.nativeSetWebKitSharedTimersSuspended(arg0);
    }
}

