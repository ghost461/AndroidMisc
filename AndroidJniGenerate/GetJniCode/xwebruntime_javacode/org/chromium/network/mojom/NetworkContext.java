package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Callbacks$Callback3;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo_base.mojom.Time;
import org.chromium.net.interfaces.AddressList;
import org.chromium.net.interfaces.IpEndPoint;
import org.chromium.url.mojom.Origin;

public interface NetworkContext extends Interface {
    public interface AddHstsForTestingResponse extends Callback0 {
    }

    public interface ClearHttpCacheResponse extends Callback0 {
    }

    public interface ClearNetworkingHistorySinceResponse extends Callback0 {
    }

    public interface CreateTcpConnectedSocketResponse extends Callback3 {
    }

    public interface CreateTcpServerSocketResponse extends Callback2 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, NetworkContext {
    }

    public static final Manager MANAGER;

    static {
        NetworkContext.MANAGER = NetworkContext_Internal.MANAGER;
    }

    void addHstsForTesting(String arg1, Time arg2, boolean arg3, AddHstsForTestingResponse arg4);

    void clearHttpCache(Time arg1, Time arg2, ClearCacheUrlFilter arg3, ClearHttpCacheResponse arg4);

    void clearNetworkingHistorySince(Time arg1, ClearNetworkingHistorySinceResponse arg2);

    void createTcpConnectedSocket(IpEndPoint arg1, AddressList arg2, MutableNetworkTrafficAnnotationTag arg3, InterfaceRequest arg4, TcpConnectedSocketObserver arg5, CreateTcpConnectedSocketResponse arg6);

    void createTcpServerSocket(IpEndPoint arg1, int arg2, MutableNetworkTrafficAnnotationTag arg3, InterfaceRequest arg4, CreateTcpServerSocketResponse arg5);

    void createUdpSocket(InterfaceRequest arg1, UdpSocketReceiver arg2);

    void createUrlLoaderFactory(InterfaceRequest arg1, int arg2);

    void createWebSocket(InterfaceRequest arg1, int arg2, int arg3, Origin arg4);

    void getCookieManager(InterfaceRequest arg1);

    void getRestrictedCookieManager(InterfaceRequest arg1, int arg2, int arg3);

    void setAcceptLanguage(String arg1);

    void setCtPolicy(String[] arg1, String[] arg2, String[] arg3, String[] arg4);

    void setNetworkConditions(String arg1, NetworkConditions arg2);
}

