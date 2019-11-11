package org.chromium.device.bluetooth;

import android.annotation.TargetApi;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNIAdditionalImport;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordHistogram;

@TargetApi(value=23) @JNIAdditionalImport(value={Wrappers.class}) @JNINamespace(value="device") final class ChromeBluetoothDevice {
    class org.chromium.device.bluetooth.ChromeBluetoothDevice$1 {
    }

    class BluetoothGattCallbackImpl extends BluetoothGattCallbackWrapper {
        BluetoothGattCallbackImpl(ChromeBluetoothDevice arg1, org.chromium.device.bluetooth.ChromeBluetoothDevice$1 arg2) {
            this(arg1);
        }

        private BluetoothGattCallbackImpl(ChromeBluetoothDevice arg1) {
            ChromeBluetoothDevice.this = arg1;
            super();
        }

        public void onCharacteristicChanged(BluetoothGattCharacteristicWrapper arg4) {
            Log.i("Bluetooth", "device onCharacteristicChanged.", new Object[0]);
            ThreadUtilsWrapper.getInstance().runOnUiThread(new Runnable(arg4, arg4.getValue()) {
                public void run() {
                    Object v0 = this.this$1.this$0.mWrapperToChromeCharacteristicsMap.get(this.val$characteristic);
                    if(v0 == null) {
                        Log.v("Bluetooth", "onCharacteristicChanged when chromeCharacteristic == null.");
                    }
                    else {
                        ((ChromeBluetoothRemoteGattCharacteristic)v0).onCharacteristicChanged(this.val$value);
                    }
                }
            });
        }

        public void onCharacteristicRead(BluetoothGattCharacteristicWrapper arg3, int arg4) {
            ThreadUtilsWrapper.getInstance().runOnUiThread(new Runnable(arg3, arg4) {
                public void run() {
                    Object v0 = this.this$1.this$0.mWrapperToChromeCharacteristicsMap.get(this.val$characteristic);
                    if(v0 == null) {
                        Log.v("Bluetooth", "onCharacteristicRead when chromeCharacteristic == null.");
                    }
                    else {
                        RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onCharacteristicRead.Status", this.val$status);
                        ((ChromeBluetoothRemoteGattCharacteristic)v0).onCharacteristicRead(this.val$status);
                    }
                }
            });
        }

        public void onCharacteristicWrite(BluetoothGattCharacteristicWrapper arg3, int arg4) {
            ThreadUtilsWrapper.getInstance().runOnUiThread(new Runnable(arg3, arg4) {
                public void run() {
                    Object v0 = this.this$1.this$0.mWrapperToChromeCharacteristicsMap.get(this.val$characteristic);
                    if(v0 == null) {
                        Log.v("Bluetooth", "onCharacteristicWrite when chromeCharacteristic == null.");
                    }
                    else {
                        RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onCharacteristicWrite.Status", this.val$status);
                        ((ChromeBluetoothRemoteGattCharacteristic)v0).onCharacteristicWrite(this.val$status);
                    }
                }
            });
        }

        public void onConnectionStateChange(int arg7, int arg8) {
            String v0 = "Bluetooth";
            String v1 = "onConnectionStateChange status:%d newState:%s";
            Object[] v3 = new Object[2];
            v3[0] = Integer.valueOf(arg7);
            String v2 = arg8 == 2 ? "Connected" : "Disconnected";
            v3[1] = v2;
            Log.i(v0, v1, v3);
            ThreadUtilsWrapper.getInstance().runOnUiThread(new Runnable(arg8, arg7) {
                public void run() {
                    int v1 = 2;
                    if(this.val$newState == v1) {
                        RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onConnectionStateChange.Status.Connected", this.val$status);
                        this.this$1.this$0.mBluetoothGatt.discoverServices();
                    }
                    else if(this.val$newState == 0) {
                        RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onConnectionStateChange.Status.Disconnected", this.val$status);
                        if(this.this$1.this$0.mBluetoothGatt != null) {
                            this.this$1.this$0.mBluetoothGatt.close();
                            this.this$1.this$0.mBluetoothGatt = null;
                        }
                    }
                    else {
                        RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onConnectionStateChange.Status.InvalidState", this.val$status);
                    }

                    if(this.this$1.this$0.mNativeBluetoothDeviceAndroid != 0) {
                        ChromeBluetoothDevice v0 = this.this$1.this$0;
                        long v2 = this.this$1.this$0.mNativeBluetoothDeviceAndroid;
                        int v4 = this.val$status;
                        boolean v1_1 = this.val$newState == v1 ? true : false;
                        v0.nativeOnConnectionStateChange(v2, v4, v1_1);
                    }
                }
            });
        }

        public void onDescriptorRead(BluetoothGattDescriptorWrapper arg3, int arg4) {
            ThreadUtilsWrapper.getInstance().runOnUiThread(new Runnable(arg3, arg4) {
                public void run() {
                    Object v0 = this.this$1.this$0.mWrapperToChromeDescriptorsMap.get(this.val$descriptor);
                    if(v0 == null) {
                        Log.v("Bluetooth", "onDescriptorRead when chromeDescriptor == null.");
                    }
                    else {
                        RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onDescriptorRead.Status", this.val$status);
                        ((ChromeBluetoothRemoteGattDescriptor)v0).onDescriptorRead(this.val$status);
                    }
                }
            });
        }

