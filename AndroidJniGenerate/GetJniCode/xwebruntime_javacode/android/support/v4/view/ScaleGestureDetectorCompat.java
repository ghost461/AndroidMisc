package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.ScaleGestureDetector;

public final class ScaleGestureDetectorCompat {
    private ScaleGestureDetectorCompat() {
        super();
    }

    public static boolean isQuickScaleEnabled(ScaleGestureDetector arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.isQuickScaleEnabled();
        }

        return 0;
    }

    @Deprecated public static boolean isQuickScaleEnabled(Object arg0) {
        return ScaleGestureDetectorCompat.isQuickScaleEnabled(((ScaleGestureDetector)arg0));
    }

    public static void setQuickScaleEnabled(ScaleGestureDetector arg2, boolean arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            arg2.setQuickScaleEnabled(arg3);
        }
    }

    @Deprecated public static void setQuickScaleEnabled(Object arg0, boolean arg1) {
        ScaleGestureDetectorCompat.setQuickScaleEnabled(((ScaleGestureDetector)arg0), arg1);
    }
}

