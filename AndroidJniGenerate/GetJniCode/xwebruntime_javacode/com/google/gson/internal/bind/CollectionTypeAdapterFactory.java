package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    final class Adapter extends TypeAdapter {
        private final ObjectConstructor constructor;
        private final TypeAdapter elementTypeAdapter;

        public Adapter(Gson arg2, Type arg3, TypeAdapter arg4, ObjectConstructor arg5) {
            super();
            this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(arg2, arg4, arg3);
            this.constructor = arg5;
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public Collection read(JsonReader arg3) throws IOException {
            if(arg3.peek() == JsonToken.NULL) {
                arg3.nextNull();
                return null;
            }

            Object v0 = this.constructor.construct();
            arg3.beginArray();
            while(arg3.hasNext()) {
                ((Collection)v0).add(this.elementTypeAdapter.read(arg3));
            }

            arg3.endArray();
            return ((Collection)v0);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Collection)arg2));
        }

        public void write(JsonWriter arg3, Collection arg4) throws IOException {
            if(arg4 == null) {
                arg3.nullValue();
                return;
            }

            arg3.beginArray();
            Iterator v4 = arg4.iterator();
            while(v4.hasNext()) {
                this.elementTypeAdapter.write(arg3, v4.next());
            }

            arg3.endArray();
        }
    }

    private final ConstructorConstructor constructorConstructor;

    public CollectionTypeAdapterFactory(ConstructorConstructor arg1) {
        super();
        this.constructorConstructor = arg1;
    }

    public TypeAdapter create(Gson arg4, TypeToken arg5) {
        Type v0 = arg5.getType();
        Class v1 = arg5.getRawType();
        if(!Collection.class.isAssignableFrom(v1)) {
            return null;
        }

        v0 = $Gson$Types.getCollectionElementType(v0, v1);
        return new Adapter(arg4, v0, arg4.getAdapter(TypeToken.get(v0)), this.constructorConstructor.get(arg5));
    }
}