        public void onDescriptorWrite(BluetoothGattDescriptorWrapper arg3, int arg4) {
            ThreadUtilsWrapper.getInstance().runOnUiThread(new Runnable(arg3, arg4) {
                public void run() {
                    Object v0 = this.this$1.this$0.mWrapperToChromeDescriptorsMap.get(this.val$descriptor);
                    if(v0 == null) {
                        Log.v("Bluetooth", "onDescriptorWrite when chromeDescriptor == null.");
                    }
                    else {
                        RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onDescriptorWrite.Status", this.val$status);
                        ((ChromeBluetoothRemoteGattDescriptor)v0).onDescriptorWrite(this.val$status);
                    }
                }
            });
        }

        public void onServicesDiscovered(int arg6) {
            String v0 = "Bluetooth";
            String v1 = "onServicesDiscovered status:%d==%s";
            Object[] v2 = new Object[2];
            v2[0] = Integer.valueOf(arg6);
            String v3 = arg6 == 0 ? "OK" : "Error";
            v2[1] = v3;
            Log.i(v0, v1, v2);
            ThreadUtilsWrapper.getInstance().runOnUiThread(new Runnable(arg6) {
                public void run() {
                    if(this.this$1.this$0.mNativeBluetoothDeviceAndroid != 0) {
                        if(this.this$1.this$0.mBluetoothGatt == null) {
                            RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onServicesDiscovered.Status.Disconnected", this.val$status);
                            return;
                        }
                        else {
                            RecordHistogram.recordSparseSlowlyHistogram("Bluetooth.Web.Android.onServicesDiscovered.Status.Connected", this.val$status);
                            Iterator v0 = this.this$1.this$0.mBluetoothGatt.getServices().iterator();
                            while(v0.hasNext()) {
                                Object v1 = v0.next();
                                this.this$1.this$0.nativeCreateGattRemoteService(this.this$1.this$0.mNativeBluetoothDeviceAndroid, this.this$1.this$0.getAddress() + "/" + ((BluetoothGattServiceWrapper)v1).getUuid().toString() + "," + ((BluetoothGattServiceWrapper)v1).getInstanceId(), ((BluetoothGattServiceWrapper)v1));
                            }

                            this.this$1.this$0.nativeOnGattServicesDiscovered(this.this$1.this$0.mNativeBluetoothDeviceAndroid);
                        }
                    }
                }
            });
        }
    }

    private static final String TAG = "Bluetooth";
    BluetoothGattWrapper mBluetoothGatt;
    private final BluetoothGattCallbackImpl mBluetoothGattCallbackImpl;
    final BluetoothDeviceWrapper mDevice;
    private long mNativeBluetoothDeviceAndroid;
    final HashMap mWrapperToChromeCharacteristicsMap;
    final HashMap mWrapperToChromeDescriptorsMap;

    private ChromeBluetoothDevice(long arg1, BluetoothDeviceWrapper arg3) {
        super();
        this.mNativeBluetoothDeviceAndroid = arg1;
        this.mDevice = arg3;
        this.mBluetoothGattCallbackImpl = new BluetoothGattCallbackImpl(this, null);
        this.mWrapperToChromeCharacteristicsMap = new HashMap();
        this.mWrapperToChromeDescriptorsMap = new HashMap();
        Log.v("Bluetooth", "ChromeBluetoothDevice created.");
    }

    static long access$100(ChromeBluetoothDevice arg2) {
        return arg2.mNativeBluetoothDeviceAndroid;
    }

    static void access$200(ChromeBluetoothDevice arg0, long arg1, int arg3, boolean arg4) {
        arg0.nativeOnConnectionStateChange(arg1, arg3, arg4);
    }

    static String access$300(ChromeBluetoothDevice arg0) {
        return arg0.getAddress();
    }

    static void access$400(ChromeBluetoothDevice arg0, long arg1, String arg3, BluetoothGattServiceWrapper arg4) {
        arg0.nativeCreateGattRemoteService(arg1, arg3, arg4);
    }

    static void access$500(ChromeBluetoothDevice arg0, long arg1) {
        arg0.nativeOnGattServicesDiscovered(arg1);
    }

    @CalledByNative private static ChromeBluetoothDevice create(long arg1, BluetoothDeviceWrapper arg3) {
        return new ChromeBluetoothDevice(arg1, arg3);
    }

    @CalledByNative private void createGattConnectionImpl() {
        Log.i("Bluetooth", "connectGatt", new Object[0]);
        if(this.mBluetoothGatt != null) {
            this.mBluetoothGatt.close();
        }

        this.mBluetoothGatt = this.mDevice.connectGatt(ContextUtils.getApplicationContext(), false, this.mBluetoothGattCallbackImpl, 2);
    }

    @CalledByNative private void disconnectGatt() {
        Log.i("Bluetooth", "BluetoothGatt.disconnect", new Object[0]);
        if(this.mBluetoothGatt != null) {
            this.mBluetoothGatt.disconnect();
        }
    }

    @CalledByNative private String getAddress() {
        return this.mDevice.getAddress();
    }

    @CalledByNative private int getBluetoothClass() {
        return this.mDevice.getBluetoothClass_getDeviceClass();
    }

    @CalledByNative private String getName() {
        return this.mDevice.getName();
    }

    @CalledByNative private boolean isPaired() {
        boolean v0 = this.mDevice.getBondState() == 12 ? true : false;
        return v0;
    }

    private native void nativeCreateGattRemoteService(long arg1, String arg2, BluetoothGattServiceWrapper arg3) {
    }

    private native void nativeOnConnectionStateChange(long arg1, int arg2, boolean arg3) {
    }

    private native void nativeOnGattServicesDiscovered(long arg1) {
    }

    @CalledByNative private void onBluetoothDeviceAndroidDestruction() {
        if(this.mBluetoothGatt != null) {
            this.mBluetoothGatt.close();
            this.mBluetoothGatt = null;
        }

        this.mNativeBluetoothDeviceAndroid = 0;
    }
}

