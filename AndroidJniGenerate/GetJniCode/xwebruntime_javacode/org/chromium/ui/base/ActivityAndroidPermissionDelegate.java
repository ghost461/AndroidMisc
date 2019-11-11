package org.chromium.ui.base;

import android.app.Activity;
import android.content.SharedPreferences$Editor;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;

public class ActivityAndroidPermissionDelegate implements AndroidPermissionDelegate {
    private static final String PERMISSION_QUERIED_KEY_PREFIX = "HasRequestedAndroidPermission::";
    private static final int REQUEST_CODE_PREFIX = 1000;
    private static final int REQUEST_CODE_RANGE_SIZE = 100;
    private WeakReference mActivity;
    private Handler mHandler;
    private int mNextRequestCode;
    private SparseArray mOutstandingPermissionRequests;

    static {
    }

    public ActivityAndroidPermissionDelegate(WeakReference arg1) {
        super();
        this.mActivity = arg1;
        this.mHandler = new Handler();
        this.mOutstandingPermissionRequests = new SparseArray();
    }

    public boolean canRequestPermission(String arg5) {
        if(Build$VERSION.SDK_INT < 23) {
            return 0;
        }

        Object v0 = this.mActivity.get();
        if(v0 == null) {
            return 0;
        }

        if(this.isPermissionRevokedByPolicy(arg5)) {
            return 0;
        }

        if(((Activity)v0).shouldShowRequestPermissionRationale(arg5)) {
            return 1;
        }

        if(!ContextUtils.getAppSharedPreferences().getBoolean(this.getHasRequestedPermissionKey(arg5), false)) {
            return 1;
        }

        this.logUMAOnRequestPermissionDenied(arg5);
        return 0;
    }

    private String getHasRequestedPermissionKey(String arg3) {
        if(Build$VERSION.SDK_INT < 26) {
            try {
                PermissionInfo v0 = ContextUtils.getApplicationContext().getPackageManager().getPermissionInfo(arg3, 0x80);
                if(!TextUtils.isEmpty(v0.group)) {
                    arg3 = v0.group;
                }

                goto label_12;
            }
            catch(PackageManager$NameNotFoundException ) {
            label_12:
                return "HasRequestedAndroidPermission::" + arg3;
            }
        }

        goto label_12;
    }

    public boolean hasPermission(String arg4) {
        boolean v4 = ApiCompatibilityUtils.checkPermission(ContextUtils.getApplicationContext(), arg4, Process.myPid(), Process.myUid()) == 0 ? true : false;
        return v4;
    }

    public boolean isPermissionRevokedByPolicy(String arg4) {
        if(Build$VERSION.SDK_INT < 23) {
            return 0;
        }

        Object v0 = this.mActivity.get();
        if(v0 == null) {
            return 0;
        }

        return ((Activity)v0).getPackageManager().isPermissionRevokedByPolicy(arg4, ((Activity)v0).getPackageName());
    }

    protected void logUMAOnRequestPermissionDenied(String arg1) {
    }

    public void onRequestPermissionsResult(int arg5, String[] arg6, int[] arg7) {
        this.mActivity.get();
        SharedPreferences$Editor v0 = ContextUtils.getAppSharedPreferences().edit();
        int v1;
        for(v1 = 0; v1 < arg6.length; ++v1) {
            v0.putBoolean(this.getHasRequestedPermissionKey(arg6[v1]), true);
        }

        v0.apply();
        Object v0_1 = this.mOutstandingPermissionRequests.get(arg5);
        this.mOutstandingPermissionRequests.delete(arg5);
        if(v0_1 == null) {
            return;
        }

        ((PermissionCallback)v0_1).onRequestPermissionsResult(arg6, arg7);
    }

    public void requestPermissions(String[] arg3, PermissionCallback arg4) {
        if(this.requestPermissionsInternal(arg3, arg4)) {
            return;
        }

        this.mHandler.post(new Runnable(arg3, arg4) {
            public void run() {
                int[] v0 = new int[this.val$permissions.length];
                int v2;
                for(v2 = 0; v2 < this.val$permissions.length; ++v2) {
                    int v3 = ActivityAndroidPermissionDelegate.this.hasPermission(this.val$permissions[v2]) ? 0 : -1;
                    v0[v2] = v3;
                }

                this.val$callback.onRequestPermissionsResult(this.val$permissions, v0);
            }
        });
    }

    private boolean requestPermissionsInternal(String[] arg5, PermissionCallback arg6) {
        if(Build$VERSION.SDK_INT < 23) {
            return 0;
        }

        Object v0 = this.mActivity.get();
        if(v0 == null) {
            return 0;
        }

        int v1 = this.mNextRequestCode + 1000;
        this.mNextRequestCode = (this.mNextRequestCode + 1) % 100;
        this.mOutstandingPermissionRequests.put(v1, arg6);
        ((Activity)v0).requestPermissions(arg5, v1);
        return 1;
    }
}

