package org.chromium.content.browser.input;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class SelectPopup$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        SelectPopup$UserDataFactoryLazyHolder$$Lambda$0.$instance = new SelectPopup$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private SelectPopup$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new SelectPopup(arg2);
    }
}

