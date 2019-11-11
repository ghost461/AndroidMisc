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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class VideoEncodeAcceleratorProvider_Internal {
    final class org.chromium.media.mojom.VideoEncodeAcceleratorProvider_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoEncodeAcceleratorProvider_Internal$1() {
            super();
        }

        public VideoEncodeAcceleratorProvider[] buildArray(int arg1) {
            return new VideoEncodeAcceleratorProvider[arg1];
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

        public Stub buildStub(Core arg2, VideoEncodeAcceleratorProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoEncodeAcceleratorProvider)arg2));
        }

        public String getName() {
            return "media::mojom::VideoEncodeAcceleratorProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoEncodeAcceleratorProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createVideoEncodeAccelerator(InterfaceRequest arg5) {
            VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams v0 = new VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams();
            v0.request = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoEncodeAcceleratorProvider arg2) {
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

                    this.getImpl().createVideoEncodeAccelerator(VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.deserialize(v4_1.getPayload()).request);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoEncodeAcceleratorProvider_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoEncodeAcceleratorProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest request;

        static {
            VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams() {
            this(0);
        }

        private VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams(int arg2) {
            super(16, arg2);
        }

        public static VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams decode(Decoder arg3) {
            VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams(arg3.readAndValidateDataHeader(VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.VERSION_ARRAY).elementsOrVersion);
                v1.request = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoEncodeAcceleratorProviderCreateVideoEncodeAcceleratorParams.DEFAULT_STRUCT_INFO).encode(this.request, 8, false);
        }
    }

    private static final int CREATE_VIDEO_ENCODE_ACCELERATOR_ORDINAL;
    public static final Manager MANAGER;

    static {
        VideoEncodeAcceleratorProvider_Internal.MANAGER = new org.chromium.media.mojom.VideoEncodeAcceleratorProvider_Internal$1();
    }

    VideoEncodeAcceleratorProvider_Internal() {
        super();
    }
}

