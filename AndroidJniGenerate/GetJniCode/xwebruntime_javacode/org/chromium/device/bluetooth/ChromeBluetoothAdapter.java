package org.chromium.device.bluetooth;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.SparseArray;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNIAdditionalImport;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.components.location.LocationUtils;

@TargetApi(value=23) @JNIAdditionalImport(value={Wrappers.class}) @JNINamespace(value="device") final class ChromeBluetoothAdapter extends BroadcastReceiver {
    class org.chromium.device.bluetooth.ChromeBluetoothAdapter$1 {
    }

    class ScanCallback extends ScanCallbackWrapper {
        ScanCallback(ChromeBluetoothAdapter arg1, org.chromium.device.bluetooth.ChromeBluetoothAdapter$1 arg2) {
            this(arg1);
        }

        private ScanCallback(ChromeBluetoothAdapter arg1) {
            ChromeBluetoothAdapter.this = arg1;
            super();
        }

        public void onBatchScanResult(List arg2) {
            Log.v("Bluetooth", "onBatchScanResults");
        }

        public void onScanFailed(int arg5) {
            Log.w("Bluetooth", "onScanFailed: %d", new Object[]{Integer.valueOf(arg5)});
            ChromeBluetoothAdapter.this.nativeOnScanFailed(ChromeBluetoothAdapter.this.mNativeBluetoothAdapterAndroid);
        }

        public void onScanResult(int arg18, ScanResultWrapper arg19) {
            byte[][] v16;
            int[] v15;
            byte[][] v4_1;
            byte[][] v14;
            String[] v13;
            String[] v3;
            String[] v11;
            ScanCallback v0 = this;
            Log.v("Bluetooth", "onScanResult %d %s %s", Integer.valueOf(arg18), arg19.getDevice().getAddress(), arg19.getDevice().getName());
            List v1 = arg19.getScanRecord_getServiceUuids();
            int v2 = 0;
            if(v1 == null) {
                v11 = new String[0];
            }
            else {
                v3 = new String[v1.size()];
                int v4;
                for(v4 = 0; v4 < v1.size(); ++v4) {
                    v3[v4] = v1.get(v4).toString();
                }

                v11 = v3;
            }

            Map v1_1 = arg19.getScanRecord_getServiceData();
            if(v1_1 == null) {
                v13 = new String[0];
                v14 = new byte[0][];
            }
            else {
                v3 = new String[v1_1.size()];
                v4_1 = new byte[v1_1.size()][];
                Iterator v1_2 = v1_1.entrySet().iterator();
                int v5;
                for(v5 = 0; v1_2.hasNext(); ++v5) {
                    Object v6 = v1_2.next();
                    v3[v5] = ((Map$Entry)v6).getKey().toString();
                    v4_1[v5] = ((Map$Entry)v6).getValue();
                }

                v13 = v3;
                v14 = v4_1;
            }

            SparseArray v1_3 = arg19.getScanRecord_getManufacturerSpecificData();
            if(v1_3 == null) {
                v15 = new int[0];
                v16 = new byte[0][];
            }
            else {
                int[] v3_1 = new int[v1_3.size()];
                v4_1 = new byte[v1_3.size()][];
                while(v2 < v1_3.size()) {
                    v3_1[v2] = v1_3.keyAt(v2);
                    v4_1[v2] = v1_3.valueAt(v2);
                    ++v2;
                }

                v15 = v3_1;
                v16 = v4_1;
            }

            if(v0.this$0.mNativeBluetoothAdapterAndroid != 0) {
                v0.this$0.nativeCreateOrUpdateDeviceOnScan(v0.this$0.mNativeBluetoothAdapterAndroid, arg19.getDevice().getAddress(), arg19.getDevice(), arg19.getRssi(), v11, arg19.getScanRecord_getTxPowerLevel(), v13, ((Object[])v14), v15, ((Object[])v16));
            }
        }
    }

    private static final String TAG = "Bluetooth";
    private final BluetoothAdapterWrapper mAdapter;
    private long mNativeBluetoothAdapterAndroid;
    private ScanCallback mScanCallback;

    static {
    }

    public ChromeBluetoothAdapter(long arg1, BluetoothAdapterWrapper arg3) {
        super();
        this.mNativeBluetoothAdapterAndroid = arg1;
        this.mAdapter = arg3;
        this.registerBroadcastReceiver();
        if(arg3 == null) {
            Log.i("Bluetooth", "ChromeBluetoothAdapter created with no adapterWrapper.", new Object[0]);
        }
        else {
            Log.i("Bluetooth", "ChromeBluetoothAdapter created with provided adapterWrapper.", new Object[0]);
        }
    }

    static long access$100(ChromeBluetoothAdapter arg2) {
        return arg2.mNativeBluetoothAdapterAndroid;
    }

