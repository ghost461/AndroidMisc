package org.chromium.ui.resources.system;

import android.content.res.Resources;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceLoader$ResourceLoaderCallback;
import org.chromium.ui.resources.async.AsyncPreloadResourceLoader$ResourceCreator;
import org.chromium.ui.resources.async.AsyncPreloadResourceLoader;
import org.chromium.ui.resources.statics.StaticResource;

public class SystemResourceLoader extends AsyncPreloadResourceLoader {
    private static final float COS_PI_OVER_6 = 0.866f;
    private static final float SIN_PI_OVER_6 = 0.5f;

    static {
    }

    public SystemResourceLoader(int arg2, ResourceLoaderCallback arg3, int arg4) {
        super(arg2, arg3, new ResourceCreator() {
            public Resource create(int arg2) {
                return SystemResourceLoader.access$000(this.val$minScreenSideLengthPx, arg2);
            }
        });
    }

    static Resource access$000(int arg0, int arg1) {
        return SystemResourceLoader.createResource(arg0, arg1);
    }

    private static Resource createOverscrollGlowLBitmap(int arg11) {
        float v11 = (((float)arg11)) * 0.5f / 0.5f;
        float v0 = 0.866f * v11;
        float v2 = -v11;
        float v4 = v2 / 2f;
        v2 -= v0;
        float v3 = 2f * v11;
        RectF v6 = new RectF(v4, v2, v4 + v3, v3 + v2);
        Paint v10 = new Paint();
        v10.setAntiAlias(true);
        v10.setAlpha(0xBB);
        v10.setStyle(Paint$Style.FILL);
        Bitmap v11_1 = Bitmap.createBitmap(((int)v11), ((int)(v11 - v0)), Bitmap$Config.ALPHA_8);
        new Canvas(v11_1).drawArc(v6, 45f, 90f, true, v10);
        return new StaticResource(v11_1);
    }

    private static Resource createResource(int arg2, int arg3) {
        int v0 = 0x80;
        switch(arg3) {
            case 0: {
                goto label_11;
            }
            case 1: {
                goto label_5;
            }
            case 2: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return SystemResourceLoader.createOverscrollGlowLBitmap(arg2);
    label_5:
        return StaticResource.create(Resources.getSystem(), SystemResourceLoader.getResourceId("android:drawable/overscroll_glow"), v0, 0x40);
    label_11:
        return StaticResource.create(Resources.getSystem(), SystemResourceLoader.getResourceId("android:drawable/overscroll_edge"), v0, 12);
    }

    private static int getResourceId(String arg2) {
        return Resources.getSystem().getIdentifier(arg2, null, null);
    }
}

