package org.chromium.media;

import android.annotation.TargetApi;
import android.media.MediaCodec$CryptoInfo$Pattern;
import android.media.MediaCodec$CryptoInfo;
import android.media.MediaCodec;
import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo$CodecProfileLevel;
import android.media.MediaCodecInfo$VideoCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.os.Build$VERSION;
import android.os.Build;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="media") @MainDex class MediaCodecUtil {
    class org.chromium.media.MediaCodecUtil$1 {
    }

    public class CodecCreationInfo {
        public BitrateAdjuster bitrateAdjuster;
        public MediaCodec mediaCodec;
        public boolean supportsAdaptivePlayback;

        public CodecCreationInfo() {
            super();
            this.bitrateAdjuster = BitrateAdjuster.NO_ADJUSTMENT;
        }
    }

    enum HWEncoderProperties {
        public static final enum HWEncoderProperties ExynosH264;
        public static final enum HWEncoderProperties ExynosVp8;
        public static final enum HWEncoderProperties QcomH264;
        public static final enum HWEncoderProperties QcomVp8;
        private final BitrateAdjuster mBitrateAdjuster;
        private final String mMime;
        private final int mMinSDK;
        private final String mPrefix;

        static {
            HWEncoderProperties.QcomVp8 = new HWEncoderProperties("QcomVp8", 0, "video/x-vnd.on2.vp8", "OMX.qcom.", 19, BitrateAdjuster.NO_ADJUSTMENT);
            HWEncoderProperties.QcomH264 = new HWEncoderProperties("QcomH264", 1, "video/avc", "OMX.qcom.", 19, BitrateAdjuster.NO_ADJUSTMENT);
            HWEncoderProperties.ExynosVp8 = new HWEncoderProperties("ExynosVp8", 2, "video/x-vnd.on2.vp8", "OMX.Exynos.", 23, BitrateAdjuster.NO_ADJUSTMENT);
            HWEncoderProperties.ExynosH264 = new HWEncoderProperties("ExynosH264", 3, "video/avc", "OMX.Exynos.", 21, BitrateAdjuster.FRAMERATE_ADJUSTMENT);
            HWEncoderProperties.$VALUES = new HWEncoderProperties[]{HWEncoderProperties.QcomVp8, HWEncoderProperties.QcomH264, HWEncoderProperties.ExynosVp8, HWEncoderProperties.ExynosH264};
        }

        private HWEncoderProperties(String arg1, int arg2, String arg3, String arg4, int arg5, BitrateAdjuster arg6) {
            super(arg1, arg2);
            this.mMime = arg3;
            this.mPrefix = arg4;
            this.mMinSDK = arg5;
            this.mBitrateAdjuster = arg6;
        }

        public BitrateAdjuster getBitrateAdjuster() {
            return this.mBitrateAdjuster;
        }

        public String getMime() {
            return this.mMime;
        }

        public int getMinSDK() {
            return this.mMinSDK;
        }

        public String getPrefix() {
            return this.mPrefix;
        }

        public static HWEncoderProperties valueOf(String arg1) {
            return Enum.valueOf(HWEncoderProperties.class, arg1);
        }

        public static HWEncoderProperties[] values() {
            return HWEncoderProperties.$VALUES.clone();
        }
    }

    class MediaCodecListHelper implements Iterable {
        class CodecInfoIterator implements Iterator {
            private int mPosition;

            CodecInfoIterator(MediaCodecListHelper arg1, org.chromium.media.MediaCodecUtil$1 arg2) {
                this(arg1);
            }

            private CodecInfoIterator(MediaCodecListHelper arg1) {
                MediaCodecListHelper.this = arg1;
                super();
                this.mPosition = 0;
            }

            public boolean hasNext() {
                boolean v0 = this.mPosition < MediaCodecListHelper.this.getCodecCount() ? true : false;
                return v0;
            }

            public MediaCodecInfo next() {
                if(this.mPosition == MediaCodecListHelper.this.getCodecCount()) {
                    throw new NoSuchElementException();
                }

                MediaCodecListHelper v0 = MediaCodecListHelper.this;
                int v1 = this.mPosition;
                this.mPosition = v1 + 1;
                return v0.getCodecInfoAt(v1);
            }

            public Object next() {
                return this.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        private MediaCodecInfo[] mCodecList;

        @TargetApi(value=21) public MediaCodecListHelper() {
            super();
            if(MediaCodecListHelper.supportsNewMediaCodecList()) {
                try {
                    this.mCodecList = new MediaCodecList(1).getCodecInfos();
                    return;
                }
                catch(RuntimeException ) {
                    return;
                }
            }
        }

        static int access$100(MediaCodecListHelper arg0) {
            return arg0.getCodecCount();
        }

        static MediaCodecInfo access$200(MediaCodecListHelper arg0, int arg1) {
            return arg0.getCodecInfoAt(arg1);
        }

        private int getCodecCount() {
            if(this.hasNewMediaCodecList()) {
                return this.mCodecList.length;
            }

            try {
                return MediaCodecList.getCodecCount();
            }
            catch(RuntimeException ) {
                return 0;
            }
        }

        private MediaCodecInfo getCodecInfoAt(int arg2) {
            if(this.hasNewMediaCodecList()) {
                return this.mCodecList[arg2];
            }

            return MediaCodecList.getCodecInfoAt(arg2);
        }

        private boolean hasNewMediaCodecList() {
            boolean v0 = !MediaCodecListHelper.supportsNewMediaCodecList() || this.mCodecList == null ? false : true;
            return v0;
        }

        public Iterator iterator() {
            return new CodecInfoIterator(this, null);
        }

        private static boolean supportsNewMediaCodecList() {
            boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
            return v0;
        }
    }

    public final class MimeTypes {
        public static final String VIDEO_H264 = "video/avc";
        public static final String VIDEO_H265 = "video/hevc";
        public static final String VIDEO_MP4 = "video/mp4";
        public static final String VIDEO_VP8 = "video/x-vnd.on2.vp8";
        public static final String VIDEO_VP9 = "video/x-vnd.on2.vp9";
        public static final String VIDEO_WEBM = "video/webm";

        public MimeTypes() {
            super();
        }
    }

    private static final String[] H264_ENCODER_MODEL_BLACKLIST = null;
    private static final String TAG = "cr_MediaCodecUtil";

    static {
        MediaCodecUtil.H264_ENCODER_MODEL_BLACKLIST = new String[]{"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    }

    MediaCodecUtil() {
        super();
    }

    @TargetApi(value=21) private static void addVp9CodecProfileLevels(CodecProfileLevelList arg9, MediaCodecInfo$CodecCapabilities arg10) {
        int[][] v0 = new int[11][];
        v0[0] = new int[]{200, 10};
        v0[1] = new int[]{800, 11};
        v0[2] = new int[]{1800, 20};
        v0[3] = new int[]{3600, 21};
        v0[4] = new int[]{7200, 30};
        v0[5] = new int[]{12000, 0x1F};
        v0[6] = new int[]{18000, 40};
        int v5 = 7;
        v0[v5] = new int[]{30000, 41};
        v0[8] = new int[]{60000, 50};
        v0[9] = new int[]{120000, 51};
        v0[10] = new int[]{180000, 52};
        MediaCodecInfo$VideoCapabilities v10 = arg10.getVideoCapabilities();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            int[] v6 = v0[v2];
            int v7 = v6[0];
            int v6_1 = v6[1];
            if(v10.getBitrateRange().contains(Integer.valueOf(v7))) {
                arg9.addCodecProfileLevel(v5, 12, v6_1);
            }
        }
    }

    @CalledByNative private static boolean canDecode(String arg4, boolean arg5) {
        CodecCreationInfo v4 = MediaCodecUtil.createDecoder(arg4, ((int)arg5));
        if(v4.mediaCodec == null) {
            return 0;
        }

        try {
            v4.mediaCodec.release();
        }
        catch(IllegalStateException v4_1) {
            Log.e("cr_MediaCodecUtil", "Cannot release media codec", new Object[]{v4_1});
        }

        return 1;
    }

    @TargetApi(value=19) private static boolean codecSupportsAdaptivePlayback(MediaCodec arg3, String arg4) {
        if(Build$VERSION.SDK_INT >= 19) {
            if(arg3 == null) {
            }
            else {
                boolean v0 = true;
                try {
                    MediaCodecInfo v3_1 = arg3.getCodecInfo();
                    if(v3_1.isEncoder()) {
                        return 0;
                    }
                    else if(MediaCodecUtil.isAdaptivePlaybackBlacklisted(arg4)) {
                        return 0;
                    }
                    else {
                        MediaCodecInfo$CodecCapabilities v3_2 = v3_1.getCapabilitiesForType(arg4);
                        if(v3_2 != null) {
                            if(!v3_2.isFeatureSupported("adaptive-playback")) {
                                return false;
                            }

                            return v0;
                        }
                        else {
                            return false;
                        }
                    }

                    return 0;
                }
                catch(IllegalArgumentException v3) {
                    Log.e("cr_MediaCodecUtil", "Cannot retrieve codec information", new Object[]{v3});
                    return 0;
                }

                return v0;
            }
        }

        return 0;
    }

    static CodecCreationInfo createDecoder(String arg8, int arg9, MediaCrypto arg10) {
        CodecCreationInfo v0 = new CodecCreationInfo();
        if(arg9 == 1 && Build$VERSION.SDK_INT < 18) {
            return v0;
        }

        if(!MediaCodecUtil.isDecoderSupportedForDevice(arg8)) {
            Log.e("cr_MediaCodecUtil", "Decoder for type %s is not supported on this device", new Object[]{arg8});
            return v0;
        }

        int v2 = 2;
        CodecCreationInfo v4 = null;
        try {
            if((arg8.startsWith("video")) && arg9 == 1 || (arg8.startsWith("audio")) && arg10 != null && (arg10.requiresSecureDecoderComponent(arg8))) {
                String v10_1 = MediaCodecUtil.getDefaultCodecName(arg8, 0, false);
                if(v10_1.equals("")) {
                    return v4;
                }
                else {
                    if(Build$VERSION.SDK_INT >= 19) {
                        MediaCodec v5 = MediaCodec.createByCodecName(v10_1);
                        v0.supportsAdaptivePlayback = MediaCodecUtil.codecSupportsAdaptivePlayback(v5, arg8);
                        v5.release();
                    }

                    StringBuilder v5_1 = new StringBuilder();
                    v5_1.append(v10_1);
                    v5_1.append(".secure");
                    v0.mediaCodec = MediaCodec.createByCodecName(v5_1.toString());
                    return v0;
                }
            }

            if(arg9 == v2) {
                v0.mediaCodec = MediaCodec.createByCodecName(MediaCodecUtil.getDefaultCodecName(arg8, 0, true));
            }
            else if(arg8.equals("audio/raw")) {
                v0.mediaCodec = MediaCodec.createByCodecName("OMX.google.raw.decoder");
            }
            else {
                v0.mediaCodec = MediaCodec.createDecoderByType(arg8);
            }

            v0.supportsAdaptivePlayback = MediaCodecUtil.codecSupportsAdaptivePlayback(v0.mediaCodec, arg8);
        }
        catch(Exception v10) {
            Object[] v7 = new Object[3];
            v7[0] = arg8;
            v7[1] = Integer.valueOf(arg9);
            v7[v2] = v10;
            Log.e("cr_MediaCodecUtil", "Failed to create MediaCodec: %s, codecType: %d", v7);
            v0.mediaCodec = ((MediaCodec)v4);
        }

        return v0;
    }

    static CodecCreationInfo createDecoder(String arg1, int arg2) {
        return MediaCodecUtil.createDecoder(arg1, arg2, null);
    }

    static CodecCreationInfo createEncoder(String arg6) {
        CodecCreationInfo v0 = new CodecCreationInfo();
        HWEncoderProperties v1 = MediaCodecUtil.findHWEncoder(arg6);
        if(v1 == null) {
            return v0;
        }

        try {
            v0.mediaCodec = MediaCodec.createEncoderByType(arg6);
            v0.supportsAdaptivePlayback = false;
            v0.bitrateAdjuster = v1.getBitrateAdjuster();
        }
        catch(Exception v1_1) {
            Log.e("cr_MediaCodecUtil", "Failed to create MediaCodec: %s", new Object[]{arg6, v1_1});
        }

        return v0;
    }

    private static HWEncoderProperties findHWEncoder(String arg9) {
        Object v1;
        String v2;
        Iterator v0 = new MediaCodecListHelper().iterator();
        while(true) {
        label_3:
            v2 = null;
            if(!v0.hasNext()) {
                goto label_70;
            }

            v1 = v0.next();
            if(!((MediaCodecInfo)v1).isEncoder()) {
                continue;
            }

            if(!MediaCodecUtil.isSoftwareCodec(((MediaCodecInfo)v1).getName())) {
                break;
            }
        }

        String[] v4 = ((MediaCodecInfo)v1).getSupportedTypes();
        int v5 = v4.length;
        int v6 = 0;
        goto label_17;
    label_70:
        Log.w("cr_MediaCodecUtil", "HW encoder for " + arg9 + " is not available on this device.", new Object[0]);
        return ((HWEncoderProperties)v2);
    label_17:
        while(v6 < v5) {
            if(v4[v6].equalsIgnoreCase(arg9)) {
                v2 = ((MediaCodecInfo)v1).getName();
            }
            else {
                ++v6;
                continue;
            }

            break;
        }

        if(v2 == null) {
            goto label_3;
        }

        HWEncoderProperties[] v1_2 = HWEncoderProperties.values();
        int v4_1 = v1_2.length;
        for(v5 = 0; true; ++v5) {
            if(v5 >= v4_1) {
                goto label_3;
            }

            HWEncoderProperties v6_1 = v1_2[v5];
            if(!arg9.equalsIgnoreCase(v6_1.getMime())) {
            }
            else if(v2.startsWith(v6_1.getPrefix())) {
                if(Build$VERSION.SDK_INT < v6_1.getMinSDK()) {
                    Log.w("cr_MediaCodecUtil", "Codec " + v2 + " is disabled due to SDK version " + Build$VERSION.SDK_INT, new Object[0]);
                }
                else {
                    Log.d("cr_MediaCodecUtil", "Found target encoder for mime " + arg9 + " : " + v2);
                    return v6_1;
                }
            }
        }
    }

    @CalledByNative private static String getDefaultCodecName(String arg6, int arg7, boolean arg8) {
        Object v1;
        int v2;
        Iterator v0 = new MediaCodecListHelper().iterator();
        while(true) {
        label_3:
            v2 = 0;
            if(!v0.hasNext()) {
                goto label_25;
            }

            v1 = v0.next();
            if(((MediaCodecInfo)v1).isEncoder() != arg7) {
                continue;
            }

            if((arg8) && !MediaCodecUtil.isSoftwareCodec(((MediaCodecInfo)v1).getName())) {
                continue;
            }

            break;
        }

        String[] v3 = ((MediaCodecInfo)v1).getSupportedTypes();
        int v4 = v3.length;
        goto label_17;
    label_25:
        Log.e("cr_MediaCodecUtil", "Decoder for type %s is not supported on this device", new Object[]{arg6});
        return "";
        while(true) {
        label_17:
            if(v2 >= v4) {
                goto label_3;
            }

            if(v3[v2].equalsIgnoreCase(arg6)) {
                return ((MediaCodecInfo)v1).getName();
            }

            ++v2;
        }
    }

    @CalledByNative private static int[] getEncoderColorFormatsForMime(String arg7) {
        Object v1;
        Iterator v0 = new MediaCodecListHelper().iterator();
        while(true) {
        label_3:
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
            if(((MediaCodecInfo)v1).isEncoder()) {
                break;
            }
        }

        String[] v2 = ((MediaCodecInfo)v1).getSupportedTypes();
        int v3 = v2.length;
        int v4 = 0;
        goto label_12;
        return null;
        while(true) {
        label_12:
            if(v4 >= v3) {
                goto label_3;
            }

            String v5 = v2[v4];
            if(v5.equalsIgnoreCase(arg7)) {
                return ((MediaCodecInfo)v1).getCapabilitiesForType(v5).colorFormats;
            }

            ++v4;
        }
    }

    @CalledByNative private static Object[] getSupportedCodecProfileLevels() {
        CodecProfileLevelList v0 = new CodecProfileLevelList();
        Iterator v1 = new MediaCodecListHelper().iterator();
    label_5:
        if(v1.hasNext()) {
            Object v2 = v1.next();
            String[] v3 = ((MediaCodecInfo)v2).getSupportedTypes();
            int v4 = v3.length;
            int v6;
            for(v6 = 0; true; ++v6) {
                if(v6 >= v4) {
                    goto label_5;
                }

                String v7 = v3[v6];
                if(!MediaCodecUtil.isDecoderSupportedForDevice(v7)) {
                    Log.w("cr_MediaCodecUtil", "Decoder for type %s disabled on this device", new Object[]{v7});
                }
                else {
                    MediaCodecInfo$CodecCapabilities v8 = ((MediaCodecInfo)v2).getCapabilitiesForType(v7);
                    if((v7.endsWith("vp9")) && 21 <= Build$VERSION.SDK_INT && Build$VERSION.SDK_INT <= 23) {
                        MediaCodecUtil.addVp9CodecProfileLevels(v0, v8);
                        goto label_43;
                    }

                    MediaCodecInfo$CodecProfileLevel[] v8_1 = v8.profileLevels;
                    int v9 = v8_1.length;
                    int v10;
                    for(v10 = 0; v10 < v9; ++v10) {
                        v0.addCodecProfileLevel(v7, v8_1[v10]);
                    }
                }

            label_43:
            }
        }

        return v0.toArray();
    }

    private static boolean isAdaptivePlaybackBlacklisted(String arg2) {
        boolean v1 = false;
        if(!arg2.equals("video/avc") && !arg2.equals("video/avc1")) {
            return 0;
        }

        if(!Build$VERSION.RELEASE.equals("4.4.2")) {
            return 0;
        }

        if(!Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung")) {
            return 0;
        }

        if((Build.MODEL.startsWith("GT-I9300")) || (Build.MODEL.startsWith("SCH-I535"))) {
            v1 = true;
        }

        return v1;
    }

    @CalledByNative static boolean isDecoderSupportedForDevice(String arg4) {
        int v1 = 19;
        int v2 = 21;
        if(!arg4.equals("video/x-vnd.on2.vp8")) {
            if(arg4.equals("video/x-vnd.on2.vp9")) {
                if(Build$VERSION.SDK_INT < v1) {
                    return 0;
                }

                if(Build$VERSION.SDK_INT < v2 && (Build.HARDWARE.startsWith("mt"))) {
                    return 0;
                }

                if(!Build.MODEL.equals("Nexus Player")) {
                    return 1;
                }

                return 0;
            }

            if(!arg4.equals("audio/opus")) {
                return 1;
            }

            if(Build$VERSION.SDK_INT >= v2) {
                return 1;
            }

            return 0;
        }
        else if(Build$VERSION.SDK_INT < 18) {
            return 0;
        }
        else {
            if(Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung")) {
                if(Build$VERSION.SDK_INT < v2 && ((Build.MODEL.startsWith("GT-I9505")) || (Build.MODEL.startsWith("GT-I9500")))) {
                    return 0;
                }

                if(!Build.MODEL.startsWith("GT-I9190")) {
                    if(Build.MODEL.startsWith("GT-I9195")) {
                    }
                    else if(Build$VERSION.SDK_INT > v1) {
                        goto label_59;
                    }
                    else if(Build.MODEL.startsWith("GT-")) {
                        return 0;
                    }
                    else if(Build.MODEL.startsWith("SCH-")) {
                        return 0;
                    }
                    else if(Build.MODEL.startsWith("SM-T")) {
                        return 0;
                    }
                    else if(Build.MODEL.startsWith("SM-G")) {
                        return 0;
                    }
                    else {
                        goto label_59;
                    }
                }

                return 0;
            }

        label_59:
            if(Build.HARDWARE.startsWith("mt")) {
                return 0;
            }

            if(Build$VERSION.SDK_INT > v1) {
                return 1;
            }

            if(!Build.MODEL.startsWith("Lenovo A6000")) {
                return 1;
            }

            return 0;
        }

        return 1;
    }

    @CalledByNative static boolean isEncoderSupportedByDevice(String arg3) {
        if(Build$VERSION.SDK_INT < 19) {
            return 0;
        }

        if((arg3.equals("video/avc")) && (Arrays.asList(MediaCodecUtil.H264_ENCODER_MODEL_BLACKLIST).contains(Build.MODEL))) {
            Log.w("cr_MediaCodecUtil", "Model: " + Build.MODEL + " has blacklisted H.264 encoder.", new Object[0]);
            return 0;
        }

        if(MediaCodecUtil.findHWEncoder(arg3) == null) {
            return 0;
        }

        return 1;
    }

    @CalledByNative static boolean isSetOutputSurfaceSupported() {
        boolean v0 = Build$VERSION.SDK_INT < 23 || (Build.HARDWARE.equalsIgnoreCase("hi6210sft")) || (Build.HARDWARE.equalsIgnoreCase("hi6250")) ? false : true;
        return v0;
    }

    public static boolean isSoftwareCodec(String arg2) {
        if(arg2.startsWith("OMX.google.")) {
            return 1;
        }

        if(arg2.startsWith("OMX.")) {
            return 0;
        }

        return 1;
    }

    @CalledByNative static boolean platformSupportsCbcsEncryption(int arg1) {
        boolean v1 = arg1 >= 25 ? true : false;
        return v1;
    }

    static void setPatternIfSupported(MediaCodec$CryptoInfo arg2, int arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 24) {
            arg2.setPattern(new MediaCodec$CryptoInfo$Pattern(arg3, arg4));
        }
    }
}

