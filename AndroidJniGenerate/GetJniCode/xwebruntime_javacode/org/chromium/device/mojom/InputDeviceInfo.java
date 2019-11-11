package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

public final class InputDeviceInfo extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO = null;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public String id;
    public boolean isAccelerometer;
    public boolean isJoystick;
    public boolean isKey;
    public boolean isKeyboard;
    public boolean isMouse;
    public boolean isTablet;
    public boolean isTouchpad;
    public boolean isTouchscreen;
    public String name;
    public int subsystem;
    public int type;

    static {
        InputDeviceInfo.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
        InputDeviceInfo.DEFAULT_STRUCT_INFO = InputDeviceInfo.VERSION_ARRAY[0];
    }

    public InputDeviceInfo() {
        this(0);
    }

    private InputDeviceInfo(int arg2) {
        super(40, arg2);
    }

    public static InputDeviceInfo decode(Decoder arg3) {
        InputDeviceInfo v1;
        if(arg3 == null) {
            return null;
        }

        arg3.increaseStackDepth();
        try {
            v1 = new InputDeviceInfo(arg3.readAndValidateDataHeader(InputDeviceInfo.VERSION_ARRAY).elementsOrVersion);
            v1.id = arg3.readString(8, false);
            v1.name = arg3.readString(16, false);
            v1.subsystem = arg3.readInt(24);
            InputDeviceSubsystem.validate(v1.subsystem);
            v1.type = arg3.readInt(28);
            InputDeviceType.validate(v1.type);
            v1.isAccelerometer = arg3.readBoolean(0x20, 0);
            v1.isJoystick = arg3.readBoolean(0x20, 1);
            v1.isKey = arg3.readBoolean(0x20, 2);
            v1.isKeyboard = arg3.readBoolean(0x20, 3);
            v1.isMouse = arg3.readBoolean(0x20, 4);
            v1.isTablet = arg3.readBoolean(0x20, 5);
            v1.isTouchpad = arg3.readBoolean(0x20, 6);
            v1.isTouchscreen = arg3.readBoolean(0x20, 7);
        }
        catch(Throwable v0) {
            arg3.decreaseStackDepth();
            throw v0;
        }

        arg3.decreaseStackDepth();
        return v1;
    }

    public static InputDeviceInfo deserialize(ByteBuffer arg2) {
        return InputDeviceInfo.deserialize(new Message(arg2, new ArrayList()));
    }

    public static InputDeviceInfo deserialize(Message arg1) {
        return InputDeviceInfo.decode(new Decoder(arg1));
    }

    protected final void encode(Encoder arg4) {
        arg4 = arg4.getEncoderAtDataOffset(InputDeviceInfo.DEFAULT_STRUCT_INFO);
        arg4.encode(this.id, 8, false);
        arg4.encode(this.name, 16, false);
        arg4.encode(this.subsystem, 24);
        arg4.encode(this.type, 28);
        arg4.encode(this.isAccelerometer, 0x20, 0);
        arg4.encode(this.isJoystick, 0x20, 1);
        arg4.encode(this.isKey, 0x20, 2);
        arg4.encode(this.isKeyboard, 0x20, 3);
        arg4.encode(this.isMouse, 0x20, 4);
        arg4.encode(this.isTablet, 0x20, 5);
        arg4.encode(this.isTouchpad, 0x20, 6);
        arg4.encode(this.isTouchscreen, 0x20, 7);
    }
}

