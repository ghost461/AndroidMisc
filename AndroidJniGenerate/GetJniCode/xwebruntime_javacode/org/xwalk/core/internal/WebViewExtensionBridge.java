package org.xwalk.core.internal;

public class WebViewExtensionBridge extends WebViewExtensionInternal {
    private ReflectMethod SetExtensionInternalMethod;
    private XWalkCoreBridge coreBridge;
    private WebViewExtensionInternal internal;
    private Object wrapper;

    WebViewExtensionBridge(Object arg5) {
        super();
        this.SetExtensionInternalMethod = new ReflectMethod(null, "SetExtension", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public void SetExtension(WebViewExtensionInterfaceBridge arg5) {
        if(this.SetExtensionInternalMethod == null || (this.SetExtensionInternalMethod.isNull())) {
            this.SetExtensionSuper(arg5);
        }
        else {
            ReflectMethod v0 = this.SetExtensionInternalMethod;
            Object[] v1 = new Object[1];
            if((arg5 instanceof WebViewExtensionInterfaceBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v0.invoke(v1);
        }
    }

    public void SetExtension(WebViewExtensionInterfaceInternal arg2) {
        if((arg2 instanceof WebViewExtensionInterfaceBridge)) {
            this.SetExtension(((WebViewExtensionInterfaceBridge)arg2));
        }
        else {
            super.SetExtension(arg2);
        }
    }

    public void SetExtensionSuper(WebViewExtensionInterfaceBridge arg2) {
        if(this.internal == null) {
            super.SetExtension(((WebViewExtensionInterfaceInternal)arg2));
        }
        else {
            this.internal.SetExtension(((WebViewExtensionInterfaceInternal)arg2));
        }
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.SetExtensionInternalMethod.init(this.wrapper, null, "SetExtension", new Class[]{this.coreBridge.getWrapperClass("WebViewExtensionInterface")});
    }
}

