package org.xwalk.core.internal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.net.http.SslCertificate;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View$OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import com.tencent.xweb.statistics.XMemoryDumpStat;
import com.util.RuntimeEnviroment;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.ContextUtils;
import org.chromium.base.LocaleUtils;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.memory.MemoryPressureMonitor;
import org.chromium.components.content_view.ContentView;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.content.browser.ContentViewRenderView$CompositingSurfaceType;
import org.chromium.content.browser.ContentViewRenderView;
import org.chromium.content.browser.ContentViewStatics;
import org.chromium.content.browser.ExtendCanvasCallback;
import org.chromium.content.browser.ExtendPluginCallback;
import org.chromium.content.browser.RenderCoordinates;
import org.chromium.content.common.CleanupReference;
import org.chromium.content_public.browser.ContentBitmapCallback;
import org.chromium.content_public.browser.ContentViewCore$$CC;
import org.chromium.content_public.browser.ContentViewCore$InternalAccessDelegate;
import org.chromium.content_public.browser.ContentViewCore;
import org.chromium.content_public.browser.ImageBitmapToFileFinishedCallback;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.JavascriptInjector$$CC;
import org.chromium.content_public.browser.JavascriptInjector;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationHistory;
import org.chromium.content_public.browser.SelectionClient$$CC;
import org.chromium.content_public.browser.SelectionClient;
import org.chromium.content_public.browser.SelectionMetricsLogger;
import org.chromium.content_public.browser.SelectionPopupController$$CC;
import org.chromium.content_public.browser.SelectionPopupController;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.ZoomController;
import org.chromium.content_public.common.Referrer;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.ActivityWindowAndroid;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.gfx.DeviceDisplayInfo;
import org.json.JSONArray;
import org.xwalk.core.internal.reporter.XWebReporter;

@JNINamespace(value="xwalk") class XWalkContent implements KeyValueChangeListener {
    final class DestroyRunnable implements Runnable {
        private final long mNativeContent;

        DestroyRunnable(long arg1, org.xwalk.core.internal.XWalkContent$1 arg3) {
            this(arg1);
        }

        private DestroyRunnable(long arg1) {
            super();
            this.mNativeContent = arg1;
        }

        public void run() {
            XWalkContent.nativeDestroy(this.mNativeContent);
        }
    }

    public class HitTestData {
        public String anchorText;
        public String hitTestResultExtraData;
        public int hitTestResultType;
        public String href;
        public String imgSrc;

        public HitTestData() {
            super();
        }
    }

    @VisibleForTesting public abstract class VisualStateCallback {
        public VisualStateCallback() {
            super();
        }

        public abstract void onComplete(long arg1);
    }

    public class XWalkContentLifeObserver implements Observer {
        public XWalkContentLifeObserver() {
            super();
        }

        public void onFirstXWalkViewCreated() {
            MemoryPressureMonitor.INSTANCE.enablePolling();
        }

        public void onLastXWalkViewDestroyed() {
            MemoryPressureMonitor.INSTANCE.disablePolling();
            XMemoryDumpStat.getInstance().Stop();
        }
    }

    class XWalkGeolocationCallback extends XWalkGeolocationPermissionsCallbackHandlerInternal {
        XWalkGeolocationCallback(XWalkContent arg1, org.xwalk.core.internal.XWalkContent$1 arg2) {
            this(arg1);
        }

        private XWalkGeolocationCallback(XWalkContent arg1) {
            XWalkContent.this = arg1;
            super();
        }

        public void invoke(String arg2, boolean arg3, boolean arg4) {
            ThreadUtils.runOnUiThread(new Runnable(arg4, arg3, arg2) {
                public void run() {
                    if(this.val$retain) {
                        if(this.val$allow) {
                            this.this$1.this$0.mGeolocationPermissions.allow(this.val$origin);
                        }
                        else {
                            this.this$1.this$0.mGeolocationPermissions.deny(this.val$origin);
                        }
                    }

                    this.this$1.this$0.nativeInvokeGeolocationCallback(this.this$1.this$0.mNativeContent, this.val$allow, this.val$origin);
                }
            });
        }
    }

    class XWalkIoThreadClientImpl extends XWalkContentsIoThreadClient {
        XWalkIoThreadClientImpl(XWalkContent arg1, org.xwalk.core.internal.XWalkContent$1 arg2) {
            this(arg1);
        }

        private XWalkIoThreadClientImpl(XWalkContent arg1) {
            XWalkContent.this = arg1;
            super();
        }

        public int getCacheMode() {
            return XWalkContent.this.mSettings.getCacheMode();
        }

        public void newLoginRequest(String arg2, String arg3, String arg4) {
            XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnReceivedLoginRequest(arg2, arg3, arg4);
        }

        public void onReceivedResponseHeaders(WebResourceRequestInner arg1, XWalkWebResourceResponseInternal arg2) {
        }

        public boolean shouldBlockContentUrls() {
            return XWalkContent.this.mSettings.getAllowContentAccess() ^ 1;
        }

        public boolean shouldBlockFileUrls() {
            return XWalkContent.this.mSettings.getAllowFileAccess() ^ 1;
        }

        public boolean shouldBlockNetworkLoads() {
            return XWalkContent.this.mSettings.getBlockNetworkLoads();
        }

        public XWalkWebResourceResponseInternal shouldInterceptRequest(WebResourceRequestInner arg4) {
            XWalkWebResourceResponseInternal v0 = XWalkContent.this.mContentsClientBridge.shouldInterceptRequest(arg4);
            if(v0 == null) {
                XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnLoadResource(arg4.url);
            }
            else if((arg4.isMainFrame) && v0.getData() == null) {
                XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnReceivedError(arg4, new WebResourceErrorInner());
            }

            return v0;
        }
    }

    private static final String DEFAULT_IMAGE_BITMAP_TO_FILE_TYPE = "jpg";
    public static final String SAVE_RESTORE_STATE_KEY = "XWALKVIEW_STATE";
    private static final int START_CHECK_DISCARDPAGE_PAGE_COUNT = 5;
    public static final int SUPPORT_TRANSPARENT_SDK_VERSION = 190503;
    private static String TAG = "XWalkContent";
    static boolean bMemoryPressureMonitorInit = false;
    private static Class javascriptInterfaceClass = null;
    private boolean mAnimated;
    private final int mAppTargetSdkVersion;
    @ColorInt private int mBgColor;
    private CleanupReference mCleanupReference;
    private XWalkContentView mContentView;
    private ContentViewCore mContentViewCore;
    private ContentViewRenderView mContentViewRenderView;
    private XWalkContentsClientBridge mContentsClientBridge;
    private double mDIPScale;
    private static XWalkDevToolsServer mDevToolsServer = null;
    private XWalkGeolocationPermissions mGeolocationPermissions;
    private ContentBitmapCallback mGetBitmapCallback;
    private boolean mHasGetSupportSmartPickWord;
    private InternalAccessDelegate mInternalAccessAdapter;
    private XWalkContentsIoThreadClient mIoThreadClient;
    private boolean mIsLoaded;
    private JavascriptInjector mJavascriptInjector;
    private XWalkLaunchScreenManager mLaunchScreenManager;
    long mNativeContent;
    private NavigationController mNavigationController;
    private final HitTestData mPossiblyStaleHitTestData;
    private SelectionClient mSelectionClient;
    private XWalkSettingsInternal mSettings;
    private boolean mSupportSmartPickWord;
    private XWalkViewAndroidDelegate mViewAndroidDelegate;
    private Context mViewContext;
    private WebContents mWebContents;
    private XWalkWebContentsObserver mWebContentsObserver;
    private WindowAndroid mWindow;
    private XWalkAutofillClientAndroid mXWalkAutofillClient;
    private XWalkWebContentsDelegateAdapter mXWalkContentsDelegateAdapter;
    private XWalkExtendCanvasClientInternal mXWalkExtendCanvasClient;
    private XWalkExtendPluginClientInternal mXWalkExtendPluginClient;
    private XWalkGetBitmapCallbackInternal mXWalkGetBitmapCallbackInternal;
    private XWalkProxyWebViewClientExtensionInternal mXWalkProxyWebViewClientExtension;
    private XWalkViewInternal mXWalkView;
    private static String sCurrentLocales = "";
    private static ArrayList sListAllXWebs = null;
    private static boolean timerPaused = false;

