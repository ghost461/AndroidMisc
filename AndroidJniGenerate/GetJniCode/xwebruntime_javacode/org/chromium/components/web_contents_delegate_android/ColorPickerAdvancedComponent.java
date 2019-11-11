package org.chromium.components.web_contents_delegate_android;

import android.graphics.drawable.GradientDrawable$Orientation;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import org.chromium.base.ApiCompatibilityUtils;

public class ColorPickerAdvancedComponent {
    private int[] mGradientColors;
    private GradientDrawable mGradientDrawable;
    private final View mGradientView;
    private final SeekBar mSeekBar;
    private final TextView mText;

    ColorPickerAdvancedComponent(View arg3, int arg4, int arg5, SeekBar$OnSeekBarChangeListener arg6) {
        super();
        this.mGradientView = arg3.findViewById(0x7F070048);
        this.mText = arg3.findViewById(0x7F0700AF);
        this.mText.setText(arg4);
        this.mGradientDrawable = new GradientDrawable(GradientDrawable$Orientation.LEFT_RIGHT, null);
        this.mSeekBar = arg3.findViewById(0x7F070091);
        this.mSeekBar.setOnSeekBarChangeListener(arg6);
        this.mSeekBar.setMax(arg5);
        this.mSeekBar.setThumbOffset(ApiCompatibilityUtils.getDrawable(arg3.getContext().getResources(), 0x7F06005A).getIntrinsicWidth() / 2);
    }

    public float getValue() {
        return ((float)this.mSeekBar.getProgress());
    }

    public void setGradientColors(int[] arg2) {
        this.mGradientColors = arg2.clone();
        this.mGradientDrawable.setColors(this.mGradientColors);
        this.mGradientView.setBackground(this.mGradientDrawable);
    }

    public void setValue(float arg2) {
        this.mSeekBar.setProgress(((int)arg2));
    }
}

