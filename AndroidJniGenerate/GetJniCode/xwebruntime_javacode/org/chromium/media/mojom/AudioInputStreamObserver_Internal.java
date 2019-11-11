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

class AudioInputStreamObserver_Internal {
    final class org.chromium.media.mojom.AudioInputStreamObserver_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioInputStreamObserver_Internal$1() {
            super();
        }

        public AudioInputStreamObserver[] buildArray(int arg1) {
            return new AudioInputStreamObserver[arg1];
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

        public Stub buildStub(Core arg2, AudioInputStreamObserver arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioInputStreamObserver)arg2));
        }

        public String getName() {
            return "media::mojom::AudioInputStreamObserver";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioInputStreamObserverDidStartRecordingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioInputStreamObserverDidStartRecordingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioInputStreamObserverDidStartRecordingParams.DEFAULT_STRUCT_INFO = AudioInputStreamObserverDidStartRecordingParams.VERSION_ARRAY[0];
        }

        public AudioInputStreamObserverDidStartRecordingParams() {
            this(0);
        }

        private AudioInputStreamObserverDidStartRecordingParams(int arg2) {
            super(8, arg2);
        }

        public static AudioInputStreamObserverDidStartRecordingParams decode(Decoder arg2) {
            AudioInputStreamObserverDidStartRecordingParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioInputStreamObserverDidStartRecordingParams(arg2.readAndValidateDataHeader(AudioInputStreamObserverDidStartRecordingParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioInputStreamObserverDidStartRecordingParams deserialize(ByteBuffer arg2) {
            return AudioInputStreamObserverDidStartRecordingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioInputStreamObserverDidStartRecordingParams deserialize(Message arg1) {
            return AudioInputStreamObserverDidStartRecordingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioInputStreamObserverDidStartRecordingParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioInputStreamObserver$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void didStartRecording() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioInputStreamObserverDidStartRecordingParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioInputStreamObserver arg2) {
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

                    AudioInputStreamObserverDidStartRecordingParams.deserialize(v4_1.getPayload());
                    this.getImpl().didStartRecording();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioInputStreamObserver_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioInputStreamObserver_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int DID_START_RECORDING_ORDINAL;
    public static final Manager MANAGER;

    static {
        AudioInputStreamObserver_Internal.MANAGER = new org.chromium.media.mojom.AudioInputStreamObserver_Internal$1();
    }

    AudioInputStreamObserver_Internal() {
        super();
    }
}

