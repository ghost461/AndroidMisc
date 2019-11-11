package org.chromium.content.browser.picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.os.LocaleList;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.DatePicker$OnDateChangedListener;
import android.widget.DatePicker;
import android.widget.TimePicker$OnTimeChangedListener;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.IllegalFormatConversionException;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import org.chromium.base.VisibleForTesting;

public class DateTimePickerDialog extends AlertDialog implements DialogInterface$OnClickListener, DatePicker$OnDateChangedListener, TimePicker$OnTimeChangedListener {
    class org.chromium.content.browser.picker.DateTimePickerDialog$1 {
    }

    public interface OnDateTimeSetListener {
        void onDateTimeSet(DatePicker arg1, TimePicker arg2, int arg3, int arg4, int arg5, int arg6, int arg7);
    }

    class WorkaroundContextForSamsungLDateTimeBug extends ContextWrapper {
        class WrappedResources extends Resources {
            WrappedResources(AssetManager arg1, DisplayMetrics arg2, Configuration arg3) {
                super(arg1, arg2, arg3);
            }

            private Locale getLocale() {
                if(Build$VERSION.SDK_INT >= 24) {
                    LocaleList v0 = this.getConfiguration().getLocales();
                    if(v0.size() > 0) {
                        return v0.get(0);
                    }
                }

                return this.getConfiguration().locale;
            }

            @NonNull public String getString(int arg4, Object[] arg5) throws Resources$NotFoundException {
                try {
                    return super.getString(arg4, arg5);
                }
                catch(IllegalFormatConversionException v0) {
                    String v4 = super.getString(arg4);
                    char v0_1 = v0.getConversion();
                    StringBuilder v1 = new StringBuilder();
                    v1.append("%");
                    v1.append(v0_1);
                    v4 = v4.replaceAll(Pattern.quote(v1.toString()), "%s");
                    v1 = new StringBuilder();
                    v1.append("%1$");
                    v1.append(v0_1);
                    return String.format(this.getLocale(), v4.replaceAll(Pattern.quote(v1.toString()), "%s"), arg5);
                }
            }
        }

        @CheckForNull private Resources mWrappedResources;

        WorkaroundContextForSamsungLDateTimeBug(Context arg1, org.chromium.content.browser.picker.DateTimePickerDialog$1 arg2) {
            this(arg1);
        }

        private WorkaroundContextForSamsungLDateTimeBug(Context arg1) {
            super(arg1);
        }

        public Resources getResources() {
            if(this.mWrappedResources == null) {
                Resources v0 = super.getResources();
                this.mWrappedResources = new WrappedResources(v0.getAssets(), v0.getDisplayMetrics(), v0.getConfiguration()) {
                };
            }

            return this.mWrappedResources;
        }
    }

    private final OnDateTimeSetListener mCallBack;
    private final DatePicker mDatePicker;
    private final long mMaxTimeMillis;
    private final long mMinTimeMillis;
    private final TimePicker mTimePicker;

    public DateTimePickerDialog(Context arg12, OnDateTimeSetListener arg13, int arg14, int arg15, int arg16, int arg17, int arg18, boolean arg19, double arg20, double arg22) {
        super(arg12, 0);
        this.mMinTimeMillis = ((long)arg20);
        this.mMaxTimeMillis = ((long)arg22);
        this.mCallBack = arg13;
        this.setButton(-1, arg12.getText(0x7F0C0043), this);
        this.setButton(-2, arg12.getText(0x1040000), null);
        this.setIcon(0);
        this.setTitle(arg12.getText(0x7F0C0045));
        View v10 = DateTimePickerDialog.getDialogContext(arg12).getSystemService("layout_inflater").inflate(0x7F090020, null);
        this.setView(v10);
        this.mDatePicker = v10.findViewById(0x7F070031);
        DateDialogNormalizer.normalize(this.mDatePicker, this, arg14, arg15, arg16, this.mMinTimeMillis, this.mMaxTimeMillis);
        this.mTimePicker = v10.findViewById(0x7F0700B6);
        this.mTimePicker.setIs24HourView(Boolean.valueOf(arg19));
        DateTimePickerDialog.setHour(this.mTimePicker, arg17);
        DateTimePickerDialog.setMinute(this.mTimePicker, arg18);
        this.mTimePicker.setOnTimeChangedListener(this);
        this.onTimeChanged(this.mTimePicker, DateTimePickerDialog.getHour(this.mTimePicker), DateTimePickerDialog.getMinute(this.mTimePicker));
    }

    private static Context getDialogContext(Context arg2) {
        if(Build$VERSION.SDK_INT != 21) {
            if(Build$VERSION.SDK_INT == 22) {
            }
            else {
                return arg2;
            }
        }

        return new WorkaroundContextForSamsungLDateTimeBug(arg2, null);
    }

    private static int getHour(TimePicker arg0) {
        return arg0.getCurrentHour().intValue();
    }

    private static int getMinute(TimePicker arg0) {
        return arg0.getCurrentMinute().intValue();
    }

    public void onClick(DialogInterface arg1, int arg2) {
        this.tryNotifyDateTimeSet();
    }

    public void onDateChanged(DatePicker arg1, int arg2, int arg3, int arg4) {
        if(this.mTimePicker != null) {
            this.onTimeChanged(this.mTimePicker, DateTimePickerDialog.getHour(this.mTimePicker), DateTimePickerDialog.getMinute(this.mTimePicker));
        }
    }

    public void onTimeChanged(TimePicker arg9, int arg10, int arg11) {
        DateTimePickerDialog.onTimeChangedInternal(this.mDatePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth(), this.mTimePicker, this.mMinTimeMillis, this.mMaxTimeMillis);
    }

    @VisibleForTesting public static void onTimeChangedInternal(int arg8, int arg9, int arg10, TimePicker arg11, long arg12, long arg14) {
        GregorianCalendar v7 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        ((Calendar)v7).clear();
        v7.set(arg8, arg9, arg10, DateTimePickerDialog.getHour(arg11), DateTimePickerDialog.getMinute(arg11), 0);
        if(((Calendar)v7).getTimeInMillis() < arg12) {
            ((Calendar)v7).setTimeInMillis(arg12);
        }
        else if(((Calendar)v7).getTimeInMillis() > arg14) {
            ((Calendar)v7).setTimeInMillis(arg14);
        }

        DateTimePickerDialog.setHour(arg11, ((Calendar)v7).get(11));
        DateTimePickerDialog.setMinute(arg11, ((Calendar)v7).get(12));
    }

    private static void setHour(TimePicker arg0, int arg1) {
        arg0.setCurrentHour(Integer.valueOf(arg1));
    }

    private static void setMinute(TimePicker arg0, int arg1) {
        arg0.setCurrentMinute(Integer.valueOf(arg1));
    }

    private void tryNotifyDateTimeSet() {
        if(this.mCallBack != null) {
            this.mDatePicker.clearFocus();
            this.mTimePicker.clearFocus();
            this.mCallBack.onDateTimeSet(this.mDatePicker, this.mTimePicker, this.mDatePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth(), DateTimePickerDialog.getHour(this.mTimePicker), DateTimePickerDialog.getMinute(this.mTimePicker));
        }
    }

    public void updateDateTime(int arg2, int arg3, int arg4, int arg5, int arg6) {
        this.mDatePicker.updateDate(arg2, arg3, arg4);
        DateTimePickerDialog.setHour(this.mTimePicker, arg5);
        DateTimePickerDialog.setMinute(this.mTimePicker, arg6);
    }
}

