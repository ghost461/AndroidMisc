package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface OutputProtection extends Interface {
    public interface EnableProtectionResponse extends Callback1 {
    }

    public final class LinkType {
        public static final int DISPLAYPORT = 0x20;
        public static final int DVI = 16;
        public static final int HDMI = 8;
        public static final int INTERNAL = 2;
        private static final boolean IS_EXTENSIBLE = false;
        public static final int NETWORK = 0x40;
        public static final int NONE = 0;
        public static final int UNKNOWN = 1;
        public static final int VGA = 4;

        private LinkType() {
            super();
        }

        public static boolean isKnownValue(int arg1) {
            if(arg1 != 4 && arg1 != 8 && arg1 != 16 && arg1 != 0x20 && arg1 != 0x40) {
                switch(arg1) {
                    case 0: 
                    case 1: 
                    case 2: {
                        return 1;
                    }
                    default: {
                        return 0;
                    }
                }
            }

            return 1;
        }

        public static void validate(int arg1) {
            if(LinkType.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public final class ProtectionType {
        public static final int HDCP = 1;
        private static final boolean IS_EXTENSIBLE = false;
        public static final int NONE;

        private ProtectionType() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            switch(arg0) {
                case 0: 
                case 1: {
                    return 1;
                }
            }

            return 0;
        }

        public static void validate(int arg1) {
            if(ProtectionType.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public interface Proxy extends OutputProtection, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface QueryStatusResponse extends Callback3 {
    }

    public static final Manager MANAGER;

    static {
        OutputProtection.MANAGER = OutputProtection_Internal.MANAGER;
    }

    void enableProtection(int arg1, EnableProtectionResponse arg2);

    void queryStatus(QueryStatusResponse arg1);
}

