package org.chromium.ui.base;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender$SendIntentException;
import android.util.Log;
import android.view.View$OnLayoutChangeListener;
import android.view.View;
import com.tencent.xweb.xprofile.XProfileManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.ApplicationStatus$ActivityStateListener;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.ui.UiUtils;

public class ActivityWindowAndroid extends WindowAndroid implements View$OnLayoutChangeListener, ActivityStateListener {
    private static final int REQUEST_CODE_PREFIX = 1000;
    private static final int REQUEST_CODE_RANGE_SIZE = 100;
    private static final String TAG = "ActivityWindowAndroid";
    private WeakReference mActivityRef;
    private int mNextRequestCode;
    public static ArrayList sListWindowWaitforActivity;

    static {
        ActivityWindowAndroid.sListWindowWaitforActivity = new ArrayList();
    }

    public ActivityWindowAndroid(Context arg2) {
        this(arg2, true);
    }

    public ActivityWindowAndroid(Context arg3, boolean arg4) {
        super(arg3);
        Activity v3 = ActivityWindowAndroid.activityFromContext(arg3);
        if(v3 != null) {
            Log.i("ActivityWindowAndroid", "ActivityWindowAndroid: is activity");
            if(arg4) {
                this.bindToActivity(v3, false);
            }
        }
        else {
            Log.i("ActivityWindowAndroid", "ActivityWindowAndroid: not activity");
            ActivityWindowAndroid.addWindowToWaittingList(this);
        }

        this.setAndroidPermissionDelegate(this.createAndroidPermissionDelegate());
    }

    Activity _getActivity() {
        if(this.mActivityRef == null) {
            return null;
        }

        return this.mActivityRef.get();
    }

    public static void addWindowToWaittingList(ActivityWindowAndroid arg3) {
        Class v0 = ActivityWindowAndroid.class;
        __monitor_enter(v0);
        try {
            ActivityWindowAndroid.sListWindowWaitforActivity.add(new WeakReference(arg3));
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    void bindToActivity(Activity arg1, boolean arg2) {
        if(arg2) {
            ApplicationStatus.registerStateListenerForActivity(((ActivityStateListener)this), arg1, null);
        }
        else {
            ApplicationStatus.registerStateListenerForActivity(((ActivityStateListener)this), arg1, this.mWebviewUI);
        }

        this.mActivityRef = new WeakReference(arg1);
    }

    public void cancelIntent(int arg2) {
        Object v0 = this.getActivity().get();
        if(v0 == null) {
            return;
        }

        ((Activity)v0).finishActivity(arg2);
    }

    public static void checkContext(boolean arg3) {
        Class v0 = ActivityWindowAndroid.class;
        __monitor_enter(v0);
        try {
            if(ActivityWindowAndroid.sListWindowWaitforActivity.size() < 1) {
                org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "checkContext no window is waitting");
            }

            int v1 = 0;
            while(v1 < ActivityWindowAndroid.sListWindowWaitforActivity.size()) {
                Object v2 = ActivityWindowAndroid.sListWindowWaitforActivity.get(v1).get();
                if(v2 != null) {
                    if(((ActivityWindowAndroid)v2).recheckBindActivity(arg3)) {
                    }
                    else {
                        ++v1;
                        continue;
                    }
                }

                ActivityWindowAndroid.sListWindowWaitforActivity.remove(v1);
            }
        }
        catch(Throwable v3) {
            goto label_28;
        }

        __monitor_exit(v0);
        return;
    label_28:
        __monitor_exit(v0);
        throw v3;
    }

    protected ActivityAndroidPermissionDelegate createAndroidPermissionDelegate() {
        return new ActivityAndroidPermissionDelegate(this.getActivity());
    }

    private Activity findCurActivity() {
        Activity v0 = ActivityWindowAndroid.activityFromContext(this.getContext().get());
        if(v0 != null) {
            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "findCurActivity by  activityFromContext");
            return v0;
        }

        Activity v1 = null;
        if(this.mWebviewUI == null) {
            org.xwalk.core.internal.Log.e("ActivityWindowAndroid", "findCurActivity mWebviewUI null");
            return v1;
        }

        View v0_1 = this.mWebviewUI.getRootView();
        if(v0_1 == null) {
            org.xwalk.core.internal.Log.e("ActivityWindowAndroid", "findCurActivity rootView null");
            return v1;
        }

        v1 = ActivityWindowAndroid.activityFromContext(v0_1.getContext());
        if(v1 != null) {
            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "findCurActivity by rootView.getContext");
            return v1;
        }

