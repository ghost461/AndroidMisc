package com.google.gson.internal.reflect;

import com.google.gson.JsonIOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

final class UnsafeReflectionAccessor extends ReflectionAccessor {
    private final Field overrideField;
    private final Object theUnsafe;
    private static Class unsafeClass;

    UnsafeReflectionAccessor() {
        super();
        this.theUnsafe = UnsafeReflectionAccessor.getUnsafeInstance();
        this.overrideField = UnsafeReflectionAccessor.getOverrideField();
    }

    private static Field getOverrideField() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        }
        catch(NoSuchFieldException ) {
            return null;
        }
    }

    private static Object getUnsafeInstance() {
        Object v0 = null;
        try {
            UnsafeReflectionAccessor.unsafeClass = Class.forName("sun.misc.Unsafe");
            Field v1 = UnsafeReflectionAccessor.unsafeClass.getDeclaredField("theUnsafe");
            v1.setAccessible(true);
            return v1.get(v0);
        }
        catch(Exception ) {
            return v0;
        }
    }

    public void makeAccessible(AccessibleObject arg5) {
        if(!this.makeAccessibleWithUnsafe(arg5)) {
            try {
                arg5.setAccessible(true);
            }
            catch(SecurityException v0) {
                StringBuilder v2 = new StringBuilder();
                v2.append("Gson couldn\'t modify fields for ");
                v2.append(arg5);
                v2.append("\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.");
                throw new JsonIOException(v2.toString(), ((Throwable)v0));
            }
        }
    }

    boolean makeAccessibleWithUnsafe(AccessibleObject arg11) {
        if(this.theUnsafe != null && this.overrideField != null) {
            try {
                UnsafeReflectionAccessor.unsafeClass.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.theUnsafe, arg11, Long.valueOf(UnsafeReflectionAccessor.unsafeClass.getMethod("objectFieldOffset", Field.class).invoke(this.theUnsafe, this.overrideField).longValue()), Boolean.valueOf(true));
                return 1;
            }
            catch(Exception ) {
                return 0;
            }
        }

        return 0;
    }
}

