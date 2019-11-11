package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gpu.mojom.SyncToken;
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

class VideoFrameHandleReleaser_Internal {
    final class org.chromium.media.mojom.VideoFrameHandleReleaser_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoFrameHandleReleaser_Internal$1() {
            super();
        }

        public VideoFrameHandleReleaser[] buildArray(int arg1) {
            return new VideoFrameHandleReleaser[arg1];
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

        public Stub buildStub(Core arg2, VideoFrameHandleReleaser arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoFrameHandleReleaser)arg2));
        }

        public String getName() {
            return "media::mojom::VideoFrameHandleReleaser";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoFrameHandleReleaser$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void releaseVideoFrame(UnguessableToken arg4, SyncToken arg5) {
            VideoFrameHandleReleaserReleaseVideoFrameParams v0 = new VideoFrameHandleReleaserReleaseVideoFrameParams();
            v0.releaseToken = arg4;
            v0.releaseSyncToken = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoFrameHandleReleaser arg2) {
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
                    if(v1_1 != 0) {
                        return 0;
                    }

                    VideoFrameHandleReleaserReleaseVideoFrameParams v4_2 = VideoFrameHandleReleaserReleaseVideoFrameParams.deserialize(v4_1.getPayload());
                    this.getImpl().releaseVideoFrame(v4_2.releaseToken, v4_2.releaseSyncToken);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoFrameHandleReleaser_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoFrameHandleReleaser_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class VideoFrameHandleReleaserReleaseVideoFrameParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public SyncToken releaseSyncToken;
        public UnguessableToken releaseToken;

        static {
            VideoFrameHandleReleaserReleaseVideoFrameParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            VideoFrameHandleReleaserReleaseVideoFrameParams.DEFAULT_STRUCT_INFO = VideoFrameHandleReleaserReleaseVideoFrameParams.VERSION_ARRAY[0];
        }

        public VideoFrameHandleReleaserReleaseVideoFrameParams() {
            this(0);
        }

        private VideoFrameHandleReleaserReleaseVideoFrameParams(int arg2) {
            super(24, arg2);
        }

        public static VideoFrameHandleReleaserReleaseVideoFrameParams decode(Decoder arg3) {
            VideoFrameHandleReleaserReleaseVideoFrameParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoFrameHandleReleaserReleaseVideoFrameParams(arg3.readAndValidateDataHeader(VideoFrameHandleReleaserReleaseVideoFrameParams.VERSION_ARRAY).elementsOrVersion);
                v1.releaseToken = UnguessableToken.decode(arg3.readPointer(8, false));
                v1.releaseSyncToken = SyncToken.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoFrameHandleReleaserReleaseVideoFrameParams deserialize(ByteBuffer arg2) {
            return VideoFrameHandleReleaserReleaseVideoFrameParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoFrameHandleReleaserReleaseVideoFrameParams deserialize(Message arg1) {
            return VideoFrameHandleReleaserReleaseVideoFrameParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoFrameHandleReleaserReleaseVideoFrameParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.releaseToken, 8, false);
            arg4.encode(this.releaseSyncToken, 16, false);
        }
    }

    public static final Manager MANAGER;
    private static final int RELEASE_VIDEO_FRAME_ORDINAL;

    static {
        VideoFrameHandleReleaser_Internal.MANAGER = new org.chromium.media.mojom.VideoFrameHandleReleaser_Internal$1();
    }

    VideoFrameHandleReleaser_Internal() {
        super();
    }
}

