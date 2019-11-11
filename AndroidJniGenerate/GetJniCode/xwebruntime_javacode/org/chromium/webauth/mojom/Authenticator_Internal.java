package org.chromium.webauth.mojom;

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

class Authenticator_Internal {
    final class org.chromium.webauth.mojom.Authenticator_Internal$1 extends Manager {
        org.chromium.webauth.mojom.Authenticator_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public Authenticator[] buildArray(int arg1) {
            return new Authenticator[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.webauth.mojom.Authenticator_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.webauth.mojom.Authenticator_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Authenticator)arg2));
        }

        public org.chromium.webauth.mojom.Authenticator_Internal$Stub buildStub(Core arg2, Authenticator arg3) {
            return new org.chromium.webauth.mojom.Authenticator_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "webauth::mojom::Authenticator";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AuthenticatorGetAssertionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PublicKeyCredentialRequestOptions options;

        static {
            AuthenticatorGetAssertionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AuthenticatorGetAssertionParams.DEFAULT_STRUCT_INFO = AuthenticatorGetAssertionParams.VERSION_ARRAY[0];
        }

        public AuthenticatorGetAssertionParams() {
            this(0);
        }

        private AuthenticatorGetAssertionParams(int arg2) {
            super(16, arg2);
        }

        public static AuthenticatorGetAssertionParams decode(Decoder arg3) {
            AuthenticatorGetAssertionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AuthenticatorGetAssertionParams(arg3.readAndValidateDataHeader(AuthenticatorGetAssertionParams.VERSION_ARRAY).elementsOrVersion);
                v1.options = PublicKeyCredentialRequestOptions.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AuthenticatorGetAssertionParams deserialize(ByteBuffer arg2) {
            return AuthenticatorGetAssertionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AuthenticatorGetAssertionParams deserialize(Message arg1) {
            return AuthenticatorGetAssertionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AuthenticatorGetAssertionParams.DEFAULT_STRUCT_INFO).encode(this.options, 8, false);
        }
    }

    final class AuthenticatorGetAssertionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public GetAssertionAuthenticatorResponse credential;
        public int status;

        static {
            AuthenticatorGetAssertionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            AuthenticatorGetAssertionResponseParams.DEFAULT_STRUCT_INFO = AuthenticatorGetAssertionResponseParams.VERSION_ARRAY[0];
        }

        public AuthenticatorGetAssertionResponseParams() {
            this(0);
        }

        private AuthenticatorGetAssertionResponseParams(int arg2) {
            super(24, arg2);
        }

        public static AuthenticatorGetAssertionResponseParams decode(Decoder arg3) {
            AuthenticatorGetAssertionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AuthenticatorGetAssertionResponseParams(arg3.readAndValidateDataHeader(AuthenticatorGetAssertionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg3.readInt(8);
                AuthenticatorStatus.validate(v1.status);
                v1.credential = GetAssertionAuthenticatorResponse.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AuthenticatorGetAssertionResponseParams deserialize(ByteBuffer arg2) {
            return AuthenticatorGetAssertionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AuthenticatorGetAssertionResponseParams deserialize(Message arg1) {
            return AuthenticatorGetAssertionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(AuthenticatorGetAssertionResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.credential, 16, true);
        }
    }

    class AuthenticatorGetAssertionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetAssertionResponse mCallback;

        AuthenticatorGetAssertionResponseParamsForwardToCallback(GetAssertionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                AuthenticatorGetAssertionResponseParams v5_1 = AuthenticatorGetAssertionResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.status), v5_1.credential);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class AuthenticatorGetAssertionResponseParamsProxyToResponder implements GetAssertionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        AuthenticatorGetAssertionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, GetAssertionAuthenticatorResponse arg7) {
            AuthenticatorGetAssertionResponseParams v0 = new AuthenticatorGetAssertionResponseParams();
            v0.status = arg6.intValue();
            v0.credential = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((GetAssertionAuthenticatorResponse)arg2));
        }
    }

    final class AuthenticatorMakeCredentialParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PublicKeyCredentialCreationOptions options;

        static {
            AuthenticatorMakeCredentialParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AuthenticatorMakeCredentialParams.DEFAULT_STRUCT_INFO = AuthenticatorMakeCredentialParams.VERSION_ARRAY[0];
        }

        public AuthenticatorMakeCredentialParams() {
            this(0);
        }

        private AuthenticatorMakeCredentialParams(int arg2) {
            super(16, arg2);
        }

        public static AuthenticatorMakeCredentialParams decode(Decoder arg3) {
            AuthenticatorMakeCredentialParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AuthenticatorMakeCredentialParams(arg3.readAndValidateDataHeader(AuthenticatorMakeCredentialParams.VERSION_ARRAY).elementsOrVersion);
                v1.options = PublicKeyCredentialCreationOptions.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AuthenticatorMakeCredentialParams deserialize(ByteBuffer arg2) {
            return AuthenticatorMakeCredentialParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AuthenticatorMakeCredentialParams deserialize(Message arg1) {
            return AuthenticatorMakeCredentialParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AuthenticatorMakeCredentialParams.DEFAULT_STRUCT_INFO).encode(this.options, 8, false);
        }
    }

    final class AuthenticatorMakeCredentialResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public MakeCredentialAuthenticatorResponse credential;
        public int status;

        static {
            AuthenticatorMakeCredentialResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            AuthenticatorMakeCredentialResponseParams.DEFAULT_STRUCT_INFO = AuthenticatorMakeCredentialResponseParams.VERSION_ARRAY[0];
        }

        public AuthenticatorMakeCredentialResponseParams() {
            this(0);
        }

        private AuthenticatorMakeCredentialResponseParams(int arg2) {
            super(24, arg2);
        }

        public static AuthenticatorMakeCredentialResponseParams decode(Decoder arg3) {
            AuthenticatorMakeCredentialResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AuthenticatorMakeCredentialResponseParams(arg3.readAndValidateDataHeader(AuthenticatorMakeCredentialResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg3.readInt(8);
                AuthenticatorStatus.validate(v1.status);
                v1.credential = MakeCredentialAuthenticatorResponse.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AuthenticatorMakeCredentialResponseParams deserialize(ByteBuffer arg2) {
            return AuthenticatorMakeCredentialResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AuthenticatorMakeCredentialResponseParams deserialize(Message arg1) {
            return AuthenticatorMakeCredentialResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(AuthenticatorMakeCredentialResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.credential, 16, true);
        }
    }

    class AuthenticatorMakeCredentialResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final MakeCredentialResponse mCallback;

        AuthenticatorMakeCredentialResponseParamsForwardToCallback(MakeCredentialResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                AuthenticatorMakeCredentialResponseParams v4_1 = AuthenticatorMakeCredentialResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.status), v4_1.credential);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class AuthenticatorMakeCredentialResponseParamsProxyToResponder implements MakeCredentialResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        AuthenticatorMakeCredentialResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, MakeCredentialAuthenticatorResponse arg7) {
            AuthenticatorMakeCredentialResponseParams v0 = new AuthenticatorMakeCredentialResponseParams();
            v0.status = arg6.intValue();
            v0.credential = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((MakeCredentialAuthenticatorResponse)arg2));
        }
    }

    final class org.chromium.webauth.mojom.Authenticator_Internal$Proxy extends AbstractProxy implements org.chromium.webauth.mojom.Authenticator$Proxy {
        org.chromium.webauth.mojom.Authenticator_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getAssertion(PublicKeyCredentialRequestOptions arg7, GetAssertionResponse arg8) {
            AuthenticatorGetAssertionParams v0 = new AuthenticatorGetAssertionParams();
            v0.options = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new AuthenticatorGetAssertionResponseParamsForwardToCallback(arg8));
        }

        public void makeCredential(PublicKeyCredentialCreationOptions arg8, MakeCredentialResponse arg9) {
            AuthenticatorMakeCredentialParams v0 = new AuthenticatorMakeCredentialParams();
            v0.options = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new AuthenticatorMakeCredentialResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.webauth.mojom.Authenticator_Internal$Stub extends Stub {
        org.chromium.webauth.mojom.Authenticator_Internal$Stub(Core arg1, Authenticator arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Authenticator_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg9, MessageReceiver arg10) {
            try {
                ServiceMessage v9_1 = arg9.asServiceMessage();
                MessageHeader v1 = v9_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_30;
                    }
                    case 0: {
                        goto label_20;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                this.getImpl().makeCredential(AuthenticatorMakeCredentialParams.deserialize(v9_1.getPayload()).options, new AuthenticatorMakeCredentialResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_10:
                this.getImpl().getAssertion(AuthenticatorGetAssertionParams.deserialize(v9_1.getPayload()).options, new AuthenticatorGetAssertionResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_30:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Authenticator_Internal.MANAGER, v9_1, arg10);
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int GET_ASSERTION_ORDINAL = 1;
    private static final int MAKE_CREDENTIAL_ORDINAL;
    public static final Manager MANAGER;

    static {
        Authenticator_Internal.MANAGER = new org.chromium.webauth.mojom.Authenticator_Internal$1();
    }

    Authenticator_Internal() {
        super();
    }
}

