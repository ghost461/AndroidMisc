package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.widget.ProgressBar;

class AppCompatProgressBarHelper {
    private static final int[] TINT_ATTRS;
    private Bitmap mSampleTile;
    private final ProgressBar mView;

    static {
        AppCompatProgressBarHelper.TINT_ATTRS = new int[]{0x101013B, 0x101013C};
    }

    AppCompatProgressBarHelper(ProgressBar arg1) {
        super();
        this.mView = arg1;
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f}, null, null);
    }

    Bitmap getSampleTime() {
        return this.mSampleTile;
    }

    void loadFromAttributes(AttributeSet arg4, int arg5) {
        TintTypedArray v4 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), arg4, AppCompatProgressBarHelper.TINT_ATTRS, arg5, 0);
        Drawable v5 = v4.getDrawableIfKnown(0);
        if(v5 != null) {
            this.mView.setIndeterminateDrawable(this.tileifyIndeterminate(v5));
        }

        v5 = v4.getDrawableIfKnown(1);
        if(v5 != null) {
            this.mView.setProgressDrawable(this.tileify(v5, false));
        }

        v4.recycle();
    }

    private Drawable tileify(Drawable arg8, boolean arg9) {
        ShapeDrawable v8_1;
        if((arg8 instanceof DrawableWrapper)) {
            Drawable v0 = arg8;
            Drawable v1 = ((DrawableWrapper)v0).getWrappedDrawable();
            if(v1 != null) {
                ((DrawableWrapper)v0).setWrappedDrawable(this.tileify(v1, arg9));
            }
        }
        else if((arg8 instanceof LayerDrawable)) {
            int v9 = ((LayerDrawable)arg8).getNumberOfLayers();
            Drawable[] v0_1 = new Drawable[v9];
            int v2 = 0;
            int v3;
            for(v3 = 0; v3 < v9; ++v3) {
                int v4 = ((LayerDrawable)arg8).getId(v3);
                Drawable v5 = ((LayerDrawable)arg8).getDrawable(v3);
                boolean v4_1 = v4 == 0x102000D || v4 == 0x102000F ? true : false;
                v0_1[v3] = this.tileify(v5, v4_1);
            }

            LayerDrawable v1_1 = new LayerDrawable(v0_1);
            while(v2 < v9) {
                v1_1.setId(v2, ((LayerDrawable)arg8).getId(v2));
                ++v2;
            }

            return ((Drawable)v1_1);
        }
        else {
            if(!(arg8 instanceof BitmapDrawable)) {
                return arg8;
            }

            Bitmap v0_2 = ((BitmapDrawable)arg8).getBitmap();
            if(this.mSampleTile == null) {
                this.mSampleTile = v0_2;
            }

            ShapeDrawable v2_1 = new ShapeDrawable(this.getDrawableShape());
            v2_1.getPaint().setShader(new BitmapShader(v0_2, Shader$TileMode.REPEAT, Shader$TileMode.CLAMP));
            v2_1.getPaint().setColorFilter(((BitmapDrawable)arg8).getPaint().getColorFilter());
            if(arg9) {
                ClipDrawable v8 = new ClipDrawable(((Drawable)v2_1), 3, 1);
            }
            else {
                v8_1 = v2_1;
            }

            return ((Drawable)v8_1);
        }

        return arg8;
    }

    private Drawable tileifyIndeterminate(Drawable arg7) {
        AnimationDrawable v7;
        int v3;
        if((arg7 instanceof AnimationDrawable)) {
            int v0 = ((AnimationDrawable)arg7).getNumberOfFrames();
            AnimationDrawable v1 = new AnimationDrawable();
            v1.setOneShot(((AnimationDrawable)arg7).isOneShot());
            int v2;
            for(v2 = 0; true; ++v2) {
                v3 = 10000;
                if(v2 >= v0) {
                    break;
                }

                Drawable v4 = this.tileify(((AnimationDrawable)arg7).getFrame(v2), true);
                v4.setLevel(v3);
                v1.addFrame(v4, ((AnimationDrawable)arg7).getDuration(v2));
            }

            v1.setLevel(v3);
            v7 = v1;
        }

        return ((Drawable)v7);
    }
}

