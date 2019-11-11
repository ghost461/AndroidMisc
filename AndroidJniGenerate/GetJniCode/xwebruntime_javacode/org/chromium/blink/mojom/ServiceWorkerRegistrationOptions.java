package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Url;

public final class ServiceWorkerRegistrationOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public Url scope;
    public int updateViaCache;

    static {
        ServiceWorkerRegistrationOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        ServiceWorkerRegistrationOptions.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationOptions.VERSION_ARRAY[0];
    }

    public ServiceWorkerRegistrationOptions() {
        this(0);
    }

    private ServiceWorkerRegistrationOptions(int arg2) {
        super(24, arg2);
        this.updateViaCache = 0;
    }

    public static ServiceWorkerRegistrationOptions decode(Decoder arg3) {
        ServiceWorkerRegistrationOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ServiceWorkerRegistrationOptions(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationOptions.VERSION_ARRAY).elementsOrVersion);
            v1.scope = Url.decode(arg3.readPointer(8, false));
            v1.updateViaCache = arg3.readInt(16);
            ServiceWorkerUpdateViaCache.validate(v1.updateViaCache);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ServiceWorkerRegistrationOptions deserialize(ByteBuffer arg2) {
        return ServiceWorkerRegistrationOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerRegistrationOptions deserialize(Message arg1) {
        return ServiceWorkerRegistrationOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.scope, 8, false);
        arg4.encode(this.updateViaCache, 16);
    }
}

