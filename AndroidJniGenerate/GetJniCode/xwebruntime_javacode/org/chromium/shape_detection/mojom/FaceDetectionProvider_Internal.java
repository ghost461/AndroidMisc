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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class FaceDetectionProvider_Internal {
    final class org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$1 extends Manager {
        org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public FaceDetectionProvider[] buildArray(int arg1) {
            return new FaceDetectionProvider[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((FaceDetectionProvider)arg2));
        }

        public org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Stub buildStub(Core arg2, FaceDetectionProvider arg3) {
            return new org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "shape_detection::mojom::FaceDetectionProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class FaceDetectionProviderCreateFaceDetectionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public FaceDetectorOptions options;
        public InterfaceRequest request;

        static {
            FaceDetectionProviderCreateFaceDetectionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            FaceDetectionProviderCreateFaceDetectionParams.DEFAULT_STRUCT_INFO = FaceDetectionProviderCreateFaceDetectionParams.VERSION_ARRAY[0];
        }

        public FaceDetectionProviderCreateFaceDetectionParams() {
            this(0);
        }

        private FaceDetectionProviderCreateFaceDetectionParams(int arg2) {
            super(24, arg2);
        }

        public static FaceDetectionProviderCreateFaceDetectionParams decode(Decoder arg3) {
            FaceDetectionProviderCreateFaceDetectionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FaceDetectionProviderCreateFaceDetectionParams(arg3.readAndValidateDataHeader(FaceDetectionProviderCreateFaceDetectionParams.VERSION_ARRAY).elementsOrVersion);
                v1.request = arg3.readInterfaceRequest(8, false);
                v1.options = FaceDetectorOptions.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FaceDetectionProviderCreateFaceDetectionParams deserialize(ByteBuffer arg2) {
            return FaceDetectionProviderCreateFaceDetectionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FaceDetectionProviderCreateFaceDetectionParams deserialize(Message arg1) {
            return FaceDetectionProviderCreateFaceDetectionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(FaceDetectionProviderCreateFaceDetectionParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.request, 8, false);
            arg4.encode(this.options, 16, false);
        }
    }

    final class org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Proxy extends AbstractProxy implements org.chromium.shape_detection.mojom.FaceDetectionProvider$Proxy {
        org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createFaceDetection(InterfaceRequest arg4, FaceDetectorOptions arg5) {
            FaceDetectionProviderCreateFaceDetectionParams v0 = new FaceDetectionProviderCreateFaceDetectionParams();
            v0.request = arg4;
            v0.options = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Stub extends Stub {
        org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$Stub(Core arg1, FaceDetectionProvider arg2) {
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
                    if(v1_1 != 0) {
                        return 0;
                    }

                    FaceDetectionProviderCreateFaceDetectionParams v4_2 = FaceDetectionProviderCreateFaceDetectionParams.deserialize(v4_1.getPayload());
                    this.getImpl().createFaceDetection(v4_2.request, v4_2.options);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(FaceDetectionProvider_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), FaceDetectionProvider_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_FACE_DETECTION_ORDINAL;
    public static final Manager MANAGER;

    static {
        FaceDetectionProvider_Internal.MANAGER = new org.chromium.shape_detection.mojom.FaceDetectionProvider_Internal$1();
    }

    FaceDetectionProvider_Internal() {
        super();
    }
}

