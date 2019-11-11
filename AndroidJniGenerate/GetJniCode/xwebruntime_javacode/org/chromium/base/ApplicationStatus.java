package org.chromium.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window$Callback;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.base.ActivityWindowAndroid;
import org.xwalk.core.internal.Log;

@JNINamespace(value="base::android") public class ApplicationStatus {
    class ActivityInfo {
        private ObserverList mListeners;
        private int mStatus;

        ActivityInfo(org.chromium.base.ApplicationStatus$1 arg1) {
            this();
        }

        private ActivityInfo() {
            super();
            this.mStatus = 6;
            this.mListeners = new ObserverList();
        }

        public ObserverList getListeners() {
            return this.mListeners;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public void setStatus(int arg1) {
            this.mStatus = arg1;
        }
    }

    public interface ActivityStateListener {
        void onActivityStateChange(Activity arg1, int arg2);
    }

    public interface ApplicationStateListener {
        void onApplicationStateChange(int arg1);
    }

    class WindowCallbackProxy implements InvocationHandler {
        private final Activity mActivity;
        private final Window$Callback mCallback;

        public WindowCallbackProxy(Activity arg1, Window$Callback arg2) {
            super();
            this.mCallback = arg2;
            this.mActivity = arg1;
        }

        public Object invoke(Object arg2, Method arg3, Object[] arg4) throws Throwable {
            if((arg3.getName().equals("onWindowFocusChanged")) && arg4.length == 1 && ((arg4[0] instanceof Boolean))) {
                this.onWindowFocusChanged(arg4[0].booleanValue());
                Log.i("ApplicationStatus", "window callback   onWindowFocusChanged invoke: id = " + this.mActivity.hashCode());
                return null;
            }

            try {
                return arg3.invoke(this.mCallback, arg4);
            }
            catch(InvocationTargetException v2) {
                if((v2.getCause() instanceof AbstractMethodError)) {
                    throw v2.getCause();
                }

                throw v2;
            }
        }

        public void onWindowFocusChanged(boolean arg4) {
            this.mCallback.onWindowFocusChanged(arg4);
            Iterator v0 = ApplicationStatus.sWindowFocusListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onWindowFocusChanged(this.mActivity, arg4);
            }
        }
    }

    public interface WindowFocusChangedListener {
        void onWindowFocusChanged(Activity arg1, boolean arg2);
    }

    private static final String TAG = "ApplicationStatus";
    private static final String TOOLBAR_CALLBACK_INTERNAL_WRAPPER_CLASS = "android.support.v7.internal.app.ToolbarActionBar$ToolbarCallbackWrapper";
    private static final String TOOLBAR_CALLBACK_WRAPPER_CLASS = "android.support.v7.app.ToolbarActionBar$ToolbarCallbackWrapper";
    private static final String WINDOW_PROFILER_CALLBACK = "com.android.tools.profiler.support.event.WindowProfilerCallback";
    @SuppressLint(value={"StaticFieldLeak"}) private static Activity sActivity;
    private static final Map sActivityInfo;
    private static final ObserverList sApplicationStateListeners;
    @SuppressLint(value={"SupportAnnotationUsage"}) private static Integer sCachedApplicationState;
    private static final Object sCachedApplicationStateLock;
    private static final ObserverList sGeneralActivityStateListeners;
    private static boolean sIsInitialized;
    private static ApplicationStateListener sNativeApplicationStateListener;
    static HashSet sSetWindowCallBackListend;
    private static final ObserverList sWindowFocusListeners;

    static {
        ApplicationStatus.sCachedApplicationStateLock = new Object();
        ApplicationStatus.sActivityInfo = new ConcurrentHashMap();
        ApplicationStatus.sGeneralActivityStateListeners = new ObserverList();
        ApplicationStatus.sApplicationStateListeners = new ObserverList();
        ApplicationStatus.sWindowFocusListeners = new ObserverList();
        ApplicationStatus.sSetWindowCallBackListend = new HashSet();
    }

    private ApplicationStatus() {
        super();
    }

    static ObserverList access$000() {
        return ApplicationStatus.sWindowFocusListeners;
    }

    static void access$100(Activity arg0, int arg1) {
        ApplicationStatus.onStateChange(arg0, arg1);
    }

    static ApplicationStateListener access$300() {
        return ApplicationStatus.sNativeApplicationStateListener;
    }

