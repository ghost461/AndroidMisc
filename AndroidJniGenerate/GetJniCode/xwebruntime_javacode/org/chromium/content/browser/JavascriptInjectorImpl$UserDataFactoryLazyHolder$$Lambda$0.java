package org.chromium.content.browser;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class JavascriptInjectorImpl$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        JavascriptInjectorImpl$UserDataFactoryLazyHolder$$Lambda$0.$instance = new JavascriptInjectorImpl$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private JavascriptInjectorImpl$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new JavascriptInjectorImpl(arg2);
    }
}

