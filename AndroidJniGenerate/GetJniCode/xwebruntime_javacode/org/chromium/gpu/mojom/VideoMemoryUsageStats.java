package org.chromium.gpu.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class VideoMemoryUsageStats extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public long bytesAllocated;
    public Map processMap;

    static {
        VideoMemoryUsageStats.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        VideoMemoryUsageStats.DEFAULT_STRUCT_INFO = VideoMemoryUsageStats.VERSION_ARRAY[0];
    }

    public VideoMemoryUsageStats() {
        this(0);
    }

    private VideoMemoryUsageStats(int arg2) {
        super(24, arg2);
    }

    public static VideoMemoryUsageStats decode(Decoder arg10) {
        VideoMemoryUsageStats v1;
        if(arg10 == null) {
            return null;
        }

        arg10.increaseStackDepth();
        try {
            v1 = new VideoMemoryUsageStats(arg10.readAndValidateDataHeader(VideoMemoryUsageStats.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            int v2 = 0;
            Decoder v3 = arg10.readPointer(v0_1, false);
            v3.readDataHeaderForMap();
            int[] v4 = v3.readInts(v0_1, 0, -1);
            int v5 = 16;
            v3 = v3.readPointer(v5, false);
            DataHeader v6 = v3.readDataHeaderForPointerArray(v4.length);
            VideoMemoryProcessStats[] v7 = new VideoMemoryProcessStats[v6.elementsOrVersion];
            int v8;
            for(v8 = 0; v8 < v6.elementsOrVersion; ++v8) {
                v7[v8] = VideoMemoryProcessStats.decode(v3.readPointer(v8 * 8 + v0_1, false));
            }

            v1.processMap = new HashMap();
            while(v2 < v4.length) {
                v1.processMap.put(Integer.valueOf(v4[v2]), v7[v2]);
                ++v2;
            }

            v1.bytesAllocated = arg10.readLong(v5);
        }
        catch(Throwable v0) {
            goto label_48;
        }

        arg10.decreaseStackDepth();
        return v1;
    label_48:
        arg10.decreaseStackDepth();
        throw v0;
    }

    public static VideoMemoryUsageStats deserialize(ByteBuffer arg2) {
        return VideoMemoryUsageStats.deserialize(new Message(arg2, new ArrayList()));
    }

    public static VideoMemoryUsageStats deserialize(Message arg1) {
        return VideoMemoryUsageStats.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg11) {
        arg11 = arg11.getEncoderAtDataOffset(VideoMemoryUsageStats.DEFAULT_STRUCT_INFO);
        int v1 = 16;
        int v3 = 8;
        if(this.processMap == null) {
            arg11.encodeNullPointer(v3, false);
        }
        else {
            Encoder v0 = arg11.encoderForMap(v3);
            int v4 = this.processMap.size();
            int[] v5 = new int[v4];
            VideoMemoryProcessStats[] v4_1 = new VideoMemoryProcessStats[v4];
            Iterator v6 = this.processMap.entrySet().iterator();
            int v7;
            for(v7 = 0; v6.hasNext(); ++v7) {
                Object v8 = v6.next();
                v5[v7] = ((Map$Entry)v8).getKey().intValue();
                v4_1[v7] = ((Map$Entry)v8).getValue();
            }

            v0.encode(v5, v3, 0, -1);
            v0 = v0.encodePointerArray(v4_1.length, v1, -1);
            int v5_1;
            for(v5_1 = 0; v5_1 < v4_1.length; ++v5_1) {
                v0.encode(v4_1[v5_1], v5_1 * 8 + v3, false);
            }
        }

        arg11.encode(this.bytesAllocated, v1);
    }
}

