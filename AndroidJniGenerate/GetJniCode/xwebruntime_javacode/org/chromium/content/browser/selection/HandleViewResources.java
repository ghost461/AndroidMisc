package org.chromium.content.browser.selection;

import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public class HandleViewResources {
    private static final int[] CENTER_HANDLE_ATTRS = null;
    private static final float HANDLE_HORIZONTAL_PADDING_RATIO = 0.25f;
    private static final int[] LEFT_HANDLE_ATTRS;
    private static final int[] RIGHT_HANDLE_ATTRS;

    static {
        HandleViewResources.LEFT_HANDLE_ATTRS = new int[]{0x10102C5};
        HandleViewResources.CENTER_HANDLE_ATTRS = new int[]{0x10102C7};
        HandleViewResources.RIGHT_HANDLE_ATTRS = new int[]{0x10102C6};
    }

    public HandleViewResources() {
        super();
    }

    @CalledByNative private static Bitmap getCenterHandleBitmap(Context arg1) {
        return HandleViewResources.getHandleBitmap(arg1, HandleViewResources.CENTER_HANDLE_ATTRS);
    }

    public static Drawable getCenterHandleDrawable(Context arg1) {
        return HandleViewResources.getHandleDrawable(arg1, HandleViewResources.CENTER_HANDLE_ATTRS);
    }

    private static Bitmap getHandleBitmap(Context arg6, int[] arg7) {
        if(arg6 == null) {
            arg6 = ContextUtils.getApplicationContext();
        }

        TypedArray v0 = arg6.getTheme().obtainStyledAttributes(arg7);
        int v2 = v0.getResourceId(v0.getIndex(0), 0);
        Resources v3 = v0.getResources();
        v0.recycle();
        Bitmap$Config v0_1 = Bitmap$Config.ARGB_8888;
        BitmapFactory$Options v4 = new BitmapFactory$Options();
        v4.inJustDecodeBounds = false;
        v4.inPreferredConfig = v0_1;
        Bitmap v5 = BitmapFactory.decodeResource(v3, v2, v4);
        if(v5 != null) {
            return v5;
        }

        if(v3 != arg6.getResources()) {
            Bitmap v2_1 = BitmapFactory.decodeResource(arg6.getResources(), v2, v4);
            if(v2_1 != null) {
                return v2_1;
            }
        }

        Drawable v6 = HandleViewResources.getHandleDrawable(arg6, arg7);
        int v7 = v6.getIntrinsicWidth();
        v2 = v6.getIntrinsicHeight();
        Bitmap v0_2 = Bitmap.createBitmap(v7, v2, v0_1);
        Canvas v3_1 = new Canvas(v0_2);
        v6.setBounds(0, 0, v7, v2);
        v6.draw(v3_1);
        return v0_2;
    }

    private static Drawable getHandleDrawable(Context arg2, int[] arg3) {
        Drawable v2;
        TypedArray v3 = arg2.getTheme().obtainStyledAttributes(arg3);
        Drawable v1 = v3.getDrawable(0);
        if(v1 == null) {
            try {
                v2 = ApiCompatibilityUtils.getDrawable(arg2.getResources(), v3.getResourceId(0, 0));
            }
            catch(Resources$NotFoundException ) {
            label_9:
                v2 = v1;
            }
        }
        else {
            goto label_9;
        }

        v3.recycle();
        return v2;
    }

    @CalledByNative public static float getHandleHorizontalPaddingRatio() {
        return 0.25f;
    }

    @CalledByNative private static Bitmap getLeftHandleBitmap(Context arg1) {
        return HandleViewResources.getHandleBitmap(arg1, HandleViewResources.LEFT_HANDLE_ATTRS);
    }

    public static Drawable getLeftHandleDrawable(Context arg1) {
        return HandleViewResources.getHandleDrawable(arg1, HandleViewResources.LEFT_HANDLE_ATTRS);
    }

    @CalledByNative private static Bitmap getRightHandleBitmap(Context arg1) {
        return HandleViewResources.getHandleBitmap(arg1, HandleViewResources.RIGHT_HANDLE_ATTRS);
    }

    public static Drawable getRightHandleDrawable(Context arg1) {
        return HandleViewResources.getHandleDrawable(arg1, HandleViewResources.RIGHT_HANDLE_ATTRS);
    }
}

