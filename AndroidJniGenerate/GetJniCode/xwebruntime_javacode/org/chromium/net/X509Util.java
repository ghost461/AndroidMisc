package org.chromium.net;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.X509TrustManagerExtensions;
import android.os.Build$VERSION;
import android.util.Log;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore$LoadStoreParameter;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordHistogram;

@JNINamespace(value="net") public class X509Util {
    class org.chromium.net.X509Util$1 {
    }

    final class TrustStorageListener extends BroadcastReceiver {
        TrustStorageListener(org.chromium.net.X509Util$1 arg1) {
            this();
        }

        private TrustStorageListener() {
            super();
        }

        public void onReceive(Context arg4, Intent arg5) {
            boolean v0 = true;
            if(Build$VERSION.SDK_INT < 26) {
                v0 = "android.security.STORAGE_CHANGED".equals(arg5.getAction());
            }
            else if(!"android.security.action.KEYCHAIN_CHANGED".equals(arg5.getAction())) {
                if("android.security.action.TRUST_STORE_CHANGED".equals(arg5.getAction())) {
                }
                else {
                    if(("android.security.action.KEY_ACCESS_CHANGED".equals(arg5.getAction())) && !arg5.getBooleanExtra("android.security.extra.KEY_ACCESSIBLE", false)) {
                        goto label_27;
                    }

                    v0 = false;
                }
            }

        label_27:
            if(v0) {
                try {
                    X509Util.reloadDefaultTrustManager();
                }
                catch(NoSuchAlgorithmException v4) {
                    Log.e("X509Util", "Unable to reload the default TrustManager", ((Throwable)v4));
                }
                catch(KeyStoreException v4_1) {
                    Log.e("X509Util", "Unable to reload the default TrustManager", ((Throwable)v4_1));
                }
                catch(CertificateException v4_2) {
                    Log.e("X509Util", "Unable to reload the default TrustManager", ((Throwable)v4_2));
                }
            }
        }
    }

    final class X509TrustManagerIceCreamSandwich implements X509TrustManagerImplementation {
        private final X509TrustManager mTrustManager;

        public X509TrustManagerIceCreamSandwich(X509TrustManager arg1) {
            super();
            this.mTrustManager = arg1;
        }

        public List checkServerTrusted(X509Certificate[] arg1, String arg2, String arg3) throws CertificateException {
            this.mTrustManager.checkServerTrusted(arg1, arg2);
            return Collections.emptyList();
        }
    }

    interface X509TrustManagerImplementation {
        List checkServerTrusted(X509Certificate[] arg1, String arg2, String arg3) throws CertificateException;
    }

    final class X509TrustManagerJellyBean implements X509TrustManagerImplementation {
        private final X509TrustManagerExtensions mTrustManagerExtensions;

        @SuppressLint(value={"NewApi"}) public X509TrustManagerJellyBean(X509TrustManager arg2) {
            super();
            this.mTrustManagerExtensions = new X509TrustManagerExtensions(arg2);
        }

        @SuppressLint(value={"NewApi"}) public List checkServerTrusted(X509Certificate[] arg2, String arg3, String arg4) throws CertificateException {
            return this.mTrustManagerExtensions.checkServerTrusted(arg2, arg3, arg4);
        }
    }

    private static final char[] HEX_DIGITS = null;
    private static final String OID_ANY_EKU = "2.5.29.37.0";
    private static final String OID_SERVER_GATED_MICROSOFT = "1.3.6.1.4.1.311.10.3.3";
    private static final String OID_SERVER_GATED_NETSCAPE = "2.16.840.1.113730.4.1";
    private static final String OID_TLS_SERVER_AUTH = "1.3.6.1.5.5.7.3.1";
    private static final String TAG = "X509Util";
    private static CertificateFactory sCertificateFactory;
    private static X509TrustManagerImplementation sDefaultTrustManager;
    private static boolean sDisableNativeCodeForTest;
    private static boolean sLoadedSystemKeyStore;
    private static final Object sLock;
    private static File sSystemCertificateDirectory;
    private static KeyStore sSystemKeyStore;
    private static Set sSystemTrustAnchorCache;
    private static KeyStore sTestKeyStore;
    private static X509TrustManagerImplementation sTestTrustManager;
    private static TrustStorageListener sTrustStorageListener;

