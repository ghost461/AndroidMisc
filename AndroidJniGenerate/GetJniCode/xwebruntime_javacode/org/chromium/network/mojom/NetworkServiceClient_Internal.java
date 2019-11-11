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
import org.chromium.url.mojom.Url;

class NetworkServiceClient_Internal {
    final class org.chromium.network.mojom.NetworkServiceClient_Internal$1 extends Manager {
        org.chromium.network.mojom.NetworkServiceClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public NetworkServiceClient[] buildArray(int arg1) {
            return new NetworkServiceClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.NetworkServiceClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.NetworkServiceClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((NetworkServiceClient)arg2));
        }

        public org.chromium.network.mojom.NetworkServiceClient_Internal$Stub buildStub(Core arg2, NetworkServiceClient arg3) {
            return new org.chromium.network.mojom.NetworkServiceClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::NetworkServiceClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NetworkServiceClientOnAuthRequiredParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public AuthChallengeInfo authInfo;
        public boolean firstAuthAttempt;
        public int processId;
        public int requestId;
        public int resourceType;
        public int routingId;
        public Url url;

        static {
            NetworkServiceClientOnAuthRequiredParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            NetworkServiceClientOnAuthRequiredParams.DEFAULT_STRUCT_INFO = NetworkServiceClientOnAuthRequiredParams.VERSION_ARRAY[0];
        }

        public NetworkServiceClientOnAuthRequiredParams() {
            this(0);
        }

        private NetworkServiceClientOnAuthRequiredParams(int arg2) {
            super(0x30, arg2);
        }

        public static NetworkServiceClientOnAuthRequiredParams decode(Decoder arg3) {
            NetworkServiceClientOnAuthRequiredParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceClientOnAuthRequiredParams(arg3.readAndValidateDataHeader(NetworkServiceClientOnAuthRequiredParams.VERSION_ARRAY).elementsOrVersion);
                v1.processId = arg3.readInt(8);
                v1.routingId = arg3.readInt(12);
                v1.requestId = arg3.readInt(16);
                v1.resourceType = arg3.readInt(20);
                v1.url = Url.decode(arg3.readPointer(24, false));
                v1.firstAuthAttempt = arg3.readBoolean(0x20, 0);
                v1.authInfo = AuthChallengeInfo.decode(arg3.readPointer(40, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceClientOnAuthRequiredParams deserialize(ByteBuffer arg2) {
            return NetworkServiceClientOnAuthRequiredParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceClientOnAuthRequiredParams deserialize(Message arg1) {
            return NetworkServiceClientOnAuthRequiredParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkServiceClientOnAuthRequiredParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.processId, 8);
            arg4.encode(this.routingId, 12);
            arg4.encode(this.requestId, 16);
            arg4.encode(this.resourceType, 20);
            arg4.encode(this.url, 24, false);
            arg4.encode(this.firstAuthAttempt, 0x20, 0);
            arg4.encode(this.authInfo, 40, false);
        }
    }

    final class NetworkServiceClientOnAuthRequiredResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AuthCredentials credentials;

        static {
            NetworkServiceClientOnAuthRequiredResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceClientOnAuthRequiredResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceClientOnAuthRequiredResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceClientOnAuthRequiredResponseParams() {
            this(0);
        }

        private NetworkServiceClientOnAuthRequiredResponseParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceClientOnAuthRequiredResponseParams decode(Decoder arg3) {
            NetworkServiceClientOnAuthRequiredResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceClientOnAuthRequiredResponseParams(arg3.readAndValidateDataHeader(NetworkServiceClientOnAuthRequiredResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.credentials = AuthCredentials.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceClientOnAuthRequiredResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceClientOnAuthRequiredResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceClientOnAuthRequiredResponseParams deserialize(Message arg1) {
            return NetworkServiceClientOnAuthRequiredResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NetworkServiceClientOnAuthRequiredResponseParams.DEFAULT_STRUCT_INFO).encode(this.credentials, 8, true);
        }
    }

    class NetworkServiceClientOnAuthRequiredResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OnAuthRequiredResponse mCallback;

        NetworkServiceClientOnAuthRequiredResponseParamsForwardToCallback(OnAuthRequiredResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(NetworkServiceClientOnAuthRequiredResponseParams.deserialize(v4.getPayload()).credentials);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkServiceClientOnAuthRequiredResponseParamsProxyToResponder implements OnAuthRequiredResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceClientOnAuthRequiredResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((AuthCredentials)arg1));
        }

        public void call(AuthCredentials arg7) {
            NetworkServiceClientOnAuthRequiredResponseParams v0 = new NetworkServiceClientOnAuthRequiredResponseParams();
            v0.credentials = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class NetworkServiceClientOnCertificateRequestedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public SslCertRequestInfo certInfo;
        public int processId;
        public int requestId;
        public int routingId;

        static {
            NetworkServiceClientOnCertificateRequestedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            NetworkServiceClientOnCertificateRequestedParams.DEFAULT_STRUCT_INFO = NetworkServiceClientOnCertificateRequestedParams.VERSION_ARRAY[0];
        }

        public NetworkServiceClientOnCertificateRequestedParams() {
            this(0);
        }

        private NetworkServiceClientOnCertificateRequestedParams(int arg2) {
            super(0x20, arg2);
        }

        public static NetworkServiceClientOnCertificateRequestedParams decode(Decoder arg3) {
            NetworkServiceClientOnCertificateRequestedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceClientOnCertificateRequestedParams(arg3.readAndValidateDataHeader(NetworkServiceClientOnCertificateRequestedParams.VERSION_ARRAY).elementsOrVersion);
                v1.processId = arg3.readInt(8);
                v1.routingId = arg3.readInt(12);
                v1.requestId = arg3.readInt(16);
                v1.certInfo = SslCertRequestInfo.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceClientOnCertificateRequestedParams deserialize(ByteBuffer arg2) {
            return NetworkServiceClientOnCertificateRequestedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceClientOnCertificateRequestedParams deserialize(Message arg1) {
            return NetworkServiceClientOnCertificateRequestedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkServiceClientOnCertificateRequestedParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.processId, 8);
            arg4.encode(this.routingId, 12);
            arg4.encode(this.requestId, 16);
            arg4.encode(this.certInfo, 24, false);
        }
    }

    final class NetworkServiceClientOnCertificateRequestedResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public short[] algorithmPreferences;
        public boolean cancelCertificateSelection;
        public SslPrivateKey sslPrivateKey;
        public X509Certificate x509Certificate;

        static {
            NetworkServiceClientOnCertificateRequestedResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            NetworkServiceClientOnCertificateRequestedResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceClientOnCertificateRequestedResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceClientOnCertificateRequestedResponseParams() {
            this(0);
        }

        private NetworkServiceClientOnCertificateRequestedResponseParams(int arg2) {
            super(40, arg2);
        }

        public static NetworkServiceClientOnCertificateRequestedResponseParams decode(Decoder arg4) {
            NetworkServiceClientOnCertificateRequestedResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new NetworkServiceClientOnCertificateRequestedResponseParams(arg4.readAndValidateDataHeader(NetworkServiceClientOnCertificateRequestedResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.x509Certificate = X509Certificate.decode(arg4.readPointer(8, false));
                v1.algorithmPreferences = arg4.readShorts(16, 0, -1);
                v1.sslPrivateKey = arg4.readServiceInterface(24, false, SslPrivateKey.MANAGER);
                v1.cancelCertificateSelection = arg4.readBoolean(0x20, 0);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceClientOnCertificateRequestedResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceClientOnCertificateRequestedResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceClientOnCertificateRequestedResponseParams deserialize(Message arg1) {
            return NetworkServiceClientOnCertificateRequestedResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(NetworkServiceClientOnCertificateRequestedResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.x509Certificate, 8, false);
            arg5.encode(this.algorithmPreferences, 16, 0, -1);
            arg5.encode(this.sslPrivateKey, 24, false, SslPrivateKey.MANAGER);
            arg5.encode(this.cancelCertificateSelection, 0x20, 0);
        }
    }

    class NetworkServiceClientOnCertificateRequestedResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OnCertificateRequestedResponse mCallback;

        NetworkServiceClientOnCertificateRequestedResponseParamsForwardToCallback(OnCertificateRequestedResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7 = arg7.asServiceMessage();
                if(!v7.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                NetworkServiceClientOnCertificateRequestedResponseParams v7_1 = NetworkServiceClientOnCertificateRequestedResponseParams.deserialize(v7.getPayload());
                this.mCallback.call(v7_1.x509Certificate, v7_1.algorithmPreferences, v7_1.sslPrivateKey, Boolean.valueOf(v7_1.cancelCertificateSelection));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkServiceClientOnCertificateRequestedResponseParamsProxyToResponder implements OnCertificateRequestedResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceClientOnCertificateRequestedResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2, Object arg3, Object arg4) {
            this.call(((X509Certificate)arg1), ((short[])arg2), ((SslPrivateKey)arg3), ((Boolean)arg4));
        }

        public void call(X509Certificate arg4, short[] arg5, SslPrivateKey arg6, Boolean arg7) {
            NetworkServiceClientOnCertificateRequestedResponseParams v0 = new NetworkServiceClientOnCertificateRequestedResponseParams();
            v0.x509Certificate = arg4;
            v0.algorithmPreferences = arg5;
            v0.sslPrivateKey = arg6;
            v0.cancelCertificateSelection = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class NetworkServiceClientOnSslCertificateErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean fatal;
        public int processId;
        public int requestId;
        public int resourceType;
        public int routingId;
        public SslInfo sslInfo;
        public Url url;

        static {
            NetworkServiceClientOnSslCertificateErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            NetworkServiceClientOnSslCertificateErrorParams.DEFAULT_STRUCT_INFO = NetworkServiceClientOnSslCertificateErrorParams.VERSION_ARRAY[0];
        }

        public NetworkServiceClientOnSslCertificateErrorParams() {
            this(0);
        }

        private NetworkServiceClientOnSslCertificateErrorParams(int arg2) {
            super(0x30, arg2);
        }

        public static NetworkServiceClientOnSslCertificateErrorParams decode(Decoder arg3) {
            NetworkServiceClientOnSslCertificateErrorParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceClientOnSslCertificateErrorParams(arg3.readAndValidateDataHeader(NetworkServiceClientOnSslCertificateErrorParams.VERSION_ARRAY).elementsOrVersion);
                v1.processId = arg3.readInt(8);
                v1.routingId = arg3.readInt(12);
                v1.requestId = arg3.readInt(16);
                v1.resourceType = arg3.readInt(20);
                v1.url = Url.decode(arg3.readPointer(24, false));
                v1.sslInfo = SslInfo.decode(arg3.readPointer(0x20, false));
                v1.fatal = arg3.readBoolean(40, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceClientOnSslCertificateErrorParams deserialize(ByteBuffer arg2) {
            return NetworkServiceClientOnSslCertificateErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceClientOnSslCertificateErrorParams deserialize(Message arg1) {
            return NetworkServiceClientOnSslCertificateErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkServiceClientOnSslCertificateErrorParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.processId, 8);
            arg4.encode(this.routingId, 12);
            arg4.encode(this.requestId, 16);
            arg4.encode(this.resourceType, 20);
            arg4.encode(this.url, 24, false);
            arg4.encode(this.sslInfo, 0x20, false);
            arg4.encode(this.fatal, 40, 0);
        }
    }

    final class NetworkServiceClientOnSslCertificateErrorResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int netError;

        static {
            NetworkServiceClientOnSslCertificateErrorResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceClientOnSslCertificateErrorResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceClientOnSslCertificateErrorResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceClientOnSslCertificateErrorResponseParams() {
            this(0);
        }

        private NetworkServiceClientOnSslCertificateErrorResponseParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceClientOnSslCertificateErrorResponseParams decode(Decoder arg2) {
            NetworkServiceClientOnSslCertificateErrorResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceClientOnSslCertificateErrorResponseParams(arg2.readAndValidateDataHeader(NetworkServiceClientOnSslCertificateErrorResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.netError = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceClientOnSslCertificateErrorResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceClientOnSslCertificateErrorResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceClientOnSslCertificateErrorResponseParams deserialize(Message arg1) {
            return NetworkServiceClientOnSslCertificateErrorResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NetworkServiceClientOnSslCertificateErrorResponseParams.DEFAULT_STRUCT_INFO).encode(this.netError, 8);
        }
    }

    class NetworkServiceClientOnSslCertificateErrorResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OnSslCertificateErrorResponse mCallback;

        NetworkServiceClientOnSslCertificateErrorResponseParamsForwardToCallback(OnSslCertificateErrorResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(NetworkServiceClientOnSslCertificateErrorResponseParams.deserialize(v4.getPayload()).netError));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NetworkServiceClientOnSslCertificateErrorResponseParamsProxyToResponder implements OnSslCertificateErrorResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceClientOnSslCertificateErrorResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6) {
            NetworkServiceClientOnSslCertificateErrorResponseParams v0 = new NetworkServiceClientOnSslCertificateErrorResponseParams();
            v0.netError = arg6.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class org.chromium.network.mojom.NetworkServiceClient_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.NetworkServiceClient$Proxy {
        org.chromium.network.mojom.NetworkServiceClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onAuthRequired(int arg2, int arg3, int arg4, int arg5, Url arg6, boolean arg7, AuthChallengeInfo arg8, OnAuthRequiredResponse arg9) {
            NetworkServiceClientOnAuthRequiredParams v0 = new NetworkServiceClientOnAuthRequiredParams();
            v0.processId = arg2;
            v0.routingId = arg3;
            v0.requestId = arg4;
            v0.resourceType = arg5;
            v0.url = arg6;
            v0.firstAuthAttempt = arg7;
            v0.authInfo = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new NetworkServiceClientOnAuthRequiredResponseParamsForwardToCallback(arg9));
        }

        public void onCertificateRequested(int arg4, int arg5, int arg6, SslCertRequestInfo arg7, OnCertificateRequestedResponse arg8) {
            NetworkServiceClientOnCertificateRequestedParams v0 = new NetworkServiceClientOnCertificateRequestedParams();
            v0.processId = arg4;
            v0.routingId = arg5;
            v0.requestId = arg6;
            v0.certInfo = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new NetworkServiceClientOnCertificateRequestedResponseParamsForwardToCallback(arg8));
        }

        public void onSslCertificateError(int arg2, int arg3, int arg4, int arg5, Url arg6, SslInfo arg7, boolean arg8, OnSslCertificateErrorResponse arg9) {
            NetworkServiceClientOnSslCertificateErrorParams v0 = new NetworkServiceClientOnSslCertificateErrorParams();
            v0.processId = arg2;
            v0.routingId = arg3;
            v0.requestId = arg4;
            v0.resourceType = arg5;
            v0.url = arg6;
            v0.sslInfo = arg7;
            v0.fatal = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new NetworkServiceClientOnSslCertificateErrorResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.network.mojom.NetworkServiceClient_Internal$Stub extends Stub {
        org.chromium.network.mojom.NetworkServiceClient_Internal$Stub(Core arg1, NetworkServiceClient arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(NetworkServiceClient_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg18, MessageReceiver arg19) {
            MessageReceiver v1 = arg19;
            try {
                ServiceMessage v3 = arg18.asServiceMessage();
                MessageHeader v4 = v3.getHeader();
                if(!v4.validateHeader(1)) {
                    return 0;
                }

                switch(v4.getType()) {
                    case -1: {
                        goto label_62;
                    }
                    case 0: {
                        goto label_44;
                    }
                    case 1: {
                        goto label_29;
                    }
                    case 2: {
                        goto label_11;
                    }
                }

                return 0;
            label_11:
                NetworkServiceClientOnSslCertificateErrorParams v3_1 = NetworkServiceClientOnSslCertificateErrorParams.deserialize(v3.getPayload());
                this.getImpl().onSslCertificateError(v3_1.processId, v3_1.routingId, v3_1.requestId, v3_1.resourceType, v3_1.url, v3_1.sslInfo, v3_1.fatal, new NetworkServiceClientOnSslCertificateErrorResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_44:
                NetworkServiceClientOnAuthRequiredParams v3_2 = NetworkServiceClientOnAuthRequiredParams.deserialize(v3.getPayload());
                this.getImpl().onAuthRequired(v3_2.processId, v3_2.routingId, v3_2.requestId, v3_2.resourceType, v3_2.url, v3_2.firstAuthAttempt, v3_2.authInfo, new NetworkServiceClientOnAuthRequiredResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_29:
                NetworkServiceClientOnCertificateRequestedParams v3_3 = NetworkServiceClientOnCertificateRequestedParams.deserialize(v3.getPayload());
                this.getImpl().onCertificateRequested(v3_3.processId, v3_3.routingId, v3_3.requestId, v3_3.certInfo, new NetworkServiceClientOnCertificateRequestedResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_62:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), NetworkServiceClient_Internal.MANAGER, v3, v1);
            }
            catch(DeserializationException v0) {
                System.err.println(v0.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_AUTH_REQUIRED_ORDINAL = 0;
    private static final int ON_CERTIFICATE_REQUESTED_ORDINAL = 1;
    private static final int ON_SSL_CERTIFICATE_ERROR_ORDINAL = 2;

    static {
        NetworkServiceClient_Internal.MANAGER = new org.chromium.network.mojom.NetworkServiceClient_Internal$1();
    }

    NetworkServiceClient_Internal() {
        super();
    }
}

