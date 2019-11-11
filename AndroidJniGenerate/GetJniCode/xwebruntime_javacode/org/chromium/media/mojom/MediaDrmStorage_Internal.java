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
import org.chromium.mojo_base.mojom.UnguessableToken;

class MediaDrmStorage_Internal {
    final class org.chromium.media.mojom.MediaDrmStorage_Internal$1 extends Manager {
        org.chromium.media.mojom.MediaDrmStorage_Internal$1() {
            super();
        }

        public MediaDrmStorage[] buildArray(int arg1) {
            return new MediaDrmStorage[arg1];
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

        public Stub buildStub(Core arg2, MediaDrmStorage arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((MediaDrmStorage)arg2));
        }

        public String getName() {
            return "media::mojom::MediaDrmStorage";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class MediaDrmStorageInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            MediaDrmStorageInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            MediaDrmStorageInitializeParams.DEFAULT_STRUCT_INFO = MediaDrmStorageInitializeParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageInitializeParams() {
            this(0);
        }

        private MediaDrmStorageInitializeParams(int arg2) {
            super(8, arg2);
        }

        public static MediaDrmStorageInitializeParams decode(Decoder arg2) {
            MediaDrmStorageInitializeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageInitializeParams(arg2.readAndValidateDataHeader(MediaDrmStorageInitializeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageInitializeParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageInitializeParams deserialize(Message arg1) {
            return MediaDrmStorageInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(MediaDrmStorageInitializeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class MediaDrmStorageInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public UnguessableToken originId;

        static {
            MediaDrmStorageInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaDrmStorageInitializeResponseParams.DEFAULT_STRUCT_INFO = MediaDrmStorageInitializeResponseParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageInitializeResponseParams() {
            this(0);
        }

        private MediaDrmStorageInitializeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static MediaDrmStorageInitializeResponseParams decode(Decoder arg3) {
            MediaDrmStorageInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageInitializeResponseParams(arg3.readAndValidateDataHeader(MediaDrmStorageInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.originId = UnguessableToken.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageInitializeResponseParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageInitializeResponseParams deserialize(Message arg1) {
            return MediaDrmStorageInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaDrmStorageInitializeResponseParams.DEFAULT_STRUCT_INFO).encode(this.originId, 8, false);
        }
    }

    class MediaDrmStorageInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        MediaDrmStorageInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(MediaDrmStorageInitializeResponseParams.deserialize(v4.getPayload()).originId);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class MediaDrmStorageInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        MediaDrmStorageInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((UnguessableToken)arg1));
        }

        public void call(UnguessableToken arg7) {
            MediaDrmStorageInitializeResponseParams v0 = new MediaDrmStorageInitializeResponseParams();
            v0.originId = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class MediaDrmStorageLoadPersistentSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String sessionId;

        static {
            MediaDrmStorageLoadPersistentSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaDrmStorageLoadPersistentSessionParams.DEFAULT_STRUCT_INFO = MediaDrmStorageLoadPersistentSessionParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageLoadPersistentSessionParams() {
            this(0);
        }

        private MediaDrmStorageLoadPersistentSessionParams(int arg2) {
            super(16, arg2);
        }

        public static MediaDrmStorageLoadPersistentSessionParams decode(Decoder arg3) {
            MediaDrmStorageLoadPersistentSessionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageLoadPersistentSessionParams(arg3.readAndValidateDataHeader(MediaDrmStorageLoadPersistentSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageLoadPersistentSessionParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageLoadPersistentSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageLoadPersistentSessionParams deserialize(Message arg1) {
            return MediaDrmStorageLoadPersistentSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaDrmStorageLoadPersistentSessionParams.DEFAULT_STRUCT_INFO).encode(this.sessionId, 8, false);
        }
    }

    final class MediaDrmStorageLoadPersistentSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SessionData sessionData;

        static {
            MediaDrmStorageLoadPersistentSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaDrmStorageLoadPersistentSessionResponseParams.DEFAULT_STRUCT_INFO = MediaDrmStorageLoadPersistentSessionResponseParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageLoadPersistentSessionResponseParams() {
            this(0);
        }

        private MediaDrmStorageLoadPersistentSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static MediaDrmStorageLoadPersistentSessionResponseParams decode(Decoder arg3) {
            MediaDrmStorageLoadPersistentSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageLoadPersistentSessionResponseParams(arg3.readAndValidateDataHeader(MediaDrmStorageLoadPersistentSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionData = SessionData.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageLoadPersistentSessionResponseParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageLoadPersistentSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageLoadPersistentSessionResponseParams deserialize(Message arg1) {
            return MediaDrmStorageLoadPersistentSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaDrmStorageLoadPersistentSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.sessionData, 8, true);
        }
    }

    class MediaDrmStorageLoadPersistentSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final LoadPersistentSessionResponse mCallback;

        MediaDrmStorageLoadPersistentSessionResponseParamsForwardToCallback(LoadPersistentSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(MediaDrmStorageLoadPersistentSessionResponseParams.deserialize(v5.getPayload()).sessionData);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class MediaDrmStorageLoadPersistentSessionResponseParamsProxyToResponder implements LoadPersistentSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        MediaDrmStorageLoadPersistentSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((SessionData)arg1));
        }

        public void call(SessionData arg7) {
            MediaDrmStorageLoadPersistentSessionResponseParams v0 = new MediaDrmStorageLoadPersistentSessionResponseParams();
            v0.sessionData = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    final class MediaDrmStorageOnProvisionedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            MediaDrmStorageOnProvisionedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            MediaDrmStorageOnProvisionedParams.DEFAULT_STRUCT_INFO = MediaDrmStorageOnProvisionedParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageOnProvisionedParams() {
            this(0);
        }

        private MediaDrmStorageOnProvisionedParams(int arg2) {
            super(8, arg2);
        }

        public static MediaDrmStorageOnProvisionedParams decode(Decoder arg2) {
            MediaDrmStorageOnProvisionedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageOnProvisionedParams(arg2.readAndValidateDataHeader(MediaDrmStorageOnProvisionedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageOnProvisionedParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageOnProvisionedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageOnProvisionedParams deserialize(Message arg1) {
            return MediaDrmStorageOnProvisionedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(MediaDrmStorageOnProvisionedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class MediaDrmStorageOnProvisionedResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            MediaDrmStorageOnProvisionedResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaDrmStorageOnProvisionedResponseParams.DEFAULT_STRUCT_INFO = MediaDrmStorageOnProvisionedResponseParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageOnProvisionedResponseParams() {
            this(0);
        }

        private MediaDrmStorageOnProvisionedResponseParams(int arg2) {
            super(16, arg2);
        }

        public static MediaDrmStorageOnProvisionedResponseParams decode(Decoder arg3) {
            MediaDrmStorageOnProvisionedResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageOnProvisionedResponseParams(arg3.readAndValidateDataHeader(MediaDrmStorageOnProvisionedResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageOnProvisionedResponseParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageOnProvisionedResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageOnProvisionedResponseParams deserialize(Message arg1) {
            return MediaDrmStorageOnProvisionedResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaDrmStorageOnProvisionedResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class MediaDrmStorageOnProvisionedResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OnProvisionedResponse mCallback;

        MediaDrmStorageOnProvisionedResponseParamsForwardToCallback(OnProvisionedResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(MediaDrmStorageOnProvisionedResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class MediaDrmStorageOnProvisionedResponseParamsProxyToResponder implements OnProvisionedResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        MediaDrmStorageOnProvisionedResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            MediaDrmStorageOnProvisionedResponseParams v0 = new MediaDrmStorageOnProvisionedResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class MediaDrmStorageRemovePersistentSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String sessionId;

        static {
            MediaDrmStorageRemovePersistentSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaDrmStorageRemovePersistentSessionParams.DEFAULT_STRUCT_INFO = MediaDrmStorageRemovePersistentSessionParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageRemovePersistentSessionParams() {
            this(0);
        }

        private MediaDrmStorageRemovePersistentSessionParams(int arg2) {
            super(16, arg2);
        }

        public static MediaDrmStorageRemovePersistentSessionParams decode(Decoder arg3) {
            MediaDrmStorageRemovePersistentSessionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageRemovePersistentSessionParams(arg3.readAndValidateDataHeader(MediaDrmStorageRemovePersistentSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageRemovePersistentSessionParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageRemovePersistentSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageRemovePersistentSessionParams deserialize(Message arg1) {
            return MediaDrmStorageRemovePersistentSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaDrmStorageRemovePersistentSessionParams.DEFAULT_STRUCT_INFO).encode(this.sessionId, 8, false);
        }
    }

    final class MediaDrmStorageRemovePersistentSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            MediaDrmStorageRemovePersistentSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaDrmStorageRemovePersistentSessionResponseParams.DEFAULT_STRUCT_INFO = MediaDrmStorageRemovePersistentSessionResponseParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageRemovePersistentSessionResponseParams() {
            this(0);
        }

        private MediaDrmStorageRemovePersistentSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static MediaDrmStorageRemovePersistentSessionResponseParams decode(Decoder arg3) {
            MediaDrmStorageRemovePersistentSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageRemovePersistentSessionResponseParams(arg3.readAndValidateDataHeader(MediaDrmStorageRemovePersistentSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageRemovePersistentSessionResponseParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageRemovePersistentSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageRemovePersistentSessionResponseParams deserialize(Message arg1) {
            return MediaDrmStorageRemovePersistentSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaDrmStorageRemovePersistentSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class MediaDrmStorageRemovePersistentSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RemovePersistentSessionResponse mCallback;

        MediaDrmStorageRemovePersistentSessionResponseParamsForwardToCallback(RemovePersistentSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(MediaDrmStorageRemovePersistentSessionResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class MediaDrmStorageRemovePersistentSessionResponseParamsProxyToResponder implements RemovePersistentSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        MediaDrmStorageRemovePersistentSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            MediaDrmStorageRemovePersistentSessionResponseParams v0 = new MediaDrmStorageRemovePersistentSessionResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class MediaDrmStorageSavePersistentSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public SessionData sessionData;
        public String sessionId;

        static {
            MediaDrmStorageSavePersistentSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            MediaDrmStorageSavePersistentSessionParams.DEFAULT_STRUCT_INFO = MediaDrmStorageSavePersistentSessionParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageSavePersistentSessionParams() {
            this(0);
        }

        private MediaDrmStorageSavePersistentSessionParams(int arg2) {
            super(24, arg2);
        }

        public static MediaDrmStorageSavePersistentSessionParams decode(Decoder arg3) {
            MediaDrmStorageSavePersistentSessionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageSavePersistentSessionParams(arg3.readAndValidateDataHeader(MediaDrmStorageSavePersistentSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.sessionId = arg3.readString(8, false);
                v1.sessionData = SessionData.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageSavePersistentSessionParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageSavePersistentSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageSavePersistentSessionParams deserialize(Message arg1) {
            return MediaDrmStorageSavePersistentSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(MediaDrmStorageSavePersistentSessionParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.sessionId, 8, false);
            arg4.encode(this.sessionData, 16, false);
        }
    }

    final class MediaDrmStorageSavePersistentSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            MediaDrmStorageSavePersistentSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            MediaDrmStorageSavePersistentSessionResponseParams.DEFAULT_STRUCT_INFO = MediaDrmStorageSavePersistentSessionResponseParams.VERSION_ARRAY[0];
        }

        public MediaDrmStorageSavePersistentSessionResponseParams() {
            this(0);
        }

        private MediaDrmStorageSavePersistentSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static MediaDrmStorageSavePersistentSessionResponseParams decode(Decoder arg3) {
            MediaDrmStorageSavePersistentSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new MediaDrmStorageSavePersistentSessionResponseParams(arg3.readAndValidateDataHeader(MediaDrmStorageSavePersistentSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static MediaDrmStorageSavePersistentSessionResponseParams deserialize(ByteBuffer arg2) {
            return MediaDrmStorageSavePersistentSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static MediaDrmStorageSavePersistentSessionResponseParams deserialize(Message arg1) {
            return MediaDrmStorageSavePersistentSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(MediaDrmStorageSavePersistentSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class MediaDrmStorageSavePersistentSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SavePersistentSessionResponse mCallback;

        MediaDrmStorageSavePersistentSessionResponseParamsForwardToCallback(SavePersistentSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(MediaDrmStorageSavePersistentSessionResponseParams.deserialize(v4.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class MediaDrmStorageSavePersistentSessionResponseParamsProxyToResponder implements SavePersistentSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        MediaDrmStorageSavePersistentSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg6) {
            MediaDrmStorageSavePersistentSessionResponseParams v0 = new MediaDrmStorageSavePersistentSessionResponseParams();
            v0.success = arg6.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.MediaDrmStorage$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void initialize(InitializeResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new MediaDrmStorageInitializeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new MediaDrmStorageInitializeResponseParamsForwardToCallback(arg9));
        }

        public void loadPersistentSession(String arg8, LoadPersistentSessionResponse arg9) {
            MediaDrmStorageLoadPersistentSessionParams v0 = new MediaDrmStorageLoadPersistentSessionParams();
            v0.sessionId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new MediaDrmStorageLoadPersistentSessionResponseParamsForwardToCallback(arg9));
        }

        public void onProvisioned(OnProvisionedResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new MediaDrmStorageOnProvisionedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new MediaDrmStorageOnProvisionedResponseParamsForwardToCallback(arg8));
        }

        public void removePersistentSession(String arg8, RemovePersistentSessionResponse arg9) {
            MediaDrmStorageRemovePersistentSessionParams v0 = new MediaDrmStorageRemovePersistentSessionParams();
            v0.sessionId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new MediaDrmStorageRemovePersistentSessionResponseParamsForwardToCallback(arg9));
        }

        public void savePersistentSession(String arg7, SessionData arg8, SavePersistentSessionResponse arg9) {
            MediaDrmStorageSavePersistentSessionParams v0 = new MediaDrmStorageSavePersistentSessionParams();
            v0.sessionId = arg7;
            v0.sessionData = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new MediaDrmStorageSavePersistentSessionResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, MediaDrmStorage arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(MediaDrmStorage_Internal.MANAGER, v4_1);
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
                        goto label_59;
                    }
                    case 0: {
                        goto label_50;
                    }
                    case 1: {
                        goto label_41;
                    }
                    case 2: {
                        goto label_30;
                    }
                    case 3: {
                        goto label_20;
                    }
                    case 4: {
                        goto label_10;
                    }
                }

                return 0;
            label_50:
                MediaDrmStorageInitializeParams.deserialize(v10_1.getPayload());
                this.getImpl().initialize(new MediaDrmStorageInitializeResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_20:
                this.getImpl().loadPersistentSession(MediaDrmStorageLoadPersistentSessionParams.deserialize(v10_1.getPayload()).sessionId, new MediaDrmStorageLoadPersistentSessionResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_41:
                MediaDrmStorageOnProvisionedParams.deserialize(v10_1.getPayload());
                this.getImpl().onProvisioned(new MediaDrmStorageOnProvisionedResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_10:
                this.getImpl().removePersistentSession(MediaDrmStorageRemovePersistentSessionParams.deserialize(v10_1.getPayload()).sessionId, new MediaDrmStorageRemovePersistentSessionResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_59:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), MediaDrmStorage_Internal.MANAGER, v10_1, arg11);
            label_30:
                MediaDrmStorageSavePersistentSessionParams v10_2 = MediaDrmStorageSavePersistentSessionParams.deserialize(v10_1.getPayload());
                this.getImpl().savePersistentSession(v10_2.sessionId, v10_2.sessionData, new MediaDrmStorageSavePersistentSessionResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int INITIALIZE_ORDINAL = 0;
    private static final int LOAD_PERSISTENT_SESSION_ORDINAL = 3;
    public static final Manager MANAGER = null;
    private static final int ON_PROVISIONED_ORDINAL = 1;
    private static final int REMOVE_PERSISTENT_SESSION_ORDINAL = 4;
    private static final int SAVE_PERSISTENT_SESSION_ORDINAL = 2;

    static {
        MediaDrmStorage_Internal.MANAGER = new org.chromium.media.mojom.MediaDrmStorage_Internal$1();
    }

    MediaDrmStorage_Internal() {
        super();
    }
}

