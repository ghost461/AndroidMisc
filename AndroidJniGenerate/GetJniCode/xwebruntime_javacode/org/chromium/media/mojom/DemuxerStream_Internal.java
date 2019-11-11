package org.chromium.media.mojom;

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
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.InvalidHandle;

class DemuxerStream_Internal {
    final class org.chromium.media.mojom.DemuxerStream_Internal$1 extends Manager {
        org.chromium.media.mojom.DemuxerStream_Internal$1() {
            super();
        }

        public DemuxerStream[] buildArray(int arg1) {
            return new DemuxerStream[arg1];
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

        public Stub buildStub(Core arg2, DemuxerStream arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((DemuxerStream)arg2));
        }

        public String getName() {
            return "media::mojom::DemuxerStream";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class DemuxerStreamEnableBitstreamConverterParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            DemuxerStreamEnableBitstreamConverterParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            DemuxerStreamEnableBitstreamConverterParams.DEFAULT_STRUCT_INFO = DemuxerStreamEnableBitstreamConverterParams.VERSION_ARRAY[0];
        }

        public DemuxerStreamEnableBitstreamConverterParams() {
            this(0);
        }

        private DemuxerStreamEnableBitstreamConverterParams(int arg2) {
            super(8, arg2);
        }

        public static DemuxerStreamEnableBitstreamConverterParams decode(Decoder arg2) {
            DemuxerStreamEnableBitstreamConverterParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new DemuxerStreamEnableBitstreamConverterParams(arg2.readAndValidateDataHeader(DemuxerStreamEnableBitstreamConverterParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static DemuxerStreamEnableBitstreamConverterParams deserialize(ByteBuffer arg2) {
            return DemuxerStreamEnableBitstreamConverterParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DemuxerStreamEnableBitstreamConverterParams deserialize(Message arg1) {
            return DemuxerStreamEnableBitstreamConverterParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(DemuxerStreamEnableBitstreamConverterParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class DemuxerStreamInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            DemuxerStreamInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            DemuxerStreamInitializeParams.DEFAULT_STRUCT_INFO = DemuxerStreamInitializeParams.VERSION_ARRAY[0];
        }

        public DemuxerStreamInitializeParams() {
            this(0);
        }

        private DemuxerStreamInitializeParams(int arg2) {
            super(8, arg2);
        }

        public static DemuxerStreamInitializeParams decode(Decoder arg2) {
            DemuxerStreamInitializeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new DemuxerStreamInitializeParams(arg2.readAndValidateDataHeader(DemuxerStreamInitializeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static DemuxerStreamInitializeParams deserialize(ByteBuffer arg2) {
            return DemuxerStreamInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DemuxerStreamInitializeParams deserialize(Message arg1) {
            return DemuxerStreamInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(DemuxerStreamInitializeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class DemuxerStreamInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public AudioDecoderConfig audioConfig;
        public ConsumerHandle pipe;
        public int type;
        public VideoDecoderConfig videoConfig;

        static {
            DemuxerStreamInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            DemuxerStreamInitializeResponseParams.DEFAULT_STRUCT_INFO = DemuxerStreamInitializeResponseParams.VERSION_ARRAY[0];
        }

        public DemuxerStreamInitializeResponseParams() {
            this(0);
        }

        private DemuxerStreamInitializeResponseParams(int arg2) {
            super(0x20, arg2);
            this.pipe = InvalidHandle.INSTANCE;
        }

        public static DemuxerStreamInitializeResponseParams decode(Decoder arg3) {
            DemuxerStreamInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DemuxerStreamInitializeResponseParams(arg3.readAndValidateDataHeader(DemuxerStreamInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg3.readInt(8);
                Type.validate(v1.type);
                v1.pipe = arg3.readConsumerHandle(12, false);
                v1.audioConfig = AudioDecoderConfig.decode(arg3.readPointer(16, true));
                v1.videoConfig = VideoDecoderConfig.decode(arg3.readPointer(24, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DemuxerStreamInitializeResponseParams deserialize(ByteBuffer arg2) {
            return DemuxerStreamInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DemuxerStreamInitializeResponseParams deserialize(Message arg1) {
            return DemuxerStreamInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(DemuxerStreamInitializeResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.type, 8);
            arg4.encode(this.pipe, 12, false);
            arg4.encode(this.audioConfig, 16, true);
            arg4.encode(this.videoConfig, 24, true);
        }
    }

    class DemuxerStreamInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        DemuxerStreamInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                DemuxerStreamInitializeResponseParams v6_1 = DemuxerStreamInitializeResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(Integer.valueOf(v6_1.type), v6_1.pipe, v6_1.audioConfig, v6_1.videoConfig);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class DemuxerStreamInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        DemuxerStreamInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg4, ConsumerHandle arg5, AudioDecoderConfig arg6, VideoDecoderConfig arg7) {
            DemuxerStreamInitializeResponseParams v0 = new DemuxerStreamInitializeResponseParams();
            v0.type = arg4.intValue();
            v0.pipe = arg5;
            v0.audioConfig = arg6;
            v0.videoConfig = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3, Object arg4) {
            this.call(((Integer)arg1), ((ConsumerHandle)arg2), ((AudioDecoderConfig)arg3), ((VideoDecoderConfig)arg4));
        }
    }

    final class DemuxerStreamReadParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            DemuxerStreamReadParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            DemuxerStreamReadParams.DEFAULT_STRUCT_INFO = DemuxerStreamReadParams.VERSION_ARRAY[0];
        }

        public DemuxerStreamReadParams() {
            this(0);
        }

        private DemuxerStreamReadParams(int arg2) {
            super(8, arg2);
        }

        public static DemuxerStreamReadParams decode(Decoder arg2) {
            DemuxerStreamReadParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new DemuxerStreamReadParams(arg2.readAndValidateDataHeader(DemuxerStreamReadParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static DemuxerStreamReadParams deserialize(ByteBuffer arg2) {
            return DemuxerStreamReadParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DemuxerStreamReadParams deserialize(Message arg1) {
            return DemuxerStreamReadParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(DemuxerStreamReadParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class DemuxerStreamReadResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public AudioDecoderConfig audioConfig;
        public DecoderBuffer buffer;
        public int status;
        public VideoDecoderConfig videoConfig;

        static {
            DemuxerStreamReadResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            DemuxerStreamReadResponseParams.DEFAULT_STRUCT_INFO = DemuxerStreamReadResponseParams.VERSION_ARRAY[0];
        }

        public DemuxerStreamReadResponseParams() {
            this(0);
        }

        private DemuxerStreamReadResponseParams(int arg2) {
            super(40, arg2);
        }

        public static DemuxerStreamReadResponseParams decode(Decoder arg3) {
            DemuxerStreamReadResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DemuxerStreamReadResponseParams(arg3.readAndValidateDataHeader(DemuxerStreamReadResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg3.readInt(8);
                Status.validate(v1.status);
                v1.buffer = DecoderBuffer.decode(arg3.readPointer(16, true));
                v1.audioConfig = AudioDecoderConfig.decode(arg3.readPointer(24, true));
                v1.videoConfig = VideoDecoderConfig.decode(arg3.readPointer(0x20, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DemuxerStreamReadResponseParams deserialize(ByteBuffer arg2) {
            return DemuxerStreamReadResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DemuxerStreamReadResponseParams deserialize(Message arg1) {
            return DemuxerStreamReadResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(DemuxerStreamReadResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.buffer, 16, true);
            arg4.encode(this.audioConfig, 24, true);
            arg4.encode(this.videoConfig, 0x20, true);
        }
    }

    class DemuxerStreamReadResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadResponse mCallback;

        DemuxerStreamReadResponseParamsForwardToCallback(ReadResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7 = arg7.asServiceMessage();
                if(!v7.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                DemuxerStreamReadResponseParams v7_1 = DemuxerStreamReadResponseParams.deserialize(v7.getPayload());
                this.mCallback.call(Integer.valueOf(v7_1.status), v7_1.buffer, v7_1.audioConfig, v7_1.videoConfig);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class DemuxerStreamReadResponseParamsProxyToResponder implements ReadResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        DemuxerStreamReadResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg4, DecoderBuffer arg5, AudioDecoderConfig arg6, VideoDecoderConfig arg7) {
            DemuxerStreamReadResponseParams v0 = new DemuxerStreamReadResponseParams();
            v0.status = arg4.intValue();
            v0.buffer = arg5;
            v0.audioConfig = arg6;
            v0.videoConfig = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3, Object arg4) {
            this.call(((Integer)arg1), ((DecoderBuffer)arg2), ((AudioDecoderConfig)arg3), ((VideoDecoderConfig)arg4));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.DemuxerStream$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void enableBitstreamConverter() {
            this.getProxyHandler().getMessageReceiver().accept(new DemuxerStreamEnableBitstreamConverterParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void initialize(InitializeResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new DemuxerStreamInitializeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new DemuxerStreamInitializeResponseParamsForwardToCallback(arg9));
        }

        public void read(ReadResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new DemuxerStreamReadParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new DemuxerStreamReadResponseParamsForwardToCallback(arg8));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, DemuxerStream arg2) {
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
                    if(v1_1 != 2) {
                        return 0;
                    }

                    DemuxerStreamEnableBitstreamConverterParams.deserialize(v4_1.getPayload());
                    this.getImpl().enableBitstreamConverter();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(DemuxerStream_Internal.MANAGER, v4_1);
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
                        goto label_28;
                    }
                    case 0: {
                        goto label_19;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                DemuxerStreamInitializeParams.deserialize(v8_1.getPayload());
                this.getImpl().initialize(new DemuxerStreamInitializeResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            label_10:
                DemuxerStreamReadParams.deserialize(v8_1.getPayload());
                this.getImpl().read(new DemuxerStreamReadResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            label_28:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), DemuxerStream_Internal.MANAGER, v8_1, arg9);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int ENABLE_BITSTREAM_CONVERTER_ORDINAL = 2;
    private static final int INITIALIZE_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int READ_ORDINAL = 1;

    static {
        DemuxerStream_Internal.MANAGER = new org.chromium.media.mojom.DemuxerStream_Internal$1();
    }

    DemuxerStream_Internal() {
        super();
    }
}

