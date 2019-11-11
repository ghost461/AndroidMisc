package org.chromium.media;

import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer;
import android.os.SystemClock;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") public class MediaServerCrashListener implements MediaPlayer$OnErrorListener {
    private static final int APPROX_MEDIA_SERVER_RESTART_TIME_IN_MS = 5000;
    private static final String TAG = "crMediaCrashListener";
    private static final long UNKNOWN_TIME = -1;
    private long mLastReportedWatchdogCreationFailure;
    private final Object mLock;
    private long mNativeMediaServerCrashListener;
    private MediaPlayer mPlayer;

    private MediaServerCrashListener(long arg3) {
        super();
        this.mLock = new Object();
        this.mLastReportedWatchdogCreationFailure = -1;
        this.mNativeMediaServerCrashListener = arg3;
    }

    @CalledByNative private static MediaServerCrashListener create(long arg1) {
        return new MediaServerCrashListener(arg1);
    }

    private native void nativeOnMediaServerCrashDetected(long arg1, boolean arg2) {
    }

    public boolean onError(MediaPlayer arg1, int arg2, int arg3) {
        if(arg2 == 100) {
            this.nativeOnMediaServerCrashDetected(this.mNativeMediaServerCrashListener, true);
            this.releaseWatchdog();
        }

        return 1;
    }

    @CalledByNative public void releaseWatchdog() {
        if(this.mPlayer == null) {
            return;
        }

        this.mPlayer.release();
        this.mPlayer = null;
    }

    @CalledByNative public boolean startListening() {
        if(this.mPlayer != null) {
            return 1;
        }

        try {
            this.mPlayer = MediaPlayer.create(ContextUtils.getApplicationContext(), 0x7F0B0000);
        }
        catch(RuntimeException v2) {
            Log.e("crMediaCrashListener", "Exception while creating the watchdog player.", new Object[]{v2});
        }
        catch(IllegalStateException v2_1) {
            Log.e("crMediaCrashListener", "Exception while creating the watchdog player.", new Object[]{v2_1});
        }

        long v3 = -1;
        if(this.mPlayer != null) {
            this.mPlayer.setOnErrorListener(((MediaPlayer$OnErrorListener)this));
            this.mLastReportedWatchdogCreationFailure = v3;
            return 1;
        }

        long v1 = SystemClock.elapsedRealtime();
        if(this.mLastReportedWatchdogCreationFailure == v3 || v1 - this.mLastReportedWatchdogCreationFailure > 5000) {
            Log.e("crMediaCrashListener", "Unable to create watchdog player, treating it as server crash.", new Object[0]);
            this.nativeOnMediaServerCrashDetected(this.mNativeMediaServerCrashListener, false);
            this.mLastReportedWatchdogCreationFailure = v1;
        }

        return 0;
    }
}

