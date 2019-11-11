package org.chromium.content.browser;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class ContentViewCoreImpl$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        ContentViewCoreImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance = new ContentViewCoreImpl$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private ContentViewCoreImpl$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new ContentViewCoreImpl(arg2);
    }
}

