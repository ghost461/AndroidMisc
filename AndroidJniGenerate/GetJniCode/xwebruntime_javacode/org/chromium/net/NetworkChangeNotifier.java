package org.chromium.net;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.os.Build$VERSION;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ContextUtils;
import org.chromium.base.ObserverList;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.NativeClassQualifiedName;

@JNINamespace(value="net") public class NetworkChangeNotifier {
    public interface ConnectionTypeObserver {
        void onConnectionTypeChanged(int arg1);
    }

    private NetworkChangeNotifierAutoDetect mAutoDetector;
    private final ObserverList mConnectionTypeObservers;
    private final ConnectivityManager mConnectivityManager;
    private int mCurrentConnectionType;
    private final ArrayList mNativeChangeNotifiers;
    @SuppressLint(value={"StaticFieldLeak"}) private static NetworkChangeNotifier sInstance;

    static {
    }

    @VisibleForTesting protected NetworkChangeNotifier() {
        super();
        this.mCurrentConnectionType = 0;
        this.mNativeChangeNotifiers = new ArrayList();
        this.mConnectionTypeObservers = new ObserverList();
        this.mConnectivityManager = ContextUtils.getApplicationContext().getSystemService("connectivity");
    }

    static void access$000(NetworkChangeNotifier arg0, int arg1) {
        arg0.updateCurrentConnectionType(arg1);
    }

    public static void addConnectionTypeObserver(ConnectionTypeObserver arg1) {
        NetworkChangeNotifier.getInstance().addConnectionTypeObserverInternal(arg1);
    }

    private void addConnectionTypeObserverInternal(ConnectionTypeObserver arg2) {
        this.mConnectionTypeObservers.addObserver(arg2);
    }

    @CalledByNative public void addNativeObserver(long arg2) {
        this.mNativeChangeNotifiers.add(Long.valueOf(arg2));
    }

    private void destroyAutoDetector() {
        if(this.mAutoDetector != null) {
            this.mAutoDetector.destroy();
            this.mAutoDetector = null;
        }
    }

    @CalledByNative public static void fakeConnectionSubtypeChanged(int arg1) {
        NetworkChangeNotifier.setAutoDetectConnectivityState(false);
        NetworkChangeNotifier.getInstance().notifyObserversOfConnectionSubtypeChange(arg1);
    }

    @CalledByNative public static void fakeDefaultNetwork(long arg1, int arg3) {
        NetworkChangeNotifier.setAutoDetectConnectivityState(false);
        NetworkChangeNotifier.getInstance().notifyObserversOfConnectionTypeChange(arg3, arg1);
    }

    @CalledByNative public static void fakeNetworkConnected(long arg1, int arg3) {
        NetworkChangeNotifier.setAutoDetectConnectivityState(false);
        NetworkChangeNotifier.getInstance().notifyObserversOfNetworkConnect(arg1, arg3);
    }

    @CalledByNative public static void fakeNetworkDisconnected(long arg1) {
        NetworkChangeNotifier.setAutoDetectConnectivityState(false);
        NetworkChangeNotifier.getInstance().notifyObserversOfNetworkDisconnect(arg1);
    }

    @CalledByNative public static void fakeNetworkSoonToBeDisconnected(long arg1) {
        NetworkChangeNotifier.setAutoDetectConnectivityState(false);
        NetworkChangeNotifier.getInstance().notifyObserversOfNetworkSoonToDisconnect(arg1);
    }

    @CalledByNative public static void fakePurgeActiveNetworkList(long[] arg1) {
        NetworkChangeNotifier.setAutoDetectConnectivityState(false);
        NetworkChangeNotifier.getInstance().notifyObserversToPurgeActiveNetworkList(arg1);
    }

    @CalledByNative public static void forceConnectivityState(boolean arg1) {
        NetworkChangeNotifier.setAutoDetectConnectivityState(false);
        NetworkChangeNotifier.getInstance().forceConnectivityStateInternal(arg1);
    }

    private void forceConnectivityStateInternal(boolean arg5) {
        int v1 = 0;
        boolean v0 = this.mCurrentConnectionType != 6 ? true : false;
        if(v0 != arg5) {
            if(arg5) {
            }
            else {
                v1 = 6;
            }

            this.updateCurrentConnectionType(v1);
            this.notifyObserversOfConnectionSubtypeChange((((int)arg5)) ^ 1);
        }
    }

