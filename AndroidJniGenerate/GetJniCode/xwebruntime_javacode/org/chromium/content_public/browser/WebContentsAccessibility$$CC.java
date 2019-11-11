package org.chromium.content_public.browser;

import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

public abstract class WebContentsAccessibility$$CC {
    public static WebContentsAccessibility fromWebContents$$STATIC$$(WebContents arg0) {
        return WebContentsAccessibilityImpl.fromWebContents(arg0);
    }
}

