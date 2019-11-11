package com.google.gson.internal;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

public abstract class JsonReaderInternalAccess {
    public static JsonReaderInternalAccess INSTANCE;

    public JsonReaderInternalAccess() {
        super();
    }

    public abstract void promoteNameToValue(JsonReader arg1) throws IOException;
}