    static {
        XWalkContent.sListAllXWebs = new ArrayList();
    }

    public XWalkContent(Context arg3, String arg4, XWalkViewInternal arg5) {
        super();
        this.mIsLoaded = false;
        this.mAnimated = false;
        this.mPossiblyStaleHitTestData = new HitTestData();
        this.mSupportSmartPickWord = false;
        this.mHasGetSupportSmartPickWord = false;
        this.mBgColor = -1;
        XWalkContent.addToAllWebviewList(this);
        XWalkContent.tryResycle();
        this.mXWalkView = arg5;
        this.mViewContext = this.mXWalkView.getContext();
        this.mContentsClientBridge = new XWalkContentsClientBridge(this.mXWalkView, this.mContentViewCore);
        this.mXWalkContentsDelegateAdapter = new XWalkWebContentsDelegateAdapter(this.mContentsClientBridge, this.mXWalkView);
        this.mIoThreadClient = new XWalkIoThreadClientImpl(this, null);
        this.mWindow = new ActivityWindowAndroid(arg3);
        this.mViewAndroidDelegate = new XWalkViewAndroidDelegate(this.mContentView, this.mContentsClientBridge);
        this.mGeolocationPermissions = XWalkViewDelegate.getGeoLocationPermissions();
        XMemoryDumpStat.getInstance().addView();
        this.setNativeContent(this.nativeInit(), arg4);
        this.mWindow.setWebviewUI(this.mXWalkView);
        this.mWindow.setActivityChangedCallBack(new ValueCallback() {
            public void onReceiveValue(Object arg1) {
                this.onReceiveValue(((String)arg1));
            }

            public void onReceiveValue(String arg2) {
                if(XWalkContent.access$200(XWalkContent.this) == null) {
                    return;
                }

                if(arg2.equals("STOPPED")) {
                    XWalkContent.access$200(XWalkContent.this).triggerCreated();
                }
                else if(arg2.equals("PAUSED")) {
                    XMemoryDumpStat.getInstance().onPause();
                    MemoryPressureListener.notifyMemoryPresure();
                }
                else if(arg2.equals("RESUMED")) {
                    XWalkContent.access$200(XWalkContent.this).triggerCreated();
                    XMemoryDumpStat.getInstance().onResume();
                }
                else {
                    arg2.equals("STARTED");
                }
            }
        });
        XWalkPreferencesInternal.load(((KeyValueChangeListener)this));
        this.mAppTargetSdkVersion = ContextUtils.getApplicationContext().getApplicationInfo().targetSdkVersion;
        this.initCaptureBitmapAsync();
        XWalkContent.updateDefaultLocale();
        if(!XWalkContent.bMemoryPressureMonitorInit) {
            MemoryPressureMonitor.INSTANCE.registerComponentCallbacks();
            XWalkContentLifecycleNotifier.addObserver(new XWalkContentLifeObserver());
            XWalkContent.bMemoryPressureMonitorInit = true;
        }
    }

    public void SetHorizontalScrollBarEnable(boolean arg2) {
        if(this.mContentViewCore == null) {
            return;
        }

        this.mContentViewCore.SetHorizontalScrollBarEnable(arg2);
    }

    public void SetVerticalScrollBarEnable(boolean arg2) {
        if(this.mContentViewCore == null) {
            return;
        }

        this.mContentViewCore.SetVerticalScrollBarEnable(arg2);
    }

    static void access$000(long arg0) {
        XWalkContent.nativeDestroy(arg0);
    }

    static XWalkContentsClientBridge access$1000(XWalkContent arg0) {
        return arg0.mContentsClientBridge;
    }

    static ContentViewCore access$1100(XWalkContent arg0) {
        return arg0.mContentViewCore;
    }

    static int access$1200(XWalkContent arg0) {
        return arg0.mBgColor;
    }

    static XWalkSettingsInternal access$1300(XWalkContent arg0) {
        return arg0.mSettings;
    }

    static XWalkGeolocationPermissions access$1400(XWalkContent arg0) {
        return arg0.mGeolocationPermissions;
    }

    static void access$1500(XWalkContent arg0, long arg1, boolean arg3, String arg4) {
        arg0.nativeInvokeGeolocationCallback(arg1, arg3, arg4);
    }

    static ContentViewRenderView access$200(XWalkContent arg0) {
        return arg0.mContentViewRenderView;
    }

    static String access$300() {
        return XWalkContent.TAG;
    }

    static XWalkExtendPluginClientInternal access$400(XWalkContent arg0) {
        return arg0.mXWalkExtendPluginClient;
    }

    static XWalkExtendCanvasClientInternal access$500(XWalkContent arg0) {
        return arg0.mXWalkExtendCanvasClient;
    }

    static XWalkProxyWebViewClientExtensionInternal access$700(XWalkContent arg0) {
        return arg0.mXWalkProxyWebViewClientExtension;
    }

    static boolean access$800(XWalkContent arg0) {
        return arg0.mSupportSmartPickWord;
    }

    static WebContents access$900(XWalkContent arg0) {
        return arg0.mWebContents;
    }

    @SuppressLint(value={"NewApi"}) public void addJavascriptInterface(Object arg6, String arg7) {
        if(this.mNativeContent == 0) {
            return;
        }

        Class v0 = null;
        if(this.mAppTargetSdkVersion >= 17) {
            v0 = JavascriptInterface.class;
        }

        this.getJavascriptInjector().addPossiblyUnsafeInterface(arg6, arg7, v0);
    }

    private static void addToAllWebviewList(XWalkContent arg3) {
        Class v0 = XWalkContent.class;
        __monitor_enter(v0);
        try {
            XWalkContent.sListAllXWebs.add(new WeakReference(arg3));
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    public void adjustSelectPosition(long arg8, String arg10, int arg11, int arg12) {
        String v0 = XWalkContent.TAG;
        Log.e(v0, "adjustSelectPosition: " + arg10);
        SelectionPopupController v1_1 = SelectionPopupController$$CC.fromWebContents$$STATIC$$(this.mWebContents);
        if(v1_1 != null) {
            v1_1.adjustSelectPosition(arg8, arg10, arg11, arg12);
        }
    }

    public boolean canGoBack() {
        boolean v0 = this.mNativeContent == 0 ? false : this.mNavigationController.canGoBack();
        return v0;
    }

    public boolean canGoForward() {
        boolean v0 = this.mNativeContent == 0 ? false : this.mNavigationController.canGoForward();
        return v0;
    }

    public boolean canZoomIn() {
        if(this.mNativeContent == 0) {
            return 0;
        }

        return ZoomController.canZoomIn(this.mWebContents);
    }

    public boolean canZoomOut() {
        if(this.mNativeContent == 0) {
            return 0;
        }

        return ZoomController.canZoomOut(this.mWebContents);
    }

    public void captureBitmapAsync(XWalkGetBitmapCallbackInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mXWalkGetBitmapCallbackInternal = arg6;
        this.mWebContents.getContentBitmapAsync(0, 0, this.mGetBitmapCallback);
    }

    public void clearCache(boolean arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.nativeClearCache(this.mNativeContent, arg6);
    }

    public void clearCacheForSingleFile(String arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        if(!this.mIsLoaded) {
            this.mXWalkView.post(new Runnable(arg6) {
                public void run() {
                    XWalkContent.this.clearCacheForSingleFile(this.val$url);
                }
            });
            return;
        }

        this.nativeClearCacheForSingleFile(this.mNativeContent, arg6);
    }

    public void clearClientCertPreferences(Runnable arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.clearClientCertPreferences(arg6);
    }

    public void clearHistory() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mNavigationController.clearHistory();
    }

    public void clearMatches() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.nativeClearMatches(this.mNativeContent);
    }

