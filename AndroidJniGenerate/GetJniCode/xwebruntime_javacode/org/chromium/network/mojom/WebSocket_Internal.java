package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface$Proxy;
import org.chromium.mojo.bindings.Interface$Stub;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.InterfaceControlMessagesHelper;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.url.mojom.Url;

class WebSocket_Internal {
    final class org.chromium.network.mojom.WebSocket_Internal$1 extends Manager {
        org.chromium.network.mojom.WebSocket_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public WebSocket[] buildArray(int arg1) {
            return new WebSocket[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.WebSocket_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.WebSocket_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((WebSocket)arg2));
        }

        public org.chromium.network.mojom.WebSocket_Internal$Stub buildStub(Core arg2, WebSocket arg3) {
            return new org.chromium.network.mojom.WebSocket_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::WebSocket";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.WebSocket_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.WebSocket$Proxy {
        org.chromium.network.mojom.WebSocket_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addChannelRequest(Url arg2, String[] arg3, Url arg4, String arg5, WebSocketClient arg6) {
            WebSocketAddChannelRequestParams v0 = new WebSocketAddChannelRequestParams();
            v0.url = arg2;
            v0.requestedProtocols = arg3;
            v0.firstPartyForCookies = arg4;
            v0.userAgentOverride = arg5;
            v0.client = arg6;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void sendFlowControl(long arg4) {
            WebSocketSendFlowControlParams v0 = new WebSocketSendFlowControlParams();
            v0.quota = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void sendFrame(boolean arg3, int arg4, byte[] arg5) {
            WebSocketSendFrameParams v0 = new WebSocketSendFrameParams();
            v0.fin = arg3;
            v0.type = arg4;
            v0.data = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void startClosingHandshake(short arg4, String arg5) {
            WebSocketStartClosingHandshakeParams v0 = new WebSocketStartClosingHandshakeParams();
            v0.code = arg4;
            v0.reason = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class org.chromium.network.mojom.WebSocket_Internal$Stub extends Stub {
        org.chromium.network.mojom.WebSocket_Internal$Stub(Core arg1, WebSocket arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg10) {
            try {
                ServiceMessage v10_1 = arg10.asServiceMessage();
                MessageHeader v1 = v10_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_44;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_33;
                    }
                    case 1: {
                        goto label_25;
                    }
                    case 2: {
                        goto label_19;
                    }
                    case 3: {
                        goto label_12;
                    }
                }

                return 0;
            label_33:
                WebSocketAddChannelRequestParams v10_2 = WebSocketAddChannelRequestParams.deserialize(v10_1.getPayload());
                this.getImpl().addChannelRequest(v10_2.url, v10_2.requestedProtocols, v10_2.firstPartyForCookies, v10_2.userAgentOverride, v10_2.client);
                return 1;
            label_19:
                this.getImpl().sendFlowControl(WebSocketSendFlowControlParams.deserialize(v10_1.getPayload()).quota);
                return 1;
            label_25:
                WebSocketSendFrameParams v10_3 = WebSocketSendFrameParams.deserialize(v10_1.getPayload());
                this.getImpl().sendFrame(v10_3.fin, v10_3.type, v10_3.data);
                return 1;
            label_12:
                WebSocketStartClosingHandshakeParams v10_4 = WebSocketStartClosingHandshakeParams.deserialize(v10_1.getPayload());
                this.getImpl().startClosingHandshake(v10_4.code, v10_4.reason);
                return 1;
            label_44:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(WebSocket_Internal.MANAGER, v10_1);
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), WebSocket_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class WebSocketAddChannelRequestParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public WebSocketClient client;
        public Url firstPartyForCookies;
        public String[] requestedProtocols;
        public Url url;
        public String userAgentOverride;

        static {
            WebSocketAddChannelRequestParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            WebSocketAddChannelRequestParams.DEFAULT_STRUCT_INFO = WebSocketAddChannelRequestParams.VERSION_ARRAY[0];
        }

        public WebSocketAddChannelRequestParams() {
            this(0);
        }

        private WebSocketAddChannelRequestParams(int arg2) {
            super(0x30, arg2);
        }

        public static WebSocketAddChannelRequestParams decode(Decoder arg8) {
            WebSocketAddChannelRequestParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new WebSocketAddChannelRequestParams(arg8.readAndValidateDataHeader(WebSocketAddChannelRequestParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.url = Url.decode(arg8.readPointer(v0_1, false));
                Decoder v3 = arg8.readPointer(16, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.requestedProtocols = new String[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.requestedProtocols[v5] = v3.readString(v5 * 8 + v0_1, false);
                }

                v1.firstPartyForCookies = Url.decode(arg8.readPointer(24, false));
                v1.userAgentOverride = arg8.readString(0x20, false);
                v1.client = arg8.readServiceInterface(40, false, WebSocketClient.MANAGER);
            }
            catch(Throwable v0) {
                goto label_45;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_45:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static WebSocketAddChannelRequestParams deserialize(ByteBuffer arg2) {
            return WebSocketAddChannelRequestParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketAddChannelRequestParams deserialize(Message arg1) {
            return WebSocketAddChannelRequestParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg7) {
            arg7 = arg7.getEncoderAtDataOffset(WebSocketAddChannelRequestParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg7.encode(this.url, v1, false);
            int v3 = 16;
            if(this.requestedProtocols == null) {
                arg7.encodeNullPointer(v3, false);
            }
            else {
                Encoder v0 = arg7.encodePointerArray(this.requestedProtocols.length, v3, -1);
                for(v3 = 0; v3 < this.requestedProtocols.length; ++v3) {
                    v0.encode(this.requestedProtocols[v3], v3 * 8 + v1, false);
                }
            }

            arg7.encode(this.firstPartyForCookies, 24, false);
            arg7.encode(this.userAgentOverride, 0x20, false);
            arg7.encode(this.client, 40, false, WebSocketClient.MANAGER);
        }
    }

    final class WebSocketSendFlowControlParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public long quota;

        static {
            WebSocketSendFlowControlParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WebSocketSendFlowControlParams.DEFAULT_STRUCT_INFO = WebSocketSendFlowControlParams.VERSION_ARRAY[0];
        }

        public WebSocketSendFlowControlParams() {
            this(0);
        }

        private WebSocketSendFlowControlParams(int arg2) {
            super(16, arg2);
        }

        public static WebSocketSendFlowControlParams decode(Decoder arg4) {
            WebSocketSendFlowControlParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new WebSocketSendFlowControlParams(arg4.readAndValidateDataHeader(WebSocketSendFlowControlParams.VERSION_ARRAY).elementsOrVersion);
                v1.quota = arg4.readLong(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static WebSocketSendFlowControlParams deserialize(ByteBuffer arg2) {
            return WebSocketSendFlowControlParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketSendFlowControlParams deserialize(Message arg1) {
            return WebSocketSendFlowControlParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WebSocketSendFlowControlParams.DEFAULT_STRUCT_INFO).encode(this.quota, 8);
        }
    }

    final class WebSocketSendFrameParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;
        public boolean fin;
        public int type;

        static {
            WebSocketSendFrameParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            WebSocketSendFrameParams.DEFAULT_STRUCT_INFO = WebSocketSendFrameParams.VERSION_ARRAY[0];
        }

        public WebSocketSendFrameParams() {
            this(0);
        }

        private WebSocketSendFrameParams(int arg2) {
            super(24, arg2);
        }

        public static WebSocketSendFrameParams decode(Decoder arg4) {
            WebSocketSendFrameParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new WebSocketSendFrameParams(arg4.readAndValidateDataHeader(WebSocketSendFrameParams.VERSION_ARRAY).elementsOrVersion);
                v1.fin = arg4.readBoolean(8, 0);
                v1.type = arg4.readInt(12);
                WebSocketMessageType.validate(v1.type);
                v1.data = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static WebSocketSendFrameParams deserialize(ByteBuffer arg2) {
            return WebSocketSendFrameParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketSendFrameParams deserialize(Message arg1) {
            return WebSocketSendFrameParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(WebSocketSendFrameParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.fin, 8, 0);
            arg5.encode(this.type, 12);
            arg5.encode(this.data, 16, 0, -1);
        }
    }

    final class WebSocketStartClosingHandshakeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public short code;
        public String reason;

        static {
            WebSocketStartClosingHandshakeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            WebSocketStartClosingHandshakeParams.DEFAULT_STRUCT_INFO = WebSocketStartClosingHandshakeParams.VERSION_ARRAY[0];
        }

        public WebSocketStartClosingHandshakeParams() {
            this(0);
        }

        private WebSocketStartClosingHandshakeParams(int arg2) {
            super(24, arg2);
        }

        public static WebSocketStartClosingHandshakeParams decode(Decoder arg3) {
            WebSocketStartClosingHandshakeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WebSocketStartClosingHandshakeParams(arg3.readAndValidateDataHeader(WebSocketStartClosingHandshakeParams.VERSION_ARRAY).elementsOrVersion);
                v1.code = arg3.readShort(8);
                v1.reason = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WebSocketStartClosingHandshakeParams deserialize(ByteBuffer arg2) {
            return WebSocketStartClosingHandshakeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketStartClosingHandshakeParams deserialize(Message arg1) {
            return WebSocketStartClosingHandshakeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(WebSocketStartClosingHandshakeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.code, 8);
            arg4.encode(this.reason, 16, false);
        }
    }

    private static final int ADD_CHANNEL_REQUEST_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int SEND_FLOW_CONTROL_ORDINAL = 2;
    private static final int SEND_FRAME_ORDINAL = 1;
    private static final int START_CLOSING_HANDSHAKE_ORDINAL = 3;

    static {
        WebSocket_Internal.MANAGER = new org.chromium.network.mojom.WebSocket_Internal$1();
    }

    WebSocket_Internal() {
        super();
    }
}

