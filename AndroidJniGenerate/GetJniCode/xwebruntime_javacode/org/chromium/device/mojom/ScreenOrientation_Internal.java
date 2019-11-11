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

class ScreenOrientation_Internal {
    final class org.chromium.device.mojom.ScreenOrientation_Internal$1 extends Manager {
        org.chromium.device.mojom.ScreenOrientation_Internal$1() {
            super();
        }

        public ScreenOrientation[] buildArray(int arg1) {
            return new ScreenOrientation[arg1];
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

        public Stub buildStub(Core arg2, ScreenOrientation arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ScreenOrientation)arg2));
        }

        public String getName() {
            return "device::mojom::ScreenOrientation";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.ScreenOrientation$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void lockOrientation(int arg8, LockOrientationResponse arg9) {
            ScreenOrientationLockOrientationParams v0 = new ScreenOrientationLockOrientationParams();
            v0.orientation = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ScreenOrientationLockOrientationResponseParamsForwardToCallback(arg9));
        }

        public void unlockOrientation() {
            this.getProxyHandler().getMessageReceiver().accept(new ScreenOrientationUnlockOrientationParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class ScreenOrientationLockOrientationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int orientation;

        static {
            ScreenOrientationLockOrientationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ScreenOrientationLockOrientationParams.DEFAULT_STRUCT_INFO = ScreenOrientationLockOrientationParams.VERSION_ARRAY[0];
        }

        public ScreenOrientationLockOrientationParams() {
            this(0);
        }

        private ScreenOrientationLockOrientationParams(int arg2) {
            super(16, arg2);
        }

        public static ScreenOrientationLockOrientationParams decode(Decoder arg2) {
            ScreenOrientationLockOrientationParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ScreenOrientationLockOrientationParams(arg2.readAndValidateDataHeader(ScreenOrientationLockOrientationParams.VERSION_ARRAY).elementsOrVersion);
                v1.orientation = arg2.readInt(8);
                ScreenOrientationLockType.validate(v1.orientation);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ScreenOrientationLockOrientationParams deserialize(ByteBuffer arg2) {
            return ScreenOrientationLockOrientationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ScreenOrientationLockOrientationParams deserialize(Message arg1) {
            return ScreenOrientationLockOrientationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ScreenOrientationLockOrientationParams.DEFAULT_STRUCT_INFO).encode(this.orientation, 8);
        }
    }

    final class ScreenOrientationLockOrientationResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;

        static {
            ScreenOrientationLockOrientationResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ScreenOrientationLockOrientationResponseParams.DEFAULT_STRUCT_INFO = ScreenOrientationLockOrientationResponseParams.VERSION_ARRAY[0];
        }

        public ScreenOrientationLockOrientationResponseParams() {
            this(0);
        }

        private ScreenOrientationLockOrientationResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ScreenOrientationLockOrientationResponseParams decode(Decoder arg2) {
            ScreenOrientationLockOrientationResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ScreenOrientationLockOrientationResponseParams(arg2.readAndValidateDataHeader(ScreenOrientationLockOrientationResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg2.readInt(8);
                ScreenOrientationLockResult.validate(v1.result);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ScreenOrientationLockOrientationResponseParams deserialize(ByteBuffer arg2) {
            return ScreenOrientationLockOrientationResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ScreenOrientationLockOrientationResponseParams deserialize(Message arg1) {
            return ScreenOrientationLockOrientationResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ScreenOrientationLockOrientationResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    class ScreenOrientationLockOrientationResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final LockOrientationResponse mCallback;

        ScreenOrientationLockOrientationResponseParamsForwardToCallback(LockOrientationResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(ScreenOrientationLockOrientationResponseParams.deserialize(v4.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ScreenOrientationLockOrientationResponseParamsProxyToResponder implements LockOrientationResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ScreenOrientationLockOrientationResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            ScreenOrientationLockOrientationResponseParams v0 = new ScreenOrientationLockOrientationResponseParams();
            v0.result = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class ScreenOrientationUnlockOrientationParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ScreenOrientationUnlockOrientationParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ScreenOrientationUnlockOrientationParams.DEFAULT_STRUCT_INFO = ScreenOrientationUnlockOrientationParams.VERSION_ARRAY[0];
        }

        public ScreenOrientationUnlockOrientationParams() {
            this(0);
        }

        private ScreenOrientationUnlockOrientationParams(int arg2) {
            super(8, arg2);
        }

        public static ScreenOrientationUnlockOrientationParams decode(Decoder arg2) {
            ScreenOrientationUnlockOrientationParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ScreenOrientationUnlockOrientationParams(arg2.readAndValidateDataHeader(ScreenOrientationUnlockOrientationParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ScreenOrientationUnlockOrientationParams deserialize(ByteBuffer arg2) {
            return ScreenOrientationUnlockOrientationParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ScreenOrientationUnlockOrientationParams deserialize(Message arg1) {
            return ScreenOrientationUnlockOrientationParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ScreenOrientationUnlockOrientationParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ScreenOrientation arg2) {
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
                    if(v1_1 != 1) {
                        return 0;
                    }

                    ScreenOrientationUnlockOrientationParams.deserialize(v4_1.getPayload());
                    this.getImpl().unlockOrientation();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ScreenOrientation_Internal.MANAGER, v4_1);
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
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ScreenOrientation_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().lockOrientation(ScreenOrientationLockOrientationParams.deserialize(v9_1.getPayload()).orientation, new ScreenOrientationLockOrientationResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int LOCK_ORIENTATION_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int UNLOCK_ORIENTATION_ORDINAL = 1;

    static {
        ScreenOrientation_Internal.MANAGER = new org.chromium.device.mojom.ScreenOrientation_Internal$1();
    }

    ScreenOrientation_Internal() {
        super();
    }
}

