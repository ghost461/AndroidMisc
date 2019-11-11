package com.google.gson;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonBuilder {
    private boolean complexMapKeySerialization;
    private String datePattern;
    private int dateStyle;
    private boolean escapeHtmlChars;
    private Excluder excluder;
    private final List factories;
    private FieldNamingStrategy fieldNamingPolicy;
    private boolean generateNonExecutableJson;
    private final List hierarchyFactories;
    private final Map instanceCreators;
    private boolean lenient;
    private LongSerializationPolicy longSerializationPolicy;
    private boolean prettyPrinting;
    private boolean serializeNulls;
    private boolean serializeSpecialFloatingPointValues;
    private int timeStyle;

    GsonBuilder(Gson arg3) {
        super();
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.prettyPrinting = false;
        this.generateNonExecutableJson = false;
        this.lenient = false;
        this.excluder = arg3.excluder;
        this.fieldNamingPolicy = arg3.fieldNamingStrategy;
        this.instanceCreators.putAll(arg3.instanceCreators);
        this.serializeNulls = arg3.serializeNulls;
        this.complexMapKeySerialization = arg3.complexMapKeySerialization;
        this.generateNonExecutableJson = arg3.generateNonExecutableJson;
        this.escapeHtmlChars = arg3.htmlSafe;
        this.prettyPrinting = arg3.prettyPrinting;
        this.lenient = arg3.lenient;
        this.serializeSpecialFloatingPointValues = arg3.serializeSpecialFloatingPointValues;
        this.longSerializationPolicy = arg3.longSerializationPolicy;
        this.datePattern = arg3.datePattern;
        this.dateStyle = arg3.dateStyle;
        this.timeStyle = arg3.timeStyle;
        this.factories.addAll(arg3.builderFactories);
        this.hierarchyFactories.addAll(arg3.builderHierarchyFactories);
    }

    public GsonBuilder() {
        super();
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.prettyPrinting = false;
        this.generateNonExecutableJson = false;
        this.lenient = false;
    }

    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy arg4) {
        this.excluder = this.excluder.withExclusionStrategy(arg4, false, true);
        return this;
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy arg4) {
        this.excluder = this.excluder.withExclusionStrategy(arg4, true, false);
        return this;
    }

    private void addTypeAdaptersForDate(String arg4, int arg5, int arg6, List arg7) {
        DefaultDateTypeAdapter v4;
        DefaultDateTypeAdapter v0;
        DefaultDateTypeAdapter v6;
        if(arg4 == null || ("".equals(arg4.trim()))) {
            int v4_1 = 2;
            if(arg5 != v4_1 && arg6 != v4_1) {
                v4 = new DefaultDateTypeAdapter(Date.class, arg5, arg6);
                v0 = new DefaultDateTypeAdapter(Timestamp.class, arg5, arg6);
                DefaultDateTypeAdapter v1 = new DefaultDateTypeAdapter(java.sql.Date.class, arg5, arg6);
                v6 = v0;
                v0 = v1;
                goto label_30;
            }

            return;
        }
        else {
            DefaultDateTypeAdapter v5 = new DefaultDateTypeAdapter(Date.class, arg4);
            v6 = new DefaultDateTypeAdapter(Timestamp.class, arg4);
            v0 = new DefaultDateTypeAdapter(java.sql.Date.class, arg4);
            v4 = v5;
        }

    label_30:
        arg7.add(TypeAdapters.newFactory(Date.class, ((TypeAdapter)v4)));
        arg7.add(TypeAdapters.newFactory(Timestamp.class, ((TypeAdapter)v6)));
        arg7.add(TypeAdapters.newFactory(java.sql.Date.class, ((TypeAdapter)v0)));
    }

    public Gson create() {
        ArrayList v15 = new ArrayList(this.factories.size() + this.hierarchyFactories.size() + 3);
        ((List)v15).addAll(this.factories);
        Collections.reverse(((List)v15));
        ArrayList v1 = new ArrayList(this.hierarchyFactories);
        Collections.reverse(((List)v1));
        ((List)v15).addAll(((Collection)v1));
        this.addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, ((List)v15));
        return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.lenient, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, this.datePattern, this.dateStyle, this.timeStyle, this.factories, this.hierarchyFactories, v15);
    }

    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public GsonBuilder excludeFieldsWithModifiers(int[] arg2) {
        this.excluder = this.excluder.withModifiers(arg2);
        return this;
    }

    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public GsonBuilder registerTypeAdapter(Type arg4, Object arg5) {
        boolean v0 = arg5 instanceof JsonSerializer;
        boolean v1 = (v0) || ((arg5 instanceof JsonDeserializer)) || ((arg5 instanceof InstanceCreator)) || ((arg5 instanceof TypeAdapter)) ? true : false;
        $Gson$Preconditions.checkArgument(v1);
        if((arg5 instanceof InstanceCreator)) {
            this.instanceCreators.put(arg4, arg5);
        }

        if((v0) || ((arg5 instanceof JsonDeserializer))) {
            this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(arg4), arg5));
        }

        if((arg5 instanceof TypeAdapter)) {
            this.factories.add(TypeAdapters.newFactory(TypeToken.get(arg4), ((TypeAdapter)arg5)));
        }

        return this;
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory arg2) {
        this.factories.add(arg2);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class arg3, Object arg4) {
        boolean v0 = arg4 instanceof JsonSerializer;
        boolean v1 = (v0) || ((arg4 instanceof JsonDeserializer)) || ((arg4 instanceof TypeAdapter)) ? true : false;
        $Gson$Preconditions.checkArgument(v1);
        if(((arg4 instanceof JsonDeserializer)) || (v0)) {
            this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(arg3, arg4));
        }

        if((arg4 instanceof TypeAdapter)) {
            this.factories.add(TypeAdapters.newTypeHierarchyFactory(arg3, ((TypeAdapter)arg4)));
        }

        return this;
    }

    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public GsonBuilder setDateFormat(int arg1) {
        this.dateStyle = arg1;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(int arg1, int arg2) {
        this.dateStyle = arg1;
        this.timeStyle = arg2;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(String arg1) {
        this.datePattern = arg1;
        return this;
    }

    public GsonBuilder setExclusionStrategies(ExclusionStrategy[] arg6) {
        int v0 = arg6.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.excluder = this.excluder.withExclusionStrategy(arg6[v1], true, true);
        }

        return this;
    }

    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy arg1) {
        this.fieldNamingPolicy = ((FieldNamingStrategy)arg1);
        return this;
    }

    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy arg1) {
        this.fieldNamingPolicy = arg1;
        return this;
    }

    public GsonBuilder setLenient() {
        this.lenient = true;
        return this;
    }

    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy arg1) {
        this.longSerializationPolicy = arg1;
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        this.prettyPrinting = true;
        return this;
    }

    public GsonBuilder setVersion(double arg2) {
        this.excluder = this.excluder.withVersion(arg2);
        return this;
    }
}

