package org.chromium.net.interfaces;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class AddressList extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public IpEndPoint[] addresses;

    static {
        AddressList.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
        AddressList.DEFAULT_STRUCT_INFO = AddressList.VERSION_ARRAY[0];
    }

    public AddressList() {
        this(0);
    }

    private AddressList(int arg2) {
        super(16, arg2);
    }

    public static AddressList decode(Decoder arg8) {
        AddressList v1;
        if(arg8 == null) {
            return null;
        }

        arg8.increaseStackDepth();
        try {
            v1 = new AddressList(arg8.readAndValidateDataHeader(AddressList.VERSION_ARRAY).elementsOrVersion);
            int v2 = 8;
            Decoder v3 = arg8.readPointer(v2, false);
            DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
            v1.addresses = new IpEndPoint[v4.elementsOrVersion];
            int v5;
            for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                v1.addresses[v5] = IpEndPoint.decode(v3.readPointer(v5 * 8 + v2, false));
            }
        }
        catch(Throwable v0) {
            goto label_31;
        }

        arg8.decreaseStackDepth();
        return v1;
    label_31:
        arg8.decreaseStackDepth();
        throw v0;
    }

    public static AddressList deserialize(ByteBuffer arg2) {
        return AddressList.deserialize(new Message(arg2, new ArrayList()));
    }

    public static AddressList deserialize(Message arg1) {
        return AddressList.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg6) {
        arg6 = arg6.getEncoderAtDataOffset(AddressList.DEFAULT_STRUCT_INFO);
        int v2 = 8;
        if(this.addresses == null) {
            arg6.encodeNullPointer(v2, false);
        }
        else {
            arg6 = arg6.encodePointerArray(this.addresses.length, v2, -1);
            int v0;
            for(v0 = 0; v0 < this.addresses.length; ++v0) {
                arg6.encode(this.addresses[v0], v0 * 8 + v2, false);
            }
        }
    }
}

