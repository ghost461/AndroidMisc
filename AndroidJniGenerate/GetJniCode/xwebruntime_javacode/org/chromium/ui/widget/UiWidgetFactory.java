package org.chromium.ui.widget;

import android.annotation.SuppressLint;
import android.app.AlertDialog$Builder;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.PopupWindow;
import android.widget.Toast;

public class UiWidgetFactory {
    private static UiWidgetFactory sFactory;

    protected UiWidgetFactory() {
        super();
    }

    public AlertDialog createAlertDialog(Context arg2) {
        return new AlertDialog$Builder(arg2).create();
    }

    public PopupWindow createPopupWindow(Context arg2) {
        return new PopupWindow(arg2);
    }

    @SuppressLint(value={"ShowToast"}) public Toast createToast(Context arg2) {
        return new Toast(arg2);
    }

    public static UiWidgetFactory getInstance() {
        if(UiWidgetFactory.sFactory == null) {
            UiWidgetFactory.sFactory = new UiWidgetFactory();
        }

        return UiWidgetFactory.sFactory;
    }

    @SuppressLint(value={"ShowToast"}) public Toast makeToast(Context arg1, CharSequence arg2, int arg3) {
        return Toast.makeText(arg1, arg2, arg3);
    }

    public static void setInstance(UiWidgetFactory arg0) {
        UiWidgetFactory.sFactory = arg0;
    }
}

