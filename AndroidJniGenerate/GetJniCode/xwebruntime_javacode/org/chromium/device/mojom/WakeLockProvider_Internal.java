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

class WakeLockProvider_Internal {
    final class org.chromium.device.mojom.WakeLockProvider_Internal$1 extends Manager {
        org.chromium.device.mojom.WakeLockProvider_Internal$1() {
            super();
        }

        public WakeLockProvider[] buildArray(int arg1) {
            return new WakeLockProvider[arg1];
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

        public Stub buildStub(Core arg2, WakeLockProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((WakeLockProvider)arg2));
        }

        public String getName() {
            return "device::mojom::WakeLockProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.WakeLockProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getWakeLockContextForId(int arg4, InterfaceRequest arg5) {
            WakeLockProviderGetWakeLockContextForIdParams v0 = new WakeLockProviderGetWakeLockContextForIdParams();
            v0.contextId = arg4;
            v0.context = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void getWakeLockWithoutContext(int arg2, int arg3, String arg4, InterfaceRequest arg5) {
            WakeLockProviderGetWakeLockWithoutContextParams v0 = new WakeLockProviderGetWakeLockWithoutContextParams();
            v0.type = arg2;
            v0.reason = arg3;
            v0.description = arg4;
            v0.wakeLock = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, WakeLockProvider arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_28;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_21;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_21:
                WakeLockProviderGetWakeLockContextForIdParams v7_2 = WakeLockProviderGetWakeLockContextForIdParams.deserialize(v7_1.getPayload());
                this.getImpl().getWakeLockContextForId(v7_2.contextId, v7_2.context);
                return 1;
            label_12:
                WakeLockProviderGetWakeLockWithoutContextParams v7_3 = WakeLockProviderGetWakeLockWithoutContextParams.deserialize(v7_1.getPayload());
                this.getImpl().getWakeLockWithoutContext(v7_3.type, v7_3.reason, v7_3.description, v7_3.wakeLock);
                return 1;
            label_28:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(WakeLockProvider_Internal.MANAGER, v7_1);
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), WakeLockProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class WakeLockProviderGetWakeLockContextForIdParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest context;
        public int contextId;

        static {
            WakeLockProviderGetWakeLockContextForIdParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WakeLockProviderGetWakeLockContextForIdParams.DEFAULT_STRUCT_INFO = WakeLockProviderGetWakeLockContextForIdParams.VERSION_ARRAY[0];
        }

        public WakeLockProviderGetWakeLockContextForIdParams() {
            this(0);
        }

        private WakeLockProviderGetWakeLockContextForIdParams(int arg2) {
            super(16, arg2);
        }

        public static WakeLockProviderGetWakeLockContextForIdParams decode(Decoder arg3) {
            WakeLockProviderGetWakeLockContextForIdParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WakeLockProviderGetWakeLockContextForIdParams(arg3.readAndValidateDataHeader(WakeLockProviderGetWakeLockContextForIdParams.VERSION_ARRAY).elementsOrVersion);
                v1.contextId = arg3.readInt(8);
                v1.context = arg3.readInterfaceRequest(12, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WakeLockProviderGetWakeLockContextForIdParams deserialize(ByteBuffer arg2) {
            return WakeLockProviderGetWakeLockContextForIdParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockProviderGetWakeLockContextForIdParams deserialize(Message arg1) {
            return WakeLockProviderGetWakeLockContextForIdParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(WakeLockProviderGetWakeLockContextForIdParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.contextId, 8);
            arg4.encode(this.context, 12, false);
        }
    }

    final class WakeLockProviderGetWakeLockWithoutContextParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public String description;
        public int reason;
        public int type;
        public InterfaceRequest wakeLock;

        static {
            WakeLockProviderGetWakeLockWithoutContextParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            WakeLockProviderGetWakeLockWithoutContextParams.DEFAULT_STRUCT_INFO = WakeLockProviderGetWakeLockWithoutContextParams.VERSION_ARRAY[0];
        }

        public WakeLockProviderGetWakeLockWithoutContextParams() {
            this(0);
        }

        private WakeLockProviderGetWakeLockWithoutContextParams(int arg2) {
            super(0x20, arg2);
        }

        public static WakeLockProviderGetWakeLockWithoutContextParams decode(Decoder arg3) {
            WakeLockProviderGetWakeLockWithoutContextParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WakeLockProviderGetWakeLockWithoutContextParams(arg3.readAndValidateDataHeader(WakeLockProviderGetWakeLockWithoutContextParams.VERSION_ARRAY).elementsOrVersion);
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

        public static WakeLockProviderGetWakeLockWithoutContextParams deserialize(ByteBuffer arg2) {
            return WakeLockProviderGetWakeLockWithoutContextParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockProviderGetWakeLockWithoutContextParams deserialize(Message arg1) {
            return WakeLockProviderGetWakeLockWithoutContextParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(WakeLockProviderGetWakeLockWithoutContextParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.type, 8);
            arg4.encode(this.reason, 12);
            arg4.encode(this.description, 16, false);
            arg4.encode(this.wakeLock, 24, false);
        }
    }

    private static final int GET_WAKE_LOCK_CONTEXT_FOR_ID_ORDINAL = 0;
    private static final int GET_WAKE_LOCK_WITHOUT_CONTEXT_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        WakeLockProvider_Internal.MANAGER = new org.chromium.device.mojom.WakeLockProvider_Internal$1();
    }

    WakeLockProvider_Internal() {
        super();
    }
}

