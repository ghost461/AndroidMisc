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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class WakeLock_Internal {
    final class org.chromium.device.mojom.WakeLock_Internal$1 extends Manager {
        org.chromium.device.mojom.WakeLock_Internal$1() {
            super();
        }

        public WakeLock[] buildArray(int arg1) {
            return new WakeLock[arg1];
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

        public Stub buildStub(Core arg2, WakeLock arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((WakeLock)arg2));
        }

        public String getName() {
            return "device::mojom::WakeLock";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.WakeLock$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addClient(InterfaceRequest arg5) {
            WakeLockAddClientParams v0 = new WakeLockAddClientParams();
            v0.wakeLock = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void cancelWakeLock() {
            this.getProxyHandler().getMessageReceiver().accept(new WakeLockCancelWakeLockParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void changeType(int arg8, ChangeTypeResponse arg9) {
            WakeLockChangeTypeParams v0 = new WakeLockChangeTypeParams();
            v0.type = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new WakeLockChangeTypeResponseParamsForwardToCallback(arg9));
        }

        public void hasWakeLockForTests(HasWakeLockForTestsResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new WakeLockHasWakeLockForTestsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new WakeLockHasWakeLockForTestsResponseParamsForwardToCallback(arg9));
        }

        public void requestWakeLock() {
            this.getProxyHandler().getMessageReceiver().accept(new WakeLockRequestWakeLockParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, WakeLock arg2) {
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
                    goto label_28;
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
                WakeLockCancelWakeLockParams.deserialize(v4_1.getPayload());
                this.getImpl().cancelWakeLock();
                return 1;
            label_23:
                WakeLockRequestWakeLockParams.deserialize(v4_1.getPayload());
                this.getImpl().requestWakeLock();
                return 1;
            label_12:
                this.getImpl().addClient(WakeLockAddClientParams.deserialize(v4_1.getPayload()).wakeLock);
                return 1;
            label_28:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(WakeLock_Internal.MANAGER, v4_1);
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

                int v3 = v1.getType();
                if(v3 == -1) {
                    goto label_31;
                }

                switch(v3) {
                    case 3: {
                        goto label_21;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_21:
                this.getImpl().changeType(WakeLockChangeTypeParams.deserialize(v9_1.getPayload()).type, new WakeLockChangeTypeResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_12:
                WakeLockHasWakeLockForTestsParams.deserialize(v9_1.getPayload());
                this.getImpl().hasWakeLockForTests(new WakeLockHasWakeLockForTestsResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_31:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), WakeLock_Internal.MANAGER, v9_1, arg10);
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    final class WakeLockAddClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest wakeLock;

        static {
            WakeLockAddClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WakeLockAddClientParams.DEFAULT_STRUCT_INFO = WakeLockAddClientParams.VERSION_ARRAY[0];
        }

        public WakeLockAddClientParams() {
            this(0);
        }

        private WakeLockAddClientParams(int arg2) {
            super(16, arg2);
        }

        public static WakeLockAddClientParams decode(Decoder arg3) {
            WakeLockAddClientParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WakeLockAddClientParams(arg3.readAndValidateDataHeader(WakeLockAddClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.wakeLock = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WakeLockAddClientParams deserialize(ByteBuffer arg2) {
            return WakeLockAddClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockAddClientParams deserialize(Message arg1) {
            return WakeLockAddClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WakeLockAddClientParams.DEFAULT_STRUCT_INFO).encode(this.wakeLock, 8, false);
        }
    }

    final class WakeLockCancelWakeLockParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            WakeLockCancelWakeLockParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            WakeLockCancelWakeLockParams.DEFAULT_STRUCT_INFO = WakeLockCancelWakeLockParams.VERSION_ARRAY[0];
        }

        public WakeLockCancelWakeLockParams() {
            this(0);
        }

        private WakeLockCancelWakeLockParams(int arg2) {
            super(8, arg2);
        }

        public static WakeLockCancelWakeLockParams decode(Decoder arg2) {
            WakeLockCancelWakeLockParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WakeLockCancelWakeLockParams(arg2.readAndValidateDataHeader(WakeLockCancelWakeLockParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static WakeLockCancelWakeLockParams deserialize(ByteBuffer arg2) {
            return WakeLockCancelWakeLockParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockCancelWakeLockParams deserialize(Message arg1) {
            return WakeLockCancelWakeLockParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(WakeLockCancelWakeLockParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class WakeLockChangeTypeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int type;

        static {
            WakeLockChangeTypeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WakeLockChangeTypeParams.DEFAULT_STRUCT_INFO = WakeLockChangeTypeParams.VERSION_ARRAY[0];
        }

        public WakeLockChangeTypeParams() {
            this(0);
        }

        private WakeLockChangeTypeParams(int arg2) {
            super(16, arg2);
        }

        public static WakeLockChangeTypeParams decode(Decoder arg2) {
            WakeLockChangeTypeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WakeLockChangeTypeParams(arg2.readAndValidateDataHeader(WakeLockChangeTypeParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg2.readInt(8);
                WakeLockType.validate(v1.type);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static WakeLockChangeTypeParams deserialize(ByteBuffer arg2) {
            return WakeLockChangeTypeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockChangeTypeParams deserialize(Message arg1) {
            return WakeLockChangeTypeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(WakeLockChangeTypeParams.DEFAULT_STRUCT_INFO).encode(this.type, 8);
        }
    }

    final class WakeLockChangeTypeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean result;

        static {
            WakeLockChangeTypeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WakeLockChangeTypeResponseParams.DEFAULT_STRUCT_INFO = WakeLockChangeTypeResponseParams.VERSION_ARRAY[0];
        }

        public WakeLockChangeTypeResponseParams() {
            this(0);
        }

        private WakeLockChangeTypeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static WakeLockChangeTypeResponseParams decode(Decoder arg3) {
            WakeLockChangeTypeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WakeLockChangeTypeResponseParams(arg3.readAndValidateDataHeader(WakeLockChangeTypeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WakeLockChangeTypeResponseParams deserialize(ByteBuffer arg2) {
            return WakeLockChangeTypeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockChangeTypeResponseParams deserialize(Message arg1) {
            return WakeLockChangeTypeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WakeLockChangeTypeResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, 0);
        }
    }

    class WakeLockChangeTypeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ChangeTypeResponse mCallback;

        WakeLockChangeTypeResponseParamsForwardToCallback(ChangeTypeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(WakeLockChangeTypeResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class WakeLockChangeTypeResponseParamsProxyToResponder implements ChangeTypeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        WakeLockChangeTypeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            WakeLockChangeTypeResponseParams v0 = new WakeLockChangeTypeResponseParams();
            v0.result = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class WakeLockHasWakeLockForTestsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            WakeLockHasWakeLockForTestsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            WakeLockHasWakeLockForTestsParams.DEFAULT_STRUCT_INFO = WakeLockHasWakeLockForTestsParams.VERSION_ARRAY[0];
        }

        public WakeLockHasWakeLockForTestsParams() {
            this(0);
        }

        private WakeLockHasWakeLockForTestsParams(int arg2) {
            super(8, arg2);
        }

        public static WakeLockHasWakeLockForTestsParams decode(Decoder arg2) {
            WakeLockHasWakeLockForTestsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WakeLockHasWakeLockForTestsParams(arg2.readAndValidateDataHeader(WakeLockHasWakeLockForTestsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static WakeLockHasWakeLockForTestsParams deserialize(ByteBuffer arg2) {
            return WakeLockHasWakeLockForTestsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockHasWakeLockForTestsParams deserialize(Message arg1) {
            return WakeLockHasWakeLockForTestsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(WakeLockHasWakeLockForTestsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class WakeLockHasWakeLockForTestsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean result;

        static {
            WakeLockHasWakeLockForTestsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            WakeLockHasWakeLockForTestsResponseParams.DEFAULT_STRUCT_INFO = WakeLockHasWakeLockForTestsResponseParams.VERSION_ARRAY[0];
        }

        public WakeLockHasWakeLockForTestsResponseParams() {
            this(0);
        }

        private WakeLockHasWakeLockForTestsResponseParams(int arg2) {
            super(16, arg2);
        }

        public static WakeLockHasWakeLockForTestsResponseParams decode(Decoder arg3) {
            WakeLockHasWakeLockForTestsResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new WakeLockHasWakeLockForTestsResponseParams(arg3.readAndValidateDataHeader(WakeLockHasWakeLockForTestsResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static WakeLockHasWakeLockForTestsResponseParams deserialize(ByteBuffer arg2) {
            return WakeLockHasWakeLockForTestsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockHasWakeLockForTestsResponseParams deserialize(Message arg1) {
            return WakeLockHasWakeLockForTestsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(WakeLockHasWakeLockForTestsResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, 0);
        }
    }

    class WakeLockHasWakeLockForTestsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final HasWakeLockForTestsResponse mCallback;

        WakeLockHasWakeLockForTestsResponseParamsForwardToCallback(HasWakeLockForTestsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(WakeLockHasWakeLockForTestsResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class WakeLockHasWakeLockForTestsResponseParamsProxyToResponder implements HasWakeLockForTestsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        WakeLockHasWakeLockForTestsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            WakeLockHasWakeLockForTestsResponseParams v0 = new WakeLockHasWakeLockForTestsResponseParams();
            v0.result = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class WakeLockRequestWakeLockParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            WakeLockRequestWakeLockParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            WakeLockRequestWakeLockParams.DEFAULT_STRUCT_INFO = WakeLockRequestWakeLockParams.VERSION_ARRAY[0];
        }

        public WakeLockRequestWakeLockParams() {
            this(0);
        }

        private WakeLockRequestWakeLockParams(int arg2) {
            super(8, arg2);
        }

        public static WakeLockRequestWakeLockParams decode(Decoder arg2) {
            WakeLockRequestWakeLockParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new WakeLockRequestWakeLockParams(arg2.readAndValidateDataHeader(WakeLockRequestWakeLockParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static WakeLockRequestWakeLockParams deserialize(ByteBuffer arg2) {
            return WakeLockRequestWakeLockParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static WakeLockRequestWakeLockParams deserialize(Message arg1) {
            return WakeLockRequestWakeLockParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(WakeLockRequestWakeLockParams.DEFAULT_STRUCT_INFO);
        }
    }

    private static final int ADD_CLIENT_ORDINAL = 2;
    private static final int CANCEL_WAKE_LOCK_ORDINAL = 1;
    private static final int CHANGE_TYPE_ORDINAL = 3;
    private static final int HAS_WAKE_LOCK_FOR_TESTS_ORDINAL = 4;
    public static final Manager MANAGER;
    private static final int REQUEST_WAKE_LOCK_ORDINAL;

    static {
        WakeLock_Internal.MANAGER = new org.chromium.device.mojom.WakeLock_Internal$1();
    }

    WakeLock_Internal() {
        super();
    }
}

