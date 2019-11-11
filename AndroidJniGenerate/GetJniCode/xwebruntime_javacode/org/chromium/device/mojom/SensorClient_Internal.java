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

class SensorClient_Internal {
    final class org.chromium.device.mojom.SensorClient_Internal$1 extends Manager {
        org.chromium.device.mojom.SensorClient_Internal$1() {
            super();
        }

        public SensorClient[] buildArray(int arg1) {
            return new SensorClient[arg1];
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

        public Stub buildStub(Core arg2, SensorClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((SensorClient)arg2));
        }

        public String getName() {
            return "device::mojom::SensorClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.SensorClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void raiseError() {
            this.getProxyHandler().getMessageReceiver().accept(new SensorClientRaiseErrorParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void sensorReadingChanged() {
            this.getProxyHandler().getMessageReceiver().accept(new SensorClientSensorReadingChangedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class SensorClientRaiseErrorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SensorClientRaiseErrorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SensorClientRaiseErrorParams.DEFAULT_STRUCT_INFO = SensorClientRaiseErrorParams.VERSION_ARRAY[0];
        }

        public SensorClientRaiseErrorParams() {
            this(0);
        }

        private SensorClientRaiseErrorParams(int arg2) {
            super(8, arg2);
        }

        public static SensorClientRaiseErrorParams decode(Decoder arg2) {
            SensorClientRaiseErrorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SensorClientRaiseErrorParams(arg2.readAndValidateDataHeader(SensorClientRaiseErrorParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SensorClientRaiseErrorParams deserialize(ByteBuffer arg2) {
            return SensorClientRaiseErrorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorClientRaiseErrorParams deserialize(Message arg1) {
            return SensorClientRaiseErrorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SensorClientRaiseErrorParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SensorClientSensorReadingChangedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SensorClientSensorReadingChangedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SensorClientSensorReadingChangedParams.DEFAULT_STRUCT_INFO = SensorClientSensorReadingChangedParams.VERSION_ARRAY[0];
        }

        public SensorClientSensorReadingChangedParams() {
            this(0);
        }

        private SensorClientSensorReadingChangedParams(int arg2) {
            super(8, arg2);
        }

        public static SensorClientSensorReadingChangedParams decode(Decoder arg2) {
            SensorClientSensorReadingChangedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SensorClientSensorReadingChangedParams(arg2.readAndValidateDataHeader(SensorClientSensorReadingChangedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SensorClientSensorReadingChangedParams deserialize(ByteBuffer arg2) {
            return SensorClientSensorReadingChangedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorClientSensorReadingChangedParams deserialize(Message arg1) {
            return SensorClientSensorReadingChangedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SensorClientSensorReadingChangedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, SensorClient arg2) {
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
                    goto label_22;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_17;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                SensorClientRaiseErrorParams.deserialize(v4_1.getPayload());
                this.getImpl().raiseError();
                return 1;
            label_12:
                SensorClientSensorReadingChangedParams.deserialize(v4_1.getPayload());
                this.getImpl().sensorReadingChanged();
                return 1;
            label_22:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(SensorClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), SensorClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int RAISE_ERROR_ORDINAL = 0;
    private static final int SENSOR_READING_CHANGED_ORDINAL = 1;

    static {
        SensorClient_Internal.MANAGER = new org.chromium.device.mojom.SensorClient_Internal$1();
    }

    SensorClient_Internal() {
        super();
    }
}

