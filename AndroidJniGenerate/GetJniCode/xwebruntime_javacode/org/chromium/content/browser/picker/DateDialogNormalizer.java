package org.chromium.content.browser.picker;

import android.os.Build$VERSION;
import android.widget.DatePicker$OnDateChangedListener;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateDialogNormalizer {
    class DateAndMillis {
        public final int day;
        public final long millisForPicker;
        public final int month;
        public final int year;

        DateAndMillis(long arg1, int arg3, int arg4, int arg5) {
            super();
            this.millisForPicker = arg1;
            this.year = arg3;
            this.month = arg4;
            this.day = arg5;
        }

        static DateAndMillis create(int arg7, int arg8, int arg9) {
            Calendar v0 = Calendar.getInstance(TimeZone.getDefault());
            v0.clear();
            v0.set(arg7, arg8, arg9);
            return new DateAndMillis(v0.getTimeInMillis(), arg7, arg8, arg9);
        }

        static DateAndMillis create(long arg4) {
            GregorianCalendar v0 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            v0.setGregorianChange(new Date(-9223372036854775808L));
            v0.setTimeInMillis(arg4);
            return DateAndMillis.create(v0.get(1), v0.get(2), v0.get(5));
        }
    }

    public DateDialogNormalizer() {
        super();
    }

    public static void normalize(DatePicker arg7, DatePicker$OnDateChangedListener arg8, int arg9, int arg10, int arg11, long arg12, long arg14) {
        DateAndMillis v9 = DateAndMillis.create(arg9, arg10, arg11);
        DateAndMillis v10 = DateAndMillis.create(arg12);
        DateAndMillis v11 = DateAndMillis.create(arg14);
        if(v11.millisForPicker < v10.millisForPicker) {
            v11 = v10;
        }

        if(v9.millisForPicker < v10.millisForPicker) {
            v9 = v10;
        }
        else if(v9.millisForPicker > v11.millisForPicker) {
            v9 = v11;
        }

        DateDialogNormalizer.setLimits(arg7, v9.millisForPicker, v10.millisForPicker, v11.millisForPicker);
        arg7.init(v9.year, v9.month, v9.day, arg8);
    }

    private static void setLimits(DatePicker arg4, long arg5, long arg7, long arg9) {
        if(Build$VERSION.SDK_INT == 21 || Build$VERSION.SDK_INT == 22) {
            arg7 = Math.max(arg7, arg5 - 157680000000000L);
            arg9 = Math.min(arg9, arg5 + 157680000000000L);
        }

        if(arg7 > arg4.getMaxDate()) {
            arg4.setMaxDate(arg9);
            arg4.setMinDate(arg7);
        }
        else {
            arg4.setMinDate(arg7);
            arg4.setMaxDate(arg9);
        }
    }
}

