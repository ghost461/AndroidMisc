package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ServiceWorkerClientQueryOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int clientType;
    public boolean includeUncontrolled;

    static {
        ServiceWorkerClientQueryOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ServiceWorkerClientQueryOptions.DEFAULT_STRUCT_INFO = ServiceWorkerClientQueryOptions.VERSION_ARRAY[0];
    }

    public ServiceWorkerClientQueryOptions() {
        this(0);
    }

    private ServiceWorkerClientQueryOptions(int arg2) {
        super(16, arg2);
        this.includeUncontrolled = false;
        this.clientType = 0;
    }

    public static ServiceWorkerClientQueryOptions decode(Decoder arg3) {
        ServiceWorkerClientQueryOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ServiceWorkerClientQueryOptions(arg3.readAndValidateDataHeader(ServiceWorkerClientQueryOptions.VERSION_ARRAY).elementsOrVersion);
            v1.includeUncontrolled = arg3.readBoolean(8, 0);
            v1.clientType = arg3.readInt(12);
            ServiceWorkerClientType.validate(v1.clientType);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ServiceWorkerClientQueryOptions deserialize(ByteBuffer arg2) {
        return ServiceWorkerClientQueryOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerClientQueryOptions deserialize(Message arg1) {
        return ServiceWorkerClientQueryOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerClientQueryOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.includeUncontrolled, 8, 0);
        arg4.encode(this.clientType, 12);
    }
}

