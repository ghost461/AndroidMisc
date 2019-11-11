package org.xwalk.core.internal;

public class XWalkExtendCanvasClientBridge extends XWalkExtendCanvasClientInternal {
    private static final String TAG = "XWalkExtendCanvasClientBridge";
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onAsycResultCallbackintintStringMethod;
    private ReflectMethod onPluginTouchStringintStringMethod;
    private ReflectMethod onSendJsonMessageStringMethod;
    private Object wrapper;

    public XWalkExtendCanvasClientBridge(XWalkViewBridge arg5, Object arg6) {
        super(((XWalkViewInternal)arg5));
        this.onSendJsonMessageStringMethod = new ReflectMethod(null, "onSendJsonMessage", new Class[0]);
        this.onAsycResultCallbackintintStringMethod = new ReflectMethod(null, "onAsycResultCallback", new Class[0]);
        this.onPluginTouchStringintStringMethod = new ReflectMethod(null, "onCanvasTouch", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onAsycResultCallback(int arg4, int arg5, String arg6) {
        try {
            this.onAsycResultCallbackintintStringMethod.invoke(new Object[]{Integer.valueOf(arg4), Integer.valueOf(arg5), arg6});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendCanvasClientBridge", "This API is incompatible with the sdk:onAsycResultCallback method error royle + " + v4.getMessage());
        }
    }

    public void onCanvasTouch(String arg4, int arg5, String arg6) {
        try {
            this.onPluginTouchStringintStringMethod.invoke(new Object[]{arg4, Integer.valueOf(arg5), arg6});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendCanvasClientBridge", "This API is incompatible with the sdk:onCanvasTouch method error royle + " + v4.getMessage());
        }
    }

    public void onSendJsonMessage(String arg4) {
        try {
            this.onSendJsonMessageStringMethod.invoke(new Object[]{arg4});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendCanvasClientBridge", "This API is incompatible with the sdk:onSendJsonMessage method error royle + " + v4.getMessage());
        }
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onSendJsonMessageStringMethod.init(this.wrapper, null, "onSendJsonMessage", new Class[]{String.class});
        this.onPluginTouchStringintStringMethod.init(this.wrapper, null, "onCanvasTouch", new Class[]{String.class, Integer.TYPE, String.class});
        this.onAsycResultCallbackintintStringMethod.init(this.wrapper, null, "onAsycResultCallback", new Class[]{Integer.TYPE, Integer.TYPE, String.class});
    }
}

