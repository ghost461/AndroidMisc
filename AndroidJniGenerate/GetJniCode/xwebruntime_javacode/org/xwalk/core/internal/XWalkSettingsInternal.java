package org.xwalk.core.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings$System;
import android.util.Log;
import com.tencent.xweb.xprofile.model.WindowPerformance$Navigation;
import com.tencent.xweb.xprofile.model.WindowPerformance$Timing;
import com.tencent.xweb.xprofile.model.WindowPerformance;
import com.util.RuntimeEnviroment;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

@JNINamespace(value="xwalk") @XWalkAPI(createInternally=true) public class XWalkSettingsInternal {
    class EventHandler {
        private static final int UPDATE_WEBKIT_PREFERENCES;
        private Handler mHandler;

        static {
        }

        EventHandler(XWalkSettingsInternal arg1) {
            XWalkSettingsInternal.this = arg1;
            super();
        }

        static void access$600(EventHandler arg0) {
            arg0.updateWebkitPreferencesLocked();
        }

        void bindUiThread() {
            if(this.mHandler != null) {
                return;
            }

            this.mHandler = new Handler(ThreadUtils.getUiThreadLooper()) {
                public void handleMessage(Message arg3) {
                    int v3 = arg3.what;
                    if(v3 != 0) {
                    }
                    else {
                        Object v3_1 = this.this$1.this$0.mXWalkSettingsLock;
                        __monitor_enter(v3_1);
                        try {
                            this.this$1.this$0.updateWebkitPreferencesOnUiThread();
                            this.this$1.this$0.mIsUpdateWebkitPrefsMessagePending = false;
                            this.this$1.this$0.mXWalkSettingsLock.notifyAll();
                            __monitor_exit(v3_1);
                            return;
                        label_21:
                            __monitor_exit(v3_1);
                            goto label_22;
                        }
                        catch(Throwable v0) {
                            goto label_21;
                        }
                    }

                    return;
                label_22:
                    throw v0;
                }
            };
        }

        void maybePostOnUiThread(Runnable arg2) {
            if(this.mHandler != null) {
                this.mHandler.post(arg2);
            }
        }

        void maybeRunOnUiThreadBlocking(Runnable arg2) {
            if(this.mHandler != null) {
                ThreadUtils.runOnUiThreadBlocking(arg2);
            }
        }

        private void updateWebkitPreferencesLocked() {
            if(XWalkSettingsInternal.this.mNativeXWalkSettings == 0) {
                return;
            }

            if(this.mHandler == null) {
                return;
            }

            if(ThreadUtils.runningOnUiThread()) {
                XWalkSettingsInternal.this.updateWebkitPreferencesOnUiThread();
            }
            else if(XWalkSettingsInternal.this.mIsUpdateWebkitPrefsMessagePending) {
                return;
            }
            else {
                XWalkSettingsInternal.this.mIsUpdateWebkitPrefsMessagePending = true;
                this.mHandler.sendMessage(Message.obtain(null, 0));
                try {
                    while(true) {
                        if(XWalkSettingsInternal.this.mIsUpdateWebkitPrefsMessagePending) {
                            XWalkSettingsInternal.this.mXWalkSettingsLock.wait();
                            continue;
                        }

                        return;
                    }
                }
                catch(InterruptedException ) {
                    return;
                }
            }
        }
    }

    @XWalkAPI public enum LayoutAlgorithmInternal {
        public static final enum LayoutAlgorithmInternal NARROW_COLUMNS;
        public static final enum LayoutAlgorithmInternal NORMAL;
        public static final enum LayoutAlgorithmInternal SINGLE_COLUMN;
        public static final enum LayoutAlgorithmInternal TEXT_AUTOSIZING;

        static {
            LayoutAlgorithmInternal.NORMAL = new LayoutAlgorithmInternal("NORMAL", 0);
            LayoutAlgorithmInternal.SINGLE_COLUMN = new LayoutAlgorithmInternal("SINGLE_COLUMN", 1);
            LayoutAlgorithmInternal.NARROW_COLUMNS = new LayoutAlgorithmInternal("NARROW_COLUMNS", 2);
            LayoutAlgorithmInternal.TEXT_AUTOSIZING = new LayoutAlgorithmInternal("TEXT_AUTOSIZING", 3);
            LayoutAlgorithmInternal.$VALUES = new LayoutAlgorithmInternal[]{LayoutAlgorithmInternal.NORMAL, LayoutAlgorithmInternal.SINGLE_COLUMN, LayoutAlgorithmInternal.NARROW_COLUMNS, LayoutAlgorithmInternal.TEXT_AUTOSIZING};
        }

        private LayoutAlgorithmInternal(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static LayoutAlgorithmInternal valueOf(String arg1) {
            return Enum.valueOf(LayoutAlgorithmInternal.class, arg1);
        }

        public static LayoutAlgorithmInternal[] values() {
            return LayoutAlgorithmInternal.$VALUES.clone();
        }
    }

    class LazyDefaultUserAgent {
        private static final String sInstance;

        static {
            LazyDefaultUserAgent.sInstance = XWalkSettingsInternal.nativeGetDefaultUserAgent();
        }

        LazyDefaultUserAgent() {
            super();
        }

        static String access$500() {
            return LazyDefaultUserAgent.sInstance;
        }
    }

    public interface WindowPerformanceHandler {
        void onWindowPerformanceReport(WindowPerformance arg1);
    }

