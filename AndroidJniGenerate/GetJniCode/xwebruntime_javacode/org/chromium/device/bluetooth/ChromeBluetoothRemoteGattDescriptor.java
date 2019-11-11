package org.chromium.device.bluetooth;

import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNIAdditionalImport;
import org.chromium.base.annotations.JNINamespace;

@JNIAdditionalImport(value={Wrappers.class}) @JNINamespace(value="device") final class ChromeBluetoothRemoteGattDescriptor {
    private static final String TAG = "Bluetooth";
    final ChromeBluetoothDevice mChromeDevice;
    final BluetoothGattDescriptorWrapper mDescriptor;
    private long mNativeBluetoothRemoteGattDescriptorAndroid;

    private ChromeBluetoothRemoteGattDescriptor(long arg1, BluetoothGattDescriptorWrapper arg3, ChromeBluetoothDevice arg4) {
        super();
        this.mNativeBluetoothRemoteGattDescriptorAndroid = arg1;
        this.mDescriptor = arg3;
        this.mChromeDevice = arg4;
        this.mChromeDevice.mWrapperToChromeDescriptorsMap.put(arg3, this);
        Log.v("Bluetooth", "ChromeBluetoothRemoteGattDescriptor created.");
    }

    @CalledByNative private static ChromeBluetoothRemoteGattDescriptor create(long arg1, BluetoothGattDescriptorWrapper arg3, ChromeBluetoothDevice arg4) {
        return new ChromeBluetoothRemoteGattDescriptor(arg1, arg3, arg4);
    }

    @CalledByNative private String getUUID() {
        return this.mDescriptor.getUuid().toString();
    }

    native void nativeOnRead(long arg1, int arg2, byte[] arg3) {
    }

    native void nativeOnWrite(long arg1, int arg2) {
    }

    @CalledByNative private void onBluetoothRemoteGattDescriptorAndroidDestruction() {
        Log.v("Bluetooth", "ChromeBluetoothRemoteGattDescriptor Destroyed.");
        this.mNativeBluetoothRemoteGattDescriptorAndroid = 0;
        this.mChromeDevice.mWrapperToChromeDescriptorsMap.remove(this.mDescriptor);
    }

    void onDescriptorRead(int arg6) {
        String v0 = "Bluetooth";
        String v1 = "onDescriptorRead status:%d==%s";
        Object[] v2 = new Object[2];
        v2[0] = Integer.valueOf(arg6);
        String v3 = arg6 == 0 ? "OK" : "Error";
        v2[1] = v3;
        Log.i(v0, v1, v2);
        if(this.mNativeBluetoothRemoteGattDescriptorAndroid != 0) {
            this.nativeOnRead(this.mNativeBluetoothRemoteGattDescriptorAndroid, arg6, this.mDescriptor.getValue());
        }
    }

    void onDescriptorWrite(int arg6) {
        String v0 = "Bluetooth";
        String v1 = "onDescriptorWrite status:%d==%s";
        Object[] v2 = new Object[2];
        v2[0] = Integer.valueOf(arg6);
        String v3 = arg6 == 0 ? "OK" : "Error";
        v2[1] = v3;
        Log.i(v0, v1, v2);
        if(this.mNativeBluetoothRemoteGattDescriptorAndroid != 0) {
            this.nativeOnWrite(this.mNativeBluetoothRemoteGattDescriptorAndroid, arg6);
        }
    }

    @CalledByNative private boolean readRemoteDescriptor() {
        if(!this.mChromeDevice.mBluetoothGatt.readDescriptor(this.mDescriptor)) {
            Log.i("Bluetooth", "readRemoteDescriptor readDescriptor failed.", new Object[0]);
            return 0;
        }

        return 1;
    }

    @CalledByNative private boolean writeRemoteDescriptor(byte[] arg4) {
        if(!this.mDescriptor.setValue(arg4)) {
            Log.i("Bluetooth", "writeRemoteDescriptor setValue failed.", new Object[0]);
            return 0;
        }

        if(!this.mChromeDevice.mBluetoothGatt.writeDescriptor(this.mDescriptor)) {
            Log.i("Bluetooth", "writeRemoteDescriptor writeDescriptor failed.", new Object[0]);
            return 0;
        }

        return 1;
    }
}

