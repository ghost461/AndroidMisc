package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.widget.Toast;

@JNINamespace(value="content") public class MemoryTracingControllerAndroid {
    public interface TracingHandler {
        void onTracingStopped(String arg1);
    }

    @SuppressLint(value={"ParcelCreator"}) class TracingIntentFilter extends IntentFilter {
        TracingIntentFilter(Context arg3) {
            super();
            this.addAction(arg3.getPackageName() + "." + "XPROFILER_START");
            this.addAction(arg3.getPackageName() + "." + "XPROFILER_STOP");
            this.addAction(arg3.getPackageName() + "." + "PROFILER_LIST_CATEGORIES");
        }
    }

    private static final String ACTION_LIST_CATEGORIES = "PROFILER_LIST_CATEGORIES";
    private static final String ACTION_START = "XPROFILER_START";
    private static final String ACTION_STOP = "XPROFILER_STOP";
    private static final String CATEGORIES_EXTRA = "categories";
    private static final String DEFAULT_CHROME_CATEGORIES_PLACE_HOLDER = "_DEFAULT_CHROME_CATEGORIES";
    private static final String PROFILER_FINISHED_FMT = "Profiler finished.";
    private static final String PROFILER_STARTED_FMT = "Profiler started: %s";
    private static final String RECORD_CONTINUOUSLY_EXTRA = "continuous";
    private static final String TAG = "cr.MemTracingController";
    private final Context mContext;
    private TracingHandler mHandler;
    private final TracingIntentFilter mIntentFilter;
    private volatile boolean mIsPendingStop;
    private volatile boolean mIsTracing;
    private long mMemoryNativeTracingControllerAndroid;
    private boolean mShowToasts;

    public MemoryTracingControllerAndroid(Context arg2) {
        super();
        this.mShowToasts = false;
        this.mContext = arg2;
        this.mIntentFilter = new TracingIntentFilter(arg2);
    }

    public void destroy() {
        long v2 = 0;
        if(this.mMemoryNativeTracingControllerAndroid != v2) {
            this.nativeDestroy(this.mMemoryNativeTracingControllerAndroid);
            this.mMemoryNativeTracingControllerAndroid = v2;
        }
    }

    public void getCategoryGroups() {
        this.initializeNativeControllerIfNeeded();
        if(!this.nativeGetKnownCategoryGroupsAsync(this.mMemoryNativeTracingControllerAndroid)) {
            Log.e("cr.MemTracingController", "Unable to fetch tracing record groups list.", new Object[0]);
        }
    }

    public IntentFilter getIntentFilter() {
        return this.mIntentFilter;
    }

    private void initializeNativeControllerIfNeeded() {
        if(this.mMemoryNativeTracingControllerAndroid == 0) {
            this.mMemoryNativeTracingControllerAndroid = this.nativeInit();
        }
    }

    public boolean isTracing() {
        return this.mIsTracing;
    }

    private void logAndToastError(String arg4) {
        Log.e("cr.MemTracingController", arg4, new Object[0]);
        if(this.mShowToasts) {
            Toast.makeText(this.mContext, ((CharSequence)arg4), 0).show();
        }
    }

    private void logForProfiler(String arg3) {
        Log.i("cr.MemTracingController", arg3, new Object[0]);
    }

    private native void nativeDestroy(long arg1) {
    }

    private native String nativeGetDefaultCategories() {
    }

    private native boolean nativeGetKnownCategoryGroupsAsync(long arg1) {
    }

    private native long nativeInit() {
    }

    private native boolean nativeStartTracing(long arg1, String arg2, String arg3) {
    }

    private native void nativeStopTracing(long arg1) {
    }

    @CalledByNative protected void onTracingStopped(String arg3) {
        if(!this.isTracing()) {
            Log.e("cr.MemTracingController", "Received onTracingStopped, but we aren\'t tracing", new Object[0]);
            return;
        }

        this.logForProfiler("Profiler finished.");
        this.showToast("Profiler finished.");
        if(this.mHandler != null) {
            this.mHandler.onTracingStopped(arg3);
        }

        this.mIsTracing = false;
    }

    public void setTracingHandler(TracingHandler arg1) {
        this.mHandler = arg1;
    }

    private void showToast(String arg3) {
        if(this.mShowToasts) {
            Toast.makeText(this.mContext, ((CharSequence)arg3), 0).show();
        }
    }

    public boolean startTracing(boolean arg4, String arg5, String arg6) {
        this.mShowToasts = arg4;
        if(this.isTracing()) {
            Log.e("cr.MemTracingController", "Received startTracing, but we\'re already tracing", new Object[0]);
            return 0;
        }

        this.initializeNativeControllerIfNeeded();
        if(!this.nativeStartTracing(this.mMemoryNativeTracingControllerAndroid, arg5, arg6.toString())) {
            this.logAndToastError("Failed to start profiler");
            return 0;
        }

        this.logForProfiler(String.format("Profiler started: %s", arg5));
        this.showToast("Profiler started: " + arg5);
        this.mIsTracing = true;
        this.mIsPendingStop = false;
        return 1;
    }

    public void stopTracing() {
        if((this.isTracing()) && !this.mIsPendingStop) {
            this.mIsPendingStop = true;
            this.nativeStopTracing(this.mMemoryNativeTracingControllerAndroid);
        }
    }
}

