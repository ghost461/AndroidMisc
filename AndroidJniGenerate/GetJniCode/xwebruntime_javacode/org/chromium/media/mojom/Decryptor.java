package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;

public interface Decryptor extends Interface {
    public interface DecryptAndDecodeAudioResponse extends Callback2 {
    }

    public interface DecryptAndDecodeVideoResponse extends Callback3 {
    }

    public interface DecryptResponse extends Callback2 {
    }

    public interface InitializeAudioDecoderResponse extends Callback1 {
    }

    public interface InitializeVideoDecoderResponse extends Callback1 {
    }

    public interface Proxy extends Decryptor, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public final class Status {
        private static final boolean IS_EXTENSIBLE = false;

        private Status() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(Status.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public final class StreamType {
        private static final boolean IS_EXTENSIBLE = false;

        private StreamType() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(StreamType.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public static final Manager MANAGER;

    static {
        Decryptor.MANAGER = Decryptor_Internal.MANAGER;
    }

    void cancelDecrypt(int arg1);

    void decrypt(int arg1, DecoderBuffer arg2, DecryptResponse arg3);

    void decryptAndDecodeAudio(DecoderBuffer arg1, DecryptAndDecodeAudioResponse arg2);

    void decryptAndDecodeVideo(DecoderBuffer arg1, DecryptAndDecodeVideoResponse arg2);

    void deinitializeDecoder(int arg1);

    void initialize(ConsumerHandle arg1, ConsumerHandle arg2, ConsumerHandle arg3, ProducerHandle arg4);

    void initializeAudioDecoder(AudioDecoderConfig arg1, InitializeAudioDecoderResponse arg2);

    void initializeVideoDecoder(VideoDecoderConfig arg1, InitializeVideoDecoderResponse arg2);

    void resetDecoder(int arg1);
}

