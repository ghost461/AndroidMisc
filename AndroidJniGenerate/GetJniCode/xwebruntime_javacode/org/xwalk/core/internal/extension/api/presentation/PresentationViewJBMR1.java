package org.xwalk.core.internal.extension.api.presentation;

import android.annotation.SuppressLint;
import android.app.Presentation;
import android.content.Context;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnShowListener;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;

@SuppressLint(value={"NewApi"}) public class PresentationViewJBMR1 extends PresentationView implements DialogInterface$OnDismissListener, DialogInterface$OnShowListener {
    private Presentation mPresentation;

    public PresentationViewJBMR1(Context arg2, Display arg3) {
        super();
        this.mPresentation = new Presentation(arg2, arg3);
    }

    public void cancel() {
        this.mPresentation.cancel();
    }

    public void dismiss() {
        this.mPresentation.dismiss();
    }

    public Display getDisplay() {
        return this.mPresentation.getDisplay();
    }

    public void onDismiss(DialogInterface arg1) {
        if(this.mListener != null) {
            this.mListener.onDismiss(((PresentationView)this));
        }
    }

    public void onShow(DialogInterface arg1) {
        if(this.mListener != null) {
            this.mListener.onShow(((PresentationView)this));
        }
    }

    public void setContentView(View arg2) {
        this.mPresentation.setContentView(arg2);
    }

    public void setPresentationListener(PresentationListener arg2) {
        super.setPresentationListener(arg2);
        if(this.mListener != null) {
            this.mPresentation.setOnShowListener(((DialogInterface$OnShowListener)this));
            this.mPresentation.setOnDismissListener(((DialogInterface$OnDismissListener)this));
        }
        else {
            this.mPresentation.setOnShowListener(null);
            this.mPresentation.setOnDismissListener(null);
        }
    }

    public void show() {
        this.mPresentation.show();
    }
}

