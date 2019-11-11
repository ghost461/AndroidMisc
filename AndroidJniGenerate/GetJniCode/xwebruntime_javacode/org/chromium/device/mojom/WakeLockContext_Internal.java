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

class WakeLockContext_Internal {
    final class org.chromium.device.mojom.WakeLockContext_Internal$1 extends Manager {
        org.chromium.device.mojom.WakeLockContext_Internal$1() {
            super();
        }

        public WakeLockContext[] buildArray(int arg1) {
            return new WakeLockContext[arg1];
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

        public Stub buildStub(Core arg2, WakeLockContext arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((WakeLockContext)arg2));
        }

        public String getName() {
            return "device::mojom::WakeLockContext";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.WakeLockContext$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getWakeLock(int arg2, int arg3, String arg4, InterfaceRequest arg5) {
            WakeLockContextGetWakeLockParams v0 = new WakeLockContextGetWakeLockParams();
            v0.type = arg2;
            v0.reason = arg3;
            v0.description = arg4;
            v0.wakeLock = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, WakeLockContext arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6_1 = arg6.asServiceMessage();
                MessageHeader v1 = v6_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 != -2) {
                    if(v1_1 != 0) {
                        return 0;
                    }

                    WakeLockContextGetWakeLockParams v6_2 = WakeLockContextGetWakeLockParams.deserialize(v6_1.getPayload());
                    this.getImpl().getWakeLock(v6_2.type, v6_2.reason, v6_2.description, v6_2.wakeLock);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(WakeLockContext_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), WakeLockContext_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class WakeLockContextGetWakeLockParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public String description;
        public int reason;
        public int type;
        public InterfaceRequest wakeLock;

        static {
            WakeLockContextGetWakeLockParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            WakeLockContextGetWakeLockParams.DEFAULT_STRUCT_INFO = WakeLockContextGetWakeLockParams.VERSION_ARRAY[0];
        }

        public WakeLockContextGetWakeLockParams() {
            this(0);
        }

        private WakeLockContextGetWakeLockParams(int arg2) {
            super(0x20, arg2);
        }

        public static WakeLockContextGetWakeLockParams decode(Decoder arg3) {
            WakeLockContextGetWakeLockParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WakeLockContextGetWakeLockParams(arg3.readAndValidateDataHeader(WakeLockContextGetWakeLockParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg3.readInt(8);
                WakeLockType.validate(v1.type);
                v1.reason = arg3.readInt(12);
                WakeLockReason.validate(v1.reason);
                v1.description = arg3.readString(16, false);
                v1.wakeLock = arg3.readInterfaceRequest(24, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WakeLockContextGetWakeLockParams deserialize(ByteBuffer arg2) {
            return WakeLockContextGetWakeLockParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockContextGetWakeLockParams deserialize(Message arg1) {
            return WakeLockContextGetWakeLockParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(WakeLockContextGetWakeLockParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.type, 8);
            arg4.encode(this.reason, 12);
            arg4.encode(this.description, 16, false);
            arg4.encode(this.wakeLock, 24, false);
        }
    }

    private static final int GET_WAKE_LOCK_ORDINAL;
    public static final Manager MANAGER;

    static {
        WakeLockContext_Internal.MANAGER = new org.chromium.device.mojom.WakeLockContext_Internal$1();
    }

    WakeLockContext_Internal() {
        super();
    }
}

