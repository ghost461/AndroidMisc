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
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class ContentDecryptionModuleClient_Internal {
    final class org.chromium.media.mojom.ContentDecryptionModuleClient_Internal$1 extends Manager {
        org.chromium.media.mojom.ContentDecryptionModuleClient_Internal$1() {
            super();
        }

        public ContentDecryptionModuleClient[] buildArray(int arg1) {
            return new ContentDecryptionModuleClient[arg1];
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

        public Stub buildStub(Core arg2, ContentDecryptionModuleClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ContentDecryptionModuleClient)arg2));
        }

        public String getName() {
            return "media::mojom::ContentDecryptionModuleClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ContentDecryptionModuleClientOnSessionClosedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String sessionId;

        static {
            ContentDecryptionModuleClientOnSessionClosedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleClientOnSessionClosedParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleClientOnSessionClosedParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleClientOnSessionClosedParams() {
            this(0);
        }

        private ContentDecryptionModuleClientOnSessionClosedParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleClientOnSessionClosedParams decode(Decoder arg3) {
            ContentDecryptionModuleClientOnSessionClosedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleClientOnSessionClosedParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleClientOnSessionClosedParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleClientOnSessionClosedParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleClientOnSessionClosedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleClientOnSessionClosedParams deserialize(Message arg1) {
            return ContentDecryptionModuleClientOnSessionClosedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ContentDecryptionModuleClientOnSessionClosedParams.DEFAULT_STRUCT_INFO).encode(this.sessionId, 8, false);
        }
    }

    final class ContentDecryptionModuleClientOnSessionExpirationUpdateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public double newExpiryTimeSec;
        public String sessionId;

        static {
            ContentDecryptionModuleClientOnSessionExpirationUpdateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ContentDecryptionModuleClientOnSessionExpirationUpdateParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleClientOnSessionExpirationUpdateParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleClientOnSessionExpirationUpdateParams() {
            this(0);
        }

        private ContentDecryptionModuleClientOnSessionExpirationUpdateParams(int arg2) {
            super(24, arg2);
        }

        public static ContentDecryptionModuleClientOnSessionExpirationUpdateParams decode(Decoder arg4) {
            ContentDecryptionModuleClientOnSessionExpirationUpdateParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleClientOnSessionExpirationUpdateParams(arg4.readAndValidateDataHeader(ContentDecryptionModuleClientOnSessionExpirationUpdateParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg4.readString(8, false);
                v1.newExpiryTimeSec = arg4.readDouble(16);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleClientOnSessionExpirationUpdateParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleClientOnSessionExpirationUpdateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleClientOnSessionExpirationUpdateParams deserialize(Message arg1) {
            return ContentDecryptionModuleClientOnSessionExpirationUpdateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ContentDecryptionModuleClientOnSessionExpirationUpdateParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.sessionId, 8, false);
            arg4.encode(this.newExpiryTimeSec, 16);
        }
    }

    final class ContentDecryptionModuleClientOnSessionKeysChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean hasAdditionalUsableKey;
        public CdmKeyInformation[] keysInfo;
        public String sessionId;

        static {
            ContentDecryptionModuleClientOnSessionKeysChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ContentDecryptionModuleClientOnSessionKeysChangeParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleClientOnSessionKeysChangeParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleClientOnSessionKeysChangeParams() {
            this(0);
        }

        private ContentDecryptionModuleClientOnSessionKeysChangeParams(int arg2) {
            super(0x20, arg2);
        }

        public static ContentDecryptionModuleClientOnSessionKeysChangeParams decode(Decoder arg8) {
            ContentDecryptionModuleClientOnSessionKeysChangeParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleClientOnSessionKeysChangeParams(arg8.readAndValidateDataHeader(ContentDecryptionModuleClientOnSessionKeysChangeParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.sessionId = arg8.readString(v0_1, false);
                v1.hasAdditionalUsableKey = arg8.readBoolean(16, 0);
                Decoder v3 = arg8.readPointer(24, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.keysInfo = new CdmKeyInformation[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.keysInfo[v5] = CdmKeyInformation.decode(v3.readPointer(v5 * 8 + v0_1, false));
                }
            }
            catch(Throwable v0) {
                goto label_37;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_37:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static ContentDecryptionModuleClientOnSessionKeysChangeParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleClientOnSessionKeysChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleClientOnSessionKeysChangeParams deserialize(Message arg1) {
            return ContentDecryptionModuleClientOnSessionKeysChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(ContentDecryptionModuleClientOnSessionKeysChangeParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg6.encode(this.sessionId, v1, false);
            arg6.encode(this.hasAdditionalUsableKey, 16, 0);
            int v3 = 24;
            if(this.keysInfo == null) {
                arg6.encodeNullPointer(v3, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.keysInfo.length, v3, -1);
                int v0;
                for(v0 = 0; v0 < this.keysInfo.length; ++v0) {
                    arg6.encode(this.keysInfo[v0], v0 * 8 + v1, false);
                }
            }
        }
    }

    final class ContentDecryptionModuleClientOnSessionMessageParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] message;
        public int messageType;
        public String sessionId;

        static {
            ContentDecryptionModuleClientOnSessionMessageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ContentDecryptionModuleClientOnSessionMessageParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleClientOnSessionMessageParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleClientOnSessionMessageParams() {
            this(0);
        }

        private ContentDecryptionModuleClientOnSessionMessageParams(int arg2) {
            super(0x20, arg2);
        }

        public static ContentDecryptionModuleClientOnSessionMessageParams decode(Decoder arg4) {
            ContentDecryptionModuleClientOnSessionMessageParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleClientOnSessionMessageParams(arg4.readAndValidateDataHeader(ContentDecryptionModuleClientOnSessionMessageParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg4.readString(8, false);
                v1.messageType = arg4.readInt(16);
                CdmMessageType.validate(v1.messageType);
                v1.message = arg4.readBytes(24, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleClientOnSessionMessageParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleClientOnSessionMessageParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleClientOnSessionMessageParams deserialize(Message arg1) {
            return ContentDecryptionModuleClientOnSessionMessageParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ContentDecryptionModuleClientOnSessionMessageParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.sessionId, 8, false);
            arg5.encode(this.messageType, 16);
            arg5.encode(this.message, 24, 0, -1);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.ContentDecryptionModuleClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onSessionClosed(String arg5) {
            ContentDecryptionModuleClientOnSessionClosedParams v0 = new ContentDecryptionModuleClientOnSessionClosedParams();
            v0.sessionId = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onSessionExpirationUpdate(String arg3, double arg4) {
            ContentDecryptionModuleClientOnSessionExpirationUpdateParams v0 = new ContentDecryptionModuleClientOnSessionExpirationUpdateParams();
            v0.sessionId = arg3;
            v0.newExpiryTimeSec = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void onSessionKeysChange(String arg3, boolean arg4, CdmKeyInformation[] arg5) {
            ContentDecryptionModuleClientOnSessionKeysChangeParams v0 = new ContentDecryptionModuleClientOnSessionKeysChangeParams();
            v0.sessionId = arg3;
            v0.hasAdditionalUsableKey = arg4;
            v0.keysInfo = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onSessionMessage(String arg3, int arg4, byte[] arg5) {
            ContentDecryptionModuleClientOnSessionMessageParams v0 = new ContentDecryptionModuleClientOnSessionMessageParams();
            v0.sessionId = arg3;
            v0.messageType = arg4;
            v0.message = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ContentDecryptionModuleClient arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_41;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_33;
                    }
                    case 1: {
                        goto label_27;
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
                ContentDecryptionModuleClientOnSessionMessageParams v7_2 = ContentDecryptionModuleClientOnSessionMessageParams.deserialize(v7_1.getPayload());
                this.getImpl().onSessionMessage(v7_2.sessionId, v7_2.messageType, v7_2.message);
                return 1;
            label_19:
                ContentDecryptionModuleClientOnSessionKeysChangeParams v7_3 = ContentDecryptionModuleClientOnSessionKeysChangeParams.deserialize(v7_1.getPayload());
                this.getImpl().onSessionKeysChange(v7_3.sessionId, v7_3.hasAdditionalUsableKey, v7_3.keysInfo);
                return 1;
            label_27:
                this.getImpl().onSessionClosed(ContentDecryptionModuleClientOnSessionClosedParams.deserialize(v7_1.getPayload()).sessionId);
                return 1;
            label_12:
                ContentDecryptionModuleClientOnSessionExpirationUpdateParams v7_4 = ContentDecryptionModuleClientOnSessionExpirationUpdateParams.deserialize(v7_1.getPayload());
                this.getImpl().onSessionExpirationUpdate(v7_4.sessionId, v7_4.newExpiryTimeSec);
                return 1;
            label_41:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ContentDecryptionModuleClient_Internal.MANAGER, v7_1);
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ContentDecryptionModuleClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_SESSION_CLOSED_ORDINAL = 1;
    private static final int ON_SESSION_EXPIRATION_UPDATE_ORDINAL = 3;
    private static final int ON_SESSION_KEYS_CHANGE_ORDINAL = 2;
    private static final int ON_SESSION_MESSAGE_ORDINAL;

    static {
        ContentDecryptionModuleClient_Internal.MANAGER = new org.chromium.media.mojom.ContentDecryptionModuleClient_Internal$1();
    }

    ContentDecryptionModuleClient_Internal() {
        super();
    }
}

