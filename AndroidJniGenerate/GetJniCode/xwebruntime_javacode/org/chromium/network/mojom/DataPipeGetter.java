package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.DataPipe$ProducerHandle;

public interface DataPipeGetter extends Interface {
    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, DataPipeGetter {
    }

    public interface ReadResponse extends Callback2 {
    }

    public static final Manager MANAGER;

    static {
        DataPipeGetter.MANAGER = DataPipeGetter_Internal.MANAGER;
    }

    void clone(InterfaceRequest arg1);

    void read(ProducerHandle arg1, ReadResponse arg2);
}

