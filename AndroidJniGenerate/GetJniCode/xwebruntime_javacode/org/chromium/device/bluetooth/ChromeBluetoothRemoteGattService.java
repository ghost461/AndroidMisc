package org.chromium.device.bluetooth;

import java.util.Iterator;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNIAdditionalImport;
import org.chromium.base.annotations.JNINamespace;

@JNIAdditionalImport(value={Wrappers.class}) @JNINamespace(value="device") final class ChromeBluetoothRemoteGattService {
    private static final String TAG = "Bluetooth";
    ChromeBluetoothDevice mChromeDevice;
    final String mInstanceId;
    private long mNativeBluetoothRemoteGattServiceAndroid;
    final BluetoothGattServiceWrapper mService;

    private ChromeBluetoothRemoteGattService(long arg1, BluetoothGattServiceWrapper arg3, String arg4, ChromeBluetoothDevice arg5) {
        super();
        this.mNativeBluetoothRemoteGattServiceAndroid = arg1;
        this.mService = arg3;
        this.mInstanceId = arg4;
        this.mChromeDevice = arg5;
        Log.v("Bluetooth", "ChromeBluetoothRemoteGattService created.");
    }

    @CalledByNative private static ChromeBluetoothRemoteGattService create(long arg7, BluetoothGattServiceWrapper arg9, String arg10, ChromeBluetoothDevice arg11) {
        return new ChromeBluetoothRemoteGattService(arg7, arg9, arg10, arg11);
    }

    @CalledByNative private void createCharacteristics() {
        Iterator v0 = this.mService.getCharacteristics().iterator();
        while(v0.hasNext()) {
            Object v6 = v0.next();
            this.nativeCreateGattRemoteCharacteristic(this.mNativeBluetoothRemoteGattServiceAndroid, this.mInstanceId + "/" + ((BluetoothGattCharacteristicWrapper)v6).getUuid().toString() + "," + ((BluetoothGattCharacteristicWrapper)v6).getInstanceId(), ((BluetoothGattCharacteristicWrapper)v6), this.mChromeDevice);
        }
    }

    @CalledByNative private String getUUID() {
        return this.mService.getUuid().toString();
    }

    private native void nativeCreateGattRemoteCharacteristic(long arg1, String arg2, BluetoothGattCharacteristicWrapper arg3, ChromeBluetoothDevice arg4) {
    }

    @CalledByNative private void onBluetoothRemoteGattServiceAndroidDestruction() {
        this.mNativeBluetoothRemoteGattServiceAndroid = 0;
    }
}

