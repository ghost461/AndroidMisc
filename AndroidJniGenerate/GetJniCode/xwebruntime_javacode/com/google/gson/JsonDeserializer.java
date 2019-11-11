package com.google.gson;

import java.lang.reflect.Type;

public interface JsonDeserializer {
    Object deserialize(JsonElement arg1, Type arg2, JsonDeserializationContext arg3) throws JsonParseException;
}

