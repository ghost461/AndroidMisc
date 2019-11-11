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
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.InvalidHandle;

class ChunkedDataPipeGetter_Internal {
    final class org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$1 extends Manager {
        org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ChunkedDataPipeGetter[] buildArray(int arg1) {
            return new ChunkedDataPipeGetter[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ChunkedDataPipeGetter)arg2));
        }

        public org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Stub buildStub(Core arg2, ChunkedDataPipeGetter arg3) {
            return new org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::ChunkedDataPipeGetter";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ChunkedDataPipeGetterGetSizeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ChunkedDataPipeGetterGetSizeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ChunkedDataPipeGetterGetSizeParams.DEFAULT_STRUCT_INFO = ChunkedDataPipeGetterGetSizeParams.VERSION_ARRAY[0];
        }

        public ChunkedDataPipeGetterGetSizeParams() {
            this(0);
        }

        private ChunkedDataPipeGetterGetSizeParams(int arg2) {
            super(8, arg2);
        }

        public static ChunkedDataPipeGetterGetSizeParams decode(Decoder arg2) {
            ChunkedDataPipeGetterGetSizeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ChunkedDataPipeGetterGetSizeParams(arg2.readAndValidateDataHeader(ChunkedDataPipeGetterGetSizeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ChunkedDataPipeGetterGetSizeParams deserialize(ByteBuffer arg2) {
            return ChunkedDataPipeGetterGetSizeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ChunkedDataPipeGetterGetSizeParams deserialize(Message arg1) {
            return ChunkedDataPipeGetterGetSizeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ChunkedDataPipeGetterGetSizeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ChunkedDataPipeGetterGetSizeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public long size;
        public int status;

        static {
            ChunkedDataPipeGetterGetSizeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ChunkedDataPipeGetterGetSizeResponseParams.DEFAULT_STRUCT_INFO = ChunkedDataPipeGetterGetSizeResponseParams.VERSION_ARRAY[0];
        }

        public ChunkedDataPipeGetterGetSizeResponseParams() {
            this(0);
        }

        private ChunkedDataPipeGetterGetSizeResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ChunkedDataPipeGetterGetSizeResponseParams decode(Decoder arg4) {
            ChunkedDataPipeGetterGetSizeResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ChunkedDataPipeGetterGetSizeResponseParams(arg4.readAndValidateDataHeader(ChunkedDataPipeGetterGetSizeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg4.readInt(8);
                v1.size = arg4.readLong(16);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ChunkedDataPipeGetterGetSizeResponseParams deserialize(ByteBuffer arg2) {
            return ChunkedDataPipeGetterGetSizeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ChunkedDataPipeGetterGetSizeResponseParams deserialize(Message arg1) {
            return ChunkedDataPipeGetterGetSizeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ChunkedDataPipeGetterGetSizeResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.size, 16);
        }
    }

    class ChunkedDataPipeGetterGetSizeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetSizeResponse mCallback;

        ChunkedDataPipeGetterGetSizeResponseParamsForwardToCallback(GetSizeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                ChunkedDataPipeGetterGetSizeResponseParams v6_1 = ChunkedDataPipeGetterGetSizeResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(Integer.valueOf(v6_1.status), Long.valueOf(v6_1.size));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ChunkedDataPipeGetterGetSizeResponseParamsProxyToResponder implements GetSizeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ChunkedDataPipeGetterGetSizeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, Long arg7) {
            ChunkedDataPipeGetterGetSizeResponseParams v0 = new ChunkedDataPipeGetterGetSizeResponseParams();
            v0.status = arg6.intValue();
            v0.size = arg7.longValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((Long)arg2));
        }
    }

    final class ChunkedDataPipeGetterStartReadingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ProducerHandle pipe;

        static {
            ChunkedDataPipeGetterStartReadingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ChunkedDataPipeGetterStartReadingParams.DEFAULT_STRUCT_INFO = ChunkedDataPipeGetterStartReadingParams.VERSION_ARRAY[0];
        }

        public ChunkedDataPipeGetterStartReadingParams() {
            this(0);
        }

        private ChunkedDataPipeGetterStartReadingParams(int arg2) {
            super(16, arg2);
            this.pipe = InvalidHandle.INSTANCE;
        }

        public static ChunkedDataPipeGetterStartReadingParams decode(Decoder arg3) {
            ChunkedDataPipeGetterStartReadingParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ChunkedDataPipeGetterStartReadingParams(arg3.readAndValidateDataHeader(ChunkedDataPipeGetterStartReadingParams.VERSION_ARRAY).elementsOrVersion);
                v1.pipe = arg3.readProducerHandle(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ChunkedDataPipeGetterStartReadingParams deserialize(ByteBuffer arg2) {
            return ChunkedDataPipeGetterStartReadingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ChunkedDataPipeGetterStartReadingParams deserialize(Message arg1) {
            return ChunkedDataPipeGetterStartReadingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ChunkedDataPipeGetterStartReadingParams.DEFAULT_STRUCT_INFO).encode(this.pipe, 8, false);
        }
    }

    final class org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.ChunkedDataPipeGetter$Proxy {
        org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getSize(GetSizeResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ChunkedDataPipeGetterGetSizeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ChunkedDataPipeGetterGetSizeResponseParamsForwardToCallback(arg9));
        }

        public void startReading(ProducerHandle arg5) {
            ChunkedDataPipeGetterStartReadingParams v0 = new ChunkedDataPipeGetterStartReadingParams();
            v0.pipe = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Stub extends Stub {
        org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$Stub(Core arg1, ChunkedDataPipeGetter arg2) {
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
                    if(v1_1 != 1) {
                        return 0;
                    }

                    this.getImpl().startReading(ChunkedDataPipeGetterStartReadingParams.deserialize(v4_1.getPayload()).pipe);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ChunkedDataPipeGetter_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
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

                switch(v1.getType()) {
                    case -1: {
                        goto label_19;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ChunkedDataPipeGetter_Internal.MANAGER, v8_1, arg9);
            label_10:
                ChunkedDataPipeGetterGetSizeParams.deserialize(v8_1.getPayload());
                this.getImpl().getSize(new ChunkedDataPipeGetterGetSizeResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int GET_SIZE_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int START_READING_ORDINAL = 1;

    static {
        ChunkedDataPipeGetter_Internal.MANAGER = new org.chromium.network.mojom.ChunkedDataPipeGetter_Internal$1();
    }

    ChunkedDataPipeGetter_Internal() {
        super();
    }
}

