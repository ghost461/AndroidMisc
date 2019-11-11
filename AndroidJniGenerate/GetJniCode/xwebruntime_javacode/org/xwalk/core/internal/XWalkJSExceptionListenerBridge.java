package org.xwalk.core.internal;

public class XWalkJSExceptionListenerBridge extends XWalkJSExceptionListenerInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onJsExceptionStringStringStringMethod;
    private Object wrapper;

    public XWalkJSExceptionListenerBridge(Object arg5) {
        super();
        this.onJsExceptionStringStringStringMethod = new ReflectMethod(null, "onJsException", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onJsException(String arg4, String arg5, String arg6) {
        this.onJsExceptionStringStringStringMethod.invoke(new Object[]{arg4, arg5, arg6});
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onJsExceptionStringStringStringMethod.init(this.wrapper, null, "onJsException", new Class[]{String.class, String.class, String.class});
    }
}

