package org.chromium.base.library_loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Process;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.system.Os;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import org.chromium.base.BuildConfig;
import org.chromium.base.CommandLine;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.SysUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.metrics.CachedMetrics$EnumeratedHistogramSample;
import org.chromium.base.metrics.RecordHistogram;

@JNINamespace(value="base::android") public class LibraryLoader {
    class LibraryPrefetchTask extends AsyncTask {
        private final boolean mColdStart;

        public LibraryPrefetchTask(boolean arg1) {
            super();
            this.mColdStart = arg1;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        protected Void doInBackground(Void[] arg8) {
            TraceEvent v8 = TraceEvent.scoped("LibraryLoader.asyncPrefetchLibrariesToMemory");
            Void v0 = null;
            try {
                int v1_1 = LibraryLoader.nativePercentageOfResidentNativeLibraryCode();
                boolean v3 = false;
                int v2 = !this.mColdStart || v1_1 >= 90 ? 0 : 1;
                if(v2 != 0) {
                    boolean v4 = LibraryLoader.nativeForkAndPrefetchNativeLibrary();
                    if(!v4) {
                        Log.w("LibraryLoader", "Forking a process to prefetch the native library failed.", new Object[0]);
                    }

                    v3 = v4;
                }

                if(v2 != 0) {
                    RecordHistogram.recordBooleanHistogram("LibraryLoader.PrefetchStatus", v3);
                }

                if(v1_1 != -1) {
                    StringBuilder v2_1 = new StringBuilder();
                    v2_1.append("LibraryLoader.PercentageOfResidentCodeBeforePrefetch");
                    String v3_1 = this.mColdStart ? ".ColdStartup" : ".WarmStartup";
                    v2_1.append(v3_1);
                    RecordHistogram.recordPercentageHistogram(v2_1.toString(), v1_1);
                }
            }
            catch(Throwable v1) {
            }
            catch(Throwable v0_1) {
                try {
                    throw v0_1;
                }
                catch(Throwable v1) {
                    if(v8 != null) {
                        if(v0_1 != null) {
                            try {
                                v8.close();
                            }
                            catch(Throwable v8_1) {
                                ThrowableExtension.addSuppressed(v0_1, v8_1);
                            }
                        }
                        else {
                            v8.close();
                        }
                    }

                    throw v1;
                }
            }

            if(v8 != null) {
                v8.close();
            }

            return v0;
        }
    }

    private static final boolean DEBUG = false;
    private static final String DONT_PREFETCH_LIBRARIES_KEY = "dont_prefetch_libraries";
    private static final String TAG = "LibraryLoader";
    private boolean mCommandLineSwitched;
    private volatile boolean mInitialized;
    private boolean mIsUsingBrowserSharedRelros;
    private long mLibraryLoadTimeMs;
    private int mLibraryPreloaderStatus;
    private final int mLibraryProcessType;
    private boolean mLibraryWasLoadedFromApk;
    private boolean mLoadAtFixedAddressFailed;
    private boolean mLoaded;
    private final AtomicBoolean mPrefetchLibraryHasBeenCalled;
    private static volatile LibraryLoader sInstance;
    private static NativeLibraryPreloader sLibraryPreloader;
    private static boolean sLibraryPreloaderCalled;
    private static final Object sLock;
    private static final EnumeratedHistogramSample sRelinkerCountHistogram;

    static {
        LibraryLoader.sLock = new Object();
        LibraryLoader.sRelinkerCountHistogram = new EnumeratedHistogramSample("ChromiumAndroidLinker.RelinkerFallbackCount", 2);
    }

    private LibraryLoader(int arg2) {
        super();
        this.mLibraryPreloaderStatus = -1;
        this.mLibraryProcessType = arg2;
        this.mPrefetchLibraryHasBeenCalled = new AtomicBoolean();
    }

    static int access$000() {
        return LibraryLoader.nativePercentageOfResidentNativeLibraryCode();
    }

    static boolean access$100() {
        return LibraryLoader.nativeForkAndPrefetchNativeLibrary();
    }

