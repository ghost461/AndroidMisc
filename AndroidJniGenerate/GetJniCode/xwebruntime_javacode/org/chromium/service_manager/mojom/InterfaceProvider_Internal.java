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
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.MessagePipeHandle;

class InterfaceProvider_Internal {
    final class org.chromium.service_manager.mojom.InterfaceProvider_Internal$1 extends Manager {
        org.chromium.service_manager.mojom.InterfaceProvider_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public InterfaceProvider[] buildArray(int arg1) {
            return new InterfaceProvider[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.service_manager.mojom.InterfaceProvider_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.service_manager.mojom.InterfaceProvider_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((InterfaceProvider)arg2));
        }

        public org.chromium.service_manager.mojom.InterfaceProvider_Internal$Stub buildStub(Core arg2, InterfaceProvider arg3) {
            return new org.chromium.service_manager.mojom.InterfaceProvider_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "service_manager::mojom::InterfaceProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class InterfaceProviderGetInterfaceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String interfaceName;
        public MessagePipeHandle pipe;

        static {
            InterfaceProviderGetInterfaceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            InterfaceProviderGetInterfaceParams.DEFAULT_STRUCT_INFO = InterfaceProviderGetInterfaceParams.VERSION_ARRAY[0];
        }

        public InterfaceProviderGetInterfaceParams() {
            this(0);
        }

        private InterfaceProviderGetInterfaceParams(int arg2) {
            super(24, arg2);
            this.pipe = InvalidHandle.INSTANCE;
        }

        public static InterfaceProviderGetInterfaceParams decode(Decoder arg3) {
            InterfaceProviderGetInterfaceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InterfaceProviderGetInterfaceParams(arg3.readAndValidateDataHeader(InterfaceProviderGetInterfaceParams.VERSION_ARRAY).elementsOrVersion);
                v1.interfaceName = arg3.readString(8, false);
                v1.pipe = arg3.readMessagePipeHandle(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InterfaceProviderGetInterfaceParams deserialize(ByteBuffer arg2) {
            return InterfaceProviderGetInterfaceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InterfaceProviderGetInterfaceParams deserialize(Message arg1) {
            return InterfaceProviderGetInterfaceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(InterfaceProviderGetInterfaceParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.interfaceName, 8, false);
            arg4.encode(this.pipe, 16, false);
        }
    }

    final class org.chromium.service_manager.mojom.InterfaceProvider_Internal$Proxy extends AbstractProxy implements org.chromium.service_manager.mojom.InterfaceProvider$Proxy {
        org.chromium.service_manager.mojom.InterfaceProvider_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getInterface(String arg4, MessagePipeHandle arg5) {
            InterfaceProviderGetInterfaceParams v0 = new InterfaceProviderGetInterfaceParams();
            v0.interfaceName = arg4;
            v0.pipe = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class org.chromium.service_manager.mojom.InterfaceProvider_Internal$Stub extends Stub {
        org.chromium.service_manager.mojom.InterfaceProvider_Internal$Stub(Core arg1, InterfaceProvider arg2) {
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

                    InterfaceProviderGetInterfaceParams v4_2 = InterfaceProviderGetInterfaceParams.deserialize(v4_1.getPayload());
                    this.getImpl().getInterface(v4_2.interfaceName, v4_2.pipe);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(InterfaceProvider_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), InterfaceProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int GET_INTERFACE_ORDINAL;
    public static final Manager MANAGER;

    static {
        InterfaceProvider_Internal.MANAGER = new org.chromium.service_manager.mojom.InterfaceProvider_Internal$1();
    }

    InterfaceProvider_Internal() {
        super();
    }
}

