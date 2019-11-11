package com.google.gson;

public final class JsonSyntaxException extends JsonParseException {
    private static final long serialVersionUID = 1;

    public JsonSyntaxException(Throwable arg1) {
        super(arg1);
    }

    public JsonSyntaxException(String arg1) {
        super(arg1);
    }

    public JsonSyntaxException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

