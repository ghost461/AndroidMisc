package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter {
    public TypeAdapter() {
        super();
    }

    public final Object fromJson(Reader arg2) throws IOException {
        return this.read(new JsonReader(arg2));
    }

    public final Object fromJson(String arg2) throws IOException {
        return this.fromJson(new StringReader(arg2));
    }

    public final Object fromJsonTree(JsonElement arg2) {
        try {
            return this.read(new JsonTreeReader(arg2));
        }
        catch(IOException v2) {
            throw new JsonIOException(((Throwable)v2));
        }
    }

    public final TypeAdapter nullSafe() {
        return new TypeAdapter() {
            public Object read(JsonReader arg3) throws IOException {
                if(arg3.peek() == JsonToken.NULL) {
                    arg3.nextNull();
                    return null;
                }

                return TypeAdapter.this.read(arg3);
            }

            public void write(JsonWriter arg2, Object arg3) throws IOException {
                if(arg3 == null) {
                    arg2.nullValue();
                }
                else {
                    TypeAdapter.this.write(arg2, arg3);
                }
            }
        };
    }

    public abstract Object read(JsonReader arg1) throws IOException;

    public final String toJson(Object arg2) {
        StringWriter v0 = new StringWriter();
        try {
            this.toJson(((Writer)v0), arg2);
        }
        catch(IOException v2) {
            throw new AssertionError(v2);
        }

        return v0.toString();
    }

    public final void toJson(Writer arg2, Object arg3) throws IOException {
        this.write(new JsonWriter(arg2), arg3);
    }

    public final JsonElement toJsonTree(Object arg2) {
        try {
            JsonTreeWriter v0 = new JsonTreeWriter();
            this.write(((JsonWriter)v0), arg2);
            return v0.get();
        }
        catch(IOException v2) {
            throw new JsonIOException(((Throwable)v2));
        }
    }

    public abstract void write(JsonWriter arg1, Object arg2) throws IOException;
}

