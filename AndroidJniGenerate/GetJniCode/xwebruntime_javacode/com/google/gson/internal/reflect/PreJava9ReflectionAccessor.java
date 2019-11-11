package com.google.gson.internal.reflect;

import java.lang.reflect.AccessibleObject;

final class PreJava9ReflectionAccessor extends ReflectionAccessor {
    PreJava9ReflectionAccessor() {
        super();
    }

    public void makeAccessible(AccessibleObject arg2) {
        arg2.setAccessible(true);
    }
}

