package org.chromium.ui.gfx;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build$VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="gfx") public class DeviceDisplayInfo {
    private final Context mAppContext;
    private DisplayMetrics mTempMetrics;
    private Point mTempPoint;
    private final WindowManager mWinManager;

    static {
    }

    private DeviceDisplayInfo(Context arg2) {
        super();
        this.mTempPoint = new Point();
        this.mTempMetrics = new DisplayMetrics();
        this.mAppContext = arg2.getApplicationContext();
        this.mWinManager = this.mAppContext.getSystemService("window");
    }

    @CalledByNative public static DeviceDisplayInfo create(Context arg1) {
        return new DeviceDisplayInfo(arg1);
    }

    @CalledByNative public int getBitsPerComponent() {
        int v1 = 5;
        int v2 = 8;
        switch(this.getPixelFormat()) {
            case 1: 
            case 2: 
            case 3: {
                return v2;
            }
            case 4: {
                return v1;
            }
            case 6: {
                return v1;
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

        return v2;
    }

    @CalledByNative public int getBitsPerPixel() {
        int v0 = this.getPixelFormat();
        PixelFormat v1 = new PixelFormat();
        PixelFormat.getPixelFormatInfo(v0, v1);
        return v1.bitsPerPixel;
    }

    @CalledByNative public double getDIPScale() {
        this.getDisplay().getMetrics(this.mTempMetrics);
        return ((double)this.mTempMetrics.density);
    }

    private Display getDisplay() {
        return this.mWinManager.getDefaultDisplay();
    }

    @CalledByNative public int getDisplayHeight() {
        this.getDisplay().getSize(this.mTempPoint);
        return this.mTempPoint.y;
    }

    @CalledByNative public int getDisplayWidth() {
        this.getDisplay().getSize(this.mTempPoint);
        return this.mTempPoint.x;
    }

    @TargetApi(value=17) @CalledByNative public int getPhysicalDisplayHeight() {
        if(Build$VERSION.SDK_INT < 17) {
            return 0;
        }

        this.getDisplay().getRealSize(this.mTempPoint);
        return this.mTempPoint.y;
    }

    @TargetApi(value=17) @CalledByNative public int getPhysicalDisplayWidth() {
        if(Build$VERSION.SDK_INT < 17) {
            return 0;
        }

        this.getDisplay().getRealSize(this.mTempPoint);
        return this.mTempPoint.x;
    }

    private int getPixelFormat() {
        if(Build$VERSION.SDK_INT < 17) {
            return this.getDisplay().getPixelFormat();
        }

        return 1;
    }

    @CalledByNative public int getRotationDegrees() {
        switch(this.getDisplay().getRotation()) {
            case 0: {
                return 0;
            }
            case 1: {
                return 90;
            }
            case 2: {
                return 180;
            }
            case 3: {
                return 270;
            }
        }

        return 0;
    }

    @CalledByNative private int getSmallestDIPWidth() {
        return this.mAppContext.getResources().getConfiguration().smallestScreenWidthDp;
    }

    private native void nativeUpdateSharedDeviceDisplayInfo(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, double arg7, int arg8, int arg9) {
    }

    public void updateNativeSharedDisplayInfo() {
        this.nativeUpdateSharedDeviceDisplayInfo(this.getDisplayHeight(), this.getDisplayWidth(), this.getPhysicalDisplayHeight(), this.getPhysicalDisplayWidth(), this.getBitsPerPixel(), this.getBitsPerComponent(), this.getDIPScale(), this.getSmallestDIPWidth(), this.getRotationDegrees());
    }
}

