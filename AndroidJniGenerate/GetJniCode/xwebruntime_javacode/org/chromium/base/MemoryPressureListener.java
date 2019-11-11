package org.chromium.base;

import android.app.Activity;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.memory.MemoryPressureCallback;
import org.xwalk.core.internal.Log;

@MainDex public class MemoryPressureListener {
    private static final String ACTION_LOW_MEMORY = "org.chromium.base.ACTION_LOW_MEMORY";
    private static final String ACTION_TRIM_MEMORY = "org.chromium.base.ACTION_TRIM_MEMORY";
    private static final String ACTION_TRIM_MEMORY_MODERATE = "org.chromium.base.ACTION_TRIM_MEMORY_MODERATE";
    private static final String ACTION_TRIM_MEMORY_RUNNING_CRITICAL = "org.chromium.base.ACTION_TRIM_MEMORY_RUNNING_CRITICAL";
    private static final ObserverList sCallbacks;

    static {
        MemoryPressureListener.sCallbacks = new ObserverList();
    }

    public MemoryPressureListener() {
        super();
    }

    public static void addCallback(MemoryPressureCallback arg1) {
        MemoryPressureListener.sCallbacks.addObserver(arg1);
    }

    @CalledByNative private static void addNativeCallback() {
        MemoryPressureListener.addCallback(MemoryPressureListener$$Lambda$0.$instance);
    }

    static final void bridge$lambda$0$MemoryPressureListener(int arg0) {
        MemoryPressureListener.nativeOnMemoryPressure(arg0);
    }

    public static boolean handleDebugIntent(Activity arg1, String arg2) {
        if("org.chromium.base.ACTION_LOW_MEMORY".equals(arg2)) {
            MemoryPressureListener.simulateLowMemoryPressureSignal(arg1);
        }
        else if("org.chromium.base.ACTION_TRIM_MEMORY".equals(arg2)) {
            MemoryPressureListener.simulateTrimMemoryPressureSignal(arg1, 80);
        }
        else if("org.chromium.base.ACTION_TRIM_MEMORY_RUNNING_CRITICAL".equals(arg2)) {
            MemoryPressureListener.simulateTrimMemoryPressureSignal(arg1, 15);
        }
        else if("org.chromium.base.ACTION_TRIM_MEMORY_MODERATE".equals(arg2)) {
            MemoryPressureListener.simulateTrimMemoryPressureSignal(arg1, 60);
        }
        else {
            return 0;
        }

        return 1;
    }

    private static native void nativeOnMemoryPressure(int arg0) {
    }

    public static void notifyMemoryPressure(int arg2) {
        Iterator v0 = MemoryPressureListener.sCallbacks.iterator();
        while(v0.hasNext()) {
            v0.next().onPressure(arg2);
        }
    }

    public static void notifyMemoryPresure() {
        Log.i("memorypresu", "notifyMemoryPresure = ");
        MemoryPressureListener.nativeOnMemoryPressure(2);
    }

    public static void removeCallback(MemoryPressureCallback arg1) {
        MemoryPressureListener.sCallbacks.removeObserver(arg1);
    }

    private static void simulateLowMemoryPressureSignal(Activity arg1) {
        arg1.getApplication().onLowMemory();
        arg1.onLowMemory();
    }

    private static void simulateTrimMemoryPressureSignal(Activity arg1, int arg2) {
        arg1.getApplication().onTrimMemory(arg2);
        arg1.onTrimMemory(arg2);
    }
}

