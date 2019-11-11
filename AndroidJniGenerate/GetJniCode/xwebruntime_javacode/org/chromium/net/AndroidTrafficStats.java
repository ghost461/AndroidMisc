package org.chromium.net;

import android.net.TrafficStats;
import android.os.Process;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="net::android::traffic_stats") public class AndroidTrafficStats {
    private AndroidTrafficStats() {
        super();
    }

    @CalledByNative private static long getCurrentUidRxBytes() {
        long v0 = TrafficStats.getUidRxBytes(Process.myUid());
        if(v0 != -1) {
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    @CalledByNative private static long getCurrentUidTxBytes() {
        long v0 = TrafficStats.getUidTxBytes(Process.myUid());
        if(v0 != -1) {
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    @CalledByNative private static long getTotalRxBytes() {
        long v0 = TrafficStats.getTotalRxBytes();
        if(v0 != -1) {
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    @CalledByNative private static long getTotalTxBytes() {
        long v0 = TrafficStats.getTotalTxBytes();
        if(v0 != -1) {
        }
        else {
            v0 = 0;
        }

        return v0;
    }
}

