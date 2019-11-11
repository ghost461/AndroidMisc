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

class NfcClient_Internal {
    final class org.chromium.device.mojom.NfcClient_Internal$1 extends Manager {
        org.chromium.device.mojom.NfcClient_Internal$1() {
            super();
        }

        public NfcClient[] buildArray(int arg1) {
            return new NfcClient[arg1];
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

        public Stub buildStub(Core arg2, NfcClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((NfcClient)arg2));
        }

        public String getName() {
            return "device::mojom::NFCClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NfcClientOnWatchParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcMessage message;
        public int[] watchIds;

        static {
            NfcClientOnWatchParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NfcClientOnWatchParams.DEFAULT_STRUCT_INFO = NfcClientOnWatchParams.VERSION_ARRAY[0];
        }

        public NfcClientOnWatchParams() {
            this(0);
        }

        private NfcClientOnWatchParams(int arg2) {
            super(24, arg2);
        }

        public static NfcClientOnWatchParams decode(Decoder arg4) {
            NfcClientOnWatchParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new NfcClientOnWatchParams(arg4.readAndValidateDataHeader(NfcClientOnWatchParams.VERSION_ARRAY).elementsOrVersion);
                v1.watchIds = arg4.readInts(8, 0, -1);
                v1.message = NfcMessage.decode(arg4.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static NfcClientOnWatchParams deserialize(ByteBuffer arg2) {
            return NfcClientOnWatchParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcClientOnWatchParams deserialize(Message arg1) {
            return NfcClientOnWatchParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(NfcClientOnWatchParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.watchIds, 8, 0, -1);
            arg5.encode(this.message, 16, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.NfcClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onWatch(int[] arg4, NfcMessage arg5) {
            NfcClientOnWatchParams v0 = new NfcClientOnWatchParams();
            v0.watchIds = arg4;
            v0.message = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, NfcClient arg2) {
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

                    NfcClientOnWatchParams v4_2 = NfcClientOnWatchParams.deserialize(v4_1.getPayload());
                    this.getImpl().onWatch(v4_2.watchIds, v4_2.message);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(NfcClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), NfcClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int ON_WATCH_ORDINAL;

    static {
        NfcClient_Internal.MANAGER = new org.chromium.device.mojom.NfcClient_Internal$1();
    }

    NfcClient_Internal() {
        super();
    }
}