    public void asyncPrefetchLibrariesToMemory() {
        SysUtils.logPageFaultCountToTracing();
        if(LibraryLoader.isNotPrefetchingLibraries()) {
            return;
        }

        boolean v0 = this.mPrefetchLibraryHasBeenCalled.compareAndSet(false, true);
        if((v0) && (CommandLine.getInstance().hasSwitch("log-native-library-residency"))) {
            new Thread(LibraryLoader$$Lambda$0.$instance).start();
            return;
        }

        new LibraryPrefetchTask(v0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    static final void bridge$lambda$0$LibraryLoader() {
        LibraryLoader.nativePeriodicallyCollectResidency();
    }

    private void ensureCommandLineSwitchedAlreadyLocked() {
        if(this.mCommandLineSwitched) {
            return;
        }

        CommandLine.enableNativeProxy();
        this.mCommandLineSwitched = true;
    }

    public void ensureInitialized() throws ProcessInitException {
        Object v0 = LibraryLoader.sLock;
        __monitor_enter(v0);
        try {
            if(this.mInitialized) {
                __monitor_exit(v0);
                return;
            }

            this.loadAlreadyLocked(ContextUtils.getApplicationContext());
            this.initializeAlreadyLocked();
            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    public static LibraryLoader get(int arg2) throws ProcessInitException {
        Object v0 = LibraryLoader.sLock;
        __monitor_enter(v0);
        try {
            if(LibraryLoader.sInstance != null) {
                if(LibraryLoader.sInstance.mLibraryProcessType == arg2) {
                    __monitor_exit(v0);
                    return LibraryLoader.sInstance;
                }

                throw new ProcessInitException(2);
            }

            LibraryLoader.sInstance = new LibraryLoader(arg2);
            __monitor_exit(v0);
            return LibraryLoader.sInstance;
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_21;
        }

        throw v2;
    }

    static String getExtractedLibraryPath(Context arg4, String arg5) {
        Log.w("LibraryLoader", "Failed to load libName %s, attempting fallback extraction then trying again", new Object[]{arg5});
        return ResourceExtractor.extractFileIfStale(arg4, LibraryLoader.makeLibraryPathInZipFile(arg5, false, false), ResourceExtractor.makeLibraryDirAndSetPermission());
    }

    private int getLibraryLoadFromApkStatus() {
        if(this.mLibraryWasLoadedFromApk) {
            return 3;
        }

        return 0;
    }

    @CalledByNative @MainDex public static int getLibraryProcessType() {
        if(LibraryLoader.sInstance == null) {
            return 0;
        }

        return LibraryLoader.sInstance.mLibraryProcessType;
    }

    static void incrementRelinkerCountHitHistogram() {
        LibraryLoader.sRelinkerCountHistogram.record(1);
    }

    static void incrementRelinkerCountNotHitHistogram() {
        LibraryLoader.sRelinkerCountHistogram.record(0);
    }

    public void initialize() throws ProcessInitException {
        Object v0 = LibraryLoader.sLock;
        __monitor_enter(v0);
        try {
            this.initializeAlreadyLocked();
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    private void initializeAlreadyLocked() throws ProcessInitException {
        if(this.mInitialized) {
            return;
        }

        this.ensureCommandLineSwitchedAlreadyLocked();
        if(!this.nativeLibraryLoaded()) {
            Log.e("LibraryLoader", "error calling nativeLibraryLoaded", new Object[0]);
            throw new ProcessInitException(1);
        }

        Log.i("LibraryLoader", String.format("Expected native library version number \"%s\", actual native library version number \"%s\"", NativeLibraries.sVersionNumber, this.nativeGetVersionNumber()), new Object[0]);
        if(!NativeLibraries.sVersionNumber.equals(this.nativeGetVersionNumber())) {
            throw new ProcessInitException(3);
        }

        TraceEvent.registerNativeEnabledObserver();
        this.mInitialized = true;
    }

    public static boolean isInitialized() {
        boolean v0 = LibraryLoader.sInstance == null || !LibraryLoader.sInstance.mInitialized ? false : true;
        return v0;
    }

    private static boolean isNotPrefetchingLibraries() {
        boolean v1_1;
        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        try {
            v1_1 = ContextUtils.getAppSharedPreferences().getBoolean("dont_prefetch_libraries", false);
        }
        catch(Throwable v1) {
            StrictMode.setThreadPolicy(v0);
            throw v1;
        }

        StrictMode.setThreadPolicy(v0);
        return v1_1;
    }

    @SuppressLint(value={"DefaultLocale", "NewApi", "UnsafeDynamicallyLoadedCode"}) private void loadAlreadyLocked(Context arg18) throws ProcessInitException {
        Throwable v8_2;
        String v8_1;
        String v2_1;
        UnsatisfiedLinkError v2;
        String v11_1;
        int v10_1;
        Throwable v2_2;
        String v15;
        String v14;
        String v13;
        int v12;
        Linker v7;
        long v5;
        TraceEvent v3;
        LibraryLoader v1 = this;
        try {
            v3 = TraceEvent.scoped("LibraryLoader.loadAlreadyLocked");
        }
        catch(UnsatisfiedLinkError v0) {
            goto label_187;
        }

        try {
            if(v1.mLoaded) {
                goto label_160;
            }

            v5 = SystemClock.uptimeMillis();
            if(!Linker.isUsed()) {
                goto label_83;
            }

            v7 = Linker.getInstance();
            v7.prepareLibraryLoad();
            String[] v10 = NativeLibraries.LIBRARIES;
            int v11 = v10.length;
            v12 = 0;
            while(true) {
            label_14:
                if(v12 >= v11) {
                    goto label_80;
                }

                v13 = v10[v12];
                if(!v7.isChromiumLinkerLibrary(v13)) {
                    v14 = System.mapLibraryName(v13);
                    if(Linker.isInZipFile()) {
                        break;
                    }
                    else {
                        goto label_43;
                    }
                }

                goto label_18;
            }
        }
        catch(Throwable v0_1) {
            goto label_168;
        }
        catch(Throwable v0_1) {
            goto label_164;
        }

        try {
            v15 = arg18.getApplicationInfo().sourceDir;
            Log.i("LibraryLoader", "Loading " + v13 + " from within " + v15, new Object[0]);
            goto label_53;
        }
        catch(Throwable v0_1) {
            goto label_168;
        }
        catch(Throwable v0_1) {
            goto label_40;
        }

        try {
        label_43:
            Log.i("LibraryLoader", "Loading " + v13, new Object[0]);
            v15 = null;
        }
        catch(Throwable v0_1) {
            goto label_168;
        }
        catch(Throwable v0_1) {
            goto label_164;
        }

        try {
        label_53:
            v1.loadLibraryWithCustomLinker(v7, v15, v14);
            LibraryLoader.incrementRelinkerCountNotHitHistogram();
            goto label_18;
        }
        catch(Throwable v0_1) {
        }
        catch(Throwable v0_1) {
        label_40:
            v2_2 = v0_1;
            v8_2 = null;
            goto label_173;
        label_18:
            v8_2 = null;
            try {
            label_67:
                ++v12;
                goto label_14;
            label_80:
                v8_2 = null;
                v7.finishLibraryLoad();
                goto label_132;
            }
            catch(Throwable v0_1) {
                goto label_168;
            }
            catch(Throwable v0_1) {
                goto label_159;
            }

        label_164:
            v8_2 = null;
            goto label_165;
            try {
            label_83:
                v8_2 = null;
                LibraryLoader.setEnvForNative();
                this.preloadAlreadyLocked(arg18);
                String[] v2_3 = NativeLibraries.LIBRARIES;
                int v7_1 = v2_3.length;
                v10_1 = 0;
                while(true) {
                label_90:
                    if(v10_1 >= v7_1) {
                        goto label_132;
                    }

                    v11_1 = v2_3[v10_1];
                    break;
                }
            }
            catch(Throwable v0_1) {
                goto label_168;
            }
            catch(Throwable v0_1) {
                goto label_159;
            }

            try {
                if(!Linker.isInZipFile()) {
                    System.loadLibrary(v11_1);
                }
                else {
                    boolean v12_1 = Process.is64Bit();
                    v13 = arg18.getApplicationInfo().sourceDir;
                    String v12_2 = v13 + "!/" + LibraryLoader.makeLibraryPathInZipFile(v11_1, true, v12_1);
                    Log.i("LibraryLoader", "libraryName: " + v12_2, new Object[0]);
                    System.load(v12_2);
                }

                goto label_118;
            }
            catch(Throwable v0_1) {
            }
            catch(Throwable v0_1) {
            }
            catch(UnsatisfiedLinkError v0) {
                v2 = v0;
                try {
                    Log.e("LibraryLoader", "Unable to load library: " + v11_1, new Object[0]);
                    throw v2;
                }
                catch(Throwable v0_1) {
                    goto label_168;
                }
                catch(Throwable v0_1) {
                    goto label_159;
                }

            label_118:
                ++v10_1;
                goto label_90;
                try {
                label_132:
                    long v10_2 = SystemClock.uptimeMillis();
                    v1.mLibraryLoadTimeMs = v10_2 - v5;
                    Log.i("LibraryLoader", String.format("Time to load native libraries: %d ms (timestamps %d-%d)", Long.valueOf(v1.mLibraryLoadTimeMs), Long.valueOf(v5 % 10000), Long.valueOf(v10_2 % 10000)), new Object[0]);
                    v1.mLoaded = true;
                    goto label_160;
                }
                catch(Throwable v0_1) {
                label_159:
                }
                catch(Throwable v0_1) {
                label_168:
                    Throwable v4_1 = v0_1;
                    try {
                        throw v4_1;
                    }
                    catch(Throwable v0_1) {
                        v2_2 = v0_1;
                        v8_2 = v4_1;
                        goto label_173;
                    }
                }
            }
        }
        catch(UnsatisfiedLinkError v0) {
            v2 = v0;
            try {
                if(!Linker.isInZipFile() && (ResourceExtractor.PLATFORM_REQUIRES_NATIVE_FALLBACK_EXTRACTION)) {
                    v2_1 = LibraryLoader.getExtractedLibraryPath(arg18, v13);
                    v8_1 = null;
                    goto label_65;
                }

                goto label_69;
            }
            catch(Throwable v0_1) {
                goto label_168;
            }
            catch(Throwable v0_1) {
                goto label_164;
            }

            try {
            label_65:
                v1.loadLibraryWithCustomLinker(v7, v8_1, v2_1);
                LibraryLoader.incrementRelinkerCountHitHistogram();
                goto label_67;
            label_69:
                v8_2 = null;
                Log.e("LibraryLoader", "Unable to load library: " + v13, new Object[0]);
                throw v2;
            }
            catch(Throwable v0_1) {
                goto label_168;
            }
            catch(Throwable v0_1) {
                goto label_159;
            }
        }

    label_165:
        v2_2 = v0_1;
    label_173:
        if(v3 == null) {
            goto label_181;
        }

        if((((Throwable)v8_1)) == null) {
            goto label_180;
        }

        try {
            v3.close();
            goto label_181;
        }
        catch(UnsatisfiedLinkError v0) {
        }
        catch(Throwable v0_1) {
            try {
                ThrowableExtension.addSuppressed(((Throwable)v8_1), v0_1);
                goto label_181;
            label_180:
                v3.close();
            label_181:
                throw v2_2;
            }
            catch(UnsatisfiedLinkError v0) {
                goto label_187;
            }

        label_160:
            if(v3 != null) {
                try {
                    v3.close();
                }
                catch(UnsatisfiedLinkError v0) {
                label_187:
                    throw new ProcessInitException(2, v0);
                }
            }
        }
    }

    private void loadLibraryWithCustomLinker(Linker arg5, @Nullable String arg6, String arg7) {
        if(arg5.isUsingBrowserSharedRelros()) {
            this.mIsUsingBrowserSharedRelros = true;
            try {
                arg5.loadLibrary(arg6, arg7);
            }
            catch(UnsatisfiedLinkError ) {
                Log.w("LibraryLoader", "Failed to load native library with shared RELRO, retrying without", new Object[0]);
                this.mLoadAtFixedAddressFailed = true;
                arg5.loadLibraryNoFixedAddress(arg6, arg7);
            }
        }
        else {
            arg5.loadLibrary(arg6, arg7);
        }

        if(arg6 != null) {
            this.mLibraryWasLoadedFromApk = true;
        }
    }

    public void loadNow() throws ProcessInitException {
        this.loadNowOverrideApplicationContext(ContextUtils.getApplicationContext());
    }

    public void loadNowOverrideApplicationContext(Context arg3) throws ProcessInitException {
        Object v0 = LibraryLoader.sLock;
        __monitor_enter(v0);
        try {
            if((this.mLoaded) && arg3 != ContextUtils.getApplicationContext()) {
                throw new IllegalStateException("Attempt to load again from alternate context.");
            }

            this.loadAlreadyLocked(arg3);
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        throw v3;
    }

    @NonNull public static String makeLibraryPathInZipFile(String arg3, boolean arg4, boolean arg5) {
        String v5;
        switch(NativeLibraries.sCpuFamily) {
            case 1: {
                if(arg5) {
                    v5 = "arm64-v8a";
                    goto label_20;
                }

                v5 = "armeabi-v7a";
                break;
            }
            case 2: {
                if(arg5) {
                    v5 = "mips64";
                    goto label_20;
                }

                v5 = "mips";
                break;
            }
            case 3: {
                if(arg5) {
                    v5 = "x86_64";
                    goto label_20;
                }

                v5 = "x86";
                break;
            }
            default: {
                throw new RuntimeException("Unknown CPU ABI for native libraries");
            }
        }

    label_20:
        String v4 = arg4 ? "crazy." : "";
        return String.format("lib/%s/%s%s", v5, v4, System.mapLibraryName(arg3));
    }

    private static native boolean nativeForkAndPrefetchNativeLibrary() {
    }

    private native String nativeGetVersionNumber() {
    }

    private native boolean nativeLibraryLoaded() {
    }

    private static native int nativePercentageOfResidentNativeLibraryCode() {
    }

    private static native void nativePeriodicallyCollectResidency() {
    }

    private native void nativeRecordChromiumAndroidLinkerBrowserHistogram(boolean arg1, boolean arg2, int arg3, long arg4) {
    }

    private native void nativeRecordLibraryPreloaderBrowserHistogram(int arg1) {
    }

    private native void nativeRegisterChromiumAndroidLinkerRendererHistogram(boolean arg1, boolean arg2, long arg3) {
    }

    private native void nativeRegisterLibraryPreloaderRendererHistogram(int arg1) {
    }

    public void onNativeInitializationComplete() {
        this.recordBrowserProcessHistogram();
    }

    private void preloadAlreadyLocked(Context arg4) {
        TraceEvent v0 = TraceEvent.scoped("LibraryLoader.preloadAlreadyLocked");
        Throwable v1 = null;
        try {
            if(LibraryLoader.sLibraryPreloader != null && !LibraryLoader.sLibraryPreloaderCalled) {
                this.mLibraryPreloaderStatus = LibraryLoader.sLibraryPreloader.loadLibrary(arg4);
                LibraryLoader.sLibraryPreloaderCalled = true;
            }
        }
        catch(Throwable v4) {
        }
        catch(Throwable v4) {
            v1 = v4;
            try {
                throw v1;
            }
            catch(Throwable v4) {
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
                    throw v4;
                }
            }
        }

        if(v0 != null) {
            v0.close();
        }
    }

    public void preloadNow() {
        this.preloadNowOverrideApplicationContext(ContextUtils.getApplicationContext());
    }

    public void preloadNowOverrideApplicationContext(Context arg3) {
        Object v0 = LibraryLoader.sLock;
        __monitor_enter(v0);
        try {
            if(!Linker.isUsed()) {
                this.preloadAlreadyLocked(arg3);
            }

            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    private void recordBrowserProcessHistogram() {
        if(Linker.isUsed()) {
            this.nativeRecordChromiumAndroidLinkerBrowserHistogram(this.mIsUsingBrowserSharedRelros, this.mLoadAtFixedAddressFailed, this.getLibraryLoadFromApkStatus(), this.mLibraryLoadTimeMs);
        }

        if(LibraryLoader.sLibraryPreloader != null) {
            this.nativeRecordLibraryPreloaderBrowserHistogram(this.mLibraryPreloaderStatus);
        }
    }

    public void registerRendererProcessHistogram(boolean arg3, boolean arg4) {
        if(Linker.isUsed()) {
            this.nativeRegisterChromiumAndroidLinkerRendererHistogram(arg3, arg4, this.mLibraryLoadTimeMs);
        }

        if(LibraryLoader.sLibraryPreloader != null) {
            this.nativeRegisterLibraryPreloaderRendererHistogram(this.mLibraryPreloaderStatus);
        }
    }

    public static void setDontPrefetchLibrariesOnNextRuns(boolean arg2) {
        ContextUtils.getAppSharedPreferences().edit().putBoolean("dont_prefetch_libraries", arg2).apply();
    }

    public static void setEnvForNative() {
        if((BuildConfig.IS_UBSAN) && Build$VERSION.SDK_INT >= 21) {
            try {
                Os.setenv("UBSAN_OPTIONS", "print_stacktrace=1 stack_trace_format=\'#%n pc %o %m\' handle_segv=0 handle_sigbus=0 handle_sigfpe=0", true);
            }
            catch(Exception v1) {
                Log.w("LibraryLoader", "failed to set UBSAN_OPTIONS", new Object[]{v1});
            }
        }
    }

    @VisibleForTesting public static void setLibraryLoaderForTesting(LibraryLoader arg0) {
        LibraryLoader.sInstance = arg0;
    }

    public static void setNativeLibraryPreloader(NativeLibraryPreloader arg1) {
        Object v0 = LibraryLoader.sLock;
        __monitor_enter(v0);
        try {
            LibraryLoader.sLibraryPreloader = arg1;
            __monitor_exit(v0);
            return;
        }
        catch(Throwable v1) {
        label_8:
            try {
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_8;
            }

            throw v1;
        }
    }

    public void switchCommandLineForWebView() {
        Object v0 = LibraryLoader.sLock;
        __monitor_enter(v0);
        try {
            this.ensureCommandLineSwitchedAlreadyLocked();
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }
}

