package org.chromium.content.browser;

import org.chromium.content_public.browser.WebContents$UserDataFactory;
import org.chromium.content_public.browser.WebContents;

final class JoystickHandler$UserDataFactoryLazyHolder$$Lambda$0 implements UserDataFactory {
    static final UserDataFactory $instance;

    static {
        JoystickHandler$UserDataFactoryLazyHolder$$Lambda$0.$instance = new JoystickHandler$UserDataFactoryLazyHolder$$Lambda$0();
    }

    private JoystickHandler$UserDataFactoryLazyHolder$$Lambda$0() {
        super();
    }

    public Object create(WebContents arg1) {
        return UserDataFactoryLazyHolder.lambda$static$0$JoystickHandler$UserDataFactoryLazyHolder(arg1);
    }
}

