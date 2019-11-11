package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;

public class MessageHeader {
    private static final int FLAGS_OFFSET = 16;
    private static final int INTERFACE_ID_OFFSET = 8;
    public static final int MESSAGE_EXPECTS_RESPONSE_FLAG = 1;
    public static final int MESSAGE_IS_RESPONSE_FLAG = 2;
    private static final int MESSAGE_WITH_REQUEST_ID_SIZE = 0x20;
    private static final DataHeader MESSAGE_WITH_REQUEST_ID_STRUCT_INFO = null;
    private static final int MESSAGE_WITH_REQUEST_ID_VERSION = 1;
    public static final int NO_FLAG = 0;
    private static final int REQUEST_ID_OFFSET = 24;
    private static final int SIMPLE_MESSAGE_SIZE = 24;
    private static final DataHeader SIMPLE_MESSAGE_STRUCT_INFO = null;
    private static final int SIMPLE_MESSAGE_VERSION = 0;
    private static final int TYPE_OFFSET = 12;
    private final DataHeader mDataHeader;
    private final int mFlags;
    private long mRequestId;
    private final int mType;

    static {
        MessageHeader.SIMPLE_MESSAGE_STRUCT_INFO = new DataHeader(24, 0);
        MessageHeader.MESSAGE_WITH_REQUEST_ID_STRUCT_INFO = new DataHeader(0x20, 1);
    }

    public MessageHeader(int arg3) {
        super();
        this.mDataHeader = MessageHeader.SIMPLE_MESSAGE_STRUCT_INFO;
        this.mType = arg3;
        this.mFlags = 0;
        this.mRequestId = 0;
    }

    public MessageHeader(int arg2, int arg3, long arg4) {
        super();
        this.mDataHeader = MessageHeader.MESSAGE_WITH_REQUEST_ID_STRUCT_INFO;
        this.mType = arg2;
        this.mFlags = arg3;
        this.mRequestId = arg4;
    }

    MessageHeader(Message arg3) {
        super();
        Decoder v0 = new Decoder(arg3);
        this.mDataHeader = v0.readDataHeader();
        MessageHeader.validateDataHeader(this.mDataHeader);
        if(v0.readInt(8) != 0) {
            throw new DeserializationException("Non-zero interface ID, expecting zero since associated interfaces are not yet supported.");
        }

        this.mType = v0.readInt(12);
        this.mFlags = v0.readInt(16);
        if(!MessageHeader.mustHaveRequestId(this.mFlags)) {
            this.mRequestId = 0;
        }
        else if(this.mDataHeader.size < 0x20) {
            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("Incorrect message size, expecting at least 32 for a message with a request identifier, but got: ");
            v0_1.append(this.mDataHeader.size);
            throw new DeserializationException(v0_1.toString());
        }
        else {
            this.mRequestId = v0.readLong(24);
        }
    }

    public void encode(Encoder arg4) {
        arg4.encode(this.mDataHeader);
        arg4.encode(0, 8);
        arg4.encode(this.getType(), 12);
        arg4.encode(this.getFlags(), 16);
        if(this.hasRequestId()) {
            arg4.encode(this.getRequestId(), 24);
        }
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if((((MessageHeader)arg8)) == this) {
            return 1;
        }

        if(arg8 == null) {
            return 0;
        }

        if(this.getClass() != arg8.getClass()) {
            return 0;
        }

        if(!BindingsHelper.equals(this.mDataHeader, ((MessageHeader)arg8).mDataHeader) || this.mFlags != ((MessageHeader)arg8).mFlags || this.mRequestId != ((MessageHeader)arg8).mRequestId || this.mType != ((MessageHeader)arg8).mType) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public long getRequestId() {
        return this.mRequestId;
    }

    public int getSize() {
        return this.mDataHeader.size;
    }

    public int getType() {
        return this.mType;
    }

    public boolean hasFlag(int arg2) {
        boolean v2 = (this.mFlags & arg2) == arg2 ? true : false;
        return v2;
    }

    public boolean hasRequestId() {
        return MessageHeader.mustHaveRequestId(this.mFlags);
    }

    public int hashCode() {
        int v0 = this.mDataHeader == null ? 0 : this.mDataHeader.hashCode();
        return (((v0 + 0x1F) * 0x1F + this.mFlags) * 0x1F + (((int)(this.mRequestId ^ this.mRequestId >>> 0x20)))) * 0x1F + this.mType;
    }

    private static boolean mustHaveRequestId(int arg0) {
        boolean v0 = (arg0 & 3) != 0 ? true : false;
        return v0;
    }

    void setRequestId(ByteBuffer arg2, long arg3) {
        arg2.putLong(24, arg3);
        this.mRequestId = arg3;
    }

    private static void validateDataHeader(DataHeader arg3) {
        StringBuilder v1;
        if(arg3.elementsOrVersion < 0) {
            v1 = new StringBuilder();
            v1.append("Incorrect number of fields, expecting at least 0, but got: ");
            v1.append(arg3.elementsOrVersion);
            throw new DeserializationException(v1.toString());
        }

        int v1_1 = 24;
        if(arg3.size < v1_1) {
            v1 = new StringBuilder();
            v1.append("Incorrect message size, expecting at least 24, but got: ");
            v1.append(arg3.size);
            throw new DeserializationException(v1.toString());
        }

        if(arg3.elementsOrVersion == 0 && arg3.size != v1_1) {
            v1 = new StringBuilder();
            v1.append("Incorrect message size for a message with 0 fields, expecting 24, but got: ");
            v1.append(arg3.size);
            throw new DeserializationException(v1.toString());
        }

        if(arg3.elementsOrVersion == 1 && arg3.size != 0x20) {
            v1 = new StringBuilder();
            v1.append("Incorrect message size for a message with 1 fields, expecting 32, but got: ");
            v1.append(arg3.size);
            throw new DeserializationException(v1.toString());
        }
    }

    public boolean validateHeader(int arg2) {
        boolean v2 = (this.getFlags() & 3) == arg2 ? true : false;
        return v2;
    }

    public boolean validateHeader(int arg2, int arg3) {
        boolean v2 = this.getType() != arg2 || !this.validateHeader(arg3) ? false : true;
        return v2;
    }
}

