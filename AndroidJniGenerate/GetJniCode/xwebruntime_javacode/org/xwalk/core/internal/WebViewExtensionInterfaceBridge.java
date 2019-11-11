package org.xwalk.core.internal;

import java.util.List;

public class WebViewExtensionInterfaceBridge extends WebViewExtensionInterfaceInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod getHostByNameMethod;
    private ReflectMethod onMiscCallBackStringBundleMethod;
    private Object wrapper;

    public WebViewExtensionInterfaceBridge(Object arg6) {
        super();
        this.onMiscCallBackStringBundleMethod = new ReflectMethod(null, "onMiscCallBack", new Class[0]);
        this.getHostByNameMethod = new ReflectMethod(null, "getHostByName", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public int getHostByName(String arg4, List arg5) {
        return this.getHostByNameMethod.invoke(new Object[]{arg4, arg5}).intValue();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public Object onMiscCallBack(String arg4, Object[] arg5) {
        return this.onMiscCallBackStringBundleMethod.invoke(new Object[]{arg4, arg5});
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onMiscCallBackStringBundleMethod.init(this.wrapper, null, "onMiscCallBack", new Class[]{String.class, Object[].class});
        this.getHostByNameMethod.init(this.wrapper, null, "getHostByName", new Class[]{String.class, List.class});
    }
}

