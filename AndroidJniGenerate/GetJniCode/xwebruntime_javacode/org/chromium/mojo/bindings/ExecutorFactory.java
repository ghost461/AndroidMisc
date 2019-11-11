package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.Core$HandleSignals;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle$CreateOptions;
import org.chromium.mojo.system.MessagePipeHandle$ReadFlags;
import org.chromium.mojo.system.MessagePipeHandle$WriteFlags;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;
import org.chromium.mojo.system.Watcher$Callback;
import org.chromium.mojo.system.Watcher;

class ExecutorFactory {
    class PipedExecutor implements Executor, Callback {
        private final Object mLock;
        private final List mPendingActions;
        private final MessagePipeHandle mReadHandle;
        private final Watcher mWatcher;
        private final MessagePipeHandle mWriteHandle;

        static {
        }

        public PipedExecutor(Core arg3) {
            super();
            this.mWatcher = arg3.getWatcher();
            this.mLock = new Object();
            Pair v3 = arg3.createMessagePipe(new CreateOptions());
            this.mReadHandle = v3.first;
            this.mWriteHandle = v3.second;
            this.mPendingActions = new ArrayList();
            this.mWatcher.start(this.mReadHandle, HandleSignals.READABLE, ((Callback)this));
        }

        private void close() {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                this.mWriteHandle.close();
                this.mPendingActions.clear();
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                try {
                label_15:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_15;
                }

                throw v1;
            }

            this.mWatcher.cancel();
            this.mWatcher.destroy();
            this.mReadHandle.close();
        }

        public void execute(Runnable arg5) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                if(!this.mWriteHandle.isValid()) {
                    throw new IllegalStateException("Trying to execute an action on a closed executor.");
                }

                this.mPendingActions.add(arg5);
                this.mWriteHandle.writeMessage(ExecutorFactory.NOTIFY_BUFFER, null, WriteFlags.NONE);
                __monitor_exit(v0);
                return;
            label_19:
                __monitor_exit(v0);
            }
            catch(Throwable v5) {
                goto label_19;
            }

            throw v5;
        }

        public void onResult(int arg1) {
            if(arg1 != 0 || !this.readNotifyBufferMessage()) {
                this.close();
            }
            else {
                this.runNextAction();
            }
        }

        private boolean readNotifyBufferMessage() {
            try {
                if(this.mReadHandle.readMessage(ReadFlags.NONE).getMojoResult() != 0) {
                    return 0;
                }
            }
            catch(MojoException ) {
                return 0;
            }

            return 1;
        }

        private void runNextAction() {
            Object v1_1;
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                v1_1 = this.mPendingActions.remove(0);
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                try {
                label_9:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_9;
                }

                throw v1;
            }

            ((Runnable)v1_1).run();
        }
    }

    private static final ThreadLocal EXECUTORS;
    private static final ByteBuffer NOTIFY_BUFFER;

    static {
        ExecutorFactory.EXECUTORS = new ThreadLocal();
    }

    ExecutorFactory() {
        super();
    }

    static ByteBuffer access$000() {
        return ExecutorFactory.NOTIFY_BUFFER;
    }

    public static Executor getExecutorForCurrentThread(Core arg1) {
        PipedExecutor v0_1;
        Object v0 = ExecutorFactory.EXECUTORS.get();
        if(v0 == null) {
            v0_1 = new PipedExecutor(arg1);
            ExecutorFactory.EXECUTORS.set(v0_1);
        }

        return ((Executor)v0_1);
    }
}

