package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator {
    public UnsafeAllocator() {
        super();
    }

    static void assertInstantiable(Class arg3) {
        StringBuilder v1;
        int v0 = arg3.getModifiers();
        if(Modifier.isInterface(v0)) {
            v1 = new StringBuilder();
            v1.append("Interface can\'t be instantiated! Interface name: ");
            v1.append(arg3.getName());
            throw new UnsupportedOperationException(v1.toString());
        }

        if(Modifier.isAbstract(v0)) {
            v1 = new StringBuilder();
            v1.append("Abstract class can\'t be instantiated! Class name: ");
            v1.append(arg3.getName());
            throw new UnsupportedOperationException(v1.toString());
        }
    }

    public static UnsafeAllocator create() {
        Object v0 = null;
        try {
            Class v3 = Class.forName("sun.misc.Unsafe");
            Field v4 = v3.getDeclaredField("theUnsafe");
            v4.setAccessible(true);
            return new UnsafeAllocator(v3.getMethod("allocateInstance", Class.class), v4.get(v0)) {
                public Object newInstance(Class arg5) throws Exception {
                    com.google.gson.internal.UnsafeAllocator$1.assertInstantiable(arg5);
                    return this.val$allocateInstance.invoke(this.val$unsafe, arg5);
                }
            };
        }
        catch(Exception ) {
            int v3_1 = 2;
            try {
                Method v4_1 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                v4_1.setAccessible(true);
                int v0_1 = v4_1.invoke(v0, Object.class).intValue();
                Class v4_2 = ObjectStreamClass.class;
                v4_1 = v4_2.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                v4_1.setAccessible(true);
                return new UnsafeAllocator(v4_1, v0_1) {
                    public Object newInstance(Class arg4) throws Exception {
                        com.google.gson.internal.UnsafeAllocator$2.assertInstantiable(arg4);
                        return this.val$newInstance.invoke(null, arg4, Integer.valueOf(this.val$constructorId));
                    }
                };
            }
            catch(Exception ) {
                try {
                    Class v0_2 = ObjectInputStream.class;
                    Method v0_3 = v0_2.getDeclaredMethod("newInstance", Class.class, Class.class);
                    v0_3.setAccessible(true);
                    return new UnsafeAllocator(v0_3) {
                        public Object newInstance(Class arg4) throws Exception {
                            com.google.gson.internal.UnsafeAllocator$3.assertInstantiable(arg4);
                            return this.val$newInstance.invoke(null, arg4, Object.class);
                        }
                    };
                }
                catch(Exception ) {
                    return new UnsafeAllocator() {
                        public Object newInstance(Class arg4) {
                            StringBuilder v1 = new StringBuilder();
                            v1.append("Cannot allocate ");
                            v1.append(arg4);
                            throw new UnsupportedOperationException(v1.toString());
                        }
                    };
                }
            }
        }
    }

    public abstract Object newInstance(Class arg1) throws Exception;
}