    static {
        X509Util.sLock = new Object();
        X509Util.HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public X509Util() {
        super();
    }

    static void access$000() throws KeyStoreException, NoSuchAlgorithmException, CertificateException {
        X509Util.reloadDefaultTrustManager();
    }

    public static void addTestRootCertificate(byte[] arg4) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        X509Util.ensureInitialized();
        X509Certificate v4 = X509Util.createCertificateFromBytes(arg4);
        Object v0 = X509Util.sLock;
        __monitor_enter(v0);
        try {
            KeyStore v1 = X509Util.sTestKeyStore;
            v1.setCertificateEntry("root_cert_" + Integer.toString(X509Util.sTestKeyStore.size()), ((Certificate)v4));
            X509Util.reloadTestTrustManager();
            __monitor_exit(v0);
            return;
        label_19:
            __monitor_exit(v0);
        }
        catch(Throwable v4_1) {
            goto label_19;
        }

        throw v4_1;
    }

    public static void clearTestRootCertificates() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        X509Util.ensureInitialized();
        Object v0 = X509Util.sLock;
        __monitor_enter(v0);
        try {
            X509Util.sTestKeyStore.load(null);
            X509Util.reloadTestTrustManager();
            goto label_10;
        }
        catch(Throwable v1) {
        }
        catch(IOException ) {
            try {
            label_10:
                __monitor_exit(v0);
                return;
            }
            catch(Throwable v1) {
            label_9:
                try {
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_9;
                }

                throw v1;
            }
        }
    }

    public static X509Certificate createCertificateFromBytes(byte[] arg2) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        X509Util.ensureInitialized();
        return X509Util.sCertificateFactory.generateCertificate(new ByteArrayInputStream(arg2));
    }

    private static X509TrustManagerImplementation createTrustManager(KeyStore arg7) throws KeyStoreException, NoSuchAlgorithmException {
        TrustManagerFactory v0 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        v0.init(arg7);
        TrustManager[] v7 = v0.getTrustManagers();
        int v0_1 = v7.length;
        int v1;
        for(v1 = 0; v1 < v0_1; ++v1) {
            TrustManager v2 = v7[v1];
            if((v2 instanceof X509TrustManager)) {
                try {
                    if(Build$VERSION.SDK_INT >= 17) {
                        return new X509TrustManagerJellyBean(v2);
                    }

                    return new X509TrustManagerIceCreamSandwich(v2);
                }
                catch(IllegalArgumentException v3) {
                    String v2_1 = v2.getClass().getName();
                    Log.e("X509Util", "Error creating trust manager (" + v2_1 + "): " + v3);
                }
            }
        }

        Log.e("X509Util", "Could not find suitable trust manager");
        return null;
    }

    private static void ensureInitialized() throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        Object v0 = X509Util.sLock;
        __monitor_enter(v0);
        try {
            X509Util.ensureInitializedLocked();
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    private static void ensureInitializedLocked() throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        if(X509Util.sCertificateFactory == null) {
            X509Util.sCertificateFactory = CertificateFactory.getInstance("X.509");
        }

        KeyStore v1 = null;
        if(X509Util.sDefaultTrustManager == null) {
            X509Util.sDefaultTrustManager = X509Util.createTrustManager(v1);
        }

        if(!X509Util.sLoadedSystemKeyStore) {
            try {
                X509Util.sSystemKeyStore = KeyStore.getInstance("AndroidCAStore");
                try {
                    X509Util.sSystemKeyStore.load(((KeyStore$LoadStoreParameter)v1));
                    goto label_17;
                }
                catch(IOException ) {
                label_17:
                    StringBuilder v2 = new StringBuilder();
                    v2.append(System.getenv("ANDROID_ROOT"));
                    v2.append("/etc/security/cacerts");
                    X509Util.sSystemCertificateDirectory = new File(v2.toString());
                    goto label_28;
                }
            }
            catch(KeyStoreException ) {
            label_28:
                if(!X509Util.sDisableNativeCodeForTest && Build$VERSION.SDK_INT >= 17) {
                    String v0 = "Net.FoundSystemTrustRootsAndroid";
                    boolean v3 = X509Util.sSystemKeyStore != null ? true : false;
                    RecordHistogram.recordBooleanHistogram(v0, v3);
                }

                X509Util.sLoadedSystemKeyStore = true;
            }
        }

        if(X509Util.sSystemTrustAnchorCache == null) {
            X509Util.sSystemTrustAnchorCache = new HashSet();
        }

        if(X509Util.sTestKeyStore == null) {
            X509Util.sTestKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                X509Util.sTestKeyStore.load(((KeyStore$LoadStoreParameter)v1));
                goto label_54;
            }
            catch(IOException ) {
            label_54:
                if(X509Util.sTestTrustManager == null) {
                    X509Util.sTestTrustManager = X509Util.createTrustManager(X509Util.sTestKeyStore);
                }

                if(!X509Util.sDisableNativeCodeForTest && X509Util.sTrustStorageListener == null) {
                    X509Util.sTrustStorageListener = new TrustStorageListener(((org.chromium.net.X509Util$1)v1));
                    IntentFilter v0_1 = new IntentFilter();
                    if(Build$VERSION.SDK_INT >= 26) {
                        v0_1.addAction("android.security.action.KEYCHAIN_CHANGED");
                        v0_1.addAction("android.security.action.KEY_ACCESS_CHANGED");
                        v0_1.addAction("android.security.action.TRUST_STORE_CHANGED");
                    }
                    else {
                        v0_1.addAction("android.security.STORAGE_CHANGED");
                    }

                    ContextUtils.getApplicationContext().registerReceiver(X509Util.sTrustStorageListener, v0_1);
                }

                return;
            }
        }

        goto label_54;
    }

    private static String hashPrincipal(X500Principal arg7) throws NoSuchAlgorithmException {
        byte[] v7 = MessageDigest.getInstance("MD5").digest(arg7.getEncoded());
        char[] v0 = new char[8];
        int v1;
        for(v1 = 0; v1 < 4; ++v1) {
            int v3 = v1 * 2;
            int v5 = 3 - v1;
            v0[v3] = X509Util.HEX_DIGITS[v7[v5] >> 4 & 15];
            v0[v3 + 1] = X509Util.HEX_DIGITS[v7[v5] & 15];
        }

        return new String(v0);
    }

    private static boolean isKnownRoot(X509Certificate arg10) throws NoSuchAlgorithmException, KeyStoreException {
        if(X509Util.sSystemKeyStore == null) {
            return 0;
        }

        Pair v0 = new Pair(arg10.getSubjectX500Principal(), arg10.getPublicKey());
        if(X509Util.sSystemTrustAnchorCache.contains(v0)) {
            return 1;
        }

        String v2 = X509Util.hashPrincipal(arg10.getSubjectX500Principal());
        int v4;
        for(v4 = 0; true; ++v4) {
            String v5_1 = v2 + '.' + v4;
            if(!new File(X509Util.sSystemCertificateDirectory, v5_1).exists()) {
                return 0;
            }

            KeyStore v6 = X509Util.sSystemKeyStore;
            StringBuilder v7 = new StringBuilder();
            v7.append("system:");
            v7.append(v5_1);
            Certificate v6_1 = v6.getCertificate(v7.toString());
            if(v6_1 == null) {
            }
            else if(!(v6_1 instanceof X509Certificate)) {
                String v6_2 = v6_1.getClass().getName();
                Log.e("X509Util", "Anchor " + v5_1 + " not an X509Certificate: " + v6_2);
            }
            else if((arg10.getSubjectX500Principal().equals(((X509Certificate)v6_1).getSubjectX500Principal())) && (arg10.getPublicKey().equals(((X509Certificate)v6_1).getPublicKey()))) {
                X509Util.sSystemTrustAnchorCache.add(v0);
                return 1;
            }
        }

        return 0;
    }

    private static native void nativeNotifyKeyChainChanged() {
    }

    private static void reloadDefaultTrustManager() throws KeyStoreException, NoSuchAlgorithmException, CertificateException {
        Object v0 = X509Util.sLock;
        __monitor_enter(v0);
        X509TrustManagerImplementation v1 = null;
        try {
            X509Util.sDefaultTrustManager = v1;
            X509Util.sSystemTrustAnchorCache = ((Set)v1);
            X509Util.ensureInitializedLocked();
            __monitor_exit(v0);
        }
        catch(Throwable v1_1) {
            try {
            label_10:
                __monitor_exit(v0);
            }
            catch(Throwable v1_1) {
                goto label_10;
            }

            throw v1_1;
        }

        X509Util.nativeNotifyKeyChainChanged();
    }

    private static void reloadTestTrustManager() throws KeyStoreException, NoSuchAlgorithmException {
        X509Util.sTestTrustManager = X509Util.createTrustManager(X509Util.sTestKeyStore);
    }

    public static void setDisableNativeCodeForTest(boolean arg0) {
        X509Util.sDisableNativeCodeForTest = arg0;
    }

    static boolean verifyKeyUsage(X509Certificate arg4) throws CertificateException {
        List v4;
        try {
            v4 = arg4.getExtendedKeyUsage();
            if(v4 != null) {
                goto label_5;
            }
        }
        catch(NullPointerException ) {
            return 0;
        }

        return 1;
    label_5:
        Iterator v4_1 = v4.iterator();
        do {
            if(!v4_1.hasNext()) {
                return 0;
            }

            Object v2 = v4_1.next();
            if(((String)v2).equals("1.3.6.1.5.5.7.3.1")) {
                return 1;
            }

            if(((String)v2).equals("2.5.29.37.0")) {
                return 1;
            }
        }
        while(!((String)v2).equals("2.16.840.1.113730.4.1") && !((String)v2).equals("1.3.6.1.4.1.311.10.3.3"));

        return 1;
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] arg8, String arg9, String arg10) throws KeyStoreException, NoSuchAlgorithmException {
        List v8_2;
        int v4;
        if(arg8 != null && arg8.length != 0) {
            if(arg8[0] == null) {
            }
            else {
                int v1 = -1;
                try {
                    X509Util.ensureInitialized();
                }
                catch(CertificateException ) {
                    return new AndroidCertVerifyResult(v1);
                }

                ArrayList v2 = new ArrayList();
                try {
                    ((List)v2).add(X509Util.createCertificateFromBytes(arg8[0]));
                    v4 = 1;
                }
                catch(CertificateException ) {
                    return new AndroidCertVerifyResult(-5);
                }

                while(v4 < arg8.length) {
                    try {
                        ((List)v2).add(X509Util.createCertificateFromBytes(arg8[v4]));
                    }
                    catch(CertificateException ) {
                        Log.w("X509Util", "intermediate " + v4 + " failed parsing");
                    }

                    ++v4;
                }

                Object[] v8 = ((List)v2).toArray(new X509Certificate[((List)v2).size()]);
                try {
                    v8[0].checkValidity();
                    if(!X509Util.verifyKeyUsage(v8[0])) {
                        return new AndroidCertVerifyResult(-6);
                    }
                    else {
                        goto label_46;
                    }

                    goto label_108;
                }
                catch(CertificateException ) {
                    return new AndroidCertVerifyResult(v1);
                }
                catch(CertificateNotYetValidException ) {
                    return new AndroidCertVerifyResult(-4);
                }
                catch(CertificateExpiredException ) {
                    return new AndroidCertVerifyResult(-3);
                }

            label_46:
                Object v2_1 = X509Util.sLock;
                __monitor_enter(v2_1);
                try {
                    if(X509Util.sDefaultTrustManager == null) {
                        __monitor_exit(v2_1);
                        return new AndroidCertVerifyResult(v1);
                    }
                    else {
                        try {
                            v8_2 = X509Util.sDefaultTrustManager.checkServerTrusted(((X509Certificate[])v8), arg9, arg10);
                            goto label_61;
                        }
                        catch(CertificateException v1_1) {
                            try {
                                v8_2 = X509Util.sTestTrustManager.checkServerTrusted(((X509Certificate[])v8_2), arg9, arg10);
                                goto label_61;
                            }
                            catch(CertificateException ) {
                                Log.i("X509Util", "Failed to validate the certificate chain, error: " + v1_1.getMessage());
                                __monitor_exit(v2_1);
                                return new AndroidCertVerifyResult(-2);
                            }
                        }
                    }

                    goto label_108;
                }
                catch(Throwable v8_1) {
                    goto label_88;
                }

            label_61:
                boolean v9_1 = v8_2.size() > 0 ? X509Util.isKnownRoot(v8_2.get(v8_2.size() - 1)) : false;
                __monitor_exit(v2_1);
                return new AndroidCertVerifyResult(0, v9_1, v8_2);
            label_88:
                __monitor_exit(v2_1);
                throw v8_1;
            }
        }

    label_108:
        StringBuilder v10 = new StringBuilder();
        v10.append("Expected non-null and non-empty certificate chain passed as |certChain|. |certChain|=");
        v10.append(Arrays.deepToString(((Object[])arg8)));
        throw new IllegalArgumentException(v10.toString());
    }
}

