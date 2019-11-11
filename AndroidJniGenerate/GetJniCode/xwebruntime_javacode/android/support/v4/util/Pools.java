package android.support.v4.util;

public final class Pools {
    public interface Pool {
        Object acquire();

        boolean release(Object arg1);
    }

    public class SimplePool implements Pool {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int arg2) {
            super();
            if(arg2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }

            this.mPool = new Object[arg2];
        }

        public Object acquire() {
            Object v1 = null;
            if(this.mPoolSize > 0) {
                int v0 = this.mPoolSize - 1;
                Object v2 = this.mPool[v0];
                this.mPool[v0] = v1;
                --this.mPoolSize;
                return v2;
            }

            return v1;
        }

        private boolean isInPool(Object arg4) {
            int v1;
            for(v1 = 0; v1 < this.mPoolSize; ++v1) {
                if(this.mPool[v1] == arg4) {
                    return 1;
                }
            }

            return 0;
        }

        public boolean release(Object arg3) {
            if(this.isInPool(arg3)) {
                throw new IllegalStateException("Already in the pool!");
            }

            if(this.mPoolSize < this.mPool.length) {
                this.mPool[this.mPoolSize] = arg3;
                ++this.mPoolSize;
                return 1;
            }

            return 0;
        }
    }

    public class SynchronizedPool extends SimplePool {
        private final Object mLock;

        public SynchronizedPool(int arg1) {
            super(arg1);
            this.mLock = new Object();
        }

        public Object acquire() {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return super.acquire();
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_6;
            }

            throw v1;
        }

        public boolean release(Object arg2) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return super.release(arg2);
            label_6:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_6;
            }

            throw v2;
        }
    }

    private Pools() {
        super();
    }
}

