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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class InterfaceFactory_Internal {
    final class org.chromium.media.mojom.InterfaceFactory_Internal$1 extends Manager {
        org.chromium.media.mojom.InterfaceFactory_Internal$1() {
            super();
        }

        public InterfaceFactory[] buildArray(int arg1) {
            return new InterfaceFactory[arg1];
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

        public Stub buildStub(Core arg2, InterfaceFactory arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((InterfaceFactory)arg2));
        }

        public String getName() {
            return "media::mojom::InterfaceFactory";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class InterfaceFactoryCreateAudioDecoderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest audioDecoder;

        static {
            InterfaceFactoryCreateAudioDecoderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InterfaceFactoryCreateAudioDecoderParams.DEFAULT_STRUCT_INFO = InterfaceFactoryCreateAudioDecoderParams.VERSION_ARRAY[0];
        }

        public InterfaceFactoryCreateAudioDecoderParams() {
            this(0);
        }

        private InterfaceFactoryCreateAudioDecoderParams(int arg2) {
            super(16, arg2);
        }

        public static InterfaceFactoryCreateAudioDecoderParams decode(Decoder arg3) {
            InterfaceFactoryCreateAudioDecoderParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InterfaceFactoryCreateAudioDecoderParams(arg3.readAndValidateDataHeader(InterfaceFactoryCreateAudioDecoderParams.VERSION_ARRAY).elementsOrVersion);
                v1.audioDecoder = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InterfaceFactoryCreateAudioDecoderParams deserialize(ByteBuffer arg2) {
            return InterfaceFactoryCreateAudioDecoderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InterfaceFactoryCreateAudioDecoderParams deserialize(Message arg1) {
            return InterfaceFactoryCreateAudioDecoderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(InterfaceFactoryCreateAudioDecoderParams.DEFAULT_STRUCT_INFO).encode(this.audioDecoder, 8, false);
        }
    }

    final class InterfaceFactoryCreateCdmParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest cdm;
        public String keySystem;

        static {
            InterfaceFactoryCreateCdmParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            InterfaceFactoryCreateCdmParams.DEFAULT_STRUCT_INFO = InterfaceFactoryCreateCdmParams.VERSION_ARRAY[0];
        }

        public InterfaceFactoryCreateCdmParams() {
            this(0);
        }

        private InterfaceFactoryCreateCdmParams(int arg2) {
            super(24, arg2);
        }

        public static InterfaceFactoryCreateCdmParams decode(Decoder arg3) {
            InterfaceFactoryCreateCdmParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InterfaceFactoryCreateCdmParams(arg3.readAndValidateDataHeader(InterfaceFactoryCreateCdmParams.VERSION_ARRAY).elementsOrVersion);
                v1.keySystem = arg3.readString(8, false);
                v1.cdm = arg3.readInterfaceRequest(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InterfaceFactoryCreateCdmParams deserialize(ByteBuffer arg2) {
            return InterfaceFactoryCreateCdmParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InterfaceFactoryCreateCdmParams deserialize(Message arg1) {
            return InterfaceFactoryCreateCdmParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(InterfaceFactoryCreateCdmParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.keySystem, 8, false);
            arg4.encode(this.cdm, 16, false);
        }
    }

    final class InterfaceFactoryCreateCdmProxyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String cdmGuid;
        public InterfaceRequest cdmProxy;

        static {
            InterfaceFactoryCreateCdmProxyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            InterfaceFactoryCreateCdmProxyParams.DEFAULT_STRUCT_INFO = InterfaceFactoryCreateCdmProxyParams.VERSION_ARRAY[0];
        }

        public InterfaceFactoryCreateCdmProxyParams() {
            this(0);
        }

        private InterfaceFactoryCreateCdmProxyParams(int arg2) {
            super(24, arg2);
        }

        public static InterfaceFactoryCreateCdmProxyParams decode(Decoder arg3) {
            InterfaceFactoryCreateCdmProxyParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InterfaceFactoryCreateCdmProxyParams(arg3.readAndValidateDataHeader(InterfaceFactoryCreateCdmProxyParams.VERSION_ARRAY).elementsOrVersion);
                v1.cdmGuid = arg3.readString(8, false);
                v1.cdmProxy = arg3.readInterfaceRequest(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InterfaceFactoryCreateCdmProxyParams deserialize(ByteBuffer arg2) {
            return InterfaceFactoryCreateCdmProxyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InterfaceFactoryCreateCdmProxyParams deserialize(Message arg1) {
            return InterfaceFactoryCreateCdmProxyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(InterfaceFactoryCreateCdmProxyParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.cdmGuid, 8, false);
            arg4.encode(this.cdmProxy, 16, false);
        }
    }

    final class InterfaceFactoryCreateRendererParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest renderer;
        public int type;
        public String typeSpecificId;

        static {
            InterfaceFactoryCreateRendererParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            InterfaceFactoryCreateRendererParams.DEFAULT_STRUCT_INFO = InterfaceFactoryCreateRendererParams.VERSION_ARRAY[0];
        }

        public InterfaceFactoryCreateRendererParams() {
            this(0);
        }

        private InterfaceFactoryCreateRendererParams(int arg2) {
            super(24, arg2);
        }

        public static InterfaceFactoryCreateRendererParams decode(Decoder arg3) {
            InterfaceFactoryCreateRendererParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InterfaceFactoryCreateRendererParams(arg3.readAndValidateDataHeader(InterfaceFactoryCreateRendererParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg3.readInt(8);
                HostedRendererType.validate(v1.type);
                v1.renderer = arg3.readInterfaceRequest(12, false);
                v1.typeSpecificId = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InterfaceFactoryCreateRendererParams deserialize(ByteBuffer arg2) {
            return InterfaceFactoryCreateRendererParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InterfaceFactoryCreateRendererParams deserialize(Message arg1) {
            return InterfaceFactoryCreateRendererParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(InterfaceFactoryCreateRendererParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.type, 8);
            arg4.encode(this.renderer, 12, false);
            arg4.encode(this.typeSpecificId, 16, false);
        }
    }

    final class InterfaceFactoryCreateVideoDecoderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest videoDecoder;

        static {
            InterfaceFactoryCreateVideoDecoderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InterfaceFactoryCreateVideoDecoderParams.DEFAULT_STRUCT_INFO = InterfaceFactoryCreateVideoDecoderParams.VERSION_ARRAY[0];
        }

        public InterfaceFactoryCreateVideoDecoderParams() {
            this(0);
        }

        private InterfaceFactoryCreateVideoDecoderParams(int arg2) {
            super(16, arg2);
        }

        public static InterfaceFactoryCreateVideoDecoderParams decode(Decoder arg3) {
            InterfaceFactoryCreateVideoDecoderParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InterfaceFactoryCreateVideoDecoderParams(arg3.readAndValidateDataHeader(InterfaceFactoryCreateVideoDecoderParams.VERSION_ARRAY).elementsOrVersion);
                v1.videoDecoder = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InterfaceFactoryCreateVideoDecoderParams deserialize(ByteBuffer arg2) {
            return InterfaceFactoryCreateVideoDecoderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InterfaceFactoryCreateVideoDecoderParams deserialize(Message arg1) {
            return InterfaceFactoryCreateVideoDecoderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(InterfaceFactoryCreateVideoDecoderParams.DEFAULT_STRUCT_INFO).encode(this.videoDecoder, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.InterfaceFactory$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createAudioDecoder(InterfaceRequest arg5) {
            InterfaceFactoryCreateAudioDecoderParams v0 = new InterfaceFactoryCreateAudioDecoderParams();
            v0.audioDecoder = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void createCdm(String arg4, InterfaceRequest arg5) {
            InterfaceFactoryCreateCdmParams v0 = new InterfaceFactoryCreateCdmParams();
            v0.keySystem = arg4;
            v0.cdm = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void createCdmProxy(String arg4, InterfaceRequest arg5) {
            InterfaceFactoryCreateCdmProxyParams v0 = new InterfaceFactoryCreateCdmProxyParams();
            v0.cdmGuid = arg4;
            v0.cdmProxy = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void createRenderer(int arg3, String arg4, InterfaceRequest arg5) {
            InterfaceFactoryCreateRendererParams v0 = new InterfaceFactoryCreateRendererParams();
            v0.type = arg3;
            v0.typeSpecificId = arg4;
            v0.renderer = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void createVideoDecoder(InterfaceRequest arg5) {
            InterfaceFactoryCreateVideoDecoderParams v0 = new InterfaceFactoryCreateVideoDecoderParams();
            v0.videoDecoder = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, InterfaceFactory arg2) {
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
                    goto label_46;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_40;
                    }
                    case 1: {
                        goto label_34;
                    }
                    case 2: {
                        goto label_26;
                    }
                    case 3: {
                        goto label_19;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_34:
                this.getImpl().createVideoDecoder(InterfaceFactoryCreateVideoDecoderParams.deserialize(v6_1.getPayload()).videoDecoder);
                return 1;
            label_19:
                InterfaceFactoryCreateCdmParams v6_2 = InterfaceFactoryCreateCdmParams.deserialize(v6_1.getPayload());
                this.getImpl().createCdm(v6_2.keySystem, v6_2.cdm);
                return 1;
            label_40:
                this.getImpl().createAudioDecoder(InterfaceFactoryCreateAudioDecoderParams.deserialize(v6_1.getPayload()).audioDecoder);
                return 1;
            label_26:
                InterfaceFactoryCreateRendererParams v6_3 = InterfaceFactoryCreateRendererParams.deserialize(v6_1.getPayload());
                this.getImpl().createRenderer(v6_3.type, v6_3.typeSpecificId, v6_3.renderer);
                return 1;
            label_12:
                InterfaceFactoryCreateCdmProxyParams v6_4 = InterfaceFactoryCreateCdmProxyParams.deserialize(v6_1.getPayload());
                this.getImpl().createCdmProxy(v6_4.cdmGuid, v6_4.cdmProxy);
                return 1;
            label_46:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(InterfaceFactory_Internal.MANAGER, v6_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), InterfaceFactory_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_AUDIO_DECODER_ORDINAL = 0;
    private static final int CREATE_CDM_ORDINAL = 3;
    private static final int CREATE_CDM_PROXY_ORDINAL = 4;
    private static final int CREATE_RENDERER_ORDINAL = 2;
    private static final int CREATE_VIDEO_DECODER_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        InterfaceFactory_Internal.MANAGER = new org.chromium.media.mojom.InterfaceFactory_Internal$1();
    }

    InterfaceFactory_Internal() {
        super();
    }
}

