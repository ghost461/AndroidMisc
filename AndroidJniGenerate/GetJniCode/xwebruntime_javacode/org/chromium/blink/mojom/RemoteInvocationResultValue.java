package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;
import org.chromium.mojo_base.mojom.String16;

public final class RemoteInvocationResultValue extends Union {
    public final class Tag {
        public static final int BooleanValue = 1;
        public static final int NumberValue = 0;
        public static final int SingletonValue = 3;
        public static final int StringValue = 2;

        public Tag() {
            super();
        }
    }

    private boolean mBooleanValue;
    private double mNumberValue;
    private int mSingletonValue;
    private String16 mStringValue;

    static {
    }

    public RemoteInvocationResultValue() {
        super();
    }

    public static final RemoteInvocationResultValue decode(Decoder arg3, int arg4) {
        DataHeader v0 = arg3.readDataHeaderForUnion(arg4);
        if(v0.size == 0) {
            return null;
        }

        RemoteInvocationResultValue v1 = new RemoteInvocationResultValue();
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mNumberValue = arg3.readDouble(arg4 + 8);
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mBooleanValue = arg3.readBoolean(arg4 + 8, 0);
                v1.mTag = 1;
                break;
            }
            case 2: {
                v1.mStringValue = String16.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 2;
                break;
            }
            case 3: {
                v1.mSingletonValue = arg3.readInt(arg4 + 8);
                SingletonJavaScriptValue.validate(v1.mSingletonValue);
                v1.mTag = 3;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static RemoteInvocationResultValue deserialize(Message arg1) {
        return RemoteInvocationResultValue.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg3, int arg4) {
        arg3.encode(16, arg4);
        arg3.encode(this.mTag, arg4 + 4);
        switch(this.mTag) {
            case 0: {
                arg3.encode(this.mNumberValue, arg4 + 8);
                break;
            }
            case 1: {
                arg3.encode(this.mBooleanValue, arg4 + 8, 0);
                break;
            }
            case 2: {
                arg3.encode(this.mStringValue, arg4 + 8, false);
                break;
            }
            case 3: {
                arg3.encode(this.mSingletonValue, arg4 + 8);
                break;
            }
            default: {
                break;
            }
        }
    }

    public boolean getBooleanValue() {
        return this.mBooleanValue;
    }

    public double getNumberValue() {
        return this.mNumberValue;
    }

    public int getSingletonValue() {
        return this.mSingletonValue;
    }

    public String16 getStringValue() {
        return this.mStringValue;
    }

    public void setBooleanValue(boolean arg2) {
        this.mTag = 1;
        this.mBooleanValue = arg2;
    }

    public void setNumberValue(double arg2) {
        this.mTag = 0;
        this.mNumberValue = arg2;
    }

    public void setSingletonValue(int arg2) {
        this.mTag = 3;
        this.mSingletonValue = arg2;
    }

    public void setStringValue(String16 arg2) {
        this.mTag = 2;
        this.mStringValue = arg2;
    }
}

