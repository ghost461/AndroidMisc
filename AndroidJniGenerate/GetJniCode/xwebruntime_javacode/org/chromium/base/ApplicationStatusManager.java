package org.chromium.base;

import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.View;
import org.xwalk.core.internal.Log;

public class ApplicationStatusManager {
    private static final String TAG = "ApplicationStatusManager";
    private static final String TOOLBAR_CALLBACK_INTERNAL_WRAPPER_CLASS = "android.support.v7.internal.app.ToolbarActionBar$ToolbarCallbackWrapper";
    private static final String TOOLBAR_CALLBACK_WRAPPER_CLASS = "android.support.v7.app.ToolbarActionBar$ToolbarCallbackWrapper";

    public ApplicationStatusManager() {
        super();
    }

    public static void checkWindowCallBack(Activity arg3, View arg4) {
        boolean v4;
        if(arg4 == null) {
            v4 = true;
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            v4 = arg4.isAttachedToWindow();
        }
        else {
            v4 = false;
        }

        Log.i("ApplicationStatusManager", "window callback  informActivityStarted needHookWindowCallBack =  " + v4 + ",  id = " + arg3.hashCode());
        if(v4) {
            ApplicationStatus.checkWindowCallBack(arg3);
        }
    }

    public static void informActivityStarted(Activity arg0, View arg1) {
        ApplicationStatus.informActivityStarted(arg0);
    }

    public static void init(Application arg2) {
        ApplicationStatus.initialize(arg2);
        Log.i("ApplicationStatusManager", "registerActivityLifecycleCallbacks");
        arg2.registerActivityLifecycleCallbacks(new Application$ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity arg3, Bundle arg4) {
                Log.i("ApplicationStatusManager", "onActivityCreated Activity:" + arg3);
            }

            public void onActivityDestroyed(Activity arg4) {
                Log.i("ApplicationStatusManager", "onActivityDestroyed Activity:" + arg4);
            }

            public void onActivityPaused(Activity arg4) {
                if(BuildConfig.DCHECK_IS_ON) {
                    Log.i("ApplicationStatusManager", "onActivityPaused Activity:" + arg4);
                }
            }

            public void onActivityResumed(Activity arg4) {
                if(BuildConfig.DCHECK_IS_ON) {
                    Log.i("ApplicationStatusManager", "onActivityResumed Activity:" + arg4);
                }
            }

            public void onActivitySaveInstanceState(Activity arg3, Bundle arg4) {
                if(BuildConfig.DCHECK_IS_ON) {
                    Log.i("ApplicationStatusManager", "onActivitySaveInstanceState Activity:" + arg3);
                }
            }

            public void onActivityStarted(Activity arg4) {
                Log.i("ApplicationStatusManager", "onActivityStarted Activity:" + arg4);
            }

            public void onActivityStopped(Activity arg4) {
                Log.i("ApplicationStatusManager", "onActivityStopped Activity:" + arg4);
            }
        });
    }
}