        v0 = ActivityWindowAndroid.getActivityFromDecorContext(v0_1.getContext());
        if(v0 != null) {
            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "findCurActivity by getActivityFromDecorContext");
            return v0;
        }

        return v0;
    }

    private int generateNextRequestCode() {
        int v0 = this.mNextRequestCode + 1000;
        this.mNextRequestCode = (this.mNextRequestCode + 1) % 100;
        return v0;
    }

    public WeakReference getActivity() {
        return new WeakReference(ActivityWindowAndroid.activityFromContext(this.getContext().get()));
    }

    public static Activity getActivityFromDecorContext(Context arg4) {
        if(arg4.getClass().getName().contains("com.android.internal.policy.DecorContext")) {
            try {
                Field v0 = arg4.getClass().getDeclaredField("mPhoneWindow");
                v0.setAccessible(true);
                Object v4_1 = v0.get(arg4);
                Activity v4_2 = ActivityWindowAndroid.activityFromContext(v4_1.getClass().getMethod("getContext").invoke(v4_1));
                return v4_2;
            }
            catch(Exception v4) {
                org.xwalk.core.internal.Log.e("ActivityWindowAndroid", "getActivityFromDecorContext " + v4.getMessage());
            }
        }

        return null;
    }

    boolean hasActivity() {
        boolean v0 = this._getActivity() != null ? true : false;
        return v0;
    }

    boolean hasActivityBefore() {
        if(this.mActivityRef != null) {
            return 1;
        }

        return 0;
    }

    public boolean onActivityResult(int arg3, int arg4, Intent arg5) {
        Object v0 = this.mOutstandingIntents.get(arg3);
        this.mOutstandingIntents.delete(arg3);
        Object v3 = this.mIntentErrors.remove(Integer.valueOf(arg3));
        if(v0 != null) {
            ((IntentCallback)v0).onIntentCompleted(((WindowAndroid)this), arg4, arg5);
            return 1;
        }

        if(v3 != null) {
            this.showCallbackNonExistentError(((String)v3));
            return 1;
        }

        return 0;
    }

    public void onActivityStateChange(Activity arg4, int arg5) {
        org.chromium.base.Log.i("ActivityWindowAndroid", "onActivityStateChange activity:" + arg4 + "id is " + arg4.hashCode() + ",newState:" + arg5, new Object[0]);
        int v4 = 5;
        if(arg5 == v4) {
            if(this.mActivityRef != null) {
                this.mActivityRef.clear();
            }

            this.onActivityStopped();
            if(this.mValueCallBack == null) {
                return;
            }

            this.mValueCallBack.onReceiveValue("STOPPED");
        }
        else {
            Object v1_1 = null;
            if(arg5 == 4) {
                if(this.mValueCallBack != null) {
                    this.mValueCallBack.onReceiveValue("PAUSED");
                }

                XProfileManager.getInstance().sendMessage(v4, v1_1);
                return;
            }

            if(arg5 == 3) {
                if(this.mValueCallBack != null) {
                    this.mValueCallBack.onReceiveValue("RESUMED");
                }

                XProfileManager.getInstance().sendMessage(6, v1_1);
                return;
            }

            if(arg5 != 2) {
                return;
            }

            this.onActivityStarted();
        }
    }

    public void onBaseContextChanged() {
        org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "andrewu onBaseContextChanged  onBaseContextChanged  " + this.hashCode());
        Activity v0 = this._getActivity();
        Activity v1_1 = ActivityWindowAndroid.activityFromContext(this.getContext().get());
        if(v0 == v1_1) {
            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "andrewu onBaseContextChanged  activity not changed ");
            return;
        }

        if(v1_1 != null) {
            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "andrewu onBaseContextChanged  find new activity ");
            this.removeFromWaitList();
            ApplicationStatus.unregisterActivityStateListener(((ActivityStateListener)this));
            this.bindToActivity(v1_1, false);
            int v0_1 = ApplicationStatus.getStatus(v1_1);
            if(2 != v0_1 && 3 != v0_1) {
                return;
            }

            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "andrewu onBaseContextChanged the new activity is started informstarted");
            this.onActivityStarted();
        }
        else {
            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "andrewu onBaseContextChanged  newActivity == null");
        }
    }

    public void onLayoutChange(View arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
        this.keyboardVisibilityPossiblyChanged(UiUtils.isKeyboardShowing(this.getActivity().get(), arg1));
    }

    public boolean onRequestPermissionsResult(int arg2, String[] arg3, int[] arg4) {
        this.getAndroidPermissionDelegate().onRequestPermissionsResult(arg2, arg3, arg4);
        return 1;
    }

    boolean recheckBindActivity(boolean arg5) {
        Activity v0 = this.findCurActivity();
        if(v0 == null) {
            org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "recheckBindActivity  failed " + this.hashCode());
            return 0;
        }

        org.xwalk.core.internal.Log.i("ActivityWindowAndroid", "recheckBindActivity sucsess " + this.hashCode());
        this.bindToActivity(v0, arg5);
        this.setWebviewUI(null);
        return 1;
    }

    protected void registerKeyboardVisibilityCallbacks() {
        Object v0 = this.getActivity().get();
        if(v0 == null) {
            return;
        }

        View v0_1 = ((Activity)v0).findViewById(0x1020002);
        this.mIsKeyboardShowing = UiUtils.isKeyboardShowing(this.getActivity().get(), v0_1);
        v0_1.addOnLayoutChangeListener(((View$OnLayoutChangeListener)this));
    }

    void removeFromWaitList() {
        __monitor_enter(this);
        int v0 = 0;
        try {
            while(true) {
            label_2:
                if(v0 >= ActivityWindowAndroid.sListWindowWaitforActivity.size()) {
                    goto label_15;
                }

                if(ActivityWindowAndroid.sListWindowWaitforActivity.get(v0).get() != this) {
                    goto label_13;
                }

                ActivityWindowAndroid.sListWindowWaitforActivity.remove(v0);
                break;
            }
        }
        catch(Throwable v0_1) {
            __monitor_exit(this);
            throw v0_1;
        }

        __monitor_exit(this);
        return;
    label_13:
        ++v0;
        goto label_2;
    label_15:
        __monitor_exit(this);
    }

    public int showCancelableIntent(PendingIntent arg10, IntentCallback arg11, Integer arg12) {
        Object v1 = this.getActivity().get();
        int v0 = -1;
        if(v1 == null) {
            return v0;
        }

        int v8 = this.generateNextRequestCode();
        try {
            ((Activity)v1).startIntentSenderForResult(arg10.getIntentSender(), v8, new Intent(), 0, 0, 0);
        }
        catch(IntentSender$SendIntentException ) {
            return v0;
        }

        this.storeCallbackData(v8, arg11, arg12);
        return v8;
    }

    public int showCancelableIntent(Intent arg4, IntentCallback arg5, Integer arg6) {
        Object v0 = this.getActivity().get();
        int v1 = -1;
        if(v0 == null) {
            return v1;
        }

        int v2 = this.generateNextRequestCode();
        try {
            ((Activity)v0).startActivityForResult(arg4, v2);
        }
        catch(ActivityNotFoundException ) {
            return v1;
        }

        this.storeCallbackData(v2, arg5, arg6);
        return v2;
    }

    public int showCancelableIntent(Callback arg3, IntentCallback arg4, Integer arg5) {
        if(this.getActivity().get() == null) {
            return -1;
        }

        int v0 = this.generateNextRequestCode();
        arg3.onResult(Integer.valueOf(v0));
        this.storeCallbackData(v0, arg4, arg5);
        return v0;
    }

    private void storeCallbackData(int arg2, IntentCallback arg3, Integer arg4) {
        Object v4;
        this.mOutstandingIntents.put(arg2, arg3);
        HashMap v3 = this.mIntentErrors;
        Integer v2 = Integer.valueOf(arg2);
        if(arg4 == null) {
            v4 = null;
        }
        else {
            String v4_1 = this.mApplicationContext.getString(arg4.intValue());
        }

        v3.put(v2, v4);
    }

    protected void unregisterKeyboardVisibilityCallbacks() {
        Object v0 = this.getActivity().get();
        if(v0 == null) {
            return;
        }

        ((Activity)v0).findViewById(0x1020002).removeOnLayoutChangeListener(((View$OnLayoutChangeListener)this));
    }
}

