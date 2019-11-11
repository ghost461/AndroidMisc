package org.chromium.ui.display;

import android.annotation.TargetApi;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build$VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import org.chromium.base.CommandLine;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;

class PhysicalDisplayAndroid extends DisplayAndroid {
    private static final String TAG = "DisplayAndroid";
    private static Float sForcedDIPScale = null;
    public static boolean sUsingCustomContext = false;

    static {
    }

    PhysicalDisplayAndroid(Display arg1) {
        super(arg1.getDisplayId());
    }

    private static int bitsPerComponent(int arg2) {
        int v0 = 5;
        int v1 = 8;
        switch(arg2) {
            case 1: 
            case 2: 
            case 3: {
                return v1;
            }
            case 4: {
                return v0;
            }
            case 6: {
                return v0;
            }
            case 7: {
                return 4;
            }
            case 8: 
            case 9: 
            case 10: {
                return 0;
            }
            case 11: {
                return 2;
            }
        }

        return v1;
    }

    private static int bitsPerPixel(int arg4) {
        int v0 = 24;
        if(arg4 == 1) {
            return v0;
        }

        PixelFormat v2 = new PixelFormat();
        PixelFormat.getPixelFormatInfo(arg4, v2);
        if(!PixelFormat.formatHasAlpha(arg4)) {
            return v2.bitsPerPixel;
        }

        if(arg4 != 1) {
            if(arg4 != 43) {
                switch(arg4) {
                    case 6: {
                        return 15;
                    }
                    case 7: {
                        return 12;
                    }
                }

                return v0;
            }
            else {
                return 30;
            }
        }

        return v0;
    }

    private static boolean hasForcedDIPScale() {
        boolean v1 = true;
        if(PhysicalDisplayAndroid.sForcedDIPScale == null) {
            String v0 = CommandLine.getInstance().getSwitchValue("force-device-scale-factor");
            if(v0 != null) {
                try {
                    PhysicalDisplayAndroid.sForcedDIPScale = Float.valueOf(v0);
                    if(PhysicalDisplayAndroid.sForcedDIPScale.floatValue() <= 0f) {
                    }
                    else {
                        goto label_41;
                    }
                }
                catch(NumberFormatException ) {
                }

                int v4_1 = 1;
                goto label_44;
            label_41:
                v4_1 = 0;
            label_44:
                if(v4_1 == 0) {
                    goto label_58;
                }

                Log.w("DisplayAndroid", "Ignoring invalid forced DIP scale \'" + v0 + "\'", new Object[0]);
                PhysicalDisplayAndroid.sForcedDIPScale = Float.valueOf(0f);
            }
            else if(PhysicalDisplayAndroid.sUsingCustomContext) {
                PhysicalDisplayAndroid.sForcedDIPScale = Float.valueOf(ContextUtils.getApplicationContext().getResources().getDisplayMetrics().density);
                Log.i("DisplayAndroid", "usingCustomContext true density:" + PhysicalDisplayAndroid.sForcedDIPScale, new Object[0]);
            }
            else {
                PhysicalDisplayAndroid.sForcedDIPScale = Float.valueOf(0f);
                Log.i("DisplayAndroid", "usingCustomContext false", new Object[0]);
            }
        }

    label_58:
        if(PhysicalDisplayAndroid.sForcedDIPScale.floatValue() > 0f) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    @TargetApi(value=17) void updateFromDisplay(Display arg10) {
        Point v1 = new Point();
        DisplayMetrics v0 = new DisplayMetrics();
        int v3 = 17;
        if(Build$VERSION.SDK_INT >= v3) {
            arg10.getRealSize(v1);
            arg10.getRealMetrics(v0);
        }
        else {
            arg10.getSize(v1);
            arg10.getMetrics(v0);
        }

        if(PhysicalDisplayAndroid.hasForcedDIPScale()) {
            v0.density = PhysicalDisplayAndroid.sForcedDIPScale.floatValue();
        }

        boolean v2 = false;
        if(Build$VERSION.SDK_INT >= 26) {
            v2 = arg10.isWideColorGamut();
        }

        v3 = Build$VERSION.SDK_INT < v3 ? arg10.getPixelFormat() : 1;
        super.update(v1, Float.valueOf(v0.density), Integer.valueOf(PhysicalDisplayAndroid.bitsPerPixel(v3)), Integer.valueOf(PhysicalDisplayAndroid.bitsPerComponent(v3)), Integer.valueOf(arg10.getRotation()), Boolean.valueOf(v2), null);
    }
}

