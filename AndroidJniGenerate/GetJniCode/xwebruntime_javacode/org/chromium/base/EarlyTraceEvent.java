package org.chromium.base;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.os.Process;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.os.SystemClock;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base::android") @MainDex public class EarlyTraceEvent {
    @VisibleForTesting final class AsyncEvent {
        final long mId;
        final boolean mIsStart;
        final String mName;
        final long mTimestampNanos;

        AsyncEvent(String arg1, long arg2, boolean arg4) {
            super();
            this.mName = arg1;
            this.mId = arg2;
            this.mIsStart = arg4;
            this.mTimestampNanos = Event.elapsedRealtimeNanos();
        }
    }

    @VisibleForTesting final class Event {
        final long mBeginThreadTimeMillis;
        final long mBeginTimeNanos;
        long mEndThreadTimeMillis;
        long mEndTimeNanos;
        final String mName;
        final int mThreadId;

        static {
        }

        Event(String arg3) {
            super();
            this.mName = arg3;
            this.mThreadId = Process.myTid();
            this.mBeginTimeNanos = Event.elapsedRealtimeNanos();
            this.mBeginThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        }

        @SuppressLint(value={"NewApi"}) @VisibleForTesting static long elapsedRealtimeNanos() {
            if(Build$VERSION.SDK_INT >= 17) {
                return SystemClock.elapsedRealtimeNanos();
            }

            return SystemClock.elapsedRealtime() * 1000000;
        }

        void end() {
            this.mEndTimeNanos = Event.elapsedRealtimeNanos();
            this.mEndThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        }
    }

    @VisibleForTesting static final int STATE_DISABLED = 0;
    @VisibleForTesting static final int STATE_ENABLED = 1;
    @VisibleForTesting static final int STATE_FINISHED = 3;
    @VisibleForTesting static final int STATE_FINISHING = 2;
    private static final String TRACE_CONFIG_FILENAME = "/data/local/chrome-trace-config.json";
    @VisibleForTesting static List sAsyncEvents;
    @VisibleForTesting static List sCompletedEvents;
    private static final Object sLock;
    @VisibleForTesting static Map sPendingEvents;
    @VisibleForTesting static volatile int sState;

    static {
        EarlyTraceEvent.sLock = new Object();
    }

    public EarlyTraceEvent() {
        super();
    }

    public static void begin(String arg3) {
        if(!EarlyTraceEvent.enabled()) {
            return;
        }

        Event v0 = new Event(arg3);
        Object v1 = EarlyTraceEvent.sLock;
        __monitor_enter(v1);
        try {
            if(!EarlyTraceEvent.enabled()) {
                __monitor_exit(v1);
                return;
            }

            __monitor_exit(v1);
            if(EarlyTraceEvent.sPendingEvents.put(arg3, v0) == null) {
                return;
            }
        }
        catch(Throwable v3) {
            try {
            label_21:
                __monitor_exit(v1);
            }
            catch(Throwable v3) {
                goto label_21;
            }

            throw v3;
        }

        throw new IllegalArgumentException("Multiple pending trace events can\'t have the same name");
    }

    static void disable() {
        Object v0 = EarlyTraceEvent.sLock;
        __monitor_enter(v0);
        try {
            if(!EarlyTraceEvent.enabled()) {
                __monitor_exit(v0);
                return;
            }

            EarlyTraceEvent.sState = 2;
            EarlyTraceEvent.maybeFinishLocked();
            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    private static void dumpAsyncEvents(List arg10) {
        long v4 = TimeUtils.nativeGetTimeTicksNowUs() * 1000 - Event.elapsedRealtimeNanos();
        Iterator v10 = arg10.iterator();
        while(v10.hasNext()) {
            Object v0 = v10.next();
            if(((AsyncEvent)v0).mIsStart) {
                EarlyTraceEvent.nativeRecordEarlyStartAsyncEvent(((AsyncEvent)v0).mName, ((AsyncEvent)v0).mId, ((AsyncEvent)v0).mTimestampNanos + v4);
                continue;
            }

            EarlyTraceEvent.nativeRecordEarlyFinishAsyncEvent(((AsyncEvent)v0).mName, ((AsyncEvent)v0).mId, ((AsyncEvent)v0).mTimestampNanos + v4);
        }
    }

    private static void dumpEvents(List arg16) {
        long v4 = TimeUtils.nativeGetTimeTicksNowUs() * 1000 - Event.elapsedRealtimeNanos();
        Iterator v0 = arg16.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            EarlyTraceEvent.nativeRecordEarlyEvent(((Event)v1).mName, ((Event)v1).mBeginTimeNanos + v4, ((Event)v1).mEndTimeNanos + v4, ((Event)v1).mThreadId, ((Event)v1).mEndThreadTimeMillis - ((Event)v1).mBeginThreadTimeMillis);
        }
    }

    @VisibleForTesting static void enable() {
        Object v0 = EarlyTraceEvent.sLock;
        __monitor_enter(v0);
        try {
            if(EarlyTraceEvent.sState != 0) {
                __monitor_exit(v0);
                return;
            }

            EarlyTraceEvent.sCompletedEvents = new ArrayList();
            EarlyTraceEvent.sPendingEvents = new HashMap();
            EarlyTraceEvent.sAsyncEvents = new ArrayList();
            EarlyTraceEvent.sState = 1;
            __monitor_exit(v0);
            return;
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_20;
        }

        throw v1;
    }

    static boolean enabled() {
        boolean v1 = true;
        if(EarlyTraceEvent.sState == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public static void end(String arg2) {
        if(!EarlyTraceEvent.isActive()) {
            return;
        }

        Object v0 = EarlyTraceEvent.sLock;
        __monitor_enter(v0);
        try {
            if(!EarlyTraceEvent.isActive()) {
                __monitor_exit(v0);
                return;
            }

            Object v2_1 = EarlyTraceEvent.sPendingEvents.remove(arg2);
            if(v2_1 == null) {
                __monitor_exit(v0);
                return;
            }

            ((Event)v2_1).end();
            EarlyTraceEvent.sCompletedEvents.add(v2_1);
            if(EarlyTraceEvent.sState == 2) {
                EarlyTraceEvent.maybeFinishLocked();
            }

            __monitor_exit(v0);
            return;
        label_24:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_24;
        }

        throw v2;
    }

    public static void finishAsync(String arg2, long arg3) {
        if(!EarlyTraceEvent.enabled()) {
            return;
        }

        AsyncEvent v0 = new AsyncEvent(arg2, arg3, false);
        Object v2 = EarlyTraceEvent.sLock;
        __monitor_enter(v2);
        try {
            if(!EarlyTraceEvent.enabled()) {
                __monitor_exit(v2);
                return;
            }

            EarlyTraceEvent.sAsyncEvents.add(v0);
            __monitor_exit(v2);
            return;
        label_17:
            __monitor_exit(v2);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }

    static boolean isActive() {
        int v0 = EarlyTraceEvent.sState;
        boolean v1 = true;
        if(v0 != 1) {
            if(v0 == 2) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    static void maybeEnable() {
        ThreadUtils.assertOnUiThread();
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        try {
            if(!CommandLine.getInstance().hasSwitch("trace-startup")) {
                goto label_8;
            }
        }
        catch(Throwable v1) {
            goto label_19;
        }

        boolean v1_1 = true;
        goto label_14;
        try {
        label_8:
            v1_1 = new File("/data/local/chrome-trace-config.json").exists();
        }
        catch(Throwable v1) {
        label_19:
            StrictMode.setThreadPolicy(v0);
            throw v1;
        }
        catch(SecurityException ) {
            v1_1 = false;
        }

    label_14:
        StrictMode.setThreadPolicy(v0);
        if(v1_1) {
            EarlyTraceEvent.enable();
        }
    }

    private static void maybeFinishLocked() {
        if(!EarlyTraceEvent.sCompletedEvents.isEmpty()) {
            EarlyTraceEvent.dumpEvents(EarlyTraceEvent.sCompletedEvents);
            EarlyTraceEvent.sCompletedEvents.clear();
        }

        if(!EarlyTraceEvent.sAsyncEvents.isEmpty()) {
            EarlyTraceEvent.dumpAsyncEvents(EarlyTraceEvent.sAsyncEvents);
            EarlyTraceEvent.sAsyncEvents.clear();
        }

        if(EarlyTraceEvent.sPendingEvents.isEmpty()) {
            EarlyTraceEvent.sState = 3;
            EarlyTraceEvent.sPendingEvents = null;
            EarlyTraceEvent.sCompletedEvents = null;
        }
    }

    private static native void nativeRecordEarlyEvent(String arg0, long arg1, long arg2, int arg3, long arg4) {
    }

    private static native void nativeRecordEarlyFinishAsyncEvent(String arg0, long arg1, long arg2) {
    }

    private static native void nativeRecordEarlyStartAsyncEvent(String arg0, long arg1, long arg2) {
    }

    @VisibleForTesting static void resetForTesting() {
        EarlyTraceEvent.sState = 0;
        EarlyTraceEvent.sCompletedEvents = null;
        EarlyTraceEvent.sPendingEvents = null;
        EarlyTraceEvent.sAsyncEvents = null;
    }

    public static void startAsync(String arg2, long arg3) {
        if(!EarlyTraceEvent.enabled()) {
            return;
        }

        AsyncEvent v0 = new AsyncEvent(arg2, arg3, true);
        Object v2 = EarlyTraceEvent.sLock;
        __monitor_enter(v2);
        try {
            if(!EarlyTraceEvent.enabled()) {
                __monitor_exit(v2);
                return;
            }

            EarlyTraceEvent.sAsyncEvents.add(v0);
            __monitor_exit(v2);
            return;
        label_17:
            __monitor_exit(v2);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }
}

