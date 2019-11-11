package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.AssociatedInterfaceRequestNotSupported;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ServiceWorkerRegistrationObjectInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x40;
    private static final DataHeader[] VERSION_ARRAY;
    public ServiceWorkerObjectInfo active;
    public AssociatedInterfaceNotSupported hostPtrInfo;
    public ServiceWorkerObjectInfo installing;
    public ServiceWorkerRegistrationOptions options;
    public long registrationId;
    public AssociatedInterfaceRequestNotSupported request;
    public ServiceWorkerObjectInfo waiting;

    static {
        ServiceWorkerRegistrationObjectInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x40, 0)};
        ServiceWorkerRegistrationObjectInfo.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectInfo.VERSION_ARRAY[0];
    }

    public ServiceWorkerRegistrationObjectInfo() {
        this(0);
    }

    private ServiceWorkerRegistrationObjectInfo(int arg3) {
        super(0x40, arg3);
        this.registrationId = -1;
    }

    public static ServiceWorkerRegistrationObjectInfo decode(Decoder arg4) {
        ServiceWorkerRegistrationObjectInfo v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new ServiceWorkerRegistrationObjectInfo(arg4.readAndValidateDataHeader(ServiceWorkerRegistrationObjectInfo.VERSION_ARRAY).elementsOrVersion);
            v1.registrationId = arg4.readLong(8);
            v1.options = ServiceWorkerRegistrationOptions.decode(arg4.readPointer(16, false));
            v1.hostPtrInfo = arg4.readAssociatedServiceInterfaceNotSupported(24, false);
            v1.request = arg4.readAssociatedInterfaceRequestNotSupported(0x20, true);
            v1.installing = ServiceWorkerObjectInfo.decode(arg4.readPointer(40, true));
            v1.waiting = ServiceWorkerObjectInfo.decode(arg4.readPointer(0x30, true));
            v1.active = ServiceWorkerObjectInfo.decode(arg4.readPointer(56, true));
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static ServiceWorkerRegistrationObjectInfo deserialize(ByteBuffer arg2) {
        return ServiceWorkerRegistrationObjectInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerRegistrationObjectInfo deserialize(Message arg1) {
        return ServiceWorkerRegistrationObjectInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.registrationId, 8);
        arg4.encode(this.options, 16, false);
        arg4.encode(this.hostPtrInfo, 24, false);
        arg4.encode(this.request, 0x20, true);
        arg4.encode(this.installing, 40, true);
        arg4.encode(this.waiting, 0x30, true);
        arg4.encode(this.active, 56, true);
    }
}

