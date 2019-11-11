package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class NavigationPreloadState extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean enabled;
    public String header;

    static {
        NavigationPreloadState.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        NavigationPreloadState.DEFAULT_STRUCT_INFO = NavigationPreloadState.VERSION_ARRAY[0];
    }

    public NavigationPreloadState() {
        this(0);
    }

    private NavigationPreloadState(int arg2) {
        super(24, arg2);
        this.enabled = false;
        this.header = "true";
    }

    public static NavigationPreloadState decode(Decoder arg3) {
        NavigationPreloadState v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new NavigationPreloadState(arg3.readAndValidateDataHeader(NavigationPreloadState.VERSION_ARRAY).elementsOrVersion);
            v1.enabled = arg3.readBoolean(8, 0);
            v1.header = arg3.readString(16, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static NavigationPreloadState deserialize(ByteBuffer arg2) {
        return NavigationPreloadState.deserialize(new Message(arg2, new ArrayList()));
    }

    public static NavigationPreloadState deserialize(Message arg1) {
        return NavigationPreloadState.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(NavigationPreloadState.DEFAULT_STRUCT_INFO);
        arg4.encode(this.enabled, 8, 0);
        arg4.encode(this.header, 16, false);
    }
}

