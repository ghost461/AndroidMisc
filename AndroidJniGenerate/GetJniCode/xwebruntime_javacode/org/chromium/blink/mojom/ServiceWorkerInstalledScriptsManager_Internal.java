package org.chromium.blink.mojom;

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

class ServiceWorkerInstalledScriptsManager_Internal {
    final class org.chromium.blink.mojom.ServiceWorkerInstalledScriptsManager_Internal$1 extends Manager {
        org.chromium.blink.mojom.ServiceWorkerInstalledScriptsManager_Internal$1() {
            super();
        }

        public ServiceWorkerInstalledScriptsManager[] buildArray(int arg1) {
            return new ServiceWorkerInstalledScriptsManager[arg1];
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

        public Stub buildStub(Core arg2, ServiceWorkerInstalledScriptsManager arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceWorkerInstalledScriptsManager)arg2));
        }

        public String getName() {
            return "blink::mojom::ServiceWorkerInstalledScriptsManager";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ServiceWorkerInstalledScriptsManager$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void transferInstalledScript(ServiceWorkerScriptInfo arg5) {
            ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams v0 = new ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams();
            v0.scriptInfo = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ServiceWorkerScriptInfo scriptInfo;

        static {
            ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.DEFAULT_STRUCT_INFO = ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams() {
            this(0);
        }

        private ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams decode(Decoder arg3) {
            ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams(arg3.readAndValidateDataHeader(ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.VERSION_ARRAY).elementsOrVersion);
                v1.scriptInfo = ServiceWorkerScriptInfo.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams deserialize(Message arg1) {
            return ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.DEFAULT_STRUCT_INFO).encode(this.scriptInfo, 8, false);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ServiceWorkerInstalledScriptsManager arg2) {
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

                    this.getImpl().transferInstalledScript(ServiceWorkerInstalledScriptsManagerTransferInstalledScriptParams.deserialize(v4_1.getPayload()).scriptInfo);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceWorkerInstalledScriptsManager_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceWorkerInstalledScriptsManager_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int TRANSFER_INSTALLED_SCRIPT_ORDINAL;

    static {
        ServiceWorkerInstalledScriptsManager_Internal.MANAGER = new org.chromium.blink.mojom.ServiceWorkerInstalledScriptsManager_Internal$1();
    }

    ServiceWorkerInstalledScriptsManager_Internal() {
        super();
    }
}

