package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.os.Process;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.NativeClassQualifiedName;
import org.chromium.base.metrics.RecordHistogram;
import org.chromium.net.NetworkChangeNotifierAutoDetect$Observer;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.RegistrationPolicyAlwaysRegister;

@JNINamespace(value="content") class BackgroundSyncNetworkObserver implements Observer {
    private static final String TAG = "cr_BgSyncNetObserver";
    private boolean mHasBroadcastConnectionType;
    private int mLastBroadcastConnectionType;
    private List mNativePtrs;
    private NetworkChangeNotifierAutoDetect mNotifier;
    @SuppressLint(value={"StaticFieldLeak"}) private static BackgroundSyncNetworkObserver sInstance;

    private BackgroundSyncNetworkObserver() {
        super();
        ThreadUtils.assertOnUiThread();
        this.mNativePtrs = new ArrayList();
    }

    private void broadcastNetworkChangeIfNecessary(int arg4) {
        if((this.mHasBroadcastConnectionType) && arg4 == this.mLastBroadcastConnectionType) {
            return;
        }

        this.mHasBroadcastConnectionType = true;
        this.mLastBroadcastConnectionType = arg4;
        Iterator v0 = this.mNativePtrs.iterator();
        while(v0.hasNext()) {
            this.nativeNotifyConnectionTypeChanged(v0.next().longValue(), arg4);
        }
    }

    private static boolean canCreateObserver() {
        boolean v0 = ApiCompatibilityUtils.checkPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0 ? true : false;
        return v0;
    }

    @CalledByNative private static BackgroundSyncNetworkObserver createObserver(long arg1) {
        ThreadUtils.assertOnUiThread();
        if(BackgroundSyncNetworkObserver.sInstance == null) {
            BackgroundSyncNetworkObserver.sInstance = new BackgroundSyncNetworkObserver();
        }

        BackgroundSyncNetworkObserver.sInstance.registerObserver(arg1);
        return BackgroundSyncNetworkObserver.sInstance;
    }

    @NativeClassQualifiedName(value="BackgroundSyncNetworkObserverAndroid::Observer") private native void nativeNotifyConnectionTypeChanged(long arg1, int arg2) {
    }

    public void onConnectionSubtypeChanged(int arg1) {
    }

    public void onConnectionTypeChanged(int arg1) {
        ThreadUtils.assertOnUiThread();
        this.broadcastNetworkChangeIfNecessary(arg1);
    }

    public void onNetworkConnect(long arg1, int arg3) {
        ThreadUtils.assertOnUiThread();
        this.broadcastNetworkChangeIfNecessary(this.mNotifier.getCurrentNetworkState().getConnectionType());
    }

    public void onNetworkDisconnect(long arg1) {
        ThreadUtils.assertOnUiThread();
        this.broadcastNetworkChangeIfNecessary(this.mNotifier.getCurrentNetworkState().getConnectionType());
    }

    public void onNetworkSoonToDisconnect(long arg1) {
    }

    public void purgeActiveNetworkList(long[] arg1) {
    }

    private void registerObserver(long arg3) {
        ThreadUtils.assertOnUiThread();
        if(!BackgroundSyncNetworkObserver.canCreateObserver()) {
            RecordHistogram.recordBooleanHistogram("BackgroundSync.NetworkObserver.HasPermission", false);
            return;
        }

        if(this.mNotifier == null) {
            this.mNotifier = new NetworkChangeNotifierAutoDetect(((Observer)this), new RegistrationPolicyAlwaysRegister());
            RecordHistogram.recordBooleanHistogram("BackgroundSync.NetworkObserver.HasPermission", true);
        }

        this.mNativePtrs.add(Long.valueOf(arg3));
        this.nativeNotifyConnectionTypeChanged(arg3, this.mNotifier.getCurrentNetworkState().getConnectionType());
    }

    @CalledByNative private void removeObserver(long arg2) {
        ThreadUtils.assertOnUiThread();
        this.mNativePtrs.remove(Long.valueOf(arg2));
        if(this.mNativePtrs.size() == 0 && this.mNotifier != null) {
            this.mNotifier.destroy();
            this.mNotifier = null;
        }
    }
}

