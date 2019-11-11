package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class SpellCheckSuggestion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public String suggestion;

    static {
        SpellCheckSuggestion.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        SpellCheckSuggestion.DEFAULT_STRUCT_INFO = SpellCheckSuggestion.VERSION_ARRAY[0];
    }

    public SpellCheckSuggestion() {
        this(0);
    }

    private SpellCheckSuggestion(int arg2) {
        super(16, arg2);
    }

    public static SpellCheckSuggestion decode(Decoder arg3) {
        SpellCheckSuggestion v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new SpellCheckSuggestion(arg3.readAndValidateDataHeader(SpellCheckSuggestion.VERSION_ARRAY).elementsOrVersion);
            v1.suggestion = arg3.readString(8, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static SpellCheckSuggestion deserialize(ByteBuffer arg2) {
        return SpellCheckSuggestion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static SpellCheckSuggestion deserialize(Message arg1) {
        return SpellCheckSuggestion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(SpellCheckSuggestion.DEFAULT_STRUCT_INFO).encode(this.suggestion, 8, false);
    }
}

