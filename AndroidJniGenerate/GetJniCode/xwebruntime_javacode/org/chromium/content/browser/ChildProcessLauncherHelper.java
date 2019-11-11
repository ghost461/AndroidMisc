package org.chromium.content.browser;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ContextUtils;
import org.chromium.base.CpuFeatures;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.process_launcher.ChildConnectionAllocator$ConnectionFactory;
import org.chromium.base.process_launcher.ChildConnectionAllocator$Listener;
import org.chromium.base.process_launcher.ChildConnectionAllocator;
import org.chromium.base.process_launcher.ChildProcessConnection$ServiceCallback;
import org.chromium.base.process_launcher.ChildProcessConnection;
import org.chromium.base.process_launcher.ChildProcessLauncher$Delegate;
import org.chromium.base.process_launcher.ChildProcessLauncher;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.app.ChromiumLinkerParams;
import org.chromium.content.app.SandboxedProcessService;
import org.chromium.content.common.ContentSwitches;

@JNINamespace(value="content::internal") public class ChildProcessLauncherHelper {
    class org.chromium.content.browser.ChildProcessLauncherHelper$1 extends Delegate {
        static {
        }

        org.chromium.content.browser.ChildProcessLauncherHelper$1(ChildProcessLauncherHelper arg1) {
            ChildProcessLauncherHelper.this = arg1;
            super();
        }

        public ChildProcessConnection getBoundConnection(ChildConnectionAllocator arg2, ServiceCallback arg3) {
            if(ChildProcessLauncherHelper.sSpareSandboxedConnection == null) {
                return null;
            }

            return ChildProcessLauncherHelper.sSpareSandboxedConnection.getConnection(arg2, arg3);
        }

        public void onBeforeConnectionAllocated(Bundle arg1) {
            ChildProcessLauncherHelper.populateServiceBundle(arg1);
        }

        public void onBeforeConnectionSetup(Bundle arg4) {
            arg4.putInt("com.google.android.apps.chrome.extra.cpu_count", CpuFeatures.getCount());
            arg4.putLong("com.google.android.apps.chrome.extra.cpu_features", CpuFeatures.getMask());
            if(Linker.isUsed()) {
                arg4.putBundle("org.chromium.base.android.linker.shared_relros", Linker.getInstance().getSharedRelros());
            }
        }

        public void onConnectionEstablished(ChildProcessConnection arg6) {
            ChildProcessLauncherHelper.sLauncherByPid.put(Integer.valueOf(arg6.getPid()), ChildProcessLauncherHelper.this);
            long v2 = 0;
            if(ChildProcessLauncherHelper.this.mNativeChildProcessLauncherHelper != v2) {
                ChildProcessLauncherHelper.nativeOnChildProcessStarted(ChildProcessLauncherHelper.this.mNativeChildProcessLauncherHelper, arg6.getPid());
            }

            ChildProcessLauncherHelper.this.mNativeChildProcessLauncherHelper = v2;
        }

        public void onConnectionLost(ChildProcessConnection arg3) {
            ChildProcessLauncherHelper.sLauncherByPid.remove(Integer.valueOf(arg3.getPid()));
            BindingManager v0 = ChildProcessLauncherHelper.getBindingManager();
            if((ChildProcessLauncherHelper.this.mUseBindingManager) && v0 != null) {
                v0.dropRecency(arg3);
            }
        }
    }

