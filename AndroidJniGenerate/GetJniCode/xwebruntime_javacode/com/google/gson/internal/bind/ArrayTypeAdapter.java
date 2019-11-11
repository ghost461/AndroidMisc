package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class ArrayTypeAdapter extends TypeAdapter {
    final class com.google.gson.internal.bind.ArrayTypeAdapter$1 implements TypeAdapterFactory {
        com.google.gson.internal.bind.ArrayTypeAdapter$1() {
            super();
        }

        public TypeAdapter create(Gson arg3, TypeToken arg4) {
            Type v4 = arg4.getType();
            if(!(v4 instanceof GenericArrayType) && (!(v4 instanceof Class) || !v4.isArray())) {
                return null;
            }

            v4 = $Gson$Types.getArrayComponentType(v4);
            return new ArrayTypeAdapter(arg3, arg3.getAdapter(TypeToken.get(v4)), $Gson$Types.getRawType(v4));
        }
    }

    public static final TypeAdapterFactory FACTORY;
    private final Class componentType;
    private final TypeAdapter componentTypeAdapter;

    static {
        ArrayTypeAdapter.FACTORY = new com.google.gson.internal.bind.ArrayTypeAdapter$1();
    }

    public ArrayTypeAdapter(Gson arg2, TypeAdapter arg3, Class arg4) {
        super();
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(arg2, arg3, ((Type)arg4));
        this.componentType = arg4;
    }

    public Object read(JsonReader arg5) throws IOException {
        if(arg5.peek() == JsonToken.NULL) {
            arg5.nextNull();
            return null;
        }

        ArrayList v0 = new ArrayList();
        arg5.beginArray();
        while(arg5.hasNext()) {
            ((List)v0).add(this.componentTypeAdapter.read(arg5));
        }

        arg5.endArray();
        int v5 = ((List)v0).size();
        Object v1 = Array.newInstance(this.componentType, v5);
        int v2;
        for(v2 = 0; v2 < v5; ++v2) {
            Array.set(v1, v2, ((List)v0).get(v2));
        }

        return v1;
    }

    public void write(JsonWriter arg5, Object arg6) throws IOException {
        if(arg6 == null) {
            arg5.nullValue();
            return;
        }

        arg5.beginArray();
        int v0 = 0;
        int v1 = Array.getLength(arg6);
        while(v0 < v1) {
            this.componentTypeAdapter.write(arg5, Array.get(arg6, v0));
            ++v0;
        }

        arg5.endArray();
    }
}

