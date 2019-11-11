package org.chromium.content.browser.selection;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class SelectionPopupControllerImpl$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        SelectionPopupControllerImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance = new SelectionPopupControllerImpl$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private SelectionPopupControllerImpl$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new SelectionPopupControllerImpl(arg2);
    }
}

