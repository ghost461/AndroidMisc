package org.chromium.components.web_contents_delegate_android;

import android.content.Context;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.widget.AbsListView$LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import org.chromium.base.ApiCompatibilityUtils;

public class ColorSuggestionListAdapter extends BaseAdapter implements View$OnClickListener {
    public interface OnColorSuggestionClickListener {
        void onColorSuggestionClick(ColorSuggestion arg1);
    }

    private static final int COLORS_PER_ROW = 4;
    private Context mContext;
    private OnColorSuggestionClickListener mListener;
    private ColorSuggestion[] mSuggestions;

    ColorSuggestionListAdapter(Context arg1, ColorSuggestion[] arg2) {
        super();
        this.mContext = arg1;
        this.mSuggestions = arg2;
    }

    public int getCount() {
        return (this.mSuggestions.length + 3) / 4;
    }

    public Object getItem(int arg1) {
        return null;
    }

    public long getItemId(int arg3) {
        return ((long)arg3);
    }

    public View getView(int arg8, View arg9, ViewGroup arg10) {
        LinearLayout v9;
        int v0 = 4;
        int v1 = 0;
        if((arg9 instanceof LinearLayout)) {
        }
        else {
            v9 = new LinearLayout(this.mContext);
            int v3 = -1;
            v9.setLayoutParams(new AbsListView$LayoutParams(v3, -2));
            v9.setOrientation(0);
            v9.setBackgroundColor(v3);
            int v10 = this.mContext.getResources().getDimensionPixelOffset(0x7F05004A);
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                View v4 = new View(this.mContext);
                LinearLayout$LayoutParams v5 = new LinearLayout$LayoutParams(0, v10, 1f);
                ApiCompatibilityUtils.setMarginStart(((ViewGroup$MarginLayoutParams)v5), v3);
                if(v2 == 3) {
                    ApiCompatibilityUtils.setMarginEnd(((ViewGroup$MarginLayoutParams)v5), v3);
                }

                v4.setLayoutParams(((ViewGroup$LayoutParams)v5));
                v4.setBackgroundResource(0x7F060059);
                v9.addView(v4);
            }
        }

        while(v1 < v0) {
            this.setUpColorButton(v9.getChildAt(v1), arg8 * 4 + v1);
            ++v1;
        }

        return ((View)v9);
    }

    public void onClick(View arg2) {
        if(this.mListener == null) {
            return;
        }

        Object v2 = arg2.getTag();
        if(v2 == null) {
            return;
        }

        this.mListener.onColorSuggestionClick(((ColorSuggestion)v2));
    }

    public void setOnColorSuggestionClickListener(OnColorSuggestionClickListener arg1) {
        this.mListener = arg1;
    }

    private void setUpColorButton(View arg5, int arg6) {
        if(arg6 >= this.mSuggestions.length) {
            arg5.setTag(null);
            arg5.setContentDescription(null);
            arg5.setVisibility(4);
            return;
        }

        arg5.setTag(this.mSuggestions[arg6]);
        arg5.setVisibility(0);
        ColorSuggestion v6 = this.mSuggestions[arg6];
        arg5.getBackground().findDrawableByLayerId(0x7F07002B).setColor(v6.mColor);
        String v1 = v6.mLabel;
        if(TextUtils.isEmpty(((CharSequence)v1))) {
            v1 = String.format("#%06X", Integer.valueOf(v6.mColor & 0xFFFFFF));
        }

        arg5.setContentDescription(((CharSequence)v1));
        arg5.setOnClickListener(((View$OnClickListener)this));
    }
}

