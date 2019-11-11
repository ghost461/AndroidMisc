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

class PowerMonitorClient_Internal {
    final class org.chromium.device.mojom.PowerMonitorClient_Internal$1 extends Manager {
        org.chromium.device.mojom.PowerMonitorClient_Internal$1() {
            super();
        }

        public PowerMonitorClient[] buildArray(int arg1) {
            return new PowerMonitorClient[arg1];
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

        public Stub buildStub(Core arg2, PowerMonitorClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((PowerMonitorClient)arg2));
        }

        public String getName() {
            return "device::mojom::PowerMonitorClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class PowerMonitorClientPowerStateChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean onBatteryPower;

        static {
            PowerMonitorClientPowerStateChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PowerMonitorClientPowerStateChangeParams.DEFAULT_STRUCT_INFO = PowerMonitorClientPowerStateChangeParams.VERSION_ARRAY[0];
        }

        public PowerMonitorClientPowerStateChangeParams() {
            this(0);
        }

        private PowerMonitorClientPowerStateChangeParams(int arg2) {
            super(16, arg2);
        }

        public static PowerMonitorClientPowerStateChangeParams decode(Decoder arg3) {
            PowerMonitorClientPowerStateChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PowerMonitorClientPowerStateChangeParams(arg3.readAndValidateDataHeader(PowerMonitorClientPowerStateChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.onBatteryPower = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PowerMonitorClientPowerStateChangeParams deserialize(ByteBuffer arg2) {
            return PowerMonitorClientPowerStateChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PowerMonitorClientPowerStateChangeParams deserialize(Message arg1) {
            return PowerMonitorClientPowerStateChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(PowerMonitorClientPowerStateChangeParams.DEFAULT_STRUCT_INFO).encode(this.onBatteryPower, 8, 0);
        }
    }

    final class PowerMonitorClientResumeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            PowerMonitorClientResumeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            PowerMonitorClientResumeParams.DEFAULT_STRUCT_INFO = PowerMonitorClientResumeParams.VERSION_ARRAY[0];
        }

        public PowerMonitorClientResumeParams() {
            this(0);
        }

        private PowerMonitorClientResumeParams(int arg2) {
            super(8, arg2);
        }

        public static PowerMonitorClientResumeParams decode(Decoder arg2) {
            PowerMonitorClientResumeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PowerMonitorClientResumeParams(arg2.readAndValidateDataHeader(PowerMonitorClientResumeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PowerMonitorClientResumeParams deserialize(ByteBuffer arg2) {
            return PowerMonitorClientResumeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PowerMonitorClientResumeParams deserialize(Message arg1) {
            return PowerMonitorClientResumeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(PowerMonitorClientResumeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class PowerMonitorClientSuspendParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            PowerMonitorClientSuspendParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            PowerMonitorClientSuspendParams.DEFAULT_STRUCT_INFO = PowerMonitorClientSuspendParams.VERSION_ARRAY[0];
        }

        public PowerMonitorClientSuspendParams() {
            this(0);
        }

        private PowerMonitorClientSuspendParams(int arg2) {
            super(8, arg2);
        }

        public static PowerMonitorClientSuspendParams decode(Decoder arg2) {
            PowerMonitorClientSuspendParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PowerMonitorClientSuspendParams(arg2.readAndValidateDataHeader(PowerMonitorClientSuspendParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PowerMonitorClientSuspendParams deserialize(ByteBuffer arg2) {
            return PowerMonitorClientSuspendParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PowerMonitorClientSuspendParams deserialize(Message arg1) {
            return PowerMonitorClientSuspendParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(PowerMonitorClientSuspendParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.PowerMonitorClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void powerStateChange(boolean arg5) {
            PowerMonitorClientPowerStateChangeParams v0 = new PowerMonitorClientPowerStateChangeParams();
            v0.onBatteryPower = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void resume() {
            this.getProxyHandler().getMessageReceiver().accept(new PowerMonitorClientResumeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void suspend() {
            this.getProxyHandler().getMessageReceiver().accept(new PowerMonitorClientSuspendParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, PowerMonitorClient arg2) {
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
                if(v1_1 == -2) {
                    goto label_28;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_22;
                    }
                    case 1: {
                        goto label_17;
                    }
                    case 2: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                PowerMonitorClientSuspendParams.deserialize(v4_1.getPayload());
                this.getImpl().suspend();
                return 1;
            label_22:
                this.getImpl().powerStateChange(PowerMonitorClientPowerStateChangeParams.deserialize(v4_1.getPayload()).onBatteryPower);
                return 1;
            label_12:
                PowerMonitorClientResumeParams.deserialize(v4_1.getPayload());
                this.getImpl().resume();
                return 1;
            label_28:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(PowerMonitorClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), PowerMonitorClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int POWER_STATE_CHANGE_ORDINAL = 0;
    private static final int RESUME_ORDINAL = 2;
    private static final int SUSPEND_ORDINAL = 1;

    static {
        PowerMonitorClient_Internal.MANAGER = new org.chromium.device.mojom.PowerMonitorClient_Internal$1();
    }

    PowerMonitorClient_Internal() {
        super();
    }
}

