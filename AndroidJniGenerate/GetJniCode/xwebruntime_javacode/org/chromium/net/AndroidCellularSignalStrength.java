package org.chromium.net;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import org.chromium.base.ApplicationStatus$ApplicationStateListener;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="net::android") public class AndroidCellularSignalStrength {
    class CellStateListener extends PhoneStateListener implements ApplicationStateListener {
        private final TelephonyManager mTelephonyManager;

        static {
        }

        CellStateListener(AndroidCellularSignalStrength arg2) {
            AndroidCellularSignalStrength.this = arg2;
            super();
            ThreadUtils.assertOnBackgroundThread();
            this.mTelephonyManager = ContextUtils.getApplicationContext().getSystemService("phone");
            if(this.mTelephonyManager.getSimState() != 5) {
                return;
            }

            ApplicationStatus.registerApplicationStateListener(((ApplicationStateListener)this));
            this.onApplicationStateChange(ApplicationStatus.getStateForApplication());
        }

        public void onApplicationStateChange(int arg2) {
            if(arg2 == 1) {
                this.register();
            }
            else if(arg2 == 2) {
                this.unregister();
            }
        }

        @TargetApi(value=23) public void onSignalStrengthsChanged(SignalStrength arg3) {
            if(ApplicationStatus.getStateForApplication() != 1) {
                return;
            }

            try {
                AndroidCellularSignalStrength.this.mSignalLevel = arg3.getLevel();
            }
            catch(SecurityException ) {
                AndroidCellularSignalStrength.this.mSignalLevel = 0x80000000;
            }
        }

        private void register() {
            this.mTelephonyManager.listen(((PhoneStateListener)this), 0x100);
        }

        private void unregister() {
            AndroidCellularSignalStrength.this.mSignalLevel = 0x80000000;
            this.mTelephonyManager.listen(((PhoneStateListener)this), 0);
        }
    }

    private volatile int mSignalLevel;
    private static final AndroidCellularSignalStrength sInstance;

    static {
        AndroidCellularSignalStrength.sInstance = new AndroidCellularSignalStrength();
    }

    private AndroidCellularSignalStrength() {
        super();
        this.mSignalLevel = 0x80000000;
        if(Build$VERSION.SDK_INT < 23) {
            return;
        }

        HandlerThread v0 = new HandlerThread("AndroidCellularSignalStrength");
        v0.start();
        new Handler(v0.getLooper()).post(new Runnable() {
            public void run() {
                new CellStateListener(AndroidCellularSignalStrength.this);
            }
        });
    }

    static int access$002(AndroidCellularSignalStrength arg0, int arg1) {
        arg0.mSignalLevel = arg1;
        return arg1;
    }

    @TargetApi(value=23) @CalledByNative private static int getSignalStrengthLevel() {
        return AndroidCellularSignalStrength.sInstance.mSignalLevel;
    }
}

