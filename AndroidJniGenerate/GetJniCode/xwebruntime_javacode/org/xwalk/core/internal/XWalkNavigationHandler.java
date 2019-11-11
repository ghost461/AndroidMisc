package org.xwalk.core.internal;

import org.chromium.components.navigation_interception.NavigationParams;

interface XWalkNavigationHandler {
    String getFallbackUrl();

    boolean handleNavigation(NavigationParams arg1);

    void resetFallbackUrl();
}

