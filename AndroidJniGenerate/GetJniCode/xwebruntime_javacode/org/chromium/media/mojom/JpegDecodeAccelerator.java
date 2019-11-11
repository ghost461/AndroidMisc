package org.chromium.media.mojom;

import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

public interface JpegDecodeAccelerator extends Interface {
    public interface DecodeResponse extends Callback2 {
    }

    public interface DecodeWithFdResponse extends Callback2 {
    }

    public interface InitializeResponse extends Callback1 {
    }

    public interface Proxy extends JpegDecodeAccelerator, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        JpegDecodeAccelerator.MANAGER = JpegDecodeAccelerator_Internal.MANAGER;
    }

    void decode(BitstreamBuffer arg1, Size arg2, SharedBufferHandle arg3, int arg4, DecodeResponse arg5);

    void decodeWithFd(int arg1, UntypedHandle arg2, int arg3, int arg4, int arg5, UntypedHandle arg6, int arg7, DecodeWithFdResponse arg8);

    void initialize(InitializeResponse arg1);

    void uninitialize();
}

