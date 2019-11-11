package org.xwalk.core.internal.videofullscreen;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$System;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager$LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import org.chromium.base.ApplicationStatus$WindowFocusChangedListener;
import org.chromium.base.ApplicationStatus;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.ReflectMethod;
import org.xwalk.core.internal.reporter.XWebReporter;

public class XWebNativeInterfaceInternal implements GestureDetector$OnGestureListener, ScaleGestureDetector$OnScaleGestureListener, VideoNativeInterface {
    class org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$19 implements Application$ActivityLifecycleCallbacks {
        org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$19(XWebNativeInterfaceInternal arg1) {
            XWebNativeInterfaceInternal.this = arg1;
            super();
        }

        public void onActivityCreated(Activity arg1, Bundle arg2) {
        }

        public void onActivityDestroyed(Activity arg2) {
            if(arg2 == XWebNativeInterfaceInternal.this.tryGetActivity()) {
                Log.i("XWebNativeInterfaceInternal", "onActivityDestroyed");
            }
        }

        public void onActivityPaused(Activity arg2) {
            if(arg2 == XWebNativeInterfaceInternal.this.tryGetActivity()) {
                Log.i("XWebNativeInterfaceInternal", "onActivityPaused");
                XWebNativeInterfaceInternal.this.mIsResumed = false;
            }
        }

        public void onActivityResumed(Activity arg2) {
            if(arg2 == XWebNativeInterfaceInternal.this.tryGetActivity()) {
                Log.i("XWebNativeInterfaceInternal", "onActivityResumed");
                XWebNativeInterfaceInternal.this.mIsResumed = true;
            }
        }

        public void onActivitySaveInstanceState(Activity arg1, Bundle arg2) {
        }

        public void onActivityStarted(Activity arg2) {
            if(arg2 == XWebNativeInterfaceInternal.this.tryGetActivity()) {
                Log.i("XWebNativeInterfaceInternal", "onActivityStarted");
            }
        }

        public void onActivityStopped(Activity arg2) {
            if(arg2 == XWebNativeInterfaceInternal.this.tryGetActivity()) {
                Log.i("XWebNativeInterfaceInternal", "onActivityStopped");
                XWebNativeInterfaceInternal.this.mIsResumed = false;
            }
        }
    }

    class org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$20 implements WindowFocusChangedListener {
        org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$20(XWebNativeInterfaceInternal arg1) {
            XWebNativeInterfaceInternal.this = arg1;
            super();
        }

        public void onWindowFocusChanged(Activity arg4, boolean arg5) {
            if(arg4 == XWebNativeInterfaceInternal.this.tryGetActivity() && (XWebNativeInterfaceInternal.this.mIsResumed)) {
                Log.i("XWebNativeInterfaceInternal", "window callback   onWindowFocusChanged hasFocus:" + arg5 + ",mIsFullscreen:" + XWebNativeInterfaceInternal.this.mIsFullscreen + ",focus element:" + arg4.getCurrentFocus() + ",mCurrentOrientation:" + XWebNativeInterfaceInternal.this.mCurrentOrientation + ".activity.getRequestedOrientation:" + arg4.getRequestedOrientation());
                if(XWebNativeInterfaceInternal.this.mIsFullscreen) {
                    if(!XWebNativeInterfaceInternal.this.hasFocus()) {
                        XWebNativeInterfaceInternal.this.runOnUIThread(new Runnable() {
                            public void run() {
                                this.this$1.this$0.fixFocus();
                            }
                        });
                    }

                    if(XWebNativeInterfaceInternal.this.mCurrentOrientation == -3) {
                        goto label_53;
                    }

                    if(XWebNativeInterfaceInternal.this.mCurrentOrientation == arg4.getRequestedOrientation()) {
                        goto label_53;
                    }

                    arg4.setRequestedOrientation(XWebNativeInterfaceInternal.this.mCurrentOrientation);
                }

            label_53:
                XWebNativeInterfaceInternal.this.mIsResumed = false;
            }
        }
    }

    enum FullscreenStatus {
        public static final enum FullscreenStatus Attached;
        public static final enum FullscreenStatus Attaching;
        public static final enum FullscreenStatus AttachingDetached;
        public static final enum FullscreenStatus Detaching;
        public static final enum FullscreenStatus DetachingAttached;
        public static final enum FullscreenStatus None;

        static {
            FullscreenStatus.None = new FullscreenStatus("None", 0);
            FullscreenStatus.Attaching = new FullscreenStatus("Attaching", 1);
            FullscreenStatus.Attached = new FullscreenStatus("Attached", 2);
            FullscreenStatus.Detaching = new FullscreenStatus("Detaching", 3);
            FullscreenStatus.AttachingDetached = new FullscreenStatus("AttachingDetached", 4);
            FullscreenStatus.DetachingAttached = new FullscreenStatus("DetachingAttached", 5);
            FullscreenStatus.$VALUES = new FullscreenStatus[]{FullscreenStatus.None, FullscreenStatus.Attaching, FullscreenStatus.Attached, FullscreenStatus.Detaching, FullscreenStatus.AttachingDetached, FullscreenStatus.DetachingAttached};
        }

        private FullscreenStatus(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static FullscreenStatus valueOf(String arg1) {
            return Enum.valueOf(FullscreenStatus.class, arg1);
        }

        public static FullscreenStatus[] values() {
            return FullscreenStatus.$VALUES.clone();
        }
    }

    public class WebViewReflectObject {
        private ReflectMethod evaluateJavascriptMethod;
        private ReflectMethod getBitmapMethod;
        private ReflectMethod isSysKernelMethod;
        private ReflectMethod isXWalkKernelMethod;
        private Context mDownloadContext;
        private View mWebView;
        private View mWebViewUI;

        public WebViewReflectObject(View arg6, View arg7, Context arg8) {
            super();
            this.isSysKernelMethod = new ReflectMethod(arg6, "isSysKernel", new Class[0]);
            this.isXWalkKernelMethod = new ReflectMethod(arg6, "isXWalkKernel", new Class[0]);
            this.getBitmapMethod = new ReflectMethod(arg6, "getBitmap", new Class[0]);
            this.evaluateJavascriptMethod = new ReflectMethod(arg6, "evaluateJavascript", new Class[]{String.class, ValueCallback.class});
            this.mWebView = arg6;
            this.mWebViewUI = arg7;
            this.mDownloadContext = arg8;
        }

        public void evaluateJavascript(String arg4, ValueCallback arg5) {
            this.evaluateJavascriptMethod.invoke(new Object[]{arg4, arg5});
        }

        public Bitmap getBitmap() {
            Object v0 = this.getBitmapMethod.invoke(new Object[0]);
            if((v0 instanceof Bitmap)) {
                return ((Bitmap)v0);
            }

            return null;
        }

        public Context getContext() {
            return this.mDownloadContext;
        }

        public View getView() {
            return this.mWebView;
        }

        public View getWebViewUI() {
            return this.mWebViewUI;
        }

        public boolean isSysKernel() {
            return this.isSysKernelMethod.invoke(new Object[0]).booleanValue();
        }

        public boolean isXWalkKernel() {
            return this.isXWalkKernelMethod.invoke(new Object[0]).booleanValue();
        }
    }

