package org.xwalk.core.internal;

import java.util.HashMap;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk") public abstract class XWalkContentsIoThreadClient {
    public XWalkContentsIoThreadClient() {
        super();
    }

    @CalledByNative public abstract int getCacheMode();

    @CalledByNative public abstract void newLoginRequest(String arg1, String arg2, String arg3);

    @CalledByNative protected void onReceivedResponseHeaders(String arg13, boolean arg14, boolean arg15, String arg16, String[] arg17, String[] arg18, String arg19, String arg20, int arg21, String arg22, String[] arg23, String[] arg24) {
        String[] v0 = arg17;
        String[] v1 = arg23;
        WebResourceRequestInner v2 = new WebResourceRequestInner();
        v2.url = arg13;
        v2.isMainFrame = arg14;
        v2.hasUserGesture = arg15;
        v2.method = arg16;
        v2.requestHeaders = new HashMap(v0.length);
        int v3 = 0;
        int v4;
        for(v4 = 0; v4 < v0.length; ++v4) {
            v2.requestHeaders.put(v0[v4], arg18[v4]);
        }

        HashMap v11 = new HashMap(v1.length);
        while(v3 < v1.length) {
            if(!((Map)v11).containsKey(v1[v3])) {
                ((Map)v11).put(v1[v3], arg24[v3]);
            }
            else if(!arg24[v3].isEmpty()) {
                Object v0_1 = ((Map)v11).get(v1[v3]);
                if(!((String)v0_1).isEmpty()) {
                    String v0_2 = (((String)v0_1)) + ", ";
                }

                String v4_2 = v1[v3];
                ((Map)v11).put(v4_2, (((String)v0_1)) + arg24[v3]);
            }

            ++v3;
        }

        this.onReceivedResponseHeaders(v2, new XWalkWebResourceResponseInternal(arg19, arg20, null, arg21, arg22, ((Map)v11)));
    }

    public abstract void onReceivedResponseHeaders(WebResourceRequestInner arg1, XWalkWebResourceResponseInternal arg2);

    @CalledByNative public abstract boolean shouldBlockContentUrls();

    @CalledByNative public abstract boolean shouldBlockFileUrls();

    @CalledByNative public abstract boolean shouldBlockNetworkLoads();

    @CalledByNative protected XWalkWebResourceResponseInternal shouldInterceptRequest(String arg2, boolean arg3, boolean arg4, String arg5, String[] arg6, String[] arg7) {
        WebResourceRequestInner v0 = new WebResourceRequestInner();
        v0.url = arg2;
        v0.isMainFrame = arg3;
        v0.hasUserGesture = arg4;
        v0.method = arg5;
        v0.requestHeaders = new HashMap(arg6.length);
        int v2;
        for(v2 = 0; v2 < arg6.length; ++v2) {
            v0.requestHeaders.put(arg6[v2], arg7[v2]);
        }

        return this.shouldInterceptRequest(v0);
    }

    public abstract XWalkWebResourceResponseInternal shouldInterceptRequest(WebResourceRequestInner arg1);
}

