package org.chromium.device.usb;

import android.annotation.TargetApi;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") final class ChromeUsbInterface {
    private static final String TAG = "Usb";
    final UsbInterface mInterface;

    private ChromeUsbInterface(UsbInterface arg2) {
        super();
        this.mInterface = arg2;
        Log.v("Usb", "ChromeUsbInterface created.");
    }

    @CalledByNative private static ChromeUsbInterface create(UsbInterface arg1) {
        return new ChromeUsbInterface(arg1);
    }

    @TargetApi(value=21) @CalledByNative private int getAlternateSetting() {
        return this.mInterface.getAlternateSetting();
    }

    @CalledByNative private UsbEndpoint[] getEndpoints() {
        int v0 = this.mInterface.getEndpointCount();
        UsbEndpoint[] v1 = new UsbEndpoint[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = this.mInterface.getEndpoint(v2);
        }

        return v1;
    }

    @CalledByNative private int getInterfaceClass() {
        return this.mInterface.getInterfaceClass();
    }

    @CalledByNative private int getInterfaceNumber() {
        return this.mInterface.getId();
    }

    @CalledByNative private int getInterfaceProtocol() {
        return this.mInterface.getInterfaceProtocol();
    }

    @CalledByNative private int getInterfaceSubclass() {
        return this.mInterface.getInterfaceSubclass();
    }
}

