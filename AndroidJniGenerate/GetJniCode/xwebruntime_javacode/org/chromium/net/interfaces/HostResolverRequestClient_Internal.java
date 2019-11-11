package org.chromium.net.interfaces;

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

class HostResolverRequestClient_Internal {
    final class org.chromium.net.interfaces.HostResolverRequestClient_Internal$1 extends Manager {
        org.chromium.net.interfaces.HostResolverRequestClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public HostResolverRequestClient[] buildArray(int arg1) {
            return new HostResolverRequestClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.net.interfaces.HostResolverRequestClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.net.interfaces.HostResolverRequestClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((HostResolverRequestClient)arg2));
        }

        public org.chromium.net.interfaces.HostResolverRequestClient_Internal$Stub buildStub(Core arg2, HostResolverRequestClient arg3) {
            return new org.chromium.net.interfaces.HostResolverRequestClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "net::interfaces::HostResolverRequestClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class HostResolverRequestClientReportResultParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public AddressList result;

        static {
            HostResolverRequestClientReportResultParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            HostResolverRequestClientReportResultParams.DEFAULT_STRUCT_INFO = HostResolverRequestClientReportResultParams.VERSION_ARRAY[0];
        }

        public HostResolverRequestClientReportResultParams() {
            this(0);
        }

        private HostResolverRequestClientReportResultParams(int arg2) {
            super(24, arg2);
        }

        public static HostResolverRequestClientReportResultParams decode(Decoder arg3) {
            HostResolverRequestClientReportResultParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new HostResolverRequestClientReportResultParams(arg3.readAndValidateDataHeader(HostResolverRequestClientReportResultParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                v1.result = AddressList.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static HostResolverRequestClientReportResultParams deserialize(ByteBuffer arg2) {
            return HostResolverRequestClientReportResultParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HostResolverRequestClientReportResultParams deserialize(Message arg1) {
            return HostResolverRequestClientReportResultParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(HostResolverRequestClientReportResultParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.result, 16, false);
        }
    }

    final class org.chromium.net.interfaces.HostResolverRequestClient_Internal$Proxy extends AbstractProxy implements org.chromium.net.interfaces.HostResolverRequestClient$Proxy {
        org.chromium.net.interfaces.HostResolverRequestClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void reportResult(int arg4, AddressList arg5) {
            HostResolverRequestClientReportResultParams v0 = new HostResolverRequestClientReportResultParams();
            v0.error = arg4;
            v0.result = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class org.chromium.net.interfaces.HostResolverRequestClient_Internal$Stub extends Stub {
        org.chromium.net.interfaces.HostResolverRequestClient_Internal$Stub(Core arg1, HostResolverRequestClient arg2) {
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

                    HostResolverRequestClientReportResultParams v4_2 = HostResolverRequestClientReportResultParams.deserialize(v4_1.getPayload());
                    this.getImpl().reportResult(v4_2.error, v4_2.result);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(HostResolverRequestClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), HostResolverRequestClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int REPORT_RESULT_ORDINAL;

    static {
        HostResolverRequestClient_Internal.MANAGER = new org.chromium.net.interfaces.HostResolverRequestClient_Internal$1();
    }

    HostResolverRequestClient_Internal() {
        super();
    }
}

