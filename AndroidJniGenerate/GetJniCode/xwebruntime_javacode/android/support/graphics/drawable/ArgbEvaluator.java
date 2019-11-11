package android.support.graphics.drawable;

import android.animation.TypeEvaluator;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance;

    static {
        ArgbEvaluator.sInstance = new ArgbEvaluator();
    }

    public ArgbEvaluator() {
        super();
    }

    public Object evaluate(float arg12, Object arg13, Object arg14) {
        int v13 = ((Integer)arg13).intValue();
        float v0 = (((float)(v13 >> 24 & 0xFF))) / 255f;
        int v14 = ((Integer)arg14).intValue();
        float v2 = ((float)Math.pow(((double)((((float)(v13 >> 16 & 0xFF))) / 255f)), 2.2));
        float v3 = ((float)Math.pow(((double)((((float)(v13 >> 8 & 0xFF))) / 255f)), 2.2));
        float v13_1 = ((float)Math.pow(((double)((((float)(v13 & 0xFF))) / 255f)), 2.2));
        return Integer.valueOf(Math.round((((float)Math.pow(((double)(v2 + ((((float)Math.pow(((double)((((float)(v14 >> 16 & 0xFF))) / 255f)), 2.2))) - v2) * arg12)), 0.454545))) * 255f) << 16 | Math.round((v0 + ((((float)(v14 >> 24 & 0xFF))) / 255f - v0) * arg12) * 255f) << 24 | Math.round((((float)Math.pow(((double)(v3 + ((((float)Math.pow(((double)((((float)(v14 >> 8 & 0xFF))) / 255f)), 2.2))) - v3) * arg12)), 0.454545))) * 255f) << 8 | Math.round((((float)Math.pow(((double)(v13_1 + arg12 * ((((float)Math.pow(((double)((((float)(v14 & 0xFF))) / 255f)), 2.2))) - v13_1))), 0.454545))) * 255f));
    }

    public static ArgbEvaluator getInstance() {
        return ArgbEvaluator.sInstance;
    }
}

