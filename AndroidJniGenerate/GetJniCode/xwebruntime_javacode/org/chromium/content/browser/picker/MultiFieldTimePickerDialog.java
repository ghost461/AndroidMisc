package org.chromium.content.browser.picker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.view.View;
import android.widget.NumberPicker$Formatter;
import android.widget.NumberPicker;
import java.util.ArrayList;

@SuppressLint(value={"DefaultLocale"}) public class MultiFieldTimePickerDialog extends AlertDialog implements DialogInterface$OnClickListener {
    class NumberFormatter implements NumberPicker$Formatter {
        private final String mFormat;

        NumberFormatter(String arg1) {
            super();
            this.mFormat = arg1;
        }

        public String format(int arg4) {
            return String.format(this.mFormat, Integer.valueOf(arg4));
        }
    }

    public interface OnMultiFieldTimeSetListener {
        void onTimeSet(int arg1, int arg2, int arg3, int arg4);
    }

    private static final int HOUR_IN_MILLIS = 3600000;
    private static final int MINUTE_IN_MILLIS = 60000;
    private static final int SECOND_IN_MILLIS = 1000;
    private final NumberPicker mAmPmSpinner;
    private final int mBaseMilli;
    private final NumberPicker mHourSpinner;
    private final boolean mIs24hourFormat;
    private final OnMultiFieldTimeSetListener mListener;
    private final NumberPicker mMilliSpinner;
    private final NumberPicker mMinuteSpinner;
    private final NumberPicker mSecSpinner;
    private final int mStep;

