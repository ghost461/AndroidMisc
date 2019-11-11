package org.chromium.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.metrics.RecordHistogram;

@MainDex public abstract class PathUtils {
    class Holder {
        private static final String[] DIRECTORY_PATHS;

        static {
            Holder.DIRECTORY_PATHS = PathUtils.getOrComputeDirectoryPaths();
        }

        private Holder() {
            super();
        }

        static String[] access$200() {
            return Holder.DIRECTORY_PATHS;
        }
    }

    private static final int CACHE_DIRECTORY = 2;
    private static final int DATA_DIRECTORY = 0;
    private static final int NUM_DIRECTORIES = 3;
    private static final int THUMBNAIL_DIRECTORY = 1;
    private static final String THUMBNAIL_DIRECTORY_NAME = "textures";
    private static String sCacheSubDirectory;
    private static String sDataDirectorySuffix;
    private static AsyncTask sDirPathFetchTask;
    private static final AtomicBoolean sInitializationStarted;

    static {
        PathUtils.sInitializationStarted = new AtomicBoolean();
    }

    private PathUtils() {
        super();
    }

    static String[] access$000() {
        return PathUtils.getOrComputeDirectoryPaths();
    }

    static String[] access$100() {
        return PathUtils.setPrivateDataDirectorySuffixInternal();
    }

    @CalledByNative public static String getCacheDirectory() {
        return PathUtils.getDirectoryPath(2);
    }

    @CalledByNative public static String getDataDirectory() {
        return PathUtils.getDirectoryPath(0);
    }

    private static String getDirectoryPath(int arg1) {
        return Holder.access$200()[arg1];
    }

    @CalledByNative private static String getDownloadsDirectory() {
        String v4;
        StrictModeContext v0 = StrictModeContext.allowDiskReads();
        Throwable v1 = null;
        try {
            long v2_1 = SystemClock.elapsedRealtime();
            v4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            RecordHistogram.recordTimesHistogram("Android.StrictMode.DownloadsDir", SystemClock.elapsedRealtime() - v2_1, TimeUnit.MILLISECONDS);
            if(v0 == null) {
                return v4;
            }
        }
        catch(Throwable v2) {
        }
        catch(Throwable v1) {
            try {
                throw v1;
            }
            catch(Throwable v2) {
                if(v0 != null) {
                    if(v1 != null) {
                        try {
                            v0.close();
                        }
                        catch(Throwable v0_1) {
                            ThrowableExtension.addSuppressed(v1, v0_1);
                        }
                    }
                    else {
                        v0.close();
                    }

                    goto label_27;
                }
                else {
                label_27:
                    throw v2;
                }

                return v4;
            }
        }

        v0.close();
        return v4;
    }

    @CalledByNative public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    @CalledByNative private static String getNativeLibraryDirectory() {
        ApplicationInfo v0 = ContextUtils.getApplicationContext().getApplicationInfo();
        if((v0.flags & 0x80) == 0) {
            if((v0.flags & 1) == 0) {
            }
            else {
                return "/system/lib/";
            }
        }

        return v0.nativeLibraryDir;
    }

    private static String[] getOrComputeDirectoryPaths() {
        String[] v2_1;
        StrictModeContext v1;
        String[] v0 = null;
        try {
            if(!PathUtils.sDirPathFetchTask.cancel(false)) {
                goto label_28;
            }

            v1 = StrictModeContext.allowDiskWrites();
        }
        catch(ExecutionException ) {
            return v0;
        }

        try {
            v2_1 = PathUtils.setPrivateDataDirectorySuffixInternal();
            if(v1 == null) {
                return v2_1;
            }

            goto label_8;
        }
        catch(Throwable v2) {
            v3 = ((Throwable)v0);
        }
        catch(Throwable v2) {
            try {
                throw v2;
            }
            catch(Throwable v3) {
                Throwable v4 = v3;
                v3 = v2;
                v2 = v4;
            }
        }

        if(v1 == null) {
            goto label_27;
        }
        else if(v3 != null) {
            try {
                v1.close();
                goto label_27;
            }
            catch(ExecutionException ) {
            }
            catch(Throwable v1_1) {
                try {
                    ThrowableExtension.addSuppressed(v3, v1_1);
                    goto label_27;
                label_26:
                    v1.close();
                label_27:
                    throw v2;
                label_8:
                    v1.close();
                }
                catch(ExecutionException ) {
                    return v0;
                }

                return v2_1;
                try {
                label_28:
                    return PathUtils.sDirPathFetchTask.get();
                }
                catch(ExecutionException ) {
                    return v0;
                }
            }
        }
        else {
            goto label_26;
        }

        return v2_1;
    }

    @CalledByNative public static String getThumbnailCacheDirectory() {
        return PathUtils.getDirectoryPath(1);
    }

    public static void setPrivateDataDirectorySuffix(String arg1) {
        PathUtils.setPrivateDataDirectorySuffix(arg1, null);
    }

    public static void setPrivateDataDirectorySuffix(String arg2, String arg3) {
        if(!PathUtils.sInitializationStarted.getAndSet(true)) {
            PathUtils.sDataDirectorySuffix = arg2;
            PathUtils.sCacheSubDirectory = arg3;
            PathUtils.sDirPathFetchTask = new AsyncTask() {
                protected Object doInBackground(Object[] arg1) {
                    return this.doInBackground(((Void[])arg1));
                }

                protected String[] doInBackground(Void[] arg1) {
                    return PathUtils.setPrivateDataDirectorySuffixInternal();
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private static String[] setPrivateDataDirectorySuffixInternal() {
        String[] v0 = new String[3];
        Context v1 = ContextUtils.getApplicationContext();
        v0[0] = v1.getDir(PathUtils.sDataDirectorySuffix, 0).getPath();
        v0[1] = v1.getDir("textures", 0).getPath();
        if(v1.getCacheDir() != null) {
            int v3 = 2;
            v0[v3] = PathUtils.sCacheSubDirectory == null ? v1.getCacheDir().getPath() : new File(v1.getCacheDir(), PathUtils.sCacheSubDirectory).getPath();
        }

        return v0;
    }
}