    static ApplicationStateListener access$302(ApplicationStateListener arg0) {
        ApplicationStatus.sNativeApplicationStateListener = arg0;
        return arg0;
    }

    static void access$400(int arg0) {
        ApplicationStatus.nativeOnApplicationStateChange(arg0);
    }

    private static void assertInitialized() {
        if(!ApplicationStatus.sIsInitialized) {
            throw new IllegalStateException("ApplicationStatus has not been initialized yet.");
        }
    }

    public static void checkWindowCallBack(Activity arg7) {
        Integer v1;
        Class v0 = ApplicationStatus.class;
        __monitor_enter(v0);
        if(arg7 != null) {
            try {
                if(arg7.getWindow() == null) {
                }
                else {
                    v1 = new Integer(arg7.hashCode() + arg7.getWindow().hashCode());
                    if(ApplicationStatus.sSetWindowCallBackListend.contains(v1)) {
                        goto label_15;
                    }
                    else {
                        goto label_17;
                    }
                }

                goto label_46;
            }
            catch(Throwable v7) {
                goto label_51;
            }

        label_15:
            __monitor_exit(v0);
            return;
            try {
            label_17:
                ApplicationStatus.sSetWindowCallBackListend.add(v1);
                arg7.getWindow().setCallback(Proxy.newProxyInstance(Window$Callback.class.getClassLoader(), new Class[]{Window$Callback.class}, new WindowCallbackProxy(arg7, arg7.getWindow().getCallback())));
                Log.e("ApplicationStatus", "window callback  checkWindowCallBack sucsess activity id = " + arg7.hashCode());
            }
            catch(Throwable v7) {
                goto label_51;
            }

            __monitor_exit(v0);
            return;
        }

        try {
        label_46:
            Log.e("ApplicationStatus", "window callback  checkWindowCallBack null == activity  ");
        }
        catch(Throwable v7) {
        label_51:
            __monitor_exit(v0);
            throw v7;
        }

        __monitor_exit(v0);
    }

    public static void destroyForJUnitTests() {
        ApplicationStatus.sApplicationStateListeners.clear();
        ApplicationStatus.sGeneralActivityStateListeners.clear();
        ApplicationStatus.sActivityInfo.clear();
        ApplicationStatus.sWindowFocusListeners.clear();
        ApplicationStatus.sIsInitialized = false;
        Object v0 = ApplicationStatus.sCachedApplicationStateLock;
        __monitor_enter(v0);
        Integer v1 = null;
        try {
            ApplicationStatus.sCachedApplicationState = v1;
            __monitor_exit(v0);
        }
        catch(Throwable v1_1) {
            try {
            label_19:
                __monitor_exit(v0);
            }
            catch(Throwable v1_1) {
                goto label_19;
            }

            throw v1_1;
        }

        ApplicationStatus.sActivity = ((Activity)v1);
        ApplicationStatus.sNativeApplicationStateListener = ((ApplicationStateListener)v1);
    }

    private static int determineApplicationState() {
        int v4;
        Iterator v0 = ApplicationStatus.sActivityInfo.values().iterator();
        int v1 = 0;
        int v2;
        for(v2 = 0; true; v2 = 1) {
        label_5:
            v4 = 4;
            if(!v0.hasNext()) {
                break;
            }

            int v3 = v0.next().getStatus();
            int v5 = 5;
            if(v3 != v4 && v3 != v5 && v3 != 6) {
                return 1;
            }

            if(v3 == v4) {
                v1 = 1;
                goto label_5;
            }

            if(v3 != v5) {
                goto label_5;
            }
        }

        if(v1 != 0) {
            return 2;
        }

        if(v2 != 0) {
            return 3;
        }

        return v4;
    }

    public static Activity getLastTrackedFocusedActivity() {
        return ApplicationStatus.sActivity;
    }

    public static List getRunningActivities() {
        ApplicationStatus.assertInitialized();
        ArrayList v0 = new ArrayList();
        Iterator v1 = ApplicationStatus.sActivityInfo.keySet().iterator();
        while(v1.hasNext()) {
            ((List)v0).add(new WeakReference(v1.next()));
        }

        return ((List)v0);
    }

