package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
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

class Fingerprint_Internal {
    final class org.chromium.device.mojom.Fingerprint_Internal$1 extends Manager {
        org.chromium.device.mojom.Fingerprint_Internal$1() {
            super();
        }

        public Fingerprint[] buildArray(int arg1) {
            return new Fingerprint[arg1];
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

        public Stub buildStub(Core arg2, Fingerprint arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Fingerprint)arg2));
        }

        public String getName() {
            return "device::mojom::Fingerprint";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class FingerprintAddFingerprintObserverParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public FingerprintObserver observer;

        static {
            FingerprintAddFingerprintObserverParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintAddFingerprintObserverParams.DEFAULT_STRUCT_INFO = FingerprintAddFingerprintObserverParams.VERSION_ARRAY[0];
        }

        public FingerprintAddFingerprintObserverParams() {
            this(0);
        }

        private FingerprintAddFingerprintObserverParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintAddFingerprintObserverParams decode(Decoder arg4) {
            FingerprintAddFingerprintObserverParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new FingerprintAddFingerprintObserverParams(arg4.readAndValidateDataHeader(FingerprintAddFingerprintObserverParams.VERSION_ARRAY).elementsOrVersion);
                v1.observer = arg4.readServiceInterface(8, false, FingerprintObserver.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static FingerprintAddFingerprintObserverParams deserialize(ByteBuffer arg2) {
            return FingerprintAddFingerprintObserverParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintAddFingerprintObserverParams deserialize(Message arg1) {
            return FingerprintAddFingerprintObserverParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(FingerprintAddFingerprintObserverParams.DEFAULT_STRUCT_INFO).encode(this.observer, 8, false, FingerprintObserver.MANAGER);
        }
    }

    final class FingerprintCancelCurrentEnrollSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            FingerprintCancelCurrentEnrollSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            FingerprintCancelCurrentEnrollSessionParams.DEFAULT_STRUCT_INFO = FingerprintCancelCurrentEnrollSessionParams.VERSION_ARRAY[0];
        }

        public FingerprintCancelCurrentEnrollSessionParams() {
            this(0);
        }

        private FingerprintCancelCurrentEnrollSessionParams(int arg2) {
            super(8, arg2);
        }

        public static FingerprintCancelCurrentEnrollSessionParams decode(Decoder arg2) {
            FingerprintCancelCurrentEnrollSessionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintCancelCurrentEnrollSessionParams(arg2.readAndValidateDataHeader(FingerprintCancelCurrentEnrollSessionParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintCancelCurrentEnrollSessionParams deserialize(ByteBuffer arg2) {
            return FingerprintCancelCurrentEnrollSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintCancelCurrentEnrollSessionParams deserialize(Message arg1) {
            return FingerprintCancelCurrentEnrollSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(FingerprintCancelCurrentEnrollSessionParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class FingerprintCancelCurrentEnrollSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            FingerprintCancelCurrentEnrollSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintCancelCurrentEnrollSessionResponseParams.DEFAULT_STRUCT_INFO = FingerprintCancelCurrentEnrollSessionResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintCancelCurrentEnrollSessionResponseParams() {
            this(0);
        }

        private FingerprintCancelCurrentEnrollSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintCancelCurrentEnrollSessionResponseParams decode(Decoder arg3) {
            FingerprintCancelCurrentEnrollSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintCancelCurrentEnrollSessionResponseParams(arg3.readAndValidateDataHeader(FingerprintCancelCurrentEnrollSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintCancelCurrentEnrollSessionResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintCancelCurrentEnrollSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintCancelCurrentEnrollSessionResponseParams deserialize(Message arg1) {
            return FingerprintCancelCurrentEnrollSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintCancelCurrentEnrollSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class FingerprintCancelCurrentEnrollSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CancelCurrentEnrollSessionResponse mCallback;

        FingerprintCancelCurrentEnrollSessionResponseParamsForwardToCallback(CancelCurrentEnrollSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(FingerprintCancelCurrentEnrollSessionResponseParams.deserialize(v4.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintCancelCurrentEnrollSessionResponseParamsProxyToResponder implements CancelCurrentEnrollSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintCancelCurrentEnrollSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg6) {
            FingerprintCancelCurrentEnrollSessionResponseParams v0 = new FingerprintCancelCurrentEnrollSessionResponseParams();
            v0.success = arg6.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class FingerprintDestroyAllRecordsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            FingerprintDestroyAllRecordsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            FingerprintDestroyAllRecordsParams.DEFAULT_STRUCT_INFO = FingerprintDestroyAllRecordsParams.VERSION_ARRAY[0];
        }

        public FingerprintDestroyAllRecordsParams() {
            this(0);
        }

        private FingerprintDestroyAllRecordsParams(int arg2) {
            super(8, arg2);
        }

        public static FingerprintDestroyAllRecordsParams decode(Decoder arg2) {
            FingerprintDestroyAllRecordsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintDestroyAllRecordsParams(arg2.readAndValidateDataHeader(FingerprintDestroyAllRecordsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintDestroyAllRecordsParams deserialize(ByteBuffer arg2) {
            return FingerprintDestroyAllRecordsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintDestroyAllRecordsParams deserialize(Message arg1) {
            return FingerprintDestroyAllRecordsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(FingerprintDestroyAllRecordsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class FingerprintDestroyAllRecordsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            FingerprintDestroyAllRecordsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintDestroyAllRecordsResponseParams.DEFAULT_STRUCT_INFO = FingerprintDestroyAllRecordsResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintDestroyAllRecordsResponseParams() {
            this(0);
        }

        private FingerprintDestroyAllRecordsResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintDestroyAllRecordsResponseParams decode(Decoder arg3) {
            FingerprintDestroyAllRecordsResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintDestroyAllRecordsResponseParams(arg3.readAndValidateDataHeader(FingerprintDestroyAllRecordsResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintDestroyAllRecordsResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintDestroyAllRecordsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintDestroyAllRecordsResponseParams deserialize(Message arg1) {
            return FingerprintDestroyAllRecordsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintDestroyAllRecordsResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class FingerprintDestroyAllRecordsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DestroyAllRecordsResponse mCallback;

        FingerprintDestroyAllRecordsResponseParamsForwardToCallback(DestroyAllRecordsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(8, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(FingerprintDestroyAllRecordsResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintDestroyAllRecordsResponseParamsProxyToResponder implements DestroyAllRecordsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintDestroyAllRecordsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            FingerprintDestroyAllRecordsResponseParams v0 = new FingerprintDestroyAllRecordsResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(8, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class FingerprintEndCurrentAuthSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            FingerprintEndCurrentAuthSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            FingerprintEndCurrentAuthSessionParams.DEFAULT_STRUCT_INFO = FingerprintEndCurrentAuthSessionParams.VERSION_ARRAY[0];
        }

        public FingerprintEndCurrentAuthSessionParams() {
            this(0);
        }

        private FingerprintEndCurrentAuthSessionParams(int arg2) {
            super(8, arg2);
        }

        public static FingerprintEndCurrentAuthSessionParams decode(Decoder arg2) {
            FingerprintEndCurrentAuthSessionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintEndCurrentAuthSessionParams(arg2.readAndValidateDataHeader(FingerprintEndCurrentAuthSessionParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintEndCurrentAuthSessionParams deserialize(ByteBuffer arg2) {
            return FingerprintEndCurrentAuthSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintEndCurrentAuthSessionParams deserialize(Message arg1) {
            return FingerprintEndCurrentAuthSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(FingerprintEndCurrentAuthSessionParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class FingerprintEndCurrentAuthSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            FingerprintEndCurrentAuthSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintEndCurrentAuthSessionResponseParams.DEFAULT_STRUCT_INFO = FingerprintEndCurrentAuthSessionResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintEndCurrentAuthSessionResponseParams() {
            this(0);
        }

        private FingerprintEndCurrentAuthSessionResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintEndCurrentAuthSessionResponseParams decode(Decoder arg3) {
            FingerprintEndCurrentAuthSessionResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintEndCurrentAuthSessionResponseParams(arg3.readAndValidateDataHeader(FingerprintEndCurrentAuthSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintEndCurrentAuthSessionResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintEndCurrentAuthSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintEndCurrentAuthSessionResponseParams deserialize(Message arg1) {
            return FingerprintEndCurrentAuthSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintEndCurrentAuthSessionResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class FingerprintEndCurrentAuthSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final EndCurrentAuthSessionResponse mCallback;

        FingerprintEndCurrentAuthSessionResponseParamsForwardToCallback(EndCurrentAuthSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(7, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(FingerprintEndCurrentAuthSessionResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintEndCurrentAuthSessionResponseParamsProxyToResponder implements EndCurrentAuthSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintEndCurrentAuthSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            FingerprintEndCurrentAuthSessionResponseParams v0 = new FingerprintEndCurrentAuthSessionResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(7, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class FingerprintGetRecordsForUserParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String userId;

        static {
            FingerprintGetRecordsForUserParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintGetRecordsForUserParams.DEFAULT_STRUCT_INFO = FingerprintGetRecordsForUserParams.VERSION_ARRAY[0];
        }

        public FingerprintGetRecordsForUserParams() {
            this(0);
        }

        private FingerprintGetRecordsForUserParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintGetRecordsForUserParams decode(Decoder arg3) {
            FingerprintGetRecordsForUserParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintGetRecordsForUserParams(arg3.readAndValidateDataHeader(FingerprintGetRecordsForUserParams.VERSION_ARRAY).elementsOrVersion);
                v1.userId = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintGetRecordsForUserParams deserialize(ByteBuffer arg2) {
            return FingerprintGetRecordsForUserParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintGetRecordsForUserParams deserialize(Message arg1) {
            return FingerprintGetRecordsForUserParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintGetRecordsForUserParams.DEFAULT_STRUCT_INFO).encode(this.userId, 8, false);
        }
    }

    final class FingerprintGetRecordsForUserResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Map records;

        static {
            FingerprintGetRecordsForUserResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintGetRecordsForUserResponseParams.DEFAULT_STRUCT_INFO = FingerprintGetRecordsForUserResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintGetRecordsForUserResponseParams() {
            this(0);
        }

        private FingerprintGetRecordsForUserResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintGetRecordsForUserResponseParams decode(Decoder arg9) {
            FingerprintGetRecordsForUserResponseParams v1;
            if(arg9 == null) {
                return null;
            }

            arg9.increaseStackDepth();
            try {
                v1 = new FingerprintGetRecordsForUserResponseParams(arg9.readAndValidateDataHeader(FingerprintGetRecordsForUserResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                int v2 = 0;
                Decoder v3 = arg9.readPointer(v0_1, false);
                v3.readDataHeaderForMap();
                Decoder v4 = v3.readPointer(v0_1, false);
                DataHeader v5 = v4.readDataHeaderForPointerArray(-1);
                String[] v6 = new String[v5.elementsOrVersion];
                int v7;
                for(v7 = 0; v7 < v5.elementsOrVersion; ++v7) {
                    v6[v7] = v4.readString(v7 * 8 + v0_1, false);
                }

                v3 = v3.readPointer(16, false);
                DataHeader v4_1 = v3.readDataHeaderForPointerArray(v6.length);
                String[] v5_1 = new String[v4_1.elementsOrVersion];
                for(v7 = 0; v7 < v4_1.elementsOrVersion; ++v7) {
                    v5_1[v7] = v3.readString(v7 * 8 + v0_1, false);
                }

                v1.records = new HashMap();
                while(v2 < v6.length) {
                    v1.records.put(v6[v2], v5_1[v2]);
                    ++v2;
                }
            }
            catch(Throwable v0) {
                goto label_56;
            }

            arg9.decreaseStackDepth();
            return v1;
        label_56:
            arg9.decreaseStackDepth();
            throw v0;
        }

        public static FingerprintGetRecordsForUserResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintGetRecordsForUserResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintGetRecordsForUserResponseParams deserialize(Message arg1) {
            return FingerprintGetRecordsForUserResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg10) {
            arg10 = arg10.getEncoderAtDataOffset(FingerprintGetRecordsForUserResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.records == null) {
                arg10.encodeNullPointer(v2, false);
            }
            else {
                arg10 = arg10.encoderForMap(v2);
                int v0 = this.records.size();
                String[] v3 = new String[v0];
                String[] v0_1 = new String[v0];
                Iterator v4 = this.records.entrySet().iterator();
                int v5;
                for(v5 = 0; v4.hasNext(); ++v5) {
                    Object v6 = v4.next();
                    v3[v5] = ((Map$Entry)v6).getKey();
                    v0_1[v5] = ((Map$Entry)v6).getValue();
                }

                v5 = -1;
                Encoder v4_1 = arg10.encodePointerArray(v3.length, v2, v5);
                int v6_1;
                for(v6_1 = 0; v6_1 < v3.length; ++v6_1) {
                    v4_1.encode(v3[v6_1], v6_1 * 8 + v2, false);
                }

                arg10 = arg10.encodePointerArray(v0_1.length, 16, v5);
                int v3_1;
                for(v3_1 = 0; v3_1 < v0_1.length; ++v3_1) {
                    arg10.encode(v0_1[v3_1], v3_1 * 8 + v2, false);
                }
            }
        }
    }

    class FingerprintGetRecordsForUserResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetRecordsForUserResponse mCallback;

        FingerprintGetRecordsForUserResponseParamsForwardToCallback(GetRecordsForUserResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(FingerprintGetRecordsForUserResponseParams.deserialize(v4.getPayload()).records);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintGetRecordsForUserResponseParamsProxyToResponder implements GetRecordsForUserResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintGetRecordsForUserResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((Map)arg1));
        }

        public void call(Map arg7) {
            FingerprintGetRecordsForUserResponseParams v0 = new FingerprintGetRecordsForUserResponseParams();
            v0.records = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class FingerprintRemoveRecordParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String recordPath;

        static {
            FingerprintRemoveRecordParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintRemoveRecordParams.DEFAULT_STRUCT_INFO = FingerprintRemoveRecordParams.VERSION_ARRAY[0];
        }

        public FingerprintRemoveRecordParams() {
            this(0);
        }

        private FingerprintRemoveRecordParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintRemoveRecordParams decode(Decoder arg3) {
            FingerprintRemoveRecordParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintRemoveRecordParams(arg3.readAndValidateDataHeader(FingerprintRemoveRecordParams.VERSION_ARRAY).elementsOrVersion);
                v1.recordPath = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintRemoveRecordParams deserialize(ByteBuffer arg2) {
            return FingerprintRemoveRecordParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintRemoveRecordParams deserialize(Message arg1) {
            return FingerprintRemoveRecordParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintRemoveRecordParams.DEFAULT_STRUCT_INFO).encode(this.recordPath, 8, false);
        }
    }

    final class FingerprintRemoveRecordResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            FingerprintRemoveRecordResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintRemoveRecordResponseParams.DEFAULT_STRUCT_INFO = FingerprintRemoveRecordResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintRemoveRecordResponseParams() {
            this(0);
        }

        private FingerprintRemoveRecordResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintRemoveRecordResponseParams decode(Decoder arg3) {
            FingerprintRemoveRecordResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintRemoveRecordResponseParams(arg3.readAndValidateDataHeader(FingerprintRemoveRecordResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintRemoveRecordResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintRemoveRecordResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintRemoveRecordResponseParams deserialize(Message arg1) {
            return FingerprintRemoveRecordResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintRemoveRecordResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class FingerprintRemoveRecordResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RemoveRecordResponse mCallback;

        FingerprintRemoveRecordResponseParamsForwardToCallback(RemoveRecordResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(FingerprintRemoveRecordResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintRemoveRecordResponseParamsProxyToResponder implements RemoveRecordResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintRemoveRecordResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            FingerprintRemoveRecordResponseParams v0 = new FingerprintRemoveRecordResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class FingerprintRequestRecordLabelParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String recordPath;

        static {
            FingerprintRequestRecordLabelParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintRequestRecordLabelParams.DEFAULT_STRUCT_INFO = FingerprintRequestRecordLabelParams.VERSION_ARRAY[0];
        }

        public FingerprintRequestRecordLabelParams() {
            this(0);
        }

        private FingerprintRequestRecordLabelParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintRequestRecordLabelParams decode(Decoder arg3) {
            FingerprintRequestRecordLabelParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintRequestRecordLabelParams(arg3.readAndValidateDataHeader(FingerprintRequestRecordLabelParams.VERSION_ARRAY).elementsOrVersion);
                v1.recordPath = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintRequestRecordLabelParams deserialize(ByteBuffer arg2) {
            return FingerprintRequestRecordLabelParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintRequestRecordLabelParams deserialize(Message arg1) {
            return FingerprintRequestRecordLabelParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintRequestRecordLabelParams.DEFAULT_STRUCT_INFO).encode(this.recordPath, 8, false);
        }
    }

    final class FingerprintRequestRecordLabelResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String label;

        static {
            FingerprintRequestRecordLabelResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintRequestRecordLabelResponseParams.DEFAULT_STRUCT_INFO = FingerprintRequestRecordLabelResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintRequestRecordLabelResponseParams() {
            this(0);
        }

        private FingerprintRequestRecordLabelResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintRequestRecordLabelResponseParams decode(Decoder arg3) {
            FingerprintRequestRecordLabelResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintRequestRecordLabelResponseParams(arg3.readAndValidateDataHeader(FingerprintRequestRecordLabelResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.label = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintRequestRecordLabelResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintRequestRecordLabelResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintRequestRecordLabelResponseParams deserialize(Message arg1) {
            return FingerprintRequestRecordLabelResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintRequestRecordLabelResponseParams.DEFAULT_STRUCT_INFO).encode(this.label, 8, false);
        }
    }

    class FingerprintRequestRecordLabelResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RequestRecordLabelResponse mCallback;

        FingerprintRequestRecordLabelResponseParamsForwardToCallback(RequestRecordLabelResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(FingerprintRequestRecordLabelResponseParams.deserialize(v5.getPayload()).label);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintRequestRecordLabelResponseParamsProxyToResponder implements RequestRecordLabelResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintRequestRecordLabelResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((String)arg1));
        }

        public void call(String arg7) {
            FingerprintRequestRecordLabelResponseParams v0 = new FingerprintRequestRecordLabelResponseParams();
            v0.label = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    final class FingerprintRequestTypeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            FingerprintRequestTypeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            FingerprintRequestTypeParams.DEFAULT_STRUCT_INFO = FingerprintRequestTypeParams.VERSION_ARRAY[0];
        }

        public FingerprintRequestTypeParams() {
            this(0);
        }

        private FingerprintRequestTypeParams(int arg2) {
            super(8, arg2);
        }

        public static FingerprintRequestTypeParams decode(Decoder arg2) {
            FingerprintRequestTypeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintRequestTypeParams(arg2.readAndValidateDataHeader(FingerprintRequestTypeParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintRequestTypeParams deserialize(ByteBuffer arg2) {
            return FingerprintRequestTypeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintRequestTypeParams deserialize(Message arg1) {
            return FingerprintRequestTypeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(FingerprintRequestTypeParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class FingerprintRequestTypeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int type;

        static {
            FingerprintRequestTypeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintRequestTypeResponseParams.DEFAULT_STRUCT_INFO = FingerprintRequestTypeResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintRequestTypeResponseParams() {
            this(0);
        }

        private FingerprintRequestTypeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintRequestTypeResponseParams decode(Decoder arg2) {
            FingerprintRequestTypeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintRequestTypeResponseParams(arg2.readAndValidateDataHeader(FingerprintRequestTypeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintRequestTypeResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintRequestTypeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintRequestTypeResponseParams deserialize(Message arg1) {
            return FingerprintRequestTypeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(FingerprintRequestTypeResponseParams.DEFAULT_STRUCT_INFO).encode(this.type, 8);
        }
    }

    class FingerprintRequestTypeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final RequestTypeResponse mCallback;

        FingerprintRequestTypeResponseParamsForwardToCallback(RequestTypeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(10, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(FingerprintRequestTypeResponseParams.deserialize(v5.getPayload()).type));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintRequestTypeResponseParamsProxyToResponder implements RequestTypeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintRequestTypeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            FingerprintRequestTypeResponseParams v0 = new FingerprintRequestTypeResponseParams();
            v0.type = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(10, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class FingerprintSetRecordLabelParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String newLabel;
        public String recordPath;

        static {
            FingerprintSetRecordLabelParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            FingerprintSetRecordLabelParams.DEFAULT_STRUCT_INFO = FingerprintSetRecordLabelParams.VERSION_ARRAY[0];
        }

        public FingerprintSetRecordLabelParams() {
            this(0);
        }

        private FingerprintSetRecordLabelParams(int arg2) {
            super(24, arg2);
        }

        public static FingerprintSetRecordLabelParams decode(Decoder arg3) {
            FingerprintSetRecordLabelParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintSetRecordLabelParams(arg3.readAndValidateDataHeader(FingerprintSetRecordLabelParams.VERSION_ARRAY).elementsOrVersion);
                v1.recordPath = arg3.readString(8, false);
                v1.newLabel = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintSetRecordLabelParams deserialize(ByteBuffer arg2) {
            return FingerprintSetRecordLabelParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintSetRecordLabelParams deserialize(Message arg1) {
            return FingerprintSetRecordLabelParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(FingerprintSetRecordLabelParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.recordPath, 8, false);
            arg4.encode(this.newLabel, 16, false);
        }
    }

    final class FingerprintSetRecordLabelResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            FingerprintSetRecordLabelResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            FingerprintSetRecordLabelResponseParams.DEFAULT_STRUCT_INFO = FingerprintSetRecordLabelResponseParams.VERSION_ARRAY[0];
        }

        public FingerprintSetRecordLabelResponseParams() {
            this(0);
        }

        private FingerprintSetRecordLabelResponseParams(int arg2) {
            super(16, arg2);
        }

        public static FingerprintSetRecordLabelResponseParams decode(Decoder arg3) {
            FingerprintSetRecordLabelResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintSetRecordLabelResponseParams(arg3.readAndValidateDataHeader(FingerprintSetRecordLabelResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintSetRecordLabelResponseParams deserialize(ByteBuffer arg2) {
            return FingerprintSetRecordLabelResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintSetRecordLabelResponseParams deserialize(Message arg1) {
            return FingerprintSetRecordLabelResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(FingerprintSetRecordLabelResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class FingerprintSetRecordLabelResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetRecordLabelResponse mCallback;

        FingerprintSetRecordLabelResponseParamsForwardToCallback(SetRecordLabelResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(FingerprintSetRecordLabelResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class FingerprintSetRecordLabelResponseParamsProxyToResponder implements SetRecordLabelResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        FingerprintSetRecordLabelResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            FingerprintSetRecordLabelResponseParams v0 = new FingerprintSetRecordLabelResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class FingerprintStartAuthSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            FingerprintStartAuthSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            FingerprintStartAuthSessionParams.DEFAULT_STRUCT_INFO = FingerprintStartAuthSessionParams.VERSION_ARRAY[0];
        }

        public FingerprintStartAuthSessionParams() {
            this(0);
        }

        private FingerprintStartAuthSessionParams(int arg2) {
            super(8, arg2);
        }

        public static FingerprintStartAuthSessionParams decode(Decoder arg2) {
            FingerprintStartAuthSessionParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintStartAuthSessionParams(arg2.readAndValidateDataHeader(FingerprintStartAuthSessionParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintStartAuthSessionParams deserialize(ByteBuffer arg2) {
            return FingerprintStartAuthSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintStartAuthSessionParams deserialize(Message arg1) {
            return FingerprintStartAuthSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(FingerprintStartAuthSessionParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class FingerprintStartEnrollSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public String label;
        public String userId;

        static {
            FingerprintStartEnrollSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            FingerprintStartEnrollSessionParams.DEFAULT_STRUCT_INFO = FingerprintStartEnrollSessionParams.VERSION_ARRAY[0];
        }

        public FingerprintStartEnrollSessionParams() {
            this(0);
        }

        private FingerprintStartEnrollSessionParams(int arg2) {
            super(24, arg2);
        }

        public static FingerprintStartEnrollSessionParams decode(Decoder arg3) {
            FingerprintStartEnrollSessionParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintStartEnrollSessionParams(arg3.readAndValidateDataHeader(FingerprintStartEnrollSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.userId = arg3.readString(8, false);
                v1.label = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintStartEnrollSessionParams deserialize(ByteBuffer arg2) {
            return FingerprintStartEnrollSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintStartEnrollSessionParams deserialize(Message arg1) {
            return FingerprintStartEnrollSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(FingerprintStartEnrollSessionParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.userId, 8, false);
            arg4.encode(this.label, 16, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.Fingerprint$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addFingerprintObserver(FingerprintObserver arg5) {
            FingerprintAddFingerprintObserverParams v0 = new FingerprintAddFingerprintObserverParams();
            v0.observer = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(9)));
        }

        public void cancelCurrentEnrollSession(CancelCurrentEnrollSessionResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new FingerprintCancelCurrentEnrollSessionParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new FingerprintCancelCurrentEnrollSessionResponseParamsForwardToCallback(arg9));
        }

        public void destroyAllRecords(DestroyAllRecordsResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new FingerprintDestroyAllRecordsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8, 1, 0)), new FingerprintDestroyAllRecordsResponseParamsForwardToCallback(arg9));
        }

        public void endCurrentAuthSession(EndCurrentAuthSessionResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new FingerprintEndCurrentAuthSessionParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7, 1, 0)), new FingerprintEndCurrentAuthSessionResponseParamsForwardToCallback(arg9));
        }

        public void getRecordsForUser(String arg8, GetRecordsForUserResponse arg9) {
            FingerprintGetRecordsForUserParams v0 = new FingerprintGetRecordsForUserParams();
            v0.userId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new FingerprintGetRecordsForUserResponseParamsForwardToCallback(arg9));
        }

        public void removeRecord(String arg8, RemoveRecordResponse arg9) {
            FingerprintRemoveRecordParams v0 = new FingerprintRemoveRecordParams();
            v0.recordPath = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new FingerprintRemoveRecordResponseParamsForwardToCallback(arg9));
        }

        public void requestRecordLabel(String arg8, RequestRecordLabelResponse arg9) {
            FingerprintRequestRecordLabelParams v0 = new FingerprintRequestRecordLabelParams();
            v0.recordPath = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new FingerprintRequestRecordLabelResponseParamsForwardToCallback(arg9));
        }

        public void requestType(RequestTypeResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new FingerprintRequestTypeParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(10, 1, 0)), new FingerprintRequestTypeResponseParamsForwardToCallback(arg9));
        }

        public void setRecordLabel(String arg7, String arg8, SetRecordLabelResponse arg9) {
            FingerprintSetRecordLabelParams v0 = new FingerprintSetRecordLabelParams();
            v0.recordPath = arg7;
            v0.newLabel = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new FingerprintSetRecordLabelResponseParamsForwardToCallback(arg9));
        }

        public void startAuthSession() {
            this.getProxyHandler().getMessageReceiver().accept(new FingerprintStartAuthSessionParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void startEnrollSession(String arg4, String arg5) {
            FingerprintStartEnrollSessionParams v0 = new FingerprintStartEnrollSessionParams();
            v0.userId = arg4;
            v0.label = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, Fingerprint arg2) {
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
                if(v1_1 != -2) {
                    if(v1_1 != 1) {
                        if(v1_1 != 6) {
                            if(v1_1 != 9) {
                                return 0;
                            }

                            this.getImpl().addFingerprintObserver(FingerprintAddFingerprintObserverParams.deserialize(v5_1.getPayload()).observer);
                            return 1;
                        }

                        FingerprintStartAuthSessionParams.deserialize(v5_1.getPayload());
                        this.getImpl().startAuthSession();
                        return 1;
                    }

                    FingerprintStartEnrollSessionParams v5_2 = FingerprintStartEnrollSessionParams.deserialize(v5_1.getPayload());
                    this.getImpl().startEnrollSession(v5_2.userId, v5_2.label);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Fingerprint_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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
                        goto label_87;
                    }
                    case 0: {
                        goto label_77;
                    }
                    case 2: {
                        goto label_68;
                    }
                    case 3: {
                        goto label_58;
                    }
                    case 4: {
                        goto label_47;
                    }
                    case 5: {
                        goto label_37;
                    }
                    case 7: {
                        goto label_28;
                    }
                    case 8: {
                        goto label_19;
                    }
                    case 10: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                FingerprintDestroyAllRecordsParams.deserialize(v10_1.getPayload());
                this.getImpl().destroyAllRecords(new FingerprintDestroyAllRecordsResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_68:
                FingerprintCancelCurrentEnrollSessionParams.deserialize(v10_1.getPayload());
                this.getImpl().cancelCurrentEnrollSession(new FingerprintCancelCurrentEnrollSessionResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_37:
                this.getImpl().removeRecord(FingerprintRemoveRecordParams.deserialize(v10_1.getPayload()).recordPath, new FingerprintRemoveRecordResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_87:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Fingerprint_Internal.MANAGER, v10_1, arg11);
            label_58:
                this.getImpl().requestRecordLabel(FingerprintRequestRecordLabelParams.deserialize(v10_1.getPayload()).recordPath, new FingerprintRequestRecordLabelResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_10:
                FingerprintRequestTypeParams.deserialize(v10_1.getPayload());
                this.getImpl().requestType(new FingerprintRequestTypeResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_28:
                FingerprintEndCurrentAuthSessionParams.deserialize(v10_1.getPayload());
                this.getImpl().endCurrentAuthSession(new FingerprintEndCurrentAuthSessionResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_77:
                this.getImpl().getRecordsForUser(FingerprintGetRecordsForUserParams.deserialize(v10_1.getPayload()).userId, new FingerprintGetRecordsForUserResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_47:
                FingerprintSetRecordLabelParams v10_2 = FingerprintSetRecordLabelParams.deserialize(v10_1.getPayload());
                this.getImpl().setRecordLabel(v10_2.recordPath, v10_2.newLabel, new FingerprintSetRecordLabelResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int ADD_FINGERPRINT_OBSERVER_ORDINAL = 9;
    private static final int CANCEL_CURRENT_ENROLL_SESSION_ORDINAL = 2;
    private static final int DESTROY_ALL_RECORDS_ORDINAL = 8;
    private static final int END_CURRENT_AUTH_SESSION_ORDINAL = 7;
    private static final int GET_RECORDS_FOR_USER_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int REMOVE_RECORD_ORDINAL = 5;
    private static final int REQUEST_RECORD_LABEL_ORDINAL = 3;
    private static final int REQUEST_TYPE_ORDINAL = 10;
    private static final int SET_RECORD_LABEL_ORDINAL = 4;
    private static final int START_AUTH_SESSION_ORDINAL = 6;
    private static final int START_ENROLL_SESSION_ORDINAL = 1;

    static {
        Fingerprint_Internal.MANAGER = new org.chromium.device.mojom.Fingerprint_Internal$1();
    }

    Fingerprint_Internal() {
        super();
    }
}

