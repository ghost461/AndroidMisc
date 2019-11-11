package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;

public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    class android.support.v4.widget.SwipeRefreshLayout$1 implements Animation$AnimationListener {
        android.support.v4.widget.SwipeRefreshLayout$1(SwipeRefreshLayout arg1) {
            SwipeRefreshLayout.this = arg1;
            super();
        }

        public void onAnimationEnd(Animation arg2) {
            if(SwipeRefreshLayout.this.mRefreshing) {
                SwipeRefreshLayout.this.mProgress.setAlpha(0xFF);
                SwipeRefreshLayout.this.mProgress.start();
                if((SwipeRefreshLayout.this.mNotify) && SwipeRefreshLayout.this.mListener != null) {
                    SwipeRefreshLayout.this.mListener.onRefresh();
                }

                SwipeRefreshLayout.this.mCurrentTargetOffsetTop = SwipeRefreshLayout.this.mCircleView.getTop();
            }
            else {
                SwipeRefreshLayout.this.reset();
            }
        }

        public void onAnimationRepeat(Animation arg1) {
        }

        public void onAnimationStart(Animation arg1) {
        }
    }

    class android.support.v4.widget.SwipeRefreshLayout$6 extends Animation {
        android.support.v4.widget.SwipeRefreshLayout$6(SwipeRefreshLayout arg1) {
            SwipeRefreshLayout.this = arg1;
            super();
        }

        public void applyTransformation(float arg3, Transformation arg4) {
            int v4 = !SwipeRefreshLayout.this.mUsingCustomStart ? SwipeRefreshLayout.this.mSpinnerOffsetEnd - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop) : SwipeRefreshLayout.this.mSpinnerOffsetEnd;
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SwipeRefreshLayout.this.mFrom + (((int)((((float)(v4 - SwipeRefreshLayout.this.mFrom))) * arg3))) - SwipeRefreshLayout.this.mCircleView.getTop());
            SwipeRefreshLayout.this.mProgress.setArrowScale(1f - arg3);
        }
    }

    class android.support.v4.widget.SwipeRefreshLayout$7 extends Animation {
        android.support.v4.widget.SwipeRefreshLayout$7(SwipeRefreshLayout arg1) {
            SwipeRefreshLayout.this = arg1;
            super();
        }

        public void applyTransformation(float arg1, Transformation arg2) {
            SwipeRefreshLayout.this.moveToStart(arg1);
        }
    }

    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(SwipeRefreshLayout arg1, @Nullable View arg2);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    @VisibleForTesting static final int CIRCLE_DIAMETER = 40;
    @VisibleForTesting static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 0x40;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS = null;
    private static final String LOG_TAG = "SwipeRefreshLayout";
    private static final int MAX_ALPHA = 0xFF;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private OnChildScrollUpCallback mChildScrollUpCallback;
    private int mCircleDiameter;
    CircleImageView mCircleView;
    private int mCircleViewIndex;
    int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    boolean mNotify;
    protected int mOriginalOffsetTop;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    CircularProgressDrawable mProgress;
    private Animation$AnimationListener mRefreshListener;
    boolean mRefreshing;
    private boolean mReturningToStart;
    boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    int mSpinnerOffsetEnd;
    float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    boolean mUsingCustomStart;

    static {
        SwipeRefreshLayout.LAYOUT_ATTRS = new int[]{0x101000E};
    }

    public SwipeRefreshLayout(Context arg2) {
        this(arg2, null);
    }

    public SwipeRefreshLayout(Context arg5, AttributeSet arg6) {
        super(arg5, arg6);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1f;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mActivePointerId = -1;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = new android.support.v4.widget.SwipeRefreshLayout$1(this);
        this.mAnimateToCorrectPosition = new android.support.v4.widget.SwipeRefreshLayout$6(this);
        this.mAnimateToStartPosition = new android.support.v4.widget.SwipeRefreshLayout$7(this);
        this.mTouchSlop = ViewConfiguration.get(arg5).getScaledTouchSlop();
        this.mMediumAnimationDuration = this.getResources().getInteger(0x10E0001);
        this.setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2f);
        DisplayMetrics v1 = this.getResources().getDisplayMetrics();
        this.mCircleDiameter = ((int)(v1.density * 40f));
        this.createProgressView();
        ViewCompat.setChildrenDrawingOrderEnabled(((ViewGroup)this), true);
        this.mSpinnerOffsetEnd = ((int)(v1.density * 64f));
        this.mTotalDragDistance = ((float)this.mSpinnerOffsetEnd);
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(((ViewGroup)this));
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(((View)this));
        this.setNestedScrollingEnabled(true);
        int v1_1 = -this.mCircleDiameter;
        this.mCurrentTargetOffsetTop = v1_1;
        this.mOriginalOffsetTop = v1_1;
        this.moveToStart(1f);
        TypedArray v5 = arg5.obtainStyledAttributes(arg6, SwipeRefreshLayout.LAYOUT_ATTRS);
        this.setEnabled(v5.getBoolean(0, true));
        v5.recycle();
    }

    private void animateOffsetToCorrectPosition(int arg3, Animation$AnimationListener arg4) {
        this.mFrom = arg3;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if(arg4 != null) {
            this.mCircleView.setAnimationListener(arg4);
        }

        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int arg3, Animation$AnimationListener arg4) {
        if(this.mScale) {
            this.startScaleDownReturnToStartAnimation(arg3, arg4);
        }
        else {
            this.mFrom = arg3;
            this.mAnimateToStartPosition.reset();
            this.mAnimateToStartPosition.setDuration(200);
            this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
            if(arg4 != null) {
                this.mCircleView.setAnimationListener(arg4);
            }

            this.mCircleView.clearAnimation();
            this.mCircleView.startAnimation(this.mAnimateToStartPosition);
        }
    }

    public boolean canChildScrollUp() {
        if(this.mChildScrollUpCallback != null) {
            return this.mChildScrollUpCallback.canChildScrollUp(this, this.mTarget);
        }

        int v1 = -1;
        if((this.mTarget instanceof ListView)) {
            return ListViewCompat.canScrollList(this.mTarget, v1);
        }

        return this.mTarget.canScrollVertically(v1);
    }

    private void createProgressView() {
        this.mCircleView = new CircleImageView(this.getContext(), 0xFFFAFAFA);
        this.mProgress = new CircularProgressDrawable(this.getContext());
        this.mProgress.setStyle(1);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        this.addView(this.mCircleView);
    }

    public boolean dispatchNestedFling(float arg2, float arg3, boolean arg4) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(arg2, arg3, arg4);
    }

    public boolean dispatchNestedPreFling(float arg2, float arg3) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(arg2, arg3);
    }

    public boolean dispatchNestedPreScroll(int arg2, int arg3, int[] arg4, int[] arg5) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(arg2, arg3, arg4, arg5);
    }

    public boolean dispatchNestedScroll(int arg7, int arg8, int arg9, int arg10, int[] arg11) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(arg7, arg8, arg9, arg10, arg11);
    }

    private void ensureTarget() {
        if(this.mTarget == null) {
            int v0 = 0;
            while(v0 < this.getChildCount()) {
                View v1 = this.getChildAt(v0);
                if(!v1.equals(this.mCircleView)) {
                    this.mTarget = v1;
                }
                else {
                    ++v0;
                    continue;
                }

                return;
            }
        }
    }

    private void finishSpinner(float arg3) {
        if(arg3 > this.mTotalDragDistance) {
            this.setRefreshing(true, true);
        }
        else {
            this.mRefreshing = false;
            this.mProgress.setStartEndTrim(0f, 0f);
            Animation$AnimationListener v0 = null;
            if(!this.mScale) {
                android.support.v4.widget.SwipeRefreshLayout$5 v0_1 = new Animation$AnimationListener() {
                    public void onAnimationEnd(Animation arg2) {
                        if(!SwipeRefreshLayout.this.mScale) {
                            SwipeRefreshLayout.this.startScaleDownAnimation(null);
                        }
                    }

                    public void onAnimationRepeat(Animation arg1) {
                    }

                    public void onAnimationStart(Animation arg1) {
                    }
                };
            }

            this.animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, v0);
            this.mProgress.setArrowEnabled(false);
        }
    }

    protected int getChildDrawingOrder(int arg2, int arg3) {
        if(this.mCircleViewIndex < 0) {
            return arg3;
        }

        if(arg3 == arg2 - 1) {
            return this.mCircleViewIndex;
        }

        if(arg3 >= this.mCircleViewIndex) {
            return arg3 + 1;
        }

        return arg3;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public int getProgressCircleDiameter() {
        return this.mCircleDiameter;
    }

    public int getProgressViewEndOffset() {
        return this.mSpinnerOffsetEnd;
    }

    public int getProgressViewStartOffset() {
        return this.mOriginalOffsetTop;
    }

    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    private boolean isAnimationRunning(Animation arg2) {
        boolean v2 = arg2 == null || !arg2.hasStarted() || (arg2.hasEnded()) ? false : true;
        return v2;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    private void moveSpinner(float arg12) {
        this.mProgress.setArrowEnabled(true);
        float v1 = 1f;
        float v0 = Math.min(v1, Math.abs(arg12 / this.mTotalDragDistance));
        float v2 = (((float)Math.max((((double)v0)) - 0.4, 0))) * 5f / 3f;
        float v3 = Math.abs(arg12) - this.mTotalDragDistance;
        int v4 = this.mUsingCustomStart ? this.mSpinnerOffsetEnd - this.mOriginalOffsetTop : this.mSpinnerOffsetEnd;
        float v4_1 = ((float)v4);
        float v5 = 2f;
        double v7 = ((double)(Math.max(0f, Math.min(v3, v4_1 * v5) / v4_1) / 4f));
        v3 = (((float)(v7 - Math.pow(v7, 2)))) * v5;
        int v8 = this.mOriginalOffsetTop + (((int)(v4_1 * v0 + v4_1 * v3 * v5)));
        if(this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }

        if(!this.mScale) {
            this.mCircleView.setScaleX(v1);
            this.mCircleView.setScaleY(v1);
        }

        if(this.mScale) {
            this.setAnimationProgress(Math.min(v1, arg12 / this.mTotalDragDistance));
        }

        if(arg12 < this.mTotalDragDistance) {
            if(this.mProgress.getAlpha() > 76 && !this.isAnimationRunning(this.mAlphaStartAnimation)) {
                this.startProgressAlphaStartAnimation();
            }
        }
        else if(this.mProgress.getAlpha() < 0xFF && !this.isAnimationRunning(this.mAlphaMaxAnimation)) {
            this.startProgressAlphaMaxAnimation();
        }

        this.mProgress.setStartEndTrim(0f, Math.min(0.8f, v2 * 0.8f));
        this.mProgress.setArrowScale(Math.min(v1, v2));
        this.mProgress.setProgressRotation((v2 * 0.4f - 0.25f + v3 * v5) * 0.5f);
        this.setTargetOffsetTopAndBottom(v8 - this.mCurrentTargetOffsetTop);
    }

    void moveToStart(float arg4) {
        this.setTargetOffsetTopAndBottom(this.mFrom + (((int)((((float)(this.mOriginalOffsetTop - this.mFrom))) * arg4))) - this.mCircleView.getTop());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.reset();
    }

    public boolean onInterceptTouchEvent(MotionEvent arg4) {
        this.ensureTarget();
        int v0 = arg4.getActionMasked();
        if((this.mReturningToStart) && v0 == 0) {
            this.mReturningToStart = false;
        }

        if((this.isEnabled()) && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing) {
            if(this.mNestedScrollInProgress) {
            }
            else {
                if(v0 != 6) {
                    int v1 = -1;
                    switch(v0) {
                        case 0: {
                            goto label_39;
                        }
                        case 2: {
                            goto label_23;
                        }
                        case 1: 
                        case 3: {
                            goto label_36;
                        }
                    }

                    goto label_55;
                label_36:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = v1;
                    goto label_55;
                label_39:
                    this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop());
                    this.mActivePointerId = arg4.getPointerId(0);
                    this.mIsBeingDragged = false;
                    v0 = arg4.findPointerIndex(this.mActivePointerId);
                    if(v0 < 0) {
                        return 0;
                    }
                    else {
                        this.mInitialDownY = arg4.getY(v0);
                        goto label_55;
                    label_23:
                        if(this.mActivePointerId == v1) {
                            Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_MOVE event but don\'t have an active pointer id.");
                            return 0;
                        }
                        else {
                            v0 = arg4.findPointerIndex(this.mActivePointerId);
                            if(v0 < 0) {
                                return 0;
                            }
                            else {
                                this.startDragging(arg4.getY(v0));
                            }
                        }
                    }
                }
                else {
                    this.onSecondaryPointerUp(arg4);
                }

            label_55:
                return this.mIsBeingDragged;
            }
        }

        return 0;
    }

    protected void onLayout(boolean arg3, int arg4, int arg5, int arg6, int arg7) {
        int v3 = this.getMeasuredWidth();
        arg4 = this.getMeasuredHeight();
        if(this.getChildCount() == 0) {
            return;
        }

        if(this.mTarget == null) {
            this.ensureTarget();
        }

        if(this.mTarget == null) {
            return;
        }

        View v5 = this.mTarget;
        arg6 = this.getPaddingLeft();
        arg7 = this.getPaddingTop();
        v5.layout(arg6, arg7, v3 - this.getPaddingLeft() - this.getPaddingRight() + arg6, arg4 - this.getPaddingTop() - this.getPaddingBottom() + arg7);
        v3 /= 2;
        arg4 = this.mCircleView.getMeasuredWidth() / 2;
        this.mCircleView.layout(v3 - arg4, this.mCurrentTargetOffsetTop, v3 + arg4, this.mCurrentTargetOffsetTop + this.mCircleView.getMeasuredHeight());
    }

    public void onMeasure(int arg4, int arg5) {
        super.onMeasure(arg4, arg5);
        if(this.mTarget == null) {
            this.ensureTarget();
        }

        if(this.mTarget == null) {
            return;
        }

        this.mTarget.measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), 0x40000000), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), 0x40000000));
        this.mCircleView.measure(View$MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 0x40000000), View$MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 0x40000000));
        this.mCircleViewIndex = -1;
        arg4 = 0;
        while(arg4 < this.getChildCount()) {
            if(this.getChildAt(arg4) == this.mCircleView) {
                this.mCircleViewIndex = arg4;
            }
            else {
                ++arg4;
                continue;
            }

            return;
        }
    }

    public boolean onNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
        return this.dispatchNestedFling(arg2, arg3, arg4);
    }

    public boolean onNestedPreFling(View arg1, float arg2, float arg3) {
        return this.dispatchNestedPreFling(arg2, arg3);
    }

    public void onNestedPreScroll(View arg4, int arg5, int arg6, int[] arg7) {
        if(arg6 > 0 && this.mTotalUnconsumed > 0f) {
            float v1 = ((float)arg6);
            if(v1 > this.mTotalUnconsumed) {
                arg7[1] = arg6 - (((int)this.mTotalUnconsumed));
                this.mTotalUnconsumed = 0f;
            }
            else {
                this.mTotalUnconsumed -= v1;
                arg7[1] = arg6;
            }

            this.moveSpinner(this.mTotalUnconsumed);
        }

        if((this.mUsingCustomStart) && arg6 > 0 && this.mTotalUnconsumed == 0f && Math.abs(arg6 - arg7[1]) > 0) {
            this.mCircleView.setVisibility(8);
        }

        int[] v4 = this.mParentScrollConsumed;
        if(this.dispatchNestedPreScroll(arg5 - arg7[0], arg6 - arg7[1], v4, null)) {
            arg7[0] += v4[0];
            arg7[1] += v4[1];
        }
    }

    public void onNestedScroll(View arg7, int arg8, int arg9, int arg10, int arg11) {
        this.dispatchNestedScroll(arg8, arg9, arg10, arg11, this.mParentOffsetInWindow);
        arg11 += this.mParentOffsetInWindow[1];
        if(arg11 < 0 && !this.canChildScrollUp()) {
            this.mTotalUnconsumed += ((float)Math.abs(arg11));
            this.moveSpinner(this.mTotalUnconsumed);
        }
    }

    public void onNestedScrollAccepted(View arg2, View arg3, int arg4) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(arg2, arg3, arg4);
        this.startNestedScroll(arg4 & 2);
        this.mTotalUnconsumed = 0f;
        this.mNestedScrollInProgress = true;
    }

    private void onSecondaryPointerUp(MotionEvent arg4) {
        int v0 = arg4.getActionIndex();
        if(arg4.getPointerId(v0) == this.mActivePointerId) {
            v0 = v0 == 0 ? 1 : 0;
            this.mActivePointerId = arg4.getPointerId(v0);
        }
    }

    public boolean onStartNestedScroll(View arg1, View arg2, int arg3) {
        boolean v1 = !this.isEnabled() || (this.mReturningToStart) || (this.mRefreshing) || (arg3 & 2) == 0 ? false : true;
        return v1;
    }

    public void onStopNestedScroll(View arg2) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(arg2);
        this.mNestedScrollInProgress = false;
        if(this.mTotalUnconsumed > 0f) {
            this.finishSpinner(this.mTotalUnconsumed);
            this.mTotalUnconsumed = 0f;
        }

        this.stopNestedScroll();
    }

    public boolean onTouchEvent(MotionEvent arg5) {
        float v5;
        int v0 = arg5.getActionMasked();
        if((this.mReturningToStart) && v0 == 0) {
            this.mReturningToStart = false;
        }

        if((this.isEnabled()) && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing) {
            if(this.mNestedScrollInProgress) {
            }
            else {
                float v1 = 0.5f;
                switch(v0) {
                    case 0: {
                        goto label_69;
                    }
                    case 1: {
                        goto label_51;
                    }
                    case 2: {
                        goto label_32;
                    }
                    case 3: {
                        return 0;
                    }
                    case 5: {
                        goto label_22;
                    }
                    case 6: {
                        goto label_20;
                    }
                }

                return 1;
            label_51:
                v0 = arg5.findPointerIndex(this.mActivePointerId);
                if(v0 < 0) {
                    Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_UP event but don\'t have an active pointer id.");
                    return 0;
                }
                else {
                    if(this.mIsBeingDragged) {
                        v5 = (arg5.getY(v0) - this.mInitialMotionY) * v1;
                        this.mIsBeingDragged = false;
                        this.finishSpinner(v5);
                    }

                    this.mActivePointerId = -1;
                    return 0;
                label_20:
                    this.onSecondaryPointerUp(arg5);
                    return 1;
                label_69:
                    this.mActivePointerId = arg5.getPointerId(0);
                    this.mIsBeingDragged = false;
                    return 1;
                label_22:
                    v0 = arg5.getActionIndex();
                    if(v0 < 0) {
                        Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return 0;
                    }

                    this.mActivePointerId = arg5.getPointerId(v0);
                    return 1;
                label_32:
                    v0 = arg5.findPointerIndex(this.mActivePointerId);
                    if(v0 < 0) {
                        Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return 0;
                    }

                    v5 = arg5.getY(v0);
                    this.startDragging(v5);
                    if(this.mIsBeingDragged) {
                        v5 = (v5 - this.mInitialMotionY) * v1;
                        if(v5 > 0f) {
                            this.moveSpinner(v5);
                        }
                        else {
                            return 0;
                        }
                    }

                    return 1;
                }
            }
        }

        return 0;
    }

    public void requestDisallowInterceptTouchEvent(boolean arg3) {
        if((Build$VERSION.SDK_INT >= 21 || !(this.mTarget instanceof AbsListView)) && (this.mTarget == null || (ViewCompat.isNestedScrollingEnabled(this.mTarget)))) {
            super.requestDisallowInterceptTouchEvent(arg3);
        }
    }

    void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.mCircleView.setVisibility(8);
        this.setColorViewAlpha(0xFF);
        if(this.mScale) {
            this.setAnimationProgress(0f);
        }
        else {
            this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop);
        }

        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    void setAnimationProgress(float arg2) {
        this.mCircleView.setScaleX(arg2);
        this.mCircleView.setScaleY(arg2);
    }

    @Deprecated public void setColorScheme(@ColorRes int[] arg1) {
        this.setColorSchemeResources(arg1);
    }

    public void setColorSchemeColors(@ColorInt int[] arg2) {
        this.ensureTarget();
        this.mProgress.setColorSchemeColors(arg2);
    }

    public void setColorSchemeResources(@ColorRes int[] arg5) {
        Context v0 = this.getContext();
        int[] v1 = new int[arg5.length];
        int v2;
        for(v2 = 0; v2 < arg5.length; ++v2) {
            v1[v2] = ContextCompat.getColor(v0, arg5[v2]);
        }

        this.setColorSchemeColors(v1);
    }

    private void setColorViewAlpha(int arg2) {
        this.mCircleView.getBackground().setAlpha(arg2);
        this.mProgress.setAlpha(arg2);
    }

    public void setDistanceToTriggerSync(int arg1) {
        this.mTotalDragDistance = ((float)arg1);
    }

    public void setEnabled(boolean arg1) {
        super.setEnabled(arg1);
        if(!arg1) {
            this.reset();
        }
    }

    public void setNestedScrollingEnabled(boolean arg2) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(arg2);
    }

    public void setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback arg1) {
        this.mChildScrollUpCallback = arg1;
    }

    public void setOnRefreshListener(OnRefreshListener arg1) {
        this.mListener = arg1;
    }

    @Deprecated public void setProgressBackgroundColor(int arg1) {
        this.setProgressBackgroundColorSchemeResource(arg1);
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int arg2) {
        this.mCircleView.setBackgroundColor(arg2);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int arg2) {
        this.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this.getContext(), arg2));
    }

    public void setProgressViewEndTarget(boolean arg1, int arg2) {
        this.mSpinnerOffsetEnd = arg2;
        this.mScale = arg1;
        this.mCircleView.invalidate();
    }

    public void setProgressViewOffset(boolean arg1, int arg2, int arg3) {
        this.mScale = arg1;
        this.mOriginalOffsetTop = arg2;
        this.mSpinnerOffsetEnd = arg3;
        this.mUsingCustomStart = true;
        this.reset();
        this.mRefreshing = false;
    }

    private void setRefreshing(boolean arg2, boolean arg3) {
        if(this.mRefreshing != arg2) {
            this.mNotify = arg3;
            this.ensureTarget();
            this.mRefreshing = arg2;
            if(this.mRefreshing) {
                this.animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            }
            else {
                this.startScaleDownAnimation(this.mRefreshListener);
            }
        }
    }

    public void setRefreshing(boolean arg3) {
        if(!arg3 || this.mRefreshing == arg3) {
            this.setRefreshing(arg3, false);
        }
        else {
            this.mRefreshing = arg3;
            int v3 = !this.mUsingCustomStart ? this.mSpinnerOffsetEnd + this.mOriginalOffsetTop : this.mSpinnerOffsetEnd;
            this.setTargetOffsetTopAndBottom(v3 - this.mCurrentTargetOffsetTop);
            this.mNotify = false;
            this.startScaleUpAnimation(this.mRefreshListener);
        }
    }

    public void setSize(int arg3) {
        if(arg3 != 0 && arg3 != 1) {
            return;
        }

        DisplayMetrics v0 = this.getResources().getDisplayMetrics();
        this.mCircleDiameter = arg3 == 0 ? ((int)(v0.density * 56f)) : ((int)(v0.density * 40f));
        this.mCircleView.setImageDrawable(null);
        this.mProgress.setStyle(arg3);
        this.mCircleView.setImageDrawable(this.mProgress);
    }

    void setTargetOffsetTopAndBottom(int arg2) {
        this.mCircleView.bringToFront();
        ViewCompat.offsetTopAndBottom(this.mCircleView, arg2);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    private Animation startAlphaAnimation(int arg2, int arg3) {
        android.support.v4.widget.SwipeRefreshLayout$4 v0 = new Animation(arg2, arg3) {
            public void applyTransformation(float arg4, Transformation arg5) {
                SwipeRefreshLayout.this.mProgress.setAlpha(((int)((((float)this.val$startingAlpha)) + (((float)(this.val$endingAlpha - this.val$startingAlpha))) * arg4)));
            }
        };
        ((Animation)v0).setDuration(300);
        this.mCircleView.setAnimationListener(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(((Animation)v0));
        return ((Animation)v0);
    }

    private void startDragging(float arg2) {
        if(arg2 - this.mInitialDownY > (((float)this.mTouchSlop)) && !this.mIsBeingDragged) {
            this.mInitialMotionY = this.mInitialDownY + (((float)this.mTouchSlop));
            this.mIsBeingDragged = true;
            this.mProgress.setAlpha(76);
        }
    }

    public boolean startNestedScroll(int arg2) {
        return this.mNestedScrollingChildHelper.startNestedScroll(arg2);
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 0xFF);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 76);
    }

    void startScaleDownAnimation(Animation$AnimationListener arg4) {
        this.mScaleDownAnimation = new Animation() {
            public void applyTransformation(float arg2, Transformation arg3) {
                SwipeRefreshLayout.this.setAnimationProgress(1f - arg2);
            }
        };
        this.mScaleDownAnimation.setDuration(150);
        this.mCircleView.setAnimationListener(arg4);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    private void startScaleDownReturnToStartAnimation(int arg3, Animation$AnimationListener arg4) {
        this.mFrom = arg3;
        this.mStartingScale = this.mCircleView.getScaleX();
        this.mScaleDownToStartAnimation = new Animation() {
            public void applyTransformation(float arg2, Transformation arg3) {
                SwipeRefreshLayout.this.setAnimationProgress(SwipeRefreshLayout.this.mStartingScale + -SwipeRefreshLayout.this.mStartingScale * arg2);
                SwipeRefreshLayout.this.moveToStart(arg2);
            }
        };
        this.mScaleDownToStartAnimation.setDuration(150);
        if(arg4 != null) {
            this.mCircleView.setAnimationListener(arg4);
        }

        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void startScaleUpAnimation(Animation$AnimationListener arg4) {
        this.mCircleView.setVisibility(0);
        if(Build$VERSION.SDK_INT >= 11) {
            this.mProgress.setAlpha(0xFF);
        }

        this.mScaleAnimation = new Animation() {
            public void applyTransformation(float arg1, Transformation arg2) {
                SwipeRefreshLayout.this.setAnimationProgress(arg1);
            }
        };
        this.mScaleAnimation.setDuration(((long)this.mMediumAnimationDuration));
        if(arg4 != null) {
            this.mCircleView.setAnimationListener(arg4);
        }

        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }
}

