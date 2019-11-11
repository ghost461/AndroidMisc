package org.xwalk.core.internal;

import android.content.Context;
import android.os.Build$VERSION;
import android.webkit.JavascriptInterface;
import org.chromium.base.JNIUtils$JniCrashCallback;
import org.chromium.base.JNIUtils;

class XWalkCoreBridge {
    private static final String BRIDGE_PACKAGE = "org.xwalk.core.internal";
    private static final String WRAPPER_PACKAGE = "org.xwalk.core";
    private Context mBridgeContext;
    private ClassLoader mWrapperLoader;
    private static XWalkCoreBridge sInstance;

    private XWalkCoreBridge(Context arg7, Object arg8) {
        Class v8;
        super();
        this.mBridgeContext = arg7;
        this.mWrapperLoader = arg8.getClass().getClassLoader();
        Class v7 = this.getBridgeClass("XWalkContent");
        if(Build$VERSION.SDK_INT >= 17) {
            v8 = JavascriptInterface.class;
            goto label_18;
        }

        try {
            v8 = XWalkCoreBridge.class.getClassLoader().loadClass("android.webkit.JavascriptInterface");
        }
        catch(ClassNotFoundException ) {
            arg8 = null;
        }

    label_18:
        new ReflectMethod(v7, "setJavascriptInterfaceClass", new Class[]{arg8.getClass()}).invoke(new Object[]{((Class)arg8)});
    }

    public Class getBridgeClass(String arg4) {
        try {
            ClassLoader v0 = XWalkCoreBridge.class.getClassLoader();
            StringBuilder v1 = new StringBuilder();
            v1.append("org.xwalk.core.internal.");
            v1.append(arg4);
            return v0.loadClass(v1.toString());
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public Object getBridgeObject(Object arg5) {
        try {
            return new ReflectMethod(arg5, "getBridge", new Class[0]).invoke(new Object[0]);
        }
        catch(RuntimeException ) {
            return null;
        }
    }

    public Context getContext() {
        return this.mBridgeContext;
    }

    public static XWalkCoreBridge getInstance() {
        return XWalkCoreBridge.sInstance;
    }

    public Class getWrapperClass(String arg4) {
        try {
            ClassLoader v0 = this.mWrapperLoader;
            StringBuilder v1 = new StringBuilder();
            v1.append("org.xwalk.core.");
            v1.append(arg4);
            return v0.loadClass(v1.toString());
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static void init(Context arg1, Object arg2) {
        JNIUtils.setJniCrashCallBack(new JniCrashCallback() {
            public void onJniCrash(Throwable arg4) {
                if("true".equalsIgnoreCase(RuntimeToSdkChannel.getCmd("dis_jni_crash_kill_self", "tools"))) {
                    return;
                }

                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), arg4);
            }
        });
        XWalkCoreBridge.sInstance = new XWalkCoreBridge(arg1, arg2);
    }
}

