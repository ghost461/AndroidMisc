package org.xwalk.core.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.util.RuntimeEnviroment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.WeakHashMap;
import org.chromium.base.ApplicationStatusManager;
import org.chromium.base.CommandLine;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.PathUtils;
import org.chromium.base.ResourceExtractor$ResourceEntry;
import org.chromium.base.ResourceExtractor$ResourceInterceptor;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.content.browser.BrowserStartupController;
import org.chromium.content.browser.DeviceUtils;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.display.DisplayAndroidManager;

@JNINamespace(value="xwalk") public class XWalkViewDelegate {
    public class ApplicationContextWrapper extends ContextWrapper {
        private Resources mResrouces;

        public ApplicationContextWrapper(Context arg1, Resources arg2) {
            super(arg1);
            this.mResrouces = arg2;
        }

        public ClassLoader getClassLoader() {
            return new ClassLoader(this.getClass().getClassLoader(), this.getBaseContext().getClassLoader()) {
                protected Class findClass(String arg2) throws ClassNotFoundException {
                    try {
                        return this.val$webViewCl.loadClass(arg2);
                    }
                    catch(ClassNotFoundException ) {
                        return this.val$appCl.loadClass(arg2);
                    }
                }
            };
        }

        public Resources getResources() {
            return this.mResrouces;
        }

        public Object getSystemService(String arg2) {
            if("layout_inflater".equals(arg2)) {
                return this.getBaseContext().getSystemService(arg2).cloneInContext(((Context)this));
            }

            return this.getBaseContext().getSystemService(arg2);
        }
    }

    private static final String COMMAND_LINE_FILE = "xwalk-command-line";
    private static final String GEO_LOCATION_PERMISSIONS_SP_NAME = "xwalk_geo_location_permissions";
    public static final int INVOKE_NOTITY_FUNCTION_ID_FOR_SET_COOKIE_DIR = 30001;
    private static final int INVOKE_RUNTIME_ID_CONTEXT_CHANGED = 80001;
    private static final int INVOKE_RUNTIME_ID_FORCE_LOW_MEMORY = 80002;
    public static final int INVOKE_RUNTIME_ID_HAS_FEATURE = 80003;
    public static final int INVOKE_RUNTIME_ID_SET_RUNTIME_TO_SDK_CHANNEL = 80005;
    public static final int INVOKE_RUNTIME_ID_SUPPORT_TRANLATE_WEB = 80011;
    private static final String[] MANDATORY_LIBRARIES = null;
    private static final String[] MANDATORY_PAKS = null;
    private static final String PRIVATE_DATA_DIRECTORY_SUFFIX = "xwalk_";
    private static final String TAG = "XWalkViewDelegate";
    private static final String XWALK_PAK_NAME = "xwalk.pak";
    private static final String XWALK_RESOURCES_LIST_RES_NAME = "xwalk_resources_list";
    private static XWalkGeolocationPermissions mGeoLocationPermissions = null;
    public static String sApkVer = "";
    private static final WeakHashMap sCtxToWrapper = null;
    private static String sDeviceAbi = null;
    private static String sExtractedCoreDir = "";
    private static boolean sInitialized = false;
    private static boolean sLibraryLoaded = false;
    private static boolean sLoadedByHoudini = false;
    private static final Object sLock = null;
    public static boolean sUsingCustomContext = false;

    static {
        XWalkViewDelegate.MANDATORY_PAKS = new String[]{"xwalk.pak", "icudtl.dat", "xwalk_100_percent.pak"};
        XWalkViewDelegate.MANDATORY_LIBRARIES = new String[]{"xwalkcore"};
        XWalkViewDelegate.sCtxToWrapper = new WeakHashMap();
        XWalkViewDelegate.sLock = new Object();
    }

    public XWalkViewDelegate() {
        super();
    }

    static String access$000() {
        return XWalkViewDelegate.sExtractedCoreDir;
    }

