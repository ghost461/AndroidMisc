package org.chromium.base;

public class NonThreadSafe {
    private Long mThreadId;

    public NonThreadSafe() {
        super();
        this.ensureThreadIdAssigned();
    }

    public boolean calledOnValidThread() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            this.ensureThreadIdAssigned();
            v0_1 = this.mThreadId.equals(Long.valueOf(Thread.currentThread().getId()));
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    @VisibleForTesting public void detachFromThread() {
        __monitor_enter(this);
        Long v0 = null;
        try {
            this.mThreadId = v0;
        }
        catch(Throwable v0_1) {
            __monitor_exit(this);
            throw v0_1;
        }

        __monitor_exit(this);
    }

    private void ensureThreadIdAssigned() {
        if(this.mThreadId == null) {
            this.mThreadId = Long.valueOf(Thread.currentThread().getId());
        }
    }
}

