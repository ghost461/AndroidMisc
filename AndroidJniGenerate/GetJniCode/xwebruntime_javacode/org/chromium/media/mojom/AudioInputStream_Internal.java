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

class AudioInputStream_Internal {
    final class org.chromium.media.mojom.AudioInputStream_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioInputStream_Internal$1() {
            super();
        }

        public AudioInputStream[] buildArray(int arg1) {
            return new AudioInputStream[arg1];
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

        public Stub buildStub(Core arg2, AudioInputStream arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioInputStream)arg2));
        }

        public String getName() {
            return "media::mojom::AudioInputStream";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioInputStreamRecordParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioInputStreamRecordParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioInputStreamRecordParams.DEFAULT_STRUCT_INFO = AudioInputStreamRecordParams.VERSION_ARRAY[0];
        }

        public AudioInputStreamRecordParams() {
            this(0);
        }

        private AudioInputStreamRecordParams(int arg2) {
            super(8, arg2);
        }

        public static AudioInputStreamRecordParams decode(Decoder arg2) {
            AudioInputStreamRecordParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioInputStreamRecordParams(arg2.readAndValidateDataHeader(AudioInputStreamRecordParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioInputStreamRecordParams deserialize(ByteBuffer arg2) {
            return AudioInputStreamRecordParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioInputStreamRecordParams deserialize(Message arg1) {
            return AudioInputStreamRecordParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioInputStreamRecordParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioInputStreamSetVolumeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public double volume;

        static {
            AudioInputStreamSetVolumeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioInputStreamSetVolumeParams.DEFAULT_STRUCT_INFO = AudioInputStreamSetVolumeParams.VERSION_ARRAY[0];
        }

        public AudioInputStreamSetVolumeParams() {
            this(0);
        }

        private AudioInputStreamSetVolumeParams(int arg2) {
            super(16, arg2);
        }

        public static AudioInputStreamSetVolumeParams decode(Decoder arg4) {
            AudioInputStreamSetVolumeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new AudioInputStreamSetVolumeParams(arg4.readAndValidateDataHeader(AudioInputStreamSetVolumeParams.VERSION_ARRAY).elementsOrVersion);
                v1.volume = arg4.readDouble(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static AudioInputStreamSetVolumeParams deserialize(ByteBuffer arg2) {
            return AudioInputStreamSetVolumeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioInputStreamSetVolumeParams deserialize(Message arg1) {
            return AudioInputStreamSetVolumeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioInputStreamSetVolumeParams.DEFAULT_STRUCT_INFO).encode(this.volume, 8);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioInputStream$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void record() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioInputStreamRecordParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void setVolume(double arg4) {
            AudioInputStreamSetVolumeParams v0 = new AudioInputStreamSetVolumeParams();
            v0.volume = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioInputStream arg2) {
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
                    goto label_23;
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
                AudioInputStreamRecordParams.deserialize(v6_1.getPayload());
                this.getImpl().record();
                return 1;
            label_12:
                this.getImpl().setVolume(AudioInputStreamSetVolumeParams.deserialize(v6_1.getPayload()).volume);
                return 1;
            label_23:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioInputStream_Internal.MANAGER, v6_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioInputStream_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int RECORD_ORDINAL = 0;
    private static final int SET_VOLUME_ORDINAL = 1;

    static {
        AudioInputStream_Internal.MANAGER = new org.chromium.media.mojom.AudioInputStream_Internal$1();
    }

    AudioInputStream_Internal() {
        super();
    }
}

