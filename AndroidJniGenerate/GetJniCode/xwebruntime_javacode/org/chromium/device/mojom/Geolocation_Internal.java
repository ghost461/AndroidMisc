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

class Geolocation_Internal {
    final class org.chromium.device.mojom.Geolocation_Internal$1 extends Manager {
        org.chromium.device.mojom.Geolocation_Internal$1() {
            super();
        }

        public Geolocation[] buildArray(int arg1) {
            return new Geolocation[arg1];
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

        public Stub buildStub(Core arg2, Geolocation arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Geolocation)arg2));
        }

        public String getName() {
            return "device::mojom::Geolocation";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class GeolocationQueryNextPositionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            GeolocationQueryNextPositionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            GeolocationQueryNextPositionParams.DEFAULT_STRUCT_INFO = GeolocationQueryNextPositionParams.VERSION_ARRAY[0];
        }

        public GeolocationQueryNextPositionParams() {
            this(0);
        }

        private GeolocationQueryNextPositionParams(int arg2) {
            super(8, arg2);
        }

        public static GeolocationQueryNextPositionParams decode(Decoder arg2) {
            GeolocationQueryNextPositionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new GeolocationQueryNextPositionParams(arg2.readAndValidateDataHeader(GeolocationQueryNextPositionParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static GeolocationQueryNextPositionParams deserialize(ByteBuffer arg2) {
            return GeolocationQueryNextPositionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationQueryNextPositionParams deserialize(Message arg1) {
            return GeolocationQueryNextPositionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(GeolocationQueryNextPositionParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class GeolocationQueryNextPositionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Geoposition geoposition;

        static {
            GeolocationQueryNextPositionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            GeolocationQueryNextPositionResponseParams.DEFAULT_STRUCT_INFO = GeolocationQueryNextPositionResponseParams.VERSION_ARRAY[0];
        }

        public GeolocationQueryNextPositionResponseParams() {
            this(0);
        }

        private GeolocationQueryNextPositionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static GeolocationQueryNextPositionResponseParams decode(Decoder arg3) {
            GeolocationQueryNextPositionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new GeolocationQueryNextPositionResponseParams(arg3.readAndValidateDataHeader(GeolocationQueryNextPositionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.geoposition = Geoposition.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static GeolocationQueryNextPositionResponseParams deserialize(ByteBuffer arg2) {
            return GeolocationQueryNextPositionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationQueryNextPositionResponseParams deserialize(Message arg1) {
            return GeolocationQueryNextPositionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(GeolocationQueryNextPositionResponseParams.DEFAULT_STRUCT_INFO).encode(this.geoposition, 8, false);
        }
    }

    class GeolocationQueryNextPositionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final QueryNextPositionResponse mCallback;

        GeolocationQueryNextPositionResponseParamsForwardToCallback(QueryNextPositionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(GeolocationQueryNextPositionResponseParams.deserialize(v5.getPayload()).geoposition);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class GeolocationQueryNextPositionResponseParamsProxyToResponder implements QueryNextPositionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        GeolocationQueryNextPositionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((Geoposition)arg1));
        }

        public void call(Geoposition arg7) {
            GeolocationQueryNextPositionResponseParams v0 = new GeolocationQueryNextPositionResponseParams();
            v0.geoposition = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class GeolocationSetHighAccuracyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean highAccuracy;

        static {
            GeolocationSetHighAccuracyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            GeolocationSetHighAccuracyParams.DEFAULT_STRUCT_INFO = GeolocationSetHighAccuracyParams.VERSION_ARRAY[0];
        }

        public GeolocationSetHighAccuracyParams() {
            this(0);
        }

        private GeolocationSetHighAccuracyParams(int arg2) {
            super(16, arg2);
        }

        public static GeolocationSetHighAccuracyParams decode(Decoder arg3) {
            GeolocationSetHighAccuracyParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new GeolocationSetHighAccuracyParams(arg3.readAndValidateDataHeader(GeolocationSetHighAccuracyParams.VERSION_ARRAY).elementsOrVersion);
                v1.highAccuracy = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static GeolocationSetHighAccuracyParams deserialize(ByteBuffer arg2) {
            return GeolocationSetHighAccuracyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationSetHighAccuracyParams deserialize(Message arg1) {
            return GeolocationSetHighAccuracyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(GeolocationSetHighAccuracyParams.DEFAULT_STRUCT_INFO).encode(this.highAccuracy, 8, 0);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.Geolocation$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void queryNextPosition(QueryNextPositionResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new GeolocationQueryNextPositionParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new GeolocationQueryNextPositionResponseParamsForwardToCallback(arg8));
        }

        public void setHighAccuracy(boolean arg5) {
            GeolocationSetHighAccuracyParams v0 = new GeolocationSetHighAccuracyParams();
            v0.highAccuracy = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, Geolocation arg2) {
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

                    this.getImpl().setHighAccuracy(GeolocationSetHighAccuracyParams.deserialize(v4_1.getPayload()).highAccuracy);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Geolocation_Internal.MANAGER, v4_1);
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

                int v3 = v1.getType();
                if(v3 != -1) {
                    if(v3 != 1) {
                        return 0;
                    }

                    GeolocationQueryNextPositionParams.deserialize(v8_1.getPayload());
                    this.getImpl().queryNextPosition(new GeolocationQueryNextPositionResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Geolocation_Internal.MANAGER, v8_1, arg9);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int QUERY_NEXT_POSITION_ORDINAL = 1;
    private static final int SET_HIGH_ACCURACY_ORDINAL;

    static {
        Geolocation_Internal.MANAGER = new org.chromium.device.mojom.Geolocation_Internal$1();
    }

    Geolocation_Internal() {
        super();
    }
}

