package android.support.v4.view.accessibility;

import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo$RangeInfo;

@RequiresApi(value=19) class AccessibilityNodeInfoCompatKitKat {
    class RangeInfo {
        RangeInfo() {
            super();
        }

        static float getCurrent(Object arg0) {
            return ((AccessibilityNodeInfo$RangeInfo)arg0).getCurrent();
        }

        static float getMax(Object arg0) {
            return ((AccessibilityNodeInfo$RangeInfo)arg0).getMax();
        }

        static float getMin(Object arg0) {
            return ((AccessibilityNodeInfo$RangeInfo)arg0).getMin();
        }

        static int getType(Object arg0) {
            return ((AccessibilityNodeInfo$RangeInfo)arg0).getType();
        }
    }

    AccessibilityNodeInfoCompatKitKat() {
        super();
    }
}

