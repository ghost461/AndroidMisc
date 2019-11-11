package android.support.v4.math;

public class MathUtils {
    private MathUtils() {
        super();
    }

    public static double clamp(double arg1, double arg3, double arg5) {
        if(arg1 < arg3) {
            return arg3;
        }

        if(arg1 > arg5) {
            return arg5;
        }

        return arg1;
    }

    public static float clamp(float arg1, float arg2, float arg3) {
        if(arg1 < arg2) {
            return arg2;
        }

        if(arg1 > arg3) {
            return arg3;
        }

        return arg1;
    }

    public static int clamp(int arg0, int arg1, int arg2) {
        if(arg0 < arg1) {
            return arg1;
        }

        if(arg0 > arg2) {
            return arg2;
        }

        return arg0;
    }
}

