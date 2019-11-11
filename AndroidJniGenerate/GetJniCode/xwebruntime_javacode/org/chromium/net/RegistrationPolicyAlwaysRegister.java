package org.chromium.net;

public class RegistrationPolicyAlwaysRegister extends RegistrationPolicy {
    public RegistrationPolicyAlwaysRegister() {
        super();
    }

    protected void destroy() {
    }

    protected void init(NetworkChangeNotifierAutoDetect arg1) {
        super.init(arg1);
        this.register();
    }
}

