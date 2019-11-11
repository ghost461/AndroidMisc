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
import org.chromium.network.mojom.MutablePartialNetworkTrafficAnnotationTag;

class PublicIpAddressGeolocationProvider_Internal {
    final class org.chromium.device.mojom.PublicIpAddressGeolocationProvider_Internal$1 extends Manager {
        org.chromium.device.mojom.PublicIpAddressGeolocationProvider_Internal$1() {
            super();
        }

        public PublicIpAddressGeolocationProvider[] buildArray(int arg1) {
            return new PublicIpAddressGeolocationProvider[arg1];
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

        public Stub buildStub(Core arg2, PublicIpAddressGeolocationProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((PublicIpAddressGeolocationProvider)arg2));
        }

        public String getName() {
            return "device::mojom::PublicIpAddressGeolocationProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.PublicIpAddressGeolocationProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createGeolocation(MutablePartialNetworkTrafficAnnotationTag arg4, InterfaceRequest arg5) {
            PublicIpAddressGeolocationProviderCreateGeolocationParams v0 = new PublicIpAddressGeolocationProviderCreateGeolocationParams();
            v0.tag = arg4;
            v0.request = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class PublicIpAddressGeolocationProviderCreateGeolocationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest request;
        public MutablePartialNetworkTrafficAnnotationTag tag;

        static {
            PublicIpAddressGeolocationProviderCreateGeolocationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            PublicIpAddressGeolocationProviderCreateGeolocationParams.DEFAULT_STRUCT_INFO = PublicIpAddressGeolocationProviderCreateGeolocationParams.VERSION_ARRAY[0];
        }

        public PublicIpAddressGeolocationProviderCreateGeolocationParams() {
            this(0);
        }

        private PublicIpAddressGeolocationProviderCreateGeolocationParams(int arg2) {
            super(24, arg2);
        }

        public static PublicIpAddressGeolocationProviderCreateGeolocationParams decode(Decoder arg3) {
            PublicIpAddressGeolocationProviderCreateGeolocationParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PublicIpAddressGeolocationProviderCreateGeolocationParams(arg3.readAndValidateDataHeader(PublicIpAddressGeolocationProviderCreateGeolocationParams.VERSION_ARRAY).elementsOrVersion);
                v1.tag = MutablePartialNetworkTrafficAnnotationTag.decode(arg3.readPointer(8, false));
                v1.request = arg3.readInterfaceRequest(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PublicIpAddressGeolocationProviderCreateGeolocationParams deserialize(ByteBuffer arg2) {
            return PublicIpAddressGeolocationProviderCreateGeolocationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PublicIpAddressGeolocationProviderCreateGeolocationParams deserialize(Message arg1) {
            return PublicIpAddressGeolocationProviderCreateGeolocationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(PublicIpAddressGeolocationProviderCreateGeolocationParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.tag, 8, false);
            arg4.encode(this.request, 16, false);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, PublicIpAddressGeolocationProvider arg2) {
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

                    PublicIpAddressGeolocationProviderCreateGeolocationParams v4_2 = PublicIpAddressGeolocationProviderCreateGeolocationParams.deserialize(v4_1.getPayload());
                    this.getImpl().createGeolocation(v4_2.tag, v4_2.request);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(PublicIpAddressGeolocationProvider_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), PublicIpAddressGeolocationProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_GEOLOCATION_ORDINAL;
    public static final Manager MANAGER;

    static {
        PublicIpAddressGeolocationProvider_Internal.MANAGER = new org.chromium.device.mojom.PublicIpAddressGeolocationProvider_Internal$1();
    }

    PublicIpAddressGeolocationProvider_Internal() {
        super();
    }
}

