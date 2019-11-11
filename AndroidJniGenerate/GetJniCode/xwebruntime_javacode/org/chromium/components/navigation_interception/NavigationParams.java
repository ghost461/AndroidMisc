package org.chromium.components.navigation_interception;

import android.text.TextUtils;
import org.chromium.base.annotations.CalledByNative;

public class NavigationParams {
    public final boolean hasUserGesture;
    public final boolean hasUserGestureCarryover;
    public final boolean isExternalProtocol;
    public final boolean isMainFrame;
    public final boolean isPost;
    public final boolean isRedirect;
    public final int pageTransitionType;
    public final String referrer;
    public final String suggestedFilename;
    public final String url;

    public NavigationParams(String arg1, String arg2, boolean arg3, boolean arg4, int arg5, boolean arg6, boolean arg7, boolean arg8, String arg9, boolean arg10) {
        super();
        this.url = arg1;
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            arg2 = null;
        }

        this.referrer = arg2;
        this.isPost = arg3;
        this.hasUserGesture = arg4;
        this.pageTransitionType = arg5;
        this.isRedirect = arg6;
        this.isExternalProtocol = arg7;
        this.isMainFrame = arg8;
        this.suggestedFilename = arg9;
        this.hasUserGestureCarryover = arg10;
    }

    @CalledByNative public static NavigationParams create(String arg12, String arg13, boolean arg14, boolean arg15, int arg16, boolean arg17, boolean arg18, boolean arg19, String arg20, boolean arg21) {
        return new NavigationParams(arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21);
    }
}

