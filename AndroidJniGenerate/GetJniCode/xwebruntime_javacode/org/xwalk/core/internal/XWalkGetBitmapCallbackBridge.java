package org.xwalk.core.internal;

import android.graphics.Bitmap;

public class XWalkGetBitmapCallbackBridge extends XWalkGetBitmapCallbackInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onFinishGetBitmapBitmapintMethod;
    private Object wrapper;

    public XWalkGetBitmapCallbackBridge(Object arg5) {
        super();
        this.onFinishGetBitmapBitmapintMethod = new ReflectMethod(null, "onFinishGetBitmap", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onFinishGetBitmap(Bitmap arg4, int arg5) {
        this.onFinishGetBitmapBitmapintMethod.invoke(new Object[]{arg4, Integer.valueOf(arg5)});
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onFinishGetBitmapBitmapintMethod.init(this.wrapper, null, "onFinishGetBitmap", new Class[]{Bitmap.class, Integer.TYPE});
    }
}

