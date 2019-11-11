package org.chromium.content.browser;

import android.os.Handler;
import android.os.Looper;
import org.chromium.base.JavaHandlerThread;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content::android") public final class LauncherThread {
    private static Handler sHandler;
    private static final JavaHandlerThread sThread;
    private static final Handler sThreadHandler;

    static {
        LauncherThread.sThread = new JavaHandlerThread("Chrome_ProcessLauncherThread");
        LauncherThread.sThread.maybeStart();
        LauncherThread.sThreadHandler = new Handler(LauncherThread.sThread.getLooper());
        LauncherThread.sHandler = LauncherThread.sThreadHandler;
    }

    private LauncherThread() {
        super();
    }

    public static Handler getHandler() {
        return LauncherThread.sHandler;
    }

    @CalledByNative private static JavaHandlerThread getHandlerThread() {
        return LauncherThread.sThread;
    }

    public static void post(Runnable arg1) {
        LauncherThread.sHandler.post(arg1);
    }

    public static void postDelayed(Runnable arg1, long arg2) {
        LauncherThread.sHandler.postDelayed(arg1, arg2);
    }

    public static void removeCallbacks(Runnable arg1) {
        LauncherThread.sHandler.removeCallbacks(arg1);
    }

    public static boolean runningOnLauncherThread() {
        boolean v0 = LauncherThread.sHandler.getLooper() == Looper.myLooper() ? true : false;
        return v0;
    }

    @VisibleForTesting public static void setCurrentThreadAsLauncherThread() {
        LauncherThread.sHandler = new Handler();
    }

    @VisibleForTesting public static void setLauncherThreadAsLauncherThread() {
        LauncherThread.sHandler = LauncherThread.sThreadHandler;
    }
}

