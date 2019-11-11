package org.chromium.base;

import android.os.Looper;
import android.os.MessageQueue$IdleHandler;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="base::android") @MainDex public class TraceEvent implements AutoCloseable {
    class org.chromium.base.TraceEvent$1 {
    }

    class BasicLooperMonitor implements Printer {
        private static final String EARLY_TOPLEVEL_TASK_NAME = "Looper.dispatchMessage: ";

        static {
        }

        private BasicLooperMonitor() {
            super();
        }

        BasicLooperMonitor(org.chromium.base.TraceEvent$1 arg1) {
            this();
        }

        void beginHandling(String arg3) {
            boolean v0 = EarlyTraceEvent.isActive();
            if((TraceEvent.sEnabled) || (v0)) {
                arg3 = BasicLooperMonitor.getTarget(arg3);
                if(TraceEvent.sEnabled) {
                    TraceEvent.nativeBeginToplevel(arg3);
                }
                else if(v0) {
                    EarlyTraceEvent.begin("Looper.dispatchMessage: " + arg3);
                }
            }
        }

        void endHandling(String arg3) {
            if(EarlyTraceEvent.isActive()) {
                EarlyTraceEvent.end("Looper.dispatchMessage: " + BasicLooperMonitor.getTarget(arg3));
            }

            if(TraceEvent.sEnabled) {
                TraceEvent.nativeEndToplevel();
            }
        }

        private static String getTarget(String arg3) {
            int v0 = arg3.indexOf(40, 21);
            int v1 = -1;
            int v2 = v0 == v1 ? -1 : arg3.indexOf(41, v0);
            return v2 != v1 ? arg3.substring(v0 + 1, v2) : "";
        }

        public void println(String arg2) {
            if(arg2.startsWith(">")) {
                this.beginHandling(arg2);
            }
            else {
                this.endHandling(arg2);
            }
        }
    }

    final class IdleTracingLooperMonitor extends BasicLooperMonitor implements MessageQueue$IdleHandler {
        private static final long FRAME_DURATION_MILLIS = 16;
        private static final String IDLE_EVENT_NAME = "Looper.queueIdle";
        private static final long MIN_INTERESTING_BURST_DURATION_MILLIS = 0x30;
        private static final long MIN_INTERESTING_DURATION_MILLIS = 16;
        private static final String TAG = "TraceEvent.LooperMonitor";
        private boolean mIdleMonitorAttached;
        private long mLastIdleStartedAt;
        private long mLastWorkStartedAt;
        private int mNumIdlesSeen;
        private int mNumTasksSeen;
        private int mNumTasksSinceLastIdle;

        IdleTracingLooperMonitor(org.chromium.base.TraceEvent$1 arg1) {
            this();
        }

        private IdleTracingLooperMonitor() {
            super(null);
        }

        final void beginHandling(String arg3) {
            if(this.mNumTasksSinceLastIdle == 0) {
                TraceEvent.end("Looper.queueIdle");
            }

            this.mLastWorkStartedAt = SystemClock.elapsedRealtime();
            this.syncIdleMonitoring();
            super.beginHandling(arg3);
        }

        final void endHandling(String arg7) {
            long v4 = SystemClock.elapsedRealtime() - this.mLastWorkStartedAt;
            if(v4 > 16) {
                IdleTracingLooperMonitor.traceAndLog(5, "observed a task that took " + v4 + "ms: " + arg7);
            }

            super.endHandling(arg7);
            this.syncIdleMonitoring();
            ++this.mNumTasksSeen;
            ++this.mNumTasksSinceLastIdle;
        }

        public final boolean queueIdle() {
            long v0 = SystemClock.elapsedRealtime();
            if(this.mLastIdleStartedAt == 0) {
                this.mLastIdleStartedAt = v0;
            }

            long v4 = v0 - this.mLastIdleStartedAt;
            ++this.mNumIdlesSeen;
            TraceEvent.begin("Looper.queueIdle", this.mNumTasksSinceLastIdle + " tasks since last idle.");
            if(v4 > 0x30) {
                IdleTracingLooperMonitor.traceAndLog(3, this.mNumTasksSeen + " tasks and " + this.mNumIdlesSeen + " idles processed so far, " + this.mNumTasksSinceLastIdle + " tasks bursted and " + v4 + "ms elapsed since last idle");
            }

            this.mLastIdleStartedAt = v0;
            this.mNumTasksSinceLastIdle = 0;
            return 1;
        }

        private final void syncIdleMonitoring() {
            if((TraceEvent.sEnabled) && !this.mIdleMonitorAttached) {
                this.mLastIdleStartedAt = SystemClock.elapsedRealtime();
                Looper.myQueue().addIdleHandler(((MessageQueue$IdleHandler)this));
                this.mIdleMonitorAttached = true;
                Log.v("TraceEvent.LooperMonitor", "attached idle handler");
            }
            else if((this.mIdleMonitorAttached) && !TraceEvent.sEnabled) {
                Looper.myQueue().removeIdleHandler(((MessageQueue$IdleHandler)this));
                this.mIdleMonitorAttached = false;
                Log.v("TraceEvent.LooperMonitor", "detached idle handler");
            }
        }

        private static void traceAndLog(int arg1, String arg2) {
            TraceEvent.instant("TraceEvent.LooperMonitor:IdleStats", arg2);
            Log.println(arg1, "TraceEvent.LooperMonitor", arg2);
        }
    }

    final class LooperMonitorHolder {
        private static final BasicLooperMonitor sInstance;

        static {
            IdleTracingLooperMonitor v0;
            org.chromium.base.TraceEvent$1 v1 = null;
            if(CommandLine.getInstance().hasSwitch("enable-idle-tracing")) {
                v0 = new IdleTracingLooperMonitor(v1);
            }
            else {
                BasicLooperMonitor v0_1 = new BasicLooperMonitor(v1);
            }

            LooperMonitorHolder.sInstance = ((BasicLooperMonitor)v0);
        }

        private LooperMonitorHolder() {
            super();
        }

        static BasicLooperMonitor access$500() {
            return LooperMonitorHolder.sInstance;
        }
    }

    private final String mName;
    private static volatile boolean sATraceEnabled;
    private static volatile boolean sEnabled;

    private TraceEvent(String arg1, String arg2) {
        super();
        this.mName = arg1;
        TraceEvent.begin(arg1, arg2);
    }

    static boolean access$000() {
        return TraceEvent.sEnabled;
    }

    static void access$100(String arg0) {
        TraceEvent.nativeBeginToplevel(arg0);
    }

    static void access$200() {
        TraceEvent.nativeEndToplevel();
    }

    public static void begin(String arg1, String arg2) {
        EarlyTraceEvent.begin(arg1);
        if(TraceEvent.sEnabled) {
            TraceEvent.nativeBegin(arg1, arg2);
        }
    }

    public static void begin(String arg1) {
        TraceEvent.begin(arg1, null);
    }

    public void close() {
        TraceEvent.end(this.mName);
    }

    public static boolean enabled() {
        return TraceEvent.sEnabled;
    }

    public static void end(String arg1) {
        TraceEvent.end(arg1, null);
    }

    public static void end(String arg1, String arg2) {
        EarlyTraceEvent.end(arg1);
        if(TraceEvent.sEnabled) {
            TraceEvent.nativeEnd(arg1, arg2);
        }
    }

    public static void finishAsync(String arg1, long arg2) {
        EarlyTraceEvent.finishAsync(arg1, arg2);
        if(TraceEvent.sEnabled) {
            TraceEvent.nativeFinishAsync(arg1, arg2);
        }
    }

    public static void instant(String arg1) {
        if(TraceEvent.sEnabled) {
            TraceEvent.nativeInstant(arg1, null);
        }
    }

    public static void instant(String arg1, String arg2) {
        if(TraceEvent.sEnabled) {
            TraceEvent.nativeInstant(arg1, arg2);
        }
    }

    public static void maybeEnableEarlyTracing() {
        EarlyTraceEvent.maybeEnable();
        if(EarlyTraceEvent.isActive()) {
            ThreadUtils.getUiThreadLooper().setMessageLogging(LooperMonitorHolder.access$500());
        }
    }

    private static native void nativeBegin(String arg0, String arg1) {
    }

    private static native void nativeBeginToplevel(String arg0) {
    }

    private static native void nativeEnd(String arg0, String arg1) {
    }

    private static native void nativeEndToplevel() {
    }

    private static native void nativeFinishAsync(String arg0, long arg1) {
    }

    private static native void nativeInstant(String arg0, String arg1) {
    }

    private static native void nativeRegisterEnabledObserver() {
    }

    private static native void nativeStartATrace() {
    }

    private static native void nativeStartAsync(String arg0, long arg1) {
    }

    private static native void nativeStopATrace() {
    }

    public static void registerNativeEnabledObserver() {
        TraceEvent.nativeRegisterEnabledObserver();
    }

    public static TraceEvent scoped(String arg1) {
        return TraceEvent.scoped(arg1, null);
    }

    public static TraceEvent scoped(String arg1, String arg2) {
        if(!EarlyTraceEvent.enabled() && !TraceEvent.enabled()) {
            return null;
        }

        return new TraceEvent(arg1, arg2);
    }

    public static void setATraceEnabled(boolean arg1) {
        if(TraceEvent.sATraceEnabled == arg1) {
            return;
        }

        TraceEvent.sATraceEnabled = arg1;
        if(arg1) {
            TraceEvent.nativeStartATrace();
        }
        else {
            TraceEvent.nativeStopATrace();
        }
    }

    @CalledByNative public static void setEnabled(boolean arg1) {
        BasicLooperMonitor v1;
        if(arg1) {
            EarlyTraceEvent.disable();
        }

        if(TraceEvent.sEnabled != arg1) {
            TraceEvent.sEnabled = arg1;
            if(TraceEvent.sATraceEnabled) {
                return;
            }
            else {
                Looper v0 = ThreadUtils.getUiThreadLooper();
                if(arg1) {
                    v1 = LooperMonitorHolder.access$500();
                }
                else {
                    Printer v1_1 = null;
                }

                v0.setMessageLogging(((Printer)v1));
            }
        }
    }

    public static void startAsync(String arg1, long arg2) {
        EarlyTraceEvent.startAsync(arg1, arg2);
        if(TraceEvent.sEnabled) {
            TraceEvent.nativeStartAsync(arg1, arg2);
        }
    }
}

