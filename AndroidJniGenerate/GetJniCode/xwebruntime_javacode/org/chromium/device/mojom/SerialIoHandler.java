package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface SerialIoHandler extends Interface {
    public interface ClearBreakResponse extends Callback1 {
    }

    public interface ConfigurePortResponse extends Callback1 {
    }

    public interface FlushResponse extends Callback1 {
    }

    public interface GetControlSignalsResponse extends Callback1 {
    }

    public interface GetPortInfoResponse extends Callback1 {
    }

    public interface OpenResponse extends Callback1 {
    }

    public interface Proxy extends SerialIoHandler, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface ReadResponse extends Callback2 {
    }

    public interface SetBreakResponse extends Callback1 {
    }

    public interface SetControlSignalsResponse extends Callback1 {
    }

    public interface WriteResponse extends Callback2 {
    }

    public static final Manager MANAGER;

    static {
        SerialIoHandler.MANAGER = SerialIoHandler_Internal.MANAGER;
    }

    void cancelRead(int arg1);

    void cancelWrite(int arg1);

    void clearBreak(ClearBreakResponse arg1);

    void configurePort(SerialConnectionOptions arg1, ConfigurePortResponse arg2);

    void flush(FlushResponse arg1);

    void getControlSignals(GetControlSignalsResponse arg1);

    void getPortInfo(GetPortInfoResponse arg1);

    void open(String arg1, SerialConnectionOptions arg2, OpenResponse arg3);

    void read(int arg1, ReadResponse arg2);

    void setBreak(SetBreakResponse arg1);

    void setControlSignals(SerialHostControlSignals arg1, SetControlSignalsResponse arg2);

    void write(byte[] arg1, WriteResponse arg2);
}

