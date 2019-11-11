package org.chromium.ui.display;

public abstract class DisplayUtil {
    public DisplayUtil() {
        super();
    }

    public static int dpToPx(DisplayAndroid arg0, int arg1) {
        return ((int)((((float)arg1)) * arg0.getDipScale() + 0.5f));
    }

    public static int getSmallestWidth(DisplayAndroid arg1) {
        int v0 = arg1.getDisplayWidth();
        int v1 = arg1.getDisplayHeight();
        if(v0 < v1) {
            v1 = v0;
        }

        return v1;
    }

    public static int pxToDp(DisplayAndroid arg0, int arg1) {
        return ((int)((((float)arg1)) / arg0.getDipScale() + 0.5f));
    }
}

