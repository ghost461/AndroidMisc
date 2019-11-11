package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor arg1) {
        super();
        this.constructorConstructor = arg1;
    }

    public TypeAdapter create(Gson arg3, TypeToken arg4) {
        Annotation v0 = arg4.getRawType().getAnnotation(JsonAdapter.class);
        if(v0 == null) {
            return null;
        }

        return this.getTypeAdapter(this.constructorConstructor, arg3, arg4, ((JsonAdapter)v0));
    }

    TypeAdapter getTypeAdapter(ConstructorConstructor arg9, Gson arg10, TypeToken arg11, JsonAdapter arg12) {
        Object v3;
        TypeAdapter v9_1;
        Object v9 = arg9.get(TypeToken.get(arg12.value())).construct();
        if((v9 instanceof TypeAdapter)) {
        }
        else if((v9 instanceof TypeAdapterFactory)) {
            v9_1 = ((TypeAdapterFactory)v9).create(arg10, arg11);
        }
        else {
            boolean v0 = v9 instanceof JsonSerializer;
            if(!v0) {
                if((v9 instanceof JsonDeserializer)) {
                }
                else {
                    StringBuilder v12 = new StringBuilder();
                    v12.append("Invalid attempt to bind an instance of ");
                    v12.append(v9.getClass().getName());
                    v12.append(" as a @JsonAdapter for ");
                    v12.append(arg11.toString());
                    v12.append(". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                    throw new IllegalArgumentException(v12.toString());
                }
            }

            Object v1 = null;
            if(v0) {
                v3 = v9;
            }
            else {
                JsonSerializer v3_1 = ((JsonSerializer)v1);
            }

            if((v9 instanceof JsonDeserializer)) {
                v1 = v9;
            }

            TreeTypeAdapter v9_2 = new TreeTypeAdapter(((JsonSerializer)v3), v1, arg10, arg11, null);
        }

        if(v9_1 != null && (arg12.nullSafe())) {
            v9_1 = v9_1.nullSafe();
        }

        return v9_1;
    }
}