    public static int getStateForActivity(@Nullable Activity arg2) {
        ApplicationStatus.assertInitialized();
        int v0 = 6;
        if(arg2 == null) {
            return v0;
        }

        Object v2 = ApplicationStatus.sActivityInfo.get(arg2);
        if(v2 != null) {
            v0 = ((ActivityInfo)v2).getStatus();
        }

        return v0;
    }

    @CalledByNative public static int getStateForApplication() {
        Object v0 = ApplicationStatus.sCachedApplicationStateLock;
        __monitor_enter(v0);
        try {
            if(ApplicationStatus.sCachedApplicationState == null) {
                ApplicationStatus.sCachedApplicationState = Integer.valueOf(ApplicationStatus.determineApplicationState());
            }

            __monitor_exit(v0);
            return ApplicationStatus.sCachedApplicationState.intValue();
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    public static int getStatus(Activity arg2) {
        if(arg2 == null) {
            return 0;
        }

        Object v2 = ApplicationStatus.sActivityInfo.get(arg2);
        if(v2 == null) {
            return 0;
        }

        return ((ActivityInfo)v2).getStatus();
    }

    public static boolean hasVisibleActivities() {
        int v0 = ApplicationStatus.getStateForApplication();
        boolean v1 = true;
        if(v0 != 1) {
            if(v0 == 2) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    public static void informActivityStarted(Activity arg3) {
        Log.e("ApplicationStatus", "fake inform start  id = " + arg3.hashCode());
        ApplicationStatus.onStateChange(arg3, 1);
        ApplicationStatus.onStateChange(arg3, 2);
        ApplicationStatus.onStateChange(arg3, 3);
        Log.e("ApplicationStatus", "fake inform end  id = " + arg3.hashCode());
    }

    public static void initialize(Application arg1) {
        if(ApplicationStatus.sIsInitialized) {
            return;
        }

        ApplicationStatus.sIsInitialized = true;
        arg1.registerActivityLifecycleCallbacks(new Application$ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity arg1, Bundle arg2) {
                ApplicationStatus.onStateChange(arg1, 1);
                ApplicationStatus.checkWindowCallBack(arg1);
            }

            public void onActivityDestroyed(Activity arg2) {
                ActivityWindowAndroid.checkContext(true);
                ApplicationStatus.onStateChange(arg2, 6);
            }

            public void onActivityPaused(Activity arg2) {
                ActivityWindowAndroid.checkContext(true);
                ApplicationStatus.onStateChange(arg2, 4);
                ApplicationStatus.checkWindowCallBack(arg2);
            }

            public void onActivityResumed(Activity arg2) {
                ActivityWindowAndroid.checkContext(true);
                ApplicationStatus.onStateChange(arg2, 3);
                ApplicationStatus.checkWindowCallBack(arg2);
            }

            public void onActivitySaveInstanceState(Activity arg1, Bundle arg2) {
            }

            public void onActivityStarted(Activity arg2) {
                ActivityWindowAndroid.checkContext(true);
                ApplicationStatus.onStateChange(arg2, 2);
                ApplicationStatus.checkWindowCallBack(arg2);
            }

            public void onActivityStopped(Activity arg2) {
                ActivityWindowAndroid.checkContext(true);
                ApplicationStatus.onStateChange(arg2, 5);
            }
        });
    }

    public static boolean isEveryActivityDestroyed() {
        return ApplicationStatus.sActivityInfo.isEmpty();
    }

    private static native void nativeOnApplicationStateChange(int arg0) {
    }

    private static void onStateChange(Activity arg5, int arg6) {
        if(arg5 == null) {
            throw new IllegalArgumentException("null activity is not supported");
        }

        Log.i("ApplicationStatus", " onStateChange: " + arg6 + " ,id = " + arg5.hashCode());
        if(ApplicationStatus.sActivity == null || arg6 == 1 || arg6 == 3 || arg6 == 2) {
            ApplicationStatus.sActivity = arg5;
        }

        int v0 = ApplicationStatus.getStateForApplication();
        org.chromium.base.ApplicationStatus$1 v2 = null;
        if(arg6 == 1) {
            if(!ApplicationStatus.sActivityInfo.containsKey(arg5)) {
                ApplicationStatus.sActivityInfo.put(arg5, new ActivityInfo(v2));
            }
            else {
                org.chromium.base.Log.i("ApplicationStatus", "multi webviews in the same activity", new Object[0]);
            }
        }

        Object v1_1 = ApplicationStatus.sCachedApplicationStateLock;
        __monitor_enter(v1_1);
        try {
            ApplicationStatus.sCachedApplicationState = ((Integer)v2);
            __monitor_exit(v1_1);
        }
        catch(Throwable v5) {
            try {
            label_83:
                __monitor_exit(v1_1);
            }
            catch(Throwable v5) {
                goto label_83;
            }

            throw v5;
        }

        v1_1 = ApplicationStatus.sActivityInfo.get(arg5);
        if(v1_1 == null) {
            return;
        }

        ((ActivityInfo)v1_1).setStatus(arg6);
        if(arg6 == 6) {
            ApplicationStatus.sActivityInfo.remove(arg5);
            if(arg5 == ApplicationStatus.sActivity) {
                ApplicationStatus.sActivity = ((Activity)v2);
            }
        }

        Iterator v1_2 = ((ActivityInfo)v1_1).getListeners().iterator();
        while(v1_2.hasNext()) {
            v1_2.next().onActivityStateChange(arg5, arg6);
        }

        v1_2 = ApplicationStatus.sGeneralActivityStateListeners.iterator();
        while(v1_2.hasNext()) {
            v1_2.next().onActivityStateChange(arg5, arg6);
        }

        int v5_1 = ApplicationStatus.getStateForApplication();
        if(v5_1 != v0) {
            Iterator v6 = ApplicationStatus.sApplicationStateListeners.iterator();
            while(v6.hasNext()) {
                v6.next().onApplicationStateChange(v5_1);
            }
        }
    }

    @VisibleForTesting public static void onStateChangeForTesting(Activity arg0, int arg1) {
        ApplicationStatus.onStateChange(arg0, arg1);
    }

    public static void registerApplicationStateListener(ApplicationStateListener arg1) {
        ApplicationStatus.sApplicationStateListeners.addObserver(arg1);
    }

    @SuppressLint(value={"NewApi"}) public static void registerStateListenerForActivity(ActivityStateListener arg1, Activity arg2, View arg3) {
        if(arg2 == null) {
            throw new IllegalStateException("Attempting to register listener on a null activity.");
        }

        ApplicationStatus.assertInitialized();
        Object v0 = ApplicationStatus.sActivityInfo.get(arg2);
        if(v0 == null) {
            ApplicationStatusManager.checkWindowCallBack(arg2, arg3);
            ApplicationStatusManager.informActivityStarted(arg2, arg3);
            v0 = ApplicationStatus.sActivityInfo.get(arg2);
        }

        if(v0 == null) {
            throw new IllegalStateException("Attempting to register listener on an untracked activity.");
        }

        ((ActivityInfo)v0).getListeners().addObserver(arg1);
    }

    public static void registerStateListenerForAllActivities(ActivityStateListener arg1) {
        ApplicationStatus.sGeneralActivityStateListeners.addObserver(arg1);
    }

    @CalledByNative private static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                if(ApplicationStatus.sNativeApplicationStateListener != null) {
                    return;
                }

                ApplicationStatus.sNativeApplicationStateListener = new ApplicationStateListener() {
                    public void onApplicationStateChange(int arg1) {
                        ApplicationStatus.nativeOnApplicationStateChange(arg1);
                    }
                };
                ApplicationStatus.registerApplicationStateListener(ApplicationStatus.sNativeApplicationStateListener);
            }
        });
    }

    public static void registerWindowFocusChangedListener(WindowFocusChangedListener arg1) {
        ApplicationStatus.sWindowFocusListeners.addObserver(arg1);
    }

    public static void unregisterActivityStateListener(ActivityStateListener arg2) {
        ApplicationStatus.sGeneralActivityStateListeners.removeObserver(arg2);
        Iterator v0 = ApplicationStatus.sActivityInfo.values().iterator();
        while(v0.hasNext()) {
            v0.next().getListeners().removeObserver(arg2);
        }
    }

    public static void unregisterApplicationStateListener(ApplicationStateListener arg1) {
        ApplicationStatus.sApplicationStateListeners.removeObserver(arg1);
    }

    public static void unregisterWindowFocusChangedListener(WindowFocusChangedListener arg1) {
        ApplicationStatus.sWindowFocusListeners.removeObserver(arg1);
    }
}

