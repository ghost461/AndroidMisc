package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.widget.Toast;

@JNINamespace(value="content") public class TracingControllerAndroid {
    class TracingBroadcastReceiver extends BroadcastReceiver {
        TracingBroadcastReceiver(TracingControllerAndroid arg1) {
            TracingControllerAndroid.this = arg1;
            super();
        }

        public void onReceive(Context arg4, Intent arg5) {
            if(arg5.getAction().endsWith("GPU_PROFILER_START")) {
                String v4 = arg5.getStringExtra("categories");
                v4 = TextUtils.isEmpty(((CharSequence)v4)) ? TracingControllerAndroid.this.nativeGetDefaultCategories() : v4.replaceFirst("_DEFAULT_CHROME_CATEGORIES", TracingControllerAndroid.this.nativeGetDefaultCategories());
                String v1 = arg5.getStringExtra("continuous") == null ? "record-until-full" : "record-continuously";
                String v5 = arg5.getStringExtra("file");
                if(v5 != null) {
                    TracingControllerAndroid.this.startTracing(v5, true, v4, v1);
                    return;
                }

                TracingControllerAndroid.this.startTracing(true, v4, v1);
            }
            else {
                if(arg5.getAction().endsWith("GPU_PROFILER_STOP")) {
                    TracingControllerAndroid.this.stopTracing();
                    return;
                }

                if(arg5.getAction().endsWith("GPU_PROFILER_LIST_CATEGORIES")) {
                    TracingControllerAndroid.this.getCategoryGroups();
                    return;
                }

                Log.e("cr.TracingController", "Unexpected intent: %s", new Object[]{arg5});
            }
        }
    }

    @SuppressLint(value={"ParcelCreator"}) class TracingIntentFilter extends IntentFilter {
        TracingIntentFilter(Context arg3) {
            super();
            this.addAction(arg3.getPackageName() + "." + "GPU_PROFILER_START");
            this.addAction(arg3.getPackageName() + "." + "GPU_PROFILER_STOP");
            this.addAction(arg3.getPackageName() + "." + "GPU_PROFILER_LIST_CATEGORIES");
        }
    }

    private static final String ACTION_LIST_CATEGORIES = "GPU_PROFILER_LIST_CATEGORIES";
    private static final String ACTION_START = "GPU_PROFILER_START";
    private static final String ACTION_STOP = "GPU_PROFILER_STOP";
    private static final String CATEGORIES_EXTRA = "categories";
    private static final String DEFAULT_CHROME_CATEGORIES_PLACE_HOLDER = "_DEFAULT_CHROME_CATEGORIES";
    private static final String FILE_EXTRA = "file";
    private static final String PROFILER_FINISHED_FMT = "Profiler finished. Results are in %s.";
    private static final String PROFILER_STARTED_FMT = "Profiler started: %s";
    private static final String RECORD_CONTINUOUSLY_EXTRA = "continuous";
    private static final String TAG = "cr.TracingController";
    private final TracingBroadcastReceiver mBroadcastReceiver;
    private final Context mContext;
    private String mFilename;
    private final TracingIntentFilter mIntentFilter;
    private boolean mIsTracing;
    private long mNativeTracingControllerAndroid;
    private boolean mShowToasts;

    public TracingControllerAndroid(Context arg2) {
        super();
        this.mShowToasts = true;
        this.mContext = arg2;
        this.mBroadcastReceiver = new TracingBroadcastReceiver(this);
        this.mIntentFilter = new TracingIntentFilter(arg2);
    }

    static String access$000(TracingControllerAndroid arg0) {
        return arg0.nativeGetDefaultCategories();
    }

    public void destroy() {
        long v2 = 0;
        if(this.mNativeTracingControllerAndroid != v2) {
            this.nativeDestroy(this.mNativeTracingControllerAndroid);
            this.mNativeTracingControllerAndroid = v2;
        }
    }

    @CalledByNative private static String generateTracingFilePath() {
        if(!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }

        SimpleDateFormat v0 = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
        v0.setTimeZone(TimeZone.getTimeZone("UTC"));
        File v1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        StringBuilder v3 = new StringBuilder();
        v3.append("chrome-profile-results-");
        v3.append(v0.format(new Date()));
        return new File(v1, v3.toString()).getPath();
    }

    public BroadcastReceiver getBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    public void getCategoryGroups() {
        this.initializeNativeControllerIfNeeded();
        if(!this.nativeGetKnownCategoryGroupsAsync(this.mNativeTracingControllerAndroid)) {
            Log.e("cr.TracingController", "Unable to fetch tracing record groups list.", new Object[0]);
        }
    }

    public IntentFilter getIntentFilter() {
        return this.mIntentFilter;
    }

    public String getOutputPath() {
        return this.mFilename;
    }

    private void initializeNativeControllerIfNeeded() {
        if(this.mNativeTracingControllerAndroid == 0) {
            this.mNativeTracingControllerAndroid = this.nativeInit();
        }
    }

    public boolean isTracing() {
        return this.mIsTracing;
    }

    private void logAndToastError(String arg4) {
        Log.e("cr.TracingController", arg4, new Object[0]);
        if(this.mShowToasts) {
            Toast.makeText(this.mContext, ((CharSequence)arg4), 0).show();
        }
    }

    private void logForProfiler(String arg3) {
        Log.i("cr.TracingController", arg3, new Object[0]);
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

    private native void nativeStopTracing(long arg1, String arg2) {
    }

    @CalledByNative protected void onTracingStopped() {
        if(!this.isTracing()) {
            Log.e("cr.TracingController", "Received onTracingStopped, but we aren\'t tracing", new Object[0]);
            return;
        }

        this.logForProfiler(String.format("Profiler finished. Results are in %s.", this.mFilename));
        this.showToast(String.format("Profiler finished. Results are in %1$s.", this.mFilename));
        this.mIsTracing = false;
        this.mFilename = null;
    }

    public void registerReceiver(Context arg3) {
        arg3.registerReceiver(this.getBroadcastReceiver(), this.getIntentFilter());
    }

    private void showToast(String arg3) {
        if(this.mShowToasts) {
            Toast.makeText(this.mContext, ((CharSequence)arg3), 0).show();
        }
    }

    public boolean startTracing(String arg4, boolean arg5, String arg6, String arg7) {
        this.mShowToasts = arg5;
        if(this.isTracing()) {
            Log.e("cr.TracingController", "Received startTracing, but we\'re already tracing", new Object[0]);
            return 0;
        }

        this.initializeNativeControllerIfNeeded();
        if(!this.nativeStartTracing(this.mNativeTracingControllerAndroid, arg6, arg7.toString())) {
            this.logAndToastError("Failed to start profiler");
            return 0;
        }

        this.logForProfiler(String.format("Profiler started: %s", arg6));
        this.showToast("Profiler started: " + arg6);
        this.mFilename = arg4;
        this.mIsTracing = true;
        return 1;
    }

    public boolean startTracing(boolean arg3, String arg4, String arg5) {
        this.mShowToasts = arg3;
        String v0 = TracingControllerAndroid.generateTracingFilePath();
        if(v0 == null) {
            this.logAndToastError("Can\'t start profiler because external storage is not ready");
        }

        return this.startTracing(v0, arg3, arg4, arg5);
    }

    public void stopTracing() {
        if(this.isTracing()) {
            this.nativeStopTracing(this.mNativeTracingControllerAndroid, this.mFilename);
        }
    }

    public void unregisterReceiver(Context arg2) {
        arg2.unregisterReceiver(this.getBroadcastReceiver());
    }
}

