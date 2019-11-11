package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.DataPipe$ProducerHandle;

public interface Blob extends Interface {
    public interface GetInternalUuidResponse extends Callback1 {
    }

    public interface Proxy extends Blob, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface ReadSideDataResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        Blob.MANAGER = Blob_Internal.MANAGER;
    }

    void asDataPipeGetter(InterfaceRequest arg1);

    void clone(InterfaceRequest arg1);

    void getInternalUuid(GetInternalUuidResponse arg1);

    void readAll(ProducerHandle arg1, BlobReaderClient arg2);

    void readRange(long arg1, long arg2, ProducerHandle arg3, BlobReaderClient arg4);

    void readSideData(ReadSideDataResponse arg1);
}

