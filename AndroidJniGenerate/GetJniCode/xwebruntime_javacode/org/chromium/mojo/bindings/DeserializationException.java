package org.chromium.mojo.bindings;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String arg1) {
        super(arg1);
    }

    public DeserializationException(Exception arg1) {
        super(((Throwable)arg1));
    }
}

