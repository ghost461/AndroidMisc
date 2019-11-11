package org.chromium.device.mojom;

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

class Nfc_Internal {
    final class org.chromium.device.mojom.Nfc_Internal$1 extends Manager {
        org.chromium.device.mojom.Nfc_Internal$1() {
            super();
        }

        public Nfc[] buildArray(int arg1) {
            return new Nfc[arg1];
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

        public Stub buildStub(Core arg2, Nfc arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Nfc)arg2));
        }

        public String getName() {
            return "device::mojom::NFC";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NfcCancelAllWatchesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NfcCancelAllWatchesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NfcCancelAllWatchesParams.DEFAULT_STRUCT_INFO = NfcCancelAllWatchesParams.VERSION_ARRAY[0];
        }

        public NfcCancelAllWatchesParams() {
            this(0);
        }

        private NfcCancelAllWatchesParams(int arg2) {
            super(8, arg2);
        }

        public static NfcCancelAllWatchesParams decode(Decoder arg2) {
            NfcCancelAllWatchesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NfcCancelAllWatchesParams(arg2.readAndValidateDataHeader(NfcCancelAllWatchesParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NfcCancelAllWatchesParams deserialize(ByteBuffer arg2) {
            return NfcCancelAllWatchesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcCancelAllWatchesParams deserialize(Message arg1) {
            return NfcCancelAllWatchesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NfcCancelAllWatchesParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class NfcCancelAllWatchesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcError error;

        static {
            NfcCancelAllWatchesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcCancelAllWatchesResponseParams.DEFAULT_STRUCT_INFO = NfcCancelAllWatchesResponseParams.VERSION_ARRAY[0];
        }

        public NfcCancelAllWatchesResponseParams() {
            this(0);
        }

        private NfcCancelAllWatchesResponseParams(int arg2) {
            super(16, arg2);
        }

        public static NfcCancelAllWatchesResponseParams decode(Decoder arg3) {
            NfcCancelAllWatchesResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcCancelAllWatchesResponseParams(arg3.readAndValidateDataHeader(NfcCancelAllWatchesResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = NfcError.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcCancelAllWatchesResponseParams deserialize(ByteBuffer arg2) {
            return NfcCancelAllWatchesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcCancelAllWatchesResponseParams deserialize(Message arg1) {
            return NfcCancelAllWatchesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NfcCancelAllWatchesResponseParams.DEFAULT_STRUCT_INFO).encode(this.error, 8, true);
        }
    }

    class NfcCancelAllWatchesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CancelAllWatchesResponse mCallback;

        NfcCancelAllWatchesResponseParamsForwardToCallback(CancelAllWatchesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                this.mCallback.call(NfcCancelAllWatchesResponseParams.deserialize(v5.getPayload()).error);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NfcCancelAllWatchesResponseParamsProxyToResponder implements CancelAllWatchesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NfcCancelAllWatchesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((NfcError)arg1));
        }

        public void call(NfcError arg7) {
            NfcCancelAllWatchesResponseParams v0 = new NfcCancelAllWatchesResponseParams();
            v0.error = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }
    }

    final class NfcCancelPushParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int target;

        static {
            NfcCancelPushParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcCancelPushParams.DEFAULT_STRUCT_INFO = NfcCancelPushParams.VERSION_ARRAY[0];
        }

        public NfcCancelPushParams() {
            this(0);
        }

        private NfcCancelPushParams(int arg2) {
            super(16, arg2);
        }

        public static NfcCancelPushParams decode(Decoder arg2) {
            NfcCancelPushParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NfcCancelPushParams(arg2.readAndValidateDataHeader(NfcCancelPushParams.VERSION_ARRAY).elementsOrVersion);
                v1.target = arg2.readInt(8);
                NfcPushTarget.validate(v1.target);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NfcCancelPushParams deserialize(ByteBuffer arg2) {
            return NfcCancelPushParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcCancelPushParams deserialize(Message arg1) {
            return NfcCancelPushParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NfcCancelPushParams.DEFAULT_STRUCT_INFO).encode(this.target, 8);
        }
    }

    final class NfcCancelPushResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcError error;

        static {
            NfcCancelPushResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcCancelPushResponseParams.DEFAULT_STRUCT_INFO = NfcCancelPushResponseParams.VERSION_ARRAY[0];
        }

        public NfcCancelPushResponseParams() {
            this(0);
        }

        private NfcCancelPushResponseParams(int arg2) {
            super(16, arg2);
        }

        public static NfcCancelPushResponseParams decode(Decoder arg3) {
            NfcCancelPushResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcCancelPushResponseParams(arg3.readAndValidateDataHeader(NfcCancelPushResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = NfcError.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcCancelPushResponseParams deserialize(ByteBuffer arg2) {
            return NfcCancelPushResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcCancelPushResponseParams deserialize(Message arg1) {
            return NfcCancelPushResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NfcCancelPushResponseParams.DEFAULT_STRUCT_INFO).encode(this.error, 8, true);
        }
    }

    class NfcCancelPushResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CancelPushResponse mCallback;

        NfcCancelPushResponseParamsForwardToCallback(CancelPushResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(NfcCancelPushResponseParams.deserialize(v4.getPayload()).error);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NfcCancelPushResponseParamsProxyToResponder implements CancelPushResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NfcCancelPushResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((NfcError)arg1));
        }

        public void call(NfcError arg6) {
            NfcCancelPushResponseParams v0 = new NfcCancelPushResponseParams();
            v0.error = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class NfcCancelWatchParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int id;

        static {
            NfcCancelWatchParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcCancelWatchParams.DEFAULT_STRUCT_INFO = NfcCancelWatchParams.VERSION_ARRAY[0];
        }

        public NfcCancelWatchParams() {
            this(0);
        }

        private NfcCancelWatchParams(int arg2) {
            super(16, arg2);
        }

        public static NfcCancelWatchParams decode(Decoder arg2) {
            NfcCancelWatchParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NfcCancelWatchParams(arg2.readAndValidateDataHeader(NfcCancelWatchParams.VERSION_ARRAY).elementsOrVersion);
                v1.id = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NfcCancelWatchParams deserialize(ByteBuffer arg2) {
            return NfcCancelWatchParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcCancelWatchParams deserialize(Message arg1) {
            return NfcCancelWatchParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NfcCancelWatchParams.DEFAULT_STRUCT_INFO).encode(this.id, 8);
        }
    }

    final class NfcCancelWatchResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcError error;

        static {
            NfcCancelWatchResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcCancelWatchResponseParams.DEFAULT_STRUCT_INFO = NfcCancelWatchResponseParams.VERSION_ARRAY[0];
        }

        public NfcCancelWatchResponseParams() {
            this(0);
        }

        private NfcCancelWatchResponseParams(int arg2) {
            super(16, arg2);
        }

        public static NfcCancelWatchResponseParams decode(Decoder arg3) {
            NfcCancelWatchResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcCancelWatchResponseParams(arg3.readAndValidateDataHeader(NfcCancelWatchResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = NfcError.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcCancelWatchResponseParams deserialize(ByteBuffer arg2) {
            return NfcCancelWatchResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcCancelWatchResponseParams deserialize(Message arg1) {
            return NfcCancelWatchResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NfcCancelWatchResponseParams.DEFAULT_STRUCT_INFO).encode(this.error, 8, true);
        }
    }

    class NfcCancelWatchResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CancelWatchResponse mCallback;

        NfcCancelWatchResponseParamsForwardToCallback(CancelWatchResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call(NfcCancelWatchResponseParams.deserialize(v5.getPayload()).error);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NfcCancelWatchResponseParamsProxyToResponder implements CancelWatchResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NfcCancelWatchResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((NfcError)arg1));
        }

        public void call(NfcError arg7) {
            NfcCancelWatchResponseParams v0 = new NfcCancelWatchResponseParams();
            v0.error = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }
    }

    final class NfcPushParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcMessage message;
        public NfcPushOptions options;

        static {
            NfcPushParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NfcPushParams.DEFAULT_STRUCT_INFO = NfcPushParams.VERSION_ARRAY[0];
        }

        public NfcPushParams() {
            this(0);
        }

        private NfcPushParams(int arg2) {
            super(24, arg2);
        }

        public static NfcPushParams decode(Decoder arg3) {
            NfcPushParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcPushParams(arg3.readAndValidateDataHeader(NfcPushParams.VERSION_ARRAY).elementsOrVersion);
                v1.message = NfcMessage.decode(arg3.readPointer(8, false));
                v1.options = NfcPushOptions.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcPushParams deserialize(ByteBuffer arg2) {
            return NfcPushParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcPushParams deserialize(Message arg1) {
            return NfcPushParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NfcPushParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.message, 8, false);
            arg4.encode(this.options, 16, true);
        }
    }

    final class NfcPushResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcError error;

        static {
            NfcPushResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcPushResponseParams.DEFAULT_STRUCT_INFO = NfcPushResponseParams.VERSION_ARRAY[0];
        }

        public NfcPushResponseParams() {
            this(0);
        }

        private NfcPushResponseParams(int arg2) {
            super(16, arg2);
        }

        public static NfcPushResponseParams decode(Decoder arg3) {
            NfcPushResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcPushResponseParams(arg3.readAndValidateDataHeader(NfcPushResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = NfcError.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcPushResponseParams deserialize(ByteBuffer arg2) {
            return NfcPushResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcPushResponseParams deserialize(Message arg1) {
            return NfcPushResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NfcPushResponseParams.DEFAULT_STRUCT_INFO).encode(this.error, 8, true);
        }
    }

    class NfcPushResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final PushResponse mCallback;

        NfcPushResponseParamsForwardToCallback(PushResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(NfcPushResponseParams.deserialize(v5.getPayload()).error);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NfcPushResponseParamsProxyToResponder implements PushResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NfcPushResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((NfcError)arg1));
        }

        public void call(NfcError arg7) {
            NfcPushResponseParams v0 = new NfcPushResponseParams();
            v0.error = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class NfcResumeNfcOperationsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NfcResumeNfcOperationsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NfcResumeNfcOperationsParams.DEFAULT_STRUCT_INFO = NfcResumeNfcOperationsParams.VERSION_ARRAY[0];
        }

        public NfcResumeNfcOperationsParams() {
            this(0);
        }

        private NfcResumeNfcOperationsParams(int arg2) {
            super(8, arg2);
        }

        public static NfcResumeNfcOperationsParams decode(Decoder arg2) {
            NfcResumeNfcOperationsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NfcResumeNfcOperationsParams(arg2.readAndValidateDataHeader(NfcResumeNfcOperationsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NfcResumeNfcOperationsParams deserialize(ByteBuffer arg2) {
            return NfcResumeNfcOperationsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcResumeNfcOperationsParams deserialize(Message arg1) {
            return NfcResumeNfcOperationsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NfcResumeNfcOperationsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class NfcSetClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcClient client;

        static {
            NfcSetClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcSetClientParams.DEFAULT_STRUCT_INFO = NfcSetClientParams.VERSION_ARRAY[0];
        }

        public NfcSetClientParams() {
            this(0);
        }

        private NfcSetClientParams(int arg2) {
            super(16, arg2);
        }

        public static NfcSetClientParams decode(Decoder arg4) {
            NfcSetClientParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new NfcSetClientParams(arg4.readAndValidateDataHeader(NfcSetClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg4.readServiceInterface(8, false, NfcClient.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static NfcSetClientParams deserialize(ByteBuffer arg2) {
            return NfcSetClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcSetClientParams deserialize(Message arg1) {
            return NfcSetClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(NfcSetClientParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false, NfcClient.MANAGER);
        }
    }

    final class NfcSuspendNfcOperationsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NfcSuspendNfcOperationsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NfcSuspendNfcOperationsParams.DEFAULT_STRUCT_INFO = NfcSuspendNfcOperationsParams.VERSION_ARRAY[0];
        }

        public NfcSuspendNfcOperationsParams() {
            this(0);
        }

        private NfcSuspendNfcOperationsParams(int arg2) {
            super(8, arg2);
        }

        public static NfcSuspendNfcOperationsParams decode(Decoder arg2) {
            NfcSuspendNfcOperationsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NfcSuspendNfcOperationsParams(arg2.readAndValidateDataHeader(NfcSuspendNfcOperationsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NfcSuspendNfcOperationsParams deserialize(ByteBuffer arg2) {
            return NfcSuspendNfcOperationsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcSuspendNfcOperationsParams deserialize(Message arg1) {
            return NfcSuspendNfcOperationsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NfcSuspendNfcOperationsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class NfcWatchParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcWatchOptions options;

        static {
            NfcWatchParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NfcWatchParams.DEFAULT_STRUCT_INFO = NfcWatchParams.VERSION_ARRAY[0];
        }

        public NfcWatchParams() {
            this(0);
        }

        private NfcWatchParams(int arg2) {
            super(16, arg2);
        }

        public static NfcWatchParams decode(Decoder arg3) {
            NfcWatchParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcWatchParams(arg3.readAndValidateDataHeader(NfcWatchParams.VERSION_ARRAY).elementsOrVersion);
                v1.options = NfcWatchOptions.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcWatchParams deserialize(ByteBuffer arg2) {
            return NfcWatchParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcWatchParams deserialize(Message arg1) {
            return NfcWatchParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(NfcWatchParams.DEFAULT_STRUCT_INFO).encode(this.options, 8, false);
        }
    }

    final class NfcWatchResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public NfcError error;
        public int id;

        static {
            NfcWatchResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            NfcWatchResponseParams.DEFAULT_STRUCT_INFO = NfcWatchResponseParams.VERSION_ARRAY[0];
        }

        public NfcWatchResponseParams() {
            this(0);
        }

        private NfcWatchResponseParams(int arg2) {
            super(24, arg2);
        }

        public static NfcWatchResponseParams decode(Decoder arg3) {
            NfcWatchResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NfcWatchResponseParams(arg3.readAndValidateDataHeader(NfcWatchResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.id = arg3.readInt(8);
                v1.error = NfcError.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NfcWatchResponseParams deserialize(ByteBuffer arg2) {
            return NfcWatchResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NfcWatchResponseParams deserialize(Message arg1) {
            return NfcWatchResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NfcWatchResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.id, 8);
            arg4.encode(this.error, 16, true);
        }
    }

    class NfcWatchResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final WatchResponse mCallback;

        NfcWatchResponseParamsForwardToCallback(WatchResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                NfcWatchResponseParams v5_1 = NfcWatchResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.id), v5_1.error);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class NfcWatchResponseParamsProxyToResponder implements WatchResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NfcWatchResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, NfcError arg7) {
            NfcWatchResponseParams v0 = new NfcWatchResponseParams();
            v0.id = arg6.intValue();
            v0.error = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((NfcError)arg2));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.Nfc$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void cancelAllWatches(CancelAllWatchesResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new NfcCancelAllWatchesParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new NfcCancelAllWatchesResponseParamsForwardToCallback(arg9));
        }

        public void cancelPush(int arg8, CancelPushResponse arg9) {
            NfcCancelPushParams v0 = new NfcCancelPushParams();
            v0.target = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new NfcCancelPushResponseParamsForwardToCallback(arg9));
        }

        public void cancelWatch(int arg8, CancelWatchResponse arg9) {
            NfcCancelWatchParams v0 = new NfcCancelWatchParams();
            v0.id = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new NfcCancelWatchResponseParamsForwardToCallback(arg9));
        }

        public void push(NfcMessage arg6, NfcPushOptions arg7, PushResponse arg8) {
            NfcPushParams v0 = new NfcPushParams();
            v0.message = arg6;
            v0.options = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new NfcPushResponseParamsForwardToCallback(arg8));
        }

        public void resumeNfcOperations() {
            this.getProxyHandler().getMessageReceiver().accept(new NfcResumeNfcOperationsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }

        public void setClient(NfcClient arg5) {
            NfcSetClientParams v0 = new NfcSetClientParams();
            v0.client = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void suspendNfcOperations() {
            this.getProxyHandler().getMessageReceiver().accept(new NfcSuspendNfcOperationsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void watch(NfcWatchOptions arg8, WatchResponse arg9) {
            NfcWatchParams v0 = new NfcWatchParams();
            v0.options = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new NfcWatchResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, Nfc arg2) {
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
                if(v1_1 == -2) {
                    goto label_29;
                }

                if(v1_1 == 0) {
                    goto label_23;
                }

                switch(v1_1) {
                    case 6: {
                        goto label_18;
                    }
                    case 7: {
                        goto label_13;
                    }
                }

                return 0;
            label_18:
                NfcSuspendNfcOperationsParams.deserialize(v4_1.getPayload());
                this.getImpl().suspendNfcOperations();
                return 1;
            label_13:
                NfcResumeNfcOperationsParams.deserialize(v4_1.getPayload());
                this.getImpl().resumeNfcOperations();
                return 1;
            label_23:
                this.getImpl().setClient(NfcSetClientParams.deserialize(v4_1.getPayload()).client);
                return 1;
            label_29:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Nfc_Internal.MANAGER, v4_1);
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

                int v3 = v1.getType();
                if(v3 == -1) {
                    goto label_62;
                }

                switch(v3) {
                    case 1: {
                        goto label_51;
                    }
                    case 2: {
                        goto label_41;
                    }
                    case 3: {
                        goto label_31;
                    }
                    case 4: {
                        goto label_21;
                    }
                    case 5: {
                        goto label_12;
                    }
                }

                return 0;
            label_51:
                NfcPushParams v10_2 = NfcPushParams.deserialize(v10_1.getPayload());
                this.getImpl().push(v10_2.message, v10_2.options, new NfcPushResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_21:
                this.getImpl().cancelWatch(NfcCancelWatchParams.deserialize(v10_1.getPayload()).id, new NfcCancelWatchResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_41:
                this.getImpl().cancelPush(NfcCancelPushParams.deserialize(v10_1.getPayload()).target, new NfcCancelPushResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_12:
                NfcCancelAllWatchesParams.deserialize(v10_1.getPayload());
                this.getImpl().cancelAllWatches(new NfcCancelAllWatchesResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_31:
                this.getImpl().watch(NfcWatchParams.deserialize(v10_1.getPayload()).options, new NfcWatchResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_62:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Nfc_Internal.MANAGER, v10_1, arg11);
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int CANCEL_ALL_WATCHES_ORDINAL = 5;
    private static final int CANCEL_PUSH_ORDINAL = 2;
    private static final int CANCEL_WATCH_ORDINAL = 4;
    public static final Manager MANAGER = null;
    private static final int PUSH_ORDINAL = 1;
    private static final int RESUME_NFC_OPERATIONS_ORDINAL = 7;
    private static final int SET_CLIENT_ORDINAL = 0;
    private static final int SUSPEND_NFC_OPERATIONS_ORDINAL = 6;
    private static final int WATCH_ORDINAL = 3;

    static {
        Nfc_Internal.MANAGER = new org.chromium.device.mojom.Nfc_Internal$1();
    }

    Nfc_Internal() {
        super();
    }
}

