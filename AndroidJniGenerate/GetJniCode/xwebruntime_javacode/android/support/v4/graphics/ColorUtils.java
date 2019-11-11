package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

public final class ColorUtils {
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    private static final ThreadLocal TEMP_ARRAY = null;
    private static final double XYZ_EPSILON = 0.008856;
    private static final double XYZ_KAPPA = 903.3;
    private static final double XYZ_WHITE_REFERENCE_X = 95.047;
    private static final double XYZ_WHITE_REFERENCE_Y = 100;
    private static final double XYZ_WHITE_REFERENCE_Z = 108.883;

    static {
        ColorUtils.TEMP_ARRAY = new ThreadLocal();
    }

    private ColorUtils() {
        super();
    }

    @ColorInt public static int HSLToColor(@NonNull float[] arg6) {
        int v3;
        int v1_1;
        int v6_1;
        float v1 = arg6[0];
        float v2 = arg6[1];
        float v6 = arg6[2];
        float v4 = (1f - Math.abs(v6 * 2f - 1f)) * v2;
        v6 -= 0.5f * v4;
        float v5 = (1f - Math.abs(v1 / 60f % 2f - 1f)) * v4;
        v2 = 255f;
        switch((((int)v1)) / 60) {
            case 0: {
                v1_1 = Math.round((v4 + v6) * v2);
                v3 = Math.round((v5 + v6) * v2);
                v6_1 = Math.round(v6 * v2);
                break;
            }
            case 1: {
                v1_1 = Math.round((v5 + v6) * v2);
                v3 = Math.round((v4 + v6) * v2);
                v6_1 = Math.round(v6 * v2);
                break;
            }
            case 2: {
                v1_1 = Math.round(v6 * v2);
                v3 = Math.round((v4 + v6) * v2);
                v6_1 = Math.round((v5 + v6) * v2);
                break;
            }
            case 3: {
                v1_1 = Math.round(v6 * v2);
                v3 = Math.round((v5 + v6) * v2);
                v6_1 = Math.round((v4 + v6) * v2);
                break;
            }
            case 4: {
                v1_1 = Math.round((v5 + v6) * v2);
                v3 = Math.round(v6 * v2);
                v6_1 = Math.round((v4 + v6) * v2);
                break;
            }
            case 5: 
            case 6: {
                v1_1 = Math.round((v4 + v6) * v2);
                v3 = Math.round(v6 * v2);
                v6_1 = Math.round((v5 + v6) * v2);
                break;
            }
            default: {
                v6_1 = 0;
                v1_1 = 0;
                v3 = 0;
                break;
            }
        }

        return Color.rgb(ColorUtils.constrain(v1_1, 0, 0xFF), ColorUtils.constrain(v3, 0, 0xFF), ColorUtils.constrain(v6_1, 0, 0xFF));
    }

    @ColorInt public static int LABToColor(@FloatRange(from=0, to=100) double arg8, @FloatRange(from=-128, to=127) double arg10, @FloatRange(from=-128, to=127) double arg12) {
        double[] v7 = ColorUtils.getTempDouble3Array();
        ColorUtils.LABToXYZ(arg8, arg10, arg12, v7);
        return ColorUtils.XYZToColor(v7[0], v7[1], v7[2]);
    }

    public static void LABToXYZ(@FloatRange(from=0, to=100) double arg21, @FloatRange(from=-128, to=127) double arg23, @FloatRange(from=-128, to=127) double arg25, @NonNull double[] arg27) {
        double v2 = 16;
        double v6 = 116;
        double v4 = (arg21 + v2) / v6;
        double v8 = arg23 / 500 + v4;
        double v10 = v4 - arg25 / 200;
        double v12 = 3;
        double v14 = Math.pow(v8, v12);
        double v16 = 0.008856;
        double v19 = 903.3;
        if(Double.compare(v14, v16) > 0) {
        }
        else {
            v14 = (v8 * v6 - v2) / v19;
        }

        double v0 = arg21 > 7.999625 ? Math.pow(v4, v12) : arg21 / v19;
        v4 = Math.pow(v10, v12);
        if(v4 > v16) {
        }
        else {
            v4 = (v10 * v6 - v2) / v19;
        }

        arg27[0] = v14 * 95.047;
        arg27[1] = v0 * 100;
        arg27[2] = v4 * 108.883;
    }

