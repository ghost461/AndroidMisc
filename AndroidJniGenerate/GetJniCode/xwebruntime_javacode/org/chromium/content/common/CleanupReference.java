package org.chromium.content.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

public class CleanupReference extends WeakReference {
    final class org.chromium.content.common.CleanupReference$1 extends Thread {
        org.chromium.content.common.CleanupReference$1(String arg1) {
            super(arg1);
        }

        public void run() {
            Object v1;
            Reference v0_1;
            try {
                while(true) {
                label_0:
                    v0_1 = CleanupReference.sGcQueue.remove();
                    v1 = CleanupReference.sCleanupMonitor;
                    __monitor_enter(v1);
                    break;
                }
            }
            catch(Exception v0) {
                goto label_23;
            }

            try {
                Message.obtain(LazyHolder.sHandler, 2, v0_1).sendToTarget();
                CleanupReference.sCleanupMonitor.wait(500);
                __monitor_exit(v1);
                goto label_0;
            label_14:
                __monitor_exit(v1);
            }
            catch(Throwable v0_2) {
                goto label_14;
            }

            try {
                throw v0_2;
            }
            catch(Exception v0) {
            label_23:
                Log.e("cr.CleanupReference", "Queue remove exception:", new Object[]{v0});
                goto label_0;
            }
        }
    }

    class LazyHolder {
        final class org.chromium.content.common.CleanupReference$LazyHolder$1 extends Handler {
            org.chromium.content.common.CleanupReference$LazyHolder$1(Looper arg1) {
                super(arg1);
            }

            public void handleMessage(Message arg5) {
                Object v5_1;
                try {
                    TraceEvent.begin("CleanupReference.LazyHolder.handleMessage");
                    Object v0 = arg5.obj;
                    switch(arg5.what) {
                        case 1: {
                            CleanupReference.sRefs.add(v0);
                            break;
                        }
                        case 2: {
                            ((CleanupReference)v0).runCleanupTaskInternal();
                            break;
                        }
                        default: {
                            String v0_1 = "cr.CleanupReference";
                            Log.e(v0_1, "Bad message=%d", new Object[]{Integer.valueOf(arg5.what)});
                            break;
                        }
                    }

                    v5_1 = CleanupReference.sCleanupMonitor;
                    __monitor_enter(v5_1);
                }
                catch(Throwable v5) {
                    goto label_38;
                }

                try {
                    while(true) {
                        Reference v0_3 = CleanupReference.sGcQueue.poll();
                        if(v0_3 == null) {
                            break;
                        }

                        ((CleanupReference)v0_3).runCleanupTaskInternal();
                    }

                    CleanupReference.sCleanupMonitor.notifyAll();
                    __monitor_exit(v5_1);
                }
                catch(Throwable v0_2) {
                    goto label_34;
                }

                TraceEvent.end("CleanupReference.LazyHolder.handleMessage");
                return;
                try {
                label_34:
                    __monitor_exit(v5_1);
                }
                catch(Throwable v0_2) {
                    goto label_34;
                }

                try {
                    throw v0_2;
                }
                catch(Throwable v5) {
                label_38:
                    TraceEvent.end("CleanupReference.LazyHolder.handleMessage");
                    throw v5;
                }
            }
        }

        static final Handler sHandler;

        static {
            LazyHolder.sHandler = new org.chromium.content.common.CleanupReference$LazyHolder$1(ThreadUtils.getUiThreadLooper());
        }

        private LazyHolder() {
            super();
        }
    }

    private static final int ADD_REF = 1;
    private static final boolean DEBUG = false;
    private static final int REMOVE_REF = 2;
    private static final String TAG = "cr.CleanupReference";
    private Runnable mCleanupTask;
    private static Object sCleanupMonitor;
    private static ReferenceQueue sGcQueue;
    private static final Thread sReaperThread;
    private static Set sRefs;

    static {
        CleanupReference.sGcQueue = new ReferenceQueue();
        CleanupReference.sCleanupMonitor = new Object();
        CleanupReference.sReaperThread = new org.chromium.content.common.CleanupReference$1("cr.CleanupReference");
        CleanupReference.sReaperThread.setDaemon(true);
        CleanupReference.sReaperThread.start();
        CleanupReference.sRefs = new HashSet();
    }

    public CleanupReference(Object arg2, Runnable arg3) {
        super(arg2, CleanupReference.sGcQueue);
        this.mCleanupTask = arg3;
        this.handleOnUiThread(1);
    }

    static ReferenceQueue access$000() {
        return CleanupReference.sGcQueue;
    }

    static Object access$100() {
        return CleanupReference.sCleanupMonitor;
    }

    static Set access$200() {
        return CleanupReference.sRefs;
    }

    static void access$300(CleanupReference arg0) {
        arg0.runCleanupTaskInternal();
    }

    public void cleanupNow() {
        this.handleOnUiThread(2);
    }

    private void handleOnUiThread(int arg3) {
        Message v3 = Message.obtain(LazyHolder.sHandler, arg3, this);
        if(Looper.myLooper() == v3.getTarget().getLooper()) {
            v3.getTarget().handleMessage(v3);
            v3.recycle();
        }
        else {
            v3.sendToTarget();
        }
    }

    private void runCleanupTaskInternal() {
        CleanupReference.sRefs.remove(this);
        if(this.mCleanupTask != null) {
            this.mCleanupTask.run();
            this.mCleanupTask = null;
        }

        this.clear();
    }
}

