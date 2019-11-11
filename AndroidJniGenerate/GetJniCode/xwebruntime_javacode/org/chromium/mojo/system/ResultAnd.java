package org.chromium.mojo.system;

public class ResultAnd {
    private final int mMojoResult;
    private final Object mValue;

    public ResultAnd(int arg1, Object arg2) {
        super();
        this.mMojoResult = arg1;
        this.mValue = arg2;
    }

    public int getMojoResult() {
        return this.mMojoResult;
    }

    public Object getValue() {
        return this.mValue;
    }
}

