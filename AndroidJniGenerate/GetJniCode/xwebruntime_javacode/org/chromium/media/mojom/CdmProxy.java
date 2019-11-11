package org.chromium.media.mojom;

import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Callbacks$Callback4;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface CdmProxy extends Interface {
    public interface CreateMediaCryptoSessionResponse extends Callback3 {
    }

    public final class Function {
        private static final boolean IS_EXTENSIBLE = false;

        private Function() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(Function.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public interface InitializeResponse extends Callback4 {
    }

    public interface ProcessResponse extends Callback2 {
    }

    public final class Protocol {
        private static final boolean IS_EXTENSIBLE = false;

        private Protocol() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(Protocol.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public interface Proxy extends CdmProxy, org.chromium.mojo.bindings.Interface$Proxy {
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

    public static final Manager MANAGER;

    static {
        CdmProxy.MANAGER = CdmProxy_Internal.MANAGER;
    }

    void createMediaCryptoSession(byte[] arg1, CreateMediaCryptoSessionResponse arg2);

    void initialize(AssociatedInterfaceNotSupported arg1, InitializeResponse arg2);

    void process(int arg1, int arg2, byte[] arg3, int arg4, ProcessResponse arg5);

    void removeKey(int arg1, byte[] arg2);

    void setKey(int arg1, byte[] arg2, byte[] arg3);
}

