package org.chromium.media.mojom;

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
import org.chromium.service_manager.mojom.InterfaceProvider;

class MediaService_Internal {
    final class org.chromium.media.mojom.MediaService_Internal$1 extends Manager {
        org.chromium.media.mojom.MediaService_Internal$1() {
            super();
        }

        public MediaService[] buildArray(int arg1) {
            return new MediaService[arg1];
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

        public Stub buildStub(Core arg2, MediaService arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((MediaService)arg2));
        }

        public String getName() {
            return "media::mojom::MediaService";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class MediaServiceCreateInterfaceFactoryParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest factory;
        public InterfaceProvider hostInterfaces;

        static {
            MediaServiceCreateInterfaceFactoryParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            MediaServiceCreateInterfaceFactoryParams.DEFAULT_STRUCT_INFO = MediaServiceCreateInterfaceFactoryParams.VERSION_ARRAY[0];
        }

        public MediaServiceCreateInterfaceFactoryParams() {
            this(0);
        }

        private MediaServiceCreateInterfaceFactoryParams(int arg2) {
            super(24, arg2);
        }

        public static MediaServiceCreateInterfaceFactoryParams decode(Decoder arg4) {
            MediaServiceCreateInterfaceFactoryParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new MediaServiceCreateInterfaceFactoryParams(arg4.readAndValidateDataHeader(MediaServiceCreateInterfaceFactoryParams.VERSION_ARRAY).elementsOrVersion);
                v1.factory = arg4.readInterfaceRequest(8, false);
                v1.hostInterfaces = arg4.readServiceInterface(12, true, InterfaceProvider.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static MediaServiceCreateInterfaceFactoryParams deserialize(ByteBuffer arg2) {
            return MediaServiceCreateInterfaceFactoryParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaServiceCreateInterfaceFactoryParams deserialize(Message arg1) {
            return MediaServiceCreateInterfaceFactoryParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(MediaServiceCreateInterfaceFactoryParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.factory, 8, false);
            arg5.encode(this.hostInterfaces, 12, true, InterfaceProvider.MANAGER);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.MediaService$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createInterfaceFactory(InterfaceRequest arg4, InterfaceProvider arg5) {
            MediaServiceCreateInterfaceFactoryParams v0 = new MediaServiceCreateInterfaceFactoryParams();
            v0.factory = arg4;
            v0.hostInterfaces = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, MediaService arg2) {
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

                    MediaServiceCreateInterfaceFactoryParams v4_2 = MediaServiceCreateInterfaceFactoryParams.deserialize(v4_1.getPayload());
                    this.getImpl().createInterfaceFactory(v4_2.factory, v4_2.hostInterfaces);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(MediaService_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), MediaService_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_INTERFACE_FACTORY_ORDINAL;
    public static final Manager MANAGER;

    static {
        MediaService_Internal.MANAGER = new org.chromium.media.mojom.MediaService_Internal$1();
    }

    MediaService_Internal() {
        super();
    }
}

