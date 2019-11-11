package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.TimeTicks;
import org.chromium.network.mojom.RequestContextFrameType;
import org.chromium.url.mojom.Url;

public final class ServiceWorkerClientInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public int clientType;
    public String clientUuid;
    public TimeTicks creationTime;
    public int frameType;
    public boolean isFocused;
    public TimeTicks lastFocusTime;
    public int pageVisibilityState;
    public Url url;

    static {
        ServiceWorkerClientInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        ServiceWorkerClientInfo.DEFAULT_STRUCT_INFO = ServiceWorkerClientInfo.VERSION_ARRAY[0];
    }

    public ServiceWorkerClientInfo() {
        this(0);
    }

    private ServiceWorkerClientInfo(int arg2) {
        super(56, arg2);
        this.pageVisibilityState = 1;
        this.isFocused = false;
        this.frameType = 2;
    }

    public static ServiceWorkerClientInfo decode(Decoder arg3) {
        ServiceWorkerClientInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ServiceWorkerClientInfo(arg3.readAndValidateDataHeader(ServiceWorkerClientInfo.VERSION_ARRAY).elementsOrVersion);
            v1.url = Url.decode(arg3.readPointer(8, false));
            v1.clientUuid = arg3.readString(16, false);
            v1.clientType = arg3.readInt(24);
            ServiceWorkerClientType.validate(v1.clientType);
            v1.pageVisibilityState = arg3.readInt(28);
            PageVisibilityState.validate(v1.pageVisibilityState);
            v1.isFocused = arg3.readBoolean(0x20, 0);
            v1.frameType = arg3.readInt(36);
            RequestContextFrameType.validate(v1.frameType);
            v1.lastFocusTime = TimeTicks.decode(arg3.readPointer(40, false));
            v1.creationTime = TimeTicks.decode(arg3.readPointer(0x30, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ServiceWorkerClientInfo deserialize(ByteBuffer arg2) {
        return ServiceWorkerClientInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerClientInfo deserialize(Message arg1) {
        return ServiceWorkerClientInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerClientInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.url, 8, false);
        arg4.encode(this.clientUuid, 16, false);
        arg4.encode(this.clientType, 24);
        arg4.encode(this.pageVisibilityState, 28);
        arg4.encode(this.isFocused, 0x20, 0);
        arg4.encode(this.frameType, 36);
        arg4.encode(this.lastFocusTime, 40, false);
        arg4.encode(this.creationTime, 0x30, false);
    }
}

