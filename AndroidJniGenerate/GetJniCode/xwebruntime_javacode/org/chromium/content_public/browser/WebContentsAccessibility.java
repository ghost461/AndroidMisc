package org.chromium.content_public.browser;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityNodeProvider;
import org.chromium.base.VisibleForTesting;

public interface WebContentsAccessibility {
    AccessibilityNodeProvider getAccessibilityNodeProvider();

    boolean isAccessibilityEnabled();

    boolean isTouchExplorationEnabled();

    void onAutofillPopupAccessibilityFocusCleared();

    void onAutofillPopupDismissed();

    void onAutofillPopupDisplayed(View arg1);

    @TargetApi(value=23) void onProvideVirtualStructure(ViewStructure arg1, boolean arg2);

    boolean performAction(int arg1, Bundle arg2);

    @VisibleForTesting void setAccessibilityEnabledForTesting();

    void setObscuredByAnotherView(boolean arg1);

    void setShouldFocusOnPageLoad(boolean arg1);

    void setState(boolean arg1);

    boolean supportsAction(int arg1);
}

