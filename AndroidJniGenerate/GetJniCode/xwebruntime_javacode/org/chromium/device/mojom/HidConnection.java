package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface HidConnection extends Interface {
    public interface GetFeatureReportResponse extends Callback2 {
    }

    public interface Proxy extends HidConnection, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface ReadResponse extends Callback3 {
    }

    public interface SendFeatureReportResponse extends Callback1 {
    }

    public interface WriteResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        HidConnection.MANAGER = HidConnection_Internal.MANAGER;
    }

    void getFeatureReport(byte arg1, GetFeatureReportResponse arg2);

    void read(ReadResponse arg1);

    void sendFeatureReport(byte arg1, byte[] arg2, SendFeatureReportResponse arg3);

    void write(byte arg1, byte[] arg2, WriteResponse arg3);
}

