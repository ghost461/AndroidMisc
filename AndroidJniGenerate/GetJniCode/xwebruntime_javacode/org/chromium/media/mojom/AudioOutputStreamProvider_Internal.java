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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class AudioOutputStreamProvider_Internal {
    final class org.chromium.media.mojom.AudioOutputStreamProvider_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioOutputStreamProvider_Internal$1() {
            super();
        }

        public AudioOutputStreamProvider[] buildArray(int arg1) {
            return new AudioOutputStreamProvider[arg1];
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

        public Stub buildStub(Core arg2, AudioOutputStreamProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioOutputStreamProvider)arg2));
        }

        public String getName() {
            return "media::mojom::AudioOutputStreamProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioOutputStreamProviderAcquireParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public AudioOutputStreamClient client;
        public InterfaceRequest outputStream;
        public AudioParameters params;

        static {
            AudioOutputStreamProviderAcquireParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            AudioOutputStreamProviderAcquireParams.DEFAULT_STRUCT_INFO = AudioOutputStreamProviderAcquireParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamProviderAcquireParams() {
            this(0);
        }

        private AudioOutputStreamProviderAcquireParams(int arg2) {
            super(0x20, arg2);
        }

        public static AudioOutputStreamProviderAcquireParams decode(Decoder arg4) {
            AudioOutputStreamProviderAcquireParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamProviderAcquireParams(arg4.readAndValidateDataHeader(AudioOutputStreamProviderAcquireParams.VERSION_ARRAY).elementsOrVersion);
                v1.outputStream = arg4.readInterfaceRequest(8, false);
                v1.client = arg4.readServiceInterface(12, false, AudioOutputStreamClient.MANAGER);
                v1.params = AudioParameters.decode(arg4.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamProviderAcquireParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamProviderAcquireParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamProviderAcquireParams deserialize(Message arg1) {
            return AudioOutputStreamProviderAcquireParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(AudioOutputStreamProviderAcquireParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.outputStream, 8, false);
            arg5.encode(this.client, 12, false, AudioOutputStreamClient.MANAGER);
            arg5.encode(this.params, 24, false);
        }
    }

    final class AudioOutputStreamProviderAcquireResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AudioDataPipe dataPipe;

        static {
            AudioOutputStreamProviderAcquireResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioOutputStreamProviderAcquireResponseParams.DEFAULT_STRUCT_INFO = AudioOutputStreamProviderAcquireResponseParams.VERSION_ARRAY[0];
        }

        public AudioOutputStreamProviderAcquireResponseParams() {
            this(0);
        }

        private AudioOutputStreamProviderAcquireResponseParams(int arg2) {
            super(16, arg2);
        }

        public static AudioOutputStreamProviderAcquireResponseParams decode(Decoder arg3) {
            AudioOutputStreamProviderAcquireResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioOutputStreamProviderAcquireResponseParams(arg3.readAndValidateDataHeader(AudioOutputStreamProviderAcquireResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.dataPipe = AudioDataPipe.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioOutputStreamProviderAcquireResponseParams deserialize(ByteBuffer arg2) {
            return AudioOutputStreamProviderAcquireResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioOutputStreamProviderAcquireResponseParams deserialize(Message arg1) {
            return AudioOutputStreamProviderAcquireResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioOutputStreamProviderAcquireResponseParams.DEFAULT_STRUCT_INFO).encode(this.dataPipe, 8, false);
        }
    }

    class AudioOutputStreamProviderAcquireResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final AcquireResponse mCallback;

        AudioOutputStreamProviderAcquireResponseParamsForwardToCallback(AcquireResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(AudioOutputStreamProviderAcquireResponseParams.deserialize(v4.getPayload()).dataPipe);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class AudioOutputStreamProviderAcquireResponseParamsProxyToResponder implements AcquireResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        AudioOutputStreamProviderAcquireResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((AudioDataPipe)arg1));
        }

        public void call(AudioDataPipe arg7) {
            AudioOutputStreamProviderAcquireResponseParams v0 = new AudioOutputStreamProviderAcquireResponseParams();
            v0.dataPipe = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioOutputStreamProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void acquire(InterfaceRequest arg6, AudioOutputStreamClient arg7, AudioParameters arg8, AcquireResponse arg9) {
            AudioOutputStreamProviderAcquireParams v0 = new AudioOutputStreamProviderAcquireParams();
            v0.outputStream = arg6;
            v0.client = arg7;
            v0.params = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new AudioOutputStreamProviderAcquireResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioOutputStreamProvider arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                if(v1.getType() != -2) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioOutputStreamProvider_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
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

                switch(v1.getType()) {
                    case -1: {
                        goto label_22;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_22:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioOutputStreamProvider_Internal.MANAGER, v11_1, arg12);
            label_10:
                AudioOutputStreamProviderAcquireParams v11_2 = AudioOutputStreamProviderAcquireParams.deserialize(v11_1.getPayload());
                this.getImpl().acquire(v11_2.outputStream, v11_2.client, v11_2.params, new AudioOutputStreamProviderAcquireResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    private static final int ACQUIRE_ORDINAL;
    public static final Manager MANAGER;

    static {
        AudioOutputStreamProvider_Internal.MANAGER = new org.chromium.media.mojom.AudioOutputStreamProvider_Internal$1();
    }

    AudioOutputStreamProvider_Internal() {
        super();
    }
}

