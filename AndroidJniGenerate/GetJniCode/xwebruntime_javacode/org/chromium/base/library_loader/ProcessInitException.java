package org.chromium.base.library_loader;

public class ProcessInitException extends Exception {
    private int mErrorCode;

    public ProcessInitException(int arg2) {
        super();
        this.mErrorCode = 0;
        this.mErrorCode = arg2;
    }

    public ProcessInitException(int arg2, Throwable arg3) {
        super(null, arg3);
        this.mErrorCode = 0;
        this.mErrorCode = arg2;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }
}

