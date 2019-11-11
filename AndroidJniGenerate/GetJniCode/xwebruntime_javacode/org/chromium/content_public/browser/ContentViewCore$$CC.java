package org.chromium.content_public.browser;

import android.content.Context;
import org.chromium.content.browser.ContentViewCoreImpl;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

public abstract class ContentViewCore$$CC {
    public static ContentViewCore create$$STATIC$$(Context arg0, String arg1, WebContents arg2, ViewAndroidDelegate arg3, InternalAccessDelegate arg4, WindowAndroid arg5) {
        return ContentViewCoreImpl.create(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    public static ContentViewCore fromWebContents$$STATIC$$(WebContents arg0) {
        return ContentViewCoreImpl.fromWebContents(arg0);
    }
}