    interface ZoomSupportChangeListener {
        void onGestureZoomSupportChanged(boolean arg1, boolean arg2);
    }

    public static final int APP_BRAND_TYPE_NONE = 0;
    public static final int APP_BRAND_TYPE_WE_APP = 1;
    public static final int APP_BRAND_TYPE_WE_APP_EMBEDDED_HTML = 2;
    @XWalkAPI public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    @XWalkAPI public static final int LOAD_CACHE_ONLY = 3;
    @XWalkAPI public static final int LOAD_DEFAULT = -1;
    @XWalkAPI public static final int LOAD_NO_CACHE = 2;
    private static final int MAXIMUM_FONT_SIZE = 72;
    private static final int MINIMUM_FONT_SIZE = 1;
    private static final String TAG = "XWalkSettings";
    private String mAcceptLanguages;
    private boolean mAllowContentUrlAccess;
    private boolean mAllowFileAccessFromFileURLs;
    private boolean mAllowFileUrlAccess;
    private final boolean mAllowGeolocationOnInsecureOrigins;
    private boolean mAllowScriptsToCloseWindows;
    private boolean mAllowUniversalAccessFromFileURLs;
    private boolean mAppCacheEnabled;
    private boolean mAutoCompleteEnabled;
    private boolean mBlockNetworkLoads;
    private boolean mBuiltInZoomControls;
    private int mCacheMode;
    private final Context mContext;
    private String mCursiveFontFamily;
    private double mDIPScale;
    private boolean mDatabaseEnabled;
    private int mDefaultFixedFontSize;
    private int mDefaultFontSize;
    private String mDefaultVideoPosterURL;
    private int mDisabledMenuItems;
    private boolean mDisplayZoomControls;
    private boolean mDomStorageEnabled;
    private final EventHandler mEventHandler;
    private String mFantasyFontFamily;
    private String mFixedFontFamily;
    private boolean mGeolocationEnabled;
    private boolean mImagesEnabled;
    private float mInitialPageScalePercent;
    private static boolean mIsSupportM67IDKEYReport = false;
    private boolean mIsUpdateWebkitPrefsMessagePending;
    private boolean mJavaScriptCanOpenWindowsAutomatically;
    private boolean mJavaScriptEnabled;
    private LayoutAlgorithmInternal mLayoutAlgorithm;
    private boolean mLoadWithOverviewMode;
    private boolean mLoadsImagesAutomatically;
    private boolean mMediaPlaybackRequiresUserGesture;
    private int mMinimumFontSize;
    private int mMinimumLogicalFontSize;
    private long mNativeXWalkSettings;
    private final boolean mPasswordEchoEnabled;
    private boolean mQuirksModeEnabled;
    private String mSansSerifFontFamily;
    private String mSerifFontFamily;
    private boolean mShouldFocusFirstNode;
    private boolean mSpatialNavigationEnabled;
    private String mStandardFontFamily;
    private boolean mSupportMultipleWindows;
    private boolean mSupportSmartPickWord;
    private boolean mSupportZoom;
    private int mTextSizePercent;
    private boolean mUseWideViewport;
    private String mUserAgent;
    private int mUsingForAppBrand;
    private boolean mUsingForAppBrandWeApp;
    private static WindowPerformanceHandler mWindowPerformanceHandler = null;
    private final Object mXWalkSettingsLock;
    private ZoomSupportChangeListener mZoomChangeListener;
    @XWalkAPI(reservable=true) private static XWalkJSExceptionListenerInternal m_js_exception_callback = null;
    @XWalkAPI(reservable=true) private static XWalkLogMessageListenerInternal m_log_callback = null;
    @XWalkAPI(reservable=true) private static XWalkNotifyChannelListenerInternal m_notify_callback_channel = null;
    private static boolean sAppCachePathIsSet = false;
    private static final Object sGlobalContentSettingsLock;

    static {
        XWalkSettingsInternal.sGlobalContentSettingsLock = new Object();
    }

    XWalkSettingsInternal() {
        super();
        this.mXWalkSettingsLock = new Object();
        this.mAllowScriptsToCloseWindows = true;
        this.mLoadsImagesAutomatically = true;
        this.mImagesEnabled = true;
        this.mJavaScriptEnabled = true;
        this.mAllowUniversalAccessFromFileURLs = false;
        this.mAllowFileAccessFromFileURLs = false;
        this.mJavaScriptCanOpenWindowsAutomatically = true;
        this.mCacheMode = -1;
        this.mSupportMultipleWindows = false;
        this.mAppCacheEnabled = true;
        this.mDomStorageEnabled = true;
        this.mDatabaseEnabled = true;
        this.mUseWideViewport = false;
        this.mLoadWithOverviewMode = false;
        this.mMediaPlaybackRequiresUserGesture = false;
        boolean v2 = ContextUtils.getApplicationContext().getApplicationInfo().targetSdkVersion <= 23 ? true : false;
        this.mAllowGeolocationOnInsecureOrigins = v2;
        this.mAllowContentUrlAccess = true;
        this.mAllowFileUrlAccess = true;
        this.mShouldFocusFirstNode = true;
        this.mGeolocationEnabled = true;
        this.mUsingForAppBrandWeApp = false;
        this.mSupportSmartPickWord = false;
        this.mUsingForAppBrand = 0;
        this.mNativeXWalkSettings = 0;
        this.mIsUpdateWebkitPrefsMessagePending = false;
        this.mDefaultFontSize = 16;
        this.mDefaultFixedFontSize = 13;
        this.mAutoCompleteEnabled = true;
        this.mInitialPageScalePercent = 0f;
        this.mDIPScale = 1;
        this.mTextSizePercent = 100;
        this.mSupportZoom = true;
        this.mBuiltInZoomControls = false;
        this.mDisplayZoomControls = true;
        this.mSpatialNavigationEnabled = false;
        this.mQuirksModeEnabled = false;
        this.mLayoutAlgorithm = LayoutAlgorithmInternal.NARROW_COLUMNS;
        this.mDisabledMenuItems = 0;
        this.mStandardFontFamily = "sans-serif";
        this.mFixedFontFamily = "monospace";
        this.mSansSerifFontFamily = "sans-serif";
        this.mSerifFontFamily = "sans-serif";
        this.mCursiveFontFamily = "cursive";
        this.mFantasyFontFamily = "fantasy";
        this.mMinimumFontSize = 8;
        this.mMinimumLogicalFontSize = 8;
        this.mContext = null;
        this.mEventHandler = null;
        this.mPasswordEchoEnabled = false;
    }

