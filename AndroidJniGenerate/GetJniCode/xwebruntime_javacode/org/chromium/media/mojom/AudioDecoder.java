package org.chromium.media.mojom;

import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;

public interface AudioDecoder extends Interface {
    public interface DecodeResponse extends Callback1 {
    }

    public interface InitializeResponse extends Callback2 {
    }

    public interface Proxy extends AudioDecoder, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface ResetResponse extends Callback0 {
    }

    public static final Manager MANAGER;

    static {
        AudioDecoder.MANAGER = AudioDecoder_Internal.MANAGER;
    }

    void construct(AssociatedInterfaceNotSupported arg1);

    void decode(DecoderBuffer arg1, DecodeResponse arg2);

    void initialize(AudioDecoderConfig arg1, int arg2, InitializeResponse arg3);

    void reset(ResetResponse arg1);

    void setDataSource(ConsumerHandle arg1);
}

