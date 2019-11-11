package org.chromium.mojo.bindings.interfacecontrol;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class RunOutput extends Union {
    public final class Tag {
        public static final int QueryVersionResult;

        public Tag() {
            super();
        }
    }

    private QueryVersionResult mQueryVersionResult;

    static {
    }

    public RunOutput() {
        super();
    }

    public static final RunOutput decode(Decoder arg2, int arg3) {
        DataHeader v0 = arg2.readDataHeaderForUnion(arg3);
        if(v0.size == 0) {
            return null;
        }

        RunOutput v1 = new RunOutput();
        if(v0.elementsOrVersion != 0) {
        }
        else {
            v1.mQueryVersionResult = QueryVersionResult.decode(arg2.readPointer(arg3 + 8, false));
            v1.mTag = 0;
        }

        return v1;
    }

    public static RunOutput deserialize(Message arg1) {
        return RunOutput.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg3, int arg4) {
        arg3.encode(16, arg4);
        arg3.encode(this.mTag, arg4 + 4);
        if(this.mTag != 0) {
        }
        else {
            arg3.encode(this.mQueryVersionResult, arg4 + 8, false);
        }
    }

    public QueryVersionResult getQueryVersionResult() {
        return this.mQueryVersionResult;
    }

    public void setQueryVersionResult(QueryVersionResult arg2) {
        this.mTag = 0;
        this.mQueryVersionResult = arg2;
    }
}

