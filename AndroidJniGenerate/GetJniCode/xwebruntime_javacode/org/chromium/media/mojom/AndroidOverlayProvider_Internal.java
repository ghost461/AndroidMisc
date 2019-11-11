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

class AndroidOverlayProvider_Internal {
    final class org.chromium.media.mojom.AndroidOverlayProvider_Internal$1 extends Manager {
        org.chromium.media.mojom.AndroidOverlayProvider_Internal$1() {
            super();
        }

        public AndroidOverlayProvider[] buildArray(int arg1) {
            return new AndroidOverlayProvider[arg1];
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

        public Stub buildStub(Core arg2, AndroidOverlayProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AndroidOverlayProvider)arg2));
        }

        public String getName() {
            return "media::mojom::AndroidOverlayProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AndroidOverlayProviderCreateOverlayParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public AndroidOverlayClient client;
        public AndroidOverlayConfig config;
        public InterfaceRequest overlay;

        static {
            AndroidOverlayProviderCreateOverlayParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            AndroidOverlayProviderCreateOverlayParams.DEFAULT_STRUCT_INFO = AndroidOverlayProviderCreateOverlayParams.VERSION_ARRAY[0];
        }

        public AndroidOverlayProviderCreateOverlayParams() {
            this(0);
        }

        private AndroidOverlayProviderCreateOverlayParams(int arg2) {
            super(0x20, arg2);
        }

        public static AndroidOverlayProviderCreateOverlayParams decode(Decoder arg4) {
            AndroidOverlayProviderCreateOverlayParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new AndroidOverlayProviderCreateOverlayParams(arg4.readAndValidateDataHeader(AndroidOverlayProviderCreateOverlayParams.VERSION_ARRAY).elementsOrVersion);
                v1.overlay = arg4.readInterfaceRequest(8, false);
                v1.client = arg4.readServiceInterface(12, false, AndroidOverlayClient.MANAGER);
                v1.config = AndroidOverlayConfig.decode(arg4.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static AndroidOverlayProviderCreateOverlayParams deserialize(ByteBuffer arg2) {
            return AndroidOverlayProviderCreateOverlayParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AndroidOverlayProviderCreateOverlayParams deserialize(Message arg1) {
            return AndroidOverlayProviderCreateOverlayParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(AndroidOverlayProviderCreateOverlayParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.overlay, 8, false);
            arg5.encode(this.client, 12, false, AndroidOverlayClient.MANAGER);
            arg5.encode(this.config, 24, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AndroidOverlayProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createOverlay(InterfaceRequest arg3, AndroidOverlayClient arg4, AndroidOverlayConfig arg5) {
            AndroidOverlayProviderCreateOverlayParams v0 = new AndroidOverlayProviderCreateOverlayParams();
            v0.overlay = arg3;
            v0.client = arg4;
            v0.config = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AndroidOverlayProvider arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5_1 = arg5.asServiceMessage();
                MessageHeader v1 = v5_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 != -2) {
                    if(v1_1 != 0) {
                        return 0;
                    }

                    AndroidOverlayProviderCreateOverlayParams v5_2 = AndroidOverlayProviderCreateOverlayParams.deserialize(v5_1.getPayload());
                    this.getImpl().createOverlay(v5_2.overlay, v5_2.client, v5_2.config);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AndroidOverlayProvider_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AndroidOverlayProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_OVERLAY_ORDINAL;
    public static final Manager MANAGER;

    static {
        AndroidOverlayProvider_Internal.MANAGER = new org.chromium.media.mojom.AndroidOverlayProvider_Internal$1();
    }

    AndroidOverlayProvider_Internal() {
        super();
    }
}

