package org.chromium.media;

import android.media.MediaCrypto;
import android.view.Surface;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="media") @MainDex class MediaCodecBridgeBuilder {
    private static final String TAG = "cr_MediaCodecBridge";

    MediaCodecBridgeBuilder() {
        super();
    }

    @CalledByNative static MediaCodecBridge createAudioDecoder(String arg8, MediaCrypto arg9, int arg10, int arg11, byte[] arg12, byte[] arg13, byte[] arg14, boolean arg15) {
        CodecCreationInfo v0;
        new CodecCreationInfo();
        int v1 = 2;
        try {
            Log.i("cr_MediaCodecBridge", "create MediaCodec audio decoder, mime %s", new Object[]{arg8});
            v0 = MediaCodecUtil.createDecoder(arg8, 0, arg9);
        }
        catch(Exception v4) {
            Object[] v7 = new Object[v1];
            v7[0] = arg8;
            v7[1] = v4;
            Log.e("cr_MediaCodecBridge", "Failed to create MediaCodec audio decoder: %s", v7);
        }

        MediaCodecBridge v5 = null;
        if(v0.mediaCodec == null) {
            return v5;
        }

        MediaCodecBridge v4_1 = new MediaCodecBridge(v0.mediaCodec, v0.bitrateAdjuster);
        byte[][] v0_1 = new byte[3][];
        v0_1[0] = arg12;
        v0_1[1] = arg13;
        v0_1[v1] = arg14;
        if(!v4_1.configureAudio(MediaFormatBuilder.createAudioFormat(arg8, arg10, arg11, v0_1, arg15), arg9, 0)) {
            return v5;
        }

        if(!v4_1.start()) {
            v4_1.release();
            return v5;
        }

        return v4_1;
    }

    @CalledByNative static MediaCodecBridge createVideoDecoder(String arg11, int arg12, MediaCrypto arg13, int arg14, int arg15, Surface arg16, byte[] arg17, byte[] arg18, HdrMetadata arg19, boolean arg20) {
        CodecCreationInfo v5;
        CodecCreationInfo v2 = new CodecCreationInfo();
        int v3 = 2;
        try {
            Log.i("cr_MediaCodecBridge", "create MediaCodec video decoder, mime %s", new Object[]{arg11});
            v5 = MediaCodecUtil.createDecoder(arg11, arg12, arg13);
        }
        catch(Exception v0) {
            Object[] v9 = new Object[3];
            v9[0] = arg11;
            v9[1] = Integer.valueOf(arg12);
            v9[v3] = v0;
            Log.e("cr_MediaCodecBridge", "Failed to create MediaCodec video decoder: %s, codecType: %d", v9);
            v5 = v2;
        }

        MediaCodecBridge v8 = null;
        if(v5.mediaCodec == null) {
            return v8;
        }

        MediaCodecBridge v9_1 = new MediaCodecBridge(v5.mediaCodec, v5.bitrateAdjuster);
        byte[][] v6 = new byte[v3][];
        v6[0] = arg17;
        v6[1] = arg18;
        boolean v10 = !v5.supportsAdaptivePlayback || !arg20 ? false : true;
        if(!v9_1.configureVideo(MediaFormatBuilder.createVideoDecoderFormat(arg11, arg14, arg15, v6, arg19, v10), arg16, arg13, 0)) {
            return v8;
        }

        if(!v9_1.start()) {
            v9_1.release();
            return v8;
        }

        return v9_1;
    }

    @CalledByNative static MediaCodecBridge createVideoEncoder(String arg12, int arg13, int arg14, int arg15, int arg16, int arg17, int arg18) {
        MediaCodecBridge v3_1;
        CodecCreationInfo v2;
        String v1 = arg12;
        new CodecCreationInfo();
        try {
            Log.i("cr_MediaCodecBridge", "create MediaCodec video encoder, mime %s", new Object[]{v1});
            v2 = MediaCodecUtil.createEncoder(v1);
        }
        catch(Exception v0) {
            Log.e("cr_MediaCodecBridge", "Failed to create MediaCodec video encoder: %s", new Object[]{v1, v0});
        }

        MediaCodecBridge v10 = null;
        if(v2.mediaCodec == null) {
            return v10;
        }

        if(v1.equals("video/avc")) {
            MediaCodecEncoder v3 = new MediaCodecEncoder(v2.mediaCodec, v2.bitrateAdjuster);
        }
        else {
            v3_1 = new MediaCodecBridge(v2.mediaCodec, v2.bitrateAdjuster);
        }

        MediaCodecEncoder v11 = ((MediaCodecEncoder)v3_1);
        if(!((MediaCodecBridge)v11).configureVideo(MediaFormatBuilder.createVideoEncoderFormat(v1, arg13, arg14, arg15, v2.bitrateAdjuster.getInitialFrameRate(arg16), arg17, arg18, v2.supportsAdaptivePlayback), ((Surface)v10), ((MediaCrypto)v10), 1)) {
            return v10;
        }

        if(!((MediaCodecBridge)v11).start()) {
            ((MediaCodecBridge)v11).release();
            return v10;
        }

        return ((MediaCodecBridge)v11);
    }
}

