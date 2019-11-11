package org.chromium.midi;

import android.annotation.TargetApi;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Handler;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="midi") class UsbMidiDeviceAndroid {
    static final int MIDI_SUBCLASS = 3;
    static final int REQUEST_GET_DESCRIPTOR = 6;
    static final int STRING_DESCRIPTOR_TYPE = 3;
    private final UsbDeviceConnection mConnection;
    private final SparseArray mEndpointMap;
    private final Handler mHandler;
    private boolean mHasInputThread;
    private boolean mIsClosed;
    private long mNativePointer;
    private final Map mRequestMap;
    private UsbDevice mUsbDevice;

    UsbMidiDeviceAndroid(UsbManager arg7, UsbDevice arg8) {
        super();
        this.mConnection = arg7.openDevice(arg8);
        this.mEndpointMap = new SparseArray();
        this.mRequestMap = new HashMap();
        this.mHandler = new Handler();
        this.mUsbDevice = arg8;
        this.mIsClosed = false;
        this.mHasInputThread = false;
        this.mNativePointer = 0;
        int v0;
        for(v0 = 0; v0 < arg8.getInterfaceCount(); ++v0) {
            UsbInterface v1 = arg8.getInterface(v0);
            if(v1.getInterfaceClass() == 1) {
                if(v1.getInterfaceSubclass() != 3) {
                }
                else {
                    this.mConnection.claimInterface(v1, true);
                    int v2;
                    for(v2 = 0; v2 < v1.getEndpointCount(); ++v2) {
                        UsbEndpoint v3 = v1.getEndpoint(v2);
                        if(v3.getDirection() == 0) {
                            this.mEndpointMap.put(v3.getEndpointNumber(), v3);
                        }
                    }
                }
            }
        }

        this.startListen(arg8);
    }

    static UsbDeviceConnection access$000(UsbMidiDeviceAndroid arg0) {
        return arg0.mConnection;
    }

    static int access$100(ByteBuffer arg0) {
        return UsbMidiDeviceAndroid.getInputDataLength(arg0);
    }

    static void access$200(UsbMidiDeviceAndroid arg0, int arg1, byte[] arg2) {
        arg0.postOnDataEvent(arg1, arg2);
    }

    static boolean access$300(UsbMidiDeviceAndroid arg0) {
        return arg0.mIsClosed;
    }

    static long access$400(UsbMidiDeviceAndroid arg2) {
        return arg2.mNativePointer;
    }

    static void access$500(long arg0, int arg2, byte[] arg3) {
        UsbMidiDeviceAndroid.nativeOnData(arg0, arg2, arg3);
    }

    @CalledByNative void close() {
        this.mEndpointMap.clear();
        Iterator v0 = this.mRequestMap.values().iterator();
        while(v0.hasNext()) {
            v0.next().close();
        }

        this.mRequestMap.clear();
        this.mConnection.close();
        this.mNativePointer = 0;
        this.mIsClosed = true;
    }

    @CalledByNative byte[] getDescriptors() {
        if(this.mConnection == null) {
            return new byte[0];
        }

        return this.mConnection.getRawDescriptors();
    }

    private static int getInputDataLength(ByteBuffer arg3) {
        int v0 = arg3.position();
        int v1;
        for(v1 = 0; v1 < v0; v1 += 4) {
            if(arg3.get(v1) == 0) {
                return v1;
            }
        }

        return v0;
    }

    @CalledByNative byte[] getStringDescriptor(int arg11) {
        if(this.mConnection == null) {
            return new byte[0];
        }

        byte[] v0 = new byte[0xFF];
        arg11 = this.mConnection.controlTransfer(0x80, 6, arg11 | 0x300, 0, v0, v0.length, 0);
        if(arg11 < 0) {
            return new byte[0];
        }

        return Arrays.copyOf(v0, arg11);
    }

    UsbDevice getUsbDevice() {
        return this.mUsbDevice;
    }

    boolean isClosed() {
        return this.mIsClosed;
    }

    private static native void nativeOnData(long arg0, int arg1, byte[] arg2) {
    }

    private void postOnDataEvent(int arg3, byte[] arg4) {
        this.mHandler.post(new Runnable(arg3, arg4) {
            public void run() {
                if(UsbMidiDeviceAndroid.this.mIsClosed) {
                    return;
                }

                UsbMidiDeviceAndroid.nativeOnData(UsbMidiDeviceAndroid.this.mNativePointer, this.val$endpointNumber, this.val$bs);
            }
        });
    }

    @CalledByNative void registerSelf(long arg1) {
        this.mNativePointer = arg1;
    }

    @TargetApi(value=18) @CalledByNative void send(int arg4, byte[] arg5) {
        if(this.mIsClosed) {
            return;
        }

        Object v4 = this.mEndpointMap.get(arg4);
        if(v4 == null) {
            return;
        }

        if(this.shouldUseBulkTransfer()) {
            this.mConnection.bulkTransfer(((UsbEndpoint)v4), arg5, arg5.length, 100);
        }
        else {
            Object v0 = this.mRequestMap.get(v4);
            if(v0 == null) {
                UsbRequest v0_1 = new UsbRequest();
                v0_1.initialize(this.mConnection, ((UsbEndpoint)v4));
                this.mRequestMap.put(v4, v0_1);
            }

            ((UsbRequest)v0).queue(ByteBuffer.wrap(arg5), arg5.length);
        }
    }

    private boolean shouldUseBulkTransfer() {
        return this.mHasInputThread;
    }

    private void startListen(UsbDevice arg10) {
        HashMap v0 = new HashMap();
        int v2;
        for(v2 = 0; v2 < arg10.getInterfaceCount(); ++v2) {
            UsbInterface v3 = arg10.getInterface(v2);
            if(v3.getInterfaceClass() == 1) {
                if(v3.getInterfaceSubclass() != 3) {
                }
                else {
                    int v4;
                    for(v4 = 0; v4 < v3.getEndpointCount(); ++v4) {
                        UsbEndpoint v5 = v3.getEndpoint(v4);
                        if(v5.getDirection() == 0x80) {
                            ByteBuffer v6 = ByteBuffer.allocate(v5.getMaxPacketSize());
                            UsbRequest v7 = new UsbRequest();
                            v7.initialize(this.mConnection, v5);
                            v7.queue(v6, v6.remaining());
                            ((Map)v0).put(v5, v6);
                        }
                    }
                }
            }
        }

        if(((Map)v0).isEmpty()) {
            return;
        }

        this.mHasInputThread = true;
        new Thread(((Map)v0)) {
            public void run() {
                while(true) {
                    UsbRequest v0 = UsbMidiDeviceAndroid.this.mConnection.requestWait();
                    if(v0 == null) {
                        return;
                    }

                    UsbEndpoint v1 = v0.getEndpoint();
                    if(v1.getDirection() != 0x80) {
                        continue;
                    }

                    Object v2 = this.val$bufferForEndpoints.get(v1);
                    int v3 = UsbMidiDeviceAndroid.getInputDataLength(((ByteBuffer)v2));
                    if(v3 > 0) {
                        ((ByteBuffer)v2).rewind();
                        byte[] v4 = new byte[v3];
                        ((ByteBuffer)v2).get(v4, 0, v3);
                        UsbMidiDeviceAndroid.this.postOnDataEvent(v1.getEndpointNumber(), v4);
                    }

                    ((ByteBuffer)v2).rewind();
                    v0.queue(((ByteBuffer)v2), ((ByteBuffer)v2).capacity());
                }
            }
        }.start();
    }
}

