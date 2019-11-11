package org.chromium.content.browser;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class GestureListenerManagerImpl$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        GestureListenerManagerImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance = new GestureListenerManagerImpl$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private GestureListenerManagerImpl$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new GestureListenerManagerImpl(arg2);
    }
}

