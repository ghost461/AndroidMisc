package org.xwalk.core.internal.videofullscreen;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient$CustomViewCallback;

public interface VideoNativeInterface {
    void evaluteJavascript(boolean arg1, boolean arg2);

    boolean hasEnteredFullscreen();

    void init(Activity arg1, View arg2, View arg3, Context arg4, String arg5);

    void onHideCustomView();

    void onShowCustomView(View arg1, WebChromeClient$CustomViewCallback arg2);

    void registerJavascriptInterface(Object arg1);
}

