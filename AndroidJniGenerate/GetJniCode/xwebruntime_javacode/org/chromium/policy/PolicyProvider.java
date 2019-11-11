package org.chromium.policy;

import android.os.Bundle;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

public abstract class PolicyProvider {
    private CombinedPolicyProvider mCombinedPolicyProvider;
    private int mSource;

    static {
    }

    protected PolicyProvider() {
        super();
        this.mSource = -1;
    }

    public void destroy() {
    }

    public void notifySettingsAvailable(Bundle arg3) {
        ThreadUtils.assertOnUiThread();
        this.mCombinedPolicyProvider.onSettingsAvailable(this.mSource, arg3);
    }

    public abstract void refresh();

    final void setManagerAndSource(CombinedPolicyProvider arg1, int arg2) {
        this.mSource = arg2;
        this.mCombinedPolicyProvider = arg1;
        this.startListeningForPolicyChanges();
    }

    protected void startListeningForPolicyChanges() {
    }

    @VisibleForTesting protected void terminateIncognitoSession() {
        this.mCombinedPolicyProvider.terminateIncognitoSession();
    }
}