    private final int BRIGHTNESS_CONTROL;
    private static final int INVALID_ORIENTATION = -3;
    private static final int MIN_DISTANCE_PROGRESS_CONTROL = 1;
    private static final int MODE_ZOOM = 1;
    private final int NONE_CONTROL;
    private static final String PNG_DATA_FLAG = "data:image/png;base64,";
    private final int PROGRESS_CONTROL;
    private static final String TAG = "XWebNativeInterfaceInternal";
    private final int VOLUMN_CONTROL;
    private Activity mActivity;
    private Application$ActivityLifecycleCallbacks mActivityLifecycleCallbacks;
    private AudioManager mAudioManager;
    private Drawable mBackgroundDrawable;
    private FrameLayout mBackgroundView;
    private float mBrightness;
    private BrightnessHelper mBrightnessHelper;
    private WebChromeClient$CustomViewCallback mCallback;
    private ViewGroup mControlView;
    private Timer mControlViewTimer;
    private int mCurrentOrientation;
    private GestureDetector mDetector;
    private Context mDownloadContext;
    private FullscreenStatus mFullscreenStatus;
    private boolean mHasInited;
    private int mHideTime;
    private int mHideWebViewTime;
    private ImageView mImageExit;
    private ImageView mImageFrame;
    private ImageView mImagePlay;
    private ImageView mImageSwitch;
    private ImageView mImageView;
    private boolean mImmersiveSticky;
    private boolean mIsFullscreen;
    private boolean mIsResumed;
    private boolean mIsShowingControlView;
    private int mLastSystemUiVisibility;
    private ClickableFrameLayout mLayoutBlank;
    private WindowManager$LayoutParams mLayoutParams;
    private View mLayoutProgress;
    private VideoStatusLayout mLayoutStatus;
    private LinearLayout mLayoutVideoControl;
    private int mMaxVideoVolume;
    private int mMode;
    private float mNewVideoProgress;
    private float mOldVideoProgress;
    private double mOldVideoVolume;
    private boolean mOriginalForceNotFullscreen;
    private boolean mOriginalFullscreen;
    private int mPreOrientation;
    private ProgressBar mProgressLoading;
    private FrameLayout mRootView;
    private ScaleGestureDetector mScaleDetector;
    private VideoPlayerSeekBar mSeekBar;
    private int mSnapshotHideTime;
    private int mSnapshotShowTime;
    private double mTimeGetFrame;
    private ViewGroup mTopViewParent;
    private int mTouchStatus;
    private TextView mTvCurrentTime;
    private TextView mTvTotalTime;
    private double mVideoDuration;
    private double mVideoHeight;
    private long mVideoId;
    private boolean mVideoIsEnterFullscreen;
    private String mVideoJs;
    private View mVideoView;
    private double mVideoWidth;
    private WebViewReflectObject mWebView;
    private int mWebViewDescendantFocusability;
    private int mWebViewUIDescendantFocusability;
    private Window mWindow;
    private WindowFocusChangedListener mWindowFocusChnagedListener;

    public XWebNativeInterfaceInternal() {
        super();
        this.NONE_CONTROL = 0;
        this.VOLUMN_CONTROL = 1;
        this.BRIGHTNESS_CONTROL = 2;
        this.PROGRESS_CONTROL = 3;
        this.mMode = 0;
        this.mPreOrientation = -3;
        this.mCurrentOrientation = -3;
        this.mWebViewDescendantFocusability = 0;
        this.mWebViewUIDescendantFocusability = 0;
        this.mHasInited = false;
        this.mMaxVideoVolume = 0;
        this.mOldVideoVolume = 0;
        this.mNewVideoProgress = 0f;
        this.mOldVideoProgress = 0f;
        this.mBrightness = 1f;
        this.mTouchStatus = 0;
        this.mHideTime = 200;
        this.mSnapshotShowTime = 200;
        this.mSnapshotHideTime = 500;
        this.mHideWebViewTime = 0;
        this.mFullscreenStatus = FullscreenStatus.None;
        this.mIsResumed = false;
        this.mActivityLifecycleCallbacks = new org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$19(this);
        this.mWindowFocusChnagedListener = new org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$20(this);
        this.mIsFullscreen = false;
        this.mImmersiveSticky = false;
    }

    static double access$000(XWebNativeInterfaceInternal arg2) {
        return arg2.mVideoDuration;
    }

    static WebViewReflectObject access$100(XWebNativeInterfaceInternal arg0) {
        return arg0.mWebView;
    }

    static ImageView access$1000(XWebNativeInterfaceInternal arg0) {
        return arg0.mImagePlay;
    }

    static VideoPlayerSeekBar access$1100(XWebNativeInterfaceInternal arg0) {
        return arg0.mSeekBar;
    }

    static String access$1200(XWebNativeInterfaceInternal arg0, double arg1) {
        return arg0.getCurrentVideoTime(arg1);
    }

    static TextView access$1300(XWebNativeInterfaceInternal arg0) {
        return arg0.mTvCurrentTime;
    }

    static String access$1400(XWebNativeInterfaceInternal arg0) {
        return arg0.getTotalVideoTime();
    }

    static TextView access$1500(XWebNativeInterfaceInternal arg0) {
        return arg0.mTvTotalTime;
    }

    static ProgressBar access$1600(XWebNativeInterfaceInternal arg0) {
        return arg0.mProgressLoading;
    }

    static View access$1700(XWebNativeInterfaceInternal arg0) {
        return arg0.mVideoView;
    }

    static SurfaceView access$1800(XWebNativeInterfaceInternal arg0, View arg1) {
        return arg0.getSurfaceView(arg1);
    }

    static TextureView access$1900(XWebNativeInterfaceInternal arg0, View arg1) {
        return arg0.getTextureView(arg1);
    }

    static boolean access$200(XWebNativeInterfaceInternal arg0) {
        return arg0.mVideoIsEnterFullscreen;
    }

    static FrameLayout access$2000(XWebNativeInterfaceInternal arg0) {
        return arg0.mBackgroundView;
    }

    static ViewGroup access$2100(XWebNativeInterfaceInternal arg0) {
        return arg0.mControlView;
    }

    static FrameLayout access$2200(XWebNativeInterfaceInternal arg0) {
        return arg0.mRootView;
    }

    static View access$2300(XWebNativeInterfaceInternal arg0) {
        return arg0.mLayoutProgress;
    }

    static Activity access$2400(XWebNativeInterfaceInternal arg0) {
        return arg0.tryGetActivity();
    }

    static boolean access$2500(XWebNativeInterfaceInternal arg0) {
        return arg0.mIsResumed;
    }

    static boolean access$2502(XWebNativeInterfaceInternal arg0, boolean arg1) {
        arg0.mIsResumed = arg1;
        return arg1;
    }

    static boolean access$2600(XWebNativeInterfaceInternal arg0) {
        return arg0.mIsFullscreen;
    }

    static int access$2700(XWebNativeInterfaceInternal arg0) {
        return arg0.mCurrentOrientation;
    }

    static boolean access$2800(XWebNativeInterfaceInternal arg0) {
        return arg0.hasFocus();
    }

    static void access$2900(XWebNativeInterfaceInternal arg0) {
        arg0.fixFocus();
    }

    static int access$300(XWebNativeInterfaceInternal arg0) {
        return arg0.mMode;
    }

    static ImageView access$3000(XWebNativeInterfaceInternal arg0) {
        return arg0.mImageView;
    }

    static int access$302(XWebNativeInterfaceInternal arg0, int arg1) {
        arg0.mMode = arg1;
        return arg1;
    }

    static int access$3100(XWebNativeInterfaceInternal arg0) {
        return arg0.mSnapshotHideTime;
    }

    static FullscreenStatus access$3200(XWebNativeInterfaceInternal arg0) {
        return arg0.mFullscreenStatus;
    }

    static FullscreenStatus access$3202(XWebNativeInterfaceInternal arg0, FullscreenStatus arg1) {
        arg0.mFullscreenStatus = arg1;
        return arg1;
    }

    static int access$3300(XWebNativeInterfaceInternal arg0) {
        return arg0.mPreOrientation;
    }

    static int access$3302(XWebNativeInterfaceInternal arg0, int arg1) {
        arg0.mPreOrientation = arg1;
        return arg1;
    }

    static int access$400(XWebNativeInterfaceInternal arg0) {
        return arg0.mTouchStatus;
    }

    static int access$402(XWebNativeInterfaceInternal arg0, int arg1) {
        arg0.mTouchStatus = arg1;
        return arg1;
    }

    static float access$500(XWebNativeInterfaceInternal arg0) {
        return arg0.mNewVideoProgress;
    }

    static ScaleGestureDetector access$600(XWebNativeInterfaceInternal arg0) {
        return arg0.mScaleDetector;
    }

    static GestureDetector access$700(XWebNativeInterfaceInternal arg0) {
        return arg0.mDetector;
    }

    static boolean access$802(XWebNativeInterfaceInternal arg0, boolean arg1) {
        arg0.mIsShowingControlView = arg1;
        return arg1;
    }

    static LinearLayout access$900(XWebNativeInterfaceInternal arg0) {
        return arg0.mLayoutVideoControl;
    }

