package org.chromium.net;

import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="net::android") public class AndroidKeyStore {
    private static final String TAG = "AndroidKeyStore";

    public AndroidKeyStore() {
        super();
    }

    @CalledByNative private static Object getOpenSSLEngineForPrivateKey(PrivateKey arg6) {
        Method v3_1;
        Object v0 = null;
        try {
            Class v2 = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLEngine");
        }
        catch(Exception v6) {
            Log.e("AndroidKeyStore", "Cannot find system OpenSSLEngine class: " + v6, new Object[0]);
            return v0;
        }

        Object v6_1 = AndroidKeyStore.getOpenSSLKeyForPrivateKey(arg6);
        if(v6_1 == null) {
            return v0;
        }

        try {
            v3_1 = v6_1.getClass().getDeclaredMethod("getEngine");
            goto label_12;
        }
        catch(Exception v6) {
            try {
                Log.e("AndroidKeyStore", "No getEngine() method on OpenSSLKey member:" + v6, new Object[0]);
                return v0;
            label_12:
                v3_1.setAccessible(true);
            }
            catch(Exception v6) {
                goto label_54;
            }
        }

        try {
            v6_1 = v3_1.invoke(v6_1);
            goto label_15;
        }
        catch(Throwable v6_2) {
            try {
                v3_1.setAccessible(false);
                throw v6_2;
            label_15:
                v3_1.setAccessible(false);
                if(v6_1 == null) {
                    Log.e("AndroidKeyStore", "getEngine() returned null", new Object[0]);
                }

                if(!v2.isInstance(v6_1)) {
                    if(v6_1 != null) {
                        Log.e("AndroidKeyStore", "Engine is not an OpenSSLEngine instance, its class name is:" + v6_1.getClass().getCanonicalName(), new Object[0]);
                    }

                    return v0;
                }

                return v6_1;
            }
            catch(Exception v6) {
            label_54:
                Log.e("AndroidKeyStore", "Exception while trying to retrieve OpenSSLEngine object: " + v6, new Object[0]);
                return v0;
            }
        }
    }

    @CalledByNative private static long getOpenSSLHandleForPrivateKey(PrivateKey arg7) {
        long v4_1;
        Method v3;
        Object v7 = AndroidKeyStore.getOpenSSLKeyForPrivateKey(arg7);
        long v0 = 0;
        if(v7 == null) {
            return v0;
        }

        try {
            v3 = v7.getClass().getDeclaredMethod("getPkeyContext");
            goto label_10;
        }
        catch(Exception v7_1) {
            try {
                Log.e("AndroidKeyStore", "No getPkeyContext() method on OpenSSLKey member:" + v7_1, new Object[0]);
                return v0;
            label_10:
                v3.setAccessible(true);
            }
            catch(Exception v7_1) {
                goto label_38;
            }
        }

        try {
            v4_1 = v3.invoke(v7).longValue();
            goto label_14;
        }
        catch(Throwable v7_2) {
            try {
                v3.setAccessible(false);
                throw v7_2;
            label_14:
                v3.setAccessible(false);
                if(v4_1 == v0) {
                    Log.e("AndroidKeyStore", "getPkeyContext() returned null", new Object[0]);
                }

                return v4_1;
            }
            catch(Exception v7_1) {
            label_38:
                Log.e("AndroidKeyStore", "Exception while trying to retrieve system EVP_PKEY handle: " + v7_1, new Object[0]);
                return v0;
            }
        }
    }

    private static Object getOpenSSLKeyForPrivateKey(PrivateKey arg5) {
        Object v5_2;
        Method v2_1;
        Class v2;
        Object v0 = null;
        if(arg5 == null) {
            Log.e("AndroidKeyStore", "privateKey == null", new Object[0]);
            return v0;
        }

        if(!(arg5 instanceof RSAPrivateKey)) {
            Log.e("AndroidKeyStore", "does not implement RSAPrivateKey", new Object[0]);
            return v0;
        }

        try {
            v2 = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLRSAPrivateKey");
        }
        catch(Exception v5) {
            Log.e("AndroidKeyStore", "Cannot find system OpenSSLRSAPrivateKey class: " + v5, new Object[0]);
            return v0;
        }

        if(!v2.isInstance(arg5)) {
            Log.e("AndroidKeyStore", "Private key is not an OpenSSLRSAPrivateKey instance, its class name is:" + arg5.getClass().getCanonicalName(), new Object[0]);
            return v0;
        }

        try {
            v2_1 = v2.getDeclaredMethod("getOpenSSLKey");
            v2_1.setAccessible(true);
        }
        catch(Exception v5) {
            goto label_52;
        }

        try {
            v5_2 = v2_1.invoke(arg5);
            goto label_38;
        }
        catch(Throwable v5_1) {
            try {
                v2_1.setAccessible(false);
                throw v5_1;
            label_38:
                v2_1.setAccessible(false);
                if(v5_2 == null) {
                    Log.e("AndroidKeyStore", "getOpenSSLKey() returned null", new Object[0]);
                    return v0;
                }

                return v5_2;
            }
            catch(Exception v5) {
            label_52:
                Log.e("AndroidKeyStore", "Exception while trying to retrieve system EVP_PKEY handle: " + v5, new Object[0]);
                return v0;
            }
        }
    }

    @CalledByNative private static byte[] signWithPrivateKey(PrivateKey arg5, String arg6, byte[] arg7) {
        Signature v2;
        byte[] v0 = null;
        try {
            v2 = Signature.getInstance(arg6);
        }
        catch(NoSuchAlgorithmException v5) {
            Log.e("AndroidKeyStore", "Signature algorithm " + arg6 + " not supported: " + v5, new Object[0]);
            return v0;
        }

        try {
            v2.initSign(arg5);
            v2.update(arg7);
            return v2.sign();
        }
        catch(Exception v7) {
            Log.e("AndroidKeyStore", "Exception while signing message with " + arg6 + " and " + arg5.getAlgorithm() + " private key (" + arg5.getClass().getName() + "): " + v7, new Object[0]);
            return v0;
        }
    }
}

