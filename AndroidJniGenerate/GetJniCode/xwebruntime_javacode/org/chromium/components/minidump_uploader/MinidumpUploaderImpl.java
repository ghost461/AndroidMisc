package org.chromium.components.minidump_uploader;

import java.io.File;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

public class MinidumpUploaderImpl implements MinidumpUploader {
    class UploadRunnable implements Runnable {
        private final UploadsFinishedCallback mUploadsFinishedCallback;

        public UploadRunnable(MinidumpUploaderImpl arg1, UploadsFinishedCallback arg2) {
            MinidumpUploaderImpl.this = arg1;
            super();
            this.mUploadsFinishedCallback = arg2;
        }

        public void run() {
            File v0 = MinidumpUploaderImpl.this.mDelegate.getCrashParentDir();
            boolean v2 = false;
            if(!v0.isDirectory()) {
                Log.e("MinidumpUploaderImpl", "Parent crash directory doesn\'t exist!", new Object[0]);
                this.mUploadsFinishedCallback.uploadsFinished(false);
                return;
            }

            CrashFileManager v0_1 = MinidumpUploaderImpl.this.createCrashFileManager(v0);
            if(!v0_1.crashDirectoryExists()) {
                Log.e("MinidumpUploaderImpl", "Crash directory doesn\'t exist!", new Object[0]);
                this.mUploadsFinishedCallback.uploadsFinished(false);
                return;
            }

            int v1 = 3;
            File[] v3 = v0_1.getMinidumpsReadyForUpload(v1);
            Log.i("MinidumpUploaderImpl", "Attempting to upload %d minidumps.", new Object[]{Integer.valueOf(v3.length)});
            int v4 = v3.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                File v7 = v3[v5];
                Log.i("MinidumpUploaderImpl", "Attempting to upload " + v7.getName(), new Object[0]);
                int v8 = MinidumpUploaderImpl.this.createMinidumpUploadCallable(v7, v0_1.getCrashUploadLogFile()).call().intValue();
                if(v8 == 0) {
                    MinidumpUploaderImpl.this.mDelegate.recordUploadSuccess(v7);
                }
                else if(v8 == 1 && CrashFileManager.readAttemptNumber(v7.getName()) + 1 == v1) {
                    MinidumpUploaderImpl.this.mDelegate.recordUploadFailure(v7);
                }

                if(MinidumpUploaderImpl.this.mCancelUpload) {
                    return;
                }

                if(v8 == 1 && CrashFileManager.tryIncrementAttemptNumber(v7) == null) {
                    Log.w("MinidumpUploaderImpl", "Failed to increment attempt number of " + v7, new Object[0]);
                }
            }

            v0_1.cleanOutAllNonFreshMinidumpFiles();
            if(v0_1.getMinidumpsReadyForUpload(v1).length > 0) {
                v2 = true;
            }

            this.mUploadsFinishedCallback.uploadsFinished(v2);
        }
    }

    @VisibleForTesting public static final int MAX_UPLOAD_TRIES_ALLOWED = 3;
    private static final String TAG = "MinidumpUploaderImpl";
    private volatile boolean mCancelUpload;
    @VisibleForTesting protected final MinidumpUploaderDelegate mDelegate;
    private Thread mWorkerThread;

    @VisibleForTesting public MinidumpUploaderImpl(MinidumpUploaderDelegate arg2) {
        super();
        this.mCancelUpload = false;
        this.mDelegate = arg2;
    }

    static boolean access$000(MinidumpUploaderImpl arg0) {
        return arg0.mCancelUpload;
    }

    static Thread access$100(MinidumpUploaderImpl arg0) {
        return arg0.mWorkerThread;
    }

    public boolean cancelUploads() {
        this.mCancelUpload = true;
        return 1;
    }

    @VisibleForTesting public CrashFileManager createCrashFileManager(File arg2) {
        return new CrashFileManager(arg2);
    }

    @VisibleForTesting public MinidumpUploadCallable createMinidumpUploadCallable(File arg3, File arg4) {
        return new MinidumpUploadCallable(arg3, arg4, this.mDelegate.createCrashReportingPermissionManager());
    }

    @VisibleForTesting public void joinWorkerThreadForTesting() throws InterruptedException {
        this.mWorkerThread.join();
    }

    public void uploadAllMinidumps(UploadsFinishedCallback arg3) {
        ThreadUtils.assertOnUiThread();
        if(this.mWorkerThread != null) {
            throw new RuntimeException("A given minidump uploader instance should never be launched more than once.");
        }

        this.mWorkerThread = new Thread(new UploadRunnable(this, arg3), "MinidumpUploader-WorkerThread");
        this.mCancelUpload = false;
        this.mDelegate.prepareToUploadMinidumps(new Runnable() {
            public void run() {
                ThreadUtils.assertOnUiThread();
                MinidumpUploaderImpl.this.mWorkerThread.start();
            }
        });
    }
}

