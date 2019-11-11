package org.chromium.media;

import android.annotation.SuppressLint;
import android.media.AudioTrack;
import android.os.Build$VERSION;
import java.nio.ByteBuffer;
import org.chromium.base.Log;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") class AudioTrackOutputStream {
    class AudioBufferInfo {
        private final int mNumBytes;
        private final int mNumFrames;

        public AudioBufferInfo(int arg1, int arg2) {
            super();
            this.mNumFrames = arg1;
            this.mNumBytes = arg2;
        }

        public int getNumBytes() {
            return this.mNumBytes;
        }

        public int getNumFrames() {
            return this.mNumFrames;
        }
    }

    interface Callback {
        AudioTrack createAudioTrack(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6);

        long getAddress(ByteBuffer arg1);

        int getMinBufferSize(int arg1, int arg2, int arg3);

        void onError();

        AudioBufferInfo onMoreData(ByteBuffer arg1, long arg2);
    }

    class WorkerThread extends Thread {
        private volatile boolean mDone;

        WorkerThread(AudioTrackOutputStream arg1) {
            AudioTrackOutputStream.this = arg1;
            super();
            this.mDone = false;
        }

        public void finish() {
            this.mDone = true;
        }

        public void run() {
            while(!this.mDone) {
                int v0 = AudioTrackOutputStream.this.writeData();
                if(v0 < 0) {
                }
                else {
                    if(v0 > 0) {
                    }
                    else {
                        AudioTrackOutputStream.this.readMoreData();
                    }

                    continue;
                }

                return;
            }
        }
    }

    private static final int CHANNEL_ALIGNMENT = 16;
    private static final String TAG = "AudioTrackOutput";
    private AudioTrack mAudioTrack;
    private int mBufferSizeInBytes;
    private Callback mCallback;
    private int mLastPlaybackHeadPosition;
    private int mLeftSize;
    private long mNativeAudioTrackOutputStream;
    private ByteBuffer mReadBuffer;
    private long mTotalPlayedFrames;
    private long mTotalReadFrames;
    private WorkerThread mWorkerThread;
    private ByteBuffer mWriteBuffer;

    static {
    }

    private AudioTrackOutputStream(Callback arg1) {
        super();
        this.mCallback = arg1;
        if(this.mCallback != null) {
            return;
        }

        this.mCallback = new Callback() {
            public AudioTrack createAudioTrack(int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
                return new AudioTrack(arg9, arg10, arg11, arg12, arg13, arg14);
            }

            public long getAddress(ByteBuffer arg4) {
                return AudioTrackOutputStream.access$400(AudioTrackOutputStream.this, AudioTrackOutputStream.access$200(AudioTrackOutputStream.this), arg4);
            }

            public int getMinBufferSize(int arg1, int arg2, int arg3) {
                return AudioTrack.getMinBufferSize(arg1, arg2, arg3);
            }

            public void onError() {
                AudioTrackOutputStream.access$500(AudioTrackOutputStream.this, AudioTrackOutputStream.access$200(AudioTrackOutputStream.this));
            }

            public AudioBufferInfo onMoreData(ByteBuffer arg7, long arg8) {
                return AudioTrackOutputStream.access$300(AudioTrackOutputStream.this, AudioTrackOutputStream.access$200(AudioTrackOutputStream.this), arg7, arg8);
            }
        };
    }

    static int access$000(AudioTrackOutputStream arg0) {
        return arg0.writeData();
    }

    static void access$100(AudioTrackOutputStream arg0) {
        arg0.readMoreData();
    }

    static long access$200(AudioTrackOutputStream arg2) {
        return arg2.mNativeAudioTrackOutputStream;
    }

    static AudioBufferInfo access$300(AudioTrackOutputStream arg0, long arg1, ByteBuffer arg3, long arg4) {
        return arg0.nativeOnMoreData(arg1, arg3, arg4);
    }

    static long access$400(AudioTrackOutputStream arg0, long arg1, ByteBuffer arg3) {
        return arg0.nativeGetAddress(arg1, arg3);
    }

    static void access$500(AudioTrackOutputStream arg0, long arg1) {
        arg0.nativeOnError(arg1);
    }

    private ByteBuffer allocateAlignedByteBuffer(int arg9, int arg10) {
        int v0 = arg10 - 1;
        ByteBuffer v1 = ByteBuffer.allocateDirect(arg9 + v0);
        arg10 = arg10 - (((int)(this.mCallback.getAddress(v1) & (((long)v0))))) & v0;
        v1.position(arg10);
        v1.limit(arg10 + arg9);
        return v1.slice();
    }

    @CalledByNative void close() {
        Log.d("AudioTrackOutput", "AudioTrackOutputStream.close()");
        if(this.mAudioTrack != null) {
            this.mAudioTrack.release();
            this.mAudioTrack = null;
        }
    }

    @CalledByNative private static AudioTrackOutputStream create() {
        return new AudioTrackOutputStream(null);
    }

    @VisibleForTesting static AudioTrackOutputStream create(Callback arg1) {
        return new AudioTrackOutputStream(arg1);
    }

    @CalledByNative AudioBufferInfo createAudioBufferInfo(int arg2, int arg3) {
        return new AudioBufferInfo(arg2, arg3);
    }

    private int getChannelConfig(int arg3) {
        int v0 = 4;
        if(arg3 == v0) {
            return 204;
        }

        if(arg3 == 6) {
            return 0xFC;
        }

        if(arg3 != 8) {
            switch(arg3) {
                case 1: {
                    return v0;
                }
                case 2: {
                    return 12;
                }
            }

            return 1;
        }

        if(Build$VERSION.SDK_INT >= 23) {
            return 0x18FC;
        }

        return 1020;
    }

    private native long nativeGetAddress(long arg1, ByteBuffer arg2) {
    }

    private native void nativeOnError(long arg1) {
    }

    private native AudioBufferInfo nativeOnMoreData(long arg1, ByteBuffer arg2, long arg3) {
    }

    @CalledByNative boolean open(int arg9, int arg10, int arg11) {
        int v3 = this.getChannelConfig(arg9);
        this.mBufferSizeInBytes = this.mCallback.getMinBufferSize(arg10, v3, arg11) * 3;
        try {
            Log.d("AudioTrackOutput", "Crate AudioTrack with sample rate:%d, channel:%d, format:%d ", Integer.valueOf(arg10), Integer.valueOf(v3), Integer.valueOf(arg11));
            this.mAudioTrack = this.mCallback.createAudioTrack(3, arg10, v3, arg11, this.mBufferSizeInBytes, 1);
        }
        catch(IllegalArgumentException v10) {
            Log.e("AudioTrackOutput", "Exception creating AudioTrack for playback: ", new Object[]{v10});
            return 0;
        }

        if(this.mAudioTrack.getState() == 0) {
            Log.e("AudioTrackOutput", "Cannot create AudioTrack", new Object[0]);
            this.mAudioTrack = null;
            return 0;
        }

        this.mLastPlaybackHeadPosition = 0;
        this.mTotalPlayedFrames = 0;
        return 1;
    }

    private void readMoreData() {
        int v0 = this.mAudioTrack.getPlaybackHeadPosition();
        this.mTotalPlayedFrames += ((long)(v0 - this.mLastPlaybackHeadPosition));
        this.mLastPlaybackHeadPosition = v0;
        long v4 = this.mTotalReadFrames - this.mTotalPlayedFrames;
        long v0_1 = 0;
        if(v4 < v0_1) {
        }
        else {
            v0_1 = v4;
        }

        AudioBufferInfo v0_2 = this.mCallback.onMoreData(this.mReadBuffer.duplicate(), v0_1);
        if(v0_2 != null) {
            if(v0_2.getNumBytes() <= 0) {
            }
            else {
                this.mTotalReadFrames += ((long)v0_2.getNumFrames());
                this.mWriteBuffer = this.mReadBuffer.asReadOnlyBuffer();
                this.mLeftSize = v0_2.getNumBytes();
                return;
            }
        }
    }

    @CalledByNative void setVolume(double arg3) {
        float v3 = ((float)(arg3 * (((double)AudioTrack.getMaxVolume()))));
        this.mAudioTrack.setStereoVolume(v3, v3);
    }

    @CalledByNative void start(long arg3) {
        Log.d("AudioTrackOutput", "AudioTrackOutputStream.start()");
        if(this.mWorkerThread != null) {
            return;
        }

        this.mNativeAudioTrackOutputStream = arg3;
        this.mTotalReadFrames = 0;
        this.mReadBuffer = this.allocateAlignedByteBuffer(this.mBufferSizeInBytes, 16);
        this.mAudioTrack.play();
        this.mWorkerThread = new WorkerThread(this);
        this.mWorkerThread.start();
    }

    @CalledByNative void stop() {
        Log.d("AudioTrackOutput", "AudioTrackOutputStream.stop()");
        if(this.mWorkerThread != null) {
            this.mWorkerThread.finish();
            try {
                this.mWorkerThread.interrupt();
                this.mWorkerThread.join();
            }
            catch(InterruptedException v2) {
                Log.e("AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", new Object[]{v2});
            }
            catch(SecurityException v2_1) {
                Log.e("AudioTrackOutput", "Exception while waiting for AudioTrack worker thread finished: ", new Object[]{v2_1});
            }

            this.mWorkerThread = null;
        }

        this.mAudioTrack.pause();
        this.mAudioTrack.flush();
        this.mLastPlaybackHeadPosition = 0;
        this.mTotalPlayedFrames = 0;
        this.mNativeAudioTrackOutputStream = 0;
    }

    @SuppressLint(value={"NewApi"}) private int writeAudioTrack() {
        return this.mAudioTrack.write(this.mWriteBuffer, this.mLeftSize, 0);
    }

    private int writeData() {
        if(this.mLeftSize == 0) {
            return 0;
        }

        int v0 = this.writeAudioTrack();
        if(v0 < 0) {
            Log.e("AudioTrackOutput", "AudioTrack.write() failed. Error:" + v0, new Object[0]);
            this.mCallback.onError();
            return v0;
        }

        this.mLeftSize -= v0;
        return this.mLeftSize;
    }
}

