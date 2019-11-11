package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat {
    class DisplayManagerCompatApi14Impl extends DisplayManagerCompat {
        private final WindowManager mWindowManager;

        DisplayManagerCompatApi14Impl(Context arg2) {
            super();
            this.mWindowManager = arg2.getSystemService("window");
        }

        public Display getDisplay(int arg3) {
            Display v0 = this.mWindowManager.getDefaultDisplay();
            if(v0.getDisplayId() == arg3) {
                return v0;
            }

            return null;
        }

        public Display[] getDisplays() {
            return new Display[]{this.mWindowManager.getDefaultDisplay()};
        }

        public Display[] getDisplays(String arg1) {
            Display[] v1 = arg1 == null ? this.getDisplays() : new Display[0];
            return v1;
        }
    }

    @RequiresApi(value=17) class DisplayManagerCompatApi17Impl extends DisplayManagerCompat {
        private final DisplayManager mDisplayManager;

        DisplayManagerCompatApi17Impl(Context arg2) {
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
    }

    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap sInstances;

    static {
        DisplayManagerCompat.sInstances = new WeakHashMap();
    }

    DisplayManagerCompat() {
        super();
    }

    public abstract Display getDisplay(int arg1);

    public abstract Display[] getDisplays();

    public abstract Display[] getDisplays(String arg1);

    public static DisplayManagerCompat getInstance(Context arg3) {
        DisplayManagerCompatApi17Impl v1_1;
        WeakHashMap v0 = DisplayManagerCompat.sInstances;
        __monitor_enter(v0);
        try {
            Object v1 = DisplayManagerCompat.sInstances.get(arg3);
            if(v1 == null) {
                if(Build$VERSION.SDK_INT >= 17) {
                    v1_1 = new DisplayManagerCompatApi17Impl(arg3);
                }
                else {
                    DisplayManagerCompatApi14Impl v1_2 = new DisplayManagerCompatApi14Impl(arg3);
                }

                DisplayManagerCompat.sInstances.put(arg3, v1_1);
            }

            __monitor_exit(v0);
            return ((DisplayManagerCompat)v1);
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_18;
        }

        throw v3;
    }
}

