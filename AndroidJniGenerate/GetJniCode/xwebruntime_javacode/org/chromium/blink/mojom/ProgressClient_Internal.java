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

class ProgressClient_Internal {
    final class org.chromium.blink.mojom.ProgressClient_Internal$1 extends Manager {
        org.chromium.blink.mojom.ProgressClient_Internal$1() {
            super();
        }

        public ProgressClient[] buildArray(int arg1) {
            return new ProgressClient[arg1];
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

        public Stub buildStub(Core arg2, ProgressClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ProgressClient)arg2));
        }

        public String getName() {
            return "blink::mojom::ProgressClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ProgressClientOnProgressParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public long delta;

        static {
            ProgressClientOnProgressParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ProgressClientOnProgressParams.DEFAULT_STRUCT_INFO = ProgressClientOnProgressParams.VERSION_ARRAY[0];
        }

        public ProgressClientOnProgressParams() {
            this(0);
        }

        private ProgressClientOnProgressParams(int arg2) {
            super(16, arg2);
        }

        public static ProgressClientOnProgressParams decode(Decoder arg4) {
            ProgressClientOnProgressParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ProgressClientOnProgressParams(arg4.readAndValidateDataHeader(ProgressClientOnProgressParams.VERSION_ARRAY).elementsOrVersion);
                v1.delta = arg4.readLong(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ProgressClientOnProgressParams deserialize(ByteBuffer arg2) {
            return ProgressClientOnProgressParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ProgressClientOnProgressParams deserialize(Message arg1) {
            return ProgressClientOnProgressParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ProgressClientOnProgressParams.DEFAULT_STRUCT_INFO).encode(this.delta, 8);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ProgressClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onProgress(long arg4) {
            ProgressClientOnProgressParams v0 = new ProgressClientOnProgressParams();
            v0.delta = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ProgressClient arg2) {
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

                    this.getImpl().onProgress(ProgressClientOnProgressParams.deserialize(v5_1.getPayload()).delta);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ProgressClient_Internal.MANAGER, v5_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ProgressClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int ON_PROGRESS_ORDINAL;

    static {
        ProgressClient_Internal.MANAGER = new org.chromium.blink.mojom.ProgressClient_Internal$1();
    }

    ProgressClient_Internal() {
        super();
    }
}

