package org.chromium.media.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;

public interface InterfaceFactory extends Interface {
    public interface Proxy extends InterfaceFactory, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        InterfaceFactory.MANAGER = InterfaceFactory_Internal.MANAGER;
    }

    void createAudioDecoder(InterfaceRequest arg1);

    void createCdm(String arg1, InterfaceRequest arg2);

    void createCdmProxy(String arg1, InterfaceRequest arg2);

    void createRenderer(int arg1, String arg2, InterfaceRequest arg3);

    void createVideoDecoder(InterfaceRequest arg1);
}

