package org.chromium.midi;

import android.annotation.TargetApi;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager$DeviceCallback;
import android.media.midi.MidiManager$OnDeviceOpenedListener;
import android.media.midi.MidiManager;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=23) @JNINamespace(value="midi") class MidiManagerAndroid {
    private final List mDevices;
    private final Handler mHandler;
    private boolean mIsInitialized;
    private final MidiManager mManager;
    private final long mNativeManagerPointer;
    private final Set mPendingDevices;

    static {
    }

    private MidiManagerAndroid(long arg3) {
        super();
        this.mDevices = new ArrayList();
        this.mPendingDevices = new HashSet();
        this.mManager = ContextUtils.getApplicationContext().getSystemService("midi");
        this.mHandler = new Handler(ThreadUtils.getUiThreadLooper());
        this.mNativeManagerPointer = arg3;
    }

    static long access$000(MidiManagerAndroid arg2) {
        return arg2.mNativeManagerPointer;
    }

    static void access$100(MidiManagerAndroid arg0, MidiDeviceInfo arg1) {
        arg0.onDeviceAdded(arg1);
    }

    static void access$200(MidiManagerAndroid arg0, MidiDeviceInfo arg1) {
        arg0.onDeviceRemoved(arg1);
    }

    static Set access$300(MidiManagerAndroid arg0) {
        return arg0.mPendingDevices;
    }

    static boolean access$400(MidiManagerAndroid arg0) {
        return arg0.mIsInitialized;
    }

    static boolean access$402(MidiManagerAndroid arg0, boolean arg1) {
        arg0.mIsInitialized = arg1;
        return arg1;
    }

    static List access$500(MidiManagerAndroid arg0) {
        return arg0.mDevices;
    }

    static void access$600(MidiManagerAndroid arg0, MidiDevice arg1, MidiDeviceInfo arg2) {
        arg0.onDeviceOpened(arg1, arg2);
    }

    @CalledByNative static MidiManagerAndroid create(long arg1) {
        return new MidiManagerAndroid(arg1);
    }

    @CalledByNative static boolean hasSystemFeatureMidi() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.software.midi");
    }

    @CalledByNative void initialize() {
        if(this.mManager == null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    MidiManagerAndroid.nativeOnInitializationFailed(MidiManagerAndroid.this.mNativeManagerPointer);
                }
            });
            return;
        }

        this.mManager.registerDeviceCallback(new MidiManager$DeviceCallback() {
            public void onDeviceAdded(MidiDeviceInfo arg2) {
                MidiManagerAndroid.this.onDeviceAdded(arg2);
            }

            public void onDeviceRemoved(MidiDeviceInfo arg2) {
                MidiManagerAndroid.this.onDeviceRemoved(arg2);
            }
        }, this.mHandler);
        MidiDeviceInfo[] v0 = this.mManager.getDevices();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            MidiDeviceInfo v3 = v0[v2];
            this.mPendingDevices.add(v3);
            this.openDevice(v3);
        }

        this.mHandler.post(new Runnable() {
            public void run() {
                if((MidiManagerAndroid.this.mPendingDevices.isEmpty()) && !MidiManagerAndroid.this.mIsInitialized) {
                    MidiManagerAndroid.nativeOnInitialized(MidiManagerAndroid.this.mNativeManagerPointer, MidiManagerAndroid.this.mDevices.toArray(new MidiDeviceAndroid[0]));
                    MidiManagerAndroid.this.mIsInitialized = true;
                }
            }
        });
    }

    static native void nativeOnAttached(long arg0, MidiDeviceAndroid arg1) {
    }

    static native void nativeOnDetached(long arg0, MidiDeviceAndroid arg1) {
    }

    static native void nativeOnInitializationFailed(long arg0) {
    }

    static native void nativeOnInitialized(long arg0, MidiDeviceAndroid[] arg1) {
    }

    private void onDeviceAdded(MidiDeviceInfo arg2) {
        if(!this.mIsInitialized) {
            this.mPendingDevices.add(arg2);
        }

        this.openDevice(arg2);
    }

    private void onDeviceOpened(MidiDevice arg3, MidiDeviceInfo arg4) {
        this.mPendingDevices.remove(arg4);
        if(arg3 != null) {
            MidiDeviceAndroid v4 = new MidiDeviceAndroid(arg3);
            this.mDevices.add(v4);
            if(this.mIsInitialized) {
                MidiManagerAndroid.nativeOnAttached(this.mNativeManagerPointer, v4);
            }
        }

        if(!this.mIsInitialized && (this.mPendingDevices.isEmpty())) {
            MidiManagerAndroid.nativeOnInitialized(this.mNativeManagerPointer, this.mDevices.toArray(new MidiDeviceAndroid[0]));
            this.mIsInitialized = true;
        }
    }

    private void onDeviceRemoved(MidiDeviceInfo arg5) {
        Iterator v0 = this.mDevices.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(!((MidiDeviceAndroid)v1).isOpen()) {
                continue;
            }

            if(((MidiDeviceAndroid)v1).getInfo().getId() != arg5.getId()) {
                continue;
            }

            ((MidiDeviceAndroid)v1).close();
            MidiManagerAndroid.nativeOnDetached(this.mNativeManagerPointer, ((MidiDeviceAndroid)v1));
        }
    }

    private void openDevice(MidiDeviceInfo arg4) {
        this.mManager.openDevice(arg4, new MidiManager$OnDeviceOpenedListener(arg4) {
            public void onDeviceOpened(MidiDevice arg3) {
                MidiManagerAndroid.this.onDeviceOpened(arg3, this.val$info);
            }
        }, this.mHandler);
    }
}