    public void clearSslPreferences() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mNavigationController.clearSslPreferences();
    }

    public int computeHorizontalScrollOffset() {
        return this.mContentView.computeHorizontalScrollOffsetDelegate();
    }

    public int computeHorizontalScrollRange() {
        return this.mContentView.computeHorizontalScrollRangeDelegate();
    }

    public int computeVerticalScrollExtent() {
        return this.mContentView.computeVerticalScrollExtentDelegate();
    }

    public int computeVerticalScrollOffset() {
        return this.mContentView.computeVerticalScrollOffsetDelegate();
    }

    public int computeVerticalScrollRange() {
        return this.mContentView.computeVerticalScrollRangeDelegate();
    }

    public void destroy() {
        long v2 = 0;
        if(this.mNativeContent == v2) {
            return;
        }

        XWalkPreferencesInternal.unload(((KeyValueChangeListener)this));
        XWalkNotificationService v0 = null;
        this.setNotificationService(v0);
        this.mContentsClientBridge.getCallbackHelper().removeCallbacksAndMessages();
        this.mXWalkView.removeView(this.mContentView);
        this.mXWalkView.removeView(this.mContentViewRenderView);
        XMemoryDumpStat.getInstance().relesaeView();
        this.mSettings.setWebContents(((WebContents)v0));
        this.mWebContentsObserver.destroy();
        this.mWebContentsObserver = ((XWalkWebContentsObserver)v0);
        this.mContentViewCore.destroy();
        this.mContentViewCore = ((ContentViewCore)v0);
        if(this.mWindow != null) {
            this.mWindow.setWebviewUI(((FrameLayout)v0));
            this.mWindow.setActivityChangedCallBack(((ValueCallback)v0));
        }

        this.mCleanupReference.cleanupNow();
        this.mContentViewRenderView.destroy();
        this.mCleanupReference = ((CleanupReference)v0);
        this.mNativeContent = v2;
    }

    public String devToolsAgentId() {
        if(this.mNativeContent == 0) {
            return "";
        }

        return this.nativeDevToolsAgentId(this.mNativeContent);
    }

    void disableRemoteDebugging() {
        if(XWalkContent.mDevToolsServer == null) {
            return;
        }

        if(XWalkContent.mDevToolsServer.isRemoteDebuggingEnabled()) {
            XWalkContent.mDevToolsServer.setRemoteDebuggingEnabled(false);
        }

        XWalkContent.mDevToolsServer.destroy();
        XWalkContent.mDevToolsServer = null;
    }

    private void doLoadUrl(LoadUrlParams arg7) {
        Object v3;
        Map v0 = arg7.getExtraHeaders();
        if(v0 != null) {
            Iterator v2 = v0.keySet().iterator();
            do {
                if(v2.hasNext()) {
                    v3 = v2.next();
                    if(!"referer".equals(((String)v3).toLowerCase(Locale.US))) {
                        continue;
                    }

                    break;
                }

                goto label_18;
            }
            while(true);

            arg7.setReferrer(new Referrer(v0.remove(v3), 1));
            arg7.setExtraHeaders(v0);
        }

    label_18:
        arg7.setOverrideUserAgent(2);
        this.mNavigationController.loadUrl(arg7);
        this.mIsLoaded = true;
        this.postSetBackgroundColor();
    }

    public void enableRemoteDebugging() {
        String v0_1 = this.mViewContext.getApplicationContext().getPackageName() + "_devtools_remote" + Process.myPid();
        if(XWalkContent.mDevToolsServer == null) {
            XWalkContent.mDevToolsServer = new XWalkDevToolsServer(v0_1);
            XWalkContent.mDevToolsServer.setRemoteDebuggingEnabled(true, Security.ALLOW_SOCKET_ACCESS);
        }
    }

    public void evaluateJavascript(String arg6, ValueCallback arg7) {
        if(this.mNativeContent == 0) {
            return;
        }

        JavaScriptCallback v0 = null;
        if(arg7 != null) {
            org.xwalk.core.internal.XWalkContent$9 v0_1 = new JavaScriptCallback(arg7) {
                public void handleJavaScriptResult(String arg2) {
                    this.val$fCallback.onReceiveValue(arg2);
                }
            };
        }

        this.mContentViewCore.getWebContents().evaluateJavaScript(arg6, v0);
    }

    void exitFullscreen() {
        if(this.hasEnteredFullscreen()) {
            this.mWebContents.exitFullscreen();
        }
    }

    public void findAllAsync(String arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.nativeFindAllAsync(this.mNativeContent, arg6);
    }

    public void findNext(boolean arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.nativeFindNext(this.mNativeContent, arg6);
    }

    private static String fixupBase(String arg1) {
        if(TextUtils.isEmpty(((CharSequence)arg1))) {
            arg1 = "about:blank";
        }

        return arg1;
    }

    private static String fixupData(String arg1) {
        if(TextUtils.isEmpty(((CharSequence)arg1))) {
            arg1 = "";
        }

        return arg1;
    }

    private static String fixupHistory(String arg1) {
        if(TextUtils.isEmpty(((CharSequence)arg1))) {
            arg1 = "about:blank";
        }

        return arg1;
    }

    private static String fixupMimeType(String arg1) {
        if(TextUtils.isEmpty(((CharSequence)arg1))) {
            arg1 = "text/html";
        }

        return arg1;
    }

    private static ArrayList getAllAliveXWalkView() {
        int v2;
        ArrayList v1_1;
        Class v0 = XWalkContent.class;
        __monitor_enter(v0);
        try {
            v1_1 = new ArrayList();
            v2 = 0;
            while(true) {
            label_5:
                if(v2 >= XWalkContent.sListAllXWebs.size()) {
                    goto label_15;
                }

                Object v3 = XWalkContent.sListAllXWebs.get(v2).get();
                if(v3 != null) {
                    v1_1.add(v3);
                }

                break;
            }
        }
        catch(Throwable v1) {
            goto label_18;
        }

        ++v2;
        goto label_5;
    label_15:
        __monitor_exit(v0);
        return v1_1;
    label_18:
        __monitor_exit(v0);
        throw v1;
    }

    public SslCertificate getCertificate() {
        if(this.mNativeContent == 0) {
            return null;
        }

        return SslUtil.getCertificateFromDerBytes(this.nativeGetCertificate(this.mNativeContent));
    }

    public String getCompositingSurfaceType() {
        if(this.mNativeContent == 0) {
            return null;
        }

        String v0 = this.mAnimated ? "TextureView" : "SurfaceView";
        return v0;
    }

    public int getContentHeight() {
        return ((int)Math.ceil(((double)this.mWebContents.getRenderCoordinates().getContentHeightCss())));
    }

    public ContentView getContentView() {
        return this.mContentView;
    }

    public ContentViewCore getContentViewCoreForTest() {
        return this.mContentViewCore;
    }

    public Bitmap getFavicon() {
        if(this.mNativeContent == 0) {
            return null;
        }

        return this.mContentsClientBridge.getFavicon();
    }

    public boolean getImageBitmapToFile(String arg7, String arg8, String arg9, XWalkGetImageBitmapToFileFinishedCallbackInternal arg10) {
        if(this.mNativeContent == 0) {
            return 0;
        }

        if(arg7 == null) {
            arg7 = "";
        }

        String v1 = arg7;
        if(arg8 == null) {
            arg8 = "";
        }

        String v3 = arg8;
        if(arg9 == null) {
            arg9 = "";
        }

        XWebReporter.onGetImageBitmapToFile();
        arg7 = XWalkContent.TAG;
        org.xwalk.core.internal.Log.i(arg7, "getImageBitmapToFile imageUrl:" + v1 + ", filePath:" + v3 + ", extraInfo:" + arg9);
        this.mWebContents.getImageBitmapToFile(v1, "jpg", v3, arg9, new ImageBitmapToFileFinishedCallback(System.currentTimeMillis(), arg10) {
            public void onFinishImageBitmapToFile(int arg9, String arg10, String arg11, int arg12, int arg13, String arg14) {
                long v6 = System.currentTimeMillis() - this.val$timestamp;
                if(arg9 == 0) {
                    XWebReporter.onGetImageBitmapToFileSuccessful();
                    XWebReporter.onGetImageBitmapToFileFinish(v6);
                }
                else {
                    XWebReporter.onGetImageBitmapToFileFailed();
                }

                String v0 = XWalkContent.TAG;
                org.xwalk.core.internal.Log.i(v0, "getImageBitmapToFile callback time:" + v6);
                XWebReporter.onGetImageBitmapToFileForKV(arg9, v6);
                this.val$callback.onFinishImageBitmapToFile(arg9, arg10, arg11, arg12, arg13, arg14);
            }
        });
        return 1;
    }

    private JavascriptInjector getJavascriptInjector() {
        if(this.mJavascriptInjector == null) {
            this.mJavascriptInjector = JavascriptInjector$$CC.fromWebContents$$STATIC$$(this.mWebContents);
        }

        return this.mJavascriptInjector;
    }

    public HitTestData getLastHitTestResult() {
        if(this.mNativeContent == 0) {
            return null;
        }

        this.nativeUpdateLastHitTestData(this.mNativeContent);
        return this.mPossiblyStaleHitTestData;
    }

    public XWalkNavigationHistoryInternal getNavigationHistory() {
        if(this.mNativeContent == 0) {
            return null;
        }

        return new XWalkNavigationHistoryInternal(this.mXWalkView, this.mNavigationController.getNavigationHistory());
    }

    public String getOriginalUrl() {
        String v0 = null;
        if(Long.compare(this.mNativeContent, 0) == 0) {
            return v0;
        }

        NavigationHistory v1 = this.mNavigationController.getNavigationHistory();
        if(v1 != null) {
            int v2 = v1.getCurrentEntryIndex();
            if(v2 >= 0 && v2 < v1.getEntryCount()) {
                return v1.getEntryAtIndex(v2).getOriginalUrl();
            }
        }

        return v0;
    }

    public String getRefererUrl() {
        String v0 = null;
        if(Long.compare(this.mNativeContent, 0) == 0) {
            return v0;
        }

        String v1 = this.mWebContents.getRefererUrl();
        if(v1 != null) {
            if(v1.trim().isEmpty()) {
            }
            else {
                return v1;
            }
        }

        return v0;
    }

    public String getRemoteDebuggingUrl() {
        if(XWalkContent.mDevToolsServer == null) {
            return "";
        }

        return "ws://" + XWalkContent.mDevToolsServer.getSocketName() + "/devtools/page/" + this.devToolsAgentId();
    }

    public int getRoutingID() {
        return this.nativeGetRoutingID(this.mNativeContent);
    }

    public float getScale() {
        if(this.mNativeContent == 0) {
            return 1f;
        }

        RenderCoordinates v0 = this.mWebContents.getRenderCoordinates();
        return v0.getPageScaleFactor() * v0.getDeviceScaleFactor();
    }

    public XWalkSettingsInternal getSettings() {
        return this.mSettings;
    }

    public void getSupportSmartPickWord() {
        if(this.mXWalkProxyWebViewClientExtension != null) {
            Object v0 = this.mXWalkProxyWebViewClientExtension.onMiscCallBack("supportSmartPickWord", new Bundle());
            if((v0 instanceof Boolean)) {
                boolean v0_1 = ((Boolean)v0).booleanValue();
                String v1 = XWalkContent.TAG;
                org.xwalk.core.internal.Log.i(v1, "smartPickWord royle supportSmartPickWord:" + v0_1);
                this.mSupportSmartPickWord = v0_1;
                this.mSettings.setSupportSmartPickWord(this.mSupportSmartPickWord);
            }
            else {
                org.xwalk.core.internal.Log.i(XWalkContent.TAG, "smartPickWord royle supportSmartPickWord not get");
            }
        }
    }

    public String getTitle() {
        if(this.mNativeContent == 0) {
            return null;
        }

        String v0 = this.mWebContents.getTitle().trim();
        if(v0 == null) {
            v0 = "";
        }

        return v0;
    }

    public void getTranslateSampleString(int arg8) {
        if(this.mWebContents != null) {
            this.mWebContents.getTranslateSampleString(arg8);
            XWebReporter.idkeyStat(938, 0x60, 1);
        }
    }

    public String getUrl() {
        String v0 = null;
        if(Long.compare(this.mNativeContent, 0) == 0) {
            return v0;
        }

        String v1 = this.mWebContents.getVisibleUrl();
        if(v1 != null) {
            if(v1.trim().isEmpty()) {
            }
            else {
                return v1;
            }
        }

        return v0;
    }

    public String getXWalkVersion() {
        if(this.mNativeContent == 0) {
            return "";
        }

        return this.nativeGetVersion(this.mNativeContent);
    }

    public XWalkWebChromeClient getXWalkWebChromeClient() {
        if(this.mNativeContent == 0) {
            return null;
        }

        return this.mContentsClientBridge.getXWalkWebChromeClient();
    }

    public void goBack() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mNavigationController.goBack();
    }

    public void goForward() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mNavigationController.goForward();
    }

    boolean hasEnteredFullscreen() {
        return this.mContentsClientBridge.hasEnteredFullscreen();
    }

    public boolean hasPermission(String arg6) {
        if(this.mNativeContent == 0) {
            return 0;
        }

        return this.mWindow.hasPermission(arg6);
    }

    public void hideAutofillPopup() {
        if(this.mNativeContent == 0) {
            return;
        }

        if(!this.mIsLoaded) {
            this.mXWalkView.post(new Runnable() {
                public void run() {
                    XWalkContent.this.hideAutofillPopup();
                }
            });
            return;
        }

        if(this.mXWalkAutofillClient != null) {
            this.mXWalkAutofillClient.hideAutofillPopup();
        }
    }

    private void initCaptureBitmapAsync() {
        this.mGetBitmapCallback = new XWalkContent$$Lambda$0(this);
    }

    public void insertVisualStateCallback(long arg12, VisualStateCallback arg14) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.nativeInsertVisualStateCallback(this.mNativeContent, arg12, arg14);
    }

    private void installWebContentsObserver() {
        if(this.mWebContentsObserver != null) {
            this.mWebContentsObserver.destroy();
        }

        this.mWebContentsObserver = new XWalkWebContentsObserver(this.mWebContents, this, this.mContentsClientBridge);
    }

    @CalledByNative public void invokeVisualStateCallback(VisualStateCallback arg3, long arg4) {
        this.mXWalkView.post(new Runnable(arg3, arg4) {
            public void run() {
                this.val$callback.onComplete(this.val$requestId);
            }
        });
    }

    private static boolean isBase64Encoded(String arg1) {
        return "base64".equals(arg1);
    }

    private boolean isOpaque(int arg2) {
        boolean v2 = (arg2 >> 24 & 0xFF) == 0xFF ? true : false;
        return v2;
    }

    public boolean isSearchable() {
        return this.mSelectionClient.isSearchable();
    }

    public boolean isSelectActionModeAllowed(int arg2) {
        boolean v2 = (this.mSettings.getDisabledActionModeMenuItems() & arg2) != arg2 ? true : false;
        return v2;
    }

    final void lambda$initCaptureBitmapAsync$0$XWalkContent(Bitmap arg3) {
        if(this.mXWalkGetBitmapCallbackInternal == null) {
            return;
        }

        XWalkGetBitmapCallbackInternal v0 = this.mXWalkGetBitmapCallbackInternal;
        int v1 = arg3 == null ? -1 : 0;
        v0.onFinishGetBitmap(arg3, v1);
    }

    public void loadAppFromManifest(String arg1, String arg2) {
    }

    public void loadData(String arg6, String arg7, String arg8) {
        if(this.mNativeContent == 0) {
            return;
        }

        if(TextUtils.isEmpty(((CharSequence)arg6))) {
            arg6 = "";
        }

        if(TextUtils.isEmpty(((CharSequence)arg7))) {
            arg7 = "text/html";
        }

        this.doLoadUrl(LoadUrlParams.createLoadDataParams(XWalkContent.fixupData(arg6), XWalkContent.fixupMimeType(arg7), XWalkContent.isBase64Encoded(arg8)));
    }

    public void loadDataWithBaseURL(String arg7, String arg8, String arg9, String arg10, String arg11) {
        LoadUrlParams v7_1;
        if(this.mNativeContent == 0) {
            return;
        }

        arg8 = XWalkContent.fixupData(arg8);
        String v1 = XWalkContent.fixupMimeType(arg9);
        String v3 = XWalkContent.fixupBase(arg7);
        String v4 = XWalkContent.fixupHistory(arg11);
        try {
            v7_1 = LoadUrlParams.createLoadDataParamsWithBaseUrl(Base64.encodeToString(arg8.getBytes("utf-8"), 0), v1, true, v3, v4, "utf-8");
        }
        catch(UnsupportedEncodingException v7) {
            arg9 = XWalkContent.TAG;
            org.xwalk.core.internal.Log.w(arg9, "Unable to load data string " + arg8, ((Throwable)v7));
            return;
        }

        this.doLoadUrl(v7_1);
    }

    public void loadUrl(String arg6, Map arg7) {
        if(this.mNativeContent == 0) {
            return;
        }

        LoadUrlParams v0 = new LoadUrlParams(arg6);
        if(arg7 != null) {
            v0.setExtraHeaders(arg7);
        }

        this.doLoadUrl(v0);
    }

    public void loadUrl(String arg2) {
        if(arg2 == null) {
            return;
        }

        this.loadUrl(arg2, null);
    }

    private native void nativeClearCache(long arg1, boolean arg2) {
    }

    private native void nativeClearCacheForSingleFile(long arg1, String arg2) {
    }

    private native void nativeClearMatches(long arg1) {
    }

    private static native void nativeDestroy(long arg0) {
    }

    private native String nativeDevToolsAgentId(long arg1) {
    }

    private native void nativeFindAllAsync(long arg1, String arg2) {
    }

    private native void nativeFindNext(long arg1, boolean arg2) {
    }

    private native byte[] nativeGetCertificate(long arg1) {
    }

    private native int nativeGetRoutingID(long arg1) {
    }

    private native byte[] nativeGetState(long arg1) {
    }

    private native String nativeGetVersion(long arg1) {
    }

    private native WebContents nativeGetWebContents(long arg1) {
    }

    private native long nativeInit() {
    }

    private native void nativeInsertVisualStateCallback(long arg1, long arg2, VisualStateCallback arg3) {
    }

    private native void nativeInvokeGeolocationCallback(long arg1, boolean arg2, String arg3) {
    }

    private native long nativeReleasePopupXWalkContent(long arg1) {
    }

    private native void nativeRequestNewHitTestDataAt(long arg1, float arg2, float arg3, float arg4) {
    }

    private native void nativeSetBackgroundColor(long arg1, int arg2) {
    }

    private native void nativeSetJavaPeers(long arg1, XWalkContent arg2, XWalkWebContentsDelegateAdapter arg3, XWalkContentsClientBridge arg4, XWalkContentsIoThreadClient arg5, InterceptNavigationDelegate arg6) {
    }

    private native void nativeSetJsOnlineProperty(long arg1, boolean arg2) {
    }

    private native boolean nativeSetManifest(long arg1, String arg2, String arg3) {
    }

    private native void nativeSetOriginAccessWhitelist(long arg1, String arg2, String arg3) {
    }

    private native boolean nativeSetState(long arg1, byte[] arg2) {
    }

    private native void nativeSmoothScroll(long arg1, int arg2, int arg3, long arg4) {
    }

    private static native void nativeUpdateDefaultLocale(String arg0, String arg1) {
    }

    private native void nativeUpdateLastHitTestData(long arg1) {
    }

    void navigateTo(int arg2) {
        this.mNavigationController.goToOffset(arg2);
    }

    public void onBaseContextChanged() {
        if(this.mWindow != null && ((this.mWindow instanceof ActivityWindowAndroid))) {
            this.mWindow.onBaseContextChanged();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return this.mContentView.onCreateInputConnectionSuper(arg2);
    }

    @CalledByNative public void onFindResultReceived(int arg2, int arg3, boolean arg4) {
        this.mContentsClientBridge.onFindResultReceived(arg2, arg3, arg4);
    }

    @CalledByNative public void onGeolocationPermissionsHidePrompt() {
        this.mContentsClientBridge.onGeolocationPermissionsHidePrompt();
    }

    @CalledByNative private void onGeolocationPermissionsShowPrompt(String arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        if(!this.mSettings.getGeolocationEnabled()) {
            this.nativeInvokeGeolocationCallback(this.mNativeContent, false, arg6);
            return;
        }

        if(this.mGeolocationPermissions.hasOrigin(arg6)) {
            this.nativeInvokeGeolocationCallback(this.mNativeContent, this.mGeolocationPermissions.isOriginAllowed(arg6), arg6);
            return;
        }

        this.mContentsClientBridge.onGeolocationPermissionsShowPrompt(arg6, new XWalkGeolocationCallback(this, null));
    }

    @CalledByNative public void onGetFullscreenFlagFromManifest(boolean arg3) {
        if(!(WindowAndroid.activityFromContext(this.mXWalkView.getContext()) instanceof Activity)) {
            return;
        }

        Activity v0 = WindowAndroid.activityFromContext(this.mXWalkView.getContext());
        if(arg3) {
            if(Build$VERSION.SDK_INT >= 19) {
                v0.getWindow().getDecorView().setSystemUiVisibility(0x1706);
            }
            else {
                v0.getWindow().addFlags(0x400);
            }
        }
    }

    @CalledByNative public void onGetUrlAndLaunchScreenFromManifest(String arg2, String arg3, String arg4) {
        if(arg2 != null) {
            if(arg2.isEmpty()) {
            }
            else {
                this.mLaunchScreenManager.displayLaunchScreen(arg3, arg4);
                this.mContentsClientBridge.registerPageLoadListener(this.mLaunchScreenManager);
                this.loadUrl(arg2);
                return;
            }
        }
    }

    @CalledByNative public void onGetUrlFromManifest(String arg2) {
        if(arg2 != null && !arg2.isEmpty()) {
            this.loadUrl(arg2);
        }
    }

    public void onKeyValueChanged(String arg2, PreferenceValue arg3) {
        if(arg2 == null) {
            return;
        }

        if(arg2.equals("remote-debugging")) {
            if(arg3.getBooleanValue()) {
                this.enableRemoteDebugging();
            }
            else {
                this.disableRemoteDebugging();
            }
        }
        else if(arg2.equals("enable-javascript")) {
            if(this.mSettings != null) {
                this.mSettings.setJavaScriptEnabled(arg3.getBooleanValue());
            }
        }
        else if(arg2.equals("javascript-can-open-window")) {
            if(this.mSettings != null) {
                this.mSettings.setJavaScriptCanOpenWindowsAutomatically(arg3.getBooleanValue());
            }
        }
        else if(arg2.equals("allow-universal-access-from-file")) {
            if(this.mSettings != null) {
                this.mSettings.setAllowUniversalAccessFromFileURLs(arg3.getBooleanValue());
            }
        }
        else if(arg2.equals("support-multiple-windows")) {
            if(this.mSettings != null) {
                this.mSettings.setSupportMultipleWindows(arg3.getBooleanValue());
            }
        }
        else if((arg2.equals("enable-spatial-navigation")) && this.mSettings != null) {
            this.mSettings.setSupportSpatialNavigation(arg3.getBooleanValue());
        }
    }

    public boolean onNewIntent(Intent arg6) {
        if(this.mNativeContent == 0) {
            return 0;
        }

        return this.mContentsClientBridge.onNewIntent(arg6);
    }

    public void onPause() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mWebContents.onHide();
    }

    public void onResume() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mWebContents.onShow();
    }

    public void onShowSos(boolean arg2) {
        this.mSelectionClient.onShowSos(arg2);
    }

    public boolean onTouchEvent(MotionEvent arg10) {
        if(this.mContentViewCore == null) {
            return 0;
        }

        if(!this.mContentViewCore.isAlive()) {
            return 0;
        }

        if(!this.mHasGetSupportSmartPickWord) {
            this.mHasGetSupportSmartPickWord = true;
            this.getSupportSmartPickWord();
        }

        if(arg10.getActionMasked() == 0) {
            this.nativeRequestNewHitTestDataAt(this.mNativeContent, arg10.getX() / (((float)this.mDIPScale)), arg10.getY() / (((float)this.mDIPScale)), arg10.getTouchMajor() / (((float)this.mDIPScale)));
        }

        if((this.hasEnteredFullscreen()) && (this.mContentsClientBridge.hasEnteredVideoFullscreen()) && arg10.getActionMasked() == 2) {
            return 1;
        }

        return this.mContentViewCore.getWebContents().getEventForwarder().onTouchEvent(arg10);
    }

    public void pauseTimers() {
        if(!XWalkContent.timerPaused) {
            if(this.mNativeContent == 0) {
            }
            else {
                ContentViewStatics.setWebKitSharedTimersSuspended(true);
                XWalkContent.timerPaused = true;
                return;
            }
        }
    }

    private void postSetBackgroundColor() {
        this.mXWalkView.post(new Runnable() {
            public void run() {
                XWalkContent.this.setBackgroundColor(XWalkContent.this.mBgColor);
            }
        });
    }

    public void preInitViewSize(int arg6, int arg7, boolean arg8) {
        if(this.mNativeContent == 0) {
            return;
        }

        if(this.mContentViewRenderView != null) {
            this.mContentViewRenderView.fakeRenderForPreload(arg6, arg7, arg8);
        }
    }

    private void receivePopupContents(long arg2) {
        this.setNativeContent(arg2, null);
        this.mWebContents.onShow();
    }

    public void reload(int arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        if(arg6 != 1) {
            this.mNavigationController.reload(true);
        }
        else {
            this.mNavigationController.reloadBypassingCache(true);
        }

        this.mIsLoaded = true;
        this.postSetBackgroundColor();
    }

    public static void removeFromAllWebList(XWalkContent arg3) {
        Class v0 = XWalkContent.class;
        __monitor_enter(v0);
        int v1 = 0;
        try {
            while(true) {
            label_3:
                if(v1 >= XWalkContent.sListAllXWebs.size()) {
                    goto label_16;
                }

                if(XWalkContent.sListAllXWebs.get(v1).get() != arg3) {
                    goto label_14;
                }

                XWalkContent.sListAllXWebs.remove(v1);
                break;
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
        return;
    label_14:
        ++v1;
        goto label_3;
    label_16:
        __monitor_exit(v0);
    }

    public void removeJavascriptInterface(String arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.getJavascriptInjector().removeInterface(arg6);
    }

    public void replaceTranslatedString(Map arg8) {
        if(this.mWebContents != null && arg8 != null) {
            this.mWebContents.replaceTranslatedString(arg8);
            XWebReporter.idkeyStat(938, 100, 1);
        }
    }

    public XWalkNavigationHistoryInternal restoreState(Bundle arg6) {
        XWalkNavigationHistoryInternal v0 = null;
        if(Long.compare(this.mNativeContent, 0) != 0) {
            if(arg6 == null) {
            }
            else {
                byte[] v6 = arg6.getByteArray("XWALKVIEW_STATE");
                if(v6 == null) {
                    return v0;
                }
                else {
                    boolean v6_1 = this.nativeSetState(this.mNativeContent, v6);
                    if(v6_1) {
                        this.mContentsClientBridge.onReceivedTitle(this.mWebContents.getTitle());
                    }

                    if(v6_1) {
                        v0 = this.getNavigationHistory();
                    }

                    return v0;
                }
            }
        }

        return v0;
    }

    public void resumeTimers() {
        if(XWalkContent.timerPaused) {
            if(this.mNativeContent == 0) {
            }
            else {
                ContentViewStatics.setWebKitSharedTimersSuspended(false);
                XWalkContent.timerPaused = false;
                return;
            }
        }
    }

    public boolean savePage(String arg6, String arg7, int arg8) {
        if(this.mNativeContent == 0) {
            return 0;
        }

        return this.mWebContents.savePage(arg6, arg7, arg8);
    }

    public XWalkNavigationHistoryInternal saveState(Bundle arg6) {
        XWalkNavigationHistoryInternal v0 = null;
        if(Long.compare(this.mNativeContent, 0) != 0) {
            if(arg6 == null) {
            }
            else {
                byte[] v1 = this.nativeGetState(this.mNativeContent);
                if(v1 == null) {
                    return v0;
                }
                else {
                    arg6.putByteArray("XWALKVIEW_STATE", v1);
                    return this.getNavigationHistory();
                }
            }
        }

        return v0;
    }

    public void scrollBy(int arg2, int arg3) {
        this.mContentView.scrollBy(arg2, arg3);
    }

    public void scrollTo(int arg2, int arg3) {
        this.mContentView.scrollTo(arg2, arg3);
    }

    @CalledByNative public void setBackgroundColor(int arg6) {
        int v0 = Color.alpha(arg6) == 0xFF ? 1 : 0;
        if(RuntimeEnviroment.getXWebSDKVersion() >= 190503 && this.mContentViewRenderView != null && v0 == 0) {
            org.xwalk.core.internal.Log.i(XWalkContent.TAG, "andrewu the color will be transparent set to the root layer");
            this.mContentViewRenderView.setRenderViewBackground(arg6);
            this.mContentViewRenderView.setSurfaceViewBackgroundColor(arg6);
        }

        if(this.mNativeContent == 0) {
            return;
        }

        this.mBgColor = arg6;
        if(!this.mIsLoaded) {
            return;
        }

        this.mContentViewRenderView.setSurfaceViewBackgroundColor(arg6);
        this.nativeSetBackgroundColor(this.mNativeContent, arg6);
    }

    public void setBottomHeight(int arg2) {
        this.mWebContents.setBottomHeight(arg2);
    }

    public void setDownloadListener(XWalkDownloadListenerInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setDownloadListener(arg6);
    }

    public void setExtendCanvasClient(XWalkExtendCanvasClientInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mXWalkExtendCanvasClient = arg6;
        this.mXWalkExtendCanvasClient.setExtendPluginManager(this.mContentViewRenderView);
    }

    public void setExtendPluginClient(XWalkExtendPluginClientInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mXWalkExtendPluginClient = arg6;
        this.mXWalkExtendPluginClient.setExtendPluginManager(this.mContentViewRenderView);
    }

    public void setFindListener(XWalkFindListenerInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setFindListener(arg6);
    }

    static void setJavascriptInterfaceClass(Class arg0) {
        XWalkContent.javascriptInterfaceClass = arg0;
    }

    private void setNativeContent(long arg11, String arg13) {
        ContentViewCore v0 = null;
        if(Long.compare(this.mNativeContent, 0) != 0) {
            this.destroy();
            this.mContentViewCore = v0;
        }

        this.mAnimated = arg13 == null ? XWalkPreferencesInternal.getValue("animatable-xwalk-view") : arg13.equalsIgnoreCase("true");
        CompositingSurfaceType v13 = this.mAnimated ? CompositingSurfaceType.TEXTURE_VIEW : CompositingSurfaceType.SURFACE_VIEW;
        String v1 = XWalkContent.TAG;
        StringBuilder v2 = new StringBuilder();
        v2.append("CompositingSurfaceType is ");
        String v3 = this.mAnimated ? "TextureView" : "SurfaceView";
        v2.append(v3);
        org.xwalk.core.internal.Log.d(v1, v2.toString());
        this.mContentViewRenderView = new ContentViewRenderView(this.mViewContext.getApplicationContext(), v13) {
            protected void onReadyToRender() {
                super.onReadyToRender();
            }
        };
        this.mContentViewRenderView.setExtendPluginCallback(new ExtendPluginCallback() {
            public void onPluginDestroy(String arg2, int arg3) {
                if(XWalkContent.this.mXWalkExtendPluginClient != null) {
                    XWalkContent.this.mXWalkExtendPluginClient.onPluginDestroy(arg2, arg3);
                }
            }

            public void onPluginReady(String arg2, int arg3, SurfaceTexture arg4) {
                if(XWalkContent.this.mXWalkExtendPluginClient != null) {
                    XWalkContent.this.mXWalkExtendPluginClient.onPluginReady(arg2, arg3, arg4);
                }
            }

            public void onPluginTouch(String arg2, int arg3, MotionEvent arg4) {
                if(XWalkContent.this.mXWalkExtendPluginClient != null) {
                    XWalkContent.this.mXWalkExtendPluginClient.onPluginTouch(arg2, arg3, arg4);
                }
            }

            public void onPluginTouch(String arg2, int arg3, String arg4) {
                if(XWalkContent.this.mXWalkExtendPluginClient != null) {
                    XWalkContent.this.mXWalkExtendPluginClient.onPluginTouch(arg2, arg3, arg4);
                }
            }

            public void onSendJsonMessage(String arg2) {
                if(XWalkContent.this.mXWalkExtendPluginClient != null) {
                    XWalkContent.this.mXWalkExtendPluginClient.onSendJsonMessage(arg2);
                }
            }
        });
        this.mContentViewRenderView.setExtendCanvasCallback(new ExtendCanvasCallback() {
            public void onAsycResultCallback(int arg2, int arg3, String arg4) {
                if(XWalkContent.this.mXWalkExtendCanvasClient != null) {
                    XWalkContent.this.mXWalkExtendCanvasClient.onAsycResultCallback(arg2, arg3, arg4);
                }
            }

            public void onCanvasTouch(String arg2, int arg3, String arg4) {
                if(XWalkContent.this.mXWalkExtendCanvasClient != null) {
                    XWalkContent.this.mXWalkExtendCanvasClient.onCanvasTouch(arg2, arg3, arg4);
                }
            }

            public void onSendJsonMessage(String arg2) {
                if(XWalkContent.this.mXWalkExtendCanvasClient != null) {
                    XWalkContent.this.mXWalkExtendCanvasClient.onSendJsonMessage(arg2);
                }
            }
        });
        this.mContentViewRenderView.onNativeLibraryLoaded(this.mWindow);
        this.mLaunchScreenManager = new XWalkLaunchScreenManager(this.mViewContext, this.mXWalkView);
        this.mContentViewRenderView.registerFirstRenderedFrameListener(this.mLaunchScreenManager);
        this.mXWalkView.addView(this.mContentViewRenderView, new FrameLayout$LayoutParams(-1, -1));
        this.mNativeContent = arg11;
        this.mCleanupReference = new CleanupReference(this, new DestroyRunnable(this.mNativeContent, ((org.xwalk.core.internal.XWalkContent$1)v0)));
        this.mWebContents = this.nativeGetWebContents(this.mNativeContent);
        this.mContentView = XWalkContentView.createContentView(this.mViewContext, this.mWebContents, this.mXWalkView);
        this.mContentsClientBridge.setContentView(this.mContentView);
        this.mViewAndroidDelegate.updateCurrentContainerView(this.mContentView, this.mWindow.getDisplay());
        this.mContentViewCore = ContentViewCore$$CC.create$$STATIC$$(this.mViewContext, "67", this.mWebContents, this.mViewAndroidDelegate, this.mContentView, this.mWindow);
        this.mNavigationController = this.mWebContents.getNavigationController();
        SelectionPopupController v11 = SelectionPopupController$$CC.fromWebContents$$STATIC$$(this.mWebContents);
        v11.setActionModeCallback(new XWalkActionModeCallback(this.mViewContext, this, v11.getActionModeCallbackHelper()));
        this.mSelectionClient = new SelectionClient() {
            public void cancelAllRequests() {
            }

            public TextClassifier getCustomTextClassifier() {
                return SelectionClient$$CC.getCustomTextClassifier(((SelectionClient)this));
            }

            public SelectionMetricsLogger getSelectionMetricsLogger() {
                return SelectionClient$$CC.getSelectionMetricsLogger(((SelectionClient)this));
            }

            public TextClassifier getTextClassifier() {
                return SelectionClient$$CC.getTextClassifier(((SelectionClient)this));
            }

            public boolean isSearchable() {
                return XWalkContent.this.mSupportSmartPickWord;
            }

            public boolean onSearchWord(String arg2, String arg3, String arg4) {
                arg3 = XWalkContent.TAG;
                org.xwalk.core.internal.Log.i(arg3, "smartPickWord royle onSearchWord query:" + arg2);
                if(XWalkContent.this.mXWalkProxyWebViewClientExtension != null) {
                    Bundle v3 = new Bundle();
                    v3.putString("query", arg2);
                    XWalkContent.this.mXWalkProxyWebViewClientExtension.onMiscCallBack("jumpToSos", v3);
                    return 1;
                }

                org.xwalk.core.internal.Log.i(XWalkContent.TAG, "smartPickWord royle onSearchWord mXWalkProxyWebViewClientExtension = null");
                return 0;
            }

            public void onSelectionChanged(String arg1) {
            }

            public void onSelectionEvent(int arg1, float arg2, float arg3) {
            }

            public void onShowSos(boolean arg4) {
                String v0 = XWalkContent.TAG;
                org.xwalk.core.internal.Log.i(v0, "smartPickWord royle onShowSos IsCursorMove:" + arg4);
                if(XWalkContent.this.mXWalkProxyWebViewClientExtension != null) {
                    Bundle v0_1 = new Bundle();
                    v0_1.putBoolean("IsCursorMove", arg4);
                    XWalkContent.this.mXWalkProxyWebViewClientExtension.onMiscCallBack("onShowSos", v0_1);
                }
                else {
                    org.xwalk.core.internal.Log.i(XWalkContent.TAG, "smartPickWord royle onShowSos mXWalkProxyWebViewClientExtension = null");
                }
            }

            public void onWeChatSelectionInfoChanged(SelectionPopupController arg15, String arg16, String arg17, String arg18, int arg19, int arg20, int arg21, String arg22, String arg23, String arg24, int arg25, int arg26, int arg27, long arg28) {
                int v4;
                int v3;
                String v12;
                String v11;
                String v10;
                org.xwalk.core.internal.XWalkContent$6 v0 = this;
                if(v0.this$0.mXWalkProxyWebViewClientExtension != null) {
                    Bundle v1 = new Bundle();
                    v10 = arg16;
                    v1.putString("PickedWord", v10);
                    v11 = arg17;
                    v1.putString("PrefixText", v11);
                    v12 = arg18;
                    v1.putString("SuffixText", v12);
                    v1.putInt("MainIndex", arg19);
                    v1.putInt("TagNewLineBefore", arg20);
                    v1.putInt("TagNewLineAfter", arg21);
                    v1.putString("SubPickedWord", arg22);
                    v1.putString("SubPrefixText", arg23);
                    v1.putString("SubSuffixText", arg24);
                    v1.putInt("SubIndex", arg25);
                    v1.putInt("TagNewLineBeforeSub", arg26);
                    v1.putInt("TagNewLineAfterSub", arg27);
                    String v2 = XWalkContent.TAG;
                    org.xwalk.core.internal.Log.i(v2, "smartPickWord royle onWeChatSelectionInfoChanged request:" + v1);
                    Object v1_1 = v0.this$0.mXWalkProxyWebViewClientExtension.onMiscCallBack("smartPickWord", v1);
                    if(((v1_1 instanceof Bundle)) && v1_1 != null) {
                        v2 = XWalkContent.TAG;
                        org.xwalk.core.internal.Log.i(v2, "smartPickWord royle response:" + v1_1);
                        v2 = ((Bundle)v1_1).getString("PickedWord");
                        if(v2 != null) {
                            int v10_1 = ((Bundle)v1_1).getInt("IsMainOrSub", 0);
                            String v13 = ((Bundle)v1_1).getString("PrefixText");
                            String v1_2 = ((Bundle)v1_1).getString("SuffixText");
                            if(v10_1 == 0) {
                                v3 = arg17.length() - v13.length();
                                v4 = arg18.length() - v1_2.length();
                            }
                            else {
                                v3 = arg23.length() - v13.length();
                                v4 = arg24.length() - v1_2.length();
                            }

                            int v11_1 = v3;
                            int v12_1 = v4;
                            arg15.didReceivedWeChatSelectionInfo(v2, v11_1, v12_1, v13, v1_2, v10_1);
                            v0.this$0.mWebContents.didReceivedWeChatSelectionInfo(v2, v11_1, v12_1, v13, v1_2, v10_1, arg28);
                            return;
                        }
                    }
                }
                else {
                    v10 = arg16;
                    v11 = arg17;
                    v12 = arg18;
                    org.xwalk.core.internal.Log.i(XWalkContent.TAG, "smartPickWord royle onWeChatSelectionInfoChanged mXWalkProxyWebViewClientExtension = null");
                }

                arg15.didReceivedWeChatSelectionInfo(v10, 0, 0, v11, v12, 0);
                v0.this$0.mWebContents.didReceivedWeChatSelectionInfo(v10, 0, 0, v11, v12, 0, arg28);
            }

            public boolean requestSelectionPopupUpdates(boolean arg1) {
                return 0;
            }

            public void selectWordAroundCaretAck(boolean arg1, int arg2, int arg3) {
            }

            public void setTextClassifier(TextClassifier arg1) {
                SelectionClient$$CC.setTextClassifier(((SelectionClient)this), arg1);
            }
        };
        v11.setSelectionClient(this.mSelectionClient);
        this.mXWalkView.addView(this.mContentView, new FrameLayout$LayoutParams(-1, -1));
        this.mWebContents.setOverscrollRefreshHandler(new OverscrollRefreshHandler() {
            public void pull(float arg1) {
            }

            public void release(boolean arg2) {
                XWalkContent.this.mContentsClientBridge.onOverscrollRefresh(false);
            }

            public void reset() {
                XWalkContent.this.mContentsClientBridge.onOverscrollRefresh(false);
            }

            public void setEnabled(boolean arg1) {
            }

            public boolean start() {
                XWalkContent.this.mContentsClientBridge.onOverscrollRefresh(true);
                return 1;
            }
        });
        this.mContentViewRenderView.setCurrentWebContents(this.mWebContents);
        this.installWebContentsObserver();
        this.mDIPScale = DeviceDisplayInfo.create(this.mViewContext).getDIPScale();
        this.mContentsClientBridge.setDIPScale(this.mDIPScale);
        this.nativeSetJavaPeers(this.mNativeContent, this, this.mXWalkContentsDelegateAdapter, this.mContentsClientBridge, this.mIoThreadClient, this.mContentsClientBridge.getInterceptNavigationDelegate());
        this.mSettings = new XWalkSettingsInternal(this.mViewContext, this.mWebContents, false);
        this.mSettings.setAllowFileAccessFromFileURLs(true);
        this.mSettings.setDIPScale(this.mDIPScale);
        String v11_1 = Locale.getDefault().toString().replaceAll("_", "-").toLowerCase();
        if(v11_1.isEmpty()) {
            v11_1 = "en";
        }

        this.mSettings.setAcceptLanguages(v11_1);
        this.mSettings.setZoomListener(new ZoomSupportChangeListener() {
            public void onGestureZoomSupportChanged(boolean arg2, boolean arg3) {
                if(XWalkContent.this.mContentViewCore == null) {
                    return;
                }

                XWalkContent.this.mContentViewCore.updateDoubleTapSupport(arg2);
                XWalkContent.this.mContentViewCore.updateMultiTouchZoomSupport(arg3);
            }
        });
    }

    public void setNavigationHandler(XWalkNavigationHandler arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setNavigationHandler(arg6);
    }

    public void setNetworkAvailable(boolean arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.nativeSetJsOnlineProperty(this.mNativeContent, arg6);
    }

    public void setNotificationService(XWalkNotificationService arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setNotificationService(arg6);
    }

    public void setOnTouchListener(View$OnTouchListener arg2) {
        this.mContentView.setOnTouchListener(arg2);
    }

    public void setOriginAccessWhitelist(String arg6, String[] arg7) {
        if(this.mNativeContent != 0) {
            if(TextUtils.isEmpty(((CharSequence)arg6))) {
            }
            else {
                String v0 = "";
                if(arg7 != null) {
                    v0 = new JSONArray(Arrays.asList(((Object[])arg7))).toString();
                }

                this.nativeSetOriginAccessWhitelist(this.mNativeContent, arg6, v0);
                return;
            }
        }
    }

    public void setOverlayVideoMode(boolean arg2) {
        if(this.mContentViewRenderView != null) {
            this.mContentViewRenderView.setOverlayVideoMode(arg2);
        }
    }

    public void setProxyWebViewClientExtension(XWalkProxyWebViewClientExtensionInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mXWalkProxyWebViewClientExtension = arg6;
        this.getSupportSmartPickWord();
    }

    public void setResourceClient(XWalkResourceClientInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setResourceClient(arg6);
    }

    public void setTranslateMode(boolean arg8) {
        if(this.mWebContents != null) {
            this.mWebContents.setTranslateMode(arg8);
            XWebReporter.idkeyStat(938, 98, 1);
        }
    }

    public void setUIClient(XWalkUIClientInternal arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setUIClient(arg6);
    }

    public void setVisibility(int arg2) {
        SurfaceView v0 = this.mContentViewRenderView.getSurfaceView();
        if(v0 == null) {
            return;
        }

        v0.setVisibility(arg2);
    }

    @CalledByNative private void setXWalkAutofillClient(XWalkAutofillClientAndroid arg3) {
        this.mXWalkAutofillClient = arg3;
        arg3.init(this.mContentViewCore, this.mViewContext);
    }

    public void setXWalkClient(XWalkClient arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setXWalkClient(arg6);
    }

    public void setXWalkWebChromeClient(XWalkWebChromeClient arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mContentsClientBridge.setXWalkWebChromeClient(arg6);
    }

    public void setZOrderOnTop(boolean arg2) {
        if(this.mContentViewRenderView == null) {
            return;
        }

        this.mContentViewRenderView.setZOrderOnTop(arg2);
    }

    public void smoothScroll(int arg9, int arg10, long arg11) {
        float v0 = this.mWebContents.getTopLevelNativeWindow().getDisplay().getDipScale();
        this.nativeSmoothScroll(this.mNativeContent, ((int)((((float)arg9)) / v0)), ((int)((((float)arg10)) / v0)), arg11);
    }

    public void stopLoading() {
        if(this.mNativeContent == 0) {
            return;
        }

        this.mWebContents.stop();
        this.mContentsClientBridge.onStopLoading();
    }

    public void supplyContentsForPopup(XWalkContent arg6) {
        long v2 = 0;
        if(this.mNativeContent == v2) {
            return;
        }

        long v0 = this.nativeReleasePopupXWalkContent(this.mNativeContent);
        if(v0 == v2) {
            org.xwalk.core.internal.Log.w(XWalkContent.TAG, "Popup XWalkView bind failed: no pending content.");
            if(arg6 != null) {
                arg6.destroy();
            }

            return;
        }

        if(arg6 == null) {
            XWalkContent.nativeDestroy(v0);
            return;
        }

        arg6.receivePopupContents(v0);
    }

    private static void tryResycle() {
        ArrayList v1_1;
        Class v0 = XWalkContent.class;
        __monitor_enter(v0);
        try {
            v1_1 = XWalkContent.getAllAliveXWalkView();
            if(v1_1.size() > 5) {
                goto label_8;
            }
        }
        catch(Throwable v1) {
            goto label_44;
        }

        __monitor_exit(v0);
        return;
    label_8:
        int v2 = 0;
        int v3 = 0;
        try {
            while(v2 < v1_1.size() - 2) {
                Object v4 = v1_1.get(v2);
                if(v4 != null && ((XWalkContent)v4).mContentsClientBridge != null && (((XWalkContent)v4).mContentsClientBridge.shouldDiscardCurrentPage())) {
                    ++v3;
                    ((XWalkContent)v4).loadUrl("about:blank");
                    ((XWalkContent)v4).mContentsClientBridge.onClearCurrentPage();
                }

                ++v2;
            }

            if(v3 > 0) {
                String v1_2 = XWalkContent.TAG;
                org.xwalk.core.internal.Log.i(v1_2, "resycled page count:" + v3);
                XWebReporter.idkeyStat(577, 67, ((long)v3));
                MemoryPressureListener.notifyMemoryPresure();
            }
        }
        catch(Throwable v1) {
            goto label_44;
        }

        __monitor_exit(v0);
        return;
    label_44:
        __monitor_exit(v0);
        throw v1;
    }

    @VisibleForTesting public static void updateDefaultLocale() {
        String v0 = LocaleUtils.getDefaultLocaleListString();
        if(!XWalkContent.sCurrentLocales.equals(v0)) {
            XWalkContent.sCurrentLocales = v0;
            XWalkContent.nativeUpdateDefaultLocale(LocaleUtils.getDefaultLocaleString(), XWalkContent.sCurrentLocales);
        }
    }

    @CalledByNative private void updateHitTestData(int arg2, String arg3, String arg4, String arg5, String arg6) {
        this.mPossiblyStaleHitTestData.hitTestResultType = arg2;
        this.mPossiblyStaleHitTestData.hitTestResultExtraData = arg3;
        this.mPossiblyStaleHitTestData.href = arg4;
        this.mPossiblyStaleHitTestData.anchorText = arg5;
        this.mPossiblyStaleHitTestData.imgSrc = arg6;
    }

    public void zoomBy(float arg6) {
        if(this.mNativeContent == 0) {
            return;
        }

        if(arg6 >= 0.01f) {
            if(arg6 > 100f) {
            }
            else {
                ZoomController.pinchByDelta(this.mWebContents, arg6);
                return;
            }
        }

        throw new IllegalStateException("zoom delta value outside [0.01, 100] range.");
    }

    public boolean zoomIn() {
        if(this.mNativeContent == 0) {
            return 0;
        }

        return ZoomController.zoomIn(this.mWebContents);
    }

    public boolean zoomOut() {
        if(this.mNativeContent == 0) {
            return 0;
        }

        return ZoomController.zoomOut(this.mWebContents);
    }
}

