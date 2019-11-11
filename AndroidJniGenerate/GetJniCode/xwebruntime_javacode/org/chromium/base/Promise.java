package org.chromium.base;

import android.os.Handler;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Promise {
    public interface AsyncFunction {
        Promise apply(Object arg1);
    }

    public interface Function {
        Object apply(Object arg1);
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface PromiseState {
    }

    public class UnhandledRejectionException extends RuntimeException {
        public UnhandledRejectionException(String arg1, Throwable arg2) {
            super(arg1, arg2);
        }
    }

    private static final int FULFILLED = 1;
    private static final int REJECTED = 2;
    private static final int UNFULFILLED;
    private final List mFulfillCallbacks;
    private final Handler mHandler;
    private final List mRejectCallbacks;
    private Exception mRejectReason;
    private Object mResult;
    private int mState;
    private final Thread mThread;
    private boolean mThrowingRejectionHandler;

    static {
    }

    public Promise() {
        super();
        this.mState = 0;
        this.mFulfillCallbacks = new LinkedList();
        this.mRejectCallbacks = new LinkedList();
        this.mThread = Thread.currentThread();
        this.mHandler = new Handler();
    }

    private void checkThread() {
    }

    public void except(Callback arg1) {
        this.checkThread();
        this.exceptInner(arg1);
    }

    private void exceptInner(Callback arg3) {
        if(this.mState == 2) {
            this.postCallbackToLooper(arg3, this.mRejectReason);
        }
        else if(this.mState == 0) {
            this.mRejectCallbacks.add(arg3);
        }
    }

    public void fulfill(Object arg3) {
        this.checkThread();
        this.mState = 1;
        this.mResult = arg3;
        Iterator v0 = this.mFulfillCallbacks.iterator();
        while(v0.hasNext()) {
            this.postCallbackToLooper(v0.next(), arg3);
        }

        this.mFulfillCallbacks.clear();
    }

    public static Promise fulfilled(Object arg1) {
        Promise v0 = new Promise();
        v0.fulfill(arg1);
        return v0;
    }

    public Object getResult() {
        return this.mResult;
    }

    public boolean isFulfilled() {
        this.checkThread();
        boolean v1 = true;
        if(this.mState == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean isRejected() {
        this.checkThread();
        boolean v0 = this.mState == 2 ? true : false;
        return v0;
    }

    static final void lambda$postCallbackToLooper$3$Promise(Callback arg0, Object arg1) {
        arg0.onResult(arg1);
    }

    static final void lambda$then$0$Promise(Exception arg2) {
        throw new UnhandledRejectionException("Promise was rejected without a rejection handler.", ((Throwable)arg2));
    }

    static final void lambda$then$1$Promise(Promise arg0, Function arg1, Object arg2) {
        try {
            arg0.fulfill(arg1.apply(arg2));
        }
        catch(Exception v1) {
            arg0.reject(v1);
        }
    }

    static final void lambda$then$2$Promise(AsyncFunction arg1, Promise arg2, Object arg3) {
        try {
            Promise v1_1 = arg1.apply(arg3);
            arg2.getClass();
            Callback v3 = Promise$$Lambda$6.get$Lambda(arg2);
            arg2.getClass();
            v1_1.then(v3, Promise$$Lambda$7.get$Lambda(arg2));
        }
        catch(Exception v1) {
            arg2.reject(v1);
        }
    }

    private void postCallbackToLooper(Callback arg3, Object arg4) {
        this.mHandler.post(new Promise$$Lambda$5(arg3, arg4));
    }

    public void reject(Exception arg3) {
        this.checkThread();
        this.mState = 2;
        this.mRejectReason = arg3;
        Iterator v0 = this.mRejectCallbacks.iterator();
        while(v0.hasNext()) {
            this.postCallbackToLooper(v0.next(), arg3);
        }

        this.mRejectCallbacks.clear();
    }

    public void reject() {
        this.reject(null);
    }

    public void then(Callback arg1, Callback arg2) {
        this.checkThread();
        this.thenInner(arg1);
        this.exceptInner(arg2);
    }

    public Promise then(AsyncFunction arg3) {
        this.checkThread();
        Promise v0 = new Promise();
        this.thenInner(new Promise$$Lambda$3(arg3, v0));
        v0.getClass();
        this.exceptInner(Promise$$Lambda$4.get$Lambda(v0));
        return v0;
    }

    public Promise then(Function arg3) {
        this.checkThread();
        Promise v0 = new Promise();
        this.thenInner(new Promise$$Lambda$1(v0, arg3));
        v0.getClass();
        this.exceptInner(Promise$$Lambda$2.get$Lambda(v0));
        return v0;
    }

    public void then(Callback arg2) {
        this.checkThread();
        if(this.mThrowingRejectionHandler) {
            this.thenInner(arg2);
            return;
        }

        this.then(arg2, Promise$$Lambda$0.$instance);
        this.mThrowingRejectionHandler = true;
    }

    private void thenInner(Callback arg3) {
        if(this.mState == 1) {
            this.postCallbackToLooper(arg3, this.mResult);
        }
        else if(this.mState == 0) {
            this.mFulfillCallbacks.add(arg3);
        }
    }
}

