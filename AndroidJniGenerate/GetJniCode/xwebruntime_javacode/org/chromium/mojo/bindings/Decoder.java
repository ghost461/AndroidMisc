package org.chromium.mojo.bindings;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

public class Decoder {
    final class Validator {
        private static final int MAX_RECURSION_DEPTH = 100;
        private final long mMaxMemory;
        private int mMinNextClaimedHandle;
        private long mMinNextMemory;
        private final long mNumberOfHandles;
        private long mStackDepth;

        Validator(long arg1, int arg3) {
            super();
            this.mMaxMemory = arg1;
            this.mNumberOfHandles = ((long)arg3);
            this.mStackDepth = 0;
        }

        public void claimHandle(int arg6) {
            if(arg6 < this.mMinNextClaimedHandle) {
                throw new DeserializationException("Trying to access handle out of order.");
            }

            if((((long)arg6)) >= this.mNumberOfHandles) {
                throw new DeserializationException("Trying to access non present handle.");
            }

            this.mMinNextClaimedHandle = arg6 + 1;
        }

        public void claimMemory(long arg6, long arg8) {
            if(arg6 % 8 != 0) {
                StringBuilder v9 = new StringBuilder();
                v9.append("Incorrect starting alignment: ");
                v9.append(arg6);
                v9.append(".");
                throw new DeserializationException(v9.toString());
            }

            if(arg6 < this.mMinNextMemory) {
                throw new DeserializationException("Trying to access memory out of order.");
            }

            if(arg8 < arg6) {
                throw new DeserializationException("Incorrect memory range.");
            }

            if(arg8 > this.mMaxMemory) {
                throw new DeserializationException("Trying to access out of range memory.");
            }

            this.mMinNextMemory = BindingsHelper.align(arg8);
        }

        public void decreaseStackDepth() {
            --this.mStackDepth;
        }

        public void increaseStackDepth() {
            ++this.mStackDepth;
            if(this.mStackDepth >= 100) {
                throw new DeserializationException("Recursion depth limit exceeded.");
            }
        }
    }

    private final int mBaseOffset;
    private final Message mMessage;
    private final Validator mValidator;

    public Decoder(Message arg5) {
        this(arg5, new Validator(((long)arg5.getData().limit()), arg5.getHandles().size()), 0);
    }

    private Decoder(Message arg2, Validator arg3, int arg4) {
        super();
        this.mMessage = arg2;
        this.mMessage.getData().order(ByteOrder.LITTLE_ENDIAN);
        this.mBaseOffset = arg4;
        this.mValidator = arg3;
    }

    public Decoder decoderForSerializedUnion() {
        this.mValidator.claimMemory(0, 16);
        return this;
    }

    public void decreaseStackDepth() {
        this.mValidator.decreaseStackDepth();
    }

    private Decoder getDecoderAtPosition(int arg4) {
        return new Decoder(this.mMessage, this.mValidator, arg4);
    }

    public void increaseStackDepth() {
        this.mValidator.increaseStackDepth();
    }

    public DataHeader readAndValidateDataHeader(DataHeader[] arg7) {
        DataHeader v0 = this.readDataHeader();
        int v1 = arg7.length - 1;
        if(v0.elementsOrVersion <= arg7[v1].elementsOrVersion) {
            DataHeader v2 = null;
            while(v1 >= 0) {
                DataHeader v3 = arg7[v1];
                if(v0.elementsOrVersion >= v3.elementsOrVersion) {
                    v2 = v3;
                }
                else {
                    --v1;
                    continue;
                }

                break;
            }

            if(v2 != null && v2.size == v0.size) {
                return v0;
            }

            throw new DeserializationException("Header doesn\'t correspond to any known version.");
        }
        else {
            if(v0.size >= arg7[v1].size) {
                return v0;
            }

            throw new DeserializationException("Message newer than the last known version cannot be shorter than required by the last known version.");
        }

        return v0;
    }

    public AssociatedInterfaceRequestNotSupported readAssociatedInterfaceRequestNotSupported(int arg1, boolean arg2) {
        return null;
    }

    public AssociatedInterfaceRequestNotSupported[] readAssociatedInterfaceRequestNotSupporteds(int arg1, int arg2, int arg3) {
        return null;
    }

    public AssociatedInterfaceNotSupported readAssociatedServiceInterfaceNotSupported(int arg1, boolean arg2) {
        return null;
    }

    public AssociatedInterfaceNotSupported[] readAssociatedServiceInterfaceNotSupporteds(int arg1, int arg2, int arg3) {
        return null;
    }

