package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build$VERSION;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public final class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
        super();
    }

    public static Interpolator create(float arg2, float arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            return new PathInterpolator(arg2, arg3);
        }

        return new PathInterpolatorApi14(arg2, arg3);
    }

    public static Interpolator create(float arg2, float arg3, float arg4, float arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            return new PathInterpolator(arg2, arg3, arg4, arg5);
        }

        return new PathInterpolatorApi14(arg2, arg3, arg4, arg5);
    }

    public static Interpolator create(Path arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return new PathInterpolator(arg2);
        }

        return new PathInterpolatorApi14(arg2);
    }
}

