package org.xwalk.core.internal.extensions;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.xwalk.core.internal.Log;

@JNINamespace(value="xwalk::extensions") public abstract class XWalkExtensionAndroid {
    private static final String TAG = "XWalkExtensionAndroid";
    private long mXWalkExtension;

    public XWalkExtensionAndroid(String arg2, String arg3) {
        super();
        this.mXWalkExtension = this.nativeGetOrCreateExtension(arg2, arg3, null);
    }

    public XWalkExtensionAndroid(String arg1, String arg2, String[] arg3) {
        super();
        this.mXWalkExtension = this.nativeGetOrCreateExtension(arg1, arg2, arg3);
    }

    public void broadcastMessage(String arg6) {
        if(this.mXWalkExtension == 0) {
            Log.e("XWalkExtensionAndroid", "Can not broadcast message to an invalid extension!");
            return;
        }

        this.nativeBroadcastMessage(this.mXWalkExtension, arg6);
    }

    protected void destroyExtension() {
        long v2 = 0;
        if(this.mXWalkExtension == v2) {
            Log.e("XWalkExtensionAndroid", "The extension to be destroyed is invalid!");
            return;
        }

        this.nativeDestroyExtension(this.mXWalkExtension);
        this.mXWalkExtension = v2;
    }

    private native void nativeBroadcastMessage(long arg1, String arg2) {
    }

    private native void nativeDestroyExtension(long arg1) {
    }

    private native long nativeGetOrCreateExtension(String arg1, String arg2, String[] arg3) {
    }

    private native void nativePostBinaryMessage(long arg1, int arg2, byte[] arg3) {
    }

    private native void nativePostMessage(long arg1, int arg2, String arg3) {
    }

    @CalledByNative public void onBinaryMessage(int arg1, byte[] arg2) {
    }

    @CalledByNative public void onInstanceCreated(int arg1) {
    }

    @CalledByNative public void onInstanceDestroyed(int arg1) {
    }

    @CalledByNative public abstract void onMessage(int arg1, String arg2);

    @CalledByNative public abstract String onSyncMessage(int arg1, String arg2);

    public void postBinaryMessage(int arg6, byte[] arg7) {
        if(this.mXWalkExtension == 0) {
            Log.e("XWalkExtensionAndroid", "Can not post a binary message to an invalid extension!");
            return;
        }

        this.nativePostBinaryMessage(this.mXWalkExtension, arg6, arg7);
    }

    public void postMessage(int arg6, String arg7) {
        if(this.mXWalkExtension == 0) {
            Log.e("XWalkExtensionAndroid", "Can not post a message to an invalid extension!");
            return;
        }

        this.nativePostMessage(this.mXWalkExtension, arg6, arg7);
    }
}

