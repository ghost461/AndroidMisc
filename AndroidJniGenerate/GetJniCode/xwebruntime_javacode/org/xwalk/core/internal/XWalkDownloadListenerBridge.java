package org.xwalk.core.internal;

import android.content.Context;

public class XWalkDownloadListenerBridge extends XWalkDownloadListenerInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod onDownloadStartStringStringStringStringlongMethod;
    private Object wrapper;

    public XWalkDownloadListenerBridge(Context arg4, Object arg5) {
        super(arg4);
        this.onDownloadStartStringStringStringStringlongMethod = new ReflectMethod(null, "onDownloadStart", new Class[0]);
        this.wrapper = arg5;
        this.reflectionInit();
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onDownloadStart(String arg4, String arg5, String arg6, String arg7, long arg8) {
        this.onDownloadStartStringStringStringStringlongMethod.invoke(new Object[]{arg4, arg5, arg6, arg7, Long.valueOf(arg8)});
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onDownloadStartStringStringStringStringlongMethod.init(this.wrapper, null, "onDownloadStart", new Class[]{String.class, String.class, String.class, String.class, Long.TYPE});
    }
}

