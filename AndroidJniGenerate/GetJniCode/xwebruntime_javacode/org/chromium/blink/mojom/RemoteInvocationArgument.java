package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;
import org.chromium.mojo_base.mojom.String16;

public final class RemoteInvocationArgument extends Union {
    public final class Tag {
        public static final int ArrayValue = 4;
        public static final int BooleanValue = 1;
        public static final int NumberValue = 0;
        public static final int SingletonValue = 3;
        public static final int StringValue = 2;

        public Tag() {
            super();
        }
    }

    private RemoteInvocationArgument[] mArrayValue;
    private boolean mBooleanValue;
    private double mNumberValue;
    private int mSingletonValue;
    private String16 mStringValue;

    static {
    }

    public RemoteInvocationArgument() {
        super();
    }

    public static final RemoteInvocationArgument decode(Decoder arg4, int arg5) {
        DataHeader v0 = arg4.readDataHeaderForUnion(arg5);
        if(v0.size == 0) {
            return null;
        }

        RemoteInvocationArgument v1 = new RemoteInvocationArgument();
        int v2 = 0;
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mNumberValue = arg4.readDouble(arg5 + 8);
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mBooleanValue = arg4.readBoolean(arg5 + 8, 0);
                v1.mTag = 1;
                break;
            }
            case 2: {
                v1.mStringValue = String16.decode(arg4.readPointer(arg5 + 8, false));
                v1.mTag = 2;
                break;
            }
            case 3: {
                v1.mSingletonValue = arg4.readInt(arg5 + 8);
                SingletonJavaScriptValue.validate(v1.mSingletonValue);
                v1.mTag = 3;
                break;
            }
            case 4: {
                arg4 = arg4.readPointer(arg5 + 8, false);
                DataHeader v5 = arg4.readDataHeaderForPointerArray(-1);
                v1.mArrayValue = new RemoteInvocationArgument[v5.elementsOrVersion];
                while(v2 < v5.elementsOrVersion) {
                    v1.mArrayValue[v2] = RemoteInvocationArgument.decode(arg4, v2 * 16 + 8);
                    ++v2;
                }

                v1.mTag = 4;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static RemoteInvocationArgument deserialize(Message arg1) {
        return RemoteInvocationArgument.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg4, int arg5) {
        arg4.encode(16, arg5);
        arg4.encode(this.mTag, arg5 + 4);
        switch(this.mTag) {
            case 0: {
                arg4.encode(this.mNumberValue, arg5 + 8);
                break;
            }
            case 1: {
                arg4.encode(this.mBooleanValue, arg5 + 8, 0);
                break;
            }
            case 2: {
                arg4.encode(this.mStringValue, arg5 + 8, false);
                break;
            }
            case 3: {
                arg4.encode(this.mSingletonValue, arg5 + 8);
                break;
            }
            case 4: {
                if(this.mArrayValue == null) {
                    arg4.encodeNullPointer(arg5 + 8, false);
                    return;
                }

                arg4 = arg4.encodeUnionArray(this.mArrayValue.length, arg5 + 8, -1);
                for(arg5 = 0; arg5 < this.mArrayValue.length; ++arg5) {
                    arg4.encode(this.mArrayValue[arg5], arg5 * 16 + 8, false);
                }
            }
            default: {
                break;
            }
        }
    }

    public RemoteInvocationArgument[] getArrayValue() {
        return this.mArrayValue;
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

    public void setArrayValue(RemoteInvocationArgument[] arg2) {
        this.mTag = 4;
        this.mArrayValue = arg2;
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

