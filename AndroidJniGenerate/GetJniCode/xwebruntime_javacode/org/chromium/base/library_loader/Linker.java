package org.chromium.base.library_loader;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map$Entry;
import javax.annotation.Nullable;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.annotations.AccessedByNative;

public abstract class Linker {
    public class LibInfo implements Parcelable {
        final class org.chromium.base.library_loader.Linker$LibInfo$1 implements Parcelable$Creator {
            org.chromium.base.library_loader.Linker$LibInfo$1() {
                super();
            }

            public Object createFromParcel(Parcel arg1) {
                return this.createFromParcel(arg1);
            }

            public LibInfo createFromParcel(Parcel arg2) {
                return new LibInfo(arg2);
            }

            public Object[] newArray(int arg1) {
                return this.newArray(arg1);
            }

            public LibInfo[] newArray(int arg1) {
                return new LibInfo[arg1];
            }
        }

        public static final Parcelable$Creator CREATOR;
        @AccessedByNative public long mLoadAddress;
        @AccessedByNative public long mLoadSize;
        @AccessedByNative public int mRelroFd;
        @AccessedByNative public long mRelroSize;
        @AccessedByNative public long mRelroStart;

        static {
            LibInfo.CREATOR = new org.chromium.base.library_loader.Linker$LibInfo$1();
        }

        public LibInfo() {
            super();
            this.mLoadAddress = 0;
            this.mLoadSize = 0;
            this.mRelroStart = 0;
            this.mRelroSize = 0;
            this.mRelroFd = -1;
        }

        public LibInfo(Parcel arg3) {
            super();
            this.mLoadAddress = arg3.readLong();
            this.mLoadSize = arg3.readLong();
            this.mRelroStart = arg3.readLong();
            this.mRelroSize = arg3.readLong();
            Object v3 = ParcelFileDescriptor.CREATOR.createFromParcel(arg3);
            int v3_1 = v3 == null ? -1 : ((ParcelFileDescriptor)v3).detachFd();
            this.mRelroFd = v3_1;
        }

        public void close() {
            if(this.mRelroFd >= 0) {
                try {
                    ParcelFileDescriptor.adoptFd(this.mRelroFd).close();
                    goto label_5;
                }
                catch(IOException ) {
                label_5:
                    this.mRelroFd = -1;
                }
            }
        }

        public int describeContents() {
            return 1;
        }

        public String toString() {
            return String.format(Locale.US, "[load=0x%x-0x%x relro=0x%x-0x%x fd=%d]", Long.valueOf(this.mLoadAddress), Long.valueOf(this.mLoadAddress + this.mLoadSize), Long.valueOf(this.mRelroStart), Long.valueOf(this.mRelroStart + this.mRelroSize), Integer.valueOf(this.mRelroFd));
        }

        public void writeToParcel(Parcel arg4, int arg5) {
            if(this.mRelroFd >= 0) {
                arg4.writeLong(this.mLoadAddress);
                arg4.writeLong(this.mLoadSize);
                arg4.writeLong(this.mRelroStart);
                arg4.writeLong(this.mRelroSize);
                try {
                    ParcelFileDescriptor v0 = ParcelFileDescriptor.fromFd(this.mRelroFd);
                    v0.writeToParcel(arg4, 0);
                    v0.close();
                }
                catch(IOException v4) {
                    Log.e("LibraryLoader", "Can\'t write LibInfo file descriptor to parcel", new Object[]{v4});
                }
            }
        }
    }

    public interface TestRunner {
        boolean runChecks(int arg1, boolean arg2);
    }

