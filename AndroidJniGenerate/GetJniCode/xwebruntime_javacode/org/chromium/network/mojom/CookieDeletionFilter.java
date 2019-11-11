package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.Time;
import org.chromium.url.mojom.Url;

public final class CookieDeletionFilter extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x40;
    private static final DataHeader[] VERSION_ARRAY;
    public String cookieName;
    public Time createdAfterTime;
    public Time createdBeforeTime;
    public String[] excludingDomains;
    public String[] includingDomains;
    public int sessionControl;
    public Url url;

    static {
        CookieDeletionFilter.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x40, 0)};
        CookieDeletionFilter.DEFAULT_STRUCT_INFO = CookieDeletionFilter.VERSION_ARRAY[0];
    }

    public CookieDeletionFilter() {
        this(0);
    }

    private CookieDeletionFilter(int arg2) {
        super(0x40, arg2);
        this.sessionControl = 0;
    }

    public static CookieDeletionFilter decode(Decoder arg11) {
        CookieDeletionFilter v2;
        CookieDeletionFilter v0 = null;
        if(arg11 == null) {
            return v0;
        }

        arg11.increaseStackDepth();
        try {
            v2 = new CookieDeletionFilter(arg11.readAndValidateDataHeader(CookieDeletionFilter.VERSION_ARRAY).elementsOrVersion);
            int v1 = 8;
            v2.createdAfterTime = Time.decode(arg11.readPointer(v1, true));
            v2.createdBeforeTime = Time.decode(arg11.readPointer(16, true));
            Decoder v4 = arg11.readPointer(24, true);
            int v5 = -1;
            if(v4 == null) {
                v2.excludingDomains = ((String[])v0);
            }
            else {
                DataHeader v7 = v4.readDataHeaderForPointerArray(v5);
                v2.excludingDomains = new String[v7.elementsOrVersion];
                int v8;
                for(v8 = 0; v8 < v7.elementsOrVersion; ++v8) {
                    v2.excludingDomains[v8] = v4.readString(v8 * 8 + v1, false);
                }
            }

            v4 = arg11.readPointer(0x20, true);
            if(v4 == null) {
                v2.includingDomains = ((String[])v0);
            }
            else {
                DataHeader v0_2 = v4.readDataHeaderForPointerArray(v5);
                v2.includingDomains = new String[v0_2.elementsOrVersion];
                for(v5 = 0; v5 < v0_2.elementsOrVersion; ++v5) {
                    v2.includingDomains[v5] = v4.readString(v5 * 8 + v1, false);
                }
            }

            v2.cookieName = arg11.readString(40, true);
            v2.url = Url.decode(arg11.readPointer(0x30, true));
            v2.sessionControl = arg11.readInt(56);
            CookieDeletionSessionControl.validate(v2.sessionControl);
        }
        catch(Throwable v0_1) {
            arg11.decreaseStackDepth();
            throw v0_1;
        }

        arg11.decreaseStackDepth();
        return v2;
    }

    public static CookieDeletionFilter deserialize(ByteBuffer arg2) {
        return CookieDeletionFilter.deserialize(new Message(arg2, new ArrayList()));
    }

    public static CookieDeletionFilter deserialize(Message arg1) {
        return CookieDeletionFilter.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg9) {
        Encoder v0;
        arg9 = arg9.getEncoderAtDataOffset(CookieDeletionFilter.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg9.encode(this.createdAfterTime, v1, true);
        arg9.encode(this.createdBeforeTime, 16, true);
        int v3 = -1;
        int v4 = 24;
        if(this.excludingDomains == null) {
            arg9.encodeNullPointer(v4, true);
        }
        else {
            v0 = arg9.encodePointerArray(this.excludingDomains.length, v4, v3);
            for(v4 = 0; v4 < this.excludingDomains.length; ++v4) {
                v0.encode(this.excludingDomains[v4], v4 * 8 + v1, false);
            }
        }

        v4 = 0x20;
        if(this.includingDomains == null) {
            arg9.encodeNullPointer(v4, true);
        }
        else {
            v0 = arg9.encodePointerArray(this.includingDomains.length, v4, v3);
            for(v3 = 0; v3 < this.includingDomains.length; ++v3) {
                v0.encode(this.includingDomains[v3], v3 * 8 + v1, false);
            }
        }

        arg9.encode(this.cookieName, 40, true);
        arg9.encode(this.url, 0x30, true);
        arg9.encode(this.sessionControl, 56);
    }
}

