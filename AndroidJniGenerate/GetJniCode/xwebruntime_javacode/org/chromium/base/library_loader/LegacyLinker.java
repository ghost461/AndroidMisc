package org.chromium.base.library_loader;

import android.os.Bundle;
import android.os.Parcel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map$Entry;
import javax.annotation.Nullable;
import org.chromium.base.Log;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

class LegacyLinker extends Linker {
    private static final String TAG = "LibraryLoader";
    private long mBaseLoadAddress;
    private boolean mBrowserUsesSharedRelro;
    private long mCurrentLoadAddress;
    private boolean mInBrowserProcess;
    private boolean mInitialized;
    private HashMap mLoadedLibraries;
    private boolean mPrepareLibraryLoadCalled;
    private Bundle mSharedRelros;
    private boolean mWaitForSharedRelros;

    static {
    }

    private LegacyLinker() {
        super();
        this.mInBrowserProcess = true;
        this.mBaseLoadAddress = -1;
        this.mCurrentLoadAddress = -1;
    }

    static void access$000(long arg0) {
        LegacyLinker.nativeRunCallbackOnUiThread(arg0);
    }

    static Linker create() {
        return new LegacyLinker();
    }

    public void disableSharedRelros() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.ensureInitializedLocked();
            this.mInBrowserProcess = false;
            this.mWaitForSharedRelros = false;
            this.mBrowserUsesSharedRelro = false;
            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_10;
        }

        throw v1;
    }

    private void dumpBundle(Bundle arg1) {
    }

    private void ensureInitializedLocked() {
        if(!this.mInitialized) {
            if(!NativeLibraries.sUseLinker) {
            }
            else {
                LegacyLinker.loadLinkerJniLibrary();
                if(this.mMemoryDeviceConfig == 0) {
                    this.mMemoryDeviceConfig = SysUtils.isLowEndDevice() ? 1 : 2;
                }

                switch(1) {
                    case 0: {
                        this.mBrowserUsesSharedRelro = false;
                        break;
                    }
                    case 1: {
                        if(this.mMemoryDeviceConfig == 1) {
                            this.mBrowserUsesSharedRelro = true;
                            Log.w("LibraryLoader", "Low-memory device: shared RELROs used in all processes", new Object[0]);
                            goto label_41;
                        }

                        this.mBrowserUsesSharedRelro = false;
                        break;
                    }
                    case 2: {
                        Log.w("LibraryLoader", "Beware: shared RELROs used in all processes!", new Object[0]);
                        this.mBrowserUsesSharedRelro = true;
                        break;
                    }
                    default: {
                        Log.wtf("LibraryLoader", "FATAL: illegal shared RELRO config", new Object[0]);
                        throw new AssertionError();
                    }
                }

            label_41:
                this.mInitialized = true;
                return;
            }
        }
    }

    public void finishLibraryLoad() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.ensureInitializedLocked();
            if(this.mLoadedLibraries != null) {
                if(this.mInBrowserProcess) {
                    this.mSharedRelros = this.createBundleFromLibInfoMap(this.mLoadedLibraries);
                    if(this.mBrowserUsesSharedRelro) {
                        this.useSharedRelrosLocked(this.mSharedRelros);
                    }
                }

                if(this.mWaitForSharedRelros) {
                    while(this.mSharedRelros == null) {
                        try {
                            this.mLock.wait();
                        }
                        catch(InterruptedException ) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    this.useSharedRelrosLocked(this.mSharedRelros);
                    this.mSharedRelros.clear();
                    this.mSharedRelros = null;
                }
            }

            if(NativeLibraries.sEnableLinkerTests) {
                this.runTestRunnerClassForTesting(this.mMemoryDeviceConfig, this.mInBrowserProcess);
            }

            __monitor_exit(v0);
            return;
        label_39:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_39;
        }

        throw v1;
    }

    public long getBaseLoadAddress() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.ensureInitializedLocked();
            if(!this.mInBrowserProcess) {
                Log.w("LibraryLoader", "Shared RELRO sections are disabled in this process!", new Object[0]);
                __monitor_exit(v0);
                return 0;
            }

            this.setupBaseLoadAddressLocked();
            __monitor_exit(v0);
            return this.mBaseLoadAddress;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_18;
        }

        throw v1;
    }

    public Bundle getSharedRelros() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(!this.mInBrowserProcess) {
                __monitor_exit(v0);
                return null;
            }

            __monitor_exit(v0);
            return this.mSharedRelros;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    public void initServiceProcess(long arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.ensureInitializedLocked();
            this.mInBrowserProcess = false;
            this.mBrowserUsesSharedRelro = false;
            this.mWaitForSharedRelros = true;
            this.mBaseLoadAddress = arg3;
            this.mCurrentLoadAddress = arg3;
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

    public boolean isUsingBrowserSharedRelros() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.ensureInitializedLocked();
            boolean v1_1 = !this.mInBrowserProcess || !this.mBrowserUsesSharedRelro ? false : true;
            __monitor_exit(v0);
            return v1_1;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_13;
        }

        throw v1;
    }

    void loadLibraryImpl(@Nullable String arg19, String arg20, boolean arg21) {
        Throwable v2_2;
        long v9;
        LegacyLinker v1 = this;
        String v2 = arg19;
        String v3 = arg20;
        Object v4 = v1.mLock;
        __monitor_enter(v4);
        try {
            this.ensureInitializedLocked();
            if(v1.mLoadedLibraries == null) {
                v1.mLoadedLibraries = new HashMap();
            }

            if(v1.mLoadedLibraries.containsKey(v3)) {
                __monitor_exit(v4);
                return;
            }

            LibInfo v5 = new LibInfo();
            if(arg21) {
                if((!v1.mInBrowserProcess || !v1.mBrowserUsesSharedRelro) && !v1.mWaitForSharedRelros) {
                    goto label_43;
                }

                v9 = v1.mCurrentLoadAddress;
                if(v9 <= v1.mBaseLoadAddress + 0xC000000) {
                    goto label_44;
                }

                v2 = "Load address outside reservation, for: " + v3;
                Log.e("LibraryLoader", v2, new Object[0]);
                throw new UnsatisfiedLinkError(v2);
            }
            else {
            label_43:
                v9 = 0;
            }

        label_44:
            if(v2 != null) {
                if(!LegacyLinker.nativeLoadLibraryInZipFile(v2, v3, v9, v5)) {
                    v2 = "Unable to load library: " + v3 + ", in: " + v2;
                    Log.e("LibraryLoader", v2, new Object[0]);
                    throw new UnsatisfiedLinkError(v2);
                }
            }
            else if(!LegacyLinker.nativeLoadLibrary(v3, v9, v5)) {
                v2 = "Unable to load library: " + v3;
                Log.e("LibraryLoader", v2, new Object[0]);
                throw new UnsatisfiedLinkError(v2);
            }
            else {
                v2 = v3;
            }

            int v12 = 2;
            if(NativeLibraries.sEnableLinkerTests) {
                String v11 = v1.mInBrowserProcess ? "BROWSER_LIBRARY_ADDRESS" : "RENDERER_LIBRARY_ADDRESS";
                Locale v15 = Locale.US;
                Object[] v7 = new Object[3];
                v7[0] = v11;
                v7[1] = v3;
                v7[v12] = Long.valueOf(v5.mLoadAddress);
                Log.i("LibraryLoader", String.format(v15, "%s: %s %x", v7), new Object[0]);
            }

            if((v1.mInBrowserProcess) && !LegacyLinker.nativeCreateSharedRelro(v2, v1.mCurrentLoadAddress, v5)) {
                Locale v7_1 = Locale.US;
                Log.w("LibraryLoader", String.format(v7_1, "Could not create shared RELRO for %s at %x", v3, Long.valueOf(v1.mCurrentLoadAddress)), new Object[0]);
            }

            long v6 = 0;
            if(v9 != v6 && v1.mCurrentLoadAddress != v6) {
                v1.mCurrentLoadAddress = v5.mLoadAddress + v5.mLoadSize + 0x1000000;
            }

            v1.mLoadedLibraries.put(v2, v5);
            __monitor_exit(v4);
            return;
        label_134:
            v2_2 = v0;
            __monitor_exit(v4);
        }
        catch(Throwable v0) {
            goto label_134;
        }

        throw v2_2;
    }

    private static native boolean nativeCreateSharedRelro(String arg0, long arg1, LibInfo arg2) {
    }

    private static native boolean nativeLoadLibrary(String arg0, long arg1, LibInfo arg2) {
    }

    private static native boolean nativeLoadLibraryInZipFile(@Nullable String arg0, String arg1, long arg2, LibInfo arg3) {
    }

    private static native void nativeRunCallbackOnUiThread(long arg0) {
    }

    private static native boolean nativeUseSharedRelro(String arg0, LibInfo arg1) {
    }

    @CalledByNative @MainDex public static void postCallbackOnMainThread(long arg1) {
        ThreadUtils.postOnUiThread(new Runnable(arg1) {
            public void run() {
                LegacyLinker.nativeRunCallbackOnUiThread(this.val$opaque);
            }
        });
    }

    public void prepareLibraryLoad() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.ensureInitializedLocked();
            this.mPrepareLibraryLoadCalled = true;
            if(this.mInBrowserProcess) {
                this.setupBaseLoadAddressLocked();
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    private void setupBaseLoadAddressLocked() {
        if(this.mBaseLoadAddress == -1) {
            this.mBaseLoadAddress = this.getRandomBaseLoadAddress();
            this.mCurrentLoadAddress = this.mBaseLoadAddress;
            if(this.mBaseLoadAddress == 0) {
                Log.w("LibraryLoader", "Disabling shared RELROs due address space pressure", new Object[0]);
                this.mBrowserUsesSharedRelro = false;
                this.mWaitForSharedRelros = false;
            }
        }
    }

    public void useSharedRelros(Bundle arg4) {
        Bundle v0;
        if(arg4 != null) {
            arg4.setClassLoader(LibInfo.class.getClassLoader());
            v0 = new Bundle(LibInfo.class.getClassLoader());
            Parcel v1 = Parcel.obtain();
            arg4.writeToParcel(v1, 0);
            v1.setDataPosition(0);
            v0.readFromParcel(v1);
            v1.recycle();
        }
        else {
            v0 = null;
        }

        Object v4 = this.mLock;
        __monitor_enter(v4);
        try {
            this.mSharedRelros = v0;
            this.mLock.notifyAll();
            __monitor_exit(v4);
            return;
        label_24:
            __monitor_exit(v4);
        }
        catch(Throwable v0_1) {
            goto label_24;
        }

        throw v0_1;
    }

    private void useSharedRelrosLocked(Bundle arg6) {
        if(arg6 == null) {
            return;
        }

        if(this.mLoadedLibraries == null) {
            return;
        }

        HashMap v6 = this.createLibInfoMapFromBundle(arg6);
        Iterator v0 = v6.entrySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v2 = ((Map$Entry)v1).getKey();
            if(LegacyLinker.nativeUseSharedRelro(((String)v2), ((Map$Entry)v1).getValue())) {
                continue;
            }

            Log.w("LibraryLoader", "Could not use shared RELRO section for " + (((String)v2)), new Object[0]);
        }

        if(!this.mInBrowserProcess) {
            this.closeLibInfoMap(v6);
        }
    }
}

