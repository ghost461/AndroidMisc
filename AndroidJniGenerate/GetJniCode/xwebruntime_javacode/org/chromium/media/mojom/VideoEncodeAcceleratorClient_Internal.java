package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Size;
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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo_base.mojom.TimeDelta;

class VideoEncodeAcceleratorClient_Internal {
    final class org.chromium.media.mojom.VideoEncodeAcceleratorClient_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoEncodeAcceleratorClient_Internal$1() {
            super();
        }

        public VideoEncodeAcceleratorClient[] buildArray(int arg1) {
            return new VideoEncodeAcceleratorClient[arg1];
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

        public Stub buildStub(Core arg2, VideoEncodeAcceleratorClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoEncodeAcceleratorClient)arg2));
        }

        public String getName() {
            return "media::mojom::VideoEncodeAcceleratorClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoEncodeAcceleratorClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void bitstreamBufferReady(int arg2, int arg3, boolean arg4, TimeDelta arg5) {
            VideoEncodeAcceleratorClientBitstreamBufferReadyParams v0 = new VideoEncodeAcceleratorClientBitstreamBufferReadyParams();
            v0.bitstreamBufferId = arg2;
            v0.payloadSize = arg3;
            v0.keyFrame = arg4;
            v0.timestamp = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void notifyError(int arg5) {
            VideoEncodeAcceleratorClientNotifyErrorParams v0 = new VideoEncodeAcceleratorClientNotifyErrorParams();
            v0.error = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void requireBitstreamBuffers(int arg3, Size arg4, int arg5) {
            VideoEncodeAcceleratorClientRequireBitstreamBuffersParams v0 = new VideoEncodeAcceleratorClientRequireBitstreamBuffersParams();
            v0.inputCount = arg3;
            v0.inputCodedSize = arg4;
            v0.outputBufferSize = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoEncodeAcceleratorClient arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_35;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_27;
                    }
                    case 1: {
                        goto label_18;
                    }
                    case 2: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                VideoEncodeAcceleratorClientBitstreamBufferReadyParams v7_2 = VideoEncodeAcceleratorClientBitstreamBufferReadyParams.deserialize(v7_1.getPayload());
                this.getImpl().bitstreamBufferReady(v7_2.bitstreamBufferId, v7_2.payloadSize, v7_2.keyFrame, v7_2.timestamp);
                return 1;
            label_27:
                VideoEncodeAcceleratorClientRequireBitstreamBuffersParams v7_3 = VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.deserialize(v7_1.getPayload());
                this.getImpl().requireBitstreamBuffers(v7_3.inputCount, v7_3.inputCodedSize, v7_3.outputBufferSize);
                return 1;
            label_12:
                this.getImpl().notifyError(VideoEncodeAcceleratorClientNotifyErrorParams.deserialize(v7_1.getPayload()).error);
                return 1;
            label_35:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoEncodeAcceleratorClient_Internal.MANAGER, v7_1);
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg4, MessageReceiver arg5) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                if(v1.getType() != -1) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoEncodeAcceleratorClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class VideoEncodeAcceleratorClientBitstreamBufferReadyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int bitstreamBufferId;
        public boolean keyFrame;
        public int payloadSize;
        public TimeDelta timestamp;

        static {
            VideoEncodeAcceleratorClientBitstreamBufferReadyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            VideoEncodeAcceleratorClientBitstreamBufferReadyParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorClientBitstreamBufferReadyParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorClientBitstreamBufferReadyParams() {
            this(0);
        }

        private VideoEncodeAcceleratorClientBitstreamBufferReadyParams(int arg2) {
            super(0x20, arg2);
        }

        public static VideoEncodeAcceleratorClientBitstreamBufferReadyParams decode(Decoder arg3) {
            VideoEncodeAcceleratorClientBitstreamBufferReadyParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorClientBitstreamBufferReadyParams(arg3.readAndValidateDataHeader(VideoEncodeAcceleratorClientBitstreamBufferReadyParams.VERSION_ARRAY).elementsOrVersion);
                v1.bitstreamBufferId = arg3.readInt(8);
                v1.payloadSize = arg3.readInt(12);
                v1.keyFrame = arg3.readBoolean(16, 0);
                v1.timestamp = TimeDelta.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorClientBitstreamBufferReadyParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorClientBitstreamBufferReadyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorClientBitstreamBufferReadyParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorClientBitstreamBufferReadyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoEncodeAcceleratorClientBitstreamBufferReadyParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.bitstreamBufferId, 8);
            arg4.encode(this.payloadSize, 12);
            arg4.encode(this.keyFrame, 16, 0);
            arg4.encode(this.timestamp, 24, false);
        }
    }

    final class VideoEncodeAcceleratorClientNotifyErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;

        static {
            VideoEncodeAcceleratorClientNotifyErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoEncodeAcceleratorClientNotifyErrorParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorClientNotifyErrorParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorClientNotifyErrorParams() {
            this(0);
        }

        private VideoEncodeAcceleratorClientNotifyErrorParams(int arg2) {
            super(16, arg2);
        }

        public static VideoEncodeAcceleratorClientNotifyErrorParams decode(Decoder arg2) {
            VideoEncodeAcceleratorClientNotifyErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorClientNotifyErrorParams(arg2.readAndValidateDataHeader(VideoEncodeAcceleratorClientNotifyErrorParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg2.readInt(8);
                Error.validate(v1.error);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorClientNotifyErrorParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorClientNotifyErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorClientNotifyErrorParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorClientNotifyErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(VideoEncodeAcceleratorClientNotifyErrorParams.DEFAULT_STRUCT_INFO).encode(this.error, 8);
        }
    }

    final class VideoEncodeAcceleratorClientRequireBitstreamBuffersParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Size inputCodedSize;
        public int inputCount;
        public int outputBufferSize;

        static {
            VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorClientRequireBitstreamBuffersParams() {
            this(0);
        }

        private VideoEncodeAcceleratorClientRequireBitstreamBuffersParams(int arg2) {
            super(24, arg2);
        }

        public static VideoEncodeAcceleratorClientRequireBitstreamBuffersParams decode(Decoder arg3) {
            VideoEncodeAcceleratorClientRequireBitstreamBuffersParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorClientRequireBitstreamBuffersParams(arg3.readAndValidateDataHeader(VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.VERSION_ARRAY).elementsOrVersion);
                v1.inputCount = arg3.readInt(8);
                v1.outputBufferSize = arg3.readInt(12);
                v1.inputCodedSize = Size.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorClientRequireBitstreamBuffersParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorClientRequireBitstreamBuffersParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoEncodeAcceleratorClientRequireBitstreamBuffersParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.inputCount, 8);
            arg4.encode(this.outputBufferSize, 12);
            arg4.encode(this.inputCodedSize, 16, false);
        }
    }

    private static final int BITSTREAM_BUFFER_READY_ORDINAL = 1;
    public static final Manager MANAGER = null;
    private static final int NOTIFY_ERROR_ORDINAL = 2;
    private static final int REQUIRE_BITSTREAM_BUFFERS_ORDINAL;

    static {
        VideoEncodeAcceleratorClient_Internal.MANAGER = new org.chromium.media.mojom.VideoEncodeAcceleratorClient_Internal$1();
    }

    VideoEncodeAcceleratorClient_Internal() {
        super();
    }
}

