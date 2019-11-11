package org.xwalk.core.internal;

public class XWalkFindListenerBridge extends XWalkFindListenerInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onFindResultReceivedintintbooleanMethod;
    private Object wrapper;

    public XWalkFindListenerBridge(Object arg5) {
        super();
        this.onFindResultReceivedintintbooleanMethod = new ReflectMethod(null, "onFindResultReceived", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onFindResultReceived(int arg4, int arg5, boolean arg6) {
        this.onFindResultReceivedintintbooleanMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5), Boolean.valueOf(arg6)});
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onFindResultReceivedintintbooleanMethod.init(this.wrapper, null, "onFindResultReceived", new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE});
    }
}