    protected static final int ADDRESS_SPACE_RESERVATION = 0xC000000;
    protected static final int BREAKPAD_GUARD_REGION_BYTES = 0x1000000;
    public static final int BROWSER_SHARED_RELRO_CONFIG = 1;
    public static final int BROWSER_SHARED_RELRO_CONFIG_ALWAYS = 2;
    public static final int BROWSER_SHARED_RELRO_CONFIG_LOW_RAM_ONLY = 1;
    public static final int BROWSER_SHARED_RELRO_CONFIG_NEVER = 0;
    protected static final boolean DEBUG = false;
    public static final String EXTRA_LINKER_SHARED_RELROS = "org.chromium.base.android.linker.shared_relros";
    private static final String LINKER_JNI_LIBRARY = "chromium_android_linker";
    public static final int MEMORY_DEVICE_CONFIG_INIT = 0;
    public static final int MEMORY_DEVICE_CONFIG_LOW = 1;
    public static final int MEMORY_DEVICE_CONFIG_NORMAL = 2;
    private static final String TAG = "LibraryLoader";
    protected final Object mLock;
    protected int mMemoryDeviceConfig;
    private String mTestRunnerClassName;
    private static Linker sSingleton;
    private static Object sSingletonLock;

    static {
        Linker.sSingletonLock = new Object();
    }

    protected Linker() {
        super();
        this.mMemoryDeviceConfig = 0;
        this.mLock = new Object();
    }

    public static boolean areTestsEnabled() {
        return NativeLibraries.sEnableLinkerTests;
    }

    private static void assertForTesting(boolean arg0) {
        if(!arg0) {
            throw new AssertionError();
        }
    }

    private static void assertLinkerTestsAreEnabled() {
        if(!NativeLibraries.sEnableLinkerTests) {
            throw new AssertionError("Testing method called in non-testing context");
        }
    }

    protected void closeLibInfoMap(HashMap arg2) {
        Iterator v2 = arg2.entrySet().iterator();
        while(v2.hasNext()) {
            v2.next().getValue().close();
        }
    }

    protected Bundle createBundleFromLibInfoMap(HashMap arg4) {
        Bundle v0 = new Bundle(arg4.size());
        Iterator v4 = arg4.entrySet().iterator();
        while(v4.hasNext()) {
            Object v1 = v4.next();
            v0.putParcelable(((Map$Entry)v1).getKey(), ((Map$Entry)v1).getValue());
        }

        return v0;
    }

    protected HashMap createLibInfoMapFromBundle(Bundle arg5) {
        HashMap v0 = new HashMap();
        Iterator v1 = arg5.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.put(v2, arg5.getParcelable(((String)v2)));
        }

