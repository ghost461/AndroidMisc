package org.chromium.midi;

import android.annotation.TargetApi;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=23) @JNINamespace(value="midi") class MidiDeviceAndroid {
    private final MidiDevice mDevice;
    private final MidiInputPortAndroid[] mInputPorts;
    private boolean mIsOpen;
    private final MidiOutputPortAndroid[] mOutputPorts;

    MidiDeviceAndroid(MidiDevice arg5) {
        super();
        this.mIsOpen = true;
        this.mDevice = arg5;
        this.mOutputPorts = new MidiOutputPortAndroid[this.getInfo().getInputPortCount()];
        int v0 = 0;
        int v1;
        for(v1 = 0; v1 < this.mOutputPorts.length; ++v1) {
            this.mOutputPorts[v1] = new MidiOutputPortAndroid(arg5, v1);
        }

        this.mInputPorts = new MidiInputPortAndroid[this.getInfo().getOutputPortCount()];
        while(v0 < this.mInputPorts.length) {
            this.mInputPorts[v0] = new MidiInputPortAndroid(arg5, v0);
            ++v0;
        }
    }

    void close() {
        int v0 = 0;
        this.mIsOpen = false;
        MidiInputPortAndroid[] v1 = this.mInputPorts;
        int v2 = v1.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1[v3].close();
        }

        MidiOutputPortAndroid[] v1_1 = this.mOutputPorts;
        v2 = v1_1.length;
        while(v0 < v2) {
            v1_1[v0].close();
            ++v0;
        }
    }

    MidiDevice getDevice() {
        return this.mDevice;
    }

    MidiDeviceInfo getInfo() {
        return this.mDevice.getInfo();
    }

    @CalledByNative MidiInputPortAndroid[] getInputPorts() {
        return this.mInputPorts;
    }

    @CalledByNative String getManufacturer() {
        return this.getProperty("manufacturer");
    }

    @CalledByNative MidiOutputPortAndroid[] getOutputPorts() {
        return this.mOutputPorts;
    }

    @CalledByNative String getProduct() {
        String v0 = this.getProperty("product");
        if(v0 != null) {
            if(v0.isEmpty()) {
            }
            else {
                return v0;
            }
        }

        return this.getProperty("name");
    }

    private String getProperty(String arg2) {
        return this.mDevice.getInfo().getProperties().getString(arg2);
    }

    @CalledByNative String getVersion() {
        return this.getProperty("version");
    }

    boolean isOpen() {
        return this.mIsOpen;
    }
}

