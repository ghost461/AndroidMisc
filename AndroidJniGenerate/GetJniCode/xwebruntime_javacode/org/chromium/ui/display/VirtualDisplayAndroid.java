package org.chromium.ui.display;

import android.graphics.Point;

public class VirtualDisplayAndroid extends DisplayAndroid {
    private float mAndroidUiScalingFactor;

    VirtualDisplayAndroid(int arg1) {
        super(arg1);
        this.mAndroidUiScalingFactor = 1f;
    }

    public static VirtualDisplayAndroid createVirtualDisplay() {
        return VirtualDisplayAndroid.getManager().addVirtualDisplay();
    }

    public void destroy() {
        VirtualDisplayAndroid.getManager().removeVirtualDisplay(this);
    }

    public float getAndroidUIScaling() {
        return this.mAndroidUiScalingFactor;
    }

    public void setTo(DisplayAndroid arg9) {
        this.update(new Point(arg9.getDisplayWidth(), arg9.getDisplayHeight()), Float.valueOf(arg9.getDipScale()), Integer.valueOf(arg9.getBitsPerPixel()), Integer.valueOf(arg9.getBitsPerComponent()), Integer.valueOf(arg9.getRotation()), Boolean.valueOf(arg9.mIsDisplayWideColorGamut), Boolean.valueOf(arg9.mIsDisplayServerWideColorGamut));
        this.mAndroidUiScalingFactor = arg9.getAndroidUIScaling();
    }

    public void update(Point arg9, Float arg10, Float arg11, Integer arg12, Integer arg13, Integer arg14, Boolean arg15, Boolean arg16) {
        super.update(arg9, arg10, arg12, arg13, arg14, arg15, arg16);
        if(arg11 != null) {
            this.mAndroidUiScalingFactor = arg11.floatValue();
        }
    }
}

