package org.xwalk.core.internal;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage$MessageLevel;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.tencent.xweb.statistics.XRequestStat;
import java.security.Principal;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.components.navigation_interception.NavigationParams;
import org.chromium.content.browser.ContentVideoView;
import org.chromium.content.browser.ContentVideoViewEmbedder;
import org.chromium.content_public.browser.ContentViewCore;

@JNINamespace(value="xwalk") class XWalkContentsClientBridge extends XWalkContentsClient {
    class org.xwalk.core.internal.XWalkContentsClientBridge$5 {
        static {
            org.xwalk.core.internal.XWalkContentsClientBridge$5.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel = new int[ConsoleMessage$MessageLevel.values().length];
            try {
                org.xwalk.core.internal.XWalkContentsClientBridge$5.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage$MessageLevel.TIP.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.xwalk.core.internal.XWalkContentsClientBridge$5.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage$MessageLevel.LOG.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.xwalk.core.internal.XWalkContentsClientBridge$5.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage$MessageLevel.WARNING.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.xwalk.core.internal.XWalkContentsClientBridge$5.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage$MessageLevel.ERROR.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    class InterceptNavigationDelegateImpl implements InterceptNavigationDelegate {
        private XWalkContentsClient mContentsClient;

        public InterceptNavigationDelegateImpl(XWalkContentsClientBridge arg1, XWalkContentsClient arg2) {
            XWalkContentsClientBridge.this = arg1;
            super();
            this.mContentsClient = arg2;
        }

        public boolean shouldIgnoreNavigation(NavigationParams arg3) {
            String v0 = arg3.url;
            boolean v3 = XWalkContentsClientBridge.this.mNavigationHandler == null || !XWalkContentsClientBridge.this.mNavigationHandler.handleNavigation(arg3) ? false : true;
            if(!v3) {
                String v1 = XWalkContentsClientBridge.this.mNavigationHandler.getFallbackUrl();
                if(v1 != null) {
                    XWalkContentsClientBridge.this.mNavigationHandler.resetFallbackUrl();
                    XWalkContentsClientBridge.this.mXWalkView.loadUrl(v1);
                }
                else {
                    this.mContentsClient.getCallbackHelper().postOnPageStarted(v0);
                }
            }

            return v3;
        }
    }

    private static final int NEW_ICON_DOWNLOAD = 101;
    private static final int NEW_XWALKVIEW_CREATED = 100;
    private static final String TAG = "org.xwalk.core.internal.XWalkContentsClientBridge";
    private XWalkContentView mContentView;
    private FrameLayout mCustomView;
    private XWalkDownloadListenerInternal mDownloadListener;
    private Bitmap mFavicon;
    private XWalkFindListenerInternal mFindListener;
    private InterceptNavigationDelegate mInterceptNavigationDelegate;
    private boolean mIsFullscreen;
    private boolean mIsVideoFullscreen;
    private LoadStatusInternal mLoadStatus;
    private String mLoadingUrl;
    protected ClientCertLookupTable mLookupTable;
    protected long mNativeContentsClientBridge;
    private XWalkNavigationHandler mNavigationHandler;
    private XWalkNotificationService mNotificationService;
    private PageLoadListener mPageLoadListener;
    private float mPageScaleFactor;
    private ViewGroup mTopViewParent;
    private Handler mUiThreadHandler;
    private XWalkClient mXWalkClient;
    private XWalkResourceClientInternal mXWalkResourceClient;
    private XWalkUIClientInternal mXWalkUIClient;
    private XWalkViewInternal mXWalkView;
    private XWalkWebChromeClient mXWalkWebChromeClient;

    static {
    }

    public XWalkContentsClientBridge(XWalkViewInternal arg1, ContentViewCore arg2) {
        super();
        this.mIsFullscreen = false;
        this.mIsVideoFullscreen = false;
        this.mLoadStatus = LoadStatusInternal.FINISHED;
        this.mLoadingUrl = null;
        this.mXWalkView = arg1;
        this.mLookupTable = new ClientCertLookupTable();
        this.mInterceptNavigationDelegate = new InterceptNavigationDelegateImpl(this, ((XWalkContentsClient)this));
        this.mUiThreadHandler = new Handler() {
            public void handleMessage(Message arg4) {
                switch(arg4.what) {
                    case 100: {
                        goto label_11;
                    }
                    case 101: {
                        goto label_5;
                    }
                }

                throw new IllegalStateException();
            label_5:
                XWalkContentsClientBridge.access$200(XWalkContentsClientBridge.this, XWalkContentsClientBridge.this.mNativeContentsClientBridge, arg4.obj);
                return;
            label_11:
                Object v4 = arg4.obj;
                if(v4 == XWalkContentsClientBridge.access$100(XWalkContentsClientBridge.this)) {
                    throw new IllegalArgumentException("Parent XWalkView cannot host it\'s own popup window");
                }

                if(v4 != null) {
                    XWalkNavigationHistoryInternal v0 = ((XWalkViewInternal)v4).getNavigationHistory();
                    if(v0 != null && v0.size() != 0) {
                        throw new IllegalArgumentException("New WebView for popup window must not have been previously navigated.");
                    }
                }

                XWalkContentsClientBridge.access$100(XWalkContentsClientBridge.this).completeWindowCreation(((XWalkViewInternal)v4));
            }
        };
    }

    public void OnGetSampleString(Map arg3) {
        this.mXWalkUIClient.OnGetSampleString(this.mXWalkView, arg3);
    }

    public void OnGetTranslateString(Map arg3) {
        this.mXWalkUIClient.OnGetTranslateString(this.mXWalkView, arg3);
    }

    static XWalkNavigationHandler access$000(XWalkContentsClientBridge arg0) {
        return arg0.mNavigationHandler;
    }

    static XWalkViewInternal access$100(XWalkContentsClientBridge arg0) {
        return arg0.mXWalkView;
    }

    static void access$200(XWalkContentsClientBridge arg0, long arg1, String arg3) {
        arg0.nativeDownloadIcon(arg1, arg3);
    }

    static Handler access$300(XWalkContentsClientBridge arg0) {
        return arg0.mUiThreadHandler;
    }

    static void access$400(XWalkContentsClientBridge arg0, long arg1, int arg3, int arg4, int arg5) {
        arg0.nativeOnFilesNotSelected(arg1, arg3, arg4, arg5);
    }

    static void access$500(XWalkContentsClientBridge arg0, long arg1, int arg3, int arg4, int arg5, String arg6, String arg7) {
        arg0.nativeOnFilesSelected(arg1, arg3, arg4, arg5, arg6, arg7);
    }

    static void access$600(XWalkContentsClientBridge arg0, boolean arg1, int arg2) {
        arg0.proceedSslError(arg1, arg2);
    }

    @CalledByNative private boolean allowCertificateError(int arg3, byte[] arg4, String arg5, int arg6) {
        if(SslUtil.shouldDenyRequest(arg3)) {
            Toast.makeText(this.mXWalkView.getContext(), 0x7F0C0069, 0).show();
            return 0;
        }

        SslCertificate v4 = SslUtil.getCertificateFromDerBytes(arg4);
        if(v4 == null) {
            return 0;
        }

        this.onReceivedSslError(new ValueCallback(arg6) {
            public void onReceiveValue(Boolean arg3) {
                XWalkContentsClientBridge.this.proceedSslError(arg3.booleanValue(), this.val$id);
            }

            public void onReceiveValue(Object arg1) {
                this.onReceiveValue(((Boolean)arg1));
            }
        }, SslUtil.sslErrorFromNetErrorCode(arg3, v4, arg5));
        return 1;
    }

    void cancelJsResult(int arg6) {
        if(this.mNativeContentsClientBridge == 0) {
            return;
        }

        this.nativeCancelJsResult(this.mNativeContentsClientBridge, arg6);
    }

    @CalledByNative private void cancelNotification(int arg2) {
        this.mNotificationService.cancelNotification(arg2);
    }

    public void clearClientCertPreferences(Runnable arg6) {
        this.mLookupTable.clear();
        if(this.mNativeContentsClientBridge != 0) {
            this.nativeClearClientCertPreferences(this.mNativeContentsClientBridge, arg6);
        }
        else if(arg6 != null) {
            arg6.run();
        }
    }

    @CalledByNative private void clientCertificatesCleared(Runnable arg1) {
        if(arg1 == null) {
            return;
        }

        arg1.run();
    }

    void confirmJsResult(int arg6, String arg7) {
        if(this.mNativeContentsClientBridge == 0) {
            return;
        }

        this.nativeConfirmJsResult(this.mNativeContentsClientBridge, arg6, arg7);
    }

    public void didFinishLoad(String arg1) {
    }

    public void doUpdateVisitedHistory(String arg3, boolean arg4) {
        this.mXWalkResourceClient.doUpdateVisitedHistory(this.mXWalkView, arg3, arg4);
    }

    public void enterFullscreenVideo(ContentVideoView arg2) {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.enterFullscreenVideo(arg2);
        }
    }

    public void exitFullscreenVideo() {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.exitFullscreenVideo();
        }
    }

