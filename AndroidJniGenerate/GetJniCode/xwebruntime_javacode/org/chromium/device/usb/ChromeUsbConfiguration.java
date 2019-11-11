package org.chromium.device.usb;

import android.annotation.TargetApi;
import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbInterface;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=21) @JNINamespace(value="device") final class ChromeUsbConfiguration {
    private static final String TAG = "Usb";
    final UsbConfiguration mConfiguration;

    private ChromeUsbConfiguration(UsbConfiguration arg2) {
        super();
        this.mConfiguration = arg2;
        Log.v("Usb", "ChromeUsbConfiguration created.");
    }

    @CalledByNative private static ChromeUsbConfiguration create(UsbConfiguration arg1) {
        return new ChromeUsbConfiguration(arg1);
    }

    @CalledByNative private int getConfigurationValue() {
        return this.mConfiguration.getId();
    }

    @CalledByNative private UsbInterface[] getInterfaces() {
        int v0 = this.mConfiguration.getInterfaceCount();
        UsbInterface[] v1 = new UsbInterface[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = this.mConfiguration.getInterface(v2);
        }

        return v1;
    }

    @CalledByNative private int getMaxPower() {
        return this.mConfiguration.getMaxPower();
    }

    @CalledByNative private boolean isRemoteWakeup() {
        return this.mConfiguration.isRemoteWakeup();
    }

    @CalledByNative private boolean isSelfPowered() {
        return this.mConfiguration.isSelfPowered();
    }
}

