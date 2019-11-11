package org.chromium.content.browser.picker;

import android.content.Context;
import java.util.Calendar;
import java.util.TimeZone;

public class WeekPicker extends TwoFieldDatePicker {
    public WeekPicker(Context arg1, double arg2, double arg4) {
        super(arg1, arg2, arg4);
        this.getPositionInYearSpinner().setContentDescription(this.getResources().getString(0x7F0C0020));
        Calendar v1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        v1.setFirstDayOfWeek(2);
        v1.setMinimalDaysInFirstWeek(4);
        v1.setTimeInMillis(System.currentTimeMillis());
        this.init(WeekPicker.getISOWeekYearForDate(v1), WeekPicker.getWeekForDate(v1), null);
    }

    public static Calendar createDateFromValue(double arg2) {
        Calendar v0 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        v0.clear();
        v0.setFirstDayOfWeek(2);
        v0.setMinimalDaysInFirstWeek(4);
        v0.setTimeInMillis(((long)arg2));
        return v0;
    }

    public static Calendar createDateFromWeek(int arg3, int arg4) {
        Calendar v0 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        v0.clear();
        v0.setFirstDayOfWeek(2);
        v0.setMinimalDaysInFirstWeek(4);
        v0.set(7, 2);
        v0.set(1, arg3);
        v0.set(3, arg4);
        return v0;
    }

    protected Calendar getDateForValue(double arg1) {
        return WeekPicker.createDateFromValue(arg1);
    }

    public static int getISOWeekYearForDate(Calendar arg4) {
        int v1 = arg4.get(1);
        int v2 = arg4.get(2);
        int v4 = arg4.get(3);
        if(v2 == 0 && v4 > 51) {
            --v1;
        }
        else if(v2 == 11 && v4 == 1) {
            ++v1;
        }

        return v1;
    }

    protected int getMaxPositionInYear(int arg2) {
        if(arg2 == WeekPicker.getISOWeekYearForDate(this.getMaxDate())) {
            return WeekPicker.getWeekForDate(this.getMaxDate());
        }

        return this.getNumberOfWeeks(arg2);
    }

    protected int getMaxYear() {
        return WeekPicker.getISOWeekYearForDate(this.getMaxDate());
    }

    protected int getMinPositionInYear(int arg2) {
        if(arg2 == WeekPicker.getISOWeekYearForDate(this.getMinDate())) {
            return WeekPicker.getWeekForDate(this.getMinDate());
        }

        return 1;
    }

    protected int getMinYear() {
        return WeekPicker.getISOWeekYearForDate(this.getMinDate());
    }

    private int getNumberOfWeeks(int arg2) {
        return WeekPicker.createDateFromWeek(arg2, 20).getActualMaximum(3);
    }

    public int getPositionInYear() {
        return this.getWeek();
    }

    public int getWeek() {
        return WeekPicker.getWeekForDate(this.getCurrentDate());
    }

    public static int getWeekForDate(Calendar arg1) {
        return arg1.get(3);
    }

    public int getYear() {
        return WeekPicker.getISOWeekYearForDate(this.getCurrentDate());
    }

    protected void setCurrentDate(int arg1, int arg2) {
        Calendar v1 = WeekPicker.createDateFromWeek(arg1, arg2);
        if(v1.before(this.getMinDate())) {
            this.setCurrentDate(this.getMinDate());
        }
        else if(v1.after(this.getMaxDate())) {
            this.setCurrentDate(this.getMaxDate());
        }
        else {
            this.setCurrentDate(v1);
        }
    }
}

