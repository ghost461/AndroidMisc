package org.chromium.net;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager$NetworkCallback;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo$DetailedState;
import android.net.NetworkInfo;
import android.net.NetworkRequest$Builder;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import javax.annotation.concurrent.GuardedBy;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BuildConfig;
import org.chromium.base.ContextUtils;
import org.chromium.base.VisibleForTesting;

@SuppressLint(value={"NewApi"}) public class NetworkChangeNotifierAutoDetect extends BroadcastReceiver {
    class ConnectivityManagerDelegate {
        private final ConnectivityManager mConnectivityManager;

        static {
        }

        ConnectivityManagerDelegate(Context arg2) {
            super();
            this.mConnectivityManager = arg2.getSystemService("connectivity");
        }

        ConnectivityManagerDelegate() {
            super();
            this.mConnectivityManager = null;
        }

        @TargetApi(value=21) @VisibleForTesting protected Network[] getAllNetworksUnfiltered() {
            Network[] v0 = this.mConnectivityManager.getAllNetworks();
            if(v0 == null) {
                v0 = new Network[0];
            }

            return v0;
        }

        @TargetApi(value=21) int getConnectionType(Network arg3) {
            NetworkInfo v3 = this.getNetworkInfo(arg3);
            if(v3 != null && v3.getType() == 17) {
                v3 = this.mConnectivityManager.getActiveNetworkInfo();
            }

            if(v3 != null && (v3.isConnected())) {
                return NetworkChangeNotifierAutoDetect.convertToConnectionType(v3.getType(), v3.getSubtype());
            }

            return 6;
        }

        @TargetApi(value=21) Network getDefaultNetwork() {
            Network v0;
            Network v1 = null;
            if(Build$VERSION.SDK_INT >= 23) {
                v0 = this.mConnectivityManager.getActiveNetwork();
                if(v0 != null) {
                    return v0;
                }
            }
            else {
                v0 = v1;
            }

            NetworkInfo v2 = this.mConnectivityManager.getActiveNetworkInfo();
            if(v2 == null) {
                return v1;
            }

            Network[] v1_1 = NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(this, v1);
            int v3 = v1_1.length;
            int v4;
            for(v4 = 0; v4 < v3; ++v4) {
                Network v5 = v1_1[v4];
                NetworkInfo v6 = this.getNetworkInfo(v5);
                if(v6 != null && (v6.getType() == v2.getType() || v6.getType() == 17)) {
                    v0 = v5;
                }
            }

            return v0;
        }

        @TargetApi(value=21) @VisibleForTesting protected NetworkCapabilities getNetworkCapabilities(Network arg2) {
            return this.mConnectivityManager.getNetworkCapabilities(arg2);
        }

        private NetworkInfo getNetworkInfo(Network arg2) {
            try {
                return this.mConnectivityManager.getNetworkInfo(arg2);
            }
            catch(NullPointerException ) {
                try {
                    return this.mConnectivityManager.getNetworkInfo(arg2);
                }
                catch(NullPointerException ) {
                    return null;
                }
            }
        }

        NetworkState getNetworkState(WifiManagerDelegate arg7) {
            NetworkInfo v2;
            Network v0;
            String v1 = null;
            if(Build$VERSION.SDK_INT >= 23) {
                v0 = this.getDefaultNetwork();
                v2 = this.mConnectivityManager.getNetworkInfo(v0);
            }
            else {
                v2 = this.mConnectivityManager.getActiveNetworkInfo();
                v0 = ((Network)v1);
            }

            v2 = this.processActiveNetworkInfo(v2);
            if(v2 == null) {
                return new NetworkState(false, -1, -1, v1);
            }

            if(v0 != null) {
                return new NetworkState(true, v2.getType(), v2.getSubtype(), String.valueOf(NetworkChangeNotifierAutoDetect.networkToNetId(v0)));
            }

            if(v2.getType() == 1) {
                if(v2.getExtraInfo() != null && !"".equals(v2.getExtraInfo())) {
                    return new NetworkState(true, v2.getType(), v2.getSubtype(), v2.getExtraInfo());
                }

                return new NetworkState(true, v2.getType(), v2.getSubtype(), arg7.getWifiSsid());
            }

            return new NetworkState(true, v2.getType(), v2.getSubtype(), v1);
        }

