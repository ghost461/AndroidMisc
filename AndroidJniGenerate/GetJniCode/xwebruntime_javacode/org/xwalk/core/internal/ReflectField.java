package org.xwalk.core.internal;

import java.lang.reflect.Field;
import java.util.concurrent.RejectedExecutionException;

class ReflectField {
    private Class mClass;
    private Field mField;
    private Object mInstance;
    private String mName;

    public ReflectField() {
        super();
    }

    public ReflectField(Class arg2, String arg3) {
        super();
        this.init(null, arg2, arg3);
    }

    public ReflectField(Object arg2, String arg3) {
        super();
        this.init(arg2, null, arg3);
    }

    public Object get() {
        if(this.mField == null) {
            throw new UnsupportedOperationException(this.toString());
        }

        try {
            return this.mField.get(this.mInstance);
        }
        catch(ExceptionInInitializerError v0) {
            throw new RuntimeException(((Throwable)v0));
        }
        catch(IllegalArgumentException v0_1) {
            throw v0_1;
        }
        catch(NullPointerException v0_2) {
            throw new RejectedExecutionException(((Throwable)v0_2));
        }
    }

    public Object getInstance() {
        return this.mInstance;
    }

    public String getName() {
        return this.mName;
    }

    public boolean init(Object arg2, Class arg3, String arg4) {
        this.mInstance = arg2;
        Field v0 = null;
        if(arg3 != null) {
        }
        else if(arg2 != null) {
            arg3 = arg2.getClass();
        }
        else {
            arg3 = ((Class)v0);
        }

        this.mClass = arg3;
        this.mName = arg4;
        this.mField = v0;
        if(this.mClass == null) {
            return 0;
        }

        boolean v2 = true;
        try {
            this.mField = this.mClass.getField(this.mName);
        }
        catch(NoSuchFieldException ) {
            Class v4 = this.mClass;
            while(v4 != null) {
                try {
                    this.mField = v4.getDeclaredField(this.mName);
                    this.mField.setAccessible(true);
                    break;
                }
                catch(NoSuchFieldException ) {
                    v4 = v4.getSuperclass();
                    continue;
                }
            }
        }

        if(this.mField != null) {
        }
        else {
            v2 = false;
        }

        return v2;
    }

    public boolean isNull() {
        boolean v0 = this.mField == null ? true : false;
        return v0;
    }

    public String toString() {
        if(this.mField != null) {
            return this.mField.toString();
        }

        String v0 = "";
        if(this.mClass != null) {
            v0 = v0 + this.mClass.toString() + ".";
        }

        if(this.mName != null) {
            v0 = v0 + this.mName;
        }

        return v0;
    }
}

