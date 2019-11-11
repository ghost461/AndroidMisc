package android.support.v4.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnApplyWindowInsetsListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;

        AccessibilityDelegate(DrawerLayout arg1) {
            DrawerLayout.this = arg1;
            super();
            this.mTmpRect = new Rect();
        }

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat arg5, ViewGroup arg6) {
            int v0 = arg6.getChildCount();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                View v2 = arg6.getChildAt(v1);
                if(DrawerLayout.includeChildForAccessibility(v2)) {
                    arg5.addChild(v2);
                }
            }
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
        }

        public boolean dispatchPopulateAccessibilityEvent(View arg3, AccessibilityEvent arg4) {
            if(arg4.getEventType() == 0x20) {
                List v3 = arg4.getText();
                View v4 = DrawerLayout.this.findVisibleDrawer();
                if(v4 != null) {
                    CharSequence v4_1 = DrawerLayout.this.getDrawerTitle(DrawerLayout.this.getDrawerViewAbsoluteGravity(v4));
                    if(v4_1 != null) {
                        v3.add(v4_1);
                    }
                }

                return 1;
            }

            return super.dispatchPopulateAccessibilityEvent(arg3, arg4);
        }

        public void onInitializeAccessibilityEvent(View arg1, AccessibilityEvent arg2) {
            super.onInitializeAccessibilityEvent(arg1, arg2);
            arg2.setClassName(DrawerLayout.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View arg4, AccessibilityNodeInfoCompat arg5) {
            if(DrawerLayout.CAN_HIDE_DESCENDANTS) {
                super.onInitializeAccessibilityNodeInfo(arg4, arg5);
            }
            else {
                AccessibilityNodeInfoCompat v0 = AccessibilityNodeInfoCompat.obtain(arg5);
                super.onInitializeAccessibilityNodeInfo(arg4, v0);
                arg5.setSource(arg4);
                ViewParent v1 = ViewCompat.getParentForAccessibility(arg4);
                if((v1 instanceof View)) {
                    arg5.setParent(((View)v1));
                }

                this.copyNodeInfoNoChildren(arg5, v0);
                v0.recycle();
                this.addChildrenForAccessibility(arg5, ((ViewGroup)arg4));
            }

            arg5.setClassName(DrawerLayout.class.getName());
            arg5.setFocusable(false);
            arg5.setFocused(false);
            arg5.removeAction(AccessibilityActionCompat.ACTION_FOCUS);
            arg5.removeAction(AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup arg2, View arg3, AccessibilityEvent arg4) {
            if(!DrawerLayout.CAN_HIDE_DESCENDANTS) {
                if(DrawerLayout.includeChildForAccessibility(arg3)) {
                }
                else {
                    return 0;
                }
            }

            return super.onRequestSendAccessibilityEvent(arg2, arg3, arg4);
        }
    }

    final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        ChildAccessibilityDelegate() {
            super();
        }

        public void onInitializeAccessibilityNodeInfo(View arg1, AccessibilityNodeInfoCompat arg2) {
            super.onInitializeAccessibilityNodeInfo(arg1, arg2);
            if(!DrawerLayout.includeChildForAccessibility(arg1)) {
                arg2.setParent(null);
            }
        }
    }

    public interface DrawerListener {
        void onDrawerClosed(View arg1);

        void onDrawerOpened(View arg1);

        void onDrawerSlide(View arg1, float arg2);

        void onDrawerStateChanged(int arg1);
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface EdgeGravity {
    }

    public class LayoutParams extends ViewGroup$MarginLayoutParams {
        private static final int FLAG_IS_CLOSING = 4;
        private static final int FLAG_IS_OPENED = 1;
        private static final int FLAG_IS_OPENING = 2;
        public int gravity;
        boolean isPeeking;
        float onScreen;
        int openState;

        public LayoutParams(int arg1, int arg2) {
            super(arg1, arg2);
            this.gravity = 0;
        }

        public LayoutParams(Context arg3, AttributeSet arg4) {
            super(arg3, arg4);
            this.gravity = 0;
            TypedArray v3 = arg3.obtainStyledAttributes(arg4, DrawerLayout.LAYOUT_ATTRS);
            this.gravity = v3.getInt(0, 0);
            v3.recycle();
        }

        public LayoutParams(LayoutParams arg2) {
            super(((ViewGroup$MarginLayoutParams)arg2));
            this.gravity = 0;
            this.gravity = arg2.gravity;
        }

        public LayoutParams(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
            this.gravity = 0;
        }

        public LayoutParams(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.gravity = 0;
        }

        public LayoutParams(int arg1, int arg2, int arg3) {
            this(arg1, arg2);
            this.gravity = arg3;
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface LockMode {
    }

    public class SavedState extends AbsSavedState {
        final class android.support.v4.widget.DrawerLayout$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v4.widget.DrawerLayout$SavedState$1() {
                super();
            }

            public SavedState createFromParcel(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState createFromParcel(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
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
        int lockModeEnd;
        int lockModeLeft;
        int lockModeRight;
        int lockModeStart;
        int openDrawerGravity;

        static {
            SavedState.CREATOR = new android.support.v4.widget.DrawerLayout$SavedState$1();
        }

        public SavedState(Parcelable arg1) {
            super(arg1);
            this.openDrawerGravity = 0;
        }

        public SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.openDrawerGravity = 0;
            this.openDrawerGravity = arg1.readInt();
            this.lockModeLeft = arg1.readInt();
            this.lockModeRight = arg1.readInt();
            this.lockModeStart = arg1.readInt();
            this.lockModeEnd = arg1.readInt();
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.openDrawerGravity);
            arg1.writeInt(this.lockModeLeft);
            arg1.writeInt(this.lockModeRight);
            arg1.writeInt(this.lockModeStart);
            arg1.writeInt(this.lockModeEnd);
        }
    }

    public abstract class SimpleDrawerListener implements DrawerListener {
        public SimpleDrawerListener() {
            super();
        }

        public void onDrawerClosed(View arg1) {
        }

        public void onDrawerOpened(View arg1) {
        }

        public void onDrawerSlide(View arg1, float arg2) {
        }

        public void onDrawerStateChanged(int arg1) {
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface State {
    }

    class ViewDragCallback extends Callback {
        class android.support.v4.widget.DrawerLayout$ViewDragCallback$1 implements Runnable {
            android.support.v4.widget.DrawerLayout$ViewDragCallback$1(ViewDragCallback arg1) {
                this.this$1 = arg1;
                super();
            }

            public void run() {
                this.this$1.peekDrawer();
            }
        }

        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable;

        ViewDragCallback(DrawerLayout arg1, int arg2) {
            DrawerLayout.this = arg1;
            super();
            this.mPeekRunnable = new android.support.v4.widget.DrawerLayout$ViewDragCallback$1(this);
            this.mAbsGravity = arg2;
        }

        public int clampViewPositionHorizontal(View arg2, int arg3, int arg4) {
            if(DrawerLayout.this.checkDrawerViewAbsoluteGravity(arg2, 3)) {
                return Math.max(-arg2.getWidth(), Math.min(arg3, 0));
            }

            arg4 = DrawerLayout.this.getWidth();
            return Math.max(arg4 - arg2.getWidth(), Math.min(arg3, arg4));
        }

        public int clampViewPositionVertical(View arg1, int arg2, int arg3) {
            return arg1.getTop();
        }

        private void closeOtherDrawer() {
            int v1 = 3;
            if(this.mAbsGravity == v1) {
                v1 = 5;
            }

            View v0 = DrawerLayout.this.findDrawerWithGravity(v1);
            if(v0 != null) {
                DrawerLayout.this.closeDrawer(v0);
            }
        }

        public int getViewHorizontalDragRange(View arg2) {
            int v2 = DrawerLayout.this.isDrawerView(arg2) ? arg2.getWidth() : 0;
            return v2;
        }

        public void onEdgeDragStarted(int arg2, int arg3) {
            View v2 = (arg2 & 1) == 1 ? DrawerLayout.this.findDrawerWithGravity(3) : DrawerLayout.this.findDrawerWithGravity(5);
            if(v2 != null && DrawerLayout.this.getDrawerLockMode(v2) == 0) {
                this.mDragger.captureChildView(v2, arg3);
            }
        }

        public boolean onEdgeLock(int arg1) {
            return 0;
        }

        public void onEdgeTouched(int arg3, int arg4) {
            DrawerLayout.this.postDelayed(this.mPeekRunnable, 0xA0);
        }

        public void onViewCaptured(View arg1, int arg2) {
            arg1.getLayoutParams().isPeeking = false;
            this.closeOtherDrawer();
        }

        public void onViewDragStateChanged(int arg4) {
            DrawerLayout.this.updateDrawerState(this.mAbsGravity, arg4, this.mDragger.getCapturedView());
        }

        public void onViewPositionChanged(View arg1, int arg2, int arg3, int arg4, int arg5) {
            arg3 = arg1.getWidth();
            float v2 = DrawerLayout.this.checkDrawerViewAbsoluteGravity(arg1, 3) ? (((float)(arg2 + arg3))) / (((float)arg3)) : (((float)(DrawerLayout.this.getWidth() - arg2))) / (((float)arg3));
            DrawerLayout.this.setDrawerViewOffset(arg1, v2);
            arg2 = v2 == 0f ? 4 : 0;
            arg1.setVisibility(arg2);
            DrawerLayout.this.invalidate();
        }

        public void onViewReleased(View arg6, float arg7, float arg8) {
            int v7;
            arg8 = DrawerLayout.this.getDrawerViewOffset(arg6);
            int v0 = arg6.getWidth();
            float v2 = 0.5f;
            if(DrawerLayout.this.checkDrawerViewAbsoluteGravity(arg6, 3)) {
                if(arg7 <= 0f && (arg7 != 0f || arg8 <= v2)) {
                    v7 = -v0;
                    goto label_24;
                }

                v7 = 0;
            }
            else {
                int v1 = DrawerLayout.this.getWidth();
                if(arg7 < 0f || arg7 == 0f && arg8 > v2) {
                    v1 -= v0;
                }

                v7 = v1;
            }

        label_24:
            this.mDragger.settleCapturedViewAt(v7, arg6.getTop());
            DrawerLayout.this.invalidate();
        }

        void peekDrawer() {
            View v4_1;
            int v0 = this.mDragger.getEdgeSize();
            int v2 = 0;
            int v4 = 3;
            int v1 = this.mAbsGravity == v4 ? 1 : 0;
            if(v1 != 0) {
                v4_1 = DrawerLayout.this.findDrawerWithGravity(v4);
                if(v4_1 != null) {
                    v2 = -v4_1.getWidth();
                }

                v2 += v0;
            }
            else {
                v4_1 = DrawerLayout.this.findDrawerWithGravity(5);
                v2 = DrawerLayout.this.getWidth() - v0;
            }

            if(v4_1 != null) {
                if(v1 == 0 || v4_1.getLeft() >= v2) {
                    if(v1 != 0) {
                    }
                    else if(v4_1.getLeft() > v2) {
                        goto label_31;
                    }

                    return;
                }

            label_31:
                if(DrawerLayout.this.getDrawerLockMode(v4_1) != 0) {
                    return;
                }

                ViewGroup$LayoutParams v0_1 = v4_1.getLayoutParams();
                this.mDragger.smoothSlideViewTo(v4_1, v2, v4_1.getTop());
                ((LayoutParams)v0_1).isPeeking = true;
                DrawerLayout.this.invalidate();
                this.closeOtherDrawer();
                DrawerLayout.this.cancelChildViewTouch();
            }
        }

        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
        }

        public void setDragger(ViewDragHelper arg1) {
            this.mDragger = arg1;
        }

        public boolean tryCaptureView(View arg2, int arg3) {
            boolean v2 = !DrawerLayout.this.isDrawerView(arg2) || !DrawerLayout.this.checkDrawerViewAbsoluteGravity(arg2, this.mAbsGravity) || DrawerLayout.this.getDrawerLockMode(arg2) != 0 ? false : true;
            return v2;
        }
    }

    private static final boolean ALLOW_EDGE_LOCK = false;
    static final boolean CAN_HIDE_DESCENDANTS = false;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
    private static final int DRAWER_ELEVATION = 10;
    static final int[] LAYOUT_ATTRS = null;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 0x40;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 0xA0;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = false;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final int[] THEME_ATTRS = null;
    private static final float TOUCH_SLOP_SENSITIVITY = 1f;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    @Nullable private DrawerListener mListener;
    private List mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    static {
        boolean v0 = true;
        DrawerLayout.THEME_ATTRS = new int[]{0x1010434};
        DrawerLayout.LAYOUT_ATTRS = new int[]{0x10100B3};
        boolean v1 = Build$VERSION.SDK_INT >= 19 ? true : false;
        DrawerLayout.CAN_HIDE_DESCENDANTS = v1;
        if(Build$VERSION.SDK_INT >= 21) {
        }
        else {
            v0 = false;
        }

        DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION = v0;
    }

    public DrawerLayout(Context arg2) {
        this(arg2, null);
    }

    public DrawerLayout(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public DrawerLayout(Context arg6, AttributeSet arg7, int arg8) {
        super(arg6, arg7, arg8);
        this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        this.mScrimColor = 0x99000000;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = true;
        this.mLockModeLeft = 3;
        this.mLockModeRight = 3;
        this.mLockModeStart = 3;
        this.mLockModeEnd = 3;
        Drawable v0 = null;
        this.mShadowStart = v0;
        this.mShadowEnd = v0;
        this.mShadowLeft = v0;
        this.mShadowRight = v0;
        this.setDescendantFocusability(0x40000);
        float v1 = this.getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = ((int)(64f * v1 + 0.5f));
        float v2 = 400f * v1;
        this.mLeftCallback = new ViewDragCallback(this, 3);
        this.mRightCallback = new ViewDragCallback(this, 5);
        this.mLeftDragger = ViewDragHelper.create(((ViewGroup)this), 1f, this.mLeftCallback);
        this.mLeftDragger.setEdgeTrackingEnabled(1);
        this.mLeftDragger.setMinVelocity(v2);
        this.mLeftCallback.setDragger(this.mLeftDragger);
        this.mRightDragger = ViewDragHelper.create(((ViewGroup)this), 1f, this.mRightCallback);
        this.mRightDragger.setEdgeTrackingEnabled(2);
        this.mRightDragger.setMinVelocity(v2);
        this.mRightCallback.setDragger(this.mRightDragger);
        this.setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(((View)this), 1);
        ViewCompat.setAccessibilityDelegate(((View)this), new AccessibilityDelegate(this));
        ViewGroupCompat.setMotionEventSplittingEnabled(((ViewGroup)this), false);
        if(ViewCompat.getFitsSystemWindows(((View)this))) {
            if(Build$VERSION.SDK_INT >= 21) {
                this.setOnApplyWindowInsetsListener(new View$OnApplyWindowInsetsListener() {
                    @TargetApi(value=21) public WindowInsets onApplyWindowInsets(View arg2, WindowInsets arg3) {
                        boolean v0 = arg3.getSystemWindowInsetTop() > 0 ? true : false;
                        ((DrawerLayout)arg2).setChildInsets(arg3, v0);
                        return arg3.consumeSystemWindowInsets();
                    }
                });
                this.setSystemUiVisibility(0x500);
                TypedArray v6 = arg6.obtainStyledAttributes(DrawerLayout.THEME_ATTRS);
                try {
                    this.mStatusBarBackground = v6.getDrawable(0);
                }
                catch(Throwable v7) {
                    v6.recycle();
                    throw v7;
                }

                v6.recycle();
            }
            else {
                this.mStatusBarBackground = v0;
            }
        }

        this.mDrawerElevation = v1 * 10f;
        this.mNonDrawerViews = new ArrayList();
    }

    public void addDrawerListener(@NonNull DrawerListener arg2) {
        if(arg2 == null) {
            return;
        }

        if(this.mListeners == null) {
            this.mListeners = new ArrayList();
        }

        this.mListeners.add(arg2);
    }

    public void addFocusables(ArrayList arg7, int arg8, int arg9) {
        if(this.getDescendantFocusability() == 0x60000) {
            return;
        }

        int v0 = this.getChildCount();
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            View v4 = this.getChildAt(v2);
            if(!this.isDrawerView(v4)) {
                this.mNonDrawerViews.add(v4);
            }
            else if(this.isDrawerOpen(v4)) {
                v4.addFocusables(arg7, arg8, arg9);
                v3 = 1;
            }

            ++v2;
        }

        if(v3 == 0) {
            v0 = this.mNonDrawerViews.size();
            while(v1 < v0) {
                Object v2_1 = this.mNonDrawerViews.get(v1);
                if(((View)v2_1).getVisibility() == 0) {
                    ((View)v2_1).addFocusables(arg7, arg8, arg9);
                }

                ++v1;
            }
        }

        this.mNonDrawerViews.clear();
    }

    public void addView(View arg1, int arg2, ViewGroup$LayoutParams arg3) {
        super.addView(arg1, arg2, arg3);
        if(this.findOpenDrawer() != null || (this.isDrawerView(arg1))) {
            ViewCompat.setImportantForAccessibility(arg1, 4);
        }
        else {
            ViewCompat.setImportantForAccessibility(arg1, 1);
        }

        if(!DrawerLayout.CAN_HIDE_DESCENDANTS) {
            ViewCompat.setAccessibilityDelegate(arg1, this.mChildAccessibilityDelegate);
        }
    }

    void cancelChildViewTouch() {
        if(!this.mChildrenCanceledTouch) {
            long v3 = SystemClock.uptimeMillis();
            MotionEvent v0 = MotionEvent.obtain(v3, v3, 3, 0f, 0f, 0);
            int v1 = this.getChildCount();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                this.getChildAt(v2).dispatchTouchEvent(v0);
            }

            v0.recycle();
            this.mChildrenCanceledTouch = true;
        }
    }

    boolean checkDrawerViewAbsoluteGravity(View arg1, int arg2) {
        boolean v1 = (this.getDrawerViewAbsoluteGravity(arg1) & arg2) == arg2 ? true : false;
        return v1;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg2) {
        boolean v2 = !(arg2 instanceof LayoutParams) || !super.checkLayoutParams(arg2) ? false : true;
        return v2;
    }

    public void closeDrawer(int arg2) {
        this.closeDrawer(arg2, true);
    }

    public void closeDrawer(int arg3, boolean arg4) {
        View v0 = this.findDrawerWithGravity(arg3);
        if(v0 == null) {
            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("No drawer view found with gravity ");
            v0_1.append(DrawerLayout.gravityToString(arg3));
            throw new IllegalArgumentException(v0_1.toString());
        }

        this.closeDrawer(v0, arg4);
    }

    public void closeDrawer(View arg5, boolean arg6) {
        if(!this.isDrawerView(arg5)) {
            StringBuilder v0 = new StringBuilder();
            v0.append("View ");
            v0.append(arg5);
            v0.append(" is not a sliding drawer");
            throw new IllegalArgumentException(v0.toString());
        }

        ViewGroup$LayoutParams v0_1 = arg5.getLayoutParams();
        if(this.mFirstLayout) {
            ((LayoutParams)v0_1).onScreen = 0f;
            ((LayoutParams)v0_1).openState = 0;
        }
        else {
            int v1 = 4;
            if(arg6) {
                ((LayoutParams)v0_1).openState |= v1;
                if(this.checkDrawerViewAbsoluteGravity(arg5, 3)) {
                    this.mLeftDragger.smoothSlideViewTo(arg5, -arg5.getWidth(), arg5.getTop());
                }
                else {
                    this.mRightDragger.smoothSlideViewTo(arg5, this.getWidth(), arg5.getTop());
                }
            }
            else {
                this.moveDrawerToOffset(arg5, 0f);
                this.updateDrawerState(((LayoutParams)v0_1).gravity, 0, arg5);
                arg5.setVisibility(v1);
            }
        }

        this.invalidate();
    }

    public void closeDrawer(View arg2) {
        this.closeDrawer(arg2, true);
    }

    public void closeDrawers() {
        this.closeDrawers(false);
    }

    void closeDrawers(boolean arg10) {
        int v0 = this.getChildCount();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            View v4 = this.getChildAt(v2);
            ViewGroup$LayoutParams v5 = v4.getLayoutParams();
            if((this.isDrawerView(v4)) && (!arg10 || (((LayoutParams)v5).isPeeking))) {
                int v6 = v4.getWidth();
                v3 |= this.checkDrawerViewAbsoluteGravity(v4, 3) ? this.mLeftDragger.smoothSlideViewTo(v4, -v6, v4.getTop()) : this.mRightDragger.smoothSlideViewTo(v4, this.getWidth(), v4.getTop());
                ((LayoutParams)v5).isPeeking = false;
            }

            ++v2;
        }

        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if(v3 != 0) {
            this.invalidate();
        }
    }

    public void computeScroll() {
        int v0 = this.getChildCount();
        float v1 = 0f;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1 = Math.max(v1, this.getChildAt(v2).getLayoutParams().onScreen);
        }

        this.mScrimOpacity = v1;
        boolean v0_1 = this.mLeftDragger.continueSettling(true);
        boolean v1_1 = this.mRightDragger.continueSettling(true);
        if((v0_1) || (v1_1)) {
            ViewCompat.postInvalidateOnAnimation(((View)this));
        }
    }

    void dispatchOnDrawerClosed(View arg4) {
        ViewGroup$LayoutParams v0 = arg4.getLayoutParams();
        if((((LayoutParams)v0).openState & 1) == 1) {
            ((LayoutParams)v0).openState = 0;
            if(this.mListeners != null) {
                int v0_1;
                for(v0_1 = this.mListeners.size() - 1; v0_1 >= 0; --v0_1) {
                    this.mListeners.get(v0_1).onDrawerClosed(arg4);
                }
            }

            this.updateChildrenImportantForAccessibility(arg4, false);
            if(!this.hasWindowFocus()) {
                return;
            }

            arg4 = this.getRootView();
            if(arg4 == null) {
                return;
            }

            arg4.sendAccessibilityEvent(0x20);
        }
    }

    void dispatchOnDrawerOpened(View arg4) {
        ViewGroup$LayoutParams v0 = arg4.getLayoutParams();
        if((((LayoutParams)v0).openState & 1) == 0) {
            ((LayoutParams)v0).openState = 1;
            if(this.mListeners != null) {
                int v0_1;
                for(v0_1 = this.mListeners.size() - 1; v0_1 >= 0; --v0_1) {
                    this.mListeners.get(v0_1).onDrawerOpened(arg4);
                }
            }

            this.updateChildrenImportantForAccessibility(arg4, true);
            if(!this.hasWindowFocus()) {
                return;
            }

            this.sendAccessibilityEvent(0x20);
        }
    }

    void dispatchOnDrawerSlide(View arg3, float arg4) {
        if(this.mListeners != null) {
            int v0;
            for(v0 = this.mListeners.size() - 1; v0 >= 0; --v0) {
                this.mListeners.get(v0).onDrawerSlide(arg3, arg4);
            }
        }
    }

    protected boolean drawChild(Canvas arg15, View arg16, long arg17) {
        float v5_1;
        int v12_1;
        int v10;
        int v11;
        DrawerLayout v0 = this;
        Canvas v1 = arg15;
        View v2 = arg16;
        int v3 = v0.getHeight();
        boolean v4 = v0.isContentView(v2);
        int v5 = v0.getWidth();
        int v6 = v1.save();
        int v7 = 3;
        if(v4) {
            int v9 = v0.getChildCount();
            v11 = v5;
            v5 = 0;
            v10 = 0;
            while(v5 < v9) {
                View v12 = v0.getChildAt(v5);
                if(v12 != v2 && v12.getVisibility() == 0 && (DrawerLayout.hasOpaqueBackground(v12)) && (v0.isDrawerView(v12))) {
                    if(v12.getHeight() < v3) {
                    }
                    else if(v0.checkDrawerViewAbsoluteGravity(v12, v7)) {
                        v12_1 = v12.getRight();
                        if(v12_1 > v10) {
                            v10 = v12_1;
                        }
                    }
                    else {
                        v12_1 = v12.getLeft();
                        if(v12_1 < v11) {
                            v11 = v12_1;
                        }
                    }
                }

                ++v5;
            }

            v1.clipRect(v10, 0, v11, v0.getHeight());
        }
        else {
            v11 = v5;
            v10 = 0;
        }

        boolean v8 = super.drawChild(arg15, arg16, arg17);
        v1.restoreToCount(v6);
        if(v0.mScrimOpacity <= 0f || !v4) {
            float v4_1 = 255f;
            float v6_1 = 1f;
            if(v0.mShadowLeftResolved != null && (v0.checkDrawerViewAbsoluteGravity(v2, v7))) {
                v3 = v0.mShadowLeftResolved.getIntrinsicWidth();
                v7 = arg16.getRight();
                v5_1 = Math.max(0f, Math.min((((float)v7)) / (((float)v0.mLeftDragger.getEdgeSize())), v6_1));
                v0.mShadowLeftResolved.setBounds(v7, arg16.getTop(), v3 + v7, arg16.getBottom());
                v0.mShadowLeftResolved.setAlpha(((int)(v5_1 * v4_1)));
                v0.mShadowLeftResolved.draw(v1);
                return v8;
            }

            if(v0.mShadowRightResolved == null) {
                return v8;
            }

            if(!v0.checkDrawerViewAbsoluteGravity(v2, 5)) {
                return v8;
            }

            v3 = v0.mShadowRightResolved.getIntrinsicWidth();
            v7 = arg16.getLeft();
            v5_1 = Math.max(0f, Math.min((((float)(v0.getWidth() - v7))) / (((float)v0.mRightDragger.getEdgeSize())), v6_1));
            v0.mShadowRightResolved.setBounds(v7 - v3, arg16.getTop(), v7, arg16.getBottom());
            v0.mShadowRightResolved.setAlpha(((int)(v5_1 * v4_1)));
            v0.mShadowRightResolved.draw(v1);
        }
        else {
            v0.mScrimPaint.setColor((((int)((((float)((v0.mScrimColor & 0xFF000000) >>> 24))) * v0.mScrimOpacity))) << 24 | v0.mScrimColor & 0xFFFFFF);
            v1.drawRect(((float)v10), 0f, ((float)v11), ((float)v0.getHeight()), v0.mScrimPaint);
        }

        return v8;
    }

    View findDrawerWithGravity(int arg5) {
        arg5 = GravityCompat.getAbsoluteGravity(arg5, ViewCompat.getLayoutDirection(((View)this))) & 7;
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((this.getDrawerViewAbsoluteGravity(v2) & 7) == arg5) {
                return v2;
            }
        }

        return null;
    }

    View findOpenDrawer() {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((v2.getLayoutParams().openState & 1) == 1) {
                return v2;
            }
        }

        return null;
    }

    View findVisibleDrawer() {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((this.isDrawerView(v2)) && (this.isDrawerVisible(v2))) {
                return v2;
            }
        }

        return null;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new LayoutParams(this.getContext(), arg3);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg2) {
        LayoutParams v0;
        if((arg2 instanceof LayoutParams)) {
            v0 = new LayoutParams(((LayoutParams)arg2));
        }
        else if((arg2 instanceof ViewGroup$MarginLayoutParams)) {
            v0 = new LayoutParams(((ViewGroup$MarginLayoutParams)arg2));
        }
        else {
            v0 = new LayoutParams(arg2);
        }

        return ((ViewGroup$LayoutParams)v0);
    }

    public float getDrawerElevation() {
        if(DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return this.mDrawerElevation;
        }

        return 0;
    }

    public int getDrawerLockMode(int arg4) {
        int v0 = ViewCompat.getLayoutDirection(((View)this));
        int v1 = 3;
        if(arg4 == v1) {
            if(this.mLockModeLeft != v1) {
                return this.mLockModeLeft;
            }

            arg4 = v0 == 0 ? this.mLockModeStart : this.mLockModeEnd;
            if(arg4 == v1) {
                return 0;
            }

            return arg4;
        }
        else if(arg4 == 5) {
            if(this.mLockModeRight != v1) {
                return this.mLockModeRight;
            }

            arg4 = v0 == 0 ? this.mLockModeEnd : this.mLockModeStart;
            if(arg4 == v1) {
                return 0;
            }

            return arg4;
        }
        else if(arg4 == 0x800003) {
            if(this.mLockModeStart != v1) {
                return this.mLockModeStart;
            }

            arg4 = v0 == 0 ? this.mLockModeLeft : this.mLockModeRight;
            if(arg4 == v1) {
                return 0;
            }

            return arg4;
        }
        else if(arg4 != 0x800005) {
        }
        else if(this.mLockModeEnd != v1) {
            return this.mLockModeEnd;
        }
        else {
            arg4 = v0 == 0 ? this.mLockModeRight : this.mLockModeLeft;
            if(arg4 == v1) {
                return 0;
            }

            return arg4;
        }

        return 0;
    }

    public int getDrawerLockMode(View arg4) {
        if(!this.isDrawerView(arg4)) {
            StringBuilder v1 = new StringBuilder();
            v1.append("View ");
            v1.append(arg4);
            v1.append(" is not a drawer");
            throw new IllegalArgumentException(v1.toString());
        }

        return this.getDrawerLockMode(arg4.getLayoutParams().gravity);
    }

    @Nullable public CharSequence getDrawerTitle(int arg2) {
        arg2 = GravityCompat.getAbsoluteGravity(arg2, ViewCompat.getLayoutDirection(((View)this)));
        if(arg2 == 3) {
            return this.mTitleLeft;
        }

        if(arg2 == 5) {
            return this.mTitleRight;
        }

        return null;
    }

    int getDrawerViewAbsoluteGravity(View arg2) {
        return GravityCompat.getAbsoluteGravity(arg2.getLayoutParams().gravity, ViewCompat.getLayoutDirection(((View)this)));
    }

    float getDrawerViewOffset(View arg1) {
        return arg1.getLayoutParams().onScreen;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    static String gravityToString(int arg2) {
        if((arg2 & 3) == 3) {
            return "LEFT";
        }

        if((arg2 & 5) == 5) {
            return "RIGHT";
        }

        return Integer.toHexString(arg2);
    }

    private static boolean hasOpaqueBackground(View arg2) {
        Drawable v2 = arg2.getBackground();
        boolean v0 = false;
        if(v2 != null) {
            if(v2.getOpacity() == -1) {
                v0 = true;
            }

            return v0;
        }

        return 0;
    }

    private boolean hasPeekingDrawer() {
        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(this.getChildAt(v2).getLayoutParams().isPeeking) {
                return 1;
            }
        }

        return 0;
    }

    private boolean hasVisibleDrawer() {
        boolean v0 = this.findVisibleDrawer() != null ? true : false;
        return v0;
    }

    static boolean includeChildForAccessibility(View arg2) {
        boolean v2 = ViewCompat.getImportantForAccessibility(arg2) == 4 || ViewCompat.getImportantForAccessibility(arg2) == 2 ? false : true;
        return v2;
    }

    boolean isContentView(View arg1) {
        boolean v1 = arg1.getLayoutParams().gravity == 0 ? true : false;
        return v1;
    }

    public boolean isDrawerOpen(View arg4) {
        if(!this.isDrawerView(arg4)) {
            StringBuilder v1 = new StringBuilder();
            v1.append("View ");
            v1.append(arg4);
            v1.append(" is not a drawer");
            throw new IllegalArgumentException(v1.toString());
        }

        boolean v0 = true;
        if((arg4.getLayoutParams().openState & 1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean isDrawerOpen(int arg1) {
        View v1 = this.findDrawerWithGravity(arg1);
        if(v1 != null) {
            return this.isDrawerOpen(v1);
        }

        return 0;
    }

    boolean isDrawerView(View arg3) {
        int v3 = GravityCompat.getAbsoluteGravity(arg3.getLayoutParams().gravity, ViewCompat.getLayoutDirection(arg3));
        if((v3 & 3) != 0) {
            return 1;
        }

        if((v3 & 5) != 0) {
            return 1;
        }

        return 0;
    }

    public boolean isDrawerVisible(View arg4) {
        if(!this.isDrawerView(arg4)) {
            StringBuilder v1 = new StringBuilder();
            v1.append("View ");
            v1.append(arg4);
            v1.append(" is not a drawer");
            throw new IllegalArgumentException(v1.toString());
        }

        boolean v4 = arg4.getLayoutParams().onScreen > 0f ? true : false;
        return v4;
    }

    public boolean isDrawerVisible(int arg1) {
        View v1 = this.findDrawerWithGravity(arg1);
        if(v1 != null) {
            return this.isDrawerVisible(v1);
        }

        return 0;
    }

    private boolean mirror(Drawable arg2, int arg3) {
        if(arg2 != null) {
            if(!DrawableCompat.isAutoMirrored(arg2)) {
            }
            else {
                DrawableCompat.setLayoutDirection(arg2, arg3);
                return 1;
            }
        }

        return 0;
    }

    void moveDrawerToOffset(View arg3, float arg4) {
        float v0 = this.getDrawerViewOffset(arg3);
        float v1 = ((float)arg3.getWidth());
        int v1_1 = (((int)(v1 * arg4))) - (((int)(v0 * v1)));
        if(this.checkDrawerViewAbsoluteGravity(arg3, 3)) {
        }
        else {
            v1_1 = -v1_1;
        }

        arg3.offsetLeftAndRight(v1_1);
        this.setDrawerViewOffset(arg3, arg4);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }

    public void onDraw(Canvas arg5) {
        super.onDraw(arg5);
        if((this.mDrawStatusBarBackground) && this.mStatusBarBackground != null) {
            int v0 = Build$VERSION.SDK_INT < 21 || this.mLastInsets == null ? 0 : this.mLastInsets.getSystemWindowInsetTop();
            if(v0 <= 0) {
                return;
            }

            this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), v0);
            this.mStatusBarBackground.draw(arg5);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent arg7) {
        int v7_2;
        int v0 = arg7.getActionMasked();
        int v1 = this.mLeftDragger.shouldInterceptTouchEvent(arg7) | this.mRightDragger.shouldInterceptTouchEvent(arg7);
        boolean v2 = true;
        switch(v0) {
            case 0: {
                float v0_1 = arg7.getX();
                float v7 = arg7.getY();
                this.mInitialMotionX = v0_1;
                this.mInitialMotionY = v7;
                if(this.mScrimOpacity > 0f) {
                    View v7_1 = this.mLeftDragger.findTopChildUnder(((int)v0_1), ((int)v7));
                    if(v7_1 == null) {
                        goto label_39;
                    }
                    else if(this.isContentView(v7_1)) {
                        v7_2 = 1;
                    }
                    else {
                        goto label_39;
                    }
                }
                else {
                label_39:
                    v7_2 = 0;
                }

                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                goto label_44;
            }
            case 2: {
                if(!this.mLeftDragger.checkTouchSlop(3)) {
                    goto label_43;
                }

                this.mLeftCallback.removeCallbacks();
                this.mRightCallback.removeCallbacks();
                break;
            }
            case 1: 
            case 3: {
                this.closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            }
            default: {
                break;
            }
        }

    label_43:
        v7_2 = 0;
    label_44:
        if(v1 == 0 && v7_2 == 0 && !this.hasPeekingDrawer()) {
            if(this.mChildrenCanceledTouch) {
            }
            else {
                v2 = false;
            }
        }

        return v2;
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(arg2 == 4 && (this.hasVisibleDrawer())) {
            arg3.startTracking();
            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    public boolean onKeyUp(int arg2, KeyEvent arg3) {
        if(arg2 == 4) {
            View v2 = this.findVisibleDrawer();
            if(v2 != null && this.getDrawerLockMode(v2) == 0) {
                this.closeDrawers();
            }

            boolean v2_1 = v2 != null ? true : false;
            return v2_1;
        }

        return super.onKeyUp(arg2, arg3);
    }

    protected void onLayout(boolean arg17, int arg18, int arg19, int arg20, int arg21) {
        int v2;
        int v12_1;
        float v13;
        int v11;
        DrawerLayout v0 = this;
        v0.mInLayout = true;
        int v3 = arg20 - arg18;
        int v4 = this.getChildCount();
        int v6;
        for(v6 = 0; v6 < v4; ++v6) {
            View v7 = v0.getChildAt(v6);
            if(v7.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v8 = v7.getLayoutParams();
                if(v0.isContentView(v7)) {
                    v7.layout(((LayoutParams)v8).leftMargin, ((LayoutParams)v8).topMargin, ((LayoutParams)v8).leftMargin + v7.getMeasuredWidth(), ((LayoutParams)v8).topMargin + v7.getMeasuredHeight());
                }
                else {
                    int v9 = v7.getMeasuredWidth();
                    int v10 = v7.getMeasuredHeight();
                    if(v0.checkDrawerViewAbsoluteGravity(v7, 3)) {
                        float v12 = ((float)v9);
                        v11 = -v9 + (((int)(((LayoutParams)v8).onScreen * v12)));
                        v13 = (((float)(v9 + v11))) / v12;
                    }
                    else {
                        float v11_1 = ((float)v9);
                        v12_1 = v3 - (((int)(((LayoutParams)v8).onScreen * v11_1)));
                        v13 = (((float)(v3 - v12_1))) / v11_1;
                        v11 = v12_1;
                    }

                    v12_1 = v13 != ((LayoutParams)v8).onScreen ? 1 : 0;
                    int v14 = ((LayoutParams)v8).gravity & 0x70;
                    if(v14 == 16) {
                        v2 = arg21 - arg19;
                        v14 = (v2 - v10) / 2;
                        if(v14 < ((LayoutParams)v8).topMargin) {
                            v14 = ((LayoutParams)v8).topMargin;
                        }
                        else if(v14 + v10 > v2 - ((LayoutParams)v8).bottomMargin) {
                            v14 = v2 - ((LayoutParams)v8).bottomMargin - v10;
                        }

                        v7.layout(v11, v14, v9 + v11, v10 + v14);
                    }
                    else if(v14 != 80) {
                        v7.layout(v11, ((LayoutParams)v8).topMargin, v9 + v11, ((LayoutParams)v8).topMargin + v10);
                    }
                    else {
                        v2 = arg21 - arg19;
                        v7.layout(v11, v2 - ((LayoutParams)v8).bottomMargin - v7.getMeasuredHeight(), v9 + v11, v2 - ((LayoutParams)v8).bottomMargin);
                    }

                    if(v12_1 != 0) {
                        v0.setDrawerViewOffset(v7, v13);
                    }

                    int v5 = ((LayoutParams)v8).onScreen > 0f ? 0 : 4;
                    if(v7.getVisibility() == v5) {
                        goto label_104;
                    }

                    v7.setVisibility(v5);
                }
            }

        label_104:
        }

        v0.mInLayout = false;
        v0.mFirstLayout = false;
    }

    protected void onMeasure(int arg19, int arg20) {
        WindowInsets v2_2;
        Object v2_1;
        View v12;
        int v7;
        DrawerLayout v0 = this;
        int v1 = View$MeasureSpec.getMode(arg19);
        int v2 = View$MeasureSpec.getMode(arg20);
        int v3 = View$MeasureSpec.getSize(arg19);
        int v4 = View$MeasureSpec.getSize(arg20);
        int v6 = 0x40000000;
        if(v1 != v6 || v2 != v6) {
            if(this.isInEditMode()) {
                v7 = 0x80000000;
                if(v1 == v7) {
                }
                else if(v1 == 0) {
                    v3 = 300;
                }

                if(v2 == v7) {
                    goto label_20;
                }

                if(v2 != 0) {
                    goto label_20;
                }

                v4 = 300;
            }
            else {
                goto label_184;
            }
        }

    label_20:
        v0.setMeasuredDimension(v3, v4);
        int v5 = 0;
        v1 = v0.mLastInsets == null || !ViewCompat.getFitsSystemWindows(((View)this)) ? 0 : 1;
        v7 = ViewCompat.getLayoutDirection(((View)this));
        int v8 = this.getChildCount();
        int v9 = 0;
        int v10 = 0;
        int v11 = 0;
        while(true) {
            if(v9 >= v8) {
                return;
            }

            v12 = v0.getChildAt(v9);
            if(v12.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v13 = v12.getLayoutParams();
                int v14 = 3;
                if(v1 != 0) {
                    int v15 = GravityCompat.getAbsoluteGravity(((LayoutParams)v13).gravity, v7);
                    v6 = 21;
                    if(!ViewCompat.getFitsSystemWindows(v12)) {
                        if(Build$VERSION.SDK_INT < v6) {
                            goto label_89;
                        }

                        v2_1 = v0.mLastInsets;
                        if(v15 == 3) {
                            v2_2 = ((WindowInsets)v2_1).replaceSystemWindowInsets(((WindowInsets)v2_1).getSystemWindowInsetLeft(), ((WindowInsets)v2_1).getSystemWindowInsetTop(), v5, ((WindowInsets)v2_1).getSystemWindowInsetBottom());
                        }
                        else if(v15 == 5) {
                            v2_2 = ((WindowInsets)v2_1).replaceSystemWindowInsets(v5, ((WindowInsets)v2_1).getSystemWindowInsetTop(), ((WindowInsets)v2_1).getSystemWindowInsetRight(), ((WindowInsets)v2_1).getSystemWindowInsetBottom());
                        }

                        ((LayoutParams)v13).leftMargin = ((WindowInsets)v2_1).getSystemWindowInsetLeft();
                        ((LayoutParams)v13).topMargin = ((WindowInsets)v2_1).getSystemWindowInsetTop();
                        ((LayoutParams)v13).rightMargin = ((WindowInsets)v2_1).getSystemWindowInsetRight();
                        ((LayoutParams)v13).bottomMargin = ((WindowInsets)v2_1).getSystemWindowInsetBottom();
                    }
                    else if(Build$VERSION.SDK_INT >= v6) {
                        v2_1 = v0.mLastInsets;
                        if(v15 == v14) {
                            v2_2 = ((WindowInsets)v2_1).replaceSystemWindowInsets(((WindowInsets)v2_1).getSystemWindowInsetLeft(), ((WindowInsets)v2_1).getSystemWindowInsetTop(), v5, ((WindowInsets)v2_1).getSystemWindowInsetBottom());
                        }
                        else if(v15 == 5) {
                            v2_2 = ((WindowInsets)v2_1).replaceSystemWindowInsets(v5, ((WindowInsets)v2_1).getSystemWindowInsetTop(), ((WindowInsets)v2_1).getSystemWindowInsetRight(), ((WindowInsets)v2_1).getSystemWindowInsetBottom());
                        }

                        v12.dispatchApplyWindowInsets(((WindowInsets)v2_1));
                    }
                }

            label_89:
                if(v0.isContentView(v12)) {
                    v12.measure(View$MeasureSpec.makeMeasureSpec(v3 - ((LayoutParams)v13).leftMargin - ((LayoutParams)v13).rightMargin, 0x40000000), View$MeasureSpec.makeMeasureSpec(v4 - ((LayoutParams)v13).topMargin - ((LayoutParams)v13).bottomMargin, 0x40000000));
                    goto label_164;
                }

                if(!v0.isDrawerView(v12)) {
                    break;
                }

                if((DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) && ViewCompat.getElevation(v12) != v0.mDrawerElevation) {
                    ViewCompat.setElevation(v12, v0.mDrawerElevation);
                }

                v2 = v0.getDrawerViewAbsoluteGravity(v12) & 7;
                v14 = v2 == 3 ? 1 : 0;
                if(v14 != 0 && v10 != 0 || v14 == 0 && v11 != 0) {
                    StringBuilder v3_1 = new StringBuilder();
                    v3_1.append("Child drawer has absolute gravity ");
                    v3_1.append(DrawerLayout.gravityToString(v2));
                    v3_1.append(" but this ");
                    v3_1.append("DrawerLayout");
                    v3_1.append(" already has a ");
                    v3_1.append("drawer view along that edge");
                    throw new IllegalStateException(v3_1.toString());
                }

                if(v14 != 0) {
                    v10 = 1;
                }
                else {
                    v11 = 1;
                }

                v12.measure(DrawerLayout.getChildMeasureSpec(arg19, v0.mMinDrawerMargin + ((LayoutParams)v13).leftMargin + ((LayoutParams)v13).rightMargin, ((LayoutParams)v13).width), DrawerLayout.getChildMeasureSpec(arg20, ((LayoutParams)v13).topMargin + ((LayoutParams)v13).bottomMargin, ((LayoutParams)v13).height));
            }

        label_164:
            ++v9;
            v5 = 0;
        }

        StringBuilder v2_3 = new StringBuilder();
        v2_3.append("Child ");
        v2_3.append(v12);
        v2_3.append(" at index ");
        v2_3.append(v9);
        v2_3.append(" does not have a valid layout_gravity - must be Gravity.LEFT, ");
        v2_3.append("Gravity.RIGHT or Gravity.NO_GRAVITY");
        throw new IllegalStateException(v2_3.toString());
        return;
    label_184:
        throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
    }

    protected void onRestoreInstanceState(Parcelable arg4) {
        if(!(arg4 instanceof SavedState)) {
            super.onRestoreInstanceState(arg4);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg4).getSuperState());
        if(((SavedState)arg4).openDrawerGravity != 0) {
            View v0 = this.findDrawerWithGravity(((SavedState)arg4).openDrawerGravity);
            if(v0 != null) {
                this.openDrawer(v0);
            }
        }

        int v1 = 3;
        if(((SavedState)arg4).lockModeLeft != v1) {
            this.setDrawerLockMode(((SavedState)arg4).lockModeLeft, v1);
        }

        if(((SavedState)arg4).lockModeRight != v1) {
            this.setDrawerLockMode(((SavedState)arg4).lockModeRight, 5);
        }

        if(((SavedState)arg4).lockModeStart != v1) {
            this.setDrawerLockMode(((SavedState)arg4).lockModeStart, 0x800003);
        }

        if(((SavedState)arg4).lockModeEnd != v1) {
            this.setDrawerLockMode(((SavedState)arg4).lockModeEnd, 0x800005);
        }
    }

    public void onRtlPropertiesChanged(int arg1) {
        this.resolveShadowDrawables();
    }

    protected Parcelable onSaveInstanceState() {
        ViewGroup$LayoutParams v4;
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        int v0 = this.getChildCount();
        int v3 = 0;
        while(true) {
            if(v3 < v0) {
                v4 = this.getChildAt(v3).getLayoutParams();
                int v6 = 1;
                int v5 = ((LayoutParams)v4).openState == 1 ? 1 : 0;
                if(((LayoutParams)v4).openState == 2) {
                }
                else {
                    v6 = 0;
                }

                if(v5 == 0) {
                    if(v6 != 0) {
                    }
                    else {
                        ++v3;
                        continue;
                    }
                }

                break;
            }

            goto label_27;
        }

        v1.openDrawerGravity = ((LayoutParams)v4).gravity;
    label_27:
        v1.lockModeLeft = this.mLockModeLeft;
        v1.lockModeRight = this.mLockModeRight;
        v1.lockModeStart = this.mLockModeStart;
        v1.lockModeEnd = this.mLockModeEnd;
        return ((Parcelable)v1);
    }

    public boolean onTouchEvent(MotionEvent arg7) {
        boolean v7_2;
        this.mLeftDragger.processTouchEvent(arg7);
        this.mRightDragger.processTouchEvent(arg7);
        int v0 = arg7.getAction() & 0xFF;
        if(v0 != 3) {
            switch(v0) {
                case 0: {
                    goto label_45;
                }
                case 1: {
                    goto label_12;
                }
            }

            return 1;
        label_12:
            float v0_1 = arg7.getX();
            float v7 = arg7.getY();
            View v1 = this.mLeftDragger.findTopChildUnder(((int)v0_1), ((int)v7));
            if(v1 == null || !this.isContentView(v1)) {
            label_41:
                v7_2 = true;
            }
            else {
                v0_1 -= this.mInitialMotionX;
                v7 -= this.mInitialMotionY;
                int v1_1 = this.mLeftDragger.getTouchSlop();
                if(v0_1 * v0_1 + v7 * v7 < (((float)(v1_1 * v1_1)))) {
                    View v7_1 = this.findOpenDrawer();
                    if(v7_1 == null) {
                        goto label_41;
                    }
                    else if(this.getDrawerLockMode(v7_1) == 2) {
                        goto label_41;
                    }
                    else {
                        v7_2 = false;
                    }
                }
                else {
                    goto label_41;
                }
            }

            this.closeDrawers(v7_2);
            this.mDisallowInterceptRequested = false;
            return 1;
        label_45:
            v0_1 = arg7.getX();
            v7 = arg7.getY();
            this.mInitialMotionX = v0_1;
            this.mInitialMotionY = v7;
            this.mDisallowInterceptRequested = false;
            this.mChildrenCanceledTouch = false;
        }
        else {
            this.closeDrawers(true);
            this.mDisallowInterceptRequested = false;
            this.mChildrenCanceledTouch = false;
        }

        return 1;
    }

    public void openDrawer(View arg2) {
        this.openDrawer(arg2, true);
    }

    public void openDrawer(int arg2) {
        this.openDrawer(arg2, true);
    }

    public void openDrawer(int arg3, boolean arg4) {
        View v0 = this.findDrawerWithGravity(arg3);
        if(v0 == null) {
            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("No drawer view found with gravity ");
            v0_1.append(DrawerLayout.gravityToString(arg3));
            throw new IllegalArgumentException(v0_1.toString());
        }

        this.openDrawer(v0, arg4);
    }

    public void openDrawer(View arg4, boolean arg5) {
        if(!this.isDrawerView(arg4)) {
            StringBuilder v0 = new StringBuilder();
            v0.append("View ");
            v0.append(arg4);
            v0.append(" is not a sliding drawer");
            throw new IllegalArgumentException(v0.toString());
        }

        ViewGroup$LayoutParams v0_1 = arg4.getLayoutParams();
        float v2 = 1f;
        if(this.mFirstLayout) {
            ((LayoutParams)v0_1).onScreen = v2;
            ((LayoutParams)v0_1).openState = 1;
            this.updateChildrenImportantForAccessibility(arg4, true);
        }
        else if(arg5) {
            ((LayoutParams)v0_1).openState |= 2;
            if(this.checkDrawerViewAbsoluteGravity(arg4, 3)) {
                this.mLeftDragger.smoothSlideViewTo(arg4, 0, arg4.getTop());
            }
            else {
                this.mRightDragger.smoothSlideViewTo(arg4, this.getWidth() - arg4.getWidth(), arg4.getTop());
            }
        }
        else {
            this.moveDrawerToOffset(arg4, v2);
            this.updateDrawerState(((LayoutParams)v0_1).gravity, 0, arg4);
            arg4.setVisibility(0);
        }

        this.invalidate();
    }

    public void removeDrawerListener(@NonNull DrawerListener arg2) {
        if(arg2 == null) {
            return;
        }

        if(this.mListeners == null) {
            return;
        }

        this.mListeners.remove(arg2);
    }

    public void requestDisallowInterceptTouchEvent(boolean arg1) {
        super.requestDisallowInterceptTouchEvent(arg1);
        this.mDisallowInterceptRequested = arg1;
        if(arg1) {
            this.closeDrawers(true);
        }
    }

    public void requestLayout() {
        if(!this.mInLayout) {
            super.requestLayout();
        }
    }

    private Drawable resolveLeftShadow() {
        int v0 = ViewCompat.getLayoutDirection(((View)this));
        if(v0 == 0) {
            if(this.mShadowStart != null) {
                this.mirror(this.mShadowStart, v0);
                return this.mShadowStart;
            }
        }
        else if(this.mShadowEnd != null) {
            this.mirror(this.mShadowEnd, v0);
            return this.mShadowEnd;
        }

        return this.mShadowLeft;
    }

    private Drawable resolveRightShadow() {
        int v0 = ViewCompat.getLayoutDirection(((View)this));
        if(v0 == 0) {
            if(this.mShadowEnd != null) {
                this.mirror(this.mShadowEnd, v0);
                return this.mShadowEnd;
            }
        }
        else if(this.mShadowStart != null) {
            this.mirror(this.mShadowStart, v0);
            return this.mShadowStart;
        }

        return this.mShadowRight;
    }

    private void resolveShadowDrawables() {
        if(DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return;
        }

        this.mShadowLeftResolved = this.resolveLeftShadow();
        this.mShadowRightResolved = this.resolveRightShadow();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setChildInsets(Object arg1, boolean arg2) {
        this.mLastInsets = arg1;
        this.mDrawStatusBarBackground = arg2;
        boolean v1 = (arg2) || this.getBackground() != null ? false : true;
        this.setWillNotDraw(v1);
        this.requestLayout();
    }

    public void setDrawerElevation(float arg3) {
        this.mDrawerElevation = arg3;
        int v3;
        for(v3 = 0; v3 < this.getChildCount(); ++v3) {
            View v0 = this.getChildAt(v3);
            if(this.isDrawerView(v0)) {
                ViewCompat.setElevation(v0, this.mDrawerElevation);
            }
        }
    }

    @Deprecated public void setDrawerListener(DrawerListener arg2) {
        if(this.mListener != null) {
            this.removeDrawerListener(this.mListener);
        }

        if(arg2 != null) {
            this.addDrawerListener(arg2);
        }

        this.mListener = arg2;
    }

    public void setDrawerLockMode(int arg4, int arg5) {
        View v4;
        int v0 = GravityCompat.getAbsoluteGravity(arg5, ViewCompat.getLayoutDirection(((View)this)));
        int v1 = 3;
        if(arg5 == v1) {
            this.mLockModeLeft = arg4;
        }
        else if(arg5 == 5) {
            this.mLockModeRight = arg4;
        }
        else if(arg5 == 0x800003) {
            this.mLockModeStart = arg4;
        }
        else if(arg5 != 0x800005) {
        }
        else {
            this.mLockModeEnd = arg4;
        }

        if(arg4 != 0) {
            ViewDragHelper v5 = v0 == v1 ? this.mLeftDragger : this.mRightDragger;
            v5.cancel();
        }

        switch(arg4) {
            case 1: {
                v4 = this.findDrawerWithGravity(v0);
                if(v4 == null) {
                    return;
                }

                this.closeDrawer(v4);
                break;
            }
            case 2: {
                v4 = this.findDrawerWithGravity(v0);
                if(v4 == null) {
                    return;
                }

                this.openDrawer(v4);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void setDrawerLockMode(int arg2) {
        this.setDrawerLockMode(arg2, 3);
        this.setDrawerLockMode(arg2, 5);
    }

    public void setDrawerLockMode(int arg3, View arg4) {
        if(!this.isDrawerView(arg4)) {
            StringBuilder v0 = new StringBuilder();
            v0.append("View ");
            v0.append(arg4);
            v0.append(" is not a ");
            v0.append("drawer with appropriate layout_gravity");
            throw new IllegalArgumentException(v0.toString());
        }

        this.setDrawerLockMode(arg3, arg4.getLayoutParams().gravity);
    }

    public void setDrawerShadow(@DrawableRes int arg2, int arg3) {
        this.setDrawerShadow(ContextCompat.getDrawable(this.getContext(), arg2), arg3);
    }

    public void setDrawerShadow(Drawable arg3, int arg4) {
        if(DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return;
        }

        if((arg4 & 0x800003) == 0x800003) {
            this.mShadowStart = arg3;
        }
        else if((arg4 & 0x800005) == 0x800005) {
            this.mShadowEnd = arg3;
        }
        else if((arg4 & 3) == 3) {
            this.mShadowLeft = arg3;
        }
        else if((arg4 & 5) == 5) {
            this.mShadowRight = arg3;
        }
        else {
            return;
        }

        this.resolveShadowDrawables();
        this.invalidate();
    }

    public void setDrawerTitle(int arg2, CharSequence arg3) {
        arg2 = GravityCompat.getAbsoluteGravity(arg2, ViewCompat.getLayoutDirection(((View)this)));
        if(arg2 == 3) {
            this.mTitleLeft = arg3;
        }
        else if(arg2 == 5) {
            this.mTitleRight = arg3;
        }
    }

    void setDrawerViewOffset(View arg3, float arg4) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        if(arg4 == ((LayoutParams)v0).onScreen) {
            return;
        }

        ((LayoutParams)v0).onScreen = arg4;
        this.dispatchOnDrawerSlide(arg3, arg4);
    }

    public void setScrimColor(@ColorInt int arg1) {
        this.mScrimColor = arg1;
        this.invalidate();
    }

    public void setStatusBarBackground(int arg2) {
        Drawable v2 = arg2 != 0 ? ContextCompat.getDrawable(this.getContext(), arg2) : null;
        this.mStatusBarBackground = v2;
        this.invalidate();
    }

    public void setStatusBarBackground(Drawable arg1) {
        this.mStatusBarBackground = arg1;
        this.invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int arg2) {
        this.mStatusBarBackground = new ColorDrawable(arg2);
        this.invalidate();
    }

    private void updateChildrenImportantForAccessibility(View arg5, boolean arg6) {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if((arg6) || (this.isDrawerView(v2))) {
                if((arg6) && v2 == arg5) {
                label_9:
                    ViewCompat.setImportantForAccessibility(v2, 1);
                    goto label_14;
                }

                ViewCompat.setImportantForAccessibility(v2, 4);
            }
            else {
                goto label_9;
            }

        label_14:
        }
    }

    void updateDrawerState(int arg4, int arg5, View arg6) {
        arg4 = this.mLeftDragger.getViewDragState();
        int v0 = this.mRightDragger.getViewDragState();
        int v1 = 2;
        if(arg4 == 1 || v0 == 1) {
            v1 = 1;
        }
        else if(arg4 != v1) {
            if(v0 == v1) {
            }
            else {
                v1 = 0;
            }
        }

        if(arg6 != null && arg5 == 0) {
            ViewGroup$LayoutParams v4 = arg6.getLayoutParams();
            if(((LayoutParams)v4).onScreen == 0f) {
                this.dispatchOnDrawerClosed(arg6);
            }
            else if(((LayoutParams)v4).onScreen == 1f) {
                this.dispatchOnDrawerOpened(arg6);
            }
        }

        if(v1 != this.mDrawerState) {
            this.mDrawerState = v1;
            if(this.mListeners != null) {
                for(arg4 = this.mListeners.size() - 1; arg4 >= 0; --arg4) {
                    this.mListeners.get(arg4).onDrawerStateChanged(v1);
                }
            }
        }
    }
}

