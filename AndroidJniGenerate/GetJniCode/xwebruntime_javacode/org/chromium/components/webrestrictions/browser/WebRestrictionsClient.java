package org.chromium.components.webrestrictions.browser;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri$Builder;
import android.net.Uri;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="web_restrictions") public class WebRestrictionsClient {
    private ContentObserver mContentObserver;
    private ContentResolver mContentResolver;
    private Uri mQueryUri;
    private Uri mRequestUri;
    private static WebRestrictionsClient sMock;

    static {
    }

    WebRestrictionsClient() {
        super();
    }

    @CalledByNative private static WebRestrictionsClient create(String arg1, long arg2) {
        WebRestrictionsClient v0 = WebRestrictionsClient.sMock == null ? new WebRestrictionsClient() : WebRestrictionsClient.sMock;
        v0.init(arg1, arg2);
        return v0;
    }

    void init(String arg3, long arg4) {
        Uri v3 = new Uri$Builder().scheme("content").authority(arg3).build();
        this.mQueryUri = Uri.withAppendedPath(v3, "authorized");
        this.mRequestUri = Uri.withAppendedPath(v3, "requested");
        this.mContentResolver = ContextUtils.getApplicationContext().getContentResolver();
        this.mContentObserver = new ContentObserver(null, arg4) {
            public void onChange(boolean arg2) {
                this.onChange(arg2, null);
            }

            public void onChange(boolean arg3, Uri arg4) {
                WebRestrictionsClient.this.nativeOnWebRestrictionsChanged(this.val$nativeProvider);
            }
        };
        this.mContentResolver.registerContentObserver(v3, true, this.mContentObserver);
    }

    native void nativeOnWebRestrictionsChanged(long arg1) {
    }

    @CalledByNative void onDestroy() {
        this.mContentResolver.unregisterContentObserver(this.mContentObserver);
    }

    protected static void registerMockForTesting(WebRestrictionsClient arg0) {
        WebRestrictionsClient.sMock = arg0;
    }

    @CalledByNative boolean requestPermission(String arg4) {
        boolean v1 = true;
        ContentValues v0 = new ContentValues(1);
        v0.put("url", arg4);
        if(this.mContentResolver.insert(this.mRequestUri, v0) != null) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    @CalledByNative WebRestrictionsClientResult shouldProceed(String arg10) {
        return new WebRestrictionsClientResult(this.mContentResolver.query(this.mQueryUri, null, String.format("url = \'%s\'", arg10), null, null));
    }

    @CalledByNative boolean supportsRequest() {
        boolean v0 = this.mContentResolver == null || this.mContentResolver.getType(this.mRequestUri) == null ? false : true;
        return v0;
    }
}

