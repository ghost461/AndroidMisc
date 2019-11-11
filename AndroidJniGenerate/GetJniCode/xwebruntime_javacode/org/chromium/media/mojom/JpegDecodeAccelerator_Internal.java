package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.gfx.mojom.Size;
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
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

class JpegDecodeAccelerator_Internal {
    final class org.chromium.media.mojom.JpegDecodeAccelerator_Internal$1 extends Manager {
        org.chromium.media.mojom.JpegDecodeAccelerator_Internal$1() {
            super();
        }

        public JpegDecodeAccelerator[] buildArray(int arg1) {
            return new JpegDecodeAccelerator[arg1];
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

        public Stub buildStub(Core arg2, JpegDecodeAccelerator arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((JpegDecodeAccelerator)arg2));
        }

        public String getName() {
            return "media::mojom::JpegDecodeAccelerator";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class JpegDecodeAcceleratorDecodeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public Size codedSize;
        public BitstreamBuffer inputBuffer;
        public int outputBufferSize;
        public SharedBufferHandle outputHandle;

        static {
            JpegDecodeAcceleratorDecodeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            JpegDecodeAcceleratorDecodeParams.DEFAULT_STRUCT_INFO = JpegDecodeAcceleratorDecodeParams.VERSION_ARRAY[0];
        }

        public JpegDecodeAcceleratorDecodeParams() {
            this(0);
        }

        private JpegDecodeAcceleratorDecodeParams(int arg2) {
            super(0x20, arg2);
            this.outputHandle = InvalidHandle.INSTANCE;
        }

        public static JpegDecodeAcceleratorDecodeParams decode(Decoder arg3) {
            JpegDecodeAcceleratorDecodeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new JpegDecodeAcceleratorDecodeParams(arg3.readAndValidateDataHeader(JpegDecodeAcceleratorDecodeParams.VERSION_ARRAY).elementsOrVersion);
                v1.inputBuffer = BitstreamBuffer.decode(arg3.readPointer(8, false));
                v1.codedSize = Size.decode(arg3.readPointer(16, false));
                v1.outputHandle = arg3.readSharedBufferHandle(24, false);
                v1.outputBufferSize = arg3.readInt(28);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static JpegDecodeAcceleratorDecodeParams deserialize(ByteBuffer arg2) {
            return JpegDecodeAcceleratorDecodeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegDecodeAcceleratorDecodeParams deserialize(Message arg1) {
            return JpegDecodeAcceleratorDecodeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(JpegDecodeAcceleratorDecodeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.inputBuffer, 8, false);
            arg4.encode(this.codedSize, 16, false);
            arg4.encode(this.outputHandle, 24, false);
            arg4.encode(this.outputBufferSize, 28);
        }
    }

    final class JpegDecodeAcceleratorDecodeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int bitstreamBufferId;
        public int error;

        static {
            JpegDecodeAcceleratorDecodeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            JpegDecodeAcceleratorDecodeResponseParams.DEFAULT_STRUCT_INFO = JpegDecodeAcceleratorDecodeResponseParams.VERSION_ARRAY[0];
        }

        public JpegDecodeAcceleratorDecodeResponseParams() {
            this(0);
        }

        private JpegDecodeAcceleratorDecodeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static JpegDecodeAcceleratorDecodeResponseParams decode(Decoder arg2) {
            JpegDecodeAcceleratorDecodeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new JpegDecodeAcceleratorDecodeResponseParams(arg2.readAndValidateDataHeader(JpegDecodeAcceleratorDecodeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.bitstreamBufferId = arg2.readInt(8);
                v1.error = arg2.readInt(12);
                DecodeError.validate(v1.error);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static JpegDecodeAcceleratorDecodeResponseParams deserialize(ByteBuffer arg2) {
            return JpegDecodeAcceleratorDecodeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegDecodeAcceleratorDecodeResponseParams deserialize(Message arg1) {
            return JpegDecodeAcceleratorDecodeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(JpegDecodeAcceleratorDecodeResponseParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.bitstreamBufferId, 8);
            arg3.encode(this.error, 12);
        }
    }

    class JpegDecodeAcceleratorDecodeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DecodeResponse mCallback;

        JpegDecodeAcceleratorDecodeResponseParamsForwardToCallback(DecodeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                JpegDecodeAcceleratorDecodeResponseParams v5_1 = JpegDecodeAcceleratorDecodeResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.bitstreamBufferId), Integer.valueOf(v5_1.error));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class JpegDecodeAcceleratorDecodeResponseParamsProxyToResponder implements DecodeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        JpegDecodeAcceleratorDecodeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, Integer arg7) {
            JpegDecodeAcceleratorDecodeResponseParams v0 = new JpegDecodeAcceleratorDecodeResponseParams();
            v0.bitstreamBufferId = arg6.intValue();
            v0.error = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((Integer)arg2));
        }
    }

    final class JpegDecodeAcceleratorDecodeWithFdParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public int bufferId;
        public int codedSizeHeight;
        public int codedSizeWidth;
        public int inputBufferSize;
        public UntypedHandle inputFd;
        public int outputBufferSize;
        public UntypedHandle outputFd;

        static {
            JpegDecodeAcceleratorDecodeWithFdParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            JpegDecodeAcceleratorDecodeWithFdParams.DEFAULT_STRUCT_INFO = JpegDecodeAcceleratorDecodeWithFdParams.VERSION_ARRAY[0];
        }

        public JpegDecodeAcceleratorDecodeWithFdParams() {
            this(0);
        }

        private JpegDecodeAcceleratorDecodeWithFdParams(int arg2) {
            super(40, arg2);
            this.inputFd = InvalidHandle.INSTANCE;
            this.outputFd = InvalidHandle.INSTANCE;
        }

        public static JpegDecodeAcceleratorDecodeWithFdParams decode(Decoder arg3) {
            JpegDecodeAcceleratorDecodeWithFdParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new JpegDecodeAcceleratorDecodeWithFdParams(arg3.readAndValidateDataHeader(JpegDecodeAcceleratorDecodeWithFdParams.VERSION_ARRAY).elementsOrVersion);
                v1.bufferId = arg3.readInt(8);
                v1.inputFd = arg3.readUntypedHandle(12, false);
                v1.inputBufferSize = arg3.readInt(16);
                v1.codedSizeWidth = arg3.readInt(20);
                v1.codedSizeHeight = arg3.readInt(24);
                v1.outputFd = arg3.readUntypedHandle(28, false);
                v1.outputBufferSize = arg3.readInt(0x20);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static JpegDecodeAcceleratorDecodeWithFdParams deserialize(ByteBuffer arg2) {
            return JpegDecodeAcceleratorDecodeWithFdParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegDecodeAcceleratorDecodeWithFdParams deserialize(Message arg1) {
            return JpegDecodeAcceleratorDecodeWithFdParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(JpegDecodeAcceleratorDecodeWithFdParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.bufferId, 8);
            arg4.encode(this.inputFd, 12, false);
            arg4.encode(this.inputBufferSize, 16);
            arg4.encode(this.codedSizeWidth, 20);
            arg4.encode(this.codedSizeHeight, 24);
            arg4.encode(this.outputFd, 28, false);
            arg4.encode(this.outputBufferSize, 0x20);
        }
    }

    final class JpegDecodeAcceleratorDecodeWithFdResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int bufferId;
        public int error;

        static {
            JpegDecodeAcceleratorDecodeWithFdResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            JpegDecodeAcceleratorDecodeWithFdResponseParams.DEFAULT_STRUCT_INFO = JpegDecodeAcceleratorDecodeWithFdResponseParams.VERSION_ARRAY[0];
        }

        public JpegDecodeAcceleratorDecodeWithFdResponseParams() {
            this(0);
        }

        private JpegDecodeAcceleratorDecodeWithFdResponseParams(int arg2) {
            super(16, arg2);
        }

        public static JpegDecodeAcceleratorDecodeWithFdResponseParams decode(Decoder arg2) {
            JpegDecodeAcceleratorDecodeWithFdResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new JpegDecodeAcceleratorDecodeWithFdResponseParams(arg2.readAndValidateDataHeader(JpegDecodeAcceleratorDecodeWithFdResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.bufferId = arg2.readInt(8);
                v1.error = arg2.readInt(12);
                DecodeError.validate(v1.error);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static JpegDecodeAcceleratorDecodeWithFdResponseParams deserialize(ByteBuffer arg2) {
            return JpegDecodeAcceleratorDecodeWithFdResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegDecodeAcceleratorDecodeWithFdResponseParams deserialize(Message arg1) {
            return JpegDecodeAcceleratorDecodeWithFdResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(JpegDecodeAcceleratorDecodeWithFdResponseParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.bufferId, 8);
            arg3.encode(this.error, 12);
        }
    }

    class JpegDecodeAcceleratorDecodeWithFdResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DecodeWithFdResponse mCallback;

        JpegDecodeAcceleratorDecodeWithFdResponseParamsForwardToCallback(DecodeWithFdResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                JpegDecodeAcceleratorDecodeWithFdResponseParams v4_1 = JpegDecodeAcceleratorDecodeWithFdResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.bufferId), Integer.valueOf(v4_1.error));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class JpegDecodeAcceleratorDecodeWithFdResponseParamsProxyToResponder implements DecodeWithFdResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        JpegDecodeAcceleratorDecodeWithFdResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, Integer arg6) {
            JpegDecodeAcceleratorDecodeWithFdResponseParams v0 = new JpegDecodeAcceleratorDecodeWithFdResponseParams();
            v0.bufferId = arg5.intValue();
            v0.error = arg6.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((Integer)arg2));
        }
    }

    final class JpegDecodeAcceleratorInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            JpegDecodeAcceleratorInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            JpegDecodeAcceleratorInitializeParams.DEFAULT_STRUCT_INFO = JpegDecodeAcceleratorInitializeParams.VERSION_ARRAY[0];
        }

