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

class OutputProtection_Internal {
    final class org.chromium.media.mojom.OutputProtection_Internal$1 extends Manager {
        org.chromium.media.mojom.OutputProtection_Internal$1() {
            super();
        }

        public OutputProtection[] buildArray(int arg1) {
            return new OutputProtection[arg1];
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

        public Stub buildStub(Core arg2, OutputProtection arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((OutputProtection)arg2));
        }

        public String getName() {
            return "media::mojom::OutputProtection";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class OutputProtectionEnableProtectionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int desiredProtectionMask;

        static {
            OutputProtectionEnableProtectionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            OutputProtectionEnableProtectionParams.DEFAULT_STRUCT_INFO = OutputProtectionEnableProtectionParams.VERSION_ARRAY[0];
        }

        public OutputProtectionEnableProtectionParams() {
            this(0);
        }

        private OutputProtectionEnableProtectionParams(int arg2) {
            super(16, arg2);
        }

        public static OutputProtectionEnableProtectionParams decode(Decoder arg2) {
            OutputProtectionEnableProtectionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new OutputProtectionEnableProtectionParams(arg2.readAndValidateDataHeader(OutputProtectionEnableProtectionParams.VERSION_ARRAY).elementsOrVersion);
                v1.desiredProtectionMask = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static OutputProtectionEnableProtectionParams deserialize(ByteBuffer arg2) {
            return OutputProtectionEnableProtectionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static OutputProtectionEnableProtectionParams deserialize(Message arg1) {
            return OutputProtectionEnableProtectionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(OutputProtectionEnableProtectionParams.DEFAULT_STRUCT_INFO).encode(this.desiredProtectionMask, 8);
        }
    }

    final class OutputProtectionEnableProtectionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            OutputProtectionEnableProtectionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            OutputProtectionEnableProtectionResponseParams.DEFAULT_STRUCT_INFO = OutputProtectionEnableProtectionResponseParams.VERSION_ARRAY[0];
        }

        public OutputProtectionEnableProtectionResponseParams() {
            this(0);
        }

        private OutputProtectionEnableProtectionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static OutputProtectionEnableProtectionResponseParams decode(Decoder arg3) {
            OutputProtectionEnableProtectionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new OutputProtectionEnableProtectionResponseParams(arg3.readAndValidateDataHeader(OutputProtectionEnableProtectionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static OutputProtectionEnableProtectionResponseParams deserialize(ByteBuffer arg2) {
            return OutputProtectionEnableProtectionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static OutputProtectionEnableProtectionResponseParams deserialize(Message arg1) {
            return OutputProtectionEnableProtectionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(OutputProtectionEnableProtectionResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class OutputProtectionEnableProtectionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final EnableProtectionResponse mCallback;

        OutputProtectionEnableProtectionResponseParamsForwardToCallback(EnableProtectionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(OutputProtectionEnableProtectionResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class OutputProtectionEnableProtectionResponseParamsProxyToResponder implements EnableProtectionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        OutputProtectionEnableProtectionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            OutputProtectionEnableProtectionResponseParams v0 = new OutputProtectionEnableProtectionResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class OutputProtectionQueryStatusParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            OutputProtectionQueryStatusParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            OutputProtectionQueryStatusParams.DEFAULT_STRUCT_INFO = OutputProtectionQueryStatusParams.VERSION_ARRAY[0];
        }

        public OutputProtectionQueryStatusParams() {
            this(0);
        }

        private OutputProtectionQueryStatusParams(int arg2) {
            super(8, arg2);
        }

        public static OutputProtectionQueryStatusParams decode(Decoder arg2) {
            OutputProtectionQueryStatusParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new OutputProtectionQueryStatusParams(arg2.readAndValidateDataHeader(OutputProtectionQueryStatusParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static OutputProtectionQueryStatusParams deserialize(ByteBuffer arg2) {
            return OutputProtectionQueryStatusParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static OutputProtectionQueryStatusParams deserialize(Message arg1) {
            return OutputProtectionQueryStatusParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(OutputProtectionQueryStatusParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class OutputProtectionQueryStatusResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int linkMask;
        public int protectionMask;
        public boolean success;

        static {
            OutputProtectionQueryStatusResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            OutputProtectionQueryStatusResponseParams.DEFAULT_STRUCT_INFO = OutputProtectionQueryStatusResponseParams.VERSION_ARRAY[0];
        }

        public OutputProtectionQueryStatusResponseParams() {
            this(0);
        }

        private OutputProtectionQueryStatusResponseParams(int arg2) {
            super(24, arg2);
        }

        public static OutputProtectionQueryStatusResponseParams decode(Decoder arg3) {
            OutputProtectionQueryStatusResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new OutputProtectionQueryStatusResponseParams(arg3.readAndValidateDataHeader(OutputProtectionQueryStatusResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
                v1.linkMask = arg3.readInt(12);
                v1.protectionMask = arg3.readInt(16);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static OutputProtectionQueryStatusResponseParams deserialize(ByteBuffer arg2) {
            return OutputProtectionQueryStatusResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static OutputProtectionQueryStatusResponseParams deserialize(Message arg1) {
            return OutputProtectionQueryStatusResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(OutputProtectionQueryStatusResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.success, 8, 0);
            arg4.encode(this.linkMask, 12);
            arg4.encode(this.protectionMask, 16);
        }
    }

    class OutputProtectionQueryStatusResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final QueryStatusResponse mCallback;

        OutputProtectionQueryStatusResponseParamsForwardToCallback(QueryStatusResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                OutputProtectionQueryStatusResponseParams v5_1 = OutputProtectionQueryStatusResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Boolean.valueOf(v5_1.success), Integer.valueOf(v5_1.linkMask), Integer.valueOf(v5_1.protectionMask));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class OutputProtectionQueryStatusResponseParamsProxyToResponder implements QueryStatusResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        OutputProtectionQueryStatusResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, Integer arg6, Integer arg7) {
            OutputProtectionQueryStatusResponseParams v0 = new OutputProtectionQueryStatusResponseParams();
            v0.success = arg5.booleanValue();
            v0.linkMask = arg6.intValue();
            v0.protectionMask = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Boolean)arg1), ((Integer)arg2), ((Integer)arg3));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.OutputProtection$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void enableProtection(int arg7, EnableProtectionResponse arg8) {
            OutputProtectionEnableProtectionParams v0 = new OutputProtectionEnableProtectionParams();
            v0.desiredProtectionMask = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new OutputProtectionEnableProtectionResponseParamsForwardToCallback(arg8));
        }

        public void queryStatus(QueryStatusResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new OutputProtectionQueryStatusParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new OutputProtectionQueryStatusResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, OutputProtection arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(OutputProtection_Internal.MANAGER, v4_1);
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
                        goto label_29;
                    }
                    case 0: {
                        goto label_20;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                OutputProtectionQueryStatusParams.deserialize(v9_1.getPayload());
                this.getImpl().queryStatus(new OutputProtectionQueryStatusResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_10:
                this.getImpl().enableProtection(OutputProtectionEnableProtectionParams.deserialize(v9_1.getPayload()).desiredProtectionMask, new OutputProtectionEnableProtectionResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_29:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), OutputProtection_Internal.MANAGER, v9_1, arg10);
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int ENABLE_PROTECTION_ORDINAL = 1;
    public static final Manager MANAGER;
    private static final int QUERY_STATUS_ORDINAL;

    static {
        OutputProtection_Internal.MANAGER = new org.chromium.media.mojom.OutputProtection_Internal$1();
    }

    OutputProtection_Internal() {
        super();
    }
}