        return v0;
    }

    public abstract void disableSharedRelros();

    public abstract void finishLibraryLoad();

    public abstract long getBaseLoadAddress();

    public static final Linker getInstance() {
        Object v0 = Linker.sSingletonLock;
        __monitor_enter(v0);
        try {
            if(Linker.sSingleton == null) {
                Linker.sSingleton = LegacyLinker.create();
                Log.i("LibraryLoader", "Using linker: LegacyLinker", new Object[0]);
            }

            __monitor_exit(v0);
            return Linker.sSingleton;
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_15;
        }

        throw v1;
    }

    protected long getRandomBaseLoadAddress() {
        return Linker.nativeGetRandomBaseLoadAddress();
    }

    public abstract Bundle getSharedRelros();

    public final String getTestRunnerClassNameForTesting() {
        Linker.assertLinkerTestsAreEnabled();
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.mTestRunnerClassName;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    public abstract void initServiceProcess(long arg1);

    public boolean isChromiumLinkerLibrary(String arg2) {
        return arg2.equals("chromium_android_linker");
    }

    public static boolean isInZipFile() {
        return NativeLibraries.sUseLibraryInZipFile;
    }

    public static boolean isUsed() {
        if(Build$VERSION.SDK_INT >= 24) {
            return 0;
        }

        return NativeLibraries.sUseLinker;
    }

    public abstract boolean isUsingBrowserSharedRelros();

    public void loadLibrary(@Nullable String arg2, String arg3) {
        this.loadLibraryImpl(arg2, arg3, true);
    }

    abstract void loadLibraryImpl(@Nullable String arg1, String arg2, boolean arg3);

    public void loadLibraryNoFixedAddress(@Nullable String arg2, String arg3) {
        this.loadLibraryImpl(arg2, arg3, false);
    }

    @SuppressLint(value={"UnsafeDynamicallyLoadedCode"}) protected static void loadLinkerJniLibrary() {
        LibraryLoader.setEnvForNative();
        try {
            System.loadLibrary("chromium_android_linker");
            LibraryLoader.incrementRelinkerCountNotHitHistogram();
        }
        catch(UnsatisfiedLinkError ) {
            if(!ResourceExtractor.PLATFORM_REQUIRES_NATIVE_FALLBACK_EXTRACTION) {
                return;
            }

            System.load(LibraryLoader.getExtractedLibraryPath(ContextUtils.getApplicationContext(), "chromium_android_linker"));
            LibraryLoader.incrementRelinkerCountHitHistogram();
        }
    }

    private static native long nativeGetRandomBaseLoadAddress() {
    }

    public abstract void prepareLibraryLoad();

    protected final void runTestRunnerClassForTesting(int arg8, boolean arg9) {
        Linker.assertLinkerTestsAreEnabled();
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.mTestRunnerClassName == null) {
                Log.wtf("LibraryLoader", "Linker runtime tests not set up for this process", new Object[0]);
                Linker.assertForTesting(false);
            }
        }
        catch(Throwable v8) {
            goto label_42;
        }

        try {
            Object v1 = Class.forName(this.mTestRunnerClassName).getDeclaredConstructor().newInstance();
            goto label_28;
        }
        catch(Throwable v8) {
        label_43:
            throw v8;
        }
        catch(Exception v3) {
            try {
                Log.wtf("LibraryLoader", "Could not instantiate test runner class by name", new Object[]{v3});
                Linker.assertForTesting(false);
            label_28:
                if(!((TestRunner)v1).runChecks(arg8, arg9)) {
                    Log.wtf("LibraryLoader", "Linker runtime tests failed in this process", new Object[0]);
                    Linker.assertForTesting(false);
                }

                Log.i("LibraryLoader", "All linker tests passed", new Object[0]);
                __monitor_exit(v0);
                return;
            label_42:
                __monitor_exit(v0);
                goto label_43;
            }
            catch(Throwable v8) {
                goto label_42;
            }
        }
    }

    public final void setMemoryDeviceConfigForTesting(int arg5) {
        Linker.assertLinkerTestsAreEnabled();
        boolean v0 = false;
        boolean v2 = arg5 == 1 || arg5 == 2 ? true : false;
        Linker.assertForTesting(v2);
        Object v2_1 = this.mLock;
        __monitor_enter(v2_1);
        try {
            if(this.mMemoryDeviceConfig == 0) {
                v0 = true;
            }

            Linker.assertForTesting(v0);
            this.mMemoryDeviceConfig = arg5;
            __monitor_exit(v2_1);
            return;
        label_21:
            __monitor_exit(v2_1);
        }
        catch(Throwable v5) {
            goto label_21;
        }

        throw v5;
    }

    public final void setTestRunnerClassNameForTesting(String arg3) {
        Linker.assertLinkerTestsAreEnabled();
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            boolean v1 = this.mTestRunnerClassName == null ? true : false;
            Linker.assertForTesting(v1);
            this.mTestRunnerClassName = arg3;
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public static final void setupForTesting(String arg4) {
        Linker.assertLinkerTestsAreEnabled();
        Object v0 = Linker.sSingletonLock;
        __monitor_enter(v0);
        try {
            boolean v2 = false;
            if(Linker.sSingleton == null) {
                Linker.assertLinkerTestsAreEnabled();
                if(Linker.sSingleton == null) {
                    v2 = true;
                }

                Linker.assertForTesting(v2);
                Linker.sSingleton = LegacyLinker.create();
                Linker.sSingleton.setTestRunnerClassNameForTesting(arg4);
                __monitor_exit(v0);
                return;
            }

            String v1 = Linker.sSingleton.getTestRunnerClassNameForTesting();
            if(arg4 == null) {
                if(v1 == null) {
                    v2 = true;
                }

                Linker.assertForTesting(v2);
            }
            else {
                Linker.assertForTesting(v1.equals(arg4));
            }

            __monitor_exit(v0);
            return;
        label_30:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_30;
        }

        throw v4;
    }

    public abstract void useSharedRelros(Bundle arg1);
}

