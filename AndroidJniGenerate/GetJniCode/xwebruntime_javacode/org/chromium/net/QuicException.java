package org.chromium.net;

public abstract class QuicException extends NetworkException {
    protected QuicException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }

    public abstract int getQuicDetailedErrorCode();
}

