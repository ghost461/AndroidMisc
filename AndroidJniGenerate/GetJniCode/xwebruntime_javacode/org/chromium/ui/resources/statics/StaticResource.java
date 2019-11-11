package org.chromium.ui.resources.statics;

import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceFactory;

public class StaticResource implements Resource {
    private final Bitmap mBitmap;
    private final Rect mBitmapSize;
    private final NinePatchData mNinePatchData;

    public StaticResource(Bitmap arg4) {
        super();
        this.mBitmap = arg4;
        this.mNinePatchData = NinePatchData.create(this.mBitmap);
        this.mBitmapSize = new Rect(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
    }

    public static StaticResource create(Resources arg2, int arg3, int arg4, int arg5) {
        StaticResource v0 = null;
        if(arg3 <= 0) {
            return v0;
        }

        Bitmap v1 = StaticResource.decodeBitmap(arg2, arg3, arg4, arg5);
        if(v1 == null) {
            v1 = StaticResource.decodeDrawable(arg2, arg3, arg4, arg5);
        }

        if(v1 == null) {
            return v0;
        }

        return new StaticResource(v1);
    }

    public long createNativeResource() {
        return ResourceFactory.createBitmapResource(this.mNinePatchData);
    }

    private static BitmapFactory$Options createOptions(Resources arg2, int arg3, int arg4, int arg5) {
        BitmapFactory$Options v0 = new BitmapFactory$Options();
        v0.inPreferredConfig = Bitmap$Config.ARGB_8888;
        if(arg4 != 0) {
            if(arg5 == 0) {
            }
            else {
                v0.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(arg2, arg3, v0);
                v0.inJustDecodeBounds = false;
                if(v0.outHeight <= arg5 && v0.outWidth <= arg4) {
                    return v0;
                }

                v0.inSampleSize = Math.min(Math.round((((float)v0.outHeight)) / (((float)arg5))), Math.round((((float)v0.outWidth)) / (((float)arg4))));
                return v0;
            }
        }

        return v0;
    }

    private static Bitmap decodeBitmap(Resources arg1, int arg2, int arg3, int arg4) {
        BitmapFactory$Options v3 = StaticResource.createOptions(arg1, arg2, arg3, arg4);
        v3.inPreferredConfig = Bitmap$Config.ARGB_8888;
        Bitmap v1 = BitmapFactory.decodeResource(arg1, arg2, v3);
        Bitmap v2 = null;
        if(v1 == null) {
            return v2;
        }

        if(v1.getConfig() == v3.inPreferredConfig) {
            return v1;
        }

        Bitmap v3_1 = Bitmap.createBitmap(v1.getWidth(), v1.getHeight(), v3.inPreferredConfig);
        new Canvas(v3_1).drawBitmap(v1, 0f, 0f, ((Paint)v2));
        v1.recycle();
        return v3_1;
    }

    private static Bitmap decodeDrawable(Resources arg2, int arg3, int arg4, int arg5) {
        try {
            Drawable v2 = ApiCompatibilityUtils.getDrawable(arg2, arg3);
            arg3 = Math.max(v2.getMinimumWidth(), Math.max(arg4, 1));
            arg4 = Math.max(v2.getMinimumHeight(), Math.max(arg5, 1));
            Bitmap v5 = Bitmap.createBitmap(arg3, arg4, Bitmap$Config.ARGB_8888);
            Canvas v0 = new Canvas(v5);
            v2.setBounds(0, 0, arg3, arg4);
            v2.draw(v0);
            return v5;
        }
        catch(Resources$NotFoundException ) {
            return null;
        }
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public Rect getBitmapSize() {
        return this.mBitmapSize;
    }

    public NinePatchData getNinePatchData() {
        return this.mNinePatchData;
    }
}

