package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ButtonBarLayout extends LinearLayout {
    private static final int ALLOW_STACKING_MIN_HEIGHT_DP = 320;
    private static final int PEEK_BUTTON_DP = 16;
    private boolean mAllowStacking;
    private int mLastWidthSize;
    private int mMinimumHeight;

    public ButtonBarLayout(Context arg4, AttributeSet arg5) {
        super(arg4, arg5);
        this.mLastWidthSize = -1;
        boolean v0 = false;
        this.mMinimumHeight = 0;
        if(this.getResources().getConfiguration().screenHeightDp >= 320) {
            v0 = true;
        }

        TypedArray v4 = arg4.obtainStyledAttributes(arg5, styleable.ButtonBarLayout);
        this.mAllowStacking = v4.getBoolean(styleable.ButtonBarLayout_allowStacking, v0);
        v4.recycle();
    }

    public int getMinimumHeight() {
        return Math.max(this.mMinimumHeight, super.getMinimumHeight());
    }

    private int getNextVisibleChildIndex(int arg3) {
        int v0 = this.getChildCount();
        while(arg3 < v0) {
            if(this.getChildAt(arg3).getVisibility() == 0) {
                return arg3;
            }

            ++arg3;
        }

        return -1;
    }

    private boolean isStacked() {
        boolean v1 = true;
        if(this.getOrientation() == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    protected void onMeasure(int arg6, int arg7) {
        int v1;
        int v0 = View$MeasureSpec.getSize(arg6);
        int v2 = 0;
        if(this.mAllowStacking) {
            if(v0 > this.mLastWidthSize && (this.isStacked())) {
                this.setStacked(false);
            }

            this.mLastWidthSize = v0;
        }

        if((this.isStacked()) || View$MeasureSpec.getMode(arg6) != 0x40000000) {
            v0 = arg6;
            v1 = 0;
        }
        else {
            v0 = View$MeasureSpec.makeMeasureSpec(v0, 0x80000000);
            v1 = 1;
        }

        super.onMeasure(v0, arg7);
        if((this.mAllowStacking) && !this.isStacked()) {
            v0 = (this.getMeasuredWidthAndState() & 0xFF000000) == 0x1000000 ? 1 : 0;
            if(v0 == 0) {
                goto label_38;
            }

            this.setStacked(true);
            v1 = 1;
        }

    label_38:
        if(v1 != 0) {
            super.onMeasure(arg6, arg7);
        }

        arg6 = this.getNextVisibleChildIndex(0);
        if(arg6 >= 0) {
            View v7 = this.getChildAt(arg6);
            ViewGroup$LayoutParams v0_1 = v7.getLayoutParams();
            v1 = this.getPaddingTop() + v7.getMeasuredHeight() + ((LinearLayout$LayoutParams)v0_1).topMargin + ((LinearLayout$LayoutParams)v0_1).bottomMargin;
            if(this.isStacked()) {
                arg6 = this.getNextVisibleChildIndex(arg6 + 1);
                if(arg6 >= 0) {
                    v1 += this.getChildAt(arg6).getPaddingTop() + (((int)(this.getResources().getDisplayMetrics().density * 16f)));
                }

                v2 = v1;
            }
            else {
                v2 = v1 + this.getPaddingBottom();
            }
        }

        if(ViewCompat.getMinimumHeight(((View)this)) != v2) {
            this.setMinimumHeight(v2);
        }
    }

    public void setAllowStacking(boolean arg2) {
        if(this.mAllowStacking != arg2) {
            this.mAllowStacking = arg2;
            if(!this.mAllowStacking && this.getOrientation() == 1) {
                this.setStacked(false);
            }

            this.requestLayout();
        }
    }

    private void setStacked(boolean arg2) {
        int v2;
        this.setOrientation(((int)arg2));
        int v0 = arg2 ? 5 : 80;
        this.setGravity(v0);
        View v0_1 = this.findViewById(id.spacer);
        if(v0_1 != null) {
            v2 = arg2 ? 8 : 4;
            v0_1.setVisibility(v2);
        }

        for(v2 = this.getChildCount() - 2; v2 >= 0; --v2) {
            this.bringChildToFront(this.getChildAt(v2));
        }
    }
}

