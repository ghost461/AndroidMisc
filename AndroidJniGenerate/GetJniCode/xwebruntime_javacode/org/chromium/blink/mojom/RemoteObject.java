package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface RemoteObject extends Interface {
    public interface GetMethodsResponse extends Callback1 {
    }

    public interface HasMethodResponse extends Callback1 {
    }

    public interface InvokeMethodResponse extends Callback1 {
    }

    public interface Proxy extends RemoteObject, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        RemoteObject.MANAGER = RemoteObject_Internal.MANAGER;
    }

    void getMethods(GetMethodsResponse arg1);

    void hasMethod(String arg1, HasMethodResponse arg2);

    void invokeMethod(String arg1, RemoteInvocationArgument[] arg2, InvokeMethodResponse arg3);
}

