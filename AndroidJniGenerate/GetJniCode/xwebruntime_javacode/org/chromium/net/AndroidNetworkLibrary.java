package org.chromium.net;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.os.Build$VERSION;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.security.NetworkSecurityPolicy;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.URLConnection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.CalledByNativeUnchecked;

public class AndroidNetworkLibrary {
    public interface NetworkCallback {
        void OnNewDns(int arg1);

        int getHostByName(String arg1, List arg2);
    }

    public class NetworkSecurityPolicyProxy {
        private static NetworkSecurityPolicyProxy sInstance;

        static {
            NetworkSecurityPolicyProxy.sInstance = new NetworkSecurityPolicyProxy();
        }

        public NetworkSecurityPolicyProxy() {
            super();
        }

        public static NetworkSecurityPolicyProxy getInstance() {
            return NetworkSecurityPolicyProxy.sInstance;
        }

        @TargetApi(value=24) public boolean isCleartextTrafficPermitted(String arg3) {
            if(Build$VERSION.SDK_INT < 24) {
                return this.isCleartextTrafficPermitted();
            }

            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(arg3);
        }

        @TargetApi(value=23) public boolean isCleartextTrafficPermitted() {
            if(Build$VERSION.SDK_INT < 23) {
                return 1;
            }

            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
        }

        @VisibleForTesting public static void setInstanceForTesting(NetworkSecurityPolicyProxy arg0) {
            NetworkSecurityPolicyProxy.sInstance = arg0;
        }
    }

    class SetFileDescriptor {
        private static final Method sFileDescriptorSetInt;

        static {
            try {
                SetFileDescriptor.sFileDescriptorSetInt = FileDescriptor.class.getMethod("setInt$", Integer.TYPE);
                return;
            }
            catch(SecurityException v0) {
                throw new RuntimeException("Unable to get FileDescriptor.setInt$", ((Throwable)v0));
            }
        }

        private SetFileDescriptor() {
            super();
        }

        public static FileDescriptor createWithFd(int arg4) {
            try {
                FileDescriptor v0 = new FileDescriptor();
                SetFileDescriptor.sFileDescriptorSetInt.invoke(v0, Integer.valueOf(arg4));
                return v0;
            }
            catch(InvocationTargetException v4) {
                throw new RuntimeException("FileDescriptor.setInt$() failed", ((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new RuntimeException("FileDescriptor.setInt$() failed", ((Throwable)v4_1));
            }
        }
    }

    class SocketFd extends Socket {
        class SocketImplFd extends SocketImpl {
            SocketImplFd(FileDescriptor arg1) {
                super();
                this.fd = arg1;
            }

            protected void accept(SocketImpl arg2) {
                throw new RuntimeException("accept not implemented");
            }

            protected int available() {
                throw new RuntimeException("accept not implemented");
            }

            protected void bind(InetAddress arg1, int arg2) {
                throw new RuntimeException("accept not implemented");
            }

            protected void close() {
            }

            protected void connect(String arg1, int arg2) {
                throw new RuntimeException("connect not implemented");
            }

            protected void connect(InetAddress arg1, int arg2) {
                throw new RuntimeException("connect not implemented");
            }

            protected void connect(SocketAddress arg1, int arg2) {
                throw new RuntimeException("connect not implemented");
            }

            protected void create(boolean arg1) {
            }

            protected InputStream getInputStream() {
                throw new RuntimeException("getInputStream not implemented");
            }

            public Object getOption(int arg2) {
                throw new RuntimeException("getOption not implemented");
            }

            protected OutputStream getOutputStream() {
                throw new RuntimeException("getOutputStream not implemented");
            }

            protected void listen(int arg2) {
                throw new RuntimeException("listen not implemented");
            }

            protected void sendUrgentData(int arg2) {
                throw new RuntimeException("sendUrgentData not implemented");
            }

            public void setOption(int arg1, Object arg2) {
                throw new RuntimeException("setOption not implemented");
            }
        }

        SocketFd(FileDescriptor arg2) throws IOException {
            super(new SocketImplFd(arg2));
        }
    }

    private static final String TAG = "AndroidNetworkLibrary";
    private static NetworkCallback sNetworkCallback;

    static {
    }

    public AndroidNetworkLibrary() {
        super();
    }

    @CalledByNativeUnchecked public static void addTestRootCertificate(byte[] arg0) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        X509Util.addTestRootCertificate(arg0);
    }

    @CalledByNativeUnchecked public static void clearTestRootCertificates() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        X509Util.clearTestRootCertificates();
    }

