package com.google.gson;

import java.lang.reflect.Type;

public interface JsonSerializationContext {
    JsonElement serialize(Object arg1);

    JsonElement serialize(Object arg1, Type arg2);
}

