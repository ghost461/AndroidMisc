package android.support.v4.hardware.fingerprint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager$AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager$AuthenticationResult;
import android.hardware.fingerprint.FingerprintManager$CryptoObject;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@RequiresApi(value=23) @RestrictTo(value={Scope.LIBRARY_GROUP}) public final class FingerprintManagerCompatApi23 {
    public abstract class AuthenticationCallback {
        public AuthenticationCallback() {
            super();
        }

        public void onAuthenticationError(int arg1, CharSequence arg2) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int arg1, CharSequence arg2) {
        }

        public void onAuthenticationSucceeded(AuthenticationResultInternal arg1) {
        }
    }

    public final class AuthenticationResultInternal {
        private CryptoObject mCryptoObject;

        public AuthenticationResultInternal(CryptoObject arg1) {
            super();
            this.mCryptoObject = arg1;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    public class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(Cipher arg1) {
            super();
            this.mCipher = arg1;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(Signature arg1) {
            super();
            this.mSignature = arg1;
            this.mCipher = null;
            this.mMac = null;
        }

        public CryptoObject(Mac arg1) {
            super();
            this.mMac = arg1;
            this.mCipher = null;
            this.mSignature = null;
        }

        public Cipher getCipher() {
            return this.mCipher;
        }

        public Mac getMac() {
            return this.mMac;
        }

        public Signature getSignature() {
            return this.mSignature;
        }
    }

    public FingerprintManagerCompatApi23() {
        super();
    }

    static CryptoObject access$000(FingerprintManager$CryptoObject arg0) {
        return FingerprintManagerCompatApi23.unwrapCryptoObject(arg0);
    }

    @SuppressLint(value={"MissingPermission"}) static void authenticate(Context arg6, CryptoObject arg7, int arg8, Object arg9, AuthenticationCallback arg10, Handler arg11) {
        FingerprintManager v0 = FingerprintManagerCompatApi23.getFingerprintManagerOrNull(arg6);
        if(v0 != null) {
            v0.authenticate(FingerprintManagerCompatApi23.wrapCryptoObject(arg7), arg9, arg8, FingerprintManagerCompatApi23.wrapCallback(arg10), arg11);
        }
    }

    private static FingerprintManager getFingerprintManagerOrNull(Context arg2) {
        if(arg2.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            return arg2.getSystemService(FingerprintManager.class);
        }

        return null;
    }

    @SuppressLint(value={"MissingPermission"}) static boolean hasEnrolledFingerprints(Context arg0) {
        FingerprintManager v0 = FingerprintManagerCompatApi23.getFingerprintManagerOrNull(arg0);
        boolean v0_1 = v0 == null || !v0.hasEnrolledFingerprints() ? false : true;
        return v0_1;
    }

    @SuppressLint(value={"MissingPermission"}) static boolean isHardwareDetected(Context arg0) {
        FingerprintManager v0 = FingerprintManagerCompatApi23.getFingerprintManagerOrNull(arg0);
        boolean v0_1 = v0 == null || !v0.isHardwareDetected() ? false : true;
        return v0_1;
    }

    private static CryptoObject unwrapCryptoObject(FingerprintManager$CryptoObject arg2) {
        CryptoObject v0 = null;
        if(arg2 == null) {
            return v0;
        }

        if(arg2.getCipher() != null) {
            return new CryptoObject(arg2.getCipher());
        }

        if(arg2.getSignature() != null) {
            return new CryptoObject(arg2.getSignature());
        }

        if(arg2.getMac() != null) {
            return new CryptoObject(arg2.getMac());
        }

        return v0;
    }

    private static FingerprintManager$AuthenticationCallback wrapCallback(AuthenticationCallback arg1) {
        return new FingerprintManager$AuthenticationCallback(arg1) {
            public void onAuthenticationError(int arg2, CharSequence arg3) {
                this.val$callback.onAuthenticationError(arg2, arg3);
            }

            public void onAuthenticationFailed() {
                this.val$callback.onAuthenticationFailed();
            }

            public void onAuthenticationHelp(int arg2, CharSequence arg3) {
                this.val$callback.onAuthenticationHelp(arg2, arg3);
            }

            public void onAuthenticationSucceeded(FingerprintManager$AuthenticationResult arg3) {
                this.val$callback.onAuthenticationSucceeded(new AuthenticationResultInternal(FingerprintManagerCompatApi23.unwrapCryptoObject(arg3.getCryptoObject())));
            }
        };
    }

    private static FingerprintManager$CryptoObject wrapCryptoObject(CryptoObject arg2) {
        FingerprintManager$CryptoObject v0 = null;
        if(arg2 == null) {
            return v0;
        }

        if(arg2.getCipher() != null) {
            return new FingerprintManager$CryptoObject(arg2.getCipher());
        }

        if(arg2.getSignature() != null) {
            return new FingerprintManager$CryptoObject(arg2.getSignature());
        }

        if(arg2.getMac() != null) {
            return new FingerprintManager$CryptoObject(arg2.getMac());
        }

        return v0;
    }
}

