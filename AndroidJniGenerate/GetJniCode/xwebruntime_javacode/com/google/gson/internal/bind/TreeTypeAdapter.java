package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public final class TreeTypeAdapter extends TypeAdapter {
    class com.google.gson.internal.bind.TreeTypeAdapter$1 {
    }

    final class GsonContextImpl implements JsonDeserializationContext, JsonSerializationContext {
        GsonContextImpl(TreeTypeAdapter arg1, com.google.gson.internal.bind.TreeTypeAdapter$1 arg2) {
            this(arg1);
        }

        private GsonContextImpl(TreeTypeAdapter arg1) {
            TreeTypeAdapter.this = arg1;
            super();
        }

        public Object deserialize(JsonElement arg2, Type arg3) throws JsonParseException {
            return TreeTypeAdapter.this.gson.fromJson(arg2, arg3);
        }

        public JsonElement serialize(Object arg2) {
            return TreeTypeAdapter.this.gson.toJsonTree(arg2);
        }

        public JsonElement serialize(Object arg2, Type arg3) {
            return TreeTypeAdapter.this.gson.toJsonTree(arg2, arg3);
        }
    }

    final class SingleTypeFactory implements TypeAdapterFactory {
        private final JsonDeserializer deserializer;
        private final TypeToken exactType;
        private final Class hierarchyType;
        private final boolean matchRawType;
        private final JsonSerializer serializer;

        SingleTypeFactory(Object arg3, TypeToken arg4, boolean arg5, Class arg6) {
            JsonSerializer v0_1;
            super();
            JsonDeserializer v1 = null;
            if((arg3 instanceof JsonSerializer)) {
                Object v0 = arg3;
            }
            else {
                v0_1 = ((JsonSerializer)v1);
            }

            this.serializer = v0_1;
            if((arg3 instanceof JsonDeserializer)) {
                Object v1_1 = arg3;
            }

            this.deserializer = v1;
            boolean v3 = this.serializer != null || this.deserializer != null ? true : false;
            $Gson$Preconditions.checkArgument(v3);
            this.exactType = arg4;
            this.matchRawType = arg5;
            this.hierarchyType = arg6;
        }

        public TypeAdapter create(Gson arg8, TypeToken arg9) {
            TypeAdapter v0_2;
            boolean v0;
            if(this.exactType != null) {
                if(!this.exactType.equals(arg9) && (!this.matchRawType || this.exactType.getType() != arg9.getRawType())) {
                    v0 = false;
                    goto label_19;
                }

                v0 = true;
            }
            else {
                v0 = this.hierarchyType.isAssignableFrom(arg9.getRawType());
            }

        label_19:
            if(v0) {
                TreeTypeAdapter v0_1 = new TreeTypeAdapter(this.serializer, this.deserializer, arg8, arg9, this);
            }
            else {
                v0_2 = null;
            }

            return v0_2;
        }
    }

    private final GsonContextImpl context;
    private TypeAdapter delegate;
    private final JsonDeserializer deserializer;
    final Gson gson;
    private final JsonSerializer serializer;
    private final TypeAdapterFactory skipPast;
    private final TypeToken typeToken;

    public TreeTypeAdapter(JsonSerializer arg3, JsonDeserializer arg4, Gson arg5, TypeToken arg6, TypeAdapterFactory arg7) {
        super();
        this.context = new GsonContextImpl(this, null);
        this.serializer = arg3;
        this.deserializer = arg4;
        this.gson = arg5;
        this.typeToken = arg6;
        this.skipPast = arg7;
    }

    private TypeAdapter delegate() {
        TypeAdapter v0 = this.delegate;
        if(v0 != null) {
        }
        else {
            v0 = this.gson.getDelegateAdapter(this.skipPast, this.typeToken);
            this.delegate = v0;
        }

        return v0;
    }

    public static TypeAdapterFactory newFactory(TypeToken arg3, Object arg4) {
        return new SingleTypeFactory(arg4, arg3, false, null);
    }

    public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken arg3, Object arg4) {
        boolean v0 = arg3.getType() == arg3.getRawType() ? true : false;
        return new SingleTypeFactory(arg4, arg3, v0, null);
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class arg3, Object arg4) {
        return new SingleTypeFactory(arg4, null, false, arg3);
    }

    public Object read(JsonReader arg4) throws IOException {
        if(this.deserializer == null) {
            return this.delegate().read(arg4);
        }

        JsonElement v4 = Streams.parse(arg4);
        if(v4.isJsonNull()) {
            return null;
        }

        return this.deserializer.deserialize(v4, this.typeToken.getType(), this.context);
    }

    public void write(JsonWriter arg4, Object arg5) throws IOException {
        if(this.serializer == null) {
            this.delegate().write(arg4, arg5);
            return;
        }

        if(arg5 == null) {
            arg4.nullValue();
            return;
        }

        Streams.write(this.serializer.serialize(arg5, this.typeToken.getType(), this.context), arg4);
    }
}

