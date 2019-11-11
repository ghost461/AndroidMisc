package org.chromium.content.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.SparseArray;
import android.view.Surface;
import java.util.List;
import org.chromium.base.CommandLine;
import org.chromium.base.JNIUtils;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.UnguessableToken;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.base.memory.MemoryPressureUma;
import org.chromium.base.process_launcher.ChildProcessServiceDelegate;
import org.chromium.content.browser.ChildProcessCreationParams;
import org.chromium.content.common.IGpuProcessCallback$Stub;
import org.chromium.content.common.IGpuProcessCallback;
import org.chromium.content.common.SurfaceWrapper;
import org.chromium.content_public.common.ContentProcessInfo;

@JNINamespace(value="content") @MainDex public class ContentChildProcessServiceDelegate implements ChildProcessServiceDelegate {
    private static final String TAG = "ContentCPSDelegate";
    private int mCpuCount;
    private long mCpuFeatures;
    private SparseArray mFdsIdsToKeys;
    private IGpuProcessCallback mGpuCallback;
    private int mLibraryProcessType;
    private ChromiumLinkerParams mLinkerParams;

    static {
    }

    public ContentChildProcessServiceDelegate() {
        super();
        KillChildUncaughtExceptionHandler.maybeInstallHandler();
    }

    @CalledByNative private void forwardSurfaceTextureForSurfaceRequest(UnguessableToken arg5, SurfaceTexture arg6) {
        if(this.mGpuCallback == null) {
            Log.e("ContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            return;
        }

        Surface v0 = new Surface(arg6);
        try {
            this.mGpuCallback.forwardSurfaceForSurfaceRequest(arg5, v0);
        }
        catch(Throwable v5) {
        label_25:
            v0.release();
            throw v5;
        }
        catch(RemoteException v5_1) {
            try {
                Log.e("ContentCPSDelegate", "Unable to call forwardSurfaceForSurfaceRequest: %s", new Object[]{v5_1});
            }
            catch(Throwable v5) {
                goto label_25;
            }

            v0.release();
            return;
        }

        v0.release();
    }

    public SparseArray getFileDescriptorsIdsToKeys() {
        return this.mFdsIdsToKeys;
    }

    private Linker getLinker() {
        if(Linker.areTestsEnabled()) {
            Linker.setupForTesting(this.mLinkerParams.mTestRunnerClassNameForTesting);
        }

        return Linker.getInstance();
    }

    @CalledByNative private Surface getViewSurface(int arg6) {
        Surface v6_2;
        Surface v2 = null;
        if(this.mGpuCallback == null) {
            Log.e("ContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            return v2;
        }

        try {
            SurfaceWrapper v6_1 = this.mGpuCallback.getViewSurface(arg6);
            if(v6_1 != null) {
                v6_2 = v6_1.getSurface();
            }
            else {
                return v2;
            }

            return v6_2;
        }
        catch(RemoteException v6) {
            Log.e("ContentCPSDelegate", "Unable to call getViewSurface: %s", new Object[]{v6});
            return v2;
        }

        return v2;
    }

    static final void lambda$onBeforeMain$0$ContentChildProcessServiceDelegate() {
        MemoryPressureUma.initializeForChildService();
    }

    public boolean loadNativeLibrary(Context arg11) {
        boolean v6;
        int v1_2;
        LibraryLoader v5_1;
        boolean v0_1;
        Linker v4;
        if(CommandLine.getInstance().getSwitchValue("type") != null) {
            JNIUtils.enableSelectiveJniRegistration();
        }

        LibraryLoader v1 = null;
        if(Linker.isUsed()) {
            Linker v0 = this.getLinker();
            if(this.mLinkerParams.mWaitForSharedRelro) {
                v0.initServiceProcess(this.mLinkerParams.mBaseLoadAddress);
                v4 = v0;
                v0_1 = true;
            }
            else {
                v0.disableSharedRelros();
                v4 = v0;
                goto label_24;
            }
        }
        else {
            v4 = ((Linker)v1);
        label_24:
            v0_1 = false;
        }

        try {
            v5_1 = LibraryLoader.get(this.mLibraryProcessType);
        }
        catch(ProcessInitException v5) {
            goto label_36;
        }

        try {
            v5_1.loadNowOverrideApplicationContext(arg11);
            v1_2 = 1;
            goto label_52;
        }
        catch(ProcessInitException v1_1) {
            LibraryLoader v9 = v5_1;
            v5 = v1_1;
            v1 = v9;
        }

    label_36:
        if(v0_1) {
            Log.w("ContentCPSDelegate", "Failed to load native library with shared RELRO, retrying without", new Object[0]);
            v5_1 = v1;
            v1_2 = 0;
            v6 = true;
        }
        else {
            Log.e("ContentCPSDelegate", "Failed to load native library", new Object[]{v5});
            v5_1 = v1;
            v1_2 = 0;
        label_52:
            v6 = false;
        }

        if(v1_2 == 0 && v5_1 != null && (v0_1)) {
            v4.disableSharedRelros();
            try {
                v5_1.loadNowOverrideApplicationContext(arg11);
                v1_2 = 1;
            }
            catch(ProcessInitException v11) {
                Log.e("ContentCPSDelegate", "Failed to load native library on retry", new Object[]{v11});
            }
        }

        if(v1_2 == 0) {
            return 0;
        }

        v5_1.registerRendererProcessHistogram(v0_1, v6);
        try {
            v5_1.initialize();
        }
        catch(ProcessInitException v11) {
            Log.w("ContentCPSDelegate", "startup failed: %s", new Object[]{v11});
            return 0;
        }

        this.nativeRetrieveFileDescriptorsIdsToKeys();
        return 1;
    }

    private native void nativeInitChildProcess(int arg1, long arg2) {
    }

    private native void nativeRetrieveFileDescriptorsIdsToKeys() {
    }

    private native void nativeShutdownMainThread() {
    }

    public void onBeforeMain() {
        this.nativeInitChildProcess(this.mCpuCount, this.mCpuFeatures);
        ThreadUtils.postOnUiThread(ContentChildProcessServiceDelegate$$Lambda$0.$instance);
    }

    public void onConnectionSetup(Bundle arg3, List arg4) {
        IGpuProcessCallback v4 = arg4 == null || (arg4.isEmpty()) ? null : Stub.asInterface(arg4.get(0));
        this.mGpuCallback = v4;
        this.mCpuCount = arg3.getInt("com.google.android.apps.chrome.extra.cpu_count");
        this.mCpuFeatures = arg3.getLong("com.google.android.apps.chrome.extra.cpu_features");
        arg3 = arg3.getBundle("org.chromium.base.android.linker.shared_relros");
        if(arg3 != null) {
            this.getLinker().useSharedRelros(arg3);
        }
    }

    public void onDestroy() {
        this.nativeShutdownMainThread();
    }

    public void onServiceBound(Intent arg2) {
        this.mLinkerParams = ChromiumLinkerParams.create(arg2.getExtras());
        this.mLibraryProcessType = ChildProcessCreationParams.getLibraryProcessType(arg2.getExtras());
    }

    public void onServiceCreated() {
        ContentProcessInfo.setInChildProcess(true);
    }

    public void preloadNativeLibrary(Context arg5) {
        try {
            LibraryLoader.get(this.mLibraryProcessType).preloadNowOverrideApplicationContext(arg5);
        }
        catch(ProcessInitException v5) {
            Log.w("ContentCPSDelegate", "Failed to preload native library", new Object[]{v5});
        }
    }

    public void runMain() {
        ContentMain.start();
    }

    @CalledByNative private void setFileDescriptorsIdsToKeys(int[] arg5, String[] arg6) {
        this.mFdsIdsToKeys = new SparseArray();
        int v0;
        for(v0 = 0; v0 < arg5.length; ++v0) {
            this.mFdsIdsToKeys.put(arg5[v0], arg6[v0]);
        }
    }
}

