package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class Rule extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public String hostPattern;
    public String replacement;

    static {
        Rule.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        Rule.DEFAULT_STRUCT_INFO = Rule.VERSION_ARRAY[0];
    }

    public Rule() {
        this(0);
    }

    private Rule(int arg2) {
        super(24, arg2);
    }

    public static Rule decode(Decoder arg3) {
        Rule v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new Rule(arg3.readAndValidateDataHeader(Rule.VERSION_ARRAY).elementsOrVersion);
            v1.hostPattern = arg3.readString(8, false);
            v1.replacement = arg3.readString(16, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static Rule deserialize(ByteBuffer arg2) {
        return Rule.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Rule deserialize(Message arg1) {
        return Rule.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(Rule.DEFAULT_STRUCT_INFO);
        arg4.encode(this.hostPattern, 8, false);
        arg4.encode(this.replacement, 16, false);
    }
}

