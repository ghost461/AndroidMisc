package org.chromium.components.download.internal;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.net.NetworkChangeNotifierAutoDetect$Observer;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.RegistrationPolicyAlwaysRegister;

@JNINamespace(value="download") public final class NetworkStatusListenerAndroid implements Observer {
    private long mNativePtr;
    private final NetworkChangeNotifierAutoDetect mNotifier;

    static {
    }

    private NetworkStatusListenerAndroid(long arg1) {
        super();
        this.mNativePtr = arg1;
        this.mNotifier = new NetworkChangeNotifierAutoDetect(((Observer)this), new RegistrationPolicyAlwaysRegister());
    }

    @CalledByNative private void clearNativePtr() {
        this.mNotifier.unregister();
        this.mNativePtr = 0;
    }

    @CalledByNative private static NetworkStatusListenerAndroid create(long arg1) {
        return new NetworkStatusListenerAndroid(arg1);
    }

    @CalledByNative private int getCurrentConnectionType() {
        return this.mNotifier.getCurrentNetworkState().getConnectionType();
    }

    private native void nativeNotifyNetworkChange(long arg1, int arg2) {
    }

    public void onConnectionSubtypeChanged(int arg1) {
    }

    public void onConnectionTypeChanged(int arg6) {
        if(this.mNativePtr != 0) {
            this.nativeNotifyNetworkChange(this.mNativePtr, arg6);
        }
    }

    public void onNetworkConnect(long arg1, int arg3) {
    }

    public void onNetworkDisconnect(long arg1) {
    }

    public void onNetworkSoonToDisconnect(long arg1) {
    }

    public void purgeActiveNetworkList(long[] arg1) {
    }
}

