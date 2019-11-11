package org.chromium.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.chromium.base.ApiCompatibilityUtils;

public class DropdownAdapter extends ArrayAdapter {
    private final boolean mAreAllItemsEnabled;
    private final Integer mBackgroundColor;
    private final Context mContext;
    private final Integer mDividerColor;
    private final Integer mDropdownItemHeight;
    private final boolean mHasUniformHorizontalMargin;
    private final int mLabelHorizontalMargin;
    private final int mLabelVerticalMargin;
    private final Set mSeparators;

    public DropdownAdapter(Context arg2, List arg3, Set arg4, @Nullable Integer arg5, @Nullable Integer arg6, @Nullable Integer arg7, @Nullable Integer arg8) {
        super(arg2, 0x7F090022);
        this.mContext = arg2;
        this.addAll(((Collection)arg3));
        this.mSeparators = arg4;
        this.mAreAllItemsEnabled = this.checkAreAllItemsEnabled();
        this.mBackgroundColor = arg5;
        this.mDividerColor = arg6;
        this.mDropdownItemHeight = arg7;
        this.mLabelVerticalMargin = arg2.getResources().getDimensionPixelSize(0x7F050058);
        if(arg8 == null) {
            this.mLabelHorizontalMargin = this.mLabelVerticalMargin;
            this.mHasUniformHorizontalMargin = false;
        }
        else {
            this.mLabelHorizontalMargin = ((int)TypedValue.applyDimension(1, ((float)arg8.intValue()), arg2.getResources().getDisplayMetrics()));
            this.mHasUniformHorizontalMargin = true;
        }
    }

    public boolean areAllItemsEnabled() {
        return this.mAreAllItemsEnabled;
    }

    private boolean checkAreAllItemsEnabled() {
        int v1;
        for(v1 = 0; v1 < this.getCount(); ++v1) {
            Object v2 = this.getItem(v1);
            if((((DropdownItem)v2).isEnabled()) && !((DropdownItem)v2).isGroupHeader()) {
                return 0;
            }
        }

        return 1;
    }

