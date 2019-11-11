package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.Time;

public final class CookieOptions extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int cookieSameSiteFilter;
    public boolean excludeHttponly;
    public Time serverTime;
    public boolean updateAccessTime;

    static {
        CookieOptions.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        CookieOptions.DEFAULT_STRUCT_INFO = CookieOptions.VERSION_ARRAY[0];
    }

    public CookieOptions() {
        this(0);
    }

    private CookieOptions(int arg2) {
        super(24, arg2);
        this.excludeHttponly = true;
        this.cookieSameSiteFilter = 2;
        this.updateAccessTime = true;
    }

    public static CookieOptions decode(Decoder arg3) {
        CookieOptions v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new CookieOptions(arg3.readAndValidateDataHeader(CookieOptions.VERSION_ARRAY).elementsOrVersion);
            v1.excludeHttponly = arg3.readBoolean(8, 0);
            v1.updateAccessTime = arg3.readBoolean(8, 1);
            v1.cookieSameSiteFilter = arg3.readInt(12);
            CookieSameSiteFilter.validate(v1.cookieSameSiteFilter);
            v1.serverTime = Time.decode(arg3.readPointer(16, true));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static CookieOptions deserialize(ByteBuffer arg2) {
        return CookieOptions.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CookieOptions deserialize(Message arg1) {
        return CookieOptions.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(CookieOptions.DEFAULT_STRUCT_INFO);
        arg4.encode(this.excludeHttponly, 8, 0);
        arg4.encode(this.updateAccessTime, 8, 1);
        arg4.encode(this.cookieSameSiteFilter, 12);
        arg4.encode(this.serverTime, 16, true);
    }
}

