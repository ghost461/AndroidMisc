package org.chromium.media.mojom;

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

class PlatformVerification_Internal {
    final class org.chromium.media.mojom.PlatformVerification_Internal$1 extends Manager {
        org.chromium.media.mojom.PlatformVerification_Internal$1() {
            super();
        }

        public PlatformVerification[] buildArray(int arg1) {
            return new PlatformVerification[arg1];
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

        public Stub buildStub(Core arg2, PlatformVerification arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((PlatformVerification)arg2));
        }

        public String getName() {
            return "media::mojom::PlatformVerification";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class PlatformVerificationChallengePlatformParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String challenge;
        public String serviceId;

        static {
            PlatformVerificationChallengePlatformParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            PlatformVerificationChallengePlatformParams.DEFAULT_STRUCT_INFO = PlatformVerificationChallengePlatformParams.VERSION_ARRAY[0];
        }

        public PlatformVerificationChallengePlatformParams() {
            this(0);
        }

        private PlatformVerificationChallengePlatformParams(int arg2) {
            super(24, arg2);
        }

        public static PlatformVerificationChallengePlatformParams decode(Decoder arg3) {
            PlatformVerificationChallengePlatformParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PlatformVerificationChallengePlatformParams(arg3.readAndValidateDataHeader(PlatformVerificationChallengePlatformParams.VERSION_ARRAY).elementsOrVersion);
                v1.serviceId = arg3.readString(8, false);
                v1.challenge = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PlatformVerificationChallengePlatformParams deserialize(ByteBuffer arg2) {
            return PlatformVerificationChallengePlatformParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PlatformVerificationChallengePlatformParams deserialize(Message arg1) {
            return PlatformVerificationChallengePlatformParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(PlatformVerificationChallengePlatformParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.serviceId, 8, false);
            arg4.encode(this.challenge, 16, false);
        }
    }

    final class PlatformVerificationChallengePlatformResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public String platformKeyCertificate;
        public String signedData;
        public String signedDataSignature;
        public boolean success;

        static {
            PlatformVerificationChallengePlatformResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            PlatformVerificationChallengePlatformResponseParams.DEFAULT_STRUCT_INFO = PlatformVerificationChallengePlatformResponseParams.VERSION_ARRAY[0];
        }

        public PlatformVerificationChallengePlatformResponseParams() {
            this(0);
        }

        private PlatformVerificationChallengePlatformResponseParams(int arg2) {
            super(40, arg2);
        }

        public static PlatformVerificationChallengePlatformResponseParams decode(Decoder arg3) {
            PlatformVerificationChallengePlatformResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new PlatformVerificationChallengePlatformResponseParams(arg3.readAndValidateDataHeader(PlatformVerificationChallengePlatformResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
                v1.signedData = arg3.readString(16, false);
                v1.signedDataSignature = arg3.readString(24, false);
                v1.platformKeyCertificate = arg3.readString(0x20, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static PlatformVerificationChallengePlatformResponseParams deserialize(ByteBuffer arg2) {
            return PlatformVerificationChallengePlatformResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PlatformVerificationChallengePlatformResponseParams deserialize(Message arg1) {
            return PlatformVerificationChallengePlatformResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(PlatformVerificationChallengePlatformResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.success, 8, 0);
            arg4.encode(this.signedData, 16, false);
            arg4.encode(this.signedDataSignature, 24, false);
            arg4.encode(this.platformKeyCertificate, 0x20, false);
        }
    }

    class PlatformVerificationChallengePlatformResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ChallengePlatformResponse mCallback;

        PlatformVerificationChallengePlatformResponseParamsForwardToCallback(ChallengePlatformResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                PlatformVerificationChallengePlatformResponseParams v6_1 = PlatformVerificationChallengePlatformResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(Boolean.valueOf(v6_1.success), v6_1.signedData, v6_1.signedDataSignature, v6_1.platformKeyCertificate);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class PlatformVerificationChallengePlatformResponseParamsProxyToResponder implements ChallengePlatformResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        PlatformVerificationChallengePlatformResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg4, String arg5, String arg6, String arg7) {
            PlatformVerificationChallengePlatformResponseParams v0 = new PlatformVerificationChallengePlatformResponseParams();
            v0.success = arg4.booleanValue();
            v0.signedData = arg5;
            v0.signedDataSignature = arg6;
            v0.platformKeyCertificate = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3, Object arg4) {
            this.call(((Boolean)arg1), ((String)arg2), ((String)arg3), ((String)arg4));
        }
    }

    final class PlatformVerificationGetStorageIdParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int version;

        static {
            PlatformVerificationGetStorageIdParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            PlatformVerificationGetStorageIdParams.DEFAULT_STRUCT_INFO = PlatformVerificationGetStorageIdParams.VERSION_ARRAY[0];
        }

        public PlatformVerificationGetStorageIdParams() {
            this(0);
        }

        private PlatformVerificationGetStorageIdParams(int arg2) {
            super(16, arg2);
        }

        public static PlatformVerificationGetStorageIdParams decode(Decoder arg2) {
            PlatformVerificationGetStorageIdParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new PlatformVerificationGetStorageIdParams(arg2.readAndValidateDataHeader(PlatformVerificationGetStorageIdParams.VERSION_ARRAY).elementsOrVersion);
                v1.version = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static PlatformVerificationGetStorageIdParams deserialize(ByteBuffer arg2) {
            return PlatformVerificationGetStorageIdParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PlatformVerificationGetStorageIdParams deserialize(Message arg1) {
            return PlatformVerificationGetStorageIdParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(PlatformVerificationGetStorageIdParams.DEFAULT_STRUCT_INFO).encode(this.version, 8);
        }
    }

    final class PlatformVerificationGetStorageIdResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] storageId;
        public int version;

        static {
            PlatformVerificationGetStorageIdResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            PlatformVerificationGetStorageIdResponseParams.DEFAULT_STRUCT_INFO = PlatformVerificationGetStorageIdResponseParams.VERSION_ARRAY[0];
        }

        public PlatformVerificationGetStorageIdResponseParams() {
            this(0);
        }

        private PlatformVerificationGetStorageIdResponseParams(int arg2) {
            super(24, arg2);
        }

        public static PlatformVerificationGetStorageIdResponseParams decode(Decoder arg4) {
            PlatformVerificationGetStorageIdResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new PlatformVerificationGetStorageIdResponseParams(arg4.readAndValidateDataHeader(PlatformVerificationGetStorageIdResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.version = arg4.readInt(8);
                v1.storageId = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static PlatformVerificationGetStorageIdResponseParams deserialize(ByteBuffer arg2) {
            return PlatformVerificationGetStorageIdResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static PlatformVerificationGetStorageIdResponseParams deserialize(Message arg1) {
            return PlatformVerificationGetStorageIdResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(PlatformVerificationGetStorageIdResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.version, 8);
            arg5.encode(this.storageId, 16, 0, -1);
        }
    }

    class PlatformVerificationGetStorageIdResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetStorageIdResponse mCallback;

        PlatformVerificationGetStorageIdResponseParamsForwardToCallback(GetStorageIdResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                PlatformVerificationGetStorageIdResponseParams v5_1 = PlatformVerificationGetStorageIdResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.version), v5_1.storageId);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class PlatformVerificationGetStorageIdResponseParamsProxyToResponder implements GetStorageIdResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        PlatformVerificationGetStorageIdResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, byte[] arg7) {
            PlatformVerificationGetStorageIdResponseParams v0 = new PlatformVerificationGetStorageIdResponseParams();
            v0.version = arg6.intValue();
            v0.storageId = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((byte[])arg2));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.PlatformVerification$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void challengePlatform(String arg7, String arg8, ChallengePlatformResponse arg9) {
            PlatformVerificationChallengePlatformParams v0 = new PlatformVerificationChallengePlatformParams();
            v0.serviceId = arg7;
            v0.challenge = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new PlatformVerificationChallengePlatformResponseParamsForwardToCallback(arg9));
        }

        public void getStorageId(int arg7, GetStorageIdResponse arg8) {
            PlatformVerificationGetStorageIdParams v0 = new PlatformVerificationGetStorageIdParams();
            v0.version = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new PlatformVerificationGetStorageIdResponseParamsForwardToCallback(arg8));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, PlatformVerification arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(PlatformVerification_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg10, MessageReceiver arg11) {
            try {
                ServiceMessage v10_1 = arg10.asServiceMessage();
                MessageHeader v1 = v10_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_31;
                    }
                    case 0: {
                        goto label_20;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                PlatformVerificationChallengePlatformParams v10_2 = PlatformVerificationChallengePlatformParams.deserialize(v10_1.getPayload());
                this.getImpl().challengePlatform(v10_2.serviceId, v10_2.challenge, new PlatformVerificationChallengePlatformResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_10:
                this.getImpl().getStorageId(PlatformVerificationGetStorageIdParams.deserialize(v10_1.getPayload()).version, new PlatformVerificationGetStorageIdResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_31:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), PlatformVerification_Internal.MANAGER, v10_1, arg11);
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int CHALLENGE_PLATFORM_ORDINAL = 0;
    private static final int GET_STORAGE_ID_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        PlatformVerification_Internal.MANAGER = new org.chromium.media.mojom.PlatformVerification_Internal$1();
    }

    PlatformVerification_Internal() {
        super();
    }
}

