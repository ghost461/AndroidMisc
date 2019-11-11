package org.chromium.mojo.system.impl;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.mojo.system.Core$HandleSignals;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.Watcher$Callback;
import org.chromium.mojo.system.Watcher;

@JNINamespace(value="mojo::android") class WatcherImpl implements Watcher {
    private Callback mCallback;
    private long mImplPtr;

    WatcherImpl() {
        super();
        this.mImplPtr = this.nativeCreateWatcher();
    }

    public void cancel() {
        if(this.mImplPtr == 0) {
            return;
        }

        this.mCallback = null;
        this.nativeCancel(this.mImplPtr);
    }

    public void destroy() {
        long v2 = 0;
        if(this.mImplPtr == v2) {
            return;
        }

        this.nativeDelete(this.mImplPtr);
        this.mImplPtr = v2;
    }

    private native void nativeCancel(long arg1) {
    }

    private native long nativeCreateWatcher() {
    }

    private native void nativeDelete(long arg1) {
    }

    private native int nativeStart(long arg1, int arg2, int arg3) {
    }

    @CalledByNative private void onHandleReady(int arg2) {
        this.mCallback.onResult(arg2);
    }

    public int start(Handle arg6, HandleSignals arg7, Callback arg8) {
        int v0 = 3;
        if(Long.compare(this.mImplPtr, 0) == 0) {
            return v0;
        }

        if(!(arg6 instanceof HandleBase)) {
            return v0;
        }

        int v6 = this.nativeStart(this.mImplPtr, ((HandleBase)arg6).getMojoHandle(), arg7.getFlags());
        if(v6 == 0) {
            this.mCallback = arg8;
        }

        return v6;
    }
}

