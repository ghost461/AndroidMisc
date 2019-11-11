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

class GeolocationControl_Internal {
    final class org.chromium.device.mojom.GeolocationControl_Internal$1 extends Manager {
        org.chromium.device.mojom.GeolocationControl_Internal$1() {
            super();
        }

        public GeolocationControl[] buildArray(int arg1) {
            return new GeolocationControl[arg1];
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

        public Stub buildStub(Core arg2, GeolocationControl arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((GeolocationControl)arg2));
        }

        public String getName() {
            return "device::mojom::GeolocationControl";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class GeolocationControlUserDidOptIntoLocationServicesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            GeolocationControlUserDidOptIntoLocationServicesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            GeolocationControlUserDidOptIntoLocationServicesParams.DEFAULT_STRUCT_INFO = GeolocationControlUserDidOptIntoLocationServicesParams.VERSION_ARRAY[0];
        }

        public GeolocationControlUserDidOptIntoLocationServicesParams() {
            this(0);
        }

        private GeolocationControlUserDidOptIntoLocationServicesParams(int arg2) {
            super(8, arg2);
        }

        public static GeolocationControlUserDidOptIntoLocationServicesParams decode(Decoder arg2) {
            GeolocationControlUserDidOptIntoLocationServicesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new GeolocationControlUserDidOptIntoLocationServicesParams(arg2.readAndValidateDataHeader(GeolocationControlUserDidOptIntoLocationServicesParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static GeolocationControlUserDidOptIntoLocationServicesParams deserialize(ByteBuffer arg2) {
            return GeolocationControlUserDidOptIntoLocationServicesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static GeolocationControlUserDidOptIntoLocationServicesParams deserialize(Message arg1) {
            return GeolocationControlUserDidOptIntoLocationServicesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(GeolocationControlUserDidOptIntoLocationServicesParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.GeolocationControl$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void userDidOptIntoLocationServices() {
            this.getProxyHandler().getMessageReceiver().accept(new GeolocationControlUserDidOptIntoLocationServicesParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, GeolocationControl arg2) {
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

                    GeolocationControlUserDidOptIntoLocationServicesParams.deserialize(v4_1.getPayload());
                    this.getImpl().userDidOptIntoLocationServices();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(GeolocationControl_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), GeolocationControl_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int USER_DID_OPT_INTO_LOCATION_SERVICES_ORDINAL;

    static {
        GeolocationControl_Internal.MANAGER = new org.chromium.device.mojom.GeolocationControl_Internal$1();
    }

    GeolocationControl_Internal() {
        super();
    }
}
