package org.chromium.mojo.bindings;

public class SerializationException extends RuntimeException {
    public SerializationException(String arg1) {
        super(arg1);
    }

    public SerializationException(Exception arg1) {
        super(((Throwable)arg1));
    }
}

