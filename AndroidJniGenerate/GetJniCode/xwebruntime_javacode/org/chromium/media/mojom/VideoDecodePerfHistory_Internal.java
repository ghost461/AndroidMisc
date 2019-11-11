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

class VideoDecodePerfHistory_Internal {
    final class org.chromium.media.mojom.VideoDecodePerfHistory_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoDecodePerfHistory_Internal$1() {
            super();
        }

        public VideoDecodePerfHistory[] buildArray(int arg1) {
            return new VideoDecodePerfHistory[arg1];
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

        public Stub buildStub(Core arg2, VideoDecodePerfHistory arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoDecodePerfHistory)arg2));
        }

        public String getName() {
            return "media::mojom::VideoDecodePerfHistory";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoDecodePerfHistory$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getPerfInfo(PredictionFeatures arg8, GetPerfInfoResponse arg9) {
            VideoDecodePerfHistoryGetPerfInfoParams v0 = new VideoDecodePerfHistoryGetPerfInfoParams();
            v0.features = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new VideoDecodePerfHistoryGetPerfInfoResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoDecodePerfHistory arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoDecodePerfHistory_Internal.MANAGER, v4_1);
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
                        goto label_20;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoDecodePerfHistory_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().getPerfInfo(VideoDecodePerfHistoryGetPerfInfoParams.deserialize(v9_1.getPayload()).features, new VideoDecodePerfHistoryGetPerfInfoResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    final class VideoDecodePerfHistoryGetPerfInfoParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PredictionFeatures features;

        static {
            VideoDecodePerfHistoryGetPerfInfoParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecodePerfHistoryGetPerfInfoParams.DEFAULT_STRUCT_INFO = VideoDecodePerfHistoryGetPerfInfoParams.VERSION_ARRAY[0];
        }

        public VideoDecodePerfHistoryGetPerfInfoParams() {
            this(0);
        }

        private VideoDecodePerfHistoryGetPerfInfoParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecodePerfHistoryGetPerfInfoParams decode(Decoder arg3) {
            VideoDecodePerfHistoryGetPerfInfoParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecodePerfHistoryGetPerfInfoParams(arg3.readAndValidateDataHeader(VideoDecodePerfHistoryGetPerfInfoParams.VERSION_ARRAY).elementsOrVersion);
                v1.features = PredictionFeatures.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecodePerfHistoryGetPerfInfoParams deserialize(ByteBuffer arg2) {
            return VideoDecodePerfHistoryGetPerfInfoParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecodePerfHistoryGetPerfInfoParams deserialize(Message arg1) {
            return VideoDecodePerfHistoryGetPerfInfoParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoDecodePerfHistoryGetPerfInfoParams.DEFAULT_STRUCT_INFO).encode(this.features, 8, false);
        }
    }

    final class VideoDecodePerfHistoryGetPerfInfoResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isPowerEfficient;
        public boolean isSmooth;

        static {
            VideoDecodePerfHistoryGetPerfInfoResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecodePerfHistoryGetPerfInfoResponseParams.DEFAULT_STRUCT_INFO = VideoDecodePerfHistoryGetPerfInfoResponseParams.VERSION_ARRAY[0];
        }

        public VideoDecodePerfHistoryGetPerfInfoResponseParams() {
            this(0);
        }

        private VideoDecodePerfHistoryGetPerfInfoResponseParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecodePerfHistoryGetPerfInfoResponseParams decode(Decoder arg3) {
            VideoDecodePerfHistoryGetPerfInfoResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecodePerfHistoryGetPerfInfoResponseParams(arg3.readAndValidateDataHeader(VideoDecodePerfHistoryGetPerfInfoResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.isSmooth = arg3.readBoolean(8, 0);
                v1.isPowerEfficient = arg3.readBoolean(8, 1);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecodePerfHistoryGetPerfInfoResponseParams deserialize(ByteBuffer arg2) {
            return VideoDecodePerfHistoryGetPerfInfoResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecodePerfHistoryGetPerfInfoResponseParams deserialize(Message arg1) {
            return VideoDecodePerfHistoryGetPerfInfoResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoDecodePerfHistoryGetPerfInfoResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.isSmooth, 8, 0);
            arg4.encode(this.isPowerEfficient, 8, 1);
        }
    }

    class VideoDecodePerfHistoryGetPerfInfoResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetPerfInfoResponse mCallback;

        VideoDecodePerfHistoryGetPerfInfoResponseParamsForwardToCallback(GetPerfInfoResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                VideoDecodePerfHistoryGetPerfInfoResponseParams v4_1 = VideoDecodePerfHistoryGetPerfInfoResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Boolean.valueOf(v4_1.isSmooth), Boolean.valueOf(v4_1.isPowerEfficient));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class VideoDecodePerfHistoryGetPerfInfoResponseParamsProxyToResponder implements GetPerfInfoResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VideoDecodePerfHistoryGetPerfInfoResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg6, Boolean arg7) {
            VideoDecodePerfHistoryGetPerfInfoResponseParams v0 = new VideoDecodePerfHistoryGetPerfInfoResponseParams();
            v0.isSmooth = arg6.booleanValue();
            v0.isPowerEfficient = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Boolean)arg1), ((Boolean)arg2));
        }
    }

    private static final int GET_PERF_INFO_ORDINAL;
    public static final Manager MANAGER;

    static {
        VideoDecodePerfHistory_Internal.MANAGER = new org.chromium.media.mojom.VideoDecodePerfHistory_Internal$1();
    }

    VideoDecodePerfHistory_Internal() {
        super();
    }
}

