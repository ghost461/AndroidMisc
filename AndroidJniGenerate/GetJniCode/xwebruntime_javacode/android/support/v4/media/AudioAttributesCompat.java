package android.support.v4.media;

import android.media.AudioAttributes$Builder;
import android.media.AudioAttributes;
import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public class AudioAttributesCompat {
    class android.support.v4.media.AudioAttributesCompat$1 {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface AttributeContentType {
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface AttributeUsage {
    }

    abstract class AudioManagerHidden {
        public static final int STREAM_ACCESSIBILITY = 10;
        public static final int STREAM_BLUETOOTH_SCO = 6;
        public static final int STREAM_SYSTEM_ENFORCED = 7;
        public static final int STREAM_TTS = 9;

        private AudioManagerHidden() {
            super();
        }
    }

    public class Builder {
        private Object mAAObject;
        private int mContentType;
        private int mFlags;
        private Integer mLegacyStream;
        private int mUsage;

        public Builder() {
            super();
            this.mUsage = 0;
            this.mContentType = 0;
            this.mFlags = 0;
        }

        public Builder(AudioAttributesCompat arg2) {
            super();
            this.mUsage = 0;
            this.mContentType = 0;
            this.mFlags = 0;
            this.mUsage = arg2.mUsage;
            this.mContentType = arg2.mContentType;
            this.mFlags = arg2.mFlags;
            this.mLegacyStream = arg2.mLegacyStream;
            this.mAAObject = arg2.unwrap();
        }

        public AudioAttributesCompat build() {
            if(!AudioAttributesCompat.sForceLegacyBehavior && Build$VERSION.SDK_INT >= 21) {
                if(this.mAAObject != null) {
                    return AudioAttributesCompat.wrap(this.mAAObject);
                }
                else {
                    AudioAttributes$Builder v0 = new AudioAttributes$Builder().setContentType(this.mContentType).setFlags(this.mFlags).setUsage(this.mUsage);
                    if(this.mLegacyStream != null) {
                        v0.setLegacyStreamType(this.mLegacyStream.intValue());
                    }

                    return AudioAttributesCompat.wrap(v0.build());
                }
            }

            AudioAttributesCompat v0_1 = new AudioAttributesCompat(null);
            v0_1.mContentType = this.mContentType;
            v0_1.mFlags = this.mFlags;
            v0_1.mUsage = this.mUsage;
            v0_1.mLegacyStream = this.mLegacyStream;
            v0_1.mAudioAttributesWrapper = null;
            return v0_1;
        }

        public Builder setContentType(int arg1) {
            switch(arg1) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: {
                    this.mContentType = arg1;
                    break;
                }
                default: {
                    this.mUsage = 0;
                    break;
                }
            }

            return this;
        }

        public Builder setFlags(int arg2) {
            this.mFlags |= arg2 & 0x3FF;
            return this;
        }

        public Builder setLegacyStreamType(int arg2) {
            if(arg2 == 10) {
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            }

            this.mLegacyStream = Integer.valueOf(arg2);
            this.mUsage = AudioAttributesCompat.usageForStreamType(arg2);
            return this;
        }

        public Builder setUsage(int arg3) {
            switch(arg3) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: {
                    this.mUsage = arg3;
                    break;
                }
                case 16: {
                    if(!AudioAttributesCompat.sForceLegacyBehavior && Build$VERSION.SDK_INT > 25) {
                        this.mUsage = arg3;
                        return this;
                    }

                    this.mUsage = 12;
                    break;
                }
                default: {
                    this.mUsage = 0;
                    break;
                }
            }

            return this;
        }
    }

    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    private static final int FLAG_ALL = 0x3FF;
    private static final int FLAG_ALL_PUBLIC = 273;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    private static final int FLAG_BEACON = 8;
    private static final int FLAG_BYPASS_INTERRUPTION_POLICY = 0x40;
    private static final int FLAG_BYPASS_MUTE = 0x80;
    private static final int FLAG_DEEP_BUFFER = 0x200;
    public static final int FLAG_HW_AV_SYNC = 16;
    private static final int FLAG_HW_HOTWORD = 0x20;
    private static final int FLAG_LOW_LATENCY = 0x100;
    private static final int FLAG_SCO = 4;
    private static final int FLAG_SECURE = 2;
    private static final int[] SDK_USAGES = null;
    private static final int SUPPRESSIBLE_CALL = 2;
    private static final int SUPPRESSIBLE_NOTIFICATION = 1;
    private static final SparseIntArray SUPPRESSIBLE_USAGES = null;
    private static final String TAG = "AudioAttributesCompat";
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    private static final int USAGE_VIRTUAL_SOURCE = 15;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    private Wrapper mAudioAttributesWrapper;
    int mContentType;
    int mFlags;
    Integer mLegacyStream;
    int mUsage;
    private static boolean sForceLegacyBehavior;

    static {
        AudioAttributesCompat.SUPPRESSIBLE_USAGES = new SparseIntArray();
        AudioAttributesCompat.SUPPRESSIBLE_USAGES.put(5, 1);
        AudioAttributesCompat.SUPPRESSIBLE_USAGES.put(6, 2);
        AudioAttributesCompat.SUPPRESSIBLE_USAGES.put(7, 2);
        AudioAttributesCompat.SUPPRESSIBLE_USAGES.put(8, 1);
        AudioAttributesCompat.SUPPRESSIBLE_USAGES.put(9, 1);
        AudioAttributesCompat.SUPPRESSIBLE_USAGES.put(10, 1);
        AudioAttributesCompat.SDK_USAGES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    }

    private AudioAttributesCompat() {
        super();
        this.mUsage = 0;
        this.mContentType = 0;
        this.mFlags = 0;
    }

    AudioAttributesCompat(android.support.v4.media.AudioAttributesCompat$1 arg1) {
        this();
    }

    static boolean access$000() {
        return AudioAttributesCompat.sForceLegacyBehavior;
    }

    static Wrapper access$202(AudioAttributesCompat arg0, Wrapper arg1) {
        arg0.mAudioAttributesWrapper = arg1;
        return arg1;
    }

    static int access$300(int arg0) {
        return AudioAttributesCompat.usageForStreamType(arg0);
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((AudioAttributesCompat)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(Build$VERSION.SDK_INT >= 21 && !AudioAttributesCompat.sForceLegacyBehavior && this.mAudioAttributesWrapper != null) {
                    return this.mAudioAttributesWrapper.unwrap().equals(((AudioAttributesCompat)arg5).unwrap());
                }

                if(this.mContentType != ((AudioAttributesCompat)arg5).getContentType() || this.mFlags != ((AudioAttributesCompat)arg5).getFlags() || this.mUsage != ((AudioAttributesCompat)arg5).getUsage()) {
                label_40:
                    v0 = false;
                }
                else if(this.mLegacyStream != null) {
                    if(this.mLegacyStream.equals(((AudioAttributesCompat)arg5).mLegacyStream)) {
                    }
                    else {
                        goto label_40;
                    }
                }
                else if(((AudioAttributesCompat)arg5).mLegacyStream == null) {
                }
                else {
                    goto label_40;
                }

                return v0;
            }
        }

        return 0;
    }

    public int getContentType() {
        if(Build$VERSION.SDK_INT >= 21 && !AudioAttributesCompat.sForceLegacyBehavior && this.mAudioAttributesWrapper != null) {
            return this.mAudioAttributesWrapper.unwrap().getContentType();
        }

        return this.mContentType;
    }

    public int getFlags() {
        if(Build$VERSION.SDK_INT >= 21 && !AudioAttributesCompat.sForceLegacyBehavior && this.mAudioAttributesWrapper != null) {
            return this.mAudioAttributesWrapper.unwrap().getFlags();
        }

        int v0 = this.mFlags;
        int v1 = this.getLegacyStreamType();
        if(v1 == 6) {
            v0 |= 4;
        }
        else if(v1 == 7) {
            v0 |= 1;
        }

        return v0 & 273;
    }

    public int getLegacyStreamType() {
        if(this.mLegacyStream != null) {
            return this.mLegacyStream.intValue();
        }

        if(Build$VERSION.SDK_INT >= 21 && !AudioAttributesCompat.sForceLegacyBehavior) {
            return AudioAttributesCompatApi21.toLegacyStreamType(this.mAudioAttributesWrapper);
        }

        return AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
    }

    public int getUsage() {
        if(Build$VERSION.SDK_INT >= 21 && !AudioAttributesCompat.sForceLegacyBehavior && this.mAudioAttributesWrapper != null) {
            return this.mAudioAttributesWrapper.unwrap().getUsage();
        }

        return this.mUsage;
    }

    public int getVolumeControlStream() {
        if(this == null) {
            throw new IllegalArgumentException("Invalid null audio attributes");
        }

        if(Build$VERSION.SDK_INT >= 26 && !AudioAttributesCompat.sForceLegacyBehavior && this.unwrap() != null) {
            return this.unwrap().getVolumeControlStream();
        }

        return AudioAttributesCompat.toVolumeStreamType(true, this);
    }

    public int hashCode() {
        if(Build$VERSION.SDK_INT >= 21 && !AudioAttributesCompat.sForceLegacyBehavior && this.mAudioAttributesWrapper != null) {
            return this.mAudioAttributesWrapper.unwrap().hashCode();
        }

        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), this.mLegacyStream});
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) public static void setForceLegacyBehavior(boolean arg0) {
        AudioAttributesCompat.sForceLegacyBehavior = arg0;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("AudioAttributesCompat:");
        if(this.unwrap() != null) {
            v0.append(" audioattributes=");
            v0.append(this.unwrap());
        }
        else {
            if(this.mLegacyStream != null) {
                v0.append(" stream=");
                v0.append(this.mLegacyStream);
                v0.append(" derived");
            }

            v0.append(" usage=");
            v0.append(this.usageToString());
            v0.append(" content=");
            v0.append(this.mContentType);
            v0.append(" flags=0x");
            v0.append(Integer.toHexString(this.mFlags).toUpperCase());
        }

        return v0.toString();
    }

    static int toVolumeStreamType(boolean arg3, int arg4, int arg5) {
        int v1 = 1;
        if((arg4 & 1) == 1) {
            if(arg3) {
            }
            else {
                v1 = 7;
            }

            return v1;
        }

        int v0 = 4;
        int v2 = 0;
        if((arg4 & v0) == v0) {
            if(arg3) {
            }
            else {
                v2 = 6;
            }

            return v2;
        }

        arg4 = 3;
        switch(arg5) {
            case 0: {
                goto label_43;
            }
            case 2: {
                return 0;
            }
            case 3: {
                goto label_37;
            }
            case 4: {
                return v0;
            }
            case 6: {
                return 2;
            }
            case 5: 
            case 7: 
            case 8: 
            case 9: 
            case 10: {
                return 5;
            }
            case 11: {
                return 10;
            }
            case 13: {
                return 1;
            }
            case 1: 
            case 12: 
            case 14: 
            case 16: {
                return arg4;
            }
        }

        if(arg3) {
            StringBuilder v4 = new StringBuilder();
            v4.append("Unknown usage value ");
            v4.append(arg5);
            v4.append(" in audio attributes");
            throw new IllegalArgumentException(v4.toString());
        }

        return arg4;
    label_37:
        if(arg3) {
        }
        else {
            v2 = 8;
        }

        return v2;
    label_43:
        if(arg3) {
            arg4 = 0x80000000;
        }

        return arg4;
    }

    static int toVolumeStreamType(boolean arg1, AudioAttributesCompat arg2) {
        return AudioAttributesCompat.toVolumeStreamType(arg1, arg2.getFlags(), arg2.getUsage());
    }

    @Nullable public Object unwrap() {
        if(this.mAudioAttributesWrapper != null) {
            return this.mAudioAttributesWrapper.unwrap();
        }

        return null;
    }

    private static int usageForStreamType(int arg1) {
        int v0 = 2;
        switch(arg1) {
            case 0: {
                return v0;
            }
            case 2: {
                return 6;
            }
            case 3: {
                return 1;
            }
            case 4: {
                return 4;
            }
            case 5: {
                return 5;
            }
            case 6: {
                return v0;
            }
            case 1: 
            case 7: {
                return 13;
            }
            case 8: {
                return 3;
            }
            case 10: {
                return 11;
            }
        }

        return 0;
    }

    static String usageToString(int arg3) {
        switch(arg3) {
            case 0: {
                goto label_70;
            }
            case 1: {
                goto label_66;
            }
            case 2: {
                goto label_62;
            }
            case 3: {
                goto label_58;
            }
            case 4: {
                goto label_54;
            }
            case 5: {
                goto label_50;
            }
            case 6: {
                goto label_46;
            }
            case 7: {
                goto label_42;
            }
            case 8: {
                goto label_38;
            }
            case 9: {
                goto label_34;
            }
            case 10: {
                goto label_30;
            }
            case 11: {
                goto label_26;
            }
            case 12: {
                goto label_22;
            }
            case 13: {
                goto label_18;
            }
            case 14: {
                goto label_14;
            }
            case 16: {
                goto label_10;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("unknown usage ");
        v1.append(arg3);
        return new String(v1.toString());
    label_66:
        return new String("USAGE_MEDIA");
    label_34:
        return new String("USAGE_NOTIFICATION_COMMUNICATION_DELAYED");
    label_70:
        return new String("USAGE_UNKNOWN");
    label_38:
        return new String("USAGE_NOTIFICATION_COMMUNICATION_INSTANT");
    label_42:
        return new String("USAGE_NOTIFICATION_COMMUNICATION_REQUEST");
    label_10:
        return new String("USAGE_ASSISTANT");
    label_46:
        return new String("USAGE_NOTIFICATION_RINGTONE");
    label_14:
        return new String("USAGE_GAME");
    label_50:
        return new String("USAGE_NOTIFICATION");
    label_18:
        return new String("USAGE_ASSISTANCE_SONIFICATION");
    label_54:
        return new String("USAGE_ALARM");
    label_22:
        return new String("USAGE_ASSISTANCE_NAVIGATION_GUIDANCE");
    label_58:
        return new String("USAGE_VOICE_COMMUNICATION_SIGNALLING");
    label_26:
        return new String("USAGE_ASSISTANCE_ACCESSIBILITY");
    label_62:
        return new String("USAGE_VOICE_COMMUNICATION");
    label_30:
        return new String("USAGE_NOTIFICATION_EVENT");
    }

    String usageToString() {
        return AudioAttributesCompat.usageToString(this.mUsage);
    }

    @Nullable public static AudioAttributesCompat wrap(@NonNull Object arg2) {
        if(Build$VERSION.SDK_INT >= 21 && !AudioAttributesCompat.sForceLegacyBehavior) {
            AudioAttributesCompat v0 = new AudioAttributesCompat();
            v0.mAudioAttributesWrapper = Wrapper.wrap(((AudioAttributes)arg2));
            return v0;
        }

        return null;
    }
}

