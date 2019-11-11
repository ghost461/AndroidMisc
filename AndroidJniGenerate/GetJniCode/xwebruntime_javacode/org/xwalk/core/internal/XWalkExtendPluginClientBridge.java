package org.xwalk.core.internal;

import android.graphics.SurfaceTexture;
import android.view.MotionEvent;

public class XWalkExtendPluginClientBridge extends XWalkExtendPluginClientInternal {
    private static final String TAG = "XWalkExtendPluginClientBridge";
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onPluginDestroyStringintMethod;
    private ReflectMethod onPluginReadyStringintSurfaceTextureMethod;
    private ReflectMethod onPluginTouchStringintMotionEventMethod;
    private ReflectMethod onPluginTouchStringintStringMethod;
    private ReflectMethod onSendJsonMessageStringMethod;
    private Object wrapper;

    public XWalkExtendPluginClientBridge(XWalkViewBridge arg5, Object arg6) {
        super(((XWalkViewInternal)arg5));
        this.onPluginReadyStringintSurfaceTextureMethod = new ReflectMethod(null, "onPluginReady", new Class[0]);
        this.onPluginDestroyStringintMethod = new ReflectMethod(null, "onPluginDestroy", new Class[0]);
        this.onSendJsonMessageStringMethod = new ReflectMethod(null, "onSendJsonMessage", new Class[0]);
        this.onPluginTouchStringintStringMethod = new ReflectMethod(null, "onPluginTouch", new Class[0]);
        this.onPluginTouchStringintMotionEventMethod = new ReflectMethod(null, "onPluginTouch", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onPluginDestroy(String arg4, int arg5) {
        try {
            this.onPluginDestroyStringintMethod.invoke(new Object[]{arg4, Integer.valueOf(arg5)});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendPluginClientBridge", "This API is incompatible with the sdk:onPluginDestroy method error royle + " + v4.getMessage());
        }
    }

    public void onPluginReady(String arg4, int arg5, SurfaceTexture arg6) {
        try {
            this.onPluginReadyStringintSurfaceTextureMethod.invoke(new Object[]{arg4, Integer.valueOf(arg5), arg6});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendPluginClientBridge", "This API is incompatible with the sdk:onPluginReady method error royle + " + v4.getMessage());
        }
    }

    public void onPluginTouch(String arg4, int arg5, MotionEvent arg6) {
        try {
            this.onPluginTouchStringintMotionEventMethod.invoke(new Object[]{arg4, Integer.valueOf(arg5), arg6});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendPluginClientBridge", "This API is incompatible with the sdk:onPluginTouch method error royle + " + v4.getMessage());
        }
    }

    public void onPluginTouch(String arg4, int arg5, String arg6) {
        try {
            this.onPluginTouchStringintStringMethod.invoke(new Object[]{arg4, Integer.valueOf(arg5), arg6});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendPluginClientBridge", "This API is incompatible with the sdk:onPluginTouch method error royle + " + v4.getMessage());
        }
    }

    public void onSendJsonMessage(String arg4) {
        try {
            this.onSendJsonMessageStringMethod.invoke(new Object[]{arg4});
        }
        catch(Exception v4) {
            Log.e("XWalkExtendPluginClientBridge", "This API is incompatible with the sdk:onSendJsonMessage method error royle + " + v4.getMessage());
        }
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onPluginReadyStringintSurfaceTextureMethod.init(this.wrapper, null, "onPluginReady", new Class[]{String.class, Integer.TYPE, SurfaceTexture.class});
        this.onPluginDestroyStringintMethod.init(this.wrapper, null, "onPluginDestroy", new Class[]{String.class, Integer.TYPE});
        this.onSendJsonMessageStringMethod.init(this.wrapper, null, "onSendJsonMessage", new Class[]{String.class});
        this.onPluginTouchStringintStringMethod.init(this.wrapper, null, "onPluginTouch", new Class[]{String.class, Integer.TYPE, String.class});
        this.onPluginTouchStringintMotionEventMethod.init(this.wrapper, null, "onPluginTouch", new Class[]{String.class, Integer.TYPE, MotionEvent.class});
    }
}

