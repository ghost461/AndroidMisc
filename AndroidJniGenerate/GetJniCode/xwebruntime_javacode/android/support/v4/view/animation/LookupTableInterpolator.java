package android.support.v4.view.animation;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize;
    private final float[] mValues;

    public LookupTableInterpolator(float[] arg2) {
        super();
        this.mValues = arg2;
        this.mStepSize = 1f / (((float)(this.mValues.length - 1)));
    }

    public float getInterpolation(float arg5) {
        float v0 = 1f;
        if(arg5 >= v0) {
            return v0;
        }

        if(arg5 <= 0f) {
            return 0;
        }

        int v0_1 = Math.min(((int)((((float)(this.mValues.length - 1))) * arg5)), this.mValues.length - 2);
        return this.mValues[v0_1] + (arg5 - (((float)v0_1)) * this.mStepSize) / this.mStepSize * (this.mValues[v0_1 + 1] - this.mValues[v0_1]);
    }
}

