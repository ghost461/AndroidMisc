package org.chromium.net;

import org.chromium.base.ApplicationStatus$ApplicationStateListener;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.VisibleForTesting;

public class RegistrationPolicyApplicationStatus extends RegistrationPolicy implements ApplicationStateListener {
    private boolean mDestroyed;

    public RegistrationPolicyApplicationStatus() {
        super();
    }

    protected void destroy() {
        if(this.mDestroyed) {
            return;
        }

        ApplicationStatus.unregisterApplicationStateListener(((ApplicationStateListener)this));
        this.mDestroyed = true;
    }

    @VisibleForTesting int getApplicationState() {
        return ApplicationStatus.getStateForApplication();
    }

    protected void init(NetworkChangeNotifierAutoDetect arg1) {
        super.init(arg1);
        ApplicationStatus.registerApplicationStateListener(((ApplicationStateListener)this));
        this.onApplicationStateChange(this.getApplicationState());
    }

    public void onApplicationStateChange(int arg2) {
        if(arg2 == 1) {
            this.register();
        }
        else if(arg2 == 2) {
            this.unregister();
        }
    }
}