    private static final String NUM_PRIVILEGED_SERVICES_KEY = "org.chromium.content.browser.NUM_PRIVILEGED_SERVICES";
    private static final String NUM_SANDBOXED_SERVICES_KEY = "org.chromium.content.browser.NUM_SANDBOXED_SERVICES";
    private static final String PRIVILEGED_SERVICES_NAME = "org.chromium.content.app.PrivilegedProcessService";
    private static final String SANDBOXED_SERVICES_NAME = "org.chromium.content.app.SandboxedProcessService";
    private static final String TAG = "ChildProcLH";
    private boolean mBoostPriorityForPendingViews;
    private boolean mForeground;
    private int mImportance;
    private final ChildProcessLauncher mLauncher;
    private final Delegate mLauncherDelegate;
    private long mNativeChildProcessLauncherHelper;
    private final String mProcessType;
    private final boolean mSandboxed;
    private final boolean mUseBindingManager;
    private static boolean sApplicationInForeground = true;
    private static BindingManager sBindingManager = null;
    private static final Map sLauncherByPid = null;
    private static boolean sLinkerInitialized = false;
    private static long sLinkerLoadAddress = 0;
    private static ChildConnectionAllocator sPrivilegedChildConnectionAllocator = null;
    private static ChildConnectionAllocator sSandboxedChildConnectionAllocator = null;
    private static ConnectionFactory sSandboxedServiceFactoryForTesting = null;
    private static int sSandboxedServicesCountForTesting = -1;
    private static String sSandboxedServicesNameForTesting;
    private static SpareChildConnection sSpareSandboxedConnection;

    static {
        ChildProcessLauncherHelper.sLauncherByPid = new HashMap();
    }

    private ChildProcessLauncherHelper(long arg10, String[] arg12, FileDescriptorInfo[] arg13, boolean arg14, IBinder arg15) {
        super();
        this.mLauncherDelegate = new org.chromium.content.browser.ChildProcessLauncherHelper$1(this);
        this.mBoostPriorityForPendingViews = true;
        this.mImportance = 0;
        this.mNativeChildProcessLauncherHelper = arg10;
        this.mUseBindingManager = arg14;
        this.mSandboxed = arg14;
        ChildConnectionAllocator v7 = ChildProcessLauncherHelper.getConnectionAllocator(ContextUtils.getApplicationContext(), arg14);
        ChildProcessLauncher v10 = null;
        Handler v3 = LauncherThread.getHandler();
        Delegate v4 = this.mLauncherDelegate;
        List v11 = arg15 == null ? null : Arrays.asList(new IBinder[]{arg15});
        List v8 = v11;
        super(v3, v4, arg12, arg13, v7, v8);
        this.mLauncher = v10;
        this.mProcessType = ContentSwitches.getSwitchValue(arg12, "type");
    }

    static SpareChildConnection access$000() {
        return ChildProcessLauncherHelper.sSpareSandboxedConnection;
    }

    static Bundle access$100(Bundle arg0) {
        return ChildProcessLauncherHelper.populateServiceBundle(arg0);
    }

    static Map access$200() {
        return ChildProcessLauncherHelper.sLauncherByPid;
    }

    static long access$300(ChildProcessLauncherHelper arg2) {
        return arg2.mNativeChildProcessLauncherHelper;
    }

    static long access$302(ChildProcessLauncherHelper arg0, long arg1) {
        arg0.mNativeChildProcessLauncherHelper = arg1;
        return arg1;
    }

    static void access$400(long arg0, int arg2) {
        ChildProcessLauncherHelper.nativeOnChildProcessStarted(arg0, arg2);
    }

    static BindingManager access$500() {
        return ChildProcessLauncherHelper.getBindingManager();
    }

    static boolean access$600(ChildProcessLauncherHelper arg0) {
        return arg0.mUseBindingManager;
    }

    static void access$700(Context arg0) {
        ChildProcessLauncherHelper.warmUpOnLauncherThread(arg0);
    }

    static BindingManager access$802(BindingManager arg0) {
        ChildProcessLauncherHelper.sBindingManager = arg0;
        return arg0;
    }

    @VisibleForTesting public static boolean crashProcessForTesting(int arg2) {
        if(ChildProcessLauncherHelper.sLauncherByPid.get(Integer.valueOf(arg2)) == null) {
            return 0;
        }

        ChildProcessConnection v2 = ChildProcessLauncherHelper.sLauncherByPid.get(Integer.valueOf(arg2)).mLauncher.getConnection();
        if(v2 == null) {
            return 0;
        }

        try {
            v2.crashServiceForTesting();
            return 1;
        }
        catch(RemoteException ) {
            return 0;
        }
    }

