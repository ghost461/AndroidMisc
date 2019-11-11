package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class Gson {
    class FutureTypeAdapter extends TypeAdapter {
        private TypeAdapter delegate;

        FutureTypeAdapter() {
            super();
        }

        public Object read(JsonReader arg2) throws IOException {
            if(this.delegate == null) {
                throw new IllegalStateException();
            }

            return this.delegate.read(arg2);
        }

        public void setDelegate(TypeAdapter arg2) {
            if(this.delegate != null) {
                throw new AssertionError();
            }

            this.delegate = arg2;
        }

        public void write(JsonWriter arg2, Object arg3) throws IOException {
            if(this.delegate == null) {
                throw new IllegalStateException();
            }

            this.delegate.write(arg2, arg3);
        }
    }

    static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    static final boolean DEFAULT_ESCAPE_HTML = true;
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    static final boolean DEFAULT_LENIENT = false;
    static final boolean DEFAULT_PRETTY_PRINT = false;
    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}\'\n";
    private static final TypeToken NULL_KEY_SURROGATE;
    final List builderFactories;
    final List builderHierarchyFactories;
    private final ThreadLocal calls;
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    final String datePattern;
    final int dateStyle;
    final Excluder excluder;
    final List factories;
    final FieldNamingStrategy fieldNamingStrategy;
    final boolean generateNonExecutableJson;
    final boolean htmlSafe;
    final Map instanceCreators;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    final boolean lenient;
    final LongSerializationPolicy longSerializationPolicy;
    final boolean prettyPrinting;
    final boolean serializeNulls;
    final boolean serializeSpecialFloatingPointValues;
    final int timeStyle;
    private final Map typeTokenCache;

    static {
        Gson.NULL_KEY_SURROGATE = TypeToken.get(Object.class);
    }

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    Gson(Excluder arg11, FieldNamingStrategy arg12, Map arg13, boolean arg14, boolean arg15, boolean arg16, boolean arg17, boolean arg18, boolean arg19, boolean arg20, LongSerializationPolicy arg21, String arg22, int arg23, int arg24, List arg25, List arg26, List arg27) {
        super();
        this.calls = new ThreadLocal();
        this.typeTokenCache = new ConcurrentHashMap();
        this.excluder = arg11;
        this.fieldNamingStrategy = arg12;
        this.instanceCreators = arg13;
        this.constructorConstructor = new ConstructorConstructor(arg13);
        this.serializeNulls = arg14;
        this.complexMapKeySerialization = arg15;
        this.generateNonExecutableJson = arg16;
        this.htmlSafe = arg17;
        this.prettyPrinting = arg18;
        this.lenient = arg19;
        this.serializeSpecialFloatingPointValues = arg20;
        this.longSerializationPolicy = arg21;
        this.datePattern = arg22;
        this.dateStyle = arg23;
        this.timeStyle = arg24;
        this.builderFactories = arg25;
        this.builderHierarchyFactories = arg26;
        ArrayList v6 = new ArrayList();
        ((List)v6).add(TypeAdapters.JSON_ELEMENT_FACTORY);
        ((List)v6).add(ObjectTypeAdapter.FACTORY);
        ((List)v6).add(arg11);
        ((List)v6).addAll(arg27);
        ((List)v6).add(TypeAdapters.STRING_FACTORY);
        ((List)v6).add(TypeAdapters.INTEGER_FACTORY);
        ((List)v6).add(TypeAdapters.BOOLEAN_FACTORY);
        ((List)v6).add(TypeAdapters.BYTE_FACTORY);
        ((List)v6).add(TypeAdapters.SHORT_FACTORY);
        TypeAdapter v3 = Gson.longAdapter(arg21);
        ((List)v6).add(TypeAdapters.newFactory(Long.TYPE, Long.class, v3));
        ((List)v6).add(TypeAdapters.newFactory(Double.TYPE, Double.class, this.doubleAdapter(arg20)));
        ((List)v6).add(TypeAdapters.newFactory(Float.TYPE, Float.class, this.floatAdapter(arg20)));
        ((List)v6).add(TypeAdapters.NUMBER_FACTORY);
        ((List)v6).add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        ((List)v6).add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        ((List)v6).add(TypeAdapters.newFactory(AtomicLong.class, Gson.atomicLongAdapter(v3)));
        ((List)v6).add(TypeAdapters.newFactory(AtomicLongArray.class, Gson.atomicLongArrayAdapter(v3)));
        ((List)v6).add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        ((List)v6).add(TypeAdapters.CHARACTER_FACTORY);
        ((List)v6).add(TypeAdapters.STRING_BUILDER_FACTORY);
        ((List)v6).add(TypeAdapters.STRING_BUFFER_FACTORY);
        ((List)v6).add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        ((List)v6).add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        ((List)v6).add(TypeAdapters.URL_FACTORY);
        ((List)v6).add(TypeAdapters.URI_FACTORY);
        ((List)v6).add(TypeAdapters.UUID_FACTORY);
        ((List)v6).add(TypeAdapters.CURRENCY_FACTORY);
        ((List)v6).add(TypeAdapters.LOCALE_FACTORY);
        ((List)v6).add(TypeAdapters.INET_ADDRESS_FACTORY);
        ((List)v6).add(TypeAdapters.BIT_SET_FACTORY);
        ((List)v6).add(DateTypeAdapter.FACTORY);
        ((List)v6).add(TypeAdapters.CALENDAR_FACTORY);
        ((List)v6).add(TimeTypeAdapter.FACTORY);
        ((List)v6).add(SqlDateTypeAdapter.FACTORY);
        ((List)v6).add(TypeAdapters.TIMESTAMP_FACTORY);
        ((List)v6).add(ArrayTypeAdapter.FACTORY);
        ((List)v6).add(TypeAdapters.CLASS_FACTORY);
        ((List)v6).add(new CollectionTypeAdapterFactory(this.constructorConstructor));
        ((List)v6).add(new MapTypeAdapterFactory(this.constructorConstructor, arg15));
        this.jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor);
        ((List)v6).add(this.jsonAdapterFactory);
        ((List)v6).add(TypeAdapters.ENUM_FACTORY);
        ((List)v6).add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, arg12, arg11, this.jsonAdapterFactory));
        this.factories = Collections.unmodifiableList(((List)v6));
    }

    private static void assertFullConsumption(Object arg0, JsonReader arg1) {
        if(arg0 != null) {
            try {
                if(arg1.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            }
            catch(IOException v0) {
                throw new JsonIOException(((Throwable)v0));
            }
            catch(MalformedJsonException v0_1) {
                throw new JsonSyntaxException(((Throwable)v0_1));
            }
        }
    }

    private static TypeAdapter atomicLongAdapter(TypeAdapter arg1) {
        return new TypeAdapter(arg1) {
            public Object read(JsonReader arg1) throws IOException {
                return this.read(arg1);
            }

            public AtomicLong read(JsonReader arg4) throws IOException {
                return new AtomicLong(this.val$longAdapter.read(arg4).longValue());
            }

            public void write(JsonWriter arg1, Object arg2) throws IOException {
                this.write(arg1, ((AtomicLong)arg2));
            }

            public void write(JsonWriter arg4, AtomicLong arg5) throws IOException {
                this.val$longAdapter.write(arg4, Long.valueOf(arg5.get()));
            }
        }.nullSafe();
    }

    private static TypeAdapter atomicLongArrayAdapter(TypeAdapter arg1) {
        return new TypeAdapter(arg1) {
            public Object read(JsonReader arg1) throws IOException {
                return this.read(arg1);
            }

            public AtomicLongArray read(JsonReader arg6) throws IOException {
                ArrayList v0 = new ArrayList();
                arg6.beginArray();
                while(arg6.hasNext()) {
                    ((List)v0).add(Long.valueOf(this.val$longAdapter.read(arg6).longValue()));
                }

                arg6.endArray();
                int v6 = ((List)v0).size();
                AtomicLongArray v1 = new AtomicLongArray(v6);
                int v2;
                for(v2 = 0; v2 < v6; ++v2) {
                    v1.set(v2, ((List)v0).get(v2).longValue());
                }

                return v1;
            }

            public void write(JsonWriter arg1, Object arg2) throws IOException {
                this.write(arg1, ((AtomicLongArray)arg2));
            }

            public void write(JsonWriter arg6, AtomicLongArray arg7) throws IOException {
                arg6.beginArray();
                int v0 = arg7.length();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.val$longAdapter.write(arg6, Long.valueOf(arg7.get(v1)));
                }

                arg6.endArray();
            }
        }.nullSafe();
    }

    static void checkValidFloatingPoint(double arg2) {
        if(!Double.isNaN(arg2)) {
            if(Double.isInfinite(arg2)) {
            }
            else {
                return;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append(arg2);
        v1.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        throw new IllegalArgumentException(v1.toString());
    }

    private TypeAdapter doubleAdapter(boolean arg1) {
        if(arg1) {
            return TypeAdapters.DOUBLE;
        }

        return new TypeAdapter() {
            public Double read(JsonReader arg3) throws IOException {
                if(arg3.peek() == JsonToken.NULL) {
                    arg3.nextNull();
                    return null;
                }

                return Double.valueOf(arg3.nextDouble());
            }

            public Object read(JsonReader arg1) throws IOException {
                return this.read(arg1);
            }

            public void write(JsonWriter arg3, Number arg4) throws IOException {
                if(arg4 == null) {
                    arg3.nullValue();
                    return;
                }

                Gson.checkValidFloatingPoint(arg4.doubleValue());
                arg3.value(arg4);
            }

            public void write(JsonWriter arg1, Object arg2) throws IOException {
                this.write(arg1, ((Number)arg2));
            }
        };
    }

    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    private TypeAdapter floatAdapter(boolean arg1) {
        if(arg1) {
            return TypeAdapters.FLOAT;
        }

        return new TypeAdapter() {
            public Float read(JsonReader arg3) throws IOException {
                if(arg3.peek() == JsonToken.NULL) {
                    arg3.nextNull();
                    return null;
                }

                return Float.valueOf(((float)arg3.nextDouble()));
            }

            public Object read(JsonReader arg1) throws IOException {
                return this.read(arg1);
            }

            public void write(JsonWriter arg3, Number arg4) throws IOException {
                if(arg4 == null) {
                    arg3.nullValue();
                    return;
                }

                Gson.checkValidFloatingPoint(((double)arg4.floatValue()));
                arg3.value(arg4);
            }

            public void write(JsonWriter arg1, Object arg2) throws IOException {
                this.write(arg1, ((Number)arg2));
            }
        };
    }

    public Object fromJson(JsonElement arg1, Class arg2) throws JsonSyntaxException {
        return Primitives.wrap(arg2).cast(this.fromJson(arg1, ((Type)arg2)));
    }

    public Object fromJson(JsonElement arg2, Type arg3) throws JsonSyntaxException {
        if(arg2 == null) {
            return null;
        }

        return this.fromJson(new JsonTreeReader(arg2), arg3);
    }

    public Object fromJson(JsonReader arg5, Type arg6) throws JsonIOException, JsonSyntaxException {
        Object v6_5;
        boolean v0 = arg5.isLenient();
        arg5.setLenient(true);
        try {
            arg5.peek();
            v6_5 = this.getAdapter(TypeToken.get(arg6)).read(arg5);
        }
        catch(Throwable v6) {
        }
        catch(AssertionError v6_1) {
            try {
                StringBuilder v2 = new StringBuilder();
                v2.append("AssertionError (GSON 2.8.5): ");
                v2.append(v6_1.getMessage());
                throw new AssertionError(v2.toString(), ((Throwable)v6_1));
            }
            catch(Throwable v6) {
            label_39:
                arg5.setLenient(v0);
                throw v6;
            }
        }
        catch(IOException v6_2) {
        }
        catch(IllegalStateException v6_3) {
        }
        catch(EOFException v6_4) {
            if(0 != 0) {
                arg5.setLenient(v0);
                return null;
            }

            try {
                throw new JsonSyntaxException(((Throwable)v6_4));
                throw new JsonSyntaxException(((Throwable)v6_3));
                throw new JsonSyntaxException(((Throwable)v6_2));
            }
            catch(Throwable v6) {
                goto label_39;
            }
        }

        arg5.setLenient(v0);
        return v6_5;
    }

    public Object fromJson(Reader arg2, Class arg3) throws JsonSyntaxException, JsonIOException {
        JsonReader v2 = this.newJsonReader(arg2);
        Object v0 = this.fromJson(v2, ((Type)arg3));
        Gson.assertFullConsumption(v0, v2);
        return Primitives.wrap(arg3).cast(v0);
    }

    public Object fromJson(Reader arg1, Type arg2) throws JsonIOException, JsonSyntaxException {
        JsonReader v1 = this.newJsonReader(arg1);
        Object v2 = this.fromJson(v1, arg2);
        Gson.assertFullConsumption(v2, v1);
        return v2;
    }

    public Object fromJson(String arg1, Class arg2) throws JsonSyntaxException {
        return Primitives.wrap(arg2).cast(this.fromJson(arg1, ((Type)arg2)));
    }

    public Object fromJson(String arg2, Type arg3) throws JsonSyntaxException {
        if(arg2 == null) {
            return null;
        }

        return this.fromJson(new StringReader(arg2), arg3);
    }

    public TypeAdapter getAdapter(TypeToken arg6) {
        TypeAdapter v4;
        HashMap v0_2;
        Map v0 = this.typeTokenCache;
        TypeToken v1 = arg6 == null ? Gson.NULL_KEY_SURROGATE : arg6;
        Object v0_1 = v0.get(v1);
        if(v0_1 != null) {
            return ((TypeAdapter)v0_1);
        }

        v0_1 = this.calls.get();
        int v1_1 = 0;
        if(v0_1 == null) {
            v0_2 = new HashMap();
            this.calls.set(v0_2);
            v1_1 = 1;
        }

        Object v2 = ((Map)v0_2).get(arg6);
        if(v2 != null) {
            return ((TypeAdapter)v2);
        }

        try {
            FutureTypeAdapter v2_2 = new FutureTypeAdapter();
            ((Map)v0_2).put(arg6, v2_2);
            Iterator v3 = this.factories.iterator();
            do {
                if(!v3.hasNext()) {
                    goto label_38;
                }

                v4 = v3.next().create(this, arg6);
            }
            while(v4 == null);

            v2_2.setDelegate(v4);
            this.typeTokenCache.put(arg6, v4);
        }
        catch(Throwable v2_1) {
            goto label_48;
        }

        ((Map)v0_2).remove(arg6);
        if(v1_1 != 0) {
            this.calls.remove();
        }

        return v4;
        try {
        label_38:
            StringBuilder v3_1 = new StringBuilder();
            v3_1.append("GSON (2.8.5) cannot handle ");
            v3_1.append(arg6);
            throw new IllegalArgumentException(v3_1.toString());
        }
        catch(Throwable v2_1) {
        label_48:
            ((Map)v0_2).remove(arg6);
            if(v1_1 != 0) {
                this.calls.remove();
            }

            throw v2_1;
        }
    }

    public TypeAdapter getAdapter(Class arg1) {
        return this.getAdapter(TypeToken.get(arg1));
    }

    public TypeAdapter getDelegateAdapter(TypeAdapterFactory arg4, TypeToken arg5) {
        TypeAdapter v2_1;
        if(!this.factories.contains(arg4)) {
            JsonAdapterAnnotationTypeAdapterFactory v4 = this.jsonAdapterFactory;
        }

        int v0 = 0;
        Iterator v1 = this.factories.iterator();
        do {
        label_7:
            if(!v1.hasNext()) {
                goto label_17;
            }

            Object v2 = v1.next();
            if(v0 == 0) {
                if((((TypeAdapterFactory)v2)) != (((TypeAdapterFactory)v4))) {
                    goto label_7;
                }

                v0 = 1;
                goto label_7;
            }

            v2_1 = ((TypeAdapterFactory)v2).create(this, arg5);
        }
        while(v2_1 == null);

        return v2_1;
    label_17:
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("GSON cannot serialize ");
        v0_1.append(arg5);
        throw new IllegalArgumentException(v0_1.toString());
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    private static TypeAdapter longAdapter(LongSerializationPolicy arg1) {
        if(arg1 == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }

        return new TypeAdapter() {
            public Number read(JsonReader arg3) throws IOException {
                if(arg3.peek() == JsonToken.NULL) {
                    arg3.nextNull();
                    return null;
                }

                return Long.valueOf(arg3.nextLong());
            }

            public Object read(JsonReader arg1) throws IOException {
                return this.read(arg1);
            }

            public void write(JsonWriter arg1, Number arg2) throws IOException {
                if(arg2 == null) {
                    arg1.nullValue();
                    return;
                }

                arg1.value(arg2.toString());
            }

            public void write(JsonWriter arg1, Object arg2) throws IOException {
                this.write(arg1, ((Number)arg2));
            }
        };
    }

    public GsonBuilder newBuilder() {
        return new GsonBuilder(this);
    }

    public JsonReader newJsonReader(Reader arg2) {
        JsonReader v0 = new JsonReader(arg2);
        v0.setLenient(this.lenient);
        return v0;
    }

    public JsonWriter newJsonWriter(Writer arg2) throws IOException {
        if(this.generateNonExecutableJson) {
            arg2.write(")]}\'\n");
        }

        JsonWriter v0 = new JsonWriter(arg2);
        if(this.prettyPrinting) {
            v0.setIndent("  ");
        }

        v0.setSerializeNulls(this.serializeNulls);
        return v0;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public String toJson(JsonElement arg2) {
        StringWriter v0 = new StringWriter();
        this.toJson(arg2, ((Appendable)v0));
        return v0.toString();
    }

    public void toJson(JsonElement arg1, Appendable arg2) throws JsonIOException {
        try {
            this.toJson(arg1, this.newJsonWriter(Streams.writerForAppendable(arg2)));
            return;
        }
        catch(IOException v1) {
            throw new JsonIOException(((Throwable)v1));
        }
    }

    public String toJson(Object arg2) {
        if(arg2 == null) {
            return this.toJson(JsonNull.INSTANCE);
        }

        return this.toJson(arg2, arg2.getClass());
    }

    public String toJson(Object arg2, Type arg3) {
        StringWriter v0 = new StringWriter();
        this.toJson(arg2, arg3, ((Appendable)v0));
        return v0.toString();
    }

    public void toJson(Object arg1, Type arg2, Appendable arg3) throws JsonIOException {
        try {
            this.toJson(arg1, arg2, this.newJsonWriter(Streams.writerForAppendable(arg3)));
            return;
        }
        catch(IOException v1) {
            throw new JsonIOException(((Throwable)v1));
        }
    }

    public void toJson(JsonElement arg7, JsonWriter arg8) throws JsonIOException {
        boolean v0 = arg8.isLenient();
        arg8.setLenient(true);
        boolean v1 = arg8.isHtmlSafe();
        arg8.setHtmlSafe(this.htmlSafe);
        boolean v2 = arg8.getSerializeNulls();
        arg8.setSerializeNulls(this.serializeNulls);
        try {
            Streams.write(arg7, arg8);
        }
        catch(Throwable v7) {
        }
        catch(AssertionError v7_1) {
            try {
                StringBuilder v4 = new StringBuilder();
                v4.append("AssertionError (GSON 2.8.5): ");
                v4.append(v7_1.getMessage());
                throw new AssertionError(v4.toString(), ((Throwable)v7_1));
            }
            catch(Throwable v7) {
            label_31:
                arg8.setLenient(v0);
                arg8.setHtmlSafe(v1);
                arg8.setSerializeNulls(v2);
                throw v7;
            }
        }
        catch(IOException v7_2) {
            try {
                throw new JsonIOException(((Throwable)v7_2));
            }
            catch(Throwable v7) {
                goto label_31;
            }
        }

        arg8.setLenient(v0);
        arg8.setHtmlSafe(v1);
        arg8.setSerializeNulls(v2);
    }

    public void toJson(Object arg2, Appendable arg3) throws JsonIOException {
        if(arg2 != null) {
            this.toJson(arg2, arg2.getClass(), arg3);
        }
        else {
            this.toJson(JsonNull.INSTANCE, arg3);
        }
    }

    public void toJson(Object arg6, Type arg7, JsonWriter arg8) throws JsonIOException {
        TypeAdapter v7 = this.getAdapter(TypeToken.get(arg7));
        boolean v0 = arg8.isLenient();
        arg8.setLenient(true);
        boolean v1 = arg8.isHtmlSafe();
        arg8.setHtmlSafe(this.htmlSafe);
        boolean v2 = arg8.getSerializeNulls();
        arg8.setSerializeNulls(this.serializeNulls);
        try {
            v7.write(arg8, arg6);
        }
        catch(Throwable v6) {
        }
        catch(AssertionError v6_1) {
            try {
                StringBuilder v3 = new StringBuilder();
                v3.append("AssertionError (GSON 2.8.5): ");
                v3.append(v6_1.getMessage());
                throw new AssertionError(v3.toString(), ((Throwable)v6_1));
            }
            catch(Throwable v6) {
            label_33:
                arg8.setLenient(v0);
                arg8.setHtmlSafe(v1);
                arg8.setSerializeNulls(v2);
                throw v6;
            }
        }
        catch(IOException v6_2) {
            try {
                throw new JsonIOException(((Throwable)v6_2));
            }
            catch(Throwable v6) {
                goto label_33;
            }
        }

        arg8.setLenient(v0);
        arg8.setHtmlSafe(v1);
        arg8.setSerializeNulls(v2);
    }

    public JsonElement toJsonTree(Object arg2) {
        if(arg2 == null) {
            return JsonNull.INSTANCE;
        }

        return this.toJsonTree(arg2, arg2.getClass());
    }

    public JsonElement toJsonTree(Object arg2, Type arg3) {
        JsonTreeWriter v0 = new JsonTreeWriter();
        this.toJson(arg2, arg3, ((JsonWriter)v0));
        return v0.get();
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("{serializeNulls:");
        v0.append(this.serializeNulls);
        v0.append(",factories:");
        v0.append(this.factories);
        v0.append(",instanceCreators:");
        v0.append(this.constructorConstructor);
        v0.append("}");
        return v0.toString();
    }
}

