package org.chromium.media.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.AssociatedInterfaceNotSupported;
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

class CdmProxy_Internal {
    final class org.chromium.media.mojom.CdmProxy_Internal$1 extends Manager {
        org.chromium.media.mojom.CdmProxy_Internal$1() {
            super();
        }

        public CdmProxy[] buildArray(int arg1) {
            return new CdmProxy[arg1];
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

        public Stub buildStub(Core arg2, CdmProxy arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CdmProxy)arg2));
        }

        public String getName() {
            return "media::mojom::CdmProxy";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CdmProxyCreateMediaCryptoSessionParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] inputData;

        static {
            CdmProxyCreateMediaCryptoSessionParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CdmProxyCreateMediaCryptoSessionParams.DEFAULT_STRUCT_INFO = CdmProxyCreateMediaCryptoSessionParams.VERSION_ARRAY[0];
        }

        public CdmProxyCreateMediaCryptoSessionParams() {
            this(0);
        }

        private CdmProxyCreateMediaCryptoSessionParams(int arg2) {
            super(16, arg2);
        }

        public static CdmProxyCreateMediaCryptoSessionParams decode(Decoder arg4) {
            CdmProxyCreateMediaCryptoSessionParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CdmProxyCreateMediaCryptoSessionParams(arg4.readAndValidateDataHeader(CdmProxyCreateMediaCryptoSessionParams.VERSION_ARRAY).elementsOrVersion);
                v1.inputData = arg4.readBytes(8, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyCreateMediaCryptoSessionParams deserialize(ByteBuffer arg2) {
            return CdmProxyCreateMediaCryptoSessionParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyCreateMediaCryptoSessionParams deserialize(Message arg1) {
            return CdmProxyCreateMediaCryptoSessionParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(CdmProxyCreateMediaCryptoSessionParams.DEFAULT_STRUCT_INFO).encode(this.inputData, 8, 0, -1);
        }
    }

    final class CdmProxyCreateMediaCryptoSessionResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int cryptoSessionId;
        public long outputData;
        public int status;

        static {
            CdmProxyCreateMediaCryptoSessionResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CdmProxyCreateMediaCryptoSessionResponseParams.DEFAULT_STRUCT_INFO = CdmProxyCreateMediaCryptoSessionResponseParams.VERSION_ARRAY[0];
        }

        public CdmProxyCreateMediaCryptoSessionResponseParams() {
            this(0);
        }

        private CdmProxyCreateMediaCryptoSessionResponseParams(int arg2) {
            super(24, arg2);
        }

        public static CdmProxyCreateMediaCryptoSessionResponseParams decode(Decoder arg4) {
            CdmProxyCreateMediaCryptoSessionResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CdmProxyCreateMediaCryptoSessionResponseParams(arg4.readAndValidateDataHeader(CdmProxyCreateMediaCryptoSessionResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg4.readInt(8);
                Status.validate(v1.status);
                v1.cryptoSessionId = arg4.readInt(12);
                v1.outputData = arg4.readLong(16);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyCreateMediaCryptoSessionResponseParams deserialize(ByteBuffer arg2) {
            return CdmProxyCreateMediaCryptoSessionResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyCreateMediaCryptoSessionResponseParams deserialize(Message arg1) {
            return CdmProxyCreateMediaCryptoSessionResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(CdmProxyCreateMediaCryptoSessionResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.cryptoSessionId, 12);
            arg4.encode(this.outputData, 16);
        }
    }

    class CdmProxyCreateMediaCryptoSessionResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CreateMediaCryptoSessionResponse mCallback;

        CdmProxyCreateMediaCryptoSessionResponseParamsForwardToCallback(CreateMediaCryptoSessionResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7 = arg7.asServiceMessage();
                if(!v7.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                CdmProxyCreateMediaCryptoSessionResponseParams v7_1 = CdmProxyCreateMediaCryptoSessionResponseParams.deserialize(v7.getPayload());
                this.mCallback.call(Integer.valueOf(v7_1.status), Integer.valueOf(v7_1.cryptoSessionId), Long.valueOf(v7_1.outputData));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CdmProxyCreateMediaCryptoSessionResponseParamsProxyToResponder implements CreateMediaCryptoSessionResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CdmProxyCreateMediaCryptoSessionResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg4, Integer arg5, Long arg6) {
            CdmProxyCreateMediaCryptoSessionResponseParams v0 = new CdmProxyCreateMediaCryptoSessionResponseParams();
            v0.status = arg4.intValue();
            v0.cryptoSessionId = arg5.intValue();
            v0.outputData = arg6.longValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((Integer)arg2), ((Long)arg3));
        }
    }

    final class CdmProxyInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AssociatedInterfaceNotSupported client;

        static {
            CdmProxyInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CdmProxyInitializeParams.DEFAULT_STRUCT_INFO = CdmProxyInitializeParams.VERSION_ARRAY[0];
        }

        public CdmProxyInitializeParams() {
            this(0);
        }

        private CdmProxyInitializeParams(int arg2) {
            super(16, arg2);
        }

        public static CdmProxyInitializeParams decode(Decoder arg3) {
            CdmProxyInitializeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CdmProxyInitializeParams(arg3.readAndValidateDataHeader(CdmProxyInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg3.readAssociatedServiceInterfaceNotSupported(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyInitializeParams deserialize(ByteBuffer arg2) {
            return CdmProxyInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyInitializeParams deserialize(Message arg1) {
            return CdmProxyInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CdmProxyInitializeParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false);
        }
    }

    final class CdmProxyInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int cdmId;
        public int cryptoSessionId;
        public int protocol;
        public int status;

        static {
            CdmProxyInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CdmProxyInitializeResponseParams.DEFAULT_STRUCT_INFO = CdmProxyInitializeResponseParams.VERSION_ARRAY[0];
        }

        public CdmProxyInitializeResponseParams() {
            this(0);
        }

        private CdmProxyInitializeResponseParams(int arg2) {
            super(24, arg2);
        }

        public static CdmProxyInitializeResponseParams decode(Decoder arg2) {
            CdmProxyInitializeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CdmProxyInitializeResponseParams(arg2.readAndValidateDataHeader(CdmProxyInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg2.readInt(8);
                Status.validate(v1.status);
                v1.protocol = arg2.readInt(12);
                Protocol.validate(v1.protocol);
                v1.cryptoSessionId = arg2.readInt(16);
                v1.cdmId = arg2.readInt(20);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyInitializeResponseParams deserialize(ByteBuffer arg2) {
            return CdmProxyInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyInitializeResponseParams deserialize(Message arg1) {
            return CdmProxyInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(CdmProxyInitializeResponseParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.status, 8);
            arg3.encode(this.protocol, 12);
            arg3.encode(this.cryptoSessionId, 16);
            arg3.encode(this.cdmId, 20);
        }
    }

    class CdmProxyInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        CdmProxyInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                CdmProxyInitializeResponseParams v6_1 = CdmProxyInitializeResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(Integer.valueOf(v6_1.status), Integer.valueOf(v6_1.protocol), Integer.valueOf(v6_1.cryptoSessionId), Integer.valueOf(v6_1.cdmId));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CdmProxyInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CdmProxyInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg4, Integer arg5, Integer arg6, Integer arg7) {
            CdmProxyInitializeResponseParams v0 = new CdmProxyInitializeResponseParams();
            v0.status = arg4.intValue();
            v0.protocol = arg5.intValue();
            v0.cryptoSessionId = arg6.intValue();
            v0.cdmId = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3, Object arg4) {
            this.call(((Integer)arg1), ((Integer)arg2), ((Integer)arg3), ((Integer)arg4));
        }
    }

    final class CdmProxyProcessParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int cryptoSessionId;
        public int function;
        public byte[] inputData;
        public int outputDataSize;

        static {
            CdmProxyProcessParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            CdmProxyProcessParams.DEFAULT_STRUCT_INFO = CdmProxyProcessParams.VERSION_ARRAY[0];
        }

        public CdmProxyProcessParams() {
            this(0);
        }

        private CdmProxyProcessParams(int arg2) {
            super(0x20, arg2);
        }

        public static CdmProxyProcessParams decode(Decoder arg4) {
            CdmProxyProcessParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CdmProxyProcessParams(arg4.readAndValidateDataHeader(CdmProxyProcessParams.VERSION_ARRAY).elementsOrVersion);
                v1.function = arg4.readInt(8);
                Function.validate(v1.function);
                v1.cryptoSessionId = arg4.readInt(12);
                v1.inputData = arg4.readBytes(16, 0, -1);
                v1.outputDataSize = arg4.readInt(24);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyProcessParams deserialize(ByteBuffer arg2) {
            return CdmProxyProcessParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyProcessParams deserialize(Message arg1) {
            return CdmProxyProcessParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(CdmProxyProcessParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.function, 8);
            arg5.encode(this.cryptoSessionId, 12);
            arg5.encode(this.inputData, 16, 0, -1);
            arg5.encode(this.outputDataSize, 24);
        }
    }

    final class CdmProxyProcessResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] outputData;
        public int status;

        static {
            CdmProxyProcessResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CdmProxyProcessResponseParams.DEFAULT_STRUCT_INFO = CdmProxyProcessResponseParams.VERSION_ARRAY[0];
        }

        public CdmProxyProcessResponseParams() {
            this(0);
        }

        private CdmProxyProcessResponseParams(int arg2) {
            super(24, arg2);
        }

        public static CdmProxyProcessResponseParams decode(Decoder arg4) {
            CdmProxyProcessResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CdmProxyProcessResponseParams(arg4.readAndValidateDataHeader(CdmProxyProcessResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg4.readInt(8);
                Status.validate(v1.status);
                v1.outputData = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyProcessResponseParams deserialize(ByteBuffer arg2) {
            return CdmProxyProcessResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyProcessResponseParams deserialize(Message arg1) {
            return CdmProxyProcessResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(CdmProxyProcessResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.status, 8);
            arg5.encode(this.outputData, 16, 0, -1);
        }
    }

    class CdmProxyProcessResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ProcessResponse mCallback;

        CdmProxyProcessResponseParamsForwardToCallback(ProcessResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                CdmProxyProcessResponseParams v5_1 = CdmProxyProcessResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.status), v5_1.outputData);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CdmProxyProcessResponseParamsProxyToResponder implements ProcessResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CdmProxyProcessResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, byte[] arg7) {
            CdmProxyProcessResponseParams v0 = new CdmProxyProcessResponseParams();
            v0.status = arg6.intValue();
            v0.outputData = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((byte[])arg2));
        }
    }

    final class CdmProxyRemoveKeyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int cryptoSessionId;
        public byte[] keyId;

        static {
            CdmProxyRemoveKeyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CdmProxyRemoveKeyParams.DEFAULT_STRUCT_INFO = CdmProxyRemoveKeyParams.VERSION_ARRAY[0];
        }

        public CdmProxyRemoveKeyParams() {
            this(0);
        }

        private CdmProxyRemoveKeyParams(int arg2) {
            super(24, arg2);
        }

        public static CdmProxyRemoveKeyParams decode(Decoder arg4) {
            CdmProxyRemoveKeyParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CdmProxyRemoveKeyParams(arg4.readAndValidateDataHeader(CdmProxyRemoveKeyParams.VERSION_ARRAY).elementsOrVersion);
                v1.cryptoSessionId = arg4.readInt(8);
                v1.keyId = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CdmProxyRemoveKeyParams deserialize(ByteBuffer arg2) {
            return CdmProxyRemoveKeyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxyRemoveKeyParams deserialize(Message arg1) {
            return CdmProxyRemoveKeyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(CdmProxyRemoveKeyParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.cryptoSessionId, 8);
            arg5.encode(this.keyId, 16, 0, -1);
        }
    }

    final class CdmProxySetKeyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int cryptoSessionId;
        public byte[] keyBlob;
        public byte[] keyId;

        static {
            CdmProxySetKeyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            CdmProxySetKeyParams.DEFAULT_STRUCT_INFO = CdmProxySetKeyParams.VERSION_ARRAY[0];
        }

        public CdmProxySetKeyParams() {
            this(0);
        }

        private CdmProxySetKeyParams(int arg2) {
            super(0x20, arg2);
        }

        public static CdmProxySetKeyParams decode(Decoder arg4) {
            CdmProxySetKeyParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CdmProxySetKeyParams(arg4.readAndValidateDataHeader(CdmProxySetKeyParams.VERSION_ARRAY).elementsOrVersion);
                v1.cryptoSessionId = arg4.readInt(8);
                v1.keyId = arg4.readBytes(16, 0, -1);
                v1.keyBlob = arg4.readBytes(24, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CdmProxySetKeyParams deserialize(ByteBuffer arg2) {
            return CdmProxySetKeyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmProxySetKeyParams deserialize(Message arg1) {
            return CdmProxySetKeyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(CdmProxySetKeyParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.cryptoSessionId, 8);
            arg5.encode(this.keyId, 16, 0, -1);
            arg5.encode(this.keyBlob, 24, 0, -1);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.CdmProxy$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void createMediaCryptoSession(byte[] arg8, CreateMediaCryptoSessionResponse arg9) {
            CdmProxyCreateMediaCryptoSessionParams v0 = new CdmProxyCreateMediaCryptoSessionParams();
            v0.inputData = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new CdmProxyCreateMediaCryptoSessionResponseParamsForwardToCallback(arg9));
        }

        public void initialize(AssociatedInterfaceNotSupported arg8, InitializeResponse arg9) {
            CdmProxyInitializeParams v0 = new CdmProxyInitializeParams();
            v0.client = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new CdmProxyInitializeResponseParamsForwardToCallback(arg9));
        }

        public void process(int arg4, int arg5, byte[] arg6, int arg7, ProcessResponse arg8) {
            CdmProxyProcessParams v0 = new CdmProxyProcessParams();
            v0.function = arg4;
            v0.cryptoSessionId = arg5;
            v0.inputData = arg6;
            v0.outputDataSize = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new CdmProxyProcessResponseParamsForwardToCallback(arg8));
        }

        public void removeKey(int arg4, byte[] arg5) {
            CdmProxyRemoveKeyParams v0 = new CdmProxyRemoveKeyParams();
            v0.cryptoSessionId = arg4;
            v0.keyId = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void setKey(int arg3, byte[] arg4, byte[] arg5) {
            CdmProxySetKeyParams v0 = new CdmProxySetKeyParams();
            v0.cryptoSessionId = arg3;
            v0.keyId = arg4;
            v0.keyBlob = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, CdmProxy arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6_1 = arg6.asServiceMessage();
                MessageHeader v1 = v6_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_27;
                }

                switch(v1_1) {
                    case 3: {
                        goto label_19;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_19:
                CdmProxySetKeyParams v6_2 = CdmProxySetKeyParams.deserialize(v6_1.getPayload());
                this.getImpl().setKey(v6_2.cryptoSessionId, v6_2.keyId, v6_2.keyBlob);
                return 1;
            label_12:
                CdmProxyRemoveKeyParams v6_3 = CdmProxyRemoveKeyParams.deserialize(v6_1.getPayload());
                this.getImpl().removeKey(v6_3.cryptoSessionId, v6_3.keyId);
                return 1;
            label_27:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CdmProxy_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
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
                        goto label_44;
                    }
                    case 0: {
                        goto label_34;
                    }
                    case 1: {
                        goto label_20;
                    }
                    case 2: {
                        goto label_10;
                    }
                }

                return 0;
            label_34:
                this.getImpl().initialize(CdmProxyInitializeParams.deserialize(v13_1.getPayload()).client, new CdmProxyInitializeResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_20:
                CdmProxyProcessParams v13_2 = CdmProxyProcessParams.deserialize(v13_1.getPayload());
                this.getImpl().process(v13_2.function, v13_2.cryptoSessionId, v13_2.inputData, v13_2.outputDataSize, new CdmProxyProcessResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_10:
                this.getImpl().createMediaCryptoSession(CdmProxyCreateMediaCryptoSessionParams.deserialize(v13_1.getPayload()).inputData, new CdmProxyCreateMediaCryptoSessionResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_44:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CdmProxy_Internal.MANAGER, v13_1, arg14);
            }
            catch(DeserializationException v13) {
                System.err.println(v13.toString());
                return 0;
            }
        }
    }

    private static final int CREATE_MEDIA_CRYPTO_SESSION_ORDINAL = 2;
    private static final int INITIALIZE_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int PROCESS_ORDINAL = 1;
    private static final int REMOVE_KEY_ORDINAL = 4;
    private static final int SET_KEY_ORDINAL = 3;

    static {
        CdmProxy_Internal.MANAGER = new org.chromium.media.mojom.CdmProxy_Internal$1();
    }

    CdmProxy_Internal() {
        super();
    }
}

