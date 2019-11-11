package org.chromium.media;

import android.annotation.TargetApi;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.MainDex;

@TargetApi(value=23) @JNINamespace(value="media") @MainDex class MediaDrmStorageBridge {
    @MainDex class PersistentInfo {
        private final byte[] mEmeId;
        private final byte[] mKeySetId;
        private final String mMimeType;

        PersistentInfo(byte[] arg1, byte[] arg2, String arg3) {
            super();
            this.mEmeId = arg1;
            this.mKeySetId = arg2;
            this.mMimeType = arg3;
        }

        @CalledByNative(value="PersistentInfo") private static PersistentInfo create(byte[] arg1, byte[] arg2, String arg3) {
            return new PersistentInfo(arg1, arg2, arg3);
        }

        @CalledByNative(value="PersistentInfo") byte[] emeId() {
            return this.mEmeId;
        }

        @CalledByNative(value="PersistentInfo") byte[] keySetId() {
            return this.mKeySetId;
        }

        @CalledByNative(value="PersistentInfo") String mimeType() {
            return this.mMimeType;
        }
    }

    private static final long INVALID_NATIVE_MEDIA_DRM_STORAGE_BRIDGE = -1;
    private long mNativeMediaDrmStorageBridge;

    static {
    }

    MediaDrmStorageBridge(long arg1) {
        super();
        this.mNativeMediaDrmStorageBridge = arg1;
    }

    void clearInfo(byte[] arg3, Callback arg4) {
        if(this.isNativeMediaDrmStorageValid()) {
            this.nativeOnClearInfo(this.mNativeMediaDrmStorageBridge, arg3, arg4);
        }
        else {
            arg4.onResult(Boolean.valueOf(true));
        }
    }

    private boolean isNativeMediaDrmStorageValid() {
        boolean v0 = this.mNativeMediaDrmStorageBridge != -1 ? true : false;
        return v0;
    }

    void loadInfo(byte[] arg3, Callback arg4) {
        if(this.isNativeMediaDrmStorageValid()) {
            this.nativeOnLoadInfo(this.mNativeMediaDrmStorageBridge, arg3, arg4);
        }
        else {
            arg4.onResult(null);
        }
    }

    private native void nativeOnClearInfo(long arg1, byte[] arg2, Callback arg3) {
    }

    private native void nativeOnLoadInfo(long arg1, byte[] arg2, Callback arg3) {
    }

    private native void nativeOnProvisioned(long arg1, Callback arg2) {
    }

    private native void nativeOnSaveInfo(long arg1, PersistentInfo arg2, Callback arg3) {
    }

    void onProvisioned(Callback arg3) {
        if(this.isNativeMediaDrmStorageValid()) {
            this.nativeOnProvisioned(this.mNativeMediaDrmStorageBridge, arg3);
        }
        else {
            arg3.onResult(Boolean.valueOf(true));
        }
    }

    void saveInfo(PersistentInfo arg3, Callback arg4) {
        if(this.isNativeMediaDrmStorageValid()) {
            this.nativeOnSaveInfo(this.mNativeMediaDrmStorageBridge, arg3, arg4);
        }
        else {
            arg4.onResult(Boolean.valueOf(false));
        }
    }
}

