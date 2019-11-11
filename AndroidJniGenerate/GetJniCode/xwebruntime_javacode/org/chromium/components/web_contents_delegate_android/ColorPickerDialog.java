package org.chromium.components.web_contents_delegate_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.Button;

public class ColorPickerDialog extends AlertDialog implements OnColorChangedListener {
    private final ColorPickerAdvanced mAdvancedColorPicker;
    private int mCurrentColor;
    private final View mCurrentColorView;
    private final int mInitialColor;
    private final OnColorChangedListener mListener;
    private final Button mMoreButton;
    private final ColorPickerSimple mSimpleColorPicker;

    public ColorPickerDialog(Context arg4, OnColorChangedListener arg5, int arg6, ColorSuggestion[] arg7) {
        super(arg4, 0);
        this.mListener = arg5;
        this.mInitialColor = arg6;
        this.mCurrentColor = this.mInitialColor;
        Object v5 = arg4.getSystemService("layout_inflater");
        View v0 = ((LayoutInflater)v5).inflate(0x7F09001F, null);
        this.setCustomTitle(v0);
        this.mCurrentColorView = v0.findViewById(0x7F07009D);
        v0.findViewById(0x7F0700B7).setText(0x7F0C003B);
        this.setButton(-1, arg4.getString(0x7F0C0038), new DialogInterface$OnClickListener() {
            public void onClick(DialogInterface arg1, int arg2) {
                ColorPickerDialog.access$100(ColorPickerDialog.this, ColorPickerDialog.access$000(ColorPickerDialog.this));
            }
        });
        this.setButton(-2, arg4.getString(0x7F0C0032), new DialogInterface$OnClickListener() {
            public void onClick(DialogInterface arg1, int arg2) {
                ColorPickerDialog.access$100(ColorPickerDialog.this, ColorPickerDialog.access$200(ColorPickerDialog.this));
            }
        });
        this.setOnCancelListener(new DialogInterface$OnCancelListener() {
            public void onCancel(DialogInterface arg2) {
                ColorPickerDialog.access$100(ColorPickerDialog.this, ColorPickerDialog.access$200(ColorPickerDialog.this));
            }
        });
        View v4 = ((LayoutInflater)v5).inflate(0x7F09001E, null);
        this.setView(v4);
        this.mMoreButton = v4.findViewById(0x7F070068);
        this.mMoreButton.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                ColorPickerDialog.access$300(ColorPickerDialog.this);
            }
        });
        this.mAdvancedColorPicker = v4.findViewById(0x7F07002C);
        this.mAdvancedColorPicker.setVisibility(8);
        this.mSimpleColorPicker = v4.findViewById(0x7F07002D);
        this.mSimpleColorPicker.init(arg7, ((OnColorChangedListener)this));
        this.updateCurrentColor(this.mInitialColor);
    }

    static int access$000(ColorPickerDialog arg0) {
        return arg0.mCurrentColor;
    }

    static void access$100(ColorPickerDialog arg0, int arg1) {
        arg0.tryNotifyColorSet(arg1);
    }

    static int access$200(ColorPickerDialog arg0) {
        return arg0.mInitialColor;
    }

    static void access$300(ColorPickerDialog arg0) {
        arg0.showAdvancedView();
    }

    public void onColorChanged(int arg1) {
        this.updateCurrentColor(arg1);
    }

    private void showAdvancedView() {
        this.findViewById(0x7F070069).setVisibility(8);
        this.findViewById(0x7F07002D).setVisibility(8);
        this.mAdvancedColorPicker.setVisibility(0);
        this.mAdvancedColorPicker.setListener(((OnColorChangedListener)this));
        this.mAdvancedColorPicker.setColor(this.mCurrentColor);
    }

    private void tryNotifyColorSet(int arg2) {
        if(this.mListener != null) {
            this.mListener.onColorChanged(arg2);
        }
    }

    private void updateCurrentColor(int arg2) {
        this.mCurrentColor = arg2;
        if(this.mCurrentColorView != null) {
            this.mCurrentColorView.setBackgroundColor(arg2);
        }
    }
}

