package android.support.v4.view;

import android.content.Context;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.util.Pools$SynchronizedPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ArrayBlockingQueue;

public final class AsyncLayoutInflater {
    class android.support.v4.view.AsyncLayoutInflater$1 implements Handler$Callback {
        android.support.v4.view.AsyncLayoutInflater$1(AsyncLayoutInflater arg1) {
            AsyncLayoutInflater.this = arg1;
            super();
        }

        public boolean handleMessage(Message arg5) {
            Object v5 = arg5.obj;
            if(((InflateRequest)v5).view == null) {
                ((InflateRequest)v5).view = AsyncLayoutInflater.this.mInflater.inflate(((InflateRequest)v5).resid, ((InflateRequest)v5).parent, false);
            }

            ((InflateRequest)v5).callback.onInflateFinished(((InflateRequest)v5).view, ((InflateRequest)v5).resid, ((InflateRequest)v5).parent);
            AsyncLayoutInflater.this.mInflateThread.releaseRequest(((InflateRequest)v5));
            return 1;
        }
    }

    class BasicInflater extends LayoutInflater {
        private static final String[] sClassPrefixList;

        static {
            BasicInflater.sClassPrefixList = new String[]{"android.widget.", "android.webkit.", "android.app."};
        }

        BasicInflater(Context arg1) {
            super(arg1);
        }

        public LayoutInflater cloneInContext(Context arg2) {
            return new BasicInflater(arg2);
        }

        protected View onCreateView(String arg5, AttributeSet arg6) throws ClassNotFoundException {
            View v3_1;
            String[] v0 = BasicInflater.sClassPrefixList;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                String v3 = v0[v2];
                try {
                    v3_1 = this.createView(arg5, v3, arg6);
                    if(v3_1 == null) {
                        goto label_8;
                    }
                }
                catch(ClassNotFoundException ) {
                    goto label_8;
                }

                return v3_1;
            label_8:
            }

            return super.onCreateView(arg5, arg6);
        }
    }

    class InflateRequest {
        OnInflateFinishedListener callback;
        AsyncLayoutInflater inflater;
        ViewGroup parent;
        int resid;
        View view;

        InflateRequest() {
            super();
        }
    }

    class InflateThread extends Thread {
        private ArrayBlockingQueue mQueue;
        private SynchronizedPool mRequestPool;
        private static final InflateThread sInstance;

        static {
            InflateThread.sInstance = new InflateThread();
            InflateThread.sInstance.start();
        }

        private InflateThread() {
            super();
            this.mQueue = new ArrayBlockingQueue(10);
            this.mRequestPool = new SynchronizedPool(10);
        }

        public void enqueue(InflateRequest arg3) {
            try {
                this.mQueue.put(arg3);
                return;
            }
            catch(InterruptedException v3) {
                throw new RuntimeException("Failed to enqueue async inflate request", ((Throwable)v3));
            }
        }

        public static InflateThread getInstance() {
            return InflateThread.sInstance;
        }

        public InflateRequest obtainRequest() {
            InflateRequest v0_1;
            Object v0 = this.mRequestPool.acquire();
            if(v0 == null) {
                v0_1 = new InflateRequest();
            }

            return v0_1;
        }

        public void releaseRequest(InflateRequest arg3) {
            arg3.callback = null;
            arg3.inflater = null;
            arg3.parent = null;
            arg3.resid = 0;
            arg3.view = null;
            this.mRequestPool.release(arg3);
        }

        public void run() {
            while(true) {
                this.runInner();
            }
        }

        public void runInner() {
            Object v0_1;
            try {
                v0_1 = this.mQueue.take();
            }
            catch(InterruptedException v0) {
                Log.w("AsyncLayoutInflater", ((Throwable)v0));
                return;
            }

            try {
                ((InflateRequest)v0_1).view = ((InflateRequest)v0_1).inflater.mInflater.inflate(((InflateRequest)v0_1).resid, ((InflateRequest)v0_1).parent, false);
            }
            catch(RuntimeException v2) {
                Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", ((Throwable)v2));
            }

            Message.obtain(((InflateRequest)v0_1).inflater.mHandler, 0, v0_1).sendToTarget();
        }
    }

    public interface OnInflateFinishedListener {
        void onInflateFinished(View arg1, int arg2, ViewGroup arg3);
    }

    private static final String TAG = "AsyncLayoutInflater";
    Handler mHandler;
    private Handler$Callback mHandlerCallback;
    InflateThread mInflateThread;
    LayoutInflater mInflater;

    public AsyncLayoutInflater(@NonNull Context arg2) {
        super();
        this.mHandlerCallback = new android.support.v4.view.AsyncLayoutInflater$1(this);
        this.mInflater = new BasicInflater(arg2);
        this.mHandler = new Handler(this.mHandlerCallback);
        this.mInflateThread = InflateThread.getInstance();
    }

    @UiThread public void inflate(@LayoutRes int arg2, @Nullable ViewGroup arg3, @NonNull OnInflateFinishedListener arg4) {
        if(arg4 == null) {
            throw new NullPointerException("callback argument may not be null!");
        }

        InflateRequest v0 = this.mInflateThread.obtainRequest();
        v0.inflater = this;
        v0.resid = arg2;
        v0.parent = arg3;
        v0.callback = arg4;
        this.mInflateThread.enqueue(v0);
    }
}