        public JpegDecodeAcceleratorInitializeParams() {
            this(0);
        }

        private JpegDecodeAcceleratorInitializeParams(int arg2) {
            super(8, arg2);
        }

        public static JpegDecodeAcceleratorInitializeParams decode(Decoder arg2) {
            JpegDecodeAcceleratorInitializeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new JpegDecodeAcceleratorInitializeParams(arg2.readAndValidateDataHeader(JpegDecodeAcceleratorInitializeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static JpegDecodeAcceleratorInitializeParams deserialize(ByteBuffer arg2) {
            return JpegDecodeAcceleratorInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegDecodeAcceleratorInitializeParams deserialize(Message arg1) {
            return JpegDecodeAcceleratorInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(JpegDecodeAcceleratorInitializeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class JpegDecodeAcceleratorInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            JpegDecodeAcceleratorInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            JpegDecodeAcceleratorInitializeResponseParams.DEFAULT_STRUCT_INFO = JpegDecodeAcceleratorInitializeResponseParams.VERSION_ARRAY[0];
        }

        public JpegDecodeAcceleratorInitializeResponseParams() {
            this(0);
        }

        private JpegDecodeAcceleratorInitializeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static JpegDecodeAcceleratorInitializeResponseParams decode(Decoder arg3) {
            JpegDecodeAcceleratorInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new JpegDecodeAcceleratorInitializeResponseParams(arg3.readAndValidateDataHeader(JpegDecodeAcceleratorInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static JpegDecodeAcceleratorInitializeResponseParams deserialize(ByteBuffer arg2) {
            return JpegDecodeAcceleratorInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegDecodeAcceleratorInitializeResponseParams deserialize(Message arg1) {
            return JpegDecodeAcceleratorInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(JpegDecodeAcceleratorInitializeResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class JpegDecodeAcceleratorInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        JpegDecodeAcceleratorInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(JpegDecodeAcceleratorInitializeResponseParams.deserialize(v4.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class JpegDecodeAcceleratorInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        JpegDecodeAcceleratorInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            JpegDecodeAcceleratorInitializeResponseParams v0 = new JpegDecodeAcceleratorInitializeResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class JpegDecodeAcceleratorUninitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            JpegDecodeAcceleratorUninitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            JpegDecodeAcceleratorUninitializeParams.DEFAULT_STRUCT_INFO = JpegDecodeAcceleratorUninitializeParams.VERSION_ARRAY[0];
        }

        public JpegDecodeAcceleratorUninitializeParams() {
            this(0);
        }

        private JpegDecodeAcceleratorUninitializeParams(int arg2) {
            super(8, arg2);
        }

        public static JpegDecodeAcceleratorUninitializeParams decode(Decoder arg2) {
            JpegDecodeAcceleratorUninitializeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new JpegDecodeAcceleratorUninitializeParams(arg2.readAndValidateDataHeader(JpegDecodeAcceleratorUninitializeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static JpegDecodeAcceleratorUninitializeParams deserialize(ByteBuffer arg2) {
            return JpegDecodeAcceleratorUninitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static JpegDecodeAcceleratorUninitializeParams deserialize(Message arg1) {
            return JpegDecodeAcceleratorUninitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(JpegDecodeAcceleratorUninitializeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.JpegDecodeAccelerator$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void decode(BitstreamBuffer arg4, Size arg5, SharedBufferHandle arg6, int arg7, DecodeResponse arg8) {
            JpegDecodeAcceleratorDecodeParams v0 = new JpegDecodeAcceleratorDecodeParams();
            v0.inputBuffer = arg4;
            v0.codedSize = arg5;
            v0.outputHandle = arg6;
            v0.outputBufferSize = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new JpegDecodeAcceleratorDecodeResponseParamsForwardToCallback(arg8));
        }

        public void decodeWithFd(int arg2, UntypedHandle arg3, int arg4, int arg5, int arg6, UntypedHandle arg7, int arg8, DecodeWithFdResponse arg9) {
            JpegDecodeAcceleratorDecodeWithFdParams v0 = new JpegDecodeAcceleratorDecodeWithFdParams();
            v0.bufferId = arg2;
            v0.inputFd = arg3;
            v0.inputBufferSize = arg4;
            v0.codedSizeWidth = arg5;
            v0.codedSizeHeight = arg6;
            v0.outputFd = arg7;
            v0.outputBufferSize = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new JpegDecodeAcceleratorDecodeWithFdResponseParamsForwardToCallback(arg9));
        }

        public void initialize(InitializeResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new JpegDecodeAcceleratorInitializeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new JpegDecodeAcceleratorInitializeResponseParamsForwardToCallback(arg9));
        }

        public void uninitialize() {
            this.getProxyHandler().getMessageReceiver().accept(new JpegDecodeAcceleratorUninitializeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, JpegDecodeAccelerator arg2) {
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
                    if(v1_1 != 3) {
                        return 0;
                    }

                    JpegDecodeAcceleratorUninitializeParams.deserialize(v4_1.getPayload());
                    this.getImpl().uninitialize();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(JpegDecodeAccelerator_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg18, MessageReceiver arg19) {
            MessageReceiver v1 = arg19;
            try {
                ServiceMessage v3 = arg18.asServiceMessage();
                MessageHeader v4 = v3.getHeader();
                if(!v4.validateHeader(1)) {
                    return 0;
                }

                switch(v4.getType()) {
                    case -1: {
                        goto label_54;
                    }
                    case 0: {
                        goto label_44;
                    }
                    case 1: {
                        goto label_29;
                    }
                    case 2: {
                        goto label_11;
                    }
                }

                return 0;
            label_54:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), JpegDecodeAccelerator_Internal.MANAGER, v3, v1);
            label_11:
                JpegDecodeAcceleratorDecodeWithFdParams v3_1 = JpegDecodeAcceleratorDecodeWithFdParams.deserialize(v3.getPayload());
                this.getImpl().decodeWithFd(v3_1.bufferId, v3_1.inputFd, v3_1.inputBufferSize, v3_1.codedSizeWidth, v3_1.codedSizeHeight, v3_1.outputFd, v3_1.outputBufferSize, new JpegDecodeAcceleratorDecodeWithFdResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_44:
                JpegDecodeAcceleratorInitializeParams.deserialize(v3.getPayload());
                this.getImpl().initialize(new JpegDecodeAcceleratorInitializeResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_29:
                JpegDecodeAcceleratorDecodeParams v3_2 = JpegDecodeAcceleratorDecodeParams.deserialize(v3.getPayload());
                this.getImpl().decode(v3_2.inputBuffer, v3_2.codedSize, v3_2.outputHandle, v3_2.outputBufferSize, new JpegDecodeAcceleratorDecodeResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            }
            catch(DeserializationException v0) {
                System.err.println(v0.toString());
                return 0;
            }
        }
    }

    private static final int DECODE_ORDINAL = 1;
    private static final int DECODE_WITH_FD_ORDINAL = 2;
    private static final int INITIALIZE_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int UNINITIALIZE_ORDINAL = 3;

    static {
        JpegDecodeAccelerator_Internal.MANAGER = new org.chromium.media.mojom.JpegDecodeAccelerator_Internal$1();
    }

    JpegDecodeAccelerator_Internal() {
        super();
    }
}

