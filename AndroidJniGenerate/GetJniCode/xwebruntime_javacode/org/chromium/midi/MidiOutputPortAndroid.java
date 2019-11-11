package org.chromium.midi;

import android.annotation.TargetApi;
import android.media.midi.MidiDevice;
import android.media.midi.MidiInputPort;
import java.io.IOException;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=23) @JNINamespace(value="midi") class MidiOutputPortAndroid {
    private static final String TAG = "midi";
    private final MidiDevice mDevice;
    private final int mIndex;
    private MidiInputPort mPort;

    MidiOutputPortAndroid(MidiDevice arg1, int arg2) {
        super();
        this.mDevice = arg1;
        this.mIndex = arg2;
    }

    @CalledByNative void close() {
        if(this.mPort == null) {
            return;
        }

        try {
            this.mPort.close();
            goto label_5;
        }
        catch(IOException ) {
        label_5:
            this.mPort = null;
            return;
        }
    }

    @CalledByNative boolean open() {
        boolean v1 = true;
        if(this.mPort != null) {
            return 1;
        }

        this.mPort = this.mDevice.openInputPort(this.mIndex);
        if(this.mPort != null) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    @CalledByNative void send(byte[] arg5) {
        if(this.mPort == null) {
            return;
        }

        try {
            this.mPort.send(arg5, 0, arg5.length);
        }
        catch(IOException v5) {
            Log.e("midi", "MidiOutputPortAndroid.send: " + v5, new Object[0]);
        }
    }
}

