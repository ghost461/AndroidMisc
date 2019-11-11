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

public final class DxDiagNode extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public Map children;
    public Map values;

    static {
        DxDiagNode.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
        DxDiagNode.DEFAULT_STRUCT_INFO = DxDiagNode.VERSION_ARRAY[0];
    }

    public DxDiagNode() {
        this(0);
    }

    private DxDiagNode(int arg2) {
        super(24, arg2);
    }

    public static DxDiagNode decode(Decoder arg11) {
        DxDiagNode v1;
        if(arg11 == null) {
            return null;
        }

        arg11.increaseStackDepth();
        try {
            v1 = new DxDiagNode(arg11.readAndValidateDataHeader(DxDiagNode.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            int v2 = 0;
            Decoder v3 = arg11.readPointer(v0_1, false);
            v3.readDataHeaderForMap();
            Decoder v4 = v3.readPointer(v0_1, false);
            int v5 = -1;
            DataHeader v6 = v4.readDataHeaderForPointerArray(v5);
            String[] v7 = new String[v6.elementsOrVersion];
            int v8;
            for(v8 = 0; v8 < v6.elementsOrVersion; ++v8) {
                v7[v8] = v4.readString(v8 * 8 + v0_1, false);
            }

            int v4_1 = 16;
            v3 = v3.readPointer(v4_1, false);
            v6 = v3.readDataHeaderForPointerArray(v7.length);
            String[] v8_1 = new String[v6.elementsOrVersion];
            int v9;
            for(v9 = 0; v9 < v6.elementsOrVersion; ++v9) {
                v8_1[v9] = v3.readString(v9 * 8 + v0_1, false);
            }

            v1.values = new HashMap();
            int v3_1;
            for(v3_1 = 0; v3_1 < v7.length; ++v3_1) {
                v1.values.put(v7[v3_1], v8_1[v3_1]);
            }

            v3 = arg11.readPointer(v4_1, false);
            v3.readDataHeaderForMap();
            Decoder v6_1 = v3.readPointer(v0_1, false);
            DataHeader v5_1 = v6_1.readDataHeaderForPointerArray(v5);
            v7 = new String[v5_1.elementsOrVersion];
            for(v8 = 0; v8 < v5_1.elementsOrVersion; ++v8) {
                v7[v8] = v6_1.readString(v8 * 8 + v0_1, false);
            }

            v3 = v3.readPointer(v4_1, false);
            DataHeader v4_2 = v3.readDataHeaderForPointerArray(v7.length);
            DxDiagNode[] v5_2 = new DxDiagNode[v4_2.elementsOrVersion];
            int v6_2;
            for(v6_2 = 0; v6_2 < v4_2.elementsOrVersion; ++v6_2) {
                v5_2[v6_2] = DxDiagNode.decode(v3.readPointer(v6_2 * 8 + v0_1, false));
            }

            v1.children = new HashMap();
            while(v2 < v7.length) {
                v1.children.put(v7[v2], v5_2[v2]);
                ++v2;
            }
        }
        catch(Throwable v0) {
            goto label_98;
        }

        arg11.decreaseStackDepth();
        return v1;
    label_98:
        arg11.decreaseStackDepth();
        throw v0;
    }

    public static DxDiagNode deserialize(ByteBuffer arg2) {
        return DxDiagNode.deserialize(new Message(arg2, new ArrayList()));
    }

    public static DxDiagNode deserialize(Message arg1) {
        return DxDiagNode.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg12) {
        String[] v5_1;
        arg12 = arg12.getEncoderAtDataOffset(DxDiagNode.DEFAULT_STRUCT_INFO);
        int v1 = 16;
        int v2 = -1;
        int v4 = 8;
        if(this.values == null) {
            arg12.encodeNullPointer(v4, false);
        }
        else {
            Encoder v0 = arg12.encoderForMap(v4);
            int v5 = this.values.size();
            String[] v6 = new String[v5];
            v5_1 = new String[v5];
            Iterator v7 = this.values.entrySet().iterator();
            int v8;
            for(v8 = 0; v7.hasNext(); ++v8) {
                Object v9 = v7.next();
                v6[v8] = ((Map$Entry)v9).getKey();
                v5_1[v8] = ((Map$Entry)v9).getValue();
            }

            Encoder v7_1 = v0.encodePointerArray(v6.length, v4, v2);
            for(v8 = 0; v8 < v6.length; ++v8) {
                v7_1.encode(v6[v8], v8 * 8 + v4, false);
            }

            v0 = v0.encodePointerArray(v5_1.length, v1, v2);
            int v6_1;
            for(v6_1 = 0; v6_1 < v5_1.length; ++v6_1) {
                v0.encode(v5_1[v6_1], v6_1 * 8 + v4, false);
            }
        }

        if(this.children == null) {
            arg12.encodeNullPointer(v1, false);
        }
        else {
            arg12 = arg12.encoderForMap(v1);
            int v0_1 = this.children.size();
            v5_1 = new String[v0_1];
            DxDiagNode[] v0_2 = new DxDiagNode[v0_1];
            Iterator v6_2 = this.children.entrySet().iterator();
            int v7_2;
            for(v7_2 = 0; v6_2.hasNext(); ++v7_2) {
                Object v8_1 = v6_2.next();
                v5_1[v7_2] = ((Map$Entry)v8_1).getKey();
                v0_2[v7_2] = ((Map$Entry)v8_1).getValue();
            }

            Encoder v6_3 = arg12.encodePointerArray(v5_1.length, v4, v2);
            for(v7_2 = 0; v7_2 < v5_1.length; ++v7_2) {
                v6_3.encode(v5_1[v7_2], v7_2 * 8 + v4, false);
            }

            arg12 = arg12.encodePointerArray(v0_2.length, v1, v2);
            for(v1 = 0; v1 < v0_2.length; ++v1) {
                arg12.encode(v0_2[v1], v1 * 8 + v4, false);
            }
        }
    }
}

