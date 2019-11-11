package org.chromium.content.browser.accessibility;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Arrays;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

@TargetApi(value=26) @JNINamespace(value="content") public class OWebContentsAccessibility extends LollipopWebContentsAccessibility {
    static {
    }

    OWebContentsAccessibility(WebContents arg1) {
        super(arg1);
    }

    public void addExtraDataToAccessibilityNodeInfo(int arg9, AccessibilityNodeInfo arg10, String arg11, Bundle arg12) {
        if(!arg11.equals("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            return;
        }

        if(!this.nativeAreInlineTextBoxesLoaded(this.mNativeObj, arg9)) {
            this.nativeLoadInlineTextBoxes(this.mNativeObj, arg9);
        }

        int v6 = arg12.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
        int v12 = arg12.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
        if(v12 > 0) {
            if(v6 < 0) {
            }
            else {
                int[] v9 = this.nativeGetCharacterBoundingBoxes(this.mNativeObj, arg9, v6, v12);
                if(v9 == null) {
                    return;
                }
                else {
                    RectF[] v0 = new RectF[v12];
                    int v1;
                    for(v1 = 0; v1 < v12; ++v1) {
                        int v3 = v1 * 4;
                        Rect v2 = new Rect(v9[v3], v9[v3 + 1], v9[v3 + 2], v9[v3 + 3]);
                        this.convertWebRectToAndroidCoordinates(v2);
                        v0[v1] = new RectF(v2);
                    }

                    arg10.getExtras().putParcelableArray(arg11, ((Parcelable[])v0));
                    return;
                }
            }
        }
    }

    protected void setAccessibilityNodeInfoKitKatAttributes(AccessibilityNodeInfo arg1, boolean arg2, boolean arg3, String arg4, String arg5, String arg6, int arg7, int arg8, boolean arg9) {
        super.setAccessibilityNodeInfoKitKatAttributes(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
        arg1.setHintText(((CharSequence)arg6));
    }

    protected void setAccessibilityNodeInfoOAttributes(AccessibilityNodeInfo arg3, boolean arg4) {
        if(!arg4) {
            return;
        }

        arg3.setAvailableExtraData(Arrays.asList(new String[]{"android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY"}));
    }
}

