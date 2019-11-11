package com.google.gson.internal.reflect;

import com.google.gson.internal.JavaVersion;
import java.lang.reflect.AccessibleObject;

public abstract class ReflectionAccessor {
    private static final ReflectionAccessor instance;

    static {
        UnsafeReflectionAccessor v0_1;
        if(JavaVersion.getMajorJavaVersion() < 9) {
            PreJava9ReflectionAccessor v0 = new PreJava9ReflectionAccessor();
        }
        else {
            v0_1 = new UnsafeReflectionAccessor();
        }

        ReflectionAccessor.instance = ((ReflectionAccessor)v0_1);
    }

    public ReflectionAccessor() {
        super();
    }

    public static ReflectionAccessor getInstance() {
        return ReflectionAccessor.instance;
    }

    public abstract void makeAccessible(AccessibleObject arg1);
}

