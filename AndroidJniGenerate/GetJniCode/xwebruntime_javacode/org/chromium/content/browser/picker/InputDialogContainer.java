package org.chromium.content.browser.picker;

import android.app.AlertDialog$Builder;
import android.app.AlertDialog;
import android.app.DatePickerDialog$OnDateSetListener;
import android.app.TimePickerDialog$OnTimeSetListener;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class InputDialogContainer {
    class DateListener implements DatePickerDialog$OnDateSetListener {
        private final int mDialogType;

        DateListener(InputDialogContainer arg1, int arg2) {
            InputDialogContainer.this = arg1;
            super();
            this.mDialogType = arg2;
        }

        public void onDateSet(DatePicker arg11, int arg12, int arg13, int arg14) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, arg12, arg13, arg14, 0, 0, 0, 0, 0);
        }
    }

    class DateTimeListener implements OnDateTimeSetListener {
        private final int mDialogType;
        private final boolean mLocal;

        public DateTimeListener(InputDialogContainer arg1, int arg2) {
            InputDialogContainer.this = arg1;
            super();
            boolean v1 = arg2 == 10 ? true : false;
            this.mLocal = v1;
            this.mDialogType = arg2;
        }

        public void onDateTimeSet(DatePicker arg12, TimePicker arg13, int arg14, int arg15, int arg16, int arg17, int arg18) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, arg14, arg15, arg16, arg17, arg18, 0, 0, 0);
        }
    }

    class FullTimeListener implements OnMultiFieldTimeSetListener {
        private final int mDialogType;

        FullTimeListener(InputDialogContainer arg1, int arg2) {
            InputDialogContainer.this = arg1;
            super();
            this.mDialogType = arg2;
        }

        public void onTimeSet(int arg11, int arg12, int arg13, int arg14) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, 0, 0, 0, arg11, arg12, arg13, arg14, 0);
        }
    }

    public interface InputActionDelegate {
        void cancelDateTimeDialog();

        void replaceDateTime(double arg1);
    }

    class MonthOrWeekListener implements OnValueSetListener {
        private final int mDialogType;

        MonthOrWeekListener(InputDialogContainer arg1, int arg2) {
            InputDialogContainer.this = arg1;
            super();
            this.mDialogType = arg2;
        }

        public void onValueSet(int arg24, int arg25) {
            MonthOrWeekListener v0 = this;
            if(v0.mDialogType == 11) {
                v0.this$0.setFieldDateTimeValue(v0.mDialogType, arg24, arg25, 0, 0, 0, 0, 0, 0);
            }
            else {
                v0.this$0.setFieldDateTimeValue(v0.mDialogType, arg24, 0, 0, 0, 0, 0, 0, arg25);
            }
        }
    }

    class TimeListener implements TimePickerDialog$OnTimeSetListener {
        private final int mDialogType;

        TimeListener(InputDialogContainer arg1, int arg2) {
            InputDialogContainer.this = arg1;
            super();
            this.mDialogType = arg2;
        }

        public void onTimeSet(TimePicker arg11, int arg12, int arg13) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, 0, 0, 0, arg12, arg13, 0, 0, 0);
        }
    }

    private final Context mContext;
    private AlertDialog mDialog;
    private boolean mDialogAlreadyDismissed;
    private final InputActionDelegate mInputActionDelegate;

    public InputDialogContainer(Context arg1, InputActionDelegate arg2) {
        super();
        this.mContext = arg1;
        this.mInputActionDelegate = arg2;
    }

    static void access$000(InputDialogContainer arg0) {
        arg0.dismissDialog();
    }

    static InputActionDelegate access$100(InputDialogContainer arg0) {
        return arg0.mInputActionDelegate;
    }

    static boolean access$200(InputDialogContainer arg0) {
        return arg0.mDialogAlreadyDismissed;
    }

    static boolean access$202(InputDialogContainer arg0, boolean arg1) {
        arg0.mDialogAlreadyDismissed = arg1;
        return arg1;
    }

    static AlertDialog access$300(InputDialogContainer arg0) {
        return arg0.mDialog;
    }

    private void dismissDialog() {
        if(this.isDialogShowing()) {
            this.mDialog.dismiss();
        }
    }

    public static boolean isDialogInputType(int arg1) {
        boolean v1 = arg1 == 8 || arg1 == 12 || arg1 == 9 || arg1 == 10 || arg1 == 11 || arg1 == 13 ? true : false;
        return v1;
    }

    private boolean isDialogShowing() {
        boolean v0 = this.mDialog == null || !this.mDialog.isShowing() ? false : true;
        return v0;
    }

    protected void setFieldDateTimeValue(int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, int arg13) {
        if(this.mDialogAlreadyDismissed) {
            return;
        }

        this.mDialogAlreadyDismissed = true;
        int v1 = 11;
        int v2 = 12;
        if(arg5 == v1) {
            this.mInputActionDelegate.replaceDateTime(((double)((arg6 - 1970) * 12 + arg7)));
        }
        else {
            int v3 = 13;
            if(arg5 == v3) {
                this.mInputActionDelegate.replaceDateTime(((double)WeekPicker.createDateFromWeek(arg6, arg13).getTimeInMillis()));
            }
            else if(arg5 == v2) {
                this.mInputActionDelegate.replaceDateTime(((double)(TimeUnit.HOURS.toMillis(((long)arg9)) + TimeUnit.MINUTES.toMillis(((long)arg10)) + TimeUnit.SECONDS.toMillis(((long)arg11)) + (((long)arg12)))));
            }
            else {
                Calendar v5 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                v5.clear();
                v5.set(1, arg6);
                v5.set(2, arg7);
                v5.set(5, arg8);
                v5.set(v1, arg9);
                v5.set(v2, arg10);
                v5.set(v3, arg11);
                v5.set(14, arg12);
                this.mInputActionDelegate.replaceDateTime(((double)v5.getTimeInMillis()));
            }
        }
    }

    public void showDialog(int arg1, double arg2, double arg4, double arg6, double arg8, DateTimeSuggestion[] arg10) {
        this.dismissDialog();
        if(arg10 == null) {
            this.showPickerDialog(arg1, arg2, arg4, arg6, arg8);
        }
        else {
            this.showSuggestionDialog(arg1, arg2, arg4, arg6, arg8, arg10);
        }
    }

    protected void showPickerDialog(int arg17, double arg18, double arg20, double arg22, double arg24) {
        GregorianCalendar v0_1;
        Calendar v0;
        int v1 = arg17;
        int v2 = 14;
        int v3 = 13;
        int v4 = 11;
        if(Double.isNaN(arg18)) {
            v0 = Calendar.getInstance();
            v0.set(v2, 0);
        }
        else if(v1 == v4) {
            v0 = MonthPicker.createDateFromValue(arg18);
        }
        else if(v1 == v3) {
            v0 = WeekPicker.createDateFromValue(arg18);
        }
        else {
            v0_1 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            v0_1.setGregorianChange(new Date(-9223372036854775808L));
            v0_1.setTimeInMillis(((long)arg18));
        }

        int v6 = 5;
        int v7 = 2;
        if(v1 == 8) {
            this.showPickerDialog(v1, ((Calendar)v0_1).get(1), ((Calendar)v0_1).get(v7), ((Calendar)v0_1).get(v6), 0, 0, 0, 0, 0, arg20, arg22, arg24);
        }
        else {
            int v5 = 12;
            if(v1 == v5) {
                this.showPickerDialog(v1, 0, 0, 0, ((Calendar)v0_1).get(v4), ((Calendar)v0_1).get(v5), 0, 0, 0, arg20, arg22, arg24);
            }
            else {
                if(v1 != 9) {
                    if(v1 == 10) {
                    }
                    else {
                        if(v1 == v4) {
                            this.showPickerDialog(v1, ((Calendar)v0_1).get(1), ((Calendar)v0_1).get(v7), 0, 0, 0, 0, 0, 0, arg20, arg22, arg24);
                        }
                        else if(v1 == v3) {
                            this.showPickerDialog(v1, WeekPicker.getISOWeekYearForDate(((Calendar)v0_1)), 0, 0, 0, 0, 0, 0, WeekPicker.getWeekForDate(((Calendar)v0_1)), arg20, arg22, arg24);
                        }
                        else {
                        }

                        return;
                    }
                }

                this.showPickerDialog(v1, ((Calendar)v0_1).get(1), ((Calendar)v0_1).get(v7), ((Calendar)v0_1).get(v6), ((Calendar)v0_1).get(v4), ((Calendar)v0_1).get(v5), ((Calendar)v0_1).get(v3), ((Calendar)v0_1).get(v2), 0, arg20, arg22, arg24);
            }
        }
    }

    protected void showPickerDialog(int arg23, int arg24, int arg25, int arg26, int arg27, int arg28, int arg29, int arg30, int arg31, double arg32, double arg34, double arg36) {
        InputDialogContainer v0 = this;
        int v1 = arg23;
        double v10 = arg32;
        double v12 = arg34;
        if(this.isDialogShowing()) {
            v0.mDialog.dismiss();
        }

        int v14 = ((int)arg36);
        if(v1 == 8) {
            DatePickerDialogCompat v2 = new DatePickerDialogCompat(v0.mContext, new DateListener(v0, v1), arg24, arg25, arg26);
            DateDialogNormalizer.normalize(v2.getDatePicker(), v2, arg24, arg25, arg26, ((long)v10), ((long)v12));
            v2.setTitle(v0.mContext.getText(0x7F0C0044));
            v0.mDialog = ((AlertDialog)v2);
        }
        else if(v1 == 12) {
            if(v14 >= 0) {
                if(v14 >= 60000) {
                }
                else {
                    v0.mDialog = new MultiFieldTimePickerDialog(v0.mContext, 0, arg27, arg28, arg29, arg30, ((int)v10), ((int)v12), v14, DateFormat.is24HourFormat(v0.mContext), new FullTimeListener(v0, v1));
                    goto label_126;
                }
            }

            v0.mDialog = new TimePickerDialog(v0.mContext, new TimeListener(v0, v1), arg27, arg28, DateFormat.is24HourFormat(v0.mContext));
        }
        else {
            if(v1 != 9) {
                if(v1 == 10) {
                }
                else {
                    if(v1 == 11) {
                        v0.mDialog = new MonthPickerDialog(v0.mContext, new MonthOrWeekListener(v0, v1), arg24, arg25, v10, v12);
                    }
                    else if(v1 == 13) {
                        v0.mDialog = new WeekPickerDialog(v0.mContext, new MonthOrWeekListener(v0, v1), arg24, arg31, v10, v12);
                    }
                    else {
                    }

                    goto label_126;
                }
            }

            v0.mDialog = new DateTimePickerDialog(v0.mContext, new DateTimeListener(v0, v1), arg24, arg25, arg26, arg27, arg28, DateFormat.is24HourFormat(v0.mContext), v10, v12);
        }

    label_126:
        v0.mDialog.setButton(-1, v0.mContext.getText(0x7F0C0043), v0.mDialog);
        v0.mDialog.setButton(-2, v0.mContext.getText(0x1040000), null);
        v0.mDialog.setButton(-3, v0.mContext.getText(0x7F0C0041), new DialogInterface$OnClickListener() {
            public void onClick(DialogInterface arg3, int arg4) {
                InputDialogContainer.this.mDialogAlreadyDismissed = true;
                InputDialogContainer.this.mInputActionDelegate.replaceDateTime(NaN);
            }
        });
        v0.mDialog.setOnDismissListener(new DialogInterface$OnDismissListener() {
            public void onDismiss(DialogInterface arg2) {
                if(!InputDialogContainer.this.mDialogAlreadyDismissed) {
                    InputDialogContainer.this.mDialogAlreadyDismissed = true;
                    InputDialogContainer.this.mInputActionDelegate.cancelDateTimeDialog();
                }
            }
        });
        v0.mDialogAlreadyDismissed = false;
        v0.mDialog.show();
    }

    private void showSuggestionDialog(int arg17, double arg18, double arg20, double arg22, double arg24, DateTimeSuggestion[] arg26) {
        int v0;
        InputDialogContainer v12 = this;
        int v13 = arg17;
        ListView v14 = new ListView(v12.mContext);
        DateTimeSuggestionListAdapter v2 = new DateTimeSuggestionListAdapter(v12.mContext, Arrays.asList(((Object[])arg26)));
        v14.setAdapter(((ListAdapter)v2));
        v14.setOnItemClickListener(new AdapterView$OnItemClickListener(v2, v13, arg18, arg20, arg22, arg24) {
            public void onItemClick(AdapterView arg11, View arg12, int arg13, long arg14) {
                if(arg13 == this.val$adapter.getCount() - 1) {
                    InputDialogContainer.this.dismissDialog();
                    InputDialogContainer.this.showPickerDialog(this.val$dialogType, this.val$dialogValue, this.val$min, this.val$max, this.val$step);
                }
                else {
                    InputDialogContainer.this.mInputActionDelegate.replaceDateTime(this.val$adapter.getItem(arg13).value());
                    InputDialogContainer.this.dismissDialog();
                    InputDialogContainer.this.mDialogAlreadyDismissed = true;
                }
            }
        });
        if(v13 == 12) {
            v0 = 0x7F0C007A;
        }
        else {
            if(v13 != 9) {
                if(v13 == 10) {
                }
                else {
                    if(v13 == 11) {
                        v0 = 0x7F0C005F;
                    }
                    else if(v13 == 13) {
                        v0 = 0x7F0C007D;
                    }
                    else {
                        v0 = 0x7F0C0044;
                    }

                    goto label_41;
                }
            }

            v0 = 0x7F0C0045;
        }

    label_41:
        v12.mDialog = new AlertDialog$Builder(v12.mContext).setTitle(v0).setView(((View)v14)).setNegativeButton(v12.mContext.getText(0x1040000), new DialogInterface$OnClickListener() {
            public void onClick(DialogInterface arg1, int arg2) {
                InputDialogContainer.this.dismissDialog();
            }
        }).create();
        v12.mDialog.setOnDismissListener(new DialogInterface$OnDismissListener() {
            public void onDismiss(DialogInterface arg2) {
                if(InputDialogContainer.this.mDialog == arg2 && !InputDialogContainer.this.mDialogAlreadyDismissed) {
                    InputDialogContainer.this.mDialogAlreadyDismissed = true;
                    InputDialogContainer.this.mInputActionDelegate.cancelDateTimeDialog();
                }
            }
        });
        v12.mDialogAlreadyDismissed = false;
        v12.mDialog.show();
    }
}

