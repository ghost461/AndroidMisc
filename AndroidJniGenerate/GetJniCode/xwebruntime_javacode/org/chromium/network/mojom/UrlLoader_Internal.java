package org.chromium.network.mojom;

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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class UrlLoader_Internal {
    final class org.chromium.network.mojom.UrlLoader_Internal$1 extends Manager {
        org.chromium.network.mojom.UrlLoader_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public UrlLoader[] buildArray(int arg1) {
            return new UrlLoader[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.UrlLoader_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.UrlLoader_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((UrlLoader)arg2));
        }

        public org.chromium.network.mojom.UrlLoader_Internal$Stub buildStub(Core arg2, UrlLoader arg3) {
            return new org.chromium.network.mojom.UrlLoader_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::URLLoader";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.UrlLoader_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.UrlLoader$Proxy {
        org.chromium.network.mojom.UrlLoader_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void followRedirect() {
            this.getProxyHandler().getMessageReceiver().accept(new UrlLoaderFollowRedirectParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void pauseReadingBodyFromNet() {
            this.getProxyHandler().getMessageReceiver().accept(new UrlLoaderPauseReadingBodyFromNetParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void proceedWithResponse() {
            this.getProxyHandler().getMessageReceiver().accept(new UrlLoaderProceedWithResponseParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void resumeReadingBodyFromNet() {
            this.getProxyHandler().getMessageReceiver().accept(new UrlLoaderResumeReadingBodyFromNetParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void setPriority(int arg4, int arg5) {
            UrlLoaderSetPriorityParams v0 = new UrlLoaderSetPriorityParams();
            v0.priority = arg4;
            v0.intraPriorityValue = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }
    }

    final class org.chromium.network.mojom.UrlLoader_Internal$Stub extends Stub {
        org.chromium.network.mojom.UrlLoader_Internal$Stub(Core arg1, UrlLoader arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5_1 = arg5.asServiceMessage();
                MessageHeader v1 = v5_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_39;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_34;
                    }
                    case 1: {
                        goto label_29;
                    }
                    case 2: {
                        goto label_22;
                    }
                    case 3: {
                        goto label_17;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                UrlLoaderPauseReadingBodyFromNetParams.deserialize(v5_1.getPayload());
                this.getImpl().pauseReadingBodyFromNet();
                return 1;
            label_34:
                UrlLoaderFollowRedirectParams.deserialize(v5_1.getPayload());
                this.getImpl().followRedirect();
                return 1;
            label_22:
                UrlLoaderSetPriorityParams v5_2 = UrlLoaderSetPriorityParams.deserialize(v5_1.getPayload());
                this.getImpl().setPriority(v5_2.priority, v5_2.intraPriorityValue);
                return 1;
            label_12:
                UrlLoaderResumeReadingBodyFromNetParams.deserialize(v5_1.getPayload());
                this.getImpl().resumeReadingBodyFromNet();
                return 1;
            label_29:
                UrlLoaderProceedWithResponseParams.deserialize(v5_1.getPayload());
                this.getImpl().proceedWithResponse();
                return 1;
            label_39:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(UrlLoader_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), UrlLoader_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    final class UrlLoaderFollowRedirectParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            UrlLoaderFollowRedirectParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            UrlLoaderFollowRedirectParams.DEFAULT_STRUCT_INFO = UrlLoaderFollowRedirectParams.VERSION_ARRAY[0];
        }

        public UrlLoaderFollowRedirectParams() {
            this(0);
        }

        private UrlLoaderFollowRedirectParams(int arg2) {
            super(8, arg2);
        }

        public static UrlLoaderFollowRedirectParams decode(Decoder arg2) {
            UrlLoaderFollowRedirectParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UrlLoaderFollowRedirectParams(arg2.readAndValidateDataHeader(UrlLoaderFollowRedirectParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderFollowRedirectParams deserialize(ByteBuffer arg2) {
            return UrlLoaderFollowRedirectParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderFollowRedirectParams deserialize(Message arg1) {
            return UrlLoaderFollowRedirectParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(UrlLoaderFollowRedirectParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class UrlLoaderPauseReadingBodyFromNetParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            UrlLoaderPauseReadingBodyFromNetParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            UrlLoaderPauseReadingBodyFromNetParams.DEFAULT_STRUCT_INFO = UrlLoaderPauseReadingBodyFromNetParams.VERSION_ARRAY[0];
        }

        public UrlLoaderPauseReadingBodyFromNetParams() {
            this(0);
        }

        private UrlLoaderPauseReadingBodyFromNetParams(int arg2) {
            super(8, arg2);
        }

        public static UrlLoaderPauseReadingBodyFromNetParams decode(Decoder arg2) {
            UrlLoaderPauseReadingBodyFromNetParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UrlLoaderPauseReadingBodyFromNetParams(arg2.readAndValidateDataHeader(UrlLoaderPauseReadingBodyFromNetParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderPauseReadingBodyFromNetParams deserialize(ByteBuffer arg2) {
            return UrlLoaderPauseReadingBodyFromNetParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderPauseReadingBodyFromNetParams deserialize(Message arg1) {
            return UrlLoaderPauseReadingBodyFromNetParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(UrlLoaderPauseReadingBodyFromNetParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class UrlLoaderProceedWithResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            UrlLoaderProceedWithResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            UrlLoaderProceedWithResponseParams.DEFAULT_STRUCT_INFO = UrlLoaderProceedWithResponseParams.VERSION_ARRAY[0];
        }

        public UrlLoaderProceedWithResponseParams() {
            this(0);
        }

        private UrlLoaderProceedWithResponseParams(int arg2) {
            super(8, arg2);
        }

        public static UrlLoaderProceedWithResponseParams decode(Decoder arg2) {
            UrlLoaderProceedWithResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UrlLoaderProceedWithResponseParams(arg2.readAndValidateDataHeader(UrlLoaderProceedWithResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderProceedWithResponseParams deserialize(ByteBuffer arg2) {
            return UrlLoaderProceedWithResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderProceedWithResponseParams deserialize(Message arg1) {
            return UrlLoaderProceedWithResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(UrlLoaderProceedWithResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class UrlLoaderResumeReadingBodyFromNetParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            UrlLoaderResumeReadingBodyFromNetParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            UrlLoaderResumeReadingBodyFromNetParams.DEFAULT_STRUCT_INFO = UrlLoaderResumeReadingBodyFromNetParams.VERSION_ARRAY[0];
        }

        public UrlLoaderResumeReadingBodyFromNetParams() {
            this(0);
        }

        private UrlLoaderResumeReadingBodyFromNetParams(int arg2) {
            super(8, arg2);
        }

        public static UrlLoaderResumeReadingBodyFromNetParams decode(Decoder arg2) {
            UrlLoaderResumeReadingBodyFromNetParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UrlLoaderResumeReadingBodyFromNetParams(arg2.readAndValidateDataHeader(UrlLoaderResumeReadingBodyFromNetParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderResumeReadingBodyFromNetParams deserialize(ByteBuffer arg2) {
            return UrlLoaderResumeReadingBodyFromNetParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderResumeReadingBodyFromNetParams deserialize(Message arg1) {
            return UrlLoaderResumeReadingBodyFromNetParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(UrlLoaderResumeReadingBodyFromNetParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class UrlLoaderSetPriorityParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int intraPriorityValue;
        public int priority;

        static {
            UrlLoaderSetPriorityParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UrlLoaderSetPriorityParams.DEFAULT_STRUCT_INFO = UrlLoaderSetPriorityParams.VERSION_ARRAY[0];
        }

        public UrlLoaderSetPriorityParams() {
            this(0);
        }

        private UrlLoaderSetPriorityParams(int arg2) {
            super(16, arg2);
        }

        public static UrlLoaderSetPriorityParams decode(Decoder arg2) {
            UrlLoaderSetPriorityParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UrlLoaderSetPriorityParams(arg2.readAndValidateDataHeader(UrlLoaderSetPriorityParams.VERSION_ARRAY).elementsOrVersion);
                v1.priority = arg2.readInt(8);
                RequestPriority.validate(v1.priority);
                v1.intraPriorityValue = arg2.readInt(12);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderSetPriorityParams deserialize(ByteBuffer arg2) {
            return UrlLoaderSetPriorityParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderSetPriorityParams deserialize(Message arg1) {
            return UrlLoaderSetPriorityParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(UrlLoaderSetPriorityParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.priority, 8);
            arg3.encode(this.intraPriorityValue, 12);
        }
    }

    private static final int FOLLOW_REDIRECT_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int PAUSE_READING_BODY_FROM_NET_ORDINAL = 3;
    private static final int PROCEED_WITH_RESPONSE_ORDINAL = 1;
    private static final int RESUME_READING_BODY_FROM_NET_ORDINAL = 4;
    private static final int SET_PRIORITY_ORDINAL = 2;

    static {
        UrlLoader_Internal.MANAGER = new org.chromium.network.mojom.UrlLoader_Internal$1();
    }

    UrlLoader_Internal() {
        super();
    }
}

