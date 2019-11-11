package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback1;
import org.chromium.mojo.bindings.Callbacks$Callback2;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo_base.mojom.ReadOnlyBuffer;
import org.chromium.net.interfaces.IpAddress;
import org.chromium.net.interfaces.IpEndPoint;

public interface UdpSocket extends Interface {
    public interface BindResponse extends Callback2 {
    }

    public interface ConnectResponse extends Callback2 {
    }

    public interface JoinGroupResponse extends Callback1 {
    }

    public interface LeaveGroupResponse extends Callback1 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, UdpSocket {
    }

    public interface SendResponse extends Callback1 {
    }

    public interface SendToResponse extends Callback1 {
    }

    public interface SetBroadcastResponse extends Callback1 {
    }

    public static final Manager MANAGER;

    static {
        UdpSocket.MANAGER = UdpSocket_Internal.MANAGER;
    }

    void bind(IpEndPoint arg1, UdpSocketOptions arg2, BindResponse arg3);

    void close();

    void connect(IpEndPoint arg1, UdpSocketOptions arg2, ConnectResponse arg3);

    void joinGroup(IpAddress arg1, JoinGroupResponse arg2);

    void leaveGroup(IpAddress arg1, LeaveGroupResponse arg2);

    void receiveMore(int arg1);

    void receiveMoreWithBufferSize(int arg1, int arg2);

    void send(ReadOnlyBuffer arg1, MutableNetworkTrafficAnnotationTag arg2, SendResponse arg3);

    void sendTo(IpEndPoint arg1, ReadOnlyBuffer arg2, MutableNetworkTrafficAnnotationTag arg3, SendToResponse arg4);

    void setBroadcast(boolean arg1, SetBroadcastResponse arg2);
}