    private Activity activityFromContext(Context arg3) {
        if((arg3 instanceof Activity)) {
            return arg3;
        }

        Activity v1 = null;
        if((arg3 instanceof ContextWrapper)) {
            Context v0 = arg3.getBaseContext();
            if(v0 != arg3) {
                return this.activityFromContext(v0);
            }

            return v1;
        }

        return v1;
    }

    public void attach() {
        Log.i("XWebNativeInterfaceInternal", "attach");
        if(this.hasEnteredFullscreen()) {
            Log.i("XWebNativeInterfaceInternal", "attach has entered fullscreen");
            return;
        }

        Activity v0 = this.tryGetActivity();
        if(v0 == null) {
            return;
        }

        this.registerActivityLifecycleCallback(v0);
        this.reset();
        if(this.mFullscreenStatus == FullscreenStatus.None) {
            this.mFullscreenStatus = FullscreenStatus.Attaching;
        }
        else if(this.mFullscreenStatus == FullscreenStatus.Detaching) {
            this.mFullscreenStatus = FullscreenStatus.DetachingAttached;
            return;
        }

        this.onToggleFullscreen(true);
        int v4 = 17;
        int v5 = -1;
        v0.getWindow().getDecorView().addView(this.mRootView, new FrameLayout$LayoutParams(v5, v5, v4));
        this.mRootView.setBackgroundColor(0);
        this.mLayoutProgress.setVisibility(0);
        int v3 = 0xFF000000;
        if(this.mWebView.isXWalkKernel()) {
            Bitmap v0_1 = this.getActivityShot(this.mActivity);
            this.mTopViewParent = this.mWebView.getView().getParent();
            this.mTopViewParent.removeView(this.mWebView.getView());
            this.mRootView.addView(this.mWebView.getView(), this.mRootView.getChildCount());
            this.mImageView = new ImageView(this.mActivity);
            this.mImageView.setImageBitmap(v0_1);
            this.mImageView.setBackgroundColor(v3);
            this.mImageView.setScaleType(ImageView$ScaleType.FIT_XY);
            this.mRootView.addView(this.mImageView, new FrameLayout$LayoutParams(v5, v5));
            this.mRootView.postDelayed(new Runnable() {
                public void run() {
                    ObjectAnimator v0 = ObjectAnimator.ofFloat(XWebNativeInterfaceInternal.this.mImageView, "alpha", new float[]{1f, 0.9f, 0.7f, 0.5f, 0.2f, 0f});
                    v0.setDuration(((long)XWebNativeInterfaceInternal.this.mSnapshotHideTime));
                    v0.setRepeatCount(0);
                    v0.addListener(new Animator$AnimatorListener() {
                        public void onAnimationCancel(Animator arg1) {
                        }

                        public void onAnimationEnd(Animator arg2) {
                            if((this.this$1.this$0.mWebView.isXWalkKernel()) && (this.this$1.this$0.getSurfaceView(this.this$1.this$0.mVideoView) != null || this.this$1.this$0.getTextureView(this.this$1.this$0.mVideoView) != null)) {
                                this.this$1.this$0.mBackgroundView.setBackgroundColor(0xFF000000);
                            }

                            if(this.this$1.this$0.mImageView != null) {
                                this.this$1.this$0.mImageView.setVisibility(8);
                            }

                            this.this$1.this$0.onActivityFullscreen(true);
                            if(this.this$1.this$0.mFullscreenStatus == FullscreenStatus.AttachingDetached) {
                                this.this$1.this$0.mFullscreenStatus = FullscreenStatus.Attached;
                                this.this$1.this$0.detach();
                            }
                            else {
                                this.this$1.this$0.mFullscreenStatus = FullscreenStatus.Attached;
                            }
                        }

                        public void onAnimationRepeat(Animator arg1) {
                        }

                        public void onAnimationStart(Animator arg1) {
                        }
                    });
                    v0.start();
                }
            }, ((long)this.mSnapshotShowTime));
            this.mBackgroundView.setBackgroundColor(0);
        }
        else {
            this.mFullscreenStatus = FullscreenStatus.Attached;
        }

        if(this.mVideoView != null) {
            this.mBackgroundView.addView(this.mVideoView);
            this.mBackgroundView.setBackgroundColor(0);
            this.mRootView.addView(this.mBackgroundView, this.mRootView.getChildCount(), new FrameLayout$LayoutParams(v5, v5, v4));
            if(this.mWebView.isSysKernel()) {
                this.mVideoView.setVisibility(4);
            }
        }

        this.mRootView.addView(this.mControlView, new FrameLayout$LayoutParams(v5, v5, v4));
        if(this.mWebView.isSysKernel()) {
            SurfaceView v0_2 = this.getSurfaceView(this.mVideoView);
            if(v0_2 != null) {
                v0_2.setZOrderMediaOverlay(true);
            }
        }

        this.mBackgroundDrawable = this.mWebView.getView().getBackground();
        if(this.mBackgroundDrawable != null) {
            this.mBackgroundDrawable = this.mBackgroundDrawable.getConstantState().newDrawable().mutate();
        }

        this.mWebView.getView().setBackground(new ColorDrawable(v3));
        if(this.mWebView.isSysKernel()) {
            this.mRootView.postDelayed(new Runnable() {
                public void run() {
                    if(XWebNativeInterfaceInternal.this.mVideoView != null) {
                        XWebNativeInterfaceInternal.this.mVideoView.setVisibility(0);
                    }
                }
            }, ((long)this.mHideTime));
        }
    }

    public void detach() {
        Log.i("XWebNativeInterfaceInternal", "detach");
        if(!this.hasEnteredFullscreen()) {
            Log.i("XWebNativeInterfaceInternal", "detach has exited fullscreen");
            return;
        }

        Activity v0 = this.tryGetActivity();
        if(v0 == null) {
            return;
        }

        this.unRegisterActivityLifecycleCallback(v0);
        if(this.mFullscreenStatus == FullscreenStatus.Attached) {
            this.mFullscreenStatus = FullscreenStatus.Detaching;
        }
        else if(this.mFullscreenStatus == FullscreenStatus.Attaching) {
            this.mFullscreenStatus = FullscreenStatus.AttachingDetached;
            return;
        }

        if(this.mBackgroundDrawable != null) {
            this.mWebView.getView().setBackground(this.mBackgroundDrawable);
            this.mBackgroundDrawable = null;
        }
        else {
            this.mWebView.getView().setBackground(new ColorDrawable(0));
        }

        if(this.mRootView.getParent() == v0.getWindow().getDecorView()) {
            if(this.mWebView.isXWalkKernel()) {
                if(!this.mVideoIsEnterFullscreen) {
                    Bitmap v1 = this.getActivityShot(this.mActivity);
                    if(this.mImageView != null) {
                        this.mImageView.setImageBitmap(v1);
                        this.mImageView.setVisibility(0);
                        this.mImageView.setAlpha(1f);
                        this.mRootView.setBackgroundColor(0);
                    }
                }

                this.mRootView.removeView(this.mWebView.getView());
                this.mTopViewParent.addView(this.mWebView.getView(), 0);
            }

            if(this.mVideoView != null && this.mVideoView.getParent() == this.mBackgroundView) {
                this.mBackgroundView.removeView(this.mVideoView);
            }

            if(this.mBackgroundView.getParent() == this.mRootView) {
                this.mRootView.removeView(this.mBackgroundView);
            }

            if(this.mControlView.getParent() == this.mRootView) {
                this.mRootView.removeView(this.mControlView);
            }

            this.mControlView.setVisibility(8);
            org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$23 v1_1 = new Runnable(v0) {
                public void run() {
                    int v1 = -3;
                    if(XWebNativeInterfaceInternal.this.mPreOrientation != v1) {
                        this.val$activity.setRequestedOrientation(XWebNativeInterfaceInternal.this.mPreOrientation);
                        XWebNativeInterfaceInternal.this.mPreOrientation = v1;
                    }

                    XWebNativeInterfaceInternal.this.onActivityFullscreen(false);
                    org.xwalk.core.internal.videofullscreen.XWebNativeInterfaceInternal$23$1 v0 = new Runnable() {
                        public void run() {
                            if(this.this$1.this$0.mImageView != null) {
                                this.this$1.this$0.mRootView.removeView(this.this$1.this$0.mImageView);
                            }

                            if((this.this$1.this$0.mRootView.getParent() instanceof ViewGroup)) {
                                this.this$1.this$0.mRootView.getParent().removeView(this.this$1.this$0.mRootView);
                            }

                            if(this.this$1.this$0.mFullscreenStatus == FullscreenStatus.DetachingAttached) {
                                this.this$1.this$0.mFullscreenStatus = FullscreenStatus.None;
                                this.this$1.this$0.attach();
                            }
                            else {
                                this.this$1.this$0.mFullscreenStatus = FullscreenStatus.None;
                            }
                        }
                    };
                    if((XWebNativeInterfaceInternal.this.mVideoIsEnterFullscreen) || XWebNativeInterfaceInternal.this.mImageView == null) {
                        ((Runnable)v0).run();
                    }
                    else {
                        ObjectAnimator v2 = ObjectAnimator.ofFloat(XWebNativeInterfaceInternal.this.mImageView, "alpha", new float[]{1f, 0.9f, 0.7f, 0.5f, 0.2f, 0f});
                        v2.setDuration(((long)XWebNativeInterfaceInternal.this.mSnapshotHideTime));
                        v2.setRepeatCount(0);
                        v2.addListener(new Animator$AnimatorListener(((Runnable)v0)) {
                            public void onAnimationCancel(Animator arg1) {
                            }

                            public void onAnimationEnd(Animator arg1) {
                                this.val$runnableHide.run();
                            }

                            public void onAnimationRepeat(Animator arg1) {
                            }

                            public void onAnimationStart(Animator arg1) {
                            }
                        });
                        v2.start();
                    }
                }
            };
            if(this.mVideoIsEnterFullscreen) {
                ((Runnable)v1_1).run();
            }
            else {
                this.mRootView.postDelayed(((Runnable)v1_1), ((long)this.mSnapshotShowTime));
            }

            this.onToggleFullscreen(false);
            return;
        }
    }

