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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class VideoDecodeStatsRecorder_Internal {
    final class org.chromium.media.mojom.VideoDecodeStatsRecorder_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoDecodeStatsRecorder_Internal$1() {
            super();
        }

        public VideoDecodeStatsRecorder[] buildArray(int arg1) {
            return new VideoDecodeStatsRecorder[arg1];
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

        public Stub buildStub(Core arg2, VideoDecodeStatsRecorder arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoDecodeStatsRecorder)arg2));
        }

        public String getName() {
            return "media::mojom::VideoDecodeStatsRecorder";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoDecodeStatsRecorder$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void startNewRecord(PredictionFeatures arg5) {
            VideoDecodeStatsRecorderStartNewRecordParams v0 = new VideoDecodeStatsRecorderStartNewRecordParams();
            v0.features = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void updateRecord(PredictionTargets arg5) {
            VideoDecodeStatsRecorderUpdateRecordParams v0 = new VideoDecodeStatsRecorderUpdateRecordParams();
            v0.targets = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoDecodeStatsRecorder arg2) {
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
                if(v1_1 == -2) {
                    goto label_24;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_18;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().startNewRecord(VideoDecodeStatsRecorderStartNewRecordParams.deserialize(v4_1.getPayload()).features);
                return 1;
            label_12:
                this.getImpl().updateRecord(VideoDecodeStatsRecorderUpdateRecordParams.deserialize(v4_1.getPayload()).targets);
                return 1;
            label_24:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoDecodeStatsRecorder_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoDecodeStatsRecorder_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class VideoDecodeStatsRecorderStartNewRecordParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PredictionFeatures features;

        static {
            VideoDecodeStatsRecorderStartNewRecordParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecodeStatsRecorderStartNewRecordParams.DEFAULT_STRUCT_INFO = VideoDecodeStatsRecorderStartNewRecordParams.VERSION_ARRAY[0];
        }

        public VideoDecodeStatsRecorderStartNewRecordParams() {
            this(0);
        }

        private VideoDecodeStatsRecorderStartNewRecordParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecodeStatsRecorderStartNewRecordParams decode(Decoder arg3) {
            VideoDecodeStatsRecorderStartNewRecordParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecodeStatsRecorderStartNewRecordParams(arg3.readAndValidateDataHeader(VideoDecodeStatsRecorderStartNewRecordParams.VERSION_ARRAY).elementsOrVersion);
                v1.features = PredictionFeatures.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecodeStatsRecorderStartNewRecordParams deserialize(ByteBuffer arg2) {
            return VideoDecodeStatsRecorderStartNewRecordParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecodeStatsRecorderStartNewRecordParams deserialize(Message arg1) {
            return VideoDecodeStatsRecorderStartNewRecordParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoDecodeStatsRecorderStartNewRecordParams.DEFAULT_STRUCT_INFO).encode(this.features, 8, false);
        }
    }

    final class VideoDecodeStatsRecorderUpdateRecordParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PredictionTargets targets;

        static {
            VideoDecodeStatsRecorderUpdateRecordParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecodeStatsRecorderUpdateRecordParams.DEFAULT_STRUCT_INFO = VideoDecodeStatsRecorderUpdateRecordParams.VERSION_ARRAY[0];
        }

        public VideoDecodeStatsRecorderUpdateRecordParams() {
            this(0);
        }

        private VideoDecodeStatsRecorderUpdateRecordParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecodeStatsRecorderUpdateRecordParams decode(Decoder arg3) {
            VideoDecodeStatsRecorderUpdateRecordParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecodeStatsRecorderUpdateRecordParams(arg3.readAndValidateDataHeader(VideoDecodeStatsRecorderUpdateRecordParams.VERSION_ARRAY).elementsOrVersion);
                v1.targets = PredictionTargets.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecodeStatsRecorderUpdateRecordParams deserialize(ByteBuffer arg2) {
            return VideoDecodeStatsRecorderUpdateRecordParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecodeStatsRecorderUpdateRecordParams deserialize(Message arg1) {
            return VideoDecodeStatsRecorderUpdateRecordParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoDecodeStatsRecorderUpdateRecordParams.DEFAULT_STRUCT_INFO).encode(this.targets, 8, false);
        }
    }

    public static final Manager MANAGER = null;
    private static final int START_NEW_RECORD_ORDINAL = 0;
    private static final int UPDATE_RECORD_ORDINAL = 1;

    static {
        VideoDecodeStatsRecorder_Internal.MANAGER = new org.chromium.media.mojom.VideoDecodeStatsRecorder_Internal$1();
    }

    VideoDecodeStatsRecorder_Internal() {
        super();
    }
}

