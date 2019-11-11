package org.chromium.base;

import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import java.util.TimeZone;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base::android") @MainDex class TimezoneUtils {
    private TimezoneUtils() {
        super();
    }

    @CalledByNative private static String getDefaultTimeZoneId() {
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        String v1 = TimeZone.getDefault().getID();
        StrictMode.setThreadPolicy(v0);
        return v1;
    }
}

