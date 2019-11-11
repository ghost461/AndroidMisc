package org.chromium.content.browser;

import android.app.AlertDialog$Builder;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$System;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import com.util.RuntimeEnviroment;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="content") public class ContentVideoView extends FrameLayout {
    final class org.chromium.content.browser.ContentVideoView$1 implements ContentVideoViewEmbedder {
        org.chromium.content.browser.ContentVideoView$1() {
            super();
        }

        public void enterFullscreenVideo(View arg1, boolean arg2) {
        }

        public void exitFullscreenVideo() {
        }

        public void fullscreenVideoLoaded() {
        }

        public void setSystemUiVisibility(boolean arg1) {
        }
    }

    class org.chromium.content.browser.ContentVideoView$2 implements Runnable {
        org.chromium.content.browser.ContentVideoView$2(ContentVideoView arg1) {
            ContentVideoView.this = arg1;
            super();
        }

        public void run() {
            ContentVideoView.this.exitFullscreen(true);
        }
    }

    class VideoSurfaceView extends SurfaceView {
        public VideoSurfaceView(ContentVideoView arg2, Context arg3) {
            ContentVideoView.this = arg2;
            super(arg3);
            this.getHolder().addCallback(new SurfaceHolder$Callback(arg2) {
                public void surfaceChanged(SurfaceHolder arg1, int arg2, int arg3, int arg4) {
                }

                public void surfaceCreated(SurfaceHolder arg2) {
                    this.this$1.this$0.mSurfaceHolder = arg2;
                    this.this$1.this$0.openVideo();
                }

                public void surfaceDestroyed(SurfaceHolder arg5) {
                    Surface v0 = null;
                    if(Long.compare(this.this$1.this$0.mNativeContentVideoView, 0) != 0) {
                        this.this$1.this$0.nativeSetSurface(this.this$1.this$0.mNativeContentVideoView, v0);
                    }

                    this.this$1.this$0.mSurfaceHolder = ((SurfaceHolder)v0);
                    this.this$1.post(this.this$1.this$0.mExitFullscreenRunnable);
                }
            });
        }

        protected void onMeasure(int arg9, int arg10) {
            if(ContentVideoView.this.mVideoWidth <= 0 || ContentVideoView.this.mVideoHeight <= 0) {
                arg9 = 1;
                arg10 = 1;
            }
            else {
                arg9 = VideoSurfaceView.getDefaultSize(ContentVideoView.this.mVideoWidth, arg9);
                arg10 = VideoSurfaceView.getDefaultSize(ContentVideoView.this.mVideoHeight, arg10);
                if(ContentVideoView.this.mVideoWidth * arg10 > ContentVideoView.this.mVideoHeight * arg9) {
                    arg10 = ContentVideoView.this.mVideoHeight * arg9 / ContentVideoView.this.mVideoWidth;
                }
                else if(ContentVideoView.this.mVideoWidth * arg10 < ContentVideoView.this.mVideoHeight * arg9) {
                    arg9 = ContentVideoView.this.mVideoWidth * arg10 / ContentVideoView.this.mVideoHeight;
                }
            }

            if(ContentVideoView.this.mUmaRecorded) {
                if(ContentVideoView.this.mPlaybackStartTime == ContentVideoView.this.mOrientationChangedTime) {
                    if(ContentVideoView.this.isOrientationPortrait() != ContentVideoView.this.mInitialOrientation) {
                        ContentVideoView.this.mOrientationChangedTime = System.currentTimeMillis();
                    }
                }
                else if(!ContentVideoView.this.mPossibleAccidentalChange && ContentVideoView.this.isOrientationPortrait() == ContentVideoView.this.mInitialOrientation && System.currentTimeMillis() - ContentVideoView.this.mOrientationChangedTime < 5000) {
                    ContentVideoView.this.mPossibleAccidentalChange = true;
                }
            }

            this.setMeasuredDimension(arg9, arg10);
        }
    }

    class VideoTextureView extends TextureView {
        public VideoTextureView(ContentVideoView arg1, Context arg2) {
            ContentVideoView.this = arg1;
            super(arg2);
            this.setSurfaceTextureListener(new TextureView$SurfaceTextureListener(arg1) {
                public void onSurfaceTextureAvailable(SurfaceTexture arg1, int arg2, int arg3) {
                    this.this$1.this$0.mSurface = new Surface(arg1);
                    this.this$1.this$0.openVideo();
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture arg5) {
                    Surface v0 = null;
                    if(Long.compare(this.this$1.this$0.mNativeContentVideoView, 0) != 0) {
                        this.this$1.this$0.nativeSetSurface(this.this$1.this$0.mNativeContentVideoView, v0);
                    }

                    this.this$1.this$0.mSurface.release();
                    this.this$1.this$0.mSurface = v0;
                    this.this$1.post(this.this$1.this$0.mExitFullscreenRunnable);
                    return 1;
                }

                public void onSurfaceTextureSizeChanged(SurfaceTexture arg1, int arg2, int arg3) {
                }

                public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
                }
            });
        }

        protected void onMeasure(int arg10, int arg11) {
            int v2;
            int v0;
            if(ContentVideoView.this.mVideoWidth <= 0 || ContentVideoView.this.mVideoHeight <= 0) {
                v0 = 1;
                v2 = 1;
            }
            else {
                v0 = VideoTextureView.getDefaultSize(ContentVideoView.this.mVideoWidth, arg10);
                v2 = VideoTextureView.getDefaultSize(ContentVideoView.this.mVideoHeight, arg11);
                if(ContentVideoView.this.mVideoWidth * v2 > ContentVideoView.this.mVideoHeight * v0) {
                    v2 = ContentVideoView.this.mVideoHeight * v0 / ContentVideoView.this.mVideoWidth;
                }
                else if(ContentVideoView.this.mVideoWidth * v2 < ContentVideoView.this.mVideoHeight * v0) {
                    v0 = ContentVideoView.this.mVideoWidth * v2 / ContentVideoView.this.mVideoHeight;
                }
            }

            if(ContentVideoView.this.mUmaRecorded) {
                if(ContentVideoView.this.mPlaybackStartTime == ContentVideoView.this.mOrientationChangedTime) {
                    if(ContentVideoView.this.isOrientationPortrait() != ContentVideoView.this.mInitialOrientation) {
                        ContentVideoView.this.mOrientationChangedTime = System.currentTimeMillis();
                    }
                }
                else if(!ContentVideoView.this.mPossibleAccidentalChange && ContentVideoView.this.isOrientationPortrait() == ContentVideoView.this.mInitialOrientation && System.currentTimeMillis() - ContentVideoView.this.mOrientationChangedTime < 5000) {
                    ContentVideoView.this.mPossibleAccidentalChange = true;
                }
            }

            Log.i("cr.ContentVideoView", "VideoTextureView onMeasure widthMeasureSpec:" + arg10 + ",heightMeasureSpec:" + arg11 + ",videoWidth:" + ContentVideoView.this.mVideoWidth + ",videoHeight:" + ContentVideoView.this.mVideoHeight + ",width:" + v0 + ",height:" + v2, new Object[0]);
            this.setMeasuredDimension(v0, v2);
        }
    }

    public static final int MEDIA_ERROR_INVALID_CODE = 3;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 2;
    private static final ContentVideoViewEmbedder NULL_VIDEO_EMBEDDER = null;
    private static final int STATE_ERROR = -1;
    private static final int STATE_NO_ERROR = 0;
    private static final String TAG = "cr.ContentVideoView";
    private int mCurrentState;
    private final ContentVideoViewEmbedder mEmbedder;
    private String mErrorButton;
    private String mErrorTitle;
    private final Runnable mExitFullscreenRunnable;
    private boolean mInitialOrientation;
    private boolean mIsVideoLoaded;
    private long mNativeContentVideoView;
    private long mOrientationChangedTime;
    private String mPlaybackErrorText;
    private long mPlaybackStartTime;
    private boolean mPossibleAccidentalChange;
    private Surface mSurface;
    private SurfaceHolder mSurfaceHolder;
    private boolean mUmaRecorded;
    private String mUnknownErrorText;
    private int mVideoHeight;
    private VideoSurfaceView mVideoSurfaceView;
    private VideoTextureView mVideoTextureView;
    private int mVideoWidth;

    static {
        ContentVideoView.NULL_VIDEO_EMBEDDER = new org.chromium.content.browser.ContentVideoView$1();
    }

    private ContentVideoView(Context arg3, long arg4, ContentVideoViewEmbedder arg6, int arg7, int arg8) {
        super(arg3);
        this.mCurrentState = 0;
        this.mExitFullscreenRunnable = new org.chromium.content.browser.ContentVideoView$2(this);
        this.mNativeContentVideoView = arg4;
        if(arg6 != null) {
        }
        else {
            arg6 = ContentVideoView.NULL_VIDEO_EMBEDDER;
        }

        this.mEmbedder = arg6;
        this.mUmaRecorded = false;
        this.mPossibleAccidentalChange = false;
        boolean v5 = arg7 <= 0 || arg8 <= 0 ? false : true;
        this.mIsVideoLoaded = v5;
        this.initResources(arg3);
        if(RuntimeEnviroment.supportVideoTextureView()) {
            this.mVideoTextureView = new VideoTextureView(this, arg3);
        }
        else {
            this.mVideoSurfaceView = new VideoSurfaceView(this, arg3);
            this.mVideoSurfaceView.setZOrderOnTop(true);
            this.mVideoSurfaceView.setZOrderMediaOverlay(true);
        }

        this.showContentVideoView();
        this.setVisibility(0);
        this.mEmbedder.enterFullscreenVideo(((View)this), this.mIsVideoLoaded);
        this.onVideoSizeChanged(arg7, arg8);
    }

    static SurfaceHolder access$002(ContentVideoView arg0, SurfaceHolder arg1) {
        arg0.mSurfaceHolder = arg1;
        return arg1;
    }

    static void access$100(ContentVideoView arg0) {
        arg0.openVideo();
    }

    static boolean access$1000(ContentVideoView arg0) {
        return arg0.isOrientationPortrait();
    }

    static boolean access$1100(ContentVideoView arg0) {
        return arg0.mInitialOrientation;
    }

    static boolean access$1200(ContentVideoView arg0) {
        return arg0.mPossibleAccidentalChange;
    }

    static boolean access$1202(ContentVideoView arg0, boolean arg1) {
        arg0.mPossibleAccidentalChange = arg1;
        return arg1;
    }

    static Surface access$1300(ContentVideoView arg0) {
        return arg0.mSurface;
    }

    static Surface access$1302(ContentVideoView arg0, Surface arg1) {
        arg0.mSurface = arg1;
        return arg1;
    }

    static long access$200(ContentVideoView arg2) {
        return arg2.mNativeContentVideoView;
    }

    static void access$300(ContentVideoView arg0, long arg1, Surface arg3) {
        arg0.nativeSetSurface(arg1, arg3);
    }

    static Runnable access$400(ContentVideoView arg0) {
        return arg0.mExitFullscreenRunnable;
    }

    static int access$500(ContentVideoView arg0) {
        return arg0.mVideoWidth;
    }

    static int access$600(ContentVideoView arg0) {
        return arg0.mVideoHeight;
    }

    static boolean access$700(ContentVideoView arg0) {
        return arg0.mUmaRecorded;
    }

    static long access$800(ContentVideoView arg2) {
        return arg2.mPlaybackStartTime;
    }

    static long access$900(ContentVideoView arg2) {
        return arg2.mOrientationChangedTime;
    }

    static long access$902(ContentVideoView arg0, long arg1) {
        arg0.mOrientationChangedTime = arg1;
        return arg1;
    }

    @CalledByNative private static ContentVideoView createContentVideoView(WebContents arg7, ContentVideoViewEmbedder arg8, long arg9, int arg11, int arg12) {
        ThreadUtils.assertOnUiThread();
        return new ContentVideoView(ContentViewCoreImpl.fromWebContents(arg7).getContext(), arg9, arg8, arg11, arg12);
    }

    @CalledByNative private void destroyContentVideoView(boolean arg3) {
        if(this.mVideoSurfaceView != null || this.mVideoTextureView != null) {
            this.removeSurfaceView();
            this.setVisibility(8);
            this.mEmbedder.exitFullscreenVideo();
        }

        if(arg3) {
            this.mNativeContentVideoView = 0;
        }
    }

    @CalledByNative public void exitFullscreen(boolean arg13) {
        long v8 = 0;
        if(this.mNativeContentVideoView != v8) {
            this.destroyContentVideoView(false);
            if((this.mUmaRecorded) && !this.mPossibleAccidentalChange) {
                long v0 = System.currentTimeMillis();
                long v6 = this.mOrientationChangedTime - this.mPlaybackStartTime;
                long v4 = v0 - this.mOrientationChangedTime;
                if(v6 == v8) {
                    v6 = v8;
                }
                else {
                    long v10 = v4;
                    v4 = v6;
                    v6 = v10;
                }

                this.nativeRecordExitFullscreenPlayback(this.mNativeContentVideoView, this.mInitialOrientation, v4, v6);
            }

            this.nativeDidExitFullscreen(this.mNativeContentVideoView, arg13);
            this.mNativeContentVideoView = v8;
        }
    }

    public static ContentVideoView getContentVideoView() {
        return ContentVideoView.nativeGetSingletonJavaContentVideoView();
    }

    public ContentVideoViewEmbedder getContentVideoViewEmbedder() {
        return this.mEmbedder;
    }

    private void initResources(Context arg2) {
        if(this.mPlaybackErrorText != null) {
            return;
        }

        this.mPlaybackErrorText = arg2.getString(0x7F0C005B);
        this.mUnknownErrorText = arg2.getString(0x7F0C005C);
        this.mErrorButton = arg2.getString(0x7F0C005A);
        this.mErrorTitle = arg2.getString(0x7F0C005D);
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

    private native void nativeDidExitFullscreen(long arg1, boolean arg2) {
    }

    private static native ContentVideoView nativeGetSingletonJavaContentVideoView() {
    }

    private native void nativeRecordExitFullscreenPlayback(long arg1, boolean arg2, long arg3, long arg4) {
    }

    private native void nativeRecordFullscreenPlayback(long arg1, boolean arg2, boolean arg3) {
    }

    private native void nativeSetSurface(long arg1, Surface arg2) {
    }

    public void onFullscreenWindowFocused() {
        this.mEmbedder.setSystemUiVisibility(true);
    }

    @CalledByNative public void onMediaPlayerError(int arg6) {
        Log.d("cr.ContentVideoView", "OnMediaPlayerError: %d", Integer.valueOf(arg6));
        int v1 = -1;
        if(this.mCurrentState == v1) {
            return;
        }

        if(arg6 == 3) {
            return;
        }

        this.mCurrentState = v1;
        if(WindowAndroid.activityFromContext(this.getContext()) == null) {
            Log.w("cr.ContentVideoView", "Unable to show alert dialog because it requires an activity context", new Object[0]);
            return;
        }

        if(this.getWindowToken() != null) {
            int v0 = 2;
            String v6 = arg6 == v0 ? this.mPlaybackErrorText : this.mUnknownErrorText;
            try {
                new AlertDialog$Builder(this.getContext()).setTitle(this.mErrorTitle).setMessage(((CharSequence)v6)).setPositiveButton(this.mErrorButton, new DialogInterface$OnClickListener() {
                    public void onClick(DialogInterface arg1, int arg2) {
                    }
                }).setCancelable(false).show();
            }
            catch(RuntimeException v2) {
                Object[] v0_1 = new Object[v0];
                v0_1[0] = v6;
                v0_1[1] = v2;
                Log.e("cr.ContentVideoView", "Cannot show the alert dialog, error message: %s", v0_1);
            }
        }
    }

    @CalledByNative private void onVideoSizeChanged(int arg4, int arg5) {
        this.mVideoWidth = arg4;
        this.mVideoHeight = arg5;
        boolean v5 = true;
        if(!this.mIsVideoLoaded && this.mVideoWidth > 0 && this.mVideoHeight > 0) {
            this.mIsVideoLoaded = true;
            this.mEmbedder.fullscreenVideoLoaded();
        }

        if(this.mVideoSurfaceView != null) {
            this.mVideoSurfaceView.getHolder().setFixedSize(this.mVideoWidth, this.mVideoHeight);
        }
        else if(this.mVideoTextureView != null) {
            this.mVideoTextureView.requestLayout();
        }

        if(this.mUmaRecorded) {
            return;
        }

        try {
            if(Settings$System.getInt(this.getContext().getContentResolver(), "accelerometer_rotation") != 0) {
                goto label_33;
            }
        }
        catch(Settings$SettingNotFoundException ) {
            return;
        }

        return;
    label_33:
        this.mInitialOrientation = this.isOrientationPortrait();
        this.mUmaRecorded = true;
        this.mPlaybackStartTime = System.currentTimeMillis();
        this.mOrientationChangedTime = this.mPlaybackStartTime;
        long v0 = this.mNativeContentVideoView;
        if(this.mVideoHeight > this.mVideoWidth) {
        }
        else {
            v5 = false;
        }

        this.nativeRecordFullscreenPlayback(v0, v5, this.mInitialOrientation);
    }

    @CalledByNative private void openVideo() {
        if(this.mSurfaceHolder != null || this.mSurface != null) {
            Surface v0 = null;
            if(this.mSurfaceHolder != null) {
                v0 = this.mSurfaceHolder.getSurface();
            }
            else if(this.mSurface != null) {
                v0 = this.mSurface;
            }

            this.mCurrentState = 0;
            if(this.mNativeContentVideoView == 0) {
                return;
            }

            this.nativeSetSurface(this.mNativeContentVideoView, v0);
        }
    }

    private void removeSurfaceView() {
        if(this.mVideoSurfaceView != null) {
            this.removeView(this.mVideoSurfaceView);
        }
        else if(this.mVideoTextureView != null) {
            this.removeView(this.mVideoTextureView);
        }

        this.mVideoSurfaceView = null;
        this.mVideoTextureView = null;
    }

    private void showContentVideoView() {
        int v1 = 17;
        int v2 = -2;
        if(this.mVideoSurfaceView != null) {
            this.addView(this.mVideoSurfaceView, new FrameLayout$LayoutParams(v2, v2, v1));
        }
        else if(this.mVideoTextureView != null) {
            this.addView(this.mVideoTextureView, new FrameLayout$LayoutParams(v2, v2, v1));
        }
    }
}

