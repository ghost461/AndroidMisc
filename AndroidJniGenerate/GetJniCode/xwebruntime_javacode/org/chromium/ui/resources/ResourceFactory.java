package org.chromium.ui.resources;

import android.graphics.Rect;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.resources.statics.NinePatchData;

@JNINamespace(value="ui") public class ResourceFactory {
    public ResourceFactory() {
        super();
    }

    public static long createBitmapResource(NinePatchData arg2) {
        long v0 = arg2 == null ? ResourceFactory.nativeCreateBitmapResource() : ResourceFactory.createNinePatchBitmapResource(arg2.getPadding(), arg2.getAperture());
        return v0;
    }

    private static long createNinePatchBitmapResource(Rect arg8, Rect arg9) {
        return ResourceFactory.nativeCreateNinePatchBitmapResource(arg8.left, arg8.top, arg8.right, arg8.bottom, arg9.left, arg9.top, arg9.right, arg9.bottom);
    }

    private static native long nativeCreateBitmapResource() {
    }

    private static native long nativeCreateNinePatchBitmapResource(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
    }
}

