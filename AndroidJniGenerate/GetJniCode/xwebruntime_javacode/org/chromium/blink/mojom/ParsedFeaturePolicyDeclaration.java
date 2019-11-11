package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.url.mojom.Origin;

public final class ParsedFeaturePolicyDeclaration extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int feature;
    public boolean matchesAllOrigins;
    public Origin[] origins;

    static {
        ParsedFeaturePolicyDeclaration.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        ParsedFeaturePolicyDeclaration.DEFAULT_STRUCT_INFO = ParsedFeaturePolicyDeclaration.VERSION_ARRAY[0];
    }

    public ParsedFeaturePolicyDeclaration() {
        this(0);
    }

    private ParsedFeaturePolicyDeclaration(int arg2) {
        super(24, arg2);
    }

    public static ParsedFeaturePolicyDeclaration decode(Decoder arg8) {
        ParsedFeaturePolicyDeclaration v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new ParsedFeaturePolicyDeclaration(arg8.readAndValidateDataHeader(ParsedFeaturePolicyDeclaration.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.feature = arg8.readInt(v0_1);
            FeaturePolicyFeature.validate(v1.feature);
            v1.matchesAllOrigins = arg8.readBoolean(12, 0);
            Decoder v2 = arg8.readPointer(16, false);
            DataHeader v4 = v2.readDataHeaderForPointerArray(-1);
            v1.origins = new Origin[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.origins[v5] = Origin.decode(v2.readPointer(v5 * 8 + v0_1, false));
            }
        }
        catch(Throwable v0) {
            goto label_39;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_39:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static ParsedFeaturePolicyDeclaration deserialize(ByteBuffer arg2) {
        return ParsedFeaturePolicyDeclaration.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ParsedFeaturePolicyDeclaration deserialize(Message arg1) {
        return ParsedFeaturePolicyDeclaration.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(ParsedFeaturePolicyDeclaration.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg6.encode(this.feature, v1);
        arg6.encode(this.matchesAllOrigins, 12, 0);
        int v3 = 16;
        if(this.origins == null) {
            arg6.encodeNullPointer(v3, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.origins.length, v3, -1);
            int v0;
            for(v0 = 0; v0 < this.origins.length; ++v0) {
                arg6.encode(this.origins[v0], v0 * 8 + v1, false);
            }
        }
    }
}

