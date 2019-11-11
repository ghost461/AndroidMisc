package org.xwalk.core.internal.extensions;

import org.chromium.base.annotations.JNINamespace;

@JNINamespace(value="xwalk::extensions") public abstract class XWalkNativeExtensionLoaderAndroid {
    public XWalkNativeExtensionLoaderAndroid() {
        super();
    }

    private static native void nativeRegisterExtensionInPath(String arg0) {
    }

    public void registerNativeExtensionsInPath(String arg1) {
        XWalkNativeExtensionLoaderAndroid.nativeRegisterExtensionInPath(arg1);
    }
}

