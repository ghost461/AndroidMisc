package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ObjectTypeAdapter extends TypeAdapter {
    final class com.google.gson.internal.bind.ObjectTypeAdapter$1 implements TypeAdapterFactory {
        com.google.gson.internal.bind.ObjectTypeAdapter$1() {
            super();
        }

        public TypeAdapter create(Gson arg2, TypeToken arg3) {
            if(arg3.getRawType() == Object.class) {
                return new ObjectTypeAdapter(arg2);
            }

            return null;
        }
    }

    class com.google.gson.internal.bind.ObjectTypeAdapter$2 {
        static {
            com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
            try {
                com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
                                    return;
                                }
                                catch(NoSuchFieldError ) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static final TypeAdapterFactory FACTORY;
    private final Gson gson;

    static {
        ObjectTypeAdapter.FACTORY = new com.google.gson.internal.bind.ObjectTypeAdapter$1();
    }

    ObjectTypeAdapter(Gson arg1) {
        super();
        this.gson = arg1;
    }

    public Object read(JsonReader arg4) throws IOException {
        switch(com.google.gson.internal.bind.ObjectTypeAdapter$2.$SwitchMap$com$google$gson$stream$JsonToken[arg4.peek().ordinal()]) {
            case 1: {
                goto label_30;
            }
            case 2: {
                goto label_19;
            }
            case 3: {
                goto label_17;
            }
            case 4: {
                goto label_14;
            }
            case 5: {
                goto label_11;
            }
            case 6: {
                goto label_8;
            }
        }

        throw new IllegalStateException();
    label_17:
        return arg4.nextString();
    label_19:
        LinkedTreeMap v0 = new LinkedTreeMap();
        arg4.beginObject();
        while(arg4.hasNext()) {
            ((Map)v0).put(arg4.nextName(), this.read(arg4));
        }

        arg4.endObject();
        return v0;
    label_8:
        arg4.nextNull();
        return null;
    label_11:
        return Boolean.valueOf(arg4.nextBoolean());
    label_30:
        ArrayList v0_1 = new ArrayList();
        arg4.beginArray();
        while(arg4.hasNext()) {
            ((List)v0_1).add(this.read(arg4));
        }

        arg4.endArray();
        return v0_1;
    label_14:
        return Double.valueOf(arg4.nextDouble());
    }

    public void write(JsonWriter arg3, Object arg4) throws IOException {
        if(arg4 == null) {
            arg3.nullValue();
            return;
        }

        TypeAdapter v0 = this.gson.getAdapter(arg4.getClass());
        if((v0 instanceof ObjectTypeAdapter)) {
            arg3.beginObject();
            arg3.endObject();
            return;
        }

        v0.write(arg3, arg4);
    }
}

