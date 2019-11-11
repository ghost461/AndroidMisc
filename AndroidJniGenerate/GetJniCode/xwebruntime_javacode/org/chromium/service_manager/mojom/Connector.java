package org.chromium.service_manager.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.system.MessagePipeHandle;

public interface Connector extends Interface {
    public interface BindInterfaceResponse extends Callback2 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, Connector {
    }

    public interface QueryServiceResponse extends Callback2 {
    }

    public interface StartServiceResponse extends Callback2 {
    }

    public interface StartServiceWithProcessResponse extends Callback2 {
    }

    public static final Manager MANAGER;

    static {
        Connector.MANAGER = Connector_Internal.MANAGER;
    }

    void bindInterface(Identity arg1, String arg2, MessagePipeHandle arg3, BindInterfaceResponse arg4);

    void clone(InterfaceRequest arg1);

    void filterInterfaces(String arg1, Identity arg2, InterfaceRequest arg3, InterfaceProvider arg4);

    void queryService(Identity arg1, QueryServiceResponse arg2);

    void startService(Identity arg1, StartServiceResponse arg2);

    void startServiceWithProcess(Identity arg1, MessagePipeHandle arg2, InterfaceRequest arg3, StartServiceWithProcessResponse arg4);
}

