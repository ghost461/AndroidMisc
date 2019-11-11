package org.chromium.mojo.bindings.interfacecontrol;

import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Union;

public final class RunOrClosePipeInput extends Union {
    public final class Tag {
        public static final int RequireVersion;

        public Tag() {
            super();
        }
    }

    private RequireVersion mRequireVersion;

    static {
    }

    public RunOrClosePipeInput() {
        super();
    }

    public static final RunOrClosePipeInput decode(Decoder arg2, int arg3) {
        DataHeader v0 = arg2.readDataHeaderForUnion(arg3);
        if(v0.size == 0) {
            return null;
        }

        RunOrClosePipeInput v1 = new RunOrClosePipeInput();
        if(v0.elementsOrVersion != 0) {
        }
        else {
            v1.mRequireVersion = RequireVersion.decode(arg2.readPointer(arg3 + 8, false));
            v1.mTag = 0;
        }

        return v1;
    }

    public static RunOrClosePipeInput deserialize(Message arg1) {
        return RunOrClosePipeInput.decode(new Decoder(arg1).decoderForSerializedUnion(), 0);
    }

    protected final void encode(Encoder arg3, int arg4) {
        arg3.encode(16, arg4);
        arg3.encode(this.mTag, arg4 + 4);
        if(this.mTag != 0) {
        }
        else {
            arg3.encode(this.mRequireVersion, arg4 + 8, false);
        }
    }

    public RequireVersion getRequireVersion() {
        return this.mRequireVersion;
    }

    public void setRequireVersion(RequireVersion arg2) {
        this.mTag = 0;
        this.mRequireVersion = arg2;
    }
}

