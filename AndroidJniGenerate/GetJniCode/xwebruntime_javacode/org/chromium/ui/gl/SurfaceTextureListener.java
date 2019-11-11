package org.chromium.ui.gl;

import android.graphics.SurfaceTexture$OnFrameAvailableListener;
import android.graphics.SurfaceTexture;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="gl") @MainDex class SurfaceTextureListener implements SurfaceTexture$OnFrameAvailableListener {
    private final long mNativeSurfaceTextureListener;

    static {
    }

    SurfaceTextureListener(long arg1) {
        super();
        this.mNativeSurfaceTextureListener = arg1;
    }

    protected void finalize() throws Throwable {
        try {
            this.nativeDestroy(this.mNativeSurfaceTextureListener);
        }
        catch(Throwable v0) {
            super.finalize();
            throw v0;
        }

        super.finalize();
    }

    private native void nativeDestroy(long arg1) {
    }

    private native void nativeFrameAvailable(long arg1) {
    }

    public void onFrameAvailable(SurfaceTexture arg3) {
        this.nativeFrameAvailable(this.mNativeSurfaceTextureListener);
    }
}

