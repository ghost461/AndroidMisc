package org.chromium.mojo.system;

public abstract class Flags {
    private int mFlags;
    private boolean mImmutable;

    protected Flags(int arg2) {
        super();
        this.mImmutable = false;
        this.mFlags = arg2;
    }

    public boolean equals(Object arg5) {
        if(this == (((Flags)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(this.mFlags != ((Flags)arg5).mFlags) {
            return 0;
        }

        return 1;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public int hashCode() {
        return this.mFlags;
    }

    protected Flags immutable() {
        this.mImmutable = true;
        return this;
    }

    protected Flags setFlag(int arg2, boolean arg3) {
        if(this.mImmutable) {
            throw new UnsupportedOperationException("Flags is immutable.");
        }

        if(arg3) {
            this.mFlags |= arg2;
        }
        else {
            this.mFlags &= arg2 ^ -1;
        }

        return this;
    }
}