    public static void RGBToHSL(@IntRange(from=0, to=0xFF) int arg7, @IntRange(from=0, to=0xFF) int arg8, @IntRange(from=0, to=0xFF) int arg9, @NonNull float[] arg10) {
        float v7 = (((float)arg7)) / 255f;
        float v8 = (((float)arg8)) / 255f;
        float v9 = (((float)arg9)) / 255f;
        float v0 = Math.max(v7, Math.max(v8, v9));
        float v1 = Math.min(v7, Math.min(v8, v9));
        float v2 = v0 - v1;
        float v4 = 2f;
        float v3 = (v0 + v1) / v4;
        float v5 = 1f;
        if(Float.compare(v0, v1) == 0) {
            v7 = 0f;
            v8 = 0f;
        }
        else {
            if(v0 == v7) {
                v7 = (v8 - v9) / v2 % 6f;
            }
            else if(v0 == v8) {
                v7 = (v9 - v7) / v2 + v4;
            }
            else {
                v7 = (v7 - v8) / v2 + 4f;
            }

            v8 = v2 / (v5 - Math.abs(v4 * v3 - v5));
        }

        v9 = 360f;
        v7 = v7 * 60f % v9;
        if(v7 < 0f) {
            v7 += v9;
        }

        arg10[0] = ColorUtils.constrain(v7, 0f, v9);
        arg10[1] = ColorUtils.constrain(v8, 0f, v5);
        arg10[2] = ColorUtils.constrain(v3, 0f, v5);
    }

    public static void RGBToLAB(@IntRange(from=0, to=0xFF) int arg7, @IntRange(from=0, to=0xFF) int arg8, @IntRange(from=0, to=0xFF) int arg9, @NonNull double[] arg10) {
        ColorUtils.RGBToXYZ(arg7, arg8, arg9, arg10);
        ColorUtils.XYZToLAB(arg10[0], arg10[1], arg10[2], arg10);
    }

    public static void RGBToXYZ(@IntRange(from=0, to=0xFF) int arg20, @IntRange(from=0, to=0xFF) int arg21, @IntRange(from=0, to=0xFF) int arg22, @NonNull double[] arg23) {
        double[] v0 = arg23;
        if(v0.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }

        double v3 = 255;
        double v1 = (((double)arg20)) / v3;
        double v5 = 0.04045;
        double v8 = 2.4;
        double v10 = 1.055;
        double v12 = 0.055;
        double v14 = 12.92;
        if(Double.compare(v1, v5) < 0) {
            v1 /= v14;
        }
        else {
            v1 = Math.pow((v1 + v12) / v10, v8);
        }

        double v16 = v1;
        int v1_1 = arg21;
        v1 = (((double)v1_1)) / v3;
        if(v1 < v5) {
            v1 /= v14;
        }
        else {
            v1 = Math.pow((v1 + v12) / v10, v8);
        }

        double v18 = v1;
        v1_1 = arg22;
        v1 = (((double)v1_1)) / v3;
        if(v1 < v5) {
            v1 /= v14;
        }
        else {
            v1 = Math.pow((v1 + v12) / v10, v8);
        }

        v0[0] = (0.4124 * v16 + 0.3576 * v18 + 0.1805 * v1) * 100;
        v0[1] = (0.2126 * v16 + 0.7152 * v18 + 0.0722 * v1) * 100;
        v0[2] = (v16 * 0.0193 + v18 * 0.1192 + v1 * 0.9505) * 100;
    }

    @ColorInt public static int XYZToColor(@FloatRange(from=0, to=95.047) double arg18, @FloatRange(from=0, to=100) double arg20, @FloatRange(from=0, to=108.883) double arg22) {
        double v6 = (3.2406 * arg18 + -1.5372 * arg20 + -0.4986 * arg22) / 100;
        double v10 = (-0.9689 * arg18 + 1.8758 * arg20 + 0.0415 * arg22) / 100;
        double v0 = (arg18 * 0.0557 + arg20 * -0.204 + 1.057 * arg22) / 100;
        double v2 = 0.003131;
        double v8 = 12.92;
        double v12 = 0.055;
        double v14 = 0.416667;
        double v16 = 1.055;
        double v4 = Double.compare(v6, v2) > 0 ? Math.pow(v6, v14) * v16 - v12 : v6 * v8;
        v6 = v10 > v2 ? Math.pow(v10, v14) * v16 - v12 : v10 * v8;
        if(v0 > v2) {
            v0 = Math.pow(v0, v14) * v16 - v12;
        }
        else {
            v0 *= v8;
        }

        return Color.rgb(ColorUtils.constrain(((int)Math.round(v4 * 255)), 0, 0xFF), ColorUtils.constrain(((int)Math.round(v6 * 255)), 0, 0xFF), ColorUtils.constrain(((int)Math.round(v0 * 255)), 0, 0xFF));
    }

