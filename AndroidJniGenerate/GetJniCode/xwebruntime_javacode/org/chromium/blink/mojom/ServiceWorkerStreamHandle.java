package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.InvalidHandle;

public final class ServiceWorkerStreamHandle extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public InterfaceRequest callbackRequest;
    public ConsumerHandle stream;

    static {
        ServiceWorkerStreamHandle.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        ServiceWorkerStreamHandle.DEFAULT_STRUCT_INFO = ServiceWorkerStreamHandle.VERSION_ARRAY[0];
    }

    public ServiceWorkerStreamHandle() {
        this(0);
    }

    private ServiceWorkerStreamHandle(int arg2) {
        super(16, arg2);
        this.stream = InvalidHandle.INSTANCE;
    }

    public static ServiceWorkerStreamHandle decode(Decoder arg3) {
        ServiceWorkerStreamHandle v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ServiceWorkerStreamHandle(arg3.readAndValidateDataHeader(ServiceWorkerStreamHandle.VERSION_ARRAY).elementsOrVersion);
            v1.stream = arg3.readConsumerHandle(8, false);
            v1.callbackRequest = arg3.readInterfaceRequest(12, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ServiceWorkerStreamHandle deserialize(ByteBuffer arg2) {
        return ServiceWorkerStreamHandle.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerStreamHandle deserialize(Message arg1) {
        return ServiceWorkerStreamHandle.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerStreamHandle.DEFAULT_STRUCT_INFO);
        arg4.encode(this.stream, 8, false);
        arg4.encode(this.callbackRequest, 12, false);
    }
}

