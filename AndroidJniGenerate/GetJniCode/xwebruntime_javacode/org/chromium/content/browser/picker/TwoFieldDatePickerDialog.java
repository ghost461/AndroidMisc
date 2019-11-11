package org.chromium.content.browser.picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public abstract class TwoFieldDatePickerDialog extends AlertDialog implements DialogInterface$OnClickListener, OnMonthOrWeekChangedListener {
    public interface OnValueSetListener {
        void onValueSet(int arg1, int arg2);
    }

    private static final String POSITION_IN_YEAR = "position_in_year";
    private static final String YEAR = "year";
    protected final OnValueSetListener mCallBack;
    protected final TwoFieldDatePicker mPicker;

    public TwoFieldDatePickerDialog(Context arg7, int arg8, OnValueSetListener arg9, int arg10, int arg11, double arg12, double arg14) {
        super(arg7, arg8);
        this.mCallBack = arg9;
        this.setButton(-1, arg7.getText(0x7F0C0043), ((DialogInterface$OnClickListener)this));
        this.setButton(-2, arg7.getText(0x1040000), null);
        this.setIcon(0);
        this.mPicker = this.createPicker(arg7, arg12, arg14);
        this.setView(this.mPicker);
        this.mPicker.init(arg10, arg11, ((OnMonthOrWeekChangedListener)this));
    }

    public TwoFieldDatePickerDialog(Context arg11, OnValueSetListener arg12, int arg13, int arg14, double arg15, double arg17) {
        this(arg11, 0, arg12, arg13, arg14, arg15, arg17);
    }

    protected TwoFieldDatePicker createPicker(Context arg1, double arg2, double arg4) {
        return null;
    }

    public void onClick(DialogInterface arg1, int arg2) {
        this.tryNotifyDateSet();
    }

    public void onMonthOrWeekChanged(TwoFieldDatePicker arg2, int arg3, int arg4) {
        this.mPicker.init(arg3, arg4, null);
    }

    protected void tryNotifyDateSet() {
        if(this.mCallBack != null) {
            this.mPicker.clearFocus();
            this.mCallBack.onValueSet(this.mPicker.getYear(), this.mPicker.getPositionInYear());
        }
    }

    public void updateDate(int arg2, int arg3) {
        this.mPicker.updateDate(arg2, arg3);
    }
}

