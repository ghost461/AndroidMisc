package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.widget.FrameLayout;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class FitWindowsFrameLayout extends FrameLayout implements FitWindowsViewGroup {
    private OnFitSystemWindowsListener mListener;

    public FitWindowsFrameLayout(Context arg1) {
        super(arg1);
    }

    public FitWindowsFrameLayout(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    protected boolean fitSystemWindows(Rect arg2) {
        if(this.mListener != null) {
            this.mListener.onFitSystemWindows(arg2);
        }

        return super.fitSystemWindows(arg2);
    }

    public void setOnFitSystemWindowsListener(OnFitSystemWindowsListener arg1) {
        this.mListener = arg1;
    }
}

