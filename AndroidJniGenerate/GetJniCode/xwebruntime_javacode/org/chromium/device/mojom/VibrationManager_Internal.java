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

class VibrationManager_Internal {
    final class org.chromium.device.mojom.VibrationManager_Internal$1 extends Manager {
        org.chromium.device.mojom.VibrationManager_Internal$1() {
            super();
        }

        public VibrationManager[] buildArray(int arg1) {
            return new VibrationManager[arg1];
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

        public Stub buildStub(Core arg2, VibrationManager arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((VibrationManager)arg2));
        }

        public String getName() {
            return "device::mojom::VibrationManager";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.VibrationManager$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void cancel(CancelResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new VibrationManagerCancelParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new VibrationManagerCancelResponseParamsForwardToCallback(arg8));
        }

        public void vibrate(long arg7, VibrateResponse arg9) {
            VibrationManagerVibrateParams v0 = new VibrationManagerVibrateParams();
            v0.milliseconds = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new VibrationManagerVibrateResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, VibrationManager arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(VibrationManager_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg10, MessageReceiver arg11) {
            try {
                ServiceMessage v10_1 = arg10.asServiceMessage();
                MessageHeader v1 = v10_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_29;
                    }
                    case 0: {
                        goto label_19;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                this.getImpl().vibrate(VibrationManagerVibrateParams.deserialize(v10_1.getPayload()).milliseconds, new VibrationManagerVibrateResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_10:
                VibrationManagerCancelParams.deserialize(v10_1.getPayload());
                this.getImpl().cancel(new VibrationManagerCancelResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_29:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), VibrationManager_Internal.MANAGER, v10_1, arg11);
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    final class VibrationManagerCancelParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            VibrationManagerCancelParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            VibrationManagerCancelParams.DEFAULT_STRUCT_INFO = VibrationManagerCancelParams.VERSION_ARRAY[0];
        }

        public VibrationManagerCancelParams() {
            this(0);
        }

        private VibrationManagerCancelParams(int arg2) {
            super(8, arg2);
        }

        public static VibrationManagerCancelParams decode(Decoder arg2) {
            VibrationManagerCancelParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VibrationManagerCancelParams(arg2.readAndValidateDataHeader(VibrationManagerCancelParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VibrationManagerCancelParams deserialize(ByteBuffer arg2) {
            return VibrationManagerCancelParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VibrationManagerCancelParams deserialize(Message arg1) {
            return VibrationManagerCancelParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(VibrationManagerCancelParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class VibrationManagerCancelResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            VibrationManagerCancelResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            VibrationManagerCancelResponseParams.DEFAULT_STRUCT_INFO = VibrationManagerCancelResponseParams.VERSION_ARRAY[0];
        }

        public VibrationManagerCancelResponseParams() {
            this(0);
        }

        private VibrationManagerCancelResponseParams(int arg2) {
            super(8, arg2);
        }

        public static VibrationManagerCancelResponseParams decode(Decoder arg2) {
            VibrationManagerCancelResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VibrationManagerCancelResponseParams(arg2.readAndValidateDataHeader(VibrationManagerCancelResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VibrationManagerCancelResponseParams deserialize(ByteBuffer arg2) {
            return VibrationManagerCancelResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VibrationManagerCancelResponseParams deserialize(Message arg1) {
            return VibrationManagerCancelResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(VibrationManagerCancelResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class VibrationManagerCancelResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CancelResponse mCallback;

        VibrationManagerCancelResponseParamsForwardToCallback(CancelResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class VibrationManagerCancelResponseParamsProxyToResponder implements CancelResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VibrationManagerCancelResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new VibrationManagerCancelResponseParams().serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class VibrationManagerVibrateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public long milliseconds;

        static {
            VibrationManagerVibrateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            VibrationManagerVibrateParams.DEFAULT_STRUCT_INFO = VibrationManagerVibrateParams.VERSION_ARRAY[0];
        }

        public VibrationManagerVibrateParams() {
            this(0);
        }

        private VibrationManagerVibrateParams(int arg2) {
            super(16, arg2);
        }

        public static VibrationManagerVibrateParams decode(Decoder arg4) {
            VibrationManagerVibrateParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new VibrationManagerVibrateParams(arg4.readAndValidateDataHeader(VibrationManagerVibrateParams.VERSION_ARRAY).elementsOrVersion);
                v1.milliseconds = arg4.readLong(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static VibrationManagerVibrateParams deserialize(ByteBuffer arg2) {
            return VibrationManagerVibrateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VibrationManagerVibrateParams deserialize(Message arg1) {
            return VibrationManagerVibrateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(VibrationManagerVibrateParams.DEFAULT_STRUCT_INFO).encode(this.milliseconds, 8);
        }
    }

    final class VibrationManagerVibrateResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            VibrationManagerVibrateResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            VibrationManagerVibrateResponseParams.DEFAULT_STRUCT_INFO = VibrationManagerVibrateResponseParams.VERSION_ARRAY[0];
        }

        public VibrationManagerVibrateResponseParams() {
            this(0);
        }

        private VibrationManagerVibrateResponseParams(int arg2) {
            super(8, arg2);
        }

        public static VibrationManagerVibrateResponseParams decode(Decoder arg2) {
            VibrationManagerVibrateResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new VibrationManagerVibrateResponseParams(arg2.readAndValidateDataHeader(VibrationManagerVibrateResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static VibrationManagerVibrateResponseParams deserialize(ByteBuffer arg2) {
            return VibrationManagerVibrateResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static VibrationManagerVibrateResponseParams deserialize(Message arg1) {
            return VibrationManagerVibrateResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(VibrationManagerVibrateResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class VibrationManagerVibrateResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final VibrateResponse mCallback;

        VibrationManagerVibrateResponseParamsForwardToCallback(VibrateResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg3) {
            try {
                if(!arg3.asServiceMessage().getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class VibrationManagerVibrateResponseParamsProxyToResponder implements VibrateResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        VibrationManagerVibrateResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new VibrationManagerVibrateResponseParams().serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    private static final int CANCEL_ORDINAL = 1;
    public static final Manager MANAGER;
    private static final int VIBRATE_ORDINAL;

    static {
        VibrationManager_Internal.MANAGER = new org.chromium.device.mojom.VibrationManager_Internal$1();
    }

    VibrationManager_Internal() {
        super();
    }
}

