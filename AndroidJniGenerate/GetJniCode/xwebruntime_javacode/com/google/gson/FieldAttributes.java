package com.google.gson;

import com.google.gson.internal.$Gson$Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field arg1) {
        super();
        $Gson$Preconditions.checkNotNull(arg1);
        this.field = arg1;
    }

    Object get(Object arg2) throws IllegalAccessException {
        return this.field.get(arg2);
    }

    public Annotation getAnnotation(Class arg2) {
        return this.field.getAnnotation(arg2);
    }

    public Collection getAnnotations() {
        return Arrays.asList(this.field.getAnnotations());
    }

    public Class getDeclaredClass() {
        return this.field.getType();
    }

    public Type getDeclaredType() {
        return this.field.getGenericType();
    }

    public Class getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public String getName() {
        return this.field.getName();
    }

    public boolean hasModifier(int arg2) {
        boolean v2 = (arg2 & this.field.getModifiers()) != 0 ? true : false;
        return v2;
    }

    boolean isSynthetic() {
        return this.field.isSynthetic();
    }
}

