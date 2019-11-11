package com.google.gson;

import com.google.gson.reflect.TypeToken;

public interface TypeAdapterFactory {
    TypeAdapter create(Gson arg1, TypeToken arg2);
}

