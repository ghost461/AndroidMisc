package org.chromium.webshare.mojom;

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
import org.chromium.url.mojom.Url;

class ShareService_Internal {
    final class org.chromium.webshare.mojom.ShareService_Internal$1 extends Manager {
        org.chromium.webshare.mojom.ShareService_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ShareService[] buildArray(int arg1) {
            return new ShareService[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.webshare.mojom.ShareService_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.webshare.mojom.ShareService_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ShareService)arg2));
        }

        public org.chromium.webshare.mojom.ShareService_Internal$Stub buildStub(Core arg2, ShareService arg3) {
            return new org.chromium.webshare.mojom.ShareService_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "blink::mojom::ShareService";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.webshare.mojom.ShareService_Internal$Proxy extends AbstractProxy implements org.chromium.webshare.mojom.ShareService$Proxy {
        org.chromium.webshare.mojom.ShareService_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void share(String arg6, String arg7, Url arg8, ShareResponse arg9) {
            ShareServiceShareParams v0 = new ShareServiceShareParams();
            v0.title = arg6;
            v0.text = arg7;
            v0.url = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ShareServiceShareResponseParamsForwardToCallback(arg9));
        }
    }

    final class ShareServiceShareParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public String text;
        public String title;
        public Url url;

        static {
            ShareServiceShareParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ShareServiceShareParams.DEFAULT_STRUCT_INFO = ShareServiceShareParams.VERSION_ARRAY[0];
        }

        public ShareServiceShareParams() {
            this(0);
        }

        private ShareServiceShareParams(int arg2) {
            super(0x20, arg2);
        }

        public static ShareServiceShareParams decode(Decoder arg3) {
            ShareServiceShareParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ShareServiceShareParams(arg3.readAndValidateDataHeader(ShareServiceShareParams.VERSION_ARRAY).elementsOrVersion);
                v1.title = arg3.readString(8, false);
                v1.text = arg3.readString(16, false);
                v1.url = Url.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ShareServiceShareParams deserialize(ByteBuffer arg2) {
            return ShareServiceShareParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ShareServiceShareParams deserialize(Message arg1) {
            return ShareServiceShareParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ShareServiceShareParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.title, 8, false);
            arg4.encode(this.text, 16, false);
            arg4.encode(this.url, 24, false);
        }
    }

    final class ShareServiceShareResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;

        static {
            ShareServiceShareResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ShareServiceShareResponseParams.DEFAULT_STRUCT_INFO = ShareServiceShareResponseParams.VERSION_ARRAY[0];
        }

        public ShareServiceShareResponseParams() {
            this(0);
        }

        private ShareServiceShareResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ShareServiceShareResponseParams decode(Decoder arg2) {
            ShareServiceShareResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ShareServiceShareResponseParams(arg2.readAndValidateDataHeader(ShareServiceShareResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg2.readInt(8);
                ShareError.validate(v1.error);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ShareServiceShareResponseParams deserialize(ByteBuffer arg2) {
            return ShareServiceShareResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ShareServiceShareResponseParams deserialize(Message arg1) {
            return ShareServiceShareResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ShareServiceShareResponseParams.DEFAULT_STRUCT_INFO).encode(this.error, 8);
        }
    }

    class ShareServiceShareResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ShareResponse mCallback;

        ShareServiceShareResponseParamsForwardToCallback(ShareResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(ShareServiceShareResponseParams.deserialize(v4.getPayload()).error));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ShareServiceShareResponseParamsProxyToResponder implements ShareResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ShareServiceShareResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            ShareServiceShareResponseParams v0 = new ShareServiceShareResponseParams();
            v0.error = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class org.chromium.webshare.mojom.ShareService_Internal$Stub extends Stub {
        org.chromium.webshare.mojom.ShareService_Internal$Stub(Core arg1, ShareService arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ShareService_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg11, MessageReceiver arg12) {
            try {
                ServiceMessage v11_1 = arg11.asServiceMessage();
                MessageHeader v1 = v11_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_22;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_22:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ShareService_Internal.MANAGER, v11_1, arg12);
            label_10:
                ShareServiceShareParams v11_2 = ShareServiceShareParams.deserialize(v11_1.getPayload());
                this.getImpl().share(v11_2.title, v11_2.text, v11_2.url, new ShareServiceShareResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int SHARE_ORDINAL;

    static {
        ShareService_Internal.MANAGER = new org.chromium.webshare.mojom.ShareService_Internal$1();
    }

    ShareService_Internal() {
        super();
    }
}

