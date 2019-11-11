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
import org.chromium.url.mojom.Origin;

class ContentDecryptionModule_Internal {
    final class org.chromium.media.mojom.ContentDecryptionModule_Internal$1 extends Manager {
        org.chromium.media.mojom.ContentDecryptionModule_Internal$1() {
            super();
        }

        public ContentDecryptionModule[] buildArray(int arg1) {
            return new ContentDecryptionModule[arg1];
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

        public Stub buildStub(Core arg2, ContentDecryptionModule arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ContentDecryptionModule)arg2));
        }

        public String getName() {
            return "media::mojom::ContentDecryptionModule";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ContentDecryptionModuleCloseSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String sessionId;

        static {
            ContentDecryptionModuleCloseSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleCloseSessionParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleCloseSessionParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleCloseSessionParams() {
            this(0);
        }

        private ContentDecryptionModuleCloseSessionParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleCloseSessionParams decode(Decoder arg3) {
            ContentDecryptionModuleCloseSessionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleCloseSessionParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleCloseSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleCloseSessionParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleCloseSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleCloseSessionParams deserialize(Message arg1) {
            return ContentDecryptionModuleCloseSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ContentDecryptionModuleCloseSessionParams.DEFAULT_STRUCT_INFO).encode(this.sessionId, 8, false);
        }
    }

    final class ContentDecryptionModuleCloseSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CdmPromiseResult result;

        static {
            ContentDecryptionModuleCloseSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleCloseSessionResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleCloseSessionResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleCloseSessionResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleCloseSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleCloseSessionResponseParams decode(Decoder arg3) {
            ContentDecryptionModuleCloseSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleCloseSessionResponseParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleCloseSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleCloseSessionResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleCloseSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleCloseSessionResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleCloseSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ContentDecryptionModuleCloseSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class ContentDecryptionModuleCloseSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CloseSessionResponse mCallback;

        ContentDecryptionModuleCloseSessionResponseParamsForwardToCallback(CloseSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(7, 2)) {
                    return 0;
                }

                this.mCallback.call(ContentDecryptionModuleCloseSessionResponseParams.deserialize(v5.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleCloseSessionResponseParamsProxyToResponder implements CloseSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleCloseSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((CdmPromiseResult)arg1));
        }

        public void call(CdmPromiseResult arg7) {
            ContentDecryptionModuleCloseSessionResponseParams v0 = new ContentDecryptionModuleCloseSessionResponseParams();
            v0.result = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(7, 2, this.mRequestId)));
        }
    }

    final class ContentDecryptionModuleCreateSessionAndGenerateRequestParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] initData;
        public int initDataType;
        public int sessionType;

        static {
            ContentDecryptionModuleCreateSessionAndGenerateRequestParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ContentDecryptionModuleCreateSessionAndGenerateRequestParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleCreateSessionAndGenerateRequestParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleCreateSessionAndGenerateRequestParams() {
            this(0);
        }

        private ContentDecryptionModuleCreateSessionAndGenerateRequestParams(int arg2) {
            super(24, arg2);
        }

        public static ContentDecryptionModuleCreateSessionAndGenerateRequestParams decode(Decoder arg4) {
            ContentDecryptionModuleCreateSessionAndGenerateRequestParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleCreateSessionAndGenerateRequestParams(arg4.readAndValidateDataHeader(ContentDecryptionModuleCreateSessionAndGenerateRequestParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionType = arg4.readInt(8);
                CdmSessionType.validate(v1.sessionType);
                v1.initDataType = arg4.readInt(12);
                EmeInitDataType.validate(v1.initDataType);
                v1.initData = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleCreateSessionAndGenerateRequestParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleCreateSessionAndGenerateRequestParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleCreateSessionAndGenerateRequestParams deserialize(Message arg1) {
            return ContentDecryptionModuleCreateSessionAndGenerateRequestParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ContentDecryptionModuleCreateSessionAndGenerateRequestParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.sessionType, 8);
            arg5.encode(this.initDataType, 12);
            arg5.encode(this.initData, 16, 0, -1);
        }
    }

    final class ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public CdmPromiseResult result;
        public String sessionId;

        static {
            ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams decode(Decoder arg3) {
            ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg3.readPointer(8, false));
                v1.sessionId = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8, false);
            arg4.encode(this.sessionId, 16, false);
        }
    }

    class ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CreateSessionAndGenerateRequestResponse mCallback;

        ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParamsForwardToCallback(CreateSessionAndGenerateRequestResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams v5_1 = ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(v5_1.result, v5_1.sessionId);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParamsProxyToResponder implements CreateSessionAndGenerateRequestResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2) {
            this.call(((CdmPromiseResult)arg1), ((String)arg2));
        }

        public void call(CdmPromiseResult arg6, String arg7) {
            ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams v0 = new ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParams();
            v0.result = arg6;
            v0.sessionId = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }
    }

    final class ContentDecryptionModuleGetStatusForPolicyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int minHdcpVersion;

        static {
            ContentDecryptionModuleGetStatusForPolicyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleGetStatusForPolicyParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleGetStatusForPolicyParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleGetStatusForPolicyParams() {
            this(0);
        }

        private ContentDecryptionModuleGetStatusForPolicyParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleGetStatusForPolicyParams decode(Decoder arg2) {
            ContentDecryptionModuleGetStatusForPolicyParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleGetStatusForPolicyParams(arg2.readAndValidateDataHeader(ContentDecryptionModuleGetStatusForPolicyParams.VERSION_ARRAY).elementsOrVersion);
                v1.minHdcpVersion = arg2.readInt(8);
                HdcpVersion.validate(v1.minHdcpVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleGetStatusForPolicyParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleGetStatusForPolicyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleGetStatusForPolicyParams deserialize(Message arg1) {
            return ContentDecryptionModuleGetStatusForPolicyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ContentDecryptionModuleGetStatusForPolicyParams.DEFAULT_STRUCT_INFO).encode(this.minHdcpVersion, 8);
        }
    }

    final class ContentDecryptionModuleGetStatusForPolicyResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int keyStatus;
        public CdmPromiseResult result;

        static {
            ContentDecryptionModuleGetStatusForPolicyResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ContentDecryptionModuleGetStatusForPolicyResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleGetStatusForPolicyResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleGetStatusForPolicyResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleGetStatusForPolicyResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ContentDecryptionModuleGetStatusForPolicyResponseParams decode(Decoder arg3) {
            ContentDecryptionModuleGetStatusForPolicyResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleGetStatusForPolicyResponseParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleGetStatusForPolicyResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg3.readPointer(8, false));
                v1.keyStatus = arg3.readInt(16);
                CdmKeyStatus.validate(v1.keyStatus);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleGetStatusForPolicyResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleGetStatusForPolicyResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleGetStatusForPolicyResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleGetStatusForPolicyResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ContentDecryptionModuleGetStatusForPolicyResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8, false);
            arg4.encode(this.keyStatus, 16);
        }
    }

    class ContentDecryptionModuleGetStatusForPolicyResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetStatusForPolicyResponse mCallback;

        ContentDecryptionModuleGetStatusForPolicyResponseParamsForwardToCallback(GetStatusForPolicyResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                ContentDecryptionModuleGetStatusForPolicyResponseParams v5_1 = ContentDecryptionModuleGetStatusForPolicyResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(v5_1.result, Integer.valueOf(v5_1.keyStatus));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleGetStatusForPolicyResponseParamsProxyToResponder implements GetStatusForPolicyResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleGetStatusForPolicyResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2) {
            this.call(((CdmPromiseResult)arg1), ((Integer)arg2));
        }

        public void call(CdmPromiseResult arg6, Integer arg7) {
            ContentDecryptionModuleGetStatusForPolicyResponseParams v0 = new ContentDecryptionModuleGetStatusForPolicyResponseParams();
            v0.result = arg6;
            v0.keyStatus = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    final class ContentDecryptionModuleInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public CdmConfig cdmConfig;
        public String keySystem;
        public Origin securityOrigin;

        static {
            ContentDecryptionModuleInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ContentDecryptionModuleInitializeParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleInitializeParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleInitializeParams() {
            this(0);
        }

        private ContentDecryptionModuleInitializeParams(int arg2) {
            super(0x20, arg2);
        }

        public static ContentDecryptionModuleInitializeParams decode(Decoder arg3) {
            ContentDecryptionModuleInitializeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleInitializeParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.keySystem = arg3.readString(8, false);
                v1.securityOrigin = Origin.decode(arg3.readPointer(16, false));
                v1.cdmConfig = CdmConfig.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleInitializeParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleInitializeParams deserialize(Message arg1) {
            return ContentDecryptionModuleInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ContentDecryptionModuleInitializeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.keySystem, 8, false);
            arg4.encode(this.securityOrigin, 16, false);
            arg4.encode(this.cdmConfig, 24, false);
        }
    }

    final class ContentDecryptionModuleInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int cdmId;
        public Decryptor decryptor;
        public CdmPromiseResult result;

        static {
            ContentDecryptionModuleInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ContentDecryptionModuleInitializeResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleInitializeResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleInitializeResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleInitializeResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static ContentDecryptionModuleInitializeResponseParams decode(Decoder arg4) {
            ContentDecryptionModuleInitializeResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleInitializeResponseParams(arg4.readAndValidateDataHeader(ContentDecryptionModuleInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg4.readPointer(8, false));
                v1.cdmId = arg4.readInt(16);
                v1.decryptor = arg4.readServiceInterface(20, true, Decryptor.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleInitializeResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleInitializeResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ContentDecryptionModuleInitializeResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.result, 8, false);
            arg5.encode(this.cdmId, 16);
            arg5.encode(this.decryptor, 20, true, Decryptor.MANAGER);
        }
    }

    class ContentDecryptionModuleInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        ContentDecryptionModuleInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                ContentDecryptionModuleInitializeResponseParams v6_1 = ContentDecryptionModuleInitializeResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(v6_1.result, Integer.valueOf(v6_1.cdmId), v6_1.decryptor);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((CdmPromiseResult)arg1), ((Integer)arg2), ((Decryptor)arg3));
        }

        public void call(CdmPromiseResult arg5, Integer arg6, Decryptor arg7) {
            ContentDecryptionModuleInitializeResponseParams v0 = new ContentDecryptionModuleInitializeResponseParams();
            v0.result = arg5;
            v0.cdmId = arg6.intValue();
            v0.decryptor = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class ContentDecryptionModuleLoadSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String sessionId;
        public int sessionType;

        static {
            ContentDecryptionModuleLoadSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ContentDecryptionModuleLoadSessionParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleLoadSessionParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleLoadSessionParams() {
            this(0);
        }

        private ContentDecryptionModuleLoadSessionParams(int arg2) {
            super(24, arg2);
        }

        public static ContentDecryptionModuleLoadSessionParams decode(Decoder arg3) {
            ContentDecryptionModuleLoadSessionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleLoadSessionParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleLoadSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionType = arg3.readInt(8);
                CdmSessionType.validate(v1.sessionType);
                v1.sessionId = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleLoadSessionParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleLoadSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleLoadSessionParams deserialize(Message arg1) {
            return ContentDecryptionModuleLoadSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ContentDecryptionModuleLoadSessionParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.sessionType, 8);
            arg4.encode(this.sessionId, 16, false);
        }
    }

    final class ContentDecryptionModuleLoadSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public CdmPromiseResult result;
        public String sessionId;

        static {
            ContentDecryptionModuleLoadSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ContentDecryptionModuleLoadSessionResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleLoadSessionResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleLoadSessionResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleLoadSessionResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ContentDecryptionModuleLoadSessionResponseParams decode(Decoder arg3) {
            ContentDecryptionModuleLoadSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleLoadSessionResponseParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleLoadSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg3.readPointer(8, false));
                v1.sessionId = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleLoadSessionResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleLoadSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleLoadSessionResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleLoadSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ContentDecryptionModuleLoadSessionResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8, false);
            arg4.encode(this.sessionId, 16, false);
        }
    }

    class ContentDecryptionModuleLoadSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final LoadSessionResponse mCallback;

        ContentDecryptionModuleLoadSessionResponseParamsForwardToCallback(LoadSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                ContentDecryptionModuleLoadSessionResponseParams v5_1 = ContentDecryptionModuleLoadSessionResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(v5_1.result, v5_1.sessionId);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleLoadSessionResponseParamsProxyToResponder implements LoadSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleLoadSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2) {
            this.call(((CdmPromiseResult)arg1), ((String)arg2));
        }

        public void call(CdmPromiseResult arg6, String arg7) {
            ContentDecryptionModuleLoadSessionResponseParams v0 = new ContentDecryptionModuleLoadSessionResponseParams();
            v0.result = arg6;
            v0.sessionId = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }
    }

    final class ContentDecryptionModuleRemoveSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String sessionId;

        static {
            ContentDecryptionModuleRemoveSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleRemoveSessionParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleRemoveSessionParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleRemoveSessionParams() {
            this(0);
        }

        private ContentDecryptionModuleRemoveSessionParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleRemoveSessionParams decode(Decoder arg3) {
            ContentDecryptionModuleRemoveSessionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleRemoveSessionParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleRemoveSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleRemoveSessionParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleRemoveSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleRemoveSessionParams deserialize(Message arg1) {
            return ContentDecryptionModuleRemoveSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ContentDecryptionModuleRemoveSessionParams.DEFAULT_STRUCT_INFO).encode(this.sessionId, 8, false);
        }
    }

    final class ContentDecryptionModuleRemoveSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CdmPromiseResult result;

        static {
            ContentDecryptionModuleRemoveSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleRemoveSessionResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleRemoveSessionResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleRemoveSessionResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleRemoveSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleRemoveSessionResponseParams decode(Decoder arg3) {
            ContentDecryptionModuleRemoveSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleRemoveSessionResponseParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleRemoveSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleRemoveSessionResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleRemoveSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleRemoveSessionResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleRemoveSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ContentDecryptionModuleRemoveSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class ContentDecryptionModuleRemoveSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RemoveSessionResponse mCallback;

        ContentDecryptionModuleRemoveSessionResponseParamsForwardToCallback(RemoveSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(8, 2)) {
                    return 0;
                }

                this.mCallback.call(ContentDecryptionModuleRemoveSessionResponseParams.deserialize(v5.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleRemoveSessionResponseParamsProxyToResponder implements RemoveSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleRemoveSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((CdmPromiseResult)arg1));
        }

        public void call(CdmPromiseResult arg7) {
            ContentDecryptionModuleRemoveSessionResponseParams v0 = new ContentDecryptionModuleRemoveSessionResponseParams();
            v0.result = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(8, 2, this.mRequestId)));
        }
    }

    final class ContentDecryptionModuleSetClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ContentDecryptionModuleClient client;

        static {
            ContentDecryptionModuleSetClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleSetClientParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleSetClientParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleSetClientParams() {
            this(0);
        }

        private ContentDecryptionModuleSetClientParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleSetClientParams decode(Decoder arg4) {
            ContentDecryptionModuleSetClientParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleSetClientParams(arg4.readAndValidateDataHeader(ContentDecryptionModuleSetClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg4.readServiceInterface(8, false, ContentDecryptionModuleClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleSetClientParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleSetClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleSetClientParams deserialize(Message arg1) {
            return ContentDecryptionModuleSetClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(ContentDecryptionModuleSetClientParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false, ContentDecryptionModuleClient.MANAGER);
        }
    }

    final class ContentDecryptionModuleSetServerCertificateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] certificateData;

        static {
            ContentDecryptionModuleSetServerCertificateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleSetServerCertificateParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleSetServerCertificateParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleSetServerCertificateParams() {
            this(0);
        }

        private ContentDecryptionModuleSetServerCertificateParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleSetServerCertificateParams decode(Decoder arg4) {
            ContentDecryptionModuleSetServerCertificateParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleSetServerCertificateParams(arg4.readAndValidateDataHeader(ContentDecryptionModuleSetServerCertificateParams.VERSION_ARRAY).elementsOrVersion);
                v1.certificateData = arg4.readBytes(8, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleSetServerCertificateParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleSetServerCertificateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleSetServerCertificateParams deserialize(Message arg1) {
            return ContentDecryptionModuleSetServerCertificateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(ContentDecryptionModuleSetServerCertificateParams.DEFAULT_STRUCT_INFO).encode(this.certificateData, 8, 0, -1);
        }
    }

    final class ContentDecryptionModuleSetServerCertificateResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CdmPromiseResult result;

        static {
            ContentDecryptionModuleSetServerCertificateResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleSetServerCertificateResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleSetServerCertificateResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleSetServerCertificateResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleSetServerCertificateResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleSetServerCertificateResponseParams decode(Decoder arg3) {
            ContentDecryptionModuleSetServerCertificateResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleSetServerCertificateResponseParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleSetServerCertificateResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleSetServerCertificateResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleSetServerCertificateResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleSetServerCertificateResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleSetServerCertificateResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ContentDecryptionModuleSetServerCertificateResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class ContentDecryptionModuleSetServerCertificateResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetServerCertificateResponse mCallback;

        ContentDecryptionModuleSetServerCertificateResponseParamsForwardToCallback(SetServerCertificateResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(ContentDecryptionModuleSetServerCertificateResponseParams.deserialize(v4.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleSetServerCertificateResponseParamsProxyToResponder implements SetServerCertificateResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleSetServerCertificateResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((CdmPromiseResult)arg1));
        }

        public void call(CdmPromiseResult arg6) {
            ContentDecryptionModuleSetServerCertificateResponseParams v0 = new ContentDecryptionModuleSetServerCertificateResponseParams();
            v0.result = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class ContentDecryptionModuleUpdateSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] response;
        public String sessionId;

        static {
            ContentDecryptionModuleUpdateSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ContentDecryptionModuleUpdateSessionParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleUpdateSessionParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleUpdateSessionParams() {
            this(0);
        }

        private ContentDecryptionModuleUpdateSessionParams(int arg2) {
            super(24, arg2);
        }

        public static ContentDecryptionModuleUpdateSessionParams decode(Decoder arg4) {
            ContentDecryptionModuleUpdateSessionParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleUpdateSessionParams(arg4.readAndValidateDataHeader(ContentDecryptionModuleUpdateSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg4.readString(8, false);
                v1.response = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleUpdateSessionParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleUpdateSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleUpdateSessionParams deserialize(Message arg1) {
            return ContentDecryptionModuleUpdateSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ContentDecryptionModuleUpdateSessionParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.sessionId, 8, false);
            arg5.encode(this.response, 16, 0, -1);
        }
    }

    final class ContentDecryptionModuleUpdateSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CdmPromiseResult result;

        static {
            ContentDecryptionModuleUpdateSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ContentDecryptionModuleUpdateSessionResponseParams.DEFAULT_STRUCT_INFO = ContentDecryptionModuleUpdateSessionResponseParams.VERSION_ARRAY[0];
        }

        public ContentDecryptionModuleUpdateSessionResponseParams() {
            this(0);
        }

        private ContentDecryptionModuleUpdateSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ContentDecryptionModuleUpdateSessionResponseParams decode(Decoder arg3) {
            ContentDecryptionModuleUpdateSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ContentDecryptionModuleUpdateSessionResponseParams(arg3.readAndValidateDataHeader(ContentDecryptionModuleUpdateSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = CdmPromiseResult.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ContentDecryptionModuleUpdateSessionResponseParams deserialize(ByteBuffer arg2) {
            return ContentDecryptionModuleUpdateSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ContentDecryptionModuleUpdateSessionResponseParams deserialize(Message arg1) {
            return ContentDecryptionModuleUpdateSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ContentDecryptionModuleUpdateSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class ContentDecryptionModuleUpdateSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final UpdateSessionResponse mCallback;

        ContentDecryptionModuleUpdateSessionResponseParamsForwardToCallback(UpdateSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(6, 2)) {
                    return 0;
                }

                this.mCallback.call(ContentDecryptionModuleUpdateSessionResponseParams.deserialize(v5.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ContentDecryptionModuleUpdateSessionResponseParamsProxyToResponder implements UpdateSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ContentDecryptionModuleUpdateSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((CdmPromiseResult)arg1));
        }

        public void call(CdmPromiseResult arg7) {
            ContentDecryptionModuleUpdateSessionResponseParams v0 = new ContentDecryptionModuleUpdateSessionResponseParams();
            v0.result = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(6, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.ContentDecryptionModule$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void closeSession(String arg8, CloseSessionResponse arg9) {
            ContentDecryptionModuleCloseSessionParams v0 = new ContentDecryptionModuleCloseSessionParams();
            v0.sessionId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7, 1, 0)), new ContentDecryptionModuleCloseSessionResponseParamsForwardToCallback(arg9));
        }

        public void createSessionAndGenerateRequest(int arg6, int arg7, byte[] arg8, CreateSessionAndGenerateRequestResponse arg9) {
            ContentDecryptionModuleCreateSessionAndGenerateRequestParams v0 = new ContentDecryptionModuleCreateSessionAndGenerateRequestParams();
            v0.sessionType = arg6;
            v0.initDataType = arg7;
            v0.initData = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParamsForwardToCallback(arg9));
        }

        public void getStatusForPolicy(int arg8, GetStatusForPolicyResponse arg9) {
            ContentDecryptionModuleGetStatusForPolicyParams v0 = new ContentDecryptionModuleGetStatusForPolicyParams();
            v0.minHdcpVersion = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new ContentDecryptionModuleGetStatusForPolicyResponseParamsForwardToCallback(arg9));
        }

        public void initialize(String arg5, Origin arg6, CdmConfig arg7, InitializeResponse arg8) {
            ContentDecryptionModuleInitializeParams v0 = new ContentDecryptionModuleInitializeParams();
            v0.keySystem = arg5;
            v0.securityOrigin = arg6;
            v0.cdmConfig = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new ContentDecryptionModuleInitializeResponseParamsForwardToCallback(arg8));
        }

        public void loadSession(int arg7, String arg8, LoadSessionResponse arg9) {
            ContentDecryptionModuleLoadSessionParams v0 = new ContentDecryptionModuleLoadSessionParams();
            v0.sessionType = arg7;
            v0.sessionId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new ContentDecryptionModuleLoadSessionResponseParamsForwardToCallback(arg9));
        }

        public void removeSession(String arg8, RemoveSessionResponse arg9) {
            ContentDecryptionModuleRemoveSessionParams v0 = new ContentDecryptionModuleRemoveSessionParams();
            v0.sessionId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8, 1, 0)), new ContentDecryptionModuleRemoveSessionResponseParamsForwardToCallback(arg9));
        }

        public void setClient(ContentDecryptionModuleClient arg5) {
            ContentDecryptionModuleSetClientParams v0 = new ContentDecryptionModuleSetClientParams();
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void setServerCertificate(byte[] arg8, SetServerCertificateResponse arg9) {
            ContentDecryptionModuleSetServerCertificateParams v0 = new ContentDecryptionModuleSetServerCertificateParams();
            v0.certificateData = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new ContentDecryptionModuleSetServerCertificateResponseParamsForwardToCallback(arg9));
        }

        public void updateSession(String arg7, byte[] arg8, UpdateSessionResponse arg9) {
            ContentDecryptionModuleUpdateSessionParams v0 = new ContentDecryptionModuleUpdateSessionParams();
            v0.sessionId = arg7;
            v0.response = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6, 1, 0)), new ContentDecryptionModuleUpdateSessionResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ContentDecryptionModule arg2) {
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

                    this.getImpl().setClient(ContentDecryptionModuleSetClientParams.deserialize(v4_1.getPayload()).client);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ContentDecryptionModule_Internal.MANAGER, v4_1);
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

                int v3 = v1.getType();
                if(v3 == -1) {
                    goto label_98;
                }

                switch(v3) {
                    case 1: {
                        goto label_86;
                    }
                    case 2: {
                        goto label_76;
                    }
                    case 3: {
                        goto label_66;
                    }
                    case 4: {
                        goto label_54;
                    }
                    case 5: {
                        goto label_43;
                    }
                    case 6: {
                        goto label_32;
                    }
                    case 7: {
                        goto label_22;
                    }
                    case 8: {
                        goto label_12;
                    }
                }

                return 0;
            label_66:
                this.getImpl().getStatusForPolicy(ContentDecryptionModuleGetStatusForPolicyParams.deserialize(v11_1.getPayload()).minHdcpVersion, new ContentDecryptionModuleGetStatusForPolicyResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_86:
                ContentDecryptionModuleInitializeParams v11_2 = ContentDecryptionModuleInitializeParams.deserialize(v11_1.getPayload());
                this.getImpl().initialize(v11_2.keySystem, v11_2.securityOrigin, v11_2.cdmConfig, new ContentDecryptionModuleInitializeResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_54:
                ContentDecryptionModuleCreateSessionAndGenerateRequestParams v11_3 = ContentDecryptionModuleCreateSessionAndGenerateRequestParams.deserialize(v11_1.getPayload());
                this.getImpl().createSessionAndGenerateRequest(v11_3.sessionType, v11_3.initDataType, v11_3.initData, new ContentDecryptionModuleCreateSessionAndGenerateRequestResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_22:
                this.getImpl().closeSession(ContentDecryptionModuleCloseSessionParams.deserialize(v11_1.getPayload()).sessionId, new ContentDecryptionModuleCloseSessionResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_43:
                ContentDecryptionModuleLoadSessionParams v11_4 = ContentDecryptionModuleLoadSessionParams.deserialize(v11_1.getPayload());
                this.getImpl().loadSession(v11_4.sessionType, v11_4.sessionId, new ContentDecryptionModuleLoadSessionResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_76:
                this.getImpl().setServerCertificate(ContentDecryptionModuleSetServerCertificateParams.deserialize(v11_1.getPayload()).certificateData, new ContentDecryptionModuleSetServerCertificateResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_12:
                this.getImpl().removeSession(ContentDecryptionModuleRemoveSessionParams.deserialize(v11_1.getPayload()).sessionId, new ContentDecryptionModuleRemoveSessionResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_32:
                ContentDecryptionModuleUpdateSessionParams v11_5 = ContentDecryptionModuleUpdateSessionParams.deserialize(v11_1.getPayload());
                this.getImpl().updateSession(v11_5.sessionId, v11_5.response, new ContentDecryptionModuleUpdateSessionResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_98:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ContentDecryptionModule_Internal.MANAGER, v11_1, arg12);
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    private static final int CLOSE_SESSION_ORDINAL = 7;
    private static final int CREATE_SESSION_AND_GENERATE_REQUEST_ORDINAL = 4;
    private static final int GET_STATUS_FOR_POLICY_ORDINAL = 3;
    private static final int INITIALIZE_ORDINAL = 1;
    private static final int LOAD_SESSION_ORDINAL = 5;
    public static final Manager MANAGER = null;
    private static final int REMOVE_SESSION_ORDINAL = 8;
    private static final int SET_CLIENT_ORDINAL = 0;
    private static final int SET_SERVER_CERTIFICATE_ORDINAL = 2;
    private static final int UPDATE_SESSION_ORDINAL = 6;

    static {
        ContentDecryptionModule_Internal.MANAGER = new org.chromium.media.mojom.ContentDecryptionModule_Internal$1();
    }

    ContentDecryptionModule_Internal() {
        super();
    }
}

