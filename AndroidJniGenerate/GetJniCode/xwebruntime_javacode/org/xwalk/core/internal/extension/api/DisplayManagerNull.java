package org.xwalk.core.internal.extension.api;

import android.view.Display;

public class DisplayManagerNull extends XWalkDisplayManager {
    private static final Display[] NO_DISPLAYS;

    static {
        DisplayManagerNull.NO_DISPLAYS = new Display[0];
    }

    public DisplayManagerNull() {
        super();
    }

    public Display getDisplay(int arg1) {
        return null;
    }

    public Display[] getDisplays() {
        return DisplayManagerNull.NO_DISPLAYS;
    }

    public Display[] getDisplays(String arg1) {
        return DisplayManagerNull.NO_DISPLAYS;
    }

    public Display[] getPresentationDisplays() {
        return DisplayManagerNull.NO_DISPLAYS;
    }
}

