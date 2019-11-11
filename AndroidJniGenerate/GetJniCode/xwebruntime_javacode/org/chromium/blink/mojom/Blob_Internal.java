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
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.InvalidHandle;

class Blob_Internal {
    final class org.chromium.blink.mojom.Blob_Internal$1 extends Manager {
        org.chromium.blink.mojom.Blob_Internal$1() {
            super();
        }

        public Blob[] buildArray(int arg1) {
            return new Blob[arg1];
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

        public Stub buildStub(Core arg2, Blob arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Blob)arg2));
        }

        public String getName() {
            return "blink::mojom::Blob";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BlobAsDataPipeGetterParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest dataPipeGetter;

        static {
            BlobAsDataPipeGetterParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobAsDataPipeGetterParams.DEFAULT_STRUCT_INFO = BlobAsDataPipeGetterParams.VERSION_ARRAY[0];
        }

        public BlobAsDataPipeGetterParams() {
            this(0);
        }

        private BlobAsDataPipeGetterParams(int arg2) {
            super(16, arg2);
        }

        public static BlobAsDataPipeGetterParams decode(Decoder arg3) {
            BlobAsDataPipeGetterParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobAsDataPipeGetterParams(arg3.readAndValidateDataHeader(BlobAsDataPipeGetterParams.VERSION_ARRAY).elementsOrVersion);
                v1.dataPipeGetter = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobAsDataPipeGetterParams deserialize(ByteBuffer arg2) {
            return BlobAsDataPipeGetterParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobAsDataPipeGetterParams deserialize(Message arg1) {
            return BlobAsDataPipeGetterParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobAsDataPipeGetterParams.DEFAULT_STRUCT_INFO).encode(this.dataPipeGetter, 8, false);
        }
    }

    final class BlobCloneParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest blob;

        static {
            BlobCloneParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobCloneParams.DEFAULT_STRUCT_INFO = BlobCloneParams.VERSION_ARRAY[0];
        }

        public BlobCloneParams() {
            this(0);
        }

        private BlobCloneParams(int arg2) {
            super(16, arg2);
        }

        public static BlobCloneParams decode(Decoder arg3) {
            BlobCloneParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobCloneParams(arg3.readAndValidateDataHeader(BlobCloneParams.VERSION_ARRAY).elementsOrVersion);
                v1.blob = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobCloneParams deserialize(ByteBuffer arg2) {
            return BlobCloneParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobCloneParams deserialize(Message arg1) {
            return BlobCloneParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobCloneParams.DEFAULT_STRUCT_INFO).encode(this.blob, 8, false);
        }
    }

    final class BlobGetInternalUuidParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BlobGetInternalUuidParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BlobGetInternalUuidParams.DEFAULT_STRUCT_INFO = BlobGetInternalUuidParams.VERSION_ARRAY[0];
        }

        public BlobGetInternalUuidParams() {
            this(0);
        }

        private BlobGetInternalUuidParams(int arg2) {
            super(8, arg2);
        }

        public static BlobGetInternalUuidParams decode(Decoder arg2) {
            BlobGetInternalUuidParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BlobGetInternalUuidParams(arg2.readAndValidateDataHeader(BlobGetInternalUuidParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BlobGetInternalUuidParams deserialize(ByteBuffer arg2) {
            return BlobGetInternalUuidParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobGetInternalUuidParams deserialize(Message arg1) {
            return BlobGetInternalUuidParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BlobGetInternalUuidParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class BlobGetInternalUuidResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String uuid;

        static {
            BlobGetInternalUuidResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobGetInternalUuidResponseParams.DEFAULT_STRUCT_INFO = BlobGetInternalUuidResponseParams.VERSION_ARRAY[0];
        }

        public BlobGetInternalUuidResponseParams() {
            this(0);
        }

        private BlobGetInternalUuidResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BlobGetInternalUuidResponseParams decode(Decoder arg3) {
            BlobGetInternalUuidResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BlobGetInternalUuidResponseParams(arg3.readAndValidateDataHeader(BlobGetInternalUuidResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.uuid = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BlobGetInternalUuidResponseParams deserialize(ByteBuffer arg2) {
            return BlobGetInternalUuidResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobGetInternalUuidResponseParams deserialize(Message arg1) {
            return BlobGetInternalUuidResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BlobGetInternalUuidResponseParams.DEFAULT_STRUCT_INFO).encode(this.uuid, 8, false);
        }
    }

    class BlobGetInternalUuidResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetInternalUuidResponse mCallback;

        BlobGetInternalUuidResponseParamsForwardToCallback(GetInternalUuidResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                this.mCallback.call(BlobGetInternalUuidResponseParams.deserialize(v5.getPayload()).uuid);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BlobGetInternalUuidResponseParamsProxyToResponder implements GetInternalUuidResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobGetInternalUuidResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((String)arg1));
        }

        public void call(String arg7) {
            BlobGetInternalUuidResponseParams v0 = new BlobGetInternalUuidResponseParams();
            v0.uuid = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }
    }

    final class BlobReadAllParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public BlobReaderClient client;
        public ProducerHandle pipe;

        static {
            BlobReadAllParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobReadAllParams.DEFAULT_STRUCT_INFO = BlobReadAllParams.VERSION_ARRAY[0];
        }

        public BlobReadAllParams() {
            this(0);
        }

        private BlobReadAllParams(int arg2) {
            super(24, arg2);
            this.pipe = InvalidHandle.INSTANCE;
        }

        public static BlobReadAllParams decode(Decoder arg4) {
            BlobReadAllParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BlobReadAllParams(arg4.readAndValidateDataHeader(BlobReadAllParams.VERSION_ARRAY).elementsOrVersion);
                v1.pipe = arg4.readProducerHandle(8, false);
                v1.client = arg4.readServiceInterface(12, true, BlobReaderClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BlobReadAllParams deserialize(ByteBuffer arg2) {
            return BlobReadAllParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobReadAllParams deserialize(Message arg1) {
            return BlobReadAllParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(BlobReadAllParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.pipe, 8, false);
            arg5.encode(this.client, 12, true, BlobReaderClient.MANAGER);
        }
    }

    final class BlobReadRangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public BlobReaderClient client;
        public long length;
        public long offset;
        public ProducerHandle pipe;

        static {
            BlobReadRangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            BlobReadRangeParams.DEFAULT_STRUCT_INFO = BlobReadRangeParams.VERSION_ARRAY[0];
        }

        public BlobReadRangeParams() {
            this(0);
        }

        private BlobReadRangeParams(int arg2) {
            super(40, arg2);
            this.pipe = InvalidHandle.INSTANCE;
        }

        public static BlobReadRangeParams decode(Decoder arg4) {
            BlobReadRangeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BlobReadRangeParams(arg4.readAndValidateDataHeader(BlobReadRangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.offset = arg4.readLong(8);
                v1.length = arg4.readLong(16);
                v1.pipe = arg4.readProducerHandle(24, false);
                v1.client = arg4.readServiceInterface(28, true, BlobReaderClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BlobReadRangeParams deserialize(ByteBuffer arg2) {
            return BlobReadRangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobReadRangeParams deserialize(Message arg1) {
            return BlobReadRangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(BlobReadRangeParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.offset, 8);
            arg5.encode(this.length, 16);
            arg5.encode(this.pipe, 24, false);
            arg5.encode(this.client, 28, true, BlobReaderClient.MANAGER);
        }
    }

    final class BlobReadSideDataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BlobReadSideDataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BlobReadSideDataParams.DEFAULT_STRUCT_INFO = BlobReadSideDataParams.VERSION_ARRAY[0];
        }

        public BlobReadSideDataParams() {
            this(0);
        }

        private BlobReadSideDataParams(int arg2) {
            super(8, arg2);
        }

        public static BlobReadSideDataParams decode(Decoder arg2) {
            BlobReadSideDataParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BlobReadSideDataParams(arg2.readAndValidateDataHeader(BlobReadSideDataParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BlobReadSideDataParams deserialize(ByteBuffer arg2) {
            return BlobReadSideDataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobReadSideDataParams deserialize(Message arg1) {
            return BlobReadSideDataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BlobReadSideDataParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class BlobReadSideDataResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;

        static {
            BlobReadSideDataResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BlobReadSideDataResponseParams.DEFAULT_STRUCT_INFO = BlobReadSideDataResponseParams.VERSION_ARRAY[0];
        }

        public BlobReadSideDataResponseParams() {
            this(0);
        }

        private BlobReadSideDataResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BlobReadSideDataResponseParams decode(Decoder arg4) {
            BlobReadSideDataResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BlobReadSideDataResponseParams(arg4.readAndValidateDataHeader(BlobReadSideDataResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.data = arg4.readBytes(8, 1, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BlobReadSideDataResponseParams deserialize(ByteBuffer arg2) {
            return BlobReadSideDataResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobReadSideDataResponseParams deserialize(Message arg1) {
            return BlobReadSideDataResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(BlobReadSideDataResponseParams.DEFAULT_STRUCT_INFO).encode(this.data, 8, 1, -1);
        }
    }

    class BlobReadSideDataResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadSideDataResponse mCallback;

        BlobReadSideDataResponseParamsForwardToCallback(ReadSideDataResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call(BlobReadSideDataResponseParams.deserialize(v5.getPayload()).data);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BlobReadSideDataResponseParamsProxyToResponder implements ReadSideDataResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BlobReadSideDataResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((byte[])arg1));
        }

        public void call(byte[] arg7) {
            BlobReadSideDataResponseParams v0 = new BlobReadSideDataResponseParams();
            v0.data = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.Blob$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void asDataPipeGetter(InterfaceRequest arg5) {
            BlobAsDataPipeGetterParams v0 = new BlobAsDataPipeGetterParams();
            v0.dataPipeGetter = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void clone(InterfaceRequest arg5) {
            BlobCloneParams v0 = new BlobCloneParams();
            v0.blob = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void getInternalUuid(GetInternalUuidResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new BlobGetInternalUuidParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new BlobGetInternalUuidResponseParamsForwardToCallback(arg9));
        }

        public void readAll(ProducerHandle arg4, BlobReaderClient arg5) {
            BlobReadAllParams v0 = new BlobReadAllParams();
            v0.pipe = arg4;
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void readRange(long arg2, long arg4, ProducerHandle arg6, BlobReaderClient arg7) {
            BlobReadRangeParams v0 = new BlobReadRangeParams();
            v0.offset = arg2;
            v0.length = arg4;
            v0.pipe = arg6;
            v0.client = arg7;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void readSideData(ReadSideDataResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new BlobReadSideDataParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new BlobReadSideDataResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, Blob arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg11) {
            try {
                ServiceMessage v11_1 = arg11.asServiceMessage();
                MessageHeader v1 = v11_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_41;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_35;
                    }
                    case 1: {
                        goto label_29;
                    }
                    case 2: {
                        goto label_22;
                    }
                    case 3: {
                        goto label_12;
                    }
                }

                return 0;
            label_35:
                this.getImpl().clone(BlobCloneParams.deserialize(v11_1.getPayload()).blob);
                return 1;
            label_22:
                BlobReadAllParams v11_2 = BlobReadAllParams.deserialize(v11_1.getPayload());
                this.getImpl().readAll(v11_2.pipe, v11_2.client);
                return 1;
            label_12:
                BlobReadRangeParams v11_3 = BlobReadRangeParams.deserialize(v11_1.getPayload());
                this.getImpl().readRange(v11_3.offset, v11_3.length, v11_3.pipe, v11_3.client);
                return 1;
            label_29:
                this.getImpl().asDataPipeGetter(BlobAsDataPipeGetterParams.deserialize(v11_1.getPayload()).dataPipeGetter);
                return 1;
            label_41:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Blob_Internal.MANAGER, v11_1);
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                int v3 = v1.getType();
                if(v3 == -1) {
                    goto label_30;
                }

                switch(v3) {
                    case 4: {
                        goto label_21;
                    }
                    case 5: {
                        goto label_12;
                    }
                }

                return 0;
            label_21:
                BlobReadSideDataParams.deserialize(v8_1.getPayload());
                this.getImpl().readSideData(new BlobReadSideDataResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            label_12:
                BlobGetInternalUuidParams.deserialize(v8_1.getPayload());
                this.getImpl().getInternalUuid(new BlobGetInternalUuidResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            label_30:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Blob_Internal.MANAGER, v8_1, arg9);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int AS_DATA_PIPE_GETTER_ORDINAL = 1;
    private static final int CLONE_ORDINAL = 0;
    private static final int GET_INTERNAL_UUID_ORDINAL = 5;
    public static final Manager MANAGER = null;
    private static final int READ_ALL_ORDINAL = 2;
    private static final int READ_RANGE_ORDINAL = 3;
    private static final int READ_SIDE_DATA_ORDINAL = 4;

    static {
        Blob_Internal.MANAGER = new org.chromium.blink.mojom.Blob_Internal$1();
    }

    Blob_Internal() {
        super();
    }
}

