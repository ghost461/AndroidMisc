package org.chromium.ui.gl;

import android.graphics.SurfaceTexture;
import android.util.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="gl") @MainDex class SurfaceTexturePlatformWrapper {
    private static final String TAG = "SurfaceTexturePlatformWrapper";

    SurfaceTexturePlatformWrapper() {
        super();
    }

    @CalledByNative private static void attachToGLContext(SurfaceTexture arg0, int arg1) {
        arg0.attachToGLContext(arg1);
    }

    @CalledByNative private static SurfaceTexture create(int arg1) {
        return new SurfaceTexture(arg1);
    }

    @CalledByNative private static void destroy(SurfaceTexture arg1) {
        arg1.setOnFrameAvailableListener(null);
        arg1.release();
    }

    @CalledByNative private static void detachFromGLContext(SurfaceTexture arg0) {
        arg0.detachFromGLContext();
    }

    @CalledByNative private static void getTransformMatrix(SurfaceTexture arg0, float[] arg1) {
        arg0.getTransformMatrix(arg1);
    }

    @CalledByNative private static void release(SurfaceTexture arg0) {
        arg0.release();
    }

    @CalledByNative private static void setDefaultBufferSize(SurfaceTexture arg0, int arg1, int arg2) {
        arg0.setDefaultBufferSize(arg1, arg2);
    }

    @CalledByNative private static void setFrameAvailableCallback(SurfaceTexture arg1, long arg2) {
        arg1.setOnFrameAvailableListener(new SurfaceTextureListener(arg2));
    }

    @CalledByNative private static void updateTexImage(SurfaceTexture arg2) {
        try {
            arg2.updateTexImage();
        }
        catch(RuntimeException v2) {
            Log.e("SurfaceTexturePlatformWrapper", "Error calling updateTexImage", ((Throwable)v2));
        }
    }
}

