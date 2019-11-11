package org.chromium.device.usb;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Parcelable;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="device") final class ChromeUsbService {
    private static final String ACTION_USB_PERMISSION = "org.chromium.device.ACTION_USB_PERMISSION";
    private static final String TAG = "Usb";
    BroadcastReceiver mUsbDeviceReceiver;
    UsbManager mUsbManager;
    long mUsbServiceAndroid;

    private ChromeUsbService(long arg1) {
        super();
        this.mUsbServiceAndroid = arg1;
        this.mUsbManager = ContextUtils.getApplicationContext().getSystemService("usb");
        this.registerForUsbDeviceIntentBroadcast();
        Log.v("Usb", "ChromeUsbService created.");
    }

    static void access$000(ChromeUsbService arg0, long arg1, UsbDevice arg3) {
        arg0.nativeDeviceAttached(arg1, arg3);
    }

    static void access$100(ChromeUsbService arg0, long arg1, int arg3) {
        arg0.nativeDeviceDetached(arg1, arg3);
    }

    static void access$200(ChromeUsbService arg0, long arg1, int arg3, boolean arg4) {
        arg0.nativeDevicePermissionRequestComplete(arg1, arg3, arg4);
    }

    @CalledByNative private void close() {
        this.unregisterForUsbDeviceIntentBroadcast();
    }

    @CalledByNative private static ChromeUsbService create(long arg1) {
        return new ChromeUsbService(arg1);
    }

    @CalledByNative private Object[] getDevices() {
        return this.mUsbManager.getDeviceList().values().toArray();
    }

    private native void nativeDeviceAttached(long arg1, UsbDevice arg2) {
    }

    private native void nativeDeviceDetached(long arg1, int arg2) {
    }

    private native void nativeDevicePermissionRequestComplete(long arg1, int arg2, boolean arg3) {
    }

    @CalledByNative private UsbDeviceConnection openDevice(ChromeUsbDevice arg2) {
        return this.mUsbManager.openDevice(arg2.getDevice());
    }

    private void registerForUsbDeviceIntentBroadcast() {
        this.mUsbDeviceReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg6, Intent arg7) {
                Parcelable v6 = arg7.getParcelableExtra("device");
                if("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(arg7.getAction())) {
                    ChromeUsbService.this.nativeDeviceAttached(ChromeUsbService.this.mUsbServiceAndroid, ((UsbDevice)v6));
                }
                else if("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(arg7.getAction())) {
                    ChromeUsbService.this.nativeDeviceDetached(ChromeUsbService.this.mUsbServiceAndroid, ((UsbDevice)v6).getDeviceId());
                }
                else if("org.chromium.device.ACTION_USB_PERMISSION".equals(arg7.getAction())) {
                    ChromeUsbService.this.nativeDevicePermissionRequestComplete(ChromeUsbService.this.mUsbServiceAndroid, ((UsbDevice)v6).getDeviceId(), arg7.getBooleanExtra("permission", false));
                }
            }
        };
        IntentFilter v0 = new IntentFilter();
        v0.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        v0.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        v0.addAction("org.chromium.device.ACTION_USB_PERMISSION");
        ContextUtils.getApplicationContext().registerReceiver(this.mUsbDeviceReceiver, v0);
    }

    @CalledByNative private void requestDevicePermission(ChromeUsbDevice arg3, long arg4) {
        UsbDevice v4 = arg3.getDevice();
        if(this.mUsbManager.hasPermission(v4)) {
            this.nativeDevicePermissionRequestComplete(this.mUsbServiceAndroid, v4.getDeviceId(), true);
        }
        else {
            this.mUsbManager.requestPermission(arg3.getDevice(), PendingIntent.getBroadcast(ContextUtils.getApplicationContext(), 0, new Intent("org.chromium.device.ACTION_USB_PERMISSION"), 0));
        }
    }

    private void unregisterForUsbDeviceIntentBroadcast() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.mUsbDeviceReceiver);
        this.mUsbDeviceReceiver = null;
    }
}

