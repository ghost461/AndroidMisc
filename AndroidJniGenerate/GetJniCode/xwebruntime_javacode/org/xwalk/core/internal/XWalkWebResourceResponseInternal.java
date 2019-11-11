package org.xwalk.core.internal;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") @XWalkAPI(createInternally=true) public class XWalkWebResourceResponseInternal {
    private InputStream mData;
    private String mEncoding;
    private String mMimeType;
    private String mReasonPhrase;
    private String[] mResponseHeaderNames;
    private String[] mResponseHeaderValues;
    private Map mResponseHeaders;
    private int mStatusCode;

    XWalkWebResourceResponseInternal(String arg1, String arg2, InputStream arg3, int arg4, String arg5, Map arg6) {
        this(arg1, arg2, arg3);
        this.mStatusCode = arg4;
        this.mReasonPhrase = arg5;
        this.mResponseHeaders = arg6;
    }

    XWalkWebResourceResponseInternal(String arg1, String arg2, InputStream arg3) {
        super();
        this.mMimeType = arg1;
        this.mEncoding = arg2;
        this.setData(arg3);
    }

    XWalkWebResourceResponseInternal() {
        super();
        this.mMimeType = null;
        this.mEncoding = null;
        this.mData = null;
    }

    private void fillInResponseHeaderNamesAndValuesIfNeeded() {
        if(this.mResponseHeaders != null) {
            if(this.mResponseHeaderNames != null) {
            }
            else {
                this.mResponseHeaderNames = new String[this.mResponseHeaders.size()];
                this.mResponseHeaderValues = new String[this.mResponseHeaders.size()];
                int v0 = 0;
                Iterator v1 = this.mResponseHeaders.entrySet().iterator();
                while(v1.hasNext()) {
                    Object v2 = v1.next();
                    this.mResponseHeaderNames[v0] = ((Map$Entry)v2).getKey();
                    this.mResponseHeaderValues[v0] = ((Map$Entry)v2).getValue();
                    ++v0;
                }

                return;
            }
        }
    }

    @XWalkAPI public InputStream getData() {
        return this.mData;
    }

    @CalledByNative public InputStream getDataNative() {
        return this.mData;
    }

    @XWalkAPI public String getEncoding() {
        return this.mEncoding;
    }

    @CalledByNative public String getEncodingNative() {
        return this.mEncoding;
    }

    @XWalkAPI public String getMimeType() {
        return this.mMimeType;
    }

    @CalledByNative public String getMimeTypeNative() {
        return this.mMimeType;
    }

    @XWalkAPI public String getReasonPhrase() {
        return this.mReasonPhrase;
    }

    @CalledByNative public String getReasonPhraseNative() {
        return this.mReasonPhrase;
    }

    @CalledByNative private String[] getResponseHeaderNames() {
        this.fillInResponseHeaderNamesAndValuesIfNeeded();
        return this.mResponseHeaderNames;
    }

    @CalledByNative private String[] getResponseHeaderValues() {
        this.fillInResponseHeaderNamesAndValuesIfNeeded();
        return this.mResponseHeaderValues;
    }

    @XWalkAPI public Map getResponseHeaders() {
        return this.mResponseHeaders;
    }

    @XWalkAPI public int getStatusCode() {
        return this.mStatusCode;
    }

    @CalledByNative public int getStatusCodeNative() {
        return this.mStatusCode;
    }

    @XWalkAPI public void setData(InputStream arg3) {
        if(arg3 != null && (StringBufferInputStream.class.isAssignableFrom(arg3.getClass()))) {
            throw new IllegalArgumentException("StringBufferInputStream is deprecated and must not be passed to a XWalkWebResourceResponse");
        }

        this.mData = arg3;
    }

    @XWalkAPI public void setEncoding(String arg1) {
        this.mEncoding = arg1;
    }

    @XWalkAPI public void setMimeType(String arg1) {
        this.mMimeType = arg1;
    }

    @XWalkAPI public void setResponseHeaders(Map arg1) {
        this.mResponseHeaders = arg1;
    }

    @XWalkAPI public void setStatusCodeAndReasonPhrase(int arg4, String arg5) {
        if(arg4 < 100) {
            throw new IllegalArgumentException("statusCode can\'t be less than 100.");
        }

        if(arg4 > 599) {
            throw new IllegalArgumentException("statusCode can\'t be greater than 599.");
        }

        if(arg4 > 299 && arg4 < 400) {
            throw new IllegalArgumentException("statusCode can\'t be in the [300, 399] range.");
        }

        if(arg5 == null) {
            throw new IllegalArgumentException("reasonPhrase can\'t be null.");
        }

        if(arg5.trim().isEmpty()) {
            throw new IllegalArgumentException("reasonPhrase can\'t be empty.");
        }

        int v0;
        for(v0 = 0; v0 < arg5.length(); ++v0) {
            if(arg5.charAt(v0) > 0x7F) {
                throw new IllegalArgumentException("reasonPhrase can\'t contain non-ASCII characters.");
            }
        }

        this.mStatusCode = arg4;
        this.mReasonPhrase = arg5;
    }
}

