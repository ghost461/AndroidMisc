package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public class TooltipCompat {
    class android.support.v7.widget.TooltipCompat$1 {
    }

    @TargetApi(value=26) class Api26ViewCompatImpl implements ViewCompatImpl {
        Api26ViewCompatImpl(android.support.v7.widget.TooltipCompat$1 arg1) {
            this();
        }

        private Api26ViewCompatImpl() {
            super();
        }

        public void setTooltipText(@NonNull View arg1, @Nullable CharSequence arg2) {
            arg1.setTooltipText(arg2);
        }
    }

    class BaseViewCompatImpl implements ViewCompatImpl {
        BaseViewCompatImpl(android.support.v7.widget.TooltipCompat$1 arg1) {
            this();
        }

        private BaseViewCompatImpl() {
            super();
        }

        public void setTooltipText(@NonNull View arg1, @Nullable CharSequence arg2) {
            TooltipCompatHandler.setTooltipText(arg1, arg2);
        }
    }

    interface ViewCompatImpl {
        void setTooltipText(@NonNull View arg1, @Nullable CharSequence arg2);
    }

    private static final ViewCompatImpl IMPL;

    static {
        android.support.v7.widget.TooltipCompat$1 v1 = null;
        TooltipCompat.IMPL = Build$VERSION.SDK_INT >= 26 ? new Api26ViewCompatImpl(v1) : new BaseViewCompatImpl(v1);
    }

    private TooltipCompat() {
        super();
    }

    public static void setTooltipText(@NonNull View arg1, @Nullable CharSequence arg2) {
        TooltipCompat.IMPL.setTooltipText(arg1, arg2);
    }
}

