package org.chromium.blink.mojom.document_metadata;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class Values extends Union {
    public final class Tag {
        public static final int BoolValues = 0;
        public static final int EntityValues = 3;
        public static final int LongValues = 1;
        public static final int StringValues = 2;

        public Tag() {
            super();
        }
    }

    private boolean[] mBoolValues;
    private Entity[] mEntityValues;
    private long[] mLongValues;
    private String[] mStringValues;

    static {
    }

    public Values() {
        super();
    }

    public static final Values decode(Decoder arg5, int arg6) {
        DataHeader v6;
        DataHeader v0 = arg5.readDataHeaderForUnion(arg6);
        if(v0.size == 0) {
            return null;
        }

        Values v1 = new Values();
        int v2 = -1;
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mBoolValues = arg5.readBooleans(arg6 + 8, 0, v2);
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mLongValues = arg5.readLongs(arg6 + 8, 0, v2);
                v1.mTag = 1;
                break;
            }
            case 2: {
                arg5 = arg5.readPointer(arg6 + 8, false);
                v6 = arg5.readDataHeaderForPointerArray(v2);
                v1.mStringValues = new String[v6.elementsOrVersion];
                for(v0_1 = 0; v0_1 < v6.elementsOrVersion; ++v0_1) {
                    v1.mStringValues[v0_1] = arg5.readString(v0_1 * 8 + 8, false);
                }

                v1.mTag = 2;
                break;
            }
            case 3: {
                arg5 = arg5.readPointer(arg6 + 8, false);
                v6 = arg5.readDataHeaderForPointerArray(v2);
                v1.mEntityValues = new Entity[v6.elementsOrVersion];
                int v0_1;
                for(v0_1 = 0; v0_1 < v6.elementsOrVersion; ++v0_1) {
                    v1.mEntityValues[v0_1] = Entity.decode(arg5.readPointer(v0_1 * 8 + 8, false));
                }

                v1.mTag = 3;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static Values deserialize(Message arg1) {
        return Values.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg4, int arg5) {
        arg4.encode(16, arg5);
        arg4.encode(this.mTag, arg5 + 4);
        int v1 = -1;
        switch(this.mTag) {
            case 0: {
                arg4.encode(this.mBoolValues, arg5 + 8, 0, v1);
                break;
            }
            case 1: {
                arg4.encode(this.mLongValues, arg5 + 8, 0, v1);
                break;
            }
            case 2: {
                if(this.mStringValues == null) {
                    arg4.encodeNullPointer(arg5 + 8, false);
                    return;
                }

                arg4 = arg4.encodePointerArray(this.mStringValues.length, arg5 + 8, v1);
                for(arg5 = 0; arg5 < this.mStringValues.length; ++arg5) {
                    arg4.encode(this.mStringValues[arg5], arg5 * 8 + 8, false);
                }
            }
            case 3: {
                if(this.mEntityValues == null) {
                    arg4.encodeNullPointer(arg5 + 8, false);
                    return;
                }

                arg4 = arg4.encodePointerArray(this.mEntityValues.length, arg5 + 8, v1);
                for(arg5 = 0; arg5 < this.mEntityValues.length; ++arg5) {
                    arg4.encode(this.mEntityValues[arg5], arg5 * 8 + 8, false);
                }
            }
            default: {
                break;
            }
        }
    }

    public boolean[] getBoolValues() {
        return this.mBoolValues;
    }

    public Entity[] getEntityValues() {
        return this.mEntityValues;
    }

    public long[] getLongValues() {
        return this.mLongValues;
    }

    public String[] getStringValues() {
        return this.mStringValues;
    }

    public void setBoolValues(boolean[] arg2) {
        this.mTag = 0;
        this.mBoolValues = arg2;
    }

    public void setEntityValues(Entity[] arg2) {
        this.mTag = 3;
        this.mEntityValues = arg2;
    }

    public void setLongValues(long[] arg2) {
        this.mTag = 1;
        this.mLongValues = arg2;
    }

    public void setStringValues(String[] arg2) {
        this.mTag = 2;
        this.mStringValues = arg2;
    }
}

