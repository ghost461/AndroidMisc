package org.chromium.content.browser;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class TapDisambiguator$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        TapDisambiguator$UserDataFactoryLazyHolder$$Lambda$0.$instance = new TapDisambiguator$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private TapDisambiguator$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new TapDisambiguator(arg2);
    }
}

