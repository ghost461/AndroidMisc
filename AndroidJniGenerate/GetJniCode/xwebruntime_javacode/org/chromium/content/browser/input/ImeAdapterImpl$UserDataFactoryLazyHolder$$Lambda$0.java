package org.chromium.content.browser.input;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class ImeAdapterImpl$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        ImeAdapterImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance = new ImeAdapterImpl$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private ImeAdapterImpl$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new ImeAdapterImpl(arg2);
    }
}