    public boolean readBoolean(int arg2, int arg3) {
        boolean v0 = true;
        this.validateBufferSize(arg2, 1);
        if((this.readByte(arg2) & 1 << arg3) != 0) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean[] readBooleans(int arg8, int arg9, int arg10) {
        Decoder v8 = this.readPointer(arg8, BindingsHelper.isArrayNullable(arg9));
        if(v8 == null) {
            return null;
        }

        DataHeader v9 = v8.readDataHeaderForBooleanArray(arg10);
        int v0 = 8;
        byte[] v10 = new byte[(v9.elementsOrVersion + 7) / v0];
        v8.mMessage.getData().position(v8.mBaseOffset + v0);
        v8.mMessage.getData().get(v10);
        boolean[] v8_1 = new boolean[v9.elementsOrVersion];
        int v1;
        for(v1 = 0; v1 < v10.length; ++v1) {
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                int v3 = v1 * 8 + v2;
                if(v3 < v8_1.length) {
                    boolean v5 = true;
                    if((v10[v1] & 1 << v2) != 0) {
                    }
                    else {
                        v5 = false;
                    }

                    v8_1[v3] = v5;
                }
            }
        }

        return v8_1;
    }

    public byte readByte(int arg3) {
        this.validateBufferSize(arg3, 1);
        return this.mMessage.getData().get(this.mBaseOffset + arg3);
    }

    public byte[] readBytes(int arg3, int arg4, int arg5) {
        Decoder v3 = this.readPointer(arg3, BindingsHelper.isArrayNullable(arg4));
        if(v3 == null) {
            return null;
        }

        byte[] v4 = new byte[v3.readDataHeaderForArray(1, arg5).elementsOrVersion];
        v3.mMessage.getData().position(v3.mBaseOffset + 8);
        v3.mMessage.getData().get(v4);
        return v4;
    }

    public ConsumerHandle readConsumerHandle(int arg1, boolean arg2) {
        return this.readUntypedHandle(arg1, arg2).toDataPipeConsumerHandle();
    }

