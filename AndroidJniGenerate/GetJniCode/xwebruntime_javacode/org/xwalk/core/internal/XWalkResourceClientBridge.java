package org.xwalk.core.internal;

import android.net.http.SslError;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import java.io.InputStream;
import java.util.Map;

public class XWalkResourceClientBridge extends XWalkResourceClientInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod createXWalkWebResourceResponseStringStringInputStreamMethod;
    private ReflectMethod createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod;
    private ReflectMethod doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod;
    private ReflectMethod onDocumentLoadedInFrameXWalkViewInternallongMethod;
    private ReflectMethod onLoadFinishedXWalkViewInternalStringMethod;
    private ReflectMethod onLoadResourceXWalkViewInternalStringMethod;
    private ReflectMethod onLoadStartedXWalkViewInternalStringMethod;
    private ReflectMethod onProgressChangedXWalkViewInternalintMethod;
    private ReflectMethod onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod;
    private ReflectMethod onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod;
    private ReflectMethod onReceivedHttpErrorXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod;
    private ReflectMethod onReceivedLoadErrorXWalkViewInternalintStringStringMethod;
    private ReflectMethod onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod;
    private ReflectMethod onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod;
    private ReflectMethod shouldInterceptLoadRequestXWalkViewInternalStringMethod;
    private ReflectMethod shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod;
    private ReflectMethod shouldOverrideUrlLoadingXWalkViewInternalStringMethod;
    private Object wrapper;

    public XWalkResourceClientBridge(XWalkViewBridge arg5, Object arg6) {
        super(((XWalkViewInternal)arg5));
        this.onDocumentLoadedInFrameXWalkViewInternallongMethod = new ReflectMethod(null, "onDocumentLoadedInFrame", new Class[0]);
        this.onLoadStartedXWalkViewInternalStringMethod = new ReflectMethod(null, "onLoadStarted", new Class[0]);
        this.onLoadFinishedXWalkViewInternalStringMethod = new ReflectMethod(null, "onLoadFinished", new Class[0]);
        this.onProgressChangedXWalkViewInternalintMethod = new ReflectMethod(null, "onProgressChanged", new Class[0]);
        this.shouldInterceptLoadRequestXWalkViewInternalStringMethod = new ReflectMethod(null, "shouldInterceptLoadRequest", new Class[0]);
        this.shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod = new ReflectMethod(null, "shouldInterceptLoadRequest", new Class[0]);
        this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod = new ReflectMethod(null, "onReceivedLoadError", new Class[0]);
        this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod = new ReflectMethod(null, "shouldOverrideUrlLoading", new Class[0]);
        this.onLoadResourceXWalkViewInternalStringMethod = new ReflectMethod(null, "onLoadResource", new Class[0]);
        this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod = new ReflectMethod(null, "onReceivedSslError", new Class[0]);
        this.onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod = new ReflectMethod(null, "onReceivedClientCertRequest", new Class[0]);
        this.onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod = new ReflectMethod(null, "onReceivedResponseHeaders", new Class[0]);
        this.onReceivedHttpErrorXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod = new ReflectMethod(null, "onReceivedHttpError", new Class[0]);
        this.doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod = new ReflectMethod(null, "doUpdateVisitedHistory", new Class[0]);
        this.onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod = new ReflectMethod(null, "onReceivedHttpAuthRequest", new Class[0]);
        this.createXWalkWebResourceResponseStringStringInputStreamMethod = new ReflectMethod(null, "createXWalkWebResourceResponse", new Class[0]);
        this.createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod = new ReflectMethod(null, "createXWalkWebResourceResponse", new Class[0]);
        this.wrapper = arg6;
        this.reflectionInit();
    }

    public XWalkWebResourceResponseInternal createXWalkWebResourceResponse(String arg5, String arg6, InputStream arg7) {
        if(this.createXWalkWebResourceResponseStringStringInputStreamMethod != null) {
            if(this.createXWalkWebResourceResponseStringStringInputStreamMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.createXWalkWebResourceResponseStringStringInputStreamMethod.invoke(new Object[]{arg5, arg6, arg7}));
            }
        }

        return this.createXWalkWebResourceResponseSuper(arg5, arg6, arg7);
    }

    public XWalkWebResourceResponseInternal createXWalkWebResourceResponse(String arg5, String arg6, InputStream arg7, int arg8, String arg9, Map arg10) {
        if(this.createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod != null) {
            if(this.createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod.isNull()) {
            }
            else {
                return this.coreBridge.getBridgeObject(this.createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod.invoke(new Object[]{arg5, arg6, arg7, Integer.valueOf(arg8), arg9, arg10}));
            }
        }

        return this.createXWalkWebResourceResponseSuper(arg5, arg6, arg7, arg8, arg9, arg10);
    }

    public XWalkWebResourceResponseBridge createXWalkWebResourceResponseSuper(String arg1, String arg2, InputStream arg3) {
        XWalkWebResourceResponseBridge v1_1;
        XWalkWebResourceResponseInternal v1 = super.createXWalkWebResourceResponse(arg1, arg2, arg3);
        if(v1 == null) {
            return null;
        }

        if((v1 instanceof XWalkWebResourceResponseBridge)) {
        }
        else {
            v1_1 = new XWalkWebResourceResponseBridge(v1);
        }

        return v1_1;
    }

    public XWalkWebResourceResponseBridge createXWalkWebResourceResponseSuper(String arg1, String arg2, InputStream arg3, int arg4, String arg5, Map arg6) {
        XWalkWebResourceResponseInternal v1 = super.createXWalkWebResourceResponse(arg1, arg2, arg3, arg4, arg5, arg6);
        if(v1 == null) {
            return null;
        }

        if((v1 instanceof XWalkWebResourceResponseBridge)) {
        }
        else {
            XWalkWebResourceResponseBridge v1_1 = new XWalkWebResourceResponseBridge(v1);
        }

        return ((XWalkWebResourceResponseBridge)v1);
    }

    public void doUpdateVisitedHistory(XWalkViewBridge arg5, String arg6, boolean arg7) {
        if(this.doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod == null || (this.doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod.isNull())) {
            this.doUpdateVisitedHistorySuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v1[2] = Boolean.valueOf(arg7);
            v0.invoke(v1);
        }
    }

    public void doUpdateVisitedHistory(XWalkViewInternal arg2, String arg3, boolean arg4) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.doUpdateVisitedHistory(((XWalkViewBridge)arg2), arg3, arg4);
        }
        else {
            super.doUpdateVisitedHistory(arg2, arg3, arg4);
        }
    }

    public void doUpdateVisitedHistorySuper(XWalkViewBridge arg1, String arg2, boolean arg3) {
        super.doUpdateVisitedHistory(((XWalkViewInternal)arg1), arg2, arg3);
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    public void onDocumentLoadedInFrame(XWalkViewBridge arg5, long arg6) {
        if(this.onDocumentLoadedInFrameXWalkViewInternallongMethod == null || (this.onDocumentLoadedInFrameXWalkViewInternallongMethod.isNull())) {
            this.onDocumentLoadedInFrameSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onDocumentLoadedInFrameXWalkViewInternallongMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = Long.valueOf(arg6);
            v0.invoke(v1);
        }
    }

    public void onDocumentLoadedInFrame(XWalkViewInternal arg2, long arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onDocumentLoadedInFrame(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onDocumentLoadedInFrame(arg2, arg3);
        }
    }

    public void onDocumentLoadedInFrameSuper(XWalkViewBridge arg1, long arg2) {
        super.onDocumentLoadedInFrame(((XWalkViewInternal)arg1), arg2);
    }

    public void onLoadFinished(XWalkViewBridge arg5, String arg6) {
        if(this.onLoadFinishedXWalkViewInternalStringMethod == null || (this.onLoadFinishedXWalkViewInternalStringMethod.isNull())) {
            this.onLoadFinishedSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onLoadFinishedXWalkViewInternalStringMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v0.invoke(v1);
        }
    }

    public void onLoadFinished(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onLoadFinished(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onLoadFinished(arg2, arg3);
        }
    }

    public void onLoadFinishedSuper(XWalkViewBridge arg1, String arg2) {
        super.onLoadFinished(((XWalkViewInternal)arg1), arg2);
    }

    public void onLoadResource(XWalkViewBridge arg5, String arg6) {
        if(this.onLoadResourceXWalkViewInternalStringMethod == null || (this.onLoadResourceXWalkViewInternalStringMethod.isNull())) {
            this.onLoadResourceSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onLoadResourceXWalkViewInternalStringMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v0.invoke(v1);
        }
    }

    public void onLoadResource(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onLoadResource(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onLoadResource(arg2, arg3);
        }
    }

    public void onLoadResourceSuper(XWalkViewBridge arg1, String arg2) {
        super.onLoadResource(((XWalkViewInternal)arg1), arg2);
    }

    public void onLoadStarted(XWalkViewBridge arg5, String arg6) {
        if(this.onLoadStartedXWalkViewInternalStringMethod == null || (this.onLoadStartedXWalkViewInternalStringMethod.isNull())) {
            this.onLoadStartedSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onLoadStartedXWalkViewInternalStringMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v0.invoke(v1);
        }
    }

    public void onLoadStarted(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onLoadStarted(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onLoadStarted(arg2, arg3);
        }
    }

    public void onLoadStartedSuper(XWalkViewBridge arg1, String arg2) {
        super.onLoadStarted(((XWalkViewInternal)arg1), arg2);
    }

    public void onProgressChanged(XWalkViewBridge arg5, int arg6) {
        if(this.onProgressChangedXWalkViewInternalintMethod == null || (this.onProgressChangedXWalkViewInternalintMethod.isNull())) {
            this.onProgressChangedSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onProgressChangedXWalkViewInternalintMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = Integer.valueOf(arg6);
            v0.invoke(v1);
        }
    }

    public void onProgressChanged(XWalkViewInternal arg2, int arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onProgressChanged(((XWalkViewBridge)arg2), arg3);
        }
        else {
            super.onProgressChanged(arg2, arg3);
        }
    }

    public void onProgressChangedSuper(XWalkViewBridge arg1, int arg2) {
        super.onProgressChanged(((XWalkViewInternal)arg1), arg2);
    }

    public void onReceivedClientCertRequest(XWalkViewBridge arg5, ClientCertRequestHandlerBridge arg6) {
        if(this.onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod == null || (this.onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod.isNull())) {
            this.onReceivedClientCertRequestSuper(arg5, arg6);
        }
        else {
            ReflectMethod v0 = this.onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod;
            Object[] v1 = new Object[2];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            if((arg6 instanceof ClientCertRequestHandlerBridge)) {
            }
            else {
                arg6 = new ClientCertRequestHandlerBridge(((ClientCertRequestHandlerInternal)arg6));
            }

            v1[1] = arg6.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onReceivedClientCertRequest(XWalkViewInternal arg2, ClientCertRequestInternal arg3) {
        ClientCertRequestHandlerBridge v3;
        if((arg2 instanceof XWalkViewBridge)) {
            if((arg3 instanceof ClientCertRequestHandlerBridge)) {
            }
            else {
                v3 = new ClientCertRequestHandlerBridge(((ClientCertRequestHandlerInternal)arg3));
            }

            this.onReceivedClientCertRequest(((XWalkViewBridge)arg2), v3);
        }
        else {
            super.onReceivedClientCertRequest(arg2, arg3);
        }
    }

    public void onReceivedClientCertRequestSuper(XWalkViewBridge arg1, ClientCertRequestHandlerBridge arg2) {
        super.onReceivedClientCertRequest(((XWalkViewInternal)arg1), ((ClientCertRequestInternal)arg2));
    }

    public void onReceivedHttpAuthRequest(XWalkViewBridge arg5, XWalkHttpAuthHandlerBridge arg6, String arg7, String arg8) {
        if(this.onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod == null || (this.onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod.isNull())) {
            this.onReceivedHttpAuthRequestSuper(arg5, arg6, arg7, arg8);
        }
        else {
            ReflectMethod v0 = this.onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod;
            Object[] v1 = new Object[4];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            if((arg6 instanceof XWalkHttpAuthHandlerBridge)) {
            }
            else {
                arg6 = new XWalkHttpAuthHandlerBridge(((XWalkHttpAuthHandlerInternal)arg6));
            }

            v1[1] = arg6.getWrapper();
            v1[2] = arg7;
            v1[3] = arg8;
            v0.invoke(v1);
        }
    }

    public void onReceivedHttpAuthRequest(XWalkViewInternal arg2, XWalkHttpAuthHandlerInternal arg3, String arg4, String arg5) {
        XWalkHttpAuthHandlerBridge v3;
        if((arg2 instanceof XWalkViewBridge)) {
            if((arg3 instanceof XWalkHttpAuthHandlerBridge)) {
            }
            else {
                v3 = new XWalkHttpAuthHandlerBridge(arg3);
            }

            this.onReceivedHttpAuthRequest(((XWalkViewBridge)arg2), v3, arg4, arg5);
        }
        else {
            super.onReceivedHttpAuthRequest(arg2, arg3, arg4, arg5);
        }
    }

    public void onReceivedHttpAuthRequestSuper(XWalkViewBridge arg1, XWalkHttpAuthHandlerBridge arg2, String arg3, String arg4) {
        super.onReceivedHttpAuthRequest(((XWalkViewInternal)arg1), ((XWalkHttpAuthHandlerInternal)arg2), arg3, arg4);
    }

    public void onReceivedHttpError(XWalkViewBridge arg5, XWalkWebResourceRequestHandlerBridge arg6, XWalkWebResourceResponseBridge arg7) {
        if(this.onReceivedHttpErrorXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod == null || (this.onReceivedHttpErrorXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod.isNull())) {
            this.onReceivedHttpErrorSuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.onReceivedHttpErrorXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            if((arg6 instanceof XWalkWebResourceRequestHandlerBridge)) {
            }
            else {
                arg6 = new XWalkWebResourceRequestHandlerBridge(((XWalkWebResourceRequestHandlerInternal)arg6));
            }

            v1[1] = arg6.getWrapper();
            int v5 = 2;
            XWalkWebResourceResponseBridge v6 = (arg7 instanceof XWalkWebResourceResponseBridge) ? arg7 : new XWalkWebResourceResponseBridge(((XWalkWebResourceResponseInternal)arg7));
            v1[v5] = v6.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onReceivedHttpError(XWalkViewInternal arg2, XWalkWebResourceRequestInternal arg3, XWalkWebResourceResponseInternal arg4) {
        XWalkWebResourceResponseBridge v4;
        XWalkWebResourceRequestHandlerBridge v3;
        if((arg2 instanceof XWalkViewBridge)) {
            if((arg3 instanceof XWalkWebResourceRequestHandlerBridge)) {
            }
            else {
                v3 = new XWalkWebResourceRequestHandlerBridge(((XWalkWebResourceRequestHandlerInternal)arg3));
            }

            if((arg4 instanceof XWalkWebResourceResponseBridge)) {
            }
            else {
                v4 = new XWalkWebResourceResponseBridge(arg4);
            }

            this.onReceivedHttpError(((XWalkViewBridge)arg2), v3, v4);
        }
        else {
            super.onReceivedHttpError(arg2, arg3, arg4);
        }
    }

    public void onReceivedHttpErrorSuper(XWalkViewBridge arg1, XWalkWebResourceRequestHandlerBridge arg2, XWalkWebResourceResponseBridge arg3) {
        super.onReceivedHttpError(((XWalkViewInternal)arg1), ((XWalkWebResourceRequestInternal)arg2), ((XWalkWebResourceResponseInternal)arg3));
    }

    public void onReceivedLoadError(XWalkViewBridge arg5, int arg6, String arg7, String arg8) {
        if(this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod == null || (this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod.isNull())) {
            this.onReceivedLoadErrorSuper(arg5, arg6, arg7, arg8);
        }
        else {
            ReflectMethod v0 = this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod;
            Object[] v1 = new Object[4];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = Integer.valueOf(arg6);
            v1[2] = arg7;
            v1[3] = arg8;
            v0.invoke(v1);
        }
    }

    public void onReceivedLoadError(XWalkViewInternal arg2, int arg3, String arg4, String arg5) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onReceivedLoadError(((XWalkViewBridge)arg2), arg3, arg4, arg5);
        }
        else {
            super.onReceivedLoadError(arg2, arg3, arg4, arg5);
        }
    }

    public void onReceivedLoadErrorSuper(XWalkViewBridge arg1, int arg2, String arg3, String arg4) {
        super.onReceivedLoadError(((XWalkViewInternal)arg1), arg2, arg3, arg4);
    }

    public void onReceivedResponseHeaders(XWalkViewBridge arg5, XWalkWebResourceRequestHandlerBridge arg6, XWalkWebResourceResponseBridge arg7) {
        if(this.onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod == null || (this.onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod.isNull())) {
            this.onReceivedResponseHeadersSuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            if((arg6 instanceof XWalkWebResourceRequestHandlerBridge)) {
            }
            else {
                arg6 = new XWalkWebResourceRequestHandlerBridge(((XWalkWebResourceRequestHandlerInternal)arg6));
            }

            v1[1] = arg6.getWrapper();
            int v5 = 2;
            XWalkWebResourceResponseBridge v6 = (arg7 instanceof XWalkWebResourceResponseBridge) ? arg7 : new XWalkWebResourceResponseBridge(((XWalkWebResourceResponseInternal)arg7));
            v1[v5] = v6.getWrapper();
            v0.invoke(v1);
        }
    }

    public void onReceivedResponseHeaders(XWalkViewInternal arg2, XWalkWebResourceRequestInternal arg3, XWalkWebResourceResponseInternal arg4) {
        XWalkWebResourceResponseBridge v4;
        XWalkWebResourceRequestHandlerBridge v3;
        if((arg2 instanceof XWalkViewBridge)) {
            if((arg3 instanceof XWalkWebResourceRequestHandlerBridge)) {
            }
            else {
                v3 = new XWalkWebResourceRequestHandlerBridge(((XWalkWebResourceRequestHandlerInternal)arg3));
            }

            if((arg4 instanceof XWalkWebResourceResponseBridge)) {
            }
            else {
                v4 = new XWalkWebResourceResponseBridge(arg4);
            }

            this.onReceivedResponseHeaders(((XWalkViewBridge)arg2), v3, v4);
        }
        else {
            super.onReceivedResponseHeaders(arg2, arg3, arg4);
        }
    }

    public void onReceivedResponseHeadersSuper(XWalkViewBridge arg1, XWalkWebResourceRequestHandlerBridge arg2, XWalkWebResourceResponseBridge arg3) {
        super.onReceivedResponseHeaders(((XWalkViewInternal)arg1), ((XWalkWebResourceRequestInternal)arg2), ((XWalkWebResourceResponseInternal)arg3));
    }

    public void onReceivedSslError(XWalkViewBridge arg5, ValueCallback arg6, SslError arg7) {
        if(this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod == null || (this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod.isNull())) {
            this.onReceivedSslErrorSuper(arg5, arg6, arg7);
        }
        else {
            ReflectMethod v0 = this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod;
            Object[] v1 = new Object[3];
            if((arg5 instanceof XWalkViewBridge)) {
            }
            else {
                arg5 = null;
            }

            v1[0] = arg5.getWrapper();
            v1[1] = arg6;
            v1[2] = arg7;
            v0.invoke(v1);
        }
    }

    public void onReceivedSslError(XWalkViewInternal arg2, ValueCallback arg3, SslError arg4) {
        if((arg2 instanceof XWalkViewBridge)) {
            this.onReceivedSslError(((XWalkViewBridge)arg2), arg3, arg4);
        }
        else {
            super.onReceivedSslError(arg2, arg3, arg4);
        }
    }

    public void onReceivedSslErrorSuper(XWalkViewBridge arg1, ValueCallback arg2, SslError arg3) {
        super.onReceivedSslError(((XWalkViewInternal)arg1), arg2, arg3);
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        this.onDocumentLoadedInFrameXWalkViewInternallongMethod.init(this.wrapper, null, "onDocumentLoadedInFrame", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Long.TYPE});
        this.onLoadStartedXWalkViewInternalStringMethod.init(this.wrapper, null, "onLoadStarted", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
        this.onLoadFinishedXWalkViewInternalStringMethod.init(this.wrapper, null, "onLoadFinished", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
        this.onProgressChangedXWalkViewInternalintMethod.init(this.wrapper, null, "onProgressChanged", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Integer.TYPE});
        this.shouldInterceptLoadRequestXWalkViewInternalStringMethod.init(this.wrapper, null, "shouldInterceptLoadRequest", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
        this.shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod.init(this.wrapper, null, "shouldInterceptLoadRequest", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), this.coreBridge.getWrapperClass("XWalkWebResourceRequest")});
        this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod.init(this.wrapper, null, "onReceivedLoadError", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), Integer.TYPE, String.class, String.class});
        this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod.init(this.wrapper, null, "shouldOverrideUrlLoading", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
        this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod.init(this.wrapper, null, "onReceivedSslError", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), ValueCallback.class, SslError.class});
        this.onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod.init(this.wrapper, null, "onReceivedClientCertRequest", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), this.coreBridge.getWrapperClass("ClientCertRequest")});
        this.onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod.init(this.wrapper, null, "onReceivedResponseHeaders", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), this.coreBridge.getWrapperClass("XWalkWebResourceRequest"), this.coreBridge.getWrapperClass("XWalkWebResourceResponse")});
        this.onReceivedHttpErrorXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod.init(this.wrapper, null, "onReceivedHttpError", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), this.coreBridge.getWrapperClass("XWalkWebResourceRequest"), this.coreBridge.getWrapperClass("XWalkWebResourceResponse")});
        this.doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod.init(this.wrapper, null, "doUpdateVisitedHistory", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class, Boolean.TYPE});
        this.onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod.init(this.wrapper, null, "onReceivedHttpAuthRequest", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), this.coreBridge.getWrapperClass("XWalkHttpAuthHandler"), String.class, String.class});
        this.createXWalkWebResourceResponseStringStringInputStreamMethod.init(this.wrapper, null, "createXWalkWebResourceResponse", new Class[]{String.class, String.class, InputStream.class});
        this.createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod.init(this.wrapper, null, "createXWalkWebResourceResponse", new Class[]{String.class, String.class, InputStream.class, Integer.TYPE, String.class, Map.class});
        this.onLoadResourceXWalkViewInternalStringMethod.init(this.wrapper, null, "onLoadResource", new Class[]{this.coreBridge.getWrapperClass("XWalkView"), String.class});
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewBridge arg5, String arg6) {
        if(this.shouldInterceptLoadRequestXWalkViewInternalStringMethod != null) {
            if(this.shouldInterceptLoadRequestXWalkViewInternalStringMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.shouldInterceptLoadRequestXWalkViewInternalStringMethod;
                Object[] v1 = new Object[2];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = arg6;
                return v0.invoke(v1);
            }
        }

        return this.shouldInterceptLoadRequestSuper(arg5, arg6);
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            return this.shouldInterceptLoadRequest(((XWalkViewBridge)arg2), arg3);
        }

        return super.shouldInterceptLoadRequest(arg2, arg3);
    }

    public XWalkWebResourceResponseInternal shouldInterceptLoadRequest(XWalkViewBridge arg6, XWalkWebResourceRequestHandlerBridge arg7) {
        if(this.shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod != null) {
            if(this.shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod.isNull()) {
            }
            else {
                XWalkCoreBridge v0 = this.coreBridge;
                ReflectMethod v1 = this.shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod;
                Object[] v2 = new Object[2];
                if((arg6 instanceof XWalkViewBridge)) {
                }
                else {
                    arg6 = null;
                }

                v2[0] = arg6.getWrapper();
                if((arg7 instanceof XWalkWebResourceRequestHandlerBridge)) {
                }
                else {
                    arg7 = new XWalkWebResourceRequestHandlerBridge(((XWalkWebResourceRequestHandlerInternal)arg7));
                }

                v2[1] = arg7.getWrapper();
                return v0.getBridgeObject(v1.invoke(v2));
            }
        }

        return this.shouldInterceptLoadRequestSuper(arg6, arg7);
    }

    public XWalkWebResourceResponseInternal shouldInterceptLoadRequest(XWalkViewInternal arg2, XWalkWebResourceRequestInternal arg3) {
        XWalkWebResourceRequestHandlerBridge v3;
        if((arg2 instanceof XWalkViewBridge)) {
            if((arg3 instanceof XWalkWebResourceRequestHandlerBridge)) {
            }
            else {
                v3 = new XWalkWebResourceRequestHandlerBridge(((XWalkWebResourceRequestHandlerInternal)arg3));
            }

            return this.shouldInterceptLoadRequest(((XWalkViewBridge)arg2), v3);
        }

        return super.shouldInterceptLoadRequest(arg2, arg3);
    }

    public WebResourceResponse shouldInterceptLoadRequestSuper(XWalkViewBridge arg1, String arg2) {
        WebResourceResponse v1 = super.shouldInterceptLoadRequest(((XWalkViewInternal)arg1), arg2);
        if(v1 == null) {
            return null;
        }

        return v1;
    }

    public XWalkWebResourceResponseBridge shouldInterceptLoadRequestSuper(XWalkViewBridge arg1, XWalkWebResourceRequestHandlerBridge arg2) {
        XWalkWebResourceResponseBridge v1_1;
        XWalkWebResourceResponseInternal v1 = super.shouldInterceptLoadRequest(((XWalkViewInternal)arg1), ((XWalkWebResourceRequestInternal)arg2));
        if(v1 == null) {
            return null;
        }

        if((v1 instanceof XWalkWebResourceResponseBridge)) {
        }
        else {
            v1_1 = new XWalkWebResourceResponseBridge(v1);
        }

        return v1_1;
    }

    public boolean shouldOverrideUrlLoading(XWalkViewBridge arg5, String arg6) {
        if(this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod != null) {
            if(this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod.isNull()) {
            }
            else {
                ReflectMethod v0 = this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod;
                Object[] v1 = new Object[2];
                if((arg5 instanceof XWalkViewBridge)) {
                }
                else {
                    arg5 = null;
                }

                v1[0] = arg5.getWrapper();
                v1[1] = arg6;
                return v0.invoke(v1).booleanValue();
            }
        }

        return this.shouldOverrideUrlLoadingSuper(arg5, arg6);
    }

    public boolean shouldOverrideUrlLoading(XWalkViewInternal arg2, String arg3) {
        if((arg2 instanceof XWalkViewBridge)) {
            return this.shouldOverrideUrlLoading(((XWalkViewBridge)arg2), arg3);
        }

        return super.shouldOverrideUrlLoading(arg2, arg3);
    }

    public boolean shouldOverrideUrlLoadingSuper(XWalkViewBridge arg1, String arg2) {
        return super.shouldOverrideUrlLoading(((XWalkViewInternal)arg1), arg2);
    }
}

