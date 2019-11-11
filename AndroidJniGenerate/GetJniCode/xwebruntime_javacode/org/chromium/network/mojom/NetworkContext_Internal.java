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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo_base.mojom.Time;
import org.chromium.net.interfaces.AddressList;
import org.chromium.net.interfaces.IpEndPoint;
import org.chromium.url.mojom.Origin;

class NetworkContext_Internal {
    final class org.chromium.network.mojom.NetworkContext_Internal$1 extends Manager {
        org.chromium.network.mojom.NetworkContext_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public NetworkContext[] buildArray(int arg1) {
            return new NetworkContext[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.NetworkContext_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.NetworkContext_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((NetworkContext)arg2));
        }

        public org.chromium.network.mojom.NetworkContext_Internal$Stub buildStub(Core arg2, NetworkContext arg3) {
            return new org.chromium.network.mojom.NetworkContext_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::NetworkContext";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NetworkContextAddHstsForTestingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public Time expiry;
        public String host;
        public boolean includeSubdomains;

        static {
            NetworkContextAddHstsForTestingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            NetworkContextAddHstsForTestingParams.DEFAULT_STRUCT_INFO = NetworkContextAddHstsForTestingParams.VERSION_ARRAY[0];
        }

        public NetworkContextAddHstsForTestingParams() {
            this(0);
        }

        private NetworkContextAddHstsForTestingParams(int arg2) {
            super(0x20, arg2);
        }

        public static NetworkContextAddHstsForTestingParams decode(Decoder arg3) {
            NetworkContextAddHstsForTestingParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextAddHstsForTestingParams(arg3.readAndValidateDataHeader(NetworkContextAddHstsForTestingParams.VERSION_ARRAY).elementsOrVersion);
                v1.host = arg3.readString(8, false);
                v1.expiry = Time.decode(arg3.readPointer(16, false));
                v1.includeSubdomains = arg3.readBoolean(24, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextAddHstsForTestingParams deserialize(ByteBuffer arg2) {
            return NetworkContextAddHstsForTestingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextAddHstsForTestingParams deserialize(Message arg1) {
            return NetworkContextAddHstsForTestingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextAddHstsForTestingParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.host, 8, false);
            arg4.encode(this.expiry, 16, false);
            arg4.encode(this.includeSubdomains, 24, 0);
        }
    }

    final class NetworkContextAddHstsForTestingResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkContextAddHstsForTestingResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkContextAddHstsForTestingResponseParams.DEFAULT_STRUCT_INFO = NetworkContextAddHstsForTestingResponseParams.VERSION_ARRAY[0];
        }

        public NetworkContextAddHstsForTestingResponseParams() {
            this(0);
        }

        private NetworkContextAddHstsForTestingResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkContextAddHstsForTestingResponseParams decode(Decoder arg2) {
            NetworkContextAddHstsForTestingResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkContextAddHstsForTestingResponseParams(arg2.readAndValidateDataHeader(NetworkContextAddHstsForTestingResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextAddHstsForTestingResponseParams deserialize(ByteBuffer arg2) {
            return NetworkContextAddHstsForTestingResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextAddHstsForTestingResponseParams deserialize(Message arg1) {
            return NetworkContextAddHstsForTestingResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkContextAddHstsForTestingResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkContextAddHstsForTestingResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final AddHstsForTestingResponse mCallback;

        NetworkContextAddHstsForTestingResponseParamsForwardToCallback(AddHstsForTestingResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(12, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkContextAddHstsForTestingResponseParamsProxyToResponder implements AddHstsForTestingResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkContextAddHstsForTestingResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkContextAddHstsForTestingResponseParams().serializeWithHeader(this.mCore, new MessageHeader(12, 2, this.mRequestId)));
        }
    }

    final class NetworkContextClearHttpCacheParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public Time endTime;
        public ClearCacheUrlFilter filter;
        public Time startTime;

        static {
            NetworkContextClearHttpCacheParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            NetworkContextClearHttpCacheParams.DEFAULT_STRUCT_INFO = NetworkContextClearHttpCacheParams.VERSION_ARRAY[0];
        }

        public NetworkContextClearHttpCacheParams() {
            this(0);
        }

        private NetworkContextClearHttpCacheParams(int arg2) {
            super(0x20, arg2);
        }

        public static NetworkContextClearHttpCacheParams decode(Decoder arg3) {
            NetworkContextClearHttpCacheParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextClearHttpCacheParams(arg3.readAndValidateDataHeader(NetworkContextClearHttpCacheParams.VERSION_ARRAY).elementsOrVersion);
                v1.startTime = Time.decode(arg3.readPointer(8, false));
                v1.endTime = Time.decode(arg3.readPointer(16, false));
                v1.filter = ClearCacheUrlFilter.decode(arg3.readPointer(24, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextClearHttpCacheParams deserialize(ByteBuffer arg2) {
            return NetworkContextClearHttpCacheParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextClearHttpCacheParams deserialize(Message arg1) {
            return NetworkContextClearHttpCacheParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextClearHttpCacheParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.startTime, 8, false);
            arg4.encode(this.endTime, 16, false);
            arg4.encode(this.filter, 24, true);
        }
    }

    final class NetworkContextClearHttpCacheResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkContextClearHttpCacheResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkContextClearHttpCacheResponseParams.DEFAULT_STRUCT_INFO = NetworkContextClearHttpCacheResponseParams.VERSION_ARRAY[0];
        }

        public NetworkContextClearHttpCacheResponseParams() {
            this(0);
        }

        private NetworkContextClearHttpCacheResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkContextClearHttpCacheResponseParams decode(Decoder arg2) {
            NetworkContextClearHttpCacheResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkContextClearHttpCacheResponseParams(arg2.readAndValidateDataHeader(NetworkContextClearHttpCacheResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextClearHttpCacheResponseParams deserialize(ByteBuffer arg2) {
            return NetworkContextClearHttpCacheResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextClearHttpCacheResponseParams deserialize(Message arg1) {
            return NetworkContextClearHttpCacheResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkContextClearHttpCacheResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkContextClearHttpCacheResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ClearHttpCacheResponse mCallback;

        NetworkContextClearHttpCacheResponseParamsForwardToCallback(ClearHttpCacheResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkContextClearHttpCacheResponseParamsProxyToResponder implements ClearHttpCacheResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkContextClearHttpCacheResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkContextClearHttpCacheResponseParams().serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }
    }

    final class NetworkContextClearNetworkingHistorySinceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Time startTime;

        static {
            NetworkContextClearNetworkingHistorySinceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkContextClearNetworkingHistorySinceParams.DEFAULT_STRUCT_INFO = NetworkContextClearNetworkingHistorySinceParams.VERSION_ARRAY[0];
        }

        public NetworkContextClearNetworkingHistorySinceParams() {
            this(0);
        }

        private NetworkContextClearNetworkingHistorySinceParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkContextClearNetworkingHistorySinceParams decode(Decoder arg3) {
            NetworkContextClearNetworkingHistorySinceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextClearNetworkingHistorySinceParams(arg3.readAndValidateDataHeader(NetworkContextClearNetworkingHistorySinceParams.VERSION_ARRAY).elementsOrVersion);
                v1.startTime = Time.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextClearNetworkingHistorySinceParams deserialize(ByteBuffer arg2) {
            return NetworkContextClearNetworkingHistorySinceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextClearNetworkingHistorySinceParams deserialize(Message arg1) {
            return NetworkContextClearNetworkingHistorySinceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NetworkContextClearNetworkingHistorySinceParams.DEFAULT_STRUCT_INFO).encode(this.startTime, 8, false);
        }
    }

    final class NetworkContextClearNetworkingHistorySinceResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkContextClearNetworkingHistorySinceResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkContextClearNetworkingHistorySinceResponseParams.DEFAULT_STRUCT_INFO = NetworkContextClearNetworkingHistorySinceResponseParams.VERSION_ARRAY[0];
        }

        public NetworkContextClearNetworkingHistorySinceResponseParams() {
            this(0);
        }

        private NetworkContextClearNetworkingHistorySinceResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkContextClearNetworkingHistorySinceResponseParams decode(Decoder arg2) {
            NetworkContextClearNetworkingHistorySinceResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkContextClearNetworkingHistorySinceResponseParams(arg2.readAndValidateDataHeader(NetworkContextClearNetworkingHistorySinceResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextClearNetworkingHistorySinceResponseParams deserialize(ByteBuffer arg2) {
            return NetworkContextClearNetworkingHistorySinceResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextClearNetworkingHistorySinceResponseParams deserialize(Message arg1) {
            return NetworkContextClearNetworkingHistorySinceResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkContextClearNetworkingHistorySinceResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkContextClearNetworkingHistorySinceResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ClearNetworkingHistorySinceResponse mCallback;

        NetworkContextClearNetworkingHistorySinceResponseParamsForwardToCallback(ClearNetworkingHistorySinceResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkContextClearNetworkingHistorySinceResponseParamsProxyToResponder implements ClearNetworkingHistorySinceResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkContextClearNetworkingHistorySinceResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkContextClearNetworkingHistorySinceResponseParams().serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    final class NetworkContextCreateTcpConnectedSocketParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public IpEndPoint localAddr;
        public TcpConnectedSocketObserver observer;
        public AddressList remoteAddrList;
        public InterfaceRequest socket;
        public MutableNetworkTrafficAnnotationTag trafficAnnotation;

        static {
            NetworkContextCreateTcpConnectedSocketParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            NetworkContextCreateTcpConnectedSocketParams.DEFAULT_STRUCT_INFO = NetworkContextCreateTcpConnectedSocketParams.VERSION_ARRAY[0];
        }

        public NetworkContextCreateTcpConnectedSocketParams() {
            this(0);
        }

        private NetworkContextCreateTcpConnectedSocketParams(int arg2) {
            super(0x30, arg2);
        }

        public static NetworkContextCreateTcpConnectedSocketParams decode(Decoder arg4) {
            NetworkContextCreateTcpConnectedSocketParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new NetworkContextCreateTcpConnectedSocketParams(arg4.readAndValidateDataHeader(NetworkContextCreateTcpConnectedSocketParams.VERSION_ARRAY).elementsOrVersion);
                v1.localAddr = IpEndPoint.decode(arg4.readPointer(8, true));
                v1.remoteAddrList = AddressList.decode(arg4.readPointer(16, false));
                v1.trafficAnnotation = MutableNetworkTrafficAnnotationTag.decode(arg4.readPointer(24, false));
                v1.socket = arg4.readInterfaceRequest(0x20, false);
                v1.observer = arg4.readServiceInterface(36, true, TcpConnectedSocketObserver.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextCreateTcpConnectedSocketParams deserialize(ByteBuffer arg2) {
            return NetworkContextCreateTcpConnectedSocketParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextCreateTcpConnectedSocketParams deserialize(Message arg1) {
            return NetworkContextCreateTcpConnectedSocketParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(NetworkContextCreateTcpConnectedSocketParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.localAddr, 8, true);
            arg5.encode(this.remoteAddrList, 16, false);
            arg5.encode(this.trafficAnnotation, 24, false);
            arg5.encode(this.socket, 0x20, false);
            arg5.encode(this.observer, 36, true, TcpConnectedSocketObserver.MANAGER);
        }
    }

    final class NetworkContextCreateTcpConnectedSocketResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public ConsumerHandle receiveStream;
        public int result;
        public ProducerHandle sendStream;

        static {
            NetworkContextCreateTcpConnectedSocketResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NetworkContextCreateTcpConnectedSocketResponseParams.DEFAULT_STRUCT_INFO = NetworkContextCreateTcpConnectedSocketResponseParams.VERSION_ARRAY[0];
        }

        public NetworkContextCreateTcpConnectedSocketResponseParams() {
            this(0);
        }

        private NetworkContextCreateTcpConnectedSocketResponseParams(int arg2) {
            super(24, arg2);
            this.receiveStream = InvalidHandle.INSTANCE;
            this.sendStream = InvalidHandle.INSTANCE;
        }

        public static NetworkContextCreateTcpConnectedSocketResponseParams decode(Decoder arg3) {
            NetworkContextCreateTcpConnectedSocketResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextCreateTcpConnectedSocketResponseParams(arg3.readAndValidateDataHeader(NetworkContextCreateTcpConnectedSocketResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                v1.receiveStream = arg3.readConsumerHandle(12, true);
                v1.sendStream = arg3.readProducerHandle(16, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextCreateTcpConnectedSocketResponseParams deserialize(ByteBuffer arg2) {
            return NetworkContextCreateTcpConnectedSocketResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextCreateTcpConnectedSocketResponseParams deserialize(Message arg1) {
            return NetworkContextCreateTcpConnectedSocketResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextCreateTcpConnectedSocketResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.receiveStream, 12, true);
            arg4.encode(this.sendStream, 16, true);
        }
    }

    class NetworkContextCreateTcpConnectedSocketResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CreateTcpConnectedSocketResponse mCallback;

        NetworkContextCreateTcpConnectedSocketResponseParamsForwardToCallback(CreateTcpConnectedSocketResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(10, 2)) {
                    return 0;
                }

                NetworkContextCreateTcpConnectedSocketResponseParams v5_1 = NetworkContextCreateTcpConnectedSocketResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.result), v5_1.receiveStream, v5_1.sendStream);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkContextCreateTcpConnectedSocketResponseParamsProxyToResponder implements CreateTcpConnectedSocketResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkContextCreateTcpConnectedSocketResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, ConsumerHandle arg6, ProducerHandle arg7) {
            NetworkContextCreateTcpConnectedSocketResponseParams v0 = new NetworkContextCreateTcpConnectedSocketResponseParams();
            v0.result = arg5.intValue();
            v0.receiveStream = arg6;
            v0.sendStream = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(10, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((ConsumerHandle)arg2), ((ProducerHandle)arg3));
        }
    }

    final class NetworkContextCreateTcpServerSocketParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int backlog;
        public IpEndPoint localAddr;
        public InterfaceRequest socket;
        public MutableNetworkTrafficAnnotationTag trafficAnnotation;

        static {
            NetworkContextCreateTcpServerSocketParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            NetworkContextCreateTcpServerSocketParams.DEFAULT_STRUCT_INFO = NetworkContextCreateTcpServerSocketParams.VERSION_ARRAY[0];
        }

        public NetworkContextCreateTcpServerSocketParams() {
            this(0);
        }

        private NetworkContextCreateTcpServerSocketParams(int arg2) {
            super(0x20, arg2);
        }

        public static NetworkContextCreateTcpServerSocketParams decode(Decoder arg3) {
            NetworkContextCreateTcpServerSocketParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextCreateTcpServerSocketParams(arg3.readAndValidateDataHeader(NetworkContextCreateTcpServerSocketParams.VERSION_ARRAY).elementsOrVersion);
                v1.localAddr = IpEndPoint.decode(arg3.readPointer(8, false));
                v1.backlog = arg3.readInt(16);
                v1.socket = arg3.readInterfaceRequest(20, false);
                v1.trafficAnnotation = MutableNetworkTrafficAnnotationTag.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextCreateTcpServerSocketParams deserialize(ByteBuffer arg2) {
            return NetworkContextCreateTcpServerSocketParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextCreateTcpServerSocketParams deserialize(Message arg1) {
            return NetworkContextCreateTcpServerSocketParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextCreateTcpServerSocketParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.localAddr, 8, false);
            arg4.encode(this.backlog, 16);
            arg4.encode(this.socket, 20, false);
            arg4.encode(this.trafficAnnotation, 24, false);
        }
    }

    final class NetworkContextCreateTcpServerSocketResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public IpEndPoint localAddrOut;
        public int result;

        static {
            NetworkContextCreateTcpServerSocketResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NetworkContextCreateTcpServerSocketResponseParams.DEFAULT_STRUCT_INFO = NetworkContextCreateTcpServerSocketResponseParams.VERSION_ARRAY[0];
        }

        public NetworkContextCreateTcpServerSocketResponseParams() {
            this(0);
        }

        private NetworkContextCreateTcpServerSocketResponseParams(int arg2) {
            super(24, arg2);
        }

        public static NetworkContextCreateTcpServerSocketResponseParams decode(Decoder arg3) {
            NetworkContextCreateTcpServerSocketResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextCreateTcpServerSocketResponseParams(arg3.readAndValidateDataHeader(NetworkContextCreateTcpServerSocketResponseParams.VERSION_ARRAY).elementsOrVersion);
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

        public static NetworkContextCreateTcpServerSocketResponseParams deserialize(ByteBuffer arg2) {
            return NetworkContextCreateTcpServerSocketResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextCreateTcpServerSocketResponseParams deserialize(Message arg1) {
            return NetworkContextCreateTcpServerSocketResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextCreateTcpServerSocketResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.localAddrOut, 16, true);
        }
    }

    class NetworkContextCreateTcpServerSocketResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CreateTcpServerSocketResponse mCallback;

        NetworkContextCreateTcpServerSocketResponseParamsForwardToCallback(CreateTcpServerSocketResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(9, 2)) {
                    return 0;
                }

                NetworkContextCreateTcpServerSocketResponseParams v5_1 = NetworkContextCreateTcpServerSocketResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.result), v5_1.localAddrOut);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkContextCreateTcpServerSocketResponseParamsProxyToResponder implements CreateTcpServerSocketResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkContextCreateTcpServerSocketResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, IpEndPoint arg7) {
            NetworkContextCreateTcpServerSocketResponseParams v0 = new NetworkContextCreateTcpServerSocketResponseParams();
            v0.result = arg6.intValue();
            v0.localAddrOut = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(9, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((IpEndPoint)arg2));
        }
    }

    final class NetworkContextCreateUdpSocketParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public UdpSocketReceiver receiver;
        public InterfaceRequest request;

        static {
            NetworkContextCreateUdpSocketParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NetworkContextCreateUdpSocketParams.DEFAULT_STRUCT_INFO = NetworkContextCreateUdpSocketParams.VERSION_ARRAY[0];
        }

        public NetworkContextCreateUdpSocketParams() {
            this(0);
        }

        private NetworkContextCreateUdpSocketParams(int arg2) {
            super(24, arg2);
        }

        public static NetworkContextCreateUdpSocketParams decode(Decoder arg4) {
            NetworkContextCreateUdpSocketParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new NetworkContextCreateUdpSocketParams(arg4.readAndValidateDataHeader(NetworkContextCreateUdpSocketParams.VERSION_ARRAY).elementsOrVersion);
                v1.request = arg4.readInterfaceRequest(8, false);
                v1.receiver = arg4.readServiceInterface(12, true, UdpSocketReceiver.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextCreateUdpSocketParams deserialize(ByteBuffer arg2) {
            return NetworkContextCreateUdpSocketParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextCreateUdpSocketParams deserialize(Message arg1) {
            return NetworkContextCreateUdpSocketParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(NetworkContextCreateUdpSocketParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.request, 8, false);
            arg5.encode(this.receiver, 12, true, UdpSocketReceiver.MANAGER);
        }
    }

    final class NetworkContextCreateUrlLoaderFactoryParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int processId;
        public InterfaceRequest urlLoaderFactory;

        static {
            NetworkContextCreateUrlLoaderFactoryParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkContextCreateUrlLoaderFactoryParams.DEFAULT_STRUCT_INFO = NetworkContextCreateUrlLoaderFactoryParams.VERSION_ARRAY[0];
        }

        public NetworkContextCreateUrlLoaderFactoryParams() {
            this(0);
        }

        private NetworkContextCreateUrlLoaderFactoryParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkContextCreateUrlLoaderFactoryParams decode(Decoder arg3) {
            NetworkContextCreateUrlLoaderFactoryParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextCreateUrlLoaderFactoryParams(arg3.readAndValidateDataHeader(NetworkContextCreateUrlLoaderFactoryParams.VERSION_ARRAY).elementsOrVersion);
                v1.urlLoaderFactory = arg3.readInterfaceRequest(8, false);
                v1.processId = arg3.readInt(12);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextCreateUrlLoaderFactoryParams deserialize(ByteBuffer arg2) {
            return NetworkContextCreateUrlLoaderFactoryParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextCreateUrlLoaderFactoryParams deserialize(Message arg1) {
            return NetworkContextCreateUrlLoaderFactoryParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextCreateUrlLoaderFactoryParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.urlLoaderFactory, 8, false);
            arg4.encode(this.processId, 12);
        }
    }

    final class NetworkContextCreateWebSocketParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public Origin origin;
        public int processId;
        public int renderFrameId;
        public InterfaceRequest request;

        static {
            NetworkContextCreateWebSocketParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            NetworkContextCreateWebSocketParams.DEFAULT_STRUCT_INFO = NetworkContextCreateWebSocketParams.VERSION_ARRAY[0];
        }

        public NetworkContextCreateWebSocketParams() {
            this(0);
        }

        private NetworkContextCreateWebSocketParams(int arg2) {
            super(0x20, arg2);
        }

        public static NetworkContextCreateWebSocketParams decode(Decoder arg3) {
            NetworkContextCreateWebSocketParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextCreateWebSocketParams(arg3.readAndValidateDataHeader(NetworkContextCreateWebSocketParams.VERSION_ARRAY).elementsOrVersion);
                v1.request = arg3.readInterfaceRequest(8, false);
                v1.processId = arg3.readInt(12);
                v1.renderFrameId = arg3.readInt(16);
                v1.origin = Origin.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextCreateWebSocketParams deserialize(ByteBuffer arg2) {
            return NetworkContextCreateWebSocketParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextCreateWebSocketParams deserialize(Message arg1) {
            return NetworkContextCreateWebSocketParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextCreateWebSocketParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.request, 8, false);
            arg4.encode(this.processId, 12);
            arg4.encode(this.renderFrameId, 16);
            arg4.encode(this.origin, 24, false);
        }
    }

    final class NetworkContextGetCookieManagerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest cookieManager;

        static {
            NetworkContextGetCookieManagerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkContextGetCookieManagerParams.DEFAULT_STRUCT_INFO = NetworkContextGetCookieManagerParams.VERSION_ARRAY[0];
        }

        public NetworkContextGetCookieManagerParams() {
            this(0);
        }

        private NetworkContextGetCookieManagerParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkContextGetCookieManagerParams decode(Decoder arg3) {
            NetworkContextGetCookieManagerParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextGetCookieManagerParams(arg3.readAndValidateDataHeader(NetworkContextGetCookieManagerParams.VERSION_ARRAY).elementsOrVersion);
                v1.cookieManager = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextGetCookieManagerParams deserialize(ByteBuffer arg2) {
            return NetworkContextGetCookieManagerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextGetCookieManagerParams deserialize(Message arg1) {
            return NetworkContextGetCookieManagerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NetworkContextGetCookieManagerParams.DEFAULT_STRUCT_INFO).encode(this.cookieManager, 8, false);
        }
    }

    final class NetworkContextGetRestrictedCookieManagerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int renderFrameId;
        public int renderProcessId;
        public InterfaceRequest restrictedCookieManager;

        static {
            NetworkContextGetRestrictedCookieManagerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NetworkContextGetRestrictedCookieManagerParams.DEFAULT_STRUCT_INFO = NetworkContextGetRestrictedCookieManagerParams.VERSION_ARRAY[0];
        }

        public NetworkContextGetRestrictedCookieManagerParams() {
            this(0);
        }

        private NetworkContextGetRestrictedCookieManagerParams(int arg2) {
            super(24, arg2);
        }

        public static NetworkContextGetRestrictedCookieManagerParams decode(Decoder arg3) {
            NetworkContextGetRestrictedCookieManagerParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextGetRestrictedCookieManagerParams(arg3.readAndValidateDataHeader(NetworkContextGetRestrictedCookieManagerParams.VERSION_ARRAY).elementsOrVersion);
                v1.restrictedCookieManager = arg3.readInterfaceRequest(8, false);
                v1.renderProcessId = arg3.readInt(12);
                v1.renderFrameId = arg3.readInt(16);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextGetRestrictedCookieManagerParams deserialize(ByteBuffer arg2) {
            return NetworkContextGetRestrictedCookieManagerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextGetRestrictedCookieManagerParams deserialize(Message arg1) {
            return NetworkContextGetRestrictedCookieManagerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextGetRestrictedCookieManagerParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.restrictedCookieManager, 8, false);
            arg4.encode(this.renderProcessId, 12);
            arg4.encode(this.renderFrameId, 16);
        }
    }

    final class NetworkContextSetAcceptLanguageParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String newAcceptLanguage;

        static {
            NetworkContextSetAcceptLanguageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkContextSetAcceptLanguageParams.DEFAULT_STRUCT_INFO = NetworkContextSetAcceptLanguageParams.VERSION_ARRAY[0];
        }

        public NetworkContextSetAcceptLanguageParams() {
            this(0);
        }

        private NetworkContextSetAcceptLanguageParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkContextSetAcceptLanguageParams decode(Decoder arg3) {
            NetworkContextSetAcceptLanguageParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextSetAcceptLanguageParams(arg3.readAndValidateDataHeader(NetworkContextSetAcceptLanguageParams.VERSION_ARRAY).elementsOrVersion);
                v1.newAcceptLanguage = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextSetAcceptLanguageParams deserialize(ByteBuffer arg2) {
            return NetworkContextSetAcceptLanguageParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextSetAcceptLanguageParams deserialize(Message arg1) {
            return NetworkContextSetAcceptLanguageParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NetworkContextSetAcceptLanguageParams.DEFAULT_STRUCT_INFO).encode(this.newAcceptLanguage, 8, false);
        }
    }

    final class NetworkContextSetCtPolicyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public String[] excludedHosts;
        public String[] excludedLegacySpkis;
        public String[] excludedSpkis;
        public String[] requiredHosts;

        static {
            NetworkContextSetCtPolicyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            NetworkContextSetCtPolicyParams.DEFAULT_STRUCT_INFO = NetworkContextSetCtPolicyParams.VERSION_ARRAY[0];
        }

        public NetworkContextSetCtPolicyParams() {
            this(0);
        }

        private NetworkContextSetCtPolicyParams(int arg2) {
            super(40, arg2);
        }

        public static NetworkContextSetCtPolicyParams decode(Decoder arg9) {
            NetworkContextSetCtPolicyParams v1;
            if(arg9 == null) {
                return null;
            }

            arg9.increaseStackDepth();
            try {
                v1 = new NetworkContextSetCtPolicyParams(arg9.readAndValidateDataHeader(NetworkContextSetCtPolicyParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                Decoder v3 = arg9.readPointer(v0_1, false);
                int v4 = -1;
                DataHeader v5 = v3.readDataHeaderForPointerArray(v4);
                v1.requiredHosts = new String[v5.elementsOrVersion];
                int v6;
                for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                    v1.requiredHosts[v6] = v3.readString(v6 * 8 + v0_1, false);
                }

                v3 = arg9.readPointer(16, false);
                v5 = v3.readDataHeaderForPointerArray(v4);
                v1.excludedHosts = new String[v5.elementsOrVersion];
                for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                    v1.excludedHosts[v6] = v3.readString(v6 * 8 + v0_1, false);
                }

                v3 = arg9.readPointer(24, false);
                v5 = v3.readDataHeaderForPointerArray(v4);
                v1.excludedSpkis = new String[v5.elementsOrVersion];
                for(v6 = 0; v6 < v5.elementsOrVersion; ++v6) {
                    v1.excludedSpkis[v6] = v3.readString(v6 * 8 + v0_1, false);
                }

                v3 = arg9.readPointer(0x20, false);
                DataHeader v4_1 = v3.readDataHeaderForPointerArray(v4);
                v1.excludedLegacySpkis = new String[v4_1.elementsOrVersion];
                int v5_1;
                for(v5_1 = 0; v5_1 < v4_1.elementsOrVersion; ++v5_1) {
                    v1.excludedLegacySpkis[v5_1] = v3.readString(v5_1 * 8 + v0_1, false);
                }
            }
            catch(Throwable v0) {
                goto label_78;
            }

            arg9.decreaseStackDepth();
            return v1;
        label_78:
            arg9.decreaseStackDepth();
            throw v0;
        }

        public static NetworkContextSetCtPolicyParams deserialize(ByteBuffer arg2) {
            return NetworkContextSetCtPolicyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextSetCtPolicyParams deserialize(Message arg1) {
            return NetworkContextSetCtPolicyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg8) {
            Encoder v0;
            arg8 = arg8.getEncoderAtDataOffset(NetworkContextSetCtPolicyParams.DEFAULT_STRUCT_INFO);
            int v1 = -1;
            int v2 = 8;
            if(this.requiredHosts == null) {
                arg8.encodeNullPointer(v2, false);
            }
            else {
                v0 = arg8.encodePointerArray(this.requiredHosts.length, v2, v1);
                int v4;
                for(v4 = 0; v4 < this.requiredHosts.length; ++v4) {
                    v0.encode(this.requiredHosts[v4], v4 * 8 + v2, false);
                }
            }

            v4 = 16;
            if(this.excludedHosts == null) {
                arg8.encodeNullPointer(v4, false);
            }
            else {
                v0 = arg8.encodePointerArray(this.excludedHosts.length, v4, v1);
                for(v4 = 0; v4 < this.excludedHosts.length; ++v4) {
                    v0.encode(this.excludedHosts[v4], v4 * 8 + v2, false);
                }
            }

            v4 = 24;
            if(this.excludedSpkis == null) {
                arg8.encodeNullPointer(v4, false);
            }
            else {
                v0 = arg8.encodePointerArray(this.excludedSpkis.length, v4, v1);
                for(v4 = 0; v4 < this.excludedSpkis.length; ++v4) {
                    v0.encode(this.excludedSpkis[v4], v4 * 8 + v2, false);
                }
            }

            v4 = 0x20;
            if(this.excludedLegacySpkis == null) {
                arg8.encodeNullPointer(v4, false);
            }
            else {
                arg8 = arg8.encodePointerArray(this.excludedLegacySpkis.length, v4, v1);
                int v0_1;
                for(v0_1 = 0; v0_1 < this.excludedLegacySpkis.length; ++v0_1) {
                    arg8.encode(this.excludedLegacySpkis[v0_1], v0_1 * 8 + v2, false);
                }
            }
        }
    }

    final class NetworkContextSetNetworkConditionsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public NetworkConditions conditions;
        public String profileId;

        static {
            NetworkContextSetNetworkConditionsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NetworkContextSetNetworkConditionsParams.DEFAULT_STRUCT_INFO = NetworkContextSetNetworkConditionsParams.VERSION_ARRAY[0];
        }

        public NetworkContextSetNetworkConditionsParams() {
            this(0);
        }

        private NetworkContextSetNetworkConditionsParams(int arg2) {
            super(24, arg2);
        }

        public static NetworkContextSetNetworkConditionsParams decode(Decoder arg3) {
            NetworkContextSetNetworkConditionsParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkContextSetNetworkConditionsParams(arg3.readAndValidateDataHeader(NetworkContextSetNetworkConditionsParams.VERSION_ARRAY).elementsOrVersion);
                v1.profileId = arg3.readString(8, false);
                v1.conditions = NetworkConditions.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkContextSetNetworkConditionsParams deserialize(ByteBuffer arg2) {
            return NetworkContextSetNetworkConditionsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkContextSetNetworkConditionsParams deserialize(Message arg1) {
            return NetworkContextSetNetworkConditionsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkContextSetNetworkConditionsParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.profileId, 8, false);
            arg4.encode(this.conditions, 16, true);
        }
    }

    final class org.chromium.network.mojom.NetworkContext_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.NetworkContext$Proxy {
        org.chromium.network.mojom.NetworkContext_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addHstsForTesting(String arg6, Time arg7, boolean arg8, AddHstsForTestingResponse arg9) {
            NetworkContextAddHstsForTestingParams v0 = new NetworkContextAddHstsForTestingParams();
            v0.host = arg6;
            v0.expiry = arg7;
            v0.includeSubdomains = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(12, 1, 0)), new NetworkContextAddHstsForTestingResponseParamsForwardToCallback(arg9));
        }

        public void clearHttpCache(Time arg6, Time arg7, ClearCacheUrlFilter arg8, ClearHttpCacheResponse arg9) {
            NetworkContextClearHttpCacheParams v0 = new NetworkContextClearHttpCacheParams();
            v0.startTime = arg6;
            v0.endTime = arg7;
            v0.filter = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new NetworkContextClearHttpCacheResponseParamsForwardToCallback(arg9));
        }

        public void clearNetworkingHistorySince(Time arg8, ClearNetworkingHistorySinceResponse arg9) {
            NetworkContextClearNetworkingHistorySinceParams v0 = new NetworkContextClearNetworkingHistorySinceParams();
            v0.startTime = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new NetworkContextClearNetworkingHistorySinceResponseParamsForwardToCallback(arg9));
        }

        public void createTcpConnectedSocket(IpEndPoint arg4, AddressList arg5, MutableNetworkTrafficAnnotationTag arg6, InterfaceRequest arg7, TcpConnectedSocketObserver arg8, CreateTcpConnectedSocketResponse arg9) {
            NetworkContextCreateTcpConnectedSocketParams v0 = new NetworkContextCreateTcpConnectedSocketParams();
            v0.localAddr = arg4;
            v0.remoteAddrList = arg5;
            v0.trafficAnnotation = arg6;
            v0.socket = arg7;
            v0.observer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(10, 1, 0)), new NetworkContextCreateTcpConnectedSocketResponseParamsForwardToCallback(arg9));
        }

        public void createTcpServerSocket(IpEndPoint arg5, int arg6, MutableNetworkTrafficAnnotationTag arg7, InterfaceRequest arg8, CreateTcpServerSocketResponse arg9) {
            NetworkContextCreateTcpServerSocketParams v0 = new NetworkContextCreateTcpServerSocketParams();
            v0.localAddr = arg5;
            v0.backlog = arg6;
            v0.trafficAnnotation = arg7;
            v0.socket = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(9, 1, 0)), new NetworkContextCreateTcpServerSocketResponseParamsForwardToCallback(arg9));
        }

        public void createUdpSocket(InterfaceRequest arg4, UdpSocketReceiver arg5) {
            NetworkContextCreateUdpSocketParams v0 = new NetworkContextCreateUdpSocketParams();
            v0.request = arg4;
            v0.receiver = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8)));
        }

        public void createUrlLoaderFactory(InterfaceRequest arg4, int arg5) {
            NetworkContextCreateUrlLoaderFactoryParams v0 = new NetworkContextCreateUrlLoaderFactoryParams();
            v0.urlLoaderFactory = arg4;
            v0.processId = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void createWebSocket(InterfaceRequest arg2, int arg3, int arg4, Origin arg5) {
            NetworkContextCreateWebSocketParams v0 = new NetworkContextCreateWebSocketParams();
            v0.request = arg2;
            v0.processId = arg3;
            v0.renderFrameId = arg4;
            v0.origin = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(11)));
        }

        public void getCookieManager(InterfaceRequest arg5) {
            NetworkContextGetCookieManagerParams v0 = new NetworkContextGetCookieManagerParams();
            v0.cookieManager = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void getRestrictedCookieManager(InterfaceRequest arg3, int arg4, int arg5) {
            NetworkContextGetRestrictedCookieManagerParams v0 = new NetworkContextGetRestrictedCookieManagerParams();
            v0.restrictedCookieManager = arg3;
            v0.renderProcessId = arg4;
            v0.renderFrameId = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void setAcceptLanguage(String arg5) {
            NetworkContextSetAcceptLanguageParams v0 = new NetworkContextSetAcceptLanguageParams();
            v0.newAcceptLanguage = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void setCtPolicy(String[] arg2, String[] arg3, String[] arg4, String[] arg5) {
            NetworkContextSetCtPolicyParams v0 = new NetworkContextSetCtPolicyParams();
            v0.requiredHosts = arg2;
            v0.excludedHosts = arg3;
            v0.excludedSpkis = arg4;
            v0.excludedLegacySpkis = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }

        public void setNetworkConditions(String arg4, NetworkConditions arg5) {
            NetworkContextSetNetworkConditionsParams v0 = new NetworkContextSetNetworkConditionsParams();
            v0.profileId = arg4;
            v0.conditions = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }
    }

    final class org.chromium.network.mojom.NetworkContext_Internal$Stub extends Stub {
        org.chromium.network.mojom.NetworkContext_Internal$Stub(Core arg1, NetworkContext arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -2: {
                        goto label_69;
                    }
                    case 0: {
                        goto label_62;
                    }
                    case 1: {
                        goto label_56;
                    }
                    case 2: {
                        goto label_48;
                    }
                    case 5: {
                        goto label_41;
                    }
                    case 6: {
                        goto label_35;
                    }
                    case 7: {
                        goto label_26;
                    }
                    case 8: {
                        goto label_19;
                    }
                    case 11: {
                        goto label_10;
                    }
                }

                return 0;
            label_35:
                this.getImpl().setAcceptLanguage(NetworkContextSetAcceptLanguageParams.deserialize(v7_1.getPayload()).newAcceptLanguage);
                return 1;
            label_19:
                NetworkContextCreateUdpSocketParams v7_2 = NetworkContextCreateUdpSocketParams.deserialize(v7_1.getPayload());
                this.getImpl().createUdpSocket(v7_2.request, v7_2.receiver);
                return 1;
            label_69:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(NetworkContext_Internal.MANAGER, v7_1);
            label_56:
                this.getImpl().getCookieManager(NetworkContextGetCookieManagerParams.deserialize(v7_1.getPayload()).cookieManager);
                return 1;
            label_41:
                NetworkContextSetNetworkConditionsParams v7_3 = NetworkContextSetNetworkConditionsParams.deserialize(v7_1.getPayload());
                this.getImpl().setNetworkConditions(v7_3.profileId, v7_3.conditions);
                return 1;
            label_26:
                NetworkContextSetCtPolicyParams v7_4 = NetworkContextSetCtPolicyParams.deserialize(v7_1.getPayload());
                this.getImpl().setCtPolicy(v7_4.requiredHosts, v7_4.excludedHosts, v7_4.excludedSpkis, v7_4.excludedLegacySpkis);
                return 1;
            label_10:
                NetworkContextCreateWebSocketParams v7_5 = NetworkContextCreateWebSocketParams.deserialize(v7_1.getPayload());
                this.getImpl().createWebSocket(v7_5.request, v7_5.processId, v7_5.renderFrameId, v7_5.origin);
                return 1;
            label_62:
                NetworkContextCreateUrlLoaderFactoryParams v7_6 = NetworkContextCreateUrlLoaderFactoryParams.deserialize(v7_1.getPayload());
                this.getImpl().createUrlLoaderFactory(v7_6.urlLoaderFactory, v7_6.processId);
                return 1;
            label_48:
                NetworkContextGetRestrictedCookieManagerParams v7_7 = NetworkContextGetRestrictedCookieManagerParams.deserialize(v7_1.getPayload());
                this.getImpl().getRestrictedCookieManager(v7_7.restrictedCookieManager, v7_7.renderProcessId, v7_7.renderFrameId);
                return 1;
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg14, MessageReceiver arg15) {
            try {
                ServiceMessage v14_1 = arg14.asServiceMessage();
                MessageHeader v1 = v14_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_73;
                    }
                    case 3: {
                        goto label_63;
                    }
                    case 4: {
                        goto label_51;
                    }
                    case 9: {
                        goto label_37;
                    }
                    case 10: {
                        goto label_22;
                    }
                    case 12: {
                        goto label_10;
                    }
                }

                return 0;
            label_51:
                NetworkContextClearHttpCacheParams v14_2 = NetworkContextClearHttpCacheParams.deserialize(v14_1.getPayload());
                this.getImpl().clearHttpCache(v14_2.startTime, v14_2.endTime, v14_2.filter, new NetworkContextClearHttpCacheResponseParamsProxyToResponder(this.getCore(), arg15, v1.getRequestId()));
                return 1;
            label_37:
                NetworkContextCreateTcpServerSocketParams v14_3 = NetworkContextCreateTcpServerSocketParams.deserialize(v14_1.getPayload());
                this.getImpl().createTcpServerSocket(v14_3.localAddr, v14_3.backlog, v14_3.trafficAnnotation, v14_3.socket, new NetworkContextCreateTcpServerSocketResponseParamsProxyToResponder(this.getCore(), arg15, v1.getRequestId()));
                return 1;
            label_22:
                NetworkContextCreateTcpConnectedSocketParams v14_4 = NetworkContextCreateTcpConnectedSocketParams.deserialize(v14_1.getPayload());
                this.getImpl().createTcpConnectedSocket(v14_4.localAddr, v14_4.remoteAddrList, v14_4.trafficAnnotation, v14_4.socket, v14_4.observer, new NetworkContextCreateTcpConnectedSocketResponseParamsProxyToResponder(this.getCore(), arg15, v1.getRequestId()));
                return 1;
            label_73:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), NetworkContext_Internal.MANAGER, v14_1, arg15);
            label_10:
                NetworkContextAddHstsForTestingParams v14_5 = NetworkContextAddHstsForTestingParams.deserialize(v14_1.getPayload());
                this.getImpl().addHstsForTesting(v14_5.host, v14_5.expiry, v14_5.includeSubdomains, new NetworkContextAddHstsForTestingResponseParamsProxyToResponder(this.getCore(), arg15, v1.getRequestId()));
                return 1;
            label_63:
                this.getImpl().clearNetworkingHistorySince(NetworkContextClearNetworkingHistorySinceParams.deserialize(v14_1.getPayload()).startTime, new NetworkContextClearNetworkingHistorySinceResponseParamsProxyToResponder(this.getCore(), arg15, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v14) {
                System.err.println(v14.toString());
                return 0;
            }
        }
    }

    private static final int ADD_HSTS_FOR_TESTING_ORDINAL = 12;
    private static final int CLEAR_HTTP_CACHE_ORDINAL = 4;
    private static final int CLEAR_NETWORKING_HISTORY_SINCE_ORDINAL = 3;
    private static final int CREATE_TCP_CONNECTED_SOCKET_ORDINAL = 10;
    private static final int CREATE_TCP_SERVER_SOCKET_ORDINAL = 9;
    private static final int CREATE_UDP_SOCKET_ORDINAL = 8;
    private static final int CREATE_URL_LOADER_FACTORY_ORDINAL = 0;
    private static final int CREATE_WEB_SOCKET_ORDINAL = 11;
    private static final int GET_COOKIE_MANAGER_ORDINAL = 1;
    private static final int GET_RESTRICTED_COOKIE_MANAGER_ORDINAL = 2;
    public static final Manager MANAGER = null;
    private static final int SET_ACCEPT_LANGUAGE_ORDINAL = 6;
    private static final int SET_CT_POLICY_ORDINAL = 7;
    private static final int SET_NETWORK_CONDITIONS_ORDINAL = 5;

    static {
        NetworkContext_Internal.MANAGER = new org.chromium.network.mojom.NetworkContext_Internal$1();
    }

    NetworkContext_Internal() {
        super();
    }
}

