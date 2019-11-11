package org.chromium.media;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Build$VERSION;
import android.support.annotation.VisibleForTesting;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="media") @MainDex class HdrMetadata {
    private static final int MAX_CHROMATICITY = 50000;
    private static final String TAG = "HdrMetadata";
    private final Object mLock;
    private long mNativeJniHdrMetadata;

    static {
    }

    @VisibleForTesting HdrMetadata() {
        super();
        this.mLock = new Object();
        this.mNativeJniHdrMetadata = 0;
    }

    private HdrMetadata(long arg2) {
        super();
        this.mLock = new Object();
        this.mNativeJniHdrMetadata = arg2;
    }

    @TargetApi(value=24) public void addMetadataToFormat(MediaFormat arg6) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(Build$VERSION.SDK_INT < 24) {
                Log.e("HdrMetadata", "HDR not supported before Android N", new Object[0]);
                __monitor_exit(v0);
                return;
            }

            int v1 = this.getColorStandard();
            int v2 = -1;
            if(v1 != v2) {
                arg6.setInteger("color-standard", v1);
            }

            v1 = this.getColorTransfer();
            if(v1 != v2) {
                arg6.setInteger("color-transfer", v1);
            }

            v1 = this.getColorRange();
            if(v1 != v2) {
                arg6.setInteger("color-range", v1);
            }

            ByteBuffer v1_1 = ByteBuffer.wrap(new byte[25]);
            v1_1.order(ByteOrder.LITTLE_ENDIAN);
            v1_1.put(0);
            v1_1.putShort(((short)(((int)(this.primaryRChromaticityX() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.primaryRChromaticityY() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.primaryGChromaticityX() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.primaryGChromaticityY() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.primaryBChromaticityX() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.primaryBChromaticityY() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.whitePointChromaticityX() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.whitePointChromaticityY() * 50000f + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.maxMasteringLuminance() + 0.5f)))));
            v1_1.putShort(((short)(((int)(this.minMasteringLuminance() + 0.5f)))));
            v1_1.putShort(((short)this.maxContentLuminance()));
            v1_1.putShort(((short)this.maxFrameAverageLuminance()));
            v1_1.rewind();
            arg6.setByteBuffer("hdr-static-info", v1_1);
            __monitor_exit(v0);
            return;
        }
        catch(Throwable v6) {
        label_105:
            try {
                __monitor_exit(v0);
            }
            catch(Throwable v6) {
                goto label_105;
            }

            throw v6;
        }
    }

    @CalledByNative private void close() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        long v1 = 0;
        try {
            this.mNativeJniHdrMetadata = v1;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1_1) {
            goto label_7;
        }

        throw v1_1;
    }

    @CalledByNative private static HdrMetadata create(long arg1) {
        return new HdrMetadata(arg1);
    }

    private int getColorRange() {
        switch(this.nativeRange(this.mNativeJniHdrMetadata)) {
            case 1: {
                return 2;
            }
            case 2: {
                return 1;
            }
        }

        return -1;
    }

    private int getColorStandard() {
        int v0 = this.nativePrimaries(this.mNativeJniHdrMetadata);
        if(v0 == 1) {
            return 1;
        }

        if(v0 == 9) {
            return 6;
        }

        switch(v0) {
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                return 4;
            }
        }

        return -1;
    }

    private int getColorTransfer() {
        int v0 = this.nativeColorTransfer(this.mNativeJniHdrMetadata);
        if(v0 != 1) {
            if(v0 == 16) {
                return 6;
            }
            else if(v0 != 18) {
                switch(v0) {
                    case 6: 
                    case 7: {
                        return 3;
                    }
                    case 8: {
                        return 1;
                    }
                }

                return -1;
            }
            else {
                return 7;
            }
        }

        return 3;
    }

    private int maxContentLuminance() {
        return this.nativeMaxContentLuminance(this.mNativeJniHdrMetadata);
    }

    private int maxFrameAverageLuminance() {
        return this.nativeMaxFrameAverageLuminance(this.mNativeJniHdrMetadata);
    }

    private float maxMasteringLuminance() {
        return this.nativeMaxMasteringLuminance(this.mNativeJniHdrMetadata);
    }

    private float minMasteringLuminance() {
        return this.nativeMinMasteringLuminance(this.mNativeJniHdrMetadata);
    }

    private native int nativeColorTransfer(long arg1) {
    }

    private native int nativeMaxContentLuminance(long arg1) {
    }

    private native int nativeMaxFrameAverageLuminance(long arg1) {
    }

    private native float nativeMaxMasteringLuminance(long arg1) {
    }

    private native float nativeMinMasteringLuminance(long arg1) {
    }

    private native int nativePrimaries(long arg1) {
    }

    private native float nativePrimaryBChromaticityX(long arg1) {
    }

    private native float nativePrimaryBChromaticityY(long arg1) {
    }

    private native float nativePrimaryGChromaticityX(long arg1) {
    }

    private native float nativePrimaryGChromaticityY(long arg1) {
    }

    private native float nativePrimaryRChromaticityX(long arg1) {
    }

    private native float nativePrimaryRChromaticityY(long arg1) {
    }

    private native int nativeRange(long arg1) {
    }

    private native float nativeWhitePointChromaticityX(long arg1) {
    }

    private native float nativeWhitePointChromaticityY(long arg1) {
    }

    private float primaryBChromaticityX() {
        return this.nativePrimaryBChromaticityX(this.mNativeJniHdrMetadata);
    }

    private float primaryBChromaticityY() {
        return this.nativePrimaryBChromaticityY(this.mNativeJniHdrMetadata);
    }

    private float primaryGChromaticityX() {
        return this.nativePrimaryGChromaticityX(this.mNativeJniHdrMetadata);
    }

    private float primaryGChromaticityY() {
        return this.nativePrimaryGChromaticityY(this.mNativeJniHdrMetadata);
    }

    private float primaryRChromaticityX() {
        return this.nativePrimaryRChromaticityX(this.mNativeJniHdrMetadata);
    }

    private float primaryRChromaticityY() {
        return this.nativePrimaryRChromaticityY(this.mNativeJniHdrMetadata);
    }

    private float whitePointChromaticityX() {
        return this.nativeWhitePointChromaticityX(this.mNativeJniHdrMetadata);
    }

    private float whitePointChromaticityY() {
        return this.nativeWhitePointChromaticityY(this.mNativeJniHdrMetadata);
    }
}

