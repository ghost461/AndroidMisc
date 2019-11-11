package org.chromium.net;

import android.net.TrafficStats;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ThreadStatsUid {
    private static final Method sClearThreadStatsUid;
    private static final Method sSetThreadStatsUid;

    static {
        try {
            ThreadStatsUid.sSetThreadStatsUid = TrafficStats.class.getMethod("setThreadStatsUid", Integer.TYPE);
            ThreadStatsUid.sClearThreadStatsUid = TrafficStats.class.getMethod("clearThreadStatsUid");
            return;
        }
        catch(SecurityException v0) {
            throw new RuntimeException("Unable to get TrafficStats methods", ((Throwable)v0));
        }
    }

    public ThreadStatsUid() {
        super();
    }

    public static void clear() {
        try {
            ThreadStatsUid.sClearThreadStatsUid.invoke(null);
            return;
        }
        catch(InvocationTargetException v0) {
            throw new RuntimeException("TrafficStats.clearThreadStatsUid failed", ((Throwable)v0));
        }
        catch(IllegalAccessException v0_1) {
            throw new RuntimeException("TrafficStats.clearThreadStatsUid failed", ((Throwable)v0_1));
        }
    }

    public static void set(int arg4) {
        try {
            ThreadStatsUid.sSetThreadStatsUid.invoke(null, Integer.valueOf(arg4));
            return;
        }
        catch(InvocationTargetException v4) {
            throw new RuntimeException("TrafficStats.setThreadStatsUid failed", ((Throwable)v4));
        }
        catch(IllegalAccessException v4_1) {
            throw new RuntimeException("TrafficStats.setThreadStatsUid failed", ((Throwable)v4_1));
        }
    }
}