    @VisibleForTesting @CalledByNative public static ChildProcessLauncherHelper createAndStart(long arg11, String[] arg13, FileDescriptorInfo[] arg14) {
        boolean v9;
        String v0 = ContentSwitches.getSwitchValue(arg13, "type");
        if(!"renderer".equals(v0)) {
            if(!"gpu-process".equals(v0)) {
                if("network".equals(ContentSwitches.getSwitchValue(arg13, "service-sandbox-type"))) {
                }
                else {
                    goto label_18;
                }
            }

            v9 = false;
        }
        else {
        label_18:
            v9 = true;
        }

        GpuProcessCallback v0_1 = "gpu-process".equals(v0) ? new GpuProcessCallback() : null;
        GpuProcessCallback v10 = v0_1;
        ChildProcessLauncherHelper v0_2 = new ChildProcessLauncherHelper(arg11, arg13, arg14, v9, ((IBinder)v10));
        v0_2.mLauncher.start(true, true);
        return v0_2;
    }

    @VisibleForTesting public static ChildProcessLauncherHelper createAndStartForTesting(String[] arg8, FileDescriptorInfo[] arg9, boolean arg10, IBinder arg11, boolean arg12) {
        ChildProcessLauncherHelper v7 = new ChildProcessLauncherHelper(0, arg8, arg9, arg10, arg11);
        v7.mLauncher.start(arg12, true);
        return v7;
    }

    @VisibleForTesting public static Map getAllProcessesForTesting() {
        return ChildProcessLauncherHelper.sLauncherByPid;
    }

    private static BindingManager getBindingManager() {
        return ChildProcessLauncherHelper.sBindingManager;
    }

    public static ChildProcessLauncherHelper getByPid(int arg1) {
        return ChildProcessLauncherHelper.sLauncherByPid.get(Integer.valueOf(arg1));
    }

    @VisibleForTesting public ChildConnectionAllocator getChildConnectionAllocatorForTesting() {
        return this.mLauncher.getConnectionAllocator();
    }

    @VisibleForTesting public ChildProcessConnection getChildProcessConnection() {
        return this.mLauncher.getConnection();
    }

    @VisibleForTesting public static int getConnectedSandboxedServicesCountForTesting() {
        int v0 = ChildProcessLauncherHelper.sSandboxedChildConnectionAllocator == null ? 0 : ChildProcessLauncherHelper.sSandboxedChildConnectionAllocator.allocatedConnectionsCountForTesting();
        return v0;
    }

    @VisibleForTesting static int getConnectedServicesCountForTesting() {
        int v0 = ChildProcessLauncherHelper.sPrivilegedChildConnectionAllocator == null ? 0 : ChildProcessLauncherHelper.sPrivilegedChildConnectionAllocator.allocatedConnectionsCountForTesting();
        return v0 + ChildProcessLauncherHelper.getConnectedSandboxedServicesCountForTesting();
    }

