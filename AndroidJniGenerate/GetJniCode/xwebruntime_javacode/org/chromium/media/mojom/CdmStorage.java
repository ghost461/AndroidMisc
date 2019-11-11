package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface CdmStorage extends Interface {
    public interface OpenResponse extends Callback3 {
    }

    public interface Proxy extends CdmStorage, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public final class Status {
        public static final int FAILURE = 2;
        public static final int IN_USE = 1;
        private static final boolean IS_EXTENSIBLE = false;
        public static final int SUCCESS;

        private Status() {
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
            if(Status.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public static final Manager MANAGER;

    static {
        CdmStorage.MANAGER = CdmStorage_Internal.MANAGER;
    }

    void open(String arg1, OpenResponse arg2);
}

