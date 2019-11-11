package org.chromium.media;

import android.media.MediaFormat;
import android.os.Build;
import java.nio.ByteBuffer;

class MediaFormatBuilder {
    MediaFormatBuilder() {
        super();
    }

    private static void addInputSizeInfoToFormat(MediaFormat arg6, boolean arg7) {
        if(arg7) {
            arg6.setInteger("max-width", arg6.getInteger("width"));
            arg6.setInteger("max-height", arg6.getInteger("height"));
        }

        if(arg6.containsKey("max-input-size")) {
            return;
        }

        int v0 = arg6.getInteger("height");
        if((arg7) && (arg6.containsKey("max-height"))) {
            v0 = Math.max(v0, arg6.getInteger("max-height"));
        }

        int v1 = arg6.getInteger("width");
        if((arg7) && (arg6.containsKey("max-width"))) {
            v1 = Math.max(v0, arg6.getInteger("max-width"));
        }

        String v7 = arg6.getString("mime");
        int v2 = -1;
        switch(v7.hashCode()) {
            case 1331836730: {
                if(!v7.equals("video/avc")) {
                    goto label_58;
                }

                v2 = 0;
                break;
            }
            case 1599127256: {
                if(!v7.equals("video/x-vnd.on2.vp8")) {
                    goto label_58;
                }

                v2 = 1;
                break;
            }
            case 1599127257: {
                if(!v7.equals("video/x-vnd.on2.vp9")) {
                    goto label_58;
                }

                v2 = 3;
                break;
            }
            case -1662541442: {
                if(!v7.equals("video/hevc")) {
                    goto label_58;
                }

                v2 = 2;
                break;
            }
            default: {
                break;
            }
        }

    label_58:
        switch(v2) {
            case 0: {
                goto label_66;
            }
            case 1: {
                goto label_63;
            }
            case 2: 
            case 3: {
                goto label_60;
            }
        }

        return;
    label_66:
        if("BRAVIA 4K 2015".equals(Build.MODEL)) {
            return;
        }

        v1 = (v1 + 15) / 16 * ((v0 + 15) / 16) * 16 * 16;
        goto label_64;
    label_60:
        v1 *= v0;
        int v7_1 = 4;
        goto label_79;
    label_63:
        v1 *= v0;
    label_64:
        v7_1 = 2;
    label_79:
        arg6.setInteger("max-input-size", v1 * 3 / (v7_1 * 2));
    }

    public static MediaFormat createAudioFormat(String arg0, int arg1, int arg2, byte[][] arg3, boolean arg4) {
        MediaFormat v0 = MediaFormat.createAudioFormat(arg0, arg1, arg2);
        MediaFormatBuilder.setCodecSpecificData(v0, arg3);
        if(arg4) {
            v0.setInteger("is-adts", 1);
        }

        return v0;
    }

    public static MediaFormat createVideoDecoderFormat(String arg0, int arg1, int arg2, byte[][] arg3, HdrMetadata arg4, boolean arg5) {
        MediaFormat v0 = MediaFormat.createVideoFormat(arg0, arg1, arg2);
        if(v0 == null) {
            return null;
        }

        MediaFormatBuilder.setCodecSpecificData(v0, arg3);
        if(arg4 != null) {
            arg4.addMetadataToFormat(v0);
        }

        MediaFormatBuilder.addInputSizeInfoToFormat(v0, arg5);
        return v0;
    }

    public static MediaFormat createVideoEncoderFormat(String arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, boolean arg7) {
        MediaFormat v0 = MediaFormat.createVideoFormat(arg0, arg1, arg2);
        v0.setInteger("bitrate", arg3);
        v0.setInteger("frame-rate", arg4);
        v0.setInteger("i-frame-interval", arg5);
        v0.setInteger("color-format", arg6);
        MediaFormatBuilder.addInputSizeInfoToFormat(v0, arg7);
        return v0;
    }

    private static void setCodecSpecificData(MediaFormat arg3, byte[][] arg4) {
        int v0;
        for(v0 = 0; v0 < arg4.length; ++v0) {
            if(arg4[v0].length == 0) {
            }
            else {
                arg3.setByteBuffer("csd-" + v0, ByteBuffer.wrap(arg4[v0]));
            }
        }
    }
}

