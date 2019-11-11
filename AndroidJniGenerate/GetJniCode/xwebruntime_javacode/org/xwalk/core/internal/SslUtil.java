package org.xwalk.core.internal;

import android.net.http.SslCertificate;
import android.net.http.SslError;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import org.chromium.net.X509Util;

class SslUtil {
    private static final String TAG = "SslUtil";

    static {
    }

    SslUtil() {
        super();
    }

    public static SslCertificate getCertificateFromDerBytes(byte[] arg4) {
        SslCertificate v0 = null;
        if(arg4 == null) {
            return v0;
        }

        try {
            return new SslCertificate(X509Util.createCertificateFromBytes(arg4));
        }
        catch(NoSuchAlgorithmException v4) {
            Log.w("SslUtil", "Could not read certificate: " + v4);
        }
        catch(KeyStoreException v4_1) {
            Log.w("SslUtil", "Could not read certificate: " + v4_1);
        }
        catch(CertificateException v4_2) {
            Log.w("SslUtil", "Could not read certificate: " + v4_2);
        }

        return v0;
    }

    public static boolean shouldDenyRequest(int arg2) {
        if(arg2 != -150 && arg2 != 0xFFFFFF7F) {
            switch(arg2) {
                case -213: 
                case -212: 
                case -211: {
                    return 0;
                }
                default: {
                    switch(arg2) {
                        case -208: 
                        case -207: 
                        case -206: {
                            return 0;
                        }
                        default: {
                            switch(arg2) {
                                case -203: 
                                case -202: 
                                case -201: 
                                case -200: {
                                    return 0;
                                }
                                default: {
                                    return 0;
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    public static SslError sslErrorFromNetErrorCode(int arg1, SslCertificate arg2, String arg3) {
        return new SslError(5, arg2, arg3);
    }
}