    @VisibleForTesting static ChildConnectionAllocator getConnectionAllocator(Context arg8, boolean arg9) {
        ChildConnectionAllocator v8_1;
        String v2 = ChildProcessCreationParams.getPackageNameForService();
        boolean v5 = ChildProcessCreationParams.getBindToCallerCheck();
        boolean v6 = !arg9 || !ChildProcessCreationParams.getIsSandboxedServiceExternal() ? false : true;
        if(!arg9) {
            if(ChildProcessLauncherHelper.sPrivilegedChildConnectionAllocator == null) {
                ChildProcessLauncherHelper.sPrivilegedChildConnectionAllocator = ChildConnectionAllocator.create(arg8, LauncherThread.getHandler(), v2, "org.chromium.content.app.PrivilegedProcessService", "org.chromium.content.browser.NUM_PRIVILEGED_SERVICES", v5, v6, true);
            }

            return ChildProcessLauncherHelper.sPrivilegedChildConnectionAllocator;
        }

        if(ChildProcessLauncherHelper.sSandboxedChildConnectionAllocator == null) {
            Log.w("ChildProcLH", "Create a new ChildConnectionAllocator with package name = %s, sandboxed = true", new Object[]{v2});
            if(ChildProcessLauncherHelper.sSandboxedServicesCountForTesting != -1) {
                String v8 = !TextUtils.isEmpty(ChildProcessLauncherHelper.sSandboxedServicesNameForTesting) ? ChildProcessLauncherHelper.sSandboxedServicesNameForTesting : SandboxedProcessService.class.getName();
                String v1 = v8;
                v8_1 = ChildConnectionAllocator.createForTest(v2, v1, ChildProcessLauncherHelper.sSandboxedServicesCountForTesting, v5, v6, false);
            }
            else {
                v8_1 = ChildConnectionAllocator.create(arg8, LauncherThread.getHandler(), v2, "org.chromium.content.app.SandboxedProcessService", "org.chromium.content.browser.NUM_SANDBOXED_SERVICES", v5, v6, false);
            }

            if(ChildProcessLauncherHelper.sSandboxedServiceFactoryForTesting != null) {
                v8_1.setConnectionFactoryForTesting(ChildProcessLauncherHelper.sSandboxedServiceFactoryForTesting);
            }

            ChildProcessLauncherHelper.sSandboxedChildConnectionAllocator = v8_1;
            v8_1.addListener(new Listener(v8_1) {
                public void onConnectionAllocated(ChildConnectionAllocator arg1, ChildProcessConnection arg2) {
                    if(!arg1.isFreeConnectionAvailable()) {
                        BindingManager v1 = ChildProcessLauncherHelper.getBindingManager();
                        if(v1 != null) {
                            v1.releaseAllModerateBindings();
                        }
                    }
                }
            });
        }

        return ChildProcessLauncherHelper.sSandboxedChildConnectionAllocator;
    }

    private static ChromiumLinkerParams getLinkerParamsForNewConnection() {
        ChildProcessLauncherHelper.initLinker();
        if(ChildProcessLauncherHelper.sLinkerLoadAddress == 0) {
            return null;
        }

        if(Linker.areTestsEnabled()) {
            return new ChromiumLinkerParams(ChildProcessLauncherHelper.sLinkerLoadAddress, true, Linker.getInstance().getTestRunnerClassNameForTesting());
        }

        return new ChromiumLinkerParams(ChildProcessLauncherHelper.sLinkerLoadAddress, true);
    }

    @CalledByNative private static int getNumberOfRendererSlots() {
        if(ChildProcessLauncherHelper.sSandboxedServicesCountForTesting != -1) {
            return ChildProcessLauncherHelper.sSandboxedServicesCountForTesting;
        }

        Context v0 = ContextUtils.getApplicationContext();
        String v1 = ChildProcessCreationParams.getPackageNameForService();
        try {
            return ChildConnectionAllocator.getNumberOfServices(v0, v1, "org.chromium.content.browser.NUM_SANDBOXED_SERVICES");
        }
        catch(RuntimeException ) {
            return 0xFFFF;
        }
    }

    public int getPid() {
        return this.mLauncher.getPid();
    }

    public static void getProcessIdsByType(Callback arg2) {
        LauncherThread.post(new ChildProcessLauncherHelper$$Lambda$0(new Handler(), arg2));
    }

    public String getProcessType() {
        String v0 = TextUtils.isEmpty(this.mProcessType) ? "" : this.mProcessType;
        return v0;
    }

