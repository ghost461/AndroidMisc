package org.xwalk.core.internal;

import java.io.InputStream;
import java.util.Map;

public class XWalkWebResourceResponseBridge extends XWalkWebResourceResponseInternal {
    private XWalkCoreBridge coreBridge;
    private ReflectMethod getDataMethod;
    private ReflectMethod getEncodingMethod;
    private ReflectMethod getMimeTypeMethod;
    private ReflectMethod getReasonPhraseMethod;
    private ReflectMethod getResponseHeadersMethod;
    private ReflectMethod getStatusCodeMethod;
    private XWalkWebResourceResponseInternal internal;
    private ReflectMethod setDataInputStreamMethod;
    private ReflectMethod setEncodingStringMethod;
    private ReflectMethod setMimeTypeStringMethod;
    private ReflectMethod setResponseHeadersMapMethod;
    private ReflectMethod setStatusCodeAndReasonPhraseintStringMethod;
    private Object wrapper;

    XWalkWebResourceResponseBridge(XWalkWebResourceResponseInternal arg6) {
        super();
        this.setMimeTypeStringMethod = new ReflectMethod(null, "setMimeType", new Class[0]);
        this.getMimeTypeMethod = new ReflectMethod(null, "getMimeType", new Class[0]);
        this.setEncodingStringMethod = new ReflectMethod(null, "setEncoding", new Class[0]);
        this.getEncodingMethod = new ReflectMethod(null, "getEncoding", new Class[0]);
        this.setDataInputStreamMethod = new ReflectMethod(null, "setData", new Class[0]);
        this.getDataMethod = new ReflectMethod(null, "getData", new Class[0]);
        this.setStatusCodeAndReasonPhraseintStringMethod = new ReflectMethod(null, "setStatusCodeAndReasonPhrase", new Class[0]);
        this.getStatusCodeMethod = new ReflectMethod(null, "getStatusCode", new Class[0]);
        this.getReasonPhraseMethod = new ReflectMethod(null, "getReasonPhrase", new Class[0]);
        this.setResponseHeadersMapMethod = new ReflectMethod(null, "setResponseHeaders", new Class[0]);
        this.getResponseHeadersMethod = new ReflectMethod(null, "getResponseHeaders", new Class[0]);
        this.internal = arg6;
        this.reflectionInit();
    }

    public InputStream getData() {
        if(this.getDataMethod != null) {
            if(this.getDataMethod.isNull()) {
            }
            else {
                return this.getDataMethod.invoke(new Object[0]);
            }
        }

        return this.getDataSuper();
    }

