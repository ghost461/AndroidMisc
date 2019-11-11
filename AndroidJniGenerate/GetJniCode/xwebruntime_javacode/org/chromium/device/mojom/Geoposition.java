package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo_base.mojom.Time;

public final class Geoposition extends Struct {
    public final class ErrorCode {
        private static final boolean IS_EXTENSIBLE = false;
        public static final int LAST = 3;
        public static final int NONE = 0;
        public static final int PERMISSION_DENIED = 1;
        public static final int POSITION_UNAVAILABLE = 2;
        public static final int TIMEOUT = 3;

        private ErrorCode() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            switch(arg0) {
                case 0: 
                case 1: 
                case 2: 
                case 3: {
                    return 1;
                }
            }

            return 0;
        }

        public static void validate(int arg1) {
            if(ErrorCode.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 88;
    private static final DataHeader[] VERSION_ARRAY;
    public double accuracy;
    public double altitude;
    public double altitudeAccuracy;
    public int errorCode;
    public String errorMessage;
    public double heading;
    public double latitude;
    public double longitude;
    public double speed;
    public Time timestamp;
    public boolean valid;

    static {
        Geoposition.VERSION_ARRAY = new DataHeader[]{new DataHeader(88, 0)};
        Geoposition.DEFAULT_STRUCT_INFO = Geoposition.VERSION_ARRAY[0];
    }

    public Geoposition() {
        this(0);
    }

    private Geoposition(int arg3) {
        super(88, arg3);
        this.latitude = 200;
        this.longitude = 200;
        this.altitude = -10000;
        this.accuracy = -1;
        this.altitudeAccuracy = -1;
        this.heading = -1;
        this.speed = -1;
        this.errorCode = 0;
    }

    public static Geoposition decode(Decoder arg5) {
        Geoposition v1;
        if(arg5 == null) {
            return null;
        }

        arg5.increaseStackDepth();
        try {
            v1 = new Geoposition(arg5.readAndValidateDataHeader(Geoposition.VERSION_ARRAY).elementsOrVersion);
            v1.valid = arg5.readBoolean(8, 0);
            v1.errorCode = arg5.readInt(12);
            ErrorCode.validate(v1.errorCode);
            v1.latitude = arg5.readDouble(16);
            v1.longitude = arg5.readDouble(24);
            v1.altitude = arg5.readDouble(0x20);
            v1.accuracy = arg5.readDouble(40);
            v1.altitudeAccuracy = arg5.readDouble(0x30);
            v1.heading = arg5.readDouble(56);
            v1.speed = arg5.readDouble(0x40);
            v1.timestamp = Time.decode(arg5.readPointer(72, false));
            v1.errorMessage = arg5.readString(80, false);
        }
        catch(Throwable v0) {
            arg5.decreaseStackDepth();
            throw v0;
        }

        arg5.decreaseStackDepth();
        return v1;
    }

    public static Geoposition deserialize(ByteBuffer arg2) {
        return Geoposition.deserialize(new Message(arg2, new ArrayList()));
    }

    public static Geoposition deserialize(Message arg1) {
        return Geoposition.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg5) {
        arg5 = arg5.getEncoderAtDataOffset(Geoposition.DEFAULT_STRUCT_INFO);
        arg5.encode(this.valid, 8, 0);
        arg5.encode(this.errorCode, 12);
        arg5.encode(this.latitude, 16);
        arg5.encode(this.longitude, 24);
        arg5.encode(this.altitude, 0x20);
        arg5.encode(this.accuracy, 40);
        arg5.encode(this.altitudeAccuracy, 0x30);
        arg5.encode(this.heading, 56);
        arg5.encode(this.speed, 0x40);
        arg5.encode(this.timestamp, 72, false);
        arg5.encode(this.errorMessage, 80, false);
    }
}

