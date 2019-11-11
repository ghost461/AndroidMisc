package android.support.v4.os;

import android.os.Build$VERSION;

public class BuildCompat {
    private BuildCompat() {
        super();
    }

    @Deprecated public static boolean isAtLeastN() {
        boolean v0 = Build$VERSION.SDK_INT >= 24 ? true : false;
        return v0;
    }

    @Deprecated public static boolean isAtLeastNMR1() {
        boolean v0 = Build$VERSION.SDK_INT >= 25 ? true : false;
        return v0;
    }

    public static boolean isAtLeastO() {
        boolean v0 = Build$VERSION.SDK_INT >= 26 ? true : false;
        return v0;
    }

    public static boolean isAtLeastOMR1() {
        boolean v0 = (Build$VERSION.CODENAME.startsWith("OMR")) || (BuildCompat.isAtLeastP()) ? true : false;
        return v0;
    }

    public static boolean isAtLeastP() {
        return Build$VERSION.CODENAME.equals("P");
    }
}

