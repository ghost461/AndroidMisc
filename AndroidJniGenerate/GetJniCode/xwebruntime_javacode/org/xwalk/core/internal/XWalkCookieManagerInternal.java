package org.xwalk.core.internal;

import java.net.MalformedURLException;
import java.net.URL;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") @XWalkAPI(createExternally=true) public class XWalkCookieManagerInternal {
    private static final String TAG = "XWalkCookieManager";

    public XWalkCookieManagerInternal() {
        super();
    }

    @XWalkAPI public boolean acceptCookie() {
        return this.nativeAcceptCookie();
    }

    @XWalkAPI public boolean allowFileSchemeCookies() {
        return this.nativeAllowFileSchemeCookies();
    }

    @XWalkAPI public void flushCookieStore() {
        this.nativeFlushCookieStore();
    }

    @XWalkAPI public String getCookie(String arg4) {
        String v0 = null;
        try {
            arg4 = this.nativeGetCookie(new URL(arg4).toString());
            if(arg4 == null) {
            }
            else if(!arg4.trim().isEmpty()) {
                return arg4;
            }
        }
        catch(MalformedURLException v4) {
            Log.e("XWalkCookieManager", "Unable to get cookies due to invalid URL", ((Throwable)v4));
            return v0;
        }

        return v0;
    }

    @XWalkAPI public boolean hasCookies() {
        return this.nativeHasCookies();
    }

    private native boolean nativeAcceptCookie() {
    }

    private native boolean nativeAllowFileSchemeCookies() {
    }

    private native void nativeFlushCookieStore() {
    }

    private native String nativeGetCookie(String arg1) {
    }

    private native boolean nativeHasCookies() {
    }

    private native void nativeRemoveAllCookie() {
    }

    private native void nativeRemoveExpiredCookie() {
    }

    private native void nativeRemoveSessionCookie() {
    }

    private native void nativeSetAcceptCookie(boolean arg1) {
    }

    private native void nativeSetAcceptFileSchemeCookies(boolean arg1) {
    }

    private native void nativeSetCookie(String arg1, String arg2) {
    }

    @XWalkAPI public void removeAllCookie() {
        this.nativeRemoveAllCookie();
    }

    @XWalkAPI public void removeExpiredCookie() {
        this.nativeRemoveExpiredCookie();
    }

    @XWalkAPI public void removeSessionCookie() {
        this.nativeRemoveSessionCookie();
    }

    @XWalkAPI public void setAcceptCookie(boolean arg1) {
        this.nativeSetAcceptCookie(arg1);
    }

    @XWalkAPI public void setAcceptFileSchemeCookies(boolean arg1) {
        this.nativeSetAcceptFileSchemeCookies(arg1);
    }

    @XWalkAPI public void setCookie(String arg2, String arg3) {
        try {
            this.nativeSetCookie(new URL(arg2).toString(), arg3);
        }
        catch(MalformedURLException v2) {
            Log.e("XWalkCookieManager", "Not setting cookie due to invalid URL", ((Throwable)v2));
        }
    }
}

