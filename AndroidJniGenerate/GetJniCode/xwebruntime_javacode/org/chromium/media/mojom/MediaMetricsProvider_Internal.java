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
import org.chromium.mojo_base.mojom.TimeDelta;
import org.chromium.url.mojom.Origin;

class MediaMetricsProvider_Internal {
    final class org.chromium.media.mojom.MediaMetricsProvider_Internal$1 extends Manager {
        org.chromium.media.mojom.MediaMetricsProvider_Internal$1() {
            super();
        }

        public MediaMetricsProvider[] buildArray(int arg1) {
            return new MediaMetricsProvider[arg1];
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

        public Stub buildStub(Core arg2, MediaMetricsProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((MediaMetricsProvider)arg2));
        }

        public String getName() {
            return "media::mojom::MediaMetricsProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest recorder;

        static {
            MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams() {
            this(0);
        }

        private MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams(int arg2) {
            super(16, arg2);
        }

        public static MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams decode(Decoder arg3) {
            MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams(arg3.readAndValidateDataHeader(MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.VERSION_ARRAY).elementsOrVersion);
                v1.recorder = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams deserialize(Message arg1) {
            return MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.DEFAULT_STRUCT_INFO).encode(this.recorder, 8, false);
        }
    }

    final class MediaMetricsProviderAcquireWatchTimeRecorderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public PlaybackProperties properties;
        public InterfaceRequest recorder;

        static {
            MediaMetricsProviderAcquireWatchTimeRecorderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            MediaMetricsProviderAcquireWatchTimeRecorderParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderAcquireWatchTimeRecorderParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderAcquireWatchTimeRecorderParams() {
            this(0);
        }

        private MediaMetricsProviderAcquireWatchTimeRecorderParams(int arg2) {
            super(24, arg2);
        }

        public static MediaMetricsProviderAcquireWatchTimeRecorderParams decode(Decoder arg3) {
            MediaMetricsProviderAcquireWatchTimeRecorderParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderAcquireWatchTimeRecorderParams(arg3.readAndValidateDataHeader(MediaMetricsProviderAcquireWatchTimeRecorderParams.VERSION_ARRAY).elementsOrVersion);
                v1.properties = PlaybackProperties.decode(arg3.readPointer(8, false));
                v1.recorder = arg3.readInterfaceRequest(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderAcquireWatchTimeRecorderParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderAcquireWatchTimeRecorderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderAcquireWatchTimeRecorderParams deserialize(Message arg1) {
            return MediaMetricsProviderAcquireWatchTimeRecorderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(MediaMetricsProviderAcquireWatchTimeRecorderParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.properties, 8, false);
            arg4.encode(this.recorder, 16, false);
        }
    }

    final class MediaMetricsProviderInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isMse;
        public boolean isTopFrame;
        public Origin untrustedTopOrigin;

        static {
            MediaMetricsProviderInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            MediaMetricsProviderInitializeParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderInitializeParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderInitializeParams() {
            this(0);
        }

        private MediaMetricsProviderInitializeParams(int arg2) {
            super(24, arg2);
        }

        public static MediaMetricsProviderInitializeParams decode(Decoder arg4) {
            MediaMetricsProviderInitializeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderInitializeParams(arg4.readAndValidateDataHeader(MediaMetricsProviderInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.isMse = arg4.readBoolean(8, 0);
                v1.isTopFrame = arg4.readBoolean(8, 1);
                v1.untrustedTopOrigin = Origin.decode(arg4.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderInitializeParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderInitializeParams deserialize(Message arg1) {
            return MediaMetricsProviderInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(MediaMetricsProviderInitializeParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.isMse, 8, 0);
            arg5.encode(this.isTopFrame, 8, 1);
            arg5.encode(this.untrustedTopOrigin, 16, false);
        }
    }

    final class MediaMetricsProviderOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int status;

        static {
            MediaMetricsProviderOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaMetricsProviderOnErrorParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderOnErrorParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderOnErrorParams() {
            this(0);
        }

        private MediaMetricsProviderOnErrorParams(int arg2) {
            super(16, arg2);
        }

        public static MediaMetricsProviderOnErrorParams decode(Decoder arg2) {
            MediaMetricsProviderOnErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderOnErrorParams(arg2.readAndValidateDataHeader(MediaMetricsProviderOnErrorParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg2.readInt(8);
                PipelineStatus.validate(v1.status);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderOnErrorParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderOnErrorParams deserialize(Message arg1) {
            return MediaMetricsProviderOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(MediaMetricsProviderOnErrorParams.DEFAULT_STRUCT_INFO).encode(this.status, 8);
        }
    }

    final class MediaMetricsProviderSetIsEmeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            MediaMetricsProviderSetIsEmeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            MediaMetricsProviderSetIsEmeParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderSetIsEmeParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderSetIsEmeParams() {
            this(0);
        }

        private MediaMetricsProviderSetIsEmeParams(int arg2) {
            super(8, arg2);
        }

        public static MediaMetricsProviderSetIsEmeParams decode(Decoder arg2) {
            MediaMetricsProviderSetIsEmeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderSetIsEmeParams(arg2.readAndValidateDataHeader(MediaMetricsProviderSetIsEmeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderSetIsEmeParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderSetIsEmeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderSetIsEmeParams deserialize(Message arg1) {
            return MediaMetricsProviderSetIsEmeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(MediaMetricsProviderSetIsEmeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class MediaMetricsProviderSetTimeToFirstFrameParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TimeDelta elapsed;

        static {
            MediaMetricsProviderSetTimeToFirstFrameParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaMetricsProviderSetTimeToFirstFrameParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderSetTimeToFirstFrameParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderSetTimeToFirstFrameParams() {
            this(0);
        }

        private MediaMetricsProviderSetTimeToFirstFrameParams(int arg2) {
            super(16, arg2);
        }

        public static MediaMetricsProviderSetTimeToFirstFrameParams decode(Decoder arg3) {
            MediaMetricsProviderSetTimeToFirstFrameParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderSetTimeToFirstFrameParams(arg3.readAndValidateDataHeader(MediaMetricsProviderSetTimeToFirstFrameParams.VERSION_ARRAY).elementsOrVersion);
                v1.elapsed = TimeDelta.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderSetTimeToFirstFrameParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderSetTimeToFirstFrameParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderSetTimeToFirstFrameParams deserialize(Message arg1) {
            return MediaMetricsProviderSetTimeToFirstFrameParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaMetricsProviderSetTimeToFirstFrameParams.DEFAULT_STRUCT_INFO).encode(this.elapsed, 8, false);
        }
    }

    final class MediaMetricsProviderSetTimeToMetadataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TimeDelta elapsed;

        static {
            MediaMetricsProviderSetTimeToMetadataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaMetricsProviderSetTimeToMetadataParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderSetTimeToMetadataParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderSetTimeToMetadataParams() {
            this(0);
        }

        private MediaMetricsProviderSetTimeToMetadataParams(int arg2) {
            super(16, arg2);
        }

        public static MediaMetricsProviderSetTimeToMetadataParams decode(Decoder arg3) {
            MediaMetricsProviderSetTimeToMetadataParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderSetTimeToMetadataParams(arg3.readAndValidateDataHeader(MediaMetricsProviderSetTimeToMetadataParams.VERSION_ARRAY).elementsOrVersion);
                v1.elapsed = TimeDelta.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderSetTimeToMetadataParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderSetTimeToMetadataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderSetTimeToMetadataParams deserialize(Message arg1) {
            return MediaMetricsProviderSetTimeToMetadataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaMetricsProviderSetTimeToMetadataParams.DEFAULT_STRUCT_INFO).encode(this.elapsed, 8, false);
        }
    }

    final class MediaMetricsProviderSetTimeToPlayReadyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TimeDelta elapsed;

        static {
            MediaMetricsProviderSetTimeToPlayReadyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaMetricsProviderSetTimeToPlayReadyParams.DEFAULT_STRUCT_INFO = MediaMetricsProviderSetTimeToPlayReadyParams.VERSION_ARRAY[0];
        }

        public MediaMetricsProviderSetTimeToPlayReadyParams() {
            this(0);
        }

        private MediaMetricsProviderSetTimeToPlayReadyParams(int arg2) {
            super(16, arg2);
        }

        public static MediaMetricsProviderSetTimeToPlayReadyParams decode(Decoder arg3) {
            MediaMetricsProviderSetTimeToPlayReadyParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaMetricsProviderSetTimeToPlayReadyParams(arg3.readAndValidateDataHeader(MediaMetricsProviderSetTimeToPlayReadyParams.VERSION_ARRAY).elementsOrVersion);
                v1.elapsed = TimeDelta.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaMetricsProviderSetTimeToPlayReadyParams deserialize(ByteBuffer arg2) {
            return MediaMetricsProviderSetTimeToPlayReadyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaMetricsProviderSetTimeToPlayReadyParams deserialize(Message arg1) {
            return MediaMetricsProviderSetTimeToPlayReadyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaMetricsProviderSetTimeToPlayReadyParams.DEFAULT_STRUCT_INFO).encode(this.elapsed, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.MediaMetricsProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void acquireVideoDecodeStatsRecorder(InterfaceRequest arg5) {
            MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams v0 = new MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams();
            v0.recorder = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }

        public void acquireWatchTimeRecorder(PlaybackProperties arg4, InterfaceRequest arg5) {
            MediaMetricsProviderAcquireWatchTimeRecorderParams v0 = new MediaMetricsProviderAcquireWatchTimeRecorderParams();
            v0.properties = arg4;
            v0.recorder = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void initialize(boolean arg3, boolean arg4, Origin arg5) {
            MediaMetricsProviderInitializeParams v0 = new MediaMetricsProviderInitializeParams();
            v0.isMse = arg3;
            v0.isTopFrame = arg4;
            v0.untrustedTopOrigin = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onError(int arg5) {
            MediaMetricsProviderOnErrorParams v0 = new MediaMetricsProviderOnErrorParams();
            v0.status = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void setIsEme() {
            this.getProxyHandler().getMessageReceiver().accept(new MediaMetricsProviderSetIsEmeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void setTimeToFirstFrame(TimeDelta arg5) {
            MediaMetricsProviderSetTimeToFirstFrameParams v0 = new MediaMetricsProviderSetTimeToFirstFrameParams();
            v0.elapsed = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void setTimeToMetadata(TimeDelta arg5) {
            MediaMetricsProviderSetTimeToMetadataParams v0 = new MediaMetricsProviderSetTimeToMetadataParams();
            v0.elapsed = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void setTimeToPlayReady(TimeDelta arg5) {
            MediaMetricsProviderSetTimeToPlayReadyParams v0 = new MediaMetricsProviderSetTimeToPlayReadyParams();
            v0.elapsed = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, MediaMetricsProvider arg2) {
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
                    goto label_62;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_54;
                    }
                    case 1: {
                        goto label_48;
                    }
                    case 2: {
                        goto label_43;
                    }
                    case 3: {
                        goto label_37;
                    }
                    case 4: {
                        goto label_31;
                    }
                    case 5: {
                        goto label_25;
                    }
                    case 6: {
                        goto label_18;
                    }
                    case 7: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                MediaMetricsProviderAcquireWatchTimeRecorderParams v6_2 = MediaMetricsProviderAcquireWatchTimeRecorderParams.deserialize(v6_1.getPayload());
                this.getImpl().acquireWatchTimeRecorder(v6_2.properties, v6_2.recorder);
                return 1;
            label_37:
                this.getImpl().setTimeToMetadata(MediaMetricsProviderSetTimeToMetadataParams.deserialize(v6_1.getPayload()).elapsed);
                return 1;
            label_54:
                MediaMetricsProviderInitializeParams v6_3 = MediaMetricsProviderInitializeParams.deserialize(v6_1.getPayload());
                this.getImpl().initialize(v6_3.isMse, v6_3.isTopFrame, v6_3.untrustedTopOrigin);
                return 1;
            label_25:
                this.getImpl().setTimeToPlayReady(MediaMetricsProviderSetTimeToPlayReadyParams.deserialize(v6_1.getPayload()).elapsed);
                return 1;
            label_43:
                MediaMetricsProviderSetIsEmeParams.deserialize(v6_1.getPayload());
                this.getImpl().setIsEme();
                return 1;
            label_12:
                this.getImpl().acquireVideoDecodeStatsRecorder(MediaMetricsProviderAcquireVideoDecodeStatsRecorderParams.deserialize(v6_1.getPayload()).recorder);
                return 1;
            label_31:
                this.getImpl().setTimeToFirstFrame(MediaMetricsProviderSetTimeToFirstFrameParams.deserialize(v6_1.getPayload()).elapsed);
                return 1;
            label_48:
                this.getImpl().onError(MediaMetricsProviderOnErrorParams.deserialize(v6_1.getPayload()).status);
                return 1;
            label_62:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(MediaMetricsProvider_Internal.MANAGER, v6_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), MediaMetricsProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int ACQUIRE_VIDEO_DECODE_STATS_RECORDER_ORDINAL = 7;
    private static final int ACQUIRE_WATCH_TIME_RECORDER_ORDINAL = 6;
    private static final int INITIALIZE_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int ON_ERROR_ORDINAL = 1;
    private static final int SET_IS_EME_ORDINAL = 2;
    private static final int SET_TIME_TO_FIRST_FRAME_ORDINAL = 4;
    private static final int SET_TIME_TO_METADATA_ORDINAL = 3;
    private static final int SET_TIME_TO_PLAY_READY_ORDINAL = 5;

    static {
        MediaMetricsProvider_Internal.MANAGER = new org.chromium.media.mojom.MediaMetricsProvider_Internal$1();
    }

    MediaMetricsProvider_Internal() {
        super();
    }
}

