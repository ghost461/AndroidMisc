package org.chromium.shape_detection.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.skia.mojom.Bitmap;

public interface BarcodeDetection extends Interface {
    public interface DetectResponse extends Callback1 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, BarcodeDetection {
    }

    public static final Manager MANAGER;

    static {
        BarcodeDetection.MANAGER = BarcodeDetection_Internal.MANAGER;
    }

    void detect(Bitmap arg1, DetectResponse arg2);
}

