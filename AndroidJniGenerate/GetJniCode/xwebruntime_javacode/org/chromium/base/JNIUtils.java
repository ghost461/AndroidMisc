package org.chromium.base;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

@MainDex public class JNIUtils {
    public interface JniCrashCallback {
        void onJniCrash(Throwable arg1);
    }

    static JniCrashCallback sCrashCallBack;
    private static Boolean sSelectiveJniRegistrationEnabled;

    static {
    }

    public JNIUtils() {
        super();
    }

    public static void enableSelectiveJniRegistration() {
        JNIUtils.sSelectiveJniRegistrationEnabled = Boolean.valueOf(true);
    }

    @CalledByNative public static Object getClassLoader() {
        return JNIUtils.class.getClassLoader();
    }

    @CalledByNative public static boolean isSelectiveJniRegistrationEnabled() {
        if(JNIUtils.sSelectiveJniRegistrationEnabled == null) {
            JNIUtils.sSelectiveJniRegistrationEnabled = Boolean.valueOf(false);
        }

        return JNIUtils.sSelectiveJniRegistrationEnabled.booleanValue();
    }

    @CalledByNative public static void onJniCrash(Throwable arg2) {
        if(arg2 == null) {
            return;
        }

        if(JNIUtils.sCrashCallBack != null) {
            JNIUtils.sCrashCallBack.onJniCrash(arg2);
        }
        else {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), arg2);
        }
    }

    public static void setJniCrashCallBack(JniCrashCallback arg0) {
        JNIUtils.sCrashCallBack = arg0;
    }
}

