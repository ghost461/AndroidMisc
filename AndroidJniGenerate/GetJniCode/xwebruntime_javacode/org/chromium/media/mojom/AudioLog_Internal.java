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

class AudioLog_Internal {
    final class org.chromium.media.mojom.AudioLog_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioLog_Internal$1() {
            super();
        }

        public AudioLog[] buildArray(int arg1) {
            return new AudioLog[arg1];
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

        public Stub buildStub(Core arg2, AudioLog arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioLog)arg2));
        }

        public String getName() {
            return "media::mojom::AudioLog";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioLogOnClosedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioLogOnClosedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioLogOnClosedParams.DEFAULT_STRUCT_INFO = AudioLogOnClosedParams.VERSION_ARRAY[0];
        }

        public AudioLogOnClosedParams() {
            this(0);
        }

        private AudioLogOnClosedParams(int arg2) {
            super(8, arg2);
        }

        public static AudioLogOnClosedParams decode(Decoder arg2) {
            AudioLogOnClosedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioLogOnClosedParams(arg2.readAndValidateDataHeader(AudioLogOnClosedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioLogOnClosedParams deserialize(ByteBuffer arg2) {
            return AudioLogOnClosedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioLogOnClosedParams deserialize(Message arg1) {
            return AudioLogOnClosedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioLogOnClosedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioLogOnCreatedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String deviceId;
        public AudioParameters params;

        static {
            AudioLogOnCreatedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            AudioLogOnCreatedParams.DEFAULT_STRUCT_INFO = AudioLogOnCreatedParams.VERSION_ARRAY[0];
        }

        public AudioLogOnCreatedParams() {
            this(0);
        }

        private AudioLogOnCreatedParams(int arg2) {
            super(24, arg2);
        }

        public static AudioLogOnCreatedParams decode(Decoder arg3) {
            AudioLogOnCreatedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioLogOnCreatedParams(arg3.readAndValidateDataHeader(AudioLogOnCreatedParams.VERSION_ARRAY).elementsOrVersion);
                v1.params = AudioParameters.decode(arg3.readPointer(8, false));
                v1.deviceId = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioLogOnCreatedParams deserialize(ByteBuffer arg2) {
            return AudioLogOnCreatedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioLogOnCreatedParams deserialize(Message arg1) {
            return AudioLogOnCreatedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(AudioLogOnCreatedParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.params, 8, false);
            arg4.encode(this.deviceId, 16, false);
        }
    }

    final class AudioLogOnErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioLogOnErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioLogOnErrorParams.DEFAULT_STRUCT_INFO = AudioLogOnErrorParams.VERSION_ARRAY[0];
        }

        public AudioLogOnErrorParams() {
            this(0);
        }

        private AudioLogOnErrorParams(int arg2) {
            super(8, arg2);
        }

        public static AudioLogOnErrorParams decode(Decoder arg2) {
            AudioLogOnErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioLogOnErrorParams(arg2.readAndValidateDataHeader(AudioLogOnErrorParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioLogOnErrorParams deserialize(ByteBuffer arg2) {
            return AudioLogOnErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioLogOnErrorParams deserialize(Message arg1) {
            return AudioLogOnErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioLogOnErrorParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioLogOnLogMessageParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String message;

        static {
            AudioLogOnLogMessageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioLogOnLogMessageParams.DEFAULT_STRUCT_INFO = AudioLogOnLogMessageParams.VERSION_ARRAY[0];
        }

        public AudioLogOnLogMessageParams() {
            this(0);
        }

        private AudioLogOnLogMessageParams(int arg2) {
            super(16, arg2);
        }

        public static AudioLogOnLogMessageParams decode(Decoder arg3) {
            AudioLogOnLogMessageParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioLogOnLogMessageParams(arg3.readAndValidateDataHeader(AudioLogOnLogMessageParams.VERSION_ARRAY).elementsOrVersion);
                v1.message = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioLogOnLogMessageParams deserialize(ByteBuffer arg2) {
            return AudioLogOnLogMessageParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioLogOnLogMessageParams deserialize(Message arg1) {
            return AudioLogOnLogMessageParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioLogOnLogMessageParams.DEFAULT_STRUCT_INFO).encode(this.message, 8, false);
        }
    }

    final class AudioLogOnSetVolumeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public double volume;

        static {
            AudioLogOnSetVolumeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioLogOnSetVolumeParams.DEFAULT_STRUCT_INFO = AudioLogOnSetVolumeParams.VERSION_ARRAY[0];
        }

        public AudioLogOnSetVolumeParams() {
            this(0);
        }

        private AudioLogOnSetVolumeParams(int arg2) {
            super(16, arg2);
        }

        public static AudioLogOnSetVolumeParams decode(Decoder arg4) {
            AudioLogOnSetVolumeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new AudioLogOnSetVolumeParams(arg4.readAndValidateDataHeader(AudioLogOnSetVolumeParams.VERSION_ARRAY).elementsOrVersion);
                v1.volume = arg4.readDouble(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static AudioLogOnSetVolumeParams deserialize(ByteBuffer arg2) {
            return AudioLogOnSetVolumeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioLogOnSetVolumeParams deserialize(Message arg1) {
            return AudioLogOnSetVolumeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioLogOnSetVolumeParams.DEFAULT_STRUCT_INFO).encode(this.volume, 8);
        }
    }

    final class AudioLogOnStartedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioLogOnStartedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioLogOnStartedParams.DEFAULT_STRUCT_INFO = AudioLogOnStartedParams.VERSION_ARRAY[0];
        }

        public AudioLogOnStartedParams() {
            this(0);
        }

        private AudioLogOnStartedParams(int arg2) {
            super(8, arg2);
        }

        public static AudioLogOnStartedParams decode(Decoder arg2) {
            AudioLogOnStartedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioLogOnStartedParams(arg2.readAndValidateDataHeader(AudioLogOnStartedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioLogOnStartedParams deserialize(ByteBuffer arg2) {
            return AudioLogOnStartedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioLogOnStartedParams deserialize(Message arg1) {
            return AudioLogOnStartedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioLogOnStartedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioLogOnStoppedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioLogOnStoppedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioLogOnStoppedParams.DEFAULT_STRUCT_INFO = AudioLogOnStoppedParams.VERSION_ARRAY[0];
        }

        public AudioLogOnStoppedParams() {
            this(0);
        }

        private AudioLogOnStoppedParams(int arg2) {
            super(8, arg2);
        }

        public static AudioLogOnStoppedParams decode(Decoder arg2) {
            AudioLogOnStoppedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioLogOnStoppedParams(arg2.readAndValidateDataHeader(AudioLogOnStoppedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioLogOnStoppedParams deserialize(ByteBuffer arg2) {
            return AudioLogOnStoppedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioLogOnStoppedParams deserialize(Message arg1) {
            return AudioLogOnStoppedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioLogOnStoppedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioLog$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onClosed() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioLogOnClosedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void onCreated(AudioParameters arg4, String arg5) {
            AudioLogOnCreatedParams v0 = new AudioLogOnCreatedParams();
            v0.params = arg4;
            v0.deviceId = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onError() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioLogOnErrorParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void onLogMessage(String arg5) {
            AudioLogOnLogMessageParams v0 = new AudioLogOnLogMessageParams();
            v0.message = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void onSetVolume(double arg4) {
            AudioLogOnSetVolumeParams v0 = new AudioLogOnSetVolumeParams();
            v0.volume = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void onStarted() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioLogOnStartedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onStopped() {
            this.getProxyHandler().getMessageReceiver().accept(new AudioLogOnStoppedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioLog arg2) {
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
                    goto label_51;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_44;
                    }
                    case 1: {
                        goto label_39;
                    }
                    case 2: {
                        goto label_34;
                    }
                    case 3: {
                        goto label_29;
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
            label_34:
                AudioLogOnStoppedParams.deserialize(v6_1.getPayload());
                this.getImpl().onStopped();
                return 1;
            label_18:
                this.getImpl().onSetVolume(AudioLogOnSetVolumeParams.deserialize(v6_1.getPayload()).volume);
                return 1;
            label_39:
                AudioLogOnStartedParams.deserialize(v6_1.getPayload());
                this.getImpl().onStarted();
                return 1;
            label_24:
                AudioLogOnErrorParams.deserialize(v6_1.getPayload());
                this.getImpl().onError();
                return 1;
            label_44:
                AudioLogOnCreatedParams v6_2 = AudioLogOnCreatedParams.deserialize(v6_1.getPayload());
                this.getImpl().onCreated(v6_2.params, v6_2.deviceId);
                return 1;
            label_12:
                this.getImpl().onLogMessage(AudioLogOnLogMessageParams.deserialize(v6_1.getPayload()).message);
                return 1;
            label_29:
                AudioLogOnClosedParams.deserialize(v6_1.getPayload());
                this.getImpl().onClosed();
                return 1;
            label_51:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioLog_Internal.MANAGER, v6_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioLog_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_CLOSED_ORDINAL = 3;
    private static final int ON_CREATED_ORDINAL = 0;
    private static final int ON_ERROR_ORDINAL = 4;
    private static final int ON_LOG_MESSAGE_ORDINAL = 6;
    private static final int ON_SET_VOLUME_ORDINAL = 5;
    private static final int ON_STARTED_ORDINAL = 1;
    private static final int ON_STOPPED_ORDINAL = 2;

    static {
        AudioLog_Internal.MANAGER = new org.chromium.media.mojom.AudioLog_Internal$1();
    }

    AudioLog_Internal() {
        super();
    }
}

