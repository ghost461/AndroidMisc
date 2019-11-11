package org.chromium.blink.mojom;

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
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.url.mojom.Url;

public final class ServiceWorkerScriptInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 56;
    private static final DataHeader[] VERSION_ARRAY;
    public ConsumerHandle body;
    public long bodySize;
    public String encoding;
    public Map headers;
    public ConsumerHandle metaData;
    public long metaDataSize;
    public Url scriptUrl;

    static {
        ServiceWorkerScriptInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(56, 0)};
        ServiceWorkerScriptInfo.DEFAULT_STRUCT_INFO = ServiceWorkerScriptInfo.VERSION_ARRAY[0];
    }

    public ServiceWorkerScriptInfo() {
        this(0);
    }

    private ServiceWorkerScriptInfo(int arg2) {
        super(56, arg2);
        this.body = InvalidHandle.INSTANCE;
        this.metaData = InvalidHandle.INSTANCE;
    }

    public static ServiceWorkerScriptInfo decode(Decoder arg10) {
        ServiceWorkerScriptInfo v1;
        if(arg10 == null) {
            return null;
        }

        arg10.increaseStackDepth();
        try {
            v1 = new ServiceWorkerScriptInfo(arg10.readAndValidateDataHeader(ServiceWorkerScriptInfo.VERSION_ARRAY).elementsOrVersion);
            int v0_1 = 8;
            v1.scriptUrl = Url.decode(arg10.readPointer(v0_1, false));
            int v3 = 16;
            v1.encoding = arg10.readString(v3, false);
            Decoder v4 = arg10.readPointer(24, false);
            v4.readDataHeaderForMap();
            Decoder v5 = v4.readPointer(v0_1, false);
            DataHeader v6 = v5.readDataHeaderForPointerArray(-1);
            String[] v7 = new String[v6.elementsOrVersion];
            int v8;
            for(v8 = 0; v8 < v6.elementsOrVersion; ++v8) {
                v7[v8] = v5.readString(v8 * 8 + v0_1, false);
            }

            Decoder v3_1 = v4.readPointer(v3, false);
            DataHeader v4_1 = v3_1.readDataHeaderForPointerArray(v7.length);
            String[] v5_1 = new String[v4_1.elementsOrVersion];
            int v6_1;
            for(v6_1 = 0; v6_1 < v4_1.elementsOrVersion; ++v6_1) {
                v5_1[v6_1] = v3_1.readString(v6_1 * 8 + v0_1, false);
            }

            v1.headers = new HashMap();
            for(v0_1 = 0; v0_1 < v7.length; ++v0_1) {
                v1.headers.put(v7[v0_1], v5_1[v0_1]);
            }

            v1.body = arg10.readConsumerHandle(0x20, false);
            v1.metaData = arg10.readConsumerHandle(36, true);
            v1.bodySize = arg10.readLong(40);
            v1.metaDataSize = arg10.readLong(0x30);
        }
        catch(Throwable v0) {
            goto label_76;
        }

        arg10.decreaseStackDepth();
        return v1;
    label_76:
        arg10.decreaseStackDepth();
        throw v0;
    }

    public static ServiceWorkerScriptInfo deserialize(ByteBuffer arg2) {
        return ServiceWorkerScriptInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static ServiceWorkerScriptInfo deserialize(Message arg1) {
        return ServiceWorkerScriptInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg13) {
        arg13 = arg13.getEncoderAtDataOffset(ServiceWorkerScriptInfo.DEFAULT_STRUCT_INFO);
        int v1 = 8;
        arg13.encode(this.scriptUrl, v1, false);
        int v3 = 16;
        arg13.encode(this.encoding, v3, false);
        int v4 = 24;
        if(this.headers == null) {
            arg13.encodeNullPointer(v4, false);
        }
        else {
            Encoder v0 = arg13.encoderForMap(v4);
            v4 = this.headers.size();
            String[] v6 = new String[v4];
            String[] v4_1 = new String[v4];
            Iterator v7 = this.headers.entrySet().iterator();
            int v8;
            for(v8 = 0; v7.hasNext(); ++v8) {
                Object v9 = v7.next();
                v6[v8] = ((Map$Entry)v9).getKey();
                v4_1[v8] = ((Map$Entry)v9).getValue();
            }

            v8 = -1;
            Encoder v7_1 = v0.encodePointerArray(v6.length, v1, v8);
            int v9_1;
            for(v9_1 = 0; v9_1 < v6.length; ++v9_1) {
                v7_1.encode(v6[v9_1], v9_1 * 8 + v1, false);
            }

            v0 = v0.encodePointerArray(v4_1.length, v3, v8);
            for(v3 = 0; v3 < v4_1.length; ++v3) {
                v0.encode(v4_1[v3], v3 * 8 + v1, false);
            }
        }

        arg13.encode(this.body, 0x20, false);
        arg13.encode(this.metaData, 36, true);
        arg13.encode(this.bodySize, 40);
        arg13.encode(this.metaDataSize, 0x30);
    }
}

