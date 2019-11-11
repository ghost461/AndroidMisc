package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.UntypedHandle;

public interface JpegEncodeAccelerator extends Interface {
    public interface EncodeWithFdResponse extends Callback3 {
    }

    public interface InitializeResponse extends Callback1 {
    }

    public interface Proxy extends JpegEncodeAccelerator, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        JpegEncodeAccelerator.MANAGER = JpegEncodeAccelerator_Internal.MANAGER;
    }

    void encodeWithFd(int arg1, UntypedHandle arg2, int arg3, int arg4, int arg5, UntypedHandle arg6, int arg7, UntypedHandle arg8, int arg9, EncodeWithFdResponse arg10);

    void initialize(InitializeResponse arg1);
}