    @VisibleForTesting public static ChildProcessConnection getWarmUpConnectionForTesting() {
        ChildProcessConnection v0 = ChildProcessLauncherHelper.sSpareSandboxedConnection == null ? null : ChildProcessLauncherHelper.sSpareSandboxedConnection.getConnection();
        return v0;
    }

    private static void initLinker() {
        if(ChildProcessLauncherHelper.sLinkerInitialized) {
            return;
        }

        if(Linker.isUsed()) {
            ChildProcessLauncherHelper.sLinkerLoadAddress = Linker.getInstance().getBaseLoadAddress();
            if(ChildProcessLauncherHelper.sLinkerLoadAddress == 0) {
                Log.i("ChildProcLH", "Shared RELRO support disabled!", new Object[0]);
            }
        }

        ChildProcessLauncherHelper.sLinkerInitialized = true;
    }

    @CalledByNative private boolean isOomProtected() {
        ChildProcessConnection v0 = this.mLauncher.getConnection();
        boolean v1 = false;
        if(v0 == null) {
            return 0;
        }

        if((ChildProcessLauncherHelper.sApplicationInForeground) && !v0.isWaivedBoundOnlyOrWasWhenDied()) {
            v1 = true;
        }

        return v1;
    }

    static final void lambda$getProcessIdsByType$2$ChildProcessLauncherHelper(Handler arg3, Callback arg4) {
        HashMap v0 = new HashMap();
        CollectionUtil.forEach(ChildProcessLauncherHelper.sLauncherByPid, new ChildProcessLauncherHelper$$Lambda$1(((Map)v0)));
        arg3.post(new ChildProcessLauncherHelper$$Lambda$2(arg4, ((Map)v0)));
    }

    static final void lambda$null$0$ChildProcessLauncherHelper(Map arg2, Map$Entry arg3) {
        ArrayList v1_1;
        String v0 = arg3.getValue().getProcessType();
        Object v1 = arg2.get(v0);
        if(v1 == null) {
            v1_1 = new ArrayList();
            arg2.put(v0, v1_1);
        }

        ((List)v1_1).add(arg3.getKey());
    }

    static final void lambda$null$1$ChildProcessLauncherHelper(Callback arg0, Map arg1) {
        arg0.onResult(arg1);
    }

    @CalledByNative private static FileDescriptorInfo makeFdInfo(int arg7, int arg8, boolean arg9, long arg10, long arg12) {
        ParcelFileDescriptor v8;
        if(arg9) {
            v8 = ParcelFileDescriptor.adoptFd(arg8);
        }
        else {
            try {
                v8 = ParcelFileDescriptor.fromFd(arg8);
            }
            catch(IOException v7) {
                Log.e("ChildProcLH", "Invalid FD provided for process connection, aborting connection.", new Object[]{v7});
                return null;
            }
        }

        ParcelFileDescriptor v2 = v8;
        return new FileDescriptorInfo(arg7, v2, arg10, arg12);
    }

    private static native void nativeOnChildProcessStarted(long arg0, int arg1) {
    }

    public static void onBroughtToForeground() {
        ChildProcessLauncherHelper.sApplicationInForeground = true;
        LauncherThread.post(new Runnable() {
            public void run() {
                BindingManager v0 = ChildProcessLauncherHelper.getBindingManager();
                if(v0 != null) {
                    v0.onBroughtToForeground();
                }
            }
        });
    }

    public static void onSentToBackground() {
        ChildProcessLauncherHelper.sApplicationInForeground = false;
        LauncherThread.post(new Runnable() {
            public void run() {
                BindingManager v0 = ChildProcessLauncherHelper.getBindingManager();
                if(v0 != null) {
                    v0.onSentToBackground();
                }
            }
        });
    }

