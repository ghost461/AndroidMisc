package org.chromium.blink.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import org.chromium.gfx.mojom.Size;
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
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo_base.mojom.BigString16;
import org.chromium.mojo_base.mojom.String16;
import org.chromium.url.mojom.Url;

class ClipboardHost_Internal {
    final class org.chromium.blink.mojom.ClipboardHost_Internal$1 extends Manager {
        org.chromium.blink.mojom.ClipboardHost_Internal$1() {
            super();
        }

        public ClipboardHost[] buildArray(int arg1) {
            return new ClipboardHost[arg1];
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

        public Stub buildStub(Core arg2, ClipboardHost arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ClipboardHost)arg2));
        }

        public String getName() {
            return "blink::mojom::ClipboardHost";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ClipboardHostCommitWriteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostCommitWriteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostCommitWriteParams.DEFAULT_STRUCT_INFO = ClipboardHostCommitWriteParams.VERSION_ARRAY[0];
        }

        public ClipboardHostCommitWriteParams() {
            this(0);
        }

        private ClipboardHostCommitWriteParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostCommitWriteParams decode(Decoder arg2) {
            ClipboardHostCommitWriteParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostCommitWriteParams(arg2.readAndValidateDataHeader(ClipboardHostCommitWriteParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostCommitWriteParams deserialize(ByteBuffer arg2) {
            return ClipboardHostCommitWriteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostCommitWriteParams deserialize(Message arg1) {
            return ClipboardHostCommitWriteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostCommitWriteParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostGetSequenceNumberParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostGetSequenceNumberParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostGetSequenceNumberParams.DEFAULT_STRUCT_INFO = ClipboardHostGetSequenceNumberParams.VERSION_ARRAY[0];
        }

        public ClipboardHostGetSequenceNumberParams() {
            this(0);
        }

        private ClipboardHostGetSequenceNumberParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostGetSequenceNumberParams decode(Decoder arg2) {
            ClipboardHostGetSequenceNumberParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostGetSequenceNumberParams(arg2.readAndValidateDataHeader(ClipboardHostGetSequenceNumberParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostGetSequenceNumberParams deserialize(ByteBuffer arg2) {
            return ClipboardHostGetSequenceNumberParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostGetSequenceNumberParams deserialize(Message arg1) {
            return ClipboardHostGetSequenceNumberParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostGetSequenceNumberParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostGetSequenceNumberResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public long result;

        static {
            ClipboardHostGetSequenceNumberResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostGetSequenceNumberResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostGetSequenceNumberResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostGetSequenceNumberResponseParams() {
            this(0);
        }

        private ClipboardHostGetSequenceNumberResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostGetSequenceNumberResponseParams decode(Decoder arg4) {
            ClipboardHostGetSequenceNumberResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ClipboardHostGetSequenceNumberResponseParams(arg4.readAndValidateDataHeader(ClipboardHostGetSequenceNumberResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg4.readLong(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostGetSequenceNumberResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostGetSequenceNumberResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostGetSequenceNumberResponseParams deserialize(Message arg1) {
            return ClipboardHostGetSequenceNumberResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ClipboardHostGetSequenceNumberResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8);
        }
    }

    class ClipboardHostGetSequenceNumberResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetSequenceNumberResponse mCallback;

        ClipboardHostGetSequenceNumberResponseParamsForwardToCallback(GetSequenceNumberResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Long.valueOf(ClipboardHostGetSequenceNumberResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostGetSequenceNumberResponseParamsProxyToResponder implements GetSequenceNumberResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostGetSequenceNumberResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Long arg7) {
            ClipboardHostGetSequenceNumberResponseParams v0 = new ClipboardHostGetSequenceNumberResponseParams();
            v0.result = arg7.longValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Long)arg1));
        }
    }

    final class ClipboardHostIsFormatAvailableParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;
        public int format;

        static {
            ClipboardHostIsFormatAvailableParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostIsFormatAvailableParams.DEFAULT_STRUCT_INFO = ClipboardHostIsFormatAvailableParams.VERSION_ARRAY[0];
        }

        public ClipboardHostIsFormatAvailableParams() {
            this(0);
        }

        private ClipboardHostIsFormatAvailableParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostIsFormatAvailableParams decode(Decoder arg2) {
            ClipboardHostIsFormatAvailableParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostIsFormatAvailableParams(arg2.readAndValidateDataHeader(ClipboardHostIsFormatAvailableParams.VERSION_ARRAY).elementsOrVersion);
                v1.format = arg2.readInt(8);
                ClipboardFormat.validate(v1.format);
                v1.buffer = arg2.readInt(12);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostIsFormatAvailableParams deserialize(ByteBuffer arg2) {
            return ClipboardHostIsFormatAvailableParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostIsFormatAvailableParams deserialize(Message arg1) {
            return ClipboardHostIsFormatAvailableParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(ClipboardHostIsFormatAvailableParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.format, 8);
            arg3.encode(this.buffer, 12);
        }
    }

    final class ClipboardHostIsFormatAvailableResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean result;

        static {
            ClipboardHostIsFormatAvailableResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostIsFormatAvailableResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostIsFormatAvailableResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostIsFormatAvailableResponseParams() {
            this(0);
        }

        private ClipboardHostIsFormatAvailableResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostIsFormatAvailableResponseParams decode(Decoder arg3) {
            ClipboardHostIsFormatAvailableResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostIsFormatAvailableResponseParams(arg3.readAndValidateDataHeader(ClipboardHostIsFormatAvailableResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostIsFormatAvailableResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostIsFormatAvailableResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostIsFormatAvailableResponseParams deserialize(Message arg1) {
            return ClipboardHostIsFormatAvailableResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ClipboardHostIsFormatAvailableResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, 0);
        }
    }

    class ClipboardHostIsFormatAvailableResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final IsFormatAvailableResponse mCallback;

        ClipboardHostIsFormatAvailableResponseParamsForwardToCallback(IsFormatAvailableResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(ClipboardHostIsFormatAvailableResponseParams.deserialize(v5.getPayload()).result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostIsFormatAvailableResponseParamsProxyToResponder implements IsFormatAvailableResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostIsFormatAvailableResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            ClipboardHostIsFormatAvailableResponseParams v0 = new ClipboardHostIsFormatAvailableResponseParams();
            v0.result = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class ClipboardHostReadAvailableTypesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostReadAvailableTypesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadAvailableTypesParams.DEFAULT_STRUCT_INFO = ClipboardHostReadAvailableTypesParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadAvailableTypesParams() {
            this(0);
        }

        private ClipboardHostReadAvailableTypesParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadAvailableTypesParams decode(Decoder arg2) {
            ClipboardHostReadAvailableTypesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadAvailableTypesParams(arg2.readAndValidateDataHeader(ClipboardHostReadAvailableTypesParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadAvailableTypesParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadAvailableTypesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadAvailableTypesParams deserialize(Message arg1) {
            return ClipboardHostReadAvailableTypesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostReadAvailableTypesParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostReadAvailableTypesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean result;
        public String16[] types;

        static {
            ClipboardHostReadAvailableTypesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ClipboardHostReadAvailableTypesResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostReadAvailableTypesResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadAvailableTypesResponseParams() {
            this(0);
        }

        private ClipboardHostReadAvailableTypesResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ClipboardHostReadAvailableTypesResponseParams decode(Decoder arg8) {
            ClipboardHostReadAvailableTypesResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadAvailableTypesResponseParams(arg8.readAndValidateDataHeader(ClipboardHostReadAvailableTypesResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                Decoder v3 = arg8.readPointer(v0_1, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.types = new String16[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.types[v5] = String16.decode(v3.readPointer(v5 * 8 + v0_1, false));
                }

                v1.result = arg8.readBoolean(16, 0);
            }
            catch(Throwable v0) {
                goto label_34;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_34:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static ClipboardHostReadAvailableTypesResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadAvailableTypesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadAvailableTypesResponseParams deserialize(Message arg1) {
            return ClipboardHostReadAvailableTypesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg7) {
            arg7 = arg7.getEncoderAtDataOffset(ClipboardHostReadAvailableTypesResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.types == null) {
                arg7.encodeNullPointer(v2, false);
            }
            else {
                Encoder v0 = arg7.encodePointerArray(this.types.length, v2, -1);
                int v3;
                for(v3 = 0; v3 < this.types.length; ++v3) {
                    v0.encode(this.types[v3], v3 * 8 + v2, false);
                }
            }

            arg7.encode(this.result, 16, 0);
        }
    }

    class ClipboardHostReadAvailableTypesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadAvailableTypesResponse mCallback;

        ClipboardHostReadAvailableTypesResponseParamsForwardToCallback(ReadAvailableTypesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                ClipboardHostReadAvailableTypesResponseParams v4_1 = ClipboardHostReadAvailableTypesResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(v4_1.types, Boolean.valueOf(v4_1.result));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostReadAvailableTypesResponseParamsProxyToResponder implements ReadAvailableTypesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostReadAvailableTypesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2) {
            this.call(((String16[])arg1), ((Boolean)arg2));
        }

        public void call(String16[] arg5, Boolean arg6) {
            ClipboardHostReadAvailableTypesResponseParams v0 = new ClipboardHostReadAvailableTypesResponseParams();
            v0.types = arg5;
            v0.result = arg6.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class ClipboardHostReadCustomDataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;
        public String16 type;

        static {
            ClipboardHostReadCustomDataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ClipboardHostReadCustomDataParams.DEFAULT_STRUCT_INFO = ClipboardHostReadCustomDataParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadCustomDataParams() {
            this(0);
        }

        private ClipboardHostReadCustomDataParams(int arg2) {
            super(24, arg2);
        }

        public static ClipboardHostReadCustomDataParams decode(Decoder arg3) {
            ClipboardHostReadCustomDataParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadCustomDataParams(arg3.readAndValidateDataHeader(ClipboardHostReadCustomDataParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg3.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
                v1.type = String16.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadCustomDataParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadCustomDataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadCustomDataParams deserialize(Message arg1) {
            return ClipboardHostReadCustomDataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ClipboardHostReadCustomDataParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.buffer, 8);
            arg4.encode(this.type, 16, false);
        }
    }

    final class ClipboardHostReadCustomDataResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public BigString16 result;

        static {
            ClipboardHostReadCustomDataResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadCustomDataResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostReadCustomDataResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadCustomDataResponseParams() {
            this(0);
        }

        private ClipboardHostReadCustomDataResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadCustomDataResponseParams decode(Decoder arg3) {
            ClipboardHostReadCustomDataResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadCustomDataResponseParams(arg3.readAndValidateDataHeader(ClipboardHostReadCustomDataResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = BigString16.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadCustomDataResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadCustomDataResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadCustomDataResponseParams deserialize(Message arg1) {
            return ClipboardHostReadCustomDataResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ClipboardHostReadCustomDataResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class ClipboardHostReadCustomDataResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadCustomDataResponse mCallback;

        ClipboardHostReadCustomDataResponseParamsForwardToCallback(ReadCustomDataResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(7, 2)) {
                    return 0;
                }

                this.mCallback.call(ClipboardHostReadCustomDataResponseParams.deserialize(v5.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostReadCustomDataResponseParamsProxyToResponder implements ReadCustomDataResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostReadCustomDataResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((BigString16)arg1));
        }

        public void call(BigString16 arg7) {
            ClipboardHostReadCustomDataResponseParams v0 = new ClipboardHostReadCustomDataResponseParams();
            v0.result = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(7, 2, this.mRequestId)));
        }
    }

    final class ClipboardHostReadHtmlParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostReadHtmlParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadHtmlParams.DEFAULT_STRUCT_INFO = ClipboardHostReadHtmlParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadHtmlParams() {
            this(0);
        }

        private ClipboardHostReadHtmlParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadHtmlParams decode(Decoder arg2) {
            ClipboardHostReadHtmlParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadHtmlParams(arg2.readAndValidateDataHeader(ClipboardHostReadHtmlParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadHtmlParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadHtmlParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadHtmlParams deserialize(Message arg1) {
            return ClipboardHostReadHtmlParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostReadHtmlParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostReadHtmlResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int fragmentEnd;
        public int fragmentStart;
        public BigString16 markup;
        public Url url;

        static {
            ClipboardHostReadHtmlResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ClipboardHostReadHtmlResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostReadHtmlResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadHtmlResponseParams() {
            this(0);
        }

        private ClipboardHostReadHtmlResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static ClipboardHostReadHtmlResponseParams decode(Decoder arg3) {
            ClipboardHostReadHtmlResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadHtmlResponseParams(arg3.readAndValidateDataHeader(ClipboardHostReadHtmlResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.markup = BigString16.decode(arg3.readPointer(8, false));
                v1.url = Url.decode(arg3.readPointer(16, false));
                v1.fragmentStart = arg3.readInt(24);
                v1.fragmentEnd = arg3.readInt(28);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadHtmlResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadHtmlResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadHtmlResponseParams deserialize(Message arg1) {
            return ClipboardHostReadHtmlResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ClipboardHostReadHtmlResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.markup, 8, false);
            arg4.encode(this.url, 16, false);
            arg4.encode(this.fragmentStart, 24);
            arg4.encode(this.fragmentEnd, 28);
        }
    }

    class ClipboardHostReadHtmlResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadHtmlResponse mCallback;

        ClipboardHostReadHtmlResponseParamsForwardToCallback(ReadHtmlResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6 = arg6.asServiceMessage();
                if(!v6.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                ClipboardHostReadHtmlResponseParams v6_1 = ClipboardHostReadHtmlResponseParams.deserialize(v6.getPayload());
                this.mCallback.call(v6_1.markup, v6_1.url, Integer.valueOf(v6_1.fragmentStart), Integer.valueOf(v6_1.fragmentEnd));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostReadHtmlResponseParamsProxyToResponder implements ReadHtmlResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostReadHtmlResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2, Object arg3, Object arg4) {
            this.call(((BigString16)arg1), ((Url)arg2), ((Integer)arg3), ((Integer)arg4));
        }

        public void call(BigString16 arg4, Url arg5, Integer arg6, Integer arg7) {
            ClipboardHostReadHtmlResponseParams v0 = new ClipboardHostReadHtmlResponseParams();
            v0.markup = arg4;
            v0.url = arg5;
            v0.fragmentStart = arg6.intValue();
            v0.fragmentEnd = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }
    }

    final class ClipboardHostReadImageParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostReadImageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadImageParams.DEFAULT_STRUCT_INFO = ClipboardHostReadImageParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadImageParams() {
            this(0);
        }

        private ClipboardHostReadImageParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadImageParams decode(Decoder arg2) {
            ClipboardHostReadImageParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadImageParams(arg2.readAndValidateDataHeader(ClipboardHostReadImageParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadImageParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadImageParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadImageParams deserialize(Message arg1) {
            return ClipboardHostReadImageParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostReadImageParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostReadImageResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SerializedBlob blob;

        static {
            ClipboardHostReadImageResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadImageResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostReadImageResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadImageResponseParams() {
            this(0);
        }

        private ClipboardHostReadImageResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadImageResponseParams decode(Decoder arg3) {
            ClipboardHostReadImageResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadImageResponseParams(arg3.readAndValidateDataHeader(ClipboardHostReadImageResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.blob = SerializedBlob.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadImageResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadImageResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadImageResponseParams deserialize(Message arg1) {
            return ClipboardHostReadImageResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ClipboardHostReadImageResponseParams.DEFAULT_STRUCT_INFO).encode(this.blob, 8, true);
        }
    }

    class ClipboardHostReadImageResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadImageResponse mCallback;

        ClipboardHostReadImageResponseParamsForwardToCallback(ReadImageResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(6, 2)) {
                    return 0;
                }

                this.mCallback.call(ClipboardHostReadImageResponseParams.deserialize(v5.getPayload()).blob);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostReadImageResponseParamsProxyToResponder implements ReadImageResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostReadImageResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((SerializedBlob)arg1));
        }

        public void call(SerializedBlob arg7) {
            ClipboardHostReadImageResponseParams v0 = new ClipboardHostReadImageResponseParams();
            v0.blob = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(6, 2, this.mRequestId)));
        }
    }

    final class ClipboardHostReadRtfParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostReadRtfParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadRtfParams.DEFAULT_STRUCT_INFO = ClipboardHostReadRtfParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadRtfParams() {
            this(0);
        }

        private ClipboardHostReadRtfParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadRtfParams decode(Decoder arg2) {
            ClipboardHostReadRtfParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadRtfParams(arg2.readAndValidateDataHeader(ClipboardHostReadRtfParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadRtfParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadRtfParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadRtfParams deserialize(Message arg1) {
            return ClipboardHostReadRtfParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostReadRtfParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostReadRtfResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String result;

        static {
            ClipboardHostReadRtfResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadRtfResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostReadRtfResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadRtfResponseParams() {
            this(0);
        }

        private ClipboardHostReadRtfResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadRtfResponseParams decode(Decoder arg3) {
            ClipboardHostReadRtfResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadRtfResponseParams(arg3.readAndValidateDataHeader(ClipboardHostReadRtfResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadRtfResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadRtfResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadRtfResponseParams deserialize(Message arg1) {
            return ClipboardHostReadRtfResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ClipboardHostReadRtfResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class ClipboardHostReadRtfResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadRtfResponse mCallback;

        ClipboardHostReadRtfResponseParamsForwardToCallback(ReadRtfResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                this.mCallback.call(ClipboardHostReadRtfResponseParams.deserialize(v5.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostReadRtfResponseParamsProxyToResponder implements ReadRtfResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostReadRtfResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((String)arg1));
        }

        public void call(String arg7) {
            ClipboardHostReadRtfResponseParams v0 = new ClipboardHostReadRtfResponseParams();
            v0.result = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }
    }

    final class ClipboardHostReadTextParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostReadTextParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadTextParams.DEFAULT_STRUCT_INFO = ClipboardHostReadTextParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadTextParams() {
            this(0);
        }

        private ClipboardHostReadTextParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadTextParams decode(Decoder arg2) {
            ClipboardHostReadTextParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadTextParams(arg2.readAndValidateDataHeader(ClipboardHostReadTextParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadTextParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadTextParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadTextParams deserialize(Message arg1) {
            return ClipboardHostReadTextParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostReadTextParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostReadTextResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public BigString16 result;

        static {
            ClipboardHostReadTextResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostReadTextResponseParams.DEFAULT_STRUCT_INFO = ClipboardHostReadTextResponseParams.VERSION_ARRAY[0];
        }

        public ClipboardHostReadTextResponseParams() {
            this(0);
        }

        private ClipboardHostReadTextResponseParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostReadTextResponseParams decode(Decoder arg3) {
            ClipboardHostReadTextResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostReadTextResponseParams(arg3.readAndValidateDataHeader(ClipboardHostReadTextResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = BigString16.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostReadTextResponseParams deserialize(ByteBuffer arg2) {
            return ClipboardHostReadTextResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostReadTextResponseParams deserialize(Message arg1) {
            return ClipboardHostReadTextResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ClipboardHostReadTextResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class ClipboardHostReadTextResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadTextResponse mCallback;

        ClipboardHostReadTextResponseParamsForwardToCallback(ReadTextResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(ClipboardHostReadTextResponseParams.deserialize(v5.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ClipboardHostReadTextResponseParamsProxyToResponder implements ReadTextResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ClipboardHostReadTextResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((BigString16)arg1));
        }

        public void call(BigString16 arg7) {
            ClipboardHostReadTextResponseParams v0 = new ClipboardHostReadTextResponseParams();
            v0.result = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    final class ClipboardHostWriteBookmarkParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;
        public String16 title;
        public String url;

        static {
            ClipboardHostWriteBookmarkParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ClipboardHostWriteBookmarkParams.DEFAULT_STRUCT_INFO = ClipboardHostWriteBookmarkParams.VERSION_ARRAY[0];
        }

        public ClipboardHostWriteBookmarkParams() {
            this(0);
        }

        private ClipboardHostWriteBookmarkParams(int arg2) {
            super(0x20, arg2);
        }

        public static ClipboardHostWriteBookmarkParams decode(Decoder arg3) {
            ClipboardHostWriteBookmarkParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostWriteBookmarkParams(arg3.readAndValidateDataHeader(ClipboardHostWriteBookmarkParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg3.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
                v1.url = arg3.readString(16, false);
                v1.title = String16.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostWriteBookmarkParams deserialize(ByteBuffer arg2) {
            return ClipboardHostWriteBookmarkParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostWriteBookmarkParams deserialize(Message arg1) {
            return ClipboardHostWriteBookmarkParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ClipboardHostWriteBookmarkParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.buffer, 8);
            arg4.encode(this.url, 16, false);
            arg4.encode(this.title, 24, false);
        }
    }

    final class ClipboardHostWriteCustomDataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;
        public Map data;

        static {
            ClipboardHostWriteCustomDataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ClipboardHostWriteCustomDataParams.DEFAULT_STRUCT_INFO = ClipboardHostWriteCustomDataParams.VERSION_ARRAY[0];
        }

        public ClipboardHostWriteCustomDataParams() {
            this(0);
        }

        private ClipboardHostWriteCustomDataParams(int arg2) {
            super(24, arg2);
        }

        public static ClipboardHostWriteCustomDataParams decode(Decoder arg10) {
            ClipboardHostWriteCustomDataParams v1;
            if(arg10 == null) {
                return null;
            }

            arg10.increaseStackDepth();
            try {
                v1 = new ClipboardHostWriteCustomDataParams(arg10.readAndValidateDataHeader(ClipboardHostWriteCustomDataParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.buffer = arg10.readInt(v0_1);
                ClipboardBuffer.validate(v1.buffer);
                int v2 = 16;
                int v3 = 0;
                Decoder v4 = arg10.readPointer(v2, false);
                v4.readDataHeaderForMap();
                Decoder v5 = v4.readPointer(v0_1, false);
                DataHeader v6 = v5.readDataHeaderForPointerArray(-1);
                String16[] v7 = new String16[v6.elementsOrVersion];
                int v8;
                for(v8 = 0; v8 < v6.elementsOrVersion; ++v8) {
                    v7[v8] = String16.decode(v5.readPointer(v8 * 8 + v0_1, false));
                }

                Decoder v2_1 = v4.readPointer(v2, false);
                DataHeader v4_1 = v2_1.readDataHeaderForPointerArray(v7.length);
                BigString16[] v5_1 = new BigString16[v4_1.elementsOrVersion];
                int v6_1;
                for(v6_1 = 0; v6_1 < v4_1.elementsOrVersion; ++v6_1) {
                    v5_1[v6_1] = BigString16.decode(v2_1.readPointer(v6_1 * 8 + v0_1, false));
                }

                v1.data = new HashMap();
                while(v3 < v7.length) {
                    v1.data.put(v7[v3], v5_1[v3]);
                    ++v3;
                }
            }
            catch(Throwable v0) {
                goto label_62;
            }

            arg10.decreaseStackDepth();
            return v1;
        label_62:
            arg10.decreaseStackDepth();
            throw v0;
        }

        public static ClipboardHostWriteCustomDataParams deserialize(ByteBuffer arg2) {
            return ClipboardHostWriteCustomDataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostWriteCustomDataParams deserialize(Message arg1) {
            return ClipboardHostWriteCustomDataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg11) {
            arg11 = arg11.getEncoderAtDataOffset(ClipboardHostWriteCustomDataParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg11.encode(this.buffer, v1);
            int v2 = 16;
            if(this.data == null) {
                arg11.encodeNullPointer(v2, false);
            }
            else {
                arg11 = arg11.encoderForMap(v2);
                int v0 = this.data.size();
                String16[] v4 = new String16[v0];
                BigString16[] v0_1 = new BigString16[v0];
                Iterator v5 = this.data.entrySet().iterator();
                int v6;
                for(v6 = 0; v5.hasNext(); ++v6) {
                    Object v7 = v5.next();
                    v4[v6] = ((Map$Entry)v7).getKey();
                    v0_1[v6] = ((Map$Entry)v7).getValue();
                }

                v6 = -1;
                Encoder v5_1 = arg11.encodePointerArray(v4.length, v1, v6);
                int v7_1;
                for(v7_1 = 0; v7_1 < v4.length; ++v7_1) {
                    v5_1.encode(v4[v7_1], v7_1 * 8 + v1, false);
                }

                arg11 = arg11.encodePointerArray(v0_1.length, v2, v6);
                for(v2 = 0; v2 < v0_1.length; ++v2) {
                    arg11.encode(v0_1[v2], v2 * 8 + v1, false);
                }
            }
        }
    }

    final class ClipboardHostWriteHtmlParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;
        public BigString16 markup;
        public Url url;

        static {
            ClipboardHostWriteHtmlParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ClipboardHostWriteHtmlParams.DEFAULT_STRUCT_INFO = ClipboardHostWriteHtmlParams.VERSION_ARRAY[0];
        }

        public ClipboardHostWriteHtmlParams() {
            this(0);
        }

        private ClipboardHostWriteHtmlParams(int arg2) {
            super(0x20, arg2);
        }

        public static ClipboardHostWriteHtmlParams decode(Decoder arg3) {
            ClipboardHostWriteHtmlParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostWriteHtmlParams(arg3.readAndValidateDataHeader(ClipboardHostWriteHtmlParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg3.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
                v1.markup = BigString16.decode(arg3.readPointer(16, false));
                v1.url = Url.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostWriteHtmlParams deserialize(ByteBuffer arg2) {
            return ClipboardHostWriteHtmlParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostWriteHtmlParams deserialize(Message arg1) {
            return ClipboardHostWriteHtmlParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ClipboardHostWriteHtmlParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.buffer, 8);
            arg4.encode(this.markup, 16, false);
            arg4.encode(this.url, 24, false);
        }
    }

    final class ClipboardHostWriteImageParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;
        public SharedBufferHandle sharedBufferHandle;
        public Size sizeInPixels;

        static {
            ClipboardHostWriteImageParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ClipboardHostWriteImageParams.DEFAULT_STRUCT_INFO = ClipboardHostWriteImageParams.VERSION_ARRAY[0];
        }

        public ClipboardHostWriteImageParams() {
            this(0);
        }

        private ClipboardHostWriteImageParams(int arg2) {
            super(24, arg2);
            this.sharedBufferHandle = InvalidHandle.INSTANCE;
        }

        public static ClipboardHostWriteImageParams decode(Decoder arg3) {
            ClipboardHostWriteImageParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostWriteImageParams(arg3.readAndValidateDataHeader(ClipboardHostWriteImageParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg3.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
                v1.sharedBufferHandle = arg3.readSharedBufferHandle(12, false);
                v1.sizeInPixels = Size.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostWriteImageParams deserialize(ByteBuffer arg2) {
            return ClipboardHostWriteImageParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostWriteImageParams deserialize(Message arg1) {
            return ClipboardHostWriteImageParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ClipboardHostWriteImageParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.buffer, 8);
            arg4.encode(this.sharedBufferHandle, 12, false);
            arg4.encode(this.sizeInPixels, 16, false);
        }
    }

    final class ClipboardHostWriteSmartPasteMarkerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;

        static {
            ClipboardHostWriteSmartPasteMarkerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ClipboardHostWriteSmartPasteMarkerParams.DEFAULT_STRUCT_INFO = ClipboardHostWriteSmartPasteMarkerParams.VERSION_ARRAY[0];
        }

        public ClipboardHostWriteSmartPasteMarkerParams() {
            this(0);
        }

        private ClipboardHostWriteSmartPasteMarkerParams(int arg2) {
            super(16, arg2);
        }

        public static ClipboardHostWriteSmartPasteMarkerParams decode(Decoder arg2) {
            ClipboardHostWriteSmartPasteMarkerParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ClipboardHostWriteSmartPasteMarkerParams(arg2.readAndValidateDataHeader(ClipboardHostWriteSmartPasteMarkerParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg2.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostWriteSmartPasteMarkerParams deserialize(ByteBuffer arg2) {
            return ClipboardHostWriteSmartPasteMarkerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostWriteSmartPasteMarkerParams deserialize(Message arg1) {
            return ClipboardHostWriteSmartPasteMarkerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(ClipboardHostWriteSmartPasteMarkerParams.DEFAULT_STRUCT_INFO).encode(this.buffer, 8);
        }
    }

    final class ClipboardHostWriteTextParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int buffer;
        public BigString16 text;

        static {
            ClipboardHostWriteTextParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ClipboardHostWriteTextParams.DEFAULT_STRUCT_INFO = ClipboardHostWriteTextParams.VERSION_ARRAY[0];
        }

        public ClipboardHostWriteTextParams() {
            this(0);
        }

        private ClipboardHostWriteTextParams(int arg2) {
            super(24, arg2);
        }

        public static ClipboardHostWriteTextParams decode(Decoder arg3) {
            ClipboardHostWriteTextParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ClipboardHostWriteTextParams(arg3.readAndValidateDataHeader(ClipboardHostWriteTextParams.VERSION_ARRAY).elementsOrVersion);
                v1.buffer = arg3.readInt(8);
                ClipboardBuffer.validate(v1.buffer);
                v1.text = BigString16.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ClipboardHostWriteTextParams deserialize(ByteBuffer arg2) {
            return ClipboardHostWriteTextParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ClipboardHostWriteTextParams deserialize(Message arg1) {
            return ClipboardHostWriteTextParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ClipboardHostWriteTextParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.buffer, 8);
            arg4.encode(this.text, 16, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ClipboardHost$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void commitWrite(int arg5) {
            ClipboardHostCommitWriteParams v0 = new ClipboardHostCommitWriteParams();
            v0.buffer = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(14)));
        }

        public void getSequenceNumber(int arg8, GetSequenceNumberResponse arg9) {
            ClipboardHostGetSequenceNumberParams v0 = new ClipboardHostGetSequenceNumberParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ClipboardHostGetSequenceNumberResponseParamsForwardToCallback(arg9));
        }

        public void isFormatAvailable(int arg6, int arg7, IsFormatAvailableResponse arg8) {
            ClipboardHostIsFormatAvailableParams v0 = new ClipboardHostIsFormatAvailableParams();
            v0.format = arg6;
            v0.buffer = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new ClipboardHostIsFormatAvailableResponseParamsForwardToCallback(arg8));
        }

        public void readAvailableTypes(int arg8, ReadAvailableTypesResponse arg9) {
            ClipboardHostReadAvailableTypesParams v0 = new ClipboardHostReadAvailableTypesParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new ClipboardHostReadAvailableTypesResponseParamsForwardToCallback(arg9));
        }

        public void readCustomData(int arg7, String16 arg8, ReadCustomDataResponse arg9) {
            ClipboardHostReadCustomDataParams v0 = new ClipboardHostReadCustomDataParams();
            v0.buffer = arg7;
            v0.type = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7, 1, 0)), new ClipboardHostReadCustomDataResponseParamsForwardToCallback(arg9));
        }

        public void readHtml(int arg8, ReadHtmlResponse arg9) {
            ClipboardHostReadHtmlParams v0 = new ClipboardHostReadHtmlParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new ClipboardHostReadHtmlResponseParamsForwardToCallback(arg9));
        }

        public void readImage(int arg8, ReadImageResponse arg9) {
            ClipboardHostReadImageParams v0 = new ClipboardHostReadImageParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6, 1, 0)), new ClipboardHostReadImageResponseParamsForwardToCallback(arg9));
        }

        public void readRtf(int arg8, ReadRtfResponse arg9) {
            ClipboardHostReadRtfParams v0 = new ClipboardHostReadRtfParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new ClipboardHostReadRtfResponseParamsForwardToCallback(arg9));
        }

        public void readText(int arg8, ReadTextResponse arg9) {
            ClipboardHostReadTextParams v0 = new ClipboardHostReadTextParams();
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new ClipboardHostReadTextResponseParamsForwardToCallback(arg9));
        }

        public void writeBookmark(int arg3, String arg4, String16 arg5) {
            ClipboardHostWriteBookmarkParams v0 = new ClipboardHostWriteBookmarkParams();
            v0.buffer = arg3;
            v0.url = arg4;
            v0.title = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(12)));
        }

        public void writeCustomData(int arg4, Map arg5) {
            ClipboardHostWriteCustomDataParams v0 = new ClipboardHostWriteCustomDataParams();
            v0.buffer = arg4;
            v0.data = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(11)));
        }

        public void writeHtml(int arg3, BigString16 arg4, Url arg5) {
            ClipboardHostWriteHtmlParams v0 = new ClipboardHostWriteHtmlParams();
            v0.buffer = arg3;
            v0.markup = arg4;
            v0.url = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(9)));
        }

        public void writeImage(int arg3, Size arg4, SharedBufferHandle arg5) {
            ClipboardHostWriteImageParams v0 = new ClipboardHostWriteImageParams();
            v0.buffer = arg3;
            v0.sizeInPixels = arg4;
            v0.sharedBufferHandle = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(13)));
        }

        public void writeSmartPasteMarker(int arg5) {
            ClipboardHostWriteSmartPasteMarkerParams v0 = new ClipboardHostWriteSmartPasteMarkerParams();
            v0.buffer = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(10)));
        }

        public void writeText(int arg4, BigString16 arg5) {
            ClipboardHostWriteTextParams v0 = new ClipboardHostWriteTextParams();
            v0.buffer = arg4;
            v0.text = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ClipboardHost arg2) {
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
                    goto label_62;
                }

                switch(v1_1) {
                    case 8: {
                        goto label_55;
                    }
                    case 9: {
                        goto label_47;
                    }
                    case 10: {
                        goto label_41;
                    }
                    case 11: {
                        goto label_34;
                    }
                    case 12: {
                        goto label_26;
                    }
                    case 13: {
                        goto label_18;
                    }
                    case 14: {
                        goto label_12;
                    }
                }

                return 0;
            label_34:
                ClipboardHostWriteCustomDataParams v6_2 = ClipboardHostWriteCustomDataParams.deserialize(v6_1.getPayload());
                this.getImpl().writeCustomData(v6_2.buffer, v6_2.data);
                return 1;
            label_18:
                ClipboardHostWriteImageParams v6_3 = ClipboardHostWriteImageParams.deserialize(v6_1.getPayload());
                this.getImpl().writeImage(v6_3.buffer, v6_3.sizeInPixels, v6_3.sharedBufferHandle);
                return 1;
            label_55:
                ClipboardHostWriteTextParams v6_4 = ClipboardHostWriteTextParams.deserialize(v6_1.getPayload());
                this.getImpl().writeText(v6_4.buffer, v6_4.text);
                return 1;
            label_41:
                this.getImpl().writeSmartPasteMarker(ClipboardHostWriteSmartPasteMarkerParams.deserialize(v6_1.getPayload()).buffer);
                return 1;
            label_26:
                ClipboardHostWriteBookmarkParams v6_5 = ClipboardHostWriteBookmarkParams.deserialize(v6_1.getPayload());
                this.getImpl().writeBookmark(v6_5.buffer, v6_5.url, v6_5.title);
                return 1;
            label_12:
                this.getImpl().commitWrite(ClipboardHostCommitWriteParams.deserialize(v6_1.getPayload()).buffer);
                return 1;
            label_47:
                ClipboardHostWriteHtmlParams v6_6 = ClipboardHostWriteHtmlParams.deserialize(v6_1.getPayload());
                this.getImpl().writeHtml(v6_6.buffer, v6_6.markup, v6_6.url);
                return 1;
            label_62:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ClipboardHost_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
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
                        goto label_92;
                    }
                    case 0: {
                        goto label_82;
                    }
                    case 1: {
                        goto label_71;
                    }
                    case 2: {
                        goto label_61;
                    }
                    case 3: {
                        goto label_51;
                    }
                    case 4: {
                        goto label_41;
                    }
                    case 5: {
                        goto label_31;
                    }
                    case 6: {
                        goto label_21;
                    }
                    case 7: {
                        goto label_10;
                    }
                }

                return 0;
            label_82:
                this.getImpl().getSequenceNumber(ClipboardHostGetSequenceNumberParams.deserialize(v10_1.getPayload()).buffer, new ClipboardHostGetSequenceNumberResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_51:
                this.getImpl().readText(ClipboardHostReadTextParams.deserialize(v10_1.getPayload()).buffer, new ClipboardHostReadTextResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_21:
                this.getImpl().readImage(ClipboardHostReadImageParams.deserialize(v10_1.getPayload()).buffer, new ClipboardHostReadImageResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_71:
                ClipboardHostIsFormatAvailableParams v10_2 = ClipboardHostIsFormatAvailableParams.deserialize(v10_1.getPayload());
                this.getImpl().isFormatAvailable(v10_2.format, v10_2.buffer, new ClipboardHostIsFormatAvailableResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_41:
                this.getImpl().readHtml(ClipboardHostReadHtmlParams.deserialize(v10_1.getPayload()).buffer, new ClipboardHostReadHtmlResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_10:
                ClipboardHostReadCustomDataParams v10_3 = ClipboardHostReadCustomDataParams.deserialize(v10_1.getPayload());
                this.getImpl().readCustomData(v10_3.buffer, v10_3.type, new ClipboardHostReadCustomDataResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_92:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ClipboardHost_Internal.MANAGER, v10_1, arg11);
            label_61:
                this.getImpl().readAvailableTypes(ClipboardHostReadAvailableTypesParams.deserialize(v10_1.getPayload()).buffer, new ClipboardHostReadAvailableTypesResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_31:
                this.getImpl().readRtf(ClipboardHostReadRtfParams.deserialize(v10_1.getPayload()).buffer, new ClipboardHostReadRtfResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int COMMIT_WRITE_ORDINAL = 14;
    private static final int GET_SEQUENCE_NUMBER_ORDINAL = 0;
    private static final int IS_FORMAT_AVAILABLE_ORDINAL = 1;
    public static final Manager MANAGER = null;
    private static final int READ_AVAILABLE_TYPES_ORDINAL = 2;
    private static final int READ_CUSTOM_DATA_ORDINAL = 7;
    private static final int READ_HTML_ORDINAL = 4;
    private static final int READ_IMAGE_ORDINAL = 6;
    private static final int READ_RTF_ORDINAL = 5;
    private static final int READ_TEXT_ORDINAL = 3;
    private static final int WRITE_BOOKMARK_ORDINAL = 12;
    private static final int WRITE_CUSTOM_DATA_ORDINAL = 11;
    private static final int WRITE_HTML_ORDINAL = 9;
    private static final int WRITE_IMAGE_ORDINAL = 13;
    private static final int WRITE_SMART_PASTE_MARKER_ORDINAL = 10;
    private static final int WRITE_TEXT_ORDINAL = 8;

    static {
        ClipboardHost_Internal.MANAGER = new org.chromium.blink.mojom.ClipboardHost_Internal$1();
    }

    ClipboardHost_Internal() {
        super();
    }
}

