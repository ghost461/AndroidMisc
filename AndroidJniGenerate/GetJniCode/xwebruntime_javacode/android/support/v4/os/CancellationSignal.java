package android.support.v4.os;

import android.os.Build$VERSION;

public final class CancellationSignal {
    public interface OnCancelListener {
        void onCancel();
    }

    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;

    public CancellationSignal() {
        super();
    }

    public void cancel() {
        Object v1;
        OnCancelListener v0_1;
        __monitor_enter(this);
        try {
            if(this.mIsCanceled) {
                __monitor_exit(this);
                return;
            }

            this.mIsCanceled = true;
            this.mCancelInProgress = true;
            v0_1 = this.mOnCancelListener;
            v1 = this.mCancellationSignalObj;
            __monitor_exit(this);
            if(v0_1 == null) {
                goto label_17;
            }
        }
        catch(Throwable v0) {
            try {
            label_40:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_40;
            }

            throw v0;
        }

        try {
            v0_1.onCancel();
        label_17:
            if(v1 != null && Build$VERSION.SDK_INT >= 16) {
                ((android.os.CancellationSignal)v1).cancel();
                goto label_31;
            label_16:
                goto label_23;
            }

            goto label_31;
        }
        catch(Throwable v0) {
            goto label_16;
        }

    label_23:
        __monitor_enter(this);
        try {
            this.mCancelInProgress = false;
            this.notifyAll();
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_29;
        }

        throw v0;
        try {
        label_29:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_29;
        }

        throw v0;
    label_31:
        __monitor_enter(this);
        try {
            this.mCancelInProgress = false;
            this.notifyAll();
            __monitor_exit(this);
            return;
        label_37:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_37;
        }

        throw v0;
    }

    public Object getCancellationSignalObject() {
        if(Build$VERSION.SDK_INT < 16) {
            return null;
        }

        __monitor_enter(this);
        try {
            if(this.mCancellationSignalObj == null) {
                this.mCancellationSignalObj = new android.os.CancellationSignal();
                if(this.mIsCanceled) {
                    this.mCancellationSignalObj.cancel();
                }
            }

            __monitor_exit(this);
            return this.mCancellationSignalObj;
        label_19:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_19;
        }

        throw v0;
    }

    public boolean isCanceled() {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.mIsCanceled;
        label_5:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_5;
        }

        throw v0;
    }

    public void setOnCancelListener(OnCancelListener arg2) {
        __monitor_enter(this);
        try {
            this.waitForCancelFinishedLocked();
            if(this.mOnCancelListener == arg2) {
                __monitor_exit(this);
                return;
            }

            this.mOnCancelListener = arg2;
            if(this.mIsCanceled) {
                if(arg2 == null) {
                }
                else {
                    __monitor_exit(this);
                    goto label_12;
                }
            }

            goto label_14;
        }
        catch(Throwable v2) {
            goto label_17;
        }

    label_12:
        arg2.onCancel();
        return;
        try {
        label_14:
            __monitor_exit(this);
            return;
        label_17:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_17;
        }

        throw v2;
    }

    public void throwIfCanceled() {
        if(this.isCanceled()) {
            throw new OperationCanceledException();
        }
    }

    private void waitForCancelFinishedLocked() {
        while(this.mCancelInProgress) {
            try {
                this.wait();
            }
            catch(InterruptedException ) {
            }
        }
    }
}

