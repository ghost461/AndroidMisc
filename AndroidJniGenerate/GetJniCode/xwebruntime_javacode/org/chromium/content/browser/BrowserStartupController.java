package org.chromium.content.browser;

import android.content.Context;
import android.os.Handler;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.content.app.ContentMain;

@JNINamespace(value="content") public class BrowserStartupController {
    public interface StartupCallback {
        void onFailure();

        void onSuccess(boolean arg1);
    }

    private static final boolean ALREADY_STARTED = true;
    private static final boolean NOT_ALREADY_STARTED = false;
    private static final int RES_EXTRACTOR_MAXTIMES = 1;
    @VisibleForTesting static final int STARTUP_FAILURE = 1;
    @VisibleForTesting static final int STARTUP_SUCCESS = -1;
    private static final String TAG = "cr.BrowserStartup";
    private final List mAsyncStartupCallbacks;
    private static int mCurrentTime;
    private boolean mHasCalledContentStart;
    private boolean mHasStartedInitializingBrowserProcess;
    private int mLibraryProcessType;
    private boolean mPostResourceExtractionTasksCompleted;
    private boolean mStartupDone;
    private boolean mStartupSuccess;
    private TracingControllerAndroid mTracingController;
    private static BrowserStartupController sInstance;
    private static boolean sShouldStartGpuProcessOnBrowserStartup;

    static {
    }

    BrowserStartupController(int arg2) {
        super();
        this.mAsyncStartupCallbacks = new ArrayList();
        this.mLibraryProcessType = arg2;
        if(BuildInfo.isDebugAndroid()) {
            ThreadUtils.postOnUiThread(new Runnable() {
                public void run() {
                    BrowserStartupController.this.addStartupCompletedObserver(new StartupCallback() {
                        public void onFailure() {
                        }

                        public void onSuccess(boolean arg3) {
                            Context v3 = ContextUtils.getApplicationContext();
                            BrowserStartupController.access$002(this.this$1.this$0, new TracingControllerAndroid(v3));
                            BrowserStartupController.access$000(this.this$1.this$0).registerReceiver(v3);
                        }
                    });
                }
            });
        }
    }

    static TracingControllerAndroid access$000(BrowserStartupController arg0) {
        return arg0.mTracingController;
    }

    static TracingControllerAndroid access$002(BrowserStartupController arg0, TracingControllerAndroid arg1) {
        arg0.mTracingController = arg1;
        return arg1;
    }

    static boolean access$100(BrowserStartupController arg0) {
        return arg0.mHasCalledContentStart;
    }

    static void access$200(BrowserStartupController arg0, int arg1, boolean arg2) {
        arg0.enqueueCallbackExecution(arg1, arg2);
    }

    static void access$300(BrowserStartupController arg0, int arg1, boolean arg2) {
        arg0.executeEnqueuedCallbacks(arg1, arg2);
    }

    static boolean access$400(BrowserStartupController arg0) {
        return arg0.mStartupSuccess;
    }

    public void addStartupCompletedObserver(StartupCallback arg2) {
        ThreadUtils.assertOnUiThread();
        if(this.mStartupDone) {
            this.postStartupCompleted(arg2);
        }
        else {
            this.mAsyncStartupCallbacks.add(arg2);
        }
    }

    @VisibleForTesting @CalledByNative static void browserStartupComplete(int arg2) {
        if(BrowserStartupController.sInstance != null) {
            BrowserStartupController.sInstance.executeEnqueuedCallbacks(arg2, false);
        }
    }

    @VisibleForTesting int contentStart() {
        this.mHasCalledContentStart = true;
        return ContentMain.start();
    }

    private void enqueueCallbackExecution(int arg3, boolean arg4) {
        new Handler().post(new Runnable(arg3, arg4) {
            public void run() {
                BrowserStartupController.this.executeEnqueuedCallbacks(this.val$startupFailure, this.val$alreadyStarted);
            }
        });
    }

    private void executeEnqueuedCallbacks(int arg3, boolean arg4) {
        boolean v0 = true;
        this.mStartupDone = true;
        if(arg3 <= 0) {
        }
        else {
            v0 = false;
        }

        this.mStartupSuccess = v0;
        Iterator v3 = this.mAsyncStartupCallbacks.iterator();
        while(v3.hasNext()) {
            Object v0_1 = v3.next();
            if(this.mStartupSuccess) {
                ((StartupCallback)v0_1).onSuccess(arg4);
                continue;
            }

            ((StartupCallback)v0_1).onFailure();
        }

        this.mAsyncStartupCallbacks.clear();
    }

    @VisibleForTesting void flushStartupTasks() {
        BrowserStartupController.nativeFlushStartupTasks();
    }

