package org.chromium.media.mojom;

import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;

public interface VideoDecoder extends Interface {
    public interface DecodeResponse extends Callback1 {
    }

    public interface InitializeResponse extends Callback3 {
    }

    public interface Proxy extends VideoDecoder, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface ResetResponse extends Callback0 {
    }

    public static final Manager MANAGER;

    static {
        VideoDecoder.MANAGER = VideoDecoder_Internal.MANAGER;
    }

    void construct(AssociatedInterfaceNotSupported arg1, AssociatedInterfaceNotSupported arg2, InterfaceRequest arg3, ConsumerHandle arg4, CommandBufferId arg5);

    void decode(DecoderBuffer arg1, DecodeResponse arg2);

    void initialize(VideoDecoderConfig arg1, boolean arg2, int arg3, InitializeResponse arg4);

    void onOverlayInfoChanged(OverlayInfo arg1);

    void reset(ResetResponse arg1);
}

