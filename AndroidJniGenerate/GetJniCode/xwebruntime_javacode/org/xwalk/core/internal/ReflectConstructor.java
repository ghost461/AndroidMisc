package org.xwalk.core.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.RejectedExecutionException;

class ReflectConstructor {
    private Class mClass;
    private Constructor mConstructor;
    private Class[] mParameterTypes;

    public ReflectConstructor() {
        super();
    }

    public ReflectConstructor(Class arg1, Class[] arg2) {
        super();
        this.init(arg1, arg2);
    }

    public boolean init(Class arg3, Class[] arg4) {
        this.mClass = arg3;
        this.mParameterTypes = arg4;
        this.mConstructor = null;
        if(this.mClass == null) {
            return 0;
        }

        boolean v3 = true;
        try {
            this.mConstructor = this.mClass.getConstructor(this.mParameterTypes);
            goto label_20;
        }
        catch(NoSuchMethodException ) {
            try {
                this.mConstructor = this.mClass.getDeclaredConstructor(this.mParameterTypes);
                this.mConstructor.setAccessible(true);
                goto label_20;
            }
            catch(NoSuchMethodException ) {
            label_20:
                if(this.mConstructor != null) {
                }
                else {
                    v3 = false;
                }

                return v3;
            }
        }
    }

    public boolean isNull() {
        boolean v0 = this.mConstructor == null ? true : false;
        return v0;
    }

    public Object newInstance(Object[] arg2) {
        if(this.mConstructor == null) {
            throw new UnsupportedOperationException(this.toString());
        }

        try {
            return this.mConstructor.newInstance(arg2);
        }
        catch(InvocationTargetException v2) {
            throw new RuntimeException(v2.getCause());
        }
        catch(IllegalArgumentException v2_1) {
            throw v2_1;
        }
        catch(InstantiationException v2_2) {
            throw new RejectedExecutionException(((Throwable)v2_2));
        }
    }

    public String toString() {
        if(this.mConstructor != null) {
            return this.mConstructor.toString();
        }

        String v0 = "";
        if(this.mClass != null) {
            v0 = v0 + this.mClass.toString();
        }

        return v0;
    }
}

