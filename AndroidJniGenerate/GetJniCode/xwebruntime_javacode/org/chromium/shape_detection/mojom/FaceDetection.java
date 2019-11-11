package org.chromium.shape_detection.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.skia.mojom.Bitmap;

public interface FaceDetection extends Interface {
    public interface DetectResponse extends Callback1 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, FaceDetection {
    }

    public static final Manager MANAGER;

    static {
        FaceDetection.MANAGER = FaceDetection_Internal.MANAGER;
    }

    void detect(Bitmap arg1, DetectResponse arg2);
}

