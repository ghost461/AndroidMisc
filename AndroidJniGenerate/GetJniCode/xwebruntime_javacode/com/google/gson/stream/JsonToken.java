package com.google.gson.stream;

public enum JsonToken {
    public static final enum JsonToken BEGIN_ARRAY;
    public static final enum JsonToken BEGIN_OBJECT;
    public static final enum JsonToken BOOLEAN;
    public static final enum JsonToken END_ARRAY;
    public static final enum JsonToken END_DOCUMENT;
    public static final enum JsonToken END_OBJECT;
    public static final enum JsonToken NAME;
    public static final enum JsonToken NULL;
    public static final enum JsonToken NUMBER;
    public static final enum JsonToken STRING;

    static {
        JsonToken.BEGIN_ARRAY = new JsonToken("BEGIN_ARRAY", 0);
        JsonToken.END_ARRAY = new JsonToken("END_ARRAY", 1);
        JsonToken.BEGIN_OBJECT = new JsonToken("BEGIN_OBJECT", 2);
        JsonToken.END_OBJECT = new JsonToken("END_OBJECT", 3);
        JsonToken.NAME = new JsonToken("NAME", 4);
        JsonToken.STRING = new JsonToken("STRING", 5);
        JsonToken.NUMBER = new JsonToken("NUMBER", 6);
        JsonToken.BOOLEAN = new JsonToken("BOOLEAN", 7);
        JsonToken.NULL = new JsonToken("NULL", 8);
        JsonToken.END_DOCUMENT = new JsonToken("END_DOCUMENT", 9);
        JsonToken.$VALUES = new JsonToken[]{JsonToken.BEGIN_ARRAY, JsonToken.END_ARRAY, JsonToken.BEGIN_OBJECT, JsonToken.END_OBJECT, JsonToken.NAME, JsonToken.STRING, JsonToken.NUMBER, JsonToken.BOOLEAN, JsonToken.NULL, JsonToken.END_DOCUMENT};
    }

    private JsonToken(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static JsonToken valueOf(String arg1) {
        return Enum.valueOf(JsonToken.class, arg1);
    }

    public static JsonToken[] values() {
        return JsonToken.$VALUES.clone();
    }
}

