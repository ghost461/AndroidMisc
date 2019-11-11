package org.chromium.ui.base;

import android.view.View;

public final class ViewUtils {
    private ViewUtils() {
        super();
    }

    public static boolean hasFocus(View arg1) {
        boolean v1 = !ViewUtils.isFocusable(arg1) ? true : arg1.hasFocus();
        return v1;
    }

    private static boolean isFocusable(View arg1) {
        boolean v1 = arg1.isInTouchMode() ? arg1.isFocusableInTouchMode() : arg1.isFocusable();
        return v1;
    }

    public static void requestFocus(View arg1) {
        if((ViewUtils.isFocusable(arg1)) && !arg1.isFocused()) {
            arg1.requestFocus();
        }
    }
}

