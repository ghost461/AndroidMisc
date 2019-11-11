package org.chromium.device.mojom;

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

class PowerMonitor_Internal {
    final class org.chromium.device.mojom.PowerMonitor_Internal$1 extends Manager {
        org.chromium.device.mojom.PowerMonitor_Internal$1() {
            super();
        }

        public PowerMonitor[] buildArray(int arg1) {
            return new PowerMonitor[arg1];
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

        public Stub buildStub(Core arg2, PowerMonitor arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((PowerMonitor)arg2));
        }

        public String getName() {
            return "device::mojom::PowerMonitor";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class PowerMonitorAddClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public PowerMonitorClient client;

        static {
            PowerMonitorAddClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PowerMonitorAddClientParams.DEFAULT_STRUCT_INFO = PowerMonitorAddClientParams.VERSION_ARRAY[0];
        }

        public PowerMonitorAddClientParams() {
            this(0);
        }

        private PowerMonitorAddClientParams(int arg2) {
            super(16, arg2);
        }

        public static PowerMonitorAddClientParams decode(Decoder arg4) {
            PowerMonitorAddClientParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new PowerMonitorAddClientParams(arg4.readAndValidateDataHeader(PowerMonitorAddClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg4.readServiceInterface(8, false, PowerMonitorClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static PowerMonitorAddClientParams deserialize(ByteBuffer arg2) {
            return PowerMonitorAddClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PowerMonitorAddClientParams deserialize(Message arg1) {
            return PowerMonitorAddClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(PowerMonitorAddClientParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false, PowerMonitorClient.MANAGER);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.PowerMonitor$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addClient(PowerMonitorClient arg5) {
            PowerMonitorAddClientParams v0 = new PowerMonitorAddClientParams();
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, PowerMonitor arg2) {
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

                    this.getImpl().addClient(PowerMonitorAddClientParams.deserialize(v4_1.getPayload()).client);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(PowerMonitor_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), PowerMonitor_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int ADD_CLIENT_ORDINAL;
    public static final Manager MANAGER;

    static {
        PowerMonitor_Internal.MANAGER = new org.chromium.device.mojom.PowerMonitor_Internal$1();
    }

    PowerMonitor_Internal() {
        super();
    }
}