    public static Context getApplicationContextWrapper(Context arg3, Resources arg4) {
        ApplicationContextWrapper v1_1;
        if((arg3 instanceof ApplicationContextWrapper)) {
            return arg3;
        }

        Object v0 = XWalkViewDelegate.sLock;
        __monitor_enter(v0);
        try {
            Object v1 = XWalkViewDelegate.sCtxToWrapper.get(arg3);
            v1 = v1 == null ? null : ((WeakReference)v1).get();
            if(v1 == null) {
                v1_1 = new ApplicationContextWrapper(arg3, arg4);
                XWalkViewDelegate.sCtxToWrapper.put(arg3, new WeakReference(v1_1));
            }

            __monitor_exit(v0);
            return ((Context)v1_1);
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_21;
        }

        throw v3;
    }

    static Application getApplicationFromContextWrapper(ContextWrapper arg7) {
        Context v2_1;
        Application v0 = null;
        if(arg7 == null) {
            return v0;
        }

        ContextWrapper v2 = arg7;
        int v7 = 0;
        while(true) {
            ++v7;
            if(v7 > 5) {
                goto label_9;
            }

            v2_1 = ((ContextWrapper)v2_1).getBaseContext();
            if(v2_1 == null) {
                Log.e("XWalkViewDelegate", "getApplication failed, basecontext type is invalid , type is null ", new Object[0]);
                return v0;
            }

            if((v2_1 instanceof Activity)) {
                return ((Activity)v2_1).getApplication();
            }

            if((v2_1 instanceof Application)) {
                return ((Application)v2_1);
            }

            if(!(v2_1 instanceof ContextWrapper)) {
                break;
            }

            Log.i("XWalkViewDelegate", "base is still contextwrapper , type is  " + v2_1.getClass().getName(), new Object[0]);
            v2_1 = v2_1;
        }

        Log.e("XWalkViewDelegate", "getApplication failed, basecontext type is invalid , type is  " + v2_1.getClass().getName(), new Object[0]);
        return v0;
    label_9:
        Log.e("XWalkViewDelegate", "getApplication failed, context wrapper layer stack over 5", new Object[0]);
        return v0;
    }

    private static String getApplicationMetaData(Context arg2, String arg3) {
        try {
            return arg2.getPackageManager().getApplicationInfo(arg2.getPackageName(), 0x80).metaData.get(arg3).toString();
        }
        catch(NullPointerException ) {
            return null;
        }
    }

    private static String getDeviceAbi() {
        if(XWalkViewDelegate.sDeviceAbi == null) {
            try {
                XWalkViewDelegate.sDeviceAbi = Build.SUPPORTED_ABIS[0].toLowerCase();
            }
            catch(NoSuchFieldError ) {
                try {
                    InputStreamReader v1 = new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream());
                    BufferedReader v0 = new BufferedReader(((Reader)v1));
                    XWalkViewDelegate.sDeviceAbi = v0.readLine().toLowerCase();
                    v0.close();
                    v1.close();
                }
                catch(IOException ) {
                    throw new RuntimeException("Can not detect device\'s ABI");
                }
            }

            org.xwalk.core.internal.Log.d("XWalkViewDelegate", "Device ABI: " + XWalkViewDelegate.sDeviceAbi);
        }

