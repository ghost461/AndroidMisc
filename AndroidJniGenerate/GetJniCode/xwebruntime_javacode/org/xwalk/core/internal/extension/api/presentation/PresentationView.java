package org.xwalk.core.internal.extension.api.presentation;

import android.content.Context;
import android.os.Build$VERSION;
import android.view.Display;
import android.view.View;

public abstract class PresentationView {
    public interface PresentationListener {
        void onDismiss(PresentationView arg1);

        void onShow(PresentationView arg1);
    }

    protected PresentationListener mListener;

    public PresentationView() {
        super();
    }

    public abstract void cancel();

    public static PresentationView createInstance(Context arg2, Display arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            return new PresentationViewJBMR1(arg2, arg3);
        }

        return new PresentationViewNull();
    }

    public abstract void dismiss();

    public abstract Display getDisplay();

    public abstract void setContentView(View arg1);

    public void setPresentationListener(PresentationListener arg1) {
        this.mListener = arg1;
    }

    public abstract void show();
}

