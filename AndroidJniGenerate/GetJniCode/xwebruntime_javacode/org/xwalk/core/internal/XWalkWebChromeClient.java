package org.xwalk.core.internal;

import android.graphics.Bitmap;
import android.os.Message;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebStorage$QuotaUpdater;

public class XWalkWebChromeClient {
    private long XWALK_MAX_QUOTA;

    public XWalkWebChromeClient() {
        super();
        this.XWALK_MAX_QUOTA = 0x6400000;
    }

    public void getVisitedHistory(ValueCallback arg1) {
    }

    @Deprecated public void onConsoleMessage(String arg1, int arg2, String arg3) {
    }

    public boolean onConsoleMessage(ConsoleMessage arg3) {
        this.onConsoleMessage(arg3.message(), arg3.lineNumber(), arg3.sourceId());
        return 0;
    }

    public void onExceededDatabaseQuota(String arg1, String arg2, long arg3, long arg5, long arg7, WebStorage$QuotaUpdater arg9) {
        arg9.updateQuota(this.XWALK_MAX_QUOTA);
    }

    public void onGeolocationPermissionsHidePrompt() {
    }

    public void onGeolocationPermissionsShowPrompt(String arg3, XWalkGeolocationPermissionsCallbackInternal arg4) {
        arg4.invoke(arg3, true, false);
    }

    public boolean onJsTimeout() {
        return 1;
    }

    public void onReachedMaxAppCacheSize(long arg1, long arg3, WebStorage$QuotaUpdater arg5) {
        arg5.updateQuota(this.XWALK_MAX_QUOTA);
    }

    public void onReceivedIcon(XWalkViewInternal arg1, Bitmap arg2) {
    }

    public void setInstallableWebApp() {
    }

    public void setupAutoFill(Message arg1) {
    }
}

