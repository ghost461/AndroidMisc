package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.url.mojom.Url;

public interface ServiceWorkerHost extends Interface {
    public interface ClaimClientsResponse extends Callback2 {
    }

    public interface FocusClientResponse extends Callback1 {
    }

    public interface GetClientResponse extends Callback1 {
    }

    public interface GetClientsResponse extends Callback1 {
    }

    public interface NavigateClientResponse extends Callback3 {
    }

    public interface OpenNewTabResponse extends Callback3 {
    }

    public interface OpenPaymentHandlerWindowResponse extends Callback3 {
    }

    public interface Proxy extends ServiceWorkerHost, org.chromium.mojo.bindings.Interface$Proxy {
    }

    public interface SkipWaitingResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        ServiceWorkerHost.MANAGER = ServiceWorkerHost_Internal.MANAGER;
    }

    void claimClients(ClaimClientsResponse arg1);

    void clearCachedMetadata(Url arg1);

    void focusClient(String arg1, FocusClientResponse arg2);

    void getClient(String arg1, GetClientResponse arg2);

    void getClients(ServiceWorkerClientQueryOptions arg1, GetClientsResponse arg2);

    void navigateClient(String arg1, Url arg2, NavigateClientResponse arg3);

    void openNewTab(Url arg1, OpenNewTabResponse arg2);

    void openPaymentHandlerWindow(Url arg1, OpenPaymentHandlerWindowResponse arg2);

    void postMessageToClient(String arg1, TransferableMessage arg2);

    void setCachedMetadata(Url arg1, byte[] arg2);

    void skipWaiting(SkipWaitingResponse arg1);
}

