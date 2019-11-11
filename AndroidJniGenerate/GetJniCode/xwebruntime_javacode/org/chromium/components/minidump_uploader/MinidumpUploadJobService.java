package org.chromium.components.minidump_uploader;

import android.annotation.TargetApi;
import android.app.job.JobInfo$Builder;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;

@TargetApi(value=21) public abstract class MinidumpUploadJobService extends JobService {
    private static final int JOB_BACKOFF_POLICY = 1;
    private static final int JOB_INITIAL_BACKOFF_TIME_IN_MS = 1800000;
    private static final String TAG = "MinidumpJobService";
    private MinidumpUploader mMinidumpUploader;
    private boolean mRunningJob;
    private final Object mRunningLock;

    static {
    }

    public MinidumpUploadJobService() {
        super();
        this.mRunningLock = new Object();
        this.mRunningJob = false;
    }

    static Object access$000(MinidumpUploadJobService arg0) {
        return arg0.mRunningLock;
    }

    static boolean access$102(MinidumpUploadJobService arg0, boolean arg1) {
        arg0.mRunningJob = arg1;
        return arg1;
    }

    private UploadsFinishedCallback createJobFinishedCallback(JobParameters arg2) {
        return new UploadsFinishedCallback(arg2) {
            public void uploadsFinished(boolean arg5) {
                if(arg5) {
                    Log.i("MinidumpJobService", "Some minidumps remain un-uploaded; rescheduling.", new Object[0]);
                }

                Object v1 = MinidumpUploadJobService.this.mRunningLock;
                __monitor_enter(v1);
                try {
                    MinidumpUploadJobService.this.mRunningJob = false;
                    __monitor_exit(v1);
                }
                catch(Throwable v5) {
                    try {
                    label_17:
                        __monitor_exit(v1);
                    }
                    catch(Throwable v5) {
                        goto label_17;
                    }

                    throw v5;
                }

                MinidumpUploadJobService.this.jobFinished(this.val$params, arg5);
            }
        };
    }

    protected abstract MinidumpUploader createMinidumpUploader(PersistableBundle arg1);

    public void onDestroy() {
        this.mMinidumpUploader = null;
        super.onDestroy();
    }

    public boolean onStartJob(JobParameters arg3) {
        Object v0 = this.mRunningLock;
        __monitor_enter(v0);
        try {
            this.mRunningJob = true;
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
        label_15:
            try {
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_15;
            }

            throw v3;
        }

        this.mMinidumpUploader = this.createMinidumpUploader(arg3.getExtras());
        this.mMinidumpUploader.uploadAllMinidumps(this.createJobFinishedCallback(arg3));
        return 1;
    }

    public boolean onStopJob(JobParameters arg4) {
        Log.i("MinidumpJobService", "Canceling pending uploads due to change in networking status.", new Object[0]);
        boolean v4 = this.mMinidumpUploader.cancelUploads();
        Object v0 = this.mRunningLock;
        __monitor_enter(v0);
        try {
            this.mRunningJob = false;
            __monitor_exit(v0);
            return v4;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v4_1) {
            goto label_13;
        }

        throw v4_1;
    }

    public static void scheduleUpload(JobInfo$Builder arg4) {
        Log.i("MinidumpJobService", "Scheduling upload of all pending minidumps.", new Object[0]);
        ContextUtils.getApplicationContext().getSystemService("jobscheduler").schedule(arg4.setRequiredNetworkType(2).setBackoffCriteria(1800000, 1).build());
    }
}

