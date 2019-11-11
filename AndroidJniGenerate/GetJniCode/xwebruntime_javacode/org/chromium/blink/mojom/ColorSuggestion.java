package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class ColorSuggestion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int color;
    public String label;

    static {
        ColorSuggestion.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        ColorSuggestion.DEFAULT_STRUCT_INFO = ColorSuggestion.VERSION_ARRAY[0];
    }

    public ColorSuggestion() {
        this(0);
    }

    private ColorSuggestion(int arg2) {
        super(24, arg2);
    }

    public static ColorSuggestion decode(Decoder arg3) {
        ColorSuggestion v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new ColorSuggestion(arg3.readAndValidateDataHeader(ColorSuggestion.VERSION_ARRAY).elementsOrVersion);
            v1.color = arg3.readInt(8);
            v1.label = arg3.readString(16, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static ColorSuggestion deserialize(ByteBuffer arg2) {
        return ColorSuggestion.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ColorSuggestion deserialize(Message arg1) {
        return ColorSuggestion.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(ColorSuggestion.DEFAULT_STRUCT_INFO);
        arg4.encode(this.color, 8);
        arg4.encode(this.label, 16, false);
    }
}

