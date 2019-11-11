package org.chromium.content.browser;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import com.util.RuntimeEnviroment;
import java.util.concurrent.atomic.AtomicLong;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.UiUtils;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace(value="content") public class ContentViewRenderView extends FrameLayout {
    public enum CompositingSurfaceType {
        public static final enum CompositingSurfaceType SURFACE_VIEW;
        public static final enum CompositingSurfaceType TEXTURE_VIEW;

        static {
            CompositingSurfaceType.SURFACE_VIEW = new CompositingSurfaceType("SURFACE_VIEW", 0);
            CompositingSurfaceType.TEXTURE_VIEW = new CompositingSurfaceType("TEXTURE_VIEW", 1);
            CompositingSurfaceType.$VALUES = new CompositingSurfaceType[]{CompositingSurfaceType.SURFACE_VIEW, CompositingSurfaceType.TEXTURE_VIEW};
        }

        private CompositingSurfaceType(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static CompositingSurfaceType valueOf(String arg1) {
            return Enum.valueOf(CompositingSurfaceType.class, arg1);
        }

        public static CompositingSurfaceType[] values() {
            return CompositingSurfaceType.$VALUES.clone();
        }
    }

    public interface FirstRenderedFrameListener {
        void onFirstFrameReceived();
    }

    public class SurfaceTextureListenerEx implements TextureView$SurfaceTextureListener {
        static {
        }

        public SurfaceTextureListenerEx(ContentViewRenderView arg1) {
            ContentViewRenderView.this = arg1;
            super();
        }

        public void onSurfaceTextureAvailable(SurfaceTexture arg4, int arg5, int arg6) {
            ContentViewRenderView.this.mSurface = new Surface(arg4);
            Log.d("ContentViewRenderView", "onSurfaceTextureAvailable: ");
            if(arg4 != ContentViewRenderView.this.mFakeSurfaceTexture) {
                if(ContentViewRenderView.this.mFakeSurfaceTexture != null) {
                    ContentViewRenderView.this.nativeSurfaceDestroyed(ContentViewRenderView.this.mNativeContentViewRenderView);
                    ContentViewRenderView.this.mFakeSurfaceTexture.release();
                    ContentViewRenderView.this.mFakeSurfaceTexture = null;
                }

                ContentViewRenderView.this.onReadyToRender();
            }

            ContentViewRenderView.this.nativeSurfaceCreated(ContentViewRenderView.this.mNativeContentViewRenderView);
            this.onSurfaceTextureSizeChanged(arg4, arg5, arg6);
            ContentViewRenderView.this.onReadyToRender();
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture arg3) {
            ContentViewRenderView.this.nativeSurfaceDestroyed(ContentViewRenderView.this.mNativeContentViewRenderView);
            Log.d("ContentViewRenderView", "onSurfaceTextureDestroyed: ");
            ContentViewRenderView.this.mSurface.release();
            ContentViewRenderView.this.mSurface = null;
            return 1;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture arg8, int arg9, int arg10) {
            ContentViewRenderView.this.mNWidth = arg9;
            ContentViewRenderView.this.mNHeight = arg10;
            ContentViewRenderView.this.nativeSurfaceChanged(ContentViewRenderView.this.mNativeContentViewRenderView, 1, arg9, arg10, ContentViewRenderView.this.mSurface);
            if(ContentViewRenderView.this.mWebContents != null) {
                ContentViewRenderView.this.nativeOnPhysicalBackingSizeChanged(ContentViewRenderView.this.mNativeContentViewRenderView, ContentViewRenderView.this.mWebContents, arg9, arg10);
            }
        }

        public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
        }
    }

    private static final String TAG = "ContentViewRenderView";
    private CompositingSurfaceType mCompositingSurfaceType;
    private ExtendCanvasCallback mExtendCanvasCallback;
    private ExtendPluginCallback mExtendPluginCallback;
    private ExtendPluginManager mExtendPluginManager;
    private SurfaceTexture mFakeSurfaceTexture;
    private boolean mFirstFrameReceived;
    private FirstRenderedFrameListener mFirstRenderedFrameListener;
    private int mHeight;
    private boolean mIsOverlayVideoMode;
    private boolean mIsReadyToRender;
    private SurfaceTextureListenerEx mListener;
    int mNHeight;
    int mNWidth;
    private long mNativeContentViewRenderView;
    private boolean mNeedFakeRender;
    private boolean mNeedFakeSize;
    private boolean mNeedWaitSizeChanged;
    private Surface mSurface;
    private SurfaceHolder$Callback mSurfaceCallback;
    private final SurfaceView mSurfaceView;
    private TextureView mTextureView;
    protected WebContents mWebContents;
    private int mWidth;
    private WindowAndroid mWindowAndroid;
    private final AtomicLong nextTextureId;

    static {
    }

    public ContentViewRenderView(Context arg2) {
        this(arg2, CompositingSurfaceType.TEXTURE_VIEW);
    }

    public ContentViewRenderView(Context arg4, CompositingSurfaceType arg5) {
        super(arg4);
        this.nextTextureId = new AtomicLong(0);
        this.mIsReadyToRender = false;
        this.mNeedFakeSize = false;
        this.mNeedFakeRender = false;
        this.mNeedWaitSizeChanged = false;
        this.mNWidth = 0;
        this.mNHeight = 0;
        this.mListener = new SurfaceTextureListenerEx(this);
        this.mCompositingSurfaceType = arg5;
        int v1 = -1;
        if(arg5 == CompositingSurfaceType.TEXTURE_VIEW) {
            this.initTextureView(arg4);
            this.addView(this.mTextureView, new FrameLayout$LayoutParams(v1, v1));
            this.mSurfaceView = null;
            this.mSurfaceCallback = null;
            return;
        }

        this.mSurfaceView = this.createSurfaceView(this.getContext());
        this.mSurfaceView.setZOrderMediaOverlay(true);
        this.setSurfaceViewBackgroundColor(v1);
        this.addView(this.mSurfaceView, new FrameLayout$LayoutParams(v1, v1));
        this.mSurfaceView.setVisibility(8);
    }

    static long access$000(ContentViewRenderView arg2) {
        return arg2.mNativeContentViewRenderView;
    }

    static Surface access$100(ContentViewRenderView arg0) {
        return arg0.mSurface;
    }

    static Surface access$102(ContentViewRenderView arg0, Surface arg1) {
        arg0.mSurface = arg1;
        return arg1;
    }

    static SurfaceTexture access$200(ContentViewRenderView arg0) {
        return arg0.mFakeSurfaceTexture;
    }

    static SurfaceTexture access$202(ContentViewRenderView arg0, SurfaceTexture arg1) {
        arg0.mFakeSurfaceTexture = arg1;
        return arg1;
    }

    static void access$300(ContentViewRenderView arg0, long arg1) {
        arg0.nativeSurfaceDestroyed(arg1);
    }

    static void access$400(ContentViewRenderView arg0, long arg1) {
        arg0.nativeSurfaceCreated(arg1);
    }

    static void access$500(ContentViewRenderView arg0, long arg1, int arg3, int arg4, int arg5, Surface arg6) {
        arg0.nativeSurfaceChanged(arg1, arg3, arg4, arg5, arg6);
    }

    static void access$600(ContentViewRenderView arg0, long arg1, WebContents arg3, int arg4, int arg5) {
        arg0.nativeOnPhysicalBackingSizeChanged(arg1, arg3, arg4, arg5);
    }

    static SurfaceView access$700(ContentViewRenderView arg0) {
        return arg0.mSurfaceView;
    }

    protected SurfaceView createSurfaceView(Context arg2) {
        return new SurfaceView(arg2);
    }

    public void destroy() {
        if(this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            TextureView$SurfaceTextureListener v1 = null;
            this.mTextureView.setSurfaceTextureListener(v1);
            if(this.mSurface != null) {
                this.mSurface.release();
                this.mSurface = ((Surface)v1);
            }
        }
        else {
            this.mSurfaceView.getHolder().removeCallback(this.mSurfaceCallback);
        }

        this.nativeDestroy(this.mNativeContentViewRenderView);
        this.mNativeContentViewRenderView = 0;
    }

    @CalledByNative private void didSwapFrame() {
        if(this.mSurfaceView == null) {
            return;
        }

        if(this.mSurfaceView.getBackground() != null) {
            this.post(new Runnable() {
                public void run() {
                    ContentViewRenderView.this.mSurfaceView.setBackgroundResource(0);
                }
            });
        }
    }

    private void fakeRender(int arg5, int arg6) {
        if((this.mIsReadyToRender) || this.mFakeSurfaceTexture != null || arg5 <= 0 || arg6 <= 0) {
            Log.i("ContentViewRenderView", "has render in surface texture no need fakeRenderForPreload", new Object[0]);
        }
        else {
            Log.i("ContentViewRenderView", "fakeRenderForPreload width:" + arg5 + ", height:" + arg6, new Object[0]);
            this.mFakeSurfaceTexture = new SurfaceTexture(0);
            this.mListener.onSurfaceTextureAvailable(this.mFakeSurfaceTexture, arg5, arg6);
        }
    }

    public void fakeRenderForPreload(int arg4, int arg5, boolean arg6) {
        Log.i("ContentViewRenderView", "fakeRenderForPreload needFakeRender:" + arg6 + ", mIsReadyToRender:" + this.mIsReadyToRender + ", mFakeSurfaceTexture:" + this.mFakeSurfaceTexture + ", mNeedWaitSizeChanged:" + this.mNeedWaitSizeChanged + ", width:" + arg4 + ", height:" + arg5 + ", mWidth:" + arg4 + ", mHeight:" + arg5, new Object[0]);
        if(this.mNeedFakeSize) {
            return;
        }

        this.mNeedFakeSize = true;
        this.mNeedFakeRender = arg6;
        int v6 = 0x7FFFFFFF;
        if(arg4 == v6 && arg5 == v6) {
            if(this.mWidth > 0 && this.mHeight > 0) {
                arg4 = this.mWidth;
                arg5 = this.mHeight;
                goto label_52;
            }

            this.mNeedWaitSizeChanged = true;
            return;
        }

    label_52:
        if(this.mWebContents != null) {
            this.mWebContents.setSize(arg4, arg5);
        }

        if(this.mNeedFakeRender) {
            this.fakeRender(arg4, arg5);
        }
    }

    public float getDeviceScaleFactor() {
        return this.mWebContents.getRenderCoordinates().getDeviceScaleFactor();
    }

    public ExtendCanvasCallback getExtendCanvasCallback() {
        return this.mExtendCanvasCallback;
    }

    public ExtendPluginCallback getExtendPluginCallback() {
        return this.mExtendPluginCallback;
    }

    public ExtendPluginManager getExtendPluginManager() {
        return this.mExtendPluginManager;
    }

    public SurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    private void initTextureView(Context arg2) {
        this.mTextureView = new TextureView(arg2);
        if(RuntimeEnviroment.supportVideoTextureView()) {
            this.mTextureView.setOpaque(false);
        }
        else {
            this.setSurfaceViewBackgroundColor(-1);
        }

        this.mTextureView.setSurfaceTextureListener(this.mListener);
    }

    public boolean isInitialized() {
        boolean v0 = this.mSurfaceView.getHolder().getSurface() != null || this.mSurface != null ? true : false;
        return v0;
    }

    private static boolean isOpaque(int arg1) {
        boolean v1 = (arg1 >> 24 & 0xFF) == 0xFF ? true : false;
        return v1;
    }

    private native void nativeDestroy(long arg1) {
    }

    private native long nativeInit(WindowAndroid arg1) {
    }

    private native void nativeOnPhysicalBackingSizeChanged(long arg1, WebContents arg2, int arg3, int arg4) {
    }

    private native void nativeSetBackgroundColor(long arg1, int arg2) {
    }

    private native void nativeSetCurrentWebContents(long arg1, WebContents arg2) {
    }

    private native void nativeSetOverlayVideoMode(long arg1, boolean arg2) {
    }

    private native void nativeSurfaceChanged(long arg1, int arg2, int arg3, int arg4, Surface arg5) {
    }

    private native void nativeSurfaceCreated(long arg1) {
    }

    private native void nativeSurfaceDestroyed(long arg1) {
    }

    protected void onMeasure(int arg6, int arg7) {
        boolean v0 = UiUtils.isKeyboardShowing(this.getContext(), ((View)this));
        if(this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            int v1 = View$MeasureSpec.getSize(arg6) - this.getPaddingLeft() - this.getPaddingRight();
            int v2 = View$MeasureSpec.getSize(arg7) - this.getPaddingLeft() - this.getPaddingRight();
            if(this.getWidth() >= v1 && this.getHeight() >= v2 && (v0)) {
                arg7 = View$MeasureSpec.makeMeasureSpec(this.getHeight(), 0x40000000);
                arg6 = View$MeasureSpec.makeMeasureSpec(this.getWidth(), 0x40000000);
            }
        }

        super.onMeasure(arg6, arg7);
    }

    public void onNativeLibraryLoaded(WindowAndroid arg3) {
        this.mNativeContentViewRenderView = this.nativeInit(arg3);
        this.mWindowAndroid = arg3;
        if(this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            return;
        }

        this.mSurfaceCallback = new SurfaceHolder$Callback() {
            public void surfaceChanged(SurfaceHolder arg8, int arg9, int arg10, int arg11) {
                ContentViewRenderView.this.nativeSurfaceChanged(ContentViewRenderView.this.mNativeContentViewRenderView, arg9, arg10, arg11, arg8.getSurface());
                if(ContentViewRenderView.this.mWebContents != null) {
                    ContentViewRenderView.this.nativeOnPhysicalBackingSizeChanged(ContentViewRenderView.this.mNativeContentViewRenderView, ContentViewRenderView.this.mWebContents, arg10, arg11);
                }
            }

            public void surfaceCreated(SurfaceHolder arg3) {
                ContentViewRenderView.this.nativeSurfaceCreated(ContentViewRenderView.this.mNativeContentViewRenderView);
                ContentViewRenderView.this.onReadyToRender();
            }

            public void surfaceDestroyed(SurfaceHolder arg3) {
                ContentViewRenderView.this.nativeSurfaceDestroyed(ContentViewRenderView.this.mNativeContentViewRenderView);
            }
        };
        this.mSurfaceView.getHolder().addCallback(this.mSurfaceCallback);
        this.mSurfaceView.setVisibility(0);
    }

    protected void onReadyToRender() {
        this.mIsReadyToRender = true;
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        this.mWidth = arg1;
        this.mHeight = arg2;
        if(this.mWebContents != null) {
            this.mWebContents.setSize(arg1, arg2);
        }

        if(this.mNeedWaitSizeChanged) {
            this.mNeedWaitSizeChanged = false;
            if(this.mNeedFakeRender) {
                this.fakeRender(arg1, arg2);
            }
        }
    }

    protected void onWindowVisibilityChanged(int arg2) {
        super.onWindowVisibilityChanged(arg2);
        if(this.mWindowAndroid == null) {
            return;
        }

        if(arg2 == 8) {
            this.mWindowAndroid.onVisibilityChanged(false);
        }
        else if(arg2 == 0) {
            this.mWindowAndroid.onVisibilityChanged(true);
        }
    }

    public void registerFirstRenderedFrameListener(FirstRenderedFrameListener arg1) {
        this.mFirstRenderedFrameListener = arg1;
        if((this.mFirstFrameReceived) && this.mFirstRenderedFrameListener != null) {
            this.mFirstRenderedFrameListener.onFirstFrameReceived();
        }
    }

    public void setCurrentWebContents(WebContents arg9) {
        this.mWebContents = arg9;
        if(arg9 != null) {
            arg9.setSize(this.mWidth, this.mHeight);
            this.nativeOnPhysicalBackingSizeChanged(this.mNativeContentViewRenderView, arg9, this.mWidth, this.mHeight);
        }

        this.nativeSetCurrentWebContents(this.mNativeContentViewRenderView, arg9);
    }

    public void setExtendCanvasCallback(ExtendCanvasCallback arg1) {
        this.mExtendCanvasCallback = arg1;
    }

    public void setExtendPluginCallback(ExtendPluginCallback arg1) {
        this.mExtendPluginCallback = arg1;
    }

    public void setExtendPluginManager(ExtendPluginManager arg1) {
        this.mExtendPluginManager = arg1;
    }

    public void setOverlayVideoMode(boolean arg3) {
        if(this.mCompositingSurfaceType == CompositingSurfaceType.TEXTURE_VIEW) {
            this.mIsOverlayVideoMode = arg3;
            this.nativeSetOverlayVideoMode(this.mNativeContentViewRenderView, arg3);
            return;
        }

        int v0 = arg3 ? -3 : -1;
        this.mSurfaceView.getHolder().setFormat(v0);
        this.nativeSetOverlayVideoMode(this.mNativeContentViewRenderView, arg3);
    }

    public void setRenderViewBackground(int arg3) {
        this.nativeSetBackgroundColor(this.mNativeContentViewRenderView, arg3);
    }

    public void setSurfaceViewBackgroundColor(int arg3) {
        if(this.mSurfaceView != null) {
            this.mSurfaceView.setBackgroundColor(arg3);
        }
        else if(this.mTextureView != null) {
            if(RuntimeEnviroment.supportVideoTextureView()) {
                this.setBackgroundColor(arg3);
            }
            else {
                this.mTextureView.setOpaque(ContentViewRenderView.isOpaque(arg3));
                this.mTextureView.setBackgroundColor(arg3);
            }
        }
    }

    public void setZOrderOnTop(boolean arg2) {
        if(this.mSurfaceView == null) {
            return;
        }

        this.mSurfaceView.setZOrderOnTop(arg2);
    }

    public void triggerCreated() {
        __monitor_enter(this);
        try {
            Log.d("ContentViewRenderView", "triggerCreated ");
            if(0 != this.mNativeContentViewRenderView) {
                if(this.mSurface == null) {
                }
                else {
                    this.nativeSurfaceDestroyed(this.mNativeContentViewRenderView);
                    this.nativeSurfaceCreated(this.mNativeContentViewRenderView);
                    this.nativeSurfaceChanged(this.mNativeContentViewRenderView, 1, this.mNWidth, this.mNHeight, this.mSurface);
                    goto label_21;
                }
            }

            goto label_23;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

    label_21:
        __monitor_exit(this);
        return;
    label_23:
        __monitor_exit(this);
    }

    public void triggerDestroyed() {
        Log.d("ContentViewRenderView", "triggerDestroyed ");
    }
}

