package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class UrlLoaderClientEndpoints extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public UrlLoader urlLoader;
    public InterfaceRequest urlLoaderClient;

    static {
        UrlLoaderClientEndpoints.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        UrlLoaderClientEndpoints.DEFAULT_STRUCT_INFO = UrlLoaderClientEndpoints.VERSION_ARRAY[0];
    }

    public UrlLoaderClientEndpoints() {
        this(0);
    }

    private UrlLoaderClientEndpoints(int arg2) {
        super(24, arg2);
    }

    public static UrlLoaderClientEndpoints decode(Decoder arg4) {
        UrlLoaderClientEndpoints v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new UrlLoaderClientEndpoints(arg4.readAndValidateDataHeader(UrlLoaderClientEndpoints.VERSION_ARRAY).elementsOrVersion);
            v1.urlLoader = arg4.readServiceInterface(8, false, UrlLoader.MANAGER);
            v1.urlLoaderClient = arg4.readInterfaceRequest(16, false);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static UrlLoaderClientEndpoints deserialize(ByteBuffer arg2) {
        return UrlLoaderClientEndpoints.deserialize(new Message(arg2, new ArrayList()));
    }

    public static UrlLoaderClientEndpoints deserialize(Message arg1) {
        return UrlLoaderClientEndpoints.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(UrlLoaderClientEndpoints.DEFAULT_STRUCT_INFO);
        arg5.encode(this.urlLoader, 8, false, UrlLoader.MANAGER);
        arg5.encode(this.urlLoaderClient, 16, false);
    }
}

