package org.chromium.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.MessageQueue$IdleHandler;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="base::android") public class JavaHandlerThread {
    private final HandlerThread mThread;
    private Throwable mUnhandledException;

    static {
    }

    public JavaHandlerThread(String arg2) {
        super();
        this.mThread = new HandlerThread(arg2);
    }

    static void access$000(JavaHandlerThread arg0, long arg1, long arg3) {
        arg0.nativeInitializeThread(arg1, arg3);
    }

    static HandlerThread access$100(JavaHandlerThread arg0) {
        return arg0.mThread;
    }

    static void access$200(JavaHandlerThread arg0, long arg1) {
        arg0.nativeOnLooperStopped(arg1);
    }

    static void access$300(JavaHandlerThread arg0, long arg1) {
        arg0.stopOnThread(arg1);
    }

    static Throwable access$402(JavaHandlerThread arg0, Throwable arg1) {
        arg0.mUnhandledException = arg1;
        return arg1;
    }

    @CalledByNative private static JavaHandlerThread create(String arg1) {
        return new JavaHandlerThread(arg1);
    }

    public Looper getLooper() {
        return this.mThread.getLooper();
    }

    @CalledByNative private Throwable getUncaughtExceptionIfAny() {
        return this.mUnhandledException;
    }

    private boolean hasStarted() {
        boolean v0 = this.mThread.getState() != Thread$State.NEW ? true : false;
        return v0;
    }

    @CalledByNative private boolean isAlive() {
        return this.mThread.isAlive();
    }

    @CalledByNative private void joinThread() {
        int v0 = 0;
        while(v0 == 0) {
            try {
                this.mThread.join();
                v0 = 1;
            }
            catch(InterruptedException ) {
            }
        }
    }

    @CalledByNative private void listenForUncaughtExceptionsForTesting() {
        this.mThread.setUncaughtExceptionHandler(new Thread$UncaughtExceptionHandler() {
            public void uncaughtException(Thread arg1, Throwable arg2) {
                JavaHandlerThread.this.mUnhandledException = arg2;
            }
        });
    }

    public void maybeStart() {
        if(this.hasStarted()) {
            return;
        }

        this.mThread.start();
    }

    private native void nativeInitializeThread(long arg1, long arg2) {
    }

    private native void nativeOnLooperStopped(long arg1) {
    }

    private native void nativeStopThread(long arg1) {
    }

    @CalledByNative private void startAndInitialize(long arg9, long arg11) {
        this.maybeStart();
        new Handler(this.mThread.getLooper()).post(new Runnable(arg9, arg11) {
            public void run() {
                JavaHandlerThread.this.nativeInitializeThread(this.val$nativeThread, this.val$nativeEvent);
            }
        });
    }

    @CalledByNative private void stop(long arg3) {
        Looper v0 = this.mThread.getLooper();
        if(this.isAlive()) {
            if(v0 == null) {
            }
            else {
                new Handler(v0).post(new Runnable(arg3) {
                    public void run() {
                        JavaHandlerThread.this.stopOnThread(this.val$nativeThread);
                    }
                });
                this.joinThread();
                return;
            }
        }
    }

    @CalledByNative private void stopOnThread(long arg3) {
        this.nativeStopThread(arg3);
        Looper.myQueue().addIdleHandler(new MessageQueue$IdleHandler(arg3) {
            public boolean queueIdle() {
                JavaHandlerThread.this.mThread.getLooper().quit();
                JavaHandlerThread.this.nativeOnLooperStopped(this.val$nativeThread);
                return 0;
            }
        });
    }
}

