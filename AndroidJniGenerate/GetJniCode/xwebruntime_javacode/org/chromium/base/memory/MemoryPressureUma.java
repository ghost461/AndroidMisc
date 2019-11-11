package org.chromium.base.memory;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.metrics.RecordHistogram;

public class MemoryPressureUma implements ComponentCallbacks2 {
    @Retention(value=RetentionPolicy.SOURCE) @interface Notification {
        public static final int NOTIFICATION_MAX = 9;
        public static final int ON_LOW_MEMORY = 8;
        public static final int TRIM_MEMORY_BACKGROUND = 3;
        public static final int TRIM_MEMORY_COMPLETE = 1;
        public static final int TRIM_MEMORY_MODERATE = 2;
        public static final int TRIM_MEMORY_RUNNING_CRITICAL = 5;
        public static final int TRIM_MEMORY_RUNNING_LOW = 6;
        public static final int TRIM_MEMORY_RUNNING_MODERATE = 7;
        public static final int TRIM_MEMORY_UI_HIDDEN = 4;
        public static final int UNKNOWN_TRIM_LEVEL;

    }

    private final String mHistogramName;
    private static MemoryPressureUma sInstance;

    static {
    }

    private MemoryPressureUma(String arg3) {
        super();
        this.mHistogramName = "Android.MemoryPressureNotification." + arg3;
    }

    public static void initializeForBrowser() {
        MemoryPressureUma.initializeInstance("Browser");
    }

    public static void initializeForChildService() {
        MemoryPressureUma.initializeInstance("ChildService");
    }

    private static void initializeInstance(String arg1) {
        ThreadUtils.assertOnUiThread();
        MemoryPressureUma.sInstance = new MemoryPressureUma(arg1);
        ContextUtils.getApplicationContext().registerComponentCallbacks(MemoryPressureUma.sInstance);
    }

    public void onConfigurationChanged(Configuration arg1) {
    }

    public void onLowMemory() {
        this.record(8);
    }

    public void onTrimMemory(int arg3) {
        int v0 = 5;
        if(arg3 == v0) {
            this.record(7);
        }
        else if(arg3 == 10) {
            this.record(6);
        }
        else if(arg3 == 15) {
            this.record(v0);
        }
        else if(arg3 == 20) {
            this.record(4);
        }
        else if(arg3 == 40) {
            this.record(3);
        }
        else if(arg3 == 60) {
            this.record(2);
        }
        else if(arg3 != 80) {
            this.record(0);
        }
        else {
            this.record(1);
        }
    }

    private void record(int arg3) {
        RecordHistogram.recordEnumeratedHistogram(this.mHistogramName, arg3, 9);
    }
}

