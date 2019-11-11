package org.chromium.mojo_base.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class FileInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        FileInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
        FileInfo.DEFAULT_STRUCT_INFO = FileInfo.VERSION_ARRAY[0];
    }

    public FileInfo() {
        this(0);
    }

    private FileInfo(int arg2) {
        super(8, arg2);
    }

    public static FileInfo decode(Decoder arg2) {
        FileInfo v1;
        if(arg2 == null) {
            return null;
        }

        arg2.increaseStackDepth();
        try {
            v1 = new FileInfo(arg2.readAndValidateDataHeader(FileInfo.VERSION_ARRAY).elementsOrVersion);
        }
        catch(Throwable v0) {
            arg2.decreaseStackDepth();
            throw v0;
        }

        arg2.decreaseStackDepth();
        return v1;
    }

    public static FileInfo deserialize(ByteBuffer arg2) {
        return FileInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static FileInfo deserialize(Message arg1) {
        return FileInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg2) {
        arg2.getEncoderAtDataOffset(FileInfo.DEFAULT_STRUCT_INFO);
    }
}

