package org.xwalk.core.internal;

import android.os.Bundle;

public class XWalkProxyWebViewClientExtensionBridge extends XWalkProxyWebViewClientExtensionInternal {
    private static final String TAG = "XWalkProxyWebViewClientExtensionBridge";
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onMiscCallBackMethod;
    private Object wrapper;

    public XWalkProxyWebViewClientExtensionBridge(Object arg5) {
        super();
        this.onMiscCallBackMethod = new ReflectMethod(null, "onMiscCallBack", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public Object onMiscCallBack(String arg4, Bundle arg5) {
        try {
            return this.onMiscCallBackMethod.invoke(new Object[]{arg4, arg5});
        }
        catch(Exception v4) {
            Log.e("XWalkProxyWebViewClientExtensionBridge", "This API is incompatible with the sdk:onMiscCallBack method error royle:" + v4.getMessage());
            return null;
        }
    }

    public void onMiscCallBackSuper(String arg1, Bundle arg2) {
        super.onMiscCallBack(arg1, arg2);
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onMiscCallBackMethod.init(this.wrapper, null, "onMiscCallBack", new Class[]{String.class, Bundle.class});
    }
}

