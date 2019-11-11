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

class CdmFactory_Internal {
    final class org.chromium.media.mojom.CdmFactory_Internal$1 extends Manager {
        org.chromium.media.mojom.CdmFactory_Internal$1() {
            super();
        }

        public CdmFactory[] buildArray(int arg1) {
            return new CdmFactory[arg1];
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

        public Stub buildStub(Core arg2, CdmFactory arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CdmFactory)arg2));
        }

        public String getName() {
            return "media::mojom::CdmFactory";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CdmFactoryCreateCdmParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest cdm;
        public String keySystem;

        static {
            CdmFactoryCreateCdmParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CdmFactoryCreateCdmParams.DEFAULT_STRUCT_INFO = CdmFactoryCreateCdmParams.VERSION_ARRAY[0];
        }

        public CdmFactoryCreateCdmParams() {
            this(0);
        }

        private CdmFactoryCreateCdmParams(int arg2) {
            super(24, arg2);
        }

        public static CdmFactoryCreateCdmParams decode(Decoder arg3) {
            CdmFactoryCreateCdmParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CdmFactoryCreateCdmParams(arg3.readAndValidateDataHeader(CdmFactoryCreateCdmParams.VERSION_ARRAY).elementsOrVersion);
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

        public static CdmFactoryCreateCdmParams deserialize(ByteBuffer arg2) {
            return CdmFactoryCreateCdmParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmFactoryCreateCdmParams deserialize(Message arg1) {
            return CdmFactoryCreateCdmParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(CdmFactoryCreateCdmParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.keySystem, 8, false);
            arg4.encode(this.cdm, 16, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.CdmFactory$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createCdm(String arg4, InterfaceRequest arg5) {
            CdmFactoryCreateCdmParams v0 = new CdmFactoryCreateCdmParams();
            v0.keySystem = arg4;
            v0.cdm = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, CdmFactory arg2) {
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

                    CdmFactoryCreateCdmParams v4_2 = CdmFactoryCreateCdmParams.deserialize(v4_1.getPayload());
                    this.getImpl().createCdm(v4_2.keySystem, v4_2.cdm);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CdmFactory_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CdmFactory_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_CDM_ORDINAL;
    public static final Manager MANAGER;

    static {
        CdmFactory_Internal.MANAGER = new org.chromium.media.mojom.CdmFactory_Internal$1();
    }

    CdmFactory_Internal() {
        super();
    }
}

