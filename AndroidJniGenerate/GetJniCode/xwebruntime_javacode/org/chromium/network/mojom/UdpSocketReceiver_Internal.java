package org.chromium.network.mojom;

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
import org.chromium.mojo_base.mojom.ReadOnlyBuffer;
import org.chromium.net.interfaces.IpEndPoint;

class UdpSocketReceiver_Internal {
    final class org.chromium.network.mojom.UdpSocketReceiver_Internal$1 extends Manager {
        org.chromium.network.mojom.UdpSocketReceiver_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public UdpSocketReceiver[] buildArray(int arg1) {
            return new UdpSocketReceiver[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.UdpSocketReceiver_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.UdpSocketReceiver_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((UdpSocketReceiver)arg2));
        }

        public org.chromium.network.mojom.UdpSocketReceiver_Internal$Stub buildStub(Core arg2, UdpSocketReceiver arg3) {
            return new org.chromium.network.mojom.UdpSocketReceiver_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::UDPSocketReceiver";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.UdpSocketReceiver_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.UdpSocketReceiver$Proxy {
        org.chromium.network.mojom.UdpSocketReceiver_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onReceived(int arg3, IpEndPoint arg4, ReadOnlyBuffer arg5) {
            UdpSocketReceiverOnReceivedParams v0 = new UdpSocketReceiverOnReceivedParams();
            v0.result = arg3;
            v0.srcAddr = arg4;
            v0.data = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class org.chromium.network.mojom.UdpSocketReceiver_Internal$Stub extends Stub {
        org.chromium.network.mojom.UdpSocketReceiver_Internal$Stub(Core arg1, UdpSocketReceiver arg2) {
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

                    UdpSocketReceiverOnReceivedParams v5_2 = UdpSocketReceiverOnReceivedParams.deserialize(v5_1.getPayload());
                    this.getImpl().onReceived(v5_2.result, v5_2.srcAddr, v5_2.data);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(UdpSocketReceiver_Internal.MANAGER, v5_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), UdpSocketReceiver_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class UdpSocketReceiverOnReceivedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public ReadOnlyBuffer data;
        public int result;
        public IpEndPoint srcAddr;

        static {
            UdpSocketReceiverOnReceivedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            UdpSocketReceiverOnReceivedParams.DEFAULT_STRUCT_INFO = UdpSocketReceiverOnReceivedParams.VERSION_ARRAY[0];
        }

        public UdpSocketReceiverOnReceivedParams() {
            this(0);
        }

        private UdpSocketReceiverOnReceivedParams(int arg2) {
            super(0x20, arg2);
        }

        public static UdpSocketReceiverOnReceivedParams decode(Decoder arg3) {
            UdpSocketReceiverOnReceivedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UdpSocketReceiverOnReceivedParams(arg3.readAndValidateDataHeader(UdpSocketReceiverOnReceivedParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                v1.srcAddr = IpEndPoint.decode(arg3.readPointer(16, true));
                v1.data = ReadOnlyBuffer.decode(arg3.readPointer(24, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UdpSocketReceiverOnReceivedParams deserialize(ByteBuffer arg2) {
            return UdpSocketReceiverOnReceivedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UdpSocketReceiverOnReceivedParams deserialize(Message arg1) {
            return UdpSocketReceiverOnReceivedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UdpSocketReceiverOnReceivedParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.srcAddr, 16, true);
            arg4.encode(this.data, 24, true);
        }
    }

    public static final Manager MANAGER;
    private static final int ON_RECEIVED_ORDINAL;

    static {
        UdpSocketReceiver_Internal.MANAGER = new org.chromium.network.mojom.UdpSocketReceiver_Internal$1();
    }

    UdpSocketReceiver_Internal() {
        super();
    }
}

