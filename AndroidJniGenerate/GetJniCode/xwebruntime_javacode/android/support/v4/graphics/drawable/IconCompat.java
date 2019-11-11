package android.support.v4.graphics.drawable;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent$ShortcutIconResource;
import android.content.Intent;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader$TileMode;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;

public class IconCompat {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010417f;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.666667f;
    private static final float ICON_DIAMETER_FACTOR = 0.916667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833f;
    private static final int TYPE_ADAPTIVE_BITMAP = 5;
    private static final int TYPE_BITMAP = 1;
    private static final int TYPE_DATA = 3;
    private static final int TYPE_RESOURCE = 2;
    private static final int TYPE_URI = 4;
    private int mInt1;
    private int mInt2;
    private Object mObj1;
    private final int mType;

    private IconCompat(int arg1) {
        super();
        this.mType = arg1;
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public void addToShortcutIntent(Intent arg4) {
        int v0 = this.mType;
        if(v0 != 5) {
            switch(v0) {
                case 1: {
                    goto label_14;
                }
                case 2: {
                    goto label_8;
                }
            }

            throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
        label_8:
            arg4.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent$ShortcutIconResource.fromContext(this.mObj1, this.mInt1));
            return;
        label_14:
            arg4.putExtra("android.intent.extra.shortcut.ICON", this.mObj1);
        }
        else {
            arg4.putExtra("android.intent.extra.shortcut.ICON", IconCompat.createLegacyIconFromAdaptiveIcon(this.mObj1));
        }
    }

    @VisibleForTesting static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap arg10) {
        int v0 = ((int)((((float)Math.min(arg10.getWidth(), arg10.getHeight()))) * 0.666667f));
        Bitmap v1 = Bitmap.createBitmap(v0, v0, Bitmap$Config.ARGB_8888);
        Canvas v2 = new Canvas(v1);
        Paint v3 = new Paint(3);
        float v4 = ((float)v0);
        float v5 = 0.5f * v4;
        float v6 = 0.916667f * v5;
        float v7 = 0.010417f * v4;
        v3.setColor(0);
        v3.setShadowLayer(v7, 0f, v4 * 0.020833f, 0x3D000000);
        v2.drawCircle(v5, v5, v6, v3);
        v3.setShadowLayer(v7, 0f, 0f, 0x1E000000);
        v2.drawCircle(v5, v5, v6, v3);
        v3.clearShadowLayer();
        v3.setColor(0xFF000000);
        BitmapShader v4_1 = new BitmapShader(arg10, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
        Matrix v7_1 = new Matrix();
        v7_1.setTranslate(((float)(-(arg10.getWidth() - v0) / 2)), ((float)(-(arg10.getHeight() - v0) / 2)));
        v4_1.setLocalMatrix(v7_1);
        v3.setShader(((Shader)v4_1));
        v2.drawCircle(v5, v5, v6, v3);
        v2.setBitmap(null);
        return v1;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }

        IconCompat v0 = new IconCompat(5);
        v0.mObj1 = arg2;
        return v0;
    }

    public static IconCompat createWithBitmap(Bitmap arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }

        IconCompat v0 = new IconCompat(1);
        v0.mObj1 = arg2;
        return v0;
    }

    public static IconCompat createWithContentUri(Uri arg1) {
        if(arg1 == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }

        return IconCompat.createWithContentUri(arg1.toString());
    }

    public static IconCompat createWithContentUri(String arg2) {
        if(arg2 == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }

        IconCompat v0 = new IconCompat(4);
        v0.mObj1 = arg2;
        return v0;
    }

    public static IconCompat createWithData(byte[] arg2, int arg3, int arg4) {
        if(arg2 == null) {
            throw new IllegalArgumentException("Data must not be null.");
        }

        IconCompat v0 = new IconCompat(3);
        v0.mObj1 = arg2;
        v0.mInt1 = arg3;
        v0.mInt2 = arg4;
        return v0;
    }

    public static IconCompat createWithResource(Context arg2, @DrawableRes int arg3) {
        if(arg2 == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }

        IconCompat v0 = new IconCompat(2);
        v0.mInt1 = arg3;
        v0.mObj1 = arg2;
        return v0;
    }

    @TargetApi(value=26) @RestrictTo(value={Scope.LIBRARY_GROUP}) public Icon toIcon() {
        switch(this.mType) {
            case 1: {
                goto label_28;
            }
            case 2: {
                goto label_24;
            }
            case 3: {
                goto label_19;
            }
            case 4: {
                goto label_16;
            }
            case 5: {
                goto label_6;
            }
        }

        throw new IllegalArgumentException("Unknown type");
    label_19:
        return Icon.createWithData(this.mObj1, this.mInt1, this.mInt2);
    label_6:
        if(Build$VERSION.SDK_INT >= 26) {
            return Icon.createWithAdaptiveBitmap(this.mObj1);
        }

        return Icon.createWithBitmap(IconCompat.createLegacyIconFromAdaptiveIcon(this.mObj1));
    label_24:
        return Icon.createWithResource(this.mObj1, this.mInt1);
    label_28:
        return Icon.createWithBitmap(this.mObj1);
    label_16:
        return Icon.createWithContentUri(this.mObj1);
    }
}

