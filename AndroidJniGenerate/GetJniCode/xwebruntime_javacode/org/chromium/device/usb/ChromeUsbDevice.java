package org.chromium.device.usb;

import android.annotation.TargetApi;
import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") final class ChromeUsbDevice {
    private static final String TAG = "Usb";
    final UsbDevice mDevice;

    static {
    }

    private ChromeUsbDevice(UsbDevice arg2) {
        super();
        this.mDevice = arg2;
        Log.v("Usb", "ChromeUsbDevice created.");
    }

    @CalledByNative private static ChromeUsbDevice create(UsbDevice arg1) {
        return new ChromeUsbDevice(arg1);
    }

    @TargetApi(value=21) @CalledByNative private UsbConfiguration[] getConfigurations() {
        int v0 = this.mDevice.getConfigurationCount();
        UsbConfiguration[] v1 = new UsbConfiguration[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = this.mDevice.getConfiguration(v2);
        }

        return v1;
    }

    public UsbDevice getDevice() {
        return this.mDevice;
    }

    @CalledByNative private int getDeviceClass() {
        return this.mDevice.getDeviceClass();
    }

    @CalledByNative private int getDeviceId() {
        return this.mDevice.getDeviceId();
    }

    @CalledByNative private int getDeviceProtocol() {
        return this.mDevice.getDeviceProtocol();
    }

    @CalledByNative private int getDeviceSubclass() {
        return this.mDevice.getDeviceSubclass();
    }

    @TargetApi(value=23) @CalledByNative private int getDeviceVersion() {
        String[] v0 = this.mDevice.getVersion().split("\\.");
        return Integer.parseInt(v0[1]) | Integer.parseInt(v0[0]) << 8;
    }

    @CalledByNative private UsbInterface[] getInterfaces() {
        int v0 = this.mDevice.getInterfaceCount();
        UsbInterface[] v1 = new UsbInterface[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = this.mDevice.getInterface(v2);
        }

        return v1;
    }

    @TargetApi(value=21) @CalledByNative private String getManufacturerName() {
        return this.mDevice.getManufacturerName();
    }

    @CalledByNative private int getProductId() {
        return this.mDevice.getProductId();
    }

    @TargetApi(value=21) @CalledByNative private String getProductName() {
        return this.mDevice.getProductName();
    }

    @TargetApi(value=21) @CalledByNative private String getSerialNumber() {
        return this.mDevice.getSerialNumber();
    }

    @CalledByNative private int getVendorId() {
        return this.mDevice.getVendorId();
    }
}

