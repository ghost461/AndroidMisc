package org.xwalk.core.internal;

public class XWalkNotifyChannelListenerBridge extends XWalkNotifyChannelListenerInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onNotifyCallBackChannelintObjectArrayMethod;
    private Object wrapper;

    public XWalkNotifyChannelListenerBridge(Object arg5) {
        super();
        this.onNotifyCallBackChannelintObjectArrayMethod = new ReflectMethod(null, "onNotifyCallBackChannel", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onNotifyCallBackChannel(int arg4, Object[] arg5) {
        this.onNotifyCallBackChannelintObjectArrayMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onNotifyCallBackChannelintObjectArrayMethod.init(this.wrapper, null, "onNotifyCallBackChannel", new Class[]{Integer.TYPE, Object[].class});
    }
}

