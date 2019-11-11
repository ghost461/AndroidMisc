package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class EncryptionScheme extends Struct {
    public final class CipherMode {
        private static final boolean IS_EXTENSIBLE = false;

        private CipherMode() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(CipherMode.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public int mode;
    public EncryptionPattern pattern;

    static {
        EncryptionScheme.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        EncryptionScheme.DEFAULT_STRUCT_INFO = EncryptionScheme.VERSION_ARRAY[0];
    }

    public EncryptionScheme() {
        this(0);
    }

    private EncryptionScheme(int arg2) {
        super(24, arg2);
    }

    public static EncryptionScheme decode(Decoder arg3) {
        EncryptionScheme v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new EncryptionScheme(arg3.readAndValidateDataHeader(EncryptionScheme.VERSION_ARRAY).elementsOrVersion);
            v1.mode = arg3.readInt(8);
            CipherMode.validate(v1.mode);
            v1.pattern = EncryptionPattern.decode(arg3.readPointer(16, false));
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static EncryptionScheme deserialize(ByteBuffer arg2) {
        return EncryptionScheme.deserialize(new Message(arg2, new ArrayList()));
    }

    public static EncryptionScheme deserialize(Message arg1) {
        return EncryptionScheme.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(EncryptionScheme.DEFAULT_STRUCT_INFO);
        arg4.encode(this.mode, 8);
        arg4.encode(this.pattern, 16, false);
    }
}