    public ContentVideoViewEmbedder getContentVideoViewEmbedder() {
        return new XWalkContentVideoViewClient(((XWalkContentsClient)this), this.mXWalkView, this.mCustomView);
    }

    public Bitmap getFavicon() {
        return this.mFavicon;
    }

    public InterceptNavigationDelegate getInterceptNavigationDelegate() {
        return this.mInterceptNavigationDelegate;
    }

    public View getVideoLoadingProgressView() {
        return this.mXWalkUIClient.getVideoLoadingProgressView();
    }

    public void getVisitedHistory(ValueCallback arg1) {
    }

    public XWalkWebChromeClient getXWalkWebChromeClient() {
        return this.mXWalkWebChromeClient;
    }

    @CalledByNative private void handleJsAlert(String arg8, String arg9, int arg10) {
        this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, JavascriptMessageTypeInternal.JAVASCRIPT_ALERT, arg8, arg9, "", new XWalkJavascriptResultHandlerInternal(this, arg10));
    }

    @CalledByNative private void handleJsBeforeUnload(String arg8, String arg9, int arg10) {
        this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, JavascriptMessageTypeInternal.JAVASCRIPT_BEFOREUNLOAD, arg8, arg9, "", new XWalkJavascriptResultHandlerInternal(this, arg10));
    }

    @CalledByNative private void handleJsConfirm(String arg8, String arg9, int arg10) {
        this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, JavascriptMessageTypeInternal.JAVASCRIPT_CONFIRM, arg8, arg9, "", new XWalkJavascriptResultHandlerInternal(this, arg10));
    }

    @CalledByNative private void handleJsPrompt(String arg8, String arg9, String arg10, int arg11) {
        this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, JavascriptMessageTypeInternal.JAVASCRIPT_PROMPT, arg8, arg9, arg10, new XWalkJavascriptResultHandlerInternal(this, arg11));
    }

    public boolean hasEnteredFullscreen() {
        return this.mIsFullscreen;
    }

    public boolean hasEnteredVideoFullscreen() {
        boolean v0 = ContentVideoView.getContentVideoView() != null ? true : false;
        return v0;
    }

    public boolean isSearchable() {
        return this.mXWalkUIClient.isSearchable();
    }

    private native void nativeCancelJsResult(long arg1, int arg2) {
    }

    private native void nativeClearClientCertPreferences(long arg1, Runnable arg2) {
    }

    private native void nativeConfirmJsResult(long arg1, int arg2, String arg3) {
    }

    private native void nativeDownloadIcon(long arg1, String arg2) {
    }

    private native void nativeNotificationClicked(long arg1, int arg2) {
    }

    private native void nativeNotificationClosed(long arg1, int arg2, boolean arg3) {
    }

    private native void nativeNotificationDisplayed(long arg1, int arg2) {
    }

    private native void nativeOnFilesNotSelected(long arg1, int arg2, int arg3, int arg4) {
    }

    private native void nativeOnFilesSelected(long arg1, int arg2, int arg3, int arg4, String arg5, String arg6) {
    }

    private native void nativeProceedSslError(long arg1, boolean arg2, int arg3) {
    }

    private native void nativeProvideClientCertificateResponse(long arg1, int arg2, byte[][] arg3, PrivateKey arg4) {
    }

    @CalledByNative private void newDownload(String arg8, String arg9, String arg10, String arg11, long arg12) {
        this.getCallbackHelper().postOnDownloadStart(arg8, arg9, arg10, arg11, arg12);
    }

    public void notificationClicked(int arg6) {
        if(this.mNativeContentsClientBridge == 0) {
            return;
        }

        this.nativeNotificationClicked(this.mNativeContentsClientBridge, arg6);
    }

    public void notificationClosed(int arg6, boolean arg7) {
        if(this.mNativeContentsClientBridge == 0) {
            return;
        }

        this.nativeNotificationClosed(this.mNativeContentsClientBridge, arg6, arg7);
    }

    public void notificationDisplayed(int arg6) {
        if(this.mNativeContentsClientBridge == 0) {
            return;
        }

        this.nativeNotificationDisplayed(this.mNativeContentsClientBridge, arg6);
    }

    public void onClearCurrentPage() {
        this.mXWalkUIClient.onClearCurrentPage();
    }

    public void onCloseWindow() {
        this.mXWalkUIClient.onJavascriptCloseWindow(this.mXWalkView);
    }

    public boolean onConsoleMessage(ConsoleMessage arg10) {
        if(this.mXWalkClient != null) {
            if(this.mXWalkView == null) {
            }
            else {
                ConsoleMessageType v0 = ConsoleMessageType.DEBUG;
                switch(org.xwalk.core.internal.XWalkContentsClientBridge$5.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel[arg10.messageLevel().ordinal()]) {
                    case 1: {
                        v0 = ConsoleMessageType.INFO;
                        break;
                    }
                    case 2: {
                        v0 = ConsoleMessageType.LOG;
                        break;
                    }
                    case 3: {
                        v0 = ConsoleMessageType.WARNING;
                        break;
                    }
                    case 4: {
                        v0 = ConsoleMessageType.ERROR;
                        break;
                    }
                    default: {
                        Log.w(XWalkContentsClientBridge.TAG, "Unknown message level, defaulting to DEBUG");
                        break;
                    }
                }

                ConsoleMessageType v8 = v0;
                return this.mXWalkUIClient.onConsoleMessage(this.mXWalkView, arg10.message(), arg10.lineNumber(), arg10.sourceId(), v8);
            }
        }

        return 0;
    }

    public boolean onCreateWindow(boolean arg3, boolean arg4) {
        if(arg3) {
            return 0;
        }

        InitiateByInternal v3 = InitiateByInternal.BY_JAVASCRIPT;
        if(arg4) {
            v3 = InitiateByInternal.BY_USER_GESTURE;
        }

        return this.mXWalkUIClient.onCreateWindowRequested(this.mXWalkView, v3, new ValueCallback() {
            public void onReceiveValue(Object arg1) {
                this.onReceiveValue(((XWalkViewInternal)arg1));
            }

            public void onReceiveValue(XWalkViewInternal arg3) {
                XWalkContentsClientBridge.this.mUiThreadHandler.obtainMessage(100, arg3).sendToTarget();
            }
        });
    }

    public void onDidChangeThemeColor(int arg1) {
    }

    public void onDocumentLoadedInFrame(long arg3) {
        this.mXWalkResourceClient.onDocumentLoadedInFrame(this.mXWalkView, arg3);
    }

    public void onDownloadStart(String arg9, String arg10, String arg11, String arg12, long arg13) {
        if(this.mDownloadListener != null) {
            this.mDownloadListener.onDownloadStart(arg9, arg10, arg11, arg12, arg13);
        }
    }

    public void onFindResultReceived(int arg2, int arg3, boolean arg4) {
        if(this.mFindListener == null) {
            return;
        }

        this.mFindListener.onFindResultReceived(arg2, arg3, arg4);
    }

    public void onFormResubmission(Message arg1, Message arg2) {
        arg1.sendToTarget();
    }

    public void onGeolocationPermissionsHidePrompt() {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.onGeolocationPermissionsHidePrompt();
        }

        if(this.mXWalkWebChromeClient != null) {
            this.mXWalkWebChromeClient.onGeolocationPermissionsHidePrompt();
        }
    }

    public void onGeolocationPermissionsShowPrompt(String arg2, XWalkGeolocationPermissionsCallbackInternal arg3) {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.onGeolocationPermissionsShowPrompt(arg2, arg3);
        }

        if(this.mXWalkWebChromeClient != null) {
            this.mXWalkWebChromeClient.onGeolocationPermissionsShowPrompt(arg2, arg3);
        }
    }

    public void onHideCustomView() {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.onHideCustomView();
        }
    }

    @CalledByNative public void onIconAvailable(String arg4) {
        this.mXWalkUIClient.onIconAvailable(this.mXWalkView, arg4, this.mUiThreadHandler.obtainMessage(101, arg4));
    }

    public void onLoadResource(String arg3) {
        this.mXWalkResourceClient.onLoadResource(this.mXWalkView, arg3);
    }

    public boolean onNewIntent(Intent arg2) {
        return this.mNotificationService.maybeHandleIntent(arg2);
    }

    public void onNewPicture(Picture arg1) {
    }

    public void onOverScrolled(int arg2, int arg3, boolean arg4, boolean arg5) {
        this.mXWalkView.onOverScrolledDelegate(arg2, arg3, arg4, arg5);
    }

    public void onOverscrollRefresh(boolean arg2) {
        this.mXWalkView.onOverscrollRefresh(arg2);
    }

    public void onPageCommitVisible(String arg3) {
        if(this.mXWalkUIClient != null && this.mXWalkView != null) {
            this.mXWalkUIClient.onPageCommitVisible(this.mXWalkView, arg3);
        }
    }

    public void onPageFinished(String arg5) {
        if(this.mPageLoadListener != null) {
            this.mPageLoadListener.onPageFinished(arg5);
        }

        if(this.mXWalkUIClient != null) {
            if(this.mLoadStatus != LoadStatusInternal.CANCELLED || this.mLoadingUrl == null) {
                this.mXWalkUIClient.onPageLoadStopped(this.mXWalkView, arg5, this.mLoadStatus);
            }
            else {
                this.mXWalkUIClient.onPageLoadStopped(this.mXWalkView, this.mLoadingUrl, this.mLoadStatus);
            }

            this.mLoadingUrl = null;
        }

        this.onResourceLoadFinished(arg5);
        XRequestStat.getInstance().OnPageFinished(this.mXWalkView, arg5);
    }

    public void onPageStarted(String arg3) {
        if(this.mXWalkUIClient != null) {
            this.mLoadingUrl = arg3;
            this.mLoadStatus = LoadStatusInternal.FINISHED;
            this.mXWalkUIClient.onPageLoadStarted(this.mXWalkView, arg3);
        }

        XRequestStat.getInstance().OnPageStarted(this.mXWalkView, arg3);
        this.mXWalkView.checkVerticalScrollBarEnableInternal();
        this.mXWalkView.checkHorizontalScrollBarEnableInternal();
    }

    public void onProgressChanged(int arg3) {
        this.mXWalkResourceClient.onProgressChanged(this.mXWalkView, arg3);
    }

    public void onReceivedClientCertRequest(ClientCertRequestInternal arg3) {
        if(this.mXWalkResourceClient != null) {
            this.mXWalkResourceClient.onReceivedClientCertRequest(this.mXWalkView, arg3);
        }
    }

    @CalledByNative private void onReceivedError(String arg10, boolean arg11, boolean arg12, String arg13, String[] arg14, String[] arg15, int arg16, String arg17, boolean arg18) {
        WebResourceRequestInner v8 = new WebResourceRequestInner(arg10, arg11, arg12, arg13, arg14, arg15);
        WebResourceErrorInner v1 = new WebResourceErrorInner();
        v1.errorCode = arg16;
        v1.description = arg17;
        if(arg16 != -3) {
            v1.errorCode = ErrorCodeConversionHelper.convertErrorCode(v1.errorCode);
            this.getCallbackHelper().postOnReceivedError(v8, v1);
            if(v8.isMainFrame) {
                this.getCallbackHelper().postOnReceivedError(v8, v1);
                this.getCallbackHelper().postOnPageFinished(v8.url);
            }
        }
    }

    public void onReceivedError(int arg3, String arg4, String arg5) {
        if(this.mLoadingUrl != null && (this.mLoadingUrl.equals(arg5))) {
            this.mLoadStatus = LoadStatusInternal.FAILED;
        }

        this.mXWalkResourceClient.onReceivedLoadError(this.mXWalkView, arg3, arg4, arg5);
    }

    protected void onReceivedError2(WebResourceRequestInner arg3, WebResourceErrorInner arg4) {
        XRequestStat.getInstance().OnReceivedError(this.mXWalkView, arg3, arg4);
    }

    @CalledByNative public void onReceivedHttpAuthRequest(XWalkHttpAuthHandlerInternal arg3, String arg4, String arg5) {
        if(this.mXWalkResourceClient != null) {
            this.mXWalkResourceClient.onReceivedHttpAuthRequest(this.mXWalkView, arg3, arg4, arg5);
        }
    }

    @CalledByNative private void onReceivedHttpError(String arg13, boolean arg14, boolean arg15, String arg16, String[] arg17, String[] arg18, String arg19, String arg20, int arg21, String arg22, String[] arg23, String[] arg24) {
        String[] v0 = arg17;
        String[] v1 = arg23;
        WebResourceRequestInner v2 = new WebResourceRequestInner();
        new WebResourceErrorInner();
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

        this.getCallbackHelper().postOnReceivedHttpError(v2, new XWalkWebResourceResponseInternal(arg19, arg20, null, arg21, arg22, ((Map)v11)));
    }

    public void onReceivedHttpError(WebResourceRequestInner arg4, XWalkWebResourceResponseInternal arg5) {
        if(this.mXWalkResourceClient != null) {
            this.mXWalkResourceClient.onReceivedHttpError(this.mXWalkView, new XWalkWebResourceRequestHandlerInternal(arg4), arg5);
        }
    }

    @CalledByNative public void onReceivedIcon(String arg3, Bitmap arg4) {
        this.mXWalkUIClient.onReceivedIcon(this.mXWalkView, arg3, arg4);
        this.mFavicon = arg4;
    }

    public void onReceivedLoginRequest(String arg1, String arg2, String arg3) {
    }

    public void onReceivedResponseHeaders(WebResourceRequestInner arg4, XWalkWebResourceResponseInternal arg5) {
        if(this.mXWalkResourceClient != null) {
            this.mXWalkResourceClient.onReceivedResponseHeaders(this.mXWalkView, new XWalkWebResourceRequestHandlerInternal(arg4), arg5);
        }
    }

    public void onReceivedSslError(ValueCallback arg3, SslError arg4) {
        if(this.mXWalkResourceClient != null) {
            this.mXWalkResourceClient.onReceivedSslError(this.mXWalkView, arg3, arg4);
        }
    }

    public void onReceivedTitle(String arg3) {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.onReceivedTitle(this.mXWalkView, arg3);
        }
    }

    public void onRendererResponsive() {
        if(this.mXWalkClient != null) {
            this.mXWalkClient.onRendererResponsive(this.mXWalkView);
        }
    }

    public void onRendererUnresponsive() {
        if(this.mXWalkClient != null) {
            this.mXWalkClient.onRendererUnresponsive(this.mXWalkView);
        }
    }

    public void onRequestFocus() {
        this.mXWalkUIClient.onRequestFocus(this.mXWalkView);
    }

    public void onResourceLoadFinished(String arg3) {
        this.mXWalkResourceClient.onLoadFinished(this.mXWalkView, arg3);
    }

    public void onResourceLoadStarted(String arg3) {
        this.mXWalkResourceClient.onLoadStarted(this.mXWalkView, arg3);
    }

    public void onScaleChangedScaled(float arg3, float arg4) {
        this.mXWalkUIClient.onScaleChanged(this.mXWalkView, arg3, arg4);
    }

    public boolean onSearchWord(String arg3, String arg4, String arg5) {
        return this.mXWalkUIClient.onSearchWord(this.mXWalkView, arg3, arg4, arg5);
    }

    public boolean onSelectInfoChanged(long arg8, String arg10, String arg11, String arg12) {
        return this.mXWalkUIClient.onSelectInfoChanged(this.mXWalkView, arg8, arg10, arg11, arg12);
    }

    public void onShowCustomView(View arg2, int arg3, CustomViewCallbackInternal arg4) {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.onShowCustomView(arg2, arg3, arg4);
        }
    }

    public void onShowCustomView(View arg2, CustomViewCallbackInternal arg3) {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.onShowCustomView(arg2, arg3);
        }
    }

    public void onShowSos() {
        this.mXWalkUIClient.onShowSos();
    }

    protected void onStopLoading() {
        this.mLoadStatus = LoadStatusInternal.CANCELLED;
    }

    public void onTitleChanged(String arg3) {
        if(this.mXWalkUIClient != null) {
            this.mXWalkUIClient.onReceivedTitle(this.mXWalkView, arg3);
        }
    }

    public void onToggleFullscreen(boolean arg3) {
        this.mIsFullscreen = arg3;
        if(arg3) {
            this.mCustomView = new FrameLayout(this.mXWalkView.getContext());
            this.mXWalkUIClient.onShowCustomView(this.mCustomView, new CustomViewCallbackHandlerInternal());
        }
        else {
            if(this.mCustomView != null) {
                FrameLayout v3 = null;
                this.mCustomView = v3;
                ContentVideoView v0 = ContentVideoView.getContentVideoView();
                if(v0 != null) {
                    ContentVideoViewEmbedder v0_1 = v0.getContentVideoViewEmbedder();
                    if((v0_1 instanceof XWalkContentVideoViewClient)) {
                        ((XWalkContentVideoViewClient)v0_1).setCustomView(v3);
                    }
                }
            }

            this.mXWalkUIClient.onHideCustomView();
        }
    }

    public void onUnhandledKeyEvent(KeyEvent arg3) {
        if(this.mXWalkUIClient != null && this.mXWalkView != null) {
            this.mXWalkUIClient.onUnhandledKeyEvent(this.mXWalkView, arg3);
        }
    }

    @CalledByNative public void onWebLayoutPageScaleFactorChanged(float arg2) {
        if(this.mPageScaleFactor == arg2) {
            return;
        }

        float v0 = this.mPageScaleFactor;
        this.mPageScaleFactor = arg2;
        this.onScaleChanged(v0, this.mPageScaleFactor);
    }

    private void proceedSslError(boolean arg6, int arg7) {
        if(this.mNativeContentsClientBridge == 0) {
            return;
        }

        this.nativeProceedSslError(this.mNativeContentsClientBridge, arg6, arg7);
    }

    public void provideClientCertificateResponse(int arg7, byte[][] arg8, PrivateKey arg9) {
        this.nativeProvideClientCertificateResponse(this.mNativeContentsClientBridge, arg7, arg8, arg9);
    }

    void registerPageLoadListener(PageLoadListener arg1) {
        this.mPageLoadListener = arg1;
    }

    @CalledByNative private void selectClientCertificate(int arg10, String[] arg11, byte[][] arg12, String arg13, int arg14) {
        Principal[] v5_1;
        if(this.mXWalkResourceClient != null) {
            Cert v2 = this.mLookupTable.getCertData(arg13, arg14);
            byte[][] v4 = null;
            if(this.mLookupTable.isDenied(arg13, arg14)) {
                this.nativeProvideClientCertificateResponse(this.mNativeContentsClientBridge, arg10, v4, null);
                return;
            }
            else if(v2 != null) {
                this.nativeProvideClientCertificateResponse(this.mNativeContentsClientBridge, arg10, v2.mCertChain, v2.mPrivateKey);
                return;
            }
            else {
                if(arg12.length > 0) {
                    X500Principal[] v2_1 = new X500Principal[arg12.length];
                    int v3 = 0;
                    while(true) {
                        if(v3 < arg12.length) {
                            try {
                                v2_1[v3] = new X500Principal(arg12[v3]);
                                ++v3;
                                continue;
                            }
                            catch(IllegalArgumentException v0) {
                                String v2_2 = XWalkContentsClientBridge.TAG;
                                Log.w(v2_2, "Exception while decoding issuers list: " + v0);
                                this.nativeProvideClientCertificateResponse(this.mNativeContentsClientBridge, arg10, v4, null);
                                return;
                            }
                        }
                        else {
                            break;
                        }

                        goto label_57;
                    }

                    X500Principal[] v5 = v2_1;
                }
                else {
                    v5_1 = ((Principal[])v4);
                }

            label_57:
                this.onReceivedClientCertRequest(new ClientCertRequestHandlerInternal(this, arg10, arg11, v5_1, arg13, arg14));
            }
        }
    }

    public void setContentView(XWalkContentView arg1) {
        this.mContentView = arg1;
    }

    void setDownloadListener(XWalkDownloadListenerInternal arg1) {
        this.mDownloadListener = arg1;
    }

    void setFindListener(XWalkFindListenerInternal arg1) {
        this.mFindListener = arg1;
    }

    @CalledByNative private void setNativeContentsClientBridge(long arg1) {
        this.mNativeContentsClientBridge = arg1;
    }

    public void setNavigationHandler(XWalkNavigationHandler arg1) {
        this.mNavigationHandler = arg1;
    }

    public void setNotificationService(XWalkNotificationService arg2) {
        if(this.mNotificationService != null) {
            this.mNotificationService.shutdown();
        }

        this.mNotificationService = arg2;
        if(this.mNotificationService != null) {
            this.mNotificationService.setBridge(this);
        }
    }

    public void setResourceClient(XWalkResourceClientInternal arg2) {
        if(arg2 != null) {
            this.mXWalkResourceClient = arg2;
            return;
        }

        this.mXWalkResourceClient = new XWalkResourceClientInternal(this.mXWalkView);
    }

    public void setUIClient(XWalkUIClientInternal arg2) {
        this.mXWalkUIClient = arg2 == null ? new XWalkUIClientInternal(this.mXWalkView) : arg2;
        this.mXWalkUIClient.setContentsClient(((XWalkContentsClient)this));
    }

    public void setXWalkClient(XWalkClient arg1) {
        this.mXWalkClient = arg1;
    }

    public void setXWalkWebChromeClient(XWalkWebChromeClient arg1) {
        if(arg1 == null) {
            return;
        }

        this.mXWalkWebChromeClient = arg1;
    }

    public boolean shouldCreateWebContents(String arg1) {
        return 1;
    }

    public boolean shouldDiscardCurrentPage() {
        return this.mXWalkUIClient.shouldDiscardCurrentPage();
    }

    public XWalkWebResourceResponseInternal shouldInterceptRequest(WebResourceRequestInner arg10) {
        XWalkWebResourceResponseInternal v0 = null;
        try {
            WebResourceResponse v1 = this.mXWalkResourceClient.shouldInterceptLoadRequest(this.mXWalkView, arg10.url);
            if(v1 == null) {
                XWalkWebResourceResponseInternal v10_1 = this.mXWalkResourceClient.shouldInterceptLoadRequest(this.mXWalkView, new XWalkWebResourceRequestHandlerInternal(arg10));
                if(v10_1 == null) {
                    return v0;
                }

                Map v1_1 = v10_1.getResponseHeaders();
                if(v1_1 == null) {
                    HashMap v1_2 = new HashMap();
                }

                return new XWalkWebResourceResponseInternal(v10_1.getMimeType(), v10_1.getEncoding(), v10_1.getData(), v10_1.getStatusCode(), v10_1.getReasonPhrase(), v1_1);
            }

            return new XWalkWebResourceResponseInternal(v1.getMimeType(), v1.getEncoding(), v1.getData());
        }
        catch(Exception v10) {
            String v1_3 = XWalkContentsClientBridge.TAG;
            Log.i(v1_3, "shouldInterceptRequest: " + v10.getMessage());
            return v0;
        }
    }

    public boolean shouldOverrideRunFileChooser(int arg2, int arg3, int arg4, String arg5, boolean arg6) {
        abstract class UriCallback implements ValueCallback {
            boolean syncCallFinished;
            boolean syncNullReceived;

            UriCallback(XWalkContentsClientBridge arg1) {
                XWalkContentsClientBridge.this = arg1;
                super();
                this.syncNullReceived = false;
                this.syncCallFinished = false;
            }

            protected String resolveFileName(Uri arg8, ContentResolver arg9) {
                String v8_1;
                String v9_2;
                Cursor v8;
                if(arg9 != null) {
                    if(arg8 == null) {
                    }
                    else {
                        Cursor v0 = null;
                        String[] v3 = null;
                        String v4 = null;
                        String[] v5 = null;
                        String v6 = null;
                        ContentResolver v1 = arg9;
                        Uri v2 = arg8;
                        try {
                            v8 = v1.query(v2, v3, v4, v5, v6);
                            if(v8 != null) {
                                goto label_12;
                            }

                            goto label_28;
                        }
                        catch(Throwable v9) {
                            goto label_33;
                        }
                        catch(NullPointerException ) {
                            goto label_35;
                        }

                        try {
                        label_12:
                            if(v8.getCount() >= 1) {
                                v8.moveToFirst();
                                int v9_1 = v8.getColumnIndex("_display_name");
                                if(v9_1 > -1) {
                                    v9_2 = v8.getString(v9_1);
                                    if(v8 == null) {
                                        return v9_2;
                                    }

                                    goto label_22;
                                }
                            }

                            goto label_28;
                        }
                        catch(Throwable v9) {
                        }
                        catch(NullPointerException ) {
                            v0 = v8;
                            try {
                            label_35:
                                v8_1 = "";
                                if(v0 == null) {
                                    return v8_1;
                                }
                            }
                            catch(Throwable v9) {
                            label_33:
                                v8 = v0;
                                goto label_39;
                            }

                            v0.close();
                            return v8_1;
                        }

                    label_39:
                        if(v8 != null) {
                            v8.close();
                        }

                        throw v9;
                    label_22:
                        v8.close();
                        return v9_2;
                    label_28:
                        if(v8 != null) {
                            v8.close();
                        }

                        return "";
                    }
                }

                return "";
            }
        }

        org.xwalk.core.internal.XWalkContentsClientBridge$3 v0 = new UriCallback(arg2, arg3, arg4) {
            boolean completed;

            public void onReceiveValue(Uri arg9) {
                String v9;
                String v0;
                if(this.completed) {
                    throw new IllegalStateException("Duplicate openFileChooser result");
                }

                if(arg9 == null && !this.syncCallFinished) {
                    this.syncNullReceived = true;
                    return;
                }

                this.completed = true;
                if(arg9 == null) {
                    XWalkContentsClientBridge.this.nativeOnFilesNotSelected(XWalkContentsClientBridge.this.mNativeContentsClientBridge, this.val$processId, this.val$renderId, this.val$modeFlags);
                }
                else {
                    if("file".equals(arg9.getScheme())) {
                        v0 = arg9.getSchemeSpecificPart();
                        v9 = arg9.getLastPathSegment();
                    }
                    else if("content".equals(arg9.getScheme())) {
                        v0 = arg9.toString();
                        v9 = this.resolveFileName(arg9, XWalkContentsClientBridge.this.mXWalkView.getContext().getContentResolver());
                    }
                    else {
                        v0 = arg9.getPath();
                        v9 = arg9.getLastPathSegment();
                    }

                    String v6 = v0;
                    String v7 = v9 == null || (v9.isEmpty()) ? v6 : v9;
                    XWalkContentsClientBridge.this.nativeOnFilesSelected(XWalkContentsClientBridge.this.mNativeContentsClientBridge, this.val$processId, this.val$renderId, this.val$modeFlags, v6, v7);
                }
            }

            public void onReceiveValue(Object arg1) {
                this.onReceiveValue(((Uri)arg1));
            }
        };
        this.mXWalkUIClient.openFileChooser(this.mXWalkView, ((ValueCallback)v0), arg5, Boolean.toString(arg6));
        ((UriCallback)v0).syncCallFinished = true;
        return 1 ^ ((UriCallback)v0).syncNullReceived;
    }

    @CalledByNative private boolean shouldOverrideUrlLoading(String arg1, boolean arg2, boolean arg3, boolean arg4) {
        return this.shouldOverrideUrlLoading(arg1);
    }

    public boolean shouldOverrideUrlLoading(String arg3) {
        if(this.mXWalkResourceClient != null && this.mXWalkView != null) {
            return this.mXWalkResourceClient.shouldOverrideUrlLoading(this.mXWalkView, arg3);
        }

        return 0;
    }

    @CalledByNative private void showNotification(String arg7, String arg8, String arg9, Bitmap arg10, int arg11) {
        this.mNotificationService.showNotification(arg7, arg8, arg9, arg10, arg11);
    }
}

