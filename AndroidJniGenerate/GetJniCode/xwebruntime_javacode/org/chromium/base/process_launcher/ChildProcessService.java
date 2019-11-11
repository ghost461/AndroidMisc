package org.chromium.base.process_launcher;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.util.SparseArray;
import java.util.List;
import java.util.concurrent.Semaphore;
import javax.annotation.concurrent.GuardedBy;
import org.chromium.base.CommandLine;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;
import org.chromium.base.memory.MemoryPressureMonitor;

@JNINamespace(value="base::android") @MainDex public abstract class ChildProcessService extends Service {
    class org.chromium.base.process_launcher.ChildProcessService$1 extends Stub {
        static {
        }

        org.chromium.base.process_launcher.ChildProcessService$1(ChildProcessService arg1) {
            ChildProcessService.this = arg1;
            super();
        }

        public boolean bindToCaller() {
            Object v0 = ChildProcessService.this.mBinderLock;
            __monitor_enter(v0);
            try {
                int v1_1 = Binder.getCallingPid();
                if(ChildProcessService.this.mBoundCallingPid == 0) {
                    ChildProcessService.this.mBoundCallingPid = v1_1;
                }
                else if(ChildProcessService.this.mBoundCallingPid != v1_1) {
                    Log.e("ChildProcessService", "Service is already bound by pid %d, cannot bind for pid %d", new Object[]{Integer.valueOf(ChildProcessService.this.mBoundCallingPid), Integer.valueOf(v1_1)});
                    __monitor_exit(v0);
                    return 0;
                }

                __monitor_exit(v0);
                return 1;
            label_31:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_31;
            }

            throw v1;
        }

        public void crashIntentionallyForTesting() {
            Process.killProcess(Process.myPid());
        }

        public void onMemoryPressure(int arg2) {
            if(arg2 >= MemoryPressureMonitor.INSTANCE.getLastReportedPressure()) {
                MemoryPressureMonitor.INSTANCE.notifyPressure(arg2);
            }
        }

        public void setupConnection(Bundle arg3, ICallbackInt arg4, List arg5) throws RemoteException {
            Object v0 = ChildProcessService.this.mBinderLock;
            __monitor_enter(v0);
            try {
                if((ChildProcessService.this.mBindToCallerCheck) && ChildProcessService.this.mBoundCallingPid == 0) {
                    Log.e("ChildProcessService", "Service has not been bound with bindToCaller()", new Object[0]);
                    arg4.call(-1);
                    __monitor_exit(v0);
                    return;
                }

                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                try {
                label_25:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_25;
                }

                throw v3;
            }

            arg4.call(Process.myPid());
            ChildProcessService.this.processConnectionBundle(arg3, arg5);
        }
    }

    private static final String MAIN_THREAD_NAME = "ChildProcessMain";
    private static final String TAG = "ChildProcessService";
    private final Semaphore mActivitySemaphore;
    private boolean mBindToCallerCheck;
    private final Stub mBinder;
    private final Object mBinderLock;
    @GuardedBy(value="mBinderLock") private int mBoundCallingPid;
    private String[] mCommandLineParams;
    private final ChildProcessServiceDelegate mDelegate;
    private FileDescriptorInfo[] mFdInfos;
    @GuardedBy(value="mLibraryInitializedLock") private boolean mLibraryInitialized;
    private final Object mLibraryInitializedLock;
    private Thread mMainThread;
    private boolean mServiceBound;
    private static boolean sCreateCalled;

    static {
    }

    public ChildProcessService(ChildProcessServiceDelegate arg3) {
        super();
        this.mBinderLock = new Object();
        this.mLibraryInitializedLock = new Object();
        this.mActivitySemaphore = new Semaphore(1);
        this.mBinder = new org.chromium.base.process_launcher.ChildProcessService$1(this);
        this.mDelegate = arg3;
    }

    static boolean access$000(ChildProcessService arg0) {
        return arg0.mBindToCallerCheck;
    }

    static boolean access$100(ChildProcessService arg0) {
        return arg0.mServiceBound;
    }

    static FileDescriptorInfo[] access$1000(ChildProcessService arg0) {
        return arg0.mFdInfos;
    }

    static void access$1100(String[] arg0, int[] arg1, int[] arg2, long[] arg3, long[] arg4) {
        ChildProcessService.nativeRegisterFileDescriptors(arg0, arg1, arg2, arg3, arg4);
    }

    static Semaphore access$1200(ChildProcessService arg0) {
        return arg0.mActivitySemaphore;
    }

