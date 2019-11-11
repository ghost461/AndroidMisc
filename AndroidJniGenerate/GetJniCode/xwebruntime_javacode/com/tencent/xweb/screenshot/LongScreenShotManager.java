package com.tencent.xweb.screenshot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xwalk.core.internal.Log;
import org.xwalk.core.internal.XWalkGetBitmapCallbackInternal;
import org.xwalk.core.internal.XWalkLongScreenshotCallbackInternal;
import org.xwalk.core.internal.XWalkViewBridge;

public class LongScreenShotManager implements Handler$Callback {
    class com.tencent.xweb.screenshot.LongScreenShotManager$1 extends Handler {
        com.tencent.xweb.screenshot.LongScreenShotManager$1(LongScreenShotManager arg1, Looper arg2) {
            LongScreenShotManager.this = arg1;
            super(arg2);
        }

        public void handleMessage(Message arg6) {
            String v2 = null;
            switch(arg6.what) {
                case 21: {
                    LongScreenShotManager.this.doStartLongScreenshot(arg6);
                    break;
                }
                case 22: {
                    LongScreenShotManager.this.mScreenshotCount = LongScreenShotManager.this.mScreenshotList.size() + 1;
                    Log.d("LongScreenShotManager", "MSG_ADD_ANOTHER_SCREENSHOT_AND_ANIMATE with count: " + LongScreenShotManager.this.mScreenshotCount);
                    LongScreenShotManager.this.mScreenshotList.add(arg6.obj);
                    LongScreenShotManager.this.mDisplayView.animateToLast();
                    LongScreenShotManager.this.cleanUnusedBitmap();
                    break;
                }
                case 23: {
                    if(LongScreenShotManager.this.mMainScrollView.canScrollVertically(1)) {
                        Log.d("LongScreenShotManager", "MSG_SCROLL_CONTENT scrollBy");
                        LongScreenShotManager.this.mMainScrollView.scrollBy(0, arg6.arg1);
                        LongScreenShotManager.this.postMessage(24, v2, 200);
                        return;
                    }

                    LongScreenShotManager.this.postMessage(25);
                    break;
                }
                case 24: {
                    Log.d("LongScreenShotManager", "MSG_DO_SCREENSHOT");
                    LongScreenShotManager.this.mMainScrollView.captureBitmapAsync(LongScreenShotManager.this.mInternalCallbackToGetBitmap);
                    break;
                }
                case 25: {
                    LongScreenShotManager.this.postMessage(2, new ArrayList(LongScreenShotManager.this.mScreenshotList));
                    break;
                }
                case 26: {
                    LongScreenShotManager.this.removeCoverView();
                    LongScreenShotManager.this.mIsTaking = false;
                    LongScreenShotManager.this.mCallback.onLongScreenshotFinished(0, arg6.obj);
                    break;
                }
                case 27: {
                    LongScreenShotManager.this.mIsTaking = false;
                    LongScreenShotManager.this.mCallback.onLongScreenshotFinished(arg6.arg1, v2);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    class com.tencent.xweb.screenshot.LongScreenShotManager$2 extends XWalkGetBitmapCallbackInternal {
        com.tencent.xweb.screenshot.LongScreenShotManager$2(LongScreenShotManager arg1) {
            LongScreenShotManager.this = arg1;
            super();
        }

        public void onFinishGetBitmap(Bitmap arg4, int arg5) {
            Log.d("LongScreenShotManager", "onFinishGetBitmap with bitmap " + arg4 + " response " + arg5);
            if(arg4 == null) {
                LongScreenShotManager.this.postMessage(27, 1, 0);
            }
            else {
                LongScreenShotManager.this.postMessage(1, arg4);
            }
        }
    }

    class Holder {
        @SuppressLint(value={"StaticFieldLeak"}) private static final LongScreenShotManager INSTANCE;

        static {
            Holder.INSTANCE = new LongScreenShotManager(null);
        }

        private Holder() {
            super();
        }

        static LongScreenShotManager access$1400() {
            return Holder.INSTANCE;
        }
    }

    private static final int ERROR_GET_SCREENSHOT_FAIL = 1;
    private static final int ERROR_SAVE_FILE_FAIL = 2;
    private static final int MSG_ADD_ANOTHER_SCREENSHOT_AND_ANIMATE = 22;
    private static final int MSG_DO_SCREENSHOT = 24;
    private static final int MSG_ERROR_HAPPENED = 27;
    private static final int MSG_FINAL_LONG_SHOT_GENERATED = 26;
    private static final int MSG_IN_UI_THREAD_START = 20;
    private static final int MSG_NOTIFY_UI_ALL_SCREENSHOT_GET = 25;
    private static final int MSG_NOTIFY_WORKER_FINISHING = 2;
    private static final int MSG_ONE_SCREENSHOT_GET = 1;
    private static final int MSG_SCROLL_CONTENT = 23;
    private static final int MSG_START_LONG_SCREENSHOT = 21;
    private static final String TAG = "LongScreenShotManager";
    private XWalkLongScreenshotCallbackInternal mCallback;
    private Context mContext;
    private LongScreenshotView mDisplayView;
    private XWalkGetBitmapCallbackInternal mInternalCallbackToGetBitmap;
    private boolean mIsTaking;
    private Handler mMainHandler;
    private XWalkViewBridge mMainScrollView;
    private Paint mPaint;
    private volatile int mPlanToScrollDistanceNextOne;
    private int mPreviousTotalScrolled;
    private volatile int mScreenshotCount;
    private List mScreenshotList;
    private Handler mWorkHandler;
    private HandlerThread mWorkThread;

    private LongScreenShotManager() {
        super();
        this.mMainHandler = new com.tencent.xweb.screenshot.LongScreenShotManager$1(this, Looper.getMainLooper());
        this.mInternalCallbackToGetBitmap = new com.tencent.xweb.screenshot.LongScreenShotManager$2(this);
        this.mWorkThread = new HandlerThread("LongScreenShotWorkThread");
        this.mWorkThread.start();
        this.mWorkHandler = new Handler(this.mWorkThread.getLooper(), ((Handler$Callback)this));
        this.mScreenshotList = new ArrayList(20);
        this.mPaint = new Paint();
    }

    LongScreenShotManager(com.tencent.xweb.screenshot.LongScreenShotManager$1 arg1) {
        this();
    }

    static void access$000(LongScreenShotManager arg0, Message arg1) {
        arg0.doStartLongScreenshot(arg1);
    }

    static XWalkGetBitmapCallbackInternal access$100(LongScreenShotManager arg0) {
        return arg0.mInternalCallbackToGetBitmap;
    }

    static void access$1000(LongScreenShotManager arg0) {
        arg0.removeCoverView();
    }

    static boolean access$1102(LongScreenShotManager arg0, boolean arg1) {
        arg0.mIsTaking = arg1;
        return arg1;
    }

    static XWalkLongScreenshotCallbackInternal access$1200(LongScreenShotManager arg0) {
        return arg0.mCallback;
    }

    static void access$1300(LongScreenShotManager arg0, int arg1, int arg2, int arg3) {
        arg0.postMessage(arg1, arg2, arg3);
    }

    static XWalkViewBridge access$200(LongScreenShotManager arg0) {
        return arg0.mMainScrollView;
    }

    static int access$300(LongScreenShotManager arg0) {
        return arg0.mScreenshotCount;
    }

    static int access$302(LongScreenShotManager arg0, int arg1) {
        arg0.mScreenshotCount = arg1;
        return arg1;
    }

    static List access$400(LongScreenShotManager arg0) {
        return arg0.mScreenshotList;
    }

    static LongScreenshotView access$500(LongScreenShotManager arg0) {
        return arg0.mDisplayView;
    }

    static void access$600(LongScreenShotManager arg0) {
        arg0.cleanUnusedBitmap();
    }

    static void access$700(LongScreenShotManager arg0, int arg1, Object arg2, long arg3) {
        arg0.postMessage(arg1, arg2, arg3);
    }

    static void access$800(LongScreenShotManager arg0, int arg1) {
        arg0.postMessage(arg1);
    }

    static void access$900(LongScreenShotManager arg0, int arg1, Object arg2) {
        arg0.postMessage(arg1, arg2);
    }

    private void cleanUnusedBitmap() {
        Iterator v0 = this.mScreenshotList.iterator();
        while(v0.hasNext()) {
            v0.next().recycleIfNeeded();
        }
    }

    private Bitmap cropBitmap(Bitmap arg7, int arg8) {
        if(arg8 != arg7.getHeight()) {
            if(arg8 == 0) {
            }
            else {
                Bitmap v0 = Bitmap.createBitmap(arg7.getWidth(), arg8, Bitmap$Config.ARGB_8888);
                new Canvas(v0).drawBitmap(arg7, new Rect(0, arg7.getHeight() - arg8, arg7.getWidth(), arg7.getHeight()), new Rect(0, 0, v0.getWidth(), v0.getHeight()), this.mPaint);
                return v0;
            }
        }

        return arg7;
    }

    private void doFinalWork(List arg2) {
        this.postMessage(26, null);
    }

    private void doStartLongScreenshot(Message arg6) {
        if(this.mIsTaking) {
            throw new IllegalStateException("try to start long screenshot before last one is finished.");
        }

        this.mIsTaking = true;
        this.mScreenshotList.clear();
        this.mScreenshotCount = 0;
        Object v1 = arg6.obj;
        if(this.prepareView(((Pair)v1).first)) {
            this.mCallback = ((Pair)v1).second;
            v1 = null;
            int v2 = 24;
            if(arg6.arg1 != 0) {
                Log.d("LongScreenShotManager", "scroll to begin and start screenshot.");
                this.mMainScrollView.scrollTo(0, 0);
                this.postMessage(v2, v1, 200);
            }
            else {
                this.postMessage(v2, v1);
            }
        }
    }

    private File getFinalScreenshotDir() {
        return this.mCallback.getResultFileDir();
    }

    public static LongScreenShotManager getInstance() {
        return Holder.access$1400();
    }

    public boolean handleMessage(Message arg2) {
        switch(arg2.what) {
            case 1: {
                this.handleScreenshot(arg2.obj);
                break;
            }
            case 2: {
                this.doFinalWork(arg2.obj);
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }

    private void handleScreenshot(@NonNull Bitmap arg10) {
        int v1 = 0;
        int v0 = this.mScreenshotCount == 0 ? 1 : 0;
        int v2 = this.mMainScrollView.computeVerticalScrollOffset();
        int v3 = this.mMainScrollView.computeVerticalScrollExtent();
        int v4 = this.mMainScrollView.computeVerticalScrollRange() - v3;
        arg10 = this.mCallback.overrideScreenshot(arg10);
        v0 = v0 == 0 ? v2 - this.mPreviousTotalScrolled : arg10.getHeight();
        v3 = Math.min(v3 * 4 / 5, v4 - v2);
        Log.d("LongScreenShotManager", "handleScreenshot with scrolled: " + v0 + " / " + v2 + " range: " + v4 + " and plan to scroll: " + v3);
        this.mPlanToScrollDistanceNextOne = v3;
        this.mPreviousTotalScrolled = v2;
        if(v0 > 0) {
            arg10 = this.cropBitmap(arg10, v0);
            String v4_1 = this.saveBitmapPerScreenshot(arg10);
            if(v4_1 == null) {
                this.postMessage(27, 2, 0);
                return;
            }
            else {
                PlaceHolderBitmap v5 = new PlaceHolderBitmap(arg10, v2, v4_1);
                v5.markCanBeRecycled();
                this.postMessage(22, v5);
            }
        }

        if(this.mCallback.getMaxHeightSupported() == 0 || v2 <= this.mCallback.getMaxHeightSupported()) {
            v1 = v3;
        }
        else {
            Log.d("LongScreenShotManager", "reach max height supported, force set planToScroll to 0");
        }

        if(v0 == 0 || v1 == 0) {
            Log.w("LongScreenShotManager", "detect useless scrolled or planToScroll, direct go to finish");
            this.postMessage(25);
        }
        else {
            this.postMessage(23, this.mPlanToScrollDistanceNextOne, 0, null, 100);
        }
    }

    private void postMessage(int arg8, int arg9, int arg10) {
        this.postMessage(arg8, arg9, arg10, null, 0);
    }

    private void postMessage(int arg8, Object arg9, long arg10) {
        this.postMessage(arg8, 0, 0, arg9, arg10);
    }

    private void postMessage(int arg2) {
        this.postMessage(arg2, null);
    }

    private void postMessage(int arg3, Object arg4) {
        this.postMessage(arg3, arg4, 0);
    }

    private void postMessage(int arg2, int arg3, int arg4, Object arg5, long arg6) {
        Handler v0 = arg2 < 20 ? this.mWorkHandler : this.mMainHandler;
        v0.sendMessageDelayed(v0.obtainMessage(arg2, arg3, arg4, arg5), arg6);
    }

    private boolean prepareView(View arg4) {
        if((arg4 instanceof XWalkViewBridge)) {
            this.mMainScrollView = arg4;
            this.mContext = arg4.getContext().getApplicationContext();
            this.mDisplayView = new LongScreenshotView(this.mContext);
            ViewGroup v4 = arg4.getXWalkContentView();
            if((v4 instanceof FrameLayout)) {
                ((FrameLayout)v4).addView(this.mDisplayView, new FrameLayout$LayoutParams(-1, -1));
                return 1;
            }

            Log.e("LongScreenShotManager", "failed to prepare view due to the content view is incorrect");
            return 0;
        }

        return 0;
    }

    private void removeCoverView() {
        if(this.mDisplayView == null || this.mDisplayView.getParent() == null) {
            Log.e("LongScreenShotManager", "try to remove cover view but it is not exist");
        }
        else {
            this.mDisplayView.getParent().removeView(this.mDisplayView);
            this.mDisplayView = null;
            Log.d("LongScreenShotManager", "cover view is removed");
        }
    }

    @Nullable private String saveBitmapPerScreenshot(Bitmap arg6) {
        File v0 = this.mCallback.getCacheFileDir();
        StringBuilder v2 = new StringBuilder();
        v2.append(System.currentTimeMillis());
        v2.append(".png");
        File v1 = new File(v0, v2.toString());
        try {
            v0.mkdirs();
            FileOutputStream v0_1 = new FileOutputStream(v1);
            arg6.compress(Bitmap$CompressFormat.PNG, 0, ((OutputStream)v0_1));
            v0_1.flush();
            v0_1.close();
            Log.d("LongScreenShotManager", "screenshot file is saved to " + v1.getAbsolutePath());
            return v1.getAbsolutePath();
        }
        catch(Exception v6) {
            ThrowableExtension.printStackTrace(((Throwable)v6));
            return null;
        }
    }

    public static void startLongScreenShot(View arg7, boolean arg8, Object arg9) {
        LongScreenShotManager.getInstance().postMessage(21, arg8, 0, new Pair(arg7, arg9), 0);
    }
}