    public static void XYZToLAB(@FloatRange(from=0, to=95.047) double arg7, @FloatRange(from=0, to=100) double arg9, @FloatRange(from=0, to=108.883) double arg11, @NonNull double[] arg13) {
        if(arg13.length != 3) {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        }

        arg7 = ColorUtils.pivotXyzComponent(arg7 / 95.047);
        arg9 = ColorUtils.pivotXyzComponent(arg9 / 100);
        arg11 = ColorUtils.pivotXyzComponent(arg11 / 108.883);
        arg13[0] = Math.max(0, 116 * arg9 - 16);
        arg13[1] = (arg7 - arg9) * 500;
        arg13[2] = (arg9 - arg11) * 200;
    }

    @ColorInt public static int blendARGB(@ColorInt int arg5, @ColorInt int arg6, @FloatRange(from=0, to=1) float arg7) {
        float v0 = 1f - arg7;
        return Color.argb(((int)((((float)Color.alpha(arg5))) * v0 + (((float)Color.alpha(arg6))) * arg7)), ((int)((((float)Color.red(arg5))) * v0 + (((float)Color.red(arg6))) * arg7)), ((int)((((float)Color.green(arg5))) * v0 + (((float)Color.green(arg6))) * arg7)), ((int)((((float)Color.blue(arg5))) * v0 + (((float)Color.blue(arg6))) * arg7)));
    }

    public static void blendHSL(@NonNull float[] arg4, @NonNull float[] arg5, @FloatRange(from=0, to=1) float arg6, @NonNull float[] arg7) {
        if(arg7.length != 3) {
            throw new IllegalArgumentException("result must have a length of 3.");
        }

        float v0 = 1f - arg6;
        arg7[0] = ColorUtils.circularInterpolate(arg4[0], arg5[0], arg6);
        arg7[1] = arg4[1] * v0 + arg5[1] * arg6;
        arg7[2] = arg4[2] * v0 + arg5[2] * arg6;
    }

    public static void blendLAB(@NonNull double[] arg7, @NonNull double[] arg8, @FloatRange(from=0, to=1) double arg9, @NonNull double[] arg11) {
        if(arg11.length != 3) {
            throw new IllegalArgumentException("outResult must have a length of 3.");
        }

        double v0 = 1 - arg9;
        arg11[0] = arg7[0] * v0 + arg8[0] * arg9;
        arg11[1] = arg7[1] * v0 + arg8[1] * arg9;
        arg11[2] = arg7[2] * v0 + arg8[2] * arg9;
    }

    public static double calculateContrast(@ColorInt int arg4, @ColorInt int arg5) {
        int v1 = 0xFF;
        if(Color.alpha(arg5) != v1) {
            StringBuilder v0 = new StringBuilder();
            v0.append("background can not be translucent: #");
            v0.append(Integer.toHexString(arg5));
            throw new IllegalArgumentException(v0.toString());
        }

        if(Color.alpha(arg4) < v1) {
            arg4 = ColorUtils.compositeColors(arg4, arg5);
        }

        double v0_1 = ColorUtils.calculateLuminance(arg4) + 0.05;
        double v4 = ColorUtils.calculateLuminance(arg5) + 0.05;
        return Math.max(v0_1, v4) / Math.min(v0_1, v4);
    }

    @FloatRange(from=0, to=1) public static double calculateLuminance(@ColorInt int arg5) {
        double[] v0 = ColorUtils.getTempDouble3Array();
        ColorUtils.colorToXYZ(arg5, v0);
        return v0[1] / 100;
    }

