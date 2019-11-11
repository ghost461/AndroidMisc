package org.chromium.mojo.system;

public class MojoException extends RuntimeException {
    private final int mCode;

    public MojoException(int arg1) {
        super();
        this.mCode = arg1;
    }

    public MojoException(Throwable arg1) {
        super(arg1);
        this.mCode = 2;
    }

    public int getMojoResult() {
        return this.mCode;
    }

    public String toString() {
        return "MojoResult(" + this.mCode + "): " + MojoResult.describe(this.mCode);
    }
}

