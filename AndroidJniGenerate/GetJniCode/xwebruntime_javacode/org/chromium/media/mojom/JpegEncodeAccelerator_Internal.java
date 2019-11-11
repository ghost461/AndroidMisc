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
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.UntypedHandle;

class JpegEncodeAccelerator_Internal {
    final class org.chromium.media.mojom.JpegEncodeAccelerator_Internal$1 extends Manager {
        org.chromium.media.mojom.JpegEncodeAccelerator_Internal$1() {
            super();
        }

        public JpegEncodeAccelerator[] buildArray(int arg1) {
            return new JpegEncodeAccelerator[arg1];
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

        public Stub buildStub(Core arg2, JpegEncodeAccelerator arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((JpegEncodeAccelerator)arg2));
        }

        public String getName() {
            return "media::mojom::JpegEncodeAccelerator";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class JpegEncodeAcceleratorEncodeWithFdParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x30;
        private static final DataHeader[] VERSION_ARRAY;
        public int bufferId;
        public int codedSizeHeight;
        public int codedSizeWidth;
        public int exifBufferSize;
        public UntypedHandle exifFd;
        public int inputBufferSize;
        public UntypedHandle inputFd;
        public int outputBufferSize;
        public UntypedHandle outputFd;

        static {
            JpegEncodeAcceleratorEncodeWithFdParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x30, 0)};
            JpegEncodeAcceleratorEncodeWithFdParams.DEFAULT_STRUCT_INFO = JpegEncodeAcceleratorEncodeWithFdParams.VERSION_ARRAY[0];
        }

        public JpegEncodeAcceleratorEncodeWithFdParams() {
            this(0);
        }

        private JpegEncodeAcceleratorEncodeWithFdParams(int arg2) {
            super(0x30, arg2);
            this.inputFd = InvalidHandle.INSTANCE;
            this.exifFd = InvalidHandle.INSTANCE;
            this.outputFd = InvalidHandle.INSTANCE;
        }

