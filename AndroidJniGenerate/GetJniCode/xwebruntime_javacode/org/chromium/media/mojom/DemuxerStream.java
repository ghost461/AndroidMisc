package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback4;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface DemuxerStream extends Interface {
    public interface InitializeResponse extends Callback4 {
    }

    public interface Proxy extends DemuxerStream, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface ReadResponse extends Callback4 {
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

    public final class Type {
        private static final boolean IS_EXTENSIBLE = false;

        private Type() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            return 0;
        }

        public static void validate(int arg1) {
            if(Type.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public static final Manager MANAGER;

    static {
        DemuxerStream.MANAGER = DemuxerStream_Internal.MANAGER;
    }

    void enableBitstreamConverter();

    void initialize(InitializeResponse arg1);

    void read(ReadResponse arg1);
}