    public static NetworkChangeNotifierAutoDetect getAutoDetectorForTest() {
        return NetworkChangeNotifier.getInstance().mAutoDetector;
    }

    @CalledByNative public int getCurrentConnectionSubtype() {
        int v0 = this.mAutoDetector == null ? 0 : this.mAutoDetector.getCurrentNetworkState().getConnectionSubtype();
        return v0;
    }

    @CalledByNative public int getCurrentConnectionType() {
        return this.mCurrentConnectionType;
    }

    @CalledByNative public long getCurrentDefaultNetId() {
        long v0 = this.mAutoDetector == null ? -1 : this.mAutoDetector.getDefaultNetId();
        return v0;
    }

    @CalledByNative public long[] getCurrentNetworksAndTypes() {
        long[] v0 = this.mAutoDetector == null ? new long[0] : this.mAutoDetector.getNetworksAndTypes();
        return v0;
    }

    public static NetworkChangeNotifier getInstance() {
        return NetworkChangeNotifier.sInstance;
    }

    @CalledByNative public static NetworkChangeNotifier init() {
        if(NetworkChangeNotifier.sInstance == null) {
            NetworkChangeNotifier.sInstance = new NetworkChangeNotifier();
        }

        return NetworkChangeNotifier.sInstance;
    }

    public static boolean isInitialized() {
        boolean v0 = NetworkChangeNotifier.sInstance != null ? true : false;
        return v0;
    }

    public static boolean isOnline() {
        boolean v0 = NetworkChangeNotifier.getInstance().getCurrentConnectionType() != 6 ? true : false;
        return v0;
    }

    @CalledByNative public static boolean isProcessBoundToNetwork() {
        return NetworkChangeNotifier.getInstance().isProcessBoundToNetworkInternal();
    }

    @TargetApi(value=23) private boolean isProcessBoundToNetworkInternal() {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT < 21) {
            return 0;
        }

        if(Build$VERSION.SDK_INT < 23) {
            if(ConnectivityManager.getProcessDefaultNetwork() != null) {
                v1 = true;
            }

            return v1;
        }

        if(this.mConnectivityManager.getBoundNetworkForProcess() != null) {
            v1 = true;
        }

