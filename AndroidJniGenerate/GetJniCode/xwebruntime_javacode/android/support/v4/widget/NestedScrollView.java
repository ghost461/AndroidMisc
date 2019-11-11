package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View$BaseSavedState;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public class NestedScrollView extends FrameLayout implements NestedScrollingChild2, NestedScrollingParent, ScrollingView {
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        AccessibilityDelegate() {
            super();
        }

        public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
            super.onInitializeAccessibilityEvent(arg2, arg3);
            arg3.setClassName(ScrollView.class.getName());
            boolean v0 = ((NestedScrollView)arg2).getScrollRange() > 0 ? true : false;
            arg3.setScrollable(v0);
            arg3.setScrollX(((NestedScrollView)arg2).getScrollX());
            arg3.setScrollY(((NestedScrollView)arg2).getScrollY());
            AccessibilityRecordCompat.setMaxScrollX(((AccessibilityRecord)arg3), ((NestedScrollView)arg2).getScrollX());
            AccessibilityRecordCompat.setMaxScrollY(((AccessibilityRecord)arg3), ((NestedScrollView)arg2).getScrollRange());
        }

        public void onInitializeAccessibilityNodeInfo(View arg3, AccessibilityNodeInfoCompat arg4) {
            super.onInitializeAccessibilityNodeInfo(arg3, arg4);
            arg4.setClassName(ScrollView.class.getName());
            if(((NestedScrollView)arg3).isEnabled()) {
                int v0 = ((NestedScrollView)arg3).getScrollRange();
                if(v0 > 0) {
                    arg4.setScrollable(true);
                    if(((NestedScrollView)arg3).getScrollY() > 0) {
                        arg4.addAction(0x2000);
                    }

                    if(((NestedScrollView)arg3).getScrollY() >= v0) {
                        return;
                    }

                    arg4.addAction(0x1000);
                }
            }
        }

        public boolean performAccessibilityAction(View arg3, int arg4, Bundle arg5) {
            if(super.performAccessibilityAction(arg3, arg4, arg5)) {
                return 1;
            }

            if(!((NestedScrollView)arg3).isEnabled()) {
                return 0;
            }

            if(arg4 != 0x1000) {
                if(arg4 != 0x2000) {
                    return 0;
                }

                arg4 = Math.max(((NestedScrollView)arg3).getScrollY() - (((NestedScrollView)arg3).getHeight() - ((NestedScrollView)arg3).getPaddingBottom() - ((NestedScrollView)arg3).getPaddingTop()), 0);
                if(arg4 != ((NestedScrollView)arg3).getScrollY()) {
                    ((NestedScrollView)arg3).smoothScrollTo(0, arg4);
                    return 1;
                }

                return 0;
            }

            arg4 = Math.min(((NestedScrollView)arg3).getScrollY() + (((NestedScrollView)arg3).getHeight() - ((NestedScrollView)arg3).getPaddingBottom() - ((NestedScrollView)arg3).getPaddingTop()), ((NestedScrollView)arg3).getScrollRange());
            if(arg4 != ((NestedScrollView)arg3).getScrollY()) {
                ((NestedScrollView)arg3).smoothScrollTo(0, arg4);
                return 1;
            }

            return 0;
        }
    }

    public interface OnScrollChangeListener {
        void onScrollChange(NestedScrollView arg1, int arg2, int arg3, int arg4, int arg5);
    }

    class SavedState extends View$BaseSavedState {
        final class android.support.v4.widget.NestedScrollView$SavedState$1 implements Parcelable$Creator {
            android.support.v4.widget.NestedScrollView$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg2) {
                return new SavedState(arg2);
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public SavedState[] newArray(int arg1) {
                return new SavedState[arg1];
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        public int scrollPosition;

        static {
            SavedState.CREATOR = new android.support.v4.widget.NestedScrollView$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        SavedState(Parcel arg1) {
            super(arg1);
            this.scrollPosition = arg1.readInt();
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.scrollPosition);
        }
    }

    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = null;
    static final int ANIMATED_SCROLL_GAP = 0xFA;
    private static final int INVALID_POINTER = -1;
    static final float MAX_SCROLL_FACTOR = 0.5f;
    private static final int[] SCROLLVIEW_STYLEABLE = null;
    private static final String TAG = "NestedScrollView";
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private boolean mIsBeingDragged;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private OnScrollChangeListener mOnScrollChangeListener;
    private final NestedScrollingParentHelper mParentHelper;
    private SavedState mSavedState;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;

    static {
        NestedScrollView.ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
        NestedScrollView.SCROLLVIEW_STYLEABLE = new int[]{0x101017A};
    }

    public NestedScrollView(Context arg2) {
        this(arg2, null);
    }

    public NestedScrollView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public NestedScrollView(Context arg5, AttributeSet arg6, int arg7) {
        super(arg5, arg6, arg7);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mIsLaidOut = false;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.initScrollView();
        TypedArray v5 = arg5.obtainStyledAttributes(arg6, NestedScrollView.SCROLLVIEW_STYLEABLE, arg7, 0);
        this.setFillViewport(v5.getBoolean(0, false));
        v5.recycle();
        this.mParentHelper = new NestedScrollingParentHelper(((ViewGroup)this));
        this.mChildHelper = new NestedScrollingChildHelper(((View)this));
        this.setNestedScrollingEnabled(true);
        ViewCompat.setAccessibilityDelegate(((View)this), NestedScrollView.ACCESSIBILITY_DELEGATE);
    }

    public void addView(View arg2) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }

        super.addView(arg2);
    }

    public void addView(View arg2, int arg3) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }

        super.addView(arg2, arg3);
    }

    public void addView(View arg2, int arg3, ViewGroup$LayoutParams arg4) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }

        super.addView(arg2, arg3, arg4);
    }

    public void addView(View arg2, ViewGroup$LayoutParams arg3) {
        if(this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }

        super.addView(arg2, arg3);
    }

    public boolean arrowScroll(int arg8) {
        View v0 = this.findFocus();
        if((((NestedScrollView)v0)) == this) {
            v0 = null;
        }

        View v1 = FocusFinder.getInstance().findNextFocus(((ViewGroup)this), v0, arg8);
        int v2 = this.getMaxScrollAmount();
        if(v1 == null || !this.isWithinDeltaOfScreen(v1, v2, this.getHeight())) {
            int v4 = 130;
            if(arg8 == 33 && this.getScrollY() < v2) {
                v2 = this.getScrollY();
            }
            else if(arg8 == v4 && this.getChildCount() > 0) {
                int v1_1 = this.getChildAt(0).getBottom() - (this.getScrollY() + this.getHeight() - this.getPaddingBottom());
                if(v1_1 < v2) {
                    v2 = v1_1;
                }
            }

            if(v2 == 0) {
                return 0;
            }

            if(arg8 == v4) {
            }
            else {
                v2 = -v2;
            }

            this.doScrollY(v2);
        }
        else {
            v1.getDrawingRect(this.mTempRect);
            this.offsetDescendantRectToMyCoords(v1, this.mTempRect);
            this.doScrollY(this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
            v1.requestFocus(arg8);
        }

        if(v0 != null && (v0.isFocused()) && (this.isOffScreen(v0))) {
            arg8 = this.getDescendantFocusability();
            this.setDescendantFocusability(0x20000);
            this.requestFocus();
            this.setDescendantFocusability(arg8);
        }

        return 1;
    }

    private boolean canScroll() {
        boolean v0 = false;
        View v1 = this.getChildAt(0);
        if(v1 != null) {
            if(this.getHeight() < v1.getHeight() + this.getPaddingTop() + this.getPaddingBottom()) {
                v0 = true;
            }

            return v0;
        }

        return 0;
    }

    private static int clamp(int arg1, int arg2, int arg3) {
        if(arg2 < arg3) {
            if(arg1 < 0) {
            }
            else if(arg2 + arg1 > arg3) {
                return arg3 - arg2;
            }
            else {
                return arg1;
            }
        }

        return 0;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public void computeScroll() {
        int v17;
        NestedScrollView v10 = this;
        if(v10.mScroller.computeScrollOffset()) {
            v10.mScroller.getCurrX();
            int v13 = v10.mScroller.getCurrY();
            int v6 = v13 - v10.mLastScrollerY;
            if(v10.dispatchNestedPreScroll(0, v6, v10.mScrollConsumed, null, 1)) {
                v6 -= v10.mScrollConsumed[1];
            }

            int v14 = v6;
            if(v14 != 0) {
                int v15 = this.getScrollRange();
                int v9 = this.getScrollY();
                int v11 = v9;
                v10.overScrollByCompat(0, v14, this.getScrollX(), v9, 0, v15, 0, 0, false);
                int v2 = this.getScrollY() - v11;
                if(!v10.dispatchNestedScroll(0, v2, 0, v14 - v2, null, 1)) {
                    int v0 = this.getOverScrollMode();
                    if(v0 != 0) {
                        if(v0 == 1 && v15 > 0) {
                            goto label_55;
                        }

                        v17 = 0;
                    }
                    else {
                    label_55:
                        v17 = 1;
                    }

                    if(v17 == 0) {
                        goto label_73;
                    }

                    this.ensureGlows();
                    if(v13 <= 0 && v11 > 0) {
                        v10.mEdgeGlowTop.onAbsorb(((int)v10.mScroller.getCurrVelocity()));
                        goto label_73;
                    }

                    if(v13 < v15) {
                        goto label_73;
                    }

                    if(v11 >= v15) {
                        goto label_73;
                    }

                    v10.mEdgeGlowBottom.onAbsorb(((int)v10.mScroller.getCurrVelocity()));
                }
            }

        label_73:
            v10.mLastScrollerY = v13;
            ViewCompat.postInvalidateOnAnimation(((View)this));
        }
        else {
            if(v10.hasNestedScrollingParent(1)) {
                v10.stopNestedScroll(1);
            }

            v10.mLastScrollerY = 0;
        }
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect arg8) {
        int v1 = 0;
        if(this.getChildCount() == 0) {
            return 0;
        }

        int v0 = this.getHeight();
        int v2 = this.getScrollY();
        int v3 = v2 + v0;
        int v4 = this.getVerticalFadingEdgeLength();
        if(arg8.top > 0) {
            v2 += v4;
        }

        if(arg8.bottom < this.getChildAt(0).getHeight()) {
            v3 -= v4;
        }

        if(arg8.bottom <= v3 || arg8.top <= v2) {
            if(arg8.top >= v2) {
                return v1;
            }

            if(arg8.bottom >= v3) {
                return v1;
            }

            v1 = arg8.height() > v0 ? -(v3 - arg8.bottom) : -(v2 - arg8.top);
            v1 = Math.max(v1, -this.getScrollY());
        }
        else {
            int v8 = arg8.height() > v0 ? arg8.top - v2 : arg8.bottom - v3;
            v1 = Math.min(v8, this.getChildAt(0).getBottom() - v3);
        }

        return v1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int computeVerticalScrollRange() {
        int v0 = this.getChildCount();
        int v1 = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
        if(v0 == 0) {
            return v1;
        }

        int v2 = this.getChildAt(0).getBottom();
        int v3 = this.getScrollY();
        v0 = Math.max(0, v2 - v1);
        if(v3 < 0) {
            v2 -= v3;
        }
        else if(v3 > v0) {
            v2 += v3 - v0;
        }

        return v2;
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        boolean v2 = (super.dispatchKeyEvent(arg2)) || (this.executeKeyEvent(arg2)) ? true : false;
        return v2;
    }

    public boolean dispatchNestedFling(float arg2, float arg3, boolean arg4) {
        return this.mChildHelper.dispatchNestedFling(arg2, arg3, arg4);
    }

    public boolean dispatchNestedPreFling(float arg2, float arg3) {
        return this.mChildHelper.dispatchNestedPreFling(arg2, arg3);
    }

    public boolean dispatchNestedPreScroll(int arg7, int arg8, int[] arg9, int[] arg10, int arg11) {
        return this.mChildHelper.dispatchNestedPreScroll(arg7, arg8, arg9, arg10, arg11);
    }

    public boolean dispatchNestedPreScroll(int arg2, int arg3, int[] arg4, int[] arg5) {
        return this.mChildHelper.dispatchNestedPreScroll(arg2, arg3, arg4, arg5);
    }

    public boolean dispatchNestedScroll(int arg8, int arg9, int arg10, int arg11, int[] arg12, int arg13) {
        return this.mChildHelper.dispatchNestedScroll(arg8, arg9, arg10, arg11, arg12, arg13);
    }

    public boolean dispatchNestedScroll(int arg7, int arg8, int arg9, int arg10, int[] arg11) {
        return this.mChildHelper.dispatchNestedScroll(arg7, arg8, arg9, arg10, arg11);
    }

    private void doScrollY(int arg3) {
        if(arg3 != 0) {
            if(this.mSmoothScrollingEnabled) {
                this.smoothScrollBy(0, arg3);
            }
            else {
                this.scrollBy(0, arg3);
            }
        }
    }

    public void draw(Canvas arg7) {
        int v2;
        int v1;
        super.draw(arg7);
        if(this.mEdgeGlowTop != null) {
            int v0 = this.getScrollY();
            if(!this.mEdgeGlowTop.isFinished()) {
                v1 = arg7.save();
                v2 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
                arg7.translate(((float)this.getPaddingLeft()), ((float)Math.min(0, v0)));
                this.mEdgeGlowTop.setSize(v2, this.getHeight());
                if(this.mEdgeGlowTop.draw(arg7)) {
                    ViewCompat.postInvalidateOnAnimation(((View)this));
                }

                arg7.restoreToCount(v1);
            }

            if(this.mEdgeGlowBottom.isFinished()) {
                return;
            }

            v1 = arg7.save();
            v2 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
            int v3 = this.getHeight();
            arg7.translate(((float)(-v2 + this.getPaddingLeft())), ((float)(Math.max(this.getScrollRange(), v0) + v3)));
            arg7.rotate(180f, ((float)v2), 0f);
            this.mEdgeGlowBottom.setSize(v2, v3);
            if(this.mEdgeGlowBottom.draw(arg7)) {
                ViewCompat.postInvalidateOnAnimation(((View)this));
            }

            arg7.restoreToCount(v1);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.recycleVelocityTracker();
        this.stopNestedScroll(0);
        if(this.mEdgeGlowTop != null) {
            this.mEdgeGlowTop.onRelease();
            this.mEdgeGlowBottom.onRelease();
        }
    }

    private void ensureGlows() {
        if(this.getOverScrollMode() == 2) {
            this.mEdgeGlowTop = null;
            this.mEdgeGlowBottom = null;
        }
        else if(this.mEdgeGlowTop == null) {
            Context v0 = this.getContext();
            this.mEdgeGlowTop = new EdgeEffect(v0);
            this.mEdgeGlowBottom = new EdgeEffect(v0);
        }
    }

    public boolean executeKeyEvent(KeyEvent arg6) {
        this.mTempRect.setEmpty();
        boolean v1 = false;
        int v2 = 130;
        if(!this.canScroll()) {
            if((this.isFocused()) && arg6.getKeyCode() != 4) {
                View v6 = this.findFocus();
                if((((NestedScrollView)v6)) == this) {
                    v6 = null;
                }

                v6 = FocusFinder.getInstance().findNextFocus(((ViewGroup)this), v6, v2);
                if(v6 != null && (((NestedScrollView)v6)) != this && (v6.requestFocus(v2))) {
                    v1 = true;
                }

                return v1;
            }

            return 0;
        }

        if(arg6.getAction() == 0) {
            int v0 = arg6.getKeyCode();
            int v4 = 33;
            if(v0 != 62) {
                switch(v0) {
                    case 19: {
                        goto label_37;
                    }
                    case 20: {
                        goto label_31;
                    }
                }

                return v1;
            label_37:
                if(!arg6.isAltPressed()) {
                    v1 = this.arrowScroll(v4);
                }
                else {
                    return this.fullScroll(v4);
                label_31:
                    v1 = !arg6.isAltPressed() ? this.arrowScroll(v2) : this.fullScroll(v2);
                }
            }
            else {
                if(arg6.isShiftPressed()) {
                    v2 = 33;
                }

                this.pageScroll(v2);
            }
        }

        return v1;
    }

    private View findFocusableViewInBounds(boolean arg13, int arg14, int arg15) {
        ArrayList v0 = this.getFocusables(2);
        int v1 = ((List)v0).size();
        Object v4 = null;
        int v3 = 0;
        int v5 = 0;
        while(v3 < v1) {
            Object v6 = ((List)v0).get(v3);
            int v7 = ((View)v6).getTop();
            int v8 = ((View)v6).getBottom();
            if(arg14 < v8 && v7 < arg15) {
                int v10 = arg14 >= v7 || v8 >= arg15 ? 0 : 1;
                if(v4 == null) {
                    v4 = v6;
                    v5 = v10;
                    goto label_43;
                }

                if(!arg13 || v7 >= ((View)v4).getTop()) {
                    if(!arg13 && v8 > ((View)v4).getBottom()) {
                    label_30:
                        v7 = 1;
                        goto label_33;
                    }

                    v7 = 0;
                }
                else {
                    goto label_30;
                }

            label_33:
                if(v5 == 0) {
                    if(v10 != 0) {
                        v4 = v6;
                        v5 = 1;
                    }
                    else if(v7 != 0) {
                        goto label_42;
                    }

                    goto label_43;
                }
                else if(v10 == 0) {
                    goto label_43;
                }
                else if(v7 != 0) {
                }
                else {
                    goto label_43;
                }

            label_42:
                v4 = v6;
            }

        label_43:
            ++v3;
        }

        return ((View)v4);
    }

    public void fling(int arg14) {
        if(this.getChildCount() > 0) {
            this.startNestedScroll(2, 1);
            this.mScroller.fling(this.getScrollX(), this.getScrollY(), 0, arg14, 0, 0, 0x80000000, 0x7FFFFFFF, 0, 0);
            this.mLastScrollerY = this.getScrollY();
            ViewCompat.postInvalidateOnAnimation(((View)this));
        }
    }

    private void flingWithNestedDispatch(int arg5) {
        boolean v0_1;
        int v0 = this.getScrollY();
        if(v0 > 0 || arg5 > 0) {
            if(v0 >= this.getScrollRange()) {
                if(arg5 < 0) {
                }
                else {
                label_7:
                    v0_1 = false;
                    goto label_10;
                }
            }

            v0_1 = true;
        }
        else {
            goto label_7;
        }

    label_10:
        float v1 = ((float)arg5);
        if(!this.dispatchNestedPreFling(0f, v1)) {
            this.dispatchNestedFling(0f, v1, v0_1);
            this.fling(arg5);
        }
    }

    public boolean fullScroll(int arg6) {
        int v2 = arg6 == 130 ? 1 : 0;
        int v3 = this.getHeight();
        this.mTempRect.top = 0;
        this.mTempRect.bottom = v3;
        if(v2 != 0) {
            int v0 = this.getChildCount();
            if(v0 > 0) {
                this.mTempRect.bottom = this.getChildAt(v0 - 1).getBottom() + this.getPaddingBottom();
                this.mTempRect.top = this.mTempRect.bottom - v3;
            }
        }

        return this.scrollAndFocus(arg6, this.mTempRect.top, this.mTempRect.bottom);
    }

    protected float getBottomFadingEdgeStrength() {
        if(this.getChildCount() == 0) {
            return 0;
        }

        int v0 = this.getVerticalFadingEdgeLength();
        int v2 = this.getChildAt(0).getBottom() - this.getScrollY() - (this.getHeight() - this.getPaddingBottom());
        if(v2 < v0) {
            return (((float)v2)) / (((float)v0));
        }

        return 1f;
    }

    public int getMaxScrollAmount() {
        return ((int)((((float)this.getHeight())) * 0.5f));
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    int getScrollRange() {
        int v1 = 0;
        if(this.getChildCount() > 0) {
            v1 = Math.max(0, this.getChildAt(0).getHeight() - (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop()));
        }

        return v1;
    }

    protected float getTopFadingEdgeStrength() {
        if(this.getChildCount() == 0) {
            return 0;
        }

        int v0 = this.getVerticalFadingEdgeLength();
        int v1 = this.getScrollY();
        if(v1 < v0) {
            return (((float)v1)) / (((float)v0));
        }

        return 1f;
    }

    private float getVerticalScrollFactorCompat() {
        if(this.mVerticalScrollFactor == 0f) {
            TypedValue v0 = new TypedValue();
            Context v1 = this.getContext();
            if(!v1.getTheme().resolveAttribute(0x101004D, v0, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            else {
                this.mVerticalScrollFactor = v0.getDimension(v1.getResources().getDisplayMetrics());
            }
        }

        return this.mVerticalScrollFactor;
    }

    public boolean hasNestedScrollingParent(int arg2) {
        return this.mChildHelper.hasNestedScrollingParent(arg2);
    }

    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    private boolean inChild(int arg5, int arg6) {
        boolean v1 = false;
        if(this.getChildCount() > 0) {
            int v0 = this.getScrollY();
            View v2 = this.getChildAt(0);
            if(arg6 >= v2.getTop() - v0 && arg6 < v2.getBottom() - v0 && arg5 >= v2.getLeft() && arg5 < v2.getRight()) {
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    private void initOrResetVelocityTracker() {
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        else {
            this.mVelocityTracker.clear();
        }
    }

    private void initScrollView() {
        this.mScroller = new OverScroller(this.getContext());
        this.setFocusable(true);
        this.setDescendantFocusability(0x40000);
        this.setWillNotDraw(false);
        ViewConfiguration v0 = ViewConfiguration.get(this.getContext());
        this.mTouchSlop = v0.getScaledTouchSlop();
        this.mMinimumVelocity = v0.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = v0.getScaledMaximumFlingVelocity();
    }

    private void initVelocityTrackerIfNotExists() {
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    public boolean isFillViewport() {
        return this.mFillViewport;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    private boolean isOffScreen(View arg3) {
        return this.isWithinDeltaOfScreen(arg3, 0, this.getHeight()) ^ 1;
    }

    public boolean isSmoothScrollingEnabled() {
        return this.mSmoothScrollingEnabled;
    }

    private static boolean isViewDescendantOf(View arg2, View arg3) {
        boolean v0 = true;
        if(arg2 == arg3) {
            return 1;
        }

        ViewParent v2 = arg2.getParent();
        if(!(v2 instanceof ViewGroup) || !NestedScrollView.isViewDescendantOf(((View)v2), arg3)) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    private boolean isWithinDeltaOfScreen(View arg2, int arg3, int arg4) {
        arg2.getDrawingRect(this.mTempRect);
        this.offsetDescendantRectToMyCoords(arg2, this.mTempRect);
        boolean v2 = this.mTempRect.bottom + arg3 < this.getScrollY() || this.mTempRect.top - arg3 > this.getScrollY() + arg4 ? false : true;
        return v2;
    }

    protected void measureChild(View arg3, int arg4, int arg5) {
        arg3.measure(NestedScrollView.getChildMeasureSpec(arg4, this.getPaddingLeft() + this.getPaddingRight(), arg3.getLayoutParams().width), View$MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View arg2, int arg3, int arg4, int arg5, int arg6) {
        ViewGroup$LayoutParams v5 = arg2.getLayoutParams();
        arg2.measure(NestedScrollView.getChildMeasureSpec(arg3, this.getPaddingLeft() + this.getPaddingRight() + ((ViewGroup$MarginLayoutParams)v5).leftMargin + ((ViewGroup$MarginLayoutParams)v5).rightMargin + arg4, ((ViewGroup$MarginLayoutParams)v5).width), View$MeasureSpec.makeMeasureSpec(((ViewGroup$MarginLayoutParams)v5).topMargin + ((ViewGroup$MarginLayoutParams)v5).bottomMargin, 0));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsLaidOut = false;
    }

    public boolean onGenericMotionEvent(MotionEvent arg4) {
        if((arg4.getSource() & 2) != 0) {
            if(arg4.getAction() != 8) {
            }
            else if(!this.mIsBeingDragged) {
                float v4 = arg4.getAxisValue(9);
                if(v4 != 0f) {
                    int v4_1 = ((int)(v4 * this.getVerticalScrollFactorCompat()));
                    int v0 = this.getScrollRange();
                    int v2 = this.getScrollY();
                    v4_1 = v2 - v4_1;
                    if(v4_1 < 0) {
                        v4_1 = 0;
                    }
                    else if(v4_1 > v0) {
                        v4_1 = v0;
                    }

                    if(v4_1 == v2) {
                        return 0;
                    }

                    super.scrollTo(this.getScrollX(), v4_1);
                    return 1;
                }
            }
        }

        return 0;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg13) {
        int v0 = arg13.getAction();
        int v1 = 2;
        if(v0 == v1 && (this.mIsBeingDragged)) {
            return 1;
        }

        v0 &= 0xFF;
        if(v0 != 6) {
            int v3 = -1;
            switch(v0) {
                case 0: {
                    goto label_65;
                }
                case 2: {
                    goto label_14;
                }
                case 1: 
                case 3: {
                    goto label_50;
                }
            }

            goto label_89;
        label_65:
            v0 = ((int)arg13.getY());
            if(!this.inChild(((int)arg13.getX()), v0)) {
                this.mIsBeingDragged = false;
                this.recycleVelocityTracker();
            }
            else {
                this.mLastMotionY = v0;
                this.mActivePointerId = arg13.getPointerId(0);
                this.initOrResetVelocityTracker();
                this.mVelocityTracker.addMovement(arg13);
                this.mScroller.computeScrollOffset();
                this.mIsBeingDragged = this.mScroller.isFinished() ^ 1;
                this.startNestedScroll(v1, 0);
                goto label_89;
            label_50:
                this.mIsBeingDragged = false;
                this.mActivePointerId = v3;
                this.recycleVelocityTracker();
                if(this.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                    ViewCompat.postInvalidateOnAnimation(((View)this));
                }

                this.stopNestedScroll(0);
                goto label_89;
            label_14:
                v0 = this.mActivePointerId;
                if(v0 == v3) {
                    goto label_89;
                }

                int v5 = arg13.findPointerIndex(v0);
                if(v5 == v3) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + v0 + " in onInterceptTouchEvent");
                    goto label_89;
                }

                v0 = ((int)arg13.getY(v5));
                if(Math.abs(v0 - this.mLastMotionY) <= this.mTouchSlop) {
                    goto label_89;
                }

                if((v1 & this.getNestedScrollAxes()) != 0) {
                    goto label_89;
                }

                this.mIsBeingDragged = true;
                this.mLastMotionY = v0;
                this.initVelocityTrackerIfNotExists();
                this.mVelocityTracker.addMovement(arg13);
                this.mNestedYOffset = 0;
                ViewParent v13 = this.getParent();
                if(v13 == null) {
                    goto label_89;
                }

                v13.requestDisallowInterceptTouchEvent(true);
            }
        }
        else {
            this.onSecondaryPointerUp(arg13);
        }

    label_89:
        return this.mIsBeingDragged;
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        super.onLayout(arg2, arg3, arg4, arg5, arg6);
        this.mIsLayoutDirty = false;
        if(this.mChildToScrollTo != null && (NestedScrollView.isViewDescendantOf(this.mChildToScrollTo, ((View)this)))) {
            this.scrollToChild(this.mChildToScrollTo);
        }

        View v3 = null;
        this.mChildToScrollTo = v3;
        if(!this.mIsLaidOut) {
            if(this.mSavedState != null) {
                this.scrollTo(this.getScrollX(), this.mSavedState.scrollPosition);
                this.mSavedState = ((SavedState)v3);
            }

            arg3 = this.getChildCount() > 0 ? this.getChildAt(0).getMeasuredHeight() : 0;
            arg3 = Math.max(0, arg3 - (arg6 - arg4 - this.getPaddingBottom() - this.getPaddingTop()));
            if(this.getScrollY() > arg3) {
                this.scrollTo(this.getScrollX(), arg3);
                goto label_43;
            }

            if(this.getScrollY() >= 0) {
                goto label_43;
            }

            this.scrollTo(this.getScrollX(), 0);
        }

    label_43:
        this.scrollTo(this.getScrollX(), this.getScrollY());
        this.mIsLaidOut = true;
    }

    protected void onMeasure(int arg5, int arg6) {
        super.onMeasure(arg5, arg6);
        if(!this.mFillViewport) {
            return;
        }

        if(View$MeasureSpec.getMode(arg6) == 0) {
            return;
        }

        if(this.getChildCount() > 0) {
            View v6 = this.getChildAt(0);
            int v0 = this.getMeasuredHeight();
            if(v6.getMeasuredHeight() < v0) {
                v6.measure(NestedScrollView.getChildMeasureSpec(arg5, this.getPaddingLeft() + this.getPaddingRight(), v6.getLayoutParams().width), View$MeasureSpec.makeMeasureSpec(v0 - this.getPaddingTop() - this.getPaddingBottom(), 0x40000000));
            }
        }
    }

    public boolean onNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
        if(!arg4) {
            this.flingWithNestedDispatch(((int)arg3));
            return 1;
        }

        return 0;
    }

    public boolean onNestedPreFling(View arg1, float arg2, float arg3) {
        return this.dispatchNestedPreFling(arg2, arg3);
    }

    public void onNestedPreScroll(View arg1, int arg2, int arg3, int[] arg4) {
        this.dispatchNestedPreScroll(arg2, arg3, arg4, null);
    }

    public void onNestedScroll(View arg7, int arg8, int arg9, int arg10, int arg11) {
        int v7 = this.getScrollY();
        this.scrollBy(0, arg11);
        int v2 = this.getScrollY() - v7;
        this.dispatchNestedScroll(0, v2, 0, arg11 - v2, null);
    }

    public void onNestedScrollAccepted(View arg2, View arg3, int arg4) {
        this.mParentHelper.onNestedScrollAccepted(arg2, arg3, arg4);
        this.startNestedScroll(2);
    }

    protected void onOverScrolled(int arg1, int arg2, boolean arg3, boolean arg4) {
        super.scrollTo(arg1, arg2);
    }

    protected boolean onRequestFocusInDescendants(int arg4, Rect arg5) {
        if(arg4 == 2) {
            arg4 = 130;
        }
        else if(arg4 == 1) {
            arg4 = 33;
        }

        View v0 = arg5 == null ? FocusFinder.getInstance().findNextFocus(((ViewGroup)this), null, arg4) : FocusFinder.getInstance().findNextFocusFromRect(((ViewGroup)this), arg5, arg4);
        if(v0 == null) {
            return 0;
        }

        if(this.isOffScreen(v0)) {
            return 0;
        }

        return v0.requestFocus(arg4, arg5);
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        this.mSavedState = ((SavedState)arg2);
        this.requestLayout();
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.scrollPosition = this.getScrollY();
        return ((Parcelable)v1);
    }

    protected void onScrollChanged(int arg8, int arg9, int arg10, int arg11) {
        super.onScrollChanged(arg8, arg9, arg10, arg11);
        if(this.mOnScrollChangeListener != null) {
            this.mOnScrollChangeListener.onScrollChange(this, arg8, arg9, arg10, arg11);
        }
    }

    private void onSecondaryPointerUp(MotionEvent arg4) {
        int v0 = arg4.getActionIndex();
        if(arg4.getPointerId(v0) == this.mActivePointerId) {
            v0 = v0 == 0 ? 1 : 0;
            this.mLastMotionY = ((int)arg4.getY(v0));
            this.mActivePointerId = arg4.getPointerId(v0);
            if(this.mVelocityTracker == null) {
                return;
            }

            this.mVelocityTracker.clear();
        }
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        View v1 = this.findFocus();
        if(v1 != null) {
            if(this == (((NestedScrollView)v1))) {
            }
            else {
                if(this.isWithinDeltaOfScreen(v1, 0, arg4)) {
                    v1.getDrawingRect(this.mTempRect);
                    this.offsetDescendantRectToMyCoords(v1, this.mTempRect);
                    this.doScrollY(this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
                }

                return;
            }
        }
    }

    public boolean onStartNestedScroll(View arg1, View arg2, int arg3) {
        boolean v1 = (arg3 & 2) != 0 ? true : false;
        return v1;
    }

    public void onStopNestedScroll(View arg2) {
        this.mParentHelper.onStopNestedScroll(arg2);
        this.stopNestedScroll();
    }

    public boolean onTouchEvent(MotionEvent arg24) {
        ViewParent v0_1;
        int v17;
        NestedScrollView v10 = this;
        MotionEvent v11 = arg24;
        this.initVelocityTrackerIfNotExists();
        MotionEvent v12 = MotionEvent.obtain(arg24);
        int v0 = arg24.getActionMasked();
        if(v0 == 0) {
            v10.mNestedYOffset = 0;
        }

        v12.offsetLocation(0f, ((float)v10.mNestedYOffset));
        int v1 = -1;
        switch(v0) {
            case 0: {
                if(this.getChildCount() == 0) {
                    return 0;
                }

                v0 = v10.mScroller.isFinished() ^ 1;
                v10.mIsBeingDragged = ((boolean)v0);
                if(v0 != 0) {
                    v0_1 = this.getParent();
                    if(v0_1 != null) {
                        v0_1.requestDisallowInterceptTouchEvent(true);
                    }
                }

                if(!v10.mScroller.isFinished()) {
                    v10.mScroller.abortAnimation();
                }

                v10.mLastMotionY = ((int)arg24.getY());
                v10.mActivePointerId = v11.getPointerId(0);
                v10.startNestedScroll(2, 0);
                break;
            }
            case 1: {
                VelocityTracker v0_2 = v10.mVelocityTracker;
                v0_2.computeCurrentVelocity(1000, ((float)v10.mMaximumVelocity));
                v0 = ((int)v0_2.getYVelocity(v10.mActivePointerId));
                if(Math.abs(v0) > v10.mMinimumVelocity) {
                    v10.flingWithNestedDispatch(-v0);
                }
                else if(v10.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                    ViewCompat.postInvalidateOnAnimation(((View)this));
                }

                v10.mActivePointerId = v1;
                this.endDrag();
                break;
            }
            case 2: {
                int v9 = v11.findPointerIndex(v10.mActivePointerId);
                if(v9 == v1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + v10.mActivePointerId + " in onTouchEvent");
                    goto label_267;
                }

                int v6 = ((int)v11.getY(v9));
                int v7 = v10.mLastMotionY - v6;
                if(v10.dispatchNestedPreScroll(0, v7, v10.mScrollConsumed, v10.mScrollOffset, 0)) {
                    v7 -= v10.mScrollConsumed[1];
                    v12.offsetLocation(0f, ((float)v10.mScrollOffset[1]));
                    v10.mNestedYOffset += v10.mScrollOffset[1];
                }

                if(!v10.mIsBeingDragged && Math.abs(v7) > v10.mTouchSlop) {
                    v0_1 = this.getParent();
                    if(v0_1 != null) {
                        v0_1.requestDisallowInterceptTouchEvent(true);
                    }

                    v10.mIsBeingDragged = true;
                    if(v7 > 0) {
                        v7 -= v10.mTouchSlop;
                        goto label_101;
                    }

                    v7 += v10.mTouchSlop;
                }

            label_101:
                int v8 = v7;
                if(!v10.mIsBeingDragged) {
                    goto label_267;
                }

                v10.mLastMotionY = v6 - v10.mScrollOffset[1];
                int v16 = this.getScrollY();
                v7 = this.getScrollRange();
                v0 = this.getOverScrollMode();
                if(v0 != 0) {
                    if(v0 == 1 && v7 > 0) {
                        goto label_117;
                    }

                    v17 = 0;
                }
                else {
                label_117:
                    v17 = 1;
                }

                int v21 = v7;
                int v14 = v8;
                int v22 = v9;
                if((v10.overScrollByCompat(0, v8, 0, this.getScrollY(), 0, v7, 0, 0, true)) && !v10.hasNestedScrollingParent(0)) {
                    v10.mVelocityTracker.clear();
                }

                int v2 = this.getScrollY() - v16;
                if(v10.dispatchNestedScroll(0, v2, 0, v14 - v2, v10.mScrollOffset, 0)) {
                    v10.mLastMotionY -= v10.mScrollOffset[1];
                    v12.offsetLocation(0f, ((float)v10.mScrollOffset[1]));
                    v10.mNestedYOffset += v10.mScrollOffset[1];
                    goto label_267;
                }

                if(v17 == 0) {
                    goto label_267;
                }

                this.ensureGlows();
                v0 = v16 + v14;
                if(v0 < 0) {
                    EdgeEffectCompat.onPull(v10.mEdgeGlowTop, (((float)v14)) / (((float)this.getHeight())), v11.getX(v22) / (((float)this.getWidth())));
                    if(!v10.mEdgeGlowBottom.isFinished()) {
                        v10.mEdgeGlowBottom.onRelease();
                    }
                }
                else {
                    v2 = v22;
                    if(v0 > v21) {
                        EdgeEffectCompat.onPull(v10.mEdgeGlowBottom, (((float)v14)) / (((float)this.getHeight())), 1f - v11.getX(v2) / (((float)this.getWidth())));
                        if(!v10.mEdgeGlowTop.isFinished()) {
                            v10.mEdgeGlowTop.onRelease();
                        }
                    }
                }

                if(v10.mEdgeGlowTop == null) {
                    goto label_267;
                }

                if((v10.mEdgeGlowTop.isFinished()) && (v10.mEdgeGlowBottom.isFinished())) {
                    goto label_267;
                }

                ViewCompat.postInvalidateOnAnimation(((View)this));
                break;
            }
            case 3: {
                if((v10.mIsBeingDragged) && this.getChildCount() > 0 && (v10.mScroller.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange()))) {
                    ViewCompat.postInvalidateOnAnimation(((View)this));
                }

                v10.mActivePointerId = v1;
                this.endDrag();
                break;
            }
            case 5: {
                v0 = arg24.getActionIndex();
                v10.mLastMotionY = ((int)v11.getY(v0));
                v10.mActivePointerId = v11.getPointerId(v0);
                break;
            }
            case 6: {
                this.onSecondaryPointerUp(arg24);
                v10.mLastMotionY = ((int)v11.getY(v11.findPointerIndex(v10.mActivePointerId)));
                break;
            }
            default: {
                break;
            }
        }

    label_267:
        if(v10.mVelocityTracker != null) {
            v10.mVelocityTracker.addMovement(v12);
        }

        v12.recycle();
        return 1;
    }

    boolean overScrollByCompat(int arg17, int arg18, int arg19, int arg20, int arg21, int arg22, int arg23, int arg24, boolean arg25) {
        boolean v2_1;
        boolean v1_1;
        int v6;
        NestedScrollView v0 = this;
        int v1 = this.getOverScrollMode();
        boolean v4 = false;
        int v2 = this.computeHorizontalScrollRange() > this.computeHorizontalScrollExtent() ? 1 : 0;
        int v3 = this.computeVerticalScrollRange() > this.computeVerticalScrollExtent() ? 1 : 0;
        if(v1 != 0) {
            if(v1 == 1 && v2 != 0) {
                goto label_22;
            }

            v2 = 0;
        }
        else {
        label_22:
            v2 = 1;
        }

        if(v1 != 0) {
            if(v1 == 1 && v3 != 0) {
                goto label_29;
            }

            v6 = 0;
        }
        else {
        label_29:
            v6 = 1;
        }

        v1 = arg19 + arg17;
        int v7 = v2 == 0 ? 0 : arg23;
        v2 = arg20 + arg18;
        v3 = v6 == 0 ? 0 : arg24;
        v6 = -v7;
        v7 += arg21;
        int v8 = -v3;
        v3 += arg22;
        if(v1 > v7) {
            v6 = v7;
            goto label_46;
        }
        else if(v1 < v6) {
        label_46:
            v1_1 = true;
        }
        else {
            v6 = v1;
            v1_1 = false;
        }

        if(v2 > v3) {
            v8 = v3;
            goto label_54;
        }
        else if(v2 < v8) {
        label_54:
            v2_1 = true;
        }
        else {
            v8 = v2;
            v2_1 = false;
        }

        if((v2_1) && !v0.hasNestedScrollingParent(1)) {
            v0.mScroller.springBack(v6, v8, 0, 0, 0, this.getScrollRange());
        }

        v0.onOverScrolled(v6, v8, v1_1, v2_1);
        if((v1_1) || (v2_1)) {
            v4 = true;
        }

        return v4;
    }

    public boolean pageScroll(int arg5) {
        int v2 = arg5 == 130 ? 1 : 0;
        int v3 = this.getHeight();
        if(v2 != 0) {
            this.mTempRect.top = this.getScrollY() + v3;
            int v0 = this.getChildCount();
            if(v0 > 0) {
                View v0_1 = this.getChildAt(v0 - 1);
                if(this.mTempRect.top + v3 > v0_1.getBottom()) {
                    this.mTempRect.top = v0_1.getBottom() - v3;
                }
            }
        }
        else {
            this.mTempRect.top = this.getScrollY() - v3;
            if(this.mTempRect.top < 0) {
                this.mTempRect.top = 0;
            }
        }

        this.mTempRect.bottom = this.mTempRect.top + v3;
        return this.scrollAndFocus(arg5, this.mTempRect.top, this.mTempRect.bottom);
    }

    private void recycleVelocityTracker() {
        if(this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void requestChildFocus(View arg2, View arg3) {
        if(!this.mIsLayoutDirty) {
            this.scrollToChild(arg3);
        }
        else {
            this.mChildToScrollTo = arg3;
        }

        super.requestChildFocus(arg2, arg3);
    }

    public boolean requestChildRectangleOnScreen(View arg3, Rect arg4, boolean arg5) {
        arg4.offset(arg3.getLeft() - arg3.getScrollX(), arg3.getTop() - arg3.getScrollY());
        return this.scrollToChildRect(arg4, arg5);
    }

    public void requestDisallowInterceptTouchEvent(boolean arg1) {
        if(arg1) {
            this.recycleVelocityTracker();
        }

        super.requestDisallowInterceptTouchEvent(arg1);
    }

    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    private boolean scrollAndFocus(int arg7, int arg8, int arg9) {
        NestedScrollView v5_1;
        int v0 = this.getHeight();
        int v1 = this.getScrollY();
        v0 += v1;
        boolean v2 = false;
        boolean v4 = arg7 == 33 ? true : false;
        View v5 = this.findFocusableViewInBounds(v4, arg8, arg9);
        if(v5 == null) {
            v5_1 = this;
        }

        if(arg8 < v1 || arg9 > v0) {
            if(v4) {
                arg8 -= v1;
            }
            else {
                arg8 = arg9 - v0;
            }

            this.doScrollY(arg8);
            v2 = true;
        }
        else {
        }

        if((((View)v5_1)) != this.findFocus()) {
            ((View)v5_1).requestFocus(arg7);
        }

        return v2;
    }

    public void scrollTo(int arg4, int arg5) {
        if(this.getChildCount() > 0) {
            View v0 = this.getChildAt(0);
            arg4 = NestedScrollView.clamp(arg4, this.getWidth() - this.getPaddingRight() - this.getPaddingLeft(), v0.getWidth());
            arg5 = NestedScrollView.clamp(arg5, this.getHeight() - this.getPaddingBottom() - this.getPaddingTop(), v0.getHeight());
            if(arg4 == this.getScrollX() && arg5 == this.getScrollY()) {
                return;
            }

            super.scrollTo(arg4, arg5);
        }
    }

    private void scrollToChild(View arg2) {
        arg2.getDrawingRect(this.mTempRect);
        this.offsetDescendantRectToMyCoords(arg2, this.mTempRect);
        int v2 = this.computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
        if(v2 != 0) {
            this.scrollBy(0, v2);
        }
    }

    private boolean scrollToChildRect(Rect arg3, boolean arg4) {
        int v3 = this.computeScrollDeltaToGetChildRectOnScreen(arg3);
        boolean v1 = v3 != 0 ? true : false;
        if(v1) {
            if(arg4) {
                this.scrollBy(0, v3);
            }
            else {
                this.smoothScrollBy(0, v3);
            }
        }

        return v1;
    }

    public void setFillViewport(boolean arg2) {
        if(arg2 != this.mFillViewport) {
            this.mFillViewport = arg2;
            this.requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean arg2) {
        this.mChildHelper.setNestedScrollingEnabled(arg2);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener arg1) {
        this.mOnScrollChangeListener = arg1;
    }

    public void setSmoothScrollingEnabled(boolean arg1) {
        this.mSmoothScrollingEnabled = arg1;
    }

    public boolean shouldDelayChildPressedState() {
        return 1;
    }

    public final void smoothScrollBy(int arg7, int arg8) {
        if(this.getChildCount() == 0) {
            return;
        }

        if(AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 0xFA) {
            arg7 = Math.max(0, this.getChildAt(0).getHeight() - (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop()));
            int v1 = this.getScrollY();
            this.mScroller.startScroll(this.getScrollX(), v1, 0, Math.max(0, Math.min(arg8 + v1, arg7)) - v1);
            ViewCompat.postInvalidateOnAnimation(((View)this));
        }
        else {
            if(!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
            }

            this.scrollBy(arg7, arg8);
        }

        this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
    }

    public final void smoothScrollTo(int arg2, int arg3) {
        this.smoothScrollBy(arg2 - this.getScrollX(), arg3 - this.getScrollY());
    }

    public boolean startNestedScroll(int arg2, int arg3) {
        return this.mChildHelper.startNestedScroll(arg2, arg3);
    }

    public boolean startNestedScroll(int arg2) {
        return this.mChildHelper.startNestedScroll(arg2);
    }

    public void stopNestedScroll(int arg2) {
        this.mChildHelper.stopNestedScroll(arg2);
    }

    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }
}

