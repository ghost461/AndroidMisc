package org.chromium.content.browser.crypto;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.annotation.concurrent.ThreadSafe;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.base.Log;
import org.chromium.base.ObserverList;
import org.chromium.base.SecureRandomInitializer;
import org.chromium.base.ThreadUtils;

@ThreadSafe public class CipherFactory {
    class CipherData {
        public final byte[] iv;
        public final Key key;

        public CipherData(Key arg1, byte[] arg2) {
            super();
            this.key = arg1;
            this.iv = arg2;
        }
    }

    public interface CipherDataObserver {
        void onCipherDataGenerated();
    }

    class LazyHolder {
        private static CipherFactory sInstance;

        static {
            LazyHolder.sInstance = new CipherFactory(null);
        }

        private LazyHolder() {
            super();
        }

        static CipherFactory access$100() {
            return LazyHolder.sInstance;
        }
    }

    static final String BUNDLE_IV = "org.chromium.content.browser.crypto.CipherFactory.IV";
    static final String BUNDLE_KEY = "org.chromium.content.browser.crypto.CipherFactory.KEY";
    static final int NUM_BYTES = 16;
    private static final String TAG = "cr.CipherFactory";
    private CipherData mData;
    private FutureTask mDataGenerator;
    private final Object mDataLock;
    private final ObserverList mObservers;
    private ByteArrayGenerator mRandomNumberProvider;

    private CipherFactory() {
        super();
        this.mDataLock = new Object();
        this.mRandomNumberProvider = new ByteArrayGenerator();
        this.mObservers = new ObserverList();
    }

    CipherFactory(org.chromium.content.browser.crypto.CipherFactory$1 arg1) {
        this();
    }

    static void access$200(CipherFactory arg0) {
        arg0.notifyCipherDataGenerated();
    }

    static ByteArrayGenerator access$300(CipherFactory arg0) {
        return arg0.mRandomNumberProvider;
    }

    public void addCipherDataObserver(CipherDataObserver arg2) {
        this.mObservers.addObserver(arg2);
    }

    private Callable createGeneratorCallable() {
        return new Callable() {
            @SuppressLint(value={"TrulyRandom"}) public Object call() throws Exception {
                return this.call();
            }

            @SuppressLint(value={"TrulyRandom"}) public CipherData call() {
                byte[] v2;
                CipherData v0 = null;
                try {
                    v2 = CipherFactory.this.mRandomNumberProvider.getBytes(16);
                }
                catch(GeneralSecurityException ) {
                    Log.e("cr.CipherFactory", "Couldn\'t get generator data.", new Object[0]);
                    return v0;
                }
                catch(IOException ) {
                    Log.e("cr.CipherFactory", "Couldn\'t get generator data.", new Object[0]);
                    return v0;
                }

                try {
                    SecureRandom v3 = new SecureRandom();
                    SecureRandomInitializer.initialize(v3);
                    KeyGenerator v4 = KeyGenerator.getInstance("AES");
                    v4.init(0x80, v3);
                    return new CipherData(v4.generateKey(), v2);
                }
                catch(GeneralSecurityException ) {
                    Log.e("cr.CipherFactory", "Couldn\'t get generator instances.", new Object[0]);
                    return v0;
                }
                catch(IOException ) {
                    Log.e("cr.CipherFactory", "Couldn\'t get generator data.", new Object[0]);
                    return v0;
                }
            }
        };
    }

    public Cipher getCipher(int arg5) {
        CipherData v0 = this.getCipherData(true);
        if(v0 == null) {
            goto label_11;
        }

        try {
            Cipher v1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            v1.init(arg5, v0.key, new IvParameterSpec(v0.iv));
            return v1;
        }
        catch(GeneralSecurityException ) {
        label_11:
            Log.e("cr.CipherFactory", "Error in creating cipher instance.", new Object[0]);
            return null;
        }
    }

