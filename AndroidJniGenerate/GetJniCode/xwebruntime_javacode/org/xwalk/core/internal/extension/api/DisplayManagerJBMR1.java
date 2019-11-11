package org.xwalk.core.internal.extension.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.display.DisplayManager$DisplayListener;
import android.hardware.display.DisplayManager;
import android.view.Display;

@SuppressLint(value={"NewApi"}) public class DisplayManagerJBMR1 extends XWalkDisplayManager implements DisplayManager$DisplayListener {
    private DisplayManager mDisplayManager;

    public DisplayManagerJBMR1(Context arg2) {
        super();
        this.mDisplayManager = arg2.getSystemService("display");
    }

    public Display getDisplay(int arg2) {
        return this.mDisplayManager.getDisplay(arg2);
    }

    public Display[] getDisplays() {
        return this.mDisplayManager.getDisplays();
    }

    public Display[] getDisplays(String arg2) {
        return this.mDisplayManager.getDisplays(arg2);
    }

    public Display[] getPresentationDisplays() {
        return this.mDisplayManager.getDisplays("android.hardware.display.category.PRESENTATION");
    }

    public void onDisplayAdded(int arg1) {
        this.notifyDisplayAdded(arg1);
    }

    public void onDisplayChanged(int arg1) {
        this.notifyDisplayChanged(arg1);
    }

    public void onDisplayRemoved(int arg1) {
        this.notifyDisplayRemoved(arg1);
    }

    public void registerDisplayListener(DisplayListener arg2) {
        super.registerDisplayListener(arg2);
        if(this.mListeners.size() == 1) {
            this.mDisplayManager.registerDisplayListener(((DisplayManager$DisplayListener)this), null);
        }
    }

    public void unregisterDisplayListener(DisplayListener arg1) {
        super.unregisterDisplayListener(arg1);
        if(this.mListeners.size() == 0) {
            this.mDisplayManager.unregisterDisplayListener(((DisplayManager$DisplayListener)this));
        }
    }
}

