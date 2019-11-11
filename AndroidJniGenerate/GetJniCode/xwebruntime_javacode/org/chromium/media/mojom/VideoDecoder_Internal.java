package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
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
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.InvalidHandle;

class VideoDecoder_Internal {
    final class org.chromium.media.mojom.VideoDecoder_Internal$1 extends Manager {
        org.chromium.media.mojom.VideoDecoder_Internal$1() {
            super();
        }

        public VideoDecoder[] buildArray(int arg1) {
            return new VideoDecoder[arg1];
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

        public Stub buildStub(Core arg2, VideoDecoder arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VideoDecoder)arg2));
        }

        public String getName() {
            return "media::mojom::VideoDecoder";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.VideoDecoder$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void construct(AssociatedInterfaceNotSupported arg2, AssociatedInterfaceNotSupported arg3, InterfaceRequest arg4, ConsumerHandle arg5, CommandBufferId arg6) {
            VideoDecoderConstructParams v0 = new VideoDecoderConstructParams();
            v0.client = arg2;
            v0.mediaLog = arg3;
            v0.videoFrameHandleReleaser = arg4;
            v0.decoderBufferPipe = arg5;
            v0.commandBufferId = arg6;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void decode(DecoderBuffer arg8, DecodeResponse arg9) {
            VideoDecoderDecodeParams v0 = new VideoDecoderDecodeParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new VideoDecoderDecodeResponseParamsForwardToCallback(arg9));
        }

        public void initialize(VideoDecoderConfig arg5, boolean arg6, int arg7, InitializeResponse arg8) {
            VideoDecoderInitializeParams v0 = new VideoDecoderInitializeParams();
            v0.config = arg5;
            v0.lowDelay = arg6;
            v0.cdmId = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new VideoDecoderInitializeResponseParamsForwardToCallback(arg8));
        }

        public void onOverlayInfoChanged(OverlayInfo arg5) {
            VideoDecoderOnOverlayInfoChangedParams v0 = new VideoDecoderOnOverlayInfoChangedParams();
            v0.overlayInfo = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void reset(ResetResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new VideoDecoderResetParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new VideoDecoderResetResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VideoDecoder arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg10) {
            try {
                ServiceMessage v10_1 = arg10.asServiceMessage();
                MessageHeader v1 = v10_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 != -2) {
                    if(v1_1 != 0) {
                        if(v1_1 != 4) {
                            return 0;
                        }

                        this.getImpl().onOverlayInfoChanged(VideoDecoderOnOverlayInfoChangedParams.deserialize(v10_1.getPayload()).overlayInfo);
                        return 1;
                    }

                    VideoDecoderConstructParams v10_2 = VideoDecoderConstructParams.deserialize(v10_1.getPayload());
                    this.getImpl().construct(v10_2.client, v10_2.mediaLog, v10_2.videoFrameHandleReleaser, v10_2.decoderBufferPipe, v10_2.commandBufferId);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VideoDecoder_Internal.MANAGER, v10_1);
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg11, MessageReceiver arg12) {
            try {
                ServiceMessage v11_1 = arg11.asServiceMessage();
                MessageHeader v1 = v11_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                int v3 = v1.getType();
                if(v3 == -1) {
                    goto label_43;
                }

                switch(v3) {
                    case 1: {
                        goto label_31;
                    }
                    case 2: {
                        goto label_21;
                    }
                    case 3: {
                        goto label_12;
                    }
                }

                return 0;
            label_21:
                this.getImpl().decode(VideoDecoderDecodeParams.deserialize(v11_1.getPayload()).buffer, new VideoDecoderDecodeResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_12:
                VideoDecoderResetParams.deserialize(v11_1.getPayload());
                this.getImpl().reset(new VideoDecoderResetResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_31:
                VideoDecoderInitializeParams v11_2 = VideoDecoderInitializeParams.deserialize(v11_1.getPayload());
                this.getImpl().initialize(v11_2.config, v11_2.lowDelay, v11_2.cdmId, new VideoDecoderInitializeResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_43:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VideoDecoder_Internal.MANAGER, v11_1, arg12);
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    final class VideoDecoderConstructParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public AssociatedInterfaceNotSupported client;
        public CommandBufferId commandBufferId;
        public ConsumerHandle decoderBufferPipe;
        public AssociatedInterfaceNotSupported mediaLog;
        public InterfaceRequest videoFrameHandleReleaser;

        static {
            VideoDecoderConstructParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            VideoDecoderConstructParams.DEFAULT_STRUCT_INFO = VideoDecoderConstructParams.VERSION_ARRAY[0];
        }

        public VideoDecoderConstructParams() {
            this(0);
        }

        private VideoDecoderConstructParams(int arg2) {
            super(40, arg2);
            this.decoderBufferPipe = InvalidHandle.INSTANCE;
        }

        public static VideoDecoderConstructParams decode(Decoder arg3) {
            VideoDecoderConstructParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecoderConstructParams(arg3.readAndValidateDataHeader(VideoDecoderConstructParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg3.readAssociatedServiceInterfaceNotSupported(8, false);
                v1.mediaLog = arg3.readAssociatedServiceInterfaceNotSupported(16, false);
                v1.videoFrameHandleReleaser = arg3.readInterfaceRequest(24, false);
                v1.decoderBufferPipe = arg3.readConsumerHandle(28, false);
                v1.commandBufferId = CommandBufferId.decode(arg3.readPointer(0x20, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderConstructParams deserialize(ByteBuffer arg2) {
            return VideoDecoderConstructParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderConstructParams deserialize(Message arg1) {
            return VideoDecoderConstructParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoDecoderConstructParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.client, 8, false);
            arg4.encode(this.mediaLog, 16, false);
            arg4.encode(this.videoFrameHandleReleaser, 24, false);
            arg4.encode(this.decoderBufferPipe, 28, false);
            arg4.encode(this.commandBufferId, 0x20, true);
        }
    }

    final class VideoDecoderDecodeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public DecoderBuffer buffer;

        static {
            VideoDecoderDecodeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecoderDecodeParams.DEFAULT_STRUCT_INFO = VideoDecoderDecodeParams.VERSION_ARRAY[0];
        }

        public VideoDecoderDecodeParams() {
            this(0);
        }

        private VideoDecoderDecodeParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecoderDecodeParams decode(Decoder arg3) {
            VideoDecoderDecodeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecoderDecodeParams(arg3.readAndValidateDataHeader(VideoDecoderDecodeParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = DecoderBuffer.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderDecodeParams deserialize(ByteBuffer arg2) {
            return VideoDecoderDecodeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderDecodeParams deserialize(Message arg1) {
            return VideoDecoderDecodeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoDecoderDecodeParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8, false);
        }
    }

    final class VideoDecoderDecodeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int status;

        static {
            VideoDecoderDecodeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecoderDecodeResponseParams.DEFAULT_STRUCT_INFO = VideoDecoderDecodeResponseParams.VERSION_ARRAY[0];
        }

        public VideoDecoderDecodeResponseParams() {
            this(0);
        }

        private VideoDecoderDecodeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecoderDecodeResponseParams decode(Decoder arg2) {
            VideoDecoderDecodeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VideoDecoderDecodeResponseParams(arg2.readAndValidateDataHeader(VideoDecoderDecodeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg2.readInt(8);
                DecodeStatus.validate(v1.status);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderDecodeResponseParams deserialize(ByteBuffer arg2) {
            return VideoDecoderDecodeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderDecodeResponseParams deserialize(Message arg1) {
            return VideoDecoderDecodeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(VideoDecoderDecodeResponseParams.DEFAULT_STRUCT_INFO).encode(this.status, 8);
        }
    }

    class VideoDecoderDecodeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DecodeResponse mCallback;

        VideoDecoderDecodeResponseParamsForwardToCallback(DecodeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(VideoDecoderDecodeResponseParams.deserialize(v4.getPayload()).status));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class VideoDecoderDecodeResponseParamsProxyToResponder implements DecodeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VideoDecoderDecodeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6) {
            VideoDecoderDecodeResponseParams v0 = new VideoDecoderDecodeResponseParams();
            v0.status = arg6.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class VideoDecoderInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int cdmId;
        public VideoDecoderConfig config;
        public boolean lowDelay;

        static {
            VideoDecoderInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            VideoDecoderInitializeParams.DEFAULT_STRUCT_INFO = VideoDecoderInitializeParams.VERSION_ARRAY[0];
        }

        public VideoDecoderInitializeParams() {
            this(0);
        }

        private VideoDecoderInitializeParams(int arg2) {
            super(24, arg2);
        }

        public static VideoDecoderInitializeParams decode(Decoder arg3) {
            VideoDecoderInitializeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecoderInitializeParams(arg3.readAndValidateDataHeader(VideoDecoderInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.config = VideoDecoderConfig.decode(arg3.readPointer(8, false));
                v1.lowDelay = arg3.readBoolean(16, 0);
                v1.cdmId = arg3.readInt(20);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderInitializeParams deserialize(ByteBuffer arg2) {
            return VideoDecoderInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderInitializeParams deserialize(Message arg1) {
            return VideoDecoderInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoDecoderInitializeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.config, 8, false);
            arg4.encode(this.lowDelay, 16, 0);
            arg4.encode(this.cdmId, 20);
        }
    }

    final class VideoDecoderInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int maxDecodeRequests;
        public boolean needsBitstreamConversion;
        public boolean success;

        static {
            VideoDecoderInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecoderInitializeResponseParams.DEFAULT_STRUCT_INFO = VideoDecoderInitializeResponseParams.VERSION_ARRAY[0];
        }

        public VideoDecoderInitializeResponseParams() {
            this(0);
        }

        private VideoDecoderInitializeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecoderInitializeResponseParams decode(Decoder arg3) {
            VideoDecoderInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecoderInitializeResponseParams(arg3.readAndValidateDataHeader(VideoDecoderInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
                v1.needsBitstreamConversion = arg3.readBoolean(8, 1);
                v1.maxDecodeRequests = arg3.readInt(12);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderInitializeResponseParams deserialize(ByteBuffer arg2) {
            return VideoDecoderInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderInitializeResponseParams deserialize(Message arg1) {
            return VideoDecoderInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(VideoDecoderInitializeResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.success, 8, 0);
            arg4.encode(this.needsBitstreamConversion, 8, 1);
            arg4.encode(this.maxDecodeRequests, 12);
        }
    }

    class VideoDecoderInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        VideoDecoderInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                VideoDecoderInitializeResponseParams v6_1 = VideoDecoderInitializeResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(Boolean.valueOf(v6_1.success), Boolean.valueOf(v6_1.needsBitstreamConversion), Integer.valueOf(v6_1.maxDecodeRequests));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class VideoDecoderInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VideoDecoderInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, Boolean arg6, Integer arg7) {
            VideoDecoderInitializeResponseParams v0 = new VideoDecoderInitializeResponseParams();
            v0.success = arg5.booleanValue();
            v0.needsBitstreamConversion = arg6.booleanValue();
            v0.maxDecodeRequests = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Boolean)arg1), ((Boolean)arg2), ((Integer)arg3));
        }
    }

    final class VideoDecoderOnOverlayInfoChangedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public OverlayInfo overlayInfo;

        static {
            VideoDecoderOnOverlayInfoChangedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VideoDecoderOnOverlayInfoChangedParams.DEFAULT_STRUCT_INFO = VideoDecoderOnOverlayInfoChangedParams.VERSION_ARRAY[0];
        }

        public VideoDecoderOnOverlayInfoChangedParams() {
            this(0);
        }

        private VideoDecoderOnOverlayInfoChangedParams(int arg2) {
            super(16, arg2);
        }

        public static VideoDecoderOnOverlayInfoChangedParams decode(Decoder arg3) {
            VideoDecoderOnOverlayInfoChangedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new VideoDecoderOnOverlayInfoChangedParams(arg3.readAndValidateDataHeader(VideoDecoderOnOverlayInfoChangedParams.VERSION_ARRAY).elementsOrVersion);
                v1.overlayInfo = OverlayInfo.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderOnOverlayInfoChangedParams deserialize(ByteBuffer arg2) {
            return VideoDecoderOnOverlayInfoChangedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderOnOverlayInfoChangedParams deserialize(Message arg1) {
            return VideoDecoderOnOverlayInfoChangedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VideoDecoderOnOverlayInfoChangedParams.DEFAULT_STRUCT_INFO).encode(this.overlayInfo, 8, false);
        }
    }

    final class VideoDecoderResetParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            VideoDecoderResetParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            VideoDecoderResetParams.DEFAULT_STRUCT_INFO = VideoDecoderResetParams.VERSION_ARRAY[0];
        }

        public VideoDecoderResetParams() {
            this(0);
        }

        private VideoDecoderResetParams(int arg2) {
            super(8, arg2);
        }

        public static VideoDecoderResetParams decode(Decoder arg2) {
            VideoDecoderResetParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VideoDecoderResetParams(arg2.readAndValidateDataHeader(VideoDecoderResetParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderResetParams deserialize(ByteBuffer arg2) {
            return VideoDecoderResetParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderResetParams deserialize(Message arg1) {
            return VideoDecoderResetParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(VideoDecoderResetParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class VideoDecoderResetResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            VideoDecoderResetResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            VideoDecoderResetResponseParams.DEFAULT_STRUCT_INFO = VideoDecoderResetResponseParams.VERSION_ARRAY[0];
        }

        public VideoDecoderResetResponseParams() {
            this(0);
        }

        private VideoDecoderResetResponseParams(int arg2) {
            super(8, arg2);
        }

        public static VideoDecoderResetResponseParams decode(Decoder arg2) {
            VideoDecoderResetResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VideoDecoderResetResponseParams(arg2.readAndValidateDataHeader(VideoDecoderResetResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VideoDecoderResetResponseParams deserialize(ByteBuffer arg2) {
            return VideoDecoderResetResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VideoDecoderResetResponseParams deserialize(Message arg1) {
            return VideoDecoderResetResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(VideoDecoderResetResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class VideoDecoderResetResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ResetResponse mCallback;

        VideoDecoderResetResponseParamsForwardToCallback(ResetResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(3, 2)) {
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

    class VideoDecoderResetResponseParamsProxyToResponder implements ResetResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VideoDecoderResetResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new VideoDecoderResetResponseParams().serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    private static final int CONSTRUCT_ORDINAL = 0;
    private static final int DECODE_ORDINAL = 2;
    private static final int INITIALIZE_ORDINAL = 1;
    public static final Manager MANAGER = null;
    private static final int ON_OVERLAY_INFO_CHANGED_ORDINAL = 4;
    private static final int RESET_ORDINAL = 3;

    static {
        VideoDecoder_Internal.MANAGER = new org.chromium.media.mojom.VideoDecoder_Internal$1();
    }

    VideoDecoder_Internal() {
        super();
    }
}