    public InputStream getDataSuper() {
        InputStream v0 = this.internal == null ? super.getData() : this.internal.getData();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getEncoding() {
        if(this.getEncodingMethod != null) {
            if(this.getEncodingMethod.isNull()) {
            }
            else {
                return this.getEncodingMethod.invoke(new Object[0]);
            }
        }

        return this.getEncodingSuper();
    }

    public String getEncodingSuper() {
        String v0 = this.internal == null ? super.getEncoding() : this.internal.getEncoding();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getMimeType() {
        if(this.getMimeTypeMethod != null) {
            if(this.getMimeTypeMethod.isNull()) {
            }
            else {
                return this.getMimeTypeMethod.invoke(new Object[0]);
            }
        }

        return this.getMimeTypeSuper();
    }

    public String getMimeTypeSuper() {
        String v0 = this.internal == null ? super.getMimeType() : this.internal.getMimeType();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public String getReasonPhrase() {
        if(this.getReasonPhraseMethod != null) {
            if(this.getReasonPhraseMethod.isNull()) {
            }
            else {
                return this.getReasonPhraseMethod.invoke(new Object[0]);
            }
        }

        return this.getReasonPhraseSuper();
    }

    public String getReasonPhraseSuper() {
        String v0 = this.internal == null ? super.getReasonPhrase() : this.internal.getReasonPhrase();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public Map getResponseHeaders() {
        if(this.getResponseHeadersMethod != null) {
            if(this.getResponseHeadersMethod.isNull()) {
            }
            else {
                return this.getResponseHeadersMethod.invoke(new Object[0]);
            }
        }

        return this.getResponseHeadersSuper();
    }

    public Map getResponseHeadersSuper() {
        Map v0 = this.internal == null ? super.getResponseHeaders() : this.internal.getResponseHeaders();
        if(v0 == null) {
            return null;
        }

        return v0;
    }

    public int getStatusCode() {
        if(this.getStatusCodeMethod != null) {
            if(this.getStatusCodeMethod.isNull()) {
            }
            else {
                return this.getStatusCodeMethod.invoke(new Object[0]).intValue();
            }
        }

        return this.getStatusCodeSuper();
    }

    public int getStatusCodeSuper() {
        int v0 = this.internal == null ? super.getStatusCode() : this.internal.getStatusCode();
        return v0;
    }

    public Object getWrapper() {
        return this.wrapper;
    }

    void reflectionInit() {
        this.coreBridge = XWalkCoreBridge.getInstance();
        if(this.coreBridge == null) {
            return;
        }

        ReflectConstructor v0 = new ReflectConstructor(this.coreBridge.getWrapperClass("XWalkWebResourceResponse"), new Class[]{Object.class});
        try {
            this.wrapper = v0.newInstance(new Object[]{this});
        }
        catch(UnsupportedOperationException ) {
            return;
        }

        this.setMimeTypeStringMethod.init(this.wrapper, null, "setMimeType", new Class[]{String.class});
        this.getMimeTypeMethod.init(this.wrapper, null, "getMimeType", new Class[0]);
        this.setEncodingStringMethod.init(this.wrapper, null, "setEncoding", new Class[]{String.class});
        this.getEncodingMethod.init(this.wrapper, null, "getEncoding", new Class[0]);
        this.setDataInputStreamMethod.init(this.wrapper, null, "setData", new Class[]{InputStream.class});
        this.getDataMethod.init(this.wrapper, null, "getData", new Class[0]);
        this.setStatusCodeAndReasonPhraseintStringMethod.init(this.wrapper, null, "setStatusCodeAndReasonPhrase", new Class[]{Integer.TYPE, String.class});
        this.getStatusCodeMethod.init(this.wrapper, null, "getStatusCode", new Class[0]);
        this.getReasonPhraseMethod.init(this.wrapper, null, "getReasonPhrase", new Class[0]);
        this.setResponseHeadersMapMethod.init(this.wrapper, null, "setResponseHeaders", new Class[]{Map.class});
        this.getResponseHeadersMethod.init(this.wrapper, null, "getResponseHeaders", new Class[0]);
    }

    public void setData(InputStream arg4) {
        if(this.setDataInputStreamMethod == null || (this.setDataInputStreamMethod.isNull())) {
            this.setDataSuper(arg4);
        }
        else {
            this.setDataInputStreamMethod.invoke(new Object[]{arg4});
        }
    }

    public void setDataSuper(InputStream arg2) {
        if(this.internal == null) {
            super.setData(arg2);
        }
        else {
            this.internal.setData(arg2);
        }
    }

    public void setEncoding(String arg4) {
        if(this.setEncodingStringMethod == null || (this.setEncodingStringMethod.isNull())) {
            this.setEncodingSuper(arg4);
        }
        else {
            this.setEncodingStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setEncodingSuper(String arg2) {
        if(this.internal == null) {
            super.setEncoding(arg2);
        }
        else {
            this.internal.setEncoding(arg2);
        }
    }

    public void setMimeType(String arg4) {
        if(this.setMimeTypeStringMethod == null || (this.setMimeTypeStringMethod.isNull())) {
            this.setMimeTypeSuper(arg4);
        }
        else {
            this.setMimeTypeStringMethod.invoke(new Object[]{arg4});
        }
    }

    public void setMimeTypeSuper(String arg2) {
        if(this.internal == null) {
            super.setMimeType(arg2);
        }
        else {
            this.internal.setMimeType(arg2);
        }
    }

    public void setResponseHeaders(Map arg4) {
        if(this.setResponseHeadersMapMethod == null || (this.setResponseHeadersMapMethod.isNull())) {
            this.setResponseHeadersSuper(arg4);
        }
        else {
            this.setResponseHeadersMapMethod.invoke(new Object[]{arg4});
        }
    }

    public void setResponseHeadersSuper(Map arg2) {
        if(this.internal == null) {
            super.setResponseHeaders(arg2);
        }
        else {
            this.internal.setResponseHeaders(arg2);
        }
    }

    public void setStatusCodeAndReasonPhrase(int arg4, String arg5) {
        if(this.setStatusCodeAndReasonPhraseintStringMethod == null || (this.setStatusCodeAndReasonPhraseintStringMethod.isNull())) {
            this.setStatusCodeAndReasonPhraseSuper(arg4, arg5);
        }
        else {
            this.setStatusCodeAndReasonPhraseintStringMethod.invoke(new Object[]{Integer.valueOf(arg4), arg5});
        }
    }

    public void setStatusCodeAndReasonPhraseSuper(int arg2, String arg3) {
        if(this.internal == null) {
            super.setStatusCodeAndReasonPhrase(arg2, arg3);
        }
        else {
            this.internal.setStatusCodeAndReasonPhrase(arg2, arg3);
        }
    }
}

