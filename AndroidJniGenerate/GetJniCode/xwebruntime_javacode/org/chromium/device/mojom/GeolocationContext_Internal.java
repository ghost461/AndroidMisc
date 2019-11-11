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

class GeolocationContext_Internal {
    final class org.chromium.device.mojom.GeolocationContext_Internal$1 extends Manager {
        org.chromium.device.mojom.GeolocationContext_Internal$1() {
            super();
        }

        public GeolocationContext[] buildArray(int arg1) {
            return new GeolocationContext[arg1];
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

        public Stub buildStub(Core arg2, GeolocationContext arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((GeolocationContext)arg2));
        }

        public String getName() {
            return "device::mojom::GeolocationContext";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class GeolocationContextBindGeolocationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest request;

        static {
            GeolocationContextBindGeolocationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            GeolocationContextBindGeolocationParams.DEFAULT_STRUCT_INFO = GeolocationContextBindGeolocationParams.VERSION_ARRAY[0];
        }

        public GeolocationContextBindGeolocationParams() {
            this(0);
        }

        private GeolocationContextBindGeolocationParams(int arg2) {
            super(16, arg2);
        }

        public static GeolocationContextBindGeolocationParams decode(Decoder arg3) {
            GeolocationContextBindGeolocationParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new GeolocationContextBindGeolocationParams(arg3.readAndValidateDataHeader(GeolocationContextBindGeolocationParams.VERSION_ARRAY).elementsOrVersion);
                v1.request = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static GeolocationContextBindGeolocationParams deserialize(ByteBuffer arg2) {
            return GeolocationContextBindGeolocationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationContextBindGeolocationParams deserialize(Message arg1) {
            return GeolocationContextBindGeolocationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(GeolocationContextBindGeolocationParams.DEFAULT_STRUCT_INFO).encode(this.request, 8, false);
        }
    }

    final class GeolocationContextClearOverrideParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            GeolocationContextClearOverrideParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            GeolocationContextClearOverrideParams.DEFAULT_STRUCT_INFO = GeolocationContextClearOverrideParams.VERSION_ARRAY[0];
        }

        public GeolocationContextClearOverrideParams() {
            this(0);
        }

        private GeolocationContextClearOverrideParams(int arg2) {
            super(8, arg2);
        }

        public static GeolocationContextClearOverrideParams decode(Decoder arg2) {
            GeolocationContextClearOverrideParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new GeolocationContextClearOverrideParams(arg2.readAndValidateDataHeader(GeolocationContextClearOverrideParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static GeolocationContextClearOverrideParams deserialize(ByteBuffer arg2) {
            return GeolocationContextClearOverrideParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationContextClearOverrideParams deserialize(Message arg1) {
            return GeolocationContextClearOverrideParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(GeolocationContextClearOverrideParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class GeolocationContextSetOverrideParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Geoposition geoposition;

        static {
            GeolocationContextSetOverrideParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            GeolocationContextSetOverrideParams.DEFAULT_STRUCT_INFO = GeolocationContextSetOverrideParams.VERSION_ARRAY[0];
        }

        public GeolocationContextSetOverrideParams() {
            this(0);
        }

        private GeolocationContextSetOverrideParams(int arg2) {
            super(16, arg2);
        }

        public static GeolocationContextSetOverrideParams decode(Decoder arg3) {
            GeolocationContextSetOverrideParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new GeolocationContextSetOverrideParams(arg3.readAndValidateDataHeader(GeolocationContextSetOverrideParams.VERSION_ARRAY).elementsOrVersion);
                v1.geoposition = Geoposition.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static GeolocationContextSetOverrideParams deserialize(ByteBuffer arg2) {
            return GeolocationContextSetOverrideParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationContextSetOverrideParams deserialize(Message arg1) {
            return GeolocationContextSetOverrideParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(GeolocationContextSetOverrideParams.DEFAULT_STRUCT_INFO).encode(this.geoposition, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.GeolocationContext$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void bindGeolocation(InterfaceRequest arg5) {
            GeolocationContextBindGeolocationParams v0 = new GeolocationContextBindGeolocationParams();
            v0.request = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void clearOverride() {
            this.getProxyHandler().getMessageReceiver().accept(new GeolocationContextClearOverrideParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void setOverride(Geoposition arg5) {
            GeolocationContextSetOverrideParams v0 = new GeolocationContextSetOverrideParams();
            v0.geoposition = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, GeolocationContext arg2) {
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
                    goto label_29;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_23;
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
                this.getImpl().setOverride(GeolocationContextSetOverrideParams.deserialize(v4_1.getPayload()).geoposition);
                return 1;
            label_23:
                this.getImpl().bindGeolocation(GeolocationContextBindGeolocationParams.deserialize(v4_1.getPayload()).request);
                return 1;
            label_12:
                GeolocationContextClearOverrideParams.deserialize(v4_1.getPayload());
                this.getImpl().clearOverride();
                return 1;
            label_29:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(GeolocationContext_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), GeolocationContext_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int BIND_GEOLOCATION_ORDINAL = 0;
    private static final int CLEAR_OVERRIDE_ORDINAL = 2;
    public static final Manager MANAGER = null;
    private static final int SET_OVERRIDE_ORDINAL = 1;

    static {
        GeolocationContext_Internal.MANAGER = new org.chromium.device.mojom.GeolocationContext_Internal$1();
    }

    GeolocationContext_Internal() {
        super();
    }
}

