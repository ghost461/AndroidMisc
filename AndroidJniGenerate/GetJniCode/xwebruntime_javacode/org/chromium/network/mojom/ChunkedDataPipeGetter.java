package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.DataPipe$ProducerHandle;

public interface ChunkedDataPipeGetter extends Interface {
    public interface GetSizeResponse extends Callback2 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, ChunkedDataPipeGetter {
    }

    public static final Manager MANAGER;

    static {
        ChunkedDataPipeGetter.MANAGER = ChunkedDataPipeGetter_Internal.MANAGER;
    }

    void getSize(GetSizeResponse arg1);

    void startReading(ProducerHandle arg1);
}

