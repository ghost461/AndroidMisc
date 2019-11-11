package com.google.gson;

import java.lang.reflect.Type;

public interface JsonDeserializationContext {
    Object deserialize(JsonElement arg1, Type arg2) throws JsonParseException;
}

