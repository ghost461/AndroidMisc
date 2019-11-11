package org.chromium.components.web_contents_delegate_android;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.widget.SeekBar;

public class ColorPickerAdvanced extends LinearLayout implements SeekBar$OnSeekBarChangeListener {
    private static final int HUE_COLOR_COUNT = 7;
    private static final int HUE_SEEK_BAR_MAX = 360;
    private static final int SATURATION_COLOR_COUNT = 2;
    private static final int SATURATION_SEEK_BAR_MAX = 100;
    private static final int VALUE_COLOR_COUNT = 2;
    private static final int VALUE_SEEK_BAR_MAX = 100;
    private int mCurrentColor;
    private final float[] mCurrentHsvValues;
    ColorPickerAdvancedComponent mHueDetails;
    private OnColorChangedListener mOnColorChangedListener;
    ColorPickerAdvancedComponent mSaturationDetails;
    ColorPickerAdvancedComponent mValueDetails;

    public ColorPickerAdvanced(Context arg1) {
        super(arg1);
        this.mCurrentHsvValues = new float[3];
        this.init();
    }

    public ColorPickerAdvanced(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.mCurrentHsvValues = new float[3];
        this.init();
    }

    public ColorPickerAdvanced(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.mCurrentHsvValues = new float[3];
        this.init();
    }

    public ColorPickerAdvancedComponent createAndAddNewGradient(int arg4, int arg5, SeekBar$OnSeekBarChangeListener arg6) {
        View v0 = this.getContext().getSystemService("layout_inflater").inflate(0x7F09001D, null);
        this.addView(v0);
        return new ColorPickerAdvancedComponent(v0, arg4, arg5, arg6);
    }

    public int getColor() {
        return this.mCurrentColor;
    }

    private void init() {
        this.setOrientation(1);
        this.mHueDetails = this.createAndAddNewGradient(0x7F0C003C, 360, ((SeekBar$OnSeekBarChangeListener)this));
        this.mSaturationDetails = this.createAndAddNewGradient(0x7F0C003D, 100, ((SeekBar$OnSeekBarChangeListener)this));
        this.mValueDetails = this.createAndAddNewGradient(0x7F0C003E, 100, ((SeekBar$OnSeekBarChangeListener)this));
        this.refreshGradientComponents();
    }

    private void notifyColorChanged() {
        if(this.mOnColorChangedListener != null) {
            this.mOnColorChangedListener.onColorChanged(this.getColor());
        }
    }

    public void onProgressChanged(SeekBar arg2, int arg3, boolean arg4) {
        if(arg4) {
            this.mCurrentHsvValues[0] = this.mHueDetails.getValue();
            this.mCurrentHsvValues[1] = this.mSaturationDetails.getValue() / 100f;
            this.mCurrentHsvValues[2] = this.mValueDetails.getValue() / 100f;
            this.mCurrentColor = Color.HSVToColor(this.mCurrentHsvValues);
            this.updateHueGradient();
            this.updateSaturationGradient();
            this.updateValueGradient();
            this.notifyColorChanged();
        }
    }

    public void onStartTrackingTouch(SeekBar arg1) {
    }

    public void onStopTrackingTouch(SeekBar arg1) {
    }

    private void refreshGradientComponents() {
        int v0 = Math.max(Math.min(Math.round(this.mCurrentHsvValues[1] * 100f), 100), 0);
        int v1 = Math.max(Math.min(Math.round(this.mCurrentHsvValues[2] * 100f), 100), 0);
        this.mHueDetails.setValue(this.mCurrentHsvValues[0]);
        this.mSaturationDetails.setValue(((float)v0));
        this.mValueDetails.setValue(((float)v1));
        this.updateHueGradient();
        this.updateSaturationGradient();
        this.updateValueGradient();
    }

    public void setColor(int arg2) {
        this.mCurrentColor = arg2;
        Color.colorToHSV(this.mCurrentColor, this.mCurrentHsvValues);
        this.refreshGradientComponents();
    }

    public void setListener(OnColorChangedListener arg1) {
        this.mOnColorChangedListener = arg1;
    }

    private void updateHueGradient() {
        float[] v0 = new float[3];
        v0[1] = this.mCurrentHsvValues[1];
        v0[2] = this.mCurrentHsvValues[2];
        int v1 = 7;
        int[] v2 = new int[v1];
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            v0[0] = (((float)v4)) * 60f;
            v2[v4] = Color.HSVToColor(v0);
        }

        this.mHueDetails.setGradientColors(v2);
    }

    private void updateSaturationGradient() {
        float[] v0 = new float[]{this.mCurrentHsvValues[0], 0f, this.mCurrentHsvValues[2]};
        int[] v3 = new int[2];
        v3[0] = Color.HSVToColor(v0);
        v0[1] = 1f;
        v3[1] = Color.HSVToColor(v0);
        this.mSaturationDetails.setGradientColors(v3);
    }

    private void updateValueGradient() {
        float[] v0 = new float[]{this.mCurrentHsvValues[0], this.mCurrentHsvValues[1], 0f};
        int[] v4 = new int[2];
        v4[0] = Color.HSVToColor(v0);
        v0[2] = 1f;
        v4[1] = Color.HSVToColor(v0);
        this.mValueDetails.setGradientColors(v4);
    }
}