    CipherData getCipherData(boolean arg3) {
        Object v0;
        Object v3_2;
        if(this.mData == null && (arg3)) {
            this.triggerKeyGeneration();
            try {
                v3_2 = this.mDataGenerator.get();
                v0 = this.mDataLock;
            }
            catch(ExecutionException v3) {
                throw new RuntimeException(((Throwable)v3));
            }
            catch(InterruptedException v3_1) {
                throw new RuntimeException(((Throwable)v3_1));
            }

            __monitor_enter(v0);
            try {
                if(this.mData == null) {
                    this.mData = ((CipherData)v3_2);
                    ThreadUtils.postOnUiThread(new Runnable() {
                        public void run() {
                            CipherFactory.this.notifyCipherDataGenerated();
                        }
                    });
                }

                __monitor_exit(v0);
                goto label_27;
            label_17:
                __monitor_exit(v0);
            }
            catch(Throwable v3_3) {
                goto label_17;
            }

            throw v3_3;
        }

    label_27:
        return this.mData;
    }

    public static CipherFactory getInstance() {
        return LazyHolder.access$100();
    }

    public boolean hasCipher() {
        Object v0 = this.mDataLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = this.mData != null ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_10;
        }

        throw v1;
    }

    private void notifyCipherDataGenerated() {
        Iterator v0 = this.mObservers.iterator();
        while(v0.hasNext()) {
            v0.next().onCipherDataGenerated();
        }
    }

    public void removeCipherDataObserver(CipherDataObserver arg2) {
        this.mObservers.removeObserver(arg2);
    }

    public boolean restoreFromBundle(Bundle arg6) {
        Object v1_1;
        SecretKeySpec v2;
        if(arg6 == null) {
            return 0;
        }

        byte[] v1 = arg6.getByteArray("org.chromium.content.browser.crypto.CipherFactory.KEY");
        byte[] v6 = arg6.getByteArray("org.chromium.content.browser.crypto.CipherFactory.IV");
        if(v1 != null) {
            if(v6 == null) {
            }
            else {
                try {
                    v2 = new SecretKeySpec(v1, "AES");
                    v1_1 = this.mDataLock;
                    __monitor_enter(v1_1);
                }
                catch(IllegalArgumentException ) {
                    goto label_42;
                }

                try {
                    if(this.mData == null) {
                        this.mData = new CipherData(((Key)v2), v6);
                        __monitor_exit(v1_1);
                        return 1;
                    }
                    else {
                        if((this.mData.key.equals(v2)) && (Arrays.equals(this.mData.iv, v6))) {
                            __monitor_exit(v1_1);
                            return 1;
                        }

                        Log.e("cr.CipherFactory", "Attempted to restore different cipher data.", new Object[0]);
                        __monitor_exit(v1_1);
                        return 0;
                    label_40:
                        __monitor_exit(v1_1);
                        goto label_41;
                    }

                    return 0;
                }
                catch(Throwable v6_1) {
                    goto label_40;
                }

                try {
                label_41:
                    throw v6_1;
                }
                catch(IllegalArgumentException ) {
                label_42:
                    Log.e("cr.CipherFactory", "Error in restoring the key from the bundle.", new Object[0]);
                }

                return 0;
            }
        }

        return 0;
    }

    public void saveToBundle(Bundle arg4) {
        CipherData v0 = this.getCipherData(false);
        if(v0 == null) {
            return;
        }

        byte[] v1 = v0.key.getEncoded();
        if(v1 != null && v0.iv != null) {
            arg4.putByteArray("org.chromium.content.browser.crypto.CipherFactory.KEY", v1);
            arg4.putByteArray("org.chromium.content.browser.crypto.CipherFactory.IV", v0.iv);
        }
    }

    void setRandomNumberProviderForTests(ByteArrayGenerator arg1) {
        this.mRandomNumberProvider = arg1;
    }

    public void triggerKeyGeneration() {
        if(this.mData != null) {
            return;
        }

        Object v0 = this.mDataLock;
        __monitor_enter(v0);
        try {
            if(this.mDataGenerator == null) {
                this.mDataGenerator = new FutureTask(this.createGeneratorCallable());
                AsyncTask.THREAD_POOL_EXECUTOR.execute(this.mDataGenerator);
            }

            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_17;
        }

        throw v1;
    }
}