    XWalkSettingsInternal(Context arg7, WebContents arg8, boolean arg9) {
        super();
        this.mXWalkSettingsLock = new Object();
        boolean v0 = true;
        this.mAllowScriptsToCloseWindows = true;
        this.mLoadsImagesAutomatically = true;
        this.mImagesEnabled = true;
        this.mJavaScriptEnabled = true;
        this.mAllowUniversalAccessFromFileURLs = false;
        this.mAllowFileAccessFromFileURLs = false;
        this.mJavaScriptCanOpenWindowsAutomatically = true;
        this.mCacheMode = -1;
        this.mSupportMultipleWindows = false;
        this.mAppCacheEnabled = true;
        this.mDomStorageEnabled = true;
        this.mDatabaseEnabled = true;
        this.mUseWideViewport = false;
        this.mLoadWithOverviewMode = false;
        this.mMediaPlaybackRequiresUserGesture = false;
        boolean v2 = ContextUtils.getApplicationContext().getApplicationInfo().targetSdkVersion <= 23 ? true : false;
        this.mAllowGeolocationOnInsecureOrigins = v2;
        this.mAllowContentUrlAccess = true;
        this.mAllowFileUrlAccess = true;
        this.mShouldFocusFirstNode = true;
        this.mGeolocationEnabled = true;
        this.mUsingForAppBrandWeApp = false;
        this.mSupportSmartPickWord = false;
        this.mUsingForAppBrand = 0;
        this.mNativeXWalkSettings = 0;
        this.mIsUpdateWebkitPrefsMessagePending = false;
        this.mDefaultFontSize = 16;
        this.mDefaultFixedFontSize = 13;
        this.mAutoCompleteEnabled = true;
        this.mInitialPageScalePercent = 0f;
        this.mDIPScale = 1;
        this.mTextSizePercent = 100;
        this.mSupportZoom = true;
        this.mBuiltInZoomControls = false;
        this.mDisplayZoomControls = true;
        this.mSpatialNavigationEnabled = false;
        this.mQuirksModeEnabled = false;
        this.mLayoutAlgorithm = LayoutAlgorithmInternal.NARROW_COLUMNS;
        this.mDisabledMenuItems = 0;
        this.mStandardFontFamily = "sans-serif";
        this.mFixedFontFamily = "monospace";
        this.mSansSerifFontFamily = "sans-serif";
        this.mSerifFontFamily = "sans-serif";
        this.mCursiveFontFamily = "cursive";
        this.mFantasyFontFamily = "fantasy";
        this.mMinimumFontSize = 8;
        this.mMinimumLogicalFontSize = 8;
        ThreadUtils.assertOnUiThread();
        this.mContext = arg7;
        v2 = this.mContext.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) != 0 ? true : false;
        this.mBlockNetworkLoads = v2;
        if(arg9) {
            this.mAllowUniversalAccessFromFileURLs = true;
            this.mAllowFileAccessFromFileURLs = true;
        }

        this.mSpatialNavigationEnabled = arg7.getPackageManager().hasSystemFeature("android.hardware.touchscreen") ^ 1;
        this.mUserAgent = LazyDefaultUserAgent.access$500();
        if(Settings$System.getInt(arg7.getContentResolver(), "show_password", 1) == 1) {
        }
        else {
            v0 = false;
        }

        this.mPasswordEchoEnabled = v0;
        this.mEventHandler = new EventHandler(this);
        this.setWebContents(arg8);
        XWalkSettingsInternal.mIsSupportM67IDKEYReport = RuntimeEnviroment.checkSupportM67Report();
    }

    public static void InvokeChannel(int arg0, Object[] arg1) {
        XWalkSettingsInternal.nativeInvokeChannel(arg0, arg1);
    }

    @XWalkAPI(reservable=true) public void SetJSExceptionCallBack(XWalkJSExceptionListenerInternal arg3) {
        if(arg3 != null) {
            XWalkSettingsInternal.m_js_exception_callback = arg3;
            this.nativeSetJSExceptionCaptureEnable(1);
            return;
        }

        XWalkSettingsInternal.m_js_exception_callback = null;
        this.nativeSetJSExceptionCaptureEnable(0);
    }

