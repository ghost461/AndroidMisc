package com.google.gson;

public class JsonParseException extends RuntimeException {
    static final long serialVersionUID = -4086729973971783390L;

    public JsonParseException(String arg1) {
        super(arg1);
    }

    public JsonParseException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }

    public JsonParseException(Throwable arg1) {
        super(arg1);
    }
}

