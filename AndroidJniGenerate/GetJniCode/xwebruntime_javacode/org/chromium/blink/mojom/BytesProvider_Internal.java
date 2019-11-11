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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo_base.mojom.File;
import org.chromium.mojo_base.mojom.Time;

class BytesProvider_Internal {
    final class org.chromium.blink.mojom.BytesProvider_Internal$1 extends Manager {
        org.chromium.blink.mojom.BytesProvider_Internal$1() {
            super();
        }

        public BytesProvider[] buildArray(int arg1) {
            return new BytesProvider[arg1];
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

        public Stub buildStub(Core arg2, BytesProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((BytesProvider)arg2));
        }

        public String getName() {
            return "blink::mojom::BytesProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BytesProviderRequestAsFileParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public File file;
        public long fileOffset;
        public long sourceOffset;
        public long sourceSize;

        static {
            BytesProviderRequestAsFileParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            BytesProviderRequestAsFileParams.DEFAULT_STRUCT_INFO = BytesProviderRequestAsFileParams.VERSION_ARRAY[0];
        }

        public BytesProviderRequestAsFileParams() {
            this(0);
        }

        private BytesProviderRequestAsFileParams(int arg2) {
            super(40, arg2);
        }

        public static BytesProviderRequestAsFileParams decode(Decoder arg4) {
            BytesProviderRequestAsFileParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BytesProviderRequestAsFileParams(arg4.readAndValidateDataHeader(BytesProviderRequestAsFileParams.VERSION_ARRAY).elementsOrVersion);
                v1.sourceOffset = arg4.readLong(8);
                v1.sourceSize = arg4.readLong(16);
                v1.file = File.decode(arg4.readPointer(24, false));
                v1.fileOffset = arg4.readLong(0x20);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BytesProviderRequestAsFileParams deserialize(ByteBuffer arg2) {
            return BytesProviderRequestAsFileParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BytesProviderRequestAsFileParams deserialize(Message arg1) {
            return BytesProviderRequestAsFileParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(BytesProviderRequestAsFileParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.sourceOffset, 8);
            arg4.encode(this.sourceSize, 16);
            arg4.encode(this.file, 24, false);
            arg4.encode(this.fileOffset, 0x20);
        }
    }

    final class BytesProviderRequestAsFileResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Time timeFileModified;

        static {
            BytesProviderRequestAsFileResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BytesProviderRequestAsFileResponseParams.DEFAULT_STRUCT_INFO = BytesProviderRequestAsFileResponseParams.VERSION_ARRAY[0];
        }

        public BytesProviderRequestAsFileResponseParams() {
            this(0);
        }

        private BytesProviderRequestAsFileResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BytesProviderRequestAsFileResponseParams decode(Decoder arg3) {
            BytesProviderRequestAsFileResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BytesProviderRequestAsFileResponseParams(arg3.readAndValidateDataHeader(BytesProviderRequestAsFileResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.timeFileModified = Time.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BytesProviderRequestAsFileResponseParams deserialize(ByteBuffer arg2) {
            return BytesProviderRequestAsFileResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BytesProviderRequestAsFileResponseParams deserialize(Message arg1) {
            return BytesProviderRequestAsFileResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BytesProviderRequestAsFileResponseParams.DEFAULT_STRUCT_INFO).encode(this.timeFileModified, 8, true);
        }
    }

    class BytesProviderRequestAsFileResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RequestAsFileResponse mCallback;

        BytesProviderRequestAsFileResponseParamsForwardToCallback(RequestAsFileResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(BytesProviderRequestAsFileResponseParams.deserialize(v4.getPayload()).timeFileModified);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BytesProviderRequestAsFileResponseParamsProxyToResponder implements RequestAsFileResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BytesProviderRequestAsFileResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((Time)arg1));
        }

        public void call(Time arg6) {
            BytesProviderRequestAsFileResponseParams v0 = new BytesProviderRequestAsFileResponseParams();
            v0.timeFileModified = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class BytesProviderRequestAsReplyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            BytesProviderRequestAsReplyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            BytesProviderRequestAsReplyParams.DEFAULT_STRUCT_INFO = BytesProviderRequestAsReplyParams.VERSION_ARRAY[0];
        }

        public BytesProviderRequestAsReplyParams() {
            this(0);
        }

        private BytesProviderRequestAsReplyParams(int arg2) {
            super(8, arg2);
        }

        public static BytesProviderRequestAsReplyParams decode(Decoder arg2) {
            BytesProviderRequestAsReplyParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new BytesProviderRequestAsReplyParams(arg2.readAndValidateDataHeader(BytesProviderRequestAsReplyParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static BytesProviderRequestAsReplyParams deserialize(ByteBuffer arg2) {
            return BytesProviderRequestAsReplyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BytesProviderRequestAsReplyParams deserialize(Message arg1) {
            return BytesProviderRequestAsReplyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(BytesProviderRequestAsReplyParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class BytesProviderRequestAsReplyResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;

        static {
            BytesProviderRequestAsReplyResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BytesProviderRequestAsReplyResponseParams.DEFAULT_STRUCT_INFO = BytesProviderRequestAsReplyResponseParams.VERSION_ARRAY[0];
        }

        public BytesProviderRequestAsReplyResponseParams() {
            this(0);
        }

        private BytesProviderRequestAsReplyResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BytesProviderRequestAsReplyResponseParams decode(Decoder arg4) {
            BytesProviderRequestAsReplyResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BytesProviderRequestAsReplyResponseParams(arg4.readAndValidateDataHeader(BytesProviderRequestAsReplyResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.data = arg4.readBytes(8, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BytesProviderRequestAsReplyResponseParams deserialize(ByteBuffer arg2) {
            return BytesProviderRequestAsReplyResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BytesProviderRequestAsReplyResponseParams deserialize(Message arg1) {
            return BytesProviderRequestAsReplyResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(BytesProviderRequestAsReplyResponseParams.DEFAULT_STRUCT_INFO).encode(this.data, 8, 0, -1);
        }
    }

    class BytesProviderRequestAsReplyResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RequestAsReplyResponse mCallback;

        BytesProviderRequestAsReplyResponseParamsForwardToCallback(RequestAsReplyResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(BytesProviderRequestAsReplyResponseParams.deserialize(v4.getPayload()).data);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BytesProviderRequestAsReplyResponseParamsProxyToResponder implements RequestAsReplyResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BytesProviderRequestAsReplyResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((byte[])arg1));
        }

        public void call(byte[] arg7) {
            BytesProviderRequestAsReplyResponseParams v0 = new BytesProviderRequestAsReplyResponseParams();
            v0.data = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class BytesProviderRequestAsStreamParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ProducerHandle pipe;

        static {
            BytesProviderRequestAsStreamParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BytesProviderRequestAsStreamParams.DEFAULT_STRUCT_INFO = BytesProviderRequestAsStreamParams.VERSION_ARRAY[0];
        }

        public BytesProviderRequestAsStreamParams() {
            this(0);
        }

        private BytesProviderRequestAsStreamParams(int arg2) {
            super(16, arg2);
            this.pipe = InvalidHandle.INSTANCE;
        }

        public static BytesProviderRequestAsStreamParams decode(Decoder arg3) {
            BytesProviderRequestAsStreamParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BytesProviderRequestAsStreamParams(arg3.readAndValidateDataHeader(BytesProviderRequestAsStreamParams.VERSION_ARRAY).elementsOrVersion);
                v1.pipe = arg3.readProducerHandle(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BytesProviderRequestAsStreamParams deserialize(ByteBuffer arg2) {
            return BytesProviderRequestAsStreamParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BytesProviderRequestAsStreamParams deserialize(Message arg1) {
            return BytesProviderRequestAsStreamParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BytesProviderRequestAsStreamParams.DEFAULT_STRUCT_INFO).encode(this.pipe, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.BytesProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void requestAsFile(long arg2, long arg4, File arg6, long arg7, RequestAsFileResponse arg9) {
            BytesProviderRequestAsFileParams v0 = new BytesProviderRequestAsFileParams();
            v0.sourceOffset = arg2;
            v0.sourceSize = arg4;
            v0.file = arg6;
            v0.fileOffset = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new BytesProviderRequestAsFileResponseParamsForwardToCallback(arg9));
        }

        public void requestAsReply(RequestAsReplyResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new BytesProviderRequestAsReplyParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new BytesProviderRequestAsReplyResponseParamsForwardToCallback(arg9));
        }

        public void requestAsStream(ProducerHandle arg5) {
            BytesProviderRequestAsStreamParams v0 = new BytesProviderRequestAsStreamParams();
            v0.pipe = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, BytesProvider arg2) {
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

                    this.getImpl().requestAsStream(BytesProviderRequestAsStreamParams.deserialize(v4_1.getPayload()).pipe);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(BytesProvider_Internal.MANAGER, v4_1);
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

                int v6 = v4.getType();
                if(v6 == 2) {
                    goto label_26;
                }

                switch(v6) {
                    case -1: {
                        goto label_22;
                    }
                    case 0: {
                        goto label_13;
                    }
                }

                return 0;
            label_22:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), BytesProvider_Internal.MANAGER, v3, v1);
            label_13:
                BytesProviderRequestAsReplyParams.deserialize(v3.getPayload());
                this.getImpl().requestAsReply(new BytesProviderRequestAsReplyResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            label_26:
                BytesProviderRequestAsFileParams v3_1 = BytesProviderRequestAsFileParams.deserialize(v3.getPayload());
                this.getImpl().requestAsFile(v3_1.sourceOffset, v3_1.sourceSize, v3_1.file, v3_1.fileOffset, new BytesProviderRequestAsFileResponseParamsProxyToResponder(this.getCore(), v1, v4.getRequestId()));
                return 1;
            }
            catch(DeserializationException v0) {
                System.err.println(v0.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int REQUEST_AS_FILE_ORDINAL = 2;
    private static final int REQUEST_AS_REPLY_ORDINAL = 0;
    private static final int REQUEST_AS_STREAM_ORDINAL = 1;

    static {
        BytesProvider_Internal.MANAGER = new org.chromium.blink.mojom.BytesProvider_Internal$1();
    }

    BytesProvider_Internal() {
        super();
    }
}

