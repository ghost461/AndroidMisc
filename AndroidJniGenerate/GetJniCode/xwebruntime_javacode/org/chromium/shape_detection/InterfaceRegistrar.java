package org.chromium.shape_detection;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.services.service_manager.InterfaceRegistry;

@JNINamespace(value="shape_detection") class InterfaceRegistrar {
    InterfaceRegistrar() {
        super();
    }

    @CalledByNative static void createInterfaceRegistryForContext(int arg1) {
        InterfaceRegistry.create(CoreImpl.getInstance().acquireNativeHandle(arg1).toMessagePipeHandle());
    }
}

