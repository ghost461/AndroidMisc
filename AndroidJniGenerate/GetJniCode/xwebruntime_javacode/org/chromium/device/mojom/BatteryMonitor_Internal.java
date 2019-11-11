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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class BatteryMonitor_Internal {
    final class org.chromium.device.mojom.BatteryMonitor_Internal$1 extends Manager {
        org.chromium.device.mojom.BatteryMonitor_Internal$1() {
            super();
        }

        public BatteryMonitor[] buildArray(int arg1) {
            return new BatteryMonitor[arg1];
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

        public Stub buildStub(Core arg2, BatteryMonitor arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((BatteryMonitor)arg2));
        }

        public String getName() {
            return "device::mojom::BatteryMonitor";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BatteryMonitorQueryNextStatusParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BatteryMonitorQueryNextStatusParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BatteryMonitorQueryNextStatusParams.DEFAULT_STRUCT_INFO = BatteryMonitorQueryNextStatusParams.VERSION_ARRAY[0];
        }

        public BatteryMonitorQueryNextStatusParams() {
            this(0);
        }

        private BatteryMonitorQueryNextStatusParams(int arg2) {
            super(8, arg2);
        }

        public static BatteryMonitorQueryNextStatusParams decode(Decoder arg2) {
            BatteryMonitorQueryNextStatusParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BatteryMonitorQueryNextStatusParams(arg2.readAndValidateDataHeader(BatteryMonitorQueryNextStatusParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BatteryMonitorQueryNextStatusParams deserialize(ByteBuffer arg2) {
            return BatteryMonitorQueryNextStatusParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BatteryMonitorQueryNextStatusParams deserialize(Message arg1) {
            return BatteryMonitorQueryNextStatusParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BatteryMonitorQueryNextStatusParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class BatteryMonitorQueryNextStatusResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public BatteryStatus status;

        static {
            BatteryMonitorQueryNextStatusResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BatteryMonitorQueryNextStatusResponseParams.DEFAULT_STRUCT_INFO = BatteryMonitorQueryNextStatusResponseParams.VERSION_ARRAY[0];
        }

        public BatteryMonitorQueryNextStatusResponseParams() {
            this(0);
        }

        private BatteryMonitorQueryNextStatusResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BatteryMonitorQueryNextStatusResponseParams decode(Decoder arg3) {
            BatteryMonitorQueryNextStatusResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BatteryMonitorQueryNextStatusResponseParams(arg3.readAndValidateDataHeader(BatteryMonitorQueryNextStatusResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = BatteryStatus.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BatteryMonitorQueryNextStatusResponseParams deserialize(ByteBuffer arg2) {
            return BatteryMonitorQueryNextStatusResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BatteryMonitorQueryNextStatusResponseParams deserialize(Message arg1) {
            return BatteryMonitorQueryNextStatusResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BatteryMonitorQueryNextStatusResponseParams.DEFAULT_STRUCT_INFO).encode(this.status, 8, false);
        }
    }

    class BatteryMonitorQueryNextStatusResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final QueryNextStatusResponse mCallback;

        BatteryMonitorQueryNextStatusResponseParamsForwardToCallback(QueryNextStatusResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(BatteryMonitorQueryNextStatusResponseParams.deserialize(v4.getPayload()).status);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BatteryMonitorQueryNextStatusResponseParamsProxyToResponder implements QueryNextStatusResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BatteryMonitorQueryNextStatusResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((BatteryStatus)arg1));
        }

        public void call(BatteryStatus arg7) {
            BatteryMonitorQueryNextStatusResponseParams v0 = new BatteryMonitorQueryNextStatusResponseParams();
            v0.status = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.BatteryMonitor$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void queryNextStatus(QueryNextStatusResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new BatteryMonitorQueryNextStatusParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new BatteryMonitorQueryNextStatusResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, BatteryMonitor arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                if(v1.getType() != -2) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(BatteryMonitor_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_19;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), BatteryMonitor_Internal.MANAGER, v8_1, arg9);
            label_10:
                BatteryMonitorQueryNextStatusParams.deserialize(v8_1.getPayload());
                this.getImpl().queryNextStatus(new BatteryMonitorQueryNextStatusResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int QUERY_NEXT_STATUS_ORDINAL;

    static {
        BatteryMonitor_Internal.MANAGER = new org.chromium.device.mojom.BatteryMonitor_Internal$1();
    }

    BatteryMonitor_Internal() {
        super();
    }
}

