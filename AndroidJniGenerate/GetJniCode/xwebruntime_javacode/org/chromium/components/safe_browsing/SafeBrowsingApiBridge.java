package org.chromium.components.safe_browsing;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="safe_browsing") public final class SafeBrowsingApiBridge {
    private static final String TAG = "ApiBridge";
    private static Class sHandler;

    private SafeBrowsingApiBridge() {
        super();
    }

    static void access$000(long arg0, int arg2, String arg3, long arg4) {
        SafeBrowsingApiBridge.nativeOnUrlCheckDone(arg0, arg2, arg3, arg4);
    }

    @CalledByNative private static SafeBrowsingApiHandler create() {
        Context v1;
        Object v2_1;
        SafeBrowsingApiHandler v0 = null;
        try {
            v2_1 = SafeBrowsingApiBridge.sHandler.getDeclaredConstructor().newInstance();
            v1 = ContextUtils.getApplicationContext();
        }
        catch(InvocationTargetException v2) {
            Log.e("ApiBridge", "Failed to init handler: " + ((Exception)v2).getMessage(), new Object[((int)v1)]);
            return v0;
        }

        if(((SafeBrowsingApiHandler)v2_1).init(v1, new Observer() {
            public void onUrlCheckDone(long arg1, int arg3, String arg4, long arg5) {
                SafeBrowsingApiBridge.nativeOnUrlCheckDone(arg1, arg3, arg4, arg5);
            }
        })) {
            Object v0_1 = v2_1;
        }

        return v0;
    }

    private static native void nativeOnUrlCheckDone(long arg0, int arg1, String arg2, long arg3) {
    }

    public static void setSafeBrowsingHandlerType(Class arg0) {
        SafeBrowsingApiBridge.sHandler = arg0;
    }

    @CalledByNative private static void startUriLookup(SafeBrowsingApiHandler arg0, long arg1, String arg3, int[] arg4) {
        arg0.startUriLookup(arg1, arg3, arg4);
        Log.d("ApiBridge", "Done starting request");
    }
}

