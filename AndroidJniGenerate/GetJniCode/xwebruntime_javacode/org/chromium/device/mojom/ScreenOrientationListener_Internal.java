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
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class ScreenOrientationListener_Internal {
    final class org.chromium.device.mojom.ScreenOrientationListener_Internal$1 extends Manager {
        org.chromium.device.mojom.ScreenOrientationListener_Internal$1() {
            super();
        }

        public ScreenOrientationListener[] buildArray(int arg1) {
            return new ScreenOrientationListener[arg1];
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

        public Stub buildStub(Core arg2, ScreenOrientationListener arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ScreenOrientationListener)arg2));
        }

        public String getName() {
            return "device::mojom::ScreenOrientationListener";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.ScreenOrientationListener$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void isAutoRotateEnabledByUser(IsAutoRotateEnabledByUserResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ScreenOrientationListenerIsAutoRotateEnabledByUserParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParamsForwardToCallback(arg9));
        }

        public void start() {
            this.getProxyHandler().getMessageReceiver().accept(new ScreenOrientationListenerStartParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void stop() {
            this.getProxyHandler().getMessageReceiver().accept(new ScreenOrientationListenerStopParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class ScreenOrientationListenerIsAutoRotateEnabledByUserParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ScreenOrientationListenerIsAutoRotateEnabledByUserParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ScreenOrientationListenerIsAutoRotateEnabledByUserParams.DEFAULT_STRUCT_INFO = ScreenOrientationListenerIsAutoRotateEnabledByUserParams.VERSION_ARRAY[0];
        }

        public ScreenOrientationListenerIsAutoRotateEnabledByUserParams() {
            this(0);
        }

        private ScreenOrientationListenerIsAutoRotateEnabledByUserParams(int arg2) {
            super(8, arg2);
        }

        public static ScreenOrientationListenerIsAutoRotateEnabledByUserParams decode(Decoder arg2) {
            ScreenOrientationListenerIsAutoRotateEnabledByUserParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ScreenOrientationListenerIsAutoRotateEnabledByUserParams(arg2.readAndValidateDataHeader(ScreenOrientationListenerIsAutoRotateEnabledByUserParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ScreenOrientationListenerIsAutoRotateEnabledByUserParams deserialize(ByteBuffer arg2) {
            return ScreenOrientationListenerIsAutoRotateEnabledByUserParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ScreenOrientationListenerIsAutoRotateEnabledByUserParams deserialize(Message arg1) {
            return ScreenOrientationListenerIsAutoRotateEnabledByUserParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ScreenOrientationListenerIsAutoRotateEnabledByUserParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean enabled;

        static {
            ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.DEFAULT_STRUCT_INFO = ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.VERSION_ARRAY[0];
        }

        public ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams() {
            this(0);
        }

        private ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams decode(Decoder arg3) {
            ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams(arg3.readAndValidateDataHeader(ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.enabled = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams deserialize(ByteBuffer arg2) {
            return ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams deserialize(Message arg1) {
            return ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.DEFAULT_STRUCT_INFO).encode(this.enabled, 8, 0);
        }
    }

    class ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final IsAutoRotateEnabledByUserResponse mCallback;

        ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParamsForwardToCallback(IsAutoRotateEnabledByUserResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams.deserialize(v4.getPayload()).enabled));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParamsProxyToResponder implements IsAutoRotateEnabledByUserResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg6) {
            ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams v0 = new ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParams();
            v0.enabled = arg6.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class ScreenOrientationListenerStartParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ScreenOrientationListenerStartParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ScreenOrientationListenerStartParams.DEFAULT_STRUCT_INFO = ScreenOrientationListenerStartParams.VERSION_ARRAY[0];
        }

        public ScreenOrientationListenerStartParams() {
            this(0);
        }

        private ScreenOrientationListenerStartParams(int arg2) {
            super(8, arg2);
        }

        public static ScreenOrientationListenerStartParams decode(Decoder arg2) {
            ScreenOrientationListenerStartParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ScreenOrientationListenerStartParams(arg2.readAndValidateDataHeader(ScreenOrientationListenerStartParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ScreenOrientationListenerStartParams deserialize(ByteBuffer arg2) {
            return ScreenOrientationListenerStartParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ScreenOrientationListenerStartParams deserialize(Message arg1) {
            return ScreenOrientationListenerStartParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ScreenOrientationListenerStartParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ScreenOrientationListenerStopParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ScreenOrientationListenerStopParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ScreenOrientationListenerStopParams.DEFAULT_STRUCT_INFO = ScreenOrientationListenerStopParams.VERSION_ARRAY[0];
        }

        public ScreenOrientationListenerStopParams() {
            this(0);
        }

        private ScreenOrientationListenerStopParams(int arg2) {
            super(8, arg2);
        }

        public static ScreenOrientationListenerStopParams decode(Decoder arg2) {
            ScreenOrientationListenerStopParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ScreenOrientationListenerStopParams(arg2.readAndValidateDataHeader(ScreenOrientationListenerStopParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ScreenOrientationListenerStopParams deserialize(ByteBuffer arg2) {
            return ScreenOrientationListenerStopParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ScreenOrientationListenerStopParams deserialize(Message arg1) {
            return ScreenOrientationListenerStopParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ScreenOrientationListenerStopParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ScreenOrientationListener arg2) {
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
                    goto label_22;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_17;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                ScreenOrientationListenerStartParams.deserialize(v4_1.getPayload());
                this.getImpl().start();
                return 1;
            label_12:
                ScreenOrientationListenerStopParams.deserialize(v4_1.getPayload());
                this.getImpl().stop();
                return 1;
            label_22:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ScreenOrientationListener_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                int v3 = v1.getType();
                if(v3 != -1) {
                    if(v3 != 2) {
                        return 0;
                    }

                    ScreenOrientationListenerIsAutoRotateEnabledByUserParams.deserialize(v8_1.getPayload());
                    this.getImpl().isAutoRotateEnabledByUser(new ScreenOrientationListenerIsAutoRotateEnabledByUserResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ScreenOrientationListener_Internal.MANAGER, v8_1, arg9);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int IS_AUTO_ROTATE_ENABLED_BY_USER_ORDINAL = 2;
    public static final Manager MANAGER = null;
    private static final int START_ORDINAL = 0;
    private static final int STOP_ORDINAL = 1;

    static {
        ScreenOrientationListener_Internal.MANAGER = new org.chromium.device.mojom.ScreenOrientationListener_Internal$1();
    }

    ScreenOrientationListener_Internal() {
        super();
    }
}

