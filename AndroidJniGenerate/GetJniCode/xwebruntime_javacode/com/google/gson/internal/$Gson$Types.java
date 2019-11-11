package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class $Gson$Types {
    final class GenericArrayTypeImpl implements Serializable, GenericArrayType {
        private final Type componentType;
        private static final long serialVersionUID;

        public GenericArrayTypeImpl(Type arg1) {
            super();
            this.componentType = $Gson$Types.canonicalize(arg1);
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof GenericArrayType) || !$Gson$Types.equals(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return $Gson$Types.typeToString(this.componentType) + "[]";
        }
    }

    final class ParameterizedTypeImpl implements Serializable, ParameterizedType {
        private final Type ownerType;
        private final Type rawType;
        private static final long serialVersionUID;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type arg5, Type arg6, Type[] arg7) {
            super();
            int v1 = 0;
            if((arg6 instanceof Class)) {
                Type v0 = arg6;
                boolean v3 = true;
                int v0_1 = (Modifier.isStatic(((Class)v0).getModifiers())) || ((Class)v0).getEnclosingClass() == null ? 1 : 0;
                if(arg5 == null) {
                    if(v0_1 != 0) {
                    }
                    else {
                        v3 = false;
                    }
                }

                $Gson$Preconditions.checkArgument(v3);
            }

            arg5 = arg5 == null ? null : $Gson$Types.canonicalize(arg5);
            this.ownerType = arg5;
            this.rawType = $Gson$Types.canonicalize(arg6);
            this.typeArguments = arg7.clone();
            int v5 = this.typeArguments.length;
            while(v1 < v5) {
                $Gson$Preconditions.checkNotNull(this.typeArguments[v1]);
                $Gson$Types.checkNotPrimitive(this.typeArguments[v1]);
                this.typeArguments[v1] = $Gson$Types.canonicalize(this.typeArguments[v1]);
                ++v1;
            }
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof ParameterizedType) || !$Gson$Types.equals(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type[] getActualTypeArguments() {
            return this.typeArguments.clone();
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ $Gson$Types.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            int v0 = this.typeArguments.length;
            if(v0 == 0) {
                return $Gson$Types.typeToString(this.rawType);
            }

            StringBuilder v1 = new StringBuilder((v0 + 1) * 30);
            v1.append($Gson$Types.typeToString(this.rawType));
            v1.append("<");
            v1.append($Gson$Types.typeToString(this.typeArguments[0]));
            int v2;
            for(v2 = 1; v2 < v0; ++v2) {
                v1.append(", ");
                v1.append($Gson$Types.typeToString(this.typeArguments[v2]));
            }

            v1.append(">");
            return v1.toString();
        }
    }

    final class WildcardTypeImpl implements Serializable, WildcardType {
        private final Type lowerBound;
        private static final long serialVersionUID;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] arg4, Type[] arg5) {
            super();
            boolean v1 = true;
            boolean v0 = arg5.length <= 1 ? true : false;
            $Gson$Preconditions.checkArgument(v0);
            v0 = arg4.length == 1 ? true : false;
            $Gson$Preconditions.checkArgument(v0);
            if(arg5.length == 1) {
                $Gson$Preconditions.checkNotNull(arg5[0]);
                $Gson$Types.checkNotPrimitive(arg5[0]);
                if(arg4[0] == Object.class) {
                }
                else {
                    v1 = false;
                }

                $Gson$Preconditions.checkArgument(v1);
                this.lowerBound = $Gson$Types.canonicalize(arg5[0]);
                this.upperBound = Object.class;
            }
            else {
                $Gson$Preconditions.checkNotNull(arg4[0]);
                $Gson$Types.checkNotPrimitive(arg4[0]);
                this.lowerBound = null;
                this.upperBound = $Gson$Types.canonicalize(arg4[0]);
            }
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof WildcardType) || !$Gson$Types.equals(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type[] getLowerBounds() {
            Type[] v0 = this.lowerBound != null ? new Type[]{this.lowerBound} : $Gson$Types.EMPTY_TYPE_ARRAY;
            return v0;
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public int hashCode() {
            int v0 = this.lowerBound != null ? this.lowerBound.hashCode() + 0x1F : 1;
            return v0 ^ this.upperBound.hashCode() + 0x1F;
        }

        public String toString() {
            if(this.lowerBound != null) {
                return "? super " + $Gson$Types.typeToString(this.lowerBound);
            }

            if(this.upperBound == Object.class) {
                return "?";
            }

            return "? extends " + $Gson$Types.typeToString(this.upperBound);
        }
    }

    static final Type[] EMPTY_TYPE_ARRAY;

    static {
        $Gson$Types.EMPTY_TYPE_ARRAY = new Type[0];
    }

    private $Gson$Types() {
        super();
        throw new UnsupportedOperationException();
    }

    public static GenericArrayType arrayOf(Type arg1) {
        return new GenericArrayTypeImpl(arg1);
    }

    public static Type canonicalize(Type arg3) {
        GenericArrayTypeImpl v3;
        if((arg3 instanceof Class)) {
            if(((Class)arg3).isArray()) {
                v3 = new GenericArrayTypeImpl($Gson$Types.canonicalize(((Class)arg3).getComponentType()));
            }

            return ((Type)v3);
        }

        if((arg3 instanceof ParameterizedType)) {
            return new ParameterizedTypeImpl(((ParameterizedType)arg3).getOwnerType(), ((ParameterizedType)arg3).getRawType(), ((ParameterizedType)arg3).getActualTypeArguments());
        }

        if((arg3 instanceof GenericArrayType)) {
            return new GenericArrayTypeImpl(((GenericArrayType)arg3).getGenericComponentType());
        }

        if((arg3 instanceof WildcardType)) {
            return new WildcardTypeImpl(((WildcardType)arg3).getUpperBounds(), ((WildcardType)arg3).getLowerBounds());
        }

        return arg3;
    }

    static void checkNotPrimitive(Type arg1) {
        boolean v1 = !(arg1 instanceof Class) || !((Class)arg1).isPrimitive() ? true : false;
        $Gson$Preconditions.checkArgument(v1);
    }

    private static Class declaringClassOf(TypeVariable arg1) {
        Class v1_1;
        GenericDeclaration v1 = arg1.getGenericDeclaration();
        if((v1 instanceof Class)) {
        }
        else {
            v1_1 = null;
        }

        return v1_1;
    }

    static boolean equal(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != arg1) {
            if(arg0 != null && (arg0.equals(arg1))) {
                goto label_7;
            }

            v0 = false;
        }
        else {
        label_7:
            v0 = true;
        }

        return v0;
    }

    public static boolean equals(Type arg4, Type arg5) {
        boolean v0 = true;
        if(arg4 == arg5) {
            return 1;
        }

        if((arg4 instanceof Class)) {
            return arg4.equals(arg5);
        }

        if((arg4 instanceof ParameterizedType)) {
            if(!(arg5 instanceof ParameterizedType)) {
                return 0;
            }

            if(!$Gson$Types.equal(((ParameterizedType)arg4).getOwnerType(), ((ParameterizedType)arg5).getOwnerType()) || !((ParameterizedType)arg4).getRawType().equals(((ParameterizedType)arg5).getRawType()) || !Arrays.equals(((ParameterizedType)arg4).getActualTypeArguments(), ((ParameterizedType)arg5).getActualTypeArguments())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        if((arg4 instanceof GenericArrayType)) {
            if(!(arg5 instanceof GenericArrayType)) {
                return 0;
            }

            return $Gson$Types.equals(((GenericArrayType)arg4).getGenericComponentType(), ((GenericArrayType)arg5).getGenericComponentType());
        }

        if((arg4 instanceof WildcardType)) {
            if(!(arg5 instanceof WildcardType)) {
                return 0;
            }

            if(!Arrays.equals(((WildcardType)arg4).getUpperBounds(), ((WildcardType)arg5).getUpperBounds()) || !Arrays.equals(((WildcardType)arg4).getLowerBounds(), ((WildcardType)arg5).getLowerBounds())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        if((arg4 instanceof TypeVariable)) {
            if(!(arg5 instanceof TypeVariable)) {
                return 0;
            }

            if(((TypeVariable)arg4).getGenericDeclaration() != ((TypeVariable)arg5).getGenericDeclaration() || !((TypeVariable)arg4).getName().equals(((TypeVariable)arg5).getName())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public static Type getArrayComponentType(Type arg1) {
        if((arg1 instanceof GenericArrayType)) {
            arg1 = ((GenericArrayType)arg1).getGenericComponentType();
        }
        else {
            Class v1 = ((Class)arg1).getComponentType();
        }

        return arg1;
    }

    public static Type getCollectionElementType(Type arg1, Class arg2) {
        arg1 = $Gson$Types.getSupertype(arg1, arg2, Collection.class);
        if((arg1 instanceof WildcardType)) {
            arg1 = ((WildcardType)arg1).getUpperBounds()[0];
        }

        if((arg1 instanceof ParameterizedType)) {
            return ((ParameterizedType)arg1).getActualTypeArguments()[0];
        }

        return Object.class;
    }

    static Type getGenericSupertype(Type arg3, Class arg4, Class arg5) {
        if(arg5 == arg4) {
            return arg3;
        }

        if(arg5.isInterface()) {
            Class[] v3 = arg4.getInterfaces();
            int v0 = 0;
            int v1 = v3.length;
            while(v0 < v1) {
                if(v3[v0] == arg5) {
                    return arg4.getGenericInterfaces()[v0];
                }
                else if(arg5.isAssignableFrom(v3[v0])) {
                    return $Gson$Types.getGenericSupertype(arg4.getGenericInterfaces()[v0], v3[v0], arg5);
                }
                else {
                    ++v0;
                    continue;
                }

                break;
            }
        }

        if(!arg4.isInterface()) {
            while(arg4 != Object.class) {
                Class v3_1 = arg4.getSuperclass();
                if(v3_1 == arg5) {
                    return arg4.getGenericSuperclass();
                }
                else if(arg5.isAssignableFrom(v3_1)) {
                    return $Gson$Types.getGenericSupertype(arg4.getGenericSuperclass(), v3_1, arg5);
                }
                else {
                    arg4 = v3_1;
                    continue;
                }

                return arg5;
            }
        }

        return arg5;
    }

    public static Type[] getMapKeyAndValueTypes(Type arg4, Class arg5) {
        Type[] v4;
        int v3 = 2;
        if(arg4 == Properties.class) {
            v4 = new Type[v3];
            v4[0] = String.class;
            v4[1] = String.class;
            return v4;
        }

        arg4 = $Gson$Types.getSupertype(arg4, arg5, Map.class);
        if((arg4 instanceof ParameterizedType)) {
            return ((ParameterizedType)arg4).getActualTypeArguments();
        }

        v4 = new Type[v3];
        v4[0] = Object.class;
        v4[1] = Object.class;
        return v4;
    }

    public static Class getRawType(Type arg4) {
        if((arg4 instanceof Class)) {
            return arg4;
        }

        if((arg4 instanceof ParameterizedType)) {
            arg4 = ((ParameterizedType)arg4).getRawType();
            $Gson$Preconditions.checkArgument(arg4 instanceof Class);
            return ((Class)arg4);
        }

        if((arg4 instanceof GenericArrayType)) {
            return Array.newInstance($Gson$Types.getRawType(((GenericArrayType)arg4).getGenericComponentType()), 0).getClass();
        }

        if((arg4 instanceof TypeVariable)) {
            return Object.class;
        }

        if((arg4 instanceof WildcardType)) {
            return $Gson$Types.getRawType(((WildcardType)arg4).getUpperBounds()[0]);
        }

        String v0 = arg4 == null ? "null" : arg4.getClass().getName();
        StringBuilder v2 = new StringBuilder();
        v2.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
        v2.append(arg4);
        v2.append("> is of type ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    }

    static Type getSupertype(Type arg1, Class arg2, Class arg3) {
        if((arg1 instanceof WildcardType)) {
            arg1 = ((WildcardType)arg1).getUpperBounds()[0];
        }

        $Gson$Preconditions.checkArgument(arg3.isAssignableFrom(arg2));
        return $Gson$Types.resolve(arg1, arg2, $Gson$Types.getGenericSupertype(arg1, arg2, arg3));
    }

    static int hashCodeOrZero(Object arg0) {
        int v0 = arg0 != null ? arg0.hashCode() : 0;
        return v0;
    }

    private static int indexOf(Object[] arg3, Object arg4) {
        int v0 = arg3.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4.equals(arg3[v1])) {
                return v1;
            }
        }

        throw new NoSuchElementException();
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type arg1, Type arg2, Type[] arg3) {
        return new ParameterizedTypeImpl(arg1, arg2, arg3);
    }

    public static Type resolve(Type arg1, Class arg2, Type arg3) {
        return $Gson$Types.resolve(arg1, arg2, arg3, new HashSet());
    }

    private static Type resolve(Type arg8, Class arg9, Type arg10, Collection arg11) {
        ParameterizedType v10_2;
        Type[] v4;
        int v0_2;
        Type v3;
        Type v0;
        do {
            if(!(arg10 instanceof TypeVariable)) {
                goto label_10;
            }

            v0 = arg10;
            if(arg11.contains(v0)) {
                return arg10;
            }

            arg11.add(v0);
            arg10 = $Gson$Types.resolveTypeVariable(arg8, arg9, ((TypeVariable)v0));
        }
        while(arg10 != v0);

        return arg10;
    label_10:
        if((arg10 instanceof Class)) {
            v0 = arg10;
            if(((Class)v0).isArray()) {
                Class v10 = ((Class)v0).getComponentType();
                arg8 = $Gson$Types.resolve(arg8, arg9, ((Type)v10), arg11);
                if(v10 == (((Class)arg8))) {
                }
                else {
                    GenericArrayType v0_1 = $Gson$Types.arrayOf(arg8);
                }

                return v0;
            }
        }

        if((arg10 instanceof GenericArrayType)) {
            v0 = ((GenericArrayType)arg10).getGenericComponentType();
            arg8 = $Gson$Types.resolve(arg8, arg9, v0, arg11);
            if(v0 == arg8) {
            }
            else {
                GenericArrayType v10_1 = $Gson$Types.arrayOf(arg8);
            }

            return arg10;
        }

        int v2 = 0;
        if((arg10 instanceof ParameterizedType)) {
            v0 = ((ParameterizedType)arg10).getOwnerType();
            v3 = $Gson$Types.resolve(arg8, arg9, v0, arg11);
            v0_2 = v3 != v0 ? 1 : 0;
            v4 = ((ParameterizedType)arg10).getActualTypeArguments();
            int v5 = v4.length;
            goto label_41;
        }

        if((arg10 instanceof WildcardType)) {
            Type[] v0_3 = ((WildcardType)arg10).getLowerBounds();
            Type[] v3_1 = ((WildcardType)arg10).getUpperBounds();
            if(v0_3.length == 1) {
                arg8 = $Gson$Types.resolve(arg8, arg9, v0_3[0], arg11);
                if(arg8 != v0_3[0]) {
                    return $Gson$Types.supertypeOf(arg8);
                }
            }
            else if(v3_1.length == 1) {
                arg8 = $Gson$Types.resolve(arg8, arg9, v3_1[0], arg11);
                if(arg8 != v3_1[0]) {
                    return $Gson$Types.subtypeOf(arg8);
                }
            }

            return arg10;
        }

        return arg10;
    label_41:
        while(v2 < v5) {
            Type v6 = $Gson$Types.resolve(arg8, arg9, v4[v2], arg11);
            if(v6 != v4[v2]) {
                if(v0_2 == 0) {
                    Object v4_1 = v4.clone();
                    v0_2 = 1;
                }

                v4[v2] = v6;
            }

            ++v2;
        }

        if(v0_2 != 0) {
            v10_2 = $Gson$Types.newParameterizedTypeWithOwner(v3, ((ParameterizedType)arg10).getRawType(), v4);
        }

        return ((Type)v10_2);
    }

    static Type resolveTypeVariable(Type arg1, Class arg2, TypeVariable arg3) {
        Class v0 = $Gson$Types.declaringClassOf(arg3);
        if(v0 == null) {
            return arg3;
        }

        arg1 = $Gson$Types.getGenericSupertype(arg1, arg2, v0);
        if((arg1 instanceof ParameterizedType)) {
            return ((ParameterizedType)arg1).getActualTypeArguments()[$Gson$Types.indexOf(v0.getTypeParameters(), arg3)];
        }

        return arg3;
    }

    public static WildcardType subtypeOf(Type arg2) {
        Type[] v2 = (arg2 instanceof WildcardType) ? ((WildcardType)arg2).getUpperBounds() : new Type[]{arg2};
        return new WildcardTypeImpl(v2, $Gson$Types.EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type arg4) {
        Type[] v4 = (arg4 instanceof WildcardType) ? ((WildcardType)arg4).getLowerBounds() : new Type[]{arg4};
        return new WildcardTypeImpl(new Type[]{Object.class}, v4);
    }

    public static String typeToString(Type arg1) {
        String v1 = (arg1 instanceof Class) ? ((Class)arg1).getName() : arg1.toString();
        return v1;
    }
}

