package org.chromium.mojo.bindings;

import org.chromium.mojo.system.Core;

public abstract class Union {
    protected int mTag;

    public Union() {
        super();
    }

    protected abstract void encode(Encoder arg1, int arg2);

    public boolean isUnknown() {
        boolean v0 = this.mTag == -1 ? true : false;
        return v0;
    }

    public Message serialize(Core arg3) {
        Encoder v0 = new Encoder(arg3, 16);
        v0.claimMemory(16);
        this.encode(v0, 0);
        return v0.getMessage();
    }

    public int which() {
        return this.mTag;
    }
}

