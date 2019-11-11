package org.xwalk.core.internal;

public class XWalkNativeExtensionLoaderBridge extends XWalkNativeExtensionLoaderInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod registerNativeExtensionsInPathStringMethod;
    private Object wrapper;

    public XWalkNativeExtensionLoaderBridge(Object arg5) {
        super();
        this.registerNativeExtensionsInPathStringMethod = new ReflectMethod(null, "registerNativeExtensionsInPath", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.registerNativeExtensionsInPathStringMethod.init(this.wrapper, null, "registerNativeExtensionsInPath", new Class[]{String.class});
    }

    public void registerNativeExtensionsInPath(String arg4) {
        if(this.registerNativeExtensionsInPathStringMethod == null || (this.registerNativeExtensionsInPathStringMethod.isNull())) {
            this.registerNativeExtensionsInPathSuper(arg4);
        }
        else {
            this.registerNativeExtensionsInPathStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void registerNativeExtensionsInPathSuper(String arg1) {
        super.registerNativeExtensionsInPath(arg1);
    }
}

