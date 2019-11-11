package android.support.v7.widget;

import android.graphics.Rect;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public interface FitWindowsViewGroup {
    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect arg1);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener arg1);
}

