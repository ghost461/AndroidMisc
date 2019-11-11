package org.chromium.mojo.system.impl;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.mojo.system.RunLoop;

@JNINamespace(value="mojo::android") class BaseRunLoop implements RunLoop {
    private final CoreImpl mCore;
    private long mRunLoopID;

    static {
    }

    BaseRunLoop(CoreImpl arg3) {
        super();
        this.mCore = arg3;
        this.mRunLoopID = this.nativeCreateBaseRunLoop();
    }

    public void close() {
        long v2 = 0;
        if(this.mRunLoopID == v2) {
            return;
        }

        this.mCore.clearCurrentRunLoop();
        this.nativeDeleteMessageLoop(this.mRunLoopID);
        this.mRunLoopID = v2;
    }

    private native long nativeCreateBaseRunLoop() {
    }

    private native void nativeDeleteMessageLoop(long arg1) {
    }

    private native void nativePostDelayedTask(long arg1, Runnable arg2, long arg3) {
    }

    private native void nativeQuit() {
    }

    private native void nativeRun() {
    }

    private native void nativeRunUntilIdle() {
    }

    public void postDelayedTask(Runnable arg7, long arg8) {
        this.nativePostDelayedTask(this.mRunLoopID, arg7, arg8);
    }

    public void quit() {
        this.nativeQuit();
    }

    public void run() {
        this.nativeRun();
    }

    @CalledByNative private static void runRunnable(Runnable arg0) {
        arg0.run();
    }

    public void runUntilIdle() {
        this.nativeRunUntilIdle();
    }
}

