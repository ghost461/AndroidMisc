package org.chromium.media;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec$CryptoException;
import android.media.MediaCodec$CryptoInfo;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="media") class MediaCodecBridge {
    class org.chromium.media.MediaCodecBridge$1 {
    }

    @MainDex class DequeueInputResult {
        private final int mIndex;
        private final int mStatus;

        DequeueInputResult(int arg1, int arg2, org.chromium.media.MediaCodecBridge$1 arg3) {
            this(arg1, arg2);
        }

        private DequeueInputResult(int arg1, int arg2) {
            super();
            this.mStatus = arg1;
            this.mIndex = arg2;
        }

        @CalledByNative(value="DequeueInputResult") private int index() {
            return this.mIndex;
        }

        @CalledByNative(value="DequeueInputResult") private int status() {
            return this.mStatus;
        }
    }

    @MainDex class DequeueOutputResult {
        private final int mFlags;
        private final int mIndex;
        private final int mNumBytes;
        private final int mOffset;
        private final long mPresentationTimeMicroseconds;
        private final int mStatus;

        DequeueOutputResult(int arg1, int arg2, int arg3, int arg4, long arg5, int arg7, org.chromium.media.MediaCodecBridge$1 arg8) {
            this(arg1, arg2, arg3, arg4, arg5, arg7);
        }

        private DequeueOutputResult(int arg1, int arg2, int arg3, int arg4, long arg5, int arg7) {
            super();
            this.mStatus = arg1;
            this.mIndex = arg2;
            this.mFlags = arg3;
            this.mOffset = arg4;
            this.mPresentationTimeMicroseconds = arg5;
            this.mNumBytes = arg7;
        }

        @CalledByNative(value="DequeueOutputResult") private int flags() {
            return this.mFlags;
        }

        @CalledByNative(value="DequeueOutputResult") private int index() {
            return this.mIndex;
        }

        @CalledByNative(value="DequeueOutputResult") private int numBytes() {
            return this.mNumBytes;
        }

        @CalledByNative(value="DequeueOutputResult") private int offset() {
            return this.mOffset;
        }

        @CalledByNative(value="DequeueOutputResult") private long presentationTimeMicroseconds() {
            return this.mPresentationTimeMicroseconds;
        }

        @CalledByNative(value="DequeueOutputResult") private int status() {
            return this.mStatus;
        }
    }

    @MainDex class GetOutputFormatResult {
        private final MediaFormat mFormat;
        private final int mStatus;

        GetOutputFormatResult(int arg1, MediaFormat arg2, org.chromium.media.MediaCodecBridge$1 arg3) {
            this(arg1, arg2);
        }

        private GetOutputFormatResult(int arg1, MediaFormat arg2) {
            super();
            this.mStatus = arg1;
            this.mFormat = arg2;
        }

        @CalledByNative(value="GetOutputFormatResult") private int channelCount() {
            return this.mFormat.getInteger("channel-count");
        }

        private boolean formatHasCropValues() {
            boolean v0 = !this.mFormat.containsKey("crop-right") || !this.mFormat.containsKey("crop-left") || !this.mFormat.containsKey("crop-bottom") || !this.mFormat.containsKey("crop-top") ? false : true;
            return v0;
        }

        @CalledByNative(value="GetOutputFormatResult") private int height() {
            int v0 = this.formatHasCropValues() ? this.mFormat.getInteger("crop-bottom") - this.mFormat.getInteger("crop-top") + 1 : this.mFormat.getInteger("height");
            return v0;
        }

        @CalledByNative(value="GetOutputFormatResult") private int sampleRate() {
            return this.mFormat.getInteger("sample-rate");
        }

        @CalledByNative(value="GetOutputFormatResult") private int status() {
            return this.mStatus;
        }

        @CalledByNative(value="GetOutputFormatResult") private int width() {
            int v0 = this.formatHasCropValues() ? this.mFormat.getInteger("crop-right") - this.mFormat.getInteger("crop-left") + 1 : this.mFormat.getInteger("width");
            return v0;
        }
    }

    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final long MAX_PRESENTATION_TIMESTAMP_SHIFT_US = 100000;
    private static final int MEDIA_CODEC_UNKNOWN_CIPHER_MODE = -1;
    private static final int PCM16_BYTES_PER_SAMPLE = 2;
    private static final String TAG = "cr_MediaCodecBridge";
    private BitrateAdjuster mBitrateAdjuster;
    private boolean mFlushed;
    private ByteBuffer[] mInputBuffers;
    private long mLastPresentationTimeUs;
    protected MediaCodec mMediaCodec;
    private ByteBuffer[] mOutputBuffers;

    static {
    }

    MediaCodecBridge(MediaCodec arg3, BitrateAdjuster arg4) {
        super();
        this.mMediaCodec = arg3;
        this.mLastPresentationTimeUs = 0;
        this.mFlushed = true;
        this.mBitrateAdjuster = arg4;
    }

    boolean configureAudio(MediaFormat arg5, MediaCrypto arg6, int arg7) {
        try {
            this.mMediaCodec.configure(arg5, null, arg6, arg7);
            return 1;
        }
        catch(Exception v5) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec", new Object[]{v5});
        }
        catch(MediaCodec$CryptoException v5_1) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec: DRM error", new Object[]{v5_1});
        }
        catch(IllegalStateException v5_2) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec", new Object[]{v5_2});
        }
        catch(IllegalArgumentException v5_3) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the audio codec", new Object[]{v5_3});
        }

        return 0;
    }

    boolean configureVideo(MediaFormat arg4, Surface arg5, MediaCrypto arg6, int arg7) {
        try {
            this.mMediaCodec.configure(arg4, arg5, arg6, arg7);
            return 1;
        }
        catch(Exception v4) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec", new Object[]{v4});
        }
        catch(MediaCodec$CryptoException v4_1) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec: DRM error", new Object[]{v4_1});
        }
        catch(IllegalStateException v4_2) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec", new Object[]{v4_2});
        }
        catch(IllegalArgumentException v4_3) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec, wrong format or surface", new Object[]{v4_3});
        }

        return 0;
    }

    @CalledByNative private DequeueInputResult dequeueInputBuffer(long arg7) {
        int v1 = -1;
        int v3 = 5;
        try {
            int v7_1 = this.mMediaCodec.dequeueInputBuffer(arg7);
            if(v7_1 >= 0) {
                v1 = v7_1;
                v3 = 0;
                goto label_29;
            }

            if(v7_1 == v1) {
                v3 = 1;
                goto label_29;
            }

            Log.e("cr_MediaCodecBridge", "Unexpected index_or_status: " + v7_1, new Object[0]);
        }
        catch(Exception v7) {
            Log.e("cr_MediaCodecBridge", "Failed to dequeue input buffer", new Object[]{v7});
        }

    label_29:
        return new DequeueInputResult(v3, v1, null);
    }

    @CalledByNative private DequeueOutputResult dequeueOutputBuffer(long arg20) {
        int v12;
        int v11;
        MediaCodecBridge v1 = this;
        MediaCodec$BufferInfo v2 = new MediaCodec$BufferInfo();
        int v3 = 1;
        int v4 = -1;
        int v5 = 5;
        long v7 = arg20;
        try {
            int v7_1 = v1.dequeueOutputBufferInternal(v2, v7);
            if(v2.presentationTimeUs < v1.mLastPresentationTimeUs) {
                v2.presentationTimeUs = v1.mLastPresentationTimeUs;
            }

            v1.mLastPresentationTimeUs = v2.presentationTimeUs;
            if(v7_1 >= 0) {
                v4 = v7_1;
                v5 = 0;
            }
            else if(v7_1 == -3) {
                v1.mOutputBuffers = v1.mMediaCodec.getOutputBuffers();
                v3 = 2;
                v5 = 2;
            }
            else if(v7_1 == -2) {
                v1.mMediaCodec.getOutputFormat();
                v5 = 3;
            }
            else if(v7_1 == v4) {
                v5 = 1;
            }
            else {
                Log.e("cr_MediaCodecBridge", "Unexpected index_or_status: " + v7_1, new Object[0]);
            }
        }
        catch(IllegalStateException v0) {
            Object[] v3_1 = new Object[v3];
            v3_1[0] = v0;
            Log.e("cr_MediaCodecBridge", "Failed to dequeue output buffer", v3_1);
            v11 = 5;
            v12 = -1;
            goto label_59;
        }

        v12 = v4;
        v11 = v5;
    label_59:
        return new DequeueOutputResult(v11, v12, v2.flags, v2.offset, v2.presentationTimeUs, v2.size, null);
    }

    protected int dequeueOutputBufferInternal(MediaCodec$BufferInfo arg2, long arg3) {
        return this.mMediaCodec.dequeueOutputBuffer(arg2, arg3);
    }

    @CalledByNative private int flush() {
        try {
            this.mFlushed = true;
            this.mMediaCodec.flush();
            return 0;
        }
        catch(Exception v2) {
            Log.e("cr_MediaCodecBridge", "Failed to flush MediaCodec", new Object[]{v2});
            return 5;
        }
    }

    private int getAudioFormat(int arg3) {
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

    @SuppressLint(value={"NewApi"}) @CalledByNative private ByteBuffer getInputBuffer(int arg5) {
        if(Build$VERSION.SDK_INT > 19) {
            try {
                return this.mMediaCodec.getInputBuffer(arg5);
            }
            catch(IllegalStateException v5) {
                Log.e("cr_MediaCodecBridge", "Failed to get input buffer", new Object[]{v5});
                return null;
            }
        }

        return this.mInputBuffers[arg5];
    }

    @TargetApi(value=19) @CalledByNative private String getName() {
        String v0;
        try {
            v0 = this.mMediaCodec.getName();
        }
        catch(IllegalStateException v1) {
            Log.e("cr_MediaCodecBridge", "Cannot get codec name", new Object[]{v1});
        }

        return v0;
    }

    @SuppressLint(value={"NewApi"}) @CalledByNative protected ByteBuffer getOutputBuffer(int arg5) {
        if(Build$VERSION.SDK_INT > 19) {
            try {
                return this.mMediaCodec.getOutputBuffer(arg5);
            }
            catch(IllegalStateException v5) {
                Log.e("cr_MediaCodecBridge", "Failed to get output buffer", new Object[]{v5});
                return null;
            }
        }

        return this.mOutputBuffers[arg5];
    }

    @CalledByNative private GetOutputFormatResult getOutputFormat() {
        MediaFormat v2_1;
        int v0 = 0;
        org.chromium.media.MediaCodecBridge$1 v1 = null;
        try {
            v2_1 = this.mMediaCodec.getOutputFormat();
        }
        catch(IllegalStateException v2) {
            Log.e("cr_MediaCodecBridge", "Failed to get output format", new Object[]{v2});
            v0 = 5;
            v2_1 = ((MediaFormat)v1);
        }

        return new GetOutputFormatResult(v0, v2_1, v1);
    }

    @CalledByNative private int queueInputBuffer(int arg9, int arg10, int arg11, long arg12, int arg14) {
        this.resetLastPresentationTimeIfNeeded(arg12);
        try {
            this.mMediaCodec.queueInputBuffer(arg9, arg10, arg11, arg12, arg14);
            return 0;
        }
        catch(Exception v9) {
            Log.e("cr_MediaCodecBridge", "Failed to queue input buffer", new Object[]{v9});
            return 5;
        }
    }

    @SuppressLint(value={"WrongConstant"}) @CalledByNative private int queueSecureInputBuffer(int arg20, int arg21, byte[] arg22, byte[] arg23, int[] arg24, int[] arg25, int arg26, int arg27, int arg28, int arg29, long arg30) {
        MediaCodecBridge v1 = this;
        int v2 = arg28;
        int v3 = arg29;
        long v6 = arg30;
        v1.resetLastPresentationTimeIfNeeded(v6);
        int v10 = 5;
        int v4 = arg27;
        try {
            v4 = v1.translateCipherModeValue(v4);
            if(v4 == -1) {
                return v10;
            }

            int v5 = v4 == 2 ? 1 : 0;
            if(v5 != 0 && !MediaCodecUtil.platformSupportsCbcsEncryption(Build$VERSION.SDK_INT)) {
                Log.e("cr_MediaCodecBridge", "Encryption scheme \'cbcs\' not supported on this platform.", new Object[0]);
                return v10;
            }

            MediaCodec$CryptoInfo v8 = new MediaCodec$CryptoInfo();
            v8.set(arg26, arg24, arg25, arg23, arg22, v4);
            if(v2 != 0 && v3 != 0) {
                if(v5 != 0) {
                    MediaCodecUtil.setPatternIfSupported(v8, v2, v3);
                }
                else {
                    Log.e("cr_MediaCodecBridge", "Pattern encryption only supported for \'cbcs\' scheme (CBC mode).", new Object[0]);
                    return v10;
                }
            }

            v1.mMediaCodec.queueSecureInputBuffer(arg20, arg21, v8, v6, 0);
            return 0;
        }
        catch(IllegalStateException v0) {
            Log.e("cr_MediaCodecBridge", "Failed to queue secure input buffer, IllegalStateException " + v0, new Object[0]);
            return v10;
        }
        catch(IllegalArgumentException v0_1) {
            Log.e("cr_MediaCodecBridge", "Failed to queue secure input buffer, IllegalArgumentException " + v0_1, new Object[0]);
            return v10;
        }
        catch(MediaCodec$CryptoException v0_2) {
            MediaCodec$CryptoException v2_1 = v0_2;
            if(v2_1.getErrorCode() == 1) {
                Log.d("cr_MediaCodecBridge", "Failed to queue secure input buffer: CryptoException.ERROR_NO_KEY");
                return 4;
            }

            Log.e("cr_MediaCodecBridge", "Failed to queue secure input buffer, CryptoException with error code " + v2_1.getErrorCode(), new Object[0]);
            return v10;
        }
    }

    @CalledByNative void release() {
        try {
            String v1_1 = "unknown";
            if(Build$VERSION.SDK_INT >= 18) {
                v1_1 = this.mMediaCodec.getName();
            }

            Log.w("cr_MediaCodecBridge", "Releasing: " + v1_1, new Object[0]);
            this.mMediaCodec.release();
            Log.w("cr_MediaCodecBridge", "Codec released", new Object[0]);
        }
        catch(IllegalStateException v1) {
            Log.e("cr_MediaCodecBridge", "Cannot release media codec", new Object[]{v1});
        }

        this.mMediaCodec = null;
    }

    @CalledByNative protected void releaseOutputBuffer(int arg4, boolean arg5) {
        try {
            this.mMediaCodec.releaseOutputBuffer(arg4, arg5);
        }
        catch(IllegalStateException v4) {
            Log.e("cr_MediaCodecBridge", "Failed to release output buffer", new Object[]{v4});
        }
    }

    @TargetApi(value=19) @CalledByNative private void requestKeyFrameSoon() {
        Bundle v0 = new Bundle();
        v0.putInt("request-sync", 0);
        try {
            this.mMediaCodec.setParameters(v0);
        }
        catch(IllegalStateException v0_1) {
            Log.e("cr_MediaCodecBridge", "Failed to set MediaCodec parameters", new Object[]{v0_1});
        }
    }

    private void resetLastPresentationTimeIfNeeded(long arg5) {
        if(this.mFlushed) {
            this.mLastPresentationTimeUs = Math.max(arg5 - 100000, 0);
            this.mFlushed = false;
        }
    }

    @TargetApi(value=23) @CalledByNative private boolean setSurface(Surface arg5) {
        try {
            this.mMediaCodec.setOutputSurface(arg5);
            return 1;
        }
        catch(IllegalStateException v5) {
            Log.e("cr_MediaCodecBridge", "Cannot set output surface", new Object[]{v5});
            return 0;
        }
    }

    @TargetApi(value=19) @CalledByNative private void setVideoBitrate(int arg7, int arg8) {
        int v0 = this.mBitrateAdjuster.getTargetBitrate(arg7, arg8);
        Bundle v1 = new Bundle();
        v1.putInt("video-bitrate", v0);
        try {
            this.mMediaCodec.setParameters(v1);
        }
        catch(IllegalStateException v1_1) {
            Log.e("cr_MediaCodecBridge", "Failed to set MediaCodec parameters", new Object[]{v1_1});
        }

        Log.v("cr_MediaCodecBridge", "setVideoBitrate: input " + arg7 + "bps@" + arg8 + ", targetBps " + v0);
    }

    boolean start() {
        try {
            this.mMediaCodec.start();
            if(Build$VERSION.SDK_INT <= 19) {
                this.mInputBuffers = this.mMediaCodec.getInputBuffers();
                this.mOutputBuffers = this.mMediaCodec.getOutputBuffers();
            }
        }
        catch(IllegalArgumentException v2) {
            Log.e("cr_MediaCodecBridge", "Cannot start the media codec", new Object[]{v2});
            return 0;
        }
        catch(IllegalStateException v2_1) {
            Log.e("cr_MediaCodecBridge", "Cannot start the media codec", new Object[]{v2_1});
            return 0;
        }

        return 1;
    }

    @CalledByNative private void stop() {
        try {
            this.mMediaCodec.stop();
        }
        catch(IllegalStateException v0) {
            Log.e("cr_MediaCodecBridge", "Failed to stop MediaCodec", new Object[]{v0});
        }
    }

    private int translateCipherModeValue(int arg5) {
        switch(arg5) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
        }

        Log.e("cr_MediaCodecBridge", "Unsupported cipher mode: " + arg5, new Object[0]);
        return -1;
    }
}

