package org.chromium.content.browser.picker;

import android.annotation.SuppressLint;
import android.content.Context;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

@SuppressLint(value={"DefaultLocale"}) public class MonthPicker extends TwoFieldDatePicker {
    private static final int MONTHS_NUMBER = 12;
    private final String[] mShortMonths;

    public MonthPicker(Context arg4, double arg5, double arg7) {
        super(arg4, arg5, arg7);
        this.getPositionInYearSpinner().setContentDescription(this.getResources().getString(0x7F0C001F));
        this.mShortMonths = DateFormatSymbols.getInstance(Locale.getDefault()).getShortMonths();
        if(this.usingNumericMonths()) {
            int v6;
            for(v6 = 0; v6 < this.mShortMonths.length; v6 = v1) {
                String[] v7 = this.mShortMonths;
                Object[] v0 = new Object[1];
                int v1 = v6 + 1;
                v0[0] = Integer.valueOf(v1);
                v7[v6] = String.format("%d", v0);
            }
        }

        Calendar v4 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        this.init(v4.get(1), v4.get(2), null);
    }

    public static Calendar createDateFromValue(double arg6) {
        int v2 = ((int)Math.min(arg6 / 12 + 1970, 2147483647));
        int v6 = ((int)(arg6 % 12));
        Calendar v7 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        v7.clear();
        v7.set(v2, v6, 1);
        return v7;
    }

    protected Calendar getDateForValue(double arg1) {
        return MonthPicker.createDateFromValue(arg1);
    }

    protected int getMaxPositionInYear(int arg3) {
        if(arg3 == this.getMaxDate().get(1)) {
            return this.getMaxDate().get(2);
        }

        return 11;
    }

    protected int getMaxYear() {
        return this.getMaxDate().get(1);
    }

    protected int getMinPositionInYear(int arg3) {
        if(arg3 == this.getMinDate().get(1)) {
            return this.getMinDate().get(2);
        }

        return 0;
    }

    protected int getMinYear() {
        return this.getMinDate().get(1);
    }

    public int getMonth() {
        return this.getCurrentDate().get(2);
    }

    public int getPositionInYear() {
        return this.getMonth();
    }

    protected void setCurrentDate(int arg3, int arg4) {
        Calendar v0 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        v0.set(arg3, arg4, 1);
        if(v0.before(this.getMinDate())) {
            this.setCurrentDate(this.getMinDate());
        }
        else if(v0.after(this.getMaxDate())) {
            this.setCurrentDate(this.getMaxDate());
        }
        else {
            this.setCurrentDate(v0);
        }
    }

    protected void updateSpinners() {
        super.updateSpinners();
        this.getPositionInYearSpinner().setDisplayedValues(Arrays.copyOfRange(this.mShortMonths, this.getPositionInYearSpinner().getMinValue(), this.getPositionInYearSpinner().getMaxValue() + 1));
    }

    protected boolean usingNumericMonths() {
        return Character.isDigit(this.mShortMonths[0].charAt(0));
    }
}

