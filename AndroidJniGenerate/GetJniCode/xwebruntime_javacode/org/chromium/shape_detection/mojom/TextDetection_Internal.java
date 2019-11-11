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

class TextDetection_Internal {
    final class org.chromium.shape_detection.mojom.TextDetection_Internal$1 extends Manager {
        org.chromium.shape_detection.mojom.TextDetection_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public TextDetection[] buildArray(int arg1) {
            return new TextDetection[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.shape_detection.mojom.TextDetection_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.shape_detection.mojom.TextDetection_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((TextDetection)arg2));
        }

        public org.chromium.shape_detection.mojom.TextDetection_Internal$Stub buildStub(Core arg2, TextDetection arg3) {
            return new org.chromium.shape_detection.mojom.TextDetection_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "shape_detection::mojom::TextDetection";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.shape_detection.mojom.TextDetection_Internal$Proxy extends AbstractProxy implements org.chromium.shape_detection.mojom.TextDetection$Proxy {
        org.chromium.shape_detection.mojom.TextDetection_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void detect(Bitmap arg8, DetectResponse arg9) {
            TextDetectionDetectParams v0 = new TextDetectionDetectParams();
            v0.bitmapData = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new TextDetectionDetectResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.shape_detection.mojom.TextDetection_Internal$Stub extends Stub {
        org.chromium.shape_detection.mojom.TextDetection_Internal$Stub(Core arg1, TextDetection arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(TextDetection_Internal.MANAGER, v4_1);
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
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), TextDetection_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().detect(TextDetectionDetectParams.deserialize(v9_1.getPayload()).bitmapData, new TextDetectionDetectResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    final class TextDetectionDetectParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Bitmap bitmapData;

        static {
            TextDetectionDetectParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TextDetectionDetectParams.DEFAULT_STRUCT_INFO = TextDetectionDetectParams.VERSION_ARRAY[0];
        }

        public TextDetectionDetectParams() {
            this(0);
        }

        private TextDetectionDetectParams(int arg2) {
            super(16, arg2);
        }

        public static TextDetectionDetectParams decode(Decoder arg3) {
            TextDetectionDetectParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new TextDetectionDetectParams(arg3.readAndValidateDataHeader(TextDetectionDetectParams.VERSION_ARRAY).elementsOrVersion);
                v1.bitmapData = Bitmap.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static TextDetectionDetectParams deserialize(ByteBuffer arg2) {
            return TextDetectionDetectParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextDetectionDetectParams deserialize(Message arg1) {
            return TextDetectionDetectParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(TextDetectionDetectParams.DEFAULT_STRUCT_INFO).encode(this.bitmapData, 8, false);
        }
    }

    final class TextDetectionDetectResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TextDetectionResult[] results;

        static {
            TextDetectionDetectResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            TextDetectionDetectResponseParams.DEFAULT_STRUCT_INFO = TextDetectionDetectResponseParams.VERSION_ARRAY[0];
        }

        public TextDetectionDetectResponseParams() {
            this(0);
        }

        private TextDetectionDetectResponseParams(int arg2) {
            super(16, arg2);
        }

        public static TextDetectionDetectResponseParams decode(Decoder arg8) {
            TextDetectionDetectResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new TextDetectionDetectResponseParams(arg8.readAndValidateDataHeader(TextDetectionDetectResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.results = new TextDetectionResult[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.results[v5] = TextDetectionResult.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static TextDetectionDetectResponseParams deserialize(ByteBuffer arg2) {
            return TextDetectionDetectResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static TextDetectionDetectResponseParams deserialize(Message arg1) {
            return TextDetectionDetectResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(TextDetectionDetectResponseParams.DEFAULT_STRUCT_INFO);
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

    class TextDetectionDetectResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DetectResponse mCallback;

        TextDetectionDetectResponseParamsForwardToCallback(DetectResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(TextDetectionDetectResponseParams.deserialize(v4.getPayload()).results);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class TextDetectionDetectResponseParamsProxyToResponder implements DetectResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        TextDetectionDetectResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((TextDetectionResult[])arg1));
        }

        public void call(TextDetectionResult[] arg7) {
            TextDetectionDetectResponseParams v0 = new TextDetectionDetectResponseParams();
            v0.results = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    private static final int DETECT_ORDINAL;
    public static final Manager MANAGER;

    static {
        TextDetection_Internal.MANAGER = new org.chromium.shape_detection.mojom.TextDetection_Internal$1();
    }

    TextDetection_Internal() {
        super();
    }
}

