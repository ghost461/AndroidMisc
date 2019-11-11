package org.chromium.media.mojom;

import org.chromium.gfx.mojom.Size;
import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.SharedBufferHandle;

public interface VideoEncodeAccelerator extends Interface {
    public interface EncodeResponse extends Callback0 {
    }

    public final class Error {
        public static final int ILLEGAL_STATE = 0;
        public static final int INVALID_ARGUMENT = 1;
        private static final boolean IS_EXTENSIBLE = false;
        public static final int PLATFORM_FAILURE = 2;

        private Error() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            switch(arg0) {
                case 0: 
                case 1: 
                case 2: {
                    return 1;
                }
            }

            return 0;
        }

        public static void validate(int arg1) {
            if(Error.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public interface InitializeResponse extends Callback1 {
    }

    public interface Proxy extends VideoEncodeAccelerator, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        VideoEncodeAccelerator.MANAGER = VideoEncodeAccelerator_Internal.MANAGER;
    }

    void encode(VideoFrame arg1, boolean arg2, EncodeResponse arg3);

    void initialize(int arg1, Size arg2, int arg3, int arg4, VideoEncodeAcceleratorClient arg5, InitializeResponse arg6);

    void requestEncodingParametersChange(int arg1, int arg2);

    void useOutputBitstreamBuffer(int arg1, SharedBufferHandle arg2);
}