    public static BrowserStartupController get(int arg1) {
        ThreadUtils.assertOnUiThread();
        if(BrowserStartupController.sInstance == null) {
            BrowserStartupController.sInstance = new BrowserStartupController(arg1);
        }

        return BrowserStartupController.sInstance;
    }

    private String getPlugins() {
        return PepperPluginManager.getPlugins(ContextUtils.getApplicationContext());
    }

    public void initChromiumBrowserProcessForTests() {
        ResourceExtractor v0 = ResourceExtractor.get();
        v0.startExtractingResources();
        v0.waitForCompletion();
        BrowserStartupController.nativeSetCommandLineFlags(false, null);
    }

    public boolean isStartupSuccessfullyCompleted() {
        ThreadUtils.assertOnUiThread();
        boolean v0 = !this.mStartupDone || !this.mStartupSuccess ? false : true;
        return v0;
    }

    private static native void nativeFlushStartupTasks() {
    }

    private static native boolean nativeIsOfficialBuild() {
    }

    private static native boolean nativeIsPluginEnabled() {
    }

    private static native void nativeSetCommandLineFlags(boolean arg0, String arg1) {
    }

    @VisibleForTesting public static BrowserStartupController overrideInstanceForTest(BrowserStartupController arg0) {
        BrowserStartupController.sInstance = arg0;
        return BrowserStartupController.sInstance;
    }

    private void postStartupCompleted(StartupCallback arg3) {
        new Handler().post(new Runnable(arg3) {
            public void run() {
                if(BrowserStartupController.this.mStartupSuccess) {
                    this.val$callback.onSuccess(true);
                }
                else {
                    this.val$callback.onFailure();
                }
            }
        });
    }

    @VisibleForTesting void prepareToStartBrowserProcess(boolean arg6, Runnable arg7) throws ProcessInitException {
        Log.i("cr.BrowserStartup", "Initializing chromium process, singleProcess=%b", new Object[]{Boolean.valueOf(arg6)});
        StrictMode$ThreadPolicy v7 = StrictMode.allowThreadDiskReads();
        try {
            LibraryLoader.get(this.mLibraryProcessType).ensureInitialized();
        }
        catch(Throwable v6) {
            StrictMode.setThreadPolicy(v7);
            throw v6;
        }

        StrictMode.setThreadPolicy(v7);
        DeviceUtils.addDeviceSpecificUserAgentSwitch(ContextUtils.getApplicationContext());
        String v7_1 = BrowserStartupController.nativeIsPluginEnabled() ? this.getPlugins() : null;
        BrowserStartupController.nativeSetCommandLineFlags(arg6, v7_1);
        this.mPostResourceExtractionTasksCompleted = true;
    }

    private static void setShouldStartGpuProcessOnBrowserStartup(boolean arg0) {
        BrowserStartupController.sShouldStartGpuProcessOnBrowserStartup = arg0;
    }

    @CalledByNative static boolean shouldStartGpuProcessOnBrowserStartup() {
        return BrowserStartupController.sShouldStartGpuProcessOnBrowserStartup;
    }

    public void startBrowserProcessesAsync(boolean arg2, StartupCallback arg3) throws ProcessInitException {
        if(this.mStartupDone) {
            this.postStartupCompleted(arg3);
            return;
        }

        this.mAsyncStartupCallbacks.add(arg3);
        if(!this.mHasStartedInitializingBrowserProcess) {
            this.mHasStartedInitializingBrowserProcess = true;
            BrowserStartupController.setShouldStartGpuProcessOnBrowserStartup(arg2);
            this.prepareToStartBrowserProcess(false, new Runnable() {
                public void run() {
                    ThreadUtils.assertOnUiThread();
                    if(BrowserStartupController.this.mHasCalledContentStart) {
                        return;
                    }

                    if(BrowserStartupController.this.contentStart() > 0) {
                        BrowserStartupController.this.enqueueCallbackExecution(1, false);
                    }
                }
            });
        }
    }

    public void startBrowserProcessesSync(boolean arg3) throws ProcessInitException {
        if(!this.mStartupDone) {
            if(!this.mHasStartedInitializingBrowserProcess || !this.mPostResourceExtractionTasksCompleted) {
                this.prepareToStartBrowserProcess(arg3, null);
            }

            int v0 = 0;
            if((this.mHasCalledContentStart) || this.contentStart() <= 0) {
                v0 = 1;
            }
            else {
                this.enqueueCallbackExecution(1, false);
            }

            if(v0 == 0) {
                goto label_19;
            }

            this.flushStartupTasks();
        }

    label_19:
        if(!this.mStartupSuccess) {
            throw new ProcessInitException(4);
        }
    }
}

