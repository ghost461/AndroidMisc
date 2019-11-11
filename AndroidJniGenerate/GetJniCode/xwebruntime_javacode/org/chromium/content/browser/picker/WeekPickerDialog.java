package org.chromium.content.browser.picker;

import android.content.Context;

public class WeekPickerDialog extends TwoFieldDatePickerDialog {
    public WeekPickerDialog(Context arg11, OnValueSetListener arg12, int arg13, int arg14, double arg15, double arg17) {
        this(arg11, 0, arg12, arg13, arg14, arg15, arg17);
    }

    public WeekPickerDialog(Context arg1, int arg2, OnValueSetListener arg3, int arg4, int arg5, double arg6, double arg8) {
        super(arg1, arg2, arg3, arg4, arg5, arg6, arg8);
        this.setTitle(0x7F0C007D);
    }

    protected TwoFieldDatePicker createPicker(Context arg8, double arg9, double arg11) {
        return new WeekPicker(arg8, arg9, arg11);
    }

    public WeekPicker getWeekPicker() {
        return this.mPicker;
    }
}

