package org.chromium.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.AsyncTask$Status;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.zip.ZipFile;

public class ResourceExtractor {
    class org.chromium.base.ResourceExtractor$1 {
    }

    class ExtractTask extends AsyncTask {
        private static final int BUFFER_SIZE = 0x4000;
        private final List mCompletionCallbacks;

        ExtractTask(ResourceExtractor arg1, org.chromium.base.ResourceExtractor$1 arg2) {
            this(arg1);
        }

        private ExtractTask(ResourceExtractor arg1) {
            ResourceExtractor.this = arg1;
            super();
            this.mCompletionCallbacks = new ArrayList();
        }

        static List access$700(ExtractTask arg0) {
            return arg0.mCompletionCallbacks;
        }

        @TargetApi(value=18) private void beginTraceSection(String arg3) {
            if(Build$VERSION.SDK_INT < 18) {
                return;
            }

            Trace.beginSection(arg3);
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected Void doInBackground(Void[] arg1) {
            this.beginTraceSection("ResourceExtractor.ExtractTask.doInBackground");
            try {
                this.doInBackgroundImpl();
            }
            catch(Throwable v1) {
                this.endTraceSection();
                throw v1;
            }

            this.endTraceSection();
            return null;
        }

        private void doInBackgroundImpl() {
            InputStream v10_1;
            File v12;
            File v0 = ResourceExtractor.this.getOutputDir();
            File v1 = ResourceExtractor.this.getAppDataDir();
            if(!v0.exists() && !v0.mkdirs()) {
                Log.e("cr.base", "Unable to create pak resources directory!", new Object[0]);
                return;
            }

            this.beginTraceSection("checkPakTimeStamp");
            long v4 = this.getApkVersion();
            SharedPreferences v2 = ContextUtils.getAppSharedPreferences();
            long v7 = 0;
            int v6 = Long.compare(v4, v2.getLong("org.chromium.base.ResourceExtractor.Version", v7)) != 0 ? 1 : 0;
            this.endTraceSection();
            if(v6 != 0) {
                ResourceExtractor.this.deleteFiles();
                v2.edit().putLong("org.chromium.base.ResourceExtractor.Version", v4).apply();
            }

            this.beginTraceSection("WalkAssets");
            byte[] v2_1 = new byte[0x4000];
            try {
                ResourceEntry[] v4_1 = ResourceExtractor.sResourcesToExtract;
                int v5 = v4_1.length;
                v6 = 0;
                while(true) {
                label_42:
                    if(v6 >= v5) {
                        goto label_80;
                    }

                    ResourceEntry v10 = v4_1[v6];
                    File v11 = ResourceExtractor.isAppDataFile(v10.extractedFileName) ? v1 : v0;
                    v12 = new File(v11, v10.extractedFileName);
                    if(v12.length() != v7) {
                    }
                    else {
                        this.beginTraceSection("ExtractResource");
                        v10_1 = ResourceExtractor.sInterceptor == null || !ResourceExtractor.sInterceptor.shouldInterceptLoadRequest(v10.extractedFileName) ? ResourceExtractor.this.mContext.getResources().openRawResource(v10.resourceId) : ResourceExtractor.sInterceptor.openRawResource(v10.extractedFileName);
                        break;
                    }

                    goto label_75;
                }
            }
            catch(Throwable v0_1) {
                goto label_95;
            }
            catch(IOException v0_2) {
                goto label_85;
            }

            try {
                this.extractResourceHelper(v10_1, v12, v2_1);
                goto label_74;
            }
            catch(Throwable v0_1) {
                try {
                    this.endTraceSection();
                    throw v0_1;
                label_74:
                    this.endTraceSection();
                label_75:
                    ++v6;
                    goto label_42;
                }
                catch(Throwable v0_1) {
                    goto label_95;
                }
                catch(IOException v0_2) {
                    goto label_85;
                }
            }

        label_80:
            this.endTraceSection();
            return;
            try {
            label_85:
                Log.w("cr.base", "Exception unpacking required pak resources: %s", new Object[]{v0_2.getMessage()});
                ResourceExtractor.this.deleteFiles();
            }
            catch(Throwable v0_1) {
                goto label_95;
            }

            this.endTraceSection();
            return;
        label_95:
            this.endTraceSection();
            throw v0_1;
        }

        @TargetApi(value=18) private void endTraceSection() {
            if(Build$VERSION.SDK_INT < 18) {
                return;
            }

            Trace.endSection();
        }

        private void extractResourceHelper(InputStream arg6, File arg7, byte[] arg8) throws IOException {
            FileOutputStream v1;
            FileOutputStream v0 = null;
            try {
                v1 = new FileOutputStream(arg7);
            }
            catch(Throwable v7) {
                v1 = v0;
                goto label_30;
            }

            try {
                Log.i("cr.base", "Extracting resource %s", new Object[]{arg7});
                while(true) {
                    int v7_1 = arg6.read(arg8, 0, 0x4000);
                    if(v7_1 == -1) {
                        break;
                    }

                    ((OutputStream)v1).write(arg8, 0, v7_1);
                }
            }
            catch(Throwable v7) {
                goto label_30;
            }

            if(v1 != null) {
                try {
                    ((OutputStream)v1).close();
                }
                catch(Throwable v7) {
                    if(arg6 != null) {
                        arg6.close();
                    }

                    throw v7;
                }
            }

            if(arg6 != null) {
                arg6.close();
            }

            return;
        label_30:
            if(v1 != null) {
                try {
                    ((OutputStream)v1).close();
                }
                catch(Throwable v7) {
                    if(arg6 != null) {
                        arg6.close();
                    }

                    throw v7;
                }
            }

            if(arg6 != null) {
                arg6.close();
            }

            throw v7;
        }

        private long getApkVersion() {
            PackageManager v0 = ResourceExtractor.this.mContext.getPackageManager();
            try {
                return v0.getPackageInfo(ResourceExtractor.this.mContext.getPackageName(), 0).lastUpdateTime;
            }
            catch(PackageManager$NameNotFoundException v0_1) {
                throw new RuntimeException(((Throwable)v0_1));
            }
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Void)arg1));
        }

        protected void onPostExecute(Void arg1) {
            this.beginTraceSection("ResourceExtractor.ExtractTask.onPostExecute");
            try {
                this.onPostExecuteImpl();
            }
            catch(Throwable v1) {
                this.endTraceSection();
                throw v1;
            }

            this.endTraceSection();
        }

        private void onPostExecuteImpl() {
            int v0;
            for(v0 = 0; v0 < this.mCompletionCallbacks.size(); ++v0) {
                this.mCompletionCallbacks.get(v0).run();
            }

            this.mCompletionCallbacks.clear();
        }
    }

    public final class ResourceEntry {
        public final String extractedFileName;
        public final String pathWithinApk;
        public final int resourceId;

        public ResourceEntry(int arg1, String arg2, String arg3) {
            super();
            this.resourceId = arg1;
            this.pathWithinApk = arg2;
            this.extractedFileName = arg3;
        }
    }

    public interface ResourceInterceptor {
        InputStream openRawResource(String arg1);

        boolean shouldInterceptLoadRequest(String arg1);
    }

    private static final String APP_VERSION_PREF = "org.chromium.base.ResourceExtractor.Version";
    private static final int BUFFER_SIZE = 0x4000;
    private static final String ICU_DATA_FILENAME = "icudtl.dat";
    private static final String LIBRARY_DIR = "native_libraries";
    public static final boolean PLATFORM_REQUIRES_NATIVE_FALLBACK_EXTRACTION = false;
    private static final String TAG = "cr.base";
    private static final String V8_NATIVES_DATA_FILENAME = "natives_blob.bin";
    private static final String V8_SNAPSHOT_DATA_FILENAME = "snapshot_blob.bin";
    private final Context mContext;
    private ExtractTask mExtractTask;
    private static ResourceExtractor sInstance;
    private static ResourceInterceptor sInterceptor;
    private static ResourceEntry[] sResourcesToExtract;

    static {
        boolean v0 = Build$VERSION.SDK_INT <= 19 ? true : false;
        ResourceExtractor.PLATFORM_REQUIRES_NATIVE_FALLBACK_EXTRACTION = v0;
        ResourceExtractor.sResourcesToExtract = new ResourceEntry[0];
    }

    private ResourceExtractor(Context arg1) {
        super();
        this.mContext = arg1.getApplicationContext();
    }

    static File access$000(ResourceExtractor arg0) {
        return arg0.getOutputDir();
    }

    static File access$100(ResourceExtractor arg0) {
        return arg0.getAppDataDir();
    }

    static void access$200(ResourceExtractor arg0) {
        arg0.deleteFiles();
    }

    static ResourceEntry[] access$300() {
        return ResourceExtractor.sResourcesToExtract;
    }

    static boolean access$400(String arg0) {
        return ResourceExtractor.isAppDataFile(arg0);
    }

    static ResourceInterceptor access$500() {
        return ResourceExtractor.sInterceptor;
    }

    static Context access$600(ResourceExtractor arg0) {
        return arg0.mContext;
    }

    public void addCompletionCallback(Runnable arg4) {
        ThreadUtils.assertOnUiThread();
        Handler v0 = new Handler(Looper.getMainLooper());
        if(ResourceExtractor.shouldSkipPakExtraction()) {
            v0.post(arg4);
            return;
        }

        if(this.mExtractTask.getStatus() == AsyncTask$Status.FINISHED) {
            v0.post(arg4);
        }
        else {
            ExtractTask.access$700(this.mExtractTask).add(arg4);
        }
    }

    private static File createFilesDir(File arg4) {
        Class v0 = ResourceExtractor.class;
        __monitor_enter(v0);
        try {
            if(!arg4.exists() && !arg4.mkdirs()) {
                if(arg4.exists()) {
                    goto label_8;
                }
                else {
                    goto label_10;
                }
            }

            goto label_22;
        }
        catch(Throwable v4) {
            goto label_25;
        }

    label_8:
        __monitor_exit(v0);
        return arg4;
        try {
        label_10:
            android.util.Log.w("cr.base", "Unable to create files subdir " + arg4.getPath());
        }
        catch(Throwable v4) {
        label_25:
            __monitor_exit(v0);
            throw v4;
        }

        __monitor_exit(v0);
        return null;
    label_22:
        __monitor_exit(v0);
        return arg4;
    }

    private void deleteFiles() {
        File v0 = new File(this.getAppDataDir(), "icudtl.dat");
        if((v0.exists()) && !v0.delete()) {
            Log.e("cr.base", "Unable to remove the icudata %s", new Object[]{v0.getName()});
        }

        v0 = new File(this.getAppDataDir(), "natives_blob.bin");
        if((v0.exists()) && !v0.delete()) {
            Log.e("cr.base", "Unable to remove the v8 data %s", new Object[]{v0.getName()});
        }

        v0 = new File(this.getAppDataDir(), "snapshot_blob.bin");
        if((v0.exists()) && !v0.delete()) {
            Log.e("cr.base", "Unable to remove the v8 data %s", new Object[]{v0.getName()});
        }

        v0 = this.getOutputDir();
        if(v0.exists()) {
            File[] v0_1 = v0.listFiles();
            if(v0_1 != null) {
                int v1 = v0_1.length;
                int v4;
                for(v4 = 0; v4 < v1; ++v4) {
                    File v5 = v0_1[v4];
                    if(!v5.delete()) {
                        Log.e("cr.base", "Unable to remove existing resource %s", new Object[]{v5.getName()});
                    }
                }
            }
        }
    }

    @SuppressLint(value={"SetWorldReadable"}) public static String extractFileIfStale(Context arg5, String arg6, File arg7) {
        InputStream v0_1;
        Throwable v5_2;
        ZipFile v7;
        String v5 = arg5.getApplicationInfo().sourceDir;
        StringBuilder v0 = new StringBuilder();
        v0.append(new File(arg6).getName());
        v0.append(BuildInfo.getInstance().extractedFileSuffix);
        File v1 = new File(arg7, v0.toString());
        if(!v1.exists()) {
            try {
                v7 = new ZipFile(v5);
                v5_2 = null;
            }
            catch(IOException v5_1) {
                goto label_78;
            }

            try {
                v0_1 = v7.getInputStream(v7.getEntry(arg6));
            }
            catch(Throwable v5_2) {
                goto label_65;
            }
            catch(Throwable v6) {
                goto label_66;
            }

            try {
                if(v7.getEntry(arg6) == null) {
                    StringBuilder v2 = new StringBuilder();
                    v2.append("Cannot find ZipEntry");
                    v2.append(arg6);
                    throw new RuntimeException(v2.toString());
                }
                else {
                    ResourceExtractor.extractResourceHelper(v0_1, v1, new byte[0x4000]);
                    v1.setReadable(true, false);
                    v1.setExecutable(true, false);
                    if(v0_1 == null) {
                        goto label_41;
                    }

                    goto label_40;
                }

                goto label_79;
            }
            catch(Throwable v6) {
                v1_1 = v5_2;
            }
            catch(Throwable v6) {
                try {
                    throw v6;
                }
                catch(Throwable v1_1) {
                    Throwable v4 = v1_1;
                    v1_1 = v6;
                    v6 = v4;
                }
            }

            if(v0_1 == null) {
                goto label_61;
            }
            else if(v1_1 != null) {
                try {
                    v0_1.close();
                    goto label_61;
                }
                catch(Throwable v6) {
                }
                catch(Throwable v0_2) {
                    try {
                        ThrowableExtension.addSuppressed(v1_1, v0_2);
                        goto label_61;
                    label_60:
                        v0_1.close();
                    label_61:
                        throw v6;
                    label_40:
                        v0_1.close();
                        goto label_41;
                    }
                    catch(Throwable v6) {
                    }
                    catch(Throwable v5_2) {
                        try {
                        label_65:
                            throw v5_2;
                        }
                        catch(Throwable v6) {
                        label_66:
                            if(v7 == null) {
                                goto label_74;
                            }
                            else if(v5_2 != null) {
                                try {
                                    v7.close();
                                    goto label_74;
                                }
                                catch(IOException v5_1) {
                                }
                                catch(Throwable v7_1) {
                                    try {
                                        ThrowableExtension.addSuppressed(v5_2, v7_1);
                                        goto label_74;
                                    label_73:
                                        v7.close();
                                    label_74:
                                        throw v6;
                                    }
                                    catch(IOException v5_1) {
                                        goto label_78;
                                    }

                                label_41:
                                    if(v7 == null) {
                                        goto label_79;
                                    }

                                    try {
                                        v7.close();
                                        goto label_79;
                                    }
                                    catch(IOException v5_1) {
                                    label_78:
                                        throw new RuntimeException(((Throwable)v5_1));
                                    }
                                }
                            }
                            else {
                                goto label_73;
                            }

                            goto label_41;
                        }
                    }
                }
            }
            else {
                goto label_60;
            }

            goto label_41;
        }

    label_79:
        return v1.getAbsolutePath();
    }

    private static void extractResourceHelper(InputStream arg7, File arg8, byte[] arg9) throws IOException {
        StringBuilder v1 = new StringBuilder();
        v1.append(arg8.getPath());
        v1.append(".tmp");
        File v0 = new File(v1.toString());
        FileOutputStream v1_1 = new FileOutputStream(v0);
        Throwable v2 = null;
        try {
            Log.i("cr.base", "Extracting resource %s", new Object[]{arg8});
            while(true) {
                int v3 = arg7.read(arg9, 0, 0x4000);
                if(v3 == -1) {
                    break;
                }

                ((OutputStream)v1_1).write(arg9, 0, v3);
            }
        }
        catch(Throwable v7) {
            goto label_38;
        }
        catch(Throwable v7) {
            goto label_36;
        }

        if(v1_1 != null) {
            ((OutputStream)v1_1).close();
        }

        if(!v0.renameTo(arg8)) {
            throw new IOException();
        }

        return;
    label_36:
        v2 = v7;
        try {
            throw v2;
        }
        catch(Throwable v7) {
        label_38:
            if(v1_1 != null) {
                if(v2 != null) {
                    try {
                        ((OutputStream)v1_1).close();
                    }
                    catch(Throwable v8) {
                        ThrowableExtension.addSuppressed(v2, v8);
                    }
                }
                else {
                    ((OutputStream)v1_1).close();
                }
            }

            throw v7;
        }
    }

    public static ResourceExtractor get() {
        return ResourceExtractor.sInstance;
    }

    public static ResourceExtractor get(Context arg1) {
        if(ResourceExtractor.sInstance == null) {
            ResourceExtractor.sInstance = new ResourceExtractor(arg1);
        }

        return ResourceExtractor.sInstance;
    }

    private File getAppDataDir() {
        return new File(PathUtils.getDataDirectory());
    }

    private static File getCodeCacheDir(Context arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getCodeCacheDir();
        }

        return ResourceExtractor.createFilesDir(new File(arg2.getApplicationInfo().dataDir, "code_cache"));
    }

    private static File getLibraryDir() {
        return new File(ResourceExtractor.getCodeCacheDir(ContextUtils.getApplicationContext()), "native_libraries");
    }

    private File getOutputDir() {
        return new File(this.getAppDataDir(), "paks");
    }

    private static boolean isAppDataFile(String arg1) {
        boolean v1 = ("icudtl.dat".equals(arg1)) || ("natives_blob.bin".equals(arg1)) || ("snapshot_blob.bin".equals(arg1)) ? true : false;
        return v1;
    }

    public static File makeLibraryDirAndSetPermission() {
        if(!ContextUtils.isIsolatedProcess()) {
            File v0 = ResourceExtractor.getCodeCacheDir(ContextUtils.getApplicationContext());
            File v1 = new File(v0, "native_libraries");
            v0.mkdir();
            v0.setExecutable(true, false);
            v1.mkdir();
            v1.setExecutable(true, false);
        }

        return ResourceExtractor.getLibraryDir();
    }

    public static void setResourceInterceptor(ResourceInterceptor arg0) {
        ResourceExtractor.sInterceptor = arg0;
    }

    public static void setResourcesToExtract(ResourceEntry[] arg0) {
        ResourceExtractor.sResourcesToExtract = arg0;
    }

    private static boolean shouldSkipPakExtraction() {
        boolean v0 = ResourceExtractor.sResourcesToExtract.length == 0 ? true : false;
        return v0;
    }

    public void startExtractingResources() {
        if(this.mExtractTask != null) {
            return;
        }

        if(ResourceExtractor.shouldSkipPakExtraction()) {
            return;
        }

        this.mExtractTask = new ExtractTask(this, null);
        this.mExtractTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void waitForCompletion() {
        if(ResourceExtractor.shouldSkipPakExtraction()) {
            return;
        }

        try {
            this.mExtractTask.get();
            ResourceExtractor.sInterceptor = null;
            ResourceExtractor.sInstance = null;
        }
        catch(InterruptedException ) {
            this.deleteFiles();
        }
        catch(ExecutionException ) {
            this.deleteFiles();
        }
        catch(CancellationException ) {
            this.deleteFiles();
        }
    }
}

