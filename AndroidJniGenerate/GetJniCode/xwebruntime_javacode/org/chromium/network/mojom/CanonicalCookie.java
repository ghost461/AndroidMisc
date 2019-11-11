package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.Time;

public final class CanonicalCookie extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 80;
    private static final DataHeader[] VERSION_ARRAY;
    public Time creation;
    public String domain;
    public Time expiry;
    public boolean httponly;
    public Time lastAccess;
    public String name;
    public String path;
    public int priority;
    public boolean secure;
    public int siteRestrictions;
    public String value;

    static {
        CanonicalCookie.VERSION_ARRAY = new DataHeader[]{new DataHeader(80, 0)};
        CanonicalCookie.DEFAULT_STRUCT_INFO = CanonicalCookie.VERSION_ARRAY[0];
    }

    public CanonicalCookie() {
        this(0);
    }

    private CanonicalCookie(int arg2) {
        super(80, arg2);
        this.secure = false;
        this.httponly = false;
        this.siteRestrictions = 0;
        this.priority = 1;
    }

    public static CanonicalCookie decode(Decoder arg4) {
        CanonicalCookie v1;
        if(arg4 == null) {
            return null;
        }

        arg4.increaseStackDepth();
        try {
            v1 = new CanonicalCookie(arg4.readAndValidateDataHeader(CanonicalCookie.VERSION_ARRAY).elementsOrVersion);
            v1.name = arg4.readString(8, false);
            v1.value = arg4.readString(16, false);
            v1.domain = arg4.readString(24, false);
            v1.path = arg4.readString(0x20, false);
            v1.creation = Time.decode(arg4.readPointer(40, true));
            v1.expiry = Time.decode(arg4.readPointer(0x30, true));
            v1.lastAccess = Time.decode(arg4.readPointer(56, true));
            v1.secure = arg4.readBoolean(0x40, 0);
            v1.httponly = arg4.readBoolean(0x40, 1);
            v1.siteRestrictions = arg4.readInt(68);
            CookieSameSite.validate(v1.siteRestrictions);
            v1.priority = arg4.readInt(72);
            CookiePriority.validate(v1.priority);
        }
        catch(Throwable v0) {
            arg4.decreaseStackDepth();
            throw v0;
        }

        arg4.decreaseStackDepth();
        return v1;
    }

    public static CanonicalCookie deserialize(ByteBuffer arg2) {
        return CanonicalCookie.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CanonicalCookie deserialize(Message arg1) {
        return CanonicalCookie.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(CanonicalCookie.DEFAULT_STRUCT_INFO);
        arg5.encode(this.name, 8, false);
        arg5.encode(this.value, 16, false);
        arg5.encode(this.domain, 24, false);
        arg5.encode(this.path, 0x20, false);
        arg5.encode(this.creation, 40, true);
        arg5.encode(this.expiry, 0x30, true);
        arg5.encode(this.lastAccess, 56, true);
        arg5.encode(this.secure, 0x40, 0);
        arg5.encode(this.httponly, 0x40, 1);
        arg5.encode(this.siteRestrictions, 68);
        arg5.encode(this.priority, 72);
    }
}

