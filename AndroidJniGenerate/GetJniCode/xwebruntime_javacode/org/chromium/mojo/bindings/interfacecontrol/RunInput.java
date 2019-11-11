package org.chromium.mojo.bindings.interfacecontrol;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class RunInput extends Union {
    public final class Tag {
        public static final int FlushForTesting = 1;
        public static final int QueryVersion;

        public Tag() {
            super();
        }
    }

    private FlushForTesting mFlushForTesting;
    private QueryVersion mQueryVersion;

    static {
    }

    public RunInput() {
        super();
    }

    public static final RunInput decode(Decoder arg3, int arg4) {
        DataHeader v0 = arg3.readDataHeaderForUnion(arg4);
        if(v0.size == 0) {
            return null;
        }

        RunInput v1 = new RunInput();
        switch(v0.elementsOrVersion) {
            case 0: {
                v1.mQueryVersion = QueryVersion.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 0;
                break;
            }
            case 1: {
                v1.mFlushForTesting = FlushForTesting.decode(arg3.readPointer(arg4 + 8, false));
                v1.mTag = 1;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }

    public static RunInput deserialize(Message arg1) {
        return RunInput.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg3, int arg4) {
        arg3.encode(16, arg4);
        arg3.encode(this.mTag, arg4 + 4);
        switch(this.mTag) {
            case 0: {
                arg3.encode(this.mQueryVersion, arg4 + 8, false);
                break;
            }
            case 1: {
                arg3.encode(this.mFlushForTesting, arg4 + 8, false);
                break;
            }
            default: {
                break;
            }
        }
    }

    public FlushForTesting getFlushForTesting() {
        return this.mFlushForTesting;
    }

    public QueryVersion getQueryVersion() {
        return this.mQueryVersion;
    }

    public void setFlushForTesting(FlushForTesting arg2) {
        this.mTag = 1;
        this.mFlushForTesting = arg2;
    }

    public void setQueryVersion(QueryVersion arg2) {
        this.mTag = 0;
        this.mQueryVersion = arg2;
    }
}

