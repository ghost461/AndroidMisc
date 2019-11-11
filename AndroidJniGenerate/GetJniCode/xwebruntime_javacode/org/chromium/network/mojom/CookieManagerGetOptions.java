package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class CookieManagerGetOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int matchType;
    public String name;

    static {
        CookieManagerGetOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        CookieManagerGetOptions.DEFAULT_STRUCT_INFO = CookieManagerGetOptions.VERSION_ARRAY[0];
    }

    public CookieManagerGetOptions() {
        this(0);
    }

    private CookieManagerGetOptions(int arg2) {
        super(24, arg2);
    }

    public static CookieManagerGetOptions decode(Decoder arg3) {
        CookieManagerGetOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new CookieManagerGetOptions(arg3.readAndValidateDataHeader(CookieManagerGetOptions.VERSION_ARRAY).elementsOrVersion);
            v1.name = arg3.readString(8, false);
            v1.matchType = arg3.readInt(16);
            CookieMatchType.validate(v1.matchType);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static CookieManagerGetOptions deserialize(ByteBuffer arg2) {
        return CookieManagerGetOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CookieManagerGetOptions deserialize(Message arg1) {
        return CookieManagerGetOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(CookieManagerGetOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.name, 8, false);
        arg4.encode(this.matchType, 16);
    }
}