    public static void SetLogCallBack(XWalkLogMessageListenerInternal arg2) {
        if(XWalkSettingsInternal.m_log_callback != null) {
            return;
        }

        if(arg2 != null) {
            XWalkSettingsInternal.m_log_callback = arg2;
            XWalkSettingsInternal.nativeSetLogEnable(1);
            return;
        }

        XWalkSettingsInternal.m_log_callback = null;
        XWalkSettingsInternal.nativeSetLogEnable(0);
    }

    public static void SetNotifyCallBackChannel(XWalkNotifyChannelListenerInternal arg1) {
        if(arg1 == null) {
            return;
        }

        if(XWalkSettingsInternal.m_notify_callback_channel != null) {
            return;
        }

        XWalkSettingsInternal.m_notify_callback_channel = arg1;
    }

    static String access$000() {
        return XWalkSettingsInternal.nativeGetDefaultUserAgent();
    }

    static Object access$100(XWalkSettingsInternal arg0) {
        return arg0.mXWalkSettingsLock;
    }

    static void access$1000(XWalkSettingsInternal arg0, long arg1) {
        arg0.nativeUpdateInitialPageScale(arg1);
    }

    static ZoomSupportChangeListener access$1100(XWalkSettingsInternal arg0) {
        return arg0.mZoomChangeListener;
    }

    static EventHandler access$1200(XWalkSettingsInternal arg0) {
        return arg0.mEventHandler;
    }

    static void access$1300(XWalkSettingsInternal arg0, long arg1) {
        arg0.nativeResetScrollAndScaleState(arg1);
    }

    static void access$200(XWalkSettingsInternal arg0) {
        arg0.updateWebkitPreferencesOnUiThread();
    }

    static boolean access$300(XWalkSettingsInternal arg0) {
        return arg0.mIsUpdateWebkitPrefsMessagePending;
    }

    static boolean access$302(XWalkSettingsInternal arg0, boolean arg1) {
        arg0.mIsUpdateWebkitPrefsMessagePending = arg1;
        return arg1;
    }

    static long access$400(XWalkSettingsInternal arg2) {
        return arg2.mNativeXWalkSettings;
    }

    static void access$700(XWalkSettingsInternal arg0, long arg1) {
        arg0.nativeUpdateUserAgent(arg1);
    }

    static void access$800(XWalkSettingsInternal arg0, long arg1) {
        arg0.nativeUpdateAcceptLanguages(arg1);
    }

    static void access$900(XWalkSettingsInternal arg0, long arg1) {
        arg0.nativeUpdateFormDataPreferences(arg1);
    }

    private int clipFontSize(int arg2) {
        if(arg2 < 1) {
            return 1;
        }

        int v0 = 72;
        if(arg2 > v0) {
            return v0;
        }

        return arg2;
    }

    @XWalkAPI public String getAcceptLanguages() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAcceptLanguages;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private String getAcceptLanguagesLocked() {
        return this.mAcceptLanguages;
    }

    @XWalkAPI public boolean getAllowContentAccess() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAllowContentUrlAccess;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getAllowFileAccess() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAllowFileUrlAccess;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getAllowFileAccessFromFileURLs() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAllowFileAccessFromFileURLs;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean getAllowGeolocationOnInsecureOrigins() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAllowGeolocationOnInsecureOrigins;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public boolean getAllowScriptsToCloseWindows() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAllowScriptsToCloseWindows;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getAllowUniversalAccessFromFileURLs() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mAllowUniversalAccessFromFileURLs;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean getAppCacheEnabled() {
        boolean v2_1;
        Object v1_1;
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(!this.mAppCacheEnabled) {
                __monitor_exit(v0);
                return 0;
            }

            v1_1 = XWalkSettingsInternal.sGlobalContentSettingsLock;
            __monitor_enter(v1_1);
        }
        catch(Throwable v1) {
            goto label_17;
        }

        try {
            v2_1 = XWalkSettingsInternal.sAppCachePathIsSet;
            __monitor_exit(v1_1);
            goto label_11;
        }
        catch(Throwable v2) {
            try {
            label_14:
                __monitor_exit(v1_1);
            }
            catch(Throwable v2) {
                goto label_14;
            }

            try {
                throw v2;
            label_11:
                __monitor_exit(v0);
                return v2_1;
            label_17:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_17;
            }
        }

