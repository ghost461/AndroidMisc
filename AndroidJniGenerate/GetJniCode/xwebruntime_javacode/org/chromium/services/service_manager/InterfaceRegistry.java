package org.chromium.services.service_manager;

import java.util.HashMap;
import java.util.Map;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.service_manager.mojom.InterfaceProvider;

public class InterfaceRegistry implements InterfaceProvider {
    class InterfaceBinder {
        private InterfaceFactory mFactory;
        private Manager mManager;

        public InterfaceBinder(Manager arg1, InterfaceFactory arg2) {
            super();
            this.mManager = arg1;
            this.mFactory = arg2;
        }

        public void bindToMessagePipe(MessagePipeHandle arg3) {
            Interface v0 = this.mFactory.createImpl();
            if(v0 == null) {
                arg3.close();
                return;
            }

            this.mManager.bind(v0, arg3);
        }
    }

    private final Map mBinders;

    InterfaceRegistry() {
        super();
        this.mBinders = new HashMap();
    }

    public void addInterface(Manager arg4, InterfaceFactory arg5) {
        this.mBinders.put(arg4.getName(), new InterfaceBinder(arg4, arg5));
    }

    public void close() {
        this.mBinders.clear();
    }

    public static InterfaceRegistry create(MessagePipeHandle arg2) {
        InterfaceRegistry v0 = new InterfaceRegistry();
        InterfaceProvider.MANAGER.bind(((Interface)v0), arg2);
        return v0;
    }

    public void getInterface(String arg2, MessagePipeHandle arg3) {
        Object v2 = this.mBinders.get(arg2);
        if(v2 == null) {
            return;
        }

        ((InterfaceBinder)v2).bindToMessagePipe(arg3);
    }

    public void onConnectionError(MojoException arg1) {
        this.close();
    }
}

