package org.chromium.components.safe_browsing;

import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface SafeBrowsingApiHandler {
    public interface Observer {
        void onUrlCheckDone(long arg1, int arg2, String arg3, long arg4);
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface SafeBrowsingResult {
    }

    public static final int STATUS_INTERNAL_ERROR = -1;
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_TIMEOUT = 1;

    boolean init(Context arg1, Observer arg2);

    void startUriLookup(long arg1, String arg2, int[] arg3);
}

