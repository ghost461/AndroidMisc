package org.chromium.content.browser.picker;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build$VERSION;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker$OnValueChangeListener;
import android.widget.NumberPicker;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public abstract class TwoFieldDatePicker extends FrameLayout {
    public interface OnMonthOrWeekChangedListener {
        void onMonthOrWeekChanged(TwoFieldDatePicker arg1, int arg2, int arg3);
    }

    private Calendar mCurrentDate;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private OnMonthOrWeekChangedListener mMonthOrWeekChangedListener;
    private final NumberPicker mPositionInYearSpinner;
    private final NumberPicker mYearSpinner;

    public TwoFieldDatePicker(Context arg3, double arg4, double arg6) {
        super(arg3, null, 0x101035C);
        arg3.getSystemService("layout_inflater").inflate(0x7F09003D, ((ViewGroup)this), true);
        org.chromium.content.browser.picker.TwoFieldDatePicker$1 v3 = new NumberPicker$OnValueChangeListener() {
            public void onValueChange(NumberPicker arg4, int arg5, int arg6) {
                int v4;
                int v0 = TwoFieldDatePicker.this.getYear();
                int v1 = TwoFieldDatePicker.this.getPositionInYear();
                if(arg4 == TwoFieldDatePicker.access$000(TwoFieldDatePicker.this)) {
                    if(arg5 == arg4.getMaxValue() && arg6 == arg4.getMinValue()) {
                        arg6 = v0 + 1;
                        v4 = TwoFieldDatePicker.this.getMinPositionInYear(arg6);
                        goto label_30;
                    }

                    if(arg5 == arg4.getMinValue() && arg6 == arg4.getMaxValue()) {
                        arg6 = v0 - 1;
                        v4 = TwoFieldDatePicker.this.getMaxPositionInYear(arg6);
                        goto label_30;
                    }

                    v4 = arg6;
                    arg6 = v0;
                }
                else {
                    if(arg4 != TwoFieldDatePicker.access$100(TwoFieldDatePicker.this)) {
                        goto label_37;
                    }

                    v4 = v1;
                }

            label_30:
                TwoFieldDatePicker.this.setCurrentDate(arg6, v4);
                TwoFieldDatePicker.this.updateSpinners();
                TwoFieldDatePicker.this.notifyDateChanged();
                return;
            label_37:
                throw new IllegalArgumentException();
            }
        };
        this.mCurrentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        if(arg4 >= arg6) {
            this.mMinDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.mMinDate.set(0, 0, 1);
            this.mMaxDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.mMaxDate.set(0x270F, 0, 1);
        }
        else {
            this.mMinDate = this.getDateForValue(arg4);
            this.mMaxDate = this.getDateForValue(arg6);
        }

        this.mPositionInYearSpinner = this.findViewById(0x7F070078);
        this.mPositionInYearSpinner.setOnLongPressUpdateInterval(200);
        this.mPositionInYearSpinner.setOnValueChangedListener(((NumberPicker$OnValueChangeListener)v3));
        this.mYearSpinner = this.findViewById(0x7F0700C5);
        this.mYearSpinner.setOnLongPressUpdateInterval(100);
        this.mYearSpinner.setOnValueChangedListener(((NumberPicker$OnValueChangeListener)v3));
        this.reorderSpinners();
    }

    static NumberPicker access$000(TwoFieldDatePicker arg0) {
        return arg0.mPositionInYearSpinner;
    }

    static NumberPicker access$100(TwoFieldDatePicker arg0) {
        return arg0.mYearSpinner;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg1) {
        this.onPopulateAccessibilityEvent(arg1);
        return 1;
    }

    protected Calendar getCurrentDate() {
        return this.mCurrentDate;
    }

    protected abstract Calendar getDateForValue(double arg1);

    protected Calendar getMaxDate() {
        return this.mMaxDate;
    }

    protected abstract int getMaxPositionInYear(int arg1);

    protected abstract int getMaxYear();

    protected Calendar getMinDate() {
        return this.mMinDate;
    }

    protected abstract int getMinPositionInYear(int arg1);

    protected abstract int getMinYear();

    public abstract int getPositionInYear();

    protected NumberPicker getPositionInYearSpinner() {
        return this.mPositionInYearSpinner;
    }

    public int getYear() {
        return this.mCurrentDate.get(1);
    }

    protected NumberPicker getYearSpinner() {
        return this.mYearSpinner;
    }

    public void init(int arg1, int arg2, OnMonthOrWeekChangedListener arg3) {
        this.setCurrentDate(arg1, arg2);
        this.updateSpinners();
        this.mMonthOrWeekChangedListener = arg3;
    }

    public boolean isNewDate(int arg2, int arg3) {
        boolean v2 = this.getYear() != arg2 || this.getPositionInYear() != arg3 ? true : false;
        return v2;
    }

    protected void notifyDateChanged() {
        this.sendAccessibilityEvent(4);
        if(this.mMonthOrWeekChangedListener != null) {
            this.mMonthOrWeekChangedListener.onMonthOrWeekChanged(this, this.getYear(), this.getPositionInYear());
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent arg5) {
        super.onPopulateAccessibilityEvent(arg5);
        arg5.getText().add(DateUtils.formatDateTime(this.getContext(), this.mCurrentDate.getTimeInMillis(), 20));
    }

    @TargetApi(value=18) private void reorderSpinners() {
        int v7;
        int v6;
        View v0 = this.findViewById(0x7F070072);
        ((LinearLayout)v0).removeView(this.mPositionInYearSpinner);
        ((LinearLayout)v0).removeView(this.mYearSpinner);
        int v2 = 0x79;
        int v3 = 77;
        int v4 = 0;
        if(Build$VERSION.SDK_INT >= 18) {
            String v1 = DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd");
            v6 = 0;
            v7 = 0;
            while(v4 < v1.length()) {
                int v8 = v1.charAt(v4);
                int v9 = 39;
                if(v8 == v9) {
                    v4 = v1.indexOf(v9, v4 + 1);
                    if(v4 == -1) {
                        StringBuilder v2_1 = new StringBuilder();
                        v2_1.append("Bad quoting in ");
                        v2_1.append(v1);
                        throw new IllegalArgumentException(v2_1.toString());
                    }
                }
                else {
                    if((v8 == v3 || v8 == 76) && v6 == 0) {
                        ((LinearLayout)v0).addView(this.mPositionInYearSpinner);
                        v6 = 1;
                        goto label_49;
                    }

                    if(v8 != v2) {
                        goto label_49;
                    }

                    if(v7 != 0) {
                        goto label_49;
                    }

                    ((LinearLayout)v0).addView(this.mYearSpinner);
                    v7 = 1;
                }

            label_49:
                ++v4;
            }
        }
        else {
            char[] v1_1 = DateFormat.getDateFormatOrder(this.getContext());
            v6 = 0;
            v7 = 0;
            while(v4 < v1_1.length) {
                if(v1_1[v4] == v3) {
                    ((LinearLayout)v0).addView(this.mPositionInYearSpinner);
                    v6 = 1;
                }
                else if(v1_1[v4] == v2) {
                    ((LinearLayout)v0).addView(this.mYearSpinner);
                    v7 = 1;
                }

                ++v4;
            }
        }

        if(v6 == 0) {
            ((LinearLayout)v0).addView(this.mPositionInYearSpinner);
        }

        if(v7 == 0) {
            ((LinearLayout)v0).addView(this.mYearSpinner);
        }
    }

    protected abstract void setCurrentDate(int arg1, int arg2);

    protected void setCurrentDate(Calendar arg1) {
        this.mCurrentDate = arg1;
    }

    public void updateDate(int arg2, int arg3) {
        if(!this.isNewDate(arg2, arg3)) {
            return;
        }

        this.setCurrentDate(arg2, arg3);
        this.updateSpinners();
        this.notifyDateChanged();
    }

    protected void updateSpinners() {
        this.mPositionInYearSpinner.setDisplayedValues(null);
        this.mPositionInYearSpinner.setMinValue(this.getMinPositionInYear(this.getYear()));
        this.mPositionInYearSpinner.setMaxValue(this.getMaxPositionInYear(this.getYear()));
        NumberPicker v0 = this.mPositionInYearSpinner;
        boolean v1 = (this.mCurrentDate.equals(this.mMinDate)) || (this.mCurrentDate.equals(this.mMaxDate)) ? false : true;
        v0.setWrapSelectorWheel(v1);
        this.mYearSpinner.setMinValue(this.getMinYear());
        this.mYearSpinner.setMaxValue(this.getMaxYear());
        this.mYearSpinner.setWrapSelectorWheel(false);
        this.mYearSpinner.setValue(this.getYear());
        this.mPositionInYearSpinner.setValue(this.getPositionInYear());
    }
}

