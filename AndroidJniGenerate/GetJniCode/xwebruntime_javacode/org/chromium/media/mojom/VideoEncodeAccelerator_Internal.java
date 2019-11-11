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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;

class VideoEncodeAccelerator_Internal {
    final class org.chromium.media.mojom.VideoEncodeAccelerator_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoEncodeAccelerator_Internal$1() {
            super();
        }

        public VideoEncodeAccelerator[] buildArray(int arg1) {
            return new VideoEncodeAccelerator[arg1];
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

        public Stub buildStub(Core arg2, VideoEncodeAccelerator arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoEncodeAccelerator)arg2));
        }

        public String getName() {
            return "media::mojom::VideoEncodeAccelerator";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoEncodeAccelerator$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void encode(VideoFrame arg6, boolean arg7, EncodeResponse arg8) {
            VideoEncodeAcceleratorEncodeParams v0 = new VideoEncodeAcceleratorEncodeParams();
            v0.frame = arg6;
            v0.forceKeyframe = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new VideoEncodeAcceleratorEncodeResponseParamsForwardToCallback(arg8));
        }

        public void initialize(int arg4, Size arg5, int arg6, int arg7, VideoEncodeAcceleratorClient arg8, InitializeResponse arg9) {
            VideoEncodeAcceleratorInitializeParams v0 = new VideoEncodeAcceleratorInitializeParams();
            v0.inputFormat = arg4;
            v0.inputVisibleSize = arg5;
            v0.outputProfile = arg6;
            v0.initialBitrate = arg7;
            v0.client = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new VideoEncodeAcceleratorInitializeResponseParamsForwardToCallback(arg9));
        }

        public void requestEncodingParametersChange(int arg4, int arg5) {
            VideoEncodeAcceleratorRequestEncodingParametersChangeParams v0 = new VideoEncodeAcceleratorRequestEncodingParametersChangeParams();
            v0.bitrate = arg4;
            v0.framerate = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void useOutputBitstreamBuffer(int arg4, SharedBufferHandle arg5) {
            VideoEncodeAcceleratorUseOutputBitstreamBufferParams v0 = new VideoEncodeAcceleratorUseOutputBitstreamBufferParams();
            v0.bitstreamBufferId = arg4;
            v0.buffer = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoEncodeAccelerator arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5_1 = arg5.asServiceMessage();
                MessageHeader v1 = v5_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_26;
                }

                switch(v1_1) {
                    case 2: {
                        goto label_19;
                    }
                    case 3: {
                        goto label_12;
                    }
                }

                return 0;
            label_19:
                VideoEncodeAcceleratorUseOutputBitstreamBufferParams v5_2 = VideoEncodeAcceleratorUseOutputBitstreamBufferParams.deserialize(v5_1.getPayload());
                this.getImpl().useOutputBitstreamBuffer(v5_2.bitstreamBufferId, v5_2.buffer);
                return 1;
            label_12:
                VideoEncodeAcceleratorRequestEncodingParametersChangeParams v5_3 = VideoEncodeAcceleratorRequestEncodingParametersChangeParams.deserialize(v5_1.getPayload());
                this.getImpl().requestEncodingParametersChange(v5_3.bitrate, v5_3.framerate);
                return 1;
            label_26:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoEncodeAccelerator_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg14, MessageReceiver arg15) {
            try {
                ServiceMessage v14_1 = arg14.asServiceMessage();
                MessageHeader v1 = v14_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_36;
                    }
                    case 0: {
                        goto label_21;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_36:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoEncodeAccelerator_Internal.MANAGER, v14_1, arg15);
            label_21:
                VideoEncodeAcceleratorInitializeParams v14_2 = VideoEncodeAcceleratorInitializeParams.deserialize(v14_1.getPayload());
                this.getImpl().initialize(v14_2.inputFormat, v14_2.inputVisibleSize, v14_2.outputProfile, v14_2.initialBitrate, v14_2.client, new VideoEncodeAcceleratorInitializeResponseParamsProxyToResponder(this.getCore(), arg15, v1.getRequestId()));
                return 1;
            label_10:
                VideoEncodeAcceleratorEncodeParams v14_3 = VideoEncodeAcceleratorEncodeParams.deserialize(v14_1.getPayload());
                this.getImpl().encode(v14_3.frame, v14_3.forceKeyframe, new VideoEncodeAcceleratorEncodeResponseParamsProxyToResponder(this.getCore(), arg15, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v14) {
                System.err.println(v14.toString());
                return 0;
            }
        }
    }

    final class VideoEncodeAcceleratorEncodeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean forceKeyframe;
        public VideoFrame frame;

        static {
            VideoEncodeAcceleratorEncodeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            VideoEncodeAcceleratorEncodeParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorEncodeParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorEncodeParams() {
            this(0);
        }

        private VideoEncodeAcceleratorEncodeParams(int arg2) {
            super(24, arg2);
        }

        public static VideoEncodeAcceleratorEncodeParams decode(Decoder arg3) {
            VideoEncodeAcceleratorEncodeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorEncodeParams(arg3.readAndValidateDataHeader(VideoEncodeAcceleratorEncodeParams.VERSION_ARRAY).elementsOrVersion);
                v1.frame = VideoFrame.decode(arg3.readPointer(8, false));
                v1.forceKeyframe = arg3.readBoolean(16, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorEncodeParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorEncodeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorEncodeParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorEncodeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoEncodeAcceleratorEncodeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.frame, 8, false);
            arg4.encode(this.forceKeyframe, 16, 0);
        }
    }

    final class VideoEncodeAcceleratorEncodeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            VideoEncodeAcceleratorEncodeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            VideoEncodeAcceleratorEncodeResponseParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorEncodeResponseParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorEncodeResponseParams() {
            this(0);
        }

        private VideoEncodeAcceleratorEncodeResponseParams(int arg2) {
            super(8, arg2);
        }

        public static VideoEncodeAcceleratorEncodeResponseParams decode(Decoder arg2) {
            VideoEncodeAcceleratorEncodeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorEncodeResponseParams(arg2.readAndValidateDataHeader(VideoEncodeAcceleratorEncodeResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorEncodeResponseParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorEncodeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorEncodeResponseParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorEncodeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(VideoEncodeAcceleratorEncodeResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class VideoEncodeAcceleratorEncodeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final EncodeResponse mCallback;

        VideoEncodeAcceleratorEncodeResponseParamsForwardToCallback(EncodeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(1, 2)) {
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

    class VideoEncodeAcceleratorEncodeResponseParamsProxyToResponder implements EncodeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VideoEncodeAcceleratorEncodeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new VideoEncodeAcceleratorEncodeResponseParams().serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class VideoEncodeAcceleratorInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public VideoEncodeAcceleratorClient client;
        public int initialBitrate;
        public int inputFormat;
        public Size inputVisibleSize;
        public int outputProfile;

        static {
            VideoEncodeAcceleratorInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            VideoEncodeAcceleratorInitializeParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorInitializeParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorInitializeParams() {
            this(0);
        }

        private VideoEncodeAcceleratorInitializeParams(int arg2) {
            super(40, arg2);
        }

        public static VideoEncodeAcceleratorInitializeParams decode(Decoder arg4) {
            VideoEncodeAcceleratorInitializeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorInitializeParams(arg4.readAndValidateDataHeader(VideoEncodeAcceleratorInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.inputFormat = arg4.readInt(8);
                VideoPixelFormat.validate(v1.inputFormat);
                v1.outputProfile = arg4.readInt(12);
                VideoCodecProfile.validate(v1.outputProfile);
                v1.inputVisibleSize = Size.decode(arg4.readPointer(16, false));
                v1.initialBitrate = arg4.readInt(24);
                v1.client = arg4.readServiceInterface(28, false, VideoEncodeAcceleratorClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorInitializeParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorInitializeParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(VideoEncodeAcceleratorInitializeParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.inputFormat, 8);
            arg5.encode(this.outputProfile, 12);
            arg5.encode(this.inputVisibleSize, 16, false);
            arg5.encode(this.initialBitrate, 24);
            arg5.encode(this.client, 28, false, VideoEncodeAcceleratorClient.MANAGER);
        }
    }

    final class VideoEncodeAcceleratorInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean result;

        static {
            VideoEncodeAcceleratorInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoEncodeAcceleratorInitializeResponseParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorInitializeResponseParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorInitializeResponseParams() {
            this(0);
        }

        private VideoEncodeAcceleratorInitializeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static VideoEncodeAcceleratorInitializeResponseParams decode(Decoder arg3) {
            VideoEncodeAcceleratorInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorInitializeResponseParams(arg3.readAndValidateDataHeader(VideoEncodeAcceleratorInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorInitializeResponseParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorInitializeResponseParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoEncodeAcceleratorInitializeResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, 0);
        }
    }

    class VideoEncodeAcceleratorInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        VideoEncodeAcceleratorInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(VideoEncodeAcceleratorInitializeResponseParams.deserialize(v4.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class VideoEncodeAcceleratorInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VideoEncodeAcceleratorInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            VideoEncodeAcceleratorInitializeResponseParams v0 = new VideoEncodeAcceleratorInitializeResponseParams();
            v0.result = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class VideoEncodeAcceleratorRequestEncodingParametersChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int bitrate;
        public int framerate;

        static {
            VideoEncodeAcceleratorRequestEncodingParametersChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoEncodeAcceleratorRequestEncodingParametersChangeParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorRequestEncodingParametersChangeParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorRequestEncodingParametersChangeParams() {
            this(0);
        }

        private VideoEncodeAcceleratorRequestEncodingParametersChangeParams(int arg2) {
            super(16, arg2);
        }

        public static VideoEncodeAcceleratorRequestEncodingParametersChangeParams decode(Decoder arg2) {
            VideoEncodeAcceleratorRequestEncodingParametersChangeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorRequestEncodingParametersChangeParams(arg2.readAndValidateDataHeader(VideoEncodeAcceleratorRequestEncodingParametersChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.bitrate = arg2.readInt(8);
                v1.framerate = arg2.readInt(12);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorRequestEncodingParametersChangeParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorRequestEncodingParametersChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorRequestEncodingParametersChangeParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorRequestEncodingParametersChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(VideoEncodeAcceleratorRequestEncodingParametersChangeParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.bitrate, 8);
            arg3.encode(this.framerate, 12);
        }
    }

    final class VideoEncodeAcceleratorUseOutputBitstreamBufferParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int bitstreamBufferId;
        public SharedBufferHandle buffer;

        static {
            VideoEncodeAcceleratorUseOutputBitstreamBufferParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoEncodeAcceleratorUseOutputBitstreamBufferParams.DEFAULT_STRUCT_INFO = VideoEncodeAcceleratorUseOutputBitstreamBufferParams.VERSION_ARRAY[0];
        }

        public VideoEncodeAcceleratorUseOutputBitstreamBufferParams() {
            this(0);
        }

        private VideoEncodeAcceleratorUseOutputBitstreamBufferParams(int arg2) {
            super(16, arg2);
            this.buffer = InvalidHandle.INSTANCE;
        }

        public static VideoEncodeAcceleratorUseOutputBitstreamBufferParams decode(Decoder arg3) {
            VideoEncodeAcceleratorUseOutputBitstreamBufferParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoEncodeAcceleratorUseOutputBitstreamBufferParams(arg3.readAndValidateDataHeader(VideoEncodeAcceleratorUseOutputBitstreamBufferParams.VERSION_ARRAY).elementsOrVersion);
                v1.bitstreamBufferId = arg3.readInt(8);
                v1.buffer = arg3.readSharedBufferHandle(12, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoEncodeAcceleratorUseOutputBitstreamBufferParams deserialize(ByteBuffer arg2) {
            return VideoEncodeAcceleratorUseOutputBitstreamBufferParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoEncodeAcceleratorUseOutputBitstreamBufferParams deserialize(Message arg1) {
            return VideoEncodeAcceleratorUseOutputBitstreamBufferParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoEncodeAcceleratorUseOutputBitstreamBufferParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.bitstreamBufferId, 8);
            arg4.encode(this.buffer, 12, false);
        }
    }

    private static final int ENCODE_ORDINAL = 1;
    private static final int INITIALIZE_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int REQUEST_ENCODING_PARAMETERS_CHANGE_ORDINAL = 3;
    private static final int USE_OUTPUT_BITSTREAM_BUFFER_ORDINAL = 2;

    static {
        VideoEncodeAccelerator_Internal.MANAGER = new org.chromium.media.mojom.VideoEncodeAccelerator_Internal$1();
    }

    VideoEncodeAccelerator_Internal() {
        super();
    }
}

