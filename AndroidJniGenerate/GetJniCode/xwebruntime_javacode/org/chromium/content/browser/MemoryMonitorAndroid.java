package org.chromium.content.browser;

import android.app.ActivityManager$MemoryInfo;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") class MemoryMonitorAndroid {
    private static final String TAG = "MemoryMonitorAndroid";
    private static ComponentCallbacks2 sCallbacks;
    private static final ActivityManager$MemoryInfo sMemoryInfo;

    static {
        MemoryMonitorAndroid.sMemoryInfo = new ActivityManager$MemoryInfo();
    }

    private MemoryMonitorAndroid() {
        super();
    }

    static void access$000(int arg0) {
        MemoryMonitorAndroid.nativeOnTrimMemory(arg0);
    }

    @CalledByNative private static void getMemoryInfo(long arg10) {
        Object v0 = ContextUtils.getApplicationContext().getSystemService("activity");
        try {
            ((ActivityManager)v0).getMemoryInfo(MemoryMonitorAndroid.sMemoryInfo);
        }
        catch(RuntimeException v0_1) {
            Log.e("MemoryMonitorAndroid", "Failed to get memory info due to a runtime exception: %s", new Object[]{v0_1});
            MemoryMonitorAndroid.sMemoryInfo.availMem = 1;
            MemoryMonitorAndroid.sMemoryInfo.lowMemory = true;
            MemoryMonitorAndroid.sMemoryInfo.threshold = 1;
            MemoryMonitorAndroid.sMemoryInfo.totalMem = 1;
        }

        MemoryMonitorAndroid.nativeGetMemoryInfoCallback(MemoryMonitorAndroid.sMemoryInfo.availMem, MemoryMonitorAndroid.sMemoryInfo.lowMemory, MemoryMonitorAndroid.sMemoryInfo.threshold, MemoryMonitorAndroid.sMemoryInfo.totalMem, arg10);
    }

    private static native void nativeGetMemoryInfoCallback(long arg0, boolean arg1, long arg2, long arg3, long arg4) {
    }

    private static native void nativeOnTrimMemory(int arg0) {
    }

    @CalledByNative private static void registerComponentCallbacks() {
        MemoryMonitorAndroid.sCallbacks = new ComponentCallbacks2() {
            public void onConfigurationChanged(Configuration arg1) {
            }

            public void onLowMemory() {
            }

            public void onTrimMemory(int arg2) {
                if(arg2 != 20) {
                    MemoryMonitorAndroid.nativeOnTrimMemory(arg2);
                }
            }
        };
        ContextUtils.getApplicationContext().registerComponentCallbacks(MemoryMonitorAndroid.sCallbacks);
    }
}

