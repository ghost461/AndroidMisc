package org.chromium.mojo.system;

public interface Core {
    public class HandleSignals extends Flags {
        private static final int FLAG_NONE = 0;
        private static final int FLAG_PEER_CLOSED = 4;
        private static final int FLAG_READABLE = 1;
        private static final int FLAG_WRITABLE = 2;
        public static final HandleSignals NONE;
        public static final HandleSignals READABLE;
        public static final HandleSignals WRITABLE;

        static {
            HandleSignals.NONE = HandleSignals.none().immutable();
            HandleSignals.READABLE = HandleSignals.none().setReadable(true).immutable();
            HandleSignals.WRITABLE = HandleSignals.none().setWritable(true).immutable();
        }

        public HandleSignals(int arg1) {
            super(arg1);
        }

        public static HandleSignals none() {
            return new HandleSignals(0);
        }

        public HandleSignals setPeerClosed(boolean arg2) {
            return this.setFlag(4, arg2);
        }

        public HandleSignals setReadable(boolean arg2) {
            return this.setFlag(1, arg2);
        }

        public HandleSignals setWritable(boolean arg2) {
            return this.setFlag(2, arg2);
        }
    }

    public class HandleSignalsState {
        private final HandleSignals mSatisfiableSignals;
        private final HandleSignals mSatisfiedSignals;

        public HandleSignalsState(HandleSignals arg1, HandleSignals arg2) {
            super();
            this.mSatisfiedSignals = arg1;
            this.mSatisfiableSignals = arg2;
        }

        public HandleSignals getSatisfiableSignals() {
            return this.mSatisfiableSignals;
        }

        public HandleSignals getSatisfiedSignals() {
            return this.mSatisfiedSignals;
        }
    }

    public static final long DEADLINE_INFINITE = -1;

    UntypedHandle acquireNativeHandle(int arg1);

    Pair createDataPipe(CreateOptions arg1);

    RunLoop createDefaultRunLoop();

    Pair createMessagePipe(org.chromium.mojo.system.MessagePipeHandle$CreateOptions arg1);

    SharedBufferHandle createSharedBuffer(org.chromium.mojo.system.SharedBufferHandle$CreateOptions arg1, long arg2);

    RunLoop getCurrentRunLoop();

    long getTimeTicksNow();

    Watcher getWatcher();
}

