package android.support.v4.app;

import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
    public interface OnSharedElementsReadyListener {
        void onSharedElementsReady();
    }

    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    private static int MAX_IMAGE_SIZE = 0x100000;
    private Matrix mTempMatrix;

    static {
    }

    public SharedElementCallback() {
        super();
    }

    private static Bitmap createDrawableBitmap(Drawable arg9) {
        int v0 = arg9.getIntrinsicWidth();
        int v1 = arg9.getIntrinsicHeight();
        if(v0 > 0) {
            if(v1 <= 0) {
            }
            else {
                float v3 = 1f;
                float v2 = Math.min(v3, (((float)SharedElementCallback.MAX_IMAGE_SIZE)) / (((float)(v0 * v1))));
                if(((arg9 instanceof BitmapDrawable)) && v2 == v3) {
                    return ((BitmapDrawable)arg9).getBitmap();
                }

                v0 = ((int)((((float)v0)) * v2));
                v1 = ((int)((((float)v1)) * v2));
                Bitmap v2_1 = Bitmap.createBitmap(v0, v1, Bitmap$Config.ARGB_8888);
                Canvas v3_1 = new Canvas(v2_1);
                Rect v4 = arg9.getBounds();
                int v5 = v4.left;
                int v6 = v4.top;
                int v7 = v4.right;
                int v4_1 = v4.bottom;
                arg9.setBounds(0, 0, v0, v1);
                arg9.draw(v3_1);
                arg9.setBounds(v5, v6, v7, v4_1);
                return v2_1;
            }
        }

        return null;
    }

    public Parcelable onCaptureSharedElementSnapshot(View arg6, Matrix arg7, RectF arg8) {
        Bitmap v2_3;
        if((arg6 instanceof ImageView)) {
            View v0 = arg6;
            Drawable v1 = ((ImageView)v0).getDrawable();
            Drawable v2 = ((ImageView)v0).getBackground();
            if(v1 != null && v2 == null) {
                Bitmap v1_1 = SharedElementCallback.createDrawableBitmap(v1);
                if(v1_1 != null) {
                    Bundle v6 = new Bundle();
                    v6.putParcelable("sharedElement:snapshot:bitmap", ((Parcelable)v1_1));
                    v6.putString("sharedElement:snapshot:imageScaleType", ((ImageView)v0).getScaleType().toString());
                    if(((ImageView)v0).getScaleType() == ImageView$ScaleType.MATRIX) {
                        arg7 = ((ImageView)v0).getImageMatrix();
                        float[] v8 = new float[9];
                        arg7.getValues(v8);
                        v6.putFloatArray("sharedElement:snapshot:imageMatrix", v8);
                    }

                    return ((Parcelable)v6);
                }
            }
        }

        int v0_1 = Math.round(arg8.width());
        int v1_2 = Math.round(arg8.height());
        Parcelable v2_1 = null;
        if(v0_1 > 0 && v1_2 > 0) {
            float v2_2 = Math.min(1f, (((float)SharedElementCallback.MAX_IMAGE_SIZE)) / (((float)(v0_1 * v1_2))));
            v0_1 = ((int)((((float)v0_1)) * v2_2));
            v1_2 = ((int)((((float)v1_2)) * v2_2));
            if(this.mTempMatrix == null) {
                this.mTempMatrix = new Matrix();
            }

            this.mTempMatrix.set(arg7);
            this.mTempMatrix.postTranslate(-arg8.left, -arg8.top);
            this.mTempMatrix.postScale(v2_2, v2_2);
            v2_3 = Bitmap.createBitmap(v0_1, v1_2, Bitmap$Config.ARGB_8888);
            Canvas v7 = new Canvas(v2_3);
            v7.concat(this.mTempMatrix);
            arg6.draw(v7);
        }

        return ((Parcelable)v2_3);
    }

    public View onCreateSnapshotView(Context arg3, Parcelable arg4) {
        ImageView v1_1;
        View v1 = null;
        if((arg4 instanceof Bundle)) {
            Parcelable v0 = ((Bundle)arg4).getParcelable("sharedElement:snapshot:bitmap");
            if(v0 == null) {
                return v1;
            }
            else {
                v1_1 = new ImageView(arg3);
                v1_1.setImageBitmap(((Bitmap)v0));
                v1_1.setScaleType(ImageView$ScaleType.valueOf(((Bundle)arg4).getString("sharedElement:snapshot:imageScaleType")));
                if(v1_1.getScaleType() == ImageView$ScaleType.MATRIX) {
                    float[] v3 = ((Bundle)arg4).getFloatArray("sharedElement:snapshot:imageMatrix");
                    Matrix v4 = new Matrix();
                    v4.setValues(v3);
                    v1_1.setImageMatrix(v4);
                }
            }
        }
        else if((arg4 instanceof Bitmap)) {
            v1_1 = new ImageView(arg3);
            v1_1.setImageBitmap(((Bitmap)arg4));
        }

        return v1;
    }

    public void onMapSharedElements(List arg1, Map arg2) {
    }

    public void onRejectSharedElements(List arg1) {
    }

    public void onSharedElementEnd(List arg1, List arg2, List arg3) {
    }

    public void onSharedElementStart(List arg1, List arg2, List arg3) {
    }

    public void onSharedElementsArrived(List arg1, List arg2, OnSharedElementsReadyListener arg3) {
        arg3.onSharedElementsReady();
    }
}