    public void evaluteJavascript(boolean arg3, boolean arg4) {
        if(this.mWebView != null && (arg4) && this.mVideoJs != null) {
            Log.i("XWebNativeInterfaceInternal", "evaluteJavascript:" + arg3);
            String v4 = this.mVideoJs;
            if(arg3) {
                v4 = XWebFullscreenVideoUtil.AddDOMLoadedEventJS(this.mVideoJs);
            }

            this.mWebView.evaluateJavascript(v4, new ValueCallback() {
                public void onReceiveValue(Object arg1) {
                    this.onReceiveValue(((String)arg1));
                }

                public void onReceiveValue(String arg2) {
                    arg2.equals("1");
                }
            });
        }
    }

    private void fixFocus() {
        int v0;
        Log.i("XWebNativeInterfaceInternal", "fixFocus");
        int v1 = 0x20000;
        if((this.mWebView.getView() instanceof ViewGroup)) {
            v0 = this.mWebView.getView().getDescendantFocusability();
            if(v0 != v1) {
                this.mWebViewDescendantFocusability = v0;
                this.mWebView.getView().setDescendantFocusability(v1);
            }
        }

        if((this.mWebView.getWebViewUI() instanceof ViewGroup)) {
            v0 = this.mWebView.getWebViewUI().getDescendantFocusability();
            if(v0 != v1) {
                this.mWebViewUIDescendantFocusability = v0;
                this.mWebView.getWebViewUI().setDescendantFocusability(v1);
            }
        }

        Log.i("XWebNativeInterfaceInternal", "fixFocus ret:" + this.mWebView.getView().requestFocus());
    }

