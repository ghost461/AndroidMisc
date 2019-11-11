package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.MessagePipeHandle;

public interface Service extends Interface {
    public interface OnBindInterfaceResponse extends Callback0 {
    }

    public interface OnStartResponse extends Callback2 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, Service {
    }

    public static final Manager MANAGER;

    static {
        Service.MANAGER = Service_Internal.MANAGER;
    }

    void onBindInterface(BindSourceInfo arg1, String arg2, MessagePipeHandle arg3, OnBindInterfaceResponse arg4);

    void onStart(Identity arg1, OnStartResponse arg2);
}

