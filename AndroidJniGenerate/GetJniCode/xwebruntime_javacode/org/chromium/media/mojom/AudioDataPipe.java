package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

public final class AudioDataPipe extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public SharedBufferHandle sharedMemory;
    public UntypedHandle socket;

    static {
        AudioDataPipe.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        AudioDataPipe.DEFAULT_STRUCT_INFO = AudioDataPipe.VERSION_ARRAY[0];
    }

    public AudioDataPipe() {
        this(0);
    }

    private AudioDataPipe(int arg2) {
        super(16, arg2);
        this.sharedMemory = InvalidHandle.INSTANCE;
        this.socket = InvalidHandle.INSTANCE;
    }

    public static AudioDataPipe decode(Decoder arg3) {
        AudioDataPipe v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new AudioDataPipe(arg3.readAndValidateDataHeader(AudioDataPipe.VERSION_ARRAY).elementsOrVersion);
            v1.sharedMemory = arg3.readSharedBufferHandle(8, false);
            v1.socket = arg3.readUntypedHandle(12, false);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static AudioDataPipe deserialize(ByteBuffer arg2) {
        return AudioDataPipe.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AudioDataPipe deserialize(Message arg1) {
        return AudioDataPipe.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(AudioDataPipe.DEFAULT_STRUCT_INFO);
        arg4.encode(this.sharedMemory, 8, false);
        arg4.encode(this.socket, 12, false);
    }
}

