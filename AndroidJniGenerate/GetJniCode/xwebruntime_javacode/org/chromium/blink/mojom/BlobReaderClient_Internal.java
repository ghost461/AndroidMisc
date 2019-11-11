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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class BlobReaderClient_Internal {
    final class org.chromium.blink.mojom.BlobReaderClient_Internal$1 extends Manager {
        org.chromium.blink.mojom.BlobReaderClient_Internal$1() {
            super();
        }

        public BlobReaderClient[] buildArray(int arg1) {
            return new BlobReaderClient[arg1];
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

        public Stub buildStub(Core arg2, BlobReaderClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((BlobReaderClient)arg2));
        }

        public String getName() {
            return "blink::mojom::BlobReaderClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BlobReaderClientOnCalculatedSizeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public long expectedContentSize;
        public long totalSize;

        static {
            BlobReaderClientOnCalculatedSizeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobReaderClientOnCalculatedSizeParams.DEFAULT_STRUCT_INFO = BlobReaderClientOnCalculatedSizeParams.VERSION_ARRAY[0];
        }

        public BlobReaderClientOnCalculatedSizeParams() {
            this(0);
        }

        private BlobReaderClientOnCalculatedSizeParams(int arg2) {
            super(24, arg2);
        }

        public static BlobReaderClientOnCalculatedSizeParams decode(Decoder arg4) {
            BlobReaderClientOnCalculatedSizeParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BlobReaderClientOnCalculatedSizeParams(arg4.readAndValidateDataHeader(BlobReaderClientOnCalculatedSizeParams.VERSION_ARRAY).elementsOrVersion);
                v1.totalSize = arg4.readLong(8);
                v1.expectedContentSize = arg4.readLong(16);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BlobReaderClientOnCalculatedSizeParams deserialize(ByteBuffer arg2) {
            return BlobReaderClientOnCalculatedSizeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobReaderClientOnCalculatedSizeParams deserialize(Message arg1) {
            return BlobReaderClientOnCalculatedSizeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(BlobReaderClientOnCalculatedSizeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.totalSize, 8);
            arg4.encode(this.expectedContentSize, 16);
        }
    }

    final class BlobReaderClientOnCompleteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public long dataLength;
        public int status;

        static {
            BlobReaderClientOnCompleteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            BlobReaderClientOnCompleteParams.DEFAULT_STRUCT_INFO = BlobReaderClientOnCompleteParams.VERSION_ARRAY[0];
        }

        public BlobReaderClientOnCompleteParams() {
            this(0);
        }

        private BlobReaderClientOnCompleteParams(int arg2) {
            super(24, arg2);
        }

        public static BlobReaderClientOnCompleteParams decode(Decoder arg4) {
            BlobReaderClientOnCompleteParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new BlobReaderClientOnCompleteParams(arg4.readAndValidateDataHeader(BlobReaderClientOnCompleteParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg4.readInt(8);
                v1.dataLength = arg4.readLong(16);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static BlobReaderClientOnCompleteParams deserialize(ByteBuffer arg2) {
            return BlobReaderClientOnCompleteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BlobReaderClientOnCompleteParams deserialize(Message arg1) {
            return BlobReaderClientOnCompleteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(BlobReaderClientOnCompleteParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.dataLength, 16);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.BlobReaderClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onCalculatedSize(long arg2, long arg4) {
            BlobReaderClientOnCalculatedSizeParams v0 = new BlobReaderClientOnCalculatedSizeParams();
            v0.totalSize = arg2;
            v0.expectedContentSize = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onComplete(int arg3, long arg4) {
            BlobReaderClientOnCompleteParams v0 = new BlobReaderClientOnCompleteParams();
            v0.status = arg3;
            v0.dataLength = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, BlobReaderClient arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg8) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_26;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_19;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_19:
                BlobReaderClientOnCalculatedSizeParams v8_2 = BlobReaderClientOnCalculatedSizeParams.deserialize(v8_1.getPayload());
                this.getImpl().onCalculatedSize(v8_2.totalSize, v8_2.expectedContentSize);
                return 1;
            label_12:
                BlobReaderClientOnCompleteParams v8_3 = BlobReaderClientOnCompleteParams.deserialize(v8_1.getPayload());
                this.getImpl().onComplete(v8_3.status, v8_3.dataLength);
                return 1;
            label_26:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(BlobReaderClient_Internal.MANAGER, v8_1);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), BlobReaderClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_CALCULATED_SIZE_ORDINAL = 0;
    private static final int ON_COMPLETE_ORDINAL = 1;

    static {
        BlobReaderClient_Internal.MANAGER = new org.chromium.blink.mojom.BlobReaderClient_Internal$1();
    }

    BlobReaderClient_Internal() {
        super();
    }
}

