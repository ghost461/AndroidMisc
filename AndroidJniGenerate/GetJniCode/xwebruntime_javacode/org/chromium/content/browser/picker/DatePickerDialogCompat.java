package org.chromium.content.browser.picker;

import android.app.DatePickerDialog$OnDateSetListener;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build$VERSION;
import android.widget.DatePicker;

class DatePickerDialogCompat extends DatePickerDialog {
    private final DatePickerDialog$OnDateSetListener mCallBack;

    public DatePickerDialogCompat(Context arg1, DatePickerDialog$OnDateSetListener arg2, int arg3, int arg4, int arg5) {
        super(arg1, arg2, arg3, arg4, arg5);
        this.mCallBack = arg2;
    }

    public void onClick(DialogInterface arg4, int arg5) {
        if(arg5 == -1 && this.mCallBack != null) {
            DatePicker v4 = this.getDatePicker();
            v4.clearFocus();
            this.mCallBack.onDateSet(v4, v4.getYear(), v4.getMonth(), v4.getDayOfMonth());
        }
    }

    public void setTitle(CharSequence arg3) {
        String v3;
        if(Build$VERSION.SDK_INT >= 21) {
            v3 = "";
        }

        super.setTitle(((CharSequence)v3));
    }
}

