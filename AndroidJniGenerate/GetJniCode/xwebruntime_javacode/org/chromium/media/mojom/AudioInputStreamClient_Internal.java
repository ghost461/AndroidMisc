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

class AudioInputStreamClient_Internal {
    final class org.chromium.media.mojom.AudioInputStreamClient_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioInputStreamClient_Internal$1() {
            super();
        }

        public AudioInputStreamClient[] buildArray(int arg1) {
            return new AudioInputStreamClient[arg1];
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

        public Stub buildStub(Core arg2, AudioInputStreamClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioInputStreamClient)arg2));
        }

        public String getName() {
            return "media::mojom::AudioInputStreamClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioInputStreamClientOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioInputStreamClientOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioInputStreamClientOnErrorParams.DEFAULT_STRUCT_INFO = AudioInputStreamClientOnErrorParams.VERSION_ARRAY[0];
        }

        public AudioInputStreamClientOnErrorParams() {
            this(0);
        }

        private AudioInputStreamClientOnErrorParams(int arg2) {
            super(8, arg2);
        }

        public static AudioInputStreamClientOnErrorParams decode(Decoder arg2) {
            AudioInputStreamClientOnErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioInputStreamClientOnErrorParams(arg2.readAndValidateDataHeader(AudioInputStreamClientOnErrorParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioInputStreamClientOnErrorParams deserialize(ByteBuffer arg2) {
            return AudioInputStreamClientOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioInputStreamClientOnErrorParams deserialize(Message arg1) {
            return AudioInputStreamClientOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioInputStreamClientOnErrorParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioInputStreamClientOnMutedStateChangedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isMuted;

        static {
            AudioInputStreamClientOnMutedStateChangedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioInputStreamClientOnMutedStateChangedParams.DEFAULT_STRUCT_INFO = AudioInputStreamClientOnMutedStateChangedParams.VERSION_ARRAY[0];
        }

        public AudioInputStreamClientOnMutedStateChangedParams() {
            this(0);
        }

        private AudioInputStreamClientOnMutedStateChangedParams(int arg2) {
            super(16, arg2);
        }

        public static AudioInputStreamClientOnMutedStateChangedParams decode(Decoder arg3) {
            AudioInputStreamClientOnMutedStateChangedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioInputStreamClientOnMutedStateChangedParams(arg3.readAndValidateDataHeader(AudioInputStreamClientOnMutedStateChangedParams.VERSION_ARRAY).elementsOrVersion);
                v1.isMuted = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioInputStreamClientOnMutedStateChangedParams deserialize(ByteBuffer arg2) {
            return AudioInputStreamClientOnMutedStateChangedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioInputStreamClientOnMutedStateChangedParams deserialize(Message arg1) {
            return AudioInputStreamClientOnMutedStateChangedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioInputStreamClientOnMutedStateChangedParams.DEFAULT_STRUCT_INFO).encode(this.isMuted, 8, 0);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioInputStreamClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onError() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioInputStreamClientOnErrorParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onMutedStateChanged(boolean arg5) {
            AudioInputStreamClientOnMutedStateChangedParams v0 = new AudioInputStreamClientOnMutedStateChangedParams();
            v0.isMuted = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioInputStreamClient arg2) {
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
                AudioInputStreamClientOnErrorParams.deserialize(v4_1.getPayload());
                this.getImpl().onError();
                return 1;
            label_12:
                this.getImpl().onMutedStateChanged(AudioInputStreamClientOnMutedStateChangedParams.deserialize(v4_1.getPayload()).isMuted);
                return 1;
            label_23:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioInputStreamClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioInputStreamClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_ERROR_ORDINAL = 0;
    private static final int ON_MUTED_STATE_CHANGED_ORDINAL = 1;

    static {
        AudioInputStreamClient_Internal.MANAGER = new org.chromium.media.mojom.AudioInputStreamClient_Internal$1();
    }

    AudioInputStreamClient_Internal() {
        super();
    }
}

