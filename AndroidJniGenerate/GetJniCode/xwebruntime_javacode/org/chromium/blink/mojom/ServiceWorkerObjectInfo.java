package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class ServiceWorkerObjectInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public int handleId;
    public AssociatedInterfaceNotSupported hostPtrInfo;
    public int state;
    public Url url;
    public long versionId;

    static {
        ServiceWorkerObjectInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        ServiceWorkerObjectInfo.DEFAULT_STRUCT_INFO = ServiceWorkerObjectInfo.VERSION_ARRAY[0];
    }

    public ServiceWorkerObjectInfo() {
        this(0);
    }

    private ServiceWorkerObjectInfo(int arg3) {
        super(40, arg3);
        this.handleId = -1;
        this.versionId = -1;
        this.state = 0;
    }

    public static ServiceWorkerObjectInfo decode(Decoder arg4) {
        ServiceWorkerObjectInfo v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new ServiceWorkerObjectInfo(arg4.readAndValidateDataHeader(ServiceWorkerObjectInfo.VERSION_ARRAY).elementsOrVersion);
            v1.handleId = arg4.readInt(8);
            v1.state = arg4.readInt(12);
            ServiceWorkerState.validate(v1.state);
            v1.versionId = arg4.readLong(16);
            v1.url = Url.decode(arg4.readPointer(24, false));
            v1.hostPtrInfo = arg4.readAssociatedServiceInterfaceNotSupported(0x20, false);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static ServiceWorkerObjectInfo deserialize(ByteBuffer arg2) {
        return ServiceWorkerObjectInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerObjectInfo deserialize(Message arg1) {
        return ServiceWorkerObjectInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerObjectInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.handleId, 8);
        arg4.encode(this.state, 12);
        arg4.encode(this.versionId, 16);
        arg4.encode(this.url, 24, false);
        arg4.encode(this.hostPtrInfo, 0x20, false);
    }
}

