package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ActionBarContainer extends FrameLayout {
    private View mActionBarView;
    Drawable mBackground;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;

    public ActionBarContainer(Context arg2) {
        this(arg2, null);
    }

    public ActionBarContainer(Context arg3, AttributeSet arg4) {
        ActionBarBackgroundDrawableV21 v0;
        super(arg3, arg4);
        if(Build$VERSION.SDK_INT >= 21) {
            v0 = new ActionBarBackgroundDrawableV21(this);
        }
        else {
            ActionBarBackgroundDrawable v0_1 = new ActionBarBackgroundDrawable(this);
        }

        ViewCompat.setBackground(((View)this), ((Drawable)v0));
        TypedArray v3 = arg3.obtainStyledAttributes(arg4, styleable.ActionBar);
        this.mBackground = v3.getDrawable(styleable.ActionBar_background);
        this.mStackedBackground = v3.getDrawable(styleable.ActionBar_backgroundStacked);
        this.mHeight = v3.getDimensionPixelSize(styleable.ActionBar_height, -1);
        if(this.getId() == id.split_action_bar) {
            this.mIsSplit = true;
            this.mSplitBackground = v3.getDrawable(styleable.ActionBar_backgroundSplit);
        }

        v3.recycle();
        boolean v4 = false;
        if(this.mIsSplit) {
            if(this.mSplitBackground != null) {
                goto label_43;
            }

            goto label_36;
        }
        else if(this.mBackground == null && this.mStackedBackground == null) {
        label_36:
            v4 = true;
        }

    label_43:
        this.setWillNotDraw(v4);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.mBackground != null && (this.mBackground.isStateful())) {
            this.mBackground.setState(this.getDrawableState());
        }

        if(this.mStackedBackground != null && (this.mStackedBackground.isStateful())) {
            this.mStackedBackground.setState(this.getDrawableState());
        }

        if(this.mSplitBackground != null && (this.mSplitBackground.isStateful())) {
            this.mSplitBackground.setState(this.getDrawableState());
        }
    }

    private int getMeasuredHeightWithMargins(View arg3) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        return arg3.getMeasuredHeight() + ((FrameLayout$LayoutParams)v0).topMargin + ((FrameLayout$LayoutParams)v0).bottomMargin;
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    private boolean isCollapsed(View arg3) {
        boolean v3 = arg3 == null || arg3.getVisibility() == 8 || arg3.getMeasuredHeight() == 0 ? true : false;
        return v3;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if(this.mBackground != null) {
            this.mBackground.jumpToCurrentState();
        }

        if(this.mStackedBackground != null) {
            this.mStackedBackground.jumpToCurrentState();
        }

        if(this.mSplitBackground != null) {
            this.mSplitBackground.jumpToCurrentState();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = this.findViewById(id.action_bar);
        this.mContextView = this.findViewById(id.action_context_bar);
    }

    public boolean onHoverEvent(MotionEvent arg1) {
        super.onHoverEvent(arg1);
        return 1;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg2) {
        boolean v2 = (this.mIsTransitioning) || (super.onInterceptTouchEvent(arg2)) ? true : false;
        return v2;
    }

    public void onLayout(boolean arg6, int arg7, int arg8, int arg9, int arg10) {
        super.onLayout(arg6, arg7, arg8, arg9, arg10);
        View v6 = this.mTabContainer;
        arg8 = 8;
        arg10 = 1;
        int v0 = 0;
        boolean v1 = v6 == null || v6.getVisibility() == arg8 ? false : true;
        if(v6 != null && v6.getVisibility() != arg8) {
            arg8 = this.getMeasuredHeight();
            ViewGroup$LayoutParams v2 = v6.getLayoutParams();
            v6.layout(arg7, arg8 - v6.getMeasuredHeight() - ((FrameLayout$LayoutParams)v2).bottomMargin, arg9, arg8 - ((FrameLayout$LayoutParams)v2).bottomMargin);
        }

        if(!this.mIsSplit) {
            if(this.mBackground != null) {
                if(this.mActionBarView.getVisibility() == 0) {
                    this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
                }
                else {
                    if(this.mContextView != null && this.mContextView.getVisibility() == 0) {
                        this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
                        goto label_68;
                    }

                    this.mBackground.setBounds(0, 0, 0, 0);
                }

            label_68:
                v0 = 1;
            }

            this.mIsStacked = v1;
            if((v1) && this.mStackedBackground != null) {
                this.mStackedBackground.setBounds(v6.getLeft(), v6.getTop(), v6.getRight(), v6.getBottom());
                goto label_81;
            }

            arg10 = v0;
        }
        else if(this.mSplitBackground != null) {
            this.mSplitBackground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        }
        else {
            arg10 = 0;
        }

    label_81:
        if(arg10 != 0) {
            this.invalidate();
        }
    }

    public void onMeasure(int arg4, int arg5) {
        int v0;
        int v1 = 0x80000000;
        if(this.mActionBarView == null && View$MeasureSpec.getMode(arg5) == v1 && this.mHeight >= 0) {
            arg5 = View$MeasureSpec.makeMeasureSpec(Math.min(this.mHeight, View$MeasureSpec.getSize(arg5)), v1);
        }

        super.onMeasure(arg4, arg5);
        if(this.mActionBarView == null) {
            return;
        }

        arg4 = View$MeasureSpec.getMode(arg5);
        if(this.mTabContainer != null && this.mTabContainer.getVisibility() != 8 && arg4 != 0x40000000) {
            if(!this.isCollapsed(this.mActionBarView)) {
                v0 = this.getMeasuredHeightWithMargins(this.mActionBarView);
            }
            else if(!this.isCollapsed(this.mContextView)) {
                v0 = this.getMeasuredHeightWithMargins(this.mContextView);
            }
            else {
                v0 = 0;
            }

            arg4 = arg4 == v1 ? View$MeasureSpec.getSize(arg5) : 0x7FFFFFFF;
            this.setMeasuredDimension(this.getMeasuredWidth(), Math.min(v0 + this.getMeasuredHeightWithMargins(this.mTabContainer), arg4));
        }
    }

    public boolean onTouchEvent(MotionEvent arg1) {
        super.onTouchEvent(arg1);
        return 1;
    }

    public void setPrimaryBackground(Drawable arg5) {
        if(this.mBackground != null) {
            this.mBackground.setCallback(null);
            this.unscheduleDrawable(this.mBackground);
        }

        this.mBackground = arg5;
        if(arg5 != null) {
            arg5.setCallback(((Drawable$Callback)this));
            if(this.mActionBarView != null) {
                this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
            }
        }

        boolean v0 = false;
        if(this.mIsSplit) {
            if(this.mSplitBackground != null) {
                goto label_35;
            }

            goto label_28;
        }
        else if(this.mBackground == null && this.mStackedBackground == null) {
        label_28:
            v0 = true;
        }

    label_35:
        this.setWillNotDraw(v0);
        this.invalidate();
    }

    public void setSplitBackground(Drawable arg4) {
        if(this.mSplitBackground != null) {
            this.mSplitBackground.setCallback(null);
            this.unscheduleDrawable(this.mSplitBackground);
        }

        this.mSplitBackground = arg4;
        boolean v0 = false;
        if(arg4 != null) {
            arg4.setCallback(((Drawable$Callback)this));
            if((this.mIsSplit) && this.mSplitBackground != null) {
                this.mSplitBackground.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }

        if(this.mIsSplit) {
            if(this.mSplitBackground != null) {
                goto label_31;
            }

            goto label_24;
        }
        else if(this.mBackground == null && this.mStackedBackground == null) {
        label_24:
            v0 = true;
        }

    label_31:
        this.setWillNotDraw(v0);
        this.invalidate();
    }

    public void setStackedBackground(Drawable arg5) {
        if(this.mStackedBackground != null) {
            this.mStackedBackground.setCallback(null);
            this.unscheduleDrawable(this.mStackedBackground);
        }

        this.mStackedBackground = arg5;
        if(arg5 != null) {
            arg5.setCallback(((Drawable$Callback)this));
            if((this.mIsStacked) && this.mStackedBackground != null) {
                this.mStackedBackground.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
            }
        }

        boolean v0 = false;
        if(this.mIsSplit) {
            if(this.mSplitBackground != null) {
                goto label_37;
            }

            goto label_30;
        }
        else if(this.mBackground == null && this.mStackedBackground == null) {
        label_30:
            v0 = true;
        }

    label_37:
        this.setWillNotDraw(v0);
        this.invalidate();
    }

    public void setTabContainer(ScrollingTabContainerView arg3) {
        if(this.mTabContainer != null) {
            this.removeView(this.mTabContainer);
        }

        this.mTabContainer = ((View)arg3);
        if(arg3 != null) {
            this.addView(((View)arg3));
            ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
            v0.width = -1;
            v0.height = -2;
            arg3.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean arg1) {
        this.mIsTransitioning = arg1;
        int v1 = arg1 ? 0x60000 : 0x40000;
        this.setDescendantFocusability(v1);
    }

    public void setVisibility(int arg3) {
        super.setVisibility(arg3);
        boolean v3 = arg3 == 0 ? true : false;
        if(this.mBackground != null) {
            this.mBackground.setVisible(v3, false);
        }

        if(this.mStackedBackground != null) {
            this.mStackedBackground.setVisible(v3, false);
        }

        if(this.mSplitBackground != null) {
            this.mSplitBackground.setVisible(v3, false);
        }
    }

    public ActionMode startActionModeForChild(View arg1, ActionMode$Callback arg2) {
        return null;
    }

    public ActionMode startActionModeForChild(View arg1, ActionMode$Callback arg2, int arg3) {
        if(arg3 != 0) {
            return super.startActionModeForChild(arg1, arg2, arg3);
        }

        return null;
    }

    protected boolean verifyDrawable(Drawable arg2) {
        boolean v2;
        if(arg2 != this.mBackground || (this.mIsSplit)) {
            if(arg2 == this.mStackedBackground && (this.mIsStacked)) {
                goto label_14;
            }

            if(arg2 == this.mSplitBackground && (this.mIsSplit)) {
                goto label_14;
            }

            if(super.verifyDrawable(arg2)) {
            label_14:
                v2 = true;
                return v2;
            }

            v2 = false;
        }
        else {
            goto label_14;
        }

        return v2;
    }
}

