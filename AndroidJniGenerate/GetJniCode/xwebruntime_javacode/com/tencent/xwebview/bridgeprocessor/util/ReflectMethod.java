package com.tencent.xwebview.bridgeprocessor.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.RejectedExecutionException;

public class ReflectMethod {
    private Object[] mArguments;
    private Class mClass;
    private Object mInstance;
    private Method mMethod;
    private String mName;
    private Class[] mParameterTypes;

    public ReflectMethod() {
        super();
    }

    public ReflectMethod(Class arg2, String arg3, Class[] arg4) {
        super();
        this.init(null, arg2, arg3, arg4);
    }

    public ReflectMethod(Object arg2, String arg3, Class[] arg4) {
        super();
        this.init(arg2, null, arg3, arg4);
    }

    public Object[] getArguments() {
        return this.mArguments;
    }

    public Object getInstance() {
        return this.mInstance;
    }

    public String getName() {
        return this.mName;
    }

    public boolean init(Object arg2, Class arg3, String arg4, Class[] arg5) {
        this.mInstance = arg2;
        Method v0 = null;
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
        this.mParameterTypes = arg5;
        this.mMethod = v0;
        if(this.mClass == null) {
            return 0;
        }

        boolean v2 = true;
        try {
            this.mMethod = this.mClass.getMethod(this.mName, this.mParameterTypes);
        }
        catch(NoSuchMethodException ) {
            Class v4 = this.mClass;
            while(v4 != null) {
                try {
                    this.mMethod = v4.getDeclaredMethod(this.mName, this.mParameterTypes);
                    this.mMethod.setAccessible(true);
                    break;
                }
                catch(NoSuchMethodException ) {
                    v4 = v4.getSuperclass();
                    continue;
                }
            }
        }

        if(this.mMethod != null) {
        }
        else {
            v2 = false;
        }

        return v2;
    }

    public Object invoke(Object[] arg3) {
        if(this.mMethod == null) {
            throw new UnsupportedOperationException(this.toString());
        }

        try {
            return this.mMethod.invoke(this.mInstance, arg3);
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(v3.getCause());
        }
        catch(IllegalArgumentException v3_1) {
            throw v3_1;
        }
        catch(NullPointerException v3_2) {
            throw new RejectedExecutionException(((Throwable)v3_2));
        }
    }

    public Object invokeWithArguments() {
        return this.invoke(this.mArguments);
    }

    public boolean isNull() {
        boolean v0 = this.mMethod == null ? true : false;
        return v0;
    }

    public void setArguments(Object[] arg1) {
        this.mArguments = arg1;
    }

    public String toString() {
        if(this.mMethod != null) {
            return this.mMethod.toString();
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

