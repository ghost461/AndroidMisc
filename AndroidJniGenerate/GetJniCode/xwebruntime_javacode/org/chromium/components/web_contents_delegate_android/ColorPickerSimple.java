package org.chromium.components.web_contents_delegate_android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ColorPickerSimple extends ListView implements OnColorSuggestionClickListener {
    private static final int[] DEFAULT_COLORS;
    private static final int[] DEFAULT_COLOR_LABEL_IDS;
    private OnColorChangedListener mOnColorChangedListener;

    static {
        ColorPickerSimple.DEFAULT_COLORS = new int[]{0xFFFF0000, 0xFF00FFFF, 0xFF0000FF, 0xFF00FF00, 0xFFFF00FF, 0xFFFFFF00, 0xFF000000, -1};
        ColorPickerSimple.DEFAULT_COLOR_LABEL_IDS = new int[]{0x7F0C0037, 0x7F0C0033, 0x7F0C0031, 0x7F0C0034, 0x7F0C0035, 0x7F0C003A, 0x7F0C0030, 0x7F0C0039};
    }

    public ColorPickerSimple(Context arg1) {
        super(arg1);
    }

    public ColorPickerSimple(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    public ColorPickerSimple(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
    }

    public void init(ColorSuggestion[] arg5, OnColorChangedListener arg6) {
        this.mOnColorChangedListener = arg6;
        if(arg5 == null) {
            arg5 = new ColorSuggestion[ColorPickerSimple.DEFAULT_COLORS.length];
            int v6;
            for(v6 = 0; v6 < arg5.length; ++v6) {
                arg5[v6] = new ColorSuggestion(ColorPickerSimple.DEFAULT_COLORS[v6], this.getContext().getString(ColorPickerSimple.DEFAULT_COLOR_LABEL_IDS[v6]));
            }
        }

        ColorSuggestionListAdapter v6_1 = new ColorSuggestionListAdapter(this.getContext(), arg5);
        v6_1.setOnColorSuggestionClickListener(((OnColorSuggestionClickListener)this));
        this.setAdapter(((ListAdapter)v6_1));
    }

    public void onColorSuggestionClick(ColorSuggestion arg2) {
        this.mOnColorChangedListener.onColorChanged(arg2.mColor);
    }
}

