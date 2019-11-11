package org.xwalk.core.internal;

import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") class XWalkDevToolsServer {
    public enum Security {
        public static final enum Security ALLOW_DEBUG_PERMISSION;
        public static final enum Security ALLOW_SOCKET_ACCESS;
        public static final enum Security DEFAULT;

        static {
            Security.DEFAULT = new Security("DEFAULT", 0);
            Security.ALLOW_DEBUG_PERMISSION = new Security("ALLOW_DEBUG_PERMISSION", 1);
            Security.ALLOW_SOCKET_ACCESS = new Security("ALLOW_SOCKET_ACCESS", 2);
            Security.$VALUES = new Security[]{Security.DEFAULT, Security.ALLOW_DEBUG_PERMISSION, Security.ALLOW_SOCKET_ACCESS};
        }

        private Security(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static Security valueOf(String arg1) {
            return Enum.valueOf(Security.class, arg1);
        }

        public static Security[] values() {
            return Security.$VALUES.clone();
        }
    }

    private static final String DEBUG_PERMISSION_SIFFIX = ".permission.DEBUG";
    private long mNativeDevToolsServer;
    private String mSocketName;

    public XWalkDevToolsServer(String arg3) {
        super();
        this.mNativeDevToolsServer = 0;
        this.mSocketName = null;
        this.mNativeDevToolsServer = this.nativeInitRemoteDebugging(arg3);
        this.mSocketName = arg3;
    }

    @CalledByNative private static boolean checkDebugPermission(int arg3, int arg4) {
        Context v0 = ContextUtils.getApplicationContext();
        StringBuilder v1 = new StringBuilder();
        v1.append(v0.getPackageName());
        v1.append(".permission.DEBUG");
        boolean v3 = v0.checkPermission(v1.toString(), arg3, arg4) == 0 ? true : false;
        return v3;
    }

    public void destroy() {
        this.nativeDestroyRemoteDebugging(this.mNativeDevToolsServer);
        this.mNativeDevToolsServer = 0;
    }

    public String getSocketName() {
        return this.mSocketName;
    }

    public boolean isRemoteDebuggingEnabled() {
        return this.nativeIsRemoteDebuggingEnabled(this.mNativeDevToolsServer);
    }

    private native void nativeDestroyRemoteDebugging(long arg1) {
    }

    private native long nativeInitRemoteDebugging(String arg1) {
    }

    private native boolean nativeIsRemoteDebuggingEnabled(long arg1) {
    }

    private native void nativeSetRemoteDebuggingEnabled(long arg1, boolean arg2, boolean arg3, boolean arg4) {
    }

    public void setRemoteDebuggingEnabled(boolean arg2) {
        this.setRemoteDebuggingEnabled(arg2, Security.DEFAULT);
    }

    public void setRemoteDebuggingEnabled(boolean arg10, Security arg11) {
        boolean v7 = arg11 == Security.ALLOW_DEBUG_PERMISSION ? true : false;
        boolean v8 = arg11 == Security.ALLOW_SOCKET_ACCESS ? true : false;
        this.nativeSetRemoteDebuggingEnabled(this.mNativeDevToolsServer, arg10, v7, v8);
    }
}

