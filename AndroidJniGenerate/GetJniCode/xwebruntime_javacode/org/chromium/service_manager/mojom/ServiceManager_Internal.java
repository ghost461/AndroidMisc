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
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class ServiceManager_Internal {
    final class org.chromium.service_manager.mojom.ServiceManager_Internal$1 extends Manager {
        org.chromium.service_manager.mojom.ServiceManager_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ServiceManager[] buildArray(int arg1) {
            return new ServiceManager[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.service_manager.mojom.ServiceManager_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.service_manager.mojom.ServiceManager_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceManager)arg2));
        }

        public org.chromium.service_manager.mojom.ServiceManager_Internal$Stub buildStub(Core arg2, ServiceManager arg3) {
            return new org.chromium.service_manager.mojom.ServiceManager_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "service_manager::mojom::ServiceManager";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.service_manager.mojom.ServiceManager_Internal$Proxy extends AbstractProxy implements org.chromium.service_manager.mojom.ServiceManager$Proxy {
        org.chromium.service_manager.mojom.ServiceManager_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addListener(ServiceManagerListener arg5) {
            ServiceManagerAddListenerParams v0 = new ServiceManagerAddListenerParams();
            v0.listener = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ServiceManagerAddListenerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceManagerListener listener;

        static {
            ServiceManagerAddListenerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceManagerAddListenerParams.DEFAULT_STRUCT_INFO = ServiceManagerAddListenerParams.VERSION_ARRAY[0];
        }

        public ServiceManagerAddListenerParams() {
            this(0);
        }

        private ServiceManagerAddListenerParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceManagerAddListenerParams decode(Decoder arg4) {
            ServiceManagerAddListenerParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ServiceManagerAddListenerParams(arg4.readAndValidateDataHeader(ServiceManagerAddListenerParams.VERSION_ARRAY).elementsOrVersion);
                v1.listener = arg4.readServiceInterface(8, false, ServiceManagerListener.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ServiceManagerAddListenerParams deserialize(ByteBuffer arg2) {
            return ServiceManagerAddListenerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceManagerAddListenerParams deserialize(Message arg1) {
            return ServiceManagerAddListenerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(ServiceManagerAddListenerParams.DEFAULT_STRUCT_INFO).encode(this.listener, 8, false, ServiceManagerListener.MANAGER);
        }
    }

    final class org.chromium.service_manager.mojom.ServiceManager_Internal$Stub extends Stub {
        org.chromium.service_manager.mojom.ServiceManager_Internal$Stub(Core arg1, ServiceManager arg2) {
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

                    this.getImpl().addListener(ServiceManagerAddListenerParams.deserialize(v4_1.getPayload()).listener);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceManager_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceManager_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int ADD_LISTENER_ORDINAL;
    public static final Manager MANAGER;

    static {
        ServiceManager_Internal.MANAGER = new org.chromium.service_manager.mojom.ServiceManager_Internal$1();
    }

    ServiceManager_Internal() {
        super();
    }
}

