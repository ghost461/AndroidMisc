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
import org.chromium.mojo_base.mojom.FilePath;
import org.chromium.service_manager.mojom.InterfaceProvider;

class CdmService_Internal {
    final class org.chromium.media.mojom.CdmService_Internal$1 extends Manager {
        org.chromium.media.mojom.CdmService_Internal$1() {
            super();
        }

        public CdmService[] buildArray(int arg1) {
            return new CdmService[arg1];
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

        public Stub buildStub(Core arg2, CdmService arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CdmService)arg2));
        }

        public String getName() {
            return "media::mojom::CdmService";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CdmServiceCreateCdmFactoryParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest factory;
        public InterfaceProvider hostInterfaces;

        static {
            CdmServiceCreateCdmFactoryParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CdmServiceCreateCdmFactoryParams.DEFAULT_STRUCT_INFO = CdmServiceCreateCdmFactoryParams.VERSION_ARRAY[0];
        }

        public CdmServiceCreateCdmFactoryParams() {
            this(0);
        }

        private CdmServiceCreateCdmFactoryParams(int arg2) {
            super(24, arg2);
        }

        public static CdmServiceCreateCdmFactoryParams decode(Decoder arg4) {
            CdmServiceCreateCdmFactoryParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CdmServiceCreateCdmFactoryParams(arg4.readAndValidateDataHeader(CdmServiceCreateCdmFactoryParams.VERSION_ARRAY).elementsOrVersion);
                v1.factory = arg4.readInterfaceRequest(8, false);
                v1.hostInterfaces = arg4.readServiceInterface(12, true, InterfaceProvider.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CdmServiceCreateCdmFactoryParams deserialize(ByteBuffer arg2) {
            return CdmServiceCreateCdmFactoryParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmServiceCreateCdmFactoryParams deserialize(Message arg1) {
            return CdmServiceCreateCdmFactoryParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(CdmServiceCreateCdmFactoryParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.factory, 8, false);
            arg5.encode(this.hostInterfaces, 12, true, InterfaceProvider.MANAGER);
        }
    }

    final class CdmServiceLoadCdmParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public FilePath cdmPath;

        static {
            CdmServiceLoadCdmParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CdmServiceLoadCdmParams.DEFAULT_STRUCT_INFO = CdmServiceLoadCdmParams.VERSION_ARRAY[0];
        }

        public CdmServiceLoadCdmParams() {
            this(0);
        }

        private CdmServiceLoadCdmParams(int arg2) {
            super(16, arg2);
        }

        public static CdmServiceLoadCdmParams decode(Decoder arg3) {
            CdmServiceLoadCdmParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CdmServiceLoadCdmParams(arg3.readAndValidateDataHeader(CdmServiceLoadCdmParams.VERSION_ARRAY).elementsOrVersion);
                v1.cdmPath = FilePath.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CdmServiceLoadCdmParams deserialize(ByteBuffer arg2) {
            return CdmServiceLoadCdmParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmServiceLoadCdmParams deserialize(Message arg1) {
            return CdmServiceLoadCdmParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CdmServiceLoadCdmParams.DEFAULT_STRUCT_INFO).encode(this.cdmPath, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.CdmService$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createCdmFactory(InterfaceRequest arg4, InterfaceProvider arg5) {
            CdmServiceCreateCdmFactoryParams v0 = new CdmServiceCreateCdmFactoryParams();
            v0.factory = arg4;
            v0.hostInterfaces = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void loadCdm(FilePath arg5) {
            CdmServiceLoadCdmParams v0 = new CdmServiceLoadCdmParams();
            v0.cdmPath = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, CdmService arg2) {
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
                    goto label_25;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_19;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_19:
                this.getImpl().loadCdm(CdmServiceLoadCdmParams.deserialize(v5_1.getPayload()).cdmPath);
                return 1;
            label_12:
                CdmServiceCreateCdmFactoryParams v5_2 = CdmServiceCreateCdmFactoryParams.deserialize(v5_1.getPayload());
                this.getImpl().createCdmFactory(v5_2.factory, v5_2.hostInterfaces);
                return 1;
            label_25:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CdmService_Internal.MANAGER, v5_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CdmService_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_CDM_FACTORY_ORDINAL = 1;
    private static final int LOAD_CDM_ORDINAL;
    public static final Manager MANAGER;

    static {
        CdmService_Internal.MANAGER = new org.chromium.media.mojom.CdmService_Internal$1();
    }

    CdmService_Internal() {
        super();
    }
}

