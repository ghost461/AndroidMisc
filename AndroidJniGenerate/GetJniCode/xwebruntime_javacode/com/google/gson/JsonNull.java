package com.google.gson;

public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE;

    static {
        JsonNull.INSTANCE = new JsonNull();
    }

    @Deprecated public JsonNull() {
        super();
    }

    public JsonElement deepCopy() {
        return this.deepCopy();
    }

    public JsonNull deepCopy() {
        return JsonNull.INSTANCE;
    }

    public boolean equals(Object arg1) {
        boolean v1 = this == (((JsonNull)arg1)) || ((arg1 instanceof JsonNull)) ? true : false;
        return v1;
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }
}

