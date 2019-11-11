package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class AudioParameters extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        AudioParameters.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        AudioParameters.DEFAULT_STRUCT_INFO = AudioParameters.VERSION_ARRAY[0];
    }

    public AudioParameters() {
        this(0);
    }

    private AudioParameters(int arg2) {
        super(8, arg2);
    }

    public static AudioParameters decode(Decoder arg2) {
        AudioParameters v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new AudioParameters(arg2.readAndValidateDataHeader(AudioParameters.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static AudioParameters deserialize(ByteBuffer arg2) {
        return AudioParameters.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AudioParameters deserialize(Message arg1) {
        return AudioParameters.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(AudioParameters.DEFAULT_STRUCT_INFO);
    }
}

