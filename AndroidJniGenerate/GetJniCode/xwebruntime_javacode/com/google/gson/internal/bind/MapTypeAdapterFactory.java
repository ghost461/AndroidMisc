package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    final class Adapter extends TypeAdapter {
        private final ObjectConstructor constructor;
        private final TypeAdapter keyTypeAdapter;
        private final TypeAdapter valueTypeAdapter;

        public Adapter(MapTypeAdapterFactory arg1, Gson arg2, Type arg3, TypeAdapter arg4, Type arg5, TypeAdapter arg6, ObjectConstructor arg7) {
            MapTypeAdapterFactory.this = arg1;
            super();
            this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(arg2, arg4, arg3);
            this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(arg2, arg6, arg5);
            this.constructor = arg7;
        }

        private String keyToString(JsonElement arg2) {
            if(arg2.isJsonPrimitive()) {
                JsonPrimitive v2 = arg2.getAsJsonPrimitive();
                if(v2.isNumber()) {
                    return String.valueOf(v2.getAsNumber());
                }

                if(v2.isBoolean()) {
                    return Boolean.toString(v2.getAsBoolean());
                }

                if(v2.isString()) {
                    return v2.getAsString();
                }

                throw new AssertionError();
            }

            if(arg2.isJsonNull()) {
                return "null";
            }

            throw new AssertionError();
        }

        public Object read(JsonReader arg1) throws IOException {
            return this.read(arg1);
        }

        public Map read(JsonReader arg4) throws IOException {
            StringBuilder v1_1;
            Object v0_1;
            JsonToken v0 = arg4.peek();
            if(v0 == JsonToken.NULL) {
                arg4.nextNull();
                return null;
            }

            Object v1 = this.constructor.construct();
            if(v0 == JsonToken.BEGIN_ARRAY) {
                arg4.beginArray();
                while(true) {
                    if(arg4.hasNext()) {
                        arg4.beginArray();
                        v0_1 = this.keyTypeAdapter.read(arg4);
                        if(((Map)v1).put(v0_1, this.valueTypeAdapter.read(arg4)) != null) {
                            v1_1 = new StringBuilder();
                            v1_1.append("duplicate key: ");
                            v1_1.append(v0_1);
                            throw new JsonSyntaxException(v1_1.toString());
                        }
                        else {
                            arg4.endArray();
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    goto label_54;
                }

                arg4.endArray();
            }
            else {
                arg4.beginObject();
                do {
                    if(arg4.hasNext()) {
                        JsonReaderInternalAccess.INSTANCE.promoteNameToValue(arg4);
                        v0_1 = this.keyTypeAdapter.read(arg4);
                        if(((Map)v1).put(v0_1, this.valueTypeAdapter.read(arg4)) == null) {
                            continue;
                        }

                        break;
                    }
                    else {
                        goto label_53;
                    }
                }
                while(true);

                v1_1 = new StringBuilder();
                v1_1.append("duplicate key: ");
                v1_1.append(v0_1);
                throw new JsonSyntaxException(v1_1.toString());
            label_53:
                arg4.endObject();
            }

        label_54:
            return ((Map)v1);
        }

        public void write(JsonWriter arg1, Object arg2) throws IOException {
            this.write(arg1, ((Map)arg2));
        }

        public void write(JsonWriter arg8, Map arg9) throws IOException {
            int v9_1;
            Iterator v9;
            if(arg9 == null) {
                arg8.nullValue();
                return;
            }

            if(!MapTypeAdapterFactory.this.complexMapKeySerialization) {
                arg8.beginObject();
                v9 = arg9.entrySet().iterator();
                while(v9.hasNext()) {
                    Object v0 = v9.next();
                    arg8.name(String.valueOf(((Map$Entry)v0).getKey()));
                    this.valueTypeAdapter.write(arg8, ((Map$Entry)v0).getValue());
                }

                arg8.endObject();
                return;
            }

            ArrayList v0_1 = new ArrayList(arg9.size());
            ArrayList v1 = new ArrayList(arg9.size());
            v9 = arg9.entrySet().iterator();
            int v2 = 0;
            int v3;
            for(v3 = 0; v9.hasNext(); v3 |= v4_1) {
                Object v4 = v9.next();
                JsonElement v5 = this.keyTypeAdapter.toJsonTree(((Map$Entry)v4).getKey());
                ((List)v0_1).add(v5);
                ((List)v1).add(((Map$Entry)v4).getValue());
                int v4_1 = (v5.isJsonArray()) || (v5.isJsonObject()) ? 1 : 0;
            }

            if(v3 != 0) {
                arg8.beginArray();
                v9_1 = ((List)v0_1).size();
                goto label_53;
            }
            else {
                arg8.beginObject();
                v9_1 = ((List)v0_1).size();
                while(v2 < v9_1) {
                    arg8.name(this.keyToString(((List)v0_1).get(v2)));
                    this.valueTypeAdapter.write(arg8, ((List)v1).get(v2));
                    ++v2;
                }

                arg8.endObject();
                return;
            label_53:
                while(v2 < v9_1) {
                    arg8.beginArray();
                    Streams.write(((List)v0_1).get(v2), arg8);
                    this.valueTypeAdapter.write(arg8, ((List)v1).get(v2));
                    arg8.endArray();
                    ++v2;
                }

                arg8.endArray();
            }
        }
    }

    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;

    public MapTypeAdapterFactory(ConstructorConstructor arg1, boolean arg2) {
        super();
        this.constructorConstructor = arg1;
        this.complexMapKeySerialization = arg2;
    }

    public TypeAdapter create(Gson arg12, TypeToken arg13) {
        Type v0 = arg13.getType();
        if(!Map.class.isAssignableFrom(arg13.getRawType())) {
            return null;
        }

        Type[] v0_1 = $Gson$Types.getMapKeyAndValueTypes(v0, $Gson$Types.getRawType(v0));
        return new Adapter(this, arg12, v0_1[0], this.getKeyAdapter(arg12, v0_1[0]), v0_1[1], arg12.getAdapter(TypeToken.get(v0_1[1])), this.constructorConstructor.get(arg13));
    }

    private TypeAdapter getKeyAdapter(Gson arg2, Type arg3) {
        TypeAdapter v2 = arg3 == Boolean.TYPE || arg3 == Boolean.class ? TypeAdapters.BOOLEAN_AS_STRING : arg2.getAdapter(TypeToken.get(arg3));
        return v2;
    }
}

