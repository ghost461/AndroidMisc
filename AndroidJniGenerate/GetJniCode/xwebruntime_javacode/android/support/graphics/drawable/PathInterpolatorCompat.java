package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class PathInterpolatorCompat implements Interpolator {
    public static final double EPSILON = 0.00001;
    public static final int MAX_NUM_POINTS = 3000;
    private static final float PRECISION = 0.002f;
    private float[] mX;
    private float[] mY;

    public PathInterpolatorCompat(Context arg2, AttributeSet arg3, XmlPullParser arg4) {
        this(arg2.getResources(), arg2.getTheme(), arg3, arg4);
    }

    public PathInterpolatorCompat(Resources arg2, Resources$Theme arg3, AttributeSet arg4, XmlPullParser arg5) {
        super();
        TypedArray v2 = TypedArrayUtils.obtainAttributes(arg2, arg3, arg4, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
        this.parseInterpolatorFromTypeArray(v2, arg5);
        v2.recycle();
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

    private void initCubic(float arg9, float arg10, float arg11, float arg12) {
        Path v7 = new Path();
        v7.moveTo(0f, 0f);
        v7.cubicTo(arg9, arg10, arg11, arg12, 1f, 1f);
        this.initPath(v7);
    }

    private void initPath(Path arg11) {
        float v3;
        int v11_1;
        StringBuilder v1_1;
        int v1 = 0;
        PathMeasure v0 = new PathMeasure(arg11, false);
        float v11 = v0.getLength();
        int v2 = Math.min(3000, (((int)(v11 / 0.002f))) + 1);
        if(v2 <= 0) {
            v1_1 = new StringBuilder();
            v1_1.append("The Path has a invalid length ");
            v1_1.append(v11);
            throw new IllegalArgumentException(v1_1.toString());
        }

        this.mX = new float[v2];
        this.mY = new float[v2];
        float[] v4 = new float[2];
        int v5;
        for(v5 = 0; v5 < v2; ++v5) {
            v0.getPosTan((((float)v5)) * v11 / (((float)(v2 - 1))), v4, null);
            this.mX[v5] = v4[0];
            this.mY[v5] = v4[1];
        }

        double v6 = 0.00001;
        if((((double)Math.abs(this.mX[0]))) <= v6 && (((double)Math.abs(this.mY[0]))) <= v6) {
            int v4_1 = v2 - 1;
            float v5_1 = 1f;
            if((((double)Math.abs(this.mX[v4_1] - v5_1))) <= v6) {
                if((((double)Math.abs(this.mY[v4_1] - v5_1))) > v6) {
                }
                else {
                    v11_1 = 0;
                    v3 = 0f;
                    goto label_73;
                }
            }
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("The Path must start at (0,0) and end at (1,1) start: ");
        v0_1.append(this.mX[0]);
        v0_1.append(",");
        v0_1.append(this.mY[0]);
        v0_1.append(" end:");
        --v2;
        v0_1.append(this.mX[v2]);
        v0_1.append(",");
        v0_1.append(this.mY[v2]);
        throw new IllegalArgumentException(v0_1.toString());
    label_73:
        while(v1 < v2) {
            v5 = v11_1 + 1;
            v11 = this.mX[v11_1];
            if(v11 < v3) {
                v1_1 = new StringBuilder();
                v1_1.append("The Path cannot loop back on itself, x :");
                v1_1.append(v11);
                throw new IllegalArgumentException(v1_1.toString());
            }

            this.mX[v1] = v11;
            ++v1;
            v3 = v11;
            v11_1 = v5;
        }

        if(v0.nextContour()) {
            throw new IllegalArgumentException("The Path should be continuous, can\'t have 2+ contours");
        }
    }

    private void initQuad(float arg3, float arg4) {
        Path v0 = new Path();
        v0.moveTo(0f, 0f);
        v0.quadTo(arg3, arg4, 1f, 1f);
        this.initPath(v0);
    }

    private void parseInterpolatorFromTypeArray(TypedArray arg7, XmlPullParser arg8) {
        if(TypedArrayUtils.hasAttribute(arg8, "pathData")) {
            String v7 = TypedArrayUtils.getNamedString(arg7, arg8, "pathData", 4);
            Path v8 = PathParser.createPathFromPathData(v7);
            if(v8 == null) {
                StringBuilder v0 = new StringBuilder();
                v0.append("The path is null, which is created from ");
                v0.append(v7);
                throw new InflateException(v0.toString());
            }
            else {
                this.initPath(v8);
            }
        }
        else if(!TypedArrayUtils.hasAttribute(arg8, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        }
        else if(!TypedArrayUtils.hasAttribute(arg8, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        else {
            float v0_1 = TypedArrayUtils.getNamedFloat(arg7, arg8, "controlX1", 0, 0f);
            float v1 = TypedArrayUtils.getNamedFloat(arg7, arg8, "controlY1", 1, 0f);
            boolean v3 = TypedArrayUtils.hasAttribute(arg8, "controlX2");
            if(v3 != TypedArrayUtils.hasAttribute(arg8, "controlY2")) {
                throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
            }
            else if(!v3) {
                this.initQuad(v0_1, v1);
            }
            else {
                this.initCubic(v0_1, v1, TypedArrayUtils.getNamedFloat(arg7, arg8, "controlX2", 2, 0f), TypedArrayUtils.getNamedFloat(arg7, arg8, "controlY2", 3, 0f));
            }
        }
    }
}

