package android.support.v4.view;

import android.view.VelocityTracker;

@Deprecated public final class VelocityTrackerCompat {
    private VelocityTrackerCompat() {
        super();
    }

    @Deprecated public static float getXVelocity(VelocityTracker arg0, int arg1) {
        return arg0.getXVelocity(arg1);
    }

    @Deprecated public static float getYVelocity(VelocityTracker arg0, int arg1) {
        return arg0.getYVelocity(arg1);
    }
}

