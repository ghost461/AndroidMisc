package org.chromium.base;

import android.annotation.TargetApi;
import android.app.ActivityManager$MemoryInfo;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build$VERSION;
import android.os.Process;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.CachedMetrics$BooleanHistogramSample;

@JNINamespace(value="base::android") public class SysUtils {
    private static final int ANDROID_LOW_MEMORY_DEVICE_THRESHOLD_MB = 0x200;
    private static final int ANDROID_O_LOW_MEMORY_DEVICE_THRESHOLD_MB = 0x400;
    private static final String TAG = "SysUtils";
    private static Integer sAmountOfPhysicalMemoryKB;
    private static Boolean sLowEndDevice;
    private static BooleanHistogramSample sLowEndMatches;

    static {
        SysUtils.sLowEndMatches = new BooleanHistogramSample("Android.SysUtilsLowEndMatches");
    }

    private SysUtils() {
        super();
    }

    @CalledByNative public static int amountOfPhysicalMemoryKB() {
        if(SysUtils.sAmountOfPhysicalMemoryKB == null) {
            SysUtils.sAmountOfPhysicalMemoryKB = Integer.valueOf(SysUtils.detectAmountOfPhysicalMemoryKB());
        }

        return SysUtils.sAmountOfPhysicalMemoryKB.intValue();
    }

    private static int detectAmountOfPhysicalMemoryKB() {
        int v5;
        Matcher v4_1;
        BufferedReader v3;
        FileReader v2;
        Pattern v0 = Pattern.compile("^MemTotal:\\s+([0-9]+) kB$");
        StrictMode$ThreadPolicy v1 = StrictMode.allowThreadDiskReads();
        try {
            v2 = new FileReader("/proc/meminfo");
        }
        catch(Throwable v0_1) {
            goto label_54;
        }
        catch(Exception v0_2) {
            goto label_48;
        }

        try {
            v3 = new BufferedReader(((Reader)v2));
        }
        catch(Throwable v0_1) {
            goto label_43;
        }

        try {
            while(true) {
                String v4 = v3.readLine();
                if(v4 == null) {
                    Log.w("SysUtils", "/proc/meminfo lacks a MemTotal entry?");
                }
                else {
                    v4_1 = v0.matcher(((CharSequence)v4));
                    if(!v4_1.find()) {
                        continue;
                    }
                    else {
                        break;
                    }
                }

                goto label_32;
            }

            v5 = Integer.parseInt(v4_1.group(1));
            if(v5 <= 0x400) {
                Log.w("SysUtils", "Invalid /proc/meminfo total size in kB: " + v4_1.group(1));
            }
            else {
                goto label_35;
            }

            goto label_32;
        }
        catch(Throwable v0_1) {
            try {
                v3.close();
                throw v0_1;
            label_32:
                v3.close();
            }
            catch(Throwable v0_1) {
                goto label_43;
            }
        }

        try {
            v2.close();
            goto label_51;
        }
        catch(Throwable v0_1) {
            goto label_54;
        }
        catch(Exception v0_2) {
            goto label_48;
        }

        try {
        label_35:
            v3.close();
        }
        catch(Throwable v0_1) {
            goto label_43;
        }

        try {
            v2.close();
        }
        catch(Throwable v0_1) {
            goto label_54;
        }
        catch(Exception v0_2) {
            goto label_48;
        }

        StrictMode.setThreadPolicy(v1);
        return v5;
        try {
        label_43:
            v2.close();
            throw v0_1;
        }
        catch(Throwable v0_1) {
        label_54:
            StrictMode.setThreadPolicy(v1);
            throw v0_1;
        }
        catch(Exception v0_2) {
            try {
            label_48:
                Log.w("SysUtils", "Cannot get total physical size from /proc/meminfo", ((Throwable)v0_2));
            }
            catch(Throwable v0_1) {
                goto label_54;
            }

        label_51:
            StrictMode.setThreadPolicy(v1);
            return 0;
        }
    }

    @TargetApi(value=19) private static boolean detectLowEndDevice() {
        boolean v0;
        boolean v1 = true;
        if(CommandLine.getInstance().hasSwitch("enable-low-end-device-mode")) {
            return 1;
        }

        if(CommandLine.getInstance().hasSwitch("disable-low-end-device-mode")) {
            return 0;
        }

        SysUtils.sAmountOfPhysicalMemoryKB = Integer.valueOf(SysUtils.detectAmountOfPhysicalMemoryKB());
        if(SysUtils.sAmountOfPhysicalMemoryKB.intValue() > 0) {
            int v4 = 0x400;
            if(Build$VERSION.SDK_INT >= 26) {
                if(SysUtils.sAmountOfPhysicalMemoryKB.intValue() / v4 > v4) {
                    goto label_18;
                }
            }
            else if(SysUtils.sAmountOfPhysicalMemoryKB.intValue() / v4 <= 0x200) {
            }
            else {
                goto label_18;
            }

            v0 = true;
        }
        else {
        label_18:
            v0 = false;
        }

        boolean v3 = ContextUtils.getApplicationContext() == null || Build$VERSION.SDK_INT < 19 ? false : ContextUtils.getApplicationContext().getSystemService("activity").isLowRamDevice();
        BooleanHistogramSample v4_1 = SysUtils.sLowEndMatches;
        if(v0 == v3) {
        }
        else {
            v1 = false;
        }

        v4_1.record(v1);
        return v0;
    }

    @CalledByNative public static String getAppName() {
        Object v1;
        Iterator v0 = ContextUtils.getApplicationContext().getSystemService("activity").getRunningAppProcesses().iterator();
        do {
            if(!v0.hasNext()) {
                return "";
            }

            v1 = v0.next();
        }
        while(((ActivityManager$RunningAppProcessInfo)v1).pid != Process.myPid());

        return ((ActivityManager$RunningAppProcessInfo)v1).processName;
    }

    public static boolean hasCamera(Context arg3) {
        PackageManager v3 = arg3.getPackageManager();
        boolean v0 = v3.hasSystemFeature("android.hardware.camera");
        if(Build$VERSION.SDK_INT >= 17) {
            int v0_1 = (((int)v0)) | v3.hasSystemFeature("android.hardware.camera.any");
        }

        return v0;
    }

    @CalledByNative public static boolean isCurrentlyLowMemory() {
        Object v0 = ContextUtils.getApplicationContext().getSystemService("activity");
        ActivityManager$MemoryInfo v1 = new ActivityManager$MemoryInfo();
        ((ActivityManager)v0).getMemoryInfo(v1);
        return v1.lowMemory;
    }

    @CalledByNative public static boolean isLowEndDevice() {
        if(SysUtils.sLowEndDevice == null) {
            SysUtils.sLowEndDevice = Boolean.valueOf(SysUtils.detectLowEndDevice());
        }

        return SysUtils.sLowEndDevice.booleanValue();
    }

    public static void logPageFaultCountToTracing() {
        SysUtils.nativeLogPageFaultCountToTracing();
    }

    private static native void nativeLogPageFaultCountToTracing() {
    }

    @VisibleForTesting public static void resetForTesting() {
        SysUtils.sLowEndDevice = null;
        SysUtils.sAmountOfPhysicalMemoryKB = null;
    }
}

