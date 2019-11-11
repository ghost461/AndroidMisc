package org.chromium.midi;

import android.annotation.TargetApi;
import android.media.midi.MidiDevice;
import android.media.midi.MidiOutputPort;
import android.media.midi.MidiReceiver;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@TargetApi(value=23) @JNINamespace(value="midi") class MidiInputPortAndroid {
    private final MidiDevice mDevice;
    private final int mIndex;
    private long mNativeReceiverPointer;
    private MidiOutputPort mPort;

    MidiInputPortAndroid(MidiDevice arg1, int arg2) {
        super();
        this.mDevice = arg1;
        this.mIndex = arg2;
    }

    static long access$000(MidiInputPortAndroid arg2) {
        return arg2.mNativeReceiverPointer;
    }

    static void access$100(long arg0, byte[] arg2, int arg3, int arg4, long arg5) {
        MidiInputPortAndroid.nativeOnData(arg0, arg2, arg3, arg4, arg5);
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
            this.mNativeReceiverPointer = 0;
            this.mPort = null;
            return;
        }
    }

    private static native void nativeOnData(long arg0, byte[] arg1, int arg2, int arg3, long arg4) {
    }

    @CalledByNative boolean open(long arg4) {
        if(this.mPort != null) {
            return 1;
        }

        this.mPort = this.mDevice.openOutputPort(this.mIndex);
        if(this.mPort == null) {
            return 0;
        }

        this.mNativeReceiverPointer = arg4;
        this.mPort.connect(new MidiReceiver() {
            public void onSend(byte[] arg9, int arg10, int arg11, long arg12) {
                MidiInputPortAndroid.nativeOnData(MidiInputPortAndroid.this.mNativeReceiverPointer, arg9, arg10, arg11, arg12);
            }
        });
        return 1;
    }
}