    @TargetApi(value=23) @CalledByNative private static byte[][] getDnsServers() {
        Object v0 = ContextUtils.getApplicationContext().getSystemService("connectivity");
        int v1 = 0;
        if(v0 == null) {
            return new byte[0][0];
        }

        Network v2 = ((ConnectivityManager)v0).getActiveNetwork();
        if(v2 == null) {
            return new byte[0][0];
        }

        LinkProperties v0_1 = ((ConnectivityManager)v0).getLinkProperties(v2);
        if(v0_1 == null) {
            return new byte[0][0];
        }

        List v0_2 = v0_1.getDnsServers();
        byte[][] v2_1 = new byte[v0_2.size()][];
        while(v1 < v0_2.size()) {
            v2_1[v1] = v0_2.get(v1).getAddress();
            ++v1;
        }

        return v2_1;
    }

    @TargetApi(value=23) @CalledByNative private static boolean getIsCaptivePortal() {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT < 23) {
            return 0;
        }

        Object v0 = ContextUtils.getApplicationContext().getSystemService("connectivity");
        if(v0 == null) {
            return 0;
        }

        Network v2 = ((ConnectivityManager)v0).getActiveNetwork();
        if(v2 == null) {
            return 0;
        }

        NetworkCapabilities v0_1 = ((ConnectivityManager)v0).getNetworkCapabilities(v2);
        if(v0_1 != null && (v0_1.hasCapability(17))) {
            v1 = true;
        }

        return v1;
    }

    @CalledByNative private static boolean getIsRoaming() {
        NetworkInfo v0 = ContextUtils.getApplicationContext().getSystemService("connectivity").getActiveNetworkInfo();
        if(v0 == null) {
            return 0;
        }

        return v0.isRoaming();
    }

    @CalledByNative public static String getMimeTypeFromExtension(String arg2) {
        StringBuilder v0 = new StringBuilder();
        v0.append("foo.");
        v0.append(arg2);
        return URLConnection.guessContentTypeFromName(v0.toString());
    }

    @CalledByNative private static String getNetworkCountryIso() {
        Object v0 = ContextUtils.getApplicationContext().getSystemService("phone");
        if(v0 == null) {
            return "";
        }

        return ((TelephonyManager)v0).getNetworkCountryIso();
    }

    @CalledByNative private static String getNetworkOperator() {
        Object v0 = ContextUtils.getApplicationContext().getSystemService("phone");
        if(v0 == null) {
            return "";
        }

        return ((TelephonyManager)v0).getNetworkOperator();
    }

    @CalledByNative private static String getSimOperator() {
        Object v0 = ContextUtils.getApplicationContext().getSystemService("phone");
        if(v0 == null) {
            return "";
        }

        return ((TelephonyManager)v0).getSimOperator();
    }

    @CalledByNative public static String getWifiSSID() {
        Intent v0 = ContextUtils.getApplicationContext().registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        if(v0 != null) {
            Parcelable v0_1 = v0.getParcelableExtra("wifiInfo");
            if(v0_1 != null) {
                String v0_2 = ((WifiInfo)v0_1).getSSID();
                if(v0_2 != null && !v0_2.equals("<unknown ssid>")) {
                    return v0_2;
                }
            }
        }

        return "";
    }

