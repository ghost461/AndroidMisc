package org.chromium.ui.resources;

import android.graphics.Rect;
import android.graphics.RectF;
import org.chromium.ui.resources.statics.NinePatchData;

public class LayoutResource {
    private final RectF mAperture;
    private final RectF mBitmapSize;
    private final RectF mPadding;

    public LayoutResource(float arg7, Resource arg8) {
        super();
        Rect v0 = new Rect();
        Rect v1 = new Rect();
        NinePatchData v2 = arg8.getNinePatchData();
        if(v2 != null) {
            v0 = v2.getPadding();
            v1 = v2.getAperture();
        }

        Rect v8 = arg8.getBitmapSize();
        this.mPadding = new RectF((((float)v0.left)) * arg7, (((float)v0.top)) * arg7, (((float)v0.right)) * arg7, (((float)v0.bottom)) * arg7);
        this.mBitmapSize = new RectF((((float)v8.left)) * arg7, (((float)v8.top)) * arg7, (((float)v8.right)) * arg7, (((float)v8.bottom)) * arg7);
        this.mAperture = new RectF((((float)v1.left)) * arg7, (((float)v1.top)) * arg7, (((float)v1.right)) * arg7, (((float)v1.bottom)) * arg7);
    }

    public RectF getAperture() {
        return this.mAperture;
    }

    public RectF getBitmapSize() {
        return this.mBitmapSize;
    }

    public RectF getPadding() {
        return this.mPadding;
    }
}

