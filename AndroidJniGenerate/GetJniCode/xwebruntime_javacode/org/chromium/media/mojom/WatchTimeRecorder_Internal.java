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
import org.chromium.mojo_base.mojom.TimeDelta;

class WatchTimeRecorder_Internal {
    final class org.chromium.media.mojom.WatchTimeRecorder_Internal$1 extends Manager {
        org.chromium.media.mojom.WatchTimeRecorder_Internal$1() {
            super();
        }

        public WatchTimeRecorder[] buildArray(int arg1) {
            return new WatchTimeRecorder[arg1];
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

        public Stub buildStub(Core arg2, WatchTimeRecorder arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((WatchTimeRecorder)arg2));
        }

        public String getName() {
            return "media::mojom::WatchTimeRecorder";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.WatchTimeRecorder$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void finalizeWatchTime(int[] arg5) {
            WatchTimeRecorderFinalizeWatchTimeParams v0 = new WatchTimeRecorderFinalizeWatchTimeParams();
            v0.watchTimeKeys = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onError(int arg5) {
            WatchTimeRecorderOnErrorParams v0 = new WatchTimeRecorderOnErrorParams();
            v0.status = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void recordWatchTime(int arg4, TimeDelta arg5) {
            WatchTimeRecorderRecordWatchTimeParams v0 = new WatchTimeRecorderRecordWatchTimeParams();
            v0.key = arg4;
            v0.watchTime = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void setAudioDecoderName(String arg5) {
            WatchTimeRecorderSetAudioDecoderNameParams v0 = new WatchTimeRecorderSetAudioDecoderNameParams();
            v0.name = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void setAutoplayInitiated(boolean arg5) {
            WatchTimeRecorderSetAutoplayInitiatedParams v0 = new WatchTimeRecorderSetAutoplayInitiatedParams();
            v0.value = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void setVideoDecoderName(String arg5) {
            WatchTimeRecorderSetVideoDecoderNameParams v0 = new WatchTimeRecorderSetVideoDecoderNameParams();
            v0.name = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void updateUnderflowCount(int arg5) {
            WatchTimeRecorderUpdateUnderflowCountParams v0 = new WatchTimeRecorderUpdateUnderflowCountParams();
            v0.count = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, WatchTimeRecorder arg2) {
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
                    goto label_55;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_48;
                    }
                    case 1: {
                        goto label_42;
                    }
                    case 2: {
                        goto label_36;
                    }
                    case 3: {
                        goto label_30;
                    }
                    case 4: {
                        goto label_24;
                    }
                    case 5: {
                        goto label_18;
                    }
                    case 6: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().setAutoplayInitiated(WatchTimeRecorderSetAutoplayInitiatedParams.deserialize(v5_1.getPayload()).value);
                return 1;
            label_36:
                this.getImpl().onError(WatchTimeRecorderOnErrorParams.deserialize(v5_1.getPayload()).status);
                return 1;
            label_24:
                this.getImpl().setVideoDecoderName(WatchTimeRecorderSetVideoDecoderNameParams.deserialize(v5_1.getPayload()).name);
                return 1;
            label_42:
                this.getImpl().finalizeWatchTime(WatchTimeRecorderFinalizeWatchTimeParams.deserialize(v5_1.getPayload()).watchTimeKeys);
                return 1;
            label_12:
                this.getImpl().updateUnderflowCount(WatchTimeRecorderUpdateUnderflowCountParams.deserialize(v5_1.getPayload()).count);
                return 1;
            label_30:
                this.getImpl().setAudioDecoderName(WatchTimeRecorderSetAudioDecoderNameParams.deserialize(v5_1.getPayload()).name);
                return 1;
            label_48:
                WatchTimeRecorderRecordWatchTimeParams v5_2 = WatchTimeRecorderRecordWatchTimeParams.deserialize(v5_1.getPayload());
                this.getImpl().recordWatchTime(v5_2.key, v5_2.watchTime);
                return 1;
            label_55:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(WatchTimeRecorder_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), WatchTimeRecorder_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class WatchTimeRecorderFinalizeWatchTimeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int[] watchTimeKeys;

        static {
            WatchTimeRecorderFinalizeWatchTimeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WatchTimeRecorderFinalizeWatchTimeParams.DEFAULT_STRUCT_INFO = WatchTimeRecorderFinalizeWatchTimeParams.VERSION_ARRAY[0];
        }

        public WatchTimeRecorderFinalizeWatchTimeParams() {
            this(0);
        }

        private WatchTimeRecorderFinalizeWatchTimeParams(int arg2) {
            super(16, arg2);
        }

        public static WatchTimeRecorderFinalizeWatchTimeParams decode(Decoder arg4) {
            WatchTimeRecorderFinalizeWatchTimeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new WatchTimeRecorderFinalizeWatchTimeParams(arg4.readAndValidateDataHeader(WatchTimeRecorderFinalizeWatchTimeParams.VERSION_ARRAY).elementsOrVersion);
                int v3 = 0;
                v1.watchTimeKeys = arg4.readInts(8, 0, -1);
                while(v3 < v1.watchTimeKeys.length) {
                    WatchTimeKey.validate(v1.watchTimeKeys[v3]);
                    ++v3;
                }
            }
            catch(Throwable v0) {
                goto label_25;
            }

            arg4.decreaseStackDepth();
            return v1;
        label_25:
            arg4.decreaseStackDepth();
            throw v0;
        }

        public static WatchTimeRecorderFinalizeWatchTimeParams deserialize(ByteBuffer arg2) {
            return WatchTimeRecorderFinalizeWatchTimeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WatchTimeRecorderFinalizeWatchTimeParams deserialize(Message arg1) {
            return WatchTimeRecorderFinalizeWatchTimeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(WatchTimeRecorderFinalizeWatchTimeParams.DEFAULT_STRUCT_INFO).encode(this.watchTimeKeys, 8, 0, -1);
        }
    }

    final class WatchTimeRecorderOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int status;

        static {
            WatchTimeRecorderOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WatchTimeRecorderOnErrorParams.DEFAULT_STRUCT_INFO = WatchTimeRecorderOnErrorParams.VERSION_ARRAY[0];
        }

        public WatchTimeRecorderOnErrorParams() {
            this(0);
        }

        private WatchTimeRecorderOnErrorParams(int arg2) {
            super(16, arg2);
        }

        public static WatchTimeRecorderOnErrorParams decode(Decoder arg2) {
            WatchTimeRecorderOnErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WatchTimeRecorderOnErrorParams(arg2.readAndValidateDataHeader(WatchTimeRecorderOnErrorParams.VERSION_ARRAY).elementsOrVersion);
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

        public static WatchTimeRecorderOnErrorParams deserialize(ByteBuffer arg2) {
            return WatchTimeRecorderOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WatchTimeRecorderOnErrorParams deserialize(Message arg1) {
            return WatchTimeRecorderOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(WatchTimeRecorderOnErrorParams.DEFAULT_STRUCT_INFO).encode(this.status, 8);
        }
    }

    final class WatchTimeRecorderRecordWatchTimeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int key;
        public TimeDelta watchTime;

        static {
            WatchTimeRecorderRecordWatchTimeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            WatchTimeRecorderRecordWatchTimeParams.DEFAULT_STRUCT_INFO = WatchTimeRecorderRecordWatchTimeParams.VERSION_ARRAY[0];
        }

        public WatchTimeRecorderRecordWatchTimeParams() {
            this(0);
        }

        private WatchTimeRecorderRecordWatchTimeParams(int arg2) {
            super(24, arg2);
        }

        public static WatchTimeRecorderRecordWatchTimeParams decode(Decoder arg3) {
            WatchTimeRecorderRecordWatchTimeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WatchTimeRecorderRecordWatchTimeParams(arg3.readAndValidateDataHeader(WatchTimeRecorderRecordWatchTimeParams.VERSION_ARRAY).elementsOrVersion);
                v1.key = arg3.readInt(8);
                WatchTimeKey.validate(v1.key);
                v1.watchTime = TimeDelta.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WatchTimeRecorderRecordWatchTimeParams deserialize(ByteBuffer arg2) {
            return WatchTimeRecorderRecordWatchTimeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WatchTimeRecorderRecordWatchTimeParams deserialize(Message arg1) {
            return WatchTimeRecorderRecordWatchTimeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(WatchTimeRecorderRecordWatchTimeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.key, 8);
            arg4.encode(this.watchTime, 16, false);
        }
    }

    final class WatchTimeRecorderSetAudioDecoderNameParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String name;

        static {
            WatchTimeRecorderSetAudioDecoderNameParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WatchTimeRecorderSetAudioDecoderNameParams.DEFAULT_STRUCT_INFO = WatchTimeRecorderSetAudioDecoderNameParams.VERSION_ARRAY[0];
        }

        public WatchTimeRecorderSetAudioDecoderNameParams() {
            this(0);
        }

        private WatchTimeRecorderSetAudioDecoderNameParams(int arg2) {
            super(16, arg2);
        }

        public static WatchTimeRecorderSetAudioDecoderNameParams decode(Decoder arg3) {
            WatchTimeRecorderSetAudioDecoderNameParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WatchTimeRecorderSetAudioDecoderNameParams(arg3.readAndValidateDataHeader(WatchTimeRecorderSetAudioDecoderNameParams.VERSION_ARRAY).elementsOrVersion);
                v1.name = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WatchTimeRecorderSetAudioDecoderNameParams deserialize(ByteBuffer arg2) {
            return WatchTimeRecorderSetAudioDecoderNameParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WatchTimeRecorderSetAudioDecoderNameParams deserialize(Message arg1) {
            return WatchTimeRecorderSetAudioDecoderNameParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WatchTimeRecorderSetAudioDecoderNameParams.DEFAULT_STRUCT_INFO).encode(this.name, 8, false);
        }
    }

    final class WatchTimeRecorderSetAutoplayInitiatedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean value;

        static {
            WatchTimeRecorderSetAutoplayInitiatedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WatchTimeRecorderSetAutoplayInitiatedParams.DEFAULT_STRUCT_INFO = WatchTimeRecorderSetAutoplayInitiatedParams.VERSION_ARRAY[0];
        }

        public WatchTimeRecorderSetAutoplayInitiatedParams() {
            this(0);
        }

        private WatchTimeRecorderSetAutoplayInitiatedParams(int arg2) {
            super(16, arg2);
        }

        public static WatchTimeRecorderSetAutoplayInitiatedParams decode(Decoder arg3) {
            WatchTimeRecorderSetAutoplayInitiatedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WatchTimeRecorderSetAutoplayInitiatedParams(arg3.readAndValidateDataHeader(WatchTimeRecorderSetAutoplayInitiatedParams.VERSION_ARRAY).elementsOrVersion);
                v1.value = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WatchTimeRecorderSetAutoplayInitiatedParams deserialize(ByteBuffer arg2) {
            return WatchTimeRecorderSetAutoplayInitiatedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WatchTimeRecorderSetAutoplayInitiatedParams deserialize(Message arg1) {
            return WatchTimeRecorderSetAutoplayInitiatedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WatchTimeRecorderSetAutoplayInitiatedParams.DEFAULT_STRUCT_INFO).encode(this.value, 8, 0);
        }
    }

    final class WatchTimeRecorderSetVideoDecoderNameParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String name;

        static {
            WatchTimeRecorderSetVideoDecoderNameParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WatchTimeRecorderSetVideoDecoderNameParams.DEFAULT_STRUCT_INFO = WatchTimeRecorderSetVideoDecoderNameParams.VERSION_ARRAY[0];
        }

        public WatchTimeRecorderSetVideoDecoderNameParams() {
            this(0);
        }

        private WatchTimeRecorderSetVideoDecoderNameParams(int arg2) {
            super(16, arg2);
        }

        public static WatchTimeRecorderSetVideoDecoderNameParams decode(Decoder arg3) {
            WatchTimeRecorderSetVideoDecoderNameParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WatchTimeRecorderSetVideoDecoderNameParams(arg3.readAndValidateDataHeader(WatchTimeRecorderSetVideoDecoderNameParams.VERSION_ARRAY).elementsOrVersion);
                v1.name = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WatchTimeRecorderSetVideoDecoderNameParams deserialize(ByteBuffer arg2) {
            return WatchTimeRecorderSetVideoDecoderNameParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WatchTimeRecorderSetVideoDecoderNameParams deserialize(Message arg1) {
            return WatchTimeRecorderSetVideoDecoderNameParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WatchTimeRecorderSetVideoDecoderNameParams.DEFAULT_STRUCT_INFO).encode(this.name, 8, false);
        }
    }

    final class WatchTimeRecorderUpdateUnderflowCountParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int count;

        static {
            WatchTimeRecorderUpdateUnderflowCountParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WatchTimeRecorderUpdateUnderflowCountParams.DEFAULT_STRUCT_INFO = WatchTimeRecorderUpdateUnderflowCountParams.VERSION_ARRAY[0];
        }

        public WatchTimeRecorderUpdateUnderflowCountParams() {
            this(0);
        }

        private WatchTimeRecorderUpdateUnderflowCountParams(int arg2) {
            super(16, arg2);
        }

        public static WatchTimeRecorderUpdateUnderflowCountParams decode(Decoder arg2) {
            WatchTimeRecorderUpdateUnderflowCountParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WatchTimeRecorderUpdateUnderflowCountParams(arg2.readAndValidateDataHeader(WatchTimeRecorderUpdateUnderflowCountParams.VERSION_ARRAY).elementsOrVersion);
                v1.count = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static WatchTimeRecorderUpdateUnderflowCountParams deserialize(ByteBuffer arg2) {
            return WatchTimeRecorderUpdateUnderflowCountParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WatchTimeRecorderUpdateUnderflowCountParams deserialize(Message arg1) {
            return WatchTimeRecorderUpdateUnderflowCountParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(WatchTimeRecorderUpdateUnderflowCountParams.DEFAULT_STRUCT_INFO).encode(this.count, 8);
        }
    }

    private static final int FINALIZE_WATCH_TIME_ORDINAL = 1;
    public static final Manager MANAGER = null;
    private static final int ON_ERROR_ORDINAL = 2;
    private static final int RECORD_WATCH_TIME_ORDINAL = 0;
    private static final int SET_AUDIO_DECODER_NAME_ORDINAL = 3;
    private static final int SET_AUTOPLAY_INITIATED_ORDINAL = 5;
    private static final int SET_VIDEO_DECODER_NAME_ORDINAL = 4;
    private static final int UPDATE_UNDERFLOW_COUNT_ORDINAL = 6;

    static {
        WatchTimeRecorder_Internal.MANAGER = new org.chromium.media.mojom.WatchTimeRecorder_Internal$1();
    }

    WatchTimeRecorder_Internal() {
        super();
    }
}

