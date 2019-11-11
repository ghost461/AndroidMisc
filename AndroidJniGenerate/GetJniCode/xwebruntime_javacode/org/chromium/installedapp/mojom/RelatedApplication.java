package org.chromium.installedapp.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class RelatedApplication extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 0x20;
    private static final DataHeader[] VERSION_ARRAY;
    public String id;
    public String platform;
    public String url;

    static {
        RelatedApplication.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
        RelatedApplication.DEFAULT_STRUCT_INFO = RelatedApplication.VERSION_ARRAY[0];
    }

    public RelatedApplication() {
        this(0);
    }

    private RelatedApplication(int arg2) {
        super(0x20, arg2);
    }

    public static RelatedApplication decode(Decoder arg3) {
        RelatedApplication v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new RelatedApplication(arg3.readAndValidateDataHeader(RelatedApplication.VERSION_ARRAY).elementsOrVersion);
            v1.platform = arg3.readString(8, false);
            v1.url = arg3.readString(16, true);
            v1.id = arg3.readString(24, true);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static RelatedApplication deserialize(ByteBuffer arg2) {
        return RelatedApplication.deserialize(new Message(arg2, new ArrayList()));
    }

    public static RelatedApplication deserialize(Message arg1) {
        return RelatedApplication.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(RelatedApplication.DEFAULT_STRUCT_INFO);
        arg4.encode(this.platform, 8, false);
        arg4.encode(this.url, 16, true);
        arg4.encode(this.id, 24, true);
    }
}

