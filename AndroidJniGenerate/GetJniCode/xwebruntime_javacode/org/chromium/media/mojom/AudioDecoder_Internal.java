package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.InvalidHandle;

class AudioDecoder_Internal {
    final class org.chromium.media.mojom.AudioDecoder_Internal$1 extends Manager {
        org.chromium.media.mojom.AudioDecoder_Internal$1() {
            super();
        }

        public AudioDecoder[] buildArray(int arg1) {
            return new AudioDecoder[arg1];
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

        public Stub buildStub(Core arg2, AudioDecoder arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AudioDecoder)arg2));
        }

        public String getName() {
            return "media::mojom::AudioDecoder";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AudioDecoderConstructParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AssociatedInterfaceNotSupported client;

        static {
            AudioDecoderConstructParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioDecoderConstructParams.DEFAULT_STRUCT_INFO = AudioDecoderConstructParams.VERSION_ARRAY[0];
        }

        public AudioDecoderConstructParams() {
            this(0);
        }

        private AudioDecoderConstructParams(int arg2) {
            super(16, arg2);
        }

        public static AudioDecoderConstructParams decode(Decoder arg3) {
            AudioDecoderConstructParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioDecoderConstructParams(arg3.readAndValidateDataHeader(AudioDecoderConstructParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg3.readAssociatedServiceInterfaceNotSupported(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderConstructParams deserialize(ByteBuffer arg2) {
            return AudioDecoderConstructParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderConstructParams deserialize(Message arg1) {
            return AudioDecoderConstructParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioDecoderConstructParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false);
        }
    }

    final class AudioDecoderDecodeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public DecoderBuffer buffer;

        static {
            AudioDecoderDecodeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioDecoderDecodeParams.DEFAULT_STRUCT_INFO = AudioDecoderDecodeParams.VERSION_ARRAY[0];
        }

        public AudioDecoderDecodeParams() {
            this(0);
        }

        private AudioDecoderDecodeParams(int arg2) {
            super(16, arg2);
        }

        public static AudioDecoderDecodeParams decode(Decoder arg3) {
            AudioDecoderDecodeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioDecoderDecodeParams(arg3.readAndValidateDataHeader(AudioDecoderDecodeParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = DecoderBuffer.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderDecodeParams deserialize(ByteBuffer arg2) {
            return AudioDecoderDecodeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderDecodeParams deserialize(Message arg1) {
            return AudioDecoderDecodeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioDecoderDecodeParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8, false);
        }
    }

    final class AudioDecoderDecodeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int status;

        static {
            AudioDecoderDecodeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioDecoderDecodeResponseParams.DEFAULT_STRUCT_INFO = AudioDecoderDecodeResponseParams.VERSION_ARRAY[0];
        }

        public AudioDecoderDecodeResponseParams() {
            this(0);
        }

        private AudioDecoderDecodeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static AudioDecoderDecodeResponseParams decode(Decoder arg2) {
            AudioDecoderDecodeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioDecoderDecodeResponseParams(arg2.readAndValidateDataHeader(AudioDecoderDecodeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg2.readInt(8);
                DecodeStatus.validate(v1.status);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderDecodeResponseParams deserialize(ByteBuffer arg2) {
            return AudioDecoderDecodeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderDecodeResponseParams deserialize(Message arg1) {
            return AudioDecoderDecodeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(AudioDecoderDecodeResponseParams.DEFAULT_STRUCT_INFO).encode(this.status, 8);
        }
    }

    class AudioDecoderDecodeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DecodeResponse mCallback;

        AudioDecoderDecodeResponseParamsForwardToCallback(DecodeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(AudioDecoderDecodeResponseParams.deserialize(v5.getPayload()).status));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class AudioDecoderDecodeResponseParamsProxyToResponder implements DecodeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        AudioDecoderDecodeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            AudioDecoderDecodeResponseParams v0 = new AudioDecoderDecodeResponseParams();
            v0.status = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class AudioDecoderInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int cdmId;
        public AudioDecoderConfig config;

        static {
            AudioDecoderInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            AudioDecoderInitializeParams.DEFAULT_STRUCT_INFO = AudioDecoderInitializeParams.VERSION_ARRAY[0];
        }

        public AudioDecoderInitializeParams() {
            this(0);
        }

        private AudioDecoderInitializeParams(int arg2) {
            super(24, arg2);
        }

        public static AudioDecoderInitializeParams decode(Decoder arg3) {
            AudioDecoderInitializeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioDecoderInitializeParams(arg3.readAndValidateDataHeader(AudioDecoderInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.config = AudioDecoderConfig.decode(arg3.readPointer(8, false));
                v1.cdmId = arg3.readInt(16);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderInitializeParams deserialize(ByteBuffer arg2) {
            return AudioDecoderInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderInitializeParams deserialize(Message arg1) {
            return AudioDecoderInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(AudioDecoderInitializeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.config, 8, false);
            arg4.encode(this.cdmId, 16);
        }
    }

    final class AudioDecoderInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean needsBitstreamConversion;
        public boolean success;

        static {
            AudioDecoderInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioDecoderInitializeResponseParams.DEFAULT_STRUCT_INFO = AudioDecoderInitializeResponseParams.VERSION_ARRAY[0];
        }

        public AudioDecoderInitializeResponseParams() {
            this(0);
        }

        private AudioDecoderInitializeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static AudioDecoderInitializeResponseParams decode(Decoder arg3) {
            AudioDecoderInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioDecoderInitializeResponseParams(arg3.readAndValidateDataHeader(AudioDecoderInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
                v1.needsBitstreamConversion = arg3.readBoolean(8, 1);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderInitializeResponseParams deserialize(ByteBuffer arg2) {
            return AudioDecoderInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderInitializeResponseParams deserialize(Message arg1) {
            return AudioDecoderInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(AudioDecoderInitializeResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.success, 8, 0);
            arg4.encode(this.needsBitstreamConversion, 8, 1);
        }
    }

    class AudioDecoderInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        AudioDecoderInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                AudioDecoderInitializeResponseParams v5_1 = AudioDecoderInitializeResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Boolean.valueOf(v5_1.success), Boolean.valueOf(v5_1.needsBitstreamConversion));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class AudioDecoderInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        AudioDecoderInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg6, Boolean arg7) {
            AudioDecoderInitializeResponseParams v0 = new AudioDecoderInitializeResponseParams();
            v0.success = arg6.booleanValue();
            v0.needsBitstreamConversion = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Boolean)arg1), ((Boolean)arg2));
        }
    }

    final class AudioDecoderResetParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioDecoderResetParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioDecoderResetParams.DEFAULT_STRUCT_INFO = AudioDecoderResetParams.VERSION_ARRAY[0];
        }

        public AudioDecoderResetParams() {
            this(0);
        }

        private AudioDecoderResetParams(int arg2) {
            super(8, arg2);
        }

        public static AudioDecoderResetParams decode(Decoder arg2) {
            AudioDecoderResetParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioDecoderResetParams(arg2.readAndValidateDataHeader(AudioDecoderResetParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderResetParams deserialize(ByteBuffer arg2) {
            return AudioDecoderResetParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderResetParams deserialize(Message arg1) {
            return AudioDecoderResetParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioDecoderResetParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AudioDecoderResetResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AudioDecoderResetResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AudioDecoderResetResponseParams.DEFAULT_STRUCT_INFO = AudioDecoderResetResponseParams.VERSION_ARRAY[0];
        }

        public AudioDecoderResetResponseParams() {
            this(0);
        }

        private AudioDecoderResetResponseParams(int arg2) {
            super(8, arg2);
        }

        public static AudioDecoderResetResponseParams decode(Decoder arg2) {
            AudioDecoderResetResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AudioDecoderResetResponseParams(arg2.readAndValidateDataHeader(AudioDecoderResetResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderResetResponseParams deserialize(ByteBuffer arg2) {
            return AudioDecoderResetResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderResetResponseParams deserialize(Message arg1) {
            return AudioDecoderResetResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AudioDecoderResetResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class AudioDecoderResetResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ResetResponse mCallback;

        AudioDecoderResetResponseParamsForwardToCallback(ResetResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class AudioDecoderResetResponseParamsProxyToResponder implements ResetResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        AudioDecoderResetResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new AudioDecoderResetResponseParams().serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }
    }

    final class AudioDecoderSetDataSourceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ConsumerHandle receivePipe;

        static {
            AudioDecoderSetDataSourceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AudioDecoderSetDataSourceParams.DEFAULT_STRUCT_INFO = AudioDecoderSetDataSourceParams.VERSION_ARRAY[0];
        }

        public AudioDecoderSetDataSourceParams() {
            this(0);
        }

        private AudioDecoderSetDataSourceParams(int arg2) {
            super(16, arg2);
            this.receivePipe = InvalidHandle.INSTANCE;
        }

        public static AudioDecoderSetDataSourceParams decode(Decoder arg3) {
            AudioDecoderSetDataSourceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AudioDecoderSetDataSourceParams(arg3.readAndValidateDataHeader(AudioDecoderSetDataSourceParams.VERSION_ARRAY).elementsOrVersion);
                v1.receivePipe = arg3.readConsumerHandle(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AudioDecoderSetDataSourceParams deserialize(ByteBuffer arg2) {
            return AudioDecoderSetDataSourceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AudioDecoderSetDataSourceParams deserialize(Message arg1) {
            return AudioDecoderSetDataSourceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AudioDecoderSetDataSourceParams.DEFAULT_STRUCT_INFO).encode(this.receivePipe, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AudioDecoder$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void construct(AssociatedInterfaceNotSupported arg5) {
            AudioDecoderConstructParams v0 = new AudioDecoderConstructParams();
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void decode(DecoderBuffer arg8, DecodeResponse arg9) {
            AudioDecoderDecodeParams v0 = new AudioDecoderDecodeParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new AudioDecoderDecodeResponseParamsForwardToCallback(arg9));
        }

        public void initialize(AudioDecoderConfig arg6, int arg7, InitializeResponse arg8) {
            AudioDecoderInitializeParams v0 = new AudioDecoderInitializeParams();
            v0.config = arg6;
            v0.cdmId = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new AudioDecoderInitializeResponseParamsForwardToCallback(arg8));
        }

        public void reset(ResetResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new AudioDecoderResetParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new AudioDecoderResetResponseParamsForwardToCallback(arg9));
        }

        public void setDataSource(ConsumerHandle arg5) {
            AudioDecoderSetDataSourceParams v0 = new AudioDecoderSetDataSourceParams();
            v0.receivePipe = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AudioDecoder arg2) {
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
                if(v1_1 != -2) {
                    if(v1_1 != 0) {
                        if(v1_1 != 2) {
                            return 0;
                        }

                        this.getImpl().setDataSource(AudioDecoderSetDataSourceParams.deserialize(v5_1.getPayload()).receivePipe);
                        return 1;
                    }

                    this.getImpl().construct(AudioDecoderConstructParams.deserialize(v5_1.getPayload()).client);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AudioDecoder_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg10, MessageReceiver arg11) {
            try {
                ServiceMessage v10_1 = arg10.asServiceMessage();
                MessageHeader v1 = v10_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                int v3 = v1.getType();
                if(v3 == -1) {
                    goto label_43;
                }

                if(v3 == 1) {
                    goto label_32;
                }

                switch(v3) {
                    case 3: {
                        goto label_22;
                    }
                    case 4: {
                        goto label_13;
                    }
                }

                return 0;
            label_22:
                this.getImpl().decode(AudioDecoderDecodeParams.deserialize(v10_1.getPayload()).buffer, new AudioDecoderDecodeResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_13:
                AudioDecoderResetParams.deserialize(v10_1.getPayload());
                this.getImpl().reset(new AudioDecoderResetResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_32:
                AudioDecoderInitializeParams v10_2 = AudioDecoderInitializeParams.deserialize(v10_1.getPayload());
                this.getImpl().initialize(v10_2.config, v10_2.cdmId, new AudioDecoderInitializeResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_43:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AudioDecoder_Internal.MANAGER, v10_1, arg11);
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int CONSTRUCT_ORDINAL = 0;
    private static final int DECODE_ORDINAL = 3;
    private static final int INITIALIZE_ORDINAL = 1;
    public static final Manager MANAGER = null;
    private static final int RESET_ORDINAL = 4;
    private static final int SET_DATA_SOURCE_ORDINAL = 2;

    static {
        AudioDecoder_Internal.MANAGER = new org.chromium.media.mojom.AudioDecoder_Internal$1();
    }

    AudioDecoder_Internal() {
        super();
    }
}

