package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.view.Gravity;

public final class GravityCompat {
    public static final int END = 0x800005;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 0x800007;
    public static final int RELATIVE_LAYOUT_DIRECTION = 0x800000;
    public static final int START = 0x800003;

    private GravityCompat() {
        super();
    }

    public static void apply(int arg2, int arg3, int arg4, Rect arg5, Rect arg6, int arg7) {
        if(Build$VERSION.SDK_INT >= 17) {
            Gravity.apply(arg2, arg3, arg4, arg5, arg6, arg7);
        }
        else {
            Gravity.apply(arg2, arg3, arg4, arg5, arg6);
        }
    }

    public static void apply(int arg2, int arg3, int arg4, Rect arg5, int arg6, int arg7, Rect arg8, int arg9) {
        if(Build$VERSION.SDK_INT >= 17) {
            Gravity.apply(arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
        }
        else {
            Gravity.apply(arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
    }

    public static void applyDisplay(int arg2, Rect arg3, Rect arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 17) {
            Gravity.applyDisplay(arg2, arg3, arg4, arg5);
        }
        else {
            Gravity.applyDisplay(arg2, arg3, arg4);
        }
    }

    public static int getAbsoluteGravity(int arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            return Gravity.getAbsoluteGravity(arg2, arg3);
        }

        return arg2 & 0xFF7FFFFF;
    }
}

