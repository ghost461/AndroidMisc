package org.chromium.content.browser.remoteobjects;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.chromium.blink.mojom.RemoteInvocationArgument;
import org.chromium.blink.mojom.RemoteInvocationResult;
import org.chromium.blink.mojom.RemoteInvocationResultValue;
import org.chromium.blink.mojom.RemoteObject$GetMethodsResponse;
import org.chromium.blink.mojom.RemoteObject$HasMethodResponse;
import org.chromium.blink.mojom.RemoteObject$InvokeMethodResponse;
import org.chromium.blink.mojom.RemoteObject;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo_base.mojom.String16;

class RemoteObjectImpl implements RemoteObject {
    interface Auditor {
        void onObjectGetClassInvocationAttempt();
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface StringCoercionMode {
        public static final int COERCE = 1;
        public static final int DO_NOT_COERCE;

    }

    private final Auditor mAuditor;
    private final SortedMap mMethods;
    private final WeakReference mTarget;
    private static final Method sGetClassMethod;

    static {
        try {
            RemoteObjectImpl.sGetClassMethod = Object.class.getMethod("getClass");
            return;
        }
        catch(NoSuchMethodException v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }

    public RemoteObjectImpl(Object arg2, Class arg3) {
        this(arg2, arg3, null);
    }

    public RemoteObjectImpl(Object arg6, Class arg7, Auditor arg8) {
        ArrayList v3_1;
        super();
        this.mMethods = new TreeMap();
        this.mTarget = new WeakReference(arg6);
        this.mAuditor = arg8;
        Method[] v6 = arg6.getClass().getMethods();
        int v8 = v6.length;
        int v0;
        for(v0 = 0; v0 < v8; ++v0) {
            Method v1 = v6[v0];
            if(arg7 == null || (v1.isAnnotationPresent(arg7))) {
                String v2 = v1.getName();
                Object v3 = this.mMethods.get(v2);
                if(v3 == null) {
                    v3_1 = new ArrayList(1);
                    this.mMethods.put(v2, v3_1);
                }

                ((List)v3_1).add(v1);
            }
            else {
            }
        }
    }

    public void close() {
    }

    private Object convertArgument(RemoteInvocationArgument arg7, Class arg8, int arg9) {
        String v3_1;
        Object v3 = null;
        switch(arg7.which()) {
            case 0: {
                goto label_86;
            }
            case 1: {
                goto label_68;
            }
            case 2: {
                goto label_55;
            }
            case 3: {
                goto label_39;
            }
            case 4: {
                goto label_9;
            }
        }

        throw new RuntimeException("invalid wire argument type");
    label_68:
        boolean v7 = arg7.getBooleanValue();
        if(arg8 == Boolean.TYPE) {
            return Boolean.valueOf(v7);
        }

        if(arg8.isPrimitive()) {
            return RemoteObjectImpl.getPrimitiveZero(arg8);
        }

        if(arg8 == String.class) {
            if(arg9 == 1) {
                v3_1 = Boolean.toString(v7);
            }

            return v3;
        }

        if(arg8.isArray()) {
            return v3;
        }

        return v3;
    label_86:
        double v4 = arg7.getNumberValue();
        if(arg8 == Byte.TYPE) {
            return Byte.valueOf(((byte)(((int)v4))));
        }

        if(arg8 == Character.TYPE) {
            if(RemoteObjectImpl.isInt32(v4)) {
                return Character.valueOf(((char)(((int)v4))));
            }

            return Character.valueOf('\u0000');
        }

        if(arg8 == Short.TYPE) {
            return Short.valueOf(((short)(((int)v4))));
        }

        if(arg8 == Integer.TYPE) {
            return Integer.valueOf(((int)v4));
        }

        if(arg8 == Long.TYPE) {
            return Long.valueOf(((long)v4));
        }

        if(arg8 == Float.TYPE) {
            return Float.valueOf(((float)v4));
        }

        if(arg8 == Double.TYPE) {
            return Double.valueOf(v4);
        }

        if(arg8 == Boolean.TYPE) {
            return Boolean.valueOf(false);
        }

        if(arg8 == String.class) {
            if(arg9 == 1) {
                v3_1 = RemoteObjectImpl.doubleToString(v4);
            }

            return v3;
        }

        if(arg8.isArray()) {
            return v3;
        }

        return v3;
    label_55:
        if(arg8 == String.class) {
            return RemoteObjectImpl.mojoStringToJavaString(arg7.getStringValue());
        }

        if(arg8.isPrimitive()) {
            return RemoteObjectImpl.getPrimitiveZero(arg8);
        }

        if(arg8.isArray()) {
            return v3;
        }

        return v3;
    label_39:
        arg7.getSingletonValue();
        if(arg8 == String.class) {
            if(arg7.getSingletonValue() == 1 && arg9 == 1) {
                v3_1 = "undefined";
            }

            return v3;
        }

        if(arg8.isPrimitive()) {
            return RemoteObjectImpl.getPrimitiveZero(arg8);
        }

        if(arg8.isArray()) {
            return v3;
        }

        return v3;
    label_9:
        RemoteInvocationArgument[] v7_1 = arg7.getArrayValue();
        if(arg8.isArray()) {
            arg8 = arg8.getComponentType();
            if(!arg8.isPrimitive() && arg8 != String.class) {
                return v3;
            }

            Object v9 = Array.newInstance(arg8, v7_1.length);
            int v0;
            for(v0 = 0; v0 < v7_1.length; ++v0) {
                Array.set(v9, v0, this.convertArgument(v7_1[v0], arg8, 0));
            }

            return v9;
        }

        if(arg8 == String.class) {
            if(arg9 == 1) {
                v3_1 = "undefined";
            }

            return v3;
        }

        if(arg8.isPrimitive()) {
            return RemoteObjectImpl.getPrimitiveZero(arg8);
        }

        return v3;
    }

    private RemoteInvocationResult convertResult(Object arg4, Class arg5) {
        RemoteInvocationResultValue v0 = new RemoteInvocationResultValue();
        if(arg5 == Void.TYPE) {
            v0.setSingletonValue(1);
        }
        else if(arg5 == Boolean.TYPE) {
            v0.setBooleanValue(((Boolean)arg4).booleanValue());
        }
        else if(arg5 == Character.TYPE) {
            v0.setNumberValue(((double)((Character)arg4).charValue()));
        }
        else if(arg5.isPrimitive()) {
            v0.setNumberValue(((Number)arg4).doubleValue());
        }
        else if(arg5 == String.class) {
            if(arg4 == null) {
                v0.setSingletonValue(1);
            }
            else {
                v0.setStringValue(RemoteObjectImpl.javaStringToMojoString(((String)arg4)));
            }
        }

        RemoteInvocationResult v4 = new RemoteInvocationResult();
        v4.value = v0;
        return v4;
    }

    private static String doubleToString(double arg7) {
        if(Double.isNaN(arg7)) {
            return "nan";
        }

        double v1 = 0;
        if(Double.isInfinite(arg7)) {
            String v7 = arg7 > v1 ? "inf" : "-inf";
            return v7;
        }

        double v3 = 1;
        if(Double.compare(arg7, v1) == 0 && v3 / arg7 < v1) {
            return "-0";
        }

        if(arg7 % v3 == v1 && arg7 >= -2147483648 && arg7 <= 2147483647) {
            return Integer.toString(((int)arg7));
        }

        return String.format(null, "%.6g", Double.valueOf(arg7)).replaceFirst("^(-?[0-9]+)(\\.0+)?((\\.[0-9]*[1-9])0*)?(e.*)?$", "$1$4$5");
    }

    private Method findMethod(String arg4, int arg5) {
        Object v1;
        Object v4 = this.mMethods.get(arg4);
        Method v0 = null;
        if(v4 == null) {
            return v0;
        }

        Iterator v4_1 = ((List)v4).iterator();
        do {
            if(!v4_1.hasNext()) {
                return v0;
            }

            v1 = v4_1.next();
        }
        while(((Method)v1).getParameterTypes().length != arg5);

        return ((Method)v1);
    }

    public void getMethods(GetMethodsResponse arg3) {
        Set v0 = this.mMethods.keySet();
        arg3.call(v0.toArray(new String[v0.size()]));
    }

    private static Object getPrimitiveZero(Class arg3) {
        if(arg3 == Boolean.TYPE) {
            return Boolean.valueOf(false);
        }

        if(arg3 == Byte.TYPE) {
            return Byte.valueOf(0);
        }

        if(arg3 == Character.TYPE) {
            return Character.valueOf('\u0000');
        }

        if(arg3 == Short.TYPE) {
            return Short.valueOf(0);
        }

        if(arg3 == Integer.TYPE) {
            return Integer.valueOf(0);
        }

        if(arg3 == Long.TYPE) {
            return Long.valueOf(0);
        }

        if(arg3 == Float.TYPE) {
            return Float.valueOf(0f);
        }

        if(arg3 == Double.TYPE) {
            return Double.valueOf(0);
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("unexpected primitive type ");
        v1.append(arg3);
        throw new RuntimeException(v1.toString());
    }

    public void hasMethod(String arg2, HasMethodResponse arg3) {
        arg3.call(Boolean.valueOf(this.mMethods.containsKey(arg2)));
    }

    public void invokeMethod(String arg9, RemoteInvocationArgument[] arg10, InvokeMethodResponse arg11) {
        Object v10;
        Object v0 = this.mTarget.get();
        if(v0 == null) {
            return;
        }

        int v1 = arg10.length;
        Method v9 = this.findMethod(arg9, v1);
        if(v9 == null) {
            arg11.call(RemoteObjectImpl.makeErrorResult(1));
            return;
        }

        if(v9.equals(RemoteObjectImpl.sGetClassMethod)) {
            if(this.mAuditor != null) {
                this.mAuditor.onObjectGetClassInvocationAttempt();
            }

            arg11.call(RemoteObjectImpl.makeErrorResult(2));
            return;
        }

        if(v9.getReturnType().isArray()) {
            RemoteInvocationResult v9_1 = new RemoteInvocationResult();
            v9_1.value = new RemoteInvocationResultValue();
            v9_1.value.setSingletonValue(1);
            arg11.call(v9_1);
            return;
        }

        Class[] v3 = v9.getParameterTypes();
        Object[] v4 = new Object[v1];
        int v5;
        for(v5 = 0; v5 < v1; ++v5) {
            v4[v5] = this.convertArgument(arg10[v5], v3[v5], 1);
        }

        try {
            v10 = v9.invoke(v0, v4);
        }
        catch(InvocationTargetException v9_2) {
            ThrowableExtension.printStackTrace(v9_2.getCause());
            arg11.call(RemoteObjectImpl.makeErrorResult(3));
            return;
        }
        catch(NullPointerException v9_3) {
            throw new RuntimeException(((Throwable)v9_3));
        }

        arg11.call(this.convertResult(v10, v9.getReturnType()));
    }

    private static boolean isInt32(double arg7) {
        boolean v7;
        double v0 = 1;
        double v4 = 0;
        if(arg7 % v0 != v4 || arg7 < -2147483648 || arg7 > 2147483647) {
        label_13:
            v7 = false;
        }
        else {
            if(arg7 == v4 && v0 / arg7 <= v4) {
                goto label_13;
            }

            v7 = true;
        }

        return v7;
    }

    private static String16 javaStringToMojoString(String arg3) {
        short[] v0 = new short[arg3.length()];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = ((short)arg3.charAt(v1));
        }

        String16 v3 = new String16();
        v3.data = v0;
        return v3;
    }

    private static RemoteInvocationResult makeErrorResult(int arg1) {
        RemoteInvocationResult v0 = new RemoteInvocationResult();
        v0.error = arg1;
        return v0;
    }

    private static String mojoStringToJavaString(String16 arg3) {
        short[] v3 = arg3.data;
        char[] v0 = new char[v3.length];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = ((char)v3[v1]);
        }

        return String.valueOf(v0);
    }

    public void onConnectionError(MojoException arg1) {
        this.close();
    }
}

