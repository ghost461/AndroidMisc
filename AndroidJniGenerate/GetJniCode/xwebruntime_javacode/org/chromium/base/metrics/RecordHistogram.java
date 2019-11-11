package org.chromium.base.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base::android") @MainDex public class RecordHistogram {
    private static Map sCache;
    private static Throwable sDisabledBy;

    static {
        RecordHistogram.sCache = Collections.synchronizedMap(new HashMap());
    }

    public RecordHistogram() {
        super();
    }

    static void assertTimesHistogramSupportsUnit(TimeUnit arg1) {
    }

    private static int clampToInt(long arg3) {
        if(arg3 > 0x7FFFFFFF) {
            return 0x7FFFFFFF;
        }

        if(arg3 < -2147483648) {
            return 0x80000000;
        }

        return ((int)arg3);
    }

    private static long getCachedHistogramKey(String arg2) {
        Object v2 = RecordHistogram.sCache.get(arg2);
        long v0 = v2 == null ? 0 : ((Long)v2).longValue();
        return v0;
    }

    @VisibleForTesting public static int getHistogramTotalCountForTesting(String arg0) {
        return RecordHistogram.nativeGetHistogramTotalCountForTesting(arg0);
    }

    @VisibleForTesting public static int getHistogramValueCountForTesting(String arg0, int arg1) {
        return RecordHistogram.nativeGetHistogramValueCountForTesting(arg0, arg1);
    }

    private static native int nativeGetHistogramTotalCountForTesting(String arg0) {
    }

    private static native int nativeGetHistogramValueCountForTesting(String arg0, int arg1) {
    }

    private static native long nativeRecordBooleanHistogram(String arg0, long arg1, boolean arg2) {
    }

    private static native long nativeRecordCustomCountHistogram(String arg0, long arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    private static native long nativeRecordCustomTimesHistogramMilliseconds(String arg0, long arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    private static native long nativeRecordEnumeratedHistogram(String arg0, long arg1, int arg2, int arg3) {
    }

    private static native long nativeRecordLinearCountHistogram(String arg0, long arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    private static native long nativeRecordSparseHistogram(String arg0, long arg1, int arg2) {
    }

    public static void recordBooleanHistogram(String arg4, boolean arg5) {
        if(RecordHistogram.sDisabledBy != null) {
            return;
        }

        long v0 = RecordHistogram.getCachedHistogramKey(arg4);
        long v2 = RecordHistogram.nativeRecordBooleanHistogram(arg4, v0, arg5);
        if(v2 != v0) {
            RecordHistogram.sCache.put(arg4, Long.valueOf(v2));
        }
    }

    public static void recordCount1000Histogram(String arg3, int arg4) {
        RecordHistogram.recordCustomCountHistogram(arg3, arg4, 1, 1000, 50);
    }

    public static void recordCount100Histogram(String arg3, int arg4) {
        RecordHistogram.recordCustomCountHistogram(arg3, arg4, 1, 100, 50);
    }

    public static void recordCountHistogram(String arg3, int arg4) {
        RecordHistogram.recordCustomCountHistogram(arg3, arg4, 1, 1000000, 50);
    }

    public static void recordCustomCountHistogram(String arg10, int arg11, int arg12, int arg13, int arg14) {
        if(RecordHistogram.sDisabledBy != null) {
            return;
        }

        long v8 = RecordHistogram.getCachedHistogramKey(arg10);
        long v11 = RecordHistogram.nativeRecordCustomCountHistogram(arg10, v8, arg11, arg12, arg13, arg14);
        if(v11 != v8) {
            RecordHistogram.sCache.put(arg10, Long.valueOf(v11));
        }
    }

    public static void recordCustomTimesHistogram(String arg0, long arg1, long arg3, long arg5, TimeUnit arg7, int arg8) {
        RecordHistogram.assertTimesHistogramSupportsUnit(arg7);
        RecordHistogram.recordCustomTimesHistogramMilliseconds(arg0, arg7.toMillis(arg1), arg7.toMillis(arg3), arg7.toMillis(arg5), arg8);
    }

    private static void recordCustomTimesHistogramMilliseconds(String arg10, long arg11, long arg13, long arg15, int arg17) {
        if(RecordHistogram.sDisabledBy != null) {
            return;
        }

        long v8 = RecordHistogram.getCachedHistogramKey(arg10);
        long v0 = RecordHistogram.nativeRecordCustomTimesHistogramMilliseconds(arg10, v8, RecordHistogram.clampToInt(arg11), RecordHistogram.clampToInt(arg13), RecordHistogram.clampToInt(arg15), arg17);
        if(v0 != v8) {
            RecordHistogram.sCache.put(arg10, Long.valueOf(v0));
        }
    }

    public static void recordEnumeratedHistogram(String arg3, int arg4, int arg5) {
        if(RecordHistogram.sDisabledBy != null) {
            return;
        }

        long v0 = RecordHistogram.getCachedHistogramKey(arg3);
        long v4 = RecordHistogram.nativeRecordEnumeratedHistogram(arg3, v0, arg4, arg5);
        if(v4 != v0) {
            RecordHistogram.sCache.put(arg3, Long.valueOf(v4));
        }
    }

    public static void recordLinearCountHistogram(String arg10, int arg11, int arg12, int arg13, int arg14) {
        if(RecordHistogram.sDisabledBy != null) {
            return;
        }

        long v8 = RecordHistogram.getCachedHistogramKey(arg10);
        long v11 = RecordHistogram.nativeRecordLinearCountHistogram(arg10, v8, arg11, arg12, arg13, arg14);
        if(v11 != v8) {
            RecordHistogram.sCache.put(arg10, Long.valueOf(v11));
        }
    }

    public static void recordLongTimesHistogram(String arg8, long arg9, TimeUnit arg11) {
        RecordHistogram.assertTimesHistogramSupportsUnit(arg11);
        RecordHistogram.recordCustomTimesHistogramMilliseconds(arg8, arg11.toMillis(arg9), 1, TimeUnit.HOURS.toMillis(1), 50);
    }

    public static void recordLongTimesHistogram100(String arg8, long arg9, TimeUnit arg11) {
        RecordHistogram.assertTimesHistogramSupportsUnit(arg11);
        RecordHistogram.recordCustomTimesHistogramMilliseconds(arg8, arg11.toMillis(arg9), 1, TimeUnit.HOURS.toMillis(1), 100);
    }

    public static void recordMediumTimesHistogram(String arg8, long arg9, TimeUnit arg11) {
        RecordHistogram.assertTimesHistogramSupportsUnit(arg11);
        RecordHistogram.recordCustomTimesHistogramMilliseconds(arg8, arg11.toMillis(arg9), 10, TimeUnit.MINUTES.toMillis(3), 50);
    }

    public static void recordMemoryKBHistogram(String arg3, int arg4) {
        RecordHistogram.recordCustomCountHistogram(arg3, arg4, 1000, 500000, 50);
    }

    public static void recordPercentageHistogram(String arg4, int arg5) {
        if(RecordHistogram.sDisabledBy != null) {
            return;
        }

        long v0 = RecordHistogram.getCachedHistogramKey(arg4);
        long v2 = RecordHistogram.nativeRecordEnumeratedHistogram(arg4, v0, arg5, 101);
        if(v2 != v0) {
            RecordHistogram.sCache.put(arg4, Long.valueOf(v2));
        }
    }

    public static void recordSparseSlowlyHistogram(String arg4, int arg5) {
        if(RecordHistogram.sDisabledBy != null) {
            return;
        }

        long v0 = RecordHistogram.getCachedHistogramKey(arg4);
        long v2 = RecordHistogram.nativeRecordSparseHistogram(arg4, v0, arg5);
        if(v2 != v0) {
            RecordHistogram.sCache.put(arg4, Long.valueOf(v2));
        }
    }

    public static void recordTimesHistogram(String arg8, long arg9, TimeUnit arg11) {
        RecordHistogram.assertTimesHistogramSupportsUnit(arg11);
        RecordHistogram.recordCustomTimesHistogramMilliseconds(arg8, arg11.toMillis(arg9), 1, TimeUnit.SECONDS.toMillis(10), 50);
    }

    @VisibleForTesting public static void setDisabledForTests(boolean arg2) {
        if((arg2) && RecordHistogram.sDisabledBy != null) {
            throw new IllegalStateException("Histograms are already disabled.", RecordHistogram.sDisabledBy);
        }

        Throwable v2 = arg2 ? new Throwable() : null;
        RecordHistogram.sDisabledBy = v2;
    }
}