    public View getView(int arg10, View arg11, ViewGroup arg12) {
        LinearLayout$LayoutParams v1_1;
        int v4;
        arg12 = null;
        if(arg11 == null) {
            arg11 = this.mContext.getSystemService("layout_inflater").inflate(0x7F090022, arg12);
            arg11.setBackground(new DropdownDividerDrawable(this.mBackgroundColor));
        }

        Drawable v0 = arg11.getBackground();
        int v1 = this.mDropdownItemHeight == null ? this.mContext.getResources().getDimensionPixelSize(0x7F050056) : ((int)TypedValue.applyDimension(1, ((float)this.mDropdownItemHeight.intValue()), this.mContext.getResources().getDisplayMetrics()));
        if(arg10 == 0) {
            ((DropdownDividerDrawable)v0).setDividerColor(0);
        }
        else {
            v4 = this.mContext.getResources().getDimensionPixelSize(0x7F050055);
            v1 += v4;
            ((DropdownDividerDrawable)v0).setHeight(v4);
            if(this.mSeparators != null && (this.mSeparators.contains(Integer.valueOf(arg10)))) {
                v4 = ApiCompatibilityUtils.getColor(this.mContext.getResources(), 0x7F04002E);
            }
            else if(this.mDividerColor == null) {
                v4 = ApiCompatibilityUtils.getColor(this.mContext.getResources(), 0x7F04002F);
            }
            else {
                v4 = this.mDividerColor.intValue();
            }

            ((DropdownDividerDrawable)v0).setDividerColor(v4);
        }

        Object v10 = this.getItem(arg10);
        View v0_1 = arg11.findViewById(0x7F07003D);
        int v5 = -2;
        if(((DropdownItem)v10).isMultilineLabel()) {
            v1 = -2;
        }

        if(((DropdownItem)v10).isLabelAndSublabelOnSameLine()) {
            ((LinearLayout)v0_1).setOrientation(0);
        }
        else {
            ((LinearLayout)v0_1).setOrientation(1);
        }

        float v6 = 1f;
        ((LinearLayout)v0_1).setLayoutParams(new LinearLayout$LayoutParams(0, v1, v6));
        v0_1 = arg11.findViewById(0x7F07003C);
        ((TextView)v0_1).setText(((DropdownItem)v10).getLabel());
        ((TextView)v0_1).setSingleLine(((DropdownItem)v10).isMultilineLabel() ^ 1);
        if(((DropdownItem)v10).isLabelAndSublabelOnSameLine()) {
            v1_1 = new LinearLayout$LayoutParams(0, v5, v6);
        }
        else {
            v1_1 = new LinearLayout$LayoutParams(v5, v5);
            if(((DropdownItem)v10).getIconId() == 0 || !this.mHasUniformHorizontalMargin) {
                ApiCompatibilityUtils.setMarginStart(((ViewGroup$MarginLayoutParams)v1_1), this.mLabelHorizontalMargin);
            }

            ApiCompatibilityUtils.setMarginEnd(((ViewGroup$MarginLayoutParams)v1_1), this.mLabelHorizontalMargin);
            if(!((DropdownItem)v10).isMultilineLabel()) {
                goto label_104;
            }

            ApiCompatibilityUtils.setPaddingRelative(v0_1, ApiCompatibilityUtils.getPaddingStart(v0_1), this.mLabelVerticalMargin, ApiCompatibilityUtils.getPaddingEnd(v0_1), this.mLabelVerticalMargin);
        }

    label_104:
        ((TextView)v0_1).setLayoutParams(((ViewGroup$LayoutParams)v1_1));
        ((TextView)v0_1).setEnabled(((DropdownItem)v10).isEnabled());
        if((((DropdownItem)v10).isGroupHeader()) || (((DropdownItem)v10).isBoldLabel())) {
            ((TextView)v0_1).setTypeface(((Typeface)arg12), 1);
        }
        else {
            ((TextView)v0_1).setTypeface(((Typeface)arg12), 0);
        }

        ((TextView)v0_1).setTextColor(ApiCompatibilityUtils.getColor(this.mContext.getResources(), ((DropdownItem)v10).getLabelFontColorResId()));
        ((TextView)v0_1).setTextSize(0, this.mContext.getResources().getDimension(((DropdownItem)v10).getLabelFontSizeResId()));
        View v12 = arg11.findViewById(0x7F07003F);
        String v0_2 = ((DropdownItem)v10).getSublabel();
        v4 = 8;
        if(TextUtils.isEmpty(((CharSequence)v0_2))) {
            ((TextView)v12).setVisibility(v4);
        }
        else {
            if(((DropdownItem)v10).isLabelAndSublabelOnSameLine()) {
                v1_1 = new LinearLayout$LayoutParams(v5, v5);
                ApiCompatibilityUtils.setMarginStart(((ViewGroup$MarginLayoutParams)v1_1), this.mLabelHorizontalMargin);
                ApiCompatibilityUtils.setMarginEnd(((ViewGroup$MarginLayoutParams)v1_1), this.mLabelHorizontalMargin);
                ((TextView)v12).setLayoutParams(((ViewGroup$LayoutParams)v1_1));
            }
            else {
                ((TextView)v12).setLayoutParams(((ViewGroup$LayoutParams)v1_1));
            }

            ((TextView)v12).setText(((CharSequence)v0_2));
            ((TextView)v12).setTextSize(0, this.mContext.getResources().getDimension(((DropdownItem)v10).getSublabelFontSizeResId()));
            ((TextView)v12).setVisibility(0);
        }

        v0_1 = arg11.findViewById(0x7F07003B);
        v12 = arg11.findViewById(0x7F07003B);
        if(((DropdownItem)v10).isIconAtStart()) {
            ((ImageView)v12).setVisibility(v4);
        }
        else {
            ((ImageView)v0_1).setVisibility(v4);
        }

        if(((DropdownItem)v10).isIconAtStart()) {
            v12 = v0_1;
        }

        if(((DropdownItem)v10).getIconId() == 0) {
            ((ImageView)v12).setVisibility(v4);
        }
        else {
            int v0_3 = ((DropdownItem)v10).getIconSizeResId();
            if(v0_3 == 0) {
            }
            else {
                v5 = this.mContext.getResources().getDimensionPixelSize(v0_3);
            }

            ViewGroup$LayoutParams v0_4 = ((ImageView)v12).getLayoutParams();
            ((ViewGroup$MarginLayoutParams)v0_4).width = v5;
            ((ViewGroup$MarginLayoutParams)v0_4).height = v5;
            v1 = this.mHasUniformHorizontalMargin ? this.mLabelHorizontalMargin : this.mContext.getResources().getDimensionPixelSize(((DropdownItem)v10).getIconMarginResId());
            ApiCompatibilityUtils.setMarginStart(((ViewGroup$MarginLayoutParams)v0_4), v1);
            ApiCompatibilityUtils.setMarginEnd(((ViewGroup$MarginLayoutParams)v0_4), v1);
            ((ImageView)v12).setLayoutParams(v0_4);
            ((ImageView)v12).setImageResource(((DropdownItem)v10).getIconId());
            ((ImageView)v12).setVisibility(0);
        }

        return arg11;
    }

    public boolean isEnabled(int arg3) {
        boolean v0 = false;
        if(arg3 >= 0) {
            if(arg3 >= this.getCount()) {
            }
            else {
                Object v3 = this.getItem(arg3);
                if((((DropdownItem)v3).isEnabled()) && !((DropdownItem)v3).isGroupHeader()) {
                    v0 = true;
                }

                return v0;
            }
        }

        return 0;
    }
}

