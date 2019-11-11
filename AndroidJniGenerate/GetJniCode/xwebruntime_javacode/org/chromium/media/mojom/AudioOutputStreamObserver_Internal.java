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

class AudioOutputStreamObserver_Internal {
    final class org.chromium.media.mojom.AudioOutputStreamObserver_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioOutputStreamObserver_Internal$1() {
            super();
        }

        public AudioOutputStreamObserver[] buildArray(int arg1) {
            return new AudioOutputStreamObserver[arg1];
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

        public Stub buildStub(Core arg2, AudioOutputStreamObserver arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioOutputStreamObserver)arg2));
        }

        public String getName() {
            return "media::mojom::AudioOutputStreamObserver";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioOutputStreamObserverDidChangeAudibleStateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isAudible;

        static {
            AudioOutputStreamObserverDidChangeAudibleStateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioOutputStreamObserverDidChangeAudibleStateParams.DEFAULT_STRUCT_INFO = AudioOutputStreamObserverDidChangeAudibleStateParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamObserverDidChangeAudibleStateParams() {
            this(0);
        }

        private AudioOutputStreamObserverDidChangeAudibleStateParams(int arg2) {
            super(16, arg2);
        }

        public static AudioOutputStreamObserverDidChangeAudibleStateParams decode(Decoder arg3) {
            AudioOutputStreamObserverDidChangeAudibleStateParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamObserverDidChangeAudibleStateParams(arg3.readAndValidateDataHeader(AudioOutputStreamObserverDidChangeAudibleStateParams.VERSION_ARRAY).elementsOrVersion);
                v1.isAudible = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamObserverDidChangeAudibleStateParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamObserverDidChangeAudibleStateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamObserverDidChangeAudibleStateParams deserialize(Message arg1) {
            return AudioOutputStreamObserverDidChangeAudibleStateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioOutputStreamObserverDidChangeAudibleStateParams.DEFAULT_STRUCT_INFO).encode(this.isAudible, 8, 0);
        }
    }

    final class AudioOutputStreamObserverDidStartPlayingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioOutputStreamObserverDidStartPlayingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioOutputStreamObserverDidStartPlayingParams.DEFAULT_STRUCT_INFO = AudioOutputStreamObserverDidStartPlayingParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamObserverDidStartPlayingParams() {
            this(0);
        }

        private AudioOutputStreamObserverDidStartPlayingParams(int arg2) {
            super(8, arg2);
        }

        public static AudioOutputStreamObserverDidStartPlayingParams decode(Decoder arg2) {
            AudioOutputStreamObserverDidStartPlayingParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamObserverDidStartPlayingParams(arg2.readAndValidateDataHeader(AudioOutputStreamObserverDidStartPlayingParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamObserverDidStartPlayingParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamObserverDidStartPlayingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamObserverDidStartPlayingParams deserialize(Message arg1) {
            return AudioOutputStreamObserverDidStartPlayingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioOutputStreamObserverDidStartPlayingParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioOutputStreamObserverDidStopPlayingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioOutputStreamObserverDidStopPlayingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioOutputStreamObserverDidStopPlayingParams.DEFAULT_STRUCT_INFO = AudioOutputStreamObserverDidStopPlayingParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamObserverDidStopPlayingParams() {
            this(0);
        }

        private AudioOutputStreamObserverDidStopPlayingParams(int arg2) {
            super(8, arg2);
        }

        public static AudioOutputStreamObserverDidStopPlayingParams decode(Decoder arg2) {
            AudioOutputStreamObserverDidStopPlayingParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamObserverDidStopPlayingParams(arg2.readAndValidateDataHeader(AudioOutputStreamObserverDidStopPlayingParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamObserverDidStopPlayingParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamObserverDidStopPlayingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamObserverDidStopPlayingParams deserialize(Message arg1) {
            return AudioOutputStreamObserverDidStopPlayingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioOutputStreamObserverDidStopPlayingParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioOutputStreamObserver$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void didChangeAudibleState(boolean arg5) {
            AudioOutputStreamObserverDidChangeAudibleStateParams v0 = new AudioOutputStreamObserverDidChangeAudibleStateParams();
            v0.isAudible = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void didStartPlaying() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioOutputStreamObserverDidStartPlayingParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void didStopPlaying() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioOutputStreamObserverDidStopPlayingParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioOutputStreamObserver arg2) {
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
                AudioOutputStreamObserverDidStopPlayingParams.deserialize(v4_1.getPayload());
                this.getImpl().didStopPlaying();
                return 1;
            label_23:
                AudioOutputStreamObserverDidStartPlayingParams.deserialize(v4_1.getPayload());
                this.getImpl().didStartPlaying();
                return 1;
            label_12:
                this.getImpl().didChangeAudibleState(AudioOutputStreamObserverDidChangeAudibleStateParams.deserialize(v4_1.getPayload()).isAudible);
                return 1;
            label_28:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioOutputStreamObserver_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioOutputStreamObserver_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int DID_CHANGE_AUDIBLE_STATE_ORDINAL = 2;
    private static final int DID_START_PLAYING_ORDINAL = 0;
    private static final int DID_STOP_PLAYING_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        AudioOutputStreamObserver_Internal.MANAGER = new org.chromium.media.mojom.AudioOutputStreamObserver_Internal$1();
    }

    AudioOutputStreamObserver_Internal() {
        super();
    }
}

