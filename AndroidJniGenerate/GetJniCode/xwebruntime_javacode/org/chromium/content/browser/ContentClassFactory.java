package org.chromium.content.browser;

import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.selection.AdditionalMenuItemProvider;
import org.chromium.content.browser.selection.SelectionInsertionHandleObserver;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl$ReadbackViewCallback;

public class ContentClassFactory {
    private static ContentClassFactory sSingleton;

    protected ContentClassFactory() {
        super();
    }

    public AdditionalMenuItemProvider createAddtionalMenuItemProvider() {
        return null;
    }

    public SelectionInsertionHandleObserver createHandleObserver(ReadbackViewCallback arg1) {
        return null;
    }

    public static ContentClassFactory get() {
        ThreadUtils.assertOnUiThread();
        if(ContentClassFactory.sSingleton == null) {
            ContentClassFactory.sSingleton = new ContentClassFactory();
        }

        return ContentClassFactory.sSingleton;
    }

    public static void set(ContentClassFactory arg0) {
        ThreadUtils.assertOnUiThread();
        ContentClassFactory.sSingleton = arg0;
    }
}

