package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class GpuFeatureInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public int[] appliedGpuBlacklistEntries;
    public int[] appliedGpuDriverBugListEntries;
    public String disabledExtensions;
    public String disabledWebglExtensions;
    public int[] enabledGpuDriverBugWorkarounds;
    public int[] statusValues;

    static {
        GpuFeatureInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        GpuFeatureInfo.DEFAULT_STRUCT_INFO = GpuFeatureInfo.VERSION_ARRAY[0];
    }

    public GpuFeatureInfo() {
        this(0);
    }

    private GpuFeatureInfo(int arg2) {
        super(56, arg2);
    }

    public static GpuFeatureInfo decode(Decoder arg5) {
        GpuFeatureInfo v1;
        if(arg5 == null) {
            return null;
        }

        arg5.increaseStackDepth();
        try {
            v1 = new GpuFeatureInfo(arg5.readAndValidateDataHeader(GpuFeatureInfo.VERSION_ARRAY).elementsOrVersion);
            int v2 = -1;
            v1.statusValues = arg5.readInts(8, 0, v2);
            int v0_1;
            for(v0_1 = 0; v0_1 < v1.statusValues.length; ++v0_1) {
                GpuFeatureStatus.validate(v1.statusValues[v0_1]);
            }

            v1.enabledGpuDriverBugWorkarounds = arg5.readInts(16, 0, v2);
            v1.disabledExtensions = arg5.readString(24, false);
            v1.disabledWebglExtensions = arg5.readString(0x20, false);
            v1.appliedGpuBlacklistEntries = arg5.readInts(40, 0, v2);
            v1.appliedGpuDriverBugListEntries = arg5.readInts(0x30, 0, v2);
        }
        catch(Throwable v0) {
            goto label_41;
        }

        arg5.decreaseStackDepth();
        return v1;
    label_41:
        arg5.decreaseStackDepth();
        throw v0;
    }

    public static GpuFeatureInfo deserialize(ByteBuffer arg2) {
        return GpuFeatureInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static GpuFeatureInfo deserialize(Message arg1) {
        return GpuFeatureInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(GpuFeatureInfo.DEFAULT_STRUCT_INFO);
        arg5.encode(this.statusValues, 8, 0, -1);
        arg5.encode(this.enabledGpuDriverBugWorkarounds, 16, 0, -1);
        arg5.encode(this.disabledExtensions, 24, false);
        arg5.encode(this.disabledWebglExtensions, 0x20, false);
        arg5.encode(this.appliedGpuBlacklistEntries, 40, 0, -1);
        arg5.encode(this.appliedGpuDriverBugListEntries, 0x30, 0, -1);
    }
}

