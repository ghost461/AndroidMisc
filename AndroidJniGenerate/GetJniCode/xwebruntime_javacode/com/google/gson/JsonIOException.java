package com.google.gson;

public final class JsonIOException extends JsonParseException {
    private static final long serialVersionUID = 1;

    public JsonIOException(String arg1) {
        super(arg1);
    }

    public JsonIOException(Throwable arg1) {
        super(arg1);
    }

    public JsonIOException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