        @TargetApi(value=21) private NetworkInfo processActiveNetworkInfo(NetworkInfo arg4) {
            NetworkInfo v0 = null;
            if(arg4 == null) {
                return v0;
            }

            if(arg4.isConnected()) {
                return arg4;
            }

            if(Build$VERSION.SDK_INT < 21) {
                return v0;
            }

            if(arg4.getDetailedState() != NetworkInfo$DetailedState.BLOCKED) {
                return v0;
            }

            if(ApplicationStatus.getStateForApplication() != 1) {
                return v0;
            }

            return arg4;
        }

        @TargetApi(value=21) void registerNetworkCallback(NetworkRequest arg2, ConnectivityManager$NetworkCallback arg3) {
            this.mConnectivityManager.registerNetworkCallback(arg2, arg3);
        }

        @TargetApi(value=21) void unregisterNetworkCallback(ConnectivityManager$NetworkCallback arg2) {
            this.mConnectivityManager.unregisterNetworkCallback(arg2);
        }

        @TargetApi(value=21) @VisibleForTesting protected boolean vpnAccessible(Network arg2) {
            Socket v0 = new Socket();
            try {
                arg2.bindSocket(v0);
            }
            catch(Throwable v2) {
                try {
                    v0.close();
                    goto label_8;
                }
                catch(IOException ) {
                label_8:
                    throw v2;
                }
            }
            catch(IOException ) {
                try {
                    v0.close();
                    return 0;
                }
                catch(IOException ) {
                    return 0;
                }
            }

            try {
                v0.close();
                return 1;
            }
            catch(IOException ) {
                return 1;
            }
        }
    }

    @TargetApi(value=21) class MyNetworkCallback extends ConnectivityManager$NetworkCallback {
        private Network mVpnInPlace;

        static {
        }

        MyNetworkCallback(NetworkChangeNotifierAutoDetect arg1, org.chromium.net.NetworkChangeNotifierAutoDetect$1 arg2) {
            this(arg1);
        }

        private MyNetworkCallback(NetworkChangeNotifierAutoDetect arg1) {
            NetworkChangeNotifierAutoDetect.this = arg1;
            super();
        }

        private boolean ignoreConnectedInaccessibleVpn(Network arg2, NetworkCapabilities arg3) {
            boolean v2;
            if(arg3 == null) {
                arg3 = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(arg2);
            }

            if(arg3 != null) {
                if((arg3.hasTransport(4)) && !NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.vpnAccessible(arg2)) {
                    goto label_15;
                }

                v2 = false;
            }
            else {
            label_15:
                v2 = true;
            }

            return v2;
        }

        private boolean ignoreConnectedNetwork(Network arg2, NetworkCapabilities arg3) {
            boolean v2 = (this.ignoreNetworkDueToVpn(arg2)) || (this.ignoreConnectedInaccessibleVpn(arg2, arg3)) ? true : false;
            return v2;
        }

        private boolean ignoreNetworkDueToVpn(Network arg2) {
            boolean v2 = this.mVpnInPlace == null || (this.mVpnInPlace.equals(arg2)) ? false : true;
            return v2;
        }

        void initializeVpnInPlace() {
            Network[] v0 = NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, null);
            this.mVpnInPlace = null;
            if(v0.length == 1) {
                NetworkCapabilities v1 = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(v0[0]);
                if(v1 != null && (v1.hasTransport(4))) {
                    this.mVpnInPlace = v0[0];
                }
            }
        }

