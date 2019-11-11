package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ActionBarContextView extends AbsActionBarView {
    private static final String TAG = "ActionBarContextView";
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public ActionBarContextView(Context arg2) {
        this(arg2, null);
    }

    public ActionBarContextView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, attr.actionModeStyle);
    }

    public ActionBarContextView(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4, arg5);
        TintTypedArray v3 = TintTypedArray.obtainStyledAttributes(arg3, arg4, styleable.ActionMode, arg5, 0);
        ViewCompat.setBackground(((View)this), v3.getDrawable(styleable.ActionMode_background));
        this.mTitleStyleRes = v3.getResourceId(styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = v3.getResourceId(styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = v3.getLayoutDimension(styleable.ActionMode_height, 0);
        this.mCloseItemLayout = v3.getResourceId(styleable.ActionMode_closeItemLayout, layout.abc_action_mode_close_item_material);
        v3.recycle();
    }

    public void animateToVisibility(int arg1) {
        super.animateToVisibility(arg1);
    }

    public boolean canShowOverflowMenu() {
        return super.canShowOverflowMenu();
    }

    public void closeMode() {
        if(this.mClose == null) {
            this.killMode();
            return;
        }
    }

    public void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup$MarginLayoutParams(-1, -2);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new ViewGroup$MarginLayoutParams(this.getContext(), arg3);
    }

    public int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public boolean hideOverflowMenu() {
        if(this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }

        return 0;
    }

    public void initForMode(ActionMode arg4) {
        if(this.mClose == null) {
            this.mClose = LayoutInflater.from(this.getContext()).inflate(this.mCloseItemLayout, ((ViewGroup)this), false);
            this.addView(this.mClose);
        }
        else if(this.mClose.getParent() == null) {
            this.addView(this.mClose);
        }

        this.mClose.findViewById(id.action_mode_close_button).setOnClickListener(new View$OnClickListener(arg4) {
            public void onClick(View arg1) {
                this.val$mode.finish();
            }
        });
        Menu v4 = arg4.getMenu();
        if(this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }

        this.mActionMenuPresenter = new ActionMenuPresenter(this.getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        ViewGroup$LayoutParams v0 = new ViewGroup$LayoutParams(-2, -1);
        ((MenuBuilder)v4).addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        this.mMenuView = this.mActionMenuPresenter.getMenuView(((ViewGroup)this));
        ViewCompat.setBackground(this.mMenuView, null);
        this.addView(this.mMenuView, v0);
    }

    private void initTitle() {
        if(this.mTitleLayout == null) {
            LayoutInflater.from(this.getContext()).inflate(layout.abc_action_bar_title_item, ((ViewGroup)this));
            this.mTitleLayout = this.getChildAt(this.getChildCount() - 1);
            this.mTitleView = this.mTitleLayout.findViewById(id.action_bar_title);
            this.mSubtitleView = this.mTitleLayout.findViewById(id.action_bar_subtitle);
            if(this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(this.getContext(), this.mTitleStyleRes);
            }

            if(this.mSubtitleStyleRes == 0) {
                goto label_30;
            }

            this.mSubtitleView.setTextAppearance(this.getContext(), this.mSubtitleStyleRes);
        }

    label_30:
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        int v0 = TextUtils.isEmpty(this.mTitle) ^ 1;
        int v1 = TextUtils.isEmpty(this.mSubtitle) ^ 1;
        TextView v2 = this.mSubtitleView;
        int v3 = 8;
        int v5 = v1 != 0 ? 0 : 8;
        v2.setVisibility(v5);
        LinearLayout v2_1 = this.mTitleLayout;
        if(v0 != 0 || v1 != 0) {
            v3 = 0;
        }

        v2_1.setVisibility(v3);
        if(this.mTitleLayout.getParent() == null) {
            this.addView(this.mTitleLayout);
        }
    }

    public boolean isOverflowMenuShowPending() {
        return super.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        if(this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }

        return 0;
    }

    public boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    public void killMode() {
        this.removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public boolean onHoverEvent(MotionEvent arg1) {
        return super.onHoverEvent(arg1);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent arg3) {
        if(arg3.getEventType() == 0x20) {
            arg3.setSource(((View)this));
            arg3.setClassName(this.getClass().getName());
            arg3.setPackageName(this.getContext().getPackageName());
            arg3.setContentDescription(this.mTitle);
        }
        else {
            super.onInitializeAccessibilityEvent(arg3);
        }
    }

    protected void onLayout(boolean arg10, int arg11, int arg12, int arg13, int arg14) {
        arg10 = ViewUtils.isLayoutRtl(((View)this));
        int v0 = arg10 ? arg13 - arg11 - this.getPaddingRight() : this.getPaddingLeft();
        int v6 = this.getPaddingTop();
        arg12 = arg14 - arg12 - this.getPaddingTop() - this.getPaddingBottom();
        int v7 = 8;
        if(this.mClose == null || this.mClose.getVisibility() == v7) {
            arg14 = v0;
        }
        else {
            ViewGroup$LayoutParams v14 = this.mClose.getLayoutParams();
            int v1 = arg10 ? ((ViewGroup$MarginLayoutParams)v14).rightMargin : ((ViewGroup$MarginLayoutParams)v14).leftMargin;
            arg14 = arg10 ? ((ViewGroup$MarginLayoutParams)v14).leftMargin : ((ViewGroup$MarginLayoutParams)v14).rightMargin;
            int v8 = ActionBarContextView.next(v0, v1, arg10);
            arg14 = ActionBarContextView.next(v8 + this.positionChild(this.mClose, v8, v6, arg12, arg10), arg14, arg10);
        }

        if(this.mTitleLayout != null && this.mCustomView == null && this.mTitleLayout.getVisibility() != v7) {
            arg14 += this.positionChild(this.mTitleLayout, arg14, v6, arg12, arg10);
        }

        int v2 = arg14;
        if(this.mCustomView != null) {
            this.positionChild(this.mCustomView, v2, v6, arg12, arg10);
        }

        int v3 = arg10 ? this.getPaddingLeft() : arg13 - arg11 - this.getPaddingRight();
        if(this.mMenuView != null) {
            this.positionChild(this.mMenuView, v3, v6, arg12, (((int)arg10)) ^ 1);
        }
    }

    protected void onMeasure(int arg11, int arg12) {
        int v6_1;
        StringBuilder v12;
        int v1 = 0x40000000;
        if(View$MeasureSpec.getMode(arg11) != v1) {
            v12 = new StringBuilder();
            v12.append(this.getClass().getSimpleName());
            v12.append(" can only be used ");
            v12.append("with android:layout_width=\"match_parent\" (or fill_parent)");
            throw new IllegalStateException(v12.toString());
        }

        if(View$MeasureSpec.getMode(arg12) == 0) {
            v12 = new StringBuilder();
            v12.append(this.getClass().getSimpleName());
            v12.append(" can only be used ");
            v12.append("with android:layout_height=\"wrap_content\"");
            throw new IllegalStateException(v12.toString());
        }

        arg11 = View$MeasureSpec.getSize(arg11);
        arg12 = this.mContentHeight > 0 ? this.mContentHeight : View$MeasureSpec.getSize(arg12);
        int v0 = this.getPaddingTop() + this.getPaddingBottom();
        int v2 = arg11 - this.getPaddingLeft() - this.getPaddingRight();
        int v3 = arg12 - v0;
        int v5 = View$MeasureSpec.makeMeasureSpec(v3, 0x80000000);
        int v7 = 0;
        if(this.mClose != null) {
            v2 = this.measureChildView(this.mClose, v2, v5, 0);
            ViewGroup$LayoutParams v6 = this.mClose.getLayoutParams();
            v2 -= ((ViewGroup$MarginLayoutParams)v6).leftMargin + ((ViewGroup$MarginLayoutParams)v6).rightMargin;
        }

        if(this.mMenuView != null && this.mMenuView.getParent() == this) {
            v2 = this.measureChildView(this.mMenuView, v2, v5, 0);
        }

        if(this.mTitleLayout != null && this.mCustomView == null) {
            if(this.mTitleOptional) {
                this.mTitleLayout.measure(View$MeasureSpec.makeMeasureSpec(0, 0), v5);
                v5 = this.mTitleLayout.getMeasuredWidth();
                v6_1 = v5 <= v2 ? 1 : 0;
                if(v6_1 != 0) {
                    v2 -= v5;
                }

                LinearLayout v5_1 = this.mTitleLayout;
                v6_1 = v6_1 != 0 ? 0 : 8;
                v5_1.setVisibility(v6_1);
            }
            else {
                v2 = this.measureChildView(this.mTitleLayout, v2, v5, 0);
            }
        }

        if(this.mCustomView != null) {
            ViewGroup$LayoutParams v5_2 = this.mCustomView.getLayoutParams();
            int v8 = -2;
            v6_1 = v5_2.width != v8 ? 0x40000000 : 0x80000000;
            if(v5_2.width >= 0) {
                v2 = Math.min(v5_2.width, v2);
            }

            if(v5_2.height != v8) {
            }
            else {
                v1 = 0x80000000;
            }

            if(v5_2.height >= 0) {
                v3 = Math.min(v5_2.height, v3);
            }

            this.mCustomView.measure(View$MeasureSpec.makeMeasureSpec(v2, v6_1), View$MeasureSpec.makeMeasureSpec(v3, v1));
        }

        if(this.mContentHeight <= 0) {
            arg12 = this.getChildCount();
            v1 = 0;
            while(v7 < arg12) {
                v2 = this.getChildAt(v7).getMeasuredHeight() + v0;
                if(v2 > v1) {
                    v1 = v2;
                }

                ++v7;
            }

            this.setMeasuredDimension(arg11, v1);
        }
        else {
            this.setMeasuredDimension(arg11, arg12);
        }
    }

    public boolean onTouchEvent(MotionEvent arg1) {
        return super.onTouchEvent(arg1);
    }

    public void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public void setContentHeight(int arg1) {
        this.mContentHeight = arg1;
    }

    public void setCustomView(View arg2) {
        if(this.mCustomView != null) {
            this.removeView(this.mCustomView);
        }

        this.mCustomView = arg2;
        if(arg2 != null && this.mTitleLayout != null) {
            this.removeView(this.mTitleLayout);
            this.mTitleLayout = null;
        }

        if(arg2 != null) {
            this.addView(arg2);
        }

        this.requestLayout();
    }

    public void setSubtitle(CharSequence arg1) {
        this.mSubtitle = arg1;
        this.initTitle();
    }

    public void setTitle(CharSequence arg1) {
        this.mTitle = arg1;
        this.initTitle();
    }

    public void setTitleOptional(boolean arg2) {
        if(arg2 != this.mTitleOptional) {
            this.requestLayout();
        }

        this.mTitleOptional = arg2;
    }

    public void setVisibility(int arg1) {
        super.setVisibility(arg1);
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int arg1, long arg2) {
        return super.setupAnimatorToVisibility(arg1, arg2);
    }

    public boolean shouldDelayChildPressedState() {
        return 0;
    }

    public boolean showOverflowMenu() {
        if(this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }

        return 0;
    }
}

