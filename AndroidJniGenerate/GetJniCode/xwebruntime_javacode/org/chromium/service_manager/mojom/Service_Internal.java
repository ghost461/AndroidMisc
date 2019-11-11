package org.chromium.service_manager.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.AssociatedInterfaceRequestNotSupported;
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
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.MessagePipeHandle;

class Service_Internal {
    final class org.chromium.service_manager.mojom.Service_Internal$1 extends Manager {
        org.chromium.service_manager.mojom.Service_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public Service[] buildArray(int arg1) {
            return new Service[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.service_manager.mojom.Service_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.service_manager.mojom.Service_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Service)arg2));
        }

        public org.chromium.service_manager.mojom.Service_Internal$Stub buildStub(Core arg2, Service arg3) {
            return new org.chromium.service_manager.mojom.Service_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "service_manager::mojom::Service";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.service_manager.mojom.Service_Internal$Proxy extends AbstractProxy implements org.chromium.service_manager.mojom.Service$Proxy {
        org.chromium.service_manager.mojom.Service_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onBindInterface(BindSourceInfo arg5, String arg6, MessagePipeHandle arg7, OnBindInterfaceResponse arg8) {
            ServiceOnBindInterfaceParams v0 = new ServiceOnBindInterfaceParams();
            v0.source = arg5;
            v0.interfaceName = arg6;
            v0.interfacePipe = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new ServiceOnBindInterfaceResponseParamsForwardToCallback(arg8));
        }

        public void onStart(Identity arg8, OnStartResponse arg9) {
            ServiceOnStartParams v0 = new ServiceOnStartParams();
            v0.identity = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ServiceOnStartResponseParamsForwardToCallback(arg9));
        }
    }

    final class ServiceOnBindInterfaceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public String interfaceName;
        public MessagePipeHandle interfacePipe;
        public BindSourceInfo source;

        static {
            ServiceOnBindInterfaceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ServiceOnBindInterfaceParams.DEFAULT_STRUCT_INFO = ServiceOnBindInterfaceParams.VERSION_ARRAY[0];
        }

        public ServiceOnBindInterfaceParams() {
            this(0);
        }

        private ServiceOnBindInterfaceParams(int arg2) {
            super(0x20, arg2);
            this.interfacePipe = InvalidHandle.INSTANCE;
        }

        public static ServiceOnBindInterfaceParams decode(Decoder arg3) {
            ServiceOnBindInterfaceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceOnBindInterfaceParams(arg3.readAndValidateDataHeader(ServiceOnBindInterfaceParams.VERSION_ARRAY).elementsOrVersion);
                v1.source = BindSourceInfo.decode(arg3.readPointer(8, false));
                v1.interfaceName = arg3.readString(16, false);
                v1.interfacePipe = arg3.readMessagePipeHandle(24, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceOnBindInterfaceParams deserialize(ByteBuffer arg2) {
            return ServiceOnBindInterfaceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceOnBindInterfaceParams deserialize(Message arg1) {
            return ServiceOnBindInterfaceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceOnBindInterfaceParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.source, 8, false);
            arg4.encode(this.interfaceName, 16, false);
            arg4.encode(this.interfacePipe, 24, false);
        }
    }

    final class ServiceOnBindInterfaceResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceOnBindInterfaceResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceOnBindInterfaceResponseParams.DEFAULT_STRUCT_INFO = ServiceOnBindInterfaceResponseParams.VERSION_ARRAY[0];
        }

        public ServiceOnBindInterfaceResponseParams() {
            this(0);
        }

        private ServiceOnBindInterfaceResponseParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceOnBindInterfaceResponseParams decode(Decoder arg2) {
            ServiceOnBindInterfaceResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceOnBindInterfaceResponseParams(arg2.readAndValidateDataHeader(ServiceOnBindInterfaceResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceOnBindInterfaceResponseParams deserialize(ByteBuffer arg2) {
            return ServiceOnBindInterfaceResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceOnBindInterfaceResponseParams deserialize(Message arg1) {
            return ServiceOnBindInterfaceResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceOnBindInterfaceResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class ServiceOnBindInterfaceResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OnBindInterfaceResponse mCallback;

        ServiceOnBindInterfaceResponseParamsForwardToCallback(OnBindInterfaceResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(1, 2)) {
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

    class ServiceOnBindInterfaceResponseParamsProxyToResponder implements OnBindInterfaceResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceOnBindInterfaceResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new ServiceOnBindInterfaceResponseParams().serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class ServiceOnStartParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity identity;

        static {
            ServiceOnStartParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceOnStartParams.DEFAULT_STRUCT_INFO = ServiceOnStartParams.VERSION_ARRAY[0];
        }

        public ServiceOnStartParams() {
            this(0);
        }

        private ServiceOnStartParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceOnStartParams decode(Decoder arg3) {
            ServiceOnStartParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceOnStartParams(arg3.readAndValidateDataHeader(ServiceOnStartParams.VERSION_ARRAY).elementsOrVersion);
                v1.identity = Identity.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceOnStartParams deserialize(ByteBuffer arg2) {
            return ServiceOnStartParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceOnStartParams deserialize(Message arg1) {
            return ServiceOnStartParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceOnStartParams.DEFAULT_STRUCT_INFO).encode(this.identity, 8, false);
        }
    }

    final class ServiceOnStartResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest connectorRequest;
        public AssociatedInterfaceRequestNotSupported controlRequest;

        static {
            ServiceOnStartResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceOnStartResponseParams.DEFAULT_STRUCT_INFO = ServiceOnStartResponseParams.VERSION_ARRAY[0];
        }

        public ServiceOnStartResponseParams() {
            this(0);
        }

        private ServiceOnStartResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceOnStartResponseParams decode(Decoder arg3) {
            ServiceOnStartResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceOnStartResponseParams(arg3.readAndValidateDataHeader(ServiceOnStartResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.connectorRequest = arg3.readInterfaceRequest(8, true);
                v1.controlRequest = arg3.readAssociatedInterfaceRequestNotSupported(12, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceOnStartResponseParams deserialize(ByteBuffer arg2) {
            return ServiceOnStartResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceOnStartResponseParams deserialize(Message arg1) {
            return ServiceOnStartResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceOnStartResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.connectorRequest, 8, true);
            arg4.encode(this.controlRequest, 12, true);
        }
    }

    class ServiceOnStartResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OnStartResponse mCallback;

        ServiceOnStartResponseParamsForwardToCallback(OnStartResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                ServiceOnStartResponseParams v4_1 = ServiceOnStartResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(v4_1.connectorRequest, v4_1.controlRequest);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceOnStartResponseParamsProxyToResponder implements OnStartResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceOnStartResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2) {
            this.call(((InterfaceRequest)arg1), ((AssociatedInterfaceRequestNotSupported)arg2));
        }

        public void call(InterfaceRequest arg6, AssociatedInterfaceRequestNotSupported arg7) {
            ServiceOnStartResponseParams v0 = new ServiceOnStartResponseParams();
            v0.connectorRequest = arg6;
            v0.controlRequest = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class org.chromium.service_manager.mojom.Service_Internal$Stub extends Stub {
        org.chromium.service_manager.mojom.Service_Internal$Stub(Core arg1, Service arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                if(v1.getType() != -2) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Service_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
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
                        goto label_32;
                    }
                    case 0: {
                        goto label_22;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_22:
                this.getImpl().onStart(ServiceOnStartParams.deserialize(v11_1.getPayload()).identity, new ServiceOnStartResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_10:
                ServiceOnBindInterfaceParams v11_2 = ServiceOnBindInterfaceParams.deserialize(v11_1.getPayload());
                this.getImpl().onBindInterface(v11_2.source, v11_2.interfaceName, v11_2.interfacePipe, new ServiceOnBindInterfaceResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_32:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Service_Internal.MANAGER, v11_1, arg12);
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_BIND_INTERFACE_ORDINAL = 1;
    private static final int ON_START_ORDINAL;

    static {
        Service_Internal.MANAGER = new org.chromium.service_manager.mojom.Service_Internal$1();
    }

    Service_Internal() {
        super();
    }
}