        public static JpegEncodeAcceleratorEncodeWithFdParams decode(Decoder arg3) {
            JpegEncodeAcceleratorEncodeWithFdParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new JpegEncodeAcceleratorEncodeWithFdParams(arg3.readAndValidateDataHeader(JpegEncodeAcceleratorEncodeWithFdParams.VERSION_ARRAY).elementsOrVersion);
                v1.bufferId = arg3.readInt(8);
                v1.inputFd = arg3.readUntypedHandle(12, false);
                v1.inputBufferSize = arg3.readInt(16);
                v1.codedSizeWidth = arg3.readInt(20);
                v1.codedSizeHeight = arg3.readInt(24);
                v1.exifFd = arg3.readUntypedHandle(28, false);
                v1.exifBufferSize = arg3.readInt(0x20);
                v1.outputFd = arg3.readUntypedHandle(36, false);
                v1.outputBufferSize = arg3.readInt(40);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static JpegEncodeAcceleratorEncodeWithFdParams deserialize(ByteBuffer arg2) {
            return JpegEncodeAcceleratorEncodeWithFdParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegEncodeAcceleratorEncodeWithFdParams deserialize(Message arg1) {
            return JpegEncodeAcceleratorEncodeWithFdParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(JpegEncodeAcceleratorEncodeWithFdParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.bufferId, 8);
            arg4.encode(this.inputFd, 12, false);
            arg4.encode(this.inputBufferSize, 16);
            arg4.encode(this.codedSizeWidth, 20);
            arg4.encode(this.codedSizeHeight, 24);
            arg4.encode(this.exifFd, 28, false);
            arg4.encode(this.exifBufferSize, 0x20);
            arg4.encode(this.outputFd, 36, false);
            arg4.encode(this.outputBufferSize, 40);
        }
    }

    final class JpegEncodeAcceleratorEncodeWithFdResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int bufferId;
        public int encodedBufferSize;
        public int status;

        static {
            JpegEncodeAcceleratorEncodeWithFdResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            JpegEncodeAcceleratorEncodeWithFdResponseParams.DEFAULT_STRUCT_INFO = JpegEncodeAcceleratorEncodeWithFdResponseParams.VERSION_ARRAY[0];
        }

        public JpegEncodeAcceleratorEncodeWithFdResponseParams() {
            this(0);
        }

        private JpegEncodeAcceleratorEncodeWithFdResponseParams(int arg2) {
            super(24, arg2);
        }

        public static JpegEncodeAcceleratorEncodeWithFdResponseParams decode(Decoder arg2) {
            JpegEncodeAcceleratorEncodeWithFdResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new JpegEncodeAcceleratorEncodeWithFdResponseParams(arg2.readAndValidateDataHeader(JpegEncodeAcceleratorEncodeWithFdResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.bufferId = arg2.readInt(8);
                v1.encodedBufferSize = arg2.readInt(12);
                v1.status = arg2.readInt(16);
                EncodeStatus.validate(v1.status);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static JpegEncodeAcceleratorEncodeWithFdResponseParams deserialize(ByteBuffer arg2) {
            return JpegEncodeAcceleratorEncodeWithFdResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegEncodeAcceleratorEncodeWithFdResponseParams deserialize(Message arg1) {
            return JpegEncodeAcceleratorEncodeWithFdResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(JpegEncodeAcceleratorEncodeWithFdResponseParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.bufferId, 8);
            arg3.encode(this.encodedBufferSize, 12);
            arg3.encode(this.status, 16);
        }
    }

    class JpegEncodeAcceleratorEncodeWithFdResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final EncodeWithFdResponse mCallback;

        JpegEncodeAcceleratorEncodeWithFdResponseParamsForwardToCallback(EncodeWithFdResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                JpegEncodeAcceleratorEncodeWithFdResponseParams v6_1 = JpegEncodeAcceleratorEncodeWithFdResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(Integer.valueOf(v6_1.bufferId), Integer.valueOf(v6_1.encodedBufferSize), Integer.valueOf(v6_1.status));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class JpegEncodeAcceleratorEncodeWithFdResponseParamsProxyToResponder implements EncodeWithFdResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        JpegEncodeAcceleratorEncodeWithFdResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, Integer arg6, Integer arg7) {
            JpegEncodeAcceleratorEncodeWithFdResponseParams v0 = new JpegEncodeAcceleratorEncodeWithFdResponseParams();
            v0.bufferId = arg5.intValue();
            v0.encodedBufferSize = arg6.intValue();
            v0.status = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((Integer)arg2), ((Integer)arg3));
        }
    }

    final class JpegEncodeAcceleratorInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            JpegEncodeAcceleratorInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            JpegEncodeAcceleratorInitializeParams.DEFAULT_STRUCT_INFO = JpegEncodeAcceleratorInitializeParams.VERSION_ARRAY[0];
        }

        public JpegEncodeAcceleratorInitializeParams() {
            this(0);
        }

        private JpegEncodeAcceleratorInitializeParams(int arg2) {
            super(8, arg2);
        }

        public static JpegEncodeAcceleratorInitializeParams decode(Decoder arg2) {
            JpegEncodeAcceleratorInitializeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new JpegEncodeAcceleratorInitializeParams(arg2.readAndValidateDataHeader(JpegEncodeAcceleratorInitializeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static JpegEncodeAcceleratorInitializeParams deserialize(ByteBuffer arg2) {
            return JpegEncodeAcceleratorInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegEncodeAcceleratorInitializeParams deserialize(Message arg1) {
            return JpegEncodeAcceleratorInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(JpegEncodeAcceleratorInitializeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class JpegEncodeAcceleratorInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            JpegEncodeAcceleratorInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            JpegEncodeAcceleratorInitializeResponseParams.DEFAULT_STRUCT_INFO = JpegEncodeAcceleratorInitializeResponseParams.VERSION_ARRAY[0];
        }

        public JpegEncodeAcceleratorInitializeResponseParams() {
            this(0);
        }

        private JpegEncodeAcceleratorInitializeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static JpegEncodeAcceleratorInitializeResponseParams decode(Decoder arg3) {
            JpegEncodeAcceleratorInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new JpegEncodeAcceleratorInitializeResponseParams(arg3.readAndValidateDataHeader(JpegEncodeAcceleratorInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static JpegEncodeAcceleratorInitializeResponseParams deserialize(ByteBuffer arg2) {
            return JpegEncodeAcceleratorInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegEncodeAcceleratorInitializeResponseParams deserialize(Message arg1) {
            return JpegEncodeAcceleratorInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(JpegEncodeAcceleratorInitializeResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class JpegEncodeAcceleratorInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        JpegEncodeAcceleratorInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(JpegEncodeAcceleratorInitializeResponseParams.deserialize(v4.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class JpegEncodeAcceleratorInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        JpegEncodeAcceleratorInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            JpegEncodeAcceleratorInitializeResponseParams v0 = new JpegEncodeAcceleratorInitializeResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.JpegEncodeAccelerator$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void encodeWithFd(int arg2, UntypedHandle arg3, int arg4, int arg5, int arg6, UntypedHandle arg7, int arg8, UntypedHandle arg9, int arg10, EncodeWithFdResponse arg11) {
            JpegEncodeAcceleratorEncodeWithFdParams v0 = new JpegEncodeAcceleratorEncodeWithFdParams();
            v0.bufferId = arg2;
            v0.inputFd = arg3;
            v0.inputBufferSize = arg4;
            v0.codedSizeWidth = arg5;
            v0.codedSizeHeight = arg6;
            v0.exifFd = arg7;
            v0.exifBufferSize = arg8;
            v0.outputFd = arg9;
            v0.outputBufferSize = arg10;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new JpegEncodeAcceleratorEncodeWithFdResponseParamsForwardToCallback(arg11));
        }

        public void initialize(InitializeResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new JpegEncodeAcceleratorInitializeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new JpegEncodeAcceleratorInitializeResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, JpegEncodeAccelerator arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(JpegEncodeAccelerator_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg20, MessageReceiver arg21) {
            MessageReceiver v1 = arg21;
            try {
                ServiceMessage v3 = arg20.asServiceMessage();
                MessageHeader v4 = v3.getHeader();
                if(!v4.validateHeader(1)) {
                    return 0;
                }

                switch(v4.getType()) {
                    case -1: {
                        goto label_44;
                    }
                    case 0: {
                        goto label_34;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_34:
                JpegEncodeAcceleratorInitializeParams.deserialize(v3.getPayload());
                this.getImpl().initialize(new JpegEncodeAcceleratorInitializeResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_44:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), JpegEncodeAccelerator_Internal.MANAGER, v3, v1);
            label_12:
                JpegEncodeAcceleratorEncodeWithFdParams v3_1 = JpegEncodeAcceleratorEncodeWithFdParams.deserialize(v3.getPayload());
                this.getImpl().encodeWithFd(v3_1.bufferId, v3_1.inputFd, v3_1.inputBufferSize, v3_1.codedSizeWidth, v3_1.codedSizeHeight, v3_1.exifFd, v3_1.exifBufferSize, v3_1.outputFd, v3_1.outputBufferSize, new JpegEncodeAcceleratorEncodeWithFdResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            }
            catch(DeserializationException v0) {
                System.err.println(v0.toString());
                return 0;
            }
        }
    }

    private static final int ENCODE_WITH_FD_ORDINAL = 1;
    private static final int INITIALIZE_ORDINAL;
    public static final Manager MANAGER;

    static {
        JpegEncodeAccelerator_Internal.MANAGER = new org.chromium.media.mojom.JpegEncodeAccelerator_Internal$1();
    }

    JpegEncodeAccelerator_Internal() {
        super();
    }
}

