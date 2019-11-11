package org.chromium.media;

import android.annotation.SuppressLint;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Process;
import java.nio.ByteBuffer;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") class AudioRecordInput {
    class org.chromium.media.AudioRecordInput$1 {
    }

    class AudioRecordThread extends Thread {
        private volatile boolean mKeepAlive;

        AudioRecordThread(AudioRecordInput arg1, org.chromium.media.AudioRecordInput$1 arg2) {
            this(arg1);
        }

        private AudioRecordThread(AudioRecordInput arg1) {
            AudioRecordInput.this = arg1;
            super();
            this.mKeepAlive = true;
        }

        public void joinRecordThread() {
            this.mKeepAlive = false;
            while(this.isAlive()) {
                try {
                    this.join();
                }
                catch(InterruptedException ) {
                }
            }
        }

        public void run() {
            Process.setThreadPriority(-19);
            try {
                AudioRecordInput.this.mAudioRecord.startRecording();
            }
            catch(IllegalStateException v2) {
                Log.e("cr.media", "startRecording failed", new Object[]{v2});
                return;
            }

            while(this.mKeepAlive) {
                int v2_1 = AudioRecordInput.this.mAudioRecord.read(AudioRecordInput.this.mBuffer, AudioRecordInput.this.mBuffer.capacity());
                if(v2_1 > 0) {
                    AudioRecordInput.this.nativeOnData(AudioRecordInput.this.mNativeAudioRecordInputStream, v2_1, 100);
                    continue;
                }

                Log.e("cr.media", "read failed: %d", new Object[]{Integer.valueOf(v2_1)});
                if(v2_1 != -3) {
                    continue;
                }

                this.mKeepAlive = false;
            }

            try {
                AudioRecordInput.this.mAudioRecord.stop();
            }
            catch(IllegalStateException v2) {
                Log.e("cr.media", "stop failed", new Object[]{v2});
            }
        }
    }

    private static final boolean DEBUG = false;
    private static final int HARDWARE_DELAY_MS = 100;
    private static final String TAG = "cr.media";
    private AcousticEchoCanceler mAEC;
    private AudioRecord mAudioRecord;
    private AudioRecordThread mAudioRecordThread;
    private final int mBitsPerSample;
    private ByteBuffer mBuffer;
    private final int mChannels;
    private final long mNativeAudioRecordInputStream;
    private final int mSampleRate;
    private final boolean mUsePlatformAEC;

    private AudioRecordInput(long arg1, int arg3, int arg4, int arg5, int arg6, boolean arg7) {
        super();
        this.mNativeAudioRecordInputStream = arg1;
        this.mSampleRate = arg3;
        this.mChannels = arg4;
        this.mBitsPerSample = arg5;
        this.mUsePlatformAEC = arg7;
        this.mBuffer = ByteBuffer.allocateDirect(arg6);
        this.nativeCacheDirectBufferAddress(this.mNativeAudioRecordInputStream, this.mBuffer);
    }

    static AudioRecord access$000(AudioRecordInput arg0) {
        return arg0.mAudioRecord;
    }

    static ByteBuffer access$100(AudioRecordInput arg0) {
        return arg0.mBuffer;
    }

    static long access$200(AudioRecordInput arg2) {
        return arg2.mNativeAudioRecordInputStream;
    }

    static void access$300(AudioRecordInput arg0, long arg1, int arg3, int arg4) {
        arg0.nativeOnData(arg1, arg3, arg4);
    }

    @SuppressLint(value={"NewApi"}) @CalledByNative private void close() {
        if(this.mAudioRecordThread != null) {
            Log.e("cr.media", "close() called before stop().", new Object[0]);
            return;
        }

        if(this.mAudioRecord == null) {
            return;
        }

        AcousticEchoCanceler v1 = null;
        if(this.mAEC != null) {
            this.mAEC.release();
            this.mAEC = v1;
        }

        this.mAudioRecord.release();
        this.mAudioRecord = ((AudioRecord)v1);
    }

    @CalledByNative private static AudioRecordInput createAudioRecordInput(long arg9, int arg11, int arg12, int arg13, int arg14, boolean arg15) {
        return new AudioRecordInput(arg9, arg11, arg12, arg13, arg14, arg15);
    }

    private native void nativeCacheDirectBufferAddress(long arg1, ByteBuffer arg2) {
    }

    private native void nativeOnData(long arg1, int arg2, int arg3) {
    }

    @SuppressLint(value={"NewApi"}) @CalledByNative private boolean open() {
        int v9;
        int v8;
        if(this.mAudioRecord != null) {
            Log.e("cr.media", "open() called twice without a close()", new Object[0]);
            return 0;
        }

        int v2 = 2;
        int v3 = 16;
        if(this.mChannels == 1) {
            v8 = 16;
        }
        else if(this.mChannels == v2) {
            v8 = 12;
        }
        else {
            goto label_88;
        }

        if(this.mBitsPerSample == 8) {
            v9 = 3;
        }
        else if(this.mBitsPerSample == v3) {
            v9 = 2;
        }
        else {
            goto label_80;
        }

        int v0 = AudioRecord.getMinBufferSize(this.mSampleRate, v8, v9);
        if(v0 < 0) {
            Log.e("cr.media", "getMinBufferSize error: %d", new Object[]{Integer.valueOf(v0)});
            return 0;
        }

        int v10 = Math.max(this.mBuffer.capacity(), v0);
        try {
            this.mAudioRecord = new AudioRecord(7, this.mSampleRate, v8, v9, v10);
        }
        catch(IllegalArgumentException v0_1) {
            Log.e("cr.media", "AudioRecord failed", new Object[]{v0_1});
            return 0;
        }

        if(AcousticEchoCanceler.isAvailable()) {
            this.mAEC = AcousticEchoCanceler.create(this.mAudioRecord.getAudioSessionId());
            if(this.mAEC == null) {
                Log.e("cr.media", "AcousticEchoCanceler.create failed", new Object[0]);
                return 0;
            }
            else {
                v0 = this.mAEC.setEnabled(this.mUsePlatformAEC);
                if(v0 != 0) {
                    Log.e("cr.media", "setEnabled error: %d", new Object[]{Integer.valueOf(v0)});
                    return 0;
                }
            }
        }

        return 1;
    label_80:
        Log.e("cr.media", "Unsupported bits per sample: %d", new Object[]{Integer.valueOf(this.mBitsPerSample)});
        return 0;
    label_88:
        Log.e("cr.media", "Unsupported number of channels: %d", new Object[]{Integer.valueOf(this.mChannels)});
        return 0;
    }

    @CalledByNative private void start() {
        if(this.mAudioRecord == null) {
            Log.e("cr.media", "start() called before open().", new Object[0]);
            return;
        }

        if(this.mAudioRecordThread != null) {
            return;
        }

        this.mAudioRecordThread = new AudioRecordThread(this, null);
        this.mAudioRecordThread.start();
    }

    @CalledByNative private void stop() {
        if(this.mAudioRecordThread == null) {
            return;
        }

        this.mAudioRecordThread.joinRecordThread();
        this.mAudioRecordThread = null;
    }
}

