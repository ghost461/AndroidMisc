package org.chromium.net;

import java.io.IOException;

public abstract class CronetException extends IOException {
    protected CronetException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

