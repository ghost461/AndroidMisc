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

class MediaSessionService_Internal {
    final class org.chromium.blink.mojom.MediaSessionService_Internal$1 extends Manager {
        org.chromium.blink.mojom.MediaSessionService_Internal$1() {
            super();
        }

        public MediaSessionService[] buildArray(int arg1) {
            return new MediaSessionService[arg1];
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

        public Stub buildStub(Core arg2, MediaSessionService arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((MediaSessionService)arg2));
        }

        public String getName() {
            return "blink::mojom::MediaSessionService";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class MediaSessionServiceDisableActionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int action;

        static {
            MediaSessionServiceDisableActionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaSessionServiceDisableActionParams.DEFAULT_STRUCT_INFO = MediaSessionServiceDisableActionParams.VERSION_ARRAY[0];
        }

        public MediaSessionServiceDisableActionParams() {
            this(0);
        }

        private MediaSessionServiceDisableActionParams(int arg2) {
            super(16, arg2);
        }

        public static MediaSessionServiceDisableActionParams decode(Decoder arg2) {
            MediaSessionServiceDisableActionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaSessionServiceDisableActionParams(arg2.readAndValidateDataHeader(MediaSessionServiceDisableActionParams.VERSION_ARRAY).elementsOrVersion);
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

        public static MediaSessionServiceDisableActionParams deserialize(ByteBuffer arg2) {
            return MediaSessionServiceDisableActionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaSessionServiceDisableActionParams deserialize(Message arg1) {
            return MediaSessionServiceDisableActionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(MediaSessionServiceDisableActionParams.DEFAULT_STRUCT_INFO).encode(this.action, 8);
        }
    }

    final class MediaSessionServiceEnableActionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int action;

        static {
            MediaSessionServiceEnableActionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaSessionServiceEnableActionParams.DEFAULT_STRUCT_INFO = MediaSessionServiceEnableActionParams.VERSION_ARRAY[0];
        }

        public MediaSessionServiceEnableActionParams() {
            this(0);
        }

        private MediaSessionServiceEnableActionParams(int arg2) {
            super(16, arg2);
        }

        public static MediaSessionServiceEnableActionParams decode(Decoder arg2) {
            MediaSessionServiceEnableActionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaSessionServiceEnableActionParams(arg2.readAndValidateDataHeader(MediaSessionServiceEnableActionParams.VERSION_ARRAY).elementsOrVersion);
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

        public static MediaSessionServiceEnableActionParams deserialize(ByteBuffer arg2) {
            return MediaSessionServiceEnableActionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaSessionServiceEnableActionParams deserialize(Message arg1) {
            return MediaSessionServiceEnableActionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(MediaSessionServiceEnableActionParams.DEFAULT_STRUCT_INFO).encode(this.action, 8);
        }
    }

    final class MediaSessionServiceSetClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public MediaSessionClient client;

        static {
            MediaSessionServiceSetClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaSessionServiceSetClientParams.DEFAULT_STRUCT_INFO = MediaSessionServiceSetClientParams.VERSION_ARRAY[0];
        }

        public MediaSessionServiceSetClientParams() {
            this(0);
        }

        private MediaSessionServiceSetClientParams(int arg2) {
            super(16, arg2);
        }

        public static MediaSessionServiceSetClientParams decode(Decoder arg4) {
            MediaSessionServiceSetClientParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new MediaSessionServiceSetClientParams(arg4.readAndValidateDataHeader(MediaSessionServiceSetClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg4.readServiceInterface(8, false, MediaSessionClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static MediaSessionServiceSetClientParams deserialize(ByteBuffer arg2) {
            return MediaSessionServiceSetClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaSessionServiceSetClientParams deserialize(Message arg1) {
            return MediaSessionServiceSetClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(MediaSessionServiceSetClientParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false, MediaSessionClient.MANAGER);
        }
    }

    final class MediaSessionServiceSetMetadataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public MediaMetadata metadata;

        static {
            MediaSessionServiceSetMetadataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaSessionServiceSetMetadataParams.DEFAULT_STRUCT_INFO = MediaSessionServiceSetMetadataParams.VERSION_ARRAY[0];
        }

        public MediaSessionServiceSetMetadataParams() {
            this(0);
        }

        private MediaSessionServiceSetMetadataParams(int arg2) {
            super(16, arg2);
        }

        public static MediaSessionServiceSetMetadataParams decode(Decoder arg3) {
            MediaSessionServiceSetMetadataParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaSessionServiceSetMetadataParams(arg3.readAndValidateDataHeader(MediaSessionServiceSetMetadataParams.VERSION_ARRAY).elementsOrVersion);
                v1.metadata = MediaMetadata.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaSessionServiceSetMetadataParams deserialize(ByteBuffer arg2) {
            return MediaSessionServiceSetMetadataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaSessionServiceSetMetadataParams deserialize(Message arg1) {
            return MediaSessionServiceSetMetadataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaSessionServiceSetMetadataParams.DEFAULT_STRUCT_INFO).encode(this.metadata, 8, true);
        }
    }

    final class MediaSessionServiceSetPlaybackStateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int state;

        static {
            MediaSessionServiceSetPlaybackStateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaSessionServiceSetPlaybackStateParams.DEFAULT_STRUCT_INFO = MediaSessionServiceSetPlaybackStateParams.VERSION_ARRAY[0];
        }

        public MediaSessionServiceSetPlaybackStateParams() {
            this(0);
        }

        private MediaSessionServiceSetPlaybackStateParams(int arg2) {
            super(16, arg2);
        }

        public static MediaSessionServiceSetPlaybackStateParams decode(Decoder arg2) {
            MediaSessionServiceSetPlaybackStateParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaSessionServiceSetPlaybackStateParams(arg2.readAndValidateDataHeader(MediaSessionServiceSetPlaybackStateParams.VERSION_ARRAY).elementsOrVersion);
                v1.state = arg2.readInt(8);
                MediaSessionPlaybackState.validate(v1.state);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static MediaSessionServiceSetPlaybackStateParams deserialize(ByteBuffer arg2) {
            return MediaSessionServiceSetPlaybackStateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaSessionServiceSetPlaybackStateParams deserialize(Message arg1) {
            return MediaSessionServiceSetPlaybackStateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(MediaSessionServiceSetPlaybackStateParams.DEFAULT_STRUCT_INFO).encode(this.state, 8);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.MediaSessionService$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void disableAction(int arg5) {
            MediaSessionServiceDisableActionParams v0 = new MediaSessionServiceDisableActionParams();
            v0.action = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void enableAction(int arg5) {
            MediaSessionServiceEnableActionParams v0 = new MediaSessionServiceEnableActionParams();
            v0.action = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void setClient(MediaSessionClient arg5) {
            MediaSessionServiceSetClientParams v0 = new MediaSessionServiceSetClientParams();
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void setMetadata(MediaMetadata arg5) {
            MediaSessionServiceSetMetadataParams v0 = new MediaSessionServiceSetMetadataParams();
            v0.metadata = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void setPlaybackState(int arg5) {
            MediaSessionServiceSetPlaybackStateParams v0 = new MediaSessionServiceSetPlaybackStateParams();
            v0.state = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, MediaSessionService arg2) {
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
                    goto label_42;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_36;
                    }
                    case 1: {
                        goto label_30;
                    }
                    case 2: {
                        goto label_24;
                    }
                    case 3: {
                        goto label_18;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().enableAction(MediaSessionServiceEnableActionParams.deserialize(v4_1.getPayload()).action);
                return 1;
            label_36:
                this.getImpl().setClient(MediaSessionServiceSetClientParams.deserialize(v4_1.getPayload()).client);
                return 1;
            label_24:
                this.getImpl().setMetadata(MediaSessionServiceSetMetadataParams.deserialize(v4_1.getPayload()).metadata);
                return 1;
            label_12:
                this.getImpl().disableAction(MediaSessionServiceDisableActionParams.deserialize(v4_1.getPayload()).action);
                return 1;
            label_30:
                this.getImpl().setPlaybackState(MediaSessionServiceSetPlaybackStateParams.deserialize(v4_1.getPayload()).state);
                return 1;
            label_42:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(MediaSessionService_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), MediaSessionService_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int DISABLE_ACTION_ORDINAL = 4;
    private static final int ENABLE_ACTION_ORDINAL = 3;
    public static final Manager MANAGER = null;
    private static final int SET_CLIENT_ORDINAL = 0;
    private static final int SET_METADATA_ORDINAL = 2;
    private static final int SET_PLAYBACK_STATE_ORDINAL = 1;

    static {
        MediaSessionService_Internal.MANAGER = new org.chromium.blink.mojom.MediaSessionService_Internal$1();
    }

    MediaSessionService_Internal() {
        super();
    }
}

