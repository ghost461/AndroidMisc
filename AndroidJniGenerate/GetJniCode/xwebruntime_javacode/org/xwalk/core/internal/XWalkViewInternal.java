package org.xwalk.core.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ApplicationStatusManager;
import org.chromium.content_public.browser.ContentViewCore;
import org.chromium.ui.base.WindowAndroid;

@XWalkAPI(createExternally=true, extendClass=FrameLayout.class) public class XWalkViewInternal extends FrameLayout {
    static final String PLAYSTORE_DETAIL_URI = "market://details?id=";
    @XWalkAPI public static final int RELOAD_IGNORE_CACHE = 1;
    @XWalkAPI public static final int RELOAD_NORMAL = 0;
    @XWalkAPI public static final String SURFACE_VIEW = "SurfaceView";
    private static final String TAG = "XWalkViewInternal";
    @XWalkAPI public static final String TEXTURE_VIEW = "TextureView";
    private XWalkContent mContent;
    private Context mContext;
    private XWalkExternalExtensionManagerInternal mExternalExtensionManager;
    protected XWalkViewFactoryProvider mFactory;
    private boolean mIsHidden;
    private boolean mIsHorizontalScrollBarEnable;
    private boolean mIsVerticalScrollBarEnable;
    private XWalkViewShared mSharedXWalkView;
    private final XWalkHitTestResultInternal mXWalkHitTestResult;

    @XWalkAPI(postWrapperLines={"        addView((FrameLayout)bridge, new FrameLayout.LayoutParams(", "                FrameLayout.LayoutParams.MATCH_PARENT,", "                FrameLayout.LayoutParams.MATCH_PARENT));", "        removeViewAt(0);", "        new org.xwalk.core.extension.XWalkExternalExtensionManagerImpl(this);"}, preWrapperLines={"        super(${param1}, null);", "        SurfaceView surfaceView = new SurfaceView(${param1});", "        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));", "        addView(surfaceView);"}) public XWalkViewInternal(Context arg2) {
        super(arg2, null);
        this.mIsVerticalScrollBarEnable = true;
        this.mIsHorizontalScrollBarEnable = true;
        XWalkViewInternal.checkThreadSafety();
        this.mContext = this.getContext();
        this.mXWalkHitTestResult = new XWalkHitTestResultInternal();
        this.initXWalkContent(null);
    }

    @Deprecated @XWalkAPI(postWrapperLines={"        addView((FrameLayout)bridge, new FrameLayout.LayoutParams(", "                FrameLayout.LayoutParams.MATCH_PARENT,", "                FrameLayout.LayoutParams.MATCH_PARENT));", "        removeViewAt(0);", "        new org.xwalk.core.extension.XWalkExternalExtensionManagerImpl(this);"}, preWrapperLines={"        super(${param1}, null);", "        SurfaceView surfaceView = new SurfaceView(${param1});", "        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));", "        addView(surfaceView);"}) public XWalkViewInternal(Context arg1, Activity arg2) {
        super(arg1, null);
        this.mIsVerticalScrollBarEnable = true;
        this.mIsHorizontalScrollBarEnable = true;
        XWalkViewInternal.checkThreadSafety();
        this.mContext = this.getContext();
        this.mXWalkHitTestResult = new XWalkHitTestResultInternal();
        this.initXWalkContent(null);
    }

    @XWalkAPI(postBridgeLines={"        String animatable = null;", "        try {", "            animatable = (String) new ReflectField(wrapper, \"mAnimatable\").get();", "        } catch (RuntimeException e) {", "        }", "        initXWalkContent(animatable);"}, postWrapperLines={"        addView((FrameLayout)bridge, new FrameLayout.LayoutParams(", "                FrameLayout.LayoutParams.MATCH_PARENT,", "                FrameLayout.LayoutParams.MATCH_PARENT));", "        removeViewAt(0);", "        new org.xwalk.core.extension.XWalkExternalExtensionManagerImpl(this);"}, preWrapperLines={"        super(${param1}, ${param2});", "        if (isInEditMode()) return;", "        if (${param2} != null)", "            mAnimatable = ${param2}.getAttributeValue(", "                    XWALK_ATTRS_NAMESPACE, ANIMATABLE);", "        SurfaceView surfaceView = new SurfaceView(${param1});", "        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));", "        addView(surfaceView);"}) public XWalkViewInternal(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.mIsVerticalScrollBarEnable = true;
        this.mIsHorizontalScrollBarEnable = true;
        XWalkViewInternal.checkThreadSafety();
        this.mContext = this.getContext();
        this.mXWalkHitTestResult = new XWalkHitTestResultInternal();
        this.initXWalkContent(null);
    }

    static void access$000(XWalkViewInternal arg0, String arg1, String arg2, Map arg3) {
        arg0.loadInternal(arg1, arg2, arg3);
    }

    static XWalkContent access$100(XWalkViewInternal arg0) {
        return arg0.mContent;
    }

    static void access$200(XWalkViewInternal arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        arg0.loadDataWithBaseURLInternal(arg1, arg2, arg3, arg4, arg5);
    }

    static XWalkHitTestResultInternal access$300(XWalkViewInternal arg0) {
        return arg0.getHitTestResultInternal();
    }

    static boolean access$402(XWalkViewInternal arg0, boolean arg1) {
        arg0.mIsHidden = arg1;
        return arg1;
    }

