package org.chromium.ui.base;

import android.view.InputDevice;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="ui") public class TouchDevice {
    private TouchDevice() {
        super();
    }

    @CalledByNative private static int[] availablePointerAndHoverTypes() {
        InputDevice v7_1;
        int v0 = 2;
        int[] v1 = new int[]{0, 0};
        int[] v2 = InputDevice.getDeviceIds();
        int v3 = v2.length;
        int v5;
        for(v5 = 0; v5 < v3; ++v5) {
            int v7 = v2[v5];
            InputDevice v8 = null;
            try {
                v7_1 = InputDevice.getDevice(v7);
            }
            catch(RuntimeException ) {
                v7_1 = v8;
            }

            if(v7_1 == null) {
            }
            else {
                v7 = v7_1.getSources();
                int v8_1 = 0x2002;
                int v10 = 0x10004;
                int v11 = 0x100008;
                if((TouchDevice.hasSource(v7, v8_1)) || (TouchDevice.hasSource(v7, 0x4002)) || (TouchDevice.hasSource(v7, v11)) || (TouchDevice.hasSource(v7, v10))) {
                    v1[0] |= 4;
                }
                else if(TouchDevice.hasSource(v7, 0x1002)) {
                    v1[0] |= v0;
                }

                if(!TouchDevice.hasSource(v7, v8_1) && !TouchDevice.hasSource(v7, v11) && !TouchDevice.hasSource(v7, v10)) {
                    goto label_48;
                }

                v1[1] |= v0;
            }

        label_48:
        }

        if(v1[0] == 0) {
            v1[0] = 1;
        }

        if(v1[1] == 0) {
            v1[1] = 1;
        }

        return v1;
    }

    private static boolean hasSource(int arg0, int arg1) {
        boolean v0 = (arg0 & arg1) == arg1 ? true : false;
        return v0;
    }

    @CalledByNative private static int maxTouchPoints() {
        if(ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand")) {
            return 5;
        }

        int v1 = 2;
        if(ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct")) {
            return v1;
        }

        if(ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
            return v1;
        }

        if(ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
            return 1;
        }

        return 0;
    }
}

