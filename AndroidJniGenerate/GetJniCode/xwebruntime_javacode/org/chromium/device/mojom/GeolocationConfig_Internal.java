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

class GeolocationConfig_Internal {
    final class org.chromium.device.mojom.GeolocationConfig_Internal$1 extends Manager {
        org.chromium.device.mojom.GeolocationConfig_Internal$1() {
            super();
        }

        public GeolocationConfig[] buildArray(int arg1) {
            return new GeolocationConfig[arg1];
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

        public Stub buildStub(Core arg2, GeolocationConfig arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((GeolocationConfig)arg2));
        }

        public String getName() {
            return "device::mojom::GeolocationConfig";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class GeolocationConfigIsHighAccuracyLocationBeingCapturedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.DEFAULT_STRUCT_INFO = GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.VERSION_ARRAY[0];
        }

        public GeolocationConfigIsHighAccuracyLocationBeingCapturedParams() {
            this(0);
        }

        private GeolocationConfigIsHighAccuracyLocationBeingCapturedParams(int arg2) {
            super(8, arg2);
        }

        public static GeolocationConfigIsHighAccuracyLocationBeingCapturedParams decode(Decoder arg2) {
            GeolocationConfigIsHighAccuracyLocationBeingCapturedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new GeolocationConfigIsHighAccuracyLocationBeingCapturedParams(arg2.readAndValidateDataHeader(GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static GeolocationConfigIsHighAccuracyLocationBeingCapturedParams deserialize(ByteBuffer arg2) {
            return GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationConfigIsHighAccuracyLocationBeingCapturedParams deserialize(Message arg1) {
            return GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean highAccuracy;

        static {
            GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.DEFAULT_STRUCT_INFO = GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.VERSION_ARRAY[0];
        }

        public GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams() {
            this(0);
        }

        private GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams(int arg2) {
            super(16, arg2);
        }

        public static GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams decode(Decoder arg3) {
            GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams(arg3.readAndValidateDataHeader(GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.highAccuracy = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams deserialize(ByteBuffer arg2) {
            return GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams deserialize(Message arg1) {
            return GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.DEFAULT_STRUCT_INFO).encode(this.highAccuracy, 8, 0);
        }
    }

    class GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final IsHighAccuracyLocationBeingCapturedResponse mCallback;

        GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParamsForwardToCallback(IsHighAccuracyLocationBeingCapturedResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams.deserialize(v4.getPayload()).highAccuracy));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParamsProxyToResponder implements IsHighAccuracyLocationBeingCapturedResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams v0 = new GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParams();
            v0.highAccuracy = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.GeolocationConfig$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void isHighAccuracyLocationBeingCaptured(IsHighAccuracyLocationBeingCapturedResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new GeolocationConfigIsHighAccuracyLocationBeingCapturedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, GeolocationConfig arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(GeolocationConfig_Internal.MANAGER, v4_1);
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
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), GeolocationConfig_Internal.MANAGER, v8_1, arg9);
            label_10:
                GeolocationConfigIsHighAccuracyLocationBeingCapturedParams.deserialize(v8_1.getPayload());
                this.getImpl().isHighAccuracyLocationBeingCaptured(new GeolocationConfigIsHighAccuracyLocationBeingCapturedResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int IS_HIGH_ACCURACY_LOCATION_BEING_CAPTURED_ORDINAL;
    public static final Manager MANAGER;

    static {
        GeolocationConfig_Internal.MANAGER = new org.chromium.device.mojom.GeolocationConfig_Internal$1();
    }

    GeolocationConfig_Internal() {
        super();
    }
}