        return XWalkViewDelegate.sDeviceAbi;
    }

    public static XWalkGeolocationPermissions getGeoLocationPermissions() {
        return XWalkViewDelegate.mGeoLocationPermissions;
    }

    private static int getResourceId(Context arg2, String arg3, String arg4) {
        int v0 = arg2.getResources().getIdentifier(arg3, arg4, arg2.getClass().getPackage().getName());
        if(v0 == 0) {
            v0 = arg2.getResources().getIdentifier(arg3, arg4, arg2.getPackageName());
        }

        return v0;
    }

    public static void init(@Nullable Context arg3, Context arg4) {
        Context v0;
        ContextUtils.initApplicationContext(XWalkViewDelegate.getApplicationContextWrapper(arg4.getApplicationContext(), arg4.getResources()));
        if(!XWalkViewDelegate.loadXWalkLibrary(arg3, null)) {
            throw new RuntimeException("Failed to load native library");
        }

        if(XWalkViewDelegate.sInitialized) {
            return;
        }

        if(arg3 == null) {
            v0 = arg4;
        }
        else {
            goto label_17;
        }

        try {
        label_19:
            XWalkViewDelegate.sExtractedCoreDir = v0.getClass().getDeclaredField("extractedCoreDir").get(v0);
            XWalkViewDelegate.sApkVer = String.valueOf(v0.getClass().getDeclaredField("apkVersion").get(v0).intValue());
        }
        catch(IllegalAccessException ) {
        }

        goto label_31;
    label_17:
        MixedContext v0_1 = new MixedContext(arg3, arg4);
        goto label_19;
        try {
        label_31:
            XWalkViewDelegate.sUsingCustomContext = v0_1.getClass().getDeclaredField("usingCustomContext").get(v0_1).booleanValue();
            org.xwalk.core.internal.Log.i("XWalkViewDelegate", "sUsingCustomContext:" + XWalkViewDelegate.sUsingCustomContext);
        }
        catch(Exception ) {
            org.xwalk.core.internal.Log.i("XWalkViewDelegate", "sUsingCustomContext default:" + XWalkViewDelegate.sUsingCustomContext);
        }

        DisplayAndroidManager.setUsingCustomContext(XWalkViewDelegate.sUsingCustomContext);
        if(XWalkViewDelegate.sApkVer == null || (XWalkViewDelegate.sApkVer.isEmpty())) {
            org.xwalk.core.internal.Log.e("XWalkViewDelegate", "context get sApkVer failed!!");
            XWalkViewDelegate.sApkVer = XWalkPreferencesInternal.getStringValue("xweb-version");
        }

        if(XWalkViewDelegate.sApkVer != null) {
            if(XWalkViewDelegate.sApkVer.isEmpty()) {
            }
            else {
                String v3_1 = "xwalk_" + XWalkViewDelegate.sApkVer;
                PathUtils.setPrivateDataDirectorySuffix(v3_1);
                XWalkViewDelegate.setCookiePath(((Context)v0_1), v3_1);
                XWalkInternalResources.resetIds(((Context)v0_1));
                if(!CommandLine.isInitialized()) {
                    CommandLine.init(XWalkViewDelegate.readCommandLine(((Context)v0_1).getApplicationContext()));
                }

                try {
                    XWalkViewDelegate.setupResourceInterceptor(((Context)v0_1).getApplicationContext());
                }
                catch(IOException v3_2) {
                    throw new RuntimeException(((Throwable)v3_2));
                }

                ResourceExtractor.get(((Context)v0_1));
                XWalkViewDelegate.startBrowserProcess(((Context)v0_1));
                if((arg4 instanceof Activity)) {
                    ApplicationStatusManager.init(((Activity)arg4).getApplication());
                }
                else if((arg4 instanceof ContextWrapper)) {
                    Application v3_3 = XWalkViewDelegate.getApplicationFromContextWrapper(((ContextWrapper)arg4));
                    if(v3_3 != null) {
                        ApplicationStatusManager.init(v3_3);
                    }
                    else {
                        Log.e("XWalkViewDelegate", "ApplicationStatusManager init failed, application is null", new Object[0]);
                    }
                }
                else if((arg4 instanceof Service)) {
                    ApplicationStatusManager.init(((Service)arg4).getApplication());
                }
                else {
                    Log.e("XWalkViewDelegate", "appContext type is invalid", new Object[0]);
                }

                XWalkPresentationHost.createInstanceOnce(((Context)v0_1));
                NetworkChangeNotifier.init();
                NetworkChangeNotifier.setAutoDetectConnectivityState(new XWalkNetworkChangeNotifierRegistrationPolicy());
                org.xwalk.core.internal.Log.i("XWalkViewDelegate", "XWalk runtime id is: 618d616");
                XWalkViewDelegate.sInitialized = true;
                return;
            }
        }

        throw new RuntimeException("get sApkVer failed!!");
    }

    public static void invokeNativeChannel(int arg0, Object[] arg1) {
        XWalkSettingsInternal.InvokeChannel(arg0, arg1);
    }

    public static Object invokeRuntimeChannel(int arg3, Object[] arg4) {
        org.xwalk.core.internal.Log.i("XWalkViewDelegate", "invokeRuntimeChannel called funid = " + arg3);
        Object v0 = null;
        if(80001 == arg3) {
            if(arg4 != null) {
                if(arg4.length <= 0) {
                }
                else if(!(arg4[0] instanceof XWalkViewBridge)) {
                    org.xwalk.core.internal.Log.e("XWalkViewDelegate", "andrewu params[0] not XWalkViewBridge");
                    return v0;
                }
                else {
                    try {
                        arg4[0].onBaseContextChanged();
                    }
                    catch(Exception v3) {
                        org.xwalk.core.internal.Log.e("XWalkViewDelegate", "andrewu checkContext failed  msg:" + v3.getMessage() + "stack:\n" + v3.getStackTrace().toString());
                    }

                    return v0;
                }
            }

            org.xwalk.core.internal.Log.e("XWalkViewDelegate", "andrewu INVOKE_RUNTIME_ID_CONTEXT_CHANGED: params empty");
            return v0;
        }

        if(80002 == arg3) {
            MemoryPressureListener.notifyMemoryPressure(2);
            return v0;
        }

        if(80003 == arg3) {
            return Boolean.valueOf(XWalkFeratureMap.hasFeature(arg4[0].intValue()));
        }

        if(80005 == arg3) {
            RuntimeToSdkChannel.init(arg4[0]);
            return v0;
        }

        if(80011 == arg3) {
            if("yes".equals(RuntimeToSdkChannel.getCmd("enabletranlate", "tools"))) {
                return Boolean.valueOf(true);
            }

            return Boolean.valueOf(false);
        }

        return v0;
    }

    private static boolean isIaDevice() {
        String v0 = XWalkViewDelegate.getDeviceAbi();
        boolean v0_1 = (v0.equals("x86")) || (v0.equals("x86_64")) ? true : false;
        return v0_1;
    }

    public static boolean loadXWalkLibrary(Context arg9, String arg10) throws UnsatisfiedLinkError {
        int v4;
        LibraryLoader v1_1;
        try {
            v1_1 = LibraryLoader.get(1);
        }
        catch(ProcessInitException v1) {
            ThrowableExtension.printStackTrace(((Throwable)v1));
            v1_1 = null;
        }

        RuntimeEnviroment.init(arg9);
        if(XWalkViewDelegate.sLibraryLoaded) {
            return 1;
        }

        if(arg10 == null || (XWalkViewDelegate.sLoadedByHoudini)) {
            String[] v10 = XWalkViewDelegate.MANDATORY_LIBRARIES;
            int v3_1 = v10.length;
            for(v4 = 0; v4 < v3_1; ++v4) {
                System.loadLibrary(v10[v4]);
            }
        }
        else {
            String[] v3 = XWalkViewDelegate.MANDATORY_LIBRARIES;
            v4 = v3.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                String v6 = v3[v5];
                System.load(arg10 + File.separator + "lib" + v6 + ".so");
            }
        }

        try {
            v1_1.loadNow();
        }
        catch(ProcessInitException v10_1) {
            ThrowableExtension.printStackTrace(((Throwable)v10_1));
        }

        if(XWalkViewDelegate.nativeIsLibraryBuiltForIA()) {
            org.xwalk.core.internal.Log.d("XWalkViewDelegate", "Native library is built for IA");
        }
        else {
            org.xwalk.core.internal.Log.d("XWalkViewDelegate", "Native library is built for ARM");
            if(XWalkViewDelegate.isIaDevice()) {
                org.xwalk.core.internal.Log.d("XWalkViewDelegate", "Crosswalk\'s native library does not support Houdini");
                XWalkViewDelegate.sLoadedByHoudini = true;
                return 0;
            }
        }

        if(arg9 != null) {
            XWalkViewDelegate.mGeoLocationPermissions = new XWalkGeolocationPermissions(arg9.getSharedPreferences("xwalk_geo_location_permissions", 0));
        }

        XWalkViewDelegate.sLibraryLoaded = true;
        return 1;
    }

    public static boolean loadXWalkLibrary(Context arg1) {
        return XWalkViewDelegate.loadXWalkLibrary(arg1, null);
    }

    private static native boolean nativeIsLibraryBuiltForIA() {
    }

    private static String[] readCommandLine(Context arg7) {
        String[] v7_2;
        InputStreamReader v4;
        StringBuilder v3;
        char[] v2;
        int v1;
        InputStreamReader v0 = null;
        try {
            InputStream v7_1 = arg7.getAssets().open("xwalk-command-line", 3);
            v1 = 0x400;
            v2 = new char[v1];
            v3 = new StringBuilder();
            v4 = new InputStreamReader(v7_1, "UTF-8");
        }
        catch(Throwable v7) {
            goto label_33;
        }
        catch(IOException ) {
            v4 = v0;
            goto label_42;
        }

        try {
            while(true) {
                int v5 = v4.read(v2, 0, v1);
                if(v5 == -1) {
                    break;
                }

                v3.append(v2, 0, v5);
            }

            v7_2 = CommandLine.tokenizeQuotedArguments(v3.toString().toCharArray());
            if(v4 == null) {
                return v7_2;
            }

            goto label_22;
        }
        catch(IOException ) {
        label_42:
            if(v4 != null) {
                try {
                    v4.close();
                }
                catch(IOException v7_3) {
                    org.xwalk.core.internal.Log.e("XWalkViewDelegate", "Unable to close file reader.", ((Throwable)v7_3));
                }
            }

            return ((String[])v0);
        }
        catch(Throwable v7) {
            v0 = v4;
            goto label_33;
            try {
            label_22:
                v4.close();
            }
            catch(IOException v0_1) {
                org.xwalk.core.internal.Log.e("XWalkViewDelegate", "Unable to close file reader.", ((Throwable)v0_1));
            }

            return v7_2;
        label_33:
            if(v0 != null) {
                try {
                    v0.close();
                }
                catch(IOException v0_1) {
                    org.xwalk.core.internal.Log.e("XWalkViewDelegate", "Unable to close file reader.", ((Throwable)v0_1));
                }
            }

            throw v7;
        }
    }

    private static void setCookiePath(Context arg6, String arg7) {
        String v0 = RuntimeToSdkChannel.getCmd("sharecookie", "tools");
        org.xwalk.core.internal.Log.i("XWalkViewDelegate", "isShareCookie =  " + v0);
        String v1 = "app_webview/Cookies";
        if(v0 != null && (v0.equalsIgnoreCase("no"))) {
            v1 = "app_webview_67/Cookies";
            String v6 = arg6.getDir(arg7, 0).getParent();
            arg7 = v6 + "/app_webview/Cookies";
            v0 = v6 + "/app_webview/Cookies-journal";
            StringBuilder v3 = new StringBuilder();
            v3.append(v6);
            v3.append("/app_webview_67/Cookies");
            File v3_1 = new File(v3.toString());
            if(v3_1 != null && (v3_1.exists())) {
                goto label_71;
            }

            File v6_1 = new File(arg7);
            if(v6_1 != null && (v6_1.exists())) {
                org.xwalk.core.internal.Log.i("XWalkViewDelegate", "delete old cookie file = " + arg7);
                v6_1.delete();
            }

            v6_1 = new File(v0);
            if(v6_1 == null) {
                goto label_71;
            }

            if(!v6_1.exists()) {
                goto label_71;
            }

            org.xwalk.core.internal.Log.i("XWalkViewDelegate", "delete old cookie oldCookiejournalFile file " + v0);
            v6_1.delete();
        }

    label_71:
        XWalkViewDelegate.invokeNativeChannel(30001, new Object[]{v1});
    }

    public static void setLogCallBack(Object arg1) {
        if(arg1 == null) {
            return;
        }

        if(!(arg1 instanceof XWalkLogMessageListenerBridge)) {
            return;
        }

        XWalkSettingsInternal.SetLogCallBack(arg1);
        org.xwalk.core.internal.Log.SetLogCallBack(((XWalkLogMessageListenerInternal)arg1));
    }

    public static void setNotifyCallBackChannel(Object arg1) {
        if(arg1 == null) {
            return;
        }

        if(!(arg1 instanceof XWalkNotifyChannelListenerBridge)) {
            return;
        }

        XWalkSettingsInternal.SetNotifyCallBackChannel(((XWalkNotifyChannelListenerInternal)arg1));
    }

    private static void setupResourceInterceptor(Context arg8) throws IOException {
        int v3;
        String[] v2;
        HashMap v0 = new HashMap();
        try {
            v2 = arg8.getResources().getStringArray(XWalkViewDelegate.getResourceId(arg8, "xwalk_resources_list", "array"));
            v3 = v2.length;
            int v4;
            for(v4 = 0; v4 < v3; ++v4) {
                String v5 = v2[v4];
                XWalkViewDelegate.getResourceId(arg8, v5, "raw");
                v0.put(v5, new ResourceEntry(0, "", v5));
            }
        }
        catch(Resources$NotFoundException ) {
            v2 = XWalkViewDelegate.MANDATORY_PAKS;
            v3 = v2.length;
            for(v4 = 0; v4 < v3; ++v4) {
                v0.put(v2[v4], new ResourceEntry(0, "", v2[v4]));
            }
        }

        ResourceExtractor.setResourcesToExtract(v0.values().toArray(new ResourceEntry[v0.size()]));
        ResourceExtractor.setResourceInterceptor(new ResourceInterceptor(new HashSet(v0.keySet()), arg8) {
            public InputStream openRawResource(String arg4) {
                try {
                    String v0 = XWalkViewDelegate.sExtractedCoreDir;
                    if(v0 == null || (v0.isEmpty())) {
                        v0 = this.val$context.getSharedPreferences("libxwalkcore", 0).getString("XWALK_CORE_EXTRACTED_DIR", "");
                    }

                    return new FileInputStream(new File(v0, arg4));
                }
                catch(FileNotFoundException ) {
                    StringBuilder v1 = new StringBuilder();
                    v1.append(arg4);
                    v1.append(" can\'t be found.");
                    throw new RuntimeException(v1.toString());
                }
            }

            public boolean shouldInterceptLoadRequest(String arg2) {
                return this.val$interceptableResources.contains(arg2);
            }
        });
    }

    private static void startBrowserProcess(Context arg1) {
        ThreadUtils.runOnUiThreadBlocking(new Runnable(arg1) {
            public void run() {
                try {
                    LibraryLoader.get(1).ensureInitialized();
                }
                catch(ProcessInitException v0) {
                    ThrowableExtension.printStackTrace(((Throwable)v0));
                    throw new RuntimeException("Cannot initialize Crosswalk Core", ((Throwable)v0));
                }

                DeviceUtils.addDeviceSpecificUserAgentSwitch(this.val$context);
                CommandLine.getInstance().appendSwitchWithValue("profile-name", XWalkPreferencesInternal.getStringValue("profile-name"));
                String v1 = XWalkPreferencesInternal.getStringValue("xweb-version");
                if(v1 != null && !v1.isEmpty()) {
                    CommandLine.getInstance().appendSwitchWithValue("xweb-version", v1);
                }

                v1 = XWalkPreferencesInternal.getStringValue("xwebsdk-version");
                if(v1 != null && !v1.isEmpty()) {
                    CommandLine.getInstance().appendSwitchWithValue("xwebsdk-version", v1);
                }

                try {
                    BrowserStartupController.get(1).startBrowserProcessesSync(true);
                    return;
                }
                catch(ProcessInitException v0) {
                    throw new RuntimeException("Cannot initialize Crosswalk Core", ((Throwable)v0));
                }
            }
        });
    }
}