        return v1;
    }

    @NativeClassQualifiedName(value="NetworkChangeNotifierDelegateAndroid") private native void nativeNotifyConnectionTypeChanged(long arg1, int arg2, long arg3) {
    }

    @NativeClassQualifiedName(value="NetworkChangeNotifierDelegateAndroid") private native void nativeNotifyMaxBandwidthChanged(long arg1, int arg2) {
    }

    @NativeClassQualifiedName(value="NetworkChangeNotifierDelegateAndroid") private native void nativeNotifyOfNetworkConnect(long arg1, long arg2, int arg3) {
    }

    @NativeClassQualifiedName(value="NetworkChangeNotifierDelegateAndroid") private native void nativeNotifyOfNetworkDisconnect(long arg1, long arg2) {
    }

    @NativeClassQualifiedName(value="NetworkChangeNotifierDelegateAndroid") private native void nativeNotifyOfNetworkSoonToDisconnect(long arg1, long arg2) {
    }

    @NativeClassQualifiedName(value="NetworkChangeNotifierDelegateAndroid") private native void nativeNotifyPurgeActiveNetworkList(long arg1, long[] arg2) {
    }

    void notifyObserversOfConnectionSubtypeChange(int arg4) {
        Iterator v0 = this.mNativeChangeNotifiers.iterator();
        while(v0.hasNext()) {
            this.nativeNotifyMaxBandwidthChanged(v0.next().longValue(), arg4);
        }
    }

    private void notifyObserversOfConnectionTypeChange(int arg9, long arg10) {
        Iterator v0 = this.mNativeChangeNotifiers.iterator();
        while(v0.hasNext()) {
            this.nativeNotifyConnectionTypeChanged(v0.next().longValue(), arg9, arg10);
        }

        Iterator v10 = this.mConnectionTypeObservers.iterator();
        while(v10.hasNext()) {
            v10.next().onConnectionTypeChanged(arg9);
        }
    }

    void notifyObserversOfConnectionTypeChange(int arg3) {
        this.notifyObserversOfConnectionTypeChange(arg3, this.getCurrentDefaultNetId());
    }

    void notifyObserversOfNetworkConnect(long arg9, int arg11) {
        Iterator v0 = this.mNativeChangeNotifiers.iterator();
        while(v0.hasNext()) {
            this.nativeNotifyOfNetworkConnect(v0.next().longValue(), arg9, arg11);
        }
    }

    void notifyObserversOfNetworkDisconnect(long arg4) {
        Iterator v0 = this.mNativeChangeNotifiers.iterator();
        while(v0.hasNext()) {
            this.nativeNotifyOfNetworkDisconnect(v0.next().longValue(), arg4);
        }
    }

    void notifyObserversOfNetworkSoonToDisconnect(long arg4) {
        Iterator v0 = this.mNativeChangeNotifiers.iterator();
        while(v0.hasNext()) {
            this.nativeNotifyOfNetworkSoonToDisconnect(v0.next().longValue(), arg4);
        }
    }

    void notifyObserversToPurgeActiveNetworkList(long[] arg4) {
        Iterator v0 = this.mNativeChangeNotifiers.iterator();
        while(v0.hasNext()) {
            this.nativeNotifyPurgeActiveNetworkList(v0.next().longValue(), arg4);
        }
    }

    @CalledByNative public boolean registerNetworkCallbackFailed() {
        boolean v0 = this.mAutoDetector == null ? false : this.mAutoDetector.registerNetworkCallbackFailed();
        return v0;
    }

    public static void registerToReceiveNotificationsAlways() {
        NetworkChangeNotifier.getInstance().setAutoDetectConnectivityStateInternal(true, new RegistrationPolicyAlwaysRegister());
    }

    public static void removeConnectionTypeObserver(ConnectionTypeObserver arg1) {
        NetworkChangeNotifier.getInstance().removeConnectionTypeObserverInternal(arg1);
    }

    private void removeConnectionTypeObserverInternal(ConnectionTypeObserver arg2) {
        this.mConnectionTypeObservers.removeObserver(arg2);
    }

    @CalledByNative public void removeNativeObserver(long arg2) {
        this.mNativeChangeNotifiers.remove(Long.valueOf(arg2));
    }

    static void resetInstanceForTests(NetworkChangeNotifier arg0) {
        NetworkChangeNotifier.sInstance = arg0;
    }

    public static void setAutoDetectConnectivityState(boolean arg2) {
        NetworkChangeNotifier.getInstance().setAutoDetectConnectivityStateInternal(arg2, new RegistrationPolicyApplicationStatus());
    }

    public static void setAutoDetectConnectivityState(RegistrationPolicy arg2) {
        NetworkChangeNotifier.getInstance().setAutoDetectConnectivityStateInternal(true, arg2);
    }

    private void setAutoDetectConnectivityStateInternal(boolean arg2, RegistrationPolicy arg3) {
        if(!arg2) {
            this.destroyAutoDetector();
        }
        else if(this.mAutoDetector == null) {
            this.mAutoDetector = new NetworkChangeNotifierAutoDetect(new Observer() {
                public void onConnectionSubtypeChanged(int arg2) {
                    NetworkChangeNotifier.this.notifyObserversOfConnectionSubtypeChange(arg2);
                }

                public void onConnectionTypeChanged(int arg2) {
                    NetworkChangeNotifier.this.updateCurrentConnectionType(arg2);
                }

                public void onNetworkConnect(long arg2, int arg4) {
                    NetworkChangeNotifier.this.notifyObserversOfNetworkConnect(arg2, arg4);
                }

                public void onNetworkDisconnect(long arg2) {
                    NetworkChangeNotifier.this.notifyObserversOfNetworkDisconnect(arg2);
                }

                public void onNetworkSoonToDisconnect(long arg2) {
                    NetworkChangeNotifier.this.notifyObserversOfNetworkSoonToDisconnect(arg2);
                }

                public void purgeActiveNetworkList(long[] arg2) {
                    NetworkChangeNotifier.this.notifyObserversToPurgeActiveNetworkList(arg2);
                }
            }, arg3);
            NetworkState v2 = this.mAutoDetector.getCurrentNetworkState();
            this.updateCurrentConnectionType(v2.getConnectionType());
            this.notifyObserversOfConnectionSubtypeChange(v2.getConnectionSubtype());
        }
    }

    private void updateCurrentConnectionType(int arg1) {
        this.mCurrentConnectionType = arg1;
        this.notifyObserversOfConnectionTypeChange(arg1);
    }
}

