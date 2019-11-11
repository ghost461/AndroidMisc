package org.chromium.content.browser.input;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class TextSuggestionHost$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        TextSuggestionHost$UserDataFactoryLazyHolder$$Lambda$0.$instance = new TextSuggestionHost$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private TextSuggestionHost$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg2) {
        return new TextSuggestionHost(arg2);
    }
}

