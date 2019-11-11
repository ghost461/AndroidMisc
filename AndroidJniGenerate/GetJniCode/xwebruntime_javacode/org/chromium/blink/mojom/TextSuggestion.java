package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class TextSuggestion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public int markerTag;
    public String prefix;
    public String suffix;
    public String suggestion;
    public int suggestionIndex;

    static {
        TextSuggestion.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        TextSuggestion.DEFAULT_STRUCT_INFO = TextSuggestion.VERSION_ARRAY[0];
    }

    public TextSuggestion() {
        this(0);
    }

    private TextSuggestion(int arg2) {
        super(40, arg2);
    }

    public static TextSuggestion decode(Decoder arg3) {
        TextSuggestion v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new TextSuggestion(arg3.readAndValidateDataHeader(TextSuggestion.VERSION_ARRAY).elementsOrVersion);
            v1.markerTag = arg3.readInt(8);
            v1.suggestionIndex = arg3.readInt(12);
            v1.prefix = arg3.readString(16, false);
            v1.suggestion = arg3.readString(24, false);
            v1.suffix = arg3.readString(0x20, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static TextSuggestion deserialize(ByteBuffer arg2) {
        return TextSuggestion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static TextSuggestion deserialize(Message arg1) {
        return TextSuggestion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(TextSuggestion.DEFAULT_STRUCT_INFO);
        arg4.encode(this.markerTag, 8);
        arg4.encode(this.suggestionIndex, 12);
        arg4.encode(this.prefix, 16, false);
        arg4.encode(this.suggestion, 24, false);
        arg4.encode(this.suffix, 0x20, false);
    }
}

