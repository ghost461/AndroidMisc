package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;

public class ContentFrameLayout extends FrameLayout {
    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    public ContentFrameLayout(Context arg2) {
        this(arg2, null);
    }

    public ContentFrameLayout(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ContentFrameLayout(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.mDecorPadding = new Rect();
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void dispatchFitSystemWindows(Rect arg1) {
        this.fitSystemWindows(arg1);
    }

    public TypedValue getFixedHeightMajor() {
        if(this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }

        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if(this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }

        return this.mFixedHeightMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if(this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }

        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if(this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }

        return this.mFixedWidthMinor;
    }

    public TypedValue getMinWidthMajor() {
        if(this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }

        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if(this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }

        return this.mMinWidthMinor;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.mAttachListener != null) {
            this.mAttachListener.onAttachedFromWindow();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.mAttachListener != null) {
            this.mAttachListener.onDetachedFromWindow();
        }
    }

    protected void onMeasure(int arg14, int arg15) {
        int v10_1;
        DisplayMetrics v0 = this.getContext().getResources().getDisplayMetrics();
        int v3 = 1;
        int v1 = v0.widthPixels < v0.heightPixels ? 1 : 0;
        int v2 = View$MeasureSpec.getMode(arg14);
        int v5 = View$MeasureSpec.getMode(arg15);
        int v6 = 6;
        int v7 = 5;
        int v8 = 0x80000000;
        int v9 = 0x40000000;
        if(v2 == v8) {
            TypedValue v10 = v1 != 0 ? this.mFixedWidthMinor : this.mFixedWidthMajor;
            if(v10 == null) {
                goto label_53;
            }

            if(v10.type == 0) {
                goto label_53;
            }

            if(v10.type == v7) {
                v10_1 = ((int)v10.getDimension(v0));
            }
            else if(v10.type == v6) {
                v10_1 = ((int)v10.getFraction(((float)v0.widthPixels), ((float)v0.widthPixels)));
            }
            else {
                v10_1 = 0;
            }

            if(v10_1 <= 0) {
                goto label_53;
            }

            v10_1 = View$MeasureSpec.makeMeasureSpec(Math.min(v10_1 - (this.mDecorPadding.left + this.mDecorPadding.right), View$MeasureSpec.getSize(arg14)), v9);
            arg14 = 1;
        }
        else {
        label_53:
            v10_1 = arg14;
            arg14 = 0;
        }

        if(v5 == v8) {
            TypedValue v5_1 = v1 != 0 ? this.mFixedHeightMajor : this.mFixedHeightMinor;
            if(v5_1 == null) {
                goto label_88;
            }

            if(v5_1.type == 0) {
                goto label_88;
            }

            if(v5_1.type == v7) {
                v5 = ((int)v5_1.getDimension(v0));
            }
            else if(v5_1.type == v6) {
                v5 = ((int)v5_1.getFraction(((float)v0.heightPixels), ((float)v0.heightPixels)));
            }
            else {
                v5 = 0;
            }

            if(v5 <= 0) {
                goto label_88;
            }

            arg15 = View$MeasureSpec.makeMeasureSpec(Math.min(v5 - (this.mDecorPadding.top + this.mDecorPadding.bottom), View$MeasureSpec.getSize(arg15)), v9);
        }

    label_88:
        super.onMeasure(v10_1, arg15);
        v5 = this.getMeasuredWidth();
        v10_1 = View$MeasureSpec.makeMeasureSpec(v5, v9);
        if(arg14 != 0 || v2 != v8) {
        label_125:
            v3 = 0;
        }
        else {
            TypedValue v14 = v1 != 0 ? this.mMinWidthMinor : this.mMinWidthMajor;
            if(v14 == null) {
                goto label_125;
            }

            if(v14.type == 0) {
                goto label_125;
            }

            if(v14.type == v7) {
                arg14 = ((int)v14.getDimension(v0));
            }
            else if(v14.type == v6) {
                arg14 = ((int)v14.getFraction(((float)v0.widthPixels), ((float)v0.widthPixels)));
            }
            else {
                arg14 = 0;
            }

            if(arg14 > 0) {
                arg14 -= this.mDecorPadding.left + this.mDecorPadding.right;
            }

            if(v5 >= arg14) {
                goto label_125;
            }

            v10_1 = View$MeasureSpec.makeMeasureSpec(arg14, v9);
        }

        if(v3 != 0) {
            super.onMeasure(v10_1, arg15);
        }
    }

    public void setAttachListener(OnAttachListener arg1) {
        this.mAttachListener = arg1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void setDecorPadding(int arg2, int arg3, int arg4, int arg5) {
        this.mDecorPadding.set(arg2, arg3, arg4, arg5);
        if(ViewCompat.isLaidOut(((View)this))) {
            this.requestLayout();
        }
    }
}

