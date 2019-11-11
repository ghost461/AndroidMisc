package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") final class ChromeUsbEndpoint {
    private static final String TAG = "Usb";
    final UsbEndpoint mEndpoint;

    private ChromeUsbEndpoint(UsbEndpoint arg2) {
        super();
        this.mEndpoint = arg2;
        Log.v("Usb", "ChromeUsbEndpoint created.");
    }

    @CalledByNative private static ChromeUsbEndpoint create(UsbEndpoint arg1) {
        return new ChromeUsbEndpoint(arg1);
    }

    @CalledByNative private int getAddress() {
        return this.mEndpoint.getAddress();
    }

    @CalledByNative private int getAttributes() {
        return this.mEndpoint.getAttributes();
    }

    @CalledByNative private int getInterval() {
        return this.mEndpoint.getInterval();
    }

    @CalledByNative private int getMaxPacketSize() {
        return this.mEndpoint.getMaxPacketSize();
    }
}

