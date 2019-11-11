package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper extends TypeAdapter {
    private final Gson context;
    private final TypeAdapter delegate;
    private final Type type;

    TypeAdapterRuntimeTypeWrapper(Gson arg1, TypeAdapter arg2, Type arg3) {
        super();
        this.context = arg1;
        this.delegate = arg2;
        this.type = arg3;
    }

    private Type getRuntimeTypeIfMoreSpecific(Type arg2, Object arg3) {
        Class v2;
        if(arg3 != null && (arg2 == Object.class || ((arg2 instanceof TypeVariable)) || ((arg2 instanceof Class)))) {
            v2 = arg3.getClass();
        }

        return ((Type)v2);
    }

    public Object read(JsonReader arg2) throws IOException {
        return this.delegate.read(arg2);
    }

    public void write(JsonWriter arg4, Object arg5) throws IOException {
        TypeAdapter v0 = this.delegate;
        Type v1 = this.getRuntimeTypeIfMoreSpecific(this.type, arg5);
        if(v1 != this.type) {
            v0 = this.context.getAdapter(TypeToken.get(v1));
            if(!(v0 instanceof Adapter)) {
            }
            else if(!(this.delegate instanceof Adapter)) {
                v0 = this.delegate;
            }
        }

        v0.write(arg4, arg5);
    }
}

