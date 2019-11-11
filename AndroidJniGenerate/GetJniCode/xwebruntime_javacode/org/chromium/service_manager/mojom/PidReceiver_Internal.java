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

class PidReceiver_Internal {
    final class org.chromium.service_manager.mojom.PidReceiver_Internal$1 extends Manager {
        org.chromium.service_manager.mojom.PidReceiver_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public PidReceiver[] buildArray(int arg1) {
            return new PidReceiver[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.service_manager.mojom.PidReceiver_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.service_manager.mojom.PidReceiver_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((PidReceiver)arg2));
        }

        public org.chromium.service_manager.mojom.PidReceiver_Internal$Stub buildStub(Core arg2, PidReceiver arg3) {
            return new org.chromium.service_manager.mojom.PidReceiver_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "service_manager::mojom::PIDReceiver";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class PidReceiverSetPidParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int pid;

        static {
            PidReceiverSetPidParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PidReceiverSetPidParams.DEFAULT_STRUCT_INFO = PidReceiverSetPidParams.VERSION_ARRAY[0];
        }

        public PidReceiverSetPidParams() {
            this(0);
        }

        private PidReceiverSetPidParams(int arg2) {
            super(16, arg2);
        }

        public static PidReceiverSetPidParams decode(Decoder arg2) {
            PidReceiverSetPidParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PidReceiverSetPidParams(arg2.readAndValidateDataHeader(PidReceiverSetPidParams.VERSION_ARRAY).elementsOrVersion);
                v1.pid = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PidReceiverSetPidParams deserialize(ByteBuffer arg2) {
            return PidReceiverSetPidParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PidReceiverSetPidParams deserialize(Message arg1) {
            return PidReceiverSetPidParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(PidReceiverSetPidParams.DEFAULT_STRUCT_INFO).encode(this.pid, 8);
        }
    }

    final class org.chromium.service_manager.mojom.PidReceiver_Internal$Proxy extends AbstractProxy implements org.chromium.service_manager.mojom.PidReceiver$Proxy {
        org.chromium.service_manager.mojom.PidReceiver_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void setPid(int arg5) {
            PidReceiverSetPidParams v0 = new PidReceiverSetPidParams();
            v0.pid = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class org.chromium.service_manager.mojom.PidReceiver_Internal$Stub extends Stub {
        org.chromium.service_manager.mojom.PidReceiver_Internal$Stub(Core arg1, PidReceiver arg2) {
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

                    this.getImpl().setPid(PidReceiverSetPidParams.deserialize(v4_1.getPayload()).pid);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(PidReceiver_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), PidReceiver_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int SET_PID_ORDINAL;

    static {
        PidReceiver_Internal.MANAGER = new org.chromium.service_manager.mojom.PidReceiver_Internal$1();
    }

    PidReceiver_Internal() {
        super();
    }
}

