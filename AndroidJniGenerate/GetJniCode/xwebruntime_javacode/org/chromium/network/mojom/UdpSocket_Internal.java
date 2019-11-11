package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface$Proxy;
import org.chromium.mojo.bindings.Interface$Stub;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceControlMessagesHelper;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo_base.mojom.ReadOnlyBuffer;
import org.chromium.net.interfaces.IpAddress;
import org.chromium.net.interfaces.IpEndPoint;

class UdpSocket_Internal {
    final class org.chromium.network.mojom.UdpSocket_Internal$1 extends Manager {
        org.chromium.network.mojom.UdpSocket_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public UdpSocket[] buildArray(int arg1) {
            return new UdpSocket[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.UdpSocket_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.UdpSocket_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((UdpSocket)arg2));
        }

        public org.chromium.network.mojom.UdpSocket_Internal$Stub buildStub(Core arg2, UdpSocket arg3) {
            return new org.chromium.network.mojom.UdpSocket_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::UDPSocket";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.UdpSocket_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.UdpSocket$Proxy {
        org.chromium.network.mojom.UdpSocket_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void bind(IpEndPoint arg7, UdpSocketOptions arg8, BindResponse arg9) {
            UdpSocketBindParams v0 = new UdpSocketBindParams();
            v0.localAddr = arg7;
            v0.socketOptions = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new UdpSocketBindResponseParamsForwardToCallback(arg9));
        }

        public void close() {
            this.getProxyHandler().getMessageReceiver().accept(new UdpSocketCloseParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(9)));
        }

