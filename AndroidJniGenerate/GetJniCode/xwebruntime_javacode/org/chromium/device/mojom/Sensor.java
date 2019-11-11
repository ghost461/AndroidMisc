package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface Sensor extends Interface {
    public interface AddConfigurationResponse extends Callback1 {
    }

    public interface GetDefaultConfigurationResponse extends Callback1 {
    }

    public interface Proxy extends Sensor, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        Sensor.MANAGER = Sensor_Internal.MANAGER;
    }

    void addConfiguration(SensorConfiguration arg1, AddConfigurationResponse arg2);

    void configureReadingChangeNotifications(boolean arg1);

    void getDefaultConfiguration(GetDefaultConfigurationResponse arg1);

    void removeConfiguration(SensorConfiguration arg1);

    void resume();

    void suspend();
}

