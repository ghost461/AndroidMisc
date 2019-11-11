package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface AudioOutputStreamProvider extends Interface {
    public interface AcquireResponse extends Callback1 {
    }

    public interface Proxy extends AudioOutputStreamProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        AudioOutputStreamProvider.MANAGER = AudioOutputStreamProvider_Internal.MANAGER;
    }

    void acquire(InterfaceRequest arg1, AudioOutputStreamClient arg2, AudioParameters arg3, AcquireResponse arg4);
}

