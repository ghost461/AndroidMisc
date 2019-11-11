package org.chromium.shape_detection.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface$Proxy;
import org.chromium.mojo.bindings.Interface$Stub;
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
import org.chromium.skia.mojom.Bitmap;

class BarcodeDetection_Internal {
    final class org.chromium.shape_detection.mojom.BarcodeDetection_Internal$1 extends Manager {
        org.chromium.shape_detection.mojom.BarcodeDetection_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public BarcodeDetection[] buildArray(int arg1) {
            return new BarcodeDetection[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((BarcodeDetection)arg2));
        }

        public org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Stub buildStub(Core arg2, BarcodeDetection arg3) {
            return new org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "shape_detection::mojom::BarcodeDetection";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class BarcodeDetectionDetectParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Bitmap bitmapData;

        static {
            BarcodeDetectionDetectParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BarcodeDetectionDetectParams.DEFAULT_STRUCT_INFO = BarcodeDetectionDetectParams.VERSION_ARRAY[0];
        }

        public BarcodeDetectionDetectParams() {
            this(0);
        }

        private BarcodeDetectionDetectParams(int arg2) {
            super(16, arg2);
        }

        public static BarcodeDetectionDetectParams decode(Decoder arg3) {
            BarcodeDetectionDetectParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new BarcodeDetectionDetectParams(arg3.readAndValidateDataHeader(BarcodeDetectionDetectParams.VERSION_ARRAY).elementsOrVersion);
                v1.bitmapData = Bitmap.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static BarcodeDetectionDetectParams deserialize(ByteBuffer arg2) {
            return BarcodeDetectionDetectParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BarcodeDetectionDetectParams deserialize(Message arg1) {
            return BarcodeDetectionDetectParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(BarcodeDetectionDetectParams.DEFAULT_STRUCT_INFO).encode(this.bitmapData, 8, false);
        }
    }

    final class BarcodeDetectionDetectResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public BarcodeDetectionResult[] results;

        static {
            BarcodeDetectionDetectResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            BarcodeDetectionDetectResponseParams.DEFAULT_STRUCT_INFO = BarcodeDetectionDetectResponseParams.VERSION_ARRAY[0];
        }

        public BarcodeDetectionDetectResponseParams() {
            this(0);
        }

        private BarcodeDetectionDetectResponseParams(int arg2) {
            super(16, arg2);
        }

        public static BarcodeDetectionDetectResponseParams decode(Decoder arg8) {
            BarcodeDetectionDetectResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new BarcodeDetectionDetectResponseParams(arg8.readAndValidateDataHeader(BarcodeDetectionDetectResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.results = new BarcodeDetectionResult[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.results[v5] = BarcodeDetectionResult.decode(v3.readPointer(v5 * 8 + v2, false));
                }
            }
            catch(Throwable v0) {
                goto label_31;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_31:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static BarcodeDetectionDetectResponseParams deserialize(ByteBuffer arg2) {
            return BarcodeDetectionDetectResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static BarcodeDetectionDetectResponseParams deserialize(Message arg1) {
            return BarcodeDetectionDetectResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(BarcodeDetectionDetectResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.results == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.results.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.results.length; ++v0) {
                    arg6.encode(this.results[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    class BarcodeDetectionDetectResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DetectResponse mCallback;

        BarcodeDetectionDetectResponseParamsForwardToCallback(DetectResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(BarcodeDetectionDetectResponseParams.deserialize(v4.getPayload()).results);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class BarcodeDetectionDetectResponseParamsProxyToResponder implements DetectResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        BarcodeDetectionDetectResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((BarcodeDetectionResult[])arg1));
        }

        public void call(BarcodeDetectionResult[] arg7) {
            BarcodeDetectionDetectResponseParams v0 = new BarcodeDetectionDetectResponseParams();
            v0.results = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Proxy extends AbstractProxy implements org.chromium.shape_detection.mojom.BarcodeDetection$Proxy {
        org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void detect(Bitmap arg8, DetectResponse arg9) {
            BarcodeDetectionDetectParams v0 = new BarcodeDetectionDetectParams();
            v0.bitmapData = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new BarcodeDetectionDetectResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Stub extends Stub {
        org.chromium.shape_detection.mojom.BarcodeDetection_Internal$Stub(Core arg1, BarcodeDetection arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(BarcodeDetection_Internal.MANAGER, v4_1);
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
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), BarcodeDetection_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().detect(BarcodeDetectionDetectParams.deserialize(v9_1.getPayload()).bitmapData, new BarcodeDetectionDetectResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int DETECT_ORDINAL;
    public static final Manager MANAGER;

    static {
        BarcodeDetection_Internal.MANAGER = new org.chromium.shape_detection.mojom.BarcodeDetection_Internal$1();
    }

    BarcodeDetection_Internal() {
        super();
    }
}

