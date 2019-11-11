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

class AudioOutputStream_Internal {
    final class org.chromium.media.mojom.AudioOutputStream_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioOutputStream_Internal$1() {
            super();
        }

        public AudioOutputStream[] buildArray(int arg1) {
            return new AudioOutputStream[arg1];
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

        public Stub buildStub(Core arg2, AudioOutputStream arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioOutputStream)arg2));
        }

        public String getName() {
            return "media::mojom::AudioOutputStream";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioOutputStreamPauseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioOutputStreamPauseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioOutputStreamPauseParams.DEFAULT_STRUCT_INFO = AudioOutputStreamPauseParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamPauseParams() {
            this(0);
        }

        private AudioOutputStreamPauseParams(int arg2) {
            super(8, arg2);
        }

        public static AudioOutputStreamPauseParams decode(Decoder arg2) {
            AudioOutputStreamPauseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamPauseParams(arg2.readAndValidateDataHeader(AudioOutputStreamPauseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamPauseParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamPauseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamPauseParams deserialize(Message arg1) {
            return AudioOutputStreamPauseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioOutputStreamPauseParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioOutputStreamPlayParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioOutputStreamPlayParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioOutputStreamPlayParams.DEFAULT_STRUCT_INFO = AudioOutputStreamPlayParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamPlayParams() {
            this(0);
        }

        private AudioOutputStreamPlayParams(int arg2) {
            super(8, arg2);
        }

        public static AudioOutputStreamPlayParams decode(Decoder arg2) {
            AudioOutputStreamPlayParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamPlayParams(arg2.readAndValidateDataHeader(AudioOutputStreamPlayParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamPlayParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamPlayParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamPlayParams deserialize(Message arg1) {
            return AudioOutputStreamPlayParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioOutputStreamPlayParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioOutputStreamSetVolumeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public double volume;

        static {
            AudioOutputStreamSetVolumeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioOutputStreamSetVolumeParams.DEFAULT_STRUCT_INFO = AudioOutputStreamSetVolumeParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamSetVolumeParams() {
            this(0);
        }

        private AudioOutputStreamSetVolumeParams(int arg2) {
            super(16, arg2);
        }

        public static AudioOutputStreamSetVolumeParams decode(Decoder arg4) {
            AudioOutputStreamSetVolumeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamSetVolumeParams(arg4.readAndValidateDataHeader(AudioOutputStreamSetVolumeParams.VERSION_ARRAY).elementsOrVersion);
                v1.volume = arg4.readDouble(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamSetVolumeParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamSetVolumeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamSetVolumeParams deserialize(Message arg1) {
            return AudioOutputStreamSetVolumeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioOutputStreamSetVolumeParams.DEFAULT_STRUCT_INFO).encode(this.volume, 8);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioOutputStream$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void pause() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioOutputStreamPauseParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void play() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioOutputStreamPlayParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void setVolume(double arg4) {
            AudioOutputStreamSetVolumeParams v0 = new AudioOutputStreamSetVolumeParams();
            v0.volume = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioOutputStream arg2) {
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
                    goto label_28;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_23;
                    }
                    case 1: {
                        goto label_18;
                    }
                    case 2: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                AudioOutputStreamPauseParams.deserialize(v6_1.getPayload());
                this.getImpl().pause();
                return 1;
            label_23:
                AudioOutputStreamPlayParams.deserialize(v6_1.getPayload());
                this.getImpl().play();
                return 1;
            label_12:
                this.getImpl().setVolume(AudioOutputStreamSetVolumeParams.deserialize(v6_1.getPayload()).volume);
                return 1;
            label_28:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioOutputStream_Internal.MANAGER, v6_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioOutputStream_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int PAUSE_ORDINAL = 1;
    private static final int PLAY_ORDINAL = 0;
    private static final int SET_VOLUME_ORDINAL = 2;

    static {
        AudioOutputStream_Internal.MANAGER = new org.chromium.media.mojom.AudioOutputStream_Internal$1();
    }

    AudioOutputStream_Internal() {
        super();
    }
}