    private static Bundle populateServiceBundle(Bundle arg2) {
        ChildProcessCreationParams v0 = ChildProcessCreationParams.get();
        if(v0 != null) {
            v0.addIntentExtras(arg2);
        }

        arg2.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", ChildProcessCreationParams.getBindToCallerCheck());
        ChromiumLinkerParams v0_1 = ChildProcessLauncherHelper.getLinkerParamsForNewConnection();
        if(v0_1 != null) {
            v0_1.populateBundle(arg2);
        }

        return arg2;
    }

    @CalledByNative private void setPriority(int arg1, boolean arg2, long arg3, boolean arg5, int arg6) {
        ChildProcessConnection v1 = this.mLauncher.getConnection();
        boolean v4 = false;
        if(ChildProcessCreationParams.getIgnoreVisibilityForImportance()) {
        }
        else {
            v4 = arg5;
        }

        if(!this.mForeground && 0 != 0) {
            v1.addStrongBinding();
            BindingManager v3 = ChildProcessLauncherHelper.getBindingManager();
            if((this.mUseBindingManager) && v3 != null) {
                v3.increaseRecency(v1);
            }
        }

        if(!this.mBoostPriorityForPendingViews && (v4)) {
            v1.addInitialBinding();
        }

        if(this.mImportance != arg6) {
            switch(arg6) {
                case 1: {
                    goto label_27;
                }
                case 2: {
                    goto label_25;
                }
            }

            goto label_28;
        label_25:
            v1.addStrongBinding();
            goto label_28;
        label_27:
            v1.addModerateBinding();
        }

    label_28:
        if((this.mForeground) && 0 == 0) {
            v1.removeStrongBinding();
        }

        if((this.mBoostPriorityForPendingViews) && !v4) {
            v1.removeInitialBinding();
        }

        if(this.mImportance != arg6) {
            switch(this.mImportance) {
                case 1: {
                    goto label_43;
                }
                case 2: {
                    goto label_41;
                }
            }

            goto label_44;
        label_41:
            v1.removeStrongBinding();
            goto label_44;
        label_43:
            v1.removeModerateBinding();
        }

    label_44:
        this.mForeground = false;
        this.mBoostPriorityForPendingViews = v4;
        this.mImportance = arg6;
    }

    @VisibleForTesting public static void setSandboxServicesSettingsForTesting(ConnectionFactory arg0, int arg1, String arg2) {
        ChildProcessLauncherHelper.sSandboxedServiceFactoryForTesting = arg0;
        ChildProcessLauncherHelper.sSandboxedServicesCountForTesting = arg1;
        ChildProcessLauncherHelper.sSandboxedServicesNameForTesting = arg2;
    }

    public static void startModerateBindingManagement(Context arg1) {
        LauncherThread.post(new Runnable(arg1) {
            public void run() {
                ChildProcessLauncherHelper.sBindingManager = new BindingManagerImpl(this.val$context, ChildProcessLauncherHelper.getConnectionAllocator(this.val$context, true).getNumberOfServices(), false);
            }
        });
    }

    @CalledByNative static void stop(int arg3) {
        Log.d("ChildProcLH", "stopping child connection: pid=%d", Integer.valueOf(arg3));
        ChildProcessLauncherHelper v3 = ChildProcessLauncherHelper.getByPid(arg3);
        if(v3 != null) {
            v3.mLauncher.stop();
        }
    }

    public static void warmUp(Context arg1) {
        LauncherThread.post(new Runnable(arg1) {
            public void run() {
                ChildProcessLauncherHelper.warmUpOnLauncherThread(this.val$context);
            }
        });
    }

    private static void warmUpOnLauncherThread(Context arg3) {
        if(ChildProcessLauncherHelper.sSpareSandboxedConnection != null && !ChildProcessLauncherHelper.sSpareSandboxedConnection.isEmpty()) {
            return;
        }

        ChildProcessLauncherHelper.sSpareSandboxedConnection = new SpareChildConnection(arg3, ChildProcessLauncherHelper.getConnectionAllocator(arg3, true), ChildProcessLauncherHelper.populateServiceBundle(new Bundle()));
    }
}

