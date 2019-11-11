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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.url.mojom.Url;

class BlobUrlStore_Internal {
    final class org.chromium.blink.mojom.BlobUrlStore_Internal$1 extends Manager {
        org.chromium.blink.mojom.BlobUrlStore_Internal$1() {
            super();
        }

        public BlobUrlStore[] buildArray(int arg1) {
            return new BlobUrlStore[arg1];
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

        public Stub buildStub(Core arg2, BlobUrlStore arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((BlobUrlStore)arg2));
        }

        public String getName() {
            return "blink::mojom::BlobURLStore";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BlobUrlStoreRegisterParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Blob blob;
        public Url url;

        static {
            BlobUrlStoreRegisterParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobUrlStoreRegisterParams.DEFAULT_STRUCT_INFO = BlobUrlStoreRegisterParams.VERSION_ARRAY[0];
        }

        public BlobUrlStoreRegisterParams() {
            this(0);
        }

        private BlobUrlStoreRegisterParams(int arg2) {
            super(24, arg2);
        }

        public static BlobUrlStoreRegisterParams decode(Decoder arg4) {
            BlobUrlStoreRegisterParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BlobUrlStoreRegisterParams(arg4.readAndValidateDataHeader(BlobUrlStoreRegisterParams.VERSION_ARRAY).elementsOrVersion);
                v1.blob = arg4.readServiceInterface(8, false, Blob.MANAGER);
                v1.url = Url.decode(arg4.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlStoreRegisterParams deserialize(ByteBuffer arg2) {
            return BlobUrlStoreRegisterParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlStoreRegisterParams deserialize(Message arg1) {
            return BlobUrlStoreRegisterParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(BlobUrlStoreRegisterParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.blob, 8, false, Blob.MANAGER);
            arg5.encode(this.url, 16, false);
        }
    }

    final class BlobUrlStoreRegisterResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BlobUrlStoreRegisterResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BlobUrlStoreRegisterResponseParams.DEFAULT_STRUCT_INFO = BlobUrlStoreRegisterResponseParams.VERSION_ARRAY[0];
        }

        public BlobUrlStoreRegisterResponseParams() {
            this(0);
        }

        private BlobUrlStoreRegisterResponseParams(int arg2) {
            super(8, arg2);
        }

        public static BlobUrlStoreRegisterResponseParams decode(Decoder arg2) {
            BlobUrlStoreRegisterResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BlobUrlStoreRegisterResponseParams(arg2.readAndValidateDataHeader(BlobUrlStoreRegisterResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlStoreRegisterResponseParams deserialize(ByteBuffer arg2) {
            return BlobUrlStoreRegisterResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlStoreRegisterResponseParams deserialize(Message arg1) {
            return BlobUrlStoreRegisterResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BlobUrlStoreRegisterResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class BlobUrlStoreRegisterResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RegisterResponse mCallback;

        BlobUrlStoreRegisterResponseParamsForwardToCallback(RegisterResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg3) {
            try {
                if(!arg3.asServiceMessage().getHeader().validateHeader(0, 2)) {
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

    class BlobUrlStoreRegisterResponseParamsProxyToResponder implements RegisterResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobUrlStoreRegisterResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new BlobUrlStoreRegisterResponseParams().serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class BlobUrlStoreResolveAsUrlLoaderFactoryParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest factory;
        public Url url;

        static {
            BlobUrlStoreResolveAsUrlLoaderFactoryParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobUrlStoreResolveAsUrlLoaderFactoryParams.DEFAULT_STRUCT_INFO = BlobUrlStoreResolveAsUrlLoaderFactoryParams.VERSION_ARRAY[0];
        }

        public BlobUrlStoreResolveAsUrlLoaderFactoryParams() {
            this(0);
        }

        private BlobUrlStoreResolveAsUrlLoaderFactoryParams(int arg2) {
            super(24, arg2);
        }

        public static BlobUrlStoreResolveAsUrlLoaderFactoryParams decode(Decoder arg3) {
            BlobUrlStoreResolveAsUrlLoaderFactoryParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobUrlStoreResolveAsUrlLoaderFactoryParams(arg3.readAndValidateDataHeader(BlobUrlStoreResolveAsUrlLoaderFactoryParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
                v1.factory = arg3.readInterfaceRequest(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlStoreResolveAsUrlLoaderFactoryParams deserialize(ByteBuffer arg2) {
            return BlobUrlStoreResolveAsUrlLoaderFactoryParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlStoreResolveAsUrlLoaderFactoryParams deserialize(Message arg1) {
            return BlobUrlStoreResolveAsUrlLoaderFactoryParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(BlobUrlStoreResolveAsUrlLoaderFactoryParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.url, 8, false);
            arg4.encode(this.factory, 16, false);
        }
    }

    final class BlobUrlStoreResolveForNavigationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest token;
        public Url url;

        static {
            BlobUrlStoreResolveForNavigationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobUrlStoreResolveForNavigationParams.DEFAULT_STRUCT_INFO = BlobUrlStoreResolveForNavigationParams.VERSION_ARRAY[0];
        }

        public BlobUrlStoreResolveForNavigationParams() {
            this(0);
        }

        private BlobUrlStoreResolveForNavigationParams(int arg2) {
            super(24, arg2);
        }

        public static BlobUrlStoreResolveForNavigationParams decode(Decoder arg3) {
            BlobUrlStoreResolveForNavigationParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobUrlStoreResolveForNavigationParams(arg3.readAndValidateDataHeader(BlobUrlStoreResolveForNavigationParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
                v1.token = arg3.readInterfaceRequest(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlStoreResolveForNavigationParams deserialize(ByteBuffer arg2) {
            return BlobUrlStoreResolveForNavigationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlStoreResolveForNavigationParams deserialize(Message arg1) {
            return BlobUrlStoreResolveForNavigationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(BlobUrlStoreResolveForNavigationParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.url, 8, false);
            arg4.encode(this.token, 16, false);
        }
    }

    final class BlobUrlStoreResolveParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Url url;

        static {
            BlobUrlStoreResolveParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobUrlStoreResolveParams.DEFAULT_STRUCT_INFO = BlobUrlStoreResolveParams.VERSION_ARRAY[0];
        }

        public BlobUrlStoreResolveParams() {
            this(0);
        }

        private BlobUrlStoreResolveParams(int arg2) {
            super(16, arg2);
        }

        public static BlobUrlStoreResolveParams decode(Decoder arg3) {
            BlobUrlStoreResolveParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobUrlStoreResolveParams(arg3.readAndValidateDataHeader(BlobUrlStoreResolveParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlStoreResolveParams deserialize(ByteBuffer arg2) {
            return BlobUrlStoreResolveParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlStoreResolveParams deserialize(Message arg1) {
            return BlobUrlStoreResolveParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobUrlStoreResolveParams.DEFAULT_STRUCT_INFO).encode(this.url, 8, false);
        }
    }

    final class BlobUrlStoreResolveResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Blob blob;

        static {
            BlobUrlStoreResolveResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobUrlStoreResolveResponseParams.DEFAULT_STRUCT_INFO = BlobUrlStoreResolveResponseParams.VERSION_ARRAY[0];
        }

        public BlobUrlStoreResolveResponseParams() {
            this(0);
        }

        private BlobUrlStoreResolveResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BlobUrlStoreResolveResponseParams decode(Decoder arg4) {
            BlobUrlStoreResolveResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BlobUrlStoreResolveResponseParams(arg4.readAndValidateDataHeader(BlobUrlStoreResolveResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.blob = arg4.readServiceInterface(8, true, Blob.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlStoreResolveResponseParams deserialize(ByteBuffer arg2) {
            return BlobUrlStoreResolveResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlStoreResolveResponseParams deserialize(Message arg1) {
            return BlobUrlStoreResolveResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(BlobUrlStoreResolveResponseParams.DEFAULT_STRUCT_INFO).encode(this.blob, 8, true, Blob.MANAGER);
        }
    }

    class BlobUrlStoreResolveResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ResolveResponse mCallback;

        BlobUrlStoreResolveResponseParamsForwardToCallback(ResolveResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(BlobUrlStoreResolveResponseParams.deserialize(v4.getPayload()).blob);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BlobUrlStoreResolveResponseParamsProxyToResponder implements ResolveResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobUrlStoreResolveResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((Blob)arg1));
        }

        public void call(Blob arg6) {
            BlobUrlStoreResolveResponseParams v0 = new BlobUrlStoreResolveResponseParams();
            v0.blob = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class BlobUrlStoreRevokeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Url url;

        static {
            BlobUrlStoreRevokeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobUrlStoreRevokeParams.DEFAULT_STRUCT_INFO = BlobUrlStoreRevokeParams.VERSION_ARRAY[0];
        }

        public BlobUrlStoreRevokeParams() {
            this(0);
        }

        private BlobUrlStoreRevokeParams(int arg2) {
            super(16, arg2);
        }

        public static BlobUrlStoreRevokeParams decode(Decoder arg3) {
            BlobUrlStoreRevokeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobUrlStoreRevokeParams(arg3.readAndValidateDataHeader(BlobUrlStoreRevokeParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobUrlStoreRevokeParams deserialize(ByteBuffer arg2) {
            return BlobUrlStoreRevokeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobUrlStoreRevokeParams deserialize(Message arg1) {
            return BlobUrlStoreRevokeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobUrlStoreRevokeParams.DEFAULT_STRUCT_INFO).encode(this.url, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.BlobUrlStore$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void register(Blob arg7, Url arg8, RegisterResponse arg9) {
            BlobUrlStoreRegisterParams v0 = new BlobUrlStoreRegisterParams();
            v0.blob = arg7;
            v0.url = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new BlobUrlStoreRegisterResponseParamsForwardToCallback(arg9));
        }

        public void resolve(Url arg8, ResolveResponse arg9) {
            BlobUrlStoreResolveParams v0 = new BlobUrlStoreResolveParams();
            v0.url = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new BlobUrlStoreResolveResponseParamsForwardToCallback(arg9));
        }

        public void resolveAsUrlLoaderFactory(Url arg4, InterfaceRequest arg5) {
            BlobUrlStoreResolveAsUrlLoaderFactoryParams v0 = new BlobUrlStoreResolveAsUrlLoaderFactoryParams();
            v0.url = arg4;
            v0.factory = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void resolveForNavigation(Url arg4, InterfaceRequest arg5) {
            BlobUrlStoreResolveForNavigationParams v0 = new BlobUrlStoreResolveForNavigationParams();
            v0.url = arg4;
            v0.token = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void revoke(Url arg5) {
            BlobUrlStoreRevokeParams v0 = new BlobUrlStoreRevokeParams();
            v0.url = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, BlobUrlStore arg2) {
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
                    goto label_33;
                }

                if(v1_1 == 1) {
                    goto label_27;
                }

                switch(v1_1) {
                    case 3: {
                        goto label_20;
                    }
                    case 4: {
                        goto label_13;
                    }
                }

                return 0;
            label_20:
                BlobUrlStoreResolveAsUrlLoaderFactoryParams v5_2 = BlobUrlStoreResolveAsUrlLoaderFactoryParams.deserialize(v5_1.getPayload());
                this.getImpl().resolveAsUrlLoaderFactory(v5_2.url, v5_2.factory);
                return 1;
            label_13:
                BlobUrlStoreResolveForNavigationParams v5_3 = BlobUrlStoreResolveForNavigationParams.deserialize(v5_1.getPayload());
                this.getImpl().resolveForNavigation(v5_3.url, v5_3.token);
                return 1;
            label_27:
                this.getImpl().revoke(BlobUrlStoreRevokeParams.deserialize(v5_1.getPayload()).url);
                return 1;
            label_33:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(BlobUrlStore_Internal.MANAGER, v5_1);
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

                int v3 = v1.getType();
                if(v3 == 2) {
                    goto label_27;
                }

                switch(v3) {
                    case -1: {
                        goto label_23;
                    }
                    case 0: {
                        goto label_12;
                    }
                }

                return 0;
            label_23:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), BlobUrlStore_Internal.MANAGER, v10_1, arg11);
            label_12:
                BlobUrlStoreRegisterParams v10_2 = BlobUrlStoreRegisterParams.deserialize(v10_1.getPayload());
                this.getImpl().register(v10_2.blob, v10_2.url, new BlobUrlStoreRegisterResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_27:
                this.getImpl().resolve(BlobUrlStoreResolveParams.deserialize(v10_1.getPayload()).url, new BlobUrlStoreResolveResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int REGISTER_ORDINAL = 0;
    private static final int RESOLVE_AS_URL_LOADER_FACTORY_ORDINAL = 3;
    private static final int RESOLVE_FOR_NAVIGATION_ORDINAL = 4;
    private static final int RESOLVE_ORDINAL = 2;
    private static final int REVOKE_ORDINAL = 1;

    static {
        BlobUrlStore_Internal.MANAGER = new org.chromium.blink.mojom.BlobUrlStore_Internal$1();
    }

    BlobUrlStore_Internal() {
        super();
    }
}

