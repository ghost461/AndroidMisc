package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = null;
    private static final double IGNORE_VERSIONS = -1;
    private List deserializationStrategies;
    private int modifiers;
    private boolean requireExpose;
    private List serializationStrategies;
    private boolean serializeInnerClasses;
    private double version;

    static {
        Excluder.DEFAULT = new Excluder();
    }

    public Excluder() {
        super();
        this.version = -1;
        this.modifiers = 0x88;
        this.serializeInnerClasses = true;
        this.serializationStrategies = Collections.emptyList();
        this.deserializationStrategies = Collections.emptyList();
    }

    protected Excluder clone() {
        try {
            return super.clone();
        }
        catch(CloneNotSupportedException v0) {
            throw new AssertionError(v0);
        }
    }

    protected Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public TypeAdapter create(Gson arg12, TypeToken arg13) {
        Class v0 = arg13.getRawType();
        boolean v1 = this.excludeClassChecks(v0);
        boolean v8 = (v1) || (this.excludeClassInStrategy(v0, true)) ? true : false;
        boolean v7 = (v1) || (this.excludeClassInStrategy(v0, false)) ? true : false;
        if(!v8 && !v7) {
            return null;
        }

        return new TypeAdapter(v7, v8, arg12, arg13) {
            private TypeAdapter delegate;

            private TypeAdapter delegate() {
                TypeAdapter v0 = this.delegate;
                if(v0 != null) {
                }
                else {
                    v0 = this.val$gson.getDelegateAdapter(Excluder.this, this.val$type);
                    this.delegate = v0;
                }

                return v0;
            }

            public Object read(JsonReader arg2) throws IOException {
                if(this.val$skipDeserialize) {
                    arg2.skipValue();
                    return null;
                }

                return this.delegate().read(arg2);
            }

            public void write(JsonWriter arg2, Object arg3) throws IOException {
                if(this.val$skipSerialize) {
                    arg2.nullValue();
                    return;
                }

                this.delegate().write(arg2, arg3);
            }
        };
    }

    public Excluder disableInnerClassSerialization() {
        Excluder v0 = this.clone();
        v0.serializeInnerClasses = false;
        return v0;
    }

    public boolean excludeClass(Class arg2, boolean arg3) {
        boolean v2 = (this.excludeClassChecks(arg2)) || (this.excludeClassInStrategy(arg2, arg3)) ? true : false;
        return v2;
    }

    private boolean excludeClassChecks(Class arg6) {
        if(Double.compare(this.version, -1) != 0 && !this.isValidVersion(arg6.getAnnotation(Since.class), arg6.getAnnotation(Until.class))) {
            return 1;
        }

        if(!this.serializeInnerClasses && (this.isInnerClass(arg6))) {
            return 1;
        }

        if(this.isAnonymousOrLocal(arg6)) {
            return 1;
        }

        return 0;
    }

    private boolean excludeClassInStrategy(Class arg2, boolean arg3) {
        List v3 = arg3 ? this.serializationStrategies : this.deserializationStrategies;
        Iterator v3_1 = v3.iterator();
        do {
            if(!v3_1.hasNext()) {
                return 0;
            }
        }
        while(!v3_1.next().shouldSkipClass(arg2));

        return 1;
    }

    public boolean excludeField(Field arg7, boolean arg8) {
        if((this.modifiers & arg7.getModifiers()) != 0) {
            return 1;
        }

        if(this.version != -1 && !this.isValidVersion(arg7.getAnnotation(Since.class), arg7.getAnnotation(Until.class))) {
            return 1;
        }

        if(arg7.isSynthetic()) {
            return 1;
        }

        if(this.requireExpose) {
            Annotation v0 = arg7.getAnnotation(Expose.class);
            if(v0 != null) {
                if(!arg8) {
                    if(!((Expose)v0).deserialize()) {
                        return 1;
                    }

                    goto label_31;
                }
                else if(!((Expose)v0).serialize()) {
                }
                else {
                    goto label_31;
                }
            }

            return 1;
        }

    label_31:
        if(!this.serializeInnerClasses && (this.isInnerClass(arg7.getType()))) {
            return 1;
        }

        if(this.isAnonymousOrLocal(arg7.getType())) {
            return 1;
        }

        List v8 = arg8 ? this.serializationStrategies : this.deserializationStrategies;
        if(!v8.isEmpty()) {
            FieldAttributes v0_1 = new FieldAttributes(arg7);
            Iterator v7 = v8.iterator();
            do {
                if(v7.hasNext()) {
                    if(!v7.next().shouldSkipField(v0_1)) {
                        continue;
                    }

                    return 1;
                }

                return 0;
            }
            while(true);

            return 1;
        }

        return 0;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder v0 = this.clone();
        v0.requireExpose = true;
        return v0;
    }

    private boolean isAnonymousOrLocal(Class arg2) {
        boolean v2;
        if(!Enum.class.isAssignableFrom(arg2)) {
            if(!arg2.isAnonymousClass() && !arg2.isLocalClass()) {
                goto label_9;
            }

            v2 = true;
        }
        else {
        label_9:
            v2 = false;
        }

        return v2;
    }

    private boolean isInnerClass(Class arg2) {
        boolean v2 = !arg2.isMemberClass() || (this.isStatic(arg2)) ? false : true;
        return v2;
    }

    private boolean isStatic(Class arg1) {
        boolean v1 = (arg1.getModifiers() & 8) != 0 ? true : false;
        return v1;
    }

    private boolean isValidSince(Since arg5) {
        if(arg5 != null && arg5.value() > this.version) {
            return 0;
        }

        return 1;
    }

    private boolean isValidUntil(Until arg5) {
        if(arg5 != null && arg5.value() <= this.version) {
            return 0;
        }

        return 1;
    }

    private boolean isValidVersion(Since arg1, Until arg2) {
        boolean v1 = !this.isValidSince(arg1) || !this.isValidUntil(arg2) ? false : true;
        return v1;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy arg3, boolean arg4, boolean arg5) {
        Excluder v0 = this.clone();
        if(arg4) {
            v0.serializationStrategies = new ArrayList(this.serializationStrategies);
            v0.serializationStrategies.add(arg3);
        }

        if(arg5) {
            v0.deserializationStrategies = new ArrayList(this.deserializationStrategies);
            v0.deserializationStrategies.add(arg3);
        }

        return v0;
    }

    public Excluder withModifiers(int[] arg6) {
        Excluder v0 = this.clone();
        int v1 = 0;
        v0.modifiers = 0;
        int v2 = arg6.length;
        while(v1 < v2) {
            v0.modifiers |= arg6[v1];
            ++v1;
        }

        return v0;
    }

    public Excluder withVersion(double arg2) {
        Excluder v0 = this.clone();
        v0.version = arg2;
        return v0;
    }
}

