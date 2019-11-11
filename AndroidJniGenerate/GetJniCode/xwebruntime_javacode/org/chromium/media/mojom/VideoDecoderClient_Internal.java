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
import org.chromium.mojo_base.mojom.UnguessableToken;

class VideoDecoderClient_Internal {
    final class org.chromium.media.mojom.VideoDecoderClient_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoDecoderClient_Internal$1() {
            super();
        }

        public VideoDecoderClient[] buildArray(int arg1) {
            return new VideoDecoderClient[arg1];
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

        public Stub buildStub(Core arg2, VideoDecoderClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoDecoderClient)arg2));
        }

        public String getName() {
            return "media::mojom::VideoDecoderClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoDecoderClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onVideoFrameDecoded(VideoFrame arg3, boolean arg4, UnguessableToken arg5) {
            VideoDecoderClientOnVideoFrameDecodedParams v0 = new VideoDecoderClientOnVideoFrameDecodedParams();
            v0.frame = arg3;
            v0.canReadWithoutStalling = arg4;
            v0.releaseToken = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void requestOverlayInfo(boolean arg5) {
            VideoDecoderClientRequestOverlayInfoParams v0 = new VideoDecoderClientRequestOverlayInfoParams();
            v0.restartForTransitions = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoDecoderClient arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6_1 = arg6.asServiceMessage();
                MessageHeader v1 = v6_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_26;
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
                VideoDecoderClientOnVideoFrameDecodedParams v6_2 = VideoDecoderClientOnVideoFrameDecodedParams.deserialize(v6_1.getPayload());
                this.getImpl().onVideoFrameDecoded(v6_2.frame, v6_2.canReadWithoutStalling, v6_2.releaseToken);
                return 1;
            label_12:
                this.getImpl().requestOverlayInfo(VideoDecoderClientRequestOverlayInfoParams.deserialize(v6_1.getPayload()).restartForTransitions);
                return 1;
            label_26:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoDecoderClient_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoDecoderClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class VideoDecoderClientOnVideoFrameDecodedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean canReadWithoutStalling;
        public VideoFrame frame;
        public UnguessableToken releaseToken;

        static {
            VideoDecoderClientOnVideoFrameDecodedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            VideoDecoderClientOnVideoFrameDecodedParams.DEFAULT_STRUCT_INFO = VideoDecoderClientOnVideoFrameDecodedParams.VERSION_ARRAY[0];
        }

        public VideoDecoderClientOnVideoFrameDecodedParams() {
            this(0);
        }

        private VideoDecoderClientOnVideoFrameDecodedParams(int arg2) {
            super(0x20, arg2);
        }

        public static VideoDecoderClientOnVideoFrameDecodedParams decode(Decoder arg3) {
            VideoDecoderClientOnVideoFrameDecodedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecoderClientOnVideoFrameDecodedParams(arg3.readAndValidateDataHeader(VideoDecoderClientOnVideoFrameDecodedParams.VERSION_ARRAY).elementsOrVersion);
                v1.frame = VideoFrame.decode(arg3.readPointer(8, false));
                v1.canReadWithoutStalling = arg3.readBoolean(16, 0);
                v1.releaseToken = UnguessableToken.decode(arg3.readPointer(24, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderClientOnVideoFrameDecodedParams deserialize(ByteBuffer arg2) {
            return VideoDecoderClientOnVideoFrameDecodedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderClientOnVideoFrameDecodedParams deserialize(Message arg1) {
            return VideoDecoderClientOnVideoFrameDecodedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoDecoderClientOnVideoFrameDecodedParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.frame, 8, false);
            arg4.encode(this.canReadWithoutStalling, 16, 0);
            arg4.encode(this.releaseToken, 24, true);
        }
    }

    final class VideoDecoderClientRequestOverlayInfoParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean restartForTransitions;

        static {
            VideoDecoderClientRequestOverlayInfoParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecoderClientRequestOverlayInfoParams.DEFAULT_STRUCT_INFO = VideoDecoderClientRequestOverlayInfoParams.VERSION_ARRAY[0];
        }

        public VideoDecoderClientRequestOverlayInfoParams() {
            this(0);
        }

        private VideoDecoderClientRequestOverlayInfoParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecoderClientRequestOverlayInfoParams decode(Decoder arg3) {
            VideoDecoderClientRequestOverlayInfoParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecoderClientRequestOverlayInfoParams(arg3.readAndValidateDataHeader(VideoDecoderClientRequestOverlayInfoParams.VERSION_ARRAY).elementsOrVersion);
                v1.restartForTransitions = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderClientRequestOverlayInfoParams deserialize(ByteBuffer arg2) {
            return VideoDecoderClientRequestOverlayInfoParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderClientRequestOverlayInfoParams deserialize(Message arg1) {
            return VideoDecoderClientRequestOverlayInfoParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoDecoderClientRequestOverlayInfoParams.DEFAULT_STRUCT_INFO).encode(this.restartForTransitions, 8, 0);
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_VIDEO_FRAME_DECODED_ORDINAL = 0;
    private static final int REQUEST_OVERLAY_INFO_ORDINAL = 1;

    static {
        VideoDecoderClient_Internal.MANAGER = new org.chromium.media.mojom.VideoDecoderClient_Internal$1();
    }

    VideoDecoderClient_Internal() {
        super();
    }
}

