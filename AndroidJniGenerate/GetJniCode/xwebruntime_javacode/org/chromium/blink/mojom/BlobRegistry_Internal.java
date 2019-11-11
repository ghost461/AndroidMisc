package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
import org.chromium.mojo.bindings.AssociatedInterfaceRequestNotSupported;
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
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.url.mojom.Origin;

class BlobRegistry_Internal {
    final class org.chromium.blink.mojom.BlobRegistry_Internal$1 extends Manager {
        org.chromium.blink.mojom.BlobRegistry_Internal$1() {
            super();
        }

        public BlobRegistry[] buildArray(int arg1) {
            return new BlobRegistry[arg1];
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

        public Stub buildStub(Core arg2, BlobRegistry arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((BlobRegistry)arg2));
        }

        public String getName() {
            return "blink::mojom::BlobRegistry";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BlobRegistryGetBlobFromUuidParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest blob;
        public String uuid;

        static {
            BlobRegistryGetBlobFromUuidParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobRegistryGetBlobFromUuidParams.DEFAULT_STRUCT_INFO = BlobRegistryGetBlobFromUuidParams.VERSION_ARRAY[0];
        }

        public BlobRegistryGetBlobFromUuidParams() {
            this(0);
        }

        private BlobRegistryGetBlobFromUuidParams(int arg2) {
            super(24, arg2);
        }

        public static BlobRegistryGetBlobFromUuidParams decode(Decoder arg3) {
            BlobRegistryGetBlobFromUuidParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobRegistryGetBlobFromUuidParams(arg3.readAndValidateDataHeader(BlobRegistryGetBlobFromUuidParams.VERSION_ARRAY).elementsOrVersion);
                v1.blob = arg3.readInterfaceRequest(8, false);
                v1.uuid = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobRegistryGetBlobFromUuidParams deserialize(ByteBuffer arg2) {
            return BlobRegistryGetBlobFromUuidParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobRegistryGetBlobFromUuidParams deserialize(Message arg1) {
            return BlobRegistryGetBlobFromUuidParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(BlobRegistryGetBlobFromUuidParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.blob, 8, false);
            arg4.encode(this.uuid, 16, false);
        }
    }

    final class BlobRegistryGetBlobFromUuidResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BlobRegistryGetBlobFromUuidResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BlobRegistryGetBlobFromUuidResponseParams.DEFAULT_STRUCT_INFO = BlobRegistryGetBlobFromUuidResponseParams.VERSION_ARRAY[0];
        }

        public BlobRegistryGetBlobFromUuidResponseParams() {
            this(0);
        }

        private BlobRegistryGetBlobFromUuidResponseParams(int arg2) {
            super(8, arg2);
        }

        public static BlobRegistryGetBlobFromUuidResponseParams decode(Decoder arg2) {
            BlobRegistryGetBlobFromUuidResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BlobRegistryGetBlobFromUuidResponseParams(arg2.readAndValidateDataHeader(BlobRegistryGetBlobFromUuidResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BlobRegistryGetBlobFromUuidResponseParams deserialize(ByteBuffer arg2) {
            return BlobRegistryGetBlobFromUuidResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobRegistryGetBlobFromUuidResponseParams deserialize(Message arg1) {
            return BlobRegistryGetBlobFromUuidResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BlobRegistryGetBlobFromUuidResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class BlobRegistryGetBlobFromUuidResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetBlobFromUuidResponse mCallback;

        BlobRegistryGetBlobFromUuidResponseParamsForwardToCallback(GetBlobFromUuidResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg3) {
            try {
                if(!arg3.asServiceMessage().getHeader().validateHeader(2, 2)) {
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

    class BlobRegistryGetBlobFromUuidResponseParamsProxyToResponder implements GetBlobFromUuidResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobRegistryGetBlobFromUuidResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new BlobRegistryGetBlobFromUuidResponseParams().serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class BlobRegistryRegisterFromStreamParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public String contentDisposition;
        public String contentType;
        public ConsumerHandle data;
        public long lengthHint;
        public AssociatedInterfaceNotSupported progressClient;

        static {
            BlobRegistryRegisterFromStreamParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            BlobRegistryRegisterFromStreamParams.DEFAULT_STRUCT_INFO = BlobRegistryRegisterFromStreamParams.VERSION_ARRAY[0];
        }

        public BlobRegistryRegisterFromStreamParams() {
            this(0);
        }

        private BlobRegistryRegisterFromStreamParams(int arg2) {
            super(0x30, arg2);
            this.data = InvalidHandle.INSTANCE;
        }

        public static BlobRegistryRegisterFromStreamParams decode(Decoder arg5) {
            BlobRegistryRegisterFromStreamParams v1;
            if(arg5 == null) {
                return null;
            }

            arg5.increaseStackDepth();
            try {
                v1 = new BlobRegistryRegisterFromStreamParams(arg5.readAndValidateDataHeader(BlobRegistryRegisterFromStreamParams.VERSION_ARRAY).elementsOrVersion);
                v1.contentType = arg5.readString(8, false);
                v1.contentDisposition = arg5.readString(16, false);
                v1.lengthHint = arg5.readLong(24);
                v1.data = arg5.readConsumerHandle(0x20, false);
                v1.progressClient = arg5.readAssociatedServiceInterfaceNotSupported(36, true);
            }
            catch(Throwable v0) {
                arg5.decreaseStackDepth();
                throw v0;
            }

            arg5.decreaseStackDepth();
            return v1;
        }

        public static BlobRegistryRegisterFromStreamParams deserialize(ByteBuffer arg2) {
            return BlobRegistryRegisterFromStreamParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobRegistryRegisterFromStreamParams deserialize(Message arg1) {
            return BlobRegistryRegisterFromStreamParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(BlobRegistryRegisterFromStreamParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.contentType, 8, false);
            arg5.encode(this.contentDisposition, 16, false);
            arg5.encode(this.lengthHint, 24);
            arg5.encode(this.data, 0x20, false);
            arg5.encode(this.progressClient, 36, true);
        }
    }

    final class BlobRegistryRegisterFromStreamResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SerializedBlob blob;

        static {
            BlobRegistryRegisterFromStreamResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobRegistryRegisterFromStreamResponseParams.DEFAULT_STRUCT_INFO = BlobRegistryRegisterFromStreamResponseParams.VERSION_ARRAY[0];
        }

        public BlobRegistryRegisterFromStreamResponseParams() {
            this(0);
        }

        private BlobRegistryRegisterFromStreamResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BlobRegistryRegisterFromStreamResponseParams decode(Decoder arg3) {
            BlobRegistryRegisterFromStreamResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobRegistryRegisterFromStreamResponseParams(arg3.readAndValidateDataHeader(BlobRegistryRegisterFromStreamResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.blob = SerializedBlob.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobRegistryRegisterFromStreamResponseParams deserialize(ByteBuffer arg2) {
            return BlobRegistryRegisterFromStreamResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobRegistryRegisterFromStreamResponseParams deserialize(Message arg1) {
            return BlobRegistryRegisterFromStreamResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobRegistryRegisterFromStreamResponseParams.DEFAULT_STRUCT_INFO).encode(this.blob, 8, true);
        }
    }

    class BlobRegistryRegisterFromStreamResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RegisterFromStreamResponse mCallback;

        BlobRegistryRegisterFromStreamResponseParamsForwardToCallback(RegisterFromStreamResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(BlobRegistryRegisterFromStreamResponseParams.deserialize(v5.getPayload()).blob);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BlobRegistryRegisterFromStreamResponseParamsProxyToResponder implements RegisterFromStreamResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobRegistryRegisterFromStreamResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((SerializedBlob)arg1));
        }

        public void call(SerializedBlob arg7) {
            BlobRegistryRegisterFromStreamResponseParams v0 = new BlobRegistryRegisterFromStreamResponseParams();
            v0.blob = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class BlobRegistryRegisterParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest blob;
        public String contentDisposition;
        public String contentType;
        public DataElement[] elements;
        public String uuid;

        static {
            BlobRegistryRegisterParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            BlobRegistryRegisterParams.DEFAULT_STRUCT_INFO = BlobRegistryRegisterParams.VERSION_ARRAY[0];
        }

        public BlobRegistryRegisterParams() {
            this(0);
        }

        private BlobRegistryRegisterParams(int arg2) {
            super(0x30, arg2);
        }

        public static BlobRegistryRegisterParams decode(Decoder arg7) {
            BlobRegistryRegisterParams v1;
            if(arg7 == null) {
                return null;
            }

            arg7.increaseStackDepth();
            try {
                v1 = new BlobRegistryRegisterParams(arg7.readAndValidateDataHeader(BlobRegistryRegisterParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                int v2 = 0;
                v1.blob = arg7.readInterfaceRequest(v0_1, false);
                v1.uuid = arg7.readString(16, false);
                v1.contentType = arg7.readString(24, false);
                v1.contentDisposition = arg7.readString(0x20, false);
                Decoder v3 = arg7.readPointer(40, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.elements = new DataElement[v4.elementsOrVersion];
                while(v2 < v4.elementsOrVersion) {
                    v1.elements[v2] = DataElement.decode(v3, v2 * 16 + v0_1);
                    ++v2;
                }
            }
            catch(Throwable v0) {
                goto label_41;
            }

            arg7.decreaseStackDepth();
            return v1;
        label_41:
            arg7.decreaseStackDepth();
            throw v0;
        }

        public static BlobRegistryRegisterParams deserialize(ByteBuffer arg2) {
            return BlobRegistryRegisterParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobRegistryRegisterParams deserialize(Message arg1) {
            return BlobRegistryRegisterParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(BlobRegistryRegisterParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg6.encode(this.blob, v1, false);
            arg6.encode(this.uuid, 16, false);
            arg6.encode(this.contentType, 24, false);
            arg6.encode(this.contentDisposition, 0x20, false);
            int v3 = 40;
            if(this.elements == null) {
                arg6.encodeNullPointer(v3, false);
            }
            else {
                arg6 = arg6.encodeUnionArray(this.elements.length, v3, -1);
                int v0;
                for(v0 = 0; v0 < this.elements.length; ++v0) {
                    arg6.encode(this.elements[v0], v0 * 16 + v1, false);
                }
            }
        }
    }

    final class BlobRegistryRegisterResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BlobRegistryRegisterResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BlobRegistryRegisterResponseParams.DEFAULT_STRUCT_INFO = BlobRegistryRegisterResponseParams.VERSION_ARRAY[0];
        }

        public BlobRegistryRegisterResponseParams() {
            this(0);
        }

        private BlobRegistryRegisterResponseParams(int arg2) {
            super(8, arg2);
        }

        public static BlobRegistryRegisterResponseParams decode(Decoder arg2) {
            BlobRegistryRegisterResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BlobRegistryRegisterResponseParams(arg2.readAndValidateDataHeader(BlobRegistryRegisterResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BlobRegistryRegisterResponseParams deserialize(ByteBuffer arg2) {
            return BlobRegistryRegisterResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobRegistryRegisterResponseParams deserialize(Message arg1) {
            return BlobRegistryRegisterResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BlobRegistryRegisterResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class BlobRegistryRegisterResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RegisterResponse mCallback;

        BlobRegistryRegisterResponseParamsForwardToCallback(RegisterResponse arg1) {
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

    class BlobRegistryRegisterResponseParamsProxyToResponder implements RegisterResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobRegistryRegisterResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new BlobRegistryRegisterResponseParams().serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class BlobRegistryUrlStoreForOriginParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Origin origin;
        public AssociatedInterfaceRequestNotSupported urlStore;

        static {
            BlobRegistryUrlStoreForOriginParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobRegistryUrlStoreForOriginParams.DEFAULT_STRUCT_INFO = BlobRegistryUrlStoreForOriginParams.VERSION_ARRAY[0];
        }

        public BlobRegistryUrlStoreForOriginParams() {
            this(0);
        }

        private BlobRegistryUrlStoreForOriginParams(int arg2) {
            super(24, arg2);
        }

        public static BlobRegistryUrlStoreForOriginParams decode(Decoder arg3) {
            BlobRegistryUrlStoreForOriginParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobRegistryUrlStoreForOriginParams(arg3.readAndValidateDataHeader(BlobRegistryUrlStoreForOriginParams.VERSION_ARRAY).elementsOrVersion);
                v1.origin = Origin.decode(arg3.readPointer(8, false));
                v1.urlStore = arg3.readAssociatedInterfaceRequestNotSupported(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobRegistryUrlStoreForOriginParams deserialize(ByteBuffer arg2) {
            return BlobRegistryUrlStoreForOriginParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobRegistryUrlStoreForOriginParams deserialize(Message arg1) {
            return BlobRegistryUrlStoreForOriginParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(BlobRegistryUrlStoreForOriginParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.origin, 8, false);
            arg4.encode(this.urlStore, 16, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.BlobRegistry$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getBlobFromUuid(InterfaceRequest arg7, String arg8, GetBlobFromUuidResponse arg9) {
            BlobRegistryGetBlobFromUuidParams v0 = new BlobRegistryGetBlobFromUuidParams();
            v0.blob = arg7;
            v0.uuid = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new BlobRegistryGetBlobFromUuidResponseParamsForwardToCallback(arg9));
        }

        public void register(InterfaceRequest arg4, String arg5, String arg6, String arg7, DataElement[] arg8, RegisterResponse arg9) {
            BlobRegistryRegisterParams v0 = new BlobRegistryRegisterParams();
            v0.blob = arg4;
            v0.uuid = arg5;
            v0.contentType = arg6;
            v0.contentDisposition = arg7;
            v0.elements = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new BlobRegistryRegisterResponseParamsForwardToCallback(arg9));
        }

        public void registerFromStream(String arg2, String arg3, long arg4, ConsumerHandle arg6, AssociatedInterfaceNotSupported arg7, RegisterFromStreamResponse arg8) {
            BlobRegistryRegisterFromStreamParams v0 = new BlobRegistryRegisterFromStreamParams();
            v0.contentType = arg2;
            v0.contentDisposition = arg3;
            v0.lengthHint = arg4;
            v0.data = arg6;
            v0.progressClient = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new BlobRegistryRegisterFromStreamResponseParamsForwardToCallback(arg8));
        }

        public void urlStoreForOrigin(Origin arg4, AssociatedInterfaceRequestNotSupported arg5) {
            BlobRegistryUrlStoreForOriginParams v0 = new BlobRegistryUrlStoreForOriginParams();
            v0.origin = arg4;
            v0.urlStore = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, BlobRegistry arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 != -2) {
                    if(v1_1 != 3) {
                        return 0;
                    }

                    BlobRegistryUrlStoreForOriginParams v4_2 = BlobRegistryUrlStoreForOriginParams.deserialize(v4_1.getPayload());
                    this.getImpl().urlStoreForOrigin(v4_2.origin, v4_2.urlStore);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(BlobRegistry_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg17, MessageReceiver arg18) {
            MessageReceiver v1 = arg18;
            try {
                ServiceMessage v3 = arg17.asServiceMessage();
                MessageHeader v4 = v3.getHeader();
                if(!v4.validateHeader(1)) {
                    return 0;
                }

                switch(v4.getType()) {
                    case -1: {
                        goto label_54;
                    }
                    case 0: {
                        goto label_38;
                    }
                    case 1: {
                        goto label_22;
                    }
                    case 2: {
                        goto label_11;
                    }
                }

                return 0;
            label_54:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), BlobRegistry_Internal.MANAGER, v3, v1);
            label_38:
                BlobRegistryRegisterParams v3_1 = BlobRegistryRegisterParams.deserialize(v3.getPayload());
                this.getImpl().register(v3_1.blob, v3_1.uuid, v3_1.contentType, v3_1.contentDisposition, v3_1.elements, new BlobRegistryRegisterResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_22:
                BlobRegistryRegisterFromStreamParams v3_2 = BlobRegistryRegisterFromStreamParams.deserialize(v3.getPayload());
                this.getImpl().registerFromStream(v3_2.contentType, v3_2.contentDisposition, v3_2.lengthHint, v3_2.data, v3_2.progressClient, new BlobRegistryRegisterFromStreamResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_11:
                BlobRegistryGetBlobFromUuidParams v3_3 = BlobRegistryGetBlobFromUuidParams.deserialize(v3.getPayload());
                this.getImpl().getBlobFromUuid(v3_3.blob, v3_3.uuid, new BlobRegistryGetBlobFromUuidResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            }
            catch(DeserializationException v0) {
                System.err.println(v0.toString());
                return 0;
            }
        }
    }

    private static final int GET_BLOB_FROM_UUID_ORDINAL = 2;
    public static final Manager MANAGER = null;
    private static final int REGISTER_FROM_STREAM_ORDINAL = 1;
    private static final int REGISTER_ORDINAL = 0;
    private static final int URL_STORE_FOR_ORIGIN_ORDINAL = 3;

    static {
        BlobRegistry_Internal.MANAGER = new org.chromium.blink.mojom.BlobRegistry_Internal$1();
    }

    BlobRegistry_Internal() {
        super();
    }
}

