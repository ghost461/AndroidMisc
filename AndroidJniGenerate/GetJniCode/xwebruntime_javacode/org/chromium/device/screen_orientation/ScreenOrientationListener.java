package org.chromium.device.screen_orientation;

import android.provider.Settings$System;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.display.DisplayAndroid;

@JNINamespace(value="device") class ScreenOrientationListener {
    ScreenOrientationListener() {
        super();
    }

    @CalledByNative static boolean isAutoRotateEnabledByUser() {
        boolean v1 = true;
        if(Settings$System.getInt(ContextUtils.getApplicationContext().getContentResolver(), "accelerometer_rotation", 0) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    @CalledByNative static void startAccurateListening() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                DisplayAndroid.startAccurateListening();
            }
        });
    }

    @CalledByNative static void stopAccurateListening() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                DisplayAndroid.stopAccurateListening();
            }
        });
    }
}

