package org.chromium.device.mojom;

import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.network.mojom.MutablePartialNetworkTrafficAnnotationTag;

public interface PublicIpAddressGeolocationProvider extends Interface {
    public interface Proxy extends PublicIpAddressGeolocationProvider, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public static final Manager MANAGER;

    static {
        PublicIpAddressGeolocationProvider.MANAGER = PublicIpAddressGeolocationProvider_Internal.MANAGER;
    }

    void createGeolocation(MutablePartialNetworkTrafficAnnotationTag arg1, InterfaceRequest arg2);
}

