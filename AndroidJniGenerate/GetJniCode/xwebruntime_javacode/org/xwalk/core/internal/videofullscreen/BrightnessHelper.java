package org.xwalk.core.internal.videofullscreen;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$System;
import android.view.Window;
import android.view.WindowManager$LayoutParams;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class BrightnessHelper {
    private int maxBrightness;
    private ContentResolver resolver;

    public BrightnessHelper(Context arg2) {
        super();
        this.maxBrightness = 0xFF;
        this.resolver = arg2.getContentResolver();
    }

    private int adjustBrightnessNumber(int arg2) {
        int v0 = 0xFF;
        if(arg2 < 0) {
            arg2 = 0;
        }
        else if(arg2 > v0) {
            arg2 = 0xFF;
        }

        return arg2;
    }

    public int getBrightness() {
        return Settings$System.getInt(this.resolver, "screen_brightness", 0xFF);
    }

    public int getMaxBrightness() {
        return this.maxBrightness;
    }

    public void offAutoBrightness() {
        try {
            if(Settings$System.getInt(this.resolver, "screen_brightness_mode") != 1) {
                return;
            }

            Settings$System.putInt(this.resolver, "screen_brightness_mode", 0);
        }
        catch(Settings$SettingNotFoundException v0) {
            ThrowableExtension.printStackTrace(((Throwable)v0));
        }
    }

    public void setAppBrightness(float arg2, Activity arg3) {
        Window v3 = arg3.getWindow();
        WindowManager$LayoutParams v0 = v3.getAttributes();
        v0.screenBrightness = arg2;
        v3.setAttributes(v0);
    }

    public void setSystemBrightness(int arg3) {
        Settings$System.putInt(this.resolver, "screen_brightness", this.adjustBrightnessNumber(arg3));
    }
}

