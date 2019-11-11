package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface DividerMode {
    }

    public class LayoutParams extends ViewGroup$MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(int arg1, int arg2) {
            super(arg1, arg2);
            this.gravity = -1;
            this.weight = 0f;
        }

        public LayoutParams(Context arg3, AttributeSet arg4) {
            super(arg3, arg4);
            this.gravity = -1;
            TypedArray v3 = arg3.obtainStyledAttributes(arg4, styleable.LinearLayoutCompat_Layout);
            this.weight = v3.getFloat(styleable.LinearLayoutCompat_Layout_android_layout_weight, 0f);
            this.gravity = v3.getInt(styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            v3.recycle();
        }

        public LayoutParams(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.gravity = -1;
        }

        public LayoutParams(int arg1, int arg2, float arg3) {
            super(arg1, arg2);
            this.gravity = -1;
            this.weight = arg3;
        }

        public LayoutParams(LayoutParams arg2) {
            super(((ViewGroup$MarginLayoutParams)arg2));
            this.gravity = -1;
            this.weight = arg2.weight;
            this.gravity = arg2.gravity;
        }

        public LayoutParams(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
            this.gravity = -1;
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface OrientationMode {
    }

    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public LinearLayoutCompat(Context arg2) {
        this(arg2, null);
    }

    public LinearLayoutCompat(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public LinearLayoutCompat(Context arg5, AttributeSet arg6, int arg7) {
        super(arg5, arg6, arg7);
        this.mBaselineAligned = true;
        int v1 = -1;
        this.mBaselineAlignedChildIndex = v1;
        this.mBaselineChildTop = 0;
        this.mGravity = 0x800033;
        TintTypedArray v5 = TintTypedArray.obtainStyledAttributes(arg5, arg6, styleable.LinearLayoutCompat, arg7, 0);
        int v6 = v5.getInt(styleable.LinearLayoutCompat_android_orientation, v1);
        if(v6 >= 0) {
            this.setOrientation(v6);
        }

        v6 = v5.getInt(styleable.LinearLayoutCompat_android_gravity, v1);
        if(v6 >= 0) {
            this.setGravity(v6);
        }

        boolean v6_1 = v5.getBoolean(styleable.LinearLayoutCompat_android_baselineAligned, true);
        if(!v6_1) {
            this.setBaselineAligned(v6_1);
        }

        this.mWeightSum = v5.getFloat(styleable.LinearLayoutCompat_android_weightSum, -1f);
        this.mBaselineAlignedChildIndex = v5.getInt(styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, v1);
        this.mUseLargestChild = v5.getBoolean(styleable.LinearLayoutCompat_measureWithLargestChild, false);
        this.setDividerDrawable(v5.getDrawable(styleable.LinearLayoutCompat_divider));
        this.mShowDividers = v5.getInt(styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = v5.getDimensionPixelSize(styleable.LinearLayoutCompat_dividerPadding, 0);
        v5.recycle();
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        return arg1 instanceof LayoutParams;
    }

    void drawDividersHorizontal(Canvas arg7) {
        int v0 = this.getVirtualChildCount();
        boolean v1 = ViewUtils.isLayoutRtl(((View)this));
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getVirtualChildAt(v2);
            if(v3 != null && v3.getVisibility() != 8 && (this.hasDividerBeforeChildAt(v2))) {
                ViewGroup$LayoutParams v4 = v3.getLayoutParams();
                int v3_1 = v1 ? v3.getRight() + ((LayoutParams)v4).rightMargin : v3.getLeft() - ((LayoutParams)v4).leftMargin - this.mDividerWidth;
                this.drawVerticalDivider(arg7, v3_1);
            }
        }

        if(this.hasDividerBeforeChildAt(v0)) {
            View v0_1 = this.getVirtualChildAt(v0 - 1);
            if(v0_1 != null) {
                ViewGroup$LayoutParams v2_1 = v0_1.getLayoutParams();
                v0 = v1 ? v0_1.getLeft() - ((LayoutParams)v2_1).leftMargin - this.mDividerWidth : v0_1.getRight() + ((LayoutParams)v2_1).rightMargin;
            }
            else if(v1) {
                v0 = this.getPaddingLeft();
            }
            else {
                v0 = this.getWidth() - this.getPaddingRight() - this.mDividerWidth;
            }

            this.drawVerticalDivider(arg7, v0);
        }
    }

    void drawDividersVertical(Canvas arg6) {
        int v0 = this.getVirtualChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getVirtualChildAt(v1);
            if(v2 != null && v2.getVisibility() != 8 && (this.hasDividerBeforeChildAt(v1))) {
                this.drawHorizontalDivider(arg6, v2.getTop() - v2.getLayoutParams().topMargin - this.mDividerHeight);
            }
        }

        if(this.hasDividerBeforeChildAt(v0)) {
            View v0_1 = this.getVirtualChildAt(v0 - 1);
            v0 = v0_1 == null ? this.getHeight() - this.getPaddingBottom() - this.mDividerHeight : v0_1.getBottom() + v0_1.getLayoutParams().bottomMargin;
            this.drawHorizontalDivider(arg6, v0);
        }
    }

    void drawHorizontalDivider(Canvas arg5, int arg6) {
        this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, arg6, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, this.mDividerHeight + arg6);
        this.mDivider.draw(arg5);
    }

    void drawVerticalDivider(Canvas arg6, int arg7) {
        this.mDivider.setBounds(arg7, this.getPaddingTop() + this.mDividerPadding, this.mDividerWidth + arg7, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
        this.mDivider.draw(arg6);
    }

    private void forceUniformHeight(int arg11, int arg12) {
        int v0 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0x40000000);
        int v1;
        for(v1 = 0; v1 < arg11; ++v1) {
            View v3 = this.getVirtualChildAt(v1);
            if(v3.getVisibility() != 8) {
                ViewGroup$LayoutParams v8 = v3.getLayoutParams();
                if(((LayoutParams)v8).height == -1) {
                    int v9 = ((LayoutParams)v8).width;
                    ((LayoutParams)v8).width = v3.getMeasuredWidth();
                    this.measureChildWithMargins(v3, arg12, 0, v0, 0);
                    ((LayoutParams)v8).width = v9;
                }
            }
        }
    }

    private void forceUniformWidth(int arg11, int arg12) {
        int v0 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0x40000000);
        int v1;
        for(v1 = 0; v1 < arg11; ++v1) {
            View v3 = this.getVirtualChildAt(v1);
            if(v3.getVisibility() != 8) {
                ViewGroup$LayoutParams v8 = v3.getLayoutParams();
                if(((LayoutParams)v8).width == -1) {
                    int v9 = ((LayoutParams)v8).height;
                    ((LayoutParams)v8).height = v3.getMeasuredHeight();
                    this.measureChildWithMargins(v3, v0, 0, arg12, 0);
                    ((LayoutParams)v8).height = v9;
                }
            }
        }
    }

    protected LayoutParams generateDefaultLayoutParams() {
        int v1 = -2;
        if(this.mOrientation == 0) {
            return new LayoutParams(v1, v1);
        }

        if(this.mOrientation == 1) {
            return new LayoutParams(-1, v1);
        }

        return null;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new LayoutParams(this.getContext(), arg3);
    }

    protected LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg2) {
        return new LayoutParams(arg2);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.generateLayoutParams(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.generateLayoutParams(arg1);
    }

    public int getBaseline() {
        if(this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }

        if(this.getChildCount() <= this.mBaselineAlignedChildIndex) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }

        View v0 = this.getChildAt(this.mBaselineAlignedChildIndex);
        int v1 = v0.getBaseline();
        int v2 = -1;
        if(v1 == v2) {
            if(this.mBaselineAlignedChildIndex == 0) {
                return v2;
            }

            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn\'t know how to get its baseline.");
        }

        v2 = this.mBaselineChildTop;
        if(this.mOrientation == 1) {
            int v3 = this.mGravity & 0x70;
            if(v3 != 0x30) {
                if(v3 == 16) {
                    v2 += (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.mTotalLength) / 2;
                }
                else if(v3 != 80) {
                }
                else {
                    v2 = this.getBottom() - this.getTop() - this.getPaddingBottom() - this.mTotalLength;
                }
            }
        }

        return v2 + v0.getLayoutParams().topMargin + v1;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    int getChildrenSkipCount(View arg1, int arg2) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    int getLocationOffset(View arg1) {
        return 0;
    }

    int getNextLocationOffset(View arg1) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    View getVirtualChildAt(int arg1) {
        return this.getChildAt(arg1);
    }

    int getVirtualChildCount() {
        return this.getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    protected boolean hasDividerBeforeChildAt(int arg5) {
        boolean v0 = false;
        if(arg5 == 0) {
            if((this.mShowDividers & 1) != 0) {
                v0 = true;
            }

            return v0;
        }

        if(arg5 == this.getChildCount()) {
            if((this.mShowDividers & 4) != 0) {
                v0 = true;
            }

            return v0;
        }

        if((this.mShowDividers & 2) != 0) {
            --arg5;
            while(arg5 >= 0) {
                if(this.getChildAt(arg5).getVisibility() != 8) {
                    v0 = true;
                }
                else {
                    --arg5;
                    continue;
                }

                return v0;
            }

            return v0;
        }

        return 0;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    void layoutHorizontal(int arg28, int arg29, int arg30, int arg31) {
        int v26;
        int v24;
        int v23;
        int v22;
        int v17;
        int v16;
        int v0;
        LinearLayoutCompat v6 = this;
        boolean v2 = ViewUtils.isLayoutRtl(((View)this));
        int v7 = this.getPaddingTop();
        int v3 = arg31 - arg29;
        int v8 = v3 - this.getPaddingBottom();
        int v9 = v3 - v7 - this.getPaddingBottom();
        int v10 = this.getVirtualChildCount();
        v3 = v6.mGravity & 0x800007;
        int v11 = v6.mGravity & 0x70;
        boolean v12 = v6.mBaselineAligned;
        int[] v13 = v6.mMaxAscent;
        int[] v14 = v6.mMaxDescent;
        v3 = GravityCompat.getAbsoluteGravity(v3, ViewCompat.getLayoutDirection(((View)this)));
        int v15 = 2;
        if(v3 == 1) {
            v0 = (arg30 - arg28 - v6.mTotalLength) / v15 + this.getPaddingLeft();
        }
        else if(v3 != 5) {
            v0 = this.getPaddingLeft();
        }
        else {
            v0 = this.getPaddingLeft() + arg30 - arg28 - v6.mTotalLength;
        }

        if(v2) {
            v16 = v10 - 1;
            v17 = -1;
        }
        else {
            v16 = 0;
            v17 = 1;
        }

        v3 = 0;
        while(v3 < v10) {
            int v2_1 = v16 + v17 * v3;
            View v1 = v6.getVirtualChildAt(v2_1);
            if(v1 == null) {
                v0 += v6.measureNullChild(v2_1);
                goto label_55;
            }
            else if(v1.getVisibility() != 8) {
                v15 = v1.getMeasuredWidth();
                int v5 = v1.getMeasuredHeight();
                ViewGroup$LayoutParams v4 = v1.getLayoutParams();
                if(v12) {
                    v22 = v3;
                    v23 = v10;
                    if(((LayoutParams)v4).height != -1) {
                        v3 = v1.getBaseline();
                    }
                    else {
                        goto label_78;
                    }
                }
                else {
                    v22 = v3;
                    v23 = v10;
                label_78:
                    v3 = -1;
                }

                v10 = ((LayoutParams)v4).gravity;
                if(v10 < 0) {
                    v10 = v11;
                }

                v10 &= 0x70;
                v24 = v11;
                if(v10 == 16) {
                    v3 = (v9 - v5) / 2 + v7 + ((LayoutParams)v4).topMargin - ((LayoutParams)v4).bottomMargin;
                }
                else if(v10 == 0x30) {
                    v10 = ((LayoutParams)v4).topMargin + v7;
                    if(v3 != -1) {
                        v10 += v13[1] - v3;
                    }

                    v3 = v10;
                }
                else if(v10 != 80) {
                    v3 = v7;
                }
                else {
                    v10 = v8 - v5 - ((LayoutParams)v4).bottomMargin;
                    if(v3 != -1) {
                        v10 -= v14[2] - (v1.getMeasuredHeight() - v3);
                    }

                    v3 = v10;
                }

                if(v6.hasDividerBeforeChildAt(v2_1)) {
                    v0 += v6.mDividerWidth;
                }

                v10 = ((LayoutParams)v4).leftMargin + v0;
                v26 = v7;
                v6.setChildFrame(v1, v10 + v6.getLocationOffset(v1), v3, v15, v5);
                v10 += v15 + v4.rightMargin + v6.getNextLocationOffset(v1);
                v3 = v22 + v6.getChildrenSkipCount(v1, v2_1);
                v0 = v10;
            }
            else {
            label_55:
                v26 = v7;
                v23 = v10;
                v24 = v11;
            }

            ++v3;
            v10 = v23;
            v11 = v24;
            v7 = v26;
        }
    }

    void layoutVertical(int arg19, int arg20, int arg21, int arg22) {
        int v0;
        LinearLayoutCompat v6 = this;
        int v7 = this.getPaddingLeft();
        int v2 = arg21 - arg19;
        int v8 = v2 - this.getPaddingRight();
        int v9 = v2 - v7 - this.getPaddingRight();
        int v10 = this.getVirtualChildCount();
        v2 = v6.mGravity & 0x70;
        int v11 = v6.mGravity & 0x800007;
        if(v2 == 16) {
            v0 = (arg22 - arg20 - v6.mTotalLength) / 2 + this.getPaddingTop();
        }
        else if(v2 != 80) {
            v0 = this.getPaddingTop();
        }
        else {
            v0 = this.getPaddingTop() + arg22 - arg20 - v6.mTotalLength;
        }

        int v12;
        for(v12 = 0; v12 < v10; ++v12) {
            View v13 = v6.getVirtualChildAt(v12);
            if(v13 == null) {
                v0 += v6.measureNullChild(v12);
            }
            else if(v13.getVisibility() != 8) {
                int v4 = v13.getMeasuredWidth();
                int v15 = v13.getMeasuredHeight();
                ViewGroup$LayoutParams v5 = v13.getLayoutParams();
                int v1 = ((LayoutParams)v5).gravity;
                if(v1 < 0) {
                    v1 = v11;
                }

                v1 = GravityCompat.getAbsoluteGravity(v1, ViewCompat.getLayoutDirection(((View)this))) & 7;
                if(v1 == 1) {
                    v1 = (v9 - v4) / 2 + v7 + ((LayoutParams)v5).leftMargin - ((LayoutParams)v5).rightMargin;
                }
                else if(v1 != 5) {
                    v1 = ((LayoutParams)v5).leftMargin + v7;
                }
                else {
                    v1 = v8 - v4 - ((LayoutParams)v5).rightMargin;
                }

                v2 = v1;
                if(v6.hasDividerBeforeChildAt(v12)) {
                    v0 += v6.mDividerHeight;
                }

                int v16 = v0 + ((LayoutParams)v5).topMargin;
                v6.setChildFrame(v13, v2, v16 + v6.getLocationOffset(v13), v4, v15);
                v16 += v15 + v5.bottomMargin + v6.getNextLocationOffset(v13);
                v12 += v6.getChildrenSkipCount(v13, v12);
                v0 = v16;
            }
        }
    }

    void measureChildBeforeLayout(View arg7, int arg8, int arg9, int arg10, int arg11, int arg12) {
        this.measureChildWithMargins(arg7, arg9, arg10, arg11, arg12);
    }

    void measureHorizontal(int arg40, int arg41) {
        int v38;
        int v37;
        int v36;
        ViewGroup$LayoutParams v6_3;
        View v4_2;
        int v6_2;
        int v5_1;
        int v4_1;
        View v12_1;
        ViewGroup$LayoutParams v8_1;
        int v34;
        int v3_1;
        int v10;
        int v35;
        boolean v32;
        boolean v30;
        int v1;
        int[] v29;
        LinearLayoutCompat v7 = this;
        int v8 = arg40;
        int v9 = arg41;
        v7.mTotalLength = 0;
        int v11 = this.getVirtualChildCount();
        int v12 = View$MeasureSpec.getMode(arg40);
        int v13 = View$MeasureSpec.getMode(arg41);
        int v14 = 4;
        if(v7.mMaxAscent == null || v7.mMaxDescent == null) {
            v7.mMaxAscent = new int[v14];
            v7.mMaxDescent = new int[v14];
        }

        int[] v15 = v7.mMaxAscent;
        int[] v6 = v7.mMaxDescent;
        int v16 = 3;
        v15[v16] = -1;
        int v17 = 2;
        v15[v17] = -1;
        v15[1] = -1;
        v15[0] = -1;
        v6[v16] = -1;
        v6[v17] = -1;
        v6[1] = -1;
        v6[0] = -1;
        boolean v4 = v7.mBaselineAligned;
        boolean v3 = v7.mUseLargestChild;
        int v2 = 0x40000000;
        int v19 = v12 == v2 ? 1 : 0;
        int v0 = 0;
        v14 = 0x80000000;
        float v21 = 0f;
        int v22 = 0;
        int v23 = 0;
        int v24 = 0;
        int v25 = 0;
        int v26 = 0;
        int v27 = 1;
        int v28 = 0;
        while(true) {
            v29 = v6;
            v1 = 8;
            if(v0 >= v11) {
                break;
            }

            View v6_1 = v7.getVirtualChildAt(v0);
            if(v6_1 == null) {
                v7.mTotalLength += v7.measureNullChild(v0);
                goto label_58;
            }
            else if(v6_1.getVisibility() == v1) {
                v0 += v7.getChildrenSkipCount(v6_1, v0);
            label_58:
                v1 = v0;
                v30 = v3;
                v32 = v4;
                v35 = v12;
            }
            else {
                if(v7.hasDividerBeforeChildAt(v0)) {
                    v7.mTotalLength += v7.mDividerWidth;
                }

                ViewGroup$LayoutParams v5 = v6_1.getLayoutParams();
                v21 += ((LayoutParams)v5).weight;
                if(v12 != v2 || ((LayoutParams)v5).width != 0 || ((LayoutParams)v5).weight <= 0f) {
                    if(((LayoutParams)v5).width != 0 || ((LayoutParams)v5).weight <= 0f) {
                        v2 = 0x80000000;
                    }
                    else {
                        ((LayoutParams)v5).width = -2;
                        v2 = 0;
                    }

                    int v31 = v21 == 0f ? v7.mTotalLength : 0;
                    v34 = v0;
                    v10 = v2;
                    v30 = v3;
                    v3_1 = v8;
                    v32 = v4;
                    v8_1 = v5;
                    v35 = v12;
                    v12_1 = v6_1;
                    v7.measureChildBeforeLayout(v6_1, v34, v3_1, v31, v9, 0);
                    if(v10 != 0x80000000) {
                        ((LayoutParams)v8_1).width = v10;
                    }

                    v0 = v12_1.getMeasuredWidth();
                    if(v19 != 0) {
                        v7.mTotalLength += ((LayoutParams)v8_1).leftMargin + v0 + ((LayoutParams)v8_1).rightMargin + v7.getNextLocationOffset(v12_1);
                    }
                    else {
                        v7.mTotalLength = Math.max(v7.mTotalLength, v7.mTotalLength + v0 + ((LayoutParams)v8_1).leftMargin + ((LayoutParams)v8_1).rightMargin + v7.getNextLocationOffset(v12_1));
                    }

                    if(v30) {
                        v14 = Math.max(v0, v14);
                    }

                label_183:
                    v0 = 0x40000000;
                }
                else {
                    if(v19 != 0) {
                        v7.mTotalLength += ((LayoutParams)v5).leftMargin + ((LayoutParams)v5).rightMargin;
                    }
                    else {
                        v7.mTotalLength = Math.max(v7.mTotalLength, ((LayoutParams)v5).leftMargin + v7.mTotalLength + ((LayoutParams)v5).rightMargin);
                    }

                    if(v4) {
                        v2 = View$MeasureSpec.makeMeasureSpec(0, 0);
                        v6_1.measure(v2, v2);
                        v34 = v0;
                        v30 = v3;
                        v32 = v4;
                        v8_1 = v5;
                        v35 = v12;
                        v12_1 = v6_1;
                        goto label_183;
                    }

                    v34 = v0;
                    v30 = v3;
                    v32 = v4;
                    v8_1 = v5;
                    v35 = v12;
                    v0 = 0x40000000;
                    v23 = 1;
                    v12_1 = v6_1;
                }

                if(v13 == v0 || ((LayoutParams)v8_1).height != -1) {
                    v1 = 0;
                }
                else {
                    v1 = 1;
                    v28 = 1;
                }

                v2 = ((LayoutParams)v8_1).topMargin + ((LayoutParams)v8_1).bottomMargin;
                v3_1 = v12_1.getMeasuredHeight() + v2;
                v4_1 = View.combineMeasuredStates(v26, v12_1.getMeasuredState());
                if(v32) {
                    v5_1 = v12_1.getBaseline();
                    if(v5_1 != -1) {
                        v6_2 = ((LayoutParams)v8_1).gravity < 0 ? v7.mGravity : ((LayoutParams)v8_1).gravity;
                        v6_2 = ((v6_2 & 0x70) >> 4 & -2) >> 1;
                        v15[v6_2] = Math.max(v15[v6_2], v5_1);
                        v29[v6_2] = Math.max(v29[v6_2], v3_1 - v5_1);
                    }
                }

                v5_1 = Math.max(v22, v3_1);
                v6_2 = v27 == 0 || ((LayoutParams)v8_1).height != -1 ? 0 : 1;
                if(((LayoutParams)v8_1).weight > 0f) {
                    if(v1 == 0) {
                        v2 = v3_1;
                    }

                    v8 = v25;
                    v8 = Math.max(v8, v2);
                }
                else {
                    v8 = v25;
                    if(v1 != 0) {
                        v3_1 = v2;
                    }

                    v24 = Math.max(v24, v3_1);
                }

                v10 = v34;
                v1 = v7.getChildrenSkipCount(v12_1, v10) + v10;
                v26 = v4_1;
                v22 = v5_1;
                v27 = v6_2;
                v25 = v8;
            }

            v0 = v1 + 1;
            v6 = v29;
            v3 = v30;
            v4 = v32;
            v12 = v35;
            v2 = 0x40000000;
            v8 = arg40;
        }

        v30 = v3;
        v32 = v4;
        v35 = v12;
        v5_1 = v22;
        v2 = v24;
        v8 = v25;
        v10 = v26;
        if(v7.mTotalLength > 0 && (v7.hasDividerBeforeChildAt(v11))) {
            v7.mTotalLength += v7.mDividerWidth;
        }

        v4_1 = -1;
        if(v15[1] != v4_1 || v15[0] != v4_1 || v15[v17] != v4_1 || v15[v16] != v4_1) {
            v5_1 = Math.max(v5_1, Math.max(v15[v16], Math.max(v15[0], Math.max(v15[1], v15[v17]))) + Math.max(v29[v16], Math.max(v29[0], Math.max(v29[1], v29[v17]))));
        }

        if(v30) {
            v0 = v35;
            if(v0 != 0x80000000 && v0 != 0) {
                goto label_358;
            }

            v7.mTotalLength = 0;
            v3_1 = 0;
            goto label_317;
        }
        else {
            v0 = v35;
            goto label_358;
        label_317:
            while(v3_1 < v11) {
                v4_2 = v7.getVirtualChildAt(v3_1);
                if(v4_2 == null) {
                    v7.mTotalLength += v7.measureNullChild(v3_1);
                    goto label_329;
                }
                else if(v4_2.getVisibility() == v1) {
                    v3_1 += v7.getChildrenSkipCount(v4_2, v3_1);
                    goto label_329;
                }
                else {
                    v6_3 = v4_2.getLayoutParams();
                    if(v19 != 0) {
                        v7.mTotalLength += ((LayoutParams)v6_3).leftMargin + v14 + ((LayoutParams)v6_3).rightMargin + v7.getNextLocationOffset(v4_2);
                    label_329:
                        v36 = v3_1;
                    }
                    else {
                        v36 = v3_1;
                        v7.mTotalLength = Math.max(v7.mTotalLength, v7.mTotalLength + v14 + ((LayoutParams)v6_3).leftMargin + ((LayoutParams)v6_3).rightMargin + v7.getNextLocationOffset(v4_2));
                    }
                }

                v3_1 = v36 + 1;
                v1 = 8;
            }
        }

    label_358:
        v7.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
        v1 = View.resolveSizeAndState(Math.max(v7.mTotalLength, this.getSuggestedMinimumWidth()), arg40, 0);
        v4_1 = (0xFFFFFF & v1) - v7.mTotalLength;
        if(v23 == 0) {
            if(v4_1 != 0 && v21 > 0f) {
                goto label_403;
            }

            v2 = Math.max(v2, v8);
            if((v30) && v0 != 0x40000000) {
                for(v0 = 0; v0 < v11; ++v0) {
                    v4_2 = v7.getVirtualChildAt(v0);
                    if(v4_2 != null) {
                        if(v4_2.getVisibility() == 8) {
                        }
                        else if(v4_2.getLayoutParams().weight > 0f) {
                            v4_2.measure(View$MeasureSpec.makeMeasureSpec(v14, 0x40000000), View$MeasureSpec.makeMeasureSpec(v4_2.getMeasuredHeight(), 0x40000000));
                        }
                    }
                }
            }

            v12 = v2;
            v37 = v11;
        }
        else {
        label_403:
            float v5_2 = v7.mWeightSum > 0f ? v7.mWeightSum : v21;
            v8 = -1;
            v15[v16] = v8;
            v15[v17] = v8;
            v15[1] = v8;
            v15[0] = v8;
            v29[v16] = v8;
            v29[v17] = v8;
            v29[1] = v8;
            v29[0] = v8;
            v7.mTotalLength = 0;
            v12 = v2;
            v2 = 0;
            v8 = -1;
            while(v2 < v11) {
                View v14_1 = v7.getVirtualChildAt(v2);
                if(v14_1 == null || v14_1.getVisibility() == 8) {
                    v37 = v11;
                }
                else {
                    v6_3 = v14_1.getLayoutParams();
                    float v3_2 = ((LayoutParams)v6_3).weight;
                    if(v3_2 > 0f) {
                        v37 = v11;
                        v11 = ((int)((((float)v4_1)) * v3_2 / v5_2));
                        v5_2 -= v3_2;
                        v38 = v4_1 - v11;
                        v3_1 = LinearLayoutCompat.getChildMeasureSpec(v9, this.getPaddingTop() + this.getPaddingBottom() + ((LayoutParams)v6_3).topMargin + ((LayoutParams)v6_3).bottomMargin, ((LayoutParams)v6_3).height);
                        if(((LayoutParams)v6_3).width == 0) {
                            v4_1 = 0x40000000;
                            if(v0 != v4_1) {
                                goto label_462;
                            }
                            else {
                                if(v11 > 0) {
                                }
                                else {
                                    v11 = 0;
                                }

                                v14_1.measure(View$MeasureSpec.makeMeasureSpec(v11, v4_1), v3_1);
                            }
                        }
                        else {
                            v4_1 = 0x40000000;
                        label_462:
                            v11 = v14_1.getMeasuredWidth() + v11;
                            if(v11 < 0) {
                                v11 = 0;
                            }

                            v14_1.measure(View$MeasureSpec.makeMeasureSpec(v11, v4_1), v3_1);
                        }

                        v10 = View.combineMeasuredStates(v10, v14_1.getMeasuredState() & 0xFF000000);
                    }
                    else {
                        v37 = v11;
                        v38 = v4_1;
                    }

                    if(v19 != 0) {
                        v7.mTotalLength += v14_1.getMeasuredWidth() + ((LayoutParams)v6_3).leftMargin + ((LayoutParams)v6_3).rightMargin + v7.getNextLocationOffset(v14_1);
                    }
                    else {
                        v7.mTotalLength = Math.max(v7.mTotalLength, v14_1.getMeasuredWidth() + v7.mTotalLength + ((LayoutParams)v6_3).leftMargin + ((LayoutParams)v6_3).rightMargin + v7.getNextLocationOffset(v14_1));
                    }

                    v3_1 = 0x40000000;
                    v3_1 = v13 == v3_1 || ((LayoutParams)v6_3).height != -1 ? 0 : 1;
                    v4_1 = ((LayoutParams)v6_3).topMargin + ((LayoutParams)v6_3).bottomMargin;
                    v11 = v14_1.getMeasuredHeight() + v4_1;
                    v8 = Math.max(v8, v11);
                    if(v3_1 != 0) {
                    }
                    else {
                        v4_1 = v11;
                    }

                    v3_1 = Math.max(v12, v4_1);
                    if(v27 != 0) {
                        v12 = -1;
                        if(((LayoutParams)v6_3).height == v12) {
                            v4_1 = 1;
                        }
                        else {
                            goto label_524;
                        }
                    }
                    else {
                        v12 = -1;
                    label_524:
                        v4_1 = 0;
                    }

                    if(v32) {
                        v14 = v14_1.getBaseline();
                        if(v14 != v12) {
                            v6_2 = ((LayoutParams)v6_3).gravity < 0 ? v7.mGravity : ((LayoutParams)v6_3).gravity;
                            v6_2 = ((v6_2 & 0x70) >> 4 & -2) >> 1;
                            v15[v6_2] = Math.max(v15[v6_2], v14);
                            v29[v6_2] = Math.max(v29[v6_2], v11 - v14);
                        }
                    }

                    v12 = v3_1;
                    v27 = v4_1;
                    v4_1 = v38;
                }

                ++v2;
                v11 = v37;
            }

            v37 = v11;
            v7.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
            v2 = -1;
            if(v15[1] == v2 && v15[0] == v2 && v15[v17] == v2) {
                if(v15[v16] != v2) {
                }
                else {
                    v5_1 = v8;
                    goto label_595;
                }
            }

            v5_1 = Math.max(v8, Math.max(v15[v16], Math.max(v15[0], Math.max(v15[1], v15[v17]))) + Math.max(v29[v16], Math.max(v29[0], Math.max(v29[1], v29[v17]))));
        }

    label_595:
        if(v27 != 0 || v13 == 0x40000000) {
            v12 = v5_1;
        }
        else {
        }

        v7.setMeasuredDimension(v1 | 0xFF000000 & v10, View.resolveSizeAndState(Math.max(v12 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), v9, v10 << 16));
        if(v28 != 0) {
            v7.forceUniformHeight(v37, arg40);
        }
    }

    int measureNullChild(int arg1) {
        return 0;
    }

    void measureVertical(int arg40, int arg41) {
        int v37;
        int v35;
        int v15_1;
        float v36;
        int v32;
        int v34;
        ViewGroup$LayoutParams v9_1;
        View v8_1;
        int v33;
        int v26;
        int v0_1;
        int v28;
        int v31;
        int v4_1;
        LinearLayoutCompat v7 = this;
        int v8 = arg40;
        int v9 = arg41;
        int v10 = 0;
        v7.mTotalLength = 0;
        int v11 = this.getVirtualChildCount();
        int v12 = View$MeasureSpec.getMode(arg40);
        int v13 = View$MeasureSpec.getMode(arg41);
        int v14 = v7.mBaselineAlignedChildIndex;
        boolean v15 = v7.mUseLargestChild;
        float v0 = 0f;
        int v1 = 0;
        int v2 = 0x80000000;
        int v3 = 0;
        int v5 = 0;
        int v18 = 0;
        int v19 = 1;
        int v20 = 0;
        int v21 = 0;
        while(v5 < v11) {
            View v4 = v7.getVirtualChildAt(v5);
            if(v4 == null) {
                v7.mTotalLength += v7.measureNullChild(v5);
                v4_1 = v10;
                v31 = v11;
                v28 = v13;
            }
            else {
                int v25 = v1;
                if(v4.getVisibility() == 8) {
                    v5 += v7.getChildrenSkipCount(v4, v5);
                    v4_1 = v10;
                    v31 = v11;
                    v28 = v13;
                    v1 = v25;
                }
                else {
                    if(v7.hasDividerBeforeChildAt(v5)) {
                        v7.mTotalLength += v7.mDividerHeight;
                    }

                    ViewGroup$LayoutParams v6 = v4.getLayoutParams();
                    float v23 = v0 + ((LayoutParams)v6).weight;
                    if(v13 != 0x40000000 || ((LayoutParams)v6).height != 0 || ((LayoutParams)v6).weight <= 0f) {
                        v26 = v2;
                        if(((LayoutParams)v6).height != 0 || ((LayoutParams)v6).weight <= 0f) {
                            v2 = 0x80000000;
                        }
                        else {
                            ((LayoutParams)v6).height = -2;
                            v2 = 0;
                        }

                        int v27 = v23 == 0f ? v7.mTotalLength : 0;
                        v28 = v13;
                        v13 = v25;
                        int v29 = v2;
                        int v30 = v26;
                        v2 = v5;
                        v31 = v11;
                        v11 = v3;
                        v3 = v8;
                        v8_1 = v4;
                        v33 = v11;
                        v32 = v21;
                        v11 = v5;
                        v5 = v9;
                        v9_1 = v6;
                        v34 = v10;
                        v7.measureChildBeforeLayout(v4, v2, v3, 0, v5, v27);
                        v0_1 = v29;
                        if(v0_1 != 0x80000000) {
                            ((LayoutParams)v9_1).height = v0_1;
                        }

                        v0_1 = v8_1.getMeasuredHeight();
                        v7.mTotalLength = Math.max(v7.mTotalLength, v7.mTotalLength + v0_1 + ((LayoutParams)v9_1).topMargin + ((LayoutParams)v9_1).bottomMargin + v7.getNextLocationOffset(v8_1));
                        if(v15) {
                            v26 = Math.max(v0_1, v30);
                            goto label_140;
                        }

                        v26 = v30;
                    }
                    else {
                        v26 = v2;
                        v7.mTotalLength = Math.max(v7.mTotalLength, ((LayoutParams)v6).topMargin + v7.mTotalLength + ((LayoutParams)v6).bottomMargin);
                        v33 = v3;
                        v8_1 = v4;
                        v9_1 = v6;
                        v34 = v10;
                        v31 = v11;
                        v28 = v13;
                        v32 = v21;
                        v13 = v25;
                        v18 = 1;
                        v11 = v5;
                    }

                label_140:
                    if(v14 >= 0 && v14 == v11 + 1) {
                        v7.mBaselineChildTop = v7.mTotalLength;
                    }

                    if(v11 < v14 && ((LayoutParams)v9_1).weight > 0f) {
                        throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won\'t work.  Either remove the weight, or don\'t set mBaselineAlignedChildIndex.");
                    }

                    if(v12 != 0x40000000) {
                        v1 = -1;
                        if(((LayoutParams)v9_1).width == v1) {
                            v0_1 = 1;
                            v20 = 1;
                        }
                        else {
                            goto label_161;
                        }
                    }
                    else {
                        v1 = -1;
                    label_161:
                        v0_1 = 0;
                    }

                    v2 = ((LayoutParams)v9_1).leftMargin + ((LayoutParams)v9_1).rightMargin;
                    v3 = v8_1.getMeasuredWidth() + v2;
                    v4_1 = Math.max(v13, v3);
                    v5 = View.combineMeasuredStates(v34, v8_1.getMeasuredState());
                    v1 = v19 == 0 || ((LayoutParams)v9_1).width != v1 ? 0 : 1;
                    if(((LayoutParams)v9_1).weight > 0f) {
                        if(v0_1 == 0) {
                            v2 = v3;
                        }

                        v9 = v33;
                        v3 = Math.max(v9, v2);
                    }
                    else {
                        v9 = v33;
                        if(v0_1 != 0) {
                            v3 = v2;
                        }

                        v21 = Math.max(v32, v3);
                        v3 = v9;
                        v32 = v21;
                    }

                    v19 = v1;
                    v1 = v4_1;
                    v4_1 = v5;
                    v2 = v26;
                    v21 = v32;
                    v5 = v7.getChildrenSkipCount(v8_1, v11) + v11;
                    v0 = v23;
                }
            }

            ++v5;
            v10 = v4_1;
            v13 = v28;
            v11 = v31;
            v8 = arg40;
            v9 = arg41;
        }

        int v6_1 = v2;
        v9 = v3;
        v4_1 = v10;
        v31 = v11;
        v28 = v13;
        v2 = v21;
        v10 = 0x80000000;
        v13 = v1;
        if(v7.mTotalLength > 0) {
            v3 = v31;
            if(v7.hasDividerBeforeChildAt(v3)) {
                v7.mTotalLength += v7.mDividerHeight;
            }
        }
        else {
            v3 = v31;
        }

        if(v15) {
            v5 = v28;
            if(v5 != v10 && v5 != 0) {
                goto label_265;
            }

            v7.mTotalLength = 0;
            v8 = 0;
            goto label_236;
        }
        else {
            v5 = v28;
            goto label_265;
        label_236:
            while(v8 < v3) {
                View v10_1 = v7.getVirtualChildAt(v8);
                if(v10_1 == null) {
                    v7.mTotalLength += v7.measureNullChild(v8);
                }
                else if(v10_1.getVisibility() == 8) {
                    v8 += v7.getChildrenSkipCount(v10_1, v8);
                }
                else {
                    ViewGroup$LayoutParams v11_1 = v10_1.getLayoutParams();
                    v7.mTotalLength = Math.max(v7.mTotalLength, v7.mTotalLength + v6_1 + ((LayoutParams)v11_1).topMargin + ((LayoutParams)v11_1).bottomMargin + v7.getNextLocationOffset(v10_1));
                }

                ++v8;
            }
        }

    label_265:
        v7.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
        v8 = arg41;
        v1 = View.resolveSizeAndState(Math.max(v7.mTotalLength, this.getSuggestedMinimumHeight()), v8, 0);
        v10 = (0xFFFFFF & v1) - v7.mTotalLength;
        if(v18 == 0) {
            if(v10 != 0 && v0 > 0f) {
                goto label_309;
            }

            v0_1 = Math.max(v2, v9);
            if((v15) && v5 != 0x40000000) {
                for(v2 = 0; v2 < v3; ++v2) {
                    View v5_1 = v7.getVirtualChildAt(v2);
                    if(v5_1 != null) {
                        if(v5_1.getVisibility() == 8) {
                        }
                        else if(v5_1.getLayoutParams().weight > 0f) {
                            v5_1.measure(View$MeasureSpec.makeMeasureSpec(v5_1.getMeasuredWidth(), 0x40000000), View$MeasureSpec.makeMeasureSpec(v6_1, 0x40000000));
                        }
                    }
                }
            }

            v9 = arg40;
        }
        else {
        label_309:
            if(v7.mWeightSum > 0f) {
                v0 = v7.mWeightSum;
            }

            v7.mTotalLength = 0;
            float v9_2 = v0;
            v0_1 = 0;
            int v38 = v10;
            v10 = v4_1;
            v4_1 = v38;
            while(v0_1 < v3) {
                View v11_2 = v7.getVirtualChildAt(v0_1);
                if(v11_2.getVisibility() == 8) {
                    v36 = v9_2;
                }
                else {
                    ViewGroup$LayoutParams v14_1 = v11_2.getLayoutParams();
                    float v6_2 = ((LayoutParams)v14_1).weight;
                    if(v6_2 > 0f) {
                        v15_1 = ((int)((((float)v4_1)) * v6_2 / v9_2));
                        v35 = v4_1 - v15_1;
                        v36 = v9_2 - v6_2;
                        v4_1 = LinearLayoutCompat.getChildMeasureSpec(arg40, this.getPaddingLeft() + this.getPaddingRight() + ((LayoutParams)v14_1).leftMargin + ((LayoutParams)v14_1).rightMargin, ((LayoutParams)v14_1).width);
                        if(((LayoutParams)v14_1).height == 0) {
                            v6_1 = 0x40000000;
                            if(v5 != v6_1) {
                                goto label_360;
                            }
                            else {
                                if(v15_1 > 0) {
                                }
                                else {
                                    v15_1 = 0;
                                }

                                v11_2.measure(v4_1, View$MeasureSpec.makeMeasureSpec(v15_1, v6_1));
                            }
                        }
                        else {
                            v6_1 = 0x40000000;
                        label_360:
                            v15_1 = v11_2.getMeasuredHeight() + v15_1;
                            if(v15_1 < 0) {
                                v15_1 = 0;
                            }

                            v11_2.measure(v4_1, View$MeasureSpec.makeMeasureSpec(v15_1, v6_1));
                        }

                        v10 = View.combineMeasuredStates(v10, v11_2.getMeasuredState() & 0xFFFFFF00);
                    }
                    else {
                        v35 = v4_1;
                        v36 = v9_2;
                    }

                    v4_1 = ((LayoutParams)v14_1).leftMargin + ((LayoutParams)v14_1).rightMargin;
                    v6_1 = v11_2.getMeasuredWidth() + v4_1;
                    v13 = Math.max(v13, v6_1);
                    if(v12 != 0x40000000) {
                        v37 = v4_1;
                        v4_1 = -1;
                        if(((LayoutParams)v14_1).width == v4_1) {
                            v15_1 = 1;
                        }
                        else {
                            goto label_390;
                        }
                    }
                    else {
                        v37 = v4_1;
                        v4_1 = -1;
                    label_390:
                        v15_1 = 0;
                    }

                    if(v15_1 != 0) {
                        v6_1 = v37;
                    }

                    v2 = Math.max(v2, v6_1);
                    v6_1 = v19 == 0 || ((LayoutParams)v14_1).width != v4_1 ? 0 : 1;
                    v7.mTotalLength = Math.max(v7.mTotalLength, v7.mTotalLength + v11_2.getMeasuredHeight() + ((LayoutParams)v14_1).topMargin + ((LayoutParams)v14_1).bottomMargin + v7.getNextLocationOffset(v11_2));
                    v19 = v6_1;
                    v4_1 = v35;
                }

                ++v0_1;
                v9_2 = v36;
            }

            v9 = arg40;
            v7.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
            v0_1 = v2;
            v4_1 = v10;
        }

        if(v19 == 0 && v12 != 0x40000000) {
            v13 = v0_1;
        }

        v7.setMeasuredDimension(View.resolveSizeAndState(Math.max(v13 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), v9, v4_1), v1);
        if(v20 != 0) {
            v7.forceUniformWidth(v3, v8);
        }
    }

    protected void onDraw(Canvas arg3) {
        if(this.mDivider == null) {
            return;
        }

        if(this.mOrientation == 1) {
            this.drawDividersVertical(arg3);
        }
        else {
            this.drawDividersHorizontal(arg3);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent arg3) {
        if(Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(arg3);
            arg3.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg3) {
        if(Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(arg3);
            arg3.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        if(this.mOrientation == 1) {
            this.layoutVertical(arg3, arg4, arg5, arg6);
        }
        else {
            this.layoutHorizontal(arg3, arg4, arg5, arg6);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        if(this.mOrientation == 1) {
            this.measureVertical(arg3, arg4);
        }
        else {
            this.measureHorizontal(arg3, arg4);
        }
    }

    public void setBaselineAligned(boolean arg1) {
        this.mBaselineAligned = arg1;
    }

    public void setBaselineAlignedChildIndex(int arg3) {
        if(arg3 >= 0) {
            if(arg3 >= this.getChildCount()) {
            }
            else {
                this.mBaselineAlignedChildIndex = arg3;
                return;
            }
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("base aligned child index out of range (0, ");
        v0.append(this.getChildCount());
        v0.append(")");
        throw new IllegalArgumentException(v0.toString());
    }

    private void setChildFrame(View arg1, int arg2, int arg3, int arg4, int arg5) {
        arg1.layout(arg2, arg3, arg4 + arg2, arg5 + arg3);
    }

    public void setDividerDrawable(Drawable arg3) {
        if(arg3 == this.mDivider) {
            return;
        }

        this.mDivider = arg3;
        boolean v0 = false;
        if(arg3 != null) {
            this.mDividerWidth = arg3.getIntrinsicWidth();
            this.mDividerHeight = arg3.getIntrinsicHeight();
        }
        else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }

        if(arg3 == null) {
            v0 = true;
        }

        this.setWillNotDraw(v0);
        this.requestLayout();
    }

    public void setDividerPadding(int arg1) {
        this.mDividerPadding = arg1;
    }

    public void setGravity(int arg2) {
        if(this.mGravity != arg2) {
            if((0x800007 & arg2) == 0) {
                arg2 |= 0x800003;
            }

            if((arg2 & 0x70) == 0) {
                arg2 |= 0x30;
            }

            this.mGravity = arg2;
            this.requestLayout();
        }
    }

    public void setHorizontalGravity(int arg3) {
        arg3 &= 0x800007;
        if((0x800007 & this.mGravity) != arg3) {
            this.mGravity = arg3 | this.mGravity & 0xFF7FFFF8;
            this.requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean arg1) {
        this.mUseLargestChild = arg1;
    }

    public void setOrientation(int arg2) {
        if(this.mOrientation != arg2) {
            this.mOrientation = arg2;
            this.requestLayout();
        }
    }

    public void setShowDividers(int arg2) {
        if(arg2 != this.mShowDividers) {
            this.requestLayout();
        }

        this.mShowDividers = arg2;
    }

    public void setVerticalGravity(int arg2) {
        arg2 &= 0x70;
        if((this.mGravity & 0x70) != arg2) {
            this.mGravity = arg2 | this.mGravity & 0xFFFFFF8F;
            this.requestLayout();
        }
    }

    public void setWeightSum(float arg2) {
        this.mWeightSum = Math.max(0f, arg2);
    }

    public boolean shouldDelayChildPressedState() {
        return 0;
    }
}

