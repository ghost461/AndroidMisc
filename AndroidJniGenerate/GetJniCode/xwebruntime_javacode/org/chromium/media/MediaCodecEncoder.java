package org.chromium.media;

import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") class MediaCodecEncoder extends MediaCodecBridge {
    private static final String TAG = "cr_MediaCodecEncoder";
    private ByteBuffer mConfigData;
    private SparseArray mOutputBuffers;

    protected MediaCodecEncoder(MediaCodec arg1, BitrateAdjuster arg2) {
        super(arg1, arg2);
        this.mOutputBuffers = new SparseArray();
        this.mConfigData = null;
    }

    protected int dequeueOutputBufferInternal(MediaCodec$BufferInfo arg8, long arg9) {
        int v3;
        int v2 = -1;
        try {
            v3 = this.mMediaCodec.dequeueOutputBuffer(arg8, arg9);
            if(v3 >= 0) {
            }
            else {
                goto label_64;
            }
        }
        catch(IllegalStateException v8) {
            goto label_118;
        }

        try {
            v2 = (arg8.flags & 2) != 0 ? 1 : 0;
            if(v2 == 0) {
                goto label_64;
            }

            Log.d("cr_MediaCodecEncoder", "Config frame generated. Offset: %d, size: %d", Integer.valueOf(arg8.offset), Integer.valueOf(arg8.size));
            ByteBuffer v2_1 = this.getMediaCodecOutputBuffer(v3);
            v2_1.position(arg8.offset);
            v2_1.limit(arg8.offset + arg8.size);
            this.mConfigData = ByteBuffer.allocateDirect(arg8.size);
            this.mConfigData.put(v2_1);
            StringBuilder v2_2 = new StringBuilder();
            int v4;
            for(v4 = 0; true; ++v4) {
                int v6 = 8;
                if(arg8.size < v6) {
                    v6 = arg8.size;
                }

                if(v4 >= v6) {
                    break;
                }

                v2_2.append(Integer.toHexString(this.mConfigData.get(v4) & 0xFF));
                v2_2.append(" ");
            }

            Log.i("cr_MediaCodecEncoder", "spsData: %s", new Object[]{v2_2.toString()});
            this.mMediaCodec.releaseOutputBuffer(v3, false);
            v2 = this.mMediaCodec.dequeueOutputBuffer(arg8, arg9);
            goto label_65;
        }
        catch(IllegalStateException v8) {
            v2 = v3;
            goto label_118;
        }

    label_64:
        v2 = v3;
    label_65:
        if(v2 >= 0) {
            try {
                ByteBuffer v9 = this.getMediaCodecOutputBuffer(v2);
                v9.position(arg8.offset);
                v9.limit(arg8.offset + arg8.size);
                int v10 = (arg8.flags & 1) != 0 ? 1 : 0;
                if(v10 != 0) {
                    Log.d("cr_MediaCodecEncoder", "Key frame generated");
                }

                if(v10 != 0 && this.mConfigData != null) {
                    Log.d("cr_MediaCodecEncoder", "Appending config frame of size %d to output buffer with size %d", Integer.valueOf(this.mConfigData.capacity()), Integer.valueOf(arg8.size));
                    ByteBuffer v10_1 = ByteBuffer.allocateDirect(this.mConfigData.capacity() + arg8.size);
                    this.mConfigData.rewind();
                    v10_1.put(this.mConfigData);
                    v10_1.put(v9);
                    v10_1.rewind();
                    arg8.offset = 0;
                    arg8.size += this.mConfigData.capacity();
                    this.mOutputBuffers.put(v2, v10_1);
                    return v2;
                }

                this.mOutputBuffers.put(v2, v9);
                return v2;
            }
            catch(IllegalStateException v8) {
            }

        label_118:
            Log.e("cr_MediaCodecEncoder", "Failed to dequeue output buffer", new Object[]{v8});
        }

        return v2;
    }

    private ByteBuffer getMediaCodecOutputBuffer(int arg2) {
        ByteBuffer v2 = super.getOutputBuffer(arg2);
        if(v2 == null) {
            throw new IllegalStateException("Got null output buffer");
        }

        return v2;
    }

    protected ByteBuffer getOutputBuffer(int arg2) {
        return this.mOutputBuffers.get(arg2);
    }

    protected void releaseOutputBuffer(int arg4, boolean arg5) {
        try {
            this.mMediaCodec.releaseOutputBuffer(arg4, arg5);
            this.mOutputBuffers.remove(arg4);
        }
        catch(IllegalStateException v4) {
            Log.e("cr_MediaCodecEncoder", "Failed to release output buffer", new Object[]{v4});
        }
    }
}

