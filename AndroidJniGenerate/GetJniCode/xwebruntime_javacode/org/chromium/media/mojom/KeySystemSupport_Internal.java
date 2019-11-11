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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class KeySystemSupport_Internal {
    final class org.chromium.media.mojom.KeySystemSupport_Internal$1 extends Manager {
        org.chromium.media.mojom.KeySystemSupport_Internal$1() {
            super();
        }

        public KeySystemSupport[] buildArray(int arg1) {
            return new KeySystemSupport[arg1];
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

        public Stub buildStub(Core arg2, KeySystemSupport arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((KeySystemSupport)arg2));
        }

        public String getName() {
            return "media::mojom::KeySystemSupport";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class KeySystemSupportIsKeySystemSupportedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String keySystem;

        static {
            KeySystemSupportIsKeySystemSupportedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            KeySystemSupportIsKeySystemSupportedParams.DEFAULT_STRUCT_INFO = KeySystemSupportIsKeySystemSupportedParams.VERSION_ARRAY[0];
        }

        public KeySystemSupportIsKeySystemSupportedParams() {
            this(0);
        }

        private KeySystemSupportIsKeySystemSupportedParams(int arg2) {
            super(16, arg2);
        }

        public static KeySystemSupportIsKeySystemSupportedParams decode(Decoder arg3) {
            KeySystemSupportIsKeySystemSupportedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new KeySystemSupportIsKeySystemSupportedParams(arg3.readAndValidateDataHeader(KeySystemSupportIsKeySystemSupportedParams.VERSION_ARRAY).elementsOrVersion);
                v1.keySystem = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static KeySystemSupportIsKeySystemSupportedParams deserialize(ByteBuffer arg2) {
            return KeySystemSupportIsKeySystemSupportedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static KeySystemSupportIsKeySystemSupportedParams deserialize(Message arg1) {
            return KeySystemSupportIsKeySystemSupportedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(KeySystemSupportIsKeySystemSupportedParams.DEFAULT_STRUCT_INFO).encode(this.keySystem, 8, false);
        }
    }

    final class KeySystemSupportIsKeySystemSupportedResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isSupported;
        public int[] supportedVideoCodecs;
        public boolean supportsPersistentLicense;

        static {
            KeySystemSupportIsKeySystemSupportedResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            KeySystemSupportIsKeySystemSupportedResponseParams.DEFAULT_STRUCT_INFO = KeySystemSupportIsKeySystemSupportedResponseParams.VERSION_ARRAY[0];
        }

        public KeySystemSupportIsKeySystemSupportedResponseParams() {
            this(0);
        }

        private KeySystemSupportIsKeySystemSupportedResponseParams(int arg2) {
            super(24, arg2);
        }

        public static KeySystemSupportIsKeySystemSupportedResponseParams decode(Decoder arg4) {
            KeySystemSupportIsKeySystemSupportedResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new KeySystemSupportIsKeySystemSupportedResponseParams(arg4.readAndValidateDataHeader(KeySystemSupportIsKeySystemSupportedResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 0;
                v1.isSupported = arg4.readBoolean(8, 0);
                v1.supportsPersistentLicense = arg4.readBoolean(8, 1);
                v1.supportedVideoCodecs = arg4.readInts(16, 0, -1);
                while(v2 < v1.supportedVideoCodecs.length) {
                    VideoCodec.validate(v1.supportedVideoCodecs[v2]);
                    ++v2;
                }
            }
            catch(Throwable v0) {
                goto label_31;
            }

            arg4.decreaseStackDepth();
            return v1;
        label_31:
            arg4.decreaseStackDepth();
            throw v0;
        }

        public static KeySystemSupportIsKeySystemSupportedResponseParams deserialize(ByteBuffer arg2) {
            return KeySystemSupportIsKeySystemSupportedResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static KeySystemSupportIsKeySystemSupportedResponseParams deserialize(Message arg1) {
            return KeySystemSupportIsKeySystemSupportedResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(KeySystemSupportIsKeySystemSupportedResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.isSupported, 8, 0);
            arg5.encode(this.supportsPersistentLicense, 8, 1);
            arg5.encode(this.supportedVideoCodecs, 16, 0, -1);
        }
    }

    class KeySystemSupportIsKeySystemSupportedResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final IsKeySystemSupportedResponse mCallback;

        KeySystemSupportIsKeySystemSupportedResponseParamsForwardToCallback(IsKeySystemSupportedResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                KeySystemSupportIsKeySystemSupportedResponseParams v5_1 = KeySystemSupportIsKeySystemSupportedResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Boolean.valueOf(v5_1.isSupported), v5_1.supportedVideoCodecs, Boolean.valueOf(v5_1.supportsPersistentLicense));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class KeySystemSupportIsKeySystemSupportedResponseParamsProxyToResponder implements IsKeySystemSupportedResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        KeySystemSupportIsKeySystemSupportedResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, int[] arg6, Boolean arg7) {
            KeySystemSupportIsKeySystemSupportedResponseParams v0 = new KeySystemSupportIsKeySystemSupportedResponseParams();
            v0.isSupported = arg5.booleanValue();
            v0.supportedVideoCodecs = arg6;
            v0.supportsPersistentLicense = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Boolean)arg1), ((int[])arg2), ((Boolean)arg3));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.KeySystemSupport$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void isKeySystemSupported(String arg8, IsKeySystemSupportedResponse arg9) {
            KeySystemSupportIsKeySystemSupportedParams v0 = new KeySystemSupportIsKeySystemSupportedParams();
            v0.keySystem = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new KeySystemSupportIsKeySystemSupportedResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, KeySystemSupport arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                if(v1.getType() != -2) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(KeySystemSupport_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg9, MessageReceiver arg10) {
            try {
                ServiceMessage v9_1 = arg9.asServiceMessage();
                MessageHeader v1 = v9_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_20;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), KeySystemSupport_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().isKeySystemSupported(KeySystemSupportIsKeySystemSupportedParams.deserialize(v9_1.getPayload()).keySystem, new KeySystemSupportIsKeySystemSupportedResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int IS_KEY_SYSTEM_SUPPORTED_ORDINAL;
    public static final Manager MANAGER;

    static {
        KeySystemSupport_Internal.MANAGER = new org.chromium.media.mojom.KeySystemSupport_Internal$1();
    }

    KeySystemSupport_Internal() {
        super();
    }
}

