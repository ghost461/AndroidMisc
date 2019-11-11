package org.chromium.net;

public abstract class CallbackException extends CronetException {
    protected CallbackException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