    static void access$1300() {
        ChildProcessService.nativeExitChildProcess();
    }

    static Object access$200(ChildProcessService arg0) {
        return arg0.mBinderLock;
    }

    static int access$300(ChildProcessService arg0) {
        return arg0.mBoundCallingPid;
    }

    static int access$302(ChildProcessService arg0, int arg1) {
        arg0.mBoundCallingPid = arg1;
        return arg1;
    }

    static void access$400(ChildProcessService arg0, Bundle arg1, List arg2) {
        arg0.processConnectionBundle(arg1, arg2);
    }

    static Thread access$500(ChildProcessService arg0) {
        return arg0.mMainThread;
    }

    static String[] access$600(ChildProcessService arg0) {
        return arg0.mCommandLineParams;
    }

    static ChildProcessServiceDelegate access$700(ChildProcessService arg0) {
        return arg0.mDelegate;
    }

    static Object access$800(ChildProcessService arg0) {
        return arg0.mLibraryInitializedLock;
    }

    static boolean access$902(ChildProcessService arg0, boolean arg1) {
        arg0.mLibraryInitialized = arg1;
        return arg1;
    }

    final void lambda$onBind$0$ChildProcessService() {
        this.mDelegate.preloadNativeLibrary(this.getApplicationContext());
    }

    private static native void nativeExitChildProcess() {
    }

    private static native void nativeRegisterFileDescriptors(String[] arg0, int[] arg1, int[] arg2, long[] arg3, long[] arg4) {
    }

    public IBinder onBind(Intent arg3) {
        this.stopSelf();
        this.mBindToCallerCheck = arg3.getBooleanExtra("org.chromium.base.process_launcher.extra.bind_to_caller", false);
        this.mServiceBound = true;
        this.mDelegate.onServiceBound(arg3);
        new Handler(Looper.getMainLooper()).post(new ChildProcessService$$Lambda$0(this));
        return this.mBinder;
    }

