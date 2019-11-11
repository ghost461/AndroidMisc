package org.chromium.media;

import android.media.MediaCodecInfo$CodecProfileLevel;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@JNINamespace(value="media") @MainDex class CodecProfileLevelList {
    class org.chromium.media.CodecProfileLevelList$1 {
    }

    class CodecProfileLevelAdapter {
        private final int mCodec;
        private final int mLevel;
        private final int mProfile;

        public CodecProfileLevelAdapter(int arg1, int arg2, int arg3) {
            super();
            this.mCodec = arg1;
            this.mProfile = arg2;
            this.mLevel = arg3;
        }

        @CalledByNative(value="CodecProfileLevelAdapter") public int getCodec() {
            return this.mCodec;
        }

        @CalledByNative(value="CodecProfileLevelAdapter") public int getLevel() {
            return this.mLevel;
        }

        @CalledByNative(value="CodecProfileLevelAdapter") public int getProfile() {
            return this.mProfile;
        }
    }

    class UnsupportedCodecProfileException extends RuntimeException {
        UnsupportedCodecProfileException(org.chromium.media.CodecProfileLevelList$1 arg1) {
            this();
        }

        private UnsupportedCodecProfileException() {
            super();
        }
    }

    private static final String TAG = "CodecProfileLevelList";
    private final List mList;

    public CodecProfileLevelList() {
        super();
        this.mList = new ArrayList();
    }

    public boolean addCodecProfileLevel(int arg3, int arg4, int arg5) {
        this.mList.add(new CodecProfileLevelAdapter(arg3, arg4, arg5));
        return 1;
    }

    public boolean addCodecProfileLevel(String arg4, MediaCodecInfo$CodecProfileLevel arg5) {
        try {
            int v4 = CodecProfileLevelList.getCodecFromMime(arg4);
            this.mList.add(new CodecProfileLevelAdapter(v4, CodecProfileLevelList.mediaCodecProfileToChromiumMediaProfile(v4, arg5.profile), CodecProfileLevelList.mediaCodecLevelToChromiumMediaLevel(v4, arg5.level)));
            return 1;
        }
        catch(UnsupportedCodecProfileException ) {
            return 0;
        }
    }

    private static int getCodecFromMime(String arg1) {
        if(arg1.endsWith("vp9")) {
            return 7;
        }

        if(arg1.endsWith("vp8")) {
            return 6;
        }

        if(arg1.endsWith("avc")) {
            return 1;
        }

        if(arg1.endsWith("hevc")) {
            return 8;
        }

        throw new UnsupportedCodecProfileException(null);
    }

    private static int mediaCodecLevelToChromiumMediaLevel(int arg14, int arg15) {
        int v0 = 10;
        int v1 = 11;
        int v2 = 20;
        int v3 = 21;
        int v4 = 0x1F;
        int v5 = 40;
        int v6 = 41;
        int v7 = 50;
        int v8 = 51;
        int v9 = 52;
        int v11 = 30;
        org.chromium.media.CodecProfileLevelList$1 v12 = null;
        if(arg14 == 1) {
            goto label_82;
        }

        int v13 = 60;
        switch(arg14) {
            case 6: {
                goto label_67;
            }
            case 7: {
                goto label_47;
            }
            case 8: {
                goto label_19;
            }
        }

        throw new UnsupportedCodecProfileException(v12);
    label_67:
        if(arg15 == 4) {
            return 2;
        }

        if(arg15 == 8) {
            return 3;
        }

        switch(arg15) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
        }

        throw new UnsupportedCodecProfileException(v12);
        return 1;
    label_19:
        switch(arg15) {
            case 1: 
            case 2: {
                return v11;
            }
            case 4: 
            case 8: {
                return v13;
            }
            case 16: 
            case 32: {
                return 0x3F;
            }
            case 64: 
            case 128: {
                return 90;
            }
            case 256: 
            case 512: {
                return 93;
            }
            case 1024: 
            case 2048: {
                return 120;
            }
            case 4096: 
            case 8192: {
                return 0x7B;
            }
            case 16384: 
            case 32768: {
                return 150;
            }
            case 65536: 
            case 131072: {
                return 0x99;
            }
            case 262144: 
            case 524288: {
                return 0x9C;
            }
            case 1048576: 
            case 2097152: {
                return 180;
            }
            case 4194304: 
            case 8388608: {
                return 0xB7;
            }
            case 16777216: 
            case 33554432: {
                return 0xBA;
            }
        }

        throw new UnsupportedCodecProfileException(v12);
        return 150;
    label_47:
        switch(arg15) {
            case 1: {
                return v0;
            }
            case 2: {
                return v1;
            }
            case 4: {
                return v2;
            }
            case 8: {
                return v3;
            }
            case 16: {
                return v11;
            }
            case 32: {
                return v4;
            }
            case 64: {
                return v5;
            }
            case 128: {
                return v6;
            }
            case 256: {
                return v7;
            }
            case 512: {
                return v8;
            }
            case 1024: {
                return v9;
            }
            case 2048: {
                return v13;
            }
            case 4096: {
                return 61;
            }
            case 8192: {
                return 62;
            }
        }

        throw new UnsupportedCodecProfileException(v12);
        return v1;
    label_82:
        switch(arg15) {
            case 1: {
                return v0;
            }
            case 4: {
                return v1;
            }
            case 8: {
                return 12;
            }
            case 16: {
                return 13;
            }
            case 32: {
                return v2;
            }
            case 64: {
                return v3;
            }
            case 128: {
                return 22;
            }
            case 256: {
                return v11;
            }
            case 512: {
                return v4;
            }
            case 1024: {
                return 0x20;
            }
            case 2048: {
                return v5;
            }
            case 4096: {
                return v6;
            }
            case 8192: {
                return 42;
            }
            case 16384: {
                return v7;
            }
            case 32768: {
                return v8;
            }
            case 65536: {
                return v9;
            }
        }

        throw new UnsupportedCodecProfileException(v12);
        return 22;
    }

    private static int mediaCodecProfileToChromiumMediaProfile(int arg6, int arg7) {
        int v0 = 8;
        int v1 = 16;
        int v2 = 4;
        org.chromium.media.CodecProfileLevelList$1 v4 = null;
        if(arg6 == 1) {
            goto label_42;
        }

        int v5 = 0x1000;
        switch(arg6) {
            case 6: {
                goto label_36;
            }
            case 7: {
                goto label_19;
            }
            case 8: {
                goto label_11;
            }
        }

        throw new UnsupportedCodecProfileException(v4);
    label_19:
        if(arg7 != v2) {
            if(arg7 != v0) {
                if(arg7 == v5) {
                    return 14;
                }
                else if(arg7 != 0x2000) {
                    switch(arg7) {
                        case 1: {
                            return 12;
                        }
                        case 2: {
                            return 13;
                        }
                    }

                    throw new UnsupportedCodecProfileException(v4);
                    return 13;
                }
            }

            return 15;
        }

        return 14;
    label_36:
        if(arg7 != 1) {
            throw new UnsupportedCodecProfileException(v4);
        }

        return 11;
    label_11:
        if(arg7 != v5) {
            switch(arg7) {
                case 1: {
                    return v1;
                }
                case 2: {
                    return 17;
                }
            }

            throw new UnsupportedCodecProfileException(v4);
            return v1;
        }

        return 17;
    label_42:
        if(arg7 == v2) {
            return 2;
        }

        if(arg7 == v0) {
            return 3;
        }

        if(arg7 == v1) {
            return v2;
        }

        if(arg7 == 0x20) {
            return 5;
        }

        if(arg7 == 0x40) {
            return 6;
        }

        switch(arg7) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
        }

        throw new UnsupportedCodecProfileException(v4);
        return 1;
    }

    public Object[] toArray() {
        return this.mList.toArray();
    }
}

