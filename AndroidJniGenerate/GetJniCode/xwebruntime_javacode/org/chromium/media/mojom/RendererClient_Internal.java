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
import org.chromium.mojo_base.mojom.TimeTicks;

class RendererClient_Internal {
    final class org.chromium.media.mojom.RendererClient_Internal$1 extends Manager {
        org.chromium.media.mojom.RendererClient_Internal$1() {
            super();
        }

        public RendererClient[] buildArray(int arg1) {
            return new RendererClient[arg1];
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

        public Stub buildStub(Core arg2, RendererClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((RendererClient)arg2));
        }

        public String getName() {
            return "media::mojom::RendererClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.RendererClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onAudioConfigChange(AudioDecoderConfig arg5) {
            RendererClientOnAudioConfigChangeParams v0 = new RendererClientOnAudioConfigChangeParams();
            v0.config = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void onBufferingStateChange(int arg5) {
            RendererClientOnBufferingStateChangeParams v0 = new RendererClientOnBufferingStateChangeParams();
            v0.state = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onDurationChange(TimeDelta arg5) {
            RendererClientOnDurationChangeParams v0 = new RendererClientOnDurationChangeParams();
            v0.duration = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(10)));
        }

        public void onEnded() {
            this.getProxyHandler().getMessageReceiver().accept(new RendererClientOnEndedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onError() {
            this.getProxyHandler().getMessageReceiver().accept(new RendererClientOnErrorParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void onStatisticsUpdate(PipelineStatistics arg5) {
            RendererClientOnStatisticsUpdateParams v0 = new RendererClientOnStatisticsUpdateParams();
            v0.stats = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8)));
        }

        public void onTimeUpdate(TimeDelta arg3, TimeDelta arg4, TimeTicks arg5) {
            RendererClientOnTimeUpdateParams v0 = new RendererClientOnTimeUpdateParams();
            v0.time = arg3;
            v0.maxTime = arg4;
            v0.captureTime = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onVideoConfigChange(VideoDecoderConfig arg5) {
            RendererClientOnVideoConfigChangeParams v0 = new RendererClientOnVideoConfigChangeParams();
            v0.config = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void onVideoNaturalSizeChange(Size arg5) {
            RendererClientOnVideoNaturalSizeChangeParams v0 = new RendererClientOnVideoNaturalSizeChangeParams();
            v0.size = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void onVideoOpacityChange(boolean arg5) {
            RendererClientOnVideoOpacityChangeParams v0 = new RendererClientOnVideoOpacityChangeParams();
            v0.opaque = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }

        public void onWaitingForDecryptionKey() {
            this.getProxyHandler().getMessageReceiver().accept(new RendererClientOnWaitingForDecryptionKeyParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(9)));
        }
    }

    final class RendererClientOnAudioConfigChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AudioDecoderConfig config;

        static {
            RendererClientOnAudioConfigChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererClientOnAudioConfigChangeParams.DEFAULT_STRUCT_INFO = RendererClientOnAudioConfigChangeParams.VERSION_ARRAY[0];
        }

        public RendererClientOnAudioConfigChangeParams() {
            this(0);
        }

        private RendererClientOnAudioConfigChangeParams(int arg2) {
            super(16, arg2);
        }

        public static RendererClientOnAudioConfigChangeParams decode(Decoder arg3) {
            RendererClientOnAudioConfigChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererClientOnAudioConfigChangeParams(arg3.readAndValidateDataHeader(RendererClientOnAudioConfigChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.config = AudioDecoderConfig.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnAudioConfigChangeParams deserialize(ByteBuffer arg2) {
            return RendererClientOnAudioConfigChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnAudioConfigChangeParams deserialize(Message arg1) {
            return RendererClientOnAudioConfigChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererClientOnAudioConfigChangeParams.DEFAULT_STRUCT_INFO).encode(this.config, 8, false);
        }
    }

    final class RendererClientOnBufferingStateChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int state;

        static {
            RendererClientOnBufferingStateChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererClientOnBufferingStateChangeParams.DEFAULT_STRUCT_INFO = RendererClientOnBufferingStateChangeParams.VERSION_ARRAY[0];
        }

        public RendererClientOnBufferingStateChangeParams() {
            this(0);
        }

        private RendererClientOnBufferingStateChangeParams(int arg2) {
            super(16, arg2);
        }

        public static RendererClientOnBufferingStateChangeParams decode(Decoder arg2) {
            RendererClientOnBufferingStateChangeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererClientOnBufferingStateChangeParams(arg2.readAndValidateDataHeader(RendererClientOnBufferingStateChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.state = arg2.readInt(8);
                BufferingState.validate(v1.state);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnBufferingStateChangeParams deserialize(ByteBuffer arg2) {
            return RendererClientOnBufferingStateChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnBufferingStateChangeParams deserialize(Message arg1) {
            return RendererClientOnBufferingStateChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(RendererClientOnBufferingStateChangeParams.DEFAULT_STRUCT_INFO).encode(this.state, 8);
        }
    }

    final class RendererClientOnDurationChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TimeDelta duration;

        static {
            RendererClientOnDurationChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererClientOnDurationChangeParams.DEFAULT_STRUCT_INFO = RendererClientOnDurationChangeParams.VERSION_ARRAY[0];
        }

        public RendererClientOnDurationChangeParams() {
            this(0);
        }

        private RendererClientOnDurationChangeParams(int arg2) {
            super(16, arg2);
        }

        public static RendererClientOnDurationChangeParams decode(Decoder arg3) {
            RendererClientOnDurationChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererClientOnDurationChangeParams(arg3.readAndValidateDataHeader(RendererClientOnDurationChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.duration = TimeDelta.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnDurationChangeParams deserialize(ByteBuffer arg2) {
            return RendererClientOnDurationChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnDurationChangeParams deserialize(Message arg1) {
            return RendererClientOnDurationChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererClientOnDurationChangeParams.DEFAULT_STRUCT_INFO).encode(this.duration, 8, false);
        }
    }

    final class RendererClientOnEndedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            RendererClientOnEndedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            RendererClientOnEndedParams.DEFAULT_STRUCT_INFO = RendererClientOnEndedParams.VERSION_ARRAY[0];
        }

        public RendererClientOnEndedParams() {
            this(0);
        }

        private RendererClientOnEndedParams(int arg2) {
            super(8, arg2);
        }

        public static RendererClientOnEndedParams decode(Decoder arg2) {
            RendererClientOnEndedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererClientOnEndedParams(arg2.readAndValidateDataHeader(RendererClientOnEndedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnEndedParams deserialize(ByteBuffer arg2) {
            return RendererClientOnEndedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnEndedParams deserialize(Message arg1) {
            return RendererClientOnEndedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(RendererClientOnEndedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class RendererClientOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            RendererClientOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            RendererClientOnErrorParams.DEFAULT_STRUCT_INFO = RendererClientOnErrorParams.VERSION_ARRAY[0];
        }

        public RendererClientOnErrorParams() {
            this(0);
        }

        private RendererClientOnErrorParams(int arg2) {
            super(8, arg2);
        }

        public static RendererClientOnErrorParams decode(Decoder arg2) {
            RendererClientOnErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererClientOnErrorParams(arg2.readAndValidateDataHeader(RendererClientOnErrorParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnErrorParams deserialize(ByteBuffer arg2) {
            return RendererClientOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnErrorParams deserialize(Message arg1) {
            return RendererClientOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(RendererClientOnErrorParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class RendererClientOnStatisticsUpdateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PipelineStatistics stats;

        static {
            RendererClientOnStatisticsUpdateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererClientOnStatisticsUpdateParams.DEFAULT_STRUCT_INFO = RendererClientOnStatisticsUpdateParams.VERSION_ARRAY[0];
        }

        public RendererClientOnStatisticsUpdateParams() {
            this(0);
        }

        private RendererClientOnStatisticsUpdateParams(int arg2) {
            super(16, arg2);
        }

        public static RendererClientOnStatisticsUpdateParams decode(Decoder arg3) {
            RendererClientOnStatisticsUpdateParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererClientOnStatisticsUpdateParams(arg3.readAndValidateDataHeader(RendererClientOnStatisticsUpdateParams.VERSION_ARRAY).elementsOrVersion);
                v1.stats = PipelineStatistics.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnStatisticsUpdateParams deserialize(ByteBuffer arg2) {
            return RendererClientOnStatisticsUpdateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnStatisticsUpdateParams deserialize(Message arg1) {
            return RendererClientOnStatisticsUpdateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererClientOnStatisticsUpdateParams.DEFAULT_STRUCT_INFO).encode(this.stats, 8, false);
        }
    }

    final class RendererClientOnTimeUpdateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public TimeTicks captureTime;
        public TimeDelta maxTime;
        public TimeDelta time;

        static {
            RendererClientOnTimeUpdateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            RendererClientOnTimeUpdateParams.DEFAULT_STRUCT_INFO = RendererClientOnTimeUpdateParams.VERSION_ARRAY[0];
        }

        public RendererClientOnTimeUpdateParams() {
            this(0);
        }

        private RendererClientOnTimeUpdateParams(int arg2) {
            super(0x20, arg2);
        }

        public static RendererClientOnTimeUpdateParams decode(Decoder arg3) {
            RendererClientOnTimeUpdateParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererClientOnTimeUpdateParams(arg3.readAndValidateDataHeader(RendererClientOnTimeUpdateParams.VERSION_ARRAY).elementsOrVersion);
                v1.time = TimeDelta.decode(arg3.readPointer(8, false));
                v1.maxTime = TimeDelta.decode(arg3.readPointer(16, false));
                v1.captureTime = TimeTicks.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnTimeUpdateParams deserialize(ByteBuffer arg2) {
            return RendererClientOnTimeUpdateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnTimeUpdateParams deserialize(Message arg1) {
            return RendererClientOnTimeUpdateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(RendererClientOnTimeUpdateParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.time, 8, false);
            arg4.encode(this.maxTime, 16, false);
            arg4.encode(this.captureTime, 24, false);
        }
    }

    final class RendererClientOnVideoConfigChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public VideoDecoderConfig config;

        static {
            RendererClientOnVideoConfigChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererClientOnVideoConfigChangeParams.DEFAULT_STRUCT_INFO = RendererClientOnVideoConfigChangeParams.VERSION_ARRAY[0];
        }

        public RendererClientOnVideoConfigChangeParams() {
            this(0);
        }

        private RendererClientOnVideoConfigChangeParams(int arg2) {
            super(16, arg2);
        }

        public static RendererClientOnVideoConfigChangeParams decode(Decoder arg3) {
            RendererClientOnVideoConfigChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererClientOnVideoConfigChangeParams(arg3.readAndValidateDataHeader(RendererClientOnVideoConfigChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.config = VideoDecoderConfig.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnVideoConfigChangeParams deserialize(ByteBuffer arg2) {
            return RendererClientOnVideoConfigChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnVideoConfigChangeParams deserialize(Message arg1) {
            return RendererClientOnVideoConfigChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererClientOnVideoConfigChangeParams.DEFAULT_STRUCT_INFO).encode(this.config, 8, false);
        }
    }

    final class RendererClientOnVideoNaturalSizeChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Size size;

        static {
            RendererClientOnVideoNaturalSizeChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererClientOnVideoNaturalSizeChangeParams.DEFAULT_STRUCT_INFO = RendererClientOnVideoNaturalSizeChangeParams.VERSION_ARRAY[0];
        }

        public RendererClientOnVideoNaturalSizeChangeParams() {
            this(0);
        }

        private RendererClientOnVideoNaturalSizeChangeParams(int arg2) {
            super(16, arg2);
        }

        public static RendererClientOnVideoNaturalSizeChangeParams decode(Decoder arg3) {
            RendererClientOnVideoNaturalSizeChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererClientOnVideoNaturalSizeChangeParams(arg3.readAndValidateDataHeader(RendererClientOnVideoNaturalSizeChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.size = Size.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnVideoNaturalSizeChangeParams deserialize(ByteBuffer arg2) {
            return RendererClientOnVideoNaturalSizeChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnVideoNaturalSizeChangeParams deserialize(Message arg1) {
            return RendererClientOnVideoNaturalSizeChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererClientOnVideoNaturalSizeChangeParams.DEFAULT_STRUCT_INFO).encode(this.size, 8, false);
        }
    }

    final class RendererClientOnVideoOpacityChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean opaque;

        static {
            RendererClientOnVideoOpacityChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererClientOnVideoOpacityChangeParams.DEFAULT_STRUCT_INFO = RendererClientOnVideoOpacityChangeParams.VERSION_ARRAY[0];
        }

        public RendererClientOnVideoOpacityChangeParams() {
            this(0);
        }

        private RendererClientOnVideoOpacityChangeParams(int arg2) {
            super(16, arg2);
        }

        public static RendererClientOnVideoOpacityChangeParams decode(Decoder arg3) {
            RendererClientOnVideoOpacityChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererClientOnVideoOpacityChangeParams(arg3.readAndValidateDataHeader(RendererClientOnVideoOpacityChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.opaque = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnVideoOpacityChangeParams deserialize(ByteBuffer arg2) {
            return RendererClientOnVideoOpacityChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnVideoOpacityChangeParams deserialize(Message arg1) {
            return RendererClientOnVideoOpacityChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererClientOnVideoOpacityChangeParams.DEFAULT_STRUCT_INFO).encode(this.opaque, 8, 0);
        }
    }

    final class RendererClientOnWaitingForDecryptionKeyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            RendererClientOnWaitingForDecryptionKeyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            RendererClientOnWaitingForDecryptionKeyParams.DEFAULT_STRUCT_INFO = RendererClientOnWaitingForDecryptionKeyParams.VERSION_ARRAY[0];
        }

        public RendererClientOnWaitingForDecryptionKeyParams() {
            this(0);
        }

        private RendererClientOnWaitingForDecryptionKeyParams(int arg2) {
            super(8, arg2);
        }

        public static RendererClientOnWaitingForDecryptionKeyParams decode(Decoder arg2) {
            RendererClientOnWaitingForDecryptionKeyParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererClientOnWaitingForDecryptionKeyParams(arg2.readAndValidateDataHeader(RendererClientOnWaitingForDecryptionKeyParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererClientOnWaitingForDecryptionKeyParams deserialize(ByteBuffer arg2) {
            return RendererClientOnWaitingForDecryptionKeyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererClientOnWaitingForDecryptionKeyParams deserialize(Message arg1) {
            return RendererClientOnWaitingForDecryptionKeyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(RendererClientOnWaitingForDecryptionKeyParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, RendererClient arg2) {
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
                    goto label_77;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_69;
                    }
                    case 1: {
                        goto label_63;
                    }
                    case 2: {
                        goto label_58;
                    }
                    case 3: {
                        goto label_53;
                    }
                    case 4: {
                        goto label_47;
                    }
                    case 5: {
                        goto label_41;
                    }
                    case 6: {
                        goto label_35;
                    }
                    case 7: {
                        goto label_29;
                    }
                    case 8: {
                        goto label_23;
                    }
                    case 9: {
                        goto label_18;
                    }
                    case 10: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                RendererClientOnWaitingForDecryptionKeyParams.deserialize(v6_1.getPayload());
                this.getImpl().onWaitingForDecryptionKey();
                return 1;
            label_35:
                this.getImpl().onVideoNaturalSizeChange(RendererClientOnVideoNaturalSizeChangeParams.deserialize(v6_1.getPayload()).size);
                return 1;
            label_69:
                RendererClientOnTimeUpdateParams v6_2 = RendererClientOnTimeUpdateParams.deserialize(v6_1.getPayload());
                this.getImpl().onTimeUpdate(v6_2.time, v6_2.maxTime, v6_2.captureTime);
                return 1;
            label_53:
                RendererClientOnErrorParams.deserialize(v6_1.getPayload());
                this.getImpl().onError();
                return 1;
            label_23:
                this.getImpl().onStatisticsUpdate(RendererClientOnStatisticsUpdateParams.deserialize(v6_1.getPayload()).stats);
                return 1;
            label_41:
                this.getImpl().onVideoConfigChange(RendererClientOnVideoConfigChangeParams.deserialize(v6_1.getPayload()).config);
                return 1;
            label_58:
                RendererClientOnEndedParams.deserialize(v6_1.getPayload());
                this.getImpl().onEnded();
                return 1;
            label_12:
                this.getImpl().onDurationChange(RendererClientOnDurationChangeParams.deserialize(v6_1.getPayload()).duration);
                return 1;
            label_29:
                this.getImpl().onVideoOpacityChange(RendererClientOnVideoOpacityChangeParams.deserialize(v6_1.getPayload()).opaque);
                return 1;
            label_63:
                this.getImpl().onBufferingStateChange(RendererClientOnBufferingStateChangeParams.deserialize(v6_1.getPayload()).state);
                return 1;
            label_47:
                this.getImpl().onAudioConfigChange(RendererClientOnAudioConfigChangeParams.deserialize(v6_1.getPayload()).config);
                return 1;
            label_77:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(RendererClient_Internal.MANAGER, v6_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), RendererClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_AUDIO_CONFIG_CHANGE_ORDINAL = 4;
    private static final int ON_BUFFERING_STATE_CHANGE_ORDINAL = 1;
    private static final int ON_DURATION_CHANGE_ORDINAL = 10;
    private static final int ON_ENDED_ORDINAL = 2;
    private static final int ON_ERROR_ORDINAL = 3;
    private static final int ON_STATISTICS_UPDATE_ORDINAL = 8;
    private static final int ON_TIME_UPDATE_ORDINAL = 0;
    private static final int ON_VIDEO_CONFIG_CHANGE_ORDINAL = 5;
    private static final int ON_VIDEO_NATURAL_SIZE_CHANGE_ORDINAL = 6;
    private static final int ON_VIDEO_OPACITY_CHANGE_ORDINAL = 7;
    private static final int ON_WAITING_FOR_DECRYPTION_KEY_ORDINAL = 9;

    static {
        RendererClient_Internal.MANAGER = new org.chromium.media.mojom.RendererClient_Internal$1();
    }

    RendererClient_Internal() {
        super();
    }
}

