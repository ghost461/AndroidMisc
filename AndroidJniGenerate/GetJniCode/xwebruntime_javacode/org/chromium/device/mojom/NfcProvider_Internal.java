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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class NfcProvider_Internal {
    final class org.chromium.device.mojom.NfcProvider_Internal$1 extends Manager {
        org.chromium.device.mojom.NfcProvider_Internal$1() {
            super();
        }

        public NfcProvider[] buildArray(int arg1) {
            return new NfcProvider[arg1];
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

        public Stub buildStub(Core arg2, NfcProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((NfcProvider)arg2));
        }

        public String getName() {
            return "device::mojom::NFCProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NfcProviderGetNfcForHostParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int hostId;
        public InterfaceRequest nfc;

        static {
            NfcProviderGetNfcForHostParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcProviderGetNfcForHostParams.DEFAULT_STRUCT_INFO = NfcProviderGetNfcForHostParams.VERSION_ARRAY[0];
        }

        public NfcProviderGetNfcForHostParams() {
            this(0);
        }

        private NfcProviderGetNfcForHostParams(int arg2) {
            super(16, arg2);
        }

        public static NfcProviderGetNfcForHostParams decode(Decoder arg3) {
            NfcProviderGetNfcForHostParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcProviderGetNfcForHostParams(arg3.readAndValidateDataHeader(NfcProviderGetNfcForHostParams.VERSION_ARRAY).elementsOrVersion);
                v1.hostId = arg3.readInt(8);
                v1.nfc = arg3.readInterfaceRequest(12, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcProviderGetNfcForHostParams deserialize(ByteBuffer arg2) {
            return NfcProviderGetNfcForHostParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcProviderGetNfcForHostParams deserialize(Message arg1) {
            return NfcProviderGetNfcForHostParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NfcProviderGetNfcForHostParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.hostId, 8);
            arg4.encode(this.nfc, 12, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.NfcProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getNfcForHost(int arg4, InterfaceRequest arg5) {
            NfcProviderGetNfcForHostParams v0 = new NfcProviderGetNfcForHostParams();
            v0.hostId = arg4;
            v0.nfc = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, NfcProvider arg2) {
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

                    NfcProviderGetNfcForHostParams v4_2 = NfcProviderGetNfcForHostParams.deserialize(v4_1.getPayload());
                    this.getImpl().getNfcForHost(v4_2.hostId, v4_2.nfc);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(NfcProvider_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), NfcProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int GET_NFC_FOR_HOST_ORDINAL;
    public static final Manager MANAGER;

    static {
        NfcProvider_Internal.MANAGER = new org.chromium.device.mojom.NfcProvider_Internal$1();
    }

    NfcProvider_Internal() {
        super();
    }
}

