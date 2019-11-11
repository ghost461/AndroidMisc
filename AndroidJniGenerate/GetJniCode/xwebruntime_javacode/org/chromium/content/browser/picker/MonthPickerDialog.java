package org.chromium.content.browser.picker;

import android.content.Context;

public class MonthPickerDialog extends TwoFieldDatePickerDialog {
    public MonthPickerDialog(Context arg1, OnValueSetListener arg2, int arg3, int arg4, double arg5, double arg7) {
        super(arg1, arg2, arg3, arg4, arg5, arg7);
        this.setTitle(0x7F0C005F);
    }

    protected TwoFieldDatePicker createPicker(Context arg8, double arg9, double arg11) {
        return new MonthPicker(arg8, arg9, arg11);
    }

    public MonthPicker getMonthPicker() {
        return this.mPicker;
    }
}

