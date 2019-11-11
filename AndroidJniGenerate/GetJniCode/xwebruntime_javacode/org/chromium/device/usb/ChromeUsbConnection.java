package org.chromium.device.usb;

import android.hardware.usb.UsbDeviceConnection;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") class ChromeUsbConnection {
    private static final String TAG = "Usb";
    final UsbDeviceConnection mConnection;

    private ChromeUsbConnection(UsbDeviceConnection arg2) {
        super();
        this.mConnection = arg2;
        Log.v("Usb", "ChromeUsbConnection created.");
    }

    @CalledByNative private void close() {
        this.mConnection.close();
    }

    @CalledByNative private static ChromeUsbConnection create(UsbDeviceConnection arg1) {
        return new ChromeUsbConnection(arg1);
    }

    @CalledByNative private int getFileDescriptor() {
        return this.mConnection.getFileDescriptor();
    }
}

