package org.chromium.mojo.common.mojom;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class Value extends Union {
    public final class Tag {
        public static final int BinaryValue = 5;
        public static final int BoolValue = 1;
        public static final int DictionaryValue = 6;
        public static final int DoubleValue = 3;
        public static final int IntValue = 2;
        public static final int ListValue = 7;
        public static final int NullValue = 0;
        public static final int StringValue = 4;

        public Tag() {
            super();
        }
    }

    private byte[] mBinaryValue;
    private boolean mBoolValue;
    private DictionaryValue mDictionaryValue;
    private double mDoubleValue;
    private int mIntValue;
    private ListValue mListValue;
    private NullValue mNullValue;
    private String mStringValue;

    static {
    }

    public Value() {
        super();
    }

    public static final Value decode(Decoder arg4, int arg5) {
        DataHeader v0 = arg4.readDataHeaderForUnion(arg5);
        if(v0.size == 0) {
            return null;
        }

        Value v1 = new Value();
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mNullValue = NullValue.decode(arg4.readPointer(arg5 + 8, true));
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mBoolValue = arg4.readBoolean(arg5 + 8, 0);
                v1.mTag = 1;
                break;
            }
            case 2: {
                v1.mIntValue = arg4.readInt(arg5 + 8);
                v1.mTag = 2;
                break;
            }
            case 3: {
                v1.mDoubleValue = arg4.readDouble(arg5 + 8);
                v1.mTag = 3;
                break;
            }
            case 4: {
                v1.mStringValue = arg4.readString(arg5 + 8, false);
                v1.mTag = 4;
                break;
            }
            case 5: {
                v1.mBinaryValue = arg4.readBytes(arg5 + 8, 0, -1);
                v1.mTag = 5;
                break;
            }
            case 6: {
                v1.mDictionaryValue = DictionaryValue.decode(arg4.readPointer(arg5 + 8, false));
                v1.mTag = 6;
                break;
            }
            case 7: {
                v1.mListValue = ListValue.decode(arg4.readPointer(arg5 + 8, false));
                v1.mTag = 7;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static Value deserialize(Message arg1) {
        return Value.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg4, int arg5) {
        arg4.encode(16, arg5);
        arg4.encode(this.mTag, arg5 + 4);
        switch(this.mTag) {
            case 0: {
                arg4.encode(this.mNullValue, arg5 + 8, true);
                break;
            }
            case 1: {
                arg4.encode(this.mBoolValue, arg5 + 8, 0);
                break;
            }
            case 2: {
                arg4.encode(this.mIntValue, arg5 + 8);
                break;
            }
            case 3: {
                arg4.encode(this.mDoubleValue, arg5 + 8);
                break;
            }
            case 4: {
                arg4.encode(this.mStringValue, arg5 + 8, false);
                break;
            }
            case 5: {
                arg4.encode(this.mBinaryValue, arg5 + 8, 0, -1);
                break;
            }
            case 6: {
                arg4.encode(this.mDictionaryValue, arg5 + 8, false);
                break;
            }
            case 7: {
                arg4.encode(this.mListValue, arg5 + 8, false);
                break;
            }
            default: {
                break;
            }
        }
    }

    public byte[] getBinaryValue() {
        return this.mBinaryValue;
    }

    public boolean getBoolValue() {
        return this.mBoolValue;
    }

    public DictionaryValue getDictionaryValue() {
        return this.mDictionaryValue;
    }

    public double getDoubleValue() {
        return this.mDoubleValue;
    }

    public int getIntValue() {
        return this.mIntValue;
    }

    public ListValue getListValue() {
        return this.mListValue;
    }

    public NullValue getNullValue() {
        return this.mNullValue;
    }

    public String getStringValue() {
        return this.mStringValue;
    }

    public void setBinaryValue(byte[] arg2) {
        this.mTag = 5;
        this.mBinaryValue = arg2;
    }

    public void setBoolValue(boolean arg2) {
        this.mTag = 1;
        this.mBoolValue = arg2;
    }

    public void setDictionaryValue(DictionaryValue arg2) {
        this.mTag = 6;
        this.mDictionaryValue = arg2;
    }

    public void setDoubleValue(double arg2) {
        this.mTag = 3;
        this.mDoubleValue = arg2;
    }

    public void setIntValue(int arg2) {
        this.mTag = 2;
        this.mIntValue = arg2;
    }

    public void setListValue(ListValue arg2) {
        this.mTag = 7;
        this.mListValue = arg2;
    }

    public void setNullValue(NullValue arg2) {
        this.mTag = 0;
        this.mNullValue = arg2;
    }

    public void setStringValue(String arg2) {
        this.mTag = 4;
        this.mStringValue = arg2;
    }
}

