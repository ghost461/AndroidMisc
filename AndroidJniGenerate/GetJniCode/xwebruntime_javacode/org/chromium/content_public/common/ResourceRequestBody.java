package org.chromium.content_public.common;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="content") public final class ResourceRequestBody {
    private byte[] mEncodedNativeForm;

    private ResourceRequestBody(byte[] arg1) {
        super();
        this.mEncodedNativeForm = arg1;
    }

    public static ResourceRequestBody createFromBytes(byte[] arg0) {
        return ResourceRequestBody.createFromEncodedNativeForm(ResourceRequestBody.nativeCreateResourceRequestBodyFromBytes(arg0));
    }

    @CalledByNative private static ResourceRequestBody createFromEncodedNativeForm(byte[] arg1) {
        return new ResourceRequestBody(arg1);
    }

    @CalledByNative private byte[] getEncodedNativeForm() {
        return this.mEncodedNativeForm;
    }

    private static native byte[] nativeCreateResourceRequestBodyFromBytes(byte[] arg0) {
    }
}

