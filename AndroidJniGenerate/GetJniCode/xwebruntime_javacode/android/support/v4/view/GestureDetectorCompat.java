package android.support.v4.view;

import android.content.Context;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class GestureDetectorCompat {
    interface GestureDetectorCompatImpl {
        boolean isLongpressEnabled();

        boolean onTouchEvent(MotionEvent arg1);

        void setIsLongpressEnabled(boolean arg1);

        void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener arg1);
    }

    class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
        class GestureHandler extends Handler {
            GestureHandler(GestureDetectorCompatImplBase arg1, Handler arg2) {
                GestureDetectorCompatImplBase.this = arg1;
                super(arg2.getLooper());
            }

            GestureHandler(GestureDetectorCompatImplBase arg1) {
                GestureDetectorCompatImplBase.this = arg1;
                super();
            }

            public void handleMessage(Message arg4) {
                switch(arg4.what) {
                    case 1: {
                        GestureDetectorCompatImplBase.this.mListener.onShowPress(GestureDetectorCompatImplBase.this.mCurrentDownEvent);
                        break;
                    }
                    case 2: {
                        GestureDetectorCompatImplBase.this.dispatchLongPress();
                        break;
                    }
                    case 3: {
                        if(GestureDetectorCompatImplBase.this.mDoubleTapListener == null) {
                            return;
                        }

                        if(!GestureDetectorCompatImplBase.this.mStillDown) {
                            GestureDetectorCompatImplBase.this.mDoubleTapListener.onSingleTapConfirmed(GestureDetectorCompatImplBase.this.mCurrentDownEvent);
                            return;
                        }

                        GestureDetectorCompatImplBase.this.mDeferConfirmSingleTap = true;
                        break;
                    }
                    default: {
                        StringBuilder v1 = new StringBuilder();
                        v1.append("Unknown message ");
                        v1.append(arg4);
                        throw new RuntimeException(v1.toString());
                    }
                }
            }
        }

        private static final int DOUBLE_TAP_TIMEOUT = 0;
        private static final int LONGPRESS_TIMEOUT = 0;
        private static final int LONG_PRESS = 2;
        private static final int SHOW_PRESS = 1;
        private static final int TAP = 3;
        private static final int TAP_TIMEOUT;
        private boolean mAlwaysInBiggerTapRegion;
        private boolean mAlwaysInTapRegion;
        MotionEvent mCurrentDownEvent;
        boolean mDeferConfirmSingleTap;
        GestureDetector$OnDoubleTapListener mDoubleTapListener;
        private int mDoubleTapSlopSquare;
        private float mDownFocusX;
        private float mDownFocusY;
        private final Handler mHandler;
        private boolean mInLongPress;
        private boolean mIsDoubleTapping;
        private boolean mIsLongpressEnabled;
        private float mLastFocusX;
        private float mLastFocusY;
        final GestureDetector$OnGestureListener mListener;
        private int mMaximumFlingVelocity;
        private int mMinimumFlingVelocity;
        private MotionEvent mPreviousUpEvent;
        boolean mStillDown;
        private int mTouchSlopSquare;
        private VelocityTracker mVelocityTracker;

        static {
            GestureDetectorCompatImplBase.LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
            GestureDetectorCompatImplBase.TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
            GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
        }

        public GestureDetectorCompatImplBase(Context arg2, GestureDetector$OnGestureListener arg3, Handler arg4) {
            super();
            this.mHandler = arg4 != null ? new GestureHandler(this, arg4) : new GestureHandler(this);
            this.mListener = arg3;
            if((arg3 instanceof GestureDetector$OnDoubleTapListener)) {
                this.setOnDoubleTapListener(((GestureDetector$OnDoubleTapListener)arg3));
            }

            this.init(arg2);
        }

        private void cancel() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            this.mIsDoubleTapping = false;
            this.mStillDown = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if(this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        private void cancelTaps() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mIsDoubleTapping = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if(this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        void dispatchLongPress() {
            this.mHandler.removeMessages(3);
            this.mDeferConfirmSingleTap = false;
            this.mInLongPress = true;
            this.mListener.onLongPress(this.mCurrentDownEvent);
        }

        private void init(Context arg4) {
            if(arg4 == null) {
                throw new IllegalArgumentException("Context must not be null");
            }

            if(this.mListener == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }

            this.mIsLongpressEnabled = true;
            ViewConfiguration v4 = ViewConfiguration.get(arg4);
            int v0 = v4.getScaledTouchSlop();
            int v1 = v4.getScaledDoubleTapSlop();
            this.mMinimumFlingVelocity = v4.getScaledMinimumFlingVelocity();
            this.mMaximumFlingVelocity = v4.getScaledMaximumFlingVelocity();
            this.mTouchSlopSquare = v0 * v0;
            this.mDoubleTapSlopSquare = v1 * v1;
        }

        private boolean isConsideredDoubleTap(MotionEvent arg9, MotionEvent arg10, MotionEvent arg11) {
            boolean v1 = false;
            if(!this.mAlwaysInBiggerTapRegion) {
                return 0;
            }

            if(arg11.getEventTime() - arg10.getEventTime() > (((long)GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT))) {
                return 0;
            }

            int v10 = (((int)arg9.getX())) - (((int)arg11.getX()));
            int v9 = (((int)arg9.getY())) - (((int)arg11.getY()));
            if(v10 * v10 + v9 * v9 < this.mDoubleTapSlopSquare) {
                v1 = true;
            }

            return v1;
        }

        public boolean isLongpressEnabled() {
            return this.mIsLongpressEnabled;
        }

        public boolean onTouchEvent(MotionEvent arg12) {
            boolean v12;
            int v6;
            int v3_1;
            float v5_2;
            int v12_1;
            int v0 = arg12.getAction();
            if(this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }

            this.mVelocityTracker.addMovement(arg12);
            v0 &= 0xFF;
            boolean v3 = false;
            int v1 = v0 == 6 ? 1 : 0;
            int v4 = v1 != 0 ? arg12.getActionIndex() : -1;
            int v5 = arg12.getPointerCount();
            int v7 = 0;
            float v8 = 0f;
            float v9 = 0f;
            while(v7 < v5) {
                if(v4 == v7) {
                }
                else {
                    v8 += arg12.getX(v7);
                    v9 += arg12.getY(v7);
                }

                ++v7;
            }

            v1 = v1 != 0 ? v5 - 1 : v5;
            float v1_1 = ((float)v1);
            v8 /= v1_1;
            v9 /= v1_1;
            v1 = 1000;
            v4 = 2;
            v7 = 3;
            switch(v0) {
                case 0: {
                    if(this.mDoubleTapListener != null) {
                        boolean v0_3 = this.mHandler.hasMessages(v7);
                        if(v0_3) {
                            this.mHandler.removeMessages(v7);
                        }

                        if(this.mCurrentDownEvent != null && this.mPreviousUpEvent != null && (v0_3) && (this.isConsideredDoubleTap(this.mCurrentDownEvent, this.mPreviousUpEvent, arg12))) {
                            this.mIsDoubleTapping = true;
                            v0 = this.mDoubleTapListener.onDoubleTap(this.mCurrentDownEvent) | 0 | this.mDoubleTapListener.onDoubleTapEvent(arg12);
                            goto label_236;
                        }

                        this.mHandler.sendEmptyMessageDelayed(v7, ((long)GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT));
                        goto label_235;
                    }
                    else {
                    label_235:
                        v0 = 0;
                    }

                label_236:
                    this.mLastFocusX = v8;
                    this.mDownFocusX = v8;
                    this.mLastFocusY = v9;
                    this.mDownFocusY = v9;
                    if(this.mCurrentDownEvent != null) {
                        this.mCurrentDownEvent.recycle();
                    }

                    this.mCurrentDownEvent = MotionEvent.obtain(arg12);
                    this.mAlwaysInTapRegion = true;
                    this.mAlwaysInBiggerTapRegion = true;
                    this.mStillDown = true;
                    this.mInLongPress = false;
                    this.mDeferConfirmSingleTap = false;
                    if(this.mIsLongpressEnabled) {
                        this.mHandler.removeMessages(v4);
                        this.mHandler.sendEmptyMessageAtTime(v4, this.mCurrentDownEvent.getDownTime() + (((long)GestureDetectorCompatImplBase.TAP_TIMEOUT)) + (((long)GestureDetectorCompatImplBase.LONGPRESS_TIMEOUT)));
                    }

                    this.mHandler.sendEmptyMessageAtTime(1, this.mCurrentDownEvent.getDownTime() + (((long)GestureDetectorCompatImplBase.TAP_TIMEOUT)));
                    v3_1 = v0 | this.mListener.onDown(arg12);
                    return v3;
                label_60:
                    while(v4 < v5) {
                        if(v4 == v0) {
                        }
                        else {
                            v7 = arg12.getPointerId(v4);
                            if(this.mVelocityTracker.getXVelocity(v7) * v2 + this.mVelocityTracker.getYVelocity(v7) * v1_1 < 0f) {
                                this.mVelocityTracker.clear();
                                return v3;
                            }
                        }

                        ++v4;
                    }
                }
                case 1: {
                    this.mStillDown = false;
                    MotionEvent v0_2 = MotionEvent.obtain(arg12);
                    if(this.mIsDoubleTapping) {
                        v12_1 = this.mDoubleTapListener.onDoubleTapEvent(arg12) | 0;
                    }
                    else {
                        if(this.mInLongPress) {
                            this.mHandler.removeMessages(v7);
                            this.mInLongPress = false;
                        }
                        else if(this.mAlwaysInTapRegion) {
                            boolean v1_2 = this.mListener.onSingleTapUp(arg12);
                            if((this.mDeferConfirmSingleTap) && this.mDoubleTapListener != null) {
                                this.mDoubleTapListener.onSingleTapConfirmed(arg12);
                            }

                            v12 = v1_2;
                            goto label_187;
                        }
                        else {
                            VelocityTracker v5_1 = this.mVelocityTracker;
                            v6 = arg12.getPointerId(0);
                            v5_1.computeCurrentVelocity(v1, ((float)this.mMaximumFlingVelocity));
                            v1_1 = v5_1.getYVelocity(v6);
                            v5_2 = v5_1.getXVelocity(v6);
                            if(Math.abs(v1_1) <= (((float)this.mMinimumFlingVelocity))) {
                                if(Math.abs(v5_2) > (((float)this.mMinimumFlingVelocity))) {
                                }
                                else {
                                    goto label_182;
                                }
                            }

                            goto label_184;
                        }

                    label_182:
                        v12 = false;
                        goto label_187;
                    label_184:
                        v12 = this.mListener.onFling(this.mCurrentDownEvent, arg12, v5_2, v1_1);
                    }

                label_187:
                    if(this.mPreviousUpEvent != null) {
                        this.mPreviousUpEvent.recycle();
                    }

                    this.mPreviousUpEvent = v0_2;
                    if(this.mVelocityTracker != null) {
                        this.mVelocityTracker.recycle();
                        this.mVelocityTracker = null;
                    }

                    this.mIsDoubleTapping = false;
                    this.mDeferConfirmSingleTap = false;
                    this.mHandler.removeMessages(1);
                    this.mHandler.removeMessages(v4);
                label_204:
                    v3 = ((boolean)v12_1);
                    break;
                }
                case 2: {
                    if(this.mInLongPress) {
                        return v3;
                    }

                    float v0_1 = this.mLastFocusX - v8;
                    v1_1 = this.mLastFocusY - v9;
                    if(this.mIsDoubleTapping) {
                        v3_1 = 0 | this.mDoubleTapListener.onDoubleTapEvent(arg12);
                        return v3;
                    }

                    if(this.mAlwaysInTapRegion) {
                        v5 = ((int)(v8 - this.mDownFocusX));
                        v6 = ((int)(v9 - this.mDownFocusY));
                        v5 = v5 * v5 + v6 * v6;
                        if(v5 > this.mTouchSlopSquare) {
                            v12 = this.mListener.onScroll(this.mCurrentDownEvent, arg12, v0_1, v1_1);
                            this.mLastFocusX = v8;
                            this.mLastFocusY = v9;
                            this.mAlwaysInTapRegion = false;
                            this.mHandler.removeMessages(v7);
                            this.mHandler.removeMessages(1);
                            this.mHandler.removeMessages(v4);
                        }
                        else {
                            v12 = false;
                        }

                        if(v5 <= this.mTouchSlopSquare) {
                            goto label_204;
                        }

                        this.mAlwaysInBiggerTapRegion = false;
                        goto label_204;
                    }

                    float v4_1 = 1f;
                    if(Math.abs(v0_1) < v4_1 && Math.abs(v1_1) < v4_1) {
                        return v3;
                    }

                    v3 = this.mListener.onScroll(this.mCurrentDownEvent, arg12, v0_1, v1_1);
                    this.mLastFocusX = v8;
                    this.mLastFocusY = v9;
                    break;
                }
                case 3: {
                    this.cancel();
                    break;
                }
                case 5: {
                    this.mLastFocusX = v8;
                    this.mDownFocusX = v8;
                    this.mLastFocusY = v9;
                    this.mDownFocusY = v9;
                    this.cancelTaps();
                    break;
                }
                case 6: {
                    this.mLastFocusX = v8;
                    this.mDownFocusX = v8;
                    this.mLastFocusY = v9;
                    this.mDownFocusY = v9;
                    this.mVelocityTracker.computeCurrentVelocity(v1, ((float)this.mMaximumFlingVelocity));
                    v0 = arg12.getActionIndex();
                    v1 = arg12.getPointerId(v0);
                    float v2 = this.mVelocityTracker.getXVelocity(v1);
                    v1_1 = this.mVelocityTracker.getYVelocity(v1);
                    v4 = 0;
                    goto label_60;
                }
                default: {
                    break;
                }
            }

            return v3;
        }

        public void setIsLongpressEnabled(boolean arg1) {
            this.mIsLongpressEnabled = arg1;
        }

        public void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener arg1) {
            this.mDoubleTapListener = arg1;
        }
    }

    class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
        private final GestureDetector mDetector;

        public GestureDetectorCompatImplJellybeanMr2(Context arg2, GestureDetector$OnGestureListener arg3, Handler arg4) {
            super();
            this.mDetector = new GestureDetector(arg2, arg3, arg4);
        }

        public boolean isLongpressEnabled() {
            return this.mDetector.isLongpressEnabled();
        }

        public boolean onTouchEvent(MotionEvent arg2) {
            return this.mDetector.onTouchEvent(arg2);
        }

        public void setIsLongpressEnabled(boolean arg2) {
            this.mDetector.setIsLongpressEnabled(arg2);
        }

        public void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener arg2) {
            this.mDetector.setOnDoubleTapListener(arg2);
        }
    }

    private final GestureDetectorCompatImpl mImpl;

    public GestureDetectorCompat(Context arg2, GestureDetector$OnGestureListener arg3) {
        this(arg2, arg3, null);
    }

    public GestureDetectorCompat(Context arg3, GestureDetector$OnGestureListener arg4, Handler arg5) {
        super();
        this.mImpl = Build$VERSION.SDK_INT > 17 ? new GestureDetectorCompatImplJellybeanMr2(arg3, arg4, arg5) : new GestureDetectorCompatImplBase(arg3, arg4, arg5);
    }

    public boolean isLongpressEnabled() {
        return this.mImpl.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        return this.mImpl.onTouchEvent(arg2);
    }

    public void setIsLongpressEnabled(boolean arg2) {
        this.mImpl.setIsLongpressEnabled(arg2);
    }

    public void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener arg2) {
        this.mImpl.setOnDoubleTapListener(arg2);
    }
}

