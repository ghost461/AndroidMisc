package com.google.gson.reflect;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.$Gson$Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken {
    final int hashCode;
    final Class rawType;
    final Type type;

    protected TypeToken() {
        super();
        this.type = TypeToken.getSuperclassTypeParameter(this.getClass());
        this.rawType = $Gson$Types.getRawType(this.type);
        this.hashCode = this.type.hashCode();
    }

    TypeToken(Type arg1) {
        super();
        this.type = $Gson$Types.canonicalize($Gson$Preconditions.checkNotNull(arg1));
        this.rawType = $Gson$Types.getRawType(this.type);
        this.hashCode = this.type.hashCode();
    }

    private static AssertionError buildUnexpectedTypeError(Type arg4, Class[] arg5) {
        StringBuilder v0 = new StringBuilder("Unexpected type. Expected one of: ");
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.append(arg5[v2].getName());
            v0.append(", ");
        }

        v0.append("but got: ");
        v0.append(arg4.getClass().getName());
        v0.append(", for type token: ");
        v0.append(arg4.toString());
        v0.append('.');
        return new AssertionError(v0.toString());
    }

    public final boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof TypeToken) || !$Gson$Types.equals(this.type, ((TypeToken)arg2).type) ? false : true;
        return v2;
    }

    public static TypeToken get(Class arg1) {
        return new TypeToken(((Type)arg1));
    }

    public static TypeToken get(Type arg1) {
        return new TypeToken(arg1);
    }

    public static TypeToken getArray(Type arg1) {
        return new TypeToken($Gson$Types.arrayOf(arg1));
    }

    public static TypeToken getParameterized(Type arg2, Type[] arg3) {
        return new TypeToken($Gson$Types.newParameterizedTypeWithOwner(null, arg2, arg3));
    }

    public final Class getRawType() {
        return this.rawType;
    }

    static Type getSuperclassTypeParameter(Class arg1) {
        Type v1 = arg1.getGenericSuperclass();
        if((v1 instanceof Class)) {
            throw new RuntimeException("Missing type parameter.");
        }

        return $Gson$Types.canonicalize(((ParameterizedType)v1).getActualTypeArguments()[0]);
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    private static boolean isAssignableFrom(Type arg1, GenericArrayType arg2) {
        Class v1;
        Type v2 = arg2.getGenericComponentType();
        if((v2 instanceof ParameterizedType)) {
            if((arg1 instanceof GenericArrayType)) {
                arg1 = ((GenericArrayType)arg1).getGenericComponentType();
            }
            else {
                if(!(arg1 instanceof Class)) {
                    goto label_13;
                }

                while(((Class)arg1).isArray()) {
                    v1 = v1.getComponentType();
                }
            }

        label_13:
            return TypeToken.isAssignableFrom(((Type)v1), ((ParameterizedType)v2), new HashMap());
        }

        return 1;
    }

    private static boolean isAssignableFrom(Type arg9, ParameterizedType arg10, Map arg11) {
        Object v6_1;
        Type[] v9;
        int v0 = 0;
        if(arg9 == null) {
            return 0;
        }

        if(arg10.equals(arg9)) {
            return 1;
        }

        Class v1 = $Gson$Types.getRawType(arg9);
        Type v3 = null;
        if((arg9 instanceof ParameterizedType)) {
            v3 = arg9;
        }

        if(v3 != null) {
            v9 = ((ParameterizedType)v3).getActualTypeArguments();
            TypeVariable[] v4 = v1.getTypeParameters();
            int v5;
            for(v5 = 0; v5 < v9.length; ++v5) {
                Type v6 = v9[v5];
                TypeVariable v7 = v4[v5];
                while((v6 instanceof TypeVariable)) {
                    v6_1 = arg11.get(((TypeVariable)v6_1).getName());
                }

                arg11.put(v7.getName(), v6_1);
            }

            if(TypeToken.typeEquals(((ParameterizedType)v3), arg10, arg11)) {
                return 1;
            }
        }

        v9 = v1.getGenericInterfaces();
        int v3_1 = v9.length;
        while(v0 < v3_1) {
            if(TypeToken.isAssignableFrom(v9[v0], arg10, new HashMap(arg11))) {
                return 1;
            }

            ++v0;
        }

        return TypeToken.isAssignableFrom(v1.getGenericSuperclass(), arg10, new HashMap(arg11));
    }

    @Deprecated public boolean isAssignableFrom(TypeToken arg1) {
        return this.isAssignableFrom(arg1.getType());
    }

    @Deprecated public boolean isAssignableFrom(Type arg5) {
        boolean v0 = false;
        if(arg5 == null) {
            return 0;
        }

        if(this.type.equals(arg5)) {
            return 1;
        }

        if((this.type instanceof Class)) {
            return this.rawType.isAssignableFrom($Gson$Types.getRawType(arg5));
        }

        if((this.type instanceof ParameterizedType)) {
            return TypeToken.isAssignableFrom(arg5, this.type, new HashMap());
        }

        if((this.type instanceof GenericArrayType)) {
            if((this.rawType.isAssignableFrom($Gson$Types.getRawType(arg5))) && (TypeToken.isAssignableFrom(arg5, this.type))) {
                v0 = true;
            }

            return v0;
        }

        throw TypeToken.buildUnexpectedTypeError(this.type, new Class[]{Class.class, ParameterizedType.class, GenericArrayType.class});
    }

    @Deprecated public boolean isAssignableFrom(Class arg1) {
        return this.isAssignableFrom(((Type)arg1));
    }

    private static boolean matches(Type arg1, Type arg2, Map arg3) {
        boolean v1;
        if(!arg2.equals(arg1)) {
            if(((arg1 instanceof TypeVariable)) && (arg2.equals(arg3.get(((TypeVariable)arg1).getName())))) {
                goto label_11;
            }

            v1 = false;
        }
        else {
        label_11:
            v1 = true;
        }

        return v1;
    }

    public final String toString() {
        return $Gson$Types.typeToString(this.type);
    }

    private static boolean typeEquals(ParameterizedType arg4, ParameterizedType arg5, Map arg6) {
        if(arg4.getRawType().equals(arg5.getRawType())) {
            Type[] v4 = arg4.getActualTypeArguments();
            Type[] v5 = arg5.getActualTypeArguments();
            int v0;
            for(v0 = 0; v0 < v4.length; ++v0) {
                if(!TypeToken.matches(v4[v0], v5[v0], arg6)) {
                    return 0;
                }
            }

            return 1;
        }

        return 0;
    }
}