        throw v1;
    }

    @XWalkAPI public boolean getBlockNetworkImage() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mImagesEnabled ^ 1;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    @XWalkAPI public boolean getBlockNetworkLoads() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mBlockNetworkLoads;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getBuiltInZoomControls() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mBuiltInZoomControls;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public int getCacheMode() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mCacheMode;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public String getCursiveFontFamily() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mCursiveFontFamily;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private double getDIPScaleLocked() {
        return this.mDIPScale;
    }

    @XWalkAPI public boolean getDatabaseEnabled() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mDatabaseEnabled;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean getDatabaseEnabledLocked() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mDatabaseEnabled;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public int getDefaultFixedFontSize() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mDefaultFixedFontSize;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public int getDefaultFontSize() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mDefaultFontSize;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public static String getDefaultUserAgent() {
        return LazyDefaultUserAgent.access$500();
    }

    public String getDefaultVideoPosterURL() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mDefaultVideoPosterURL;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public int getDisabledActionModeMenuItems() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mDisabledMenuItems;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getDomStorageEnabled() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mDomStorageEnabled;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public String getFantasyFontFamily() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mFantasyFontFamily;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public String getFixedFontFamily() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mFixedFontFamily;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    boolean getGeolocationEnabled() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mGeolocationEnabled;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private float getInitialPageScalePercentLocked() {
        return this.mInitialPageScalePercent;
    }

    @XWalkAPI public boolean getJavaScriptCanOpenWindowsAutomatically() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mJavaScriptCanOpenWindowsAutomatically;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean getJavaScriptCanOpenWindowsAutomaticallyLocked() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mJavaScriptCanOpenWindowsAutomatically;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getJavaScriptEnabled() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mJavaScriptEnabled;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public LayoutAlgorithmInternal getLayoutAlgorithm() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mLayoutAlgorithm;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getLoadWithOverviewMode() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mLoadWithOverviewMode;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getLoadsImagesAutomatically() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mLoadsImagesAutomatically;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getMediaPlaybackRequiresUserGesture() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mMediaPlaybackRequiresUserGesture;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public int getMinimumFontSize() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mMinimumFontSize;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public int getMinimumLogicalFontSize() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mMinimumLogicalFontSize;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean getPasswordEchoEnabledLocked() {
        return this.mPasswordEchoEnabled;
    }

    @XWalkAPI public String getSansSerifFontFamily() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mSansSerifFontFamily;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getSaveFormData() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.getSaveFormDataLocked();
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean getSaveFormDataLocked() {
        return this.mAutoCompleteEnabled;
    }

    @XWalkAPI public String getSerifFontFamily() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mSerifFontFamily;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public String getStandardFontFamily() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mStandardFontFamily;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getSupportQuirksMode() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mQuirksModeEnabled;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getSupportSpatialNavigation() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mSpatialNavigationEnabled;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean getTextAutosizingEnabledLocked() {
        boolean v0 = this.mLayoutAlgorithm == LayoutAlgorithmInternal.TEXT_AUTOSIZING ? true : false;
        return v0;
    }

    @XWalkAPI public int getTextZoom() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mTextSizePercent;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getUseWideViewPort() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mUseWideViewport;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private String getUserAgentLocked() {
        return this.mUserAgent;
    }

    @XWalkAPI public String getUserAgentString() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mUserAgent;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public int getUsingForAppBrand() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mUsingForAppBrand;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean getUsingForAppBrandWeApp() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mUsingForAppBrandWeApp;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    private native void nativeDestroy(long arg1) {
    }

    private static native String nativeGetDefaultUserAgent() {
    }

    private native long nativeInit(WebContents arg1) {
    }

    private static native void nativeInvokeChannel(int arg0, Object[] arg1) {
    }

    private native void nativeResetScrollAndScaleState(long arg1) {
    }

    private native void nativeSetJSExceptionCaptureEnable(long arg1) {
    }

    private static native void nativeSetLogEnable(long arg0) {
    }

    private static native void nativeSetWindowPerformanceReporterEnable(boolean arg0) {
    }

    private native void nativeUpdateAcceptLanguages(long arg1) {
    }

    private native void nativeUpdateEverythingLocked(long arg1) {
    }

    private native void nativeUpdateFormDataPreferences(long arg1) {
    }

    private native void nativeUpdateInitialPageScale(long arg1) {
    }

    private native void nativeUpdateUserAgent(long arg1) {
    }

    private native void nativeUpdateWebkitPreferences(long arg1) {
    }

    @CalledByNative private void nativeXWalkSettingsGone(long arg1) {
        this.mNativeXWalkSettings = 0;
    }

    private void onGestureZoomSupportChanged(boolean arg3, boolean arg4) {
        this.mEventHandler.maybePostOnUiThread(new Runnable(arg3, arg4) {
            public void run() {
                Object v0 = XWalkSettingsInternal.this.mXWalkSettingsLock;
                __monitor_enter(v0);
                try {
                    if(XWalkSettingsInternal.this.mZoomChangeListener == null) {
                        __monitor_exit(v0);
                        return;
                    }

                    XWalkSettingsInternal.this.mZoomChangeListener.onGestureZoomSupportChanged(this.val$supportsDoubleTapZoom, this.val$supportsMultiTouchZoom);
                    __monitor_exit(v0);
                    return;
                label_16:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_16;
                }

                throw v1;
            }
        });
    }

    @CalledByNative public static void onJSExceptionCallBack(String arg4, String arg5) {
        String v0 = "";
        int v1 = arg5.indexOf(":");
        if(v1 > 0) {
            v0 = arg5.substring(0, v1).trim();
            arg5 = arg5.substring(v1 + 1).trim();
        }

        Log.d("XWalkSettings", "onJSErrorCallBack: " + arg4 + "|type:" + v0 + "|info:" + arg5);
        if(XWalkSettingsInternal.m_js_exception_callback == null) {
            return;
        }

        XWalkSettingsInternal.m_js_exception_callback.onJsException(arg4, v0, arg5);
    }

    @CalledByNative public static void onLogMessageCallBack(int arg1, String arg2, int arg3, String arg4) {
        if(XWalkSettingsInternal.m_log_callback == null) {
            return;
        }

        XWalkSettingsInternal.m_log_callback.onLogMessage(arg1, arg2, arg3, arg4);
    }

    @CalledByNative public static void onNotifyCallBackChannel(int arg4, Object[] arg5) {
        if(XWalkSettingsInternal.m_notify_callback_channel == null) {
            return;
        }

        if(arg4 == 50001 && arg5 != null && arg5.length >= 2) {
            if((arg5[0].equals(String.valueOf(938))) && !XWalkSettingsInternal.mIsSupportM67IDKEYReport) {
                org.xwalk.core.internal.Log.i("XWalkSettings", "idkey id:" + arg5[0] + ",sdk version is low, quit report !");
                return;
            }

            org.xwalk.core.internal.Log.i("XWalkSettings", "idkey id:" + arg5[0] + ",key:" + arg5[1]);
        }

        XWalkSettingsInternal.m_notify_callback_channel.onNotifyCallBackChannel(arg4, arg5);
    }

    @CalledByNative public static void onWindowPerformanceReported(long arg45, long arg47, long arg49, long arg51, long arg53, long arg55, long arg57, long arg59, long arg61, long arg63, long arg65, long arg67, long arg69, long arg71, long arg73, long arg75, long arg77, long arg79, long arg81, long arg83, long arg85, long arg87, long arg89, double arg91) {
        if(XWalkSettingsInternal.mWindowPerformanceHandler != null) {
            WindowPerformance v0 = new WindowPerformance();
            v0.navigation = new Navigation(arg45, arg47);
            v0.timing = new Timing(arg49, arg51, arg53, arg55, arg57, arg59, arg61, arg63, arg65, arg67, arg69, arg71, arg73, arg75, arg77, arg79, arg81, arg83, arg85, arg87, arg89);
            v0.timeOrigin = arg91;
            XWalkSettingsInternal.mWindowPerformanceHandler.onWindowPerformanceReport(v0);
        }
    }

    @XWalkAPI public void setAcceptLanguages(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAcceptLanguages == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mAcceptLanguages = arg3;
            this.mEventHandler.maybeRunOnUiThreadBlocking(new Runnable() {
                public void run() {
                    if(XWalkSettingsInternal.this.mNativeXWalkSettings != 0) {
                        XWalkSettingsInternal.this.nativeUpdateAcceptLanguages(XWalkSettingsInternal.this.mNativeXWalkSettings);
                    }
                }
            });
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    @XWalkAPI public void setAllowContentAccess(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAllowContentUrlAccess != arg3) {
                this.mAllowContentUrlAccess = arg3;
            }

            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    @XWalkAPI public void setAllowFileAccess(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAllowFileUrlAccess != arg3) {
                this.mAllowFileUrlAccess = arg3;
            }

            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    @XWalkAPI public void setAllowFileAccessFromFileURLs(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAllowFileAccessFromFileURLs != arg3) {
                this.mAllowFileAccessFromFileURLs = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    public void setAllowScriptsToCloseWindows(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAllowScriptsToCloseWindows != arg3) {
                this.mAllowScriptsToCloseWindows = arg3;
            }

            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    @XWalkAPI public void setAllowUniversalAccessFromFileURLs(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAllowUniversalAccessFromFileURLs != arg3) {
                this.mAllowUniversalAccessFromFileURLs = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    public void setAppCacheEnabled(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAppCacheEnabled != arg3) {
                this.mAppCacheEnabled = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    public void setAppCachePath(String arg4) {
        Object v0 = XWalkSettingsInternal.sGlobalContentSettingsLock;
        __monitor_enter(v0);
        try {
            int v2 = 1;
            if((XWalkSettingsInternal.sAppCachePathIsSet) || arg4 == null || (arg4.isEmpty())) {
                v2 = 0;
            }
            else {
                XWalkSettingsInternal.sAppCachePathIsSet = true;
            }

            __monitor_exit(v0);
            if(v2 == 0) {
                return;
            }
        }
        catch(Throwable v4) {
            try {
            label_24:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_24;
            }

            throw v4;
        }

        Object v4_1 = this.mXWalkSettingsLock;
        __monitor_enter(v4_1);
        try {
            EventHandler.access$600(this.mEventHandler);
            __monitor_exit(v4_1);
            return;
        label_20:
            __monitor_exit(v4_1);
        }
        catch(Throwable v0_1) {
            goto label_20;
        }

        throw v0_1;
    }

    @XWalkAPI public void setBlockNetworkImage(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mImagesEnabled == arg3) {
                this.mImagesEnabled = (((int)arg3)) ^ 1;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @XWalkAPI public void setBlockNetworkLoads(boolean arg6) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        if(!arg6) {
            try {
                if(this.mContext.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) != 0) {
                    throw new SecurityException("Permission denied - application missing INTERNET permission");
                }

            label_15:
                this.mBlockNetworkLoads = arg6;
                __monitor_exit(v0);
                return;
            label_18:
                __monitor_exit(v0);
                goto label_19;
            }
            catch(Throwable v6) {
                goto label_18;
            }
        }

        goto label_15;
    label_19:
        throw v6;
    }

    @XWalkAPI public void setBuiltInZoomControls(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mBuiltInZoomControls == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mBuiltInZoomControls = arg3;
            this.onGestureZoomSupportChanged(this.supportsDoubleTapZoomLocked(), this.supportsMultiTouchZoomLocked());
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    @XWalkAPI public void setCacheMode(int arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mCacheMode != arg3) {
                this.mCacheMode = arg3;
            }

            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    @XWalkAPI public void setCursiveFontFamily(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(!this.mCursiveFontFamily.equals(arg3)) {
                this.mCursiveFontFamily = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    void setDIPScale(double arg2) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            this.mDIPScale = arg2;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }

    @XWalkAPI public void setDatabaseEnabled(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mDatabaseEnabled != arg3) {
                this.mDatabaseEnabled = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setDefaultFixedFontSize(int arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            arg3 = this.clipFontSize(arg3);
            if(this.mDefaultFixedFontSize == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mDefaultFixedFontSize = arg3;
            EventHandler.access$600(this.mEventHandler);
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    @XWalkAPI public void setDefaultFontSize(int arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            arg3 = this.clipFontSize(arg3);
            if(this.mDefaultFontSize == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mDefaultFontSize = arg3;
            EventHandler.access$600(this.mEventHandler);
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public void setDefaultVideoPosterURL(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mDefaultVideoPosterURL != null && !this.mDefaultVideoPosterURL.equals(arg3) || this.mDefaultVideoPosterURL == null && arg3 != null) {
                this.mDefaultVideoPosterURL = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_16;
        }

        throw v3;
    }

    public void setDisabledActionModeMenuItems(int arg2) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            this.mDisabledMenuItems = arg2;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }

    @XWalkAPI public void setDomStorageEnabled(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mDomStorageEnabled != arg3) {
                this.mDomStorageEnabled = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setFantasyFontFamily(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(!this.mFantasyFontFamily.equals(arg3)) {
                this.mFantasyFontFamily = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @XWalkAPI public void setFixedFontFamily(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(!this.mFixedFontFamily.equals(arg3)) {
                this.mFixedFontFamily = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    public void setGeolocationEnabled(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mGeolocationEnabled != arg3) {
                this.mGeolocationEnabled = arg3;
            }

            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    @XWalkAPI public void setInitialPageScale(float arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mInitialPageScalePercent == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mInitialPageScalePercent = arg3;
            this.mEventHandler.maybeRunOnUiThreadBlocking(new Runnable() {
                public void run() {
                    if(XWalkSettingsInternal.this.mNativeXWalkSettings != 0) {
                        XWalkSettingsInternal.this.nativeUpdateInitialPageScale(XWalkSettingsInternal.this.mNativeXWalkSettings);
                    }
                }
            });
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    @XWalkAPI public void setJavaScriptCanOpenWindowsAutomatically(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mJavaScriptCanOpenWindowsAutomatically != arg3) {
                this.mJavaScriptCanOpenWindowsAutomatically = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setJavaScriptEnabled(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mJavaScriptEnabled != arg3) {
                this.mJavaScriptEnabled = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setLayoutAlgorithm(LayoutAlgorithmInternal arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mLayoutAlgorithm == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mLayoutAlgorithm = arg3;
            EventHandler.access$600(this.mEventHandler);
            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    @XWalkAPI public void setLoadWithOverviewMode(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mLoadWithOverviewMode == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mLoadWithOverviewMode = arg3;
            this.mEventHandler.maybeRunOnUiThreadBlocking(new Runnable() {
                public void run() {
                    if(XWalkSettingsInternal.this.mNativeXWalkSettings != 0) {
                        EventHandler.access$600(XWalkSettingsInternal.this.mEventHandler);
                        XWalkSettingsInternal.this.nativeResetScrollAndScaleState(XWalkSettingsInternal.this.mNativeXWalkSettings);
                    }
                }
            });
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    @XWalkAPI public void setLoadsImagesAutomatically(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mLoadsImagesAutomatically != arg3) {
                this.mLoadsImagesAutomatically = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setMediaPlaybackRequiresUserGesture(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mMediaPlaybackRequiresUserGesture != arg3) {
                this.mMediaPlaybackRequiresUserGesture = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setMinimumFontSize(int arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            arg3 = this.clipFontSize(arg3);
            if(this.mMinimumFontSize != arg3) {
                this.mMinimumFontSize = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @XWalkAPI public void setMinimumLogicalFontSize(int arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            arg3 = this.clipFontSize(arg3);
            if(this.mMinimumLogicalFontSize != arg3) {
                this.mMinimumLogicalFontSize = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @XWalkAPI public void setSansSerifFontFamily(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(!this.mSansSerifFontFamily.equals(arg3)) {
                this.mSansSerifFontFamily = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @XWalkAPI public void setSaveFormData(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mAutoCompleteEnabled == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mAutoCompleteEnabled = arg3;
            this.mEventHandler.maybeRunOnUiThreadBlocking(new Runnable() {
                public void run() {
                    if(XWalkSettingsInternal.this.mNativeXWalkSettings != 0) {
                        XWalkSettingsInternal.this.nativeUpdateFormDataPreferences(XWalkSettingsInternal.this.mNativeXWalkSettings);
                    }
                }
            });
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    @XWalkAPI public void setSerifFontFamily(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(!this.mSerifFontFamily.equals(arg3)) {
                this.mSerifFontFamily = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @XWalkAPI public void setStandardFontFamily(String arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(!this.mStandardFontFamily.equals(arg3)) {
                this.mStandardFontFamily = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    @XWalkAPI public void setSupportMultipleWindows(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mSupportMultipleWindows != arg3) {
                this.mSupportMultipleWindows = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setSupportQuirksMode(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mQuirksModeEnabled == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mQuirksModeEnabled = arg3;
            EventHandler.access$600(this.mEventHandler);
            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    @XWalkAPI public void setSupportSmartPickWord(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mSupportSmartPickWord != arg3) {
                this.mSupportSmartPickWord = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setSupportSpatialNavigation(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mSpatialNavigationEnabled == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mSpatialNavigationEnabled = arg3;
            EventHandler.access$600(this.mEventHandler);
            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    @XWalkAPI public void setSupportZoom(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mSupportZoom == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mSupportZoom = arg3;
            this.onGestureZoomSupportChanged(this.supportsDoubleTapZoomLocked(), this.supportsMultiTouchZoomLocked());
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    @XWalkAPI public void setTextZoom(int arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mTextSizePercent == arg3) {
                __monitor_exit(v0);
                return;
            }

            this.mTextSizePercent = arg3;
            EventHandler.access$600(this.mEventHandler);
            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    @XWalkAPI public void setUseWideViewPort(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mUseWideViewport != arg3) {
                this.mUseWideViewport = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    @XWalkAPI public void setUserAgentString(String arg4) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            String v1 = this.mUserAgent;
            this.mUserAgent = arg4 == null || arg4.length() == 0 ? LazyDefaultUserAgent.access$500() : arg4;
            if(!v1.equals(this.mUserAgent)) {
                this.mEventHandler.maybeRunOnUiThreadBlocking(new Runnable() {
                    public void run() {
                        if(XWalkSettingsInternal.this.mNativeXWalkSettings != 0) {
                            XWalkSettingsInternal.this.nativeUpdateUserAgent(XWalkSettingsInternal.this.mNativeXWalkSettings);
                        }
                    }
                });
            }

            __monitor_exit(v0);
            return;
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_21;
        }

        throw v4;
    }

    public void setUsingForAppBrand(int arg4) {
        if(arg4 == 1) {
            org.xwalk.core.internal.Log.i("XWalkSettings", "setUsingForAppBrand:APP_BRAND_TYPE_WE_APP");
        }
        else if(arg4 == 2) {
            org.xwalk.core.internal.Log.i("XWalkSettings", "setUsingForAppBrand:APP_BRAND_TYPE_WE_APP_EMBEDDED_HTML");
        }
        else {
            org.xwalk.core.internal.Log.i("XWalkSettings", "setUsingForAppBrand:APP_BRAND_TYPE_NONE");
        }

        Object v1 = this.mXWalkSettingsLock;
        __monitor_enter(v1);
        try {
            if(this.mUsingForAppBrand != arg4) {
                this.mUsingForAppBrand = arg4;
            }

            __monitor_exit(v1);
            if(arg4 != 1) {
                goto label_24;
            }
        }
        catch(Throwable v4) {
            try {
            label_28:
                __monitor_exit(v1);
            }
            catch(Throwable v4) {
                goto label_28;
            }

            throw v4;
        }

        this.setUsingForAppBrandWeApp(true);
        return;
    label_24:
        this.setUsingForAppBrandWeApp(false);
    }

    @XWalkAPI public void setUsingForAppBrandWeApp(boolean arg3) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mUsingForAppBrandWeApp != arg3) {
                this.mUsingForAppBrandWeApp = arg3;
                EventHandler.access$600(this.mEventHandler);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }

    void setWebContents(WebContents arg7) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            if(this.mNativeXWalkSettings != 0) {
                this.nativeDestroy(this.mNativeXWalkSettings);
            }

            if(arg7 != null) {
                this.mEventHandler.bindUiThread();
                this.mNativeXWalkSettings = this.nativeInit(arg7);
                this.nativeUpdateEverythingLocked(this.mNativeXWalkSettings);
            }

            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_17;
        }

        throw v7;
    }

    public static void setWindowPerformanceHandler(WindowPerformanceHandler arg0) {
        XWalkSettingsInternal.mWindowPerformanceHandler = arg0;
    }

    public static void setWindowPerformanceReporterEnable(boolean arg0) {
        XWalkSettingsInternal.nativeSetWindowPerformanceReporterEnable(arg0);
    }

    void setZoomListener(ZoomSupportChangeListener arg2) {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            this.mZoomChangeListener = arg2;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }

    @XWalkAPI public boolean supportMultipleWindows() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mSupportMultipleWindows;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @XWalkAPI public boolean supportZoom() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mSupportZoom;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @CalledByNative private boolean supportsDoubleTapZoomLocked() {
        boolean v0 = !this.mSupportZoom || !this.mBuiltInZoomControls || !this.mUseWideViewport ? false : true;
        return v0;
    }

    @XWalkAPI public boolean supportsMultiTouchZoomForTest() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.supportsMultiTouchZoomLocked();
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    private boolean supportsMultiTouchZoomLocked() {
        boolean v0 = !this.mSupportZoom || !this.mBuiltInZoomControls ? false : true;
        return v0;
    }

    @CalledByNative private void updateEverything() {
        Object v0 = this.mXWalkSettingsLock;
        __monitor_enter(v0);
        try {
            this.nativeUpdateEverythingLocked(this.mNativeXWalkSettings);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    private void updateWebkitPreferencesOnUiThread() {
        if(this.mNativeXWalkSettings != 0) {
            ThreadUtils.assertOnUiThread();
            this.nativeUpdateWebkitPreferences(this.mNativeXWalkSettings);
        }
    }
}

