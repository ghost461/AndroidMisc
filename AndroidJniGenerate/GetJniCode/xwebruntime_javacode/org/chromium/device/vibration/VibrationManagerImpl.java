package org.chromium.device.vibration;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.device.mojom.VibrationManager$CancelResponse;
import org.chromium.device.mojom.VibrationManager$VibrateResponse;
import org.chromium.device.mojom.VibrationManager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.MojoException;
import org.chromium.services.service_manager.InterfaceFactory;

@JNINamespace(value="device") public class VibrationManagerImpl implements VibrationManager {
    public class Factory implements InterfaceFactory {
        public Factory() {
            super();
        }

        public VibrationManager createImpl() {
            return new VibrationManagerImpl();
        }

        public Interface createImpl() {
            return this.createImpl();
        }
    }

    private static final long MAXIMUM_VIBRATION_DURATION_MS = 10000;
    private static final long MINIMUM_VIBRATION_DURATION_MS = 1;
    private static final String TAG = "VibrationManagerImpl";
    private final AudioManager mAudioManager;
    private final boolean mHasVibratePermission;
    private final Vibrator mVibrator;
    private static boolean sVibrateCancelledForTesting = false;
    private static long sVibrateMilliSecondsForTesting = -1;

    static {
    }

    public VibrationManagerImpl() {
        super();
        Context v0 = ContextUtils.getApplicationContext();
        this.mAudioManager = v0.getSystemService("audio");
        this.mVibrator = v0.getSystemService("vibrator");
        boolean v0_1 = v0.checkCallingOrSelfPermission("android.permission.VIBRATE") == 0 ? true : false;
        this.mHasVibratePermission = v0_1;
        if(!this.mHasVibratePermission) {
            Log.w("VibrationManagerImpl", "Failed to use vibrate API, requires VIBRATE permission.", new Object[0]);
        }
    }

    public void cancel(CancelResponse arg2) {
        if(this.mHasVibratePermission) {
            this.mVibrator.cancel();
        }

        VibrationManagerImpl.setVibrateCancelledForTesting(true);
        arg2.call();
    }

    public void close() {
    }

    @CalledByNative static boolean getVibrateCancelledForTesting() {
        return VibrationManagerImpl.sVibrateCancelledForTesting;
    }

    @CalledByNative static long getVibrateMilliSecondsForTesting() {
        return VibrationManagerImpl.sVibrateMilliSecondsForTesting;
    }

    public void onConnectionError(MojoException arg1) {
    }

    static void setVibrateCancelledForTesting(boolean arg0) {
        VibrationManagerImpl.sVibrateCancelledForTesting = arg0;
    }

    static void setVibrateMilliSecondsForTesting(long arg0) {
        VibrationManagerImpl.sVibrateMilliSecondsForTesting = arg0;
    }

    public void vibrate(long arg3, VibrateResponse arg5) {
        arg3 = Math.max(1, Math.min(arg3, 10000));
        if(this.mAudioManager.getRingerMode() != 0 && (this.mHasVibratePermission)) {
            this.mVibrator.vibrate(arg3);
        }

        VibrationManagerImpl.setVibrateMilliSecondsForTesting(arg3);
        arg5.call();
    }
}

