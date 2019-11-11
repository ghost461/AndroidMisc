package org.chromium.ui.gfx;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="gfx") public class BitmapHelper {
    class org.chromium.ui.gfx.BitmapHelper$1 {
        static {
            org.chromium.ui.gfx.BitmapHelper$1.$SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap$Config.values().length];
            try {
                org.chromium.ui.gfx.BitmapHelper$1.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.ALPHA_8.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.chromium.ui.gfx.BitmapHelper$1.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.ARGB_4444.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.chromium.ui.gfx.BitmapHelper$1.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.ARGB_8888.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.chromium.ui.gfx.BitmapHelper$1.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.RGB_565.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public BitmapHelper() {
        super();
    }

    @CalledByNative private static Bitmap createBitmap(int arg0, int arg1, int arg2) {
        return Bitmap.createBitmap(arg0, arg1, BitmapHelper.getBitmapConfigForFormat(arg2));
    }

    private static Bitmap$Config getBitmapConfigForFormat(int arg1) {
        if(arg1 == 4) {
            goto label_9;
        }

        switch(arg1) {
            case 1: {
                goto label_7;
            }
            case 2: {
                goto label_5;
            }
        }

        return Bitmap$Config.ARGB_8888;
    label_5:
        return Bitmap$Config.ARGB_4444;
    label_7:
        return Bitmap$Config.ALPHA_8;
    label_9:
        return Bitmap$Config.RGB_565;
    }

    @CalledByNative private static int getBitmapFormatForConfig(Bitmap$Config arg1) {
        switch(org.chromium.ui.gfx.BitmapHelper$1.$SwitchMap$android$graphics$Bitmap$Config[arg1.ordinal()]) {
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 4;
            }
        }

        return 0;
    }

    @CalledByNative private static int getByteCount(Bitmap arg0) {
        return arg0.getByteCount();
    }
}

