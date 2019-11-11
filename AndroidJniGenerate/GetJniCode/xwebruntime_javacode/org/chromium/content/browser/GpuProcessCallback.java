package org.chromium.content.browser;

import android.view.Surface;
import org.chromium.base.UnguessableToken;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.common.IGpuProcessCallback$Stub;
import org.chromium.content.common.SurfaceWrapper;

@JNINamespace(value="content") class GpuProcessCallback extends Stub {
    GpuProcessCallback() {
        super();
    }

    public void forwardSurfaceForSurfaceRequest(UnguessableToken arg1, Surface arg2) {
        GpuProcessCallback.nativeCompleteScopedSurfaceRequest(arg1, arg2);
    }

    public SurfaceWrapper getViewSurface(int arg2) {
        Surface v2 = GpuProcessCallback.nativeGetViewSurface(arg2);
        if(v2 == null) {
            return null;
        }

        return new SurfaceWrapper(v2);
    }

    private static native void nativeCompleteScopedSurfaceRequest(UnguessableToken arg0, Surface arg1) {
    }

    private static native Surface nativeGetViewSurface(int arg0) {
    }
}

