package org.chromium.services.device;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.device.battery.BatteryMonitorFactory;
import org.chromium.device.mojom.BatteryMonitor;
import org.chromium.device.mojom.NfcProvider;
import org.chromium.device.mojom.VibrationManager;
import org.chromium.device.nfc.NfcDelegate;
import org.chromium.device.nfc.NfcProviderImpl$Factory;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.services.service_manager.InterfaceRegistry;

@JNINamespace(value="device") class InterfaceRegistrar {
    InterfaceRegistrar() {
        super();
    }

    @CalledByNative static void createInterfaceRegistryForContext(int arg2, NfcDelegate arg3) {
        InterfaceRegistry v2 = InterfaceRegistry.create(CoreImpl.getInstance().acquireNativeHandle(arg2).toMessagePipeHandle());
        v2.addInterface(BatteryMonitor.MANAGER, new BatteryMonitorFactory());
        v2.addInterface(NfcProvider.MANAGER, new Factory(arg3));
        v2.addInterface(VibrationManager.MANAGER, new org.chromium.device.vibration.VibrationManagerImpl$Factory());
    }
}