    public void onCreate() {
        super.onCreate();
        Log.i("ChildProcessService", "Creating new ChildProcessService pid=%d", new Object[]{Integer.valueOf(Process.myPid())});
        if(ChildProcessService.sCreateCalled) {
            throw new RuntimeException("Illegal child process reuse.");
        }

        ChildProcessService.sCreateCalled = true;
        ContextUtils.initApplicationContext(this.getApplicationContext());
        this.mDelegate.onServiceCreated();
        this.mMainThread = new Thread(new Runnable() {
            public void run() {
                Object v2_4;
                boolean v2_3;
                Thread v2_1;
                try {
                    v2_1 = ChildProcessService.this.mMainThread;
                    __monitor_enter(v2_1);
                }
                catch(InterruptedException v2) {
                    goto label_140;
                }

                try {
                    while(ChildProcessService.this.mCommandLineParams == null) {
                        ChildProcessService.this.mMainThread.wait();
                    }

                    __monitor_exit(v2_1);
                }
                catch(Throwable v3) {
                    goto label_130;
                }

                try {
                    CommandLine.init(ChildProcessService.this.mCommandLineParams);
                    if(CommandLine.getInstance().hasSwitch("renderer-wait-for-java-debugger")) {
                        Debug.waitForDebugger();
                    }

                    try {
                        v2_3 = ChildProcessService.this.mDelegate.loadNativeLibrary(ChildProcessService.this.getApplicationContext());
                        goto label_34;
                    }
                    catch(Exception v2_2) {
                        try {
                            Log.e("ChildProcessService", "Failed to load native library.", new Object[]{v2_2});
                            v2_3 = false;
                        label_34:
                            if(!v2_3) {
                                System.exit(-1);
                            }

                            v2_4 = ChildProcessService.this.mLibraryInitializedLock;
                            __monitor_enter(v2_4);
                        }
                        catch(InterruptedException v2) {
                            goto label_140;
                        }
                    }
                }
                catch(InterruptedException v2) {
                    goto label_140;
                }

                try {
                    ChildProcessService.this.mLibraryInitialized = true;
                    ChildProcessService.this.mLibraryInitializedLock.notifyAll();
                    __monitor_exit(v2_4);
                }
                catch(Throwable v3) {
                    goto label_127;
                }

                try {
                    v2_1 = ChildProcessService.this.mMainThread;
                    __monitor_enter(v2_1);
                }
                catch(InterruptedException v2) {
                    goto label_140;
                }

                try {
                    ChildProcessService.this.mMainThread.notifyAll();
                    while(ChildProcessService.this.mFdInfos == null) {
                        ChildProcessService.this.mMainThread.wait();
                    }

                    __monitor_exit(v2_1);
                }
                catch(Throwable v3) {
                    goto label_124;
                }

                try {
                    SparseArray v2_5 = ChildProcessService.this.mDelegate.getFileDescriptorsIdsToKeys();
                    int[] v3_1 = new int[ChildProcessService.this.mFdInfos.length];
                    String[] v4 = new String[ChildProcessService.this.mFdInfos.length];
                    int[] v5 = new int[ChildProcessService.this.mFdInfos.length];
                    long[] v6 = new long[ChildProcessService.this.mFdInfos.length];
                    long[] v7 = new long[ChildProcessService.this.mFdInfos.length];
                    int v8;
                    for(v8 = 0; v8 < ChildProcessService.this.mFdInfos.length; ++v8) {
                        FileDescriptorInfo v9 = ChildProcessService.this.mFdInfos[v8];
                        Object v10 = v2_5 != null ? v2_5.get(v9.id) : null;
                        if(v10 != null) {
                            v4[v8] = ((String)v10);
                        }
                        else {
                            v3_1[v8] = v9.id;
                        }

                        v5[v8] = v9.fd.detachFd();
                        v6[v8] = v9.offset;
                        v7[v8] = v9.size;
                    }

                    ChildProcessService.nativeRegisterFileDescriptors(v4, v3_1, v5, v6, v7);
                    ChildProcessService.this.mDelegate.onBeforeMain();
                    if(!ChildProcessService.this.mActivitySemaphore.tryAcquire()) {
                        return;
                    }

                    ChildProcessService.this.mDelegate.runMain();
                    ChildProcessService.nativeExitChildProcess();
                    return;
                }
                catch(InterruptedException v2) {
                    goto label_140;
                }

                try {
                label_124:
                    __monitor_exit(v2_1);
                }
                catch(Throwable v3) {
                    goto label_124;
                }

                try {
                    throw v3;
                }
                catch(InterruptedException v2) {
                    goto label_140;
                }

                try {
                label_127:
                    __monitor_exit(v2_4);
                }
                catch(Throwable v3) {
                    goto label_127;
                }

                try {
                    throw v3;
                }
                catch(InterruptedException v2) {
                    goto label_140;
                }

                try {
                label_130:
                    __monitor_exit(v2_1);
                }
                catch(Throwable v3) {
                    goto label_130;
                }

                try {
                    throw v3;
                }
                catch(InterruptedException v2) {
                label_140:
                    Log.w("ChildProcessService", "%s startup failed: %s", new Object[]{"ChildProcessMain", v2});
                }
            }
        }, "ChildProcessMain");
        this.mMainThread.start();
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("ChildProcessService", "Destroying ChildProcessService pid=%d", new Object[]{Integer.valueOf(Process.myPid())});
        if(this.mActivitySemaphore.tryAcquire()) {
            System.exit(0);
            return;
        }

        Object v0 = this.mLibraryInitializedLock;
        __monitor_enter(v0);
        try {
            while(true) {
                if(this.mLibraryInitialized) {
                    goto label_24;
                }

                this.mLibraryInitializedLock.wait();
            }
        }
        catch(Throwable v1) {
        label_23:
            try {
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_23;
            }

            throw v1;
        }
        catch(InterruptedException ) {
            try {
            label_24:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_23;
            }

            this.mDelegate.onDestroy();
            return;
        }
    }

    private void processConnectionBundle(Bundle arg6, List arg7) {
        arg6.setClassLoader(this.getApplicationContext().getClassLoader());
        Thread v0 = this.mMainThread;
        __monitor_enter(v0);
        try {
            if(this.mCommandLineParams == null) {
                this.mCommandLineParams = arg6.getStringArray("org.chromium.base.process_launcher.extra.command_line");
                this.mMainThread.notifyAll();
            }

            Parcelable[] v1 = arg6.getParcelableArray("org.chromium.base.process_launcher.extra.extraFiles");
            if(v1 != null) {
                this.mFdInfos = new FileDescriptorInfo[v1.length];
                System.arraycopy(v1, 0, this.mFdInfos, 0, v1.length);
            }

            this.mDelegate.onConnectionSetup(arg6, arg7);
            this.mMainThread.notifyAll();
            __monitor_exit(v0);
            return;
        label_29:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_29;
        }

        throw v6;
    }
}

