package org.chromium.media;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build$VERSION;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.provider.Settings$System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.ContextUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="media") class AudioManagerAndroid {
    class AudioDeviceName {
        private final int mId;
        private final String mName;

        AudioDeviceName(int arg1, String arg2, org.chromium.media.AudioManagerAndroid$1 arg3) {
            this(arg1, arg2);
        }

        private AudioDeviceName(int arg1, String arg2) {
            super();
            this.mId = arg1;
            this.mName = arg2;
        }

        @CalledByNative(value="AudioDeviceName") private String id() {
            return String.valueOf(this.mId);
        }

        @CalledByNative(value="AudioDeviceName") private String name() {
            return this.mName;
        }
    }

    class NonThreadSafe {
        private final Long mThreadId;

        public NonThreadSafe() {
            super();
            this.mThreadId = Long.valueOf(0);
        }

        public boolean calledOnValidThread() {
            return 1;
        }
    }

    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 0x100;
    private static final int DEFAULT_SAMPLING_RATE = 44100;
    private static final int DEVICE_BLUETOOTH_HEADSET = 3;
    private static final int DEVICE_COUNT = 5;
    private static final int DEVICE_DEFAULT = -2;
    private static final int DEVICE_EARPIECE = 2;
    private static final int DEVICE_INVALID = -1;
    private static final String[] DEVICE_NAMES = null;
    private static final int DEVICE_SPEAKERPHONE = 0;
    private static final int DEVICE_USB_AUDIO = 4;
    private static final int DEVICE_WIRED_HEADSET = 1;
    private static final int STATE_BLUETOOTH_SCO_INVALID = -1;
    private static final int STATE_BLUETOOTH_SCO_OFF = 0;
    private static final int STATE_BLUETOOTH_SCO_ON = 1;
    private static final int STATE_BLUETOOTH_SCO_TURNING_OFF = 3;
    private static final int STATE_BLUETOOTH_SCO_TURNING_ON = 2;
    private static final String[] SUPPORTED_AEC_MODELS = null;
    private static final String TAG = "cr.media";
    private static final Integer[] VALID_DEVICES;
    private boolean[] mAudioDevices;
    private final AudioManager mAudioManager;
    private BroadcastReceiver mBluetoothHeadsetReceiver;
    private BroadcastReceiver mBluetoothScoReceiver;
    private int mBluetoothScoState;
    private final ContentResolver mContentResolver;
    private int mCurrentVolume;
    private boolean mHasBluetoothPermission;
    private boolean mHasModifyAudioSettingsPermission;
    private boolean mIsInitialized;
    private final Object mLock;
    private final long mNativeAudioManagerAndroid;
    private final NonThreadSafe mNonThreadSafe;
    private int mRequestedAudioDevice;
    private boolean mSavedIsMicrophoneMute;
    private boolean mSavedIsSpeakerphoneOn;
    private ContentObserver mSettingsObserver;
    private HandlerThread mSettingsObserverThread;
    private BroadcastReceiver mUsbAudioReceiver;
    private final UsbManager mUsbManager;
    private BroadcastReceiver mWiredHeadsetReceiver;

    static {
        AudioManagerAndroid.SUPPORTED_AEC_MODELS = new String[]{"GT-I9300", "GT-I9500", "GT-N7105", "Nexus 4", "Nexus 5", "Nexus 7", "SM-N9005", "SM-T310"};
        AudioManagerAndroid.DEVICE_NAMES = new String[]{"Speakerphone", "Wired headset", "Headset earpiece", "Bluetooth headset", "USB audio"};
        AudioManagerAndroid.VALID_DEVICES = new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)};
    }

    private AudioManagerAndroid(long arg2) {
        super();
        this.mBluetoothScoState = -1;
        this.mRequestedAudioDevice = -1;
        this.mNonThreadSafe = new NonThreadSafe();
        this.mLock = new Object();
        this.mAudioDevices = new boolean[5];
        this.mNativeAudioManagerAndroid = arg2;
        this.mAudioManager = ContextUtils.getApplicationContext().getSystemService("audio");
        this.mContentResolver = ContextUtils.getApplicationContext().getContentResolver();
        this.mUsbManager = ContextUtils.getApplicationContext().getSystemService("usb");
    }

    static Object access$100(AudioManagerAndroid arg0) {
        return arg0.mLock;
    }

    static long access$1000(AudioManagerAndroid arg2) {
        return arg2.mNativeAudioManagerAndroid;
    }

    static void access$1100(AudioManagerAndroid arg0, long arg1, boolean arg3) {
        arg0.nativeSetMute(arg1, arg3);
    }

    static boolean access$1200(AudioManagerAndroid arg0, UsbDevice arg1) {
        return arg0.hasUsbAudioCommInterface(arg1);
    }

    static boolean access$1300(AudioManagerAndroid arg0) {
        return arg0.hasWiredHeadset();
    }

    static boolean[] access$200(AudioManagerAndroid arg0) {
        return arg0.mAudioDevices;
    }

    static boolean access$300(AudioManagerAndroid arg0) {
        return arg0.hasUsbAudio();
    }

    static boolean access$400(AudioManagerAndroid arg0) {
        return arg0.hasEarpiece();
    }

    static void access$500(String arg0) {
        AudioManagerAndroid.loge(arg0);
    }

    static boolean access$600(AudioManagerAndroid arg0) {
        return arg0.deviceHasBeenRequested();
    }

    static void access$700(AudioManagerAndroid arg0) {
        arg0.updateDeviceActivation();
    }

    static int access$800(AudioManagerAndroid arg0) {
        return arg0.mBluetoothScoState;
    }

    static int access$802(AudioManagerAndroid arg0, int arg1) {
        arg0.mBluetoothScoState = arg1;
        return arg1;
    }

    static AudioManager access$900(AudioManagerAndroid arg0) {
        return arg0.mAudioManager;
    }

    private void checkIfCalledOnValidThread() {
    }

    @CalledByNative private void close() {
        this.checkIfCalledOnValidThread();
        if(!this.mIsInitialized) {
            return;
        }

        this.stopObservingVolumeChanges();
        this.unregisterForWiredHeadsetIntentBroadcast();
        this.unregisterBluetoothIntentsIfNeeded();
        this.unregisterForUsbAudioIntentBroadcast();
        this.mIsInitialized = false;
    }

    @CalledByNative private static AudioManagerAndroid createAudioManagerAndroid(long arg1) {
        return new AudioManagerAndroid(arg1);
    }

    private boolean deviceHasBeenRequested() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = this.mRequestedAudioDevice != -1 ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    @CalledByNative private AudioDeviceName[] getAudioInputDeviceNames() {
        Object v2;
        AudioDeviceName[] v1 = null;
        if(!this.mIsInitialized) {
            return v1;
        }

        boolean v0 = this.hasPermission("android.permission.RECORD_AUDIO");
        int v3 = 0;
        if(this.mHasModifyAudioSettingsPermission) {
            if(!v0) {
            }
            else {
                Object v0_1 = this.mLock;
                __monitor_enter(v0_1);
                try {
                    v2 = this.mAudioDevices.clone();
                    __monitor_exit(v0_1);
                }
                catch(Throwable v1_1) {
                    try {
                    label_38:
                        __monitor_exit(v0_1);
                    }
                    catch(Throwable v1_1) {
                        goto label_38;
                    }

                    throw v1_1;
                }

                ArrayList v0_2 = new ArrayList();
                AudioDeviceName[] v4 = new AudioDeviceName[AudioManagerAndroid.getNumOfAudioDevices(((boolean[])v2))];
                int v5 = 0;
                while(v3 < 5) {
                    if(v2[v3]) {
                        v4[v5] = new AudioDeviceName(v3, AudioManagerAndroid.DEVICE_NAMES[v3], ((org.chromium.media.AudioManagerAndroid$1)v1));
                        ((List)v0_2).add(AudioManagerAndroid.DEVICE_NAMES[v3]);
                        ++v5;
                    }

                    ++v3;
                }

                return v4;
            }
        }

        Log.w("cr.media", "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. No audio device will be available for recording", new Object[0]);
        return v1;
    }

    @TargetApi(value=17) @CalledByNative private int getAudioLowLatencyOutputFrameSize() {
        int v1 = 0x100;
        if(Build$VERSION.SDK_INT < 17) {
            return v1;
        }

        String v0 = this.mAudioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if(v0 == null) {
        }
        else {
            v1 = Integer.parseInt(v0);
        }

        return v1;
    }

    @CalledByNative private static int getMinInputFrameSize(int arg2, int arg3) {
        int v1;
        int v0 = 2;
        if(arg3 == 1) {
            v1 = 16;
        }
        else if(arg3 == v0) {
            v1 = 12;
        }
        else {
            return -1;
        }

        return AudioRecord.getMinBufferSize(arg2, v1, v0) / v0 / arg3;
    }

    @CalledByNative private static int getMinOutputFrameSize(int arg2, int arg3) {
        int v1;
        int v0 = 2;
        if(arg3 == 1) {
            v1 = 4;
        }
        else if(arg3 == v0) {
            v1 = 12;
        }
        else {
            return -1;
        }

        return AudioTrack.getMinBufferSize(arg2, v1, v0) / v0 / arg3;
    }

    @TargetApi(value=17) @CalledByNative private int getNativeOutputSampleRate() {
        int v1 = 44100;
        if(Build$VERSION.SDK_INT >= 17) {
            String v0 = this.mAudioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            if(v0 == null) {
            }
            else {
                v1 = Integer.parseInt(v0);
            }

            return v1;
        }

        return v1;
    }

    private static int getNumOfAudioDevices(boolean[] arg3) {
        int v0 = 0;
        int v1 = 0;
        while(v0 < 5) {
            if(arg3[v0]) {
                ++v1;
            }

            ++v0;
        }

        return v1;
    }

    @TargetApi(value=18) private boolean hasBluetoothHeadset() {
        boolean v1 = false;
        if(!this.mHasBluetoothPermission) {
            Log.w("cr.media", "hasBluetoothHeadset() requires BLUETOOTH permission", new Object[0]);
            return 0;
        }

        BluetoothAdapter v0 = Build$VERSION.SDK_INT >= 18 ? ContextUtils.getApplicationContext().getSystemService("bluetooth").getAdapter() : BluetoothAdapter.getDefaultAdapter();
        if(v0 == null) {
            return 0;
        }

        int v3 = v0.getProfileConnectionState(1);
        if((v0.isEnabled()) && v3 == 2) {
            v1 = true;
        }

        return v1;
    }

    private boolean hasEarpiece() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean hasPermission(String arg4) {
        boolean v4 = ContextUtils.getApplicationContext().checkPermission(arg4, Process.myPid(), Process.myUid()) == 0 ? true : false;
        return v4;
    }

    private boolean hasUsbAudio() {
        HashMap v0;
        boolean v1 = false;
        if(Build$VERSION.SDK_INT < 21) {
            return 0;
        }

        try {
            v0 = this.mUsbManager.getDeviceList();
        }
        catch(NullPointerException ) {
            return 0;
        }

        Iterator v0_1 = ((Map)v0).values().iterator();
        do {
            if(v0_1.hasNext()) {
                if(!this.hasUsbAudioCommInterface(v0_1.next())) {
                    continue;
                }

                return true;
            }

            return v1;
        }
        while(true);

        return true;
    }

    private boolean hasUsbAudioCommInterface(UsbDevice arg6) {
        boolean v0 = false;
        int v1;
        for(v1 = 0; v1 < arg6.getInterfaceCount(); ++v1) {
            UsbInterface v2 = arg6.getInterface(v1);
            if(v2.getInterfaceClass() == 1 && v2.getInterfaceSubclass() == 2) {
                return true;
            }
        }

        return v0;
    }

    @Deprecated private boolean hasWiredHeadset() {
        return this.mAudioManager.isWiredHeadsetOn();
    }

    @CalledByNative private void init() {
        this.checkIfCalledOnValidThread();
        if(this.mIsInitialized) {
            return;
        }

        this.mHasModifyAudioSettingsPermission = this.hasPermission("android.permission.MODIFY_AUDIO_SETTINGS");
        this.mAudioDevices[2] = this.hasEarpiece();
        this.mAudioDevices[1] = this.hasWiredHeadset();
        this.mAudioDevices[4] = this.hasUsbAudio();
        this.mAudioDevices[0] = true;
        this.registerBluetoothIntentsIfNeeded();
        this.registerForWiredHeadsetIntentBroadcast();
        this.registerForUsbAudioIntentBroadcast();
        this.mIsInitialized = true;
    }

    @CalledByNative private boolean isAudioLowLatencySupported() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private boolean isMicrophoneMute() {
        return this.mAudioManager.isMicrophoneMute();
    }

    private void logDeviceInfo() {
        AudioManagerAndroid.logd("Android SDK: " + Build$VERSION.SDK_INT + ", Release: " + Build$VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }

    private static void logd(String arg1) {
        Log.d("cr.media", arg1);
    }

    private static void loge(String arg2) {
        Log.e("cr.media", arg2, new Object[0]);
    }

    private native void nativeSetMute(long arg1, boolean arg2) {
    }

    private void registerBluetoothIntentsIfNeeded() {
        this.mHasBluetoothPermission = this.hasPermission("android.permission.BLUETOOTH");
        if(!this.mHasBluetoothPermission) {
            Log.w("cr.media", "Requires BLUETOOTH permission", new Object[0]);
            return;
        }

        this.mAudioDevices[3] = this.hasBluetoothHeadset();
        this.registerForBluetoothHeadsetIntentBroadcast();
        this.registerForBluetoothScoIntentBroadcast();
    }

    private void registerForBluetoothHeadsetIntentBroadcast() {
        IntentFilter v0 = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        this.mBluetoothHeadsetReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg3, Intent arg4) {
                Object v3_1;
                int v3 = arg4.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                int v4 = 3;
                switch(v3) {
                    case 0: {
                        v3_1 = AudioManagerAndroid.this.mLock;
                        __monitor_enter(v3_1);
                        try {
                            AudioManagerAndroid.this.mAudioDevices[v4] = false;
                            __monitor_exit(v3_1);
                            return;
                        label_29:
                            __monitor_exit(v3_1);
                        }
                        catch(Throwable v4_1) {
                            goto label_29;
                        }

                        throw v4_1;
                    }
                    case 2: {
                        v3_1 = AudioManagerAndroid.this.mLock;
                        __monitor_enter(v3_1);
                        try {
                            AudioManagerAndroid.this.mAudioDevices[v4] = true;
                            __monitor_exit(v3_1);
                            return;
                        label_18:
                            __monitor_exit(v3_1);
                        }
                        catch(Throwable v4_1) {
                            goto label_18;
                        }

                        throw v4_1;
                    }
                    case 1: 
                    case 3: {
                        break;
                    }
                    default: {
                        AudioManagerAndroid.loge("Invalid state");
                        break;
                    }
                }
            }
        };
        ContextUtils.getApplicationContext().registerReceiver(this.mBluetoothHeadsetReceiver, v0);
    }

    private void registerForBluetoothScoIntentBroadcast() {
        IntentFilter v0 = new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        this.mBluetoothScoReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg2, Intent arg3) {
                switch(arg3.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0)) {
                    case 0: {
                        if(AudioManagerAndroid.this.mBluetoothScoState != 3 && (AudioManagerAndroid.this.deviceHasBeenRequested())) {
                            AudioManagerAndroid.this.updateDeviceActivation();
                        }

                        AudioManagerAndroid.this.mBluetoothScoState = 0;
                        break;
                    }
                    case 1: {
                        AudioManagerAndroid.this.mBluetoothScoState = 1;
                        break;
                    }
                    case 2: {
                        break;
                    }
                    default: {
                        AudioManagerAndroid.loge("Invalid state");
                        break;
                    }
                }
            }
        };
        ContextUtils.getApplicationContext().registerReceiver(this.mBluetoothScoReceiver, v0);
    }

    private void registerForUsbAudioIntentBroadcast() {
        this.mUsbAudioReceiver = new BroadcastReceiver() {
            public void onReceive(Context arg5, Intent arg6) {
                Object v5;
                if(!AudioManagerAndroid.this.hasUsbAudioCommInterface(arg6.getParcelableExtra("device"))) {
                    return;
                }

                int v1 = 2;
                int v3 = 4;
                if("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(arg6.getAction())) {
                    v5 = AudioManagerAndroid.this.mLock;
                    __monitor_enter(v5);
                    try {
                        if(!AudioManagerAndroid.this.hasWiredHeadset()) {
                            AudioManagerAndroid.this.mAudioDevices[v3] = true;
                            AudioManagerAndroid.this.mAudioDevices[v1] = false;
                        }

                        __monitor_exit(v5);
                        goto label_58;
                    label_29:
                        __monitor_exit(v5);
                    }
                    catch(Throwable v6) {
                        goto label_29;
                    }

                    throw v6;
                }
                else {
                    if(!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(arg6.getAction())) {
                        goto label_58;
                    }

                    if(AudioManagerAndroid.this.hasUsbAudio()) {
                        goto label_58;
                    }

                    v5 = AudioManagerAndroid.this.mLock;
                    __monitor_enter(v5);
                    try {
                        if(!AudioManagerAndroid.this.hasWiredHeadset()) {
                            AudioManagerAndroid.this.mAudioDevices[v3] = false;
                            if(AudioManagerAndroid.this.hasEarpiece()) {
                                AudioManagerAndroid.this.mAudioDevices[v1] = true;
                            }
                        }

                        __monitor_exit(v5);
                        goto label_58;
                    label_56:
                        __monitor_exit(v5);
                    }
                    catch(Throwable v6) {
                        goto label_56;
                    }

                    throw v6;
                }

            label_58:
                if(AudioManagerAndroid.this.deviceHasBeenRequested()) {
                    AudioManagerAndroid.this.updateDeviceActivation();
                }
            }
        };
        IntentFilter v0 = new IntentFilter();
        v0.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        v0.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        ContextUtils.getApplicationContext().registerReceiver(this.mUsbAudioReceiver, v0);
    }

    private void registerForWiredHeadsetIntentBroadcast() {
        IntentFilter v0 = new IntentFilter("android.intent.action.HEADSET_PLUG");
        this.mWiredHeadsetReceiver = new BroadcastReceiver() {
            private static final int HAS_MIC = 1;
            private static final int HAS_NO_MIC = 0;
            private static final int STATE_PLUGGED = 1;
            private static final int STATE_UNPLUGGED;

            public void onReceive(Context arg5, Intent arg6) {
                Object v5_1;
                int v5 = arg6.getIntExtra("state", 0);
                int v6 = 4;
                int v1 = 2;
                switch(v5) {
                    case 0: {
                        v5_1 = AudioManagerAndroid.this.mLock;
                        __monitor_enter(v5_1);
                        try {
                            AudioManagerAndroid.this.mAudioDevices[1] = false;
                            if(AudioManagerAndroid.this.hasUsbAudio()) {
                                AudioManagerAndroid.this.mAudioDevices[v6] = true;
                                AudioManagerAndroid.this.mAudioDevices[v1] = false;
                            }
                            else if(AudioManagerAndroid.this.hasEarpiece()) {
                                AudioManagerAndroid.this.mAudioDevices[v1] = true;
                                AudioManagerAndroid.this.mAudioDevices[v6] = false;
                            }

                            __monitor_exit(v5_1);
                            goto label_57;
                        label_55:
                            __monitor_exit(v5_1);
                        }
                        catch(Throwable v6_1) {
                            goto label_55;
                        }

                        throw v6_1;
                    }
                    case 1: {
                        v5_1 = AudioManagerAndroid.this.mLock;
                        __monitor_enter(v5_1);
                        try {
                            AudioManagerAndroid.this.mAudioDevices[1] = true;
                            AudioManagerAndroid.this.mAudioDevices[v1] = false;
                            AudioManagerAndroid.this.mAudioDevices[v6] = false;
                            __monitor_exit(v5_1);
                            goto label_57;
                        label_25:
                            __monitor_exit(v5_1);
                        }
                        catch(Throwable v6_1) {
                            goto label_25;
                        }

                        throw v6_1;
                    }
                    default: {
                        AudioManagerAndroid.loge("Invalid state");
                        break;
                    }
                }

            label_57:
                if(AudioManagerAndroid.this.deviceHasBeenRequested()) {
                    AudioManagerAndroid.this.updateDeviceActivation();
                }
            }
        };
        ContextUtils.getApplicationContext().registerReceiver(this.mWiredHeadsetReceiver, v0);
    }

    private void reportUpdate() {
    }

    private static int selectDefaultDevice(boolean[] arg2) {
        if(arg2[1]) {
            return 1;
        }

        int v0 = 4;
        if(arg2[v0]) {
            return v0;
        }

        v0 = 3;
        if(arg2[v0]) {
            return v0;
        }

        return 0;
    }

    private void setAudioDevice(int arg2) {
        if(arg2 == 3) {
            this.startBluetoothSco();
        }
        else {
            this.stopBluetoothSco();
        }

        switch(arg2) {
            case 0: {
                this.setSpeakerphoneOn(true);
                break;
            }
            case 1: {
                this.setSpeakerphoneOn(false);
                break;
            }
            case 2: {
                this.setSpeakerphoneOn(false);
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                this.setSpeakerphoneOn(false);
                break;
            }
            default: {
                AudioManagerAndroid.loge("Invalid audio device selection");
                break;
            }
        }

        this.reportUpdate();
    }

    @CalledByNative private void setCommunicationAudioModeOn(boolean arg3) {
        this.checkIfCalledOnValidThread();
        if(!this.mIsInitialized) {
            return;
        }

        if(!this.mHasModifyAudioSettingsPermission) {
            Log.w("cr.media", "MODIFY_AUDIO_SETTINGS is missing => client will run with reduced functionality", new Object[0]);
            return;
        }

        if(arg3) {
            this.mSavedIsSpeakerphoneOn = this.mAudioManager.isSpeakerphoneOn();
            this.mSavedIsMicrophoneMute = this.mAudioManager.isMicrophoneMute();
            this.startObservingVolumeChanges();
        }
        else {
            this.stopObservingVolumeChanges();
            this.stopBluetoothSco();
            Object v0 = this.mLock;
            __monitor_enter(v0);
            int v1 = -1;
            try {
                this.mRequestedAudioDevice = v1;
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                try {
                label_35:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_35;
                }

                throw v3;
            }

            this.setMicrophoneMute(this.mSavedIsMicrophoneMute);
            this.setSpeakerphoneOn(this.mSavedIsSpeakerphoneOn);
        }

        this.setCommunicationAudioModeOnInternal(arg3);
    }

    private void setCommunicationAudioModeOnInternal(boolean arg2) {
        if(arg2) {
            try {
                this.mAudioManager.setMode(3);
            }
            catch(SecurityException v2) {
                this.logDeviceInfo();
                throw v2;
            }
        }
        else {
            try {
                this.mAudioManager.setMode(0);
            }
            catch(SecurityException v2) {
                this.logDeviceInfo();
                throw v2;
            }
        }
    }

    @CalledByNative private boolean setDevice(String arg5) {
        Object v5_2;
        if(!this.mIsInitialized) {
            return 0;
        }

        boolean v0 = this.hasPermission("android.permission.RECORD_AUDIO");
        if(this.mHasModifyAudioSettingsPermission) {
            if(!v0) {
            }
            else {
                int v2 = -2;
                int v5 = arg5.isEmpty() ? -2 : Integer.parseInt(arg5);
                if(v5 == v2) {
                    Object v3 = this.mLock;
                    __monitor_enter(v3);
                    try {
                        v5_2 = this.mAudioDevices.clone();
                        this.mRequestedAudioDevice = v2;
                        __monitor_exit(v3);
                    }
                    catch(Throwable v5_1) {
                        try {
                        label_28:
                            __monitor_exit(v3);
                        }
                        catch(Throwable v5_1) {
                            goto label_28;
                        }

                        throw v5_1;
                    }

                    this.setAudioDevice(AudioManagerAndroid.selectDefaultDevice(((boolean[])v5_2)));
                    return 1;
                }

                if(Arrays.asList(AudioManagerAndroid.VALID_DEVICES).contains(Integer.valueOf(v5))) {
                    if(!this.mAudioDevices[v5]) {
                    }
                    else {
                        Object v2_1 = this.mLock;
                        __monitor_enter(v2_1);
                        try {
                            this.mRequestedAudioDevice = v5;
                            __monitor_exit(v2_1);
                        }
                        catch(Throwable v5_1) {
                            try {
                            label_46:
                                __monitor_exit(v2_1);
                            }
                            catch(Throwable v5_1) {
                                goto label_46;
                            }

                            throw v5_1;
                        }

                        this.setAudioDevice(v5);
                        return 1;
                    }
                }

                return 0;
            }
        }

        Log.w("cr.media", "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. Selected device will not be available for recording", new Object[0]);
        return 0;
    }

    private void setMicrophoneMute(boolean arg2) {
        if(this.mAudioManager.isMicrophoneMute() == arg2) {
            return;
        }

        this.mAudioManager.setMicrophoneMute(arg2);
    }

    private void setSpeakerphoneOn(boolean arg2) {
        if(this.mAudioManager.isSpeakerphoneOn() == arg2) {
            return;
        }

        this.mAudioManager.setSpeakerphoneOn(arg2);
    }

    @CalledByNative private static boolean shouldUseAcousticEchoCanceler() {
        if(!Arrays.asList(AudioManagerAndroid.SUPPORTED_AEC_MODELS).contains(Build.MODEL)) {
            return 0;
        }

        return AcousticEchoCanceler.isAvailable();
    }

    private void startBluetoothSco() {
        if(!this.mHasBluetoothPermission) {
            return;
        }

        if(this.mBluetoothScoState != 1) {
            int v2 = 2;
            if(this.mBluetoothScoState == v2) {
            }
            else if(this.mAudioManager.isBluetoothScoOn()) {
                this.mBluetoothScoState = 1;
                return;
            }
            else {
                this.mBluetoothScoState = v2;
                this.mAudioManager.startBluetoothSco();
                return;
            }
        }
    }

    private void startObservingVolumeChanges() {
        if(this.mSettingsObserverThread != null) {
            return;
        }

        this.mSettingsObserverThread = new HandlerThread("SettingsObserver");
        this.mSettingsObserverThread.start();
        this.mSettingsObserver = new ContentObserver(new Handler(this.mSettingsObserverThread.getLooper())) {
            public void onChange(boolean arg5) {
                super.onChange(arg5);
                boolean v0 = false;
                int v5 = AudioManagerAndroid.this.mAudioManager.getStreamVolume(0);
                AudioManagerAndroid v1 = AudioManagerAndroid.this;
                long v2 = AudioManagerAndroid.this.mNativeAudioManagerAndroid;
                if(v5 == 0) {
                    v0 = true;
                }

                v1.nativeSetMute(v2, v0);
            }
        };
        this.mContentResolver.registerContentObserver(Settings$System.CONTENT_URI, true, this.mSettingsObserver);
    }

    private void stopBluetoothSco() {
        if(!this.mHasBluetoothPermission) {
            return;
        }

        if(this.mBluetoothScoState != 1 && this.mBluetoothScoState != 2) {
            return;
        }

        if(!this.mAudioManager.isBluetoothScoOn()) {
            AudioManagerAndroid.loge("Unable to stop BT SCO since it is already disabled");
            this.mBluetoothScoState = 0;
            return;
        }

        this.mBluetoothScoState = 3;
        this.mAudioManager.stopBluetoothSco();
    }

    private void stopObservingVolumeChanges() {
        if(this.mSettingsObserverThread == null) {
            return;
        }

        this.mContentResolver.unregisterContentObserver(this.mSettingsObserver);
        ContentObserver v0 = null;
        this.mSettingsObserver = v0;
        this.mSettingsObserverThread.quit();
        try {
            this.mSettingsObserverThread.join();
        }
        catch(InterruptedException v1) {
            Log.e("cr.media", "Thread.join() exception: ", new Object[]{v1});
        }

        this.mSettingsObserverThread = ((HandlerThread)v0);
    }

    private void unregisterBluetoothIntentsIfNeeded() {
        if(this.mHasBluetoothPermission) {
            this.mAudioManager.stopBluetoothSco();
            this.unregisterForBluetoothHeadsetIntentBroadcast();
            this.unregisterForBluetoothScoIntentBroadcast();
        }
    }

    private void unregisterForBluetoothHeadsetIntentBroadcast() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.mBluetoothHeadsetReceiver);
        this.mBluetoothHeadsetReceiver = null;
    }

    private void unregisterForBluetoothScoIntentBroadcast() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.mBluetoothScoReceiver);
        this.mBluetoothScoReceiver = null;
    }

    private void unregisterForUsbAudioIntentBroadcast() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.mUsbAudioReceiver);
        this.mUsbAudioReceiver = null;
    }

    private void unregisterForWiredHeadsetIntentBroadcast() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.mWiredHeadsetReceiver);
        this.mWiredHeadsetReceiver = null;
    }

    private void updateDeviceActivation() {
        int v0_1;
        Object v2;
        int v1_1;
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            v1_1 = this.mRequestedAudioDevice;
            v2 = this.mAudioDevices.clone();
            __monitor_exit(v0);
            v0_1 = -1;
            if(v1_1 != v0_1) {
                goto label_11;
            }
        }
        catch(Throwable v1) {
            try {
            label_22:
                __monitor_exit(v0_1);
            }
            catch(Throwable v1) {
                goto label_22;
            }

            throw v1;
        }

        AudioManagerAndroid.loge("Unable to activate device since no device is selected");
        return;
    label_11:
        if(v1_1 == -2 || !v2[v1_1]) {
            this.setAudioDevice(AudioManagerAndroid.selectDefaultDevice(((boolean[])v2)));
        }
        else {
            this.setAudioDevice(v1_1);
        }
    }
}

