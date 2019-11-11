package android.support.v4.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

class PathInterpolatorApi14 implements Interpolator {
    private static final float PRECISION = 0.002f;
    private final float[] mX;
    private final float[] mY;

    PathInterpolatorApi14(float arg1, float arg2) {
        this(PathInterpolatorApi14.createQuad(arg1, arg2));
    }

    PathInterpolatorApi14(Path arg9) {
        super();
        PathMeasure v0 = new PathMeasure(arg9, false);
        float v9 = v0.getLength();
        int v2 = (((int)(v9 / 0.002f))) + 1;
        this.mX = new float[v2];
        this.mY = new float[v2];
        float[] v4 = new float[2];
        int v5;
        for(v5 = 0; v5 < v2; ++v5) {
            v0.getPosTan((((float)v5)) * v9 / (((float)(v2 - 1))), v4, null);
            this.mX[v5] = v4[0];
            this.mY[v5] = v4[1];
        }
    }

    PathInterpolatorApi14(float arg1, float arg2, float arg3, float arg4) {
        this(PathInterpolatorApi14.createCubic(arg1, arg2, arg3, arg4));
    }

    private static Path createCubic(float arg8, float arg9, float arg10, float arg11) {
        Path v7 = new Path();
        v7.moveTo(0f, 0f);
        v7.cubicTo(arg8, arg9, arg10, arg11, 1f, 1f);
        return v7;
    }

    private static Path createQuad(float arg2, float arg3) {
        Path v0 = new Path();
        v0.moveTo(0f, 0f);
        v0.quadTo(arg2, arg3, 1f, 1f);
        return v0;
    }

    public float getInterpolation(float arg7) {
        if(arg7 <= 0f) {
            return 0;
        }

        float v1 = 1f;
        if(arg7 >= v1) {
            return v1;
        }

        int v1_1 = 0;
        int v2 = this.mX.length - 1;
        while(v2 - v1_1 > 1) {
            int v4 = (v1_1 + v2) / 2;
            if(arg7 < this.mX[v4]) {
                v2 = v4;
                continue;
            }

            v1_1 = v4;
        }

        float v3 = this.mX[v2] - this.mX[v1_1];
        if(v3 == 0f) {
            return this.mY[v1_1];
        }

        return this.mY[v1_1] + (arg7 - this.mX[v1_1]) / v3 * (this.mY[v2] - this.mY[v1_1]);
    }
}

