package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RestrictTo(value={Scope.LIBRARY_GROUP}) class Lifecycling {
    private static Map sCallbackCache;
    private static Constructor sREFLECTIVE;

    static {
        try {
            Lifecycling.sREFLECTIVE = ReflectiveGenericLifecycleObserver.class.getDeclaredConstructor(Object.class);
            goto label_8;
        }
        catch(NoSuchMethodException ) {
        label_8:
            Lifecycling.sCallbackCache = new HashMap();
            return;
        }
    }

    Lifecycling() {
        super();
    }

    static String getAdapterName(String arg3) {
        return arg3.replace(".", "_") + "_LifecycleAdapter";
    }

    @NonNull static GenericLifecycleObserver getCallback(Object arg5) {
        if((arg5 instanceof GenericLifecycleObserver)) {
            return arg5;
        }

        try {
            Class v0 = arg5.getClass();
            Object v1 = Lifecycling.sCallbackCache.get(v0);
            if(v1 != null) {
                return ((Constructor)v1).newInstance(arg5);
            }

            Constructor v1_1 = Lifecycling.getGeneratedAdapterConstructor(v0);
            if(v1_1 == null) {
                v1_1 = Lifecycling.sREFLECTIVE;
            }
            else if(!v1_1.isAccessible()) {
                v1_1.setAccessible(true);
            }

            Lifecycling.sCallbackCache.put(v0, v1_1);
            return v1_1.newInstance(arg5);
        }
        catch(InvocationTargetException v5) {
            throw new RuntimeException(((Throwable)v5));
        }
        catch(InstantiationException v5_1) {
            throw new RuntimeException(((Throwable)v5_1));
        }
        catch(IllegalAccessException v5_2) {
            throw new RuntimeException(((Throwable)v5_2));
        }
    }

    @Nullable private static Constructor getGeneratedAdapterConstructor(Class arg5) {
        Package v0 = arg5.getPackage();
        String v0_1 = v0 != null ? v0.getName() : "";
        String v1 = arg5.getCanonicalName();
        Constructor v2 = null;
        if(v1 == null) {
            return v2;
        }

        if(v0_1.isEmpty()) {
        }
        else {
            v1 = v1.substring(v0_1.length() + 1);
        }

        v1 = Lifecycling.getAdapterName(v1);
        try {
            if(v0_1.isEmpty()) {
            }
            else {
                v1 = v0_1 + "." + v1;
            }

            return Class.forName(v1).getDeclaredConstructor(arg5);
        }
        catch(NoSuchMethodException v5) {
            throw new RuntimeException(((Throwable)v5));
        }
        catch(ClassNotFoundException ) {
            arg5 = arg5.getSuperclass();
            if(arg5 != null) {
                return Lifecycling.getGeneratedAdapterConstructor(arg5);
            }

            return v2;
        }
    }
}

