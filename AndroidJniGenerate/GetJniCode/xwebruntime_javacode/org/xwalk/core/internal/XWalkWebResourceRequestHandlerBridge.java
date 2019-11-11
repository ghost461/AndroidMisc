package org.xwalk.core.internal;

import android.net.Uri;
import java.util.Map;

public class XWalkWebResourceRequestHandlerBridge extends XWalkWebResourceRequestHandlerInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod getMethodMethod;
    private ReflectMethod getRequestHeadersMethod;
    private ReflectMethod getUrlMethod;
    private ReflectMethod hasGestureMethod;
    private XWalkWebResourceRequestHandlerInternal internal;
    private ReflectMethod isForMainFrameMethod;
    private Object wrapper;

    XWalkWebResourceRequestHandlerBridge(XWalkWebResourceRequestHandlerInternal arg6) {
        super();
        this.getUrlMethod = new ReflectMethod(null, "getUrl", new Class[0]);
        this.isForMainFrameMethod = new ReflectMethod(null, "isForMainFrame", new Class[0]);
        this.hasGestureMethod = new ReflectMethod(null, "hasGesture", new Class[0]);
        this.getMethodMethod = new ReflectMethod(null, "getMethod", new Class[0]);
        this.getRequestHeadersMethod = new ReflectMethod(null, "getRequestHeaders", new Class[0]);
        this.internal = arg6;
        this.reflectionInit();
    }

    public String getMethod() {
        if(this.getMethodMethod != null) {
            if(this.getMethodMethod.isNull()) {
            }
            else {
                return this.getMethodMethod.invoke(new Object[0]);
            }
        }

        return this.getMethodSuper();
    }

    public String getMethodSuper() {
        String v0 = this.internal == null ? super.getMethod() : this.internal.getMethod();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Map getRequestHeaders() {
        if(this.getRequestHeadersMethod != null) {
            if(this.getRequestHeadersMethod.isNull()) {
            }
            else {
                return this.getRequestHeadersMethod.invoke(new Object[0]);
            }
        }

        return this.getRequestHeadersSuper();
    }

    public Map getRequestHeadersSuper() {
        Map v0 = this.internal == null ? super.getRequestHeaders() : this.internal.getRequestHeaders();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Uri getUrl() {
        if(this.getUrlMethod != null) {
            if(this.getUrlMethod.isNull()) {
            }
            else {
                return this.getUrlMethod.invoke(new Object[0]);
            }
        }

        return this.getUrlSuper();
    }

    public Uri getUrlSuper() {
        Uri v0 = this.internal == null ? super.getUrl() : this.internal.getUrl();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public boolean hasGesture() {
        if(this.hasGestureMethod != null) {
            if(this.hasGestureMethod.isNull()) {
            }
            else {
                return this.hasGestureMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.hasGestureSuper();
    }

    public boolean hasGestureSuper() {
        boolean v0 = this.internal == null ? super.hasGesture() : this.internal.hasGesture();
        return v0;
    }

    public boolean isForMainFrame() {
        if(this.isForMainFrameMethod != null) {
            if(this.isForMainFrameMethod.isNull()) {
            }
            else {
                return this.isForMainFrameMethod.invoke(new Object[0]).booleanValue();
            }
        }

        return this.isForMainFrameSuper();
    }

    public boolean isForMainFrameSuper() {
        boolean v0 = this.internal == null ? super.isForMainFrame() : this.internal.isForMainFrame();
        return v0;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkWebResourceRequestHandler"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.getUrlMethod.init(this.wrapper, null, "getUrl", new Class[0]);
        this.isForMainFrameMethod.init(this.wrapper, null, "isForMainFrame", new Class[0]);
        this.hasGestureMethod.init(this.wrapper, null, "hasGesture", new Class[0]);
        this.getMethodMethod.init(this.wrapper, null, "getMethod", new Class[0]);
        this.getRequestHeadersMethod.init(this.wrapper, null, "getRequestHeaders", new Class[0]);
    }
}

