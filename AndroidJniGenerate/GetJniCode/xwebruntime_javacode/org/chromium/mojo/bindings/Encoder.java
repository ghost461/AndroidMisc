package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.Pair;

public class Encoder {
    class org.chromium.mojo.bindings.Encoder$1 {
    }

    class EncoderState {
        public ByteBuffer byteBuffer;
        public final Core core;
        public int dataEnd;
        public final List handles;

        static {
        }

        EncoderState(Core arg1, int arg2, org.chromium.mojo.bindings.Encoder$1 arg3) {
            this(arg1, arg2);
        }

        private EncoderState(Core arg2, int arg3) {
            super();
            this.handles = new ArrayList();
            this.core = arg2;
            if(arg3 > 0) {
            }
            else {
                arg3 = 0x400;
            }

            this.byteBuffer = ByteBuffer.allocateDirect(arg3);
            this.byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            this.dataEnd = 0;
        }

        public void claimMemory(int arg2) {
            this.dataEnd += arg2;
            this.growIfNeeded();
        }

        private void growIfNeeded() {
            if(this.byteBuffer.capacity() >= this.dataEnd) {
                return;
            }

            int v0;
            for(v0 = this.byteBuffer.capacity() * 2; v0 < this.dataEnd; v0 *= 2) {
            }

            ByteBuffer v0_1 = ByteBuffer.allocateDirect(v0);
            v0_1.order(ByteOrder.nativeOrder());
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.byteBuffer.capacity());
            v0_1.put(this.byteBuffer);
            this.byteBuffer = v0_1;
        }
    }

    private static final int INITIAL_BUFFER_SIZE = 0x400;
    private int mBaseOffset;
    private final EncoderState mEncoderState;

    private Encoder(EncoderState arg1) {
        super();
        this.mEncoderState = arg1;
        this.mBaseOffset = arg1.dataEnd;
    }

    public Encoder(Core arg3, int arg4) {
        this(new EncoderState(arg3, arg4, null));
    }

    private void append(byte[] arg3) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.put(arg3);
    }

    private void append(double[] arg3) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asDoubleBuffer().put(arg3);
    }

    private void append(float[] arg3) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asFloatBuffer().put(arg3);
    }

    private void append(int[] arg3) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asIntBuffer().put(arg3);
    }

    private void append(long[] arg3) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asLongBuffer().put(arg3);
    }

    private void append(short[] arg3) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asShortBuffer().put(arg3);
    }

    void claimMemory(int arg2) {
        this.mEncoderState.claimMemory(BindingsHelper.align(arg2));
    }

    public void encode(long arg3, int arg5) {
        this.mEncoderState.byteBuffer.putLong(this.mBaseOffset + arg5, arg3);
    }

    public void encode(int arg3, int arg4) {
        this.mEncoderState.byteBuffer.putInt(this.mBaseOffset + arg4, arg3);
    }

    public void encode(InterfaceRequest arg2, int arg3, boolean arg4) {
        if(arg2 == null) {
            this.encodeInvalidHandle(arg3, arg4);
            return;
        }

        if(this.mEncoderState.core == null) {
            throw new UnsupportedOperationException("The encoder has been created without a Core. It can\'t encode an interface.");
        }

        this.encode(arg2.passHandle(), arg3, arg4);
    }

    public void encode(String arg2, int arg3, boolean arg4) {
        if(arg2 == null) {
            this.encodeNullPointer(arg3, arg4);
            return;
        }

        this.encode(arg2.getBytes(Charset.forName("utf8")), arg3, ((int)arg4), -1);
    }

    public void encode(Handle arg2, int arg3, boolean arg4) {
        if(arg2 == null || !arg2.isValid()) {
            this.encodeInvalidHandle(arg3, arg4);
        }
        else {
            this.encode(this.mEncoderState.handles.size(), arg3);
            this.mEncoderState.handles.add(arg2);
        }
    }

    public void encode(AssociatedInterfaceNotSupported arg1, int arg2, boolean arg3) {
    }

    public void encode(Struct arg1, int arg2, boolean arg3) {
        if(arg1 == null) {
            this.encodeNullPointer(arg2, arg3);
            return;
        }

        this.encodePointerToNextUnclaimedData(arg2);
        arg1.encode(this);
    }

    public void encode(Union arg3, int arg4, boolean arg5) {
        if(arg3 == null && !arg5) {
            throw new SerializationException("Trying to encode a null pointer for a non-nullable type.");
        }

        if(arg3 == null) {
            this.encode(0, arg4);
            this.encode(0, arg4 + 8);
            return;
        }

        arg3.encode(this, arg4);
    }

    public void encode(AssociatedInterfaceRequestNotSupported arg1, int arg2, boolean arg3) {
    }

    public void encode(Interface arg3, int arg4, boolean arg5, Manager arg6) {
        if(arg3 == null) {
            this.encodeInvalidHandle(arg4, arg5);
            this.encode(0, arg4 + 4);
            return;
        }

        if(this.mEncoderState.core == null) {
            throw new UnsupportedOperationException("The encoder has been created without a Core. It can\'t encode an interface.");
        }

        if((arg3 instanceof Proxy)) {
            Handler v3 = ((Proxy)arg3).getProxyHandler();
            this.encode(v3.passHandle(), arg4, arg5);
            this.encode(v3.getVersion(), arg4 + 4);
            return;
        }

        Pair v0 = this.mEncoderState.core.createMessagePipe(null);
        arg6.bind(arg3, v0.first);
        this.encode(v0.second, arg4, arg5);
        this.encode(arg6.getVersion(), arg4 + 4);
    }

    public void encode(byte[] arg1, int arg2, int arg3, int arg4) {
        if(arg1 == null) {
            this.encodeNullPointer(arg2, BindingsHelper.isArrayNullable(arg3));
            return;
        }

        if(arg4 != -1 && arg4 != arg1.length) {
            throw new SerializationException("Trying to encode a fixed array of incorrect length.");
        }

        this.encodeByteArray(arg1, arg1.length, arg2);
    }

    public void encode(boolean arg2, int arg3, int arg4) {
        if(arg2) {
            this.mEncoderState.byteBuffer.put(this.mBaseOffset + arg3, ((byte)(this.mEncoderState.byteBuffer.get(this.mBaseOffset + arg3) | (((byte)(1 << arg4))))));
        }
    }

    public void encode(double arg3, int arg5) {
        this.mEncoderState.byteBuffer.putDouble(this.mBaseOffset + arg5, arg3);
    }

    public void encode(byte arg3, int arg4) {
        this.mEncoderState.byteBuffer.put(this.mBaseOffset + arg4, arg3);
    }

    public void encode(int[] arg2, int arg3, int arg4, int arg5) {
        if(arg2 == null) {
            this.encodeNullPointer(arg3, BindingsHelper.isArrayNullable(arg4));
            return;
        }

        this.encoderForArray(4, arg2.length, arg3, arg5).append(arg2);
    }

    public void encode(Interface[] arg5, int arg6, int arg7, int arg8, Manager arg9) {
        if(arg5 == null) {
            this.encodeNullPointer(arg6, BindingsHelper.isArrayNullable(arg7));
            return;
        }

        int v1 = 8;
        Encoder v6 = this.encoderForArray(v1, arg5.length, arg6, arg8);
        for(arg8 = 0; arg8 < arg5.length; ++arg8) {
            v6.encode(arg5[arg8], arg8 * 8 + v1, BindingsHelper.isElementNullable(arg7), arg9);
        }
    }

    public void encode(float arg3, int arg4) {
        this.mEncoderState.byteBuffer.putFloat(this.mBaseOffset + arg4, arg3);
    }

    public void encode(DataHeader arg3) {
        this.mEncoderState.claimMemory(BindingsHelper.align(arg3.size));
        this.encode(arg3.size, 0);
        this.encode(arg3.elementsOrVersion, 4);
    }

    public void encode(short arg3, int arg4) {
        this.mEncoderState.byteBuffer.putShort(this.mBaseOffset + arg4, arg3);
    }

    public void encode(double[] arg2, int arg3, int arg4, int arg5) {
        if(arg2 == null) {
            this.encodeNullPointer(arg3, BindingsHelper.isArrayNullable(arg4));
            return;
        }

        this.encoderForArray(8, arg2.length, arg3, arg5).append(arg2);
    }

    public void encode(float[] arg2, int arg3, int arg4, int arg5) {
        if(arg2 == null) {
            this.encodeNullPointer(arg3, BindingsHelper.isArrayNullable(arg4));
            return;
        }

        this.encoderForArray(4, arg2.length, arg3, arg5).append(arg2);
    }

    public void encode(long[] arg2, int arg3, int arg4, int arg5) {
        if(arg2 == null) {
            this.encodeNullPointer(arg3, BindingsHelper.isArrayNullable(arg4));
            return;
        }

        this.encoderForArray(8, arg2.length, arg3, arg5).append(arg2);
    }

    public void encode(AssociatedInterfaceNotSupported[] arg1, int arg2, int arg3, int arg4) {
    }

    public void encode(AssociatedInterfaceRequestNotSupported[] arg1, int arg2, int arg3, int arg4) {
    }

    public void encode(InterfaceRequest[] arg4, int arg5, int arg6, int arg7) {
        if(arg4 == null) {
            this.encodeNullPointer(arg5, BindingsHelper.isArrayNullable(arg6));
            return;
        }

        Encoder v5 = this.encoderForArray(4, arg4.length, arg5, arg7);
        for(arg7 = 0; arg7 < arg4.length; ++arg7) {
            v5.encode(arg4[arg7], arg7 * 4 + 8, BindingsHelper.isElementNullable(arg6));
        }
    }

    public void encode(Handle[] arg4, int arg5, int arg6, int arg7) {
        if(arg4 == null) {
            this.encodeNullPointer(arg5, BindingsHelper.isArrayNullable(arg6));
            return;
        }

        Encoder v5 = this.encoderForArray(4, arg4.length, arg5, arg7);
        for(arg7 = 0; arg7 < arg4.length; ++arg7) {
            v5.encode(arg4[arg7], arg7 * 4 + 8, BindingsHelper.isElementNullable(arg6));
        }
    }

    public void encode(short[] arg2, int arg3, int arg4, int arg5) {
        if(arg2 == null) {
            this.encodeNullPointer(arg3, BindingsHelper.isArrayNullable(arg4));
            return;
        }

        this.encoderForArray(2, arg2.length, arg3, arg5).append(arg2);
    }

    public void encode(boolean[] arg6, int arg7, int arg8, int arg9) {
        if(arg6 == null) {
            this.encodeNullPointer(arg7, BindingsHelper.isArrayNullable(arg8));
            return;
        }

        if(arg9 != -1 && arg9 != arg6.length) {
            throw new SerializationException("Trying to encode a fixed array of incorrect length.");
        }

        arg9 = 8;
        byte[] v8 = new byte[(arg6.length + 7) / arg9];
        int v1;
        for(v1 = 0; v1 < v8.length; ++v1) {
            int v2;
            for(v2 = 0; v2 < arg9; ++v2) {
                int v3 = v1 * 8 + v2;
                if(v3 < arg6.length && (arg6[v3])) {
                    v8[v1] = ((byte)(v8[v1] | (((byte)(1 << v2)))));
                }
            }
        }

        this.encodeByteArray(v8, arg6.length, arg7);
    }

    private void encodeByteArray(byte[] arg2, int arg3, int arg4) {
        this.encoderForArrayByTotalSize(arg2.length, arg3, arg4).append(arg2);
    }

    public void encodeInvalidHandle(int arg2, boolean arg3) {
        if(!arg3) {
            throw new SerializationException("Trying to encode an invalid handle for a non-nullable type.");
        }

        this.mEncoderState.byteBuffer.putInt(this.mBaseOffset + arg2, -1);
    }

    public void encodeNullPointer(int arg4, boolean arg5) {
        if(!arg5) {
            throw new SerializationException("Trying to encode a null pointer for a non-nullable type.");
        }

        this.mEncoderState.byteBuffer.putLong(this.mBaseOffset + arg4, 0);
    }

    public Encoder encodePointerArray(int arg2, int arg3, int arg4) {
        return this.encoderForArray(8, arg2, arg3, arg4);
    }

    private void encodePointerToNextUnclaimedData(int arg7) {
        this.encode((((long)this.mEncoderState.dataEnd)) - (((long)(this.mBaseOffset + arg7))), arg7);
    }

    public Encoder encodeUnionArray(int arg2, int arg3, int arg4) {
        return this.encoderForArray(16, arg2, arg3, arg4);
    }

    private Encoder encoderForArray(int arg2, int arg3, int arg4, int arg5) {
        if(arg5 != -1 && arg5 != arg3) {
            throw new SerializationException("Trying to encode a fixed array of incorrect length.");
        }

        return this.encoderForArrayByTotalSize(arg2 * arg3, arg3, arg4);
    }

    private Encoder encoderForArrayByTotalSize(int arg1, int arg2, int arg3) {
        this.encodePointerToNextUnclaimedData(arg3);
        return this.getEncoderAtDataOffset(new DataHeader(arg1 + 8, arg2));
    }

    public Encoder encoderForMap(int arg1) {
        this.encodePointerToNextUnclaimedData(arg1);
        return this.getEncoderAtDataOffset(BindingsHelper.MAP_STRUCT_HEADER);
    }

    public Encoder encoderForUnionPointer(int arg3) {
        this.encodePointerToNextUnclaimedData(arg3);
        Encoder v3 = new Encoder(this.mEncoderState);
        v3.mEncoderState.claimMemory(16);
        return v3;
    }

    public Encoder getEncoderAtDataOffset(DataHeader arg3) {
        Encoder v0 = new Encoder(this.mEncoderState);
        v0.encode(arg3);
        return v0;
    }

    public Message getMessage() {
        this.mEncoderState.byteBuffer.position(0);
        this.mEncoderState.byteBuffer.limit(this.mEncoderState.dataEnd);
        return new Message(this.mEncoderState.byteBuffer, this.mEncoderState.handles);
    }
}

