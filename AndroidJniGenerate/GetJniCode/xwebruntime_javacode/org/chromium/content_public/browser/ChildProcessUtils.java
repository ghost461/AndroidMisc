package org.chromium.content_public.browser;

import org.chromium.base.Callback;
import org.chromium.content.browser.ChildProcessLauncherHelper;

public final class ChildProcessUtils {
    private ChildProcessUtils() {
        super();
    }

    public static void getProcessIdsByType(Callback arg0) {
        ChildProcessLauncherHelper.getProcessIdsByType(arg0);
    }
}

