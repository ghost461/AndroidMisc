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
import org.chromium.url.mojom.Origin;

class QuotaDispatcherHost_Internal {
    final class org.chromium.blink.mojom.QuotaDispatcherHost_Internal$1 extends Manager {
        org.chromium.blink.mojom.QuotaDispatcherHost_Internal$1() {
            super();
        }

        public QuotaDispatcherHost[] buildArray(int arg1) {
            return new QuotaDispatcherHost[arg1];
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

        public Stub buildStub(Core arg2, QuotaDispatcherHost arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((QuotaDispatcherHost)arg2));
        }

        public String getName() {
            return "blink::mojom::QuotaDispatcherHost";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.QuotaDispatcherHost$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void queryStorageUsageAndQuota(Origin arg7, int arg8, QueryStorageUsageAndQuotaResponse arg9) {
            QuotaDispatcherHostQueryStorageUsageAndQuotaParams v0 = new QuotaDispatcherHostQueryStorageUsageAndQuotaParams();
            v0.origin = arg7;
            v0.storageType = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParamsForwardToCallback(arg9));
        }

        public void requestStorageQuota(Origin arg4, int arg5, long arg6, RequestStorageQuotaResponse arg8) {
            QuotaDispatcherHostRequestStorageQuotaParams v0 = new QuotaDispatcherHostRequestStorageQuotaParams();
            v0.origin = arg4;
            v0.storageType = arg5;
            v0.requestedSize = arg6;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new QuotaDispatcherHostRequestStorageQuotaResponseParamsForwardToCallback(arg8));
        }
    }

    final class QuotaDispatcherHostQueryStorageUsageAndQuotaParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Origin origin;
        public int storageType;

        static {
            QuotaDispatcherHostQueryStorageUsageAndQuotaParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            QuotaDispatcherHostQueryStorageUsageAndQuotaParams.DEFAULT_STRUCT_INFO = QuotaDispatcherHostQueryStorageUsageAndQuotaParams.VERSION_ARRAY[0];
        }

        public QuotaDispatcherHostQueryStorageUsageAndQuotaParams() {
            this(0);
        }

        private QuotaDispatcherHostQueryStorageUsageAndQuotaParams(int arg2) {
            super(24, arg2);
        }

        public static QuotaDispatcherHostQueryStorageUsageAndQuotaParams decode(Decoder arg3) {
            QuotaDispatcherHostQueryStorageUsageAndQuotaParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new QuotaDispatcherHostQueryStorageUsageAndQuotaParams(arg3.readAndValidateDataHeader(QuotaDispatcherHostQueryStorageUsageAndQuotaParams.VERSION_ARRAY).elementsOrVersion);
                v1.origin = Origin.decode(arg3.readPointer(8, false));
                v1.storageType = arg3.readInt(16);
                StorageType.validate(v1.storageType);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static QuotaDispatcherHostQueryStorageUsageAndQuotaParams deserialize(ByteBuffer arg2) {
            return QuotaDispatcherHostQueryStorageUsageAndQuotaParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static QuotaDispatcherHostQueryStorageUsageAndQuotaParams deserialize(Message arg1) {
            return QuotaDispatcherHostQueryStorageUsageAndQuotaParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(QuotaDispatcherHostQueryStorageUsageAndQuotaParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.origin, 8, false);
            arg4.encode(this.storageType, 16);
        }
    }

    final class QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public long currentQuota;
        public long currentUsage;
        public int error;

        static {
            QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.DEFAULT_STRUCT_INFO = QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.VERSION_ARRAY[0];
        }

        public QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams() {
            this(0);
        }

        private QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams decode(Decoder arg4) {
            QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams(arg4.readAndValidateDataHeader(QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg4.readInt(8);
                QuotaStatusCode.validate(v1.error);
                v1.currentUsage = arg4.readLong(16);
                v1.currentQuota = arg4.readLong(24);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams deserialize(ByteBuffer arg2) {
            return QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams deserialize(Message arg1) {
            return QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.currentUsage, 16);
            arg4.encode(this.currentQuota, 24);
        }
    }

    class QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final QueryStorageUsageAndQuotaResponse mCallback;

        QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParamsForwardToCallback(QueryStorageUsageAndQuotaResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7 = arg7.asServiceMessage();
                if(!v7.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams v7_1 = QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams.deserialize(v7.getPayload());
                this.mCallback.call(Integer.valueOf(v7_1.error), Long.valueOf(v7_1.currentUsage), Long.valueOf(v7_1.currentQuota));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParamsProxyToResponder implements QueryStorageUsageAndQuotaResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, Long arg6, Long arg7) {
            QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams v0 = new QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParams();
            v0.error = arg5.intValue();
            v0.currentUsage = arg6.longValue();
            v0.currentQuota = arg7.longValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((Long)arg2), ((Long)arg3));
        }
    }

    final class QuotaDispatcherHostRequestStorageQuotaParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public Origin origin;
        public long requestedSize;
        public int storageType;

        static {
            QuotaDispatcherHostRequestStorageQuotaParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            QuotaDispatcherHostRequestStorageQuotaParams.DEFAULT_STRUCT_INFO = QuotaDispatcherHostRequestStorageQuotaParams.VERSION_ARRAY[0];
        }

        public QuotaDispatcherHostRequestStorageQuotaParams() {
            this(0);
        }

        private QuotaDispatcherHostRequestStorageQuotaParams(int arg2) {
            super(0x20, arg2);
        }

        public static QuotaDispatcherHostRequestStorageQuotaParams decode(Decoder arg4) {
            QuotaDispatcherHostRequestStorageQuotaParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new QuotaDispatcherHostRequestStorageQuotaParams(arg4.readAndValidateDataHeader(QuotaDispatcherHostRequestStorageQuotaParams.VERSION_ARRAY).elementsOrVersion);
                v1.origin = Origin.decode(arg4.readPointer(8, false));
                v1.storageType = arg4.readInt(16);
                StorageType.validate(v1.storageType);
                v1.requestedSize = arg4.readLong(24);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static QuotaDispatcherHostRequestStorageQuotaParams deserialize(ByteBuffer arg2) {
            return QuotaDispatcherHostRequestStorageQuotaParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static QuotaDispatcherHostRequestStorageQuotaParams deserialize(Message arg1) {
            return QuotaDispatcherHostRequestStorageQuotaParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(QuotaDispatcherHostRequestStorageQuotaParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.origin, 8, false);
            arg4.encode(this.storageType, 16);
            arg4.encode(this.requestedSize, 24);
        }
    }

    final class QuotaDispatcherHostRequestStorageQuotaResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public long currentUsage;
        public int error;
        public long grantedQuota;

        static {
            QuotaDispatcherHostRequestStorageQuotaResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            QuotaDispatcherHostRequestStorageQuotaResponseParams.DEFAULT_STRUCT_INFO = QuotaDispatcherHostRequestStorageQuotaResponseParams.VERSION_ARRAY[0];
        }

        public QuotaDispatcherHostRequestStorageQuotaResponseParams() {
            this(0);
        }

        private QuotaDispatcherHostRequestStorageQuotaResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static QuotaDispatcherHostRequestStorageQuotaResponseParams decode(Decoder arg4) {
            QuotaDispatcherHostRequestStorageQuotaResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new QuotaDispatcherHostRequestStorageQuotaResponseParams(arg4.readAndValidateDataHeader(QuotaDispatcherHostRequestStorageQuotaResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg4.readInt(8);
                QuotaStatusCode.validate(v1.error);
                v1.currentUsage = arg4.readLong(16);
                v1.grantedQuota = arg4.readLong(24);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static QuotaDispatcherHostRequestStorageQuotaResponseParams deserialize(ByteBuffer arg2) {
            return QuotaDispatcherHostRequestStorageQuotaResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static QuotaDispatcherHostRequestStorageQuotaResponseParams deserialize(Message arg1) {
            return QuotaDispatcherHostRequestStorageQuotaResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(QuotaDispatcherHostRequestStorageQuotaResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.currentUsage, 16);
            arg4.encode(this.grantedQuota, 24);
        }
    }

    class QuotaDispatcherHostRequestStorageQuotaResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RequestStorageQuotaResponse mCallback;

        QuotaDispatcherHostRequestStorageQuotaResponseParamsForwardToCallback(RequestStorageQuotaResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg8) {
            try {
                ServiceMessage v8 = arg8.asServiceMessage();
                if(!v8.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                QuotaDispatcherHostRequestStorageQuotaResponseParams v8_1 = QuotaDispatcherHostRequestStorageQuotaResponseParams.deserialize(v8.getPayload());
                this.mCallback.call(Integer.valueOf(v8_1.error), Long.valueOf(v8_1.currentUsage), Long.valueOf(v8_1.grantedQuota));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class QuotaDispatcherHostRequestStorageQuotaResponseParamsProxyToResponder implements RequestStorageQuotaResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        QuotaDispatcherHostRequestStorageQuotaResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, Long arg6, Long arg7) {
            QuotaDispatcherHostRequestStorageQuotaResponseParams v0 = new QuotaDispatcherHostRequestStorageQuotaResponseParams();
            v0.error = arg5.intValue();
            v0.currentUsage = arg6.longValue();
            v0.grantedQuota = arg7.longValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((Long)arg2), ((Long)arg3));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, QuotaDispatcherHost arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(QuotaDispatcherHost_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg13, MessageReceiver arg14) {
            try {
                ServiceMessage v13_1 = arg13.asServiceMessage();
                MessageHeader v1 = v13_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_34;
                    }
                    case 0: {
                        goto label_23;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_34:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), QuotaDispatcherHost_Internal.MANAGER, v13_1, arg14);
            label_23:
                QuotaDispatcherHostQueryStorageUsageAndQuotaParams v13_2 = QuotaDispatcherHostQueryStorageUsageAndQuotaParams.deserialize(v13_1.getPayload());
                this.getImpl().queryStorageUsageAndQuota(v13_2.origin, v13_2.storageType, new QuotaDispatcherHostQueryStorageUsageAndQuotaResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_10:
                QuotaDispatcherHostRequestStorageQuotaParams v13_3 = QuotaDispatcherHostRequestStorageQuotaParams.deserialize(v13_1.getPayload());
                this.getImpl().requestStorageQuota(v13_3.origin, v13_3.storageType, v13_3.requestedSize, new QuotaDispatcherHostRequestStorageQuotaResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v13) {
                System.err.println(v13.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int QUERY_STORAGE_USAGE_AND_QUOTA_ORDINAL = 0;
    private static final int REQUEST_STORAGE_QUOTA_ORDINAL = 1;

    static {
        QuotaDispatcherHost_Internal.MANAGER = new org.chromium.blink.mojom.QuotaDispatcherHost_Internal$1();
    }

    QuotaDispatcherHost_Internal() {
        super();
    }
}

