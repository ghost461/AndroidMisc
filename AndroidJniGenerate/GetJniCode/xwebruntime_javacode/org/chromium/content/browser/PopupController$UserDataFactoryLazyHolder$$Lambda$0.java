package org.chromium.content.browser;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class PopupController$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        PopupController$UserDataFactoryLazyHolder$$Lambda$0.$instance = new PopupController$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private PopupController$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg1) {
        return UserDataFactoryLazyHolder.lambda$static$0$PopupController$UserDataFactoryLazyHolder(arg1);
    }
}

