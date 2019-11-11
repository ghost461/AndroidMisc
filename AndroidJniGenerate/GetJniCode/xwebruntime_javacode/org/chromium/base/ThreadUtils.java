package org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.chromium.base.annotations.CalledByNative;

public class ThreadUtils {
    private static final Object sLock;
    private static boolean sThreadAssertsDisabled;
    private static Handler sUiThreadHandler;
    private static boolean sWillOverride;

    static {
        ThreadUtils.sLock = new Object();
    }

    public ThreadUtils() {
        super();
    }

    public static void assertOnBackgroundThread() {
        if(ThreadUtils.sThreadAssertsDisabled) {
            return;
        }
    }

    public static void assertOnUiThread() {
        if(ThreadUtils.sThreadAssertsDisabled) {
            return;
        }
    }

    public static void checkUiThread() {
        if(!ThreadUtils.sThreadAssertsDisabled && !ThreadUtils.runningOnUiThread()) {
            throw new IllegalStateException("Must be called on the UI thread.");
        }
    }

    private static Handler getUiThreadHandler() {
        Object v0 = ThreadUtils.sLock;
        __monitor_enter(v0);
        try {
            if(ThreadUtils.sUiThreadHandler == null) {
                if(ThreadUtils.sWillOverride) {
                    throw new RuntimeException("Did not yet override the UI thread");
                }
                else {
                    ThreadUtils.sUiThreadHandler = new Handler(Looper.getMainLooper());
                }
            }

            __monitor_exit(v0);
            return ThreadUtils.sUiThreadHandler;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_18;
        }

        throw v1;
    }

    public static Looper getUiThreadLooper() {
        return ThreadUtils.getUiThreadHandler().getLooper();
    }

    @CalledByNative private static boolean isThreadPriorityAudio(int arg1) {
        boolean v1 = Process.getThreadPriority(arg1) == -16 ? true : false;
        return v1;
    }

    public static FutureTask postOnUiThread(FutureTask arg1) {
        ThreadUtils.getUiThreadHandler().post(((Runnable)arg1));
        return arg1;
    }

    public static void postOnUiThread(Runnable arg1) {
        ThreadUtils.getUiThreadHandler().post(arg1);
    }

    @VisibleForTesting public static void postOnUiThreadDelayed(Runnable arg1, long arg2) {
        ThreadUtils.getUiThreadHandler().postDelayed(arg1, arg2);
    }

    public static void runOnUiThread(Runnable arg1) {
        if(ThreadUtils.runningOnUiThread()) {
            arg1.run();
        }
        else {
            ThreadUtils.getUiThreadHandler().post(arg1);
        }
    }

    public static FutureTask runOnUiThread(Callable arg1) {
        return ThreadUtils.runOnUiThread(new FutureTask(arg1));
    }

    public static FutureTask runOnUiThread(FutureTask arg1) {
        if(ThreadUtils.runningOnUiThread()) {
            arg1.run();
        }
        else {
            ThreadUtils.postOnUiThread(arg1);
        }

        return arg1;
    }

    public static Object runOnUiThreadBlocking(Callable arg2) throws ExecutionException {
        FutureTask v0 = new FutureTask(arg2);
        ThreadUtils.runOnUiThread(v0);
        try {
            return v0.get();
        }
        catch(InterruptedException v2) {
            throw new RuntimeException("Interrupted waiting for callable", ((Throwable)v2));
        }
    }

    public static void runOnUiThreadBlocking(Runnable arg2) {
        if(ThreadUtils.runningOnUiThread()) {
            arg2.run();
        }
        else {
            FutureTask v0 = new FutureTask(arg2, null);
            ThreadUtils.postOnUiThread(v0);
            try {
                v0.get();
            }
            catch(Exception v2) {
                throw new RuntimeException("Exception occurred while waiting for runnable", ((Throwable)v2));
            }
        }
    }

    @VisibleForTesting public static Object runOnUiThreadBlockingNoException(Callable arg2) {
        try {
            return ThreadUtils.runOnUiThreadBlocking(arg2);
        }
        catch(ExecutionException v2) {
            throw new RuntimeException("Error occurred waiting for callable", ((Throwable)v2));
        }
    }

    public static boolean runningOnUiThread() {
        boolean v0 = ThreadUtils.getUiThreadHandler().getLooper() == Looper.myLooper() ? true : false;
        return v0;
    }

    public static void setThreadAssertsDisabledForTesting(boolean arg0) {
        ThreadUtils.sThreadAssertsDisabled = arg0;
    }

    @CalledByNative public static void setThreadPriorityAudio(int arg1) {
        Process.setThreadPriority(arg1, -16);
    }

    @VisibleForTesting public static void setUiThread(Looper arg4) {
        Object v0 = ThreadUtils.sLock;
        __monitor_enter(v0);
        if(arg4 != null) {
            goto label_9;
        }

        Handler v4 = null;
        try {
            ThreadUtils.sUiThreadHandler = v4;
            __monitor_exit(v0);
            return;
        label_9:
            if(ThreadUtils.sUiThreadHandler != null && ThreadUtils.sUiThreadHandler.getLooper() != arg4) {
                StringBuilder v2 = new StringBuilder();
                v2.append("UI thread looper is already set to ");
                v2.append(ThreadUtils.sUiThreadHandler.getLooper());
                v2.append(" (Main thread looper is ");
                v2.append(Looper.getMainLooper());
                v2.append("), cannot set to new looper ");
                v2.append(arg4);
                throw new RuntimeException(v2.toString());
            }

            ThreadUtils.sUiThreadHandler = new Handler(arg4);
            __monitor_exit(v0);
            return;
        label_37:
            __monitor_exit(v0);
        }
        catch(Throwable v4_1) {
            goto label_37;
        }

        throw v4_1;
    }

    public static void setWillOverrideUiThread() {
        Object v0 = ThreadUtils.sLock;
        __monitor_enter(v0);
        try {
            ThreadUtils.sWillOverride = true;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }
}

