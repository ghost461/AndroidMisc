package org.chromium.components.download.internal;

import android.content.Intent;
import android.content.IntentFilter;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="download") public final class BatteryStatusListenerAndroid {
    static {
    }

    public BatteryStatusListenerAndroid() {
        super();
    }

    @CalledByNative public static int getBatteryPercentage() {
        Intent v0 = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if(v0 == null) {
            return 0;
        }

        int v3 = -1;
        int v2 = v0.getIntExtra("scale", v3);
        if(v2 == 0) {
            return 0;
        }

        return ((int)Math.round((((double)v0.getIntExtra("level", v3))) * 100 / (((double)v2))));
    }
}