    static void access$200(ChromeBluetoothAdapter arg0, long arg1, String arg3, BluetoothDeviceWrapper arg4, int arg5, String[] arg6, int arg7, String[] arg8, Object[] arg9, int[] arg10, Object[] arg11) {
        arg0.nativeCreateOrUpdateDeviceOnScan(arg1, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
    }

    static void access$300(ChromeBluetoothAdapter arg0, long arg1) {
        arg0.nativeOnScanFailed(arg1);
    }

    private boolean canScan() {
        LocationUtils v0 = LocationUtils.getInstance();
        boolean v0_1 = !v0.hasAndroidLocationPermission() || !v0.isSystemLocationSettingEnabled() ? false : true;
        return v0_1;
    }

    @CalledByNative private static ChromeBluetoothAdapter create(long arg1, BluetoothAdapterWrapper arg3) {
        return new ChromeBluetoothAdapter(arg1, arg3);
    }

    @CalledByNative private String getAddress() {
        if(this.isPresent()) {
            return this.mAdapter.getAddress();
        }

        return "";
    }

    private String getBluetoothStateString(int arg3) {
        switch(arg3) {
            case 10: {
                return "STATE_OFF";
            }
            case 11: {
                return "STATE_TURNING_ON";
            }
            case 12: {
                return "STATE_ON";
            }
            case 13: {
                return "STATE_TURNING_OFF";
            }
        }

        return "illegal state: " + arg3;
    }

    @CalledByNative private String getName() {
        if(this.isPresent()) {
            return this.mAdapter.getName();
        }

        return "";
    }

    @CalledByNative private boolean isDiscoverable() {
        boolean v0 = !this.isPresent() || this.mAdapter.getScanMode() != 23 ? false : true;
        return v0;
    }

    @CalledByNative private boolean isDiscovering() {
        boolean v0;
        if(this.isPresent()) {
            if(!this.mAdapter.isDiscovering() && this.mScanCallback == null) {
                goto label_9;
            }

            v0 = true;
        }
        else {
        label_9:
            v0 = false;
        }

        return v0;
    }

    @CalledByNative private boolean isPowered() {
        boolean v0 = !this.isPresent() || !this.mAdapter.isEnabled() ? false : true;
        return v0;
    }

    @CalledByNative private boolean isPresent() {
        boolean v0 = this.mAdapter != null ? true : false;
        return v0;
    }

    private native void nativeCreateOrUpdateDeviceOnScan(long arg1, String arg2, BluetoothDeviceWrapper arg3, int arg4, String[] arg5, int arg6, String[] arg7, Object[] arg8, int[] arg9, Object[] arg10) {
    }

    private native void nativeOnAdapterStateChanged(long arg1, boolean arg2) {
    }

    private native void nativeOnScanFailed(long arg1) {
    }

    @CalledByNative private void onBluetoothAdapterAndroidDestruction() {
        this.stopScan();
        this.mNativeBluetoothAdapterAndroid = 0;
        this.unregisterBroadcastReceiver();
    }

    public void onReceive(Context arg6, Intent arg7) {
        String v6 = arg7.getAction();
        if((this.isPresent()) && ("android.bluetooth.adapter.action.STATE_CHANGED".equals(v6))) {
            int v6_1 = arg7.getIntExtra("android.bluetooth.adapter.extra.STATE", 0x80000000);
            Log.w("Bluetooth", "onReceive: BluetoothAdapter.ACTION_STATE_CHANGED: %s", new Object[]{this.getBluetoothStateString(v6_1)});
            if(v6_1 == 10) {
                this.nativeOnAdapterStateChanged(this.mNativeBluetoothAdapterAndroid, false);
            }
            else if(v6_1 != 12) {
            }
            else {
                this.nativeOnAdapterStateChanged(this.mNativeBluetoothAdapterAndroid, true);
            }
        }
    }

    private void registerBroadcastReceiver() {
        if(this.mAdapter != null) {
            this.mAdapter.getContext().registerReceiver(((BroadcastReceiver)this), new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        }
    }

    @CalledByNative private boolean setPowered(boolean arg3) {
        boolean v0 = false;
        if(arg3) {
            if((this.isPresent()) && (this.mAdapter.enable())) {
                v0 = true;
            }

            return v0;
        }

        if((this.isPresent()) && (this.mAdapter.disable())) {
            v0 = true;
        }

        return v0;
    }

    @CalledByNative private boolean startScan() {
        BluetoothLeScannerWrapper v0 = this.mAdapter.getBluetoothLeScanner();
        if(v0 == null) {
            return 0;
        }

        if(!this.canScan()) {
            return 0;
        }

        int v2 = 2;
        org.chromium.device.bluetooth.ChromeBluetoothAdapter$1 v4 = null;
        this.mScanCallback = new ScanCallback(this, v4);
        try {
            v0.startScan(((List)v4), v2, this.mScanCallback);
            return 1;
        }
        catch(IllegalStateException v0_1) {
            Log.e("Bluetooth", "Adapter is off. Cannot start scan: " + v0_1, new Object[0]);
            this.mScanCallback = ((ScanCallback)v4);
            return 0;
        }
        catch(IllegalArgumentException v0_2) {
            Log.e("Bluetooth", "Cannot start scan: " + v0_2, new Object[0]);
            this.mScanCallback = ((ScanCallback)v4);
            return 0;
        }
    }

    @CalledByNative private boolean stopScan() {
        if(this.mScanCallback == null) {
            return 0;
        }

        try {
            BluetoothLeScannerWrapper v0_2 = this.mAdapter.getBluetoothLeScanner();
            if(v0_2 == null) {
                goto label_31;
            }

            v0_2.stopScan(this.mScanCallback);
        }
        catch(IllegalStateException v0) {
            Log.e("Bluetooth", "Adapter is off. Cannot stop scan: " + v0, new Object[0]);
        }
        catch(IllegalArgumentException v0_1) {
            Log.e("Bluetooth", "Cannot stop scan: " + v0_1, new Object[0]);
        }

    label_31:
        this.mScanCallback = null;
        return 1;
    }

    private void unregisterBroadcastReceiver() {
        if(this.mAdapter != null) {
            this.mAdapter.getContext().unregisterReceiver(((BroadcastReceiver)this));
        }
    }
}

