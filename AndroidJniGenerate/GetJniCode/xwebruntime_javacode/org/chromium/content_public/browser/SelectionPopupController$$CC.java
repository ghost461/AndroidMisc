package org.chromium.content_public.browser;

import android.content.Context;
import android.view.View;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.ui.base.WindowAndroid;

public abstract class SelectionPopupController$$CC {
    public static SelectionPopupController createForTesting$$STATIC$$(Context arg1, WindowAndroid arg2, WebContents arg3, View arg4) {
        return SelectionPopupControllerImpl.createForTesting(arg1, arg2, arg3, arg4, null);
    }

    public static SelectionPopupController fromWebContents$$STATIC$$(WebContents arg0) {
        return SelectionPopupControllerImpl.fromWebContents(arg0);
    }

    public static void setShouldGetReadbackViewFromWindowAndroid$$STATIC$$() {
        SelectionPopupControllerImpl.setShouldGetReadbackViewFromWindowAndroid();
    }
}