    public static int calculateMinimumAlpha(@ColorInt int arg8, @ColorInt int arg9, float arg10) {
        int v1 = 0xFF;
        if(Color.alpha(arg9) != v1) {
            StringBuilder v10 = new StringBuilder();
            v10.append("background can not be translucent: #");
            v10.append(Integer.toHexString(arg9));
            throw new IllegalArgumentException(v10.toString());
        }

        double v4 = ((double)arg10);
        if(ColorUtils.calculateContrast(ColorUtils.setAlphaComponent(arg8, v1), arg9) < v4) {
            return -1;
        }

        int v10_1 = 0;
        int v0 = 0;
        while(v10_1 <= 10) {
            if(v1 - v0 <= 1) {
                return v1;
            }

            int v2 = (v0 + v1) / 2;
            if(ColorUtils.calculateContrast(ColorUtils.setAlphaComponent(arg8, v2), arg9) < v4) {
                v0 = v2;
            }
            else {
                v1 = v2;
            }

            ++v10_1;
        }

        return v1;
    }

    @VisibleForTesting static float circularInterpolate(float arg2, float arg3, float arg4) {
        float v1 = 360f;
        if(Float.compare(Math.abs(arg3 - arg2), 180f) > 0) {
            if(arg3 > arg2) {
                arg2 += v1;
            }
            else {
                arg3 += v1;
            }
        }

        return (arg2 + (arg3 - arg2) * arg4) % v1;
    }

    public static void colorToHSL(@ColorInt int arg2, @NonNull float[] arg3) {
        ColorUtils.RGBToHSL(Color.red(arg2), Color.green(arg2), Color.blue(arg2), arg3);
    }

    public static void colorToLAB(@ColorInt int arg2, @NonNull double[] arg3) {
        ColorUtils.RGBToLAB(Color.red(arg2), Color.green(arg2), Color.blue(arg2), arg3);
    }

    public static void colorToXYZ(@ColorInt int arg2, @NonNull double[] arg3) {
        ColorUtils.RGBToXYZ(Color.red(arg2), Color.green(arg2), Color.blue(arg2), arg3);
    }

    private static int compositeAlpha(int arg0, int arg1) {
        return 0xFF - (0xFF - arg1) * (0xFF - arg0) / 0xFF;
    }

    public static int compositeColors(@ColorInt int arg6, @ColorInt int arg7) {
        int v0 = Color.alpha(arg7);
        int v1 = Color.alpha(arg6);
        int v2 = ColorUtils.compositeAlpha(v1, v0);
        return Color.argb(v2, ColorUtils.compositeComponent(Color.red(arg6), v1, Color.red(arg7), v0, v2), ColorUtils.compositeComponent(Color.green(arg6), v1, Color.green(arg7), v0, v2), ColorUtils.compositeComponent(Color.blue(arg6), v1, Color.blue(arg7), v0, v2));
    }

    private static int compositeComponent(int arg0, int arg1, int arg2, int arg3, int arg4) {
        if(arg4 == 0) {
            return 0;
        }

        return (arg0 * 0xFF * arg1 + arg2 * arg3 * (0xFF - arg1)) / (arg4 * 0xFF);
    }

    private static int constrain(int arg0, int arg1, int arg2) {
        if(arg0 < arg1) {
            arg0 = arg1;
        }
        else if(arg0 > arg2) {
            arg0 = arg2;
        }

        return arg0;
    }

    private static float constrain(float arg1, float arg2, float arg3) {
        if(arg1 < arg2) {
            arg1 = arg2;
        }
        else if(arg1 > arg3) {
            arg1 = arg3;
        }

        return arg1;
    }

    public static double distanceEuclidean(@NonNull double[] arg9, @NonNull double[] arg10) {
        return Math.sqrt(Math.pow(arg9[0] - arg10[0], 2) + Math.pow(arg9[1] - arg10[1], 2) + Math.pow(arg9[2] - arg10[2], 2));
    }

    private static double[] getTempDouble3Array() {
        double[] v0_1;
        Object v0 = ColorUtils.TEMP_ARRAY.get();
        if(v0 == null) {
            v0_1 = new double[3];
            ColorUtils.TEMP_ARRAY.set(v0_1);
        }

        return v0_1;
    }

    private static double pivotXyzComponent(double arg3) {
        return arg3 > 0.008856 ? Math.pow(arg3, 0.333333) : (arg3 * 903.3 + 16) / 116;
    }

    @ColorInt public static int setAlphaComponent(@ColorInt int arg1, @IntRange(from=0, to=0xFF) int arg2) {
        if(arg2 >= 0) {
            if(arg2 > 0xFF) {
            }
            else {
                return arg1 & 0xFFFFFF | arg2 << 24;
            }
        }

        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }
}

