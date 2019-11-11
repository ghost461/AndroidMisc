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

class AndroidOverlayClient_Internal {
    final class org.chromium.media.mojom.AndroidOverlayClient_Internal$1 extends Manager {
        org.chromium.media.mojom.AndroidOverlayClient_Internal$1() {
            super();
        }

        public AndroidOverlayClient[] buildArray(int arg1) {
            return new AndroidOverlayClient[arg1];
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

        public Stub buildStub(Core arg2, AndroidOverlayClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((AndroidOverlayClient)arg2));
        }

        public String getName() {
            return "media::mojom::AndroidOverlayClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class AndroidOverlayClientOnDestroyedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            AndroidOverlayClientOnDestroyedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            AndroidOverlayClientOnDestroyedParams.DEFAULT_STRUCT_INFO = AndroidOverlayClientOnDestroyedParams.VERSION_ARRAY[0];
        }

        public AndroidOverlayClientOnDestroyedParams() {
            this(0);
        }

        private AndroidOverlayClientOnDestroyedParams(int arg2) {
            super(8, arg2);
        }

        public static AndroidOverlayClientOnDestroyedParams decode(Decoder arg2) {
            AndroidOverlayClientOnDestroyedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new AndroidOverlayClientOnDestroyedParams(arg2.readAndValidateDataHeader(AndroidOverlayClientOnDestroyedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static AndroidOverlayClientOnDestroyedParams deserialize(ByteBuffer arg2) {
            return AndroidOverlayClientOnDestroyedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AndroidOverlayClientOnDestroyedParams deserialize(Message arg1) {
            return AndroidOverlayClientOnDestroyedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(AndroidOverlayClientOnDestroyedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class AndroidOverlayClientOnPowerEfficientStateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isPowerEfficient;

        static {
            AndroidOverlayClientOnPowerEfficientStateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AndroidOverlayClientOnPowerEfficientStateParams.DEFAULT_STRUCT_INFO = AndroidOverlayClientOnPowerEfficientStateParams.VERSION_ARRAY[0];
        }

        public AndroidOverlayClientOnPowerEfficientStateParams() {
            this(0);
        }

        private AndroidOverlayClientOnPowerEfficientStateParams(int arg2) {
            super(16, arg2);
        }

        public static AndroidOverlayClientOnPowerEfficientStateParams decode(Decoder arg3) {
            AndroidOverlayClientOnPowerEfficientStateParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new AndroidOverlayClientOnPowerEfficientStateParams(arg3.readAndValidateDataHeader(AndroidOverlayClientOnPowerEfficientStateParams.VERSION_ARRAY).elementsOrVersion);
                v1.isPowerEfficient = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static AndroidOverlayClientOnPowerEfficientStateParams deserialize(ByteBuffer arg2) {
            return AndroidOverlayClientOnPowerEfficientStateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AndroidOverlayClientOnPowerEfficientStateParams deserialize(Message arg1) {
            return AndroidOverlayClientOnPowerEfficientStateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AndroidOverlayClientOnPowerEfficientStateParams.DEFAULT_STRUCT_INFO).encode(this.isPowerEfficient, 8, 0);
        }
    }

    final class AndroidOverlayClientOnSurfaceReadyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public long surfaceKey;

        static {
            AndroidOverlayClientOnSurfaceReadyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            AndroidOverlayClientOnSurfaceReadyParams.DEFAULT_STRUCT_INFO = AndroidOverlayClientOnSurfaceReadyParams.VERSION_ARRAY[0];
        }

        public AndroidOverlayClientOnSurfaceReadyParams() {
            this(0);
        }

        private AndroidOverlayClientOnSurfaceReadyParams(int arg2) {
            super(16, arg2);
        }

        public static AndroidOverlayClientOnSurfaceReadyParams decode(Decoder arg4) {
            AndroidOverlayClientOnSurfaceReadyParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new AndroidOverlayClientOnSurfaceReadyParams(arg4.readAndValidateDataHeader(AndroidOverlayClientOnSurfaceReadyParams.VERSION_ARRAY).elementsOrVersion);
                v1.surfaceKey = arg4.readLong(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static AndroidOverlayClientOnSurfaceReadyParams deserialize(ByteBuffer arg2) {
            return AndroidOverlayClientOnSurfaceReadyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static AndroidOverlayClientOnSurfaceReadyParams deserialize(Message arg1) {
            return AndroidOverlayClientOnSurfaceReadyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(AndroidOverlayClientOnSurfaceReadyParams.DEFAULT_STRUCT_INFO).encode(this.surfaceKey, 8);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.AndroidOverlayClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onDestroyed() {
            this.getProxyHandler().getMessageReceiver().accept(new AndroidOverlayClientOnDestroyedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onPowerEfficientState(boolean arg5) {
            AndroidOverlayClientOnPowerEfficientStateParams v0 = new AndroidOverlayClientOnPowerEfficientStateParams();
            v0.isPowerEfficient = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onSurfaceReady(long arg4) {
            AndroidOverlayClientOnSurfaceReadyParams v0 = new AndroidOverlayClientOnSurfaceReadyParams();
            v0.surfaceKey = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, AndroidOverlayClient arg2) {
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
                    goto label_29;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_23;
                    }
                    case 1: {
                        goto label_18;
                    }
                    case 2: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                AndroidOverlayClientOnDestroyedParams.deserialize(v6_1.getPayload());
                this.getImpl().onDestroyed();
                return 1;
            label_23:
                this.getImpl().onSurfaceReady(AndroidOverlayClientOnSurfaceReadyParams.deserialize(v6_1.getPayload()).surfaceKey);
                return 1;
            label_12:
                this.getImpl().onPowerEfficientState(AndroidOverlayClientOnPowerEfficientStateParams.deserialize(v6_1.getPayload()).isPowerEfficient);
                return 1;
            label_29:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(AndroidOverlayClient_Internal.MANAGER, v6_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), AndroidOverlayClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_DESTROYED_ORDINAL = 1;
    private static final int ON_POWER_EFFICIENT_STATE_ORDINAL = 2;
    private static final int ON_SURFACE_READY_ORDINAL;

    static {
        AndroidOverlayClient_Internal.MANAGER = new org.chromium.media.mojom.AndroidOverlayClient_Internal$1();
    }

    AndroidOverlayClient_Internal() {
        super();
    }
}