        public void onAvailable(Network arg9) {
            NetworkCapabilities v0 = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(arg9);
            if(this.ignoreConnectedNetwork(arg9, v0)) {
                return;
            }

            boolean v7 = v0.hasTransport(4);
            if(v7) {
                this.mVpnInPlace = arg9;
            }

            NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable(NetworkChangeNotifierAutoDetect.networkToNetId(arg9), NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getConnectionType(arg9), v7) {
                public void run() {
                    this.this$1.this$0.mObserver.onNetworkConnect(this.val$netId, this.val$connectionType);
                    if(this.val$makeVpnDefault) {
                        this.this$1.this$0.mObserver.onConnectionTypeChanged(this.val$connectionType);
                        this.this$1.this$0.mObserver.purgeActiveNetworkList(new long[]{this.val$netId});
                    }
                }
            });
        }

        public void onCapabilitiesChanged(Network arg4, NetworkCapabilities arg5) {
            if(this.ignoreConnectedNetwork(arg4, arg5)) {
                return;
            }

            NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable(NetworkChangeNotifierAutoDetect.networkToNetId(arg4), NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getConnectionType(arg4)) {
                public void run() {
                    this.this$1.this$0.mObserver.onNetworkConnect(this.val$netId, this.val$connectionType);
                }
            });
        }

        public void onLosing(Network arg3, int arg4) {
            if(this.ignoreConnectedNetwork(arg3, null)) {
                return;
            }

            NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable(NetworkChangeNotifierAutoDetect.networkToNetId(arg3)) {
                public void run() {
                    this.this$1.this$0.mObserver.onNetworkSoonToDisconnect(this.val$netId);
                }
            });
        }

        public void onLost(Network arg4) {
            if(this.ignoreNetworkDueToVpn(arg4)) {
                return;
            }

            NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable(arg4) {
                public void run() {
                    this.this$1.this$0.mObserver.onNetworkDisconnect(NetworkChangeNotifierAutoDetect.networkToNetId(this.val$network));
                }
            });
            if(this.mVpnInPlace != null) {
                this.mVpnInPlace = null;
                Network[] v4 = NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, arg4);
                int v0 = v4.length;
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.onAvailable(v4[v1]);
                }

                NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable(NetworkChangeNotifierAutoDetect.this.getCurrentNetworkState().getConnectionType()) {
                    public void run() {
                        this.this$1.this$0.mObserver.onConnectionTypeChanged(this.val$newConnectionType);
                    }
                });
            }
        }
    }

    @SuppressLint(value={"NewApi", "ParcelCreator"}) class NetworkConnectivityIntentFilter extends IntentFilter {
        NetworkConnectivityIntentFilter() {
            super();
            this.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
    }

    public class NetworkState {
        private final boolean mConnected;
        private final String mNetworkIdentifier;
        private final int mSubtype;
        private final int mType;

        public NetworkState(boolean arg1, int arg2, int arg3, String arg4) {
            super();
            this.mConnected = arg1;
            this.mType = arg2;
            this.mSubtype = arg3;
            if(arg4 == null) {
                arg4 = "";
            }

            this.mNetworkIdentifier = arg4;
        }

        public int getConnectionSubtype() {
            if(!this.isConnected()) {
                return 1;
            }

            switch(this.getNetworkType()) {
                case 0: {
                    goto label_9;
                }
                case 1: 
                case 6: 
                case 7: 
                case 9: {
                    return 0;
                }
            }

            return 0;
        label_9:
            switch(this.getNetworkSubType()) {
                case 1: {
                    return 7;
                }
                case 2: {
                    return 8;
                }
                case 3: {
                    return 9;
                }
                case 4: {
                    return 5;
                }
                case 5: {
                    return 10;
                }
                case 6: {
                    return 11;
                }
                case 7: {
                    return 6;
                }
                case 8: {
                    return 14;
                }
                case 9: {
                    return 15;
                }
                case 10: {
                    return 12;
                }
                case 11: {
                    return 4;
                }
                case 12: {
                    return 13;
                }
                case 13: {
                    return 18;
                }
                case 14: {
                    return 16;
                }
                case 15: {
                    return 17;
                }
            }

            return 0;
        }

        public int getConnectionType() {
            if(!this.isConnected()) {
                return 6;
            }

            return NetworkChangeNotifierAutoDetect.convertToConnectionType(this.getNetworkType(), this.getNetworkSubType());
        }

        public String getNetworkIdentifier() {
            return this.mNetworkIdentifier;
        }

        public int getNetworkSubType() {
            return this.mSubtype;
        }

        public int getNetworkType() {
            return this.mType;
        }

        public boolean isConnected() {
            return this.mConnected;
        }
    }

    public interface Observer {
        void onConnectionSubtypeChanged(int arg1);

        void onConnectionTypeChanged(int arg1);

        void onNetworkConnect(long arg1, int arg2);

        void onNetworkDisconnect(long arg1);

        void onNetworkSoonToDisconnect(long arg1);

        void purgeActiveNetworkList(long[] arg1);
    }

    public abstract class RegistrationPolicy {
        private NetworkChangeNotifierAutoDetect mNotifier;

        static {
        }

        public RegistrationPolicy() {
            super();
        }

        protected abstract void destroy();

        protected void init(NetworkChangeNotifierAutoDetect arg1) {
            this.mNotifier = arg1;
        }

        protected final void register() {
            this.mNotifier.register();
        }

        protected final void unregister() {
            this.mNotifier.unregister();
        }
    }

    class WifiManagerDelegate {
        private final Context mContext;
        @GuardedBy(value="mLock") private boolean mHasWifiPermission;
        @GuardedBy(value="mLock") private boolean mHasWifiPermissionComputed;
        private final Object mLock;
        @GuardedBy(value="mLock") private WifiManager mWifiManager;

        static {
        }

        WifiManagerDelegate(Context arg2) {
            super();
            this.mLock = new Object();
            this.mContext = arg2;
        }

        WifiManagerDelegate() {
            super();
            this.mLock = new Object();
            this.mContext = null;
        }

        @GuardedBy(value="mLock") private WifiInfo getWifiInfoLocked() {
            try {
                return this.mWifiManager.getConnectionInfo();
            }
            catch(NullPointerException ) {
                try {
                    return this.mWifiManager.getConnectionInfo();
                }
                catch(NullPointerException ) {
                    return null;
                }
            }
        }

        String getWifiSsid() {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                if(this.hasPermissionLocked()) {
                    WifiInfo v1_1 = this.getWifiInfoLocked();
                    if(v1_1 != null) {
                        __monitor_exit(v0);
                        return v1_1.getSSID();
                    }

                    __monitor_exit(v0);
                    return "";
                }

                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                try {
                label_16:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_16;
                }

                throw v1;
            }

            return AndroidNetworkLibrary.getWifiSSID();
        }

        @SuppressLint(value={"WifiManagerPotentialLeak"}) @GuardedBy(value="mLock") private boolean hasPermissionLocked() {
            WifiManager v0_2;
            if(this.mHasWifiPermissionComputed) {
                return this.mHasWifiPermission;
            }

            boolean v0 = this.mContext.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", this.mContext.getPackageName()) == 0 ? true : false;
            this.mHasWifiPermission = v0;
            if(this.mHasWifiPermission) {
                Object v0_1 = this.mContext.getSystemService("wifi");
            }
            else {
                v0_2 = null;
            }

            this.mWifiManager = v0_2;
            this.mHasWifiPermissionComputed = true;
            return this.mHasWifiPermission;
        }
    }

    private static final String TAG = "NetworkChangeNotifierAutoDetect";
    private static final int UNKNOWN_LINK_SPEED = -1;
    private ConnectivityManagerDelegate mConnectivityManagerDelegate;
    private final Handler mHandler;
    private boolean mIgnoreNextBroadcast;
    private final NetworkConnectivityIntentFilter mIntentFilter;
    private final Looper mLooper;
    private MyNetworkCallback mNetworkCallback;
    private NetworkRequest mNetworkRequest;
    private NetworkState mNetworkState;
    private final Observer mObserver;
    private boolean mRegisterNetworkCallbackFailed;
    private boolean mRegistered;
    private final RegistrationPolicy mRegistrationPolicy;
    private boolean mShouldSignalObserver;
    private WifiManagerDelegate mWifiManagerDelegate;

    static {
    }

    @TargetApi(value=21) public NetworkChangeNotifierAutoDetect(Observer arg3, RegistrationPolicy arg4) {
        super();
        this.mLooper = Looper.myLooper();
        this.mHandler = new Handler(this.mLooper);
        this.mObserver = arg3;
        this.mConnectivityManagerDelegate = new ConnectivityManagerDelegate(ContextUtils.getApplicationContext());
        if(Build$VERSION.SDK_INT < 23) {
            this.mWifiManagerDelegate = new WifiManagerDelegate(ContextUtils.getApplicationContext());
        }

        org.chromium.net.NetworkChangeNotifierAutoDetect$1 v1 = null;
        if(Build$VERSION.SDK_INT >= 21) {
            this.mNetworkCallback = new MyNetworkCallback(this, v1);
            this.mNetworkRequest = new NetworkRequest$Builder().addCapability(12).removeCapability(15).build();
        }
        else {
            this.mNetworkCallback = ((MyNetworkCallback)v1);
            this.mNetworkRequest = ((NetworkRequest)v1);
        }

        this.mNetworkState = this.getCurrentNetworkState();
        this.mIntentFilter = new NetworkConnectivityIntentFilter();
        this.mIgnoreNextBroadcast = false;
        this.mShouldSignalObserver = false;
        this.mRegistrationPolicy = arg4;
        this.mRegistrationPolicy.init(this);
        this.mShouldSignalObserver = true;
    }

    static int access$000(int arg0, int arg1) {
        return NetworkChangeNotifierAutoDetect.convertToConnectionType(arg0, arg1);
    }

    static Network[] access$100(ConnectivityManagerDelegate arg0, Network arg1) {
        return NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(arg0, arg1);
    }

    static ConnectivityManagerDelegate access$200(NetworkChangeNotifierAutoDetect arg0) {
        return arg0.mConnectivityManagerDelegate;
    }

    static Observer access$300(NetworkChangeNotifierAutoDetect arg0) {
        return arg0.mObserver;
    }

    static void access$400(NetworkChangeNotifierAutoDetect arg0, Runnable arg1) {
        arg0.runOnThread(arg1);
    }

    static boolean access$600(NetworkChangeNotifierAutoDetect arg0) {
        return arg0.mRegistered;
    }

    static boolean access$700(NetworkChangeNotifierAutoDetect arg0) {
        return arg0.mIgnoreNextBroadcast;
    }

    static boolean access$702(NetworkChangeNotifierAutoDetect arg0, boolean arg1) {
        arg0.mIgnoreNextBroadcast = arg1;
        return arg1;
    }

    static void access$800(NetworkChangeNotifierAutoDetect arg0) {
        arg0.connectionTypeChanged();
    }

    private void assertOnThread() {
        if((BuildConfig.DCHECK_IS_ON) && !this.onThread()) {
            throw new IllegalStateException("Must be called on NetworkChangeNotifierAutoDetect thread.");
        }
    }

    private void connectionTypeChanged() {
        NetworkState v0 = this.getCurrentNetworkState();
        if(v0.getConnectionType() != this.mNetworkState.getConnectionType() || !v0.getNetworkIdentifier().equals(this.mNetworkState.getNetworkIdentifier())) {
            this.mObserver.onConnectionTypeChanged(v0.getConnectionType());
        }

        if(v0.getConnectionType() != this.mNetworkState.getConnectionType() || v0.getConnectionSubtype() != this.mNetworkState.getConnectionSubtype()) {
            this.mObserver.onConnectionSubtypeChanged(v0.getConnectionSubtype());
        }

        this.mNetworkState = v0;
    }

    private static int convertToConnectionType(int arg2, int arg3) {
        int v0 = 5;
        switch(arg2) {
            case 0: {
                goto label_11;
            }
            case 1: {
                return 2;
            }
            case 6: {
                return v0;
            }
            case 7: {
                return 7;
            }
            case 9: {
                return 1;
            }
        }

        return 0;
    label_11:
        switch(arg3) {
            case 1: 
            case 2: 
            case 4: 
            case 7: 
            case 11: {
                return 3;
            }
            case 13: {
                return v0;
            }
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 12: 
            case 14: 
            case 15: {
                return 4;
            }
        }

        return 0;
    }

    public void destroy() {
        this.assertOnThread();
        this.mRegistrationPolicy.destroy();
        this.unregister();
    }

    @TargetApi(value=21) private static Network[] getAllNetworksFiltered(ConnectivityManagerDelegate arg8, Network arg9) {
        Network[] v0 = arg8.getAllNetworksUnfiltered();
        int v1 = v0.length;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v1) {
            Network v5 = v0[v3];
            if(v5.equals(arg9)) {
            }
            else {
                NetworkCapabilities v6 = arg8.getNetworkCapabilities(v5);
                if(v6 != null) {
                    if(!v6.hasCapability(12)) {
                    }
                    else if(!v6.hasTransport(4)) {
                        v0[v4] = v5;
                        ++v4;
                    }
                    else if(arg8.vpnAccessible(v5)) {
                        return new Network[]{v5};
                    }
                }
            }

            ++v3;
        }

        return Arrays.copyOf(((Object[])v0), v4);
    }

    public NetworkState getCurrentNetworkState() {
        return this.mConnectivityManagerDelegate.getNetworkState(this.mWifiManagerDelegate);
    }

    public long getDefaultNetId() {
        long v1 = -1;
        if(Build$VERSION.SDK_INT < 21) {
            return v1;
        }

        Network v0 = this.mConnectivityManagerDelegate.getDefaultNetwork();
        if(v0 == null) {
        }
        else {
            v1 = NetworkChangeNotifierAutoDetect.networkToNetId(v0);
        }

        return v1;
    }

    public long[] getNetworksAndTypes() {
        int v1 = 0;
        if(Build$VERSION.SDK_INT < 21) {
            return new long[0];
        }

        Network[] v0 = NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
        long[] v2 = new long[v0.length * 2];
        int v3 = v0.length;
        int v4 = 0;
        while(v1 < v3) {
            Network v5 = v0[v1];
            int v6 = v4 + 1;
            v2[v4] = NetworkChangeNotifierAutoDetect.networkToNetId(v5);
            v4 = v6 + 1;
            v2[v6] = ((long)this.mConnectivityManagerDelegate.getConnectionType(v5));
            ++v1;
        }

        return v2;
    }

    @VisibleForTesting RegistrationPolicy getRegistrationPolicy() {
        return this.mRegistrationPolicy;
    }

    @VisibleForTesting boolean isReceiverRegisteredForTesting() {
        return this.mRegistered;
    }

    @TargetApi(value=21) @VisibleForTesting static long networkToNetId(Network arg2) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getNetworkHandle();
        }

        return ((long)Integer.parseInt(arg2.toString()));
    }

    public void onReceive(Context arg1, Intent arg2) {
        this.runOnThread(new Runnable() {
            public void run() {
                if(!NetworkChangeNotifierAutoDetect.this.mRegistered) {
                    return;
                }

                if(NetworkChangeNotifierAutoDetect.this.mIgnoreNextBroadcast) {
                    NetworkChangeNotifierAutoDetect.this.mIgnoreNextBroadcast = false;
                    return;
                }

                NetworkChangeNotifierAutoDetect.this.connectionTypeChanged();
            }
        });
    }

    private boolean onThread() {
        boolean v0 = this.mLooper == Looper.myLooper() ? true : false;
        return v0;
    }

    public void register() {
        this.assertOnThread();
        if(this.mRegistered) {
            return;
        }

        if(this.mShouldSignalObserver) {
            this.connectionTypeChanged();
        }

        int v1 = 0;
        boolean v0 = ContextUtils.getApplicationContext().registerReceiver(((BroadcastReceiver)this), this.mIntentFilter) != null ? true : false;
        this.mIgnoreNextBroadcast = v0;
        this.mRegistered = true;
        if(this.mNetworkCallback != null) {
            this.mNetworkCallback.initializeVpnInPlace();
            MyNetworkCallback v0_1 = null;
            try {
                this.mConnectivityManagerDelegate.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback);
            }
            catch(RuntimeException ) {
                this.mRegisterNetworkCallbackFailed = true;
                this.mNetworkCallback = v0_1;
            }

            if(this.mRegisterNetworkCallbackFailed) {
                return;
            }

            if(!this.mShouldSignalObserver) {
                return;
            }

            Network[] v0_2 = NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(this.mConnectivityManagerDelegate, ((Network)v0_1));
            long[] v2 = new long[v0_2.length];
            while(v1 < v0_2.length) {
                v2[v1] = NetworkChangeNotifierAutoDetect.networkToNetId(v0_2[v1]);
                ++v1;
            }

            this.mObserver.purgeActiveNetworkList(v2);
        }
    }

    public boolean registerNetworkCallbackFailed() {
        return this.mRegisterNetworkCallbackFailed;
    }

    private void runOnThread(Runnable arg2) {
        if(this.onThread()) {
            arg2.run();
        }
        else {
            this.mHandler.post(arg2);
        }
    }

    void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate arg1) {
        this.mConnectivityManagerDelegate = arg1;
    }

    void setWifiManagerDelegateForTests(WifiManagerDelegate arg1) {
        this.mWifiManagerDelegate = arg1;
    }

    public void unregister() {
        this.assertOnThread();
        if(!this.mRegistered) {
            return;
        }

        ContextUtils.getApplicationContext().unregisterReceiver(((BroadcastReceiver)this));
        this.mRegistered = false;
        if(this.mNetworkCallback != null) {
            this.mConnectivityManagerDelegate.unregisterNetworkCallback(this.mNetworkCallback);
        }
    }
}

