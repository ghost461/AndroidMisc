package org.chromium.device.bluetooth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings$Builder;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build$VERSION;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=23) @JNINamespace(value="device") class Wrappers {
    class BluetoothAdapterWrapper {
        private final BluetoothAdapter mAdapter;
        protected final Context mContext;
        protected BluetoothLeScannerWrapper mScannerWrapper;

        public BluetoothAdapterWrapper(BluetoothAdapter arg1, Context arg2) {
            super();
            this.mAdapter = arg1;
            this.mContext = arg2;
        }

        @CalledByNative(value="BluetoothAdapterWrapper") public static BluetoothAdapterWrapper createWithDefaultAdapter() {
            int v1 = 1;
            int v0 = Build$VERSION.SDK_INT >= 23 ? 1 : 0;
            BluetoothAdapterWrapper v3 = null;
            if(v0 == 0) {
                Log.i("Bluetooth", "BluetoothAdapterWrapper.create failed: SDK version (%d) too low.", new Object[]{Integer.valueOf(Build$VERSION.SDK_INT)});
                return v3;
            }

            v0 = ContextUtils.getApplicationContext().checkCallingOrSelfPermission("android.permission.BLUETOOTH") != 0 || ContextUtils.getApplicationContext().checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") != 0 ? 0 : 1;
            if(v0 == 0) {
                Log.w("Bluetooth", "BluetoothAdapterWrapper.create failed: Lacking Bluetooth permissions.", new Object[0]);
                return v3;
            }

            if(Build$VERSION.SDK_INT < 18 || !ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
                v1 = 0;
            }
            else {
            }

            if(v1 == 0) {
                Log.i("Bluetooth", "BluetoothAdapterWrapper.create failed: No Low Energy support.", new Object[0]);
                return v3;
            }

            BluetoothAdapter v0_1 = BluetoothAdapter.getDefaultAdapter();
            if(v0_1 == null) {
                Log.i("Bluetooth", "BluetoothAdapterWrapper.create failed: Default adapter not found.", new Object[0]);
                return v3;
            }

            return new BluetoothAdapterWrapper(v0_1, ContextUtils.getApplicationContext());
        }

        public boolean disable() {
            return this.mAdapter.disable();
        }

        public boolean enable() {
            return this.mAdapter.enable();
        }

        @SuppressLint(value={"HardwareIds"}) public String getAddress() {
            return this.mAdapter.getAddress();
        }

        public BluetoothLeScannerWrapper getBluetoothLeScanner() {
            BluetoothLeScanner v0 = this.mAdapter.getBluetoothLeScanner();
            if(v0 == null) {
                return null;
            }

            if(this.mScannerWrapper == null) {
                this.mScannerWrapper = new BluetoothLeScannerWrapper(v0);
            }

            return this.mScannerWrapper;
        }

        public Context getContext() {
            return this.mContext;
        }

        public String getName() {
            return this.mAdapter.getName();
        }

        public int getScanMode() {
            return this.mAdapter.getScanMode();
        }

        public boolean isDiscovering() {
            return this.mAdapter.isDiscovering();
        }

        public boolean isEnabled() {
            return this.mAdapter.isEnabled();
        }
    }

    class BluetoothDeviceWrapper {
        private final HashMap mCharacteristicsToWrappers;
        private final HashMap mDescriptorsToWrappers;
        private final BluetoothDevice mDevice;

        public BluetoothDeviceWrapper(BluetoothDevice arg1) {
            super();
            this.mDevice = arg1;
            this.mCharacteristicsToWrappers = new HashMap();
            this.mDescriptorsToWrappers = new HashMap();
        }

        static HashMap access$100(BluetoothDeviceWrapper arg0) {
            return arg0.mCharacteristicsToWrappers;
        }

        static HashMap access$200(BluetoothDeviceWrapper arg0) {
            return arg0.mDescriptorsToWrappers;
        }

        public BluetoothGattWrapper connectGatt(Context arg4, boolean arg5, BluetoothGattCallbackWrapper arg6, int arg7) {
            return new BluetoothGattWrapper(this.mDevice.connectGatt(arg4, arg5, new ForwardBluetoothGattCallbackToWrapper(arg6, this), arg7), this);
        }

        public String getAddress() {
            return this.mDevice.getAddress();
        }

        public int getBluetoothClass_getDeviceClass() {
            if(this.mDevice != null) {
                if(this.mDevice.getBluetoothClass() == null) {
                }
                else {
                    return this.mDevice.getBluetoothClass().getDeviceClass();
                }
            }

            return 0x1F00;
        }

        public int getBondState() {
            return this.mDevice.getBondState();
        }

        public String getName() {
            return this.mDevice.getName();
        }
    }

    abstract class BluetoothGattCallbackWrapper {
        BluetoothGattCallbackWrapper() {
            super();
        }

        public abstract void onCharacteristicChanged(BluetoothGattCharacteristicWrapper arg1);

        public abstract void onCharacteristicRead(BluetoothGattCharacteristicWrapper arg1, int arg2);

        public abstract void onCharacteristicWrite(BluetoothGattCharacteristicWrapper arg1, int arg2);

        public abstract void onConnectionStateChange(int arg1, int arg2);

        public abstract void onDescriptorRead(BluetoothGattDescriptorWrapper arg1, int arg2);

        public abstract void onDescriptorWrite(BluetoothGattDescriptorWrapper arg1, int arg2);

        public abstract void onServicesDiscovered(int arg1);
    }

    class BluetoothGattCharacteristicWrapper {
        final BluetoothGattCharacteristic mCharacteristic;
        final BluetoothDeviceWrapper mDeviceWrapper;

        public BluetoothGattCharacteristicWrapper(BluetoothGattCharacteristic arg1, BluetoothDeviceWrapper arg2) {
            super();
            this.mCharacteristic = arg1;
            this.mDeviceWrapper = arg2;
        }

        public List getDescriptors() {
            List v0 = this.mCharacteristic.getDescriptors();
            ArrayList v1 = new ArrayList(v0.size());
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                Object v2 = v0_1.next();
                Object v3 = this.mDeviceWrapper.mDescriptorsToWrappers.get(v2);
                if(v3 == null) {
                    BluetoothGattDescriptorWrapper v3_1 = new BluetoothGattDescriptorWrapper(((BluetoothGattDescriptor)v2), this.mDeviceWrapper);
                    this.mDeviceWrapper.mDescriptorsToWrappers.put(v2, v3_1);
                }

                v1.add(v3);
            }

            return ((List)v1);
        }

        public int getInstanceId() {
            return this.mCharacteristic.getInstanceId();
        }

        public int getProperties() {
            return this.mCharacteristic.getProperties();
        }

        public UUID getUuid() {
            return this.mCharacteristic.getUuid();
        }

        public byte[] getValue() {
            return this.mCharacteristic.getValue();
        }

        public boolean setValue(byte[] arg2) {
            return this.mCharacteristic.setValue(arg2);
        }
    }

    class BluetoothGattDescriptorWrapper {
        private final BluetoothGattDescriptor mDescriptor;
        final BluetoothDeviceWrapper mDeviceWrapper;

        public BluetoothGattDescriptorWrapper(BluetoothGattDescriptor arg1, BluetoothDeviceWrapper arg2) {
            super();
            this.mDescriptor = arg1;
            this.mDeviceWrapper = arg2;
        }

        static BluetoothGattDescriptor access$000(BluetoothGattDescriptorWrapper arg0) {
            return arg0.mDescriptor;
        }

        public BluetoothGattCharacteristicWrapper getCharacteristic() {
            return this.mDeviceWrapper.mCharacteristicsToWrappers.get(this.mDescriptor.getCharacteristic());
        }

        public UUID getUuid() {
            return this.mDescriptor.getUuid();
        }

        public byte[] getValue() {
            return this.mDescriptor.getValue();
        }

        public boolean setValue(byte[] arg2) {
            return this.mDescriptor.setValue(arg2);
        }
    }

    class BluetoothGattServiceWrapper {
        private final BluetoothDeviceWrapper mDeviceWrapper;
        private final BluetoothGattService mService;

        public BluetoothGattServiceWrapper(BluetoothGattService arg1, BluetoothDeviceWrapper arg2) {
            super();
            this.mService = arg1;
            this.mDeviceWrapper = arg2;
        }

        public List getCharacteristics() {
            List v0 = this.mService.getCharacteristics();
            ArrayList v1 = new ArrayList(v0.size());
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                Object v2 = v0_1.next();
                Object v3 = this.mDeviceWrapper.mCharacteristicsToWrappers.get(v2);
                if(v3 == null) {
                    BluetoothGattCharacteristicWrapper v3_1 = new BluetoothGattCharacteristicWrapper(((BluetoothGattCharacteristic)v2), this.mDeviceWrapper);
                    this.mDeviceWrapper.mCharacteristicsToWrappers.put(v2, v3_1);
                }

                v1.add(v3);
            }

            return ((List)v1);
        }

        public int getInstanceId() {
            return this.mService.getInstanceId();
        }

        public UUID getUuid() {
            return this.mService.getUuid();
        }
    }

    class BluetoothGattWrapper {
        private final BluetoothDeviceWrapper mDeviceWrapper;
        private final BluetoothGatt mGatt;

        BluetoothGattWrapper(BluetoothGatt arg1, BluetoothDeviceWrapper arg2) {
            super();
            this.mGatt = arg1;
            this.mDeviceWrapper = arg2;
        }

        public void close() {
            this.mGatt.close();
        }

        public void disconnect() {
            this.mGatt.disconnect();
        }

        public void discoverServices() {
            this.mGatt.discoverServices();
        }

        public List getServices() {
            List v0 = this.mGatt.getServices();
            ArrayList v1 = new ArrayList(v0.size());
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                v1.add(new BluetoothGattServiceWrapper(v0_1.next(), this.mDeviceWrapper));
            }

            return ((List)v1);
        }

        boolean readCharacteristic(BluetoothGattCharacteristicWrapper arg2) {
            return this.mGatt.readCharacteristic(arg2.mCharacteristic);
        }

        boolean readDescriptor(BluetoothGattDescriptorWrapper arg2) {
            return this.mGatt.readDescriptor(arg2.mDescriptor);
        }

        boolean setCharacteristicNotification(BluetoothGattCharacteristicWrapper arg2, boolean arg3) {
            return this.mGatt.setCharacteristicNotification(arg2.mCharacteristic, arg3);
        }

        boolean writeCharacteristic(BluetoothGattCharacteristicWrapper arg2) {
            return this.mGatt.writeCharacteristic(arg2.mCharacteristic);
        }

        boolean writeDescriptor(BluetoothGattDescriptorWrapper arg2) {
            return this.mGatt.writeDescriptor(arg2.mDescriptor);
        }
    }

    class BluetoothLeScannerWrapper {
        private final HashMap mCallbacks;
        protected final BluetoothLeScanner mScanner;

        public BluetoothLeScannerWrapper(BluetoothLeScanner arg1) {
            super();
            this.mScanner = arg1;
            this.mCallbacks = new HashMap();
        }

        public void startScan(List arg3, int arg4, ScanCallbackWrapper arg5) {
            ScanSettings v4 = new ScanSettings$Builder().setScanMode(arg4).build();
            ForwardScanCallbackToWrapper v0 = new ForwardScanCallbackToWrapper(arg5);
            this.mCallbacks.put(arg5, v0);
            this.mScanner.startScan(arg3, v4, ((ScanCallback)v0));
        }

        public void stopScan(ScanCallbackWrapper arg2) {
            this.mScanner.stopScan(this.mCallbacks.remove(arg2));
        }
    }

    class ForwardBluetoothGattCallbackToWrapper extends BluetoothGattCallback {
        final BluetoothDeviceWrapper mDeviceWrapper;
        final BluetoothGattCallbackWrapper mWrapperCallback;

        ForwardBluetoothGattCallbackToWrapper(BluetoothGattCallbackWrapper arg1, BluetoothDeviceWrapper arg2) {
            super();
            this.mWrapperCallback = arg1;
            this.mDeviceWrapper = arg2;
        }

        public void onCharacteristicChanged(BluetoothGatt arg3, BluetoothGattCharacteristic arg4) {
            Log.i("Bluetooth", "wrapper onCharacteristicChanged.", new Object[0]);
            this.mWrapperCallback.onCharacteristicChanged(this.mDeviceWrapper.mCharacteristicsToWrappers.get(arg4));
        }

        public void onCharacteristicRead(BluetoothGatt arg2, BluetoothGattCharacteristic arg3, int arg4) {
            this.mWrapperCallback.onCharacteristicRead(this.mDeviceWrapper.mCharacteristicsToWrappers.get(arg3), arg4);
        }

        public void onCharacteristicWrite(BluetoothGatt arg2, BluetoothGattCharacteristic arg3, int arg4) {
            this.mWrapperCallback.onCharacteristicWrite(this.mDeviceWrapper.mCharacteristicsToWrappers.get(arg3), arg4);
        }

        public void onConnectionStateChange(BluetoothGatt arg1, int arg2, int arg3) {
            this.mWrapperCallback.onConnectionStateChange(arg2, arg3);
        }

        public void onDescriptorRead(BluetoothGatt arg2, BluetoothGattDescriptor arg3, int arg4) {
            this.mWrapperCallback.onDescriptorRead(this.mDeviceWrapper.mDescriptorsToWrappers.get(arg3), arg4);
        }

        public void onDescriptorWrite(BluetoothGatt arg2, BluetoothGattDescriptor arg3, int arg4) {
            this.mWrapperCallback.onDescriptorWrite(this.mDeviceWrapper.mDescriptorsToWrappers.get(arg3), arg4);
        }

        public void onServicesDiscovered(BluetoothGatt arg1, int arg2) {
            this.mWrapperCallback.onServicesDiscovered(arg2);
        }
    }

    class ForwardScanCallbackToWrapper extends ScanCallback {
        final ScanCallbackWrapper mWrapperCallback;

        ForwardScanCallbackToWrapper(ScanCallbackWrapper arg1) {
            super();
            this.mWrapperCallback = arg1;
        }

        public void onBatchScanResults(List arg4) {
            ArrayList v0 = new ArrayList(arg4.size());
            Iterator v4 = arg4.iterator();
            while(v4.hasNext()) {
                v0.add(new ScanResultWrapper(v4.next()));
            }

            this.mWrapperCallback.onBatchScanResult(((List)v0));
        }

        public void onScanFailed(int arg2) {
            this.mWrapperCallback.onScanFailed(arg2);
        }

        public void onScanResult(int arg3, ScanResult arg4) {
            this.mWrapperCallback.onScanResult(arg3, new ScanResultWrapper(arg4));
        }
    }

    abstract class ScanCallbackWrapper {
        ScanCallbackWrapper() {
            super();
        }

        public abstract void onBatchScanResult(List arg1);

        public abstract void onScanFailed(int arg1);

        public abstract void onScanResult(int arg1, ScanResultWrapper arg2);
    }

    class ScanResultWrapper {
        private final ScanResult mScanResult;

        public ScanResultWrapper(ScanResult arg1) {
            super();
            this.mScanResult = arg1;
        }

        public BluetoothDeviceWrapper getDevice() {
            return new BluetoothDeviceWrapper(this.mScanResult.getDevice());
        }

        public int getRssi() {
            return this.mScanResult.getRssi();
        }

        public SparseArray getScanRecord_getManufacturerSpecificData() {
            return this.mScanResult.getScanRecord().getManufacturerSpecificData();
        }

        public Map getScanRecord_getServiceData() {
            return this.mScanResult.getScanRecord().getServiceData();
        }

        public List getScanRecord_getServiceUuids() {
            return this.mScanResult.getScanRecord().getServiceUuids();
        }

        public int getScanRecord_getTxPowerLevel() {
            return this.mScanResult.getScanRecord().getTxPowerLevel();
        }
    }

    class ThreadUtilsWrapper {
        public interface Factory {
            ThreadUtilsWrapper create();
        }

        private static Factory sFactory;
        private static ThreadUtilsWrapper sInstance;

        protected ThreadUtilsWrapper() {
            super();
        }

        public static ThreadUtilsWrapper getInstance() {
            if(ThreadUtilsWrapper.sInstance == null) {
                ThreadUtilsWrapper.sInstance = ThreadUtilsWrapper.sFactory == null ? new ThreadUtilsWrapper() : ThreadUtilsWrapper.sFactory.create();
            }

            return ThreadUtilsWrapper.sInstance;
        }

        public void runOnUiThread(Runnable arg1) {
            ThreadUtils.runOnUiThread(arg1);
        }

        public static void setFactory(Factory arg0) {
            ThreadUtilsWrapper.sFactory = arg0;
            ThreadUtilsWrapper.sInstance = null;
        }
    }

    public static final int DEVICE_CLASS_UNSPECIFIED = 0x1F00;
    private static final String TAG = "Bluetooth";

    Wrappers() {
        super();
    }
}

