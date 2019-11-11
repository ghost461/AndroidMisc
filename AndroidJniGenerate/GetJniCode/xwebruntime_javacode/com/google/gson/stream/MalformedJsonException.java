package com.google.gson.stream;

import java.io.IOException;

public final class MalformedJsonException extends IOException {
    private static final long serialVersionUID = 1;

    public MalformedJsonException(String arg1) {
        super(arg1);
    }

    public MalformedJsonException(String arg1, Throwable arg2) {
        super(arg1);
        this.initCause(arg2);
    }

    public MalformedJsonException(Throwable arg1) {
        super();
        this.initCause(arg1);
    }
}

