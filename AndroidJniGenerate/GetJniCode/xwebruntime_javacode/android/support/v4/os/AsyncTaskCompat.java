package android.support.v4.os;

import android.os.AsyncTask;

@Deprecated public final class AsyncTaskCompat {
    private AsyncTaskCompat() {
        super();
    }

    @Deprecated public static AsyncTask executeParallel(AsyncTask arg1, Object[] arg2) {
        if(arg1 == null) {
            throw new IllegalArgumentException("task can not be null");
        }

        arg1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, arg2);
        return arg1;
    }
}