    public ConsumerHandle[] readConsumerHandles(int arg4, int arg5, int arg6) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        ConsumerHandle[] v6 = new ConsumerHandle[v4.readDataHeaderForArray(4, arg6).elementsOrVersion];
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readConsumerHandle(v0 * 4 + 8, BindingsHelper.isElementNullable(arg5));
        }

        return v6;
    }

    public DataHeader readDataHeader() {
        this.mValidator.claimMemory(((long)this.mBaseOffset), ((long)(this.mBaseOffset + 8)));
        DataHeader v0 = this.readDataHeaderAtOffset(0, false);
        this.mValidator.claimMemory(((long)(this.mBaseOffset + 8)), ((long)(this.mBaseOffset + v0.size)));
        return v0;
    }

    private DataHeader readDataHeaderAtOffset(int arg2, boolean arg3) {
        int v0 = this.readInt(arg2);
        arg2 = this.readInt(arg2 + 4);
        if(v0 < 0) {
            throw new DeserializationException("Negative size. Unsigned integers are not valid for java.");
        }

        if(arg2 < 0 && (!arg3 || arg2 != -1)) {
            throw new DeserializationException("Negative elements or version. Unsigned integers are not valid for java.");
        }

        return new DataHeader(v0, arg2);
    }

    private DataHeader readDataHeaderForArray(long arg8, int arg10) {
        DataHeader v0 = this.readDataHeader();
        if((((long)v0.size)) < arg8 * (((long)v0.elementsOrVersion)) + 8) {
            throw new DeserializationException("Array header is incorrect.");
        }

        if(arg10 != -1 && v0.elementsOrVersion != arg10) {
            StringBuilder v9 = new StringBuilder();
            v9.append("Incorrect array length. Expected: ");
            v9.append(arg10);
            v9.append(", but got: ");
            v9.append(v0.elementsOrVersion);
            v9.append(".");
            throw new DeserializationException(v9.toString());
        }

        return v0;
    }

    private DataHeader readDataHeaderForBooleanArray(int arg5) {
        DataHeader v0 = this.readDataHeader();
        if(v0.size < (v0.elementsOrVersion + 7) / 8 + 8) {
            throw new DeserializationException("Array header is incorrect.");
        }

        if(arg5 != -1 && v0.elementsOrVersion != arg5) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Incorrect array length. Expected: ");
            v2.append(arg5);
            v2.append(", but got: ");
            v2.append(v0.elementsOrVersion);
            v2.append(".");
            throw new DeserializationException(v2.toString());
        }

        return v0;
    }

    public void readDataHeaderForMap() {
        DataHeader v0 = this.readDataHeader();
        if(v0.size != BindingsHelper.MAP_STRUCT_HEADER.size) {
            throw new DeserializationException("Incorrect header for map. The size is incorrect.");
        }

        if(v0.elementsOrVersion != BindingsHelper.MAP_STRUCT_HEADER.elementsOrVersion) {
            throw new DeserializationException("Incorrect header for map. The version is incorrect.");
        }
    }

    public DataHeader readDataHeaderForPointerArray(int arg3) {
        return this.readDataHeaderForArray(8, arg3);
    }

    public DataHeader readDataHeaderForUnion(int arg4) {
        DataHeader v4 = this.readDataHeaderAtOffset(arg4, true);
        if(v4.size == 0) {
            if(v4.elementsOrVersion != 0) {
                StringBuilder v1 = new StringBuilder();
                v1.append("Unexpected version tag for a null union. Expecting 0, found: ");
                v1.append(v4.elementsOrVersion);
                throw new DeserializationException(v1.toString());
            }
        }
        else if(v4.size != 16) {
            throw new DeserializationException("Unexpected size of an union. The size must be 0 for a null union, or 16 for a non-null union.");
        }

        return v4;
    }

    public DataHeader readDataHeaderForUnionArray(int arg3) {
        return this.readDataHeaderForArray(16, arg3);
    }

    public double readDouble(int arg3) {
        this.validateBufferSize(arg3, 8);
        return this.mMessage.getData().getDouble(this.mBaseOffset + arg3);
    }

    public double[] readDoubles(int arg3, int arg4, int arg5) {
        Decoder v3 = this.readPointer(arg3, BindingsHelper.isArrayNullable(arg4));
        if(v3 == null) {
            return null;
        }

        double[] v4 = new double[v3.readDataHeaderForArray(8, arg5).elementsOrVersion];
        v3.mMessage.getData().position(v3.mBaseOffset + 8);
        v3.mMessage.getData().asDoubleBuffer().get(v4);
        return v4;
    }

    public float readFloat(int arg3) {
        this.validateBufferSize(arg3, 4);
        return this.mMessage.getData().getFloat(this.mBaseOffset + arg3);
    }

    public float[] readFloats(int arg3, int arg4, int arg5) {
        Decoder v3 = this.readPointer(arg3, BindingsHelper.isArrayNullable(arg4));
        if(v3 == null) {
            return null;
        }

        float[] v4 = new float[v3.readDataHeaderForArray(4, arg5).elementsOrVersion];
        v3.mMessage.getData().position(v3.mBaseOffset + 8);
        v3.mMessage.getData().asFloatBuffer().get(v4);
        return v4;
    }

    public Handle readHandle(int arg2, boolean arg3) {
        arg2 = this.readInt(arg2);
        if(arg2 == -1) {
            if(!arg3) {
                throw new DeserializationException("Trying to decode an invalid handle for a non-nullable type.");
            }

            return InvalidHandle.INSTANCE;
        }

        this.mValidator.claimHandle(arg2);
        return this.mMessage.getHandles().get(arg2);
    }

    public Handle[] readHandles(int arg4, int arg5, int arg6) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        Handle[] v6 = new Handle[v4.readDataHeaderForArray(4, arg6).elementsOrVersion];
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readHandle(v0 * 4 + 8, BindingsHelper.isElementNullable(arg5));
        }

        return v6;
    }

    public int readInt(int arg3) {
        this.validateBufferSize(arg3, 4);
        return this.mMessage.getData().getInt(this.mBaseOffset + arg3);
    }

    public InterfaceRequest readInterfaceRequest(int arg1, boolean arg2) {
        MessagePipeHandle v1 = this.readMessagePipeHandle(arg1, arg2);
        if(v1 == null) {
            return null;
        }

        return new InterfaceRequest(v1);
    }

    public InterfaceRequest[] readInterfaceRequests(int arg4, int arg5, int arg6) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        InterfaceRequest[] v6 = new InterfaceRequest[v4.readDataHeaderForArray(4, arg6).elementsOrVersion];
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readInterfaceRequest(v0 * 4 + 8, BindingsHelper.isElementNullable(arg5));
        }

        return v6;
    }

    public int[] readInts(int arg3, int arg4, int arg5) {
        Decoder v3 = this.readPointer(arg3, BindingsHelper.isArrayNullable(arg4));
        if(v3 == null) {
            return null;
        }

        int[] v4 = new int[v3.readDataHeaderForArray(4, arg5).elementsOrVersion];
        v3.mMessage.getData().position(v3.mBaseOffset + 8);
        v3.mMessage.getData().asIntBuffer().get(v4);
        return v4;
    }

    public long readLong(int arg3) {
        this.validateBufferSize(arg3, 8);
        return this.mMessage.getData().getLong(this.mBaseOffset + arg3);
    }

    public long[] readLongs(int arg3, int arg4, int arg5) {
        Decoder v3 = this.readPointer(arg3, BindingsHelper.isArrayNullable(arg4));
        if(v3 == null) {
            return null;
        }

        long[] v4 = new long[v3.readDataHeaderForArray(8, arg5).elementsOrVersion];
        v3.mMessage.getData().position(v3.mBaseOffset + 8);
        v3.mMessage.getData().asLongBuffer().get(v4);
        return v4;
    }

    public MessagePipeHandle readMessagePipeHandle(int arg1, boolean arg2) {
        return this.readUntypedHandle(arg1, arg2).toMessagePipeHandle();
    }

    public MessagePipeHandle[] readMessagePipeHandles(int arg4, int arg5, int arg6) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        MessagePipeHandle[] v6 = new MessagePipeHandle[v4.readDataHeaderForArray(4, arg6).elementsOrVersion];
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readMessagePipeHandle(v0 * 4 + 8, BindingsHelper.isElementNullable(arg5));
        }

        return v6;
    }

    public Decoder readPointer(int arg6, boolean arg7) {
        int v0 = this.mBaseOffset + arg6;
        long v1 = this.readLong(arg6);
        if(v1 == 0) {
            if(!arg7) {
                throw new DeserializationException("Trying to decode null pointer for a non-nullable type.");
            }

            return null;
        }

        return this.getDecoderAtPosition(((int)((((long)v0)) + v1)));
    }

    public ProducerHandle readProducerHandle(int arg1, boolean arg2) {
        return this.readUntypedHandle(arg1, arg2).toDataPipeProducerHandle();
    }

    public ProducerHandle[] readProducerHandles(int arg4, int arg5, int arg6) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        ProducerHandle[] v6 = new ProducerHandle[v4.readDataHeaderForArray(4, arg6).elementsOrVersion];
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readProducerHandle(v0 * 4 + 8, BindingsHelper.isElementNullable(arg5));
        }

        return v6;
    }

    public Proxy readServiceInterface(int arg2, boolean arg3, Manager arg4) {
        MessagePipeHandle v3 = this.readMessagePipeHandle(arg2, arg3);
        if(!v3.isValid()) {
            return null;
        }

        return arg4.attachProxy(v3, this.readInt(arg2 + 4));
    }

    public Interface[] readServiceInterfaces(int arg4, int arg5, int arg6, Manager arg7) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        Interface[] v6 = arg7.buildArray(v4.readDataHeaderForArray(8, arg6).elementsOrVersion);
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readServiceInterface(v0 * 8 + 8, BindingsHelper.isElementNullable(arg5), arg7);
        }

        return v6;
    }

    public SharedBufferHandle readSharedBufferHandle(int arg1, boolean arg2) {
        return this.readUntypedHandle(arg1, arg2).toSharedBufferHandle();
    }

    public SharedBufferHandle[] readSharedBufferHandles(int arg4, int arg5, int arg6) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        SharedBufferHandle[] v6 = new SharedBufferHandle[v4.readDataHeaderForArray(4, arg6).elementsOrVersion];
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readSharedBufferHandle(v0 * 4 + 8, BindingsHelper.isElementNullable(arg5));
        }

        return v6;
    }

    public short readShort(int arg3) {
        this.validateBufferSize(arg3, 2);
        return this.mMessage.getData().getShort(this.mBaseOffset + arg3);
    }

    public short[] readShorts(int arg3, int arg4, int arg5) {
        Decoder v3 = this.readPointer(arg3, BindingsHelper.isArrayNullable(arg4));
        if(v3 == null) {
            return null;
        }

        short[] v4 = new short[v3.readDataHeaderForArray(2, arg5).elementsOrVersion];
        v3.mMessage.getData().position(v3.mBaseOffset + 8);
        v3.mMessage.getData().asShortBuffer().get(v4);
        return v4;
    }

    public String readString(int arg2, boolean arg3) {
        byte[] v2 = this.readBytes(arg2, ((int)arg3), -1);
        if(v2 == null) {
            return null;
        }

        return new String(v2, Charset.forName("utf8"));
    }

    public UntypedHandle readUntypedHandle(int arg1, boolean arg2) {
        return this.readHandle(arg1, arg2).toUntypedHandle();
    }

    public UntypedHandle[] readUntypedHandles(int arg4, int arg5, int arg6) {
        Decoder v4 = this.readPointer(arg4, BindingsHelper.isArrayNullable(arg5));
        if(v4 == null) {
            return null;
        }

        UntypedHandle[] v6 = new UntypedHandle[v4.readDataHeaderForArray(4, arg6).elementsOrVersion];
        int v0;
        for(v0 = 0; v0 < v6.length; ++v0) {
            v6[v0] = v4.readUntypedHandle(v0 * 4 + 8, BindingsHelper.isElementNullable(arg5));
        }

        return v6;
    }

    private void validateBufferSize(int arg2, int arg3) {
        if(this.mMessage.getData().limit() < arg2 + arg3) {
            throw new DeserializationException("Buffer is smaller than expected.");
        }
    }
}

