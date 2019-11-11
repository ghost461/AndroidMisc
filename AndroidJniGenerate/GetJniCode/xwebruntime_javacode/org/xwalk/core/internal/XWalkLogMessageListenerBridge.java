package org.xwalk.core.internal;

public class XWalkLogMessageListenerBridge extends XWalkLogMessageListenerInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onLogMessageintStringintStringMethod;
    private Object wrapper;

    public XWalkLogMessageListenerBridge(Object arg5) {
        super();
        this.onLogMessageintStringintStringMethod = new ReflectMethod(null, "onLogMessage", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onLogMessage(int arg4, String arg5, int arg6, String arg7) {
        this.onLogMessageintStringintStringMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5, Integer.valueOf(arg6), arg7});
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onLogMessageintStringintStringMethod.init(this.wrapper, null, "onLogMessage", new Class[]{Integer.TYPE, String.class, Integer.TYPE, String.class});
    }
}

