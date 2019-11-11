package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.UntypedHandle;

public final class File extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public UntypedHandle fd;

    static {
        File.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        File.DEFAULT_STRUCT_INFO = File.VERSION_ARRAY[0];
    }

    public File() {
        this(0);
    }

    private File(int arg2) {
        super(16, arg2);
        this.fd = InvalidHandle.INSTANCE;
    }

    public static File decode(Decoder arg3) {
        File v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new File(arg3.readAndValidateDataHeader(File.VERSION_ARRAY).elementsOrVersion);
            v1.fd = arg3.readUntypedHandle(8, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static File deserialize(ByteBuffer arg2) {
        return File.deserialize(new Message(arg2, new ArrayList()));
    }

    public static File deserialize(Message arg1) {
        return File.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4.getEncoderAtDataOffset(File.DEFAULT_STRUCT_INFO).encode(this.fd, 8, false);
    }
}

