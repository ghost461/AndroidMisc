package org.chromium.blink.mojom;

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

class MediaSessionClient_Internal {
    final class org.chromium.blink.mojom.MediaSessionClient_Internal$1 extends Manager {
        org.chromium.blink.mojom.MediaSessionClient_Internal$1() {
            super();
        }

        public MediaSessionClient[] buildArray(int arg1) {
            return new MediaSessionClient[arg1];
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

        public Stub buildStub(Core arg2, MediaSessionClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((MediaSessionClient)arg2));
        }

        public String getName() {
            return "blink::mojom::MediaSessionClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class MediaSessionClientDidReceiveActionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int action;

        static {
            MediaSessionClientDidReceiveActionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaSessionClientDidReceiveActionParams.DEFAULT_STRUCT_INFO = MediaSessionClientDidReceiveActionParams.VERSION_ARRAY[0];
        }

        public MediaSessionClientDidReceiveActionParams() {
            this(0);
        }

        private MediaSessionClientDidReceiveActionParams(int arg2) {
            super(16, arg2);
        }

        public static MediaSessionClientDidReceiveActionParams decode(Decoder arg2) {
            MediaSessionClientDidReceiveActionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaSessionClientDidReceiveActionParams(arg2.readAndValidateDataHeader(MediaSessionClientDidReceiveActionParams.VERSION_ARRAY).elementsOrVersion);
                v1.action = arg2.readInt(8);
                MediaSessionAction.validate(v1.action);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static MediaSessionClientDidReceiveActionParams deserialize(ByteBuffer arg2) {
            return MediaSessionClientDidReceiveActionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaSessionClientDidReceiveActionParams deserialize(Message arg1) {
            return MediaSessionClientDidReceiveActionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(MediaSessionClientDidReceiveActionParams.DEFAULT_STRUCT_INFO).encode(this.action, 8);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.MediaSessionClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void didReceiveAction(int arg5) {
            MediaSessionClientDidReceiveActionParams v0 = new MediaSessionClientDidReceiveActionParams();
            v0.action = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, MediaSessionClient arg2) {
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

                    this.getImpl().didReceiveAction(MediaSessionClientDidReceiveActionParams.deserialize(v4_1.getPayload()).action);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(MediaSessionClient_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), MediaSessionClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int DID_RECEIVE_ACTION_ORDINAL;
    public static final Manager MANAGER;

    static {
        MediaSessionClient_Internal.MANAGER = new org.chromium.blink.mojom.MediaSessionClient_Internal$1();
    }

    MediaSessionClient_Internal() {
        super();
    }
}

