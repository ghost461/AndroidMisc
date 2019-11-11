package org.chromium.content.browser;

import android.content.Context;
import org.chromium.base.CommandLine;
import org.chromium.ui.base.DeviceFormFactor;

public class DeviceUtils {
    public DeviceUtils() {
        super();
    }

    public static void addDeviceSpecificUserAgentSwitch(Context arg1) {
        if(!DeviceFormFactor.isTablet()) {
            CommandLine.getInstance().appendSwitch("use-mobile-user-agent");
        }
    }
}

