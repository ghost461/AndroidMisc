package org.chromium.device.bluetooth;

import android.annotation.TargetApi;
import java.util.Iterator;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNIAdditionalImport;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=23) @JNIAdditionalImport(value={Wrappers.class}) @JNINamespace(value="device") final class ChromeBluetoothRemoteGattCharacteristic {
    private static final String TAG = "Bluetooth";
    final BluetoothGattCharacteristicWrapper mCharacteristic;
    final ChromeBluetoothDevice mChromeDevice;
    final String mInstanceId;
    private long mNativeBluetoothRemoteGattCharacteristicAndroid;

    private ChromeBluetoothRemoteGattCharacteristic(long arg1, BluetoothGattCharacteristicWrapper arg3, String arg4, ChromeBluetoothDevice arg5) {
        super();
        this.mNativeBluetoothRemoteGattCharacteristicAndroid = arg1;
        this.mCharacteristic = arg3;
        this.mInstanceId = arg4;
        this.mChromeDevice = arg5;
        this.mChromeDevice.mWrapperToChromeCharacteristicsMap.put(arg3, this);
        Log.v("Bluetooth", "ChromeBluetoothRemoteGattCharacteristic created.");
    }

    @CalledByNative private static ChromeBluetoothRemoteGattCharacteristic create(long arg7, BluetoothGattCharacteristicWrapper arg9, String arg10, ChromeBluetoothDevice arg11) {
        return new ChromeBluetoothRemoteGattCharacteristic(arg7, arg9, arg10, arg11);
    }

    @CalledByNative private void createDescriptors() {
        Iterator v0 = this.mCharacteristic.getDescriptors().iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); ++v1) {
            Object v7 = v0.next();
            this.nativeCreateGattRemoteDescriptor(this.mNativeBluetoothRemoteGattCharacteristicAndroid, this.mInstanceId + "/" + ((BluetoothGattDescriptorWrapper)v7).getUuid().toString() + ";" + v1, ((BluetoothGattDescriptorWrapper)v7), this.mChromeDevice);
        }
    }

    @CalledByNative private int getProperties() {
        return this.mCharacteristic.getProperties();
    }

    @CalledByNative private String getUUID() {
        return this.mCharacteristic.getUuid().toString();
    }

    private native void nativeCreateGattRemoteDescriptor(long arg1, String arg2, BluetoothGattDescriptorWrapper arg3, ChromeBluetoothDevice arg4) {
    }

    native void nativeOnChanged(long arg1, byte[] arg2) {
    }

    native void nativeOnRead(long arg1, int arg2, byte[] arg3) {
    }

    native void nativeOnWrite(long arg1, int arg2) {
    }

    @CalledByNative private void onBluetoothRemoteGattCharacteristicAndroidDestruction() {
        Log.v("Bluetooth", "ChromeBluetoothRemoteGattCharacteristic Destroyed.");
        if(this.mChromeDevice.mBluetoothGatt != null) {
            this.mChromeDevice.mBluetoothGatt.setCharacteristicNotification(this.mCharacteristic, false);
        }

        this.mNativeBluetoothRemoteGattCharacteristicAndroid = 0;
        this.mChromeDevice.mWrapperToChromeCharacteristicsMap.remove(this.mCharacteristic);
    }

    void onCharacteristicChanged(byte[] arg6) {
        Log.i("Bluetooth", "onCharacteristicChanged", new Object[0]);
        if(this.mNativeBluetoothRemoteGattCharacteristicAndroid != 0) {
            this.nativeOnChanged(this.mNativeBluetoothRemoteGattCharacteristicAndroid, arg6);
        }
    }

    void onCharacteristicRead(int arg6) {
        String v0 = "Bluetooth";
        String v1 = "onCharacteristicRead status:%d==%s";
        Object[] v2 = new Object[2];
        v2[0] = Integer.valueOf(arg6);
        String v3 = arg6 == 0 ? "OK" : "Error";
        v2[1] = v3;
        Log.i(v0, v1, v2);
        if(this.mNativeBluetoothRemoteGattCharacteristicAndroid != 0) {
            this.nativeOnRead(this.mNativeBluetoothRemoteGattCharacteristicAndroid, arg6, this.mCharacteristic.getValue());
        }
    }

    void onCharacteristicWrite(int arg6) {
        String v0 = "Bluetooth";
        String v1 = "onCharacteristicWrite status:%d==%s";
        Object[] v2 = new Object[2];
        v2[0] = Integer.valueOf(arg6);
        String v3 = arg6 == 0 ? "OK" : "Error";
        v2[1] = v3;
        Log.i(v0, v1, v2);
        if(this.mNativeBluetoothRemoteGattCharacteristicAndroid != 0) {
            this.nativeOnWrite(this.mNativeBluetoothRemoteGattCharacteristicAndroid, arg6);
        }
    }

    @CalledByNative private boolean readRemoteCharacteristic() {
        if(!this.mChromeDevice.mBluetoothGatt.readCharacteristic(this.mCharacteristic)) {
            Log.i("Bluetooth", "readRemoteCharacteristic readCharacteristic failed.", new Object[0]);
            return 0;
        }

        return 1;
    }

    @CalledByNative private boolean setCharacteristicNotification(boolean arg3) {
        return this.mChromeDevice.mBluetoothGatt.setCharacteristicNotification(this.mCharacteristic, arg3);
    }

    @CalledByNative private boolean writeRemoteCharacteristic(byte[] arg4) {
        if(!this.mCharacteristic.setValue(arg4)) {
            Log.i("Bluetooth", "writeRemoteCharacteristic setValue failed.", new Object[0]);
            return 0;
        }

        if(!this.mChromeDevice.mBluetoothGatt.writeCharacteristic(this.mCharacteristic)) {
            Log.i("Bluetooth", "writeRemoteCharacteristic writeCharacteristic failed.", new Object[0]);
            return 0;
        }

        return 1;
    }
}

