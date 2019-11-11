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

class SensorProvider_Internal {
    final class org.chromium.device.mojom.SensorProvider_Internal$1 extends Manager {
        org.chromium.device.mojom.SensorProvider_Internal$1() {
            super();
        }

        public SensorProvider[] buildArray(int arg1) {
            return new SensorProvider[arg1];
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

        public Stub buildStub(Core arg2, SensorProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((SensorProvider)arg2));
        }

        public String getName() {
            return "device::mojom::SensorProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.SensorProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getSensor(int arg8, GetSensorResponse arg9) {
            SensorProviderGetSensorParams v0 = new SensorProviderGetSensorParams();
            v0.type = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new SensorProviderGetSensorResponseParamsForwardToCallback(arg9));
        }
    }

    final class SensorProviderGetSensorParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int type;

        static {
            SensorProviderGetSensorParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SensorProviderGetSensorParams.DEFAULT_STRUCT_INFO = SensorProviderGetSensorParams.VERSION_ARRAY[0];
        }

        public SensorProviderGetSensorParams() {
            this(0);
        }

        private SensorProviderGetSensorParams(int arg2) {
            super(16, arg2);
        }

        public static SensorProviderGetSensorParams decode(Decoder arg2) {
            SensorProviderGetSensorParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SensorProviderGetSensorParams(arg2.readAndValidateDataHeader(SensorProviderGetSensorParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg2.readInt(8);
                SensorType.validate(v1.type);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SensorProviderGetSensorParams deserialize(ByteBuffer arg2) {
            return SensorProviderGetSensorParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorProviderGetSensorParams deserialize(Message arg1) {
            return SensorProviderGetSensorParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(SensorProviderGetSensorParams.DEFAULT_STRUCT_INFO).encode(this.type, 8);
        }
    }

    final class SensorProviderGetSensorResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public SensorInitParams initParams;
        public int result;

        static {
            SensorProviderGetSensorResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            SensorProviderGetSensorResponseParams.DEFAULT_STRUCT_INFO = SensorProviderGetSensorResponseParams.VERSION_ARRAY[0];
        }

        public SensorProviderGetSensorResponseParams() {
            this(0);
        }

        private SensorProviderGetSensorResponseParams(int arg2) {
            super(24, arg2);
        }

        public static SensorProviderGetSensorResponseParams decode(Decoder arg3) {
            SensorProviderGetSensorResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SensorProviderGetSensorResponseParams(arg3.readAndValidateDataHeader(SensorProviderGetSensorResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                SensorCreationResult.validate(v1.result);
                v1.initParams = SensorInitParams.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SensorProviderGetSensorResponseParams deserialize(ByteBuffer arg2) {
            return SensorProviderGetSensorResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SensorProviderGetSensorResponseParams deserialize(Message arg1) {
            return SensorProviderGetSensorResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(SensorProviderGetSensorResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.initParams, 16, true);
        }
    }

    class SensorProviderGetSensorResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetSensorResponse mCallback;

        SensorProviderGetSensorResponseParamsForwardToCallback(GetSensorResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                SensorProviderGetSensorResponseParams v4_1 = SensorProviderGetSensorResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.result), v4_1.initParams);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SensorProviderGetSensorResponseParamsProxyToResponder implements GetSensorResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SensorProviderGetSensorResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, SensorInitParams arg7) {
            SensorProviderGetSensorResponseParams v0 = new SensorProviderGetSensorResponseParams();
            v0.result = arg6.intValue();
            v0.initParams = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((SensorInitParams)arg2));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, SensorProvider arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(SensorProvider_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg9, MessageReceiver arg10) {
            try {
                ServiceMessage v9_1 = arg9.asServiceMessage();
                MessageHeader v1 = v9_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_20;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), SensorProvider_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().getSensor(SensorProviderGetSensorParams.deserialize(v9_1.getPayload()).type, new SensorProviderGetSensorResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int GET_SENSOR_ORDINAL;
    public static final Manager MANAGER;

    static {
        SensorProvider_Internal.MANAGER = new org.chromium.device.mojom.SensorProvider_Internal$1();
    }

    SensorProvider_Internal() {
        super();
    }
}

