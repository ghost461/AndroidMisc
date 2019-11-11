package org.chromium.blink.mojom.document_metadata;

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

class CopylessPaste_Internal {
    final class org.chromium.blink.mojom.document_metadata.CopylessPaste_Internal$1 extends Manager {
        org.chromium.blink.mojom.document_metadata.CopylessPaste_Internal$1() {
            super();
        }

        public CopylessPaste[] buildArray(int arg1) {
            return new CopylessPaste[arg1];
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

        public Stub buildStub(Core arg2, CopylessPaste arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CopylessPaste)arg2));
        }

        public String getName() {
            return "blink::mojom::document_metadata::CopylessPaste";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CopylessPasteGetEntitiesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            CopylessPasteGetEntitiesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            CopylessPasteGetEntitiesParams.DEFAULT_STRUCT_INFO = CopylessPasteGetEntitiesParams.VERSION_ARRAY[0];
        }

        public CopylessPasteGetEntitiesParams() {
            this(0);
        }

        private CopylessPasteGetEntitiesParams(int arg2) {
            super(8, arg2);
        }

        public static CopylessPasteGetEntitiesParams decode(Decoder arg2) {
            CopylessPasteGetEntitiesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CopylessPasteGetEntitiesParams(arg2.readAndValidateDataHeader(CopylessPasteGetEntitiesParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CopylessPasteGetEntitiesParams deserialize(ByteBuffer arg2) {
            return CopylessPasteGetEntitiesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CopylessPasteGetEntitiesParams deserialize(Message arg1) {
            return CopylessPasteGetEntitiesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(CopylessPasteGetEntitiesParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class CopylessPasteGetEntitiesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public WebPage page;

        static {
            CopylessPasteGetEntitiesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CopylessPasteGetEntitiesResponseParams.DEFAULT_STRUCT_INFO = CopylessPasteGetEntitiesResponseParams.VERSION_ARRAY[0];
        }

        public CopylessPasteGetEntitiesResponseParams() {
            this(0);
        }

        private CopylessPasteGetEntitiesResponseParams(int arg2) {
            super(16, arg2);
        }

        public static CopylessPasteGetEntitiesResponseParams decode(Decoder arg3) {
            CopylessPasteGetEntitiesResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CopylessPasteGetEntitiesResponseParams(arg3.readAndValidateDataHeader(CopylessPasteGetEntitiesResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.page = WebPage.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CopylessPasteGetEntitiesResponseParams deserialize(ByteBuffer arg2) {
            return CopylessPasteGetEntitiesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CopylessPasteGetEntitiesResponseParams deserialize(Message arg1) {
            return CopylessPasteGetEntitiesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CopylessPasteGetEntitiesResponseParams.DEFAULT_STRUCT_INFO).encode(this.page, 8, true);
        }
    }

    class CopylessPasteGetEntitiesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetEntitiesResponse mCallback;

        CopylessPasteGetEntitiesResponseParamsForwardToCallback(GetEntitiesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(CopylessPasteGetEntitiesResponseParams.deserialize(v4.getPayload()).page);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CopylessPasteGetEntitiesResponseParamsProxyToResponder implements GetEntitiesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CopylessPasteGetEntitiesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((WebPage)arg1));
        }

        public void call(WebPage arg7) {
            CopylessPasteGetEntitiesResponseParams v0 = new CopylessPasteGetEntitiesResponseParams();
            v0.page = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.document_metadata.CopylessPaste$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getEntities(GetEntitiesResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new CopylessPasteGetEntitiesParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new CopylessPasteGetEntitiesResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, CopylessPaste arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CopylessPaste_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_19;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CopylessPaste_Internal.MANAGER, v8_1, arg9);
            label_10:
                CopylessPasteGetEntitiesParams.deserialize(v8_1.getPayload());
                this.getImpl().getEntities(new CopylessPasteGetEntitiesResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int GET_ENTITIES_ORDINAL;
    public static final Manager MANAGER;

    static {
        CopylessPaste_Internal.MANAGER = new org.chromium.blink.mojom.document_metadata.CopylessPaste_Internal$1();
    }

    CopylessPaste_Internal() {
        super();
    }
}

