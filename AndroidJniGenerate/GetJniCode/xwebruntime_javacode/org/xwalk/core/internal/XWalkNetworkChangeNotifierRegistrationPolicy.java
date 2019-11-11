package org.xwalk.core.internal;

import org.chromium.net.NetworkChangeNotifierAutoDetect$RegistrationPolicy;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

public class XWalkNetworkChangeNotifierRegistrationPolicy extends RegistrationPolicy implements Observer {
    public XWalkNetworkChangeNotifierRegistrationPolicy() {
        super();
    }

    protected void destroy() {
        XWalkContentLifecycleNotifier.removeObserver(((Observer)this));
    }

    protected void init(NetworkChangeNotifierAutoDetect arg1) {
        super.init(arg1);
        XWalkContentLifecycleNotifier.addObserver(((Observer)this));
    }

    public void onFirstXWalkViewCreated() {
        this.register();
    }

    public void onLastXWalkViewDestroyed() {
        this.unregister();
    }
}

