package android.support.v4.provider;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo(value={Scope.LIBRARY_GROUP}) public class SelfDestructiveThread {
    class android.support.v4.provider.SelfDestructiveThread$1 implements Handler$Callback {
        android.support.v4.provider.SelfDestructiveThread$1(SelfDestructiveThread arg1) {
            SelfDestructiveThread.this = arg1;
            super();
        }

        public boolean handleMessage(Message arg3) {
            switch(arg3.what) {
                case 0: {
                    goto label_8;
                }
                case 1: {
                    goto label_4;
                }
            }

            return 1;
        label_4:
            SelfDestructiveThread.this.onInvokeRunnable(arg3.obj);
            return 1;
        label_8:
            SelfDestructiveThread.this.onDestruction();
            return 1;
        }
    }

    public interface ReplyCallback {
        void onReply(Object arg1);
    }

    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private Handler$Callback mCallback;
    private final int mDestructAfterMillisec;
    @GuardedBy(value="mLock") private int mGeneration;
    @GuardedBy(value="mLock") private Handler mHandler;
    private final Object mLock;
    private final int mPriority;
    @GuardedBy(value="mLock") private HandlerThread mThread;
    private final String mThreadName;

    public SelfDestructiveThread(String arg2, int arg3, int arg4) {
        super();
        this.mLock = new Object();
        this.mCallback = new android.support.v4.provider.SelfDestructiveThread$1(this);
        this.mThreadName = arg2;
        this.mPriority = arg3;
        this.mDestructAfterMillisec = arg4;
        this.mGeneration = 0;
    }

    static void access$000(SelfDestructiveThread arg0, Runnable arg1) {
        arg0.onInvokeRunnable(arg1);
    }

    static void access$100(SelfDestructiveThread arg0) {
        arg0.onDestruction();
    }

    @VisibleForTesting public int getGeneration() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mGeneration;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @VisibleForTesting public boolean isRunning() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = this.mThread != null ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_10;
        }

        throw v1;
    }

    private void onDestruction() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.mHandler.hasMessages(1)) {
                __monitor_exit(v0);
                return;
            }

            this.mThread.quit();
            this.mThread = null;
            this.mHandler = null;
            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_16;
        }

        throw v1;
    }

    private void onInvokeRunnable(Runnable arg5) {
        arg5.run();
        Object v5 = this.mLock;
        __monitor_enter(v5);
        try {
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), ((long)this.mDestructAfterMillisec));
            __monitor_exit(v5);
            return;
        label_15:
            __monitor_exit(v5);
        }
        catch(Throwable v0) {
            goto label_15;
        }

        throw v0;
    }

    private void post(Runnable arg6) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.mThread == null) {
                this.mThread = new HandlerThread(this.mThreadName, this.mPriority);
                this.mThread.start();
                this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
                ++this.mGeneration;
            }

            this.mHandler.removeMessages(0);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, arg6));
            __monitor_exit(v0);
            return;
        label_31:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_31;
        }

        throw v6;
    }

    public void postAndReply(Callable arg3, ReplyCallback arg4) {
        this.post(new Runnable(arg3, new Handler(), arg4) {
            public void run() {
                Object v0;
                try {
                    v0 = this.val$callable.call();
                }
                catch(Exception ) {
                    v0 = null;
                }

                this.val$callingHandler.post(new Runnable(v0) {
                    public void run() {
                        this.this$1.val$reply.onReply(this.val$result);
                    }
                });
            }
        });
    }

    public Object postAndWait(Callable arg13, int arg14) throws InterruptedException {
        Object v13_1;
        ReentrantLock v7 = new ReentrantLock();
        Condition v8 = v7.newCondition();
        AtomicReference v9 = new AtomicReference();
        AtomicBoolean v10 = new AtomicBoolean(true);
        this.post(new Runnable(v9, arg13, v7, v10, v8) {
            public void run() {
                try {
                    this.val$holder.set(this.val$callable.call());
                    goto label_4;
                }
                catch(Exception ) {
                label_4:
                    this.val$lock.lock();
                    try {
                        this.val$running.set(false);
                        this.val$cond.signal();
                    }
                    catch(Throwable v0) {
                        this.val$lock.unlock();
                        throw v0;
                    }

                    this.val$lock.unlock();
                    return;
                }
            }
        });
        v7.lock();
        try {
            if(v10.get()) {
                goto label_25;
            }

            v13_1 = v9.get();
        }
        catch(Throwable v13) {
            goto label_42;
        }

        v7.unlock();
        return v13_1;
        try {
        label_25:
            long v13_2 = TimeUnit.MILLISECONDS.toNanos(((long)arg14));
            try {
                do {
                label_28:
                    v13_2 = v8.awaitNanos(v13_2);
                    goto label_30;
                }
                while(true);
            }
            catch(InterruptedException ) {
            label_30:
                if(!v10.get()) {
                    v13_1 = v9.get();
                    v7.unlock();
                    return v13_1;
                }

                if(v13_2 > 0) {
                    goto label_28;
                }

                try {
                    throw new InterruptedException("timeout");
                }
                catch(Throwable v13) {
                label_42:
                    v7.unlock();
                    throw v13;
                }
            }
        }
        catch(Throwable v13) {
            goto label_42;
        }
    }
}

