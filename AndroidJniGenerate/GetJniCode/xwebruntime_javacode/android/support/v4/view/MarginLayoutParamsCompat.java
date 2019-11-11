package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.ViewGroup$MarginLayoutParams;

public final class MarginLayoutParamsCompat {
    private MarginLayoutParamsCompat() {
        super();
    }

    public static int getLayoutDirection(ViewGroup$MarginLayoutParams arg3) {
        int v3 = Build$VERSION.SDK_INT >= 17 ? arg3.getLayoutDirection() : 0;
        if(v3 != 0 && v3 != 1) {
            v3 = 0;
        }

        return v3;
    }

    public static int getMarginEnd(ViewGroup$MarginLayoutParams arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getMarginEnd();
        }

        return arg2.rightMargin;
    }

    public static int getMarginStart(ViewGroup$MarginLayoutParams arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getMarginStart();
        }

        return arg2.leftMargin;
    }

    public static boolean isMarginRelative(ViewGroup$MarginLayoutParams arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.isMarginRelative();
        }

        return 0;
    }

    public static void resolveLayoutDirection(ViewGroup$MarginLayoutParams arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.resolveLayoutDirection(arg3);
        }
    }

    public static void setLayoutDirection(ViewGroup$MarginLayoutParams arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setLayoutDirection(arg3);
        }
    }

    public static void setMarginEnd(ViewGroup$MarginLayoutParams arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setMarginEnd(arg3);
        }
        else {
            arg2.rightMargin = arg3;
        }
    }

    public static void setMarginStart(ViewGroup$MarginLayoutParams arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setMarginStart(arg3);
        }
        else {
            arg2.leftMargin = arg3;
        }
    }
}

