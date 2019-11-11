package org.chromium.midi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="midi") class UsbMidiDeviceFactoryAndroid {
    private static final String ACTION_USB_PERMISSION = "org.chromium.midi.USB_PERMISSION";
    private final List mDevices;
    private boolean mIsEnumeratingDevices;
    private long mNativePointer;
    private BroadcastReceiver mReceiver;
    private Set mRequestedDevices;
    private UsbManager mUsbManager;

    static {
    }

    UsbMidiDeviceFactoryAndroid(long arg3) {
        super();
        this.mDevices = new ArrayList();
        this.mUsbManager = ContextUtils.getApplicationContext().getSystemService("usb");
        this.mNativePointer = arg3;
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg4, Intent arg5) {
                Parcelable v0 = arg5.getParcelableExtra("device");
                if("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(arg5.getAction())) {
                    UsbMidiDeviceFactoryAndroid.access$000(UsbMidiDeviceFactoryAndroid.this, v0);
                }

                if("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(arg5.getAction())) {
                    UsbMidiDeviceFactoryAndroid.access$100(UsbMidiDeviceFactoryAndroid.this, ((UsbDevice)v0));
                }

                if("org.chromium.midi.USB_PERMISSION".equals(arg5.getAction())) {
                    UsbMidiDeviceFactoryAndroid.access$200(UsbMidiDeviceFactoryAndroid.this, arg4, arg5);
                }
            }
        };
        IntentFilter v3 = new IntentFilter();
        v3.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        v3.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        v3.addAction("org.chromium.midi.USB_PERMISSION");
        ContextUtils.getApplicationContext().registerReceiver(this.mReceiver, v3);
        this.mRequestedDevices = new HashSet();
    }

    static void access$000(UsbMidiDeviceFactoryAndroid arg0, UsbDevice arg1) {
        arg0.requestDevicePermissionIfNecessary(arg1);
    }

    static void access$100(UsbMidiDeviceFactoryAndroid arg0, UsbDevice arg1) {
        arg0.onUsbDeviceDetached(arg1);
    }

    static void access$200(UsbMidiDeviceFactoryAndroid arg0, Context arg1, Intent arg2) {
        arg0.onUsbDevicePermissionRequestDone(arg1, arg2);
    }

    @CalledByNative void close() {
        this.mNativePointer = 0;
        ContextUtils.getApplicationContext().unregisterReceiver(this.mReceiver);
    }

    @CalledByNative static UsbMidiDeviceFactoryAndroid create(long arg1) {
        return new UsbMidiDeviceFactoryAndroid(arg1);
    }

    @CalledByNative boolean enumerateDevices() {
        this.mIsEnumeratingDevices = true;
        HashMap v1 = this.mUsbManager.getDeviceList();
        if(((Map)v1).isEmpty()) {
            this.mIsEnumeratingDevices = false;
            return 0;
        }

        Iterator v1_1 = ((Map)v1).values().iterator();
        while(v1_1.hasNext()) {
            this.requestDevicePermissionIfNecessary(v1_1.next());
        }

        return 1 ^ this.mRequestedDevices.isEmpty();
    }

    private static native void nativeOnUsbMidiDeviceAttached(long arg0, Object arg1) {
    }

    private static native void nativeOnUsbMidiDeviceDetached(long arg0, int arg1) {
    }

    private static native void nativeOnUsbMidiDeviceRequestDone(long arg0, Object[] arg1) {
    }

    private void onUsbDeviceDetached(UsbDevice arg6) {
        Object v1;
        Iterator v0 = this.mRequestedDevices.iterator();
        do {
            if(v0.hasNext()) {
                v1 = v0.next();
                if(((UsbDevice)v1).getDeviceId() != arg6.getDeviceId()) {
                    continue;
                }

                break;
            }

            goto label_10;
        }
        while(true);

        this.mRequestedDevices.remove(v1);
    label_10:
        int v0_1;
        for(v0_1 = 0; v0_1 < this.mDevices.size(); ++v0_1) {
            v1 = this.mDevices.get(v0_1);
            if(((UsbMidiDeviceAndroid)v1).isClosed()) {
            }
            else if(((UsbMidiDeviceAndroid)v1).getUsbDevice().getDeviceId() == arg6.getDeviceId()) {
                ((UsbMidiDeviceAndroid)v1).close();
                if(this.mIsEnumeratingDevices) {
                    this.mDevices.remove(v0_1);
                    return;
                }
                else {
                    if(this.mNativePointer != 0) {
                        UsbMidiDeviceFactoryAndroid.nativeOnUsbMidiDeviceDetached(this.mNativePointer, v0_1);
                    }

                    return;
                }
            }
        }
    }

    private void onUsbDevicePermissionRequestDone(Context arg6, Intent arg7) {
        Parcelable v6 = arg7.getParcelableExtra("device");
        UsbMidiDeviceAndroid v2 = null;
        if(this.mRequestedDevices.contains(v6)) {
            this.mRequestedDevices.remove(v6);
            if(!arg7.getBooleanExtra("permission", false)) {
                goto label_12;
            }
        }
        else {
        label_12:
            v6 = ((Parcelable)v2);
        }

        if(v6 != null) {
            Iterator v7 = this.mDevices.iterator();
            do {
                if(v7.hasNext()) {
                    Object v0 = v7.next();
                    if(((UsbMidiDeviceAndroid)v0).isClosed()) {
                        continue;
                    }

                    if(((UsbMidiDeviceAndroid)v0).getUsbDevice().getDeviceId() != ((UsbDevice)v6).getDeviceId()) {
                        continue;
                    }

                    break;
                }

                goto label_26;
            }
            while(true);

            v6 = ((Parcelable)v2);
        }

    label_26:
        if(v6 != null) {
            v2 = new UsbMidiDeviceAndroid(this.mUsbManager, ((UsbDevice)v6));
            this.mDevices.add(v2);
        }

        if(!this.mRequestedDevices.isEmpty()) {
            return;
        }

        if(this.mNativePointer == 0) {
            return;
        }

        if(this.mIsEnumeratingDevices) {
            UsbMidiDeviceFactoryAndroid.nativeOnUsbMidiDeviceRequestDone(this.mNativePointer, this.mDevices.toArray());
            this.mIsEnumeratingDevices = false;
        }
        else if(v2 != null) {
            UsbMidiDeviceFactoryAndroid.nativeOnUsbMidiDeviceAttached(this.mNativePointer, v2);
        }
    }

    private void requestDevicePermissionIfNecessary(UsbDevice arg6) {
        Iterator v0 = this.mRequestedDevices.iterator();
        do {
            if(!v0.hasNext()) {
                goto label_9;
            }
        }
        while(v0.next().getDeviceId() != arg6.getDeviceId());

        return;
    label_9:
        int v1;
        for(v1 = 0; v1 < arg6.getInterfaceCount(); ++v1) {
            UsbInterface v2 = arg6.getInterface(v1);
            if(v2.getInterfaceClass() == 1 && v2.getInterfaceSubclass() == 3) {
                this.mUsbManager.requestPermission(arg6, PendingIntent.getBroadcast(ContextUtils.getApplicationContext(), 0, new Intent("org.chromium.midi.USB_PERMISSION"), 0));
                this.mRequestedDevices.add(arg6);
                return;
            }
        }
    }
}

