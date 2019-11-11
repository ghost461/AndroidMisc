package com.google.gson;

import java.lang.reflect.Type;

public interface JsonSerializer {
    JsonElement serialize(Object arg1, Type arg2, JsonSerializationContext arg3);
}