    public MultiFieldTimePickerDialog(Context arg19, int arg20, int arg21, int arg22, int arg23, int arg24, int arg25, int arg26, int arg27, boolean arg28, OnMultiFieldTimeSetListener arg29) {
        int v15;
        int v3_1;
        int v12;
        MultiFieldTimePickerDialog v0 = this;
        Context v1 = arg19;
        int v2 = arg27;
        boolean v3 = arg28;
        super(arg19, arg20);
        v0.mListener = arg29;
        v0.mStep = v2;
        v0.mIs24hourFormat = v3;
        int v5 = arg25;
        int v6 = arg26;
        if(v5 >= v6) {
            v5 = 0;
            v6 = 0x5265BFF;
        }

        if(v2 < 0 || v2 >= 86400000) {
            v2 = 60000;
        }

        View v8 = v1.getSystemService("layout_inflater").inflate(0x7F090024, null);
        v0.setView(v8);
        v0.mHourSpinner = v8.findViewById(0x7F07004C);
        v0.mMinuteSpinner = v8.findViewById(0x7F070067);
        v0.mSecSpinner = v8.findViewById(0x7F07008E);
        v0.mMilliSpinner = v8.findViewById(0x7F070066);
        v0.mAmPmSpinner = v8.findViewById(0x7F07001E);
        int v10 = v5 / 3600000;
        int v11 = v6 / 3600000;
        v5 -= v10 * 3600000;
        v6 -= v11 * 3600000;
        if(v10 == v11) {
            v0.mHourSpinner.setEnabled(false);
            v12 = v10;
        }
        else {
            v12 = arg21;
        }

        int v13 = 8;
        if(v3) {
            v0.mAmPmSpinner.setVisibility(v13);
        }
        else {
            v3_1 = v10 / 12;
            v15 = v11 / 12;
            int v16 = v12 / 12;
            v0.mAmPmSpinner.setMinValue(v3_1);
            v0.mAmPmSpinner.setMaxValue(v15);
            v0.mAmPmSpinner.setDisplayedValues(new String[]{v1.getString(0x7F0C0075), v1.getString(0x7F0C0078)});
            v12 %= 12;
            if(v12 == 0) {
                v12 = 12;
            }

            if(v3_1 == v15) {
                v0.mAmPmSpinner.setEnabled(false);
                v10 %= 12;
                v11 %= 12;
                if(v10 == 0 && v11 == 0) {
                    v10 = 12;
                    goto label_97;
                }

                if(v10 == 0) {
                    v10 = v11;
                    goto label_97;
                }

                if(v11 != 0) {
                    goto label_98;
                }

                goto label_97;
            }
            else {
                v3_1 = v16;
                v10 = 1;
            label_97:
                v11 = 12;
            }

        label_98:
            v0.mAmPmSpinner.setValue(v3_1);
        }

        if(v10 == v11) {
            v0.mHourSpinner.setEnabled(false);
        }

        v0.mHourSpinner.setMinValue(v10);
        v0.mHourSpinner.setMaxValue(v11);
        v0.mHourSpinner.setValue(v12);
        NumberFormatter v1_1 = new NumberFormatter("%02d");
        int v7 = v5 / 60000;
        int v9 = v6 / 60000;
        v5 -= v7 * 60000;
        v6 -= v9 * 60000;
        v3_1 = 59;
        if(v10 == v11) {
            v0.mMinuteSpinner.setMinValue(v7);
            v0.mMinuteSpinner.setMaxValue(v9);
            if(v7 == v9) {
                v0.mMinuteSpinner.setDisplayedValues(new String[]{v1_1.format(v7)});
                v0.mMinuteSpinner.setEnabled(false);
                v12 = v7;
            }
            else {
                goto label_139;
            }
        }
        else {
            v0.mMinuteSpinner.setMinValue(0);
            v0.mMinuteSpinner.setMaxValue(v3_1);
        label_139:
            v12 = arg22;
        }

        v0.mMinuteSpinner.setValue(v12);
        if(v2 % 3600000 == 0) {
            v0.mMinuteSpinner.setEnabled(false);
            v0.mMinuteSpinner.setValue(v7);
        }

        v0.mMinuteSpinner.setFormatter(((NumberPicker$Formatter)v1_1));
        if(v2 >= 60000) {
            v8.findViewById(0x7F07008F).setVisibility(8);
            v0.mSecSpinner.setVisibility(8);
        }

        v12 = v5 / 1000;
        v13 = v6 / 1000;
        v5 -= v12 * 1000;
        v6 -= v13 * 1000;
        if(v10 != v11 || v7 != v9) {
            v0.mSecSpinner.setMinValue(0);
            v0.mSecSpinner.setMaxValue(v3_1);
        label_185:
            v3_1 = arg23;
        }
        else {
            v0.mSecSpinner.setMinValue(v12);
            v0.mSecSpinner.setMaxValue(v13);
            if(v12 == v13) {
                v0.mSecSpinner.setDisplayedValues(new String[]{v1_1.format(v12)});
                v0.mSecSpinner.setEnabled(false);
                v3_1 = v12;
            }
            else {
                goto label_185;
            }
        }

        v0.mSecSpinner.setValue(v3_1);
        v0.mSecSpinner.setFormatter(((NumberPicker$Formatter)v1_1));
        int v1_2 = 1000;
        if(v2 >= v1_2) {
            v8.findViewById(0x7F070090).setVisibility(8);
            v0.mMilliSpinner.setVisibility(8);
        }

        v3_1 = (arg24 + v2 / 2) / v2 * v2;
        int v8_1 = 100;
        v15 = 10;
        if(v2 == 1 || v2 == v15 || v2 == v8_1) {
            if(v10 != v11 || v7 != v9 || v12 != v13) {
                v0.mMilliSpinner.setMinValue(0);
                v0.mMilliSpinner.setMaxValue(999 / v2);
            }
            else {
                v0.mMilliSpinner.setMinValue(v5 / v2);
                v0.mMilliSpinner.setMaxValue(v6 / v2);
                if(v5 == v6) {
                    v0.mMilliSpinner.setEnabled(false);
                    v3_1 = v5;
                }
            }

            if(v2 == 1) {
                v0.mMilliSpinner.setFormatter(new NumberFormatter("%03d"));
            }
            else if(v2 == v15) {
                v0.mMilliSpinner.setFormatter(new NumberFormatter("%02d"));
            }
            else if(v2 == v8_1) {
                v0.mMilliSpinner.setFormatter(new NumberFormatter("%d"));
            }

            v0.mMilliSpinner.setValue(v3_1 / v2);
            v0.mBaseMilli = 0;
        }
        else if(v2 < v1_2) {
            ArrayList v1_3 = new ArrayList();
            for(v7 = v5; v7 < v6; v7 += v2) {
                v1_3.add(String.format("%03d", Integer.valueOf(v7)));
            }

            v0.mMilliSpinner.setMinValue(0);
            v0.mMilliSpinner.setMaxValue(v1_3.size() - 1);
            v0.mMilliSpinner.setValue((v3_1 - v5) / v2);
            v0.mMilliSpinner.setDisplayedValues(v1_3.toArray(new String[v1_3.size()]));
            v0.mBaseMilli = v5;
        }
        else {
            v0.mBaseMilli = 0;
        }
    }

    private int getPickerValue(NumberPicker arg1) {
        arg1.clearFocus();
        return arg1.getValue();
    }

    private void notifyDateSet() {
        int v0 = this.getPickerValue(this.mHourSpinner);
        int v1 = this.getPickerValue(this.mMinuteSpinner);
        int v2 = this.getPickerValue(this.mSecSpinner);
        int v3 = this.getPickerValue(this.mMilliSpinner) * this.mStep + this.mBaseMilli;
        if(!this.mIs24hourFormat) {
            int v4 = this.getPickerValue(this.mAmPmSpinner);
            if(v0 == 12) {
                v0 = 0;
            }

            v0 += v4 * 12;
        }

        this.mListener.onTimeSet(v0, v1, v2, v3);
    }

    public void onClick(DialogInterface arg1, int arg2) {
        this.notifyDateSet();
    }
}

