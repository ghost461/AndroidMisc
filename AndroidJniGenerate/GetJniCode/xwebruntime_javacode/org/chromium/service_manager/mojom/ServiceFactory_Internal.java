package org.chromium.service_manager.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface$Proxy;
import org.chromium.mojo.bindings.Interface$Stub;
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

class ServiceFactory_Internal {
    final class org.chromium.service_manager.mojom.ServiceFactory_Internal$1 extends Manager {
        org.chromium.service_manager.mojom.ServiceFactory_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ServiceFactory[] buildArray(int arg1) {
            return new ServiceFactory[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.service_manager.mojom.ServiceFactory_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.service_manager.mojom.ServiceFactory_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceFactory)arg2));
        }

        public org.chromium.service_manager.mojom.ServiceFactory_Internal$Stub buildStub(Core arg2, ServiceFactory arg3) {
            return new org.chromium.service_manager.mojom.ServiceFactory_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "service_manager::mojom::ServiceFactory";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.service_manager.mojom.ServiceFactory_Internal$Proxy extends AbstractProxy implements org.chromium.service_manager.mojom.ServiceFactory$Proxy {
        org.chromium.service_manager.mojom.ServiceFactory_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createService(InterfaceRequest arg3, String arg4, PidReceiver arg5) {
            ServiceFactoryCreateServiceParams v0 = new ServiceFactoryCreateServiceParams();
            v0.service = arg3;
            v0.name = arg4;
            v0.pidReceiver = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ServiceFactoryCreateServiceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public String name;
        public PidReceiver pidReceiver;
        public InterfaceRequest service;

        static {
            ServiceFactoryCreateServiceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ServiceFactoryCreateServiceParams.DEFAULT_STRUCT_INFO = ServiceFactoryCreateServiceParams.VERSION_ARRAY[0];
        }

        public ServiceFactoryCreateServiceParams() {
            this(0);
        }

        private ServiceFactoryCreateServiceParams(int arg2) {
            super(0x20, arg2);
        }

        public static ServiceFactoryCreateServiceParams decode(Decoder arg4) {
            ServiceFactoryCreateServiceParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ServiceFactoryCreateServiceParams(arg4.readAndValidateDataHeader(ServiceFactoryCreateServiceParams.VERSION_ARRAY).elementsOrVersion);
                v1.service = arg4.readInterfaceRequest(8, false);
                v1.name = arg4.readString(16, false);
                v1.pidReceiver = arg4.readServiceInterface(24, false, PidReceiver.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ServiceFactoryCreateServiceParams deserialize(ByteBuffer arg2) {
            return ServiceFactoryCreateServiceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceFactoryCreateServiceParams deserialize(Message arg1) {
            return ServiceFactoryCreateServiceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ServiceFactoryCreateServiceParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.service, 8, false);
            arg5.encode(this.name, 16, false);
            arg5.encode(this.pidReceiver, 24, false, PidReceiver.MANAGER);
        }
    }

    final class org.chromium.service_manager.mojom.ServiceFactory_Internal$Stub extends Stub {
        org.chromium.service_manager.mojom.ServiceFactory_Internal$Stub(Core arg1, ServiceFactory arg2) {
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
                        return 0;
                    }

                    ServiceFactoryCreateServiceParams v5_2 = ServiceFactoryCreateServiceParams.deserialize(v5_1.getPayload());
                    this.getImpl().createService(v5_2.service, v5_2.name, v5_2.pidReceiver);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceFactory_Internal.MANAGER, v5_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceFactory_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_SERVICE_ORDINAL;
    public static final Manager MANAGER;

    static {
        ServiceFactory_Internal.MANAGER = new org.chromium.service_manager.mojom.ServiceFactory_Internal$1();
    }

    ServiceFactory_Internal() {
        super();
    }
}

