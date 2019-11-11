package android.support.v4.media;

import android.media.AudioAttributes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Method;

@RequiresApi(value=21) class AudioAttributesCompatApi21 {
    final class Wrapper {
        private AudioAttributes mWrapped;

        private Wrapper(AudioAttributes arg1) {
            super();
            this.mWrapped = arg1;
        }

        public AudioAttributes unwrap() {
            return this.mWrapped;
        }

        public static Wrapper wrap(@NonNull AudioAttributes arg1) {
            if(arg1 == null) {
                throw new IllegalArgumentException("AudioAttributesApi21.Wrapper cannot wrap null");
            }

            return new Wrapper(arg1);
        }
    }

    private static final String TAG = "AudioAttributesCompat";
    private static Method sAudioAttributesToLegacyStreamType;

    AudioAttributesCompatApi21() {
        super();
    }

    public static int toLegacyStreamType(Wrapper arg6) {
        AudioAttributes v6 = arg6.unwrap();
        try {
            if(AudioAttributesCompatApi21.sAudioAttributesToLegacyStreamType == null) {
                AudioAttributesCompatApi21.sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", AudioAttributes.class);
            }

            return AudioAttributesCompatApi21.sAudioAttributesToLegacyStreamType.invoke(null, v6).intValue();
        }
        catch(ClassCastException v6_1) {
            Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", ((Throwable)v6_1));
            return -1;
        }
    }
}