    @CalledByNative private static byte[][] gethostbyname(String arg5) {
        byte[][] v5_1;
        try {
            if(AndroidNetworkLibrary.sNetworkCallback == null) {
                goto label_28;
            }

            ArrayList v1 = new ArrayList();
            int v5 = AndroidNetworkLibrary.sNetworkCallback.getHostByName(arg5, ((List)v1));
            AndroidNetworkLibrary.sNetworkCallback.OnNewDns(v5);
            Log.i("AndroidNetworkLibrary", "gethostbyname type: " + v5);
            v5_1 = new byte[((List)v1).size()][];
            int v2;
            for(v2 = 0; v2 < ((List)v1).size(); ++v2) {
                v5_1[v2] = ((List)v1).get(v2).getBytes();
            }
        }
        catch(Exception ) {
            goto label_28;
        }

        return v5_1;
    label_28:
        return new byte[0][];
    }

    @CalledByNative public static boolean haveOnlyLoopbackAddresses() {
        Enumeration v1_1;
        try {
            v1_1 = NetworkInterface.getNetworkInterfaces();
            if(v1_1 != null) {
                goto label_4;
            }
        }
        catch(Exception v1) {
            Log.w("AndroidNetworkLibrary", "could not get network interfaces: " + v1);
            return 0;
        }

        return 0;
        do {
        label_4:
            if(!v1_1.hasMoreElements()) {
                return 1;
            }

            Object v2 = v1_1.nextElement();
            try {
                if(!((NetworkInterface)v2).isUp()) {
                    continue;
                }

                if(((NetworkInterface)v2).isLoopback()) {
                    continue;
                }

                return 0;
            }
            catch(SocketException ) {
                continue;
            }
        }
        while(true);

        return 0;
    }

    @CalledByNative private static boolean isCleartextPermitted(String arg1) {
        try {
            return NetworkSecurityPolicyProxy.getInstance().isCleartextTrafficPermitted(arg1);
        }
        catch(IllegalArgumentException ) {
            return NetworkSecurityPolicyProxy.getInstance().isCleartextTrafficPermitted();
        }
    }

    public static void setNetworkCallback(NetworkCallback arg0) {
        AndroidNetworkLibrary.sNetworkCallback = arg0;
    }

    @CalledByNative private static void tagSocket(int arg4, int arg5, int arg6) throws IOException {
        FileDescriptor v4;
        ParcelFileDescriptor v2;
        int v0 = TrafficStats.getThreadStatsTag();
        if(arg6 != v0) {
            TrafficStats.setThreadStatsTag(arg6);
        }

        int v1 = -1;
        if(arg5 != v1) {
            ThreadStatsUid.set(arg5);
        }

        if(Build$VERSION.SDK_INT < 23) {
            v2 = null;
            v4 = SetFileDescriptor.createWithFd(arg4);
        }
        else {
            v2 = ParcelFileDescriptor.adoptFd(arg4);
            v4 = v2.getFileDescriptor();
        }

        SocketFd v3 = new SocketFd(v4);
        TrafficStats.tagSocket(((Socket)v3));
        ((Socket)v3).close();
        if(v2 != null) {
            v2.detachFd();
        }

        if(arg6 != v0) {
            TrafficStats.setThreadStatsTag(v0);
        }

        if(arg5 != v1) {
            ThreadStatsUid.clear();
        }
    }

    @CalledByNative public static AndroidCertVerifyResult verifyServerCertificates(byte[][] arg1, String arg2, String arg3) {
        int v0 = -1;
        try {
            return X509Util.verifyServerCertificates(arg1, arg2, arg3);
        }
        catch(IllegalArgumentException ) {
            return new AndroidCertVerifyResult(v0);
        }
        catch(NoSuchAlgorithmException ) {
            return new AndroidCertVerifyResult(v0);
        }
        catch(KeyStoreException ) {
            return new AndroidCertVerifyResult(v0);
        }
    }
}