    private Bitmap getActivityShot(Activity arg9) {
        View v0 = arg9.getWindow().getDecorView();
        v0.setDrawingCacheEnabled(true);
        v0.buildDrawingCache();
        v0.getWindowVisibleDisplayFrame(new Rect());
        arg9.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        Bitmap v9 = v0.getDrawingCache();
        if(v9 == null) {
            return null;
        }

        v9 = Bitmap.createBitmap(v9, 0, 0, v9.getWidth(), v9.getHeight());
        Canvas v2 = new Canvas(v9);
        Paint v3 = new Paint();
        v3.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.SRC_OVER));
        int[] v5 = this.getLocation(this.mWebView.getView());
        v2.drawBitmap(this.mWebView.getBitmap(), ((float)v5[0]), ((float)v5[1]), v3);
        v0.destroyDrawingCache();
        v0.setDrawingCacheEnabled(false);
        return v9;
    }

    private String getAllVideoTime(double arg4) {
        return this.getFormatVideoTime(arg4, this.mVideoDuration) + "/" + this.getFormatVideoTime(this.mVideoDuration, this.mVideoDuration);
    }

    private FrameLayout$LayoutParams getAutoFixLayoutParams(int arg11, int arg12, int arg13) {
        int v0_1;
        Display v0 = this.getContext().getSystemService("window").getDefaultDisplay();
        Point v1 = new Point(0, 0);
        v0.getSize(v1);
        if(arg11 == 0) {
            arg11 = Math.max(v1.x, v1.y);
            v0_1 = Math.min(v1.x, v1.y);
        }
        else {
            v0_1 = Math.max(v1.x, v1.y);
            arg11 = Math.min(v1.x, v1.y);
        }

        double v3 = 1;
        double v1_1 = (((double)arg12)) * v3 / (((double)arg13));
        double v12 = ((double)arg11);
        double v7 = ((double)v0_1);
        if(v1_1 >= v12 * v3 / v7) {
            v0_1 = ((int)(v12 * (v3 / v1_1)));
        }
        else {
            arg11 = ((int)(v7 * v1_1));
        }

        return new FrameLayout$LayoutParams(arg11, v0_1, 17);
    }

    private Context getContext() {
        return this.mWebView.getContext();
    }

    private String getCurrentVideoTime(double arg3) {
        return this.getFormatVideoTime(arg3, this.mVideoDuration);
    }

    private String getFormatVideoTime(double arg8, double arg10) {
        String v0 = String.format("%02d", Integer.valueOf(((int)(arg8 / 3600))));
        Object[] v5 = new Object[1];
        int v8 = ((int)(arg8 % 3600));
        v5[0] = Integer.valueOf(v8 / 60);
        String v9 = String.format("%02d", v5);
        String v8_1 = String.format("%02d", Integer.valueOf(v8 % 60));
        int v2 = Double.compare(arg10, 3600);
        int v10 = 2;
        if(v2 > 0) {
            Object[] v2_1 = new Object[3];
            v2_1[0] = v0;
            v2_1[1] = v9;
            v2_1[v10] = v8_1;
            v8_1 = String.format("%s:%s:%s", v2_1);
        }
        else {
            v8_1 = String.format("%s:%s", v9, v8_1);
        }

        return v8_1;
    }

    private FrameLayout$LayoutParams getFullscreenLayoutParams(int arg11, int arg12, int arg13) {
        int v0_1;
        Display v0 = this.getContext().getSystemService("window").getDefaultDisplay();
        Point v1 = new Point(0, 0);
        v0.getSize(v1);
        if(arg11 == 0) {
            arg11 = Math.max(v1.x, v1.y);
            v0_1 = Math.min(v1.x, v1.y);
        }
        else {
            v0_1 = Math.max(v1.x, v1.y);
            arg11 = Math.min(v1.x, v1.y);
        }

        double v3 = 1;
        double v1_1 = (((double)arg12)) * v3 / (((double)arg13));
        double v12 = ((double)arg11);
        double v7 = ((double)v0_1);
        if(v1_1 >= v12 * v3 / v7) {
            arg11 = ((int)(v7 * v1_1));
        }
        else {
            v0_1 = ((int)(v12 * (v3 / v1_1)));
        }

        return new FrameLayout$LayoutParams(arg11, v0_1, 17);
    }

    public int[] getLocation(View arg6) {
        int[] v0 = new int[4];
        int[] v2 = new int[2];
        arg6.getLocationOnScreen(v2);
        v0[0] = v2[0];
        v0[1] = v2[1];
        arg6.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
        v0[2] = arg6.getMeasuredWidth();
        v0[3] = arg6.getMeasuredHeight();
        return v0;
    }

    private SurfaceView getSurfaceView(View arg3) {
        if((arg3 instanceof SurfaceView)) {
            return arg3;
        }

        if((arg3 instanceof ViewGroup)) {
            int v0 = 0;
            while(v0 < ((ViewGroup)arg3).getChildCount()) {
                SurfaceView v1 = this.getSurfaceView(((ViewGroup)arg3).getChildAt(v0));
                if(v1 != null) {
                    return v1;
                }
                else {
                    ++v0;
                    continue;
                }

                return null;
            }
        }

        return null;
    }

    private TextureView getTextureView(View arg3) {
        if((arg3 instanceof TextureView)) {
            return arg3;
        }

        if((arg3 instanceof ViewGroup)) {
            int v0 = 0;
            while(v0 < ((ViewGroup)arg3).getChildCount()) {
                TextureView v1 = this.getTextureView(((ViewGroup)arg3).getChildAt(v0));
                if(v1 != null) {
                    return v1;
                }
                else {
                    ++v0;
                    continue;
                }

                return null;
            }
        }

        return null;
    }

    private String getTotalVideoTime() {
        return this.getFormatVideoTime(this.mVideoDuration, this.mVideoDuration);
    }

    public void getVideoFrame(double arg1) {
        this.mTimeGetFrame = arg1;
    }

    public boolean hasEnteredFullscreen() {
        return this.mIsFullscreen;
    }

    private boolean hasFocus() {
        return this.mWebView.getView().hasFocus();
    }

    public void hideControlView() {
        this.runOnUIThread(new Runnable() {
            public void run() {
                XWebNativeInterfaceInternal.this.mIsShowingControlView = false;
                XWebNativeInterfaceInternal.this.mLayoutVideoControl.setVisibility(4);
            }
        });
    }

    public void init(Activity arg1, View arg2, View arg3, Context arg4, String arg5) {
        this.mActivity = arg1;
        this.mWebView = new WebViewReflectObject(arg2, arg3, arg4);
        this.mDownloadContext = arg4;
        this.mVideoJs = arg5;
    }

    public void initView() {
        if(this.mHasInited) {
            return;
        }

        this.mRootView = new FrameLayout(this.getContext());
        this.mBackgroundView = new FrameLayout(this.getContext());
        this.mDetector = new GestureDetector(this.getContext(), ((GestureDetector$OnGestureListener)this));
        this.mScaleDetector = new ScaleGestureDetector(this.getContext(), ((ScaleGestureDetector$OnScaleGestureListener)this));
        this.mDetector.setIsLongpressEnabled(false);
        this.mControlView = LayoutInflater.from(this.mDownloadContext).inflate(0x7F09003F, null);
        this.mControlView.setVisibility(8);
        this.mSeekBar = new VideoPlayerSeekBar(this.mControlView.findViewById(0x7F070077), new IVideoPlaySeekCallback() {
            public void onProgressChanged(VideoPlayerSeekBar arg4, float arg5, boolean arg6) {
                if(arg6) {
                    double v0 = XWebNativeInterfaceInternal.this.mVideoDuration * (((double)arg5)) / 100;
                    XWebNativeInterfaceInternal.this.updateVideoTime(v0, false);
                    XWebNativeInterfaceInternal.this.mWebView.evaluateJavascript(String.format("xwebVideoBridge.xwebToJS_Video_Seek(%f);", Double.valueOf(v0)), new ValueCallback() {
                        public void onReceiveValue(Object arg1) {
                            this.onReceiveValue(((String)arg1));
                        }

                        public void onReceiveValue(String arg1) {
                        }
                    });
                }

                XWebNativeInterfaceInternal.this.startControlViewTimer();
            }

            public void onSeekPre() {
                XWebNativeInterfaceInternal.this.stopControlViewTimer();
                XWebNativeInterfaceInternal.this.showControlView();
            }
        });
        this.mLayoutProgress = this.mControlView.findViewById(0x7F070059);
        this.mProgressLoading = this.mControlView.findViewById(0x7F070079);
        this.mTvCurrentTime = this.mControlView.findViewById(0x7F0700BD);
        this.mTvTotalTime = this.mControlView.findViewById(0x7F0700BF);
        this.mImageFrame = this.mControlView.findViewById(0x7F070053);
        this.mLayoutStatus = this.mControlView.findViewById(0x7F07005B);
        this.mLayoutBlank = this.mControlView.findViewById(0x7F070058);
        this.mLayoutBlank.setGestureDetector(this.mDetector);
        this.mLayoutBlank.setOnTouchListener(new View$OnTouchListener() {
            public boolean onTouch(View arg7, MotionEvent arg8) {
                if(!XWebNativeInterfaceInternal.this.mVideoIsEnterFullscreen) {
                    return 0;
                }

                int v7 = arg8.getAction() & 0xFF;
                if(v7 != 1) {
                    switch(v7) {
                        case 5: {
                            goto label_14;
                        }
                        case 6: {
                            goto label_11;
                        }
                    }

                    goto label_22;
                label_11:
                    XWebNativeInterfaceInternal.this.mMode = 0;
                    goto label_22;
                label_14:
                    if(arg8.getPointerCount() == 2) {
                        XWebNativeInterfaceInternal.this.mMode = 1;
                    }
                }
                else {
                    XWebNativeInterfaceInternal.this.mMode = 0;
                }

            label_22:
                if(arg8.getAction() == 1 && XWebNativeInterfaceInternal.this.mTouchStatus == 3 && !Double.isInfinite(XWebNativeInterfaceInternal.this.mVideoDuration)) {
                    double v2 = XWebNativeInterfaceInternal.this.mVideoDuration * (((double)XWebNativeInterfaceInternal.this.mNewVideoProgress)) / 100;
                    XWebNativeInterfaceInternal.this.updateVideoTime(v2, true);
                    XWebNativeInterfaceInternal.this.mWebView.evaluateJavascript(String.format("xwebVideoBridge.xwebToJS_Video_Seek(%f);", Double.valueOf(v2)), new ValueCallback() {
                        public void onReceiveValue(Object arg1) {
                            this.onReceiveValue(((String)arg1));
                        }

                        public void onReceiveValue(String arg1) {
                        }
                    });
                    XWebNativeInterfaceInternal.this.mTouchStatus = 0;
                }

                if(XWebNativeInterfaceInternal.this.mMode == 1) {
                    return XWebNativeInterfaceInternal.this.mScaleDetector.onTouchEvent(arg8);
                }

                return XWebNativeInterfaceInternal.this.mDetector.onTouchEvent(arg8);
            }
        });
        this.mImageExit = this.mControlView.findViewById(0x7F070052);
        this.mImageExit.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                XWebNativeInterfaceInternal.this.mWebView.evaluateJavascript("xwebVideoBridge.xwebToJS_Video_ExitFullscreen();", new ValueCallback() {
                    public void onReceiveValue(Object arg1) {
                        this.onReceiveValue(((String)arg1));
                    }

                    public void onReceiveValue(String arg1) {
                    }
                });
            }
        });
        this.mLayoutVideoControl = this.mControlView.findViewById(0x7F07005C);
        this.mLayoutVideoControl.setVisibility(4);
        this.mImagePlay = this.mControlView.findViewById(0x7F070054);
        this.mImagePlay.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                XWebNativeInterfaceInternal.this.mWebView.evaluateJavascript("xwebVideoBridge.xwebToJS_Video_Play();", new ValueCallback() {
                    public void onReceiveValue(Object arg1) {
                        this.onReceiveValue(((String)arg1));
                    }

                    public void onReceiveValue(String arg1) {
                    }
                });
                XWebNativeInterfaceInternal.this.startControlViewTimer();
            }
        });
        this.mHasInited = true;
    }

    private boolean isOrientationPortrait() {
        Display v0 = this.getContext().getSystemService("window").getDefaultDisplay();
        boolean v2 = false;
        Point v1 = new Point(0, 0);
        v0.getSize(v1);
        if(v1.x <= v1.y) {
            v2 = true;
        }

        return v2;
    }

    public void onActivityFullscreen(boolean arg6) {
        Activity v0 = this.tryGetActivity();
        if(v0 == null) {
            return;
        }

        int v1 = 0x400;
        int v2 = 0x800;
        if(arg6) {
            if((this.mActivity.getWindow().getAttributes().flags & v2) != 0) {
                this.mOriginalForceNotFullscreen = true;
                v0.getWindow().clearFlags(v2);
            }
            else {
                this.mOriginalForceNotFullscreen = false;
            }

            if((v0.getWindow().getAttributes().flags & v1) != 0) {
                this.mOriginalFullscreen = true;
            }
            else {
                this.mOriginalFullscreen = false;
                v0.getWindow().addFlags(v1);
            }

            if((this.mRootView.getSystemUiVisibility() & 0x1000) != 0 && (this.mRootView.getSystemUiVisibility() & 4) != 0 && (this.mRootView.getSystemUiVisibility() & 2) != 0) {
                this.mImmersiveSticky = true;
                return;
            }

            this.mImmersiveSticky = false;
            this.mLastSystemUiVisibility = this.mRootView.getSystemUiVisibility();
            this.mRootView.setSystemUiVisibility(this.mRootView.getSystemUiVisibility() | 0x1000 | 4 | 2);
        }
        else {
            if(this.mOriginalForceNotFullscreen) {
                v0.getWindow().addFlags(v2);
            }

            if(!this.mOriginalFullscreen) {
                v0.getWindow().clearFlags(v1);
            }

            if(this.mImmersiveSticky) {
                return;
            }

            this.mRootView.setSystemUiVisibility(this.mLastSystemUiVisibility);
        }
    }

    public boolean onDown(MotionEvent arg1) {
        this.mTouchStatus = 0;
        return 1;
    }

    public boolean onFling(MotionEvent arg1, MotionEvent arg2, float arg3, float arg4) {
        return 0;
    }

    public void onHideCustomView() {
        this.initView();
        if(this.mCallback != null) {
            this.mCallback.onCustomViewHidden();
        }

        this.resumeFocus();
        this.detach();
    }

    public void onLongPress(MotionEvent arg1) {
    }

    public boolean onScale(ScaleGestureDetector arg5) {
        if(this.mMode == 1 && this.mVideoView != null) {
            float v5 = arg5.getScaleFactor();
            this.mVideoView.getLeft();
            this.mVideoView.getTop();
            this.mVideoView.getBottom();
            this.mVideoView.getRight();
            this.setOriginalSize(this.mCurrentOrientation, ((int)((((float)this.mVideoView.getWidth())) * v5)), ((int)((((float)this.mVideoView.getHeight())) * v5)));
            return 1;
        }

        return 0;
    }

    public boolean onScaleBegin(ScaleGestureDetector arg1) {
        return 1;
    }

    public void onScaleEnd(ScaleGestureDetector arg1) {
    }

    public boolean onScroll(MotionEvent arg18, MotionEvent arg19, float arg20, float arg21) {
        double v4_2;
        int v1_1;
        float v7;
        float v6;
        XWebNativeInterfaceInternal v0 = this;
        if(arg18 != null) {
            if(arg19 == null) {
            }
            else {
                float v4 = arg19.getX() - arg18.getX();
                float v5 = arg19.getY() - arg18.getY();
                if(v0.mControlView.getHeight() > v0.mControlView.getWidth()) {
                    v6 = ((float)v0.mControlView.getWidth());
                    v7 = ((float)v0.mControlView.getHeight());
                }
                else {
                    v6 = ((float)v0.mControlView.getHeight());
                    v7 = ((float)v0.mControlView.getWidth());
                }

                int v9 = 3;
                float v10 = 1.2f;
                float v11 = -1f;
                double v12 = 100;
                float v14 = 1f;
                float v15 = 100f;
                switch(v0.mTouchStatus) {
                    case 0: {
                        double v6_1 = Math.ceil(((double)(v0.mWebView.getContext().getResources().getDisplayMetrics().density * 25f)));
                        v1_1 = Float.compare(Math.abs(v4) - Math.abs(v5), v14);
                        v4_2 = 2;
                        if(v1_1 > 0) {
                            v6_1 *= v4_2;
                            if((((double)arg18.getX())) >= v6_1) {
                                if((((double)arg18.getX())) > (((double)v0.mControlView.getWidth())) - v6_1) {
                                }
                                else {
                                    v0.mTouchStatus = v9;
                                    goto label_37;
                                }
                            }

                            v0.mTouchStatus = 0;
                            goto label_37;
                        }

                        this.reset();
                        v6_1 *= v4_2;
                        if((((double)arg18.getY())) >= v6_1) {
                            if((((double)arg18.getY())) > (((double)v0.mControlView.getHeight())) - v6_1) {
                            }
                            else if(arg18.getX() < (((float)v0.mControlView.getWidth())) / 2f) {
                                v0.mTouchStatus = 2;
                                goto label_37;
                            }
                            else {
                                goto label_210;
                            }
                        }

                        goto label_213;
                    }
                    case 1: {
                        v5 *= v11;
                        double v2_1 = ((double)((((float)v0.mMaxVideoVolume)) * v5 / v6 * v10));
                        int v4_1 = ((int)v2_1);
                        if(v4_1 != 0 || Math.abs(v2_1) <= 0.2) {
                        label_124:
                            v1_1 = v4_1;
                        }
                        else if(v5 > 0f) {
                            v1_1 = 1;
                        }
                        else if(v5 < 0f) {
                            v1_1 = -1;
                        }
                        else {
                            goto label_124;
                        }

                        v2_1 = v0.mOldVideoVolume + (((double)v1_1));
                        v4_2 = 0;
                        if(Double.compare(v2_1, ((double)v0.mMaxVideoVolume)) > 0) {
                            v2_1 = ((double)v0.mMaxVideoVolume);
                        }
                        else if(v2_1 < v4_2) {
                            v2_1 = v4_2;
                        }

                        v0.mAudioManager.setStreamVolume(v9, ((int)v2_1), 4);
                        v0.mLayoutStatus.setVolumnProgress(((int)(v2_1 / (((double)Float.valueOf(((float)v0.mMaxVideoVolume)).floatValue())) * v12)));
                        v0.mLayoutStatus.show();
                        break;
                    }
                    case 2: {
                        float v2 = v0.mBrightness + v5 * v11 / v6 * v10;
                        if(v2 < 0f) {
                            v14 = 0f;
                        }
                        else if(v2 > v14) {
                        }
                        else {
                            v14 = v2;
                        }

                        v0.mLayoutParams.screenBrightness = v14;
                        v0.mWindow.setAttributes(v0.mLayoutParams);
                        v0.mLayoutStatus.setBrightProgress(((int)(v14 * v15)));
                        v0.mLayoutStatus.show();
                        break;
                    }
                    case 3: {
                        if(Double.isInfinite(v0.mVideoDuration)) {
                            goto label_37;
                        }

                        float v3 = arg19.getX() - arg18.getX();
                        v0.mOldVideoProgress = v0.mSeekBar.getProgress();
                        if(v3 > 0f) {
                            v0.mNewVideoProgress = ((float)(((int)(v0.mOldVideoProgress + v3 / v7 * v15))));
                            if(v0.mNewVideoProgress > v15) {
                                v0.mNewVideoProgress = v15;
                            }
                        }
                        else {
                            v0.mNewVideoProgress = ((float)(((int)(v0.mOldVideoProgress + v3 / v7 * v15))));
                            if(v0.mNewVideoProgress < 0f) {
                                v0.mNewVideoProgress = 0f;
                            }
                        }

                        double v1 = v0.mVideoDuration * (((double)v0.mNewVideoProgress)) / v12;
                        v0.mLayoutStatus.setVideoTimeProgress(v0.getAllVideoTime(v1));
                        v0.mLayoutStatus.show();
                        v0.getVideoFrame(v1);
                        break;
                    }
                }

            label_37:
                boolean v1_2 = true;
                return v1_2;
            label_210:
                v1_2 = true;
                v0.mTouchStatus = 1;
                return v1_2;
            label_213:
                v1_2 = true;
                v0.mTouchStatus = 0;
                return v1_2;
            }
        }

        return 1;
    }

    public void onShowCustomView(View arg3, WebChromeClient$CustomViewCallback arg4) {
        Log.i("XWebNativeInterfaceInternal", "onShowCustomView");
        this.initView();
        this.mVideoView = arg3;
        this.mCallback = arg4;
        if(this.mVideoView != null || (this.mWebView.isXWalkKernel())) {
            if(this.mWebView.isXWalkKernel()) {
                XWebReporter.onXWWebViewOnShowCustomViewSpecial();
            }
            else if(this.mWebView.isSysKernel()) {
                XWebReporter.onSysWebViewOnShowCustomViewSpecial();
            }

            this.attach();
        }

        this.fixFocus();
    }

    public void onShowPress(MotionEvent arg1) {
    }

    public boolean onSingleTapUp(MotionEvent arg1) {
        this.stopControlViewTimer();
        this.switchControlView();
        return 1;
    }

    @JavascriptInterface public void onSpecialVideoEnterFullscreen(int arg4) {
        Log.i("XWebNativeInterfaceInternal", "onSpecialVideoEnterFullscreen:" + arg4);
        XWebReporter.onSpecialVideoEnterFullscreen(arg4, this.mWebView.isXWalkKernel());
    }

    @JavascriptInterface public void onSpecialVideoHook(int arg4) {
        Log.i("XWebNativeInterfaceInternal", "onSpecialVideoHook:" + arg4);
        XWebReporter.onSpecialVideoHook(arg4, this.mWebView.isXWalkKernel());
    }

    public void onToggleFullscreen(boolean arg1) {
        if(arg1) {
            this.mIsFullscreen = true;
        }
        else {
            this.mIsFullscreen = false;
            this.mVideoIsEnterFullscreen = false;
        }
    }

    @JavascriptInterface public void onVideoEnterFullscreen(boolean arg17, long arg18, double arg20, double arg22, boolean arg24, boolean arg25, double arg26, double arg28, double[] arg30) {
        XWebNativeInterfaceInternal v9 = this;
        boolean v6 = arg17;
        double v2 = arg20;
        double v4 = arg22;
        boolean v10 = arg24;
        boolean v0 = arg25;
        double v11 = arg28;
        StringBuilder v7 = new StringBuilder();
        v7.append("onVideoEnterFullscreen:isVideoTag:");
        v7.append(v6);
        v7.append(",width:");
        v7.append(v2);
        v7.append(",height:");
        v7.append(v4);
        v7.append(",pause:");
        v7.append(v10);
        v7.append(",seeking:");
        v7.append(v0);
        v7.append(",currentTime:");
        double v13 = arg26;
        v7.append(v13);
        v7.append(",duration:");
        v7.append(v11);
        Log.i("XWebNativeInterfaceInternal", v7.toString());
        if(v9.mWebView.isXWalkKernel()) {
            XWebReporter.onXWWebViewOnShowCustomViewSpecialNative();
        }
        else if(v9.mWebView.isSysKernel()) {
            XWebReporter.onSysWebViewOnShowCustomViewSpecialNative();
        }

        if(v6) {
            if(v9.mWebView.isXWalkKernel()) {
                XWebReporter.onXWWebViewOnShowCustomViewSpecialNativeVideo();
            }
            else if(v9.mWebView.isSysKernel()) {
                XWebReporter.onSysWebViewOnShowCustomViewSpecialNativeVideo();
            }

            v9.mVideoIsEnterFullscreen = true;
        }

        v9.mVideoId = arg18;
        v9.mVideoDuration = v11;
        v9.mVideoWidth = v2;
        v9.mVideoHeight = v4;
        if(v0) {
            this.onVideoSeeking();
        }
        else {
            this.onVideoSeeked();
        }

        v9.runOnUIThread(new Runnable(v2, v4, v6, v11) {
            public void run() {
                XWebNativeInterfaceInternal.this.onVideoSizeChanged(((int)this.val$width), ((int)this.val$height));
                XWebNativeInterfaceInternal.this.runOnUIThread(new Runnable() {
                    public void run() {
                        int v1 = 0xFF000000;
                        if(this.this$1.val$isVideoTag) {
                            if((this.this$1.this$0.mWebView.isXWalkKernel()) && this.this$1.this$0.getSurfaceView(this.this$1.this$0.mVideoView) == null && this.this$1.this$0.getTextureView(this.this$1.this$0.mVideoView) == null) {
                                this.this$1.this$0.mBackgroundView.setBackgroundColor(0);
                                this.this$1.this$0.mControlView.setVisibility(0);
                                return;
                            }

                            this.this$1.this$0.mBackgroundView.setBackgroundColor(v1);
                            this.this$1.this$0.mRootView.setBackgroundColor(0);
                            this.this$1.this$0.mControlView.setVisibility(0);
                        }
                        else {
                            this.this$1.this$0.mBackgroundView.setBackgroundColor(0);
                            this.this$1.this$0.mRootView.setBackgroundColor(v1);
                        }

                        if(Double.isInfinite(this.this$1.val$duration)) {
                            Log.i("XWebNativeInterfaceInternal", "onVideoEnterFullscreen duration is infinite, maybe live video");
                            this.this$1.this$0.mLayoutProgress.setVisibility(4);
                        }
                        else {
                            this.this$1.this$0.mLayoutProgress.setVisibility(0);
                        }
                    }
                });
            }
        });
        v9.onVideoTimeUpdate(v13, v11, arg30);
        v9.updateImagePlay(v10);
    }

    @JavascriptInterface public void onVideoExitFullscreen() {
        Log.i("XWebNativeInterfaceInternal", "onVideoExitFullscreen");
        this.mVideoIsEnterFullscreen = false;
        this.runOnUIThread(new Runnable() {
            public void run() {
                XWebNativeInterfaceInternal.this.mControlView.setVisibility(8);
            }
        });
    }

    @JavascriptInterface public void onVideoGetFrame(long arg4, double arg6, String arg8) {
        if(arg4 == this.mVideoId && this.mTimeGetFrame == arg6 && arg8 != null && (arg8.startsWith("data:image/png;base64,"))) {
            byte[] v4 = Base64.decode(arg8.substring("data:image/png;base64,".length()), 0);
            this.mImageFrame.setImageBitmap(BitmapFactory.decodeByteArray(v4, 0, v4.length));
            this.mImageFrame.setVisibility(0);
        }
    }

    @JavascriptInterface public void onVideoPause() {
        Log.i("XWebNativeInterfaceInternal", "onVideoPause");
        this.updateImagePlay(true);
    }

    @JavascriptInterface public void onVideoPlay() {
        Log.i("XWebNativeInterfaceInternal", "onVideoPlay");
        this.updateImagePlay(false);
    }

    @JavascriptInterface public void onVideoPlaying() {
        Log.i("XWebNativeInterfaceInternal", "onVideoPlaying");
        this.runOnUIThread(new Runnable() {
            public void run() {
                XWebNativeInterfaceInternal.this.mProgressLoading.setVisibility(8);
            }
        });
    }

    @JavascriptInterface public void onVideoSeeked() {
        Log.i("XWebNativeInterfaceInternal", "onVideoSeeked");
        this.runOnUIThread(new Runnable() {
            public void run() {
                XWebNativeInterfaceInternal.this.mProgressLoading.setVisibility(8);
            }
        });
    }

    @JavascriptInterface public void onVideoSeeking() {
        Log.i("XWebNativeInterfaceInternal", "onVideoSeeking");
        this.runOnUIThread(new Runnable() {
            public void run() {
                XWebNativeInterfaceInternal.this.mProgressLoading.setVisibility(0);
            }
        });
    }

    public void onVideoSizeChanged(int arg4, int arg5) {
        Activity v0 = this.tryGetActivity();
        if(v0 == null) {
            return;
        }

        if(this.mPreOrientation == -3 && v0 != null) {
            this.mPreOrientation = v0.getRequestedOrientation();
        }

        int v1 = 0;
        if(arg4 <= arg5) {
            v0.setRequestedOrientation(1);
            v1 = 1;
        }
        else {
            v0.setRequestedOrientation(0);
        }

        this.mCurrentOrientation = v1;
        this.setAutoFix(v1, arg4, arg5);
    }

    @JavascriptInterface public void onVideoSizeUpdate(double arg9, double arg11) {
        if(this.mVideoHeight != arg11 || this.mVideoWidth != arg9) {
            Log.i("XWebNativeInterfaceInternal", "onVideoSizeUpdate width:" + arg9 + ", height:" + arg11);
            this.mVideoWidth = arg9;
            this.mVideoHeight = arg11;
            this.runOnUIThread(new Runnable(arg9, arg11) {
                public void run() {
                    XWebNativeInterfaceInternal.this.onVideoSizeChanged(((int)this.val$width), ((int)this.val$height));
                }
            });
        }
    }

    @JavascriptInterface public void onVideoTimeUpdate(double arg2, double arg4, double[] arg6) {
        this.mVideoDuration = arg4;
        this.updateVideoTime(arg2, true);
        this.updateVideoCache(arg4, arg6);
    }

    @JavascriptInterface public void onVideoWaiting() {
        Log.i("XWebNativeInterfaceInternal", "onVideoWaiting");
        this.runOnUIThread(new Runnable() {
            public void run() {
                XWebNativeInterfaceInternal.this.mProgressLoading.setVisibility(0);
            }
        });
    }

    private void registerActivityLifecycleCallback(Activity arg2) {
        if(arg2 != null) {
            Application v2 = arg2.getApplication();
            if(v2 != null) {
                v2.registerActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
            }
        }

        ApplicationStatus.registerWindowFocusChangedListener(this.mWindowFocusChnagedListener);
    }

    public void registerJavascriptInterface(Object arg8) {
        new ReflectMethod(arg8, "addJavascriptInterface", new Class[]{Object.class, String.class}).invoke(new Object[]{this, "xwebToNative"});
    }

    public void reset() {
        if(this.mAudioManager == null) {
            this.mAudioManager = this.getContext().getSystemService("audio");
        }

        this.mMaxVideoVolume = this.mAudioManager.getStreamMaxVolume(3);
        this.mOldVideoVolume = ((double)this.mAudioManager.getStreamVolume(3));
        if(this.mBrightnessHelper == null) {
            this.mBrightnessHelper = new BrightnessHelper(this.getContext());
        }

        if(this.mWindow == null) {
            Activity v0 = this.tryGetActivity();
            if(v0 != null) {
                this.mWindow = v0.getWindow();
            }
        }

        if(this.mWindow != null && this.mLayoutParams == null) {
            this.mLayoutParams = this.mWindow.getAttributes();
        }

        if(this.mLayoutParams != null) {
            this.mBrightness = this.mLayoutParams.screenBrightness;
            if(this.mBrightness != -1f) {
                return;
            }

            try {
                this.mBrightness = ((float)((((double)Settings$System.getInt(this.getContext().getContentResolver(), "screen_brightness"))) / 256));
            }
            catch(Settings$SettingNotFoundException v0_1) {
                Log.e("XWebNativeInterfaceInternal", "brightness get error:" + v0_1.getMessage());
            }
        }
    }

    private void resumeFocus() {
        Log.i("XWebNativeInterfaceInternal", "resumeFocus");
        if(((this.mWebView.getView() instanceof ViewGroup)) && this.mWebViewDescendantFocusability != 0) {
            this.mWebView.getView().setDescendantFocusability(this.mWebViewDescendantFocusability);
            this.mWebViewDescendantFocusability = 0;
        }

        if(((this.mWebView.getWebViewUI() instanceof ViewGroup)) && this.mWebViewUIDescendantFocusability != 0) {
            this.mWebView.getView().setDescendantFocusability(this.mWebViewUIDescendantFocusability);
            this.mWebViewUIDescendantFocusability = 0;
        }
    }

    public void runOnUIThread(Runnable arg2) {
        this.initView();
        Activity v0 = this.tryGetActivity();
        if(v0 != null) {
            v0.runOnUiThread(arg2);
        }
        else {
            this.mWebView.getView().post(arg2);
        }
    }

    private void setAutoFix(int arg2, int arg3, int arg4) {
        if(this.mVideoView != null) {
            this.mVideoView.setLayoutParams(this.getAutoFixLayoutParams(arg2, arg3, arg4));
        }
    }

    private void setFillScreen(int arg2, int arg3, int arg4) {
        if(this.mVideoView != null) {
            this.mVideoView.setLayoutParams(this.getFullscreenLayoutParams(arg2, arg3, arg4));
        }
    }

    private void setOriginalSize(int arg3, int arg4, int arg5) {
        FrameLayout$LayoutParams v3 = new FrameLayout$LayoutParams(-2, -2, 17);
        v3.width = arg4;
        v3.height = arg5;
        if(this.mVideoView != null) {
            this.mVideoView.setLayoutParams(((ViewGroup$LayoutParams)v3));
        }
    }

    public void showControlView() {
        this.runOnUIThread(new Runnable() {
            public void run() {
                XWebNativeInterfaceInternal.this.mIsShowingControlView = true;
                XWebNativeInterfaceInternal.this.mLayoutVideoControl.setVisibility(0);
                XWebNativeInterfaceInternal.this.startControlViewTimer();
            }
        });
    }

    public void startControlViewTimer() {
        if(this.mControlViewTimer != null) {
            this.mControlViewTimer.cancel();
            this.mControlViewTimer.purge();
            this.mControlViewTimer = null;
        }

        this.mControlViewTimer = new Timer();
        this.mControlViewTimer.schedule(new TimerTask() {
            public void run() {
                XWebNativeInterfaceInternal.this.hideControlView();
            }
        }, 4000);
    }

    public void stopControlViewTimer() {
        if(this.mControlViewTimer != null) {
            this.mControlViewTimer.cancel();
            this.mControlViewTimer.purge();
            this.mControlViewTimer = null;
        }
    }

    public void switchControlView() {
        if(this.mIsShowingControlView) {
            this.hideControlView();
        }
        else {
            this.showControlView();
        }
    }

    public void switchOrientation() {
        Activity v0 = this.tryGetActivity();
        if(v0 == null) {
            return;
        }

        if(this.mCurrentOrientation == 1) {
            v0.setRequestedOrientation(0);
            this.mCurrentOrientation = 0;
        }
        else if(this.mCurrentOrientation == 0) {
            v0.setRequestedOrientation(1);
            this.mCurrentOrientation = 1;
        }

        this.setAutoFix(this.mCurrentOrientation, ((int)this.mVideoWidth), ((int)this.mVideoHeight));
    }

    private Activity tryGetActivity() {
        if(this.mActivity != null) {
            return this.mActivity;
        }

        Activity v0 = this.activityFromContext(this.mWebView.getContext());
        if(v0 != null) {
            this.mActivity = v0;
        }
        else {
            Log.e("XWebNativeInterfaceInternal", "royle:tryGetActivity but no Activity");
        }

        return v0;
    }

    public void unRegisterActivityLifecycleCallback(Activity arg2) {
        if(arg2 != null) {
            Application v2 = arg2.getApplication();
            if(v2 != null) {
                v2.unregisterActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
            }
        }

        ApplicationStatus.unregisterWindowFocusChangedListener(this.mWindowFocusChnagedListener);
    }

    public void updateImagePlay(boolean arg2) {
        this.runOnUIThread(new Runnable(arg2) {
            public void run() {
                if(this.val$paused) {
                    XWebNativeInterfaceInternal.this.mImagePlay.setImageResource(0x7F06007E);
                }
                else {
                    XWebNativeInterfaceInternal.this.mImagePlay.setImageResource(0x7F060080);
                }
            }
        });
    }

    public void updateVideoCache(double arg2, double[] arg4) {
        this.runOnUIThread(new Runnable(arg2, arg4) {
            public void run() {
                XWebNativeInterfaceInternal.this.mSeekBar.updateCacheProgress(this.val$duration, this.val$buffered);
            }
        });
    }

    public void updateVideoTime(double arg2, boolean arg4) {
        this.runOnUIThread(new Runnable(arg4, arg2) {
            public void run() {
                if((this.val$needChangeSeekBar) && XWebNativeInterfaceInternal.this.mVideoDuration != 0) {
                    XWebNativeInterfaceInternal.this.mSeekBar.setProgress(((float)(((int)(this.val$currentTime / XWebNativeInterfaceInternal.this.mVideoDuration * 100)))), false);
                }

                XWebNativeInterfaceInternal.this.mTvCurrentTime.setText(XWebNativeInterfaceInternal.this.getCurrentVideoTime(this.val$currentTime));
                XWebNativeInterfaceInternal.this.mTvTotalTime.setText(XWebNativeInterfaceInternal.this.getTotalVideoTime());
            }
        });
    }
}

