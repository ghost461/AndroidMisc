package org.chromium.shape_detection.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface FaceDetectionProvider extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, FaceDetectionProvider {
    }

    public static final Manager MANAGER;

    static {
        FaceDetectionProvider.MANAGER = FaceDetectionProvider_Internal.MANAGER;
    }

    void createFaceDetection(InterfaceRequest arg1, FaceDetectorOptions arg2);
}