    static void access$501(XWalkViewInternal arg0, int arg1) {
        super.setVisibility(arg1);
    }

    static XWalkExternalExtensionManagerInternal access$600(XWalkViewInternal arg0) {
        return arg0.mExternalExtensionManager;
    }

    static XWalkExternalExtensionManagerInternal access$602(XWalkViewInternal arg0, XWalkExternalExtensionManagerInternal arg1) {
        arg0.mExternalExtensionManager = arg1;
        return arg1;
    }

    static boolean access$700(XWalkViewInternal arg0) {
        return arg0.mIsVerticalScrollBarEnable;
    }

    static boolean access$702(XWalkViewInternal arg0, boolean arg1) {
        arg0.mIsVerticalScrollBarEnable = arg1;
        return arg1;
    }

    static boolean access$800(XWalkViewInternal arg0) {
        return arg0.mIsHorizontalScrollBarEnable;
    }

    static boolean access$802(XWalkViewInternal arg0, boolean arg1) {
        arg0.mIsHorizontalScrollBarEnable = arg1;
        return arg1;
    }

    @XWalkAPI(reservable=true) public void addJavascriptInterface(Object arg3, String arg4) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3, arg4) {
                public void run() {
                    XWalkViewInternal.this.mContent.addJavascriptInterface(this.val$object, this.val$name);
                }
            });
            return;
        }

        this.mContent.addJavascriptInterface(arg3, arg4);
    }

    @XWalkAPI public void adjustSelectPosition(long arg10, String arg12, int arg13, int arg14) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg10, arg12, arg13, arg14) {
                public void run() {
                    XWalkViewInternal.this.mContent.adjustSelectPosition(this.val$time, this.val$pickedText, this.val$startAdjust, this.val$endAdjust);
                }
            });
            return;
        }

        this.mContent.adjustSelectPosition(arg10, arg12, arg13, arg14);
    }

    boolean canGoBack() {
        if(this.mContent == null) {
            return 0;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Boolean call() {
                    return Boolean.valueOf(XWalkViewInternal.this.mContent.canGoBack());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).booleanValue();
        }

        return this.mContent.canGoBack();
    }

    boolean canGoForward() {
        if(this.mContent == null) {
            return 0;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Boolean call() {
                    return Boolean.valueOf(XWalkViewInternal.this.mContent.canGoForward());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).booleanValue();
        }

        return this.mContent.canGoForward();
    }

    @XWalkAPI public boolean canZoomIn() {
        if(this.mContent == null) {
            return 0;
        }

        if(this.checkNeedsPost()) {
            return 0;
        }

        return this.mContent.canZoomIn();
    }

    @XWalkAPI public boolean canZoomOut() {
        if(this.mContent == null) {
            return 0;
        }

        if(this.checkNeedsPost()) {
            return 0;
        }

        return this.mContent.canZoomOut();
    }

    @XWalkAPI public void captureBitmapAsync(XWalkGetBitmapCallbackInternal arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.captureBitmapAsync(this.val$callback);
                }
            });
            return;
        }

        this.mContent.captureBitmapAsync(arg3);
    }

    public void checkHorizontalScrollBarEnableInternal() {
        if(this.mContent == null) {
            return;
        }

        Log.i("XWalkViewInternal", "checkHorizontalScrollBarEnableInternal in isHorizontalScrollBarEnable() = " + this.mIsHorizontalScrollBarEnable);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.SetHorizontalScrollBarEnable(XWalkViewInternal.this.mIsHorizontalScrollBarEnable);
                }
            });
            return;
        }

        this.mContent.SetHorizontalScrollBarEnable(this.mIsHorizontalScrollBarEnable);
    }

    protected boolean checkNeedsPost() {
        return this.mSharedXWalkView.checkNeedsPost();
    }

    private static void checkThreadSafety() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Warning: A XWalkViewInternal method was called on thread \'");
            v1.append(Thread.currentThread().getName());
            v1.append("\'. All XWalkViewInternal methods must be called on the UI thread. ");
            throw new RuntimeException(new Throwable(v1.toString()));
        }
    }

    public void checkVerticalScrollBarEnableInternal() {
        if(this.mContent == null) {
            return;
        }

        Log.i("XWalkViewInternal", "checkVerticalScrollBarEnableInternal in isVerticalScrollBarEnable() = " + this.mIsVerticalScrollBarEnable);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.SetVerticalScrollBarEnable(XWalkViewInternal.this.mIsVerticalScrollBarEnable);
                }
            });
            return;
        }

        this.mContent.SetVerticalScrollBarEnable(this.mIsVerticalScrollBarEnable);
    }

    @XWalkAPI public void clearCache(boolean arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.clearCache(this.val$includeDiskFiles);
                }
            });
            return;
        }

        this.mContent.clearCache(arg3);
    }

    @XWalkAPI public void clearCacheForSingleFile(String arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.clearCacheForSingleFile(this.val$url);
                }
            });
            return;
        }

        this.mContent.clearCacheForSingleFile(arg3);
    }

    @XWalkAPI public void clearClientCertPreferences(Runnable arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.clearClientCertPreferences(this.val$callback);
                }
            });
            return;
        }

        this.mContent.clearClientCertPreferences(arg3);
    }

    @XWalkAPI public void clearFormData() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.hideAutofillPopup();
                }
            });
            return;
        }

        this.mContent.hideAutofillPopup();
    }

    void clearHistory() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.clearHistory();
                }
            });
            return;
        }

        this.mContent.clearHistory();
    }

    @XWalkAPI public void clearMatches() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.clearMatches();
                }
            });
            return;
        }

        this.mContent.clearMatches();
    }

    @XWalkAPI public void clearSslPreferences() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.clearSslPreferences();
                }
            });
            return;
        }

        this.mContent.clearSslPreferences();
    }

    public void completeWindowCreation(XWalkViewInternal arg2) {
        XWalkContent v0 = this.mContent;
        XWalkContent v2 = arg2 == null ? null : arg2.mContent;
        v0.supplyContentsForPopup(v2);
    }

    @XWalkAPI public int computeHorizontalScrollOffset() {
        return this.mContent.computeHorizontalScrollOffset();
    }

    @XWalkAPI public int computeHorizontalScrollRange() {
        return this.mContent.computeHorizontalScrollRange();
    }

    @XWalkAPI public int computeVerticalScrollExtent() {
        this.mSharedXWalkView.startYourEngines(false);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Integer call() {
                    return Integer.valueOf(XWalkViewInternal.this.mContent.computeVerticalScrollExtent());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).intValue();
        }

        return this.mContent.computeVerticalScrollExtent();
    }

    @XWalkAPI public int computeVerticalScrollOffset() {
        this.mSharedXWalkView.startYourEngines(false);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Integer call() {
                    return Integer.valueOf(XWalkViewInternal.this.mContent.computeVerticalScrollOffset());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).intValue();
        }

        return this.mContent.computeVerticalScrollOffset();
    }

    @XWalkAPI public int computeVerticalScrollRange() {
        this.mSharedXWalkView.startYourEngines(false);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Integer call() {
                    return Integer.valueOf(XWalkViewInternal.this.mContent.computeVerticalScrollRange());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).intValue();
        }

        return this.mContent.computeVerticalScrollRange();
    }

    void destroy() {
        if(this.mContent == null) {
            return;
        }

        XWalkContent.removeFromAllWebList(this.mContent);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.destroy();
                }
            });
            return;
        }

        this.mContent.destroy();
    }

    public boolean dispatchKeyEvent(KeyEvent arg3) {
        if(arg3.getAction() == 0 && arg3.getKeyCode() == 4 && (this.hasEnteredFullscreen())) {
            this.leaveFullscreen();
            return 1;
        }

        return super.dispatchKeyEvent(arg3);
    }

    public void enableRemoteDebugging() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.enableRemoteDebugging();
                }
            });
            return;
        }

        this.mContent.enableRemoteDebugging();
    }

    @XWalkAPI public void evaluateJavascript(String arg3, ValueCallback arg4) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3, arg4) {
                public void run() {
                    XWalkViewInternal.this.mContent.evaluateJavascript(this.val$script, this.val$callback);
                }
            });
            return;
        }

        this.mContent.evaluateJavascript(arg3, arg4);
    }

    @XWalkAPI public void findAllAsync(String arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.findAllAsync(this.val$searchString);
                }
            });
            return;
        }

        this.mContent.findAllAsync(arg3);
    }

    @XWalkAPI public void findNext(boolean arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.findNext(this.val$forward);
                }
            });
            return;
        }

        this.mContent.findNext(arg3);
    }

    @XWalkAPI public String getAPIVersion() {
        return String.valueOf(8) + ".0";
    }

    @XWalkAPI public SslCertificate getCertificate() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public SslCertificate call() {
                    return XWalkViewInternal.this.mContent.getCertificate();
                }

                public Object call() throws Exception {
                    return this.call();
                }
            });
        }

        return this.mContent.getCertificate();
    }

    @XWalkAPI public String getCompositingSurfaceType() {
        if(this.mContent == null) {
            return null;
        }

        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public String call() {
                    return XWalkViewInternal.this.mContent.getCompositingSurfaceType();
                }
            });
        }

        return this.mContent.getCompositingSurfaceType();
    }

    @XWalkAPI public int getContentHeight() {
        return this.mContent.getContentHeight();
    }

    public int getContentID() {
        if(this.mContent == null) {
            return -1;
        }

        return this.mContent.getRoutingID();
    }

    @XWalkAPI public XWalkExternalExtensionManagerInternal getExtensionManager() {
        if(this.mContent == null) {
            return null;
        }

        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public XWalkExternalExtensionManagerInternal call() {
                    return XWalkViewInternal.this.mExternalExtensionManager;
                }
            });
        }

        return this.mExternalExtensionManager;
    }

    @XWalkAPI public Bitmap getFavicon() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Bitmap call() {
                    return XWalkViewInternal.this.mContent.getFavicon();
                }

                public Object call() throws Exception {
                    return this.call();
                }
            });
        }

        return this.mContent.getFavicon();
    }

    @XWalkAPI public XWalkHitTestResultInternal getHitTestResult() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public XWalkHitTestResultInternal call() {
                    return XWalkViewInternal.this.getHitTestResultInternal();
                }
            });
        }

        return this.getHitTestResultInternal();
    }

    private XWalkHitTestResultInternal getHitTestResultInternal() {
        HitTestData v0 = this.mContent.getLastHitTestResult();
        if(v0 != null) {
            if(v0.hitTestResultType != 0 || (TextUtils.isEmpty(v0.imgSrc))) {
                this.mXWalkHitTestResult.setType(v0.hitTestResultType);
            }
            else {
                this.mXWalkHitTestResult.setType(5);
            }

            this.mXWalkHitTestResult.setExtra(v0.hitTestResultExtraData);
        }
        else {
            Log.e("XWalkViewInternal", "getHitTestResultInternal HitTestData = null");
        }

        return this.mXWalkHitTestResult;
    }

    @XWalkAPI public boolean getImageBitmapToFile(String arg9, String arg10, String arg11, XWalkGetImageBitmapToFileFinishedCallbackInternal arg12) {
        if(this.mContent == null) {
            return 0;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg9, arg10, arg11, arg12) {
                public void run() {
                    if(!XWalkViewInternal.this.mContent.getImageBitmapToFile(this.val$url, this.val$filePath, this.val$extraInfo, this.val$callback)) {
                        this.val$callback.onFinishImageBitmapToFile(-1, this.val$url, this.val$filePath, 0, 0, this.val$extraInfo);
                    }
                }
            });
            return 1;
        }

        return this.mContent.getImageBitmapToFile(arg9, arg10, arg11, arg12);
    }

    @XWalkAPI public XWalkNavigationHistoryInternal getNavigationHistory() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public XWalkNavigationHistoryInternal call() {
                    return XWalkViewInternal.this.mContent.getNavigationHistory();
                }
            });
        }

        return this.mContent.getNavigationHistory();
    }

    @XWalkAPI public String getOriginalUrl() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public String call() {
                    return XWalkViewInternal.this.mContent.getOriginalUrl();
                }
            });
        }

        return this.mContent.getOriginalUrl();
    }

    @XWalkAPI public String getRefererUrl() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public String call() {
                    return XWalkViewInternal.this.mContent.getRefererUrl();
                }
            });
        }

        return this.mContent.getRefererUrl();
    }

    @XWalkAPI public Uri getRemoteDebuggingUrl() {
        if(this.mContent == null) {
            return null;
        }

        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Uri call() {
                    return XWalkViewInternal.this.getRemoteDebuggingUrlInternal();
                }

                public Object call() throws Exception {
                    return this.call();
                }
            });
        }

        return this.getRemoteDebuggingUrlInternal();
    }

    public Uri getRemoteDebuggingUrlInternal() {
        String v0 = this.mContent.getRemoteDebuggingUrl();
        if(v0 != null) {
            if(v0.isEmpty()) {
            }
            else {
                return Uri.parse(v0);
            }
        }

        return null;
    }

    @XWalkAPI public float getScale() {
        if(this.mContent == null) {
            return 1f;
        }

        this.mSharedXWalkView.startYourEngines(true);
        return this.mContent.getScale();
    }

    @XWalkAPI public XWalkSettingsInternal getSettings() {
        if(this.mContent == null) {
            return null;
        }

        return this.mContent.getSettings();
    }

    @XWalkAPI public String getTitle() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public String call() {
                    return XWalkViewInternal.this.mContent.getTitle();
                }
            });
        }

        return this.mContent.getTitle();
    }

    @XWalkAPI public void getTranslateSampleString(int arg2) {
        if(this.mContent == null) {
            return;
        }

        XWalkViewInternal.checkThreadSafety();
        this.mContent.getTranslateSampleString(arg2);
    }

    @XWalkAPI public String getUrl() {
        if(this.mContent == null) {
            return null;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Object call() throws Exception {
                    return this.call();
                }

                public String call() {
                    return XWalkViewInternal.this.mContent.getUrl();
                }
            });
        }

        return this.mContent.getUrl();
    }

    @XWalkAPI public String getUserAgentString() {
        XWalkSettingsInternal v0 = this.getSettings();
        if(v0 == null) {
            return null;
        }

        return v0.getUserAgentString();
    }

    public Context getViewContext() {
        return this.mContext;
    }

    public ContentViewCore getXWalkContentForTest() {
        return this.mContent.getContentViewCoreForTest();
    }

    public ViewGroup getXWalkContentView() {
        return this.mContent.getContentView();
    }

    @XWalkAPI public String getXWalkVersion() {
        if(this.mContent == null) {
            return null;
        }

        return this.mContent.getXWalkVersion();
    }

    void goBack() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.goBack();
                }
            });
            return;
        }

        this.mContent.goBack();
    }

    void goForward() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.goForward();
                }
            });
            return;
        }

        this.mContent.goForward();
    }

    @XWalkAPI public boolean hasEnteredFullscreen() {
        if(this.mContent == null) {
            return 0;
        }

        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Boolean call() {
                    return Boolean.valueOf(XWalkViewInternal.this.mContent.hasEnteredFullscreen());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).booleanValue();
        }

        return this.mContent.hasEnteredFullscreen();
    }

    protected void initXWalkContent(String arg3) {
        XWalkViewDelegate.init(null, this.mContext);
        Activity v0 = WindowAndroid.activityFromContext(this.mContext);
        if(v0 != null) {
            int v1 = ApplicationStatus.getStatus(v0);
            ApplicationStatusManager.checkWindowCallBack(v0, ((View)this));
            if(v1 == 0) {
                ApplicationStatusManager.informActivityStarted(v0, ((View)this));
            }
        }

        XWalkPreferencesInternal.setValue("enable-extensions", false);
        this.mIsHidden = false;
        this.mContent = new XWalkContent(this.mContext, arg3, this);
        this.mSharedXWalkView = new XWalkViewShared();
        this.mSharedXWalkView.setContentsOnUiThread(this.mContent);
        this.mFactory = new XWalkViewFactoryProvider(this.mSharedXWalkView.getRunQueue());
        this.mSharedXWalkView.setFactory(this.mFactory);
        this.mSharedXWalkView.startYourEngines(true);
        this.mContent.resumeTimers();
        this.setXWalkClient(new XWalkClient(this));
        this.setXWalkWebChromeClient(new XWalkWebChromeClient());
        this.setUIClient(new XWalkUIClientInternal(this));
        this.setResourceClient(new XWalkResourceClientInternal(this));
        this.setExtendPluginClient(new XWalkExtendPluginClientInternal(this));
        this.setExtendCanvasClient(new XWalkExtendCanvasClientInternal(this));
        this.setDownloadListener(new XWalkDownloadListenerImpl(this.mContext));
        this.setNavigationHandler(new XWalkNavigationHandlerImpl(this.mContext));
        this.setNotificationService(new XWalkNotificationServiceImpl(this.mContext, this));
        XWalkPathHelper.initialize();
        XWalkPathHelper.setCacheDirectory(this.mContext.getApplicationContext().getCacheDir().getPath());
        XWalkPathHelper.initExternalCacheDirectory(this.mContext.getApplicationContext());
    }

    @XWalkAPI public boolean isSupportExtendPluginForAppbrand() {
        return 1;
    }

    @XWalkAPI public void leaveFullscreen() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.exitFullscreen();
                }
            });
            return;
        }

        this.mContent.exitFullscreen();
    }

    @Deprecated @XWalkAPI public void load(String arg2, String arg3) {
        this.load(arg2, arg3, null);
    }

    @Deprecated @XWalkAPI public void load(String arg3, String arg4, Map arg5) {
        if(this.mContent == null) {
            return;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3, arg4, arg5) {
                public void run() {
                    XWalkViewInternal.this.loadInternal(this.val$url, this.val$content, this.val$headers);
                }
            });
            return;
        }

        this.loadInternal(arg3, arg4, arg5);
    }

    @XWalkAPI public void loadAppFromManifest(String arg3, String arg4) {
        if(this.mContent == null) {
            return;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3, arg4) {
                public void run() {
                    XWalkViewInternal.this.mContent.loadAppFromManifest(this.val$url, this.val$content);
                }
            });
            return;
        }

        this.mContent.loadAppFromManifest(arg3, arg4);
    }

    @XWalkAPI public void loadData(String arg3, String arg4, String arg5) {
        if(this.mContent == null) {
            return;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3, arg4, arg5) {
                public void run() {
                    XWalkViewInternal.this.mContent.loadData(this.val$data, this.val$mimeType, this.val$encoding);
                }
            });
            return;
        }

        this.mContent.loadData(arg3, arg4, arg5);
    }

    @XWalkAPI public void loadDataWithBaseURL(String arg10, String arg11, String arg12, String arg13, String arg14) {
        if(this.mContent == null) {
            return;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg10, arg11, arg12, arg13, arg14) {
                public void run() {
                    XWalkViewInternal.this.loadDataWithBaseURLInternal(this.val$baseUrl, this.val$data, this.val$mimeType, this.val$encoding, this.val$historyUrl);
                }
            });
            return;
        }

        this.loadDataWithBaseURLInternal(arg10, arg11, arg12, arg13, arg14);
    }

    private void loadDataWithBaseURLInternal(String arg7, String arg8, String arg9, String arg10, String arg11) {
        if(arg7 == null || !arg7.toLowerCase(Locale.US).startsWith("data:")) {
            this.mContent.loadDataWithBaseURL(arg7, arg8, arg9, arg10, arg11);
        }
        else {
            this.mContent.loadData(arg8, arg9, arg10);
        }
    }

    private void loadInternal(String arg8, String arg9, Map arg10) {
        if(arg8 == null || (arg8.isEmpty())) {
            if(arg9 != null) {
                if(arg9.isEmpty()) {
                }
                else {
                    goto label_7;
                }
            }

            return;
        }

    label_7:
        if(arg8 == null || (arg8.isEmpty()) || !TextUtils.equals(((CharSequence)arg8), this.getUrl())) {
            if(arg9 != null) {
                if(arg9.isEmpty()) {
                }
                else {
                    this.mContent.loadDataWithBaseURL(arg8, arg9, "text/html", null, null);
                    return;
                }
            }

            this.mContent.loadUrl(arg8, arg10);
        }
        else {
            this.reload(0);
        }
    }

    @XWalkAPI public void loadUrl(String arg3) {
        if(this.mContent == null) {
            return;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.loadUrl(this.val$url);
                }
            });
            return;
        }

        this.mContent.loadUrl(arg3);
    }

    @XWalkAPI public void loadUrl(String arg3, Map arg4) {
        if(this.mContent == null) {
            return;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3, arg4) {
                public void run() {
                    XWalkViewInternal.this.mContent.loadUrl(this.val$url, this.val$additionalHttpHeaders);
                }
            });
            return;
        }

        this.mContent.loadUrl(arg3, arg4);
    }

    void navigateTo(int arg2) {
        if(this.mContent == null) {
            return;
        }

        this.mContent.navigateTo(arg2);
    }

    @Deprecated @XWalkAPI public void onActivityResult(int arg1, int arg2, Intent arg3) {
    }

    public void onBaseContextChanged() {
        if(this.mContent != null) {
            this.mContent.onBaseContextChanged();
        }
    }

    @XWalkAPI public InputConnection onCreateInputConnection(EditorInfo arg2) {
        if(this.checkNeedsPost()) {
            return null;
        }

        return this.mContent.onCreateInputConnection(arg2);
    }

    @XWalkAPI public void onDestroy() {
        this.destroy();
    }

    @XWalkAPI(delegate=true, preWrapperLines={"onFocusChanged(gainFocus, direction, previouslyFocusedRect);"}) public void onFocusChangedDelegate(boolean arg1, int arg2, Rect arg3) {
    }

    @XWalkAPI public void onHide() {
        if(this.mContent != null) {
            if(this.mIsHidden) {
            }
            else if(this.checkNeedsPost()) {
                this.mFactory.addTask(new Runnable() {
                    public void run() {
                        XWalkViewInternal.this.mContent.onPause();
                        XWalkViewInternal.this.mIsHidden = true;
                    }
                });
                return;
            }
            else {
                this.mContent.onPause();
                this.mIsHidden = true;
                return;
            }
        }
    }

    @XWalkAPI public boolean onNewIntent(Intent arg2) {
        if(this.mContent == null) {
            return 0;
        }

        if(this.mExternalExtensionManager != null) {
            this.mExternalExtensionManager.onNewIntent(arg2);
        }

        return this.mContent.onNewIntent(arg2);
    }

    @XWalkAPI(delegate=true, preWrapperLines={"onOverScrolled(scrollX, scrollY, clampedX, clampedY);"}) public void onOverScrolledDelegate(int arg1, int arg2, boolean arg3, boolean arg4) {
    }

    public void onOverscrollRefresh(boolean arg1) {
    }

    @XWalkAPI(delegate=true, preWrapperLines={"onScrollChanged(l, t, oldl, oldt);"}) public void onScrollChangedDelegate(int arg1, int arg2, int arg3, int arg4) {
    }

    @XWalkAPI public void onShow() {
        if(this.mContent != null) {
            if(!this.mIsHidden) {
            }
            else if(this.checkNeedsPost()) {
                this.mFactory.addTask(new Runnable() {
                    public void run() {
                        XWalkViewInternal.this.mContent.onResume();
                        XWalkViewInternal.this.mIsHidden = false;
                    }
                });
                return;
            }
            else {
                this.mContent.onResume();
                this.mIsHidden = false;
                return;
            }
        }
    }

    @XWalkAPI public boolean onTouchEvent(MotionEvent arg2) {
        return this.mContent.onTouchEvent(arg2);
    }

    @XWalkAPI(delegate=true, preWrapperLines={"return onTouchEvent(event);"}) public boolean onTouchEventDelegate(MotionEvent arg1) {
        return 0;
    }

    @XWalkAPI protected boolean overScrollBy(int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, int arg13, boolean arg14) {
        super.getOverScrollMode();
        boolean v2 = false;
        arg8 += arg6;
        arg9 += arg7;
        arg6 = -0;
        arg7 = arg10;
        arg10 = -0;
        arg11 = arg11;
        if(arg8 > arg7) {
            arg6 = arg7;
            goto label_41;
        }
        else if(arg8 < arg6) {
        label_41:
            arg7 = 1;
        }
        else {
            arg6 = arg8;
            arg7 = 0;
        }

        if(arg9 > arg11) {
            arg9 = arg11;
            goto label_49;
        }
        else if(arg9 < arg10) {
            arg9 = arg10;
        label_49:
            arg8 = 1;
        }
        else {
            arg8 = 0;
        }

        this.scrollTo(arg6, arg9);
        if(arg7 != 0 || arg8 != 0) {
            v2 = true;
        }

        return v2;
    }

    @XWalkAPI public void pauseTimers() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.pauseTimers();
                }
            });
            return;
        }

        this.mContent.pauseTimers();
    }

    @XWalkAPI(delegate=true, preWrapperLines={"return performLongClick();"}) public boolean performLongClickDelegate() {
        return 0;
    }

    public boolean preInitViewSize(int arg4, int arg5, boolean arg6) {
        Log.i("XWalkViewInternal", "preInitViewSize width " + arg4 + ", height:" + arg5);
        if(this.mContent == null) {
            return 0;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg4, arg5, arg6) {
                public void run() {
                    XWalkViewInternal.this.mContent.preInitViewSize(this.val$width, this.val$height, this.val$needFakeRender);
                }
            });
        }

        this.mContent.preInitViewSize(arg4, arg5, arg6);
        return 1;
    }

    @XWalkAPI public void reload(int arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.reload(this.val$mode);
                }
            });
            return;
        }

        this.mContent.reload(arg3);
    }

    @XWalkAPI(reservable=true) public void removeJavascriptInterface(String arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.removeJavascriptInterface(this.val$name);
                }
            });
            return;
        }

        this.mContent.removeJavascriptInterface(arg3);
    }

    @XWalkAPI public void replaceTranslatedString(Map arg2) {
        if(this.mContent == null) {
            return;
        }

        XWalkViewInternal.checkThreadSafety();
        this.mContent.replaceTranslatedString(arg2);
    }

    @XWalkAPI public boolean restoreState(Bundle arg4) {
        if(this.mContent == null) {
            return 0;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable(arg4) {
                public Boolean call() {
                    boolean v0 = XWalkViewInternal.this.mContent.restoreState(this.val$inState) != null ? true : false;
                    return Boolean.valueOf(v0);
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).booleanValue();
        }

        if(this.mContent.restoreState(arg4) != null) {
            return 1;
        }

        return 0;
    }

    @XWalkAPI public void resumeTimers() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.resumeTimers();
                }
            });
            return;
        }

        this.mContent.resumeTimers();
    }

    @XWalkAPI public boolean savePage(String arg3, String arg4, int arg5) {
        if(this.mContent == null) {
            return 0;
        }

        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable(arg3, arg4, arg5) {
                public Boolean call() {
                    return Boolean.valueOf(XWalkViewInternal.this.mContent.savePage(this.val$mainFile, this.val$dirPath, this.val$saveType));
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).booleanValue();
        }

        return this.mContent.savePage(arg3, arg4, arg5);
    }

    @XWalkAPI public boolean saveState(Bundle arg4) {
        if(this.mContent == null) {
            return 0;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg4) {
                public void run() {
                    XWalkViewInternal.this.mContent.saveState(this.val$outState);
                }
            });
            return 1;
        }

        this.mContent.saveState(arg4);
        return 1;
    }

    @XWalkAPI public void scrollBy(int arg2, int arg3) {
        this.mContent.scrollBy(arg2, arg3);
    }

    @XWalkAPI public void scrollTo(int arg2, int arg3) {
        this.mContent.scrollTo(arg2, arg3);
    }

    @XWalkAPI public void setAcceptLanguages(String arg4) {
        XWalkSettingsInternal v0 = this.getSettings();
        if(v0 == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(v0, arg4) {
                public void run() {
                    this.val$settings.setAcceptLanguages(this.val$acceptLanguages);
                }
            });
            return;
        }

        v0.setAcceptLanguages(arg4);
    }

    @XWalkAPI public void setBackgroundColor(int arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setBackgroundColor(this.val$color);
                }
            });
            return;
        }

        this.mContent.setBackgroundColor(arg3);
    }

    @XWalkAPI public void setBottomHeight(int arg2) {
        this.mContent.setBottomHeight(arg2);
    }

    @XWalkAPI(reservable=true) public void setDownloadListener(XWalkDownloadListenerInternal arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setDownloadListener(this.val$listener);
                }
            });
            return;
        }

        this.mContent.setDownloadListener(arg3);
    }

    @XWalkAPI(reservable=true) public void setExtendCanvasClient(XWalkExtendCanvasClientInternal arg2) {
        if(this.mContent == null) {
            return;
        }

        XWalkViewInternal.checkThreadSafety();
        this.mContent.setExtendCanvasClient(arg2);
    }

    @XWalkAPI(reservable=true) public void setExtendPluginClient(XWalkExtendPluginClientInternal arg2) {
        if(this.mContent == null) {
            return;
        }

        XWalkViewInternal.checkThreadSafety();
        this.mContent.setExtendPluginClient(arg2);
    }

    public void setExternalExtensionManager(XWalkExternalExtensionManagerInternal arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mExternalExtensionManager = this.val$manager;
                }
            });
            return;
        }

        this.mExternalExtensionManager = arg3;
    }

    @XWalkAPI(reservable=true) public void setFindListener(XWalkFindListenerInternal arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setFindListener(this.val$listener);
                }
            });
            return;
        }

        this.mContent.setFindListener(arg3);
    }

    public void setHorizontalScrollBarEnable(boolean arg4) {
        Log.i("XWalkViewInternal", "setHorizontalScrollBarEnable in systemScrollbarEnable = " + arg4);
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg4) {
                public void run() {
                    XWalkViewInternal.this.mIsHorizontalScrollBarEnable = this.val$systemScrollbarEnable;
                    XWalkViewInternal.this.mContent.SetHorizontalScrollBarEnable(XWalkViewInternal.this.mIsHorizontalScrollBarEnable);
                }
            });
            return;
        }

        this.mIsHorizontalScrollBarEnable = arg4;
        this.mContent.SetHorizontalScrollBarEnable(this.mIsHorizontalScrollBarEnable);
    }

    @XWalkAPI public void setInitialScale(int arg2) {
        XWalkSettingsInternal v0 = this.getSettings();
        if(v0 == null) {
            return;
        }

        v0.setInitialPageScale(((float)arg2));
    }

    @XWalkAPI(disableReflectMethod=true, preWrapperLines={"        return;"}) public void setLayerType(int arg1, Paint arg2) {
    }

    public void setNavigationHandler(XWalkNavigationHandler arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setNavigationHandler(this.val$handler);
                }
            });
            return;
        }

        this.mContent.setNavigationHandler(arg3);
    }

    @XWalkAPI public void setNetworkAvailable(boolean arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setNetworkAvailable(this.val$networkUp);
                }
            });
            return;
        }

        this.mContent.setNetworkAvailable(arg3);
    }

    public void setNotificationService(XWalkNotificationService arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setNotificationService(this.val$service);
                }
            });
            return;
        }

        this.mContent.setNotificationService(arg3);
    }

    @XWalkAPI public void setOnTouchListener(View$OnTouchListener arg2) {
        this.mContent.setOnTouchListener(arg2);
    }

    @XWalkAPI public void setOriginAccessWhitelist(String arg3, String[] arg4) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3, arg4) {
                public void run() {
                    XWalkViewInternal.this.mContent.setOriginAccessWhitelist(this.val$url, this.val$patterns);
                }
            });
            return;
        }

        this.mContent.setOriginAccessWhitelist(arg3, arg4);
    }

    public void setOverlayVideoMode(boolean arg2) {
        this.mContent.setOverlayVideoMode(arg2);
    }

    @XWalkAPI(reservable=true) public void setProxyWebViewClientExtension(XWalkProxyWebViewClientExtensionInternal arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setProxyWebViewClientExtension(this.val$extension);
                }
            });
            return;
        }

        this.mContent.setProxyWebViewClientExtension(arg3);
    }

    @XWalkAPI(reservable=true) public void setResourceClient(XWalkResourceClientInternal arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setResourceClient(this.val$client);
                }
            });
            return;
        }

        this.mContent.setResourceClient(arg3);
    }

    @XWalkAPI(reservable=true) public void setSurfaceViewVisibility(int arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setVisibility(this.val$visibility);
                }
            });
            return;
        }

        this.mContent.setVisibility(arg3);
    }

    @XWalkAPI public void setTranslateMode(boolean arg2) {
        if(this.mContent == null) {
            return;
        }

        XWalkViewInternal.checkThreadSafety();
        this.mContent.setTranslateMode(arg2);
    }

    @XWalkAPI(reservable=true) public void setUIClient(XWalkUIClientInternal arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setUIClient(this.val$client);
                }
            });
            return;
        }

        this.mContent.setUIClient(arg3);
    }

    @XWalkAPI public void setUserAgentString(String arg4) {
        XWalkSettingsInternal v0 = this.getSettings();
        if(v0 == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(v0, arg4) {
                public void run() {
                    this.val$settings.setUserAgentString(this.val$userAgent);
                }
            });
            return;
        }

        v0.setUserAgentString(arg4);
    }

    public void setVerticalScrollBarEnable(boolean arg4) {
        Log.i("XWalkViewInternal", "setVerticalScrollBarEnable in systemScrollbarEnable = " + arg4);
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg4) {
                public void run() {
                    XWalkViewInternal.this.mIsVerticalScrollBarEnable = this.val$systemScrollbarEnable;
                    XWalkViewInternal.this.mContent.SetVerticalScrollBarEnable(XWalkViewInternal.this.mIsVerticalScrollBarEnable);
                }
            });
            return;
        }

        this.mIsVerticalScrollBarEnable = arg4;
        this.mContent.SetVerticalScrollBarEnable(this.mIsVerticalScrollBarEnable);
    }

    @XWalkAPI(disableReflectMethod=true, preWrapperLines={"        if (visibility == View.INVISIBLE) visibility = View.GONE;", "        super.setVisibility(visibility);", "        setXWalkViewInternalVisibility(visibility);", "        setSurfaceViewVisibility(visibility);"}) public void setVisibility(int arg1) {
    }

    public void setXWalkClient(XWalkClient arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setXWalkClient(this.val$client);
                }
            });
            return;
        }

        this.mContent.setXWalkClient(arg3);
    }

    @XWalkAPI(reservable=true) public void setXWalkViewInternalVisibility(int arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.access$501(XWalkViewInternal.this, this.val$visibility);
                }
            });
            return;
        }

        super.setVisibility(arg3);
    }

    public void setXWalkWebChromeClient(XWalkWebChromeClient arg3) {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable(arg3) {
                public void run() {
                    XWalkViewInternal.this.mContent.setXWalkWebChromeClient(this.val$client);
                }
            });
            return;
        }

        this.mContent.setXWalkWebChromeClient(arg3);
    }

    @XWalkAPI public void setZOrderOnTop(boolean arg2) {
        if(this.mContent == null) {
            return;
        }

        this.mContent.setZOrderOnTop(arg2);
    }

    @XWalkAPI public void smoothScroll(int arg2, int arg3, long arg4) {
        this.mContent.smoothScroll(arg2, arg3, arg4);
    }

    @Deprecated @XWalkAPI public void startActivityForResult(Intent arg1, int arg2, Bundle arg3) {
        throw new ActivityNotFoundException("This method is no longer supported");
    }

    @XWalkAPI public void stopLoading() {
        if(this.mContent == null) {
            return;
        }

        if(this.checkNeedsPost()) {
            this.mFactory.addTask(new Runnable() {
                public void run() {
                    XWalkViewInternal.this.mContent.stopLoading();
                }
            });
            return;
        }

        this.mContent.stopLoading();
    }

    @XWalkAPI public void zoomBy(float arg2) {
        if(this.mContent == null) {
            return;
        }

        XWalkViewInternal.checkThreadSafety();
        this.mContent.zoomBy(arg2);
    }

    @XWalkAPI public boolean zoomIn() {
        if(this.mContent == null) {
            return 0;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Boolean call() {
                    return Boolean.valueOf(XWalkViewInternal.this.mContent.zoomIn());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).booleanValue();
        }

        return this.mContent.zoomIn();
    }

    @XWalkAPI public boolean zoomOut() {
        if(this.mContent == null) {
            return 0;
        }

        this.mSharedXWalkView.startYourEngines(true);
        if(this.checkNeedsPost()) {
            return this.mFactory.runOnUiThreadBlocking(new Callable() {
                public Boolean call() {
                    return Boolean.valueOf(XWalkViewInternal.this.mContent.zoomOut());
                }

                public Object call() throws Exception {
                    return this.call();
                }
            }).booleanValue();
        }

        return this.mContent.zoomOut();
    }
}