        public void connect(IpEndPoint arg6, UdpSocketOptions arg7, ConnectResponse arg8) {
            UdpSocketConnectParams v0 = new UdpSocketConnectParams();
            v0.remoteAddr = arg6;
            v0.socketOptions = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new UdpSocketConnectResponseParamsForwardToCallback(arg8));
        }

        public void joinGroup(IpAddress arg8, JoinGroupResponse arg9) {
            UdpSocketJoinGroupParams v0 = new UdpSocketJoinGroupParams();
            v0.groupAddress = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new UdpSocketJoinGroupResponseParamsForwardToCallback(arg9));
        }

        public void leaveGroup(IpAddress arg8, LeaveGroupResponse arg9) {
            UdpSocketLeaveGroupParams v0 = new UdpSocketLeaveGroupParams();
            v0.groupAddress = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new UdpSocketLeaveGroupResponseParamsForwardToCallback(arg9));
        }

        public void receiveMore(int arg5) {
            UdpSocketReceiveMoreParams v0 = new UdpSocketReceiveMoreParams();
            v0.numAdditionalDatagrams = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void receiveMoreWithBufferSize(int arg4, int arg5) {
            UdpSocketReceiveMoreWithBufferSizeParams v0 = new UdpSocketReceiveMoreWithBufferSizeParams();
            v0.numAdditionalDatagrams = arg4;
            v0.bufferSize = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void send(ReadOnlyBuffer arg7, MutableNetworkTrafficAnnotationTag arg8, SendResponse arg9) {
            UdpSocketSendParams v0 = new UdpSocketSendParams();
            v0.data = arg7;
            v0.trafficAnnotation = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8, 1, 0)), new UdpSocketSendResponseParamsForwardToCallback(arg9));
        }

        public void sendTo(IpEndPoint arg6, ReadOnlyBuffer arg7, MutableNetworkTrafficAnnotationTag arg8, SendToResponse arg9) {
            UdpSocketSendToParams v0 = new UdpSocketSendToParams();
            v0.destAddr = arg6;
            v0.data = arg7;
            v0.trafficAnnotation = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7, 1, 0)), new UdpSocketSendToResponseParamsForwardToCallback(arg9));
        }

        public void setBroadcast(boolean arg8, SetBroadcastResponse arg9) {
            UdpSocketSetBroadcastParams v0 = new UdpSocketSetBroadcastParams();
            v0.broadcast = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new UdpSocketSetBroadcastResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.network.mojom.UdpSocket_Internal$Stub extends Stub {
        org.chromium.network.mojom.UdpSocket_Internal$Stub(Core arg1, UdpSocket arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5_1 = arg5.asServiceMessage();
                MessageHeader v1 = v5_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_32;
                }

                if(v1_1 == 9) {
                    goto label_27;
                }

                switch(v1_1) {
                    case 5: {
                        goto label_21;
                    }
                    case 6: {
                        goto label_14;
                    }
                }

                return 0;
            label_21:
                this.getImpl().receiveMore(UdpSocketReceiveMoreParams.deserialize(v5_1.getPayload()).numAdditionalDatagrams);
                return 1;
            label_14:
                UdpSocketReceiveMoreWithBufferSizeParams v5_2 = UdpSocketReceiveMoreWithBufferSizeParams.deserialize(v5_1.getPayload());
                this.getImpl().receiveMoreWithBufferSize(v5_2.numAdditionalDatagrams, v5_2.bufferSize);
                return 1;
            label_27:
                UdpSocketCloseParams.deserialize(v5_1.getPayload());
                this.getImpl().close();
                return 1;
            label_32:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(UdpSocket_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg11, MessageReceiver arg12) {
            try {
                ServiceMessage v11_1 = arg11.asServiceMessage();
                MessageHeader v1 = v11_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_85;
                    }
                    case 0: {
                        goto label_74;
                    }
                    case 1: {
                        goto label_63;
                    }
                    case 2: {
                        goto label_53;
                    }
                    case 3: {
                        goto label_43;
                    }
                    case 4: {
                        goto label_33;
                    }
                    case 7: {
                        goto label_21;
                    }
                    case 8: {
                        goto label_10;
                    }
                }

                return 0;
            label_33:
                this.getImpl().leaveGroup(UdpSocketLeaveGroupParams.deserialize(v11_1.getPayload()).groupAddress, new UdpSocketLeaveGroupResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_85:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), UdpSocket_Internal.MANAGER, v11_1, arg12);
            label_53:
                this.getImpl().setBroadcast(UdpSocketSetBroadcastParams.deserialize(v11_1.getPayload()).broadcast, new UdpSocketSetBroadcastResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_21:
                UdpSocketSendToParams v11_2 = UdpSocketSendToParams.deserialize(v11_1.getPayload());
                this.getImpl().sendTo(v11_2.destAddr, v11_2.data, v11_2.trafficAnnotation, new UdpSocketSendToResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_74:
                UdpSocketBindParams v11_3 = UdpSocketBindParams.deserialize(v11_1.getPayload());
                this.getImpl().bind(v11_3.localAddr, v11_3.socketOptions, new UdpSocketBindResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_10:
                UdpSocketSendParams v11_4 = UdpSocketSendParams.deserialize(v11_1.getPayload());
                this.getImpl().send(v11_4.data, v11_4.trafficAnnotation, new UdpSocketSendResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_43:
                this.getImpl().joinGroup(UdpSocketJoinGroupParams.deserialize(v11_1.getPayload()).groupAddress, new UdpSocketJoinGroupResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_63:
                UdpSocketConnectParams v11_5 = UdpSocketConnectParams.deserialize(v11_1.getPayload());
                this.getImpl().connect(v11_5.remoteAddr, v11_5.socketOptions, new UdpSocketConnectResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    final class UdpSocketBindParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public IpEndPoint localAddr;
        public UdpSocketOptions socketOptions;

        static {
            UdpSocketBindParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UdpSocketBindParams.DEFAULT_STRUCT_INFO = UdpSocketBindParams.VERSION_ARRAY[0];
        }

        public UdpSocketBindParams() {
            this(0);
        }

        private UdpSocketBindParams(int arg2) {
            super(24, arg2);
        }

        public static UdpSocketBindParams decode(Decoder arg3) {
            UdpSocketBindParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketBindParams(arg3.readAndValidateDataHeader(UdpSocketBindParams.VERSION_ARRAY).elementsOrVersion);
                v1.localAddr = IpEndPoint.decode(arg3.readPointer(8, false));
                v1.socketOptions = UdpSocketOptions.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketBindParams deserialize(ByteBuffer arg2) {
            return UdpSocketBindParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketBindParams deserialize(Message arg1) {
            return UdpSocketBindParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UdpSocketBindParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.localAddr, 8, false);
            arg4.encode(this.socketOptions, 16, true);
        }
    }

    final class UdpSocketBindResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public IpEndPoint localAddrOut;
        public int result;

        static {
            UdpSocketBindResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UdpSocketBindResponseParams.DEFAULT_STRUCT_INFO = UdpSocketBindResponseParams.VERSION_ARRAY[0];
        }

        public UdpSocketBindResponseParams() {
            this(0);
        }

        private UdpSocketBindResponseParams(int arg2) {
            super(24, arg2);
        }

        public static UdpSocketBindResponseParams decode(Decoder arg3) {
            UdpSocketBindResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketBindResponseParams(arg3.readAndValidateDataHeader(UdpSocketBindResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                v1.localAddrOut = IpEndPoint.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketBindResponseParams deserialize(ByteBuffer arg2) {
            return UdpSocketBindResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketBindResponseParams deserialize(Message arg1) {
            return UdpSocketBindResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UdpSocketBindResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.localAddrOut, 16, true);
        }
    }

    class UdpSocketBindResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final BindResponse mCallback;

        UdpSocketBindResponseParamsForwardToCallback(BindResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                UdpSocketBindResponseParams v4_1 = UdpSocketBindResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.result), v4_1.localAddrOut);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class UdpSocketBindResponseParamsProxyToResponder implements BindResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UdpSocketBindResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, IpEndPoint arg7) {
            UdpSocketBindResponseParams v0 = new UdpSocketBindResponseParams();
            v0.result = arg6.intValue();
            v0.localAddrOut = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((IpEndPoint)arg2));
        }
    }

    final class UdpSocketCloseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            UdpSocketCloseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            UdpSocketCloseParams.DEFAULT_STRUCT_INFO = UdpSocketCloseParams.VERSION_ARRAY[0];
        }

        public UdpSocketCloseParams() {
            this(0);
        }

        private UdpSocketCloseParams(int arg2) {
            super(8, arg2);
        }

        public static UdpSocketCloseParams decode(Decoder arg2) {
            UdpSocketCloseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketCloseParams(arg2.readAndValidateDataHeader(UdpSocketCloseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketCloseParams deserialize(ByteBuffer arg2) {
            return UdpSocketCloseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketCloseParams deserialize(Message arg1) {
            return UdpSocketCloseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(UdpSocketCloseParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class UdpSocketConnectParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public IpEndPoint remoteAddr;
        public UdpSocketOptions socketOptions;

        static {
            UdpSocketConnectParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UdpSocketConnectParams.DEFAULT_STRUCT_INFO = UdpSocketConnectParams.VERSION_ARRAY[0];
        }

        public UdpSocketConnectParams() {
            this(0);
        }

        private UdpSocketConnectParams(int arg2) {
            super(24, arg2);
        }

        public static UdpSocketConnectParams decode(Decoder arg3) {
            UdpSocketConnectParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketConnectParams(arg3.readAndValidateDataHeader(UdpSocketConnectParams.VERSION_ARRAY).elementsOrVersion);
                v1.remoteAddr = IpEndPoint.decode(arg3.readPointer(8, false));
                v1.socketOptions = UdpSocketOptions.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketConnectParams deserialize(ByteBuffer arg2) {
            return UdpSocketConnectParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketConnectParams deserialize(Message arg1) {
            return UdpSocketConnectParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UdpSocketConnectParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.remoteAddr, 8, false);
            arg4.encode(this.socketOptions, 16, true);
        }
    }

    final class UdpSocketConnectResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public IpEndPoint localAddrOut;
        public int result;

        static {
            UdpSocketConnectResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UdpSocketConnectResponseParams.DEFAULT_STRUCT_INFO = UdpSocketConnectResponseParams.VERSION_ARRAY[0];
        }

        public UdpSocketConnectResponseParams() {
            this(0);
        }

        private UdpSocketConnectResponseParams(int arg2) {
            super(24, arg2);
        }

        public static UdpSocketConnectResponseParams decode(Decoder arg3) {
            UdpSocketConnectResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketConnectResponseParams(arg3.readAndValidateDataHeader(UdpSocketConnectResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                v1.localAddrOut = IpEndPoint.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketConnectResponseParams deserialize(ByteBuffer arg2) {
            return UdpSocketConnectResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketConnectResponseParams deserialize(Message arg1) {
            return UdpSocketConnectResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UdpSocketConnectResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.localAddrOut, 16, true);
        }
    }

    class UdpSocketConnectResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ConnectResponse mCallback;

        UdpSocketConnectResponseParamsForwardToCallback(ConnectResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                UdpSocketConnectResponseParams v5_1 = UdpSocketConnectResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.result), v5_1.localAddrOut);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class UdpSocketConnectResponseParamsProxyToResponder implements ConnectResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UdpSocketConnectResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, IpEndPoint arg7) {
            UdpSocketConnectResponseParams v0 = new UdpSocketConnectResponseParams();
            v0.result = arg6.intValue();
            v0.localAddrOut = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((IpEndPoint)arg2));
        }
    }

    final class UdpSocketJoinGroupParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public IpAddress groupAddress;

        static {
            UdpSocketJoinGroupParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketJoinGroupParams.DEFAULT_STRUCT_INFO = UdpSocketJoinGroupParams.VERSION_ARRAY[0];
        }

        public UdpSocketJoinGroupParams() {
            this(0);
        }

        private UdpSocketJoinGroupParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketJoinGroupParams decode(Decoder arg3) {
            UdpSocketJoinGroupParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketJoinGroupParams(arg3.readAndValidateDataHeader(UdpSocketJoinGroupParams.VERSION_ARRAY).elementsOrVersion);
                v1.groupAddress = IpAddress.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketJoinGroupParams deserialize(ByteBuffer arg2) {
            return UdpSocketJoinGroupParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketJoinGroupParams deserialize(Message arg1) {
            return UdpSocketJoinGroupParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(UdpSocketJoinGroupParams.DEFAULT_STRUCT_INFO).encode(this.groupAddress, 8, false);
        }
    }

    final class UdpSocketJoinGroupResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            UdpSocketJoinGroupResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketJoinGroupResponseParams.DEFAULT_STRUCT_INFO = UdpSocketJoinGroupResponseParams.VERSION_ARRAY[0];
        }

        public UdpSocketJoinGroupResponseParams() {
            this(0);
        }

        private UdpSocketJoinGroupResponseParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketJoinGroupResponseParams decode(Decoder arg2) {
            UdpSocketJoinGroupResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketJoinGroupResponseParams(arg2.readAndValidateDataHeader(UdpSocketJoinGroupResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketJoinGroupResponseParams deserialize(ByteBuffer arg2) {
            return UdpSocketJoinGroupResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketJoinGroupResponseParams deserialize(Message arg1) {
            return UdpSocketJoinGroupResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(UdpSocketJoinGroupResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    class UdpSocketJoinGroupResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final JoinGroupResponse mCallback;

        UdpSocketJoinGroupResponseParamsForwardToCallback(JoinGroupResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(UdpSocketJoinGroupResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class UdpSocketJoinGroupResponseParamsProxyToResponder implements JoinGroupResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UdpSocketJoinGroupResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            UdpSocketJoinGroupResponseParams v0 = new UdpSocketJoinGroupResponseParams();
            v0.result = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class UdpSocketLeaveGroupParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public IpAddress groupAddress;

        static {
            UdpSocketLeaveGroupParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketLeaveGroupParams.DEFAULT_STRUCT_INFO = UdpSocketLeaveGroupParams.VERSION_ARRAY[0];
        }

        public UdpSocketLeaveGroupParams() {
            this(0);
        }

        private UdpSocketLeaveGroupParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketLeaveGroupParams decode(Decoder arg3) {
            UdpSocketLeaveGroupParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketLeaveGroupParams(arg3.readAndValidateDataHeader(UdpSocketLeaveGroupParams.VERSION_ARRAY).elementsOrVersion);
                v1.groupAddress = IpAddress.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketLeaveGroupParams deserialize(ByteBuffer arg2) {
            return UdpSocketLeaveGroupParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketLeaveGroupParams deserialize(Message arg1) {
            return UdpSocketLeaveGroupParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(UdpSocketLeaveGroupParams.DEFAULT_STRUCT_INFO).encode(this.groupAddress, 8, false);
        }
    }

    final class UdpSocketLeaveGroupResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            UdpSocketLeaveGroupResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketLeaveGroupResponseParams.DEFAULT_STRUCT_INFO = UdpSocketLeaveGroupResponseParams.VERSION_ARRAY[0];
        }

        public UdpSocketLeaveGroupResponseParams() {
            this(0);
        }

        private UdpSocketLeaveGroupResponseParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketLeaveGroupResponseParams decode(Decoder arg2) {
            UdpSocketLeaveGroupResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketLeaveGroupResponseParams(arg2.readAndValidateDataHeader(UdpSocketLeaveGroupResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketLeaveGroupResponseParams deserialize(ByteBuffer arg2) {
            return UdpSocketLeaveGroupResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketLeaveGroupResponseParams deserialize(Message arg1) {
            return UdpSocketLeaveGroupResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(UdpSocketLeaveGroupResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    class UdpSocketLeaveGroupResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final LeaveGroupResponse mCallback;

        UdpSocketLeaveGroupResponseParamsForwardToCallback(LeaveGroupResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(UdpSocketLeaveGroupResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class UdpSocketLeaveGroupResponseParamsProxyToResponder implements LeaveGroupResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UdpSocketLeaveGroupResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            UdpSocketLeaveGroupResponseParams v0 = new UdpSocketLeaveGroupResponseParams();
            v0.result = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class UdpSocketReceiveMoreParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int numAdditionalDatagrams;

        static {
            UdpSocketReceiveMoreParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketReceiveMoreParams.DEFAULT_STRUCT_INFO = UdpSocketReceiveMoreParams.VERSION_ARRAY[0];
        }

        public UdpSocketReceiveMoreParams() {
            this(0);
        }

        private UdpSocketReceiveMoreParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketReceiveMoreParams decode(Decoder arg2) {
            UdpSocketReceiveMoreParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketReceiveMoreParams(arg2.readAndValidateDataHeader(UdpSocketReceiveMoreParams.VERSION_ARRAY).elementsOrVersion);
                v1.numAdditionalDatagrams = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketReceiveMoreParams deserialize(ByteBuffer arg2) {
            return UdpSocketReceiveMoreParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketReceiveMoreParams deserialize(Message arg1) {
            return UdpSocketReceiveMoreParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(UdpSocketReceiveMoreParams.DEFAULT_STRUCT_INFO).encode(this.numAdditionalDatagrams, 8);
        }
    }

    final class UdpSocketReceiveMoreWithBufferSizeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int bufferSize;
        public int numAdditionalDatagrams;

        static {
            UdpSocketReceiveMoreWithBufferSizeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketReceiveMoreWithBufferSizeParams.DEFAULT_STRUCT_INFO = UdpSocketReceiveMoreWithBufferSizeParams.VERSION_ARRAY[0];
        }

        public UdpSocketReceiveMoreWithBufferSizeParams() {
            this(0);
        }

        private UdpSocketReceiveMoreWithBufferSizeParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketReceiveMoreWithBufferSizeParams decode(Decoder arg2) {
            UdpSocketReceiveMoreWithBufferSizeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketReceiveMoreWithBufferSizeParams(arg2.readAndValidateDataHeader(UdpSocketReceiveMoreWithBufferSizeParams.VERSION_ARRAY).elementsOrVersion);
                v1.numAdditionalDatagrams = arg2.readInt(8);
                v1.bufferSize = arg2.readInt(12);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketReceiveMoreWithBufferSizeParams deserialize(ByteBuffer arg2) {
            return UdpSocketReceiveMoreWithBufferSizeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketReceiveMoreWithBufferSizeParams deserialize(Message arg1) {
            return UdpSocketReceiveMoreWithBufferSizeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(UdpSocketReceiveMoreWithBufferSizeParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.numAdditionalDatagrams, 8);
            arg3.encode(this.bufferSize, 12);
        }
    }

    final class UdpSocketSendParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public ReadOnlyBuffer data;
        public MutableNetworkTrafficAnnotationTag trafficAnnotation;

        static {
            UdpSocketSendParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UdpSocketSendParams.DEFAULT_STRUCT_INFO = UdpSocketSendParams.VERSION_ARRAY[0];
        }

        public UdpSocketSendParams() {
            this(0);
        }

        private UdpSocketSendParams(int arg2) {
            super(24, arg2);
        }

        public static UdpSocketSendParams decode(Decoder arg3) {
            UdpSocketSendParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketSendParams(arg3.readAndValidateDataHeader(UdpSocketSendParams.VERSION_ARRAY).elementsOrVersion);
                v1.data = ReadOnlyBuffer.decode(arg3.readPointer(8, false));
                v1.trafficAnnotation = MutableNetworkTrafficAnnotationTag.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketSendParams deserialize(ByteBuffer arg2) {
            return UdpSocketSendParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketSendParams deserialize(Message arg1) {
            return UdpSocketSendParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UdpSocketSendParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.data, 8, false);
            arg4.encode(this.trafficAnnotation, 16, false);
        }
    }

    final class UdpSocketSendResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            UdpSocketSendResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketSendResponseParams.DEFAULT_STRUCT_INFO = UdpSocketSendResponseParams.VERSION_ARRAY[0];
        }

        public UdpSocketSendResponseParams() {
            this(0);
        }

        private UdpSocketSendResponseParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketSendResponseParams decode(Decoder arg2) {
            UdpSocketSendResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketSendResponseParams(arg2.readAndValidateDataHeader(UdpSocketSendResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketSendResponseParams deserialize(ByteBuffer arg2) {
            return UdpSocketSendResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketSendResponseParams deserialize(Message arg1) {
            return UdpSocketSendResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(UdpSocketSendResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    class UdpSocketSendResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SendResponse mCallback;

        UdpSocketSendResponseParamsForwardToCallback(SendResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(8, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(UdpSocketSendResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class UdpSocketSendResponseParamsProxyToResponder implements SendResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UdpSocketSendResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            UdpSocketSendResponseParams v0 = new UdpSocketSendResponseParams();
            v0.result = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(8, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class UdpSocketSendToParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public ReadOnlyBuffer data;
        public IpEndPoint destAddr;
        public MutableNetworkTrafficAnnotationTag trafficAnnotation;

        static {
            UdpSocketSendToParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            UdpSocketSendToParams.DEFAULT_STRUCT_INFO = UdpSocketSendToParams.VERSION_ARRAY[0];
        }

        public UdpSocketSendToParams() {
            this(0);
        }

        private UdpSocketSendToParams(int arg2) {
            super(0x20, arg2);
        }

        public static UdpSocketSendToParams decode(Decoder arg3) {
            UdpSocketSendToParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketSendToParams(arg3.readAndValidateDataHeader(UdpSocketSendToParams.VERSION_ARRAY).elementsOrVersion);
                v1.destAddr = IpEndPoint.decode(arg3.readPointer(8, false));
                v1.data = ReadOnlyBuffer.decode(arg3.readPointer(16, false));
                v1.trafficAnnotation = MutableNetworkTrafficAnnotationTag.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketSendToParams deserialize(ByteBuffer arg2) {
            return UdpSocketSendToParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketSendToParams deserialize(Message arg1) {
            return UdpSocketSendToParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UdpSocketSendToParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.destAddr, 8, false);
            arg4.encode(this.data, 16, false);
            arg4.encode(this.trafficAnnotation, 24, false);
        }
    }

    final class UdpSocketSendToResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            UdpSocketSendToResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketSendToResponseParams.DEFAULT_STRUCT_INFO = UdpSocketSendToResponseParams.VERSION_ARRAY[0];
        }

        public UdpSocketSendToResponseParams() {
            this(0);
        }

        private UdpSocketSendToResponseParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketSendToResponseParams decode(Decoder arg2) {
            UdpSocketSendToResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketSendToResponseParams(arg2.readAndValidateDataHeader(UdpSocketSendToResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketSendToResponseParams deserialize(ByteBuffer arg2) {
            return UdpSocketSendToResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketSendToResponseParams deserialize(Message arg1) {
            return UdpSocketSendToResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(UdpSocketSendToResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    class UdpSocketSendToResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SendToResponse mCallback;

        UdpSocketSendToResponseParamsForwardToCallback(SendToResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(7, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(UdpSocketSendToResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class UdpSocketSendToResponseParamsProxyToResponder implements SendToResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UdpSocketSendToResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            UdpSocketSendToResponseParams v0 = new UdpSocketSendToResponseParams();
            v0.result = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(7, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class UdpSocketSetBroadcastParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean broadcast;

        static {
            UdpSocketSetBroadcastParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketSetBroadcastParams.DEFAULT_STRUCT_INFO = UdpSocketSetBroadcastParams.VERSION_ARRAY[0];
        }

        public UdpSocketSetBroadcastParams() {
            this(0);
        }

        private UdpSocketSetBroadcastParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketSetBroadcastParams decode(Decoder arg3) {
            UdpSocketSetBroadcastParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketSetBroadcastParams(arg3.readAndValidateDataHeader(UdpSocketSetBroadcastParams.VERSION_ARRAY).elementsOrVersion);
                v1.broadcast = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketSetBroadcastParams deserialize(ByteBuffer arg2) {
            return UdpSocketSetBroadcastParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketSetBroadcastParams deserialize(Message arg1) {
            return UdpSocketSetBroadcastParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(UdpSocketSetBroadcastParams.DEFAULT_STRUCT_INFO).encode(this.broadcast, 8, 0);
        }
    }

    final class UdpSocketSetBroadcastResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            UdpSocketSetBroadcastResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UdpSocketSetBroadcastResponseParams.DEFAULT_STRUCT_INFO = UdpSocketSetBroadcastResponseParams.VERSION_ARRAY[0];
        }

        public UdpSocketSetBroadcastResponseParams() {
            this(0);
        }

        private UdpSocketSetBroadcastResponseParams(int arg2) {
            super(16, arg2);
        }

        public static UdpSocketSetBroadcastResponseParams decode(Decoder arg2) {
            UdpSocketSetBroadcastResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UdpSocketSetBroadcastResponseParams(arg2.readAndValidateDataHeader(UdpSocketSetBroadcastResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketSetBroadcastResponseParams deserialize(ByteBuffer arg2) {
            return UdpSocketSetBroadcastResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketSetBroadcastResponseParams deserialize(Message arg1) {
            return UdpSocketSetBroadcastResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(UdpSocketSetBroadcastResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    class UdpSocketSetBroadcastResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetBroadcastResponse mCallback;

        UdpSocketSetBroadcastResponseParamsForwardToCallback(SetBroadcastResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(UdpSocketSetBroadcastResponseParams.deserialize(v4.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class UdpSocketSetBroadcastResponseParamsProxyToResponder implements SetBroadcastResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UdpSocketSetBroadcastResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6) {
            UdpSocketSetBroadcastResponseParams v0 = new UdpSocketSetBroadcastResponseParams();
            v0.result = arg6.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    private static final int BIND_ORDINAL = 0;
    private static final int CLOSE_ORDINAL = 9;
    private static final int CONNECT_ORDINAL = 1;
    private static final int JOIN_GROUP_ORDINAL = 3;
    private static final int LEAVE_GROUP_ORDINAL = 4;
    public static final Manager MANAGER = null;
    private static final int RECEIVE_MORE_ORDINAL = 5;
    private static final int RECEIVE_MORE_WITH_BUFFER_SIZE_ORDINAL = 6;
    private static final int SEND_ORDINAL = 8;
    private static final int SEND_TO_ORDINAL = 7;
    private static final int SET_BROADCAST_ORDINAL = 2;

    static {
        UdpSocket_Internal.MANAGER = new org.chromium.network.mojom.UdpSocket_Internal$1();
    }

    UdpSocket_Internal() {
        super();
    }
}

