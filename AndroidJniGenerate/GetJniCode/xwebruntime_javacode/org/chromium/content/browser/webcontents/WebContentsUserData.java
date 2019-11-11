package org.chromium.content.browser.webcontents;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

public final class WebContentsUserData {
    private final Object mObject;

    WebContentsUserData(Object arg1) {
        super();
        this.mObject = arg1;
    }

    public static Object fromWebContents(WebContents arg0, Class arg1, UserDataFactory arg2) {
        return ((WebContentsImpl)arg0).getOrSetUserData(arg1, arg2);
    }

    Object getObject() {
        return this.mObject;
    }
}

