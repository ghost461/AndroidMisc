package com.google.gson.internal;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Primitives {
    private static final Map PRIMITIVE_TO_WRAPPER_TYPE;
    private static final Map WRAPPER_TO_PRIMITIVE_TYPE;

    static {
        HashMap v0 = new HashMap(16);
        HashMap v2 = new HashMap(16);
        Primitives.add(((Map)v0), ((Map)v2), Boolean.TYPE, Boolean.class);
        Primitives.add(((Map)v0), ((Map)v2), Byte.TYPE, Byte.class);
        Primitives.add(((Map)v0), ((Map)v2), Character.TYPE, Character.class);
        Primitives.add(((Map)v0), ((Map)v2), Double.TYPE, Double.class);
        Primitives.add(((Map)v0), ((Map)v2), Float.TYPE, Float.class);
        Primitives.add(((Map)v0), ((Map)v2), Integer.TYPE, Integer.class);
        Primitives.add(((Map)v0), ((Map)v2), Long.TYPE, Long.class);
        Primitives.add(((Map)v0), ((Map)v2), Short.TYPE, Short.class);
        Primitives.add(((Map)v0), ((Map)v2), Void.TYPE, Void.class);
        Primitives.PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(((Map)v0));
        Primitives.WRAPPER_TO_PRIMITIVE_TYPE = Collections.unmodifiableMap(((Map)v2));
    }

    private Primitives() {
        super();
        throw new UnsupportedOperationException();
    }

    private static void add(Map arg0, Map arg1, Class arg2, Class arg3) {
        arg0.put(arg2, arg3);
        arg1.put(arg3, arg2);
    }

    public static boolean isPrimitive(Type arg1) {
        return Primitives.PRIMITIVE_TO_WRAPPER_TYPE.containsKey(arg1);
    }

    public static boolean isWrapperType(Type arg1) {
        return Primitives.WRAPPER_TO_PRIMITIVE_TYPE.containsKey($Gson$Preconditions.checkNotNull(arg1));
    }

    public static Class unwrap(Class arg2) {
        Object v2;
        Object v0 = Primitives.WRAPPER_TO_PRIMITIVE_TYPE.get($Gson$Preconditions.checkNotNull(arg2));
        if(v0 == null) {
        }
        else {
            v2 = v0;
        }

        return ((Class)v2);
    }

    public static Class wrap(Class arg2) {
        Object v2;
        Object v0 = Primitives.PRIMITIVE_TO_WRAPPER_TYPE.get($Gson$Preconditions.checkNotNull(arg2));
        if(v0 == null) {
        }
        else {
            v2 = v0;
        }

        return ((Class)v2);
    }
}

