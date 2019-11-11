package org.xwalk.core.internal;

import android.graphics.Picture;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import org.chromium.content.browser.ContentVideoView;
import org.chromium.content.browser.ContentVideoViewEmbedder;

public abstract class XWalkContentsClient {
    public class WebResourceErrorInner {
        public String description;
        public int errorCode;

        public WebResourceErrorInner() {
            super();
            this.errorCode = -1;
        }
    }

    public class WebResourceRequestInner {
        public boolean hasUserGesture;
        public boolean isMainFrame;
        public boolean isRedirect;
        public String method;
        public HashMap requestHeaders;
        public String url;

        public WebResourceRequestInner() {
            super();
        }

        public WebResourceRequestInner(String arg1, boolean arg2, boolean arg3, String arg4, HashMap arg5) {
            super();
            this.url = arg1;
            this.isMainFrame = arg2;
            this.hasUserGesture = arg3;
            this.method = arg4;
            this.requestHeaders = arg5;
        }

        public WebResourceRequestInner(String arg7, boolean arg8, boolean arg9, String arg10, String[] arg11, String[] arg12) {
            this(arg7, arg8, arg9, arg10, new HashMap(arg12.length));
            int v7;
            for(v7 = 0; v7 < arg11.length; ++v7) {
                this.requestHeaders.put(arg11[v7], arg12[v7]);
            }
        }
    }

    private static final String TAG = "XWalkContentsClient";
    private final XWalkContentsClientCallbackHelper mCallbackHelper;
    private double mDIPScale;
    private String mTitle;
    private XWalkWebContentsObserver mWebContentsObserver;

    public XWalkContentsClient() {
        super();
        this.mCallbackHelper = new XWalkContentsClientCallbackHelper(this);
        this.mTitle = "";
    }

    public abstract void OnGetSampleString(Map arg1);

    public abstract void OnGetTranslateString(Map arg1);

    public abstract void didFinishLoad(String arg1);

    public abstract void doUpdateVisitedHistory(String arg1, boolean arg2);

    public abstract void enterFullscreenVideo(ContentVideoView arg1);

    public abstract void exitFullscreenVideo();

    final XWalkContentsClientCallbackHelper getCallbackHelper() {
        return this.mCallbackHelper;
    }

    public abstract ContentVideoViewEmbedder getContentVideoViewEmbedder();

    public abstract View getVideoLoadingProgressView();

    public abstract void getVisitedHistory(ValueCallback arg1);

    public abstract boolean hasEnteredFullscreen();

    public abstract boolean isSearchable();

    final void onBackgroundColorChanged(int arg1) {
    }

    protected abstract void onCloseWindow();

    public abstract boolean onConsoleMessage(ConsoleMessage arg1);

    protected abstract boolean onCreateWindow(boolean arg1, boolean arg2);

    public abstract void onDidChangeThemeColor(int arg1);

    public abstract void onDocumentLoadedInFrame(long arg1);

    public abstract void onDownloadStart(String arg1, String arg2, String arg3, String arg4, long arg5);

    public abstract void onFindResultReceived(int arg1, int arg2, boolean arg3);

    public abstract void onFormResubmission(Message arg1, Message arg2);

    public abstract void onGeolocationPermissionsHidePrompt();

    public abstract void onGeolocationPermissionsShowPrompt(String arg1, XWalkGeolocationPermissionsCallbackInternal arg2);

    public abstract void onHideCustomView();

    public abstract void onLoadResource(String arg1);

    public abstract void onNewPicture(Picture arg1);

    public abstract void onPageCommitVisible(String arg1);

    public abstract void onPageFinished(String arg1);

    public abstract void onPageStarted(String arg1);

    public abstract void onProgressChanged(int arg1);

    public abstract void onReceivedClientCertRequest(ClientCertRequestInternal arg1);

    protected abstract void onReceivedError(int arg1, String arg2, String arg3);

    public final void onReceivedError(WebResourceRequestInner arg4, WebResourceErrorInner arg5) {
        if(arg4.isMainFrame) {
            this.onReceivedError(arg5.errorCode, arg5.description, arg4.url);
        }

        this.onReceivedError2(arg4, arg5);
    }

    protected abstract void onReceivedError2(WebResourceRequestInner arg1, WebResourceErrorInner arg2);

    public abstract void onReceivedHttpAuthRequest(XWalkHttpAuthHandlerInternal arg1, String arg2, String arg3);

    public abstract void onReceivedHttpError(WebResourceRequestInner arg1, XWalkWebResourceResponseInternal arg2);

    public abstract void onReceivedLoginRequest(String arg1, String arg2, String arg3);

    public abstract void onReceivedResponseHeaders(WebResourceRequestInner arg1, XWalkWebResourceResponseInternal arg2);

    public abstract void onReceivedSslError(ValueCallback arg1, SslError arg2);

    public abstract void onReceivedTitle(String arg1);

    public abstract void onRendererResponsive();

    public abstract void onRendererUnresponsive();

    protected abstract void onRequestFocus();

    public abstract void onResourceLoadFinished(String arg1);

    public abstract void onResourceLoadStarted(String arg1);

    public final void onScaleChanged(float arg5, float arg6) {
        this.onScaleChangedScaled(((float)((((double)arg5)) * this.mDIPScale)), ((float)((((double)arg6)) * this.mDIPScale)));
    }

    public abstract void onScaleChangedScaled(float arg1, float arg2);

    public abstract boolean onSearchWord(String arg1, String arg2, String arg3);

    public abstract boolean onSelectInfoChanged(long arg1, String arg2, String arg3, String arg4);

    public abstract void onShowCustomView(View arg1, int arg2, CustomViewCallbackInternal arg3);

    public void onShowCustomView(View arg2, CustomViewCallbackInternal arg3) {
        this.onShowCustomView(arg2, -1, arg3);
    }

    public abstract void onShowSos();

    protected abstract void onStopLoading();

    public abstract void onTitleChanged(String arg1);

    public abstract void onToggleFullscreen(boolean arg1);

    public abstract void onUnhandledKeyEvent(KeyEvent arg1);

    public abstract void provideClientCertificateResponse(int arg1, byte[][] arg2, PrivateKey arg3);

    void setDIPScale(double arg1) {
        this.mDIPScale = arg1;
    }

    public abstract boolean shouldCreateWebContents(String arg1);

    public abstract XWalkWebResourceResponseInternal shouldInterceptRequest(WebResourceRequestInner arg1);

    public abstract boolean shouldOverrideRunFileChooser(int arg1, int arg2, int arg3, String arg4, boolean arg5);

    public abstract boolean shouldOverrideUrlLoading(String arg1);

    public final void updateTitle(String arg1, boolean arg2) {
        if(!arg2 && (TextUtils.equals(this.mTitle, ((CharSequence)arg1)))) {
            return;
        }

        this.mTitle = arg1;
        this.mCallbackHelper.postOnReceivedTitle(this.mTitle);
    }
}

