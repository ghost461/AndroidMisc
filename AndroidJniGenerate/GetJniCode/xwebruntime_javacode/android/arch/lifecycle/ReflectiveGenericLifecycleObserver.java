package android.arch.lifecycle;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {
    class CallbackInfo {
        final Map mEventToHandlers;
        final Map mHandlerToEvent;

        CallbackInfo(Map arg5) {
            ArrayList v2_1;
            super();
            this.mHandlerToEvent = arg5;
            this.mEventToHandlers = new HashMap();
            Iterator v5 = arg5.entrySet().iterator();
            while(v5.hasNext()) {
                Object v0 = v5.next();
                Object v1 = ((Map$Entry)v0).getValue();
                Object v2 = this.mEventToHandlers.get(v1);
                if(v2 == null) {
                    v2_1 = new ArrayList();
                    this.mEventToHandlers.put(v1, v2_1);
                }

                ((List)v2_1).add(((Map$Entry)v0).getKey());
            }
        }
    }

    class MethodReference {
        final int mCallType;
        final Method mMethod;

        MethodReference(int arg1, Method arg2) {
            super();
            this.mCallType = arg1;
            this.mMethod = arg2;
            this.mMethod.setAccessible(true);
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((MethodReference)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.mCallType != ((MethodReference)arg5).mCallType || !this.mMethod.getName().equals(((MethodReference)arg5).mMethod.getName())) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.mCallType * 0x1F + this.mMethod.getName().hashCode();
        }
    }

    private static final int CALL_TYPE_NO_ARG = 0;
    private static final int CALL_TYPE_PROVIDER = 1;
    private static final int CALL_TYPE_PROVIDER_WITH_EVENT = 2;
    private final CallbackInfo mInfo;
    private final Object mWrapped;
    static final Map sInfoCache;

    static {
        ReflectiveGenericLifecycleObserver.sInfoCache = new HashMap();
    }

    ReflectiveGenericLifecycleObserver(Object arg1) {
        super();
        this.mWrapped = arg1;
        this.mInfo = ReflectiveGenericLifecycleObserver.getInfo(this.mWrapped.getClass());
    }

    private static CallbackInfo createInfo(Class arg12) {
        int v8;
        CallbackInfo v0_1;
        Class v0 = arg12.getSuperclass();
        HashMap v1 = new HashMap();
        if(v0 != null) {
            v0_1 = ReflectiveGenericLifecycleObserver.getInfo(v0);
            if(v0_1 != null) {
                ((Map)v1).putAll(v0_1.mHandlerToEvent);
            }
        }

        Method[] v0_2 = arg12.getDeclaredMethods();
        Class[] v2 = arg12.getInterfaces();
        int v3 = v2.length;
        int v5;
        for(v5 = 0; v5 < v3; ++v5) {
            Iterator v6 = ReflectiveGenericLifecycleObserver.getInfo(v2[v5]).mHandlerToEvent.entrySet().iterator();
            while(v6.hasNext()) {
                Object v7 = v6.next();
                ReflectiveGenericLifecycleObserver.verifyAndPutHandler(((Map)v1), ((Map$Entry)v7).getKey(), ((Map$Entry)v7).getValue(), arg12);
            }
        }

        int v2_1 = v0_2.length;
        for(v3 = 0; v3 < v2_1; ++v3) {
            Method v5_1 = v0_2[v3];
            Annotation v6_1 = v5_1.getAnnotation(OnLifecycleEvent.class);
            if(v6_1 == null) {
            }
            else {
                Class[] v7_1 = v5_1.getParameterTypes();
                if(v7_1.length <= 0) {
                    v8 = 0;
                }
                else if(!v7_1[0].isAssignableFrom(LifecycleOwner.class)) {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                else {
                    v8 = 1;
                }

                Event v6_2 = ((OnLifecycleEvent)v6_1).value();
                int v11 = 2;
                if(v7_1.length > 1) {
                    if(!v7_1[1].isAssignableFrom(Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    else if(v6_2 != Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    else {
                        v8 = 2;
                    }
                }

                if(v7_1.length > v11) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }

                ReflectiveGenericLifecycleObserver.verifyAndPutHandler(((Map)v1), new MethodReference(v8, v5_1), v6_2, arg12);
            }
        }

        v0_1 = new CallbackInfo(((Map)v1));
        ReflectiveGenericLifecycleObserver.sInfoCache.put(arg12, v0_1);
        return v0_1;
    }

    private static CallbackInfo getInfo(Class arg1) {
        Object v0 = ReflectiveGenericLifecycleObserver.sInfoCache.get(arg1);
        if(v0 != null) {
            return ((CallbackInfo)v0);
        }

        return ReflectiveGenericLifecycleObserver.createInfo(arg1);
    }

    private void invokeCallback(MethodReference arg5, LifecycleOwner arg6, Event arg7) {
        try {
            switch(arg5.mCallType) {
                case 0: {
                    arg5.mMethod.invoke(this.mWrapped);
                    return;
                }
                case 1: {
                    arg5.mMethod.invoke(this.mWrapped, arg6);
                    return;
                    throw new RuntimeException("Failed to call observer method", v5_1.getCause());
                    throw new RuntimeException(((Throwable)v5));
                }
                case 2: {
                    arg5.mMethod.invoke(this.mWrapped, arg6, arg7);
                    return;
                }
                default: {
                    return;
                }
            }
        }
        catch(IllegalAccessException v5) {
        }
        catch(InvocationTargetException v5_1) {
        }
    }

    private void invokeCallbacks(CallbackInfo arg2, LifecycleOwner arg3, Event arg4) {
        this.invokeMethodsForEvent(arg2.mEventToHandlers.get(arg4), arg3, arg4);
        this.invokeMethodsForEvent(arg2.mEventToHandlers.get(Event.ON_ANY), arg3, arg4);
    }

    private void invokeMethodsForEvent(List arg3, LifecycleOwner arg4, Event arg5) {
        if(arg3 != null) {
            int v0;
            for(v0 = arg3.size() - 1; v0 >= 0; --v0) {
                this.invokeCallback(arg3.get(v0), arg4, arg5);
            }
        }
    }

    public void onStateChanged(LifecycleOwner arg2, Event arg3) {
        this.invokeCallbacks(this.mInfo, arg2, arg3);
    }

    private static void verifyAndPutHandler(Map arg3, MethodReference arg4, Event arg5, Class arg6) {
        Object v0 = arg3.get(arg4);
        if(v0 != null && arg5 != (((Event)v0))) {
            Method v3 = arg4.mMethod;
            StringBuilder v1 = new StringBuilder();
            v1.append("Method ");
            v1.append(v3.getName());
            v1.append(" in ");
            v1.append(arg6.getName());
            v1.append(" already declared with different @OnLifecycleEvent value: previous");
            v1.append(" value ");
            v1.append(v0);
            v1.append(", new value ");
            v1.append(arg5);
            throw new IllegalArgumentException(v1.toString());
        }

        if(v0 == null) {
            arg3.put(arg4, arg5);
        }
    }
}

