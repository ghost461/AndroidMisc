package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
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
import org.chromium.url.mojom.Url;

class ServiceWorkerHost_Internal {
    final class org.chromium.blink.mojom.ServiceWorkerHost_Internal$1 extends Manager {
        org.chromium.blink.mojom.ServiceWorkerHost_Internal$1() {
            super();
        }

        public ServiceWorkerHost[] buildArray(int arg1) {
            return new ServiceWorkerHost[arg1];
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new Proxy(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public Stub buildStub(Core arg2, ServiceWorkerHost arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceWorkerHost)arg2));
        }

        public String getName() {
            return "blink::mojom::ServiceWorkerHost";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ServiceWorkerHost$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void claimClients(ClaimClientsResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ServiceWorkerHostClaimClientsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(10, 1, 0)), new ServiceWorkerHostClaimClientsResponseParamsForwardToCallback(arg9));
        }

        public void clearCachedMetadata(Url arg5) {
            ServiceWorkerHostClearCachedMetadataParams v0 = new ServiceWorkerHostClearCachedMetadataParams();
            v0.url = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void focusClient(String arg8, FocusClientResponse arg9) {
            ServiceWorkerHostFocusClientParams v0 = new ServiceWorkerHostFocusClientParams();
            v0.clientUuid = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7, 1, 0)), new ServiceWorkerHostFocusClientResponseParamsForwardToCallback(arg9));
        }

        public void getClient(String arg8, GetClientResponse arg9) {
            ServiceWorkerHostGetClientParams v0 = new ServiceWorkerHostGetClientParams();
            v0.clientUuid = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new ServiceWorkerHostGetClientResponseParamsForwardToCallback(arg9));
        }

        public void getClients(ServiceWorkerClientQueryOptions arg8, GetClientsResponse arg9) {
            ServiceWorkerHostGetClientsParams v0 = new ServiceWorkerHostGetClientsParams();
            v0.options = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new ServiceWorkerHostGetClientsResponseParamsForwardToCallback(arg9));
        }

        public void navigateClient(String arg7, Url arg8, NavigateClientResponse arg9) {
            ServiceWorkerHostNavigateClientParams v0 = new ServiceWorkerHostNavigateClientParams();
            v0.clientUuid = arg7;
            v0.url = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8, 1, 0)), new ServiceWorkerHostNavigateClientResponseParamsForwardToCallback(arg9));
        }

        public void openNewTab(Url arg8, OpenNewTabResponse arg9) {
            ServiceWorkerHostOpenNewTabParams v0 = new ServiceWorkerHostOpenNewTabParams();
            v0.url = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new ServiceWorkerHostOpenNewTabResponseParamsForwardToCallback(arg9));
        }

        public void openPaymentHandlerWindow(Url arg8, OpenPaymentHandlerWindowResponse arg9) {
            ServiceWorkerHostOpenPaymentHandlerWindowParams v0 = new ServiceWorkerHostOpenPaymentHandlerWindowParams();
            v0.url = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new ServiceWorkerHostOpenPaymentHandlerWindowResponseParamsForwardToCallback(arg9));
        }

        public void postMessageToClient(String arg4, TransferableMessage arg5) {
            ServiceWorkerHostPostMessageToClientParams v0 = new ServiceWorkerHostPostMessageToClientParams();
            v0.clientUuid = arg4;
            v0.message = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void setCachedMetadata(Url arg4, byte[] arg5) {
            ServiceWorkerHostSetCachedMetadataParams v0 = new ServiceWorkerHostSetCachedMetadataParams();
            v0.url = arg4;
            v0.data = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void skipWaiting(SkipWaitingResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ServiceWorkerHostSkipWaitingParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(9, 1, 0)), new ServiceWorkerHostSkipWaitingResponseParamsForwardToCallback(arg9));
        }
    }

    final class ServiceWorkerHostClaimClientsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerHostClaimClientsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerHostClaimClientsParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostClaimClientsParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostClaimClientsParams() {
            this(0);
        }

        private ServiceWorkerHostClaimClientsParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerHostClaimClientsParams decode(Decoder arg2) {
            ServiceWorkerHostClaimClientsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostClaimClientsParams(arg2.readAndValidateDataHeader(ServiceWorkerHostClaimClientsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostClaimClientsParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostClaimClientsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostClaimClientsParams deserialize(Message arg1) {
            return ServiceWorkerHostClaimClientsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerHostClaimClientsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ServiceWorkerHostClaimClientsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public String errorMsg;

        static {
            ServiceWorkerHostClaimClientsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerHostClaimClientsResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostClaimClientsResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostClaimClientsResponseParams() {
            this(0);
        }

        private ServiceWorkerHostClaimClientsResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerHostClaimClientsResponseParams decode(Decoder arg3) {
            ServiceWorkerHostClaimClientsResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostClaimClientsResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerHostClaimClientsResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                ServiceWorkerErrorType.validate(v1.error);
                v1.errorMsg = arg3.readString(16, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostClaimClientsResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostClaimClientsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostClaimClientsResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostClaimClientsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerHostClaimClientsResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.errorMsg, 16, true);
        }
    }

    class ServiceWorkerHostClaimClientsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ClaimClientsResponse mCallback;

        ServiceWorkerHostClaimClientsResponseParamsForwardToCallback(ClaimClientsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(10, 2)) {
                    return 0;
                }

                ServiceWorkerHostClaimClientsResponseParams v5_1 = ServiceWorkerHostClaimClientsResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.error), v5_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostClaimClientsResponseParamsProxyToResponder implements ClaimClientsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostClaimClientsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, String arg7) {
            ServiceWorkerHostClaimClientsResponseParams v0 = new ServiceWorkerHostClaimClientsResponseParams();
            v0.error = arg6.intValue();
            v0.errorMsg = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(10, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((String)arg2));
        }
    }

    final class ServiceWorkerHostClearCachedMetadataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Url url;

        static {
            ServiceWorkerHostClearCachedMetadataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostClearCachedMetadataParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostClearCachedMetadataParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostClearCachedMetadataParams() {
            this(0);
        }

        private ServiceWorkerHostClearCachedMetadataParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostClearCachedMetadataParams decode(Decoder arg3) {
            ServiceWorkerHostClearCachedMetadataParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostClearCachedMetadataParams(arg3.readAndValidateDataHeader(ServiceWorkerHostClearCachedMetadataParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostClearCachedMetadataParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostClearCachedMetadataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostClearCachedMetadataParams deserialize(Message arg1) {
            return ServiceWorkerHostClearCachedMetadataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostClearCachedMetadataParams.DEFAULT_STRUCT_INFO).encode(this.url, 8, false);
        }
    }

    final class ServiceWorkerHostFocusClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String clientUuid;

        static {
            ServiceWorkerHostFocusClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostFocusClientParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostFocusClientParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostFocusClientParams() {
            this(0);
        }

        private ServiceWorkerHostFocusClientParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostFocusClientParams decode(Decoder arg3) {
            ServiceWorkerHostFocusClientParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostFocusClientParams(arg3.readAndValidateDataHeader(ServiceWorkerHostFocusClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.clientUuid = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostFocusClientParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostFocusClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostFocusClientParams deserialize(Message arg1) {
            return ServiceWorkerHostFocusClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostFocusClientParams.DEFAULT_STRUCT_INFO).encode(this.clientUuid, 8, false);
        }
    }

    final class ServiceWorkerHostFocusClientResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerClientInfo client;

        static {
            ServiceWorkerHostFocusClientResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostFocusClientResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostFocusClientResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostFocusClientResponseParams() {
            this(0);
        }

        private ServiceWorkerHostFocusClientResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostFocusClientResponseParams decode(Decoder arg3) {
            ServiceWorkerHostFocusClientResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostFocusClientResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerHostFocusClientResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = ServiceWorkerClientInfo.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostFocusClientResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostFocusClientResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostFocusClientResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostFocusClientResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostFocusClientResponseParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, true);
        }
    }

    class ServiceWorkerHostFocusClientResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final FocusClientResponse mCallback;

        ServiceWorkerHostFocusClientResponseParamsForwardToCallback(FocusClientResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(7, 2)) {
                    return 0;
                }

                this.mCallback.call(ServiceWorkerHostFocusClientResponseParams.deserialize(v5.getPayload()).client);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostFocusClientResponseParamsProxyToResponder implements FocusClientResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostFocusClientResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((ServiceWorkerClientInfo)arg1));
        }

        public void call(ServiceWorkerClientInfo arg7) {
            ServiceWorkerHostFocusClientResponseParams v0 = new ServiceWorkerHostFocusClientResponseParams();
            v0.client = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(7, 2, this.mRequestId)));
        }
    }

    final class ServiceWorkerHostGetClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String clientUuid;

        static {
            ServiceWorkerHostGetClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostGetClientParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostGetClientParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostGetClientParams() {
            this(0);
        }

        private ServiceWorkerHostGetClientParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostGetClientParams decode(Decoder arg3) {
            ServiceWorkerHostGetClientParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostGetClientParams(arg3.readAndValidateDataHeader(ServiceWorkerHostGetClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.clientUuid = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostGetClientParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostGetClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostGetClientParams deserialize(Message arg1) {
            return ServiceWorkerHostGetClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostGetClientParams.DEFAULT_STRUCT_INFO).encode(this.clientUuid, 8, false);
        }
    }

    final class ServiceWorkerHostGetClientResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerClientInfo client;

        static {
            ServiceWorkerHostGetClientResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostGetClientResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostGetClientResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostGetClientResponseParams() {
            this(0);
        }

        private ServiceWorkerHostGetClientResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostGetClientResponseParams decode(Decoder arg3) {
            ServiceWorkerHostGetClientResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostGetClientResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerHostGetClientResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = ServiceWorkerClientInfo.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostGetClientResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostGetClientResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostGetClientResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostGetClientResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostGetClientResponseParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, true);
        }
    }

    class ServiceWorkerHostGetClientResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetClientResponse mCallback;

        ServiceWorkerHostGetClientResponseParamsForwardToCallback(GetClientResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(ServiceWorkerHostGetClientResponseParams.deserialize(v5.getPayload()).client);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostGetClientResponseParamsProxyToResponder implements GetClientResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostGetClientResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((ServiceWorkerClientInfo)arg1));
        }

        public void call(ServiceWorkerClientInfo arg7) {
            ServiceWorkerHostGetClientResponseParams v0 = new ServiceWorkerHostGetClientResponseParams();
            v0.client = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    final class ServiceWorkerHostGetClientsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerClientQueryOptions options;

        static {
            ServiceWorkerHostGetClientsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostGetClientsParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostGetClientsParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostGetClientsParams() {
            this(0);
        }

        private ServiceWorkerHostGetClientsParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostGetClientsParams decode(Decoder arg3) {
            ServiceWorkerHostGetClientsParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostGetClientsParams(arg3.readAndValidateDataHeader(ServiceWorkerHostGetClientsParams.VERSION_ARRAY).elementsOrVersion);
                v1.options = ServiceWorkerClientQueryOptions.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostGetClientsParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostGetClientsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostGetClientsParams deserialize(Message arg1) {
            return ServiceWorkerHostGetClientsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostGetClientsParams.DEFAULT_STRUCT_INFO).encode(this.options, 8, false);
        }
    }

    final class ServiceWorkerHostGetClientsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerClientInfo[] clients;

        static {
            ServiceWorkerHostGetClientsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostGetClientsResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostGetClientsResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostGetClientsResponseParams() {
            this(0);
        }

        private ServiceWorkerHostGetClientsResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostGetClientsResponseParams decode(Decoder arg8) {
            ServiceWorkerHostGetClientsResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostGetClientsResponseParams(arg8.readAndValidateDataHeader(ServiceWorkerHostGetClientsResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.clients = new ServiceWorkerClientInfo[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.clients[v5] = ServiceWorkerClientInfo.decode(v3.readPointer(v5 * 8 + v2, false));
                }
            }
            catch(Throwable v0) {
                goto label_31;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_31:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static ServiceWorkerHostGetClientsResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostGetClientsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostGetClientsResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostGetClientsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(ServiceWorkerHostGetClientsResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.clients == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.clients.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.clients.length; ++v0) {
                    arg6.encode(this.clients[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    class ServiceWorkerHostGetClientsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetClientsResponse mCallback;

        ServiceWorkerHostGetClientsResponseParamsForwardToCallback(GetClientsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(ServiceWorkerHostGetClientsResponseParams.deserialize(v4.getPayload()).clients);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostGetClientsResponseParamsProxyToResponder implements GetClientsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostGetClientsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((ServiceWorkerClientInfo[])arg1));
        }

        public void call(ServiceWorkerClientInfo[] arg6) {
            ServiceWorkerHostGetClientsResponseParams v0 = new ServiceWorkerHostGetClientsResponseParams();
            v0.clients = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class ServiceWorkerHostNavigateClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String clientUuid;
        public Url url;

        static {
            ServiceWorkerHostNavigateClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerHostNavigateClientParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostNavigateClientParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostNavigateClientParams() {
            this(0);
        }

        private ServiceWorkerHostNavigateClientParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerHostNavigateClientParams decode(Decoder arg3) {
            ServiceWorkerHostNavigateClientParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostNavigateClientParams(arg3.readAndValidateDataHeader(ServiceWorkerHostNavigateClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.clientUuid = arg3.readString(8, false);
                v1.url = Url.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostNavigateClientParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostNavigateClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostNavigateClientParams deserialize(Message arg1) {
            return ServiceWorkerHostNavigateClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerHostNavigateClientParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.clientUuid, 8, false);
            arg4.encode(this.url, 16, false);
        }
    }

    final class ServiceWorkerHostNavigateClientResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerClientInfo client;
        public String errorMsg;
        public boolean success;

        static {
            ServiceWorkerHostNavigateClientResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ServiceWorkerHostNavigateClientResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostNavigateClientResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostNavigateClientResponseParams() {
            this(0);
        }

        private ServiceWorkerHostNavigateClientResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static ServiceWorkerHostNavigateClientResponseParams decode(Decoder arg3) {
            ServiceWorkerHostNavigateClientResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostNavigateClientResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerHostNavigateClientResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
                v1.client = ServiceWorkerClientInfo.decode(arg3.readPointer(16, true));
                v1.errorMsg = arg3.readString(24, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostNavigateClientResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostNavigateClientResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostNavigateClientResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostNavigateClientResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerHostNavigateClientResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.success, 8, 0);
            arg4.encode(this.client, 16, true);
            arg4.encode(this.errorMsg, 24, true);
        }
    }

    class ServiceWorkerHostNavigateClientResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final NavigateClientResponse mCallback;

        ServiceWorkerHostNavigateClientResponseParamsForwardToCallback(NavigateClientResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(8, 2)) {
                    return 0;
                }

                ServiceWorkerHostNavigateClientResponseParams v5_1 = ServiceWorkerHostNavigateClientResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Boolean.valueOf(v5_1.success), v5_1.client, v5_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostNavigateClientResponseParamsProxyToResponder implements NavigateClientResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostNavigateClientResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, ServiceWorkerClientInfo arg6, String arg7) {
            ServiceWorkerHostNavigateClientResponseParams v0 = new ServiceWorkerHostNavigateClientResponseParams();
            v0.success = arg5.booleanValue();
            v0.client = arg6;
            v0.errorMsg = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(8, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Boolean)arg1), ((ServiceWorkerClientInfo)arg2), ((String)arg3));
        }
    }

    final class ServiceWorkerHostOpenNewTabParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Url url;

        static {
            ServiceWorkerHostOpenNewTabParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostOpenNewTabParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostOpenNewTabParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostOpenNewTabParams() {
            this(0);
        }

        private ServiceWorkerHostOpenNewTabParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostOpenNewTabParams decode(Decoder arg3) {
            ServiceWorkerHostOpenNewTabParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostOpenNewTabParams(arg3.readAndValidateDataHeader(ServiceWorkerHostOpenNewTabParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostOpenNewTabParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostOpenNewTabParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostOpenNewTabParams deserialize(Message arg1) {
            return ServiceWorkerHostOpenNewTabParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostOpenNewTabParams.DEFAULT_STRUCT_INFO).encode(this.url, 8, false);
        }
    }

    final class ServiceWorkerHostOpenNewTabResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerClientInfo client;
        public String errorMsg;
        public boolean success;

        static {
            ServiceWorkerHostOpenNewTabResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ServiceWorkerHostOpenNewTabResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostOpenNewTabResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostOpenNewTabResponseParams() {
            this(0);
        }

        private ServiceWorkerHostOpenNewTabResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static ServiceWorkerHostOpenNewTabResponseParams decode(Decoder arg3) {
            ServiceWorkerHostOpenNewTabResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostOpenNewTabResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerHostOpenNewTabResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
                v1.client = ServiceWorkerClientInfo.decode(arg3.readPointer(16, true));
                v1.errorMsg = arg3.readString(24, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostOpenNewTabResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostOpenNewTabResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostOpenNewTabResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostOpenNewTabResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerHostOpenNewTabResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.success, 8, 0);
            arg4.encode(this.client, 16, true);
            arg4.encode(this.errorMsg, 24, true);
        }
    }

    class ServiceWorkerHostOpenNewTabResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OpenNewTabResponse mCallback;

        ServiceWorkerHostOpenNewTabResponseParamsForwardToCallback(OpenNewTabResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                ServiceWorkerHostOpenNewTabResponseParams v5_1 = ServiceWorkerHostOpenNewTabResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Boolean.valueOf(v5_1.success), v5_1.client, v5_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostOpenNewTabResponseParamsProxyToResponder implements OpenNewTabResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostOpenNewTabResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, ServiceWorkerClientInfo arg6, String arg7) {
            ServiceWorkerHostOpenNewTabResponseParams v0 = new ServiceWorkerHostOpenNewTabResponseParams();
            v0.success = arg5.booleanValue();
            v0.client = arg6;
            v0.errorMsg = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Boolean)arg1), ((ServiceWorkerClientInfo)arg2), ((String)arg3));
        }
    }

    final class ServiceWorkerHostOpenPaymentHandlerWindowParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Url url;

        static {
            ServiceWorkerHostOpenPaymentHandlerWindowParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostOpenPaymentHandlerWindowParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostOpenPaymentHandlerWindowParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostOpenPaymentHandlerWindowParams() {
            this(0);
        }

        private ServiceWorkerHostOpenPaymentHandlerWindowParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostOpenPaymentHandlerWindowParams decode(Decoder arg3) {
            ServiceWorkerHostOpenPaymentHandlerWindowParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostOpenPaymentHandlerWindowParams(arg3.readAndValidateDataHeader(ServiceWorkerHostOpenPaymentHandlerWindowParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostOpenPaymentHandlerWindowParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostOpenPaymentHandlerWindowParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostOpenPaymentHandlerWindowParams deserialize(Message arg1) {
            return ServiceWorkerHostOpenPaymentHandlerWindowParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostOpenPaymentHandlerWindowParams.DEFAULT_STRUCT_INFO).encode(this.url, 8, false);
        }
    }

    final class ServiceWorkerHostOpenPaymentHandlerWindowResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerClientInfo client;
        public String errorMsg;
        public boolean success;

        static {
            ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostOpenPaymentHandlerWindowResponseParams() {
            this(0);
        }

        private ServiceWorkerHostOpenPaymentHandlerWindowResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static ServiceWorkerHostOpenPaymentHandlerWindowResponseParams decode(Decoder arg3) {
            ServiceWorkerHostOpenPaymentHandlerWindowResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostOpenPaymentHandlerWindowResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
                v1.client = ServiceWorkerClientInfo.decode(arg3.readPointer(16, true));
                v1.errorMsg = arg3.readString(24, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostOpenPaymentHandlerWindowResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostOpenPaymentHandlerWindowResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.success, 8, 0);
            arg4.encode(this.client, 16, true);
            arg4.encode(this.errorMsg, 24, true);
        }
    }

    class ServiceWorkerHostOpenPaymentHandlerWindowResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OpenPaymentHandlerWindowResponse mCallback;

        ServiceWorkerHostOpenPaymentHandlerWindowResponseParamsForwardToCallback(OpenPaymentHandlerWindowResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                ServiceWorkerHostOpenPaymentHandlerWindowResponseParams v5_1 = ServiceWorkerHostOpenPaymentHandlerWindowResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Boolean.valueOf(v5_1.success), v5_1.client, v5_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostOpenPaymentHandlerWindowResponseParamsProxyToResponder implements OpenPaymentHandlerWindowResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostOpenPaymentHandlerWindowResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, ServiceWorkerClientInfo arg6, String arg7) {
            ServiceWorkerHostOpenPaymentHandlerWindowResponseParams v0 = new ServiceWorkerHostOpenPaymentHandlerWindowResponseParams();
            v0.success = arg5.booleanValue();
            v0.client = arg6;
            v0.errorMsg = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Boolean)arg1), ((ServiceWorkerClientInfo)arg2), ((String)arg3));
        }
    }

    final class ServiceWorkerHostPostMessageToClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String clientUuid;
        public TransferableMessage message;

        static {
            ServiceWorkerHostPostMessageToClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerHostPostMessageToClientParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostPostMessageToClientParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostPostMessageToClientParams() {
            this(0);
        }

        private ServiceWorkerHostPostMessageToClientParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerHostPostMessageToClientParams decode(Decoder arg3) {
            ServiceWorkerHostPostMessageToClientParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostPostMessageToClientParams(arg3.readAndValidateDataHeader(ServiceWorkerHostPostMessageToClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.clientUuid = arg3.readString(8, false);
                v1.message = TransferableMessage.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostPostMessageToClientParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostPostMessageToClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostPostMessageToClientParams deserialize(Message arg1) {
            return ServiceWorkerHostPostMessageToClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerHostPostMessageToClientParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.clientUuid, 8, false);
            arg4.encode(this.message, 16, false);
        }
    }

    final class ServiceWorkerHostSetCachedMetadataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;
        public Url url;

        static {
            ServiceWorkerHostSetCachedMetadataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerHostSetCachedMetadataParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostSetCachedMetadataParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostSetCachedMetadataParams() {
            this(0);
        }

        private ServiceWorkerHostSetCachedMetadataParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerHostSetCachedMetadataParams decode(Decoder arg4) {
            ServiceWorkerHostSetCachedMetadataParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostSetCachedMetadataParams(arg4.readAndValidateDataHeader(ServiceWorkerHostSetCachedMetadataParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg4.readPointer(8, false));
                v1.data = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostSetCachedMetadataParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostSetCachedMetadataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostSetCachedMetadataParams deserialize(Message arg1) {
            return ServiceWorkerHostSetCachedMetadataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ServiceWorkerHostSetCachedMetadataParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.url, 8, false);
            arg5.encode(this.data, 16, 0, -1);
        }
    }

    final class ServiceWorkerHostSkipWaitingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerHostSkipWaitingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerHostSkipWaitingParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostSkipWaitingParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostSkipWaitingParams() {
            this(0);
        }

        private ServiceWorkerHostSkipWaitingParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerHostSkipWaitingParams decode(Decoder arg2) {
            ServiceWorkerHostSkipWaitingParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostSkipWaitingParams(arg2.readAndValidateDataHeader(ServiceWorkerHostSkipWaitingParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostSkipWaitingParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostSkipWaitingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostSkipWaitingParams deserialize(Message arg1) {
            return ServiceWorkerHostSkipWaitingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerHostSkipWaitingParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ServiceWorkerHostSkipWaitingResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            ServiceWorkerHostSkipWaitingResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerHostSkipWaitingResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerHostSkipWaitingResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerHostSkipWaitingResponseParams() {
            this(0);
        }

        private ServiceWorkerHostSkipWaitingResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerHostSkipWaitingResponseParams decode(Decoder arg3) {
            ServiceWorkerHostSkipWaitingResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerHostSkipWaitingResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerHostSkipWaitingResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerHostSkipWaitingResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerHostSkipWaitingResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerHostSkipWaitingResponseParams deserialize(Message arg1) {
            return ServiceWorkerHostSkipWaitingResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerHostSkipWaitingResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class ServiceWorkerHostSkipWaitingResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SkipWaitingResponse mCallback;

        ServiceWorkerHostSkipWaitingResponseParamsForwardToCallback(SkipWaitingResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(9, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(ServiceWorkerHostSkipWaitingResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerHostSkipWaitingResponseParamsProxyToResponder implements SkipWaitingResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerHostSkipWaitingResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            ServiceWorkerHostSkipWaitingResponseParams v0 = new ServiceWorkerHostSkipWaitingResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(9, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ServiceWorkerHost arg2) {
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
                    goto label_34;
                }

                if(v1_1 == 6) {
                    goto label_27;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_20;
                    }
                    case 1: {
                        goto label_14;
                    }
                }

                return 0;
            label_20:
                ServiceWorkerHostSetCachedMetadataParams v5_2 = ServiceWorkerHostSetCachedMetadataParams.deserialize(v5_1.getPayload());
                this.getImpl().setCachedMetadata(v5_2.url, v5_2.data);
                return 1;
            label_14:
                this.getImpl().clearCachedMetadata(ServiceWorkerHostClearCachedMetadataParams.deserialize(v5_1.getPayload()).url);
                return 1;
            label_27:
                ServiceWorkerHostPostMessageToClientParams v5_3 = ServiceWorkerHostPostMessageToClientParams.deserialize(v5_1.getPayload());
                this.getImpl().postMessageToClient(v5_3.clientUuid, v5_3.message);
                return 1;
            label_34:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceWorkerHost_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg10, MessageReceiver arg11) {
            try {
                ServiceMessage v10_1 = arg10.asServiceMessage();
                MessageHeader v1 = v10_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_89;
                    }
                    case 2: {
                        goto label_79;
                    }
                    case 3: {
                        goto label_69;
                    }
                    case 4: {
                        goto label_59;
                    }
                    case 5: {
                        goto label_49;
                    }
                    case 7: {
                        goto label_39;
                    }
                    case 8: {
                        goto label_28;
                    }
                    case 9: {
                        goto label_19;
                    }
                    case 10: {
                        goto label_10;
                    }
                }

                return 0;
            label_49:
                this.getImpl().openPaymentHandlerWindow(ServiceWorkerHostOpenPaymentHandlerWindowParams.deserialize(v10_1.getPayload()).url, new ServiceWorkerHostOpenPaymentHandlerWindowResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_19:
                ServiceWorkerHostSkipWaitingParams.deserialize(v10_1.getPayload());
                this.getImpl().skipWaiting(new ServiceWorkerHostSkipWaitingResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_69:
                this.getImpl().getClient(ServiceWorkerHostGetClientParams.deserialize(v10_1.getPayload()).clientUuid, new ServiceWorkerHostGetClientResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_39:
                this.getImpl().focusClient(ServiceWorkerHostFocusClientParams.deserialize(v10_1.getPayload()).clientUuid, new ServiceWorkerHostFocusClientResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_89:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceWorkerHost_Internal.MANAGER, v10_1, arg11);
            label_10:
                ServiceWorkerHostClaimClientsParams.deserialize(v10_1.getPayload());
                this.getImpl().claimClients(new ServiceWorkerHostClaimClientsResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_59:
                this.getImpl().openNewTab(ServiceWorkerHostOpenNewTabParams.deserialize(v10_1.getPayload()).url, new ServiceWorkerHostOpenNewTabResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_28:
                ServiceWorkerHostNavigateClientParams v10_2 = ServiceWorkerHostNavigateClientParams.deserialize(v10_1.getPayload());
                this.getImpl().navigateClient(v10_2.clientUuid, v10_2.url, new ServiceWorkerHostNavigateClientResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_79:
                this.getImpl().getClients(ServiceWorkerHostGetClientsParams.deserialize(v10_1.getPayload()).options, new ServiceWorkerHostGetClientsResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int CLAIM_CLIENTS_ORDINAL = 10;
    private static final int CLEAR_CACHED_METADATA_ORDINAL = 1;
    private static final int FOCUS_CLIENT_ORDINAL = 7;
    private static final int GET_CLIENTS_ORDINAL = 2;
    private static final int GET_CLIENT_ORDINAL = 3;
    public static final Manager MANAGER = null;
    private static final int NAVIGATE_CLIENT_ORDINAL = 8;
    private static final int OPEN_NEW_TAB_ORDINAL = 4;
    private static final int OPEN_PAYMENT_HANDLER_WINDOW_ORDINAL = 5;
    private static final int POST_MESSAGE_TO_CLIENT_ORDINAL = 6;
    private static final int SET_CACHED_METADATA_ORDINAL = 0;
    private static final int SKIP_WAITING_ORDINAL = 9;

    static {
        ServiceWorkerHost_Internal.MANAGER = new org.chromium.blink.mojom.ServiceWorkerHost_Internal$1();
    }

    ServiceWorkerHost_Internal() {
        super();
    }
}

