package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;

        AccessibilityDelegate(SlidingPaneLayout arg1) {
            SlidingPaneLayout.this = arg1;
            super();
            this.mTmpRect = new Rect();
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat arg2, AccessibilityNodeInfoCompat arg3) {
            Rect v0 = this.mTmpRect;
            arg3.getBoundsInParent(v0);
            arg2.setBoundsInParent(v0);
            arg3.getBoundsInScreen(v0);
            arg2.setBoundsInScreen(v0);
            arg2.setVisibleToUser(arg3.isVisibleToUser());
            arg2.setPackageName(arg3.getPackageName());
            arg2.setClassName(arg3.getClassName());
            arg2.setContentDescription(arg3.getContentDescription());
            arg2.setEnabled(arg3.isEnabled());
            arg2.setClickable(arg3.isClickable());
            arg2.setFocusable(arg3.isFocusable());
            arg2.setFocused(arg3.isFocused());
            arg2.setAccessibilityFocused(arg3.isAccessibilityFocused());
            arg2.setSelected(arg3.isSelected());
            arg2.setLongClickable(arg3.isLongClickable());
            arg2.addAction(arg3.getActions());
            arg2.setMovementGranularities(arg3.getMovementGranularities());
        }

        public boolean filter(View arg2) {
            return SlidingPaneLayout.this.isDimmed(arg2);
        }

        public void onInitializeAccessibilityEvent(View arg1, AccessibilityEvent arg2) {
            super.onInitializeAccessibilityEvent(arg1, arg2);
            arg2.setClassName(SlidingPaneLayout.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View arg4, AccessibilityNodeInfoCompat arg5) {
            AccessibilityNodeInfoCompat v0 = AccessibilityNodeInfoCompat.obtain(arg5);
            super.onInitializeAccessibilityNodeInfo(arg4, v0);
            this.copyNodeInfoNoChildren(arg5, v0);
            v0.recycle();
            arg5.setClassName(SlidingPaneLayout.class.getName());
            arg5.setSource(arg4);
            ViewParent v4 = ViewCompat.getParentForAccessibility(arg4);
            if((v4 instanceof View)) {
                arg5.setParent(((View)v4));
            }

            int v4_1 = SlidingPaneLayout.this.getChildCount();
            int v0_1;
            for(v0_1 = 0; v0_1 < v4_1; ++v0_1) {
                View v1 = SlidingPaneLayout.this.getChildAt(v0_1);
                if(!this.filter(v1) && v1.getVisibility() == 0) {
                    ViewCompat.setImportantForAccessibility(v1, 1);
                    arg5.addChild(v1);
                }
            }
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup arg2, View arg3, AccessibilityEvent arg4) {
            if(!this.filter(arg3)) {
                return super.onRequestSendAccessibilityEvent(arg2, arg3, arg4);
            }

            return 0;
        }
    }

    class DisableLayerRunnable implements Runnable {
        final View mChildView;

        DisableLayerRunnable(SlidingPaneLayout arg1, View arg2) {
            SlidingPaneLayout.this = arg1;
            super();
            this.mChildView = arg2;
        }

        public void run() {
            if(this.mChildView.getParent() == SlidingPaneLayout.this) {
                this.mChildView.setLayerType(0, null);
                SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
            }

            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    class DragHelperCallback extends Callback {
        DragHelperCallback(SlidingPaneLayout arg1) {
            SlidingPaneLayout.this = arg1;
            super();
        }

        public int clampViewPositionHorizontal(View arg2, int arg3, int arg4) {
            int v2_1;
            ViewGroup$LayoutParams v2 = SlidingPaneLayout.this.mSlideableView.getLayoutParams();
            if(SlidingPaneLayout.this.isLayoutRtlSupport()) {
                arg4 = SlidingPaneLayout.this.getWidth() - (SlidingPaneLayout.this.getPaddingRight() + ((LayoutParams)v2).rightMargin + SlidingPaneLayout.this.mSlideableView.getWidth());
                v2_1 = Math.max(Math.min(arg3, arg4), arg4 - SlidingPaneLayout.this.mSlideRange);
            }
            else {
                arg4 = SlidingPaneLayout.this.getPaddingLeft() + ((LayoutParams)v2).leftMargin;
                v2_1 = Math.min(Math.max(arg3, arg4), SlidingPaneLayout.this.mSlideRange + arg4);
            }

            return v2_1;
        }

        public int clampViewPositionVertical(View arg1, int arg2, int arg3) {
            return arg1.getTop();
        }

        public int getViewHorizontalDragRange(View arg1) {
            return SlidingPaneLayout.this.mSlideRange;
        }

        public void onEdgeDragStarted(int arg2, int arg3) {
            SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, arg3);
        }

        public void onViewCaptured(View arg1, int arg2) {
            SlidingPaneLayout.this.setAllChildrenVisible();
        }

        public void onViewDragStateChanged(int arg2) {
            if(SlidingPaneLayout.this.mDragHelper.getViewDragState() == 0) {
                if(SlidingPaneLayout.this.mSlideOffset == 0f) {
                    SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
                    SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
                    SlidingPaneLayout.this.mPreservedOpenState = false;
                }
                else {
                    SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
                    SlidingPaneLayout.this.mPreservedOpenState = true;
                }
            }
        }

        public void onViewPositionChanged(View arg1, int arg2, int arg3, int arg4, int arg5) {
            SlidingPaneLayout.this.onPanelDragged(arg2);
            SlidingPaneLayout.this.invalidate();
        }

        public void onViewReleased(View arg4, float arg5, float arg6) {
            int v6_1;
            ViewGroup$LayoutParams v6 = arg4.getLayoutParams();
            float v1 = 0.5f;
            if(SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int v0 = SlidingPaneLayout.this.getPaddingRight() + ((LayoutParams)v6).rightMargin;
                if(arg5 < 0f || arg5 == 0f && SlidingPaneLayout.this.mSlideOffset > v1) {
                    v0 += SlidingPaneLayout.this.mSlideRange;
                }

                v6_1 = SlidingPaneLayout.this.getWidth() - v0 - SlidingPaneLayout.this.mSlideableView.getWidth();
            }
            else {
                v6_1 = ((LayoutParams)v6).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if(arg5 <= 0f) {
                    if(arg5 != 0f) {
                    }
                    else if(SlidingPaneLayout.this.mSlideOffset > v1) {
                        goto label_35;
                    }

                    goto label_38;
                }

            label_35:
                v6_1 += SlidingPaneLayout.this.mSlideRange;
            }

        label_38:
            SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(v6_1, arg4.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        public boolean tryCaptureView(View arg1, int arg2) {
            if(SlidingPaneLayout.this.mIsUnableToDrag) {
                return 0;
            }

            return arg1.getLayoutParams().slideable;
        }
    }

    public class LayoutParams extends ViewGroup$MarginLayoutParams {
        private static final int[] ATTRS;
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight;

        static {
            LayoutParams.ATTRS = new int[]{0x1010181};
        }

        public LayoutParams() {
            super(-1, -1);
            this.weight = 0f;
        }

        public LayoutParams(Context arg3, AttributeSet arg4) {
            super(arg3, arg4);
            this.weight = 0f;
            TypedArray v3 = arg3.obtainStyledAttributes(arg4, LayoutParams.ATTRS);
            this.weight = v3.getFloat(0, 0f);
            v3.recycle();
        }

        public LayoutParams(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
            this.weight = 0f;
        }

        public LayoutParams(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.weight = 0f;
        }

        public LayoutParams(int arg1, int arg2) {
            super(arg1, arg2);
            this.weight = 0f;
        }

        public LayoutParams(LayoutParams arg2) {
            super(((ViewGroup$MarginLayoutParams)arg2));
            this.weight = 0f;
            this.weight = arg2.weight;
        }
    }

    public interface PanelSlideListener {
        void onPanelClosed(View arg1);

        void onPanelOpened(View arg1);

        void onPanelSlide(View arg1, float arg2);
    }

    class SavedState extends AbsSavedState {
        final class android.support.v4.widget.SlidingPaneLayout$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v4.widget.SlidingPaneLayout$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState createFromParcel(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, null);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.createFromParcel(arg1, arg2);
            }

            public SavedState[] newArray(int arg1) {
                return new SavedState[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        boolean isOpen;

        static {
            SavedState.CREATOR = new android.support.v4.widget.SlidingPaneLayout$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            boolean v1 = arg1.readInt() != 0 ? true : false;
            this.isOpen = v1;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.isOpen);
        }
    }

    public class SimplePanelSlideListener implements PanelSlideListener {
        public SimplePanelSlideListener() {
            super();
        }

        public void onPanelClosed(View arg1) {
        }

        public void onPanelOpened(View arg1) {
        }

        public void onPanelSlide(View arg1, float arg2) {
        }
    }

    interface SlidingPanelLayoutImpl {
        void invalidateChildRegion(SlidingPaneLayout arg1, View arg2);
    }

    class SlidingPanelLayoutImplBase implements SlidingPanelLayoutImpl {
        SlidingPanelLayoutImplBase() {
            super();
        }

        public void invalidateChildRegion(SlidingPaneLayout arg4, View arg5) {
            ViewCompat.postInvalidateOnAnimation(((View)arg4), arg5.getLeft(), arg5.getTop(), arg5.getRight(), arg5.getBottom());
        }
    }

    @RequiresApi(value=16) class SlidingPanelLayoutImplJB extends SlidingPanelLayoutImplBase {
        private Method mGetDisplayList;
        private Field mRecreateDisplayList;

        SlidingPanelLayoutImplJB() {
            super();
            try {
                this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", null);
            }
            catch(NoSuchMethodException v0) {
                Log.e("SlidingPaneLayout", "Couldn\'t fetch getDisplayList method; dimming won\'t work right.", ((Throwable)v0));
            }

            try {
                this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                this.mRecreateDisplayList.setAccessible(true);
            }
            catch(NoSuchFieldException v0_1) {
                Log.e("SlidingPaneLayout", "Couldn\'t fetch mRecreateDisplayList field; dimming will be slow.", ((Throwable)v0_1));
            }
        }

        public void invalidateChildRegion(SlidingPaneLayout arg4, View arg5) {
            if(this.mGetDisplayList != null && this.mRecreateDisplayList != null) {
                try {
                    this.mRecreateDisplayList.setBoolean(arg5, true);
                    this.mGetDisplayList.invoke(arg5, null);
                }
                catch(Exception v0) {
                    Log.e("SlidingPaneLayout", "Error refreshing display list state", ((Throwable)v0));
                }

                super.invalidateChildRegion(arg4, arg5);
                return;
            }

            arg5.invalidate();
        }
    }

    @RequiresApi(value=17) class SlidingPanelLayoutImplJBMR1 extends SlidingPanelLayoutImplBase {
        SlidingPanelLayoutImplJBMR1() {
            super();
        }

        public void invalidateChildRegion(SlidingPaneLayout arg1, View arg2) {
            ViewCompat.setLayerPaint(arg2, arg2.getLayoutParams().dimPaint);
        }
    }

    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 0x20;
    static final SlidingPanelLayoutImpl IMPL = null;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList mPostedRunnables;
    boolean mPreservedOpenState;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    static {
        if(Build$VERSION.SDK_INT >= 17) {
            SlidingPaneLayout.IMPL = new SlidingPanelLayoutImplJBMR1();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            SlidingPaneLayout.IMPL = new SlidingPanelLayoutImplJB();
        }
        else {
            SlidingPaneLayout.IMPL = new SlidingPanelLayoutImplBase();
        }
    }

    public SlidingPaneLayout(Context arg2) {
        this(arg2, null);
    }

    public SlidingPaneLayout(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public SlidingPaneLayout(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.mSliderFadeColor = -858993460;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList();
        float v2 = arg2.getResources().getDisplayMetrics().density;
        this.mOverhangSize = ((int)(32f * v2 + 0.5f));
        this.setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(((View)this), new AccessibilityDelegate(this));
        ViewCompat.setImportantForAccessibility(((View)this), 1);
        this.mDragHelper = ViewDragHelper.create(((ViewGroup)this), 0.5f, new DragHelperCallback(this));
        this.mDragHelper.setMinVelocity(v2 * 400f);
    }

    protected boolean canScroll(View arg14, boolean arg15, int arg16, int arg17, int arg18) {
        View v0 = arg14;
        boolean v2 = true;
        if((v0 instanceof ViewGroup)) {
            View v1 = v0;
            int v3 = v0.getScrollX();
            int v4 = v0.getScrollY();
            int v5;
            for(v5 = ((ViewGroup)v1).getChildCount() - 1; v5 >= 0; --v5) {
                View v7 = ((ViewGroup)v1).getChildAt(v5);
                int v6 = arg17 + v3;
                if(v6 >= v7.getLeft() && v6 < v7.getRight()) {
                    int v8 = arg18 + v4;
                    if(v8 >= v7.getTop() && v8 < v7.getBottom() && (this.canScroll(v7, true, arg16, v6 - v7.getLeft(), v8 - v7.getTop()))) {
                        return 1;
                    }
                }
            }
        }

        if(arg15) {
            int v1_1 = this.isLayoutRtlSupport() ? arg16 : -arg16;
            if(!v0.canScrollHorizontally(v1_1)) {
                goto label_44;
            }
        }
        else {
        label_44:
            v2 = false;
        }

        return v2;
    }

    @Deprecated public boolean canSlide() {
        return this.mCanSlide;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg2) {
        boolean v2 = !(arg2 instanceof LayoutParams) || !super.checkLayoutParams(arg2) ? false : true;
        return v2;
    }

    private boolean closePane(View arg2, int arg3) {
        if(!this.mFirstLayout) {
            if(this.smoothSlideTo(0f, arg3)) {
            }
            else {
                return 0;
            }
        }

        this.mPreservedOpenState = false;
        return 1;
    }

    public boolean closePane() {
        return this.closePane(this.mSlideableView, 0);
    }

    public void computeScroll() {
        if(this.mDragHelper.continueSettling(true)) {
            if(!this.mCanSlide) {
                this.mDragHelper.abort();
                return;
            }
            else {
                ViewCompat.postInvalidateOnAnimation(((View)this));
            }
        }
    }

    private void dimChildView(View arg4, float arg5, int arg6) {
        ViewGroup$LayoutParams v0 = arg4.getLayoutParams();
        if(arg5 <= 0f || arg6 == 0) {
            if(arg4.getLayerType() == 0) {
                return;
            }

            if(((LayoutParams)v0).dimPaint != null) {
                ((LayoutParams)v0).dimPaint.setColorFilter(null);
            }

            DisableLayerRunnable v5_1 = new DisableLayerRunnable(this, arg4);
            this.mPostedRunnables.add(v5_1);
            ViewCompat.postOnAnimation(((View)this), ((Runnable)v5_1));
        }
        else {
            int v5 = (((int)((((float)((0xFF000000 & arg6) >>> 24))) * arg5))) << 24 | arg6 & 0xFFFFFF;
            if(((LayoutParams)v0).dimPaint == null) {
                ((LayoutParams)v0).dimPaint = new Paint();
            }

            ((LayoutParams)v0).dimPaint.setColorFilter(new PorterDuffColorFilter(v5, PorterDuff$Mode.SRC_OVER));
            arg6 = 2;
            if(arg4.getLayerType() != arg6) {
                arg4.setLayerType(arg6, ((LayoutParams)v0).dimPaint);
            }

            this.invalidateChildRegion(arg4);
        }
    }

    void dispatchOnPanelClosed(View arg2) {
        if(this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelClosed(arg2);
        }

        this.sendAccessibilityEvent(0x20);
    }

    void dispatchOnPanelOpened(View arg2) {
        if(this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelOpened(arg2);
        }

        this.sendAccessibilityEvent(0x20);
    }

    void dispatchOnPanelSlide(View arg3) {
        if(this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelSlide(arg3, this.mSlideOffset);
        }
    }

    public void draw(Canvas arg8) {
        int v1_1;
        super.draw(arg8);
        Drawable v0 = this.isLayoutRtlSupport() ? this.mShadowDrawableRight : this.mShadowDrawableLeft;
        View v1 = this.getChildCount() > 1 ? this.getChildAt(1) : null;
        if(v1 != null) {
            if(v0 == null) {
            }
            else {
                int v2 = v1.getTop();
                int v3 = v1.getBottom();
                int v4 = v0.getIntrinsicWidth();
                if(this.isLayoutRtlSupport()) {
                    v1_1 = v1.getRight();
                    v4 += v1_1;
                }
                else {
                    v1_1 = v1.getLeft();
                    int v6 = v1_1 - v4;
                    v4 = v1_1;
                    v1_1 = v6;
                }

                v0.setBounds(v1_1, v2, v4, v3);
                v0.draw(arg8);
                return;
            }
        }
    }

    protected boolean drawChild(Canvas arg5, View arg6, long arg7) {
        ViewGroup$LayoutParams v0 = arg6.getLayoutParams();
        int v1 = arg5.save(2);
        if((this.mCanSlide) && !((LayoutParams)v0).slideable && this.mSlideableView != null) {
            arg5.getClipBounds(this.mTmpRect);
            if(this.isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            }
            else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }

            arg5.clipRect(this.mTmpRect);
        }

        boolean v6 = super.drawChild(arg5, arg6, arg7);
        arg5.restoreToCount(v1);
        return v6;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new LayoutParams(this.getContext(), arg3);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg2) {
        LayoutParams v0 = (arg2 instanceof ViewGroup$MarginLayoutParams) ? new LayoutParams(((ViewGroup$MarginLayoutParams)arg2)) : new LayoutParams(arg2);
        return ((ViewGroup$LayoutParams)v0);
    }

    @ColorInt public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    @ColorInt public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    void invalidateChildRegion(View arg2) {
        SlidingPaneLayout.IMPL.invalidateChildRegion(this, arg2);
    }

    boolean isDimmed(View arg3) {
        boolean v0 = false;
        if(arg3 == null) {
            return 0;
        }

        ViewGroup$LayoutParams v3 = arg3.getLayoutParams();
        if((this.mCanSlide) && (((LayoutParams)v3).dimWhenOffset) && this.mSlideOffset > 0f) {
            v0 = true;
        }

        return v0;
    }

    boolean isLayoutRtlSupport() {
        boolean v1 = true;
        if(ViewCompat.getLayoutDirection(((View)this)) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean isOpen() {
        boolean v0 = !this.mCanSlide || this.mSlideOffset == 1f ? true : false;
        return v0;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        int v0 = this.mPostedRunnables.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.mPostedRunnables.get(v1).run();
        }

        this.mPostedRunnables.clear();
    }

    public boolean onInterceptTouchEvent(MotionEvent arg7) {
        float v1_1;
        float v0_1;
        int v0 = arg7.getActionMasked();
        boolean v2 = true;
        if(!this.mCanSlide && v0 == 0 && this.getChildCount() > 1) {
            View v1 = this.getChildAt(1);
            if(v1 != null) {
                this.mPreservedOpenState = this.mDragHelper.isViewUnder(v1, ((int)arg7.getX()), ((int)arg7.getY())) ^ 1;
            }
        }

        if((this.mCanSlide) && (!this.mIsUnableToDrag || v0 == 0)) {
            if(v0 != 3) {
                if(v0 == 1) {
                }
                else {
                    if(v0 == 0) {
                        this.mIsUnableToDrag = false;
                        v0_1 = arg7.getX();
                        v1_1 = arg7.getY();
                        this.mInitialMotionX = v0_1;
                        this.mInitialMotionY = v1_1;
                        if(!this.mDragHelper.isViewUnder(this.mSlideableView, ((int)v0_1), ((int)v1_1)) || !this.isDimmed(this.mSlideableView)) {
                        label_65:
                            v0 = 0;
                        }
                        else {
                            v0 = 1;
                        }
                    }
                    else if(v0 != 2) {
                        goto label_65;
                    }
                    else {
                        v0_1 = arg7.getX();
                        v1_1 = arg7.getY();
                        v0_1 = Math.abs(v0_1 - this.mInitialMotionX);
                        v1_1 = Math.abs(v1_1 - this.mInitialMotionY);
                        if(v0_1 <= (((float)this.mDragHelper.getTouchSlop()))) {
                            goto label_65;
                        }
                        else if(v1_1 > v0_1) {
                            this.mDragHelper.cancel();
                            this.mIsUnableToDrag = true;
                            return 0;
                        }
                        else {
                            goto label_65;
                        }
                    }

                    if(!this.mDragHelper.shouldInterceptTouchEvent(arg7)) {
                        if(v0 != 0) {
                        }
                        else {
                            v2 = false;
                        }
                    }

                    return v2;
                }
            }

            this.mDragHelper.cancel();
            return 0;
        }

        this.mDragHelper.cancel();
        return super.onInterceptTouchEvent(arg7);
    }

    protected void onLayout(boolean arg20, int arg21, int arg22, int arg23, int arg24) {
        int v10;
        int v2;
        int v9;
        SlidingPaneLayout v0 = this;
        boolean v1 = this.isLayoutRtlSupport();
        if(v1) {
            v0.mDragHelper.setEdgeTrackingEnabled(2);
        }
        else {
            v0.mDragHelper.setEdgeTrackingEnabled(1);
        }

        int v3 = arg23 - arg21;
        int v4 = v1 ? this.getPaddingRight() : this.getPaddingLeft();
        int v5 = v1 ? this.getPaddingLeft() : this.getPaddingRight();
        int v6 = this.getPaddingTop();
        int v7 = this.getChildCount();
        if(v0.mFirstLayout) {
            float v8 = !v0.mCanSlide || !v0.mPreservedOpenState ? 0f : 1f;
            v0.mSlideOffset = v8;
        }

        int v11 = v4;
        int v12 = v11;
        for(v4 = 0; v4 < v7; ++v4) {
            View v13 = v0.getChildAt(v4);
            if(v13.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v14 = v13.getLayoutParams();
                int v15 = v13.getMeasuredWidth();
                if(((LayoutParams)v14).slideable) {
                    int v8_1 = v3 - v5;
                    v9 = Math.min(v11, v8_1 - v0.mOverhangSize) - v12 - (((LayoutParams)v14).leftMargin + ((LayoutParams)v14).rightMargin);
                    v0.mSlideRange = v9;
                    v2 = v1 ? ((LayoutParams)v14).rightMargin : ((LayoutParams)v14).leftMargin;
                    boolean v8_2 = v12 + v2 + v9 + v15 / 2 > v8_1 ? true : false;
                    ((LayoutParams)v14).dimWhenOffset = v8_2;
                    v8_1 = ((int)((((float)v9)) * v0.mSlideOffset));
                    v2 = v2 + v8_1 + v12;
                    v0.mSlideOffset = (((float)v8_1)) / (((float)v0.mSlideRange));
                    goto label_97;
                }
                else {
                    if((v0.mCanSlide) && v0.mParallaxBy != 0) {
                        v9 = ((int)((1f - v0.mSlideOffset) * (((float)v0.mParallaxBy))));
                        v2 = v11;
                        goto label_98;
                    }

                    v2 = v11;
                label_97:
                    v9 = 0;
                }

            label_98:
                if(v1) {
                    v10 = v3 - v2 + v9;
                    v9 = v10 - v15;
                }
                else {
                    v9 = v2 - v9;
                    v10 = v9 + v15;
                }

                v13.layout(v9, v6, v10, v13.getMeasuredHeight() + v6);
                v11 += v13.getWidth();
                v12 = v2;
            }
        }

        if(v0.mFirstLayout) {
            if(v0.mCanSlide) {
                if(v0.mParallaxBy != 0) {
                    v0.parallaxOtherViews(v0.mSlideOffset);
                }

                if(!v0.mSlideableView.getLayoutParams().dimWhenOffset) {
                    goto label_139;
                }

                v0.dimChildView(v0.mSlideableView, v0.mSlideOffset, v0.mSliderFadeColor);
            }
            else {
                int v1_1;
                for(v1_1 = 0; v1_1 < v7; ++v1_1) {
                    v0.dimChildView(v0.getChildAt(v1_1), 0f, v0.mSliderFadeColor);
                }
            }

        label_139:
            v0.updateObscuredViewsVisibility(v0.mSlideableView);
        }

        v0.mFirstLayout = false;
    }

    protected void onMeasure(int arg21, int arg22) {
        int v19;
        int v17;
        int v10;
        int v15;
        int v5;
        SlidingPaneLayout v0 = this;
        int v1 = View$MeasureSpec.getMode(arg21);
        int v2 = View$MeasureSpec.getSize(arg21);
        int v3 = View$MeasureSpec.getMode(arg22);
        int v4 = View$MeasureSpec.getSize(arg22);
        int v6 = 0x80000000;
        int v7 = 0x40000000;
        if(v1 != v7) {
            if(!this.isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }
            else if(v1 == v6) {
            }
            else if(v1 == 0) {
                v2 = 300;
            }
        }
        else if(v3 == 0) {
            if(!this.isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            }
            else if(v3 == 0) {
                v3 = 0x80000000;
                v4 = 300;
            }
        }

        boolean v1_1 = false;
        if(v3 == v6) {
            v5 = v4 - this.getPaddingTop() - this.getPaddingBottom();
            v4 = 0;
        }
        else if(v3 != v7) {
            v4 = 0;
            v5 = 0;
        }
        else {
            v4 = v4 - this.getPaddingTop() - this.getPaddingBottom();
            v5 = v4;
        }

        int v8 = v2 - this.getPaddingLeft() - this.getPaddingRight();
        int v9 = this.getChildCount();
        if(v9 > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }

        v0.mSlideableView = null;
        int v13 = v4;
        int v14 = v8;
        v4 = 0;
        int v11 = 0;
        float v12 = 0f;
        while(true) {
            v15 = 8;
            if(v4 >= v9) {
                break;
            }

            View v6_1 = v0.getChildAt(v4);
            ViewGroup$LayoutParams v7_1 = v6_1.getLayoutParams();
            if(v6_1.getVisibility() == v15) {
                ((LayoutParams)v7_1).dimWhenOffset = v1_1;
            }
            else {
                if(((LayoutParams)v7_1).weight > 0f) {
                    v12 += ((LayoutParams)v7_1).weight;
                    if(((LayoutParams)v7_1).width == 0) {
                        goto label_138;
                    }
                }

                v10 = ((LayoutParams)v7_1).leftMargin + ((LayoutParams)v7_1).rightMargin;
                if(((LayoutParams)v7_1).width == -2) {
                    v1 = View$MeasureSpec.makeMeasureSpec(v8 - v10, 0x80000000);
                }
                else if(((LayoutParams)v7_1).width == -1) {
                    v1 = View$MeasureSpec.makeMeasureSpec(v8 - v10, 0x40000000);
                }
                else {
                    v1 = View$MeasureSpec.makeMeasureSpec(((LayoutParams)v7_1).width, 0x40000000);
                }

                if(((LayoutParams)v7_1).height == -2) {
                    v15 = View$MeasureSpec.makeMeasureSpec(v5, 0x80000000);
                }
                else if(((LayoutParams)v7_1).height == -1) {
                    v15 = View$MeasureSpec.makeMeasureSpec(v5, 0x40000000);
                }
                else {
                    v15 = View$MeasureSpec.makeMeasureSpec(((LayoutParams)v7_1).height, 0x40000000);
                }

                v6_1.measure(v1, v15);
                v1 = v6_1.getMeasuredWidth();
                v10 = v6_1.getMeasuredHeight();
                if(v3 == 0x80000000 && v10 > v13) {
                    v13 = Math.min(v10, v5);
                }

                v14 -= v1;
                v1_1 = v14 < 0 ? true : false;
                ((LayoutParams)v7_1).slideable = v1_1;
                v1 = (((int)v1_1)) | v11;
                if(((LayoutParams)v7_1).slideable) {
                    v0.mSlideableView = v6_1;
                }

                v11 = v1;
            }

        label_138:
            ++v4;
            v1_1 = false;
        }

        if(v11 != 0 || v12 > 0f) {
            v1 = v8 - v0.mOverhangSize;
            v3 = 0;
            while(v3 < v9) {
                View v4_1 = v0.getChildAt(v3);
                if(v4_1.getVisibility() != v15) {
                    ViewGroup$LayoutParams v6_2 = v4_1.getLayoutParams();
                    if(v4_1.getVisibility() == v15) {
                        goto label_153;
                    }
                    else {
                        v7 = ((LayoutParams)v6_2).width != 0 || ((LayoutParams)v6_2).weight <= 0f ? 0 : 1;
                        v10 = v7 != 0 ? 0 : v4_1.getMeasuredWidth();
                        if(v11 != 0 && v4_1 != v0.mSlideableView) {
                            if(((LayoutParams)v6_2).width < 0) {
                                if(v10 <= v1 && ((LayoutParams)v6_2).weight <= 0f) {
                                    goto label_153;
                                }

                                if(v7 == 0) {
                                    v7 = 0x40000000;
                                    v6 = View$MeasureSpec.makeMeasureSpec(v4_1.getMeasuredHeight(), v7);
                                }
                                else if(((LayoutParams)v6_2).height == -2) {
                                    v6 = View$MeasureSpec.makeMeasureSpec(v5, 0x80000000);
                                    v7 = 0x40000000;
                                }
                                else if(((LayoutParams)v6_2).height == -1) {
                                    v7 = 0x40000000;
                                    v6 = View$MeasureSpec.makeMeasureSpec(v5, v7);
                                }
                                else {
                                    v7 = 0x40000000;
                                    v6 = View$MeasureSpec.makeMeasureSpec(((LayoutParams)v6_2).height, v7);
                                }

                                v4_1.measure(View$MeasureSpec.makeMeasureSpec(v1, v7), v6);
                            }
                            else {
                            }

                            goto label_153;
                        }

                        if(((LayoutParams)v6_2).weight <= 0f) {
                            goto label_153;
                        }

                        if(((LayoutParams)v6_2).width != 0) {
                            v17 = View$MeasureSpec.makeMeasureSpec(v4_1.getMeasuredHeight(), 0x40000000);
                        label_232:
                            v15 = v17;
                        }
                        else if(((LayoutParams)v6_2).height == -2) {
                            v15 = View$MeasureSpec.makeMeasureSpec(v5, 0x80000000);
                        }
                        else {
                            v17 = ((LayoutParams)v6_2).height == -1 ? View$MeasureSpec.makeMeasureSpec(v5, 0x40000000) : View$MeasureSpec.makeMeasureSpec(((LayoutParams)v6_2).height, 0x40000000);
                            goto label_232;
                        }

                        if(v11 != 0) {
                            v6 = v8 - (((LayoutParams)v6_2).leftMargin + ((LayoutParams)v6_2).rightMargin);
                            v19 = v1;
                            v1 = View$MeasureSpec.makeMeasureSpec(v6, 0x40000000);
                            if(v10 == v6) {
                                goto label_256;
                            }

                            v4_1.measure(v1, v15);
                            goto label_256;
                        }

                        v19 = v1;
                        v4_1.measure(View$MeasureSpec.makeMeasureSpec(v10 + (((int)(((LayoutParams)v6_2).weight * (((float)Math.max(0, v14))) / v12))), 0x40000000), v15);
                    }
                }
                else {
                label_153:
                    v19 = v1;
                }

            label_256:
                ++v3;
                v1 = v19;
                v15 = 8;
            }
        }

        v0.setMeasuredDimension(v2, v13 + this.getPaddingTop() + this.getPaddingBottom());
        v0.mCanSlide = ((boolean)v11);
        if(v0.mDragHelper.getViewDragState() != 0 && v11 == 0) {
            v0.mDragHelper.abort();
        }
    }

    void onPanelDragged(int arg5) {
        if(this.mSlideableView == null) {
            this.mSlideOffset = 0f;
            return;
        }

        boolean v0 = this.isLayoutRtlSupport();
        ViewGroup$LayoutParams v1 = this.mSlideableView.getLayoutParams();
        int v2 = this.mSlideableView.getWidth();
        if(v0) {
            arg5 = this.getWidth() - arg5 - v2;
        }

        v2 = v0 ? this.getPaddingRight() : this.getPaddingLeft();
        int v0_1 = v0 ? ((LayoutParams)v1).rightMargin : ((LayoutParams)v1).leftMargin;
        this.mSlideOffset = (((float)(arg5 - (v2 + v0_1)))) / (((float)this.mSlideRange));
        if(this.mParallaxBy != 0) {
            this.parallaxOtherViews(this.mSlideOffset);
        }

        if(((LayoutParams)v1).dimWhenOffset) {
            this.dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }

        this.dispatchOnPanelSlide(this.mSlideableView);
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        if(((SavedState)arg2).isOpen) {
            this.openPane();
        }
        else {
            this.closePane();
        }

        this.mPreservedOpenState = ((SavedState)arg2).isOpen;
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        boolean v0 = this.isSlideable() ? this.isOpen() : this.mPreservedOpenState;
        v1.isOpen = v0;
        return ((Parcelable)v1);
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        if(arg1 != arg3) {
            this.mFirstLayout = true;
        }
    }

    public boolean onTouchEvent(MotionEvent arg6) {
        float v6;
        float v1;
        if(!this.mCanSlide) {
            return super.onTouchEvent(arg6);
        }

        this.mDragHelper.processTouchEvent(arg6);
        switch(arg6.getActionMasked()) {
            case 0: {
                v1 = arg6.getX();
                v6 = arg6.getY();
                this.mInitialMotionX = v1;
                this.mInitialMotionY = v6;
                break;
            }
            case 1: {
                if(!this.isDimmed(this.mSlideableView)) {
                    return 1;
                }

                v1 = arg6.getX();
                v6 = arg6.getY();
                float v2 = v1 - this.mInitialMotionX;
                float v3 = v6 - this.mInitialMotionY;
                int v4 = this.mDragHelper.getTouchSlop();
                if(v2 * v2 + v3 * v3 >= (((float)(v4 * v4)))) {
                    return 1;
                }

                if(!this.mDragHelper.isViewUnder(this.mSlideableView, ((int)v1), ((int)v6))) {
                    return 1;
                }

                this.closePane(this.mSlideableView, 0);
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }

    private boolean openPane(View arg1, int arg2) {
        if(!this.mFirstLayout) {
            if(this.smoothSlideTo(1f, arg2)) {
            }
            else {
                return 0;
            }
        }

        this.mPreservedOpenState = true;
        return 1;
    }

    public boolean openPane() {
        return this.openPane(this.mSlideableView, 0);
    }

    private void parallaxOtherViews(float arg10) {
        int v1_1;
        boolean v0 = this.isLayoutRtlSupport();
        ViewGroup$LayoutParams v1 = this.mSlideableView.getLayoutParams();
        int v3 = 0;
        if(((LayoutParams)v1).dimWhenOffset) {
            v1_1 = v0 ? ((LayoutParams)v1).rightMargin : ((LayoutParams)v1).leftMargin;
            if(v1_1 > 0) {
                goto label_13;
            }

            v1_1 = 1;
        }
        else {
        label_13:
            v1_1 = 0;
        }

        int v2 = this.getChildCount();
        while(v3 < v2) {
            View v4 = this.getChildAt(v3);
            if(v4 == this.mSlideableView) {
            }
            else {
                float v6 = 1f;
                int v5 = ((int)((v6 - this.mParallaxOffset) * (((float)this.mParallaxBy))));
                this.mParallaxOffset = arg10;
                v5 -= ((int)((v6 - arg10) * (((float)this.mParallaxBy))));
                if(v0) {
                    v5 = -v5;
                }

                v4.offsetLeftAndRight(v5);
                if(v1_1 == 0) {
                    goto label_46;
                }

                float v5_1 = v0 ? this.mParallaxOffset - v6 : v6 - this.mParallaxOffset;
                this.dimChildView(v4, v5_1, this.mCoveredFadeColor);
            }

        label_46:
            ++v3;
        }
    }

    public void requestChildFocus(View arg1, View arg2) {
        super.requestChildFocus(arg1, arg2);
        if(!this.isInTouchMode() && !this.mCanSlide) {
            boolean v1 = arg1 == this.mSlideableView ? true : false;
            this.mPreservedOpenState = v1;
        }
    }

    void setAllChildrenVisible() {
        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getChildAt(v2);
            if(v3.getVisibility() == 4) {
                v3.setVisibility(0);
            }
        }
    }

    public void setCoveredFadeColor(@ColorInt int arg1) {
        this.mCoveredFadeColor = arg1;
    }

    public void setPanelSlideListener(PanelSlideListener arg1) {
        this.mPanelSlideListener = arg1;
    }

    public void setParallaxDistance(int arg1) {
        this.mParallaxBy = arg1;
        this.requestLayout();
    }

    @Deprecated public void setShadowDrawable(Drawable arg1) {
        this.setShadowDrawableLeft(arg1);
    }

    public void setShadowDrawableLeft(Drawable arg1) {
        this.mShadowDrawableLeft = arg1;
    }

    public void setShadowDrawableRight(Drawable arg1) {
        this.mShadowDrawableRight = arg1;
    }

    @Deprecated public void setShadowResource(@DrawableRes int arg2) {
        this.setShadowDrawable(this.getResources().getDrawable(arg2));
    }

    public void setShadowResourceLeft(int arg2) {
        this.setShadowDrawableLeft(ContextCompat.getDrawable(this.getContext(), arg2));
    }

    public void setShadowResourceRight(int arg2) {
        this.setShadowDrawableRight(ContextCompat.getDrawable(this.getContext(), arg2));
    }

    public void setSliderFadeColor(@ColorInt int arg1) {
        this.mSliderFadeColor = arg1;
    }

    @Deprecated public void smoothSlideClosed() {
        this.closePane();
    }

    @Deprecated public void smoothSlideOpen() {
        this.openPane();
    }

    boolean smoothSlideTo(float arg5, int arg6) {
        if(!this.mCanSlide) {
            return 0;
        }

        boolean v6 = this.isLayoutRtlSupport();
        ViewGroup$LayoutParams v1 = this.mSlideableView.getLayoutParams();
        int v5 = v6 ? ((int)((((float)this.getWidth())) - ((((float)(this.getPaddingRight() + ((LayoutParams)v1).rightMargin))) + arg5 * (((float)this.mSlideRange)) + (((float)this.mSlideableView.getWidth()))))) : ((int)((((float)(this.getPaddingLeft() + ((LayoutParams)v1).leftMargin))) + arg5 * (((float)this.mSlideRange))));
        if(this.mDragHelper.smoothSlideViewTo(this.mSlideableView, v5, this.mSlideableView.getTop())) {
            this.setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation(((View)this));
            return 1;
        }

        return 0;
    }

    void updateObscuredViewsVisibility(View arg18) {
        int v0_1;
        boolean v16;
        int v10;
        int v9;
        int v8;
        int v7;
        View v0 = arg18;
        boolean v1 = this.isLayoutRtlSupport();
        int v2 = v1 ? this.getWidth() - this.getPaddingRight() : this.getPaddingLeft();
        int v3 = v1 ? this.getPaddingLeft() : this.getWidth() - this.getPaddingRight();
        int v4 = this.getPaddingTop();
        int v5 = this.getHeight() - this.getPaddingBottom();
        if(v0 == null || !SlidingPaneLayout.viewIsOpaque(arg18)) {
            v7 = 0;
            v8 = 0;
            v9 = 0;
            v10 = 0;
        }
        else {
            v7 = arg18.getLeft();
            v8 = arg18.getRight();
            v9 = arg18.getTop();
            v10 = arg18.getBottom();
        }

        int v11 = this.getChildCount();
        int v12 = 0;
        while(v12 < v11) {
            View v14 = this.getChildAt(v12);
            if(v14 == v0) {
            }
            else {
                if(v14.getVisibility() == 8) {
                    v16 = v1;
                }
                else {
                    int v6 = v1 ? v3 : v2;
                    v6 = Math.max(v6, v14.getLeft());
                    int v15 = Math.max(v4, v14.getTop());
                    if(v1) {
                        v16 = v1;
                        v0_1 = v2;
                    }
                    else {
                        v16 = v1;
                        v0_1 = v3;
                    }

                    v0_1 = Math.min(v0_1, v14.getRight());
                    int v1_1 = Math.min(v5, v14.getBottom());
                    v6 = v6 < v7 || v15 < v9 || v0_1 > v8 || v1_1 > v10 ? 0 : 4;
                    v14.setVisibility(v6);
                }

                ++v12;
                v1 = v16;
                v0 = arg18;
                continue;
            }

            return;
        }
    }

    private static boolean viewIsOpaque(View arg4) {
        boolean v1 = true;
        if(arg4.isOpaque()) {
            return 1;
        }

        if(Build$VERSION.SDK_INT >= 18) {
            return 0;
        }

        Drawable v4 = arg4.getBackground();
        if(v4 != null) {
            if(v4.getOpacity() == -1) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        return 0;
    }
}

