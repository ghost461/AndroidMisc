package org.chromium.content.browser;

import android.media.AudioManager$OnAudioFocusChangeListener;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public class AudioFocusDelegate implements AudioManager$OnAudioFocusChangeListener {
    private static final String TAG = "MediaSession";
    private int mFocusType;
    private boolean mIsDucking;
    private long mNativeAudioFocusDelegateAndroid;

    static {
    }

    private AudioFocusDelegate(long arg1) {
        super();
        this.mNativeAudioFocusDelegateAndroid = arg1;
    }

    @CalledByNative private void abandonAudioFocus() {
        ContextUtils.getApplicationContext().getSystemService("audio").abandonAudioFocus(((AudioManager$OnAudioFocusChangeListener)this));
    }

    @CalledByNative private static AudioFocusDelegate create(long arg1) {
        return new AudioFocusDelegate(arg1);
    }

    private native void nativeOnResume(long arg1) {
    }

    private native void nativeOnStartDucking(long arg1) {
    }

    private native void nativeOnStopDucking(long arg1) {
    }

    private native void nativeOnSuspend(long arg1) {
    }

    private native void nativeRecordSessionDuck(long arg1) {
    }

    public void onAudioFocusChange(int arg6) {
        if(this.mNativeAudioFocusDelegateAndroid == 0) {
            return;
        }

        if(arg6 != 1) {
            switch(arg6) {
                case -3: {
                    goto label_22;
                }
                case -2: {
                    goto label_19;
                }
                case -1: {
                    goto label_15;
                }
            }

            Log.w("MediaSession", "onAudioFocusChange called with unexpected value %d", new Object[]{Integer.valueOf(arg6)});
            return;
        label_19:
            this.nativeOnSuspend(this.mNativeAudioFocusDelegateAndroid);
            return;
        label_22:
            this.mIsDucking = true;
            this.nativeRecordSessionDuck(this.mNativeAudioFocusDelegateAndroid);
            this.nativeOnStartDucking(this.mNativeAudioFocusDelegateAndroid);
            return;
        label_15:
            this.abandonAudioFocus();
            this.nativeOnSuspend(this.mNativeAudioFocusDelegateAndroid);
        }
        else if(this.mIsDucking) {
            this.nativeOnStopDucking(this.mNativeAudioFocusDelegateAndroid);
            this.mIsDucking = false;
        }
        else {
            this.nativeOnResume(this.mNativeAudioFocusDelegateAndroid);
        }
    }

    @CalledByNative private boolean requestAudioFocus(boolean arg1) {
        int v1 = arg1 ? 3 : 1;
        this.mFocusType = v1;
        return this.requestAudioFocusInternal();
    }

    private boolean requestAudioFocusInternal() {
        boolean v1 = true;
        if(ContextUtils.getApplicationContext().getSystemService("audio").requestAudioFocus(((AudioManager$OnAudioFocusChangeListener)this), 3, this.mFocusType) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    @CalledByNative private void tearDown() {
        this.abandonAudioFocus();
        this.mNativeAudioFocusDelegateAndroid = 0;
    }
}

