package org.chromium.content.browser.accessibility;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Iterator;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

@TargetApi(value=19) @JNINamespace(value="content") public class KitKatWebContentsAccessibility extends WebContentsAccessibilityImpl {
    private String mSupportedHtmlElementTypes;

    KitKatWebContentsAccessibility(WebContents arg1) {
        super(arg1);
    }

    protected int getAccessibilityServiceCapabilitiesMask() {
        Iterator v0 = this.mAccessibilityManager.getEnabledAccessibilityServiceList(-1).iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); v1 |= v0.next().getCapabilities()) {
        }

        return v1;
    }

    protected void onNativeInit() {
        super.onNativeInit();
        this.mSupportedHtmlElementTypes = this.nativeGetSupportedHtmlElementTypes(this.mNativeObj);
    }

    protected void setAccessibilityNodeInfoKitKatAttributes(AccessibilityNodeInfo arg3, boolean arg4, boolean arg5, String arg6, String arg7, String arg8, int arg9, int arg10, boolean arg11) {
        Bundle v0 = arg3.getExtras();
        v0.putCharSequence("AccessibilityNodeInfo.chromeRole", ((CharSequence)arg6));
        v0.putCharSequence("AccessibilityNodeInfo.roleDescription", ((CharSequence)arg7));
        v0.putCharSequence("AccessibilityNodeInfo.hint", ((CharSequence)arg8));
        if(arg11) {
            v0.putCharSequence("AccessibilityNodeInfo.hasImage", "true");
        }

        if(arg4) {
            v0.putCharSequence("ACTION_ARGUMENT_HTML_ELEMENT_STRING_VALUES", this.mSupportedHtmlElementTypes);
        }

        if(arg5) {
            arg3.setEditable(true);
            arg3.setTextSelection(arg9, arg10);
        }
    }
}

