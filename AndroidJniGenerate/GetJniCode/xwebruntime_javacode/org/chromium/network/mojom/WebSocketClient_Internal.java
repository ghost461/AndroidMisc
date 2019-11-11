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

class WebSocketClient_Internal {
    final class org.chromium.network.mojom.WebSocketClient_Internal$1 extends Manager {
        org.chromium.network.mojom.WebSocketClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public WebSocketClient[] buildArray(int arg1) {
            return new WebSocketClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.WebSocketClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.WebSocketClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((WebSocketClient)arg2));
        }

        public org.chromium.network.mojom.WebSocketClient_Internal$Stub buildStub(Core arg2, WebSocketClient arg3) {
            return new org.chromium.network.mojom.WebSocketClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::WebSocketClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.WebSocketClient_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.WebSocketClient$Proxy {
        org.chromium.network.mojom.WebSocketClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onAddChannelResponse(String arg4, String arg5) {
            WebSocketClientOnAddChannelResponseParams v0 = new WebSocketClientOnAddChannelResponseParams();
            v0.selectedProtocol = arg4;
            v0.extensions = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void onClosingHandshake() {
            this.getProxyHandler().getMessageReceiver().accept(new WebSocketClientOnClosingHandshakeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }

        public void onDataFrame(boolean arg3, int arg4, byte[] arg5) {
            WebSocketClientOnDataFrameParams v0 = new WebSocketClientOnDataFrameParams();
            v0.fin = arg3;
            v0.type = arg4;
            v0.data = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void onDropChannel(boolean arg3, short arg4, String arg5) {
            WebSocketClientOnDropChannelParams v0 = new WebSocketClientOnDropChannelParams();
            v0.wasClean = arg3;
            v0.code = arg4;
            v0.reason = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void onFailChannel(String arg5) {
            WebSocketClientOnFailChannelParams v0 = new WebSocketClientOnFailChannelParams();
            v0.reason = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onFinishOpeningHandshake(WebSocketHandshakeResponse arg5) {
            WebSocketClientOnFinishOpeningHandshakeParams v0 = new WebSocketClientOnFinishOpeningHandshakeParams();
            v0.response = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onFlowControl(long arg4) {
            WebSocketClientOnFlowControlParams v0 = new WebSocketClientOnFlowControlParams();
            v0.quota = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void onStartOpeningHandshake(WebSocketHandshakeRequest arg5) {
            WebSocketClientOnStartOpeningHandshakeParams v0 = new WebSocketClientOnStartOpeningHandshakeParams();
            v0.request = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class org.chromium.network.mojom.WebSocketClient_Internal$Stub extends Stub {
        org.chromium.network.mojom.WebSocketClient_Internal$Stub(Core arg1, WebSocketClient arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6_1 = arg6.asServiceMessage();
                MessageHeader v1 = v6_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_64;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_58;
                    }
                    case 1: {
                        goto label_52;
                    }
                    case 2: {
                        goto label_46;
                    }
                    case 3: {
                        goto label_39;
                    }
                    case 4: {
                        goto label_31;
                    }
                    case 5: {
                        goto label_25;
                    }
                    case 6: {
                        goto label_17;
                    }
                    case 7: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                WebSocketClientOnDropChannelParams v6_2 = WebSocketClientOnDropChannelParams.deserialize(v6_1.getPayload());
                this.getImpl().onDropChannel(v6_2.wasClean, v6_2.code, v6_2.reason);
                return 1;
            label_52:
                this.getImpl().onStartOpeningHandshake(WebSocketClientOnStartOpeningHandshakeParams.deserialize(v6_1.getPayload()).request);
                return 1;
            label_39:
                WebSocketClientOnAddChannelResponseParams v6_3 = WebSocketClientOnAddChannelResponseParams.deserialize(v6_1.getPayload());
                this.getImpl().onAddChannelResponse(v6_3.selectedProtocol, v6_3.extensions);
                return 1;
            label_25:
                this.getImpl().onFlowControl(WebSocketClientOnFlowControlParams.deserialize(v6_1.getPayload()).quota);
                return 1;
            label_58:
                this.getImpl().onFailChannel(WebSocketClientOnFailChannelParams.deserialize(v6_1.getPayload()).reason);
                return 1;
            label_12:
                WebSocketClientOnClosingHandshakeParams.deserialize(v6_1.getPayload());
                this.getImpl().onClosingHandshake();
                return 1;
            label_46:
                this.getImpl().onFinishOpeningHandshake(WebSocketClientOnFinishOpeningHandshakeParams.deserialize(v6_1.getPayload()).response);
                return 1;
            label_31:
                WebSocketClientOnDataFrameParams v6_4 = WebSocketClientOnDataFrameParams.deserialize(v6_1.getPayload());
                this.getImpl().onDataFrame(v6_4.fin, v6_4.type, v6_4.data);
                return 1;
            label_64:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(WebSocketClient_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), WebSocketClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class WebSocketClientOnAddChannelResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String extensions;
        public String selectedProtocol;

        static {
            WebSocketClientOnAddChannelResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            WebSocketClientOnAddChannelResponseParams.DEFAULT_STRUCT_INFO = WebSocketClientOnAddChannelResponseParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnAddChannelResponseParams() {
            this(0);
        }

        private WebSocketClientOnAddChannelResponseParams(int arg2) {
            super(24, arg2);
        }

        public static WebSocketClientOnAddChannelResponseParams decode(Decoder arg3) {
            WebSocketClientOnAddChannelResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnAddChannelResponseParams(arg3.readAndValidateDataHeader(WebSocketClientOnAddChannelResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.selectedProtocol = arg3.readString(8, false);
                v1.extensions = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WebSocketClientOnAddChannelResponseParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnAddChannelResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnAddChannelResponseParams deserialize(Message arg1) {
            return WebSocketClientOnAddChannelResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(WebSocketClientOnAddChannelResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.selectedProtocol, 8, false);
            arg4.encode(this.extensions, 16, false);
        }
    }

    final class WebSocketClientOnClosingHandshakeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            WebSocketClientOnClosingHandshakeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            WebSocketClientOnClosingHandshakeParams.DEFAULT_STRUCT_INFO = WebSocketClientOnClosingHandshakeParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnClosingHandshakeParams() {
            this(0);
        }

        private WebSocketClientOnClosingHandshakeParams(int arg2) {
            super(8, arg2);
        }

        public static WebSocketClientOnClosingHandshakeParams decode(Decoder arg2) {
            WebSocketClientOnClosingHandshakeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnClosingHandshakeParams(arg2.readAndValidateDataHeader(WebSocketClientOnClosingHandshakeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static WebSocketClientOnClosingHandshakeParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnClosingHandshakeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnClosingHandshakeParams deserialize(Message arg1) {
            return WebSocketClientOnClosingHandshakeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(WebSocketClientOnClosingHandshakeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class WebSocketClientOnDataFrameParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;
        public boolean fin;
        public int type;

        static {
            WebSocketClientOnDataFrameParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            WebSocketClientOnDataFrameParams.DEFAULT_STRUCT_INFO = WebSocketClientOnDataFrameParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnDataFrameParams() {
            this(0);
        }

        private WebSocketClientOnDataFrameParams(int arg2) {
            super(24, arg2);
        }

        public static WebSocketClientOnDataFrameParams decode(Decoder arg4) {
            WebSocketClientOnDataFrameParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnDataFrameParams(arg4.readAndValidateDataHeader(WebSocketClientOnDataFrameParams.VERSION_ARRAY).elementsOrVersion);
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

        public static WebSocketClientOnDataFrameParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnDataFrameParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnDataFrameParams deserialize(Message arg1) {
            return WebSocketClientOnDataFrameParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(WebSocketClientOnDataFrameParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.fin, 8, 0);
            arg5.encode(this.type, 12);
            arg5.encode(this.data, 16, 0, -1);
        }
    }

    final class WebSocketClientOnDropChannelParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public short code;
        public String reason;
        public boolean wasClean;

        static {
            WebSocketClientOnDropChannelParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            WebSocketClientOnDropChannelParams.DEFAULT_STRUCT_INFO = WebSocketClientOnDropChannelParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnDropChannelParams() {
            this(0);
        }

        private WebSocketClientOnDropChannelParams(int arg2) {
            super(24, arg2);
        }

        public static WebSocketClientOnDropChannelParams decode(Decoder arg3) {
            WebSocketClientOnDropChannelParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnDropChannelParams(arg3.readAndValidateDataHeader(WebSocketClientOnDropChannelParams.VERSION_ARRAY).elementsOrVersion);
                v1.wasClean = arg3.readBoolean(8, 0);
                v1.code = arg3.readShort(10);
                v1.reason = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WebSocketClientOnDropChannelParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnDropChannelParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnDropChannelParams deserialize(Message arg1) {
            return WebSocketClientOnDropChannelParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(WebSocketClientOnDropChannelParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.wasClean, 8, 0);
            arg4.encode(this.code, 10);
            arg4.encode(this.reason, 16, false);
        }
    }

    final class WebSocketClientOnFailChannelParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String reason;

        static {
            WebSocketClientOnFailChannelParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WebSocketClientOnFailChannelParams.DEFAULT_STRUCT_INFO = WebSocketClientOnFailChannelParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnFailChannelParams() {
            this(0);
        }

        private WebSocketClientOnFailChannelParams(int arg2) {
            super(16, arg2);
        }

        public static WebSocketClientOnFailChannelParams decode(Decoder arg3) {
            WebSocketClientOnFailChannelParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnFailChannelParams(arg3.readAndValidateDataHeader(WebSocketClientOnFailChannelParams.VERSION_ARRAY).elementsOrVersion);
                v1.reason = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WebSocketClientOnFailChannelParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnFailChannelParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnFailChannelParams deserialize(Message arg1) {
            return WebSocketClientOnFailChannelParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WebSocketClientOnFailChannelParams.DEFAULT_STRUCT_INFO).encode(this.reason, 8, false);
        }
    }

    final class WebSocketClientOnFinishOpeningHandshakeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public WebSocketHandshakeResponse response;

        static {
            WebSocketClientOnFinishOpeningHandshakeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WebSocketClientOnFinishOpeningHandshakeParams.DEFAULT_STRUCT_INFO = WebSocketClientOnFinishOpeningHandshakeParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnFinishOpeningHandshakeParams() {
            this(0);
        }

        private WebSocketClientOnFinishOpeningHandshakeParams(int arg2) {
            super(16, arg2);
        }

        public static WebSocketClientOnFinishOpeningHandshakeParams decode(Decoder arg3) {
            WebSocketClientOnFinishOpeningHandshakeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnFinishOpeningHandshakeParams(arg3.readAndValidateDataHeader(WebSocketClientOnFinishOpeningHandshakeParams.VERSION_ARRAY).elementsOrVersion);
                v1.response = WebSocketHandshakeResponse.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WebSocketClientOnFinishOpeningHandshakeParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnFinishOpeningHandshakeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnFinishOpeningHandshakeParams deserialize(Message arg1) {
            return WebSocketClientOnFinishOpeningHandshakeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WebSocketClientOnFinishOpeningHandshakeParams.DEFAULT_STRUCT_INFO).encode(this.response, 8, false);
        }
    }

    final class WebSocketClientOnFlowControlParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public long quota;

        static {
            WebSocketClientOnFlowControlParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WebSocketClientOnFlowControlParams.DEFAULT_STRUCT_INFO = WebSocketClientOnFlowControlParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnFlowControlParams() {
            this(0);
        }

        private WebSocketClientOnFlowControlParams(int arg2) {
            super(16, arg2);
        }

        public static WebSocketClientOnFlowControlParams decode(Decoder arg4) {
            WebSocketClientOnFlowControlParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnFlowControlParams(arg4.readAndValidateDataHeader(WebSocketClientOnFlowControlParams.VERSION_ARRAY).elementsOrVersion);
                v1.quota = arg4.readLong(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static WebSocketClientOnFlowControlParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnFlowControlParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnFlowControlParams deserialize(Message arg1) {
            return WebSocketClientOnFlowControlParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WebSocketClientOnFlowControlParams.DEFAULT_STRUCT_INFO).encode(this.quota, 8);
        }
    }

    final class WebSocketClientOnStartOpeningHandshakeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public WebSocketHandshakeRequest request;

        static {
            WebSocketClientOnStartOpeningHandshakeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WebSocketClientOnStartOpeningHandshakeParams.DEFAULT_STRUCT_INFO = WebSocketClientOnStartOpeningHandshakeParams.VERSION_ARRAY[0];
        }

        public WebSocketClientOnStartOpeningHandshakeParams() {
            this(0);
        }

        private WebSocketClientOnStartOpeningHandshakeParams(int arg2) {
            super(16, arg2);
        }

        public static WebSocketClientOnStartOpeningHandshakeParams decode(Decoder arg3) {
            WebSocketClientOnStartOpeningHandshakeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WebSocketClientOnStartOpeningHandshakeParams(arg3.readAndValidateDataHeader(WebSocketClientOnStartOpeningHandshakeParams.VERSION_ARRAY).elementsOrVersion);
                v1.request = WebSocketHandshakeRequest.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WebSocketClientOnStartOpeningHandshakeParams deserialize(ByteBuffer arg2) {
            return WebSocketClientOnStartOpeningHandshakeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WebSocketClientOnStartOpeningHandshakeParams deserialize(Message arg1) {
            return WebSocketClientOnStartOpeningHandshakeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WebSocketClientOnStartOpeningHandshakeParams.DEFAULT_STRUCT_INFO).encode(this.request, 8, false);
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_ADD_CHANNEL_RESPONSE_ORDINAL = 3;
    private static final int ON_CLOSING_HANDSHAKE_ORDINAL = 7;
    private static final int ON_DATA_FRAME_ORDINAL = 4;
    private static final int ON_DROP_CHANNEL_ORDINAL = 6;
    private static final int ON_FAIL_CHANNEL_ORDINAL = 0;
    private static final int ON_FINISH_OPENING_HANDSHAKE_ORDINAL = 2;
    private static final int ON_FLOW_CONTROL_ORDINAL = 5;
    private static final int ON_START_OPENING_HANDSHAKE_ORDINAL = 1;

    static {
        WebSocketClient_Internal.MANAGER = new org.chromium.network.mojom.WebSocketClient_Internal$1();
    }

    WebSocketClient_Internal() {
        super();
    }
}

