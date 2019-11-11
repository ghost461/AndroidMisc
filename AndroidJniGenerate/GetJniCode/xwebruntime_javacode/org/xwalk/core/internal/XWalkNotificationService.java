package org.xwalk.core.internal;

import android.content.Intent;
import android.graphics.Bitmap;

interface XWalkNotificationService {
    void cancelNotification(int arg1);

    boolean maybeHandleIntent(Intent arg1);

    void setBridge(XWalkContentsClientBridge arg1);

    void showNotification(String arg1, String arg2, String arg3, Bitmap arg4, int arg5);

    void shutdown();
}

