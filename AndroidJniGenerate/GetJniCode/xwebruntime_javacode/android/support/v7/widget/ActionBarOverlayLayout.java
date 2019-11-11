package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$id;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window$Callback;
import android.widget.OverScroller;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ActionBarOverlayLayout extends ViewGroup implements NestedScrollingParent, DecorContentParent {
    class android.support.v7.widget.ActionBarOverlayLayout$1 extends AnimatorListenerAdapter {
        android.support.v7.widget.ActionBarOverlayLayout$1(ActionBarOverlayLayout arg1) {
            ActionBarOverlayLayout.this = arg1;
            super();
        }

        public void onAnimationCancel(Animator arg2) {
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
        }

        public void onAnimationEnd(Animator arg2) {
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
        }
    }

    class android.support.v7.widget.ActionBarOverlayLayout$2 implements Runnable {
        android.support.v7.widget.ActionBarOverlayLayout$2(ActionBarOverlayLayout arg1) {
            ActionBarOverlayLayout.this = arg1;
            super();
        }

        public void run() {
            ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ActionBarOverlayLayout.this.mActionBarTop.animate().translationY(0f).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
        }
    }

    class android.support.v7.widget.ActionBarOverlayLayout$3 implements Runnable {
        android.support.v7.widget.ActionBarOverlayLayout$3(ActionBarOverlayLayout arg1) {
            ActionBarOverlayLayout.this = arg1;
            super();
        }

        public void run() {
            ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ActionBarOverlayLayout.this.mActionBarTop.animate().translationY(((float)(-ActionBarOverlayLayout.this.mActionBarTop.getHeight()))).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
        }
    }

    public interface ActionBarVisibilityCallback {
        void enableContentAnimations(boolean arg1);

        void hideForSystem();

        void onContentScrollStarted();

        void onContentScrollStopped();

        void onWindowVisibilityChanged(int arg1);

        void showForSystem();
    }

    public class LayoutParams extends ViewGroup$MarginLayoutParams {
        public LayoutParams(int arg1, int arg2) {
            super(arg1, arg2);
        }

        public LayoutParams(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        public LayoutParams(ViewGroup$LayoutParams arg1) {
            super(arg1);
        }

        public LayoutParams(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
        }
    }

    private final int ACTION_BAR_ANIMATE_DELAY;
    static final int[] ATTRS = null;
    private static final String TAG = "ActionBarOverlayLayout";
    private int mActionBarHeight;
    ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private final Rect mBaseInnerInsets;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private OverScroller mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private final Rect mLastBaseInnerInsets;
    private final Rect mLastInnerInsets;
    private int mLastSystemUiVisibility;
    private boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    final AnimatorListenerAdapter mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;

    static {
        ActionBarOverlayLayout.ATTRS = new int[]{attr.actionBarSize, 0x1010059};
    }

    public ActionBarOverlayLayout(Context arg2) {
        this(arg2, null);
    }

    public ActionBarOverlayLayout(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mLastBaseInnerInsets = new Rect();
        this.mInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.ACTION_BAR_ANIMATE_DELAY = 600;
        this.mTopAnimatorListener = new android.support.v7.widget.ActionBarOverlayLayout$1(this);
        this.mRemoveActionBarHideOffset = new android.support.v7.widget.ActionBarOverlayLayout$2(this);
        this.mAddActionBarHideOffset = new android.support.v7.widget.ActionBarOverlayLayout$3(this);
        this.init(arg1);
        this.mParentHelper = new NestedScrollingParentHelper(((ViewGroup)this));
    }

    private void addActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View arg3, Rect arg4, boolean arg5, boolean arg6, boolean arg7, boolean arg8) {
        ViewGroup$LayoutParams v3 = arg3.getLayoutParams();
        if(!arg5 || ((LayoutParams)v3).leftMargin == arg4.left) {
            arg5 = false;
        }
        else {
            ((LayoutParams)v3).leftMargin = arg4.left;
            arg5 = true;
        }

        if((arg6) && ((LayoutParams)v3).topMargin != arg4.top) {
            ((LayoutParams)v3).topMargin = arg4.top;
            arg5 = true;
        }

        if((arg8) && ((LayoutParams)v3).rightMargin != arg4.right) {
            ((LayoutParams)v3).rightMargin = arg4.right;
            arg5 = true;
        }

        if((arg7) && ((LayoutParams)v3).bottomMargin != arg4.bottom) {
            ((LayoutParams)v3).bottomMargin = arg4.bottom;
            arg5 = true;
        }

        return arg5;
    }

    public boolean canShowOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        return arg1 instanceof LayoutParams;
    }

    public void dismissPopups() {
        this.pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }

    public void draw(Canvas arg6) {
        super.draw(arg6);
        if(this.mWindowContentOverlay != null && !this.mIgnoreWindowContentOverlay) {
            int v0 = this.mActionBarTop.getVisibility() == 0 ? ((int)((((float)this.mActionBarTop.getBottom())) + this.mActionBarTop.getTranslationY() + 0.5f)) : 0;
            this.mWindowContentOverlay.setBounds(0, v0, this.getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + v0);
            this.mWindowContentOverlay.draw(arg6);
        }
    }

    protected boolean fitSystemWindows(Rect arg9) {
        this.pullChildren();
        ViewCompat.getWindowSystemUiVisibility(((View)this));
        boolean v0 = this.applyInsets(this.mActionBarTop, arg9, true, true, false, true);
        this.mBaseInnerInsets.set(arg9);
        ViewUtils.computeFitSystemWindows(((View)this), this.mBaseInnerInsets, this.mBaseContentInsets);
        if(!this.mLastBaseInnerInsets.equals(this.mBaseInnerInsets)) {
            this.mLastBaseInnerInsets.set(this.mBaseInnerInsets);
            v0 = true;
        }

        if(!this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
            v0 = true;
        }

        if(v0) {
            this.requestLayout();
        }

        return 1;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new LayoutParams(this.getContext(), arg3);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.generateLayoutParams(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg2) {
        return new LayoutParams(arg2);
    }

    public int getActionBarHideOffset() {
        int v0 = this.mActionBarTop != null ? -(((int)this.mActionBarTop.getTranslationY())) : 0;
        return v0;
    }

    private DecorToolbar getDecorToolbar(View arg4) {
        if((arg4 instanceof DecorToolbar)) {
            return arg4;
        }

        if((arg4 instanceof Toolbar)) {
            return ((Toolbar)arg4).getWrapper();
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Can\'t make a decor toolbar out of ");
        v1.append(arg4.getClass().getSimpleName());
        throw new IllegalStateException(v1.toString());
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public CharSequence getTitle() {
        this.pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    void haltActionBarHideOffsetAnimations() {
        this.removeCallbacks(this.mRemoveActionBarHideOffset);
        this.removeCallbacks(this.mAddActionBarHideOffset);
        if(this.mCurrentActionBarTopAnimator != null) {
            this.mCurrentActionBarTopAnimator.cancel();
        }
    }

    public boolean hasIcon() {
        this.pullChildren();
        return this.mDecorToolbar.hasIcon();
    }

    public boolean hasLogo() {
        this.pullChildren();
        return this.mDecorToolbar.hasLogo();
    }

    public boolean hideOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    private void init(Context arg5) {
        TypedArray v0 = this.getContext().getTheme().obtainStyledAttributes(ActionBarOverlayLayout.ATTRS);
        boolean v1 = false;
        this.mActionBarHeight = v0.getDimensionPixelSize(0, 0);
        this.mWindowContentOverlay = v0.getDrawable(1);
        boolean v3 = this.mWindowContentOverlay == null ? true : false;
        this.setWillNotDraw(v3);
        v0.recycle();
        if(arg5.getApplicationInfo().targetSdkVersion < 19) {
            v1 = true;
        }

        this.mIgnoreWindowContentOverlay = v1;
        this.mFlingEstimator = new OverScroller(arg5);
    }

    public void initFeature(int arg2) {
        this.pullChildren();
        if(arg2 == 2) {
            this.mDecorToolbar.initProgress();
        }
        else if(arg2 == 5) {
            this.mDecorToolbar.initIndeterminateProgress();
        }
        else if(arg2 != 109) {
        }
        else {
            this.setOverlayMode(true);
        }
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.mHideOnContentScroll;
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    public boolean isOverflowMenuShowPending() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    protected void onConfigurationChanged(Configuration arg1) {
        super.onConfigurationChanged(arg1);
        this.init(this.getContext());
        ViewCompat.requestApplyInsets(((View)this));
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.haltActionBarHideOffsetAnimations();
    }

    protected void onLayout(boolean arg5, int arg6, int arg7, int arg8, int arg9) {
        int v5 = this.getChildCount();
        arg6 = this.getPaddingLeft();
        this.getPaddingRight();
        arg7 = this.getPaddingTop();
        this.getPaddingBottom();
        for(arg8 = 0; arg8 < v5; ++arg8) {
            View v9 = this.getChildAt(arg8);
            if(v9.getVisibility() != 8) {
                ViewGroup$LayoutParams v0 = v9.getLayoutParams();
                int v1 = v9.getMeasuredWidth();
                int v2 = v9.getMeasuredHeight();
                int v3 = ((LayoutParams)v0).leftMargin + arg6;
                int v0_1 = ((LayoutParams)v0).topMargin + arg7;
                v9.layout(v3, v0_1, v1 + v3, v2 + v0_1);
            }
        }
    }

    protected void onMeasure(int arg14, int arg15) {
        int v5;
        this.pullChildren();
        this.measureChildWithMargins(this.mActionBarTop, arg14, 0, arg15, 0);
        ViewGroup$LayoutParams v0 = this.mActionBarTop.getLayoutParams();
        int v1 = Math.max(0, this.mActionBarTop.getMeasuredWidth() + ((LayoutParams)v0).leftMargin + ((LayoutParams)v0).rightMargin);
        int v0_1 = Math.max(0, this.mActionBarTop.getMeasuredHeight() + ((LayoutParams)v0).topMargin + ((LayoutParams)v0).bottomMargin);
        int v3 = View.combineMeasuredStates(0, this.mActionBarTop.getMeasuredState());
        int v4 = (ViewCompat.getWindowSystemUiVisibility(((View)this)) & 0x100) != 0 ? 1 : 0;
        if(v4 != 0) {
            v5 = this.mActionBarHeight;
            if((this.mHasNonEmbeddedTabs) && this.mActionBarTop.getTabContainer() != null) {
                v5 += this.mActionBarHeight;
            }
        }
        else if(this.mActionBarTop.getVisibility() != 8) {
            v5 = this.mActionBarTop.getMeasuredHeight();
        }
        else {
            v5 = 0;
        }

        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets.set(this.mBaseInnerInsets);
        if((this.mOverlayMode) || v4 != 0) {
            this.mInnerInsets.top += v5;
            this.mInnerInsets.bottom = this.mInnerInsets.bottom;
        }
        else {
            this.mContentInsets.top += v5;
            this.mContentInsets.bottom = this.mContentInsets.bottom;
        }

        this.applyInsets(this.mContent, this.mContentInsets, true, true, true, true);
        if(!this.mLastInnerInsets.equals(this.mInnerInsets)) {
            this.mLastInnerInsets.set(this.mInnerInsets);
            this.mContent.dispatchFitSystemWindows(this.mInnerInsets);
        }

        this.measureChildWithMargins(this.mContent, arg14, 0, arg15, 0);
        ViewGroup$LayoutParams v2 = this.mContent.getLayoutParams();
        v1 = Math.max(v1, this.mContent.getMeasuredWidth() + ((LayoutParams)v2).leftMargin + ((LayoutParams)v2).rightMargin);
        v0_1 = Math.max(v0_1, this.mContent.getMeasuredHeight() + ((LayoutParams)v2).topMargin + ((LayoutParams)v2).bottomMargin);
        int v2_1 = View.combineMeasuredStates(v3, this.mContent.getMeasuredState());
        this.setMeasuredDimension(View.resolveSizeAndState(Math.max(v1 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), arg14, v2_1), View.resolveSizeAndState(Math.max(v0_1 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), arg15, v2_1 << 16));
    }

    public boolean onNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
        if(this.mHideOnContentScroll) {
            if(!arg4) {
            }
            else {
                if(this.shouldHideActionBarOnFling(arg2, arg3)) {
                    this.addActionBarHideOffset();
                }
                else {
                    this.removeActionBarHideOffset();
                }

                this.mAnimatingForFling = true;
                return 1;
            }
        }

        return 0;
    }

    public boolean onNestedPreFling(View arg1, float arg2, float arg3) {
        return 0;
    }

    public void onNestedPreScroll(View arg1, int arg2, int arg3, int[] arg4) {
    }

    public void onNestedScroll(View arg1, int arg2, int arg3, int arg4, int arg5) {
        this.mHideOnContentScrollReference += arg3;
        this.setActionBarHideOffset(this.mHideOnContentScrollReference);
    }

    public void onNestedScrollAccepted(View arg2, View arg3, int arg4) {
        this.mParentHelper.onNestedScrollAccepted(arg2, arg3, arg4);
        this.mHideOnContentScrollReference = this.getActionBarHideOffset();
        this.haltActionBarHideOffsetAnimations();
        if(this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStarted();
        }
    }

    public boolean onStartNestedScroll(View arg1, View arg2, int arg3) {
        if((arg3 & 2) != 0) {
            if(this.mActionBarTop.getVisibility() != 0) {
            }
            else {
                return this.mHideOnContentScroll;
            }
        }

        return 0;
    }

    public void onStopNestedScroll(View arg2) {
        if((this.mHideOnContentScroll) && !this.mAnimatingForFling) {
            if(this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                this.postRemoveActionBarHideOffset();
            }
            else {
                this.postAddActionBarHideOffset();
            }
        }

        if(this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStopped();
        }
    }

    public void onWindowSystemUiVisibilityChanged(int arg5) {
        if(Build$VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(arg5);
        }

        this.pullChildren();
        int v0 = this.mLastSystemUiVisibility ^ arg5;
        this.mLastSystemUiVisibility = arg5;
        int v2 = 0;
        int v1 = (arg5 & 4) == 0 ? 1 : 0;
        if((arg5 & 0x100) != 0) {
            v2 = 1;
        }

        if(this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.enableContentAnimations(v2 ^ 1);
            if(v1 == 0) {
                if(v2 == 0) {
                }
                else {
                    this.mActionBarVisibilityCallback.hideForSystem();
                    goto label_31;
                }
            }

            this.mActionBarVisibilityCallback.showForSystem();
        }

    label_31:
        if((v0 & 0x100) != 0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.requestApplyInsets(((View)this));
        }
    }

    protected void onWindowVisibilityChanged(int arg2) {
        super.onWindowVisibilityChanged(arg2);
        this.mWindowVisibility = arg2;
        if(this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(arg2);
        }
    }

    private void postAddActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mAddActionBarHideOffset, 600);
    }

    private void postRemoveActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mRemoveActionBarHideOffset, 600);
    }

    void pullChildren() {
        if(this.mContent == null) {
            this.mContent = this.findViewById(id.action_bar_activity_content);
            this.mActionBarTop = this.findViewById(id.action_bar_container);
            this.mDecorToolbar = this.getDecorToolbar(this.findViewById(id.action_bar));
        }
    }

    private void removeActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    public void restoreToolbarHierarchyState(SparseArray arg2) {
        this.pullChildren();
        this.mDecorToolbar.restoreHierarchyState(arg2);
    }

    public void saveToolbarHierarchyState(SparseArray arg2) {
        this.pullChildren();
        this.mDecorToolbar.saveHierarchyState(arg2);
    }

    public void setActionBarHideOffset(int arg2) {
        this.haltActionBarHideOffsetAnimations();
        this.mActionBarTop.setTranslationY(((float)(-Math.max(0, Math.min(arg2, this.mActionBarTop.getHeight())))));
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback arg2) {
        this.mActionBarVisibilityCallback = arg2;
        if(this.getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            if(this.mLastSystemUiVisibility != 0) {
                this.onWindowSystemUiVisibilityChanged(this.mLastSystemUiVisibility);
                ViewCompat.requestApplyInsets(((View)this));
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean arg1) {
        this.mHasNonEmbeddedTabs = arg1;
    }

    public void setHideOnContentScrollEnabled(boolean arg2) {
        if(arg2 != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = arg2;
            if(!arg2) {
                this.haltActionBarHideOffsetAnimations();
                this.setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int arg2) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(arg2);
    }

    public void setIcon(Drawable arg2) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(arg2);
    }

    public void setLogo(int arg2) {
        this.pullChildren();
        this.mDecorToolbar.setLogo(arg2);
    }

    public void setMenu(Menu arg2, Callback arg3) {
        this.pullChildren();
        this.mDecorToolbar.setMenu(arg2, arg3);
    }

    public void setMenuPrepared() {
        this.pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    public void setOverlayMode(boolean arg2) {
        this.mOverlayMode = arg2;
        arg2 = !arg2 || this.getContext().getApplicationInfo().targetSdkVersion >= 19 ? false : true;
        this.mIgnoreWindowContentOverlay = arg2;
    }

    public void setShowingForActionMode(boolean arg1) {
    }

    public void setUiOptions(int arg1) {
    }

    public void setWindowCallback(Window$Callback arg2) {
        this.pullChildren();
        this.mDecorToolbar.setWindowCallback(arg2);
    }

    public void setWindowTitle(CharSequence arg2) {
        this.pullChildren();
        this.mDecorToolbar.setWindowTitle(arg2);
    }

    public boolean shouldDelayChildPressedState() {
        return 0;
    }

    private boolean shouldHideActionBarOnFling(float arg10, float arg11) {
        this.mFlingEstimator.fling(0, 0, 0, ((int)arg11), 0, 0, 0x80000000, 0x7FFFFFFF);
        boolean v10 = this.mFlingEstimator.getFinalY() > this.mActionBarTop.getHeight() ? true : false;
        return v10;
    }

    public boolean showOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }
}

