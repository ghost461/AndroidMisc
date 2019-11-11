package org.chromium.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

public abstract class AbstractAppRestrictionsProvider extends PolicyProvider {
    class org.chromium.policy.AbstractAppRestrictionsProvider$1 extends BroadcastReceiver {
        org.chromium.policy.AbstractAppRestrictionsProvider$1(AbstractAppRestrictionsProvider arg1) {
            AbstractAppRestrictionsProvider.this = arg1;
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            AbstractAppRestrictionsProvider.this.refresh();
        }
    }

    private static final String TAG = "policy";
    private final BroadcastReceiver mAppRestrictionsChangedReceiver;
    private final Context mContext;
    private static Bundle sTestRestrictions;

    public AbstractAppRestrictionsProvider(Context arg2) {
        super();
        this.mAppRestrictionsChangedReceiver = new org.chromium.policy.AbstractAppRestrictionsProvider$1(this);
        this.mContext = arg2;
    }

    public void destroy() {
        this.stopListening();
        super.destroy();
    }

    protected abstract Bundle getApplicationRestrictions(String arg1);

    protected abstract String getRestrictionChangeIntentAction();

    @VisibleForTesting protected void recordStartTimeHistogram(long arg1) {
    }

    public void refresh() {
        if(AbstractAppRestrictionsProvider.sTestRestrictions != null) {
            this.notifySettingsAvailable(AbstractAppRestrictionsProvider.sTestRestrictions);
            return;
        }

        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        long v1 = System.currentTimeMillis();
        Bundle v3 = this.getApplicationRestrictions(this.mContext.getPackageName());
        this.recordStartTimeHistogram(v1);
        StrictMode.setThreadPolicy(v0);
        this.notifySettingsAvailable(v3);
    }

    @VisibleForTesting public static void setTestRestrictions(Bundle arg3) {
        Object[] v2_1;
        String v0 = "policy";
        String v1 = "Test Restrictions: %s";
        if(arg3 == null) {
            Object v2 = null;
        }
        else {
            v2_1 = arg3.keySet().toArray();
        }

        Log.d(v0, v1, v2_1);
        AbstractAppRestrictionsProvider.sTestRestrictions = arg3;
    }

    public void startListeningForPolicyChanges() {
        String v0 = this.getRestrictionChangeIntentAction();
        if(v0 == null) {
            return;
        }

        this.mContext.registerReceiver(this.mAppRestrictionsChangedReceiver, new IntentFilter(v0), null, new Handler(ThreadUtils.getUiThreadLooper()));
    }

    public void stopListening() {
        if(this.getRestrictionChangeIntentAction() != null) {
            this.mContext.unregisterReceiver(this.mAppRestrictionsChangedReceiver);
        }
    }
}

