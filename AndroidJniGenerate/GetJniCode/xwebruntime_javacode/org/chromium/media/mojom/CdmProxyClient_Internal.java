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
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class CdmProxyClient_Internal {
    final class org.chromium.media.mojom.CdmProxyClient_Internal$1 extends Manager {
        org.chromium.media.mojom.CdmProxyClient_Internal$1() {
            super();
        }

        public CdmProxyClient[] buildArray(int arg1) {
            return new CdmProxyClient[arg1];
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

        public Stub buildStub(Core arg2, CdmProxyClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CdmProxyClient)arg2));
        }

        public String getName() {
            return "media::mojom::CdmProxyClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CdmProxyClientNotifyHardwareResetParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            CdmProxyClientNotifyHardwareResetParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            CdmProxyClientNotifyHardwareResetParams.DEFAULT_STRUCT_INFO = CdmProxyClientNotifyHardwareResetParams.VERSION_ARRAY[0];
        }

        public CdmProxyClientNotifyHardwareResetParams() {
            this(0);
        }

        private CdmProxyClientNotifyHardwareResetParams(int arg2) {
            super(8, arg2);
        }

        public static CdmProxyClientNotifyHardwareResetParams decode(Decoder arg2) {
            CdmProxyClientNotifyHardwareResetParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CdmProxyClientNotifyHardwareResetParams(arg2.readAndValidateDataHeader(CdmProxyClientNotifyHardwareResetParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyClientNotifyHardwareResetParams deserialize(ByteBuffer arg2) {
            return CdmProxyClientNotifyHardwareResetParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyClientNotifyHardwareResetParams deserialize(Message arg1) {
            return CdmProxyClientNotifyHardwareResetParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(CdmProxyClientNotifyHardwareResetParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.CdmProxyClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void notifyHardwareReset() {
            this.getProxyHandler().getMessageReceiver().accept(new CdmProxyClientNotifyHardwareResetParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, CdmProxyClient arg2) {
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

                    CdmProxyClientNotifyHardwareResetParams.deserialize(v4_1.getPayload());
                    this.getImpl().notifyHardwareReset();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CdmProxyClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CdmProxyClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int NOTIFY_HARDWARE_RESET_ORDINAL;

    static {
        CdmProxyClient_Internal.MANAGER = new org.chromium.media.mojom.CdmProxyClient_Internal$1();
    }

    CdmProxyClient_Internal() {
        super();
    }
}

