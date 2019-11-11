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
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.InvalidHandle;

class Decryptor_Internal {
    final class org.chromium.media.mojom.Decryptor_Internal$1 extends Manager {
        org.chromium.media.mojom.Decryptor_Internal$1() {
            super();
        }

        public Decryptor[] buildArray(int arg1) {
            return new Decryptor[arg1];
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

        public Stub buildStub(Core arg2, Decryptor arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Decryptor)arg2));
        }

        public String getName() {
            return "media::mojom::Decryptor";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class DecryptorCancelDecryptParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int streamType;

        static {
            DecryptorCancelDecryptParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorCancelDecryptParams.DEFAULT_STRUCT_INFO = DecryptorCancelDecryptParams.VERSION_ARRAY[0];
        }

        public DecryptorCancelDecryptParams() {
            this(0);
        }

        private DecryptorCancelDecryptParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorCancelDecryptParams decode(Decoder arg2) {
            DecryptorCancelDecryptParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new DecryptorCancelDecryptParams(arg2.readAndValidateDataHeader(DecryptorCancelDecryptParams.VERSION_ARRAY).elementsOrVersion);
                v1.streamType = arg2.readInt(8);
                StreamType.validate(v1.streamType);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static DecryptorCancelDecryptParams deserialize(ByteBuffer arg2) {
            return DecryptorCancelDecryptParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorCancelDecryptParams deserialize(Message arg1) {
            return DecryptorCancelDecryptParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(DecryptorCancelDecryptParams.DEFAULT_STRUCT_INFO).encode(this.streamType, 8);
        }
    }

    final class DecryptorDecryptAndDecodeAudioParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public DecoderBuffer encrypted;

        static {
            DecryptorDecryptAndDecodeAudioParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorDecryptAndDecodeAudioParams.DEFAULT_STRUCT_INFO = DecryptorDecryptAndDecodeAudioParams.VERSION_ARRAY[0];
        }

        public DecryptorDecryptAndDecodeAudioParams() {
            this(0);
        }

        private DecryptorDecryptAndDecodeAudioParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorDecryptAndDecodeAudioParams decode(Decoder arg3) {
            DecryptorDecryptAndDecodeAudioParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorDecryptAndDecodeAudioParams(arg3.readAndValidateDataHeader(DecryptorDecryptAndDecodeAudioParams.VERSION_ARRAY).elementsOrVersion);
                v1.encrypted = DecoderBuffer.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorDecryptAndDecodeAudioParams deserialize(ByteBuffer arg2) {
            return DecryptorDecryptAndDecodeAudioParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorDecryptAndDecodeAudioParams deserialize(Message arg1) {
            return DecryptorDecryptAndDecodeAudioParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(DecryptorDecryptAndDecodeAudioParams.DEFAULT_STRUCT_INFO).encode(this.encrypted, 8, false);
        }
    }

    final class DecryptorDecryptAndDecodeAudioResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public AudioBuffer[] audioBuffers;
        public int status;

        static {
            DecryptorDecryptAndDecodeAudioResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            DecryptorDecryptAndDecodeAudioResponseParams.DEFAULT_STRUCT_INFO = DecryptorDecryptAndDecodeAudioResponseParams.VERSION_ARRAY[0];
        }

        public DecryptorDecryptAndDecodeAudioResponseParams() {
            this(0);
        }

        private DecryptorDecryptAndDecodeAudioResponseParams(int arg2) {
            super(24, arg2);
        }

        public static DecryptorDecryptAndDecodeAudioResponseParams decode(Decoder arg8) {
            DecryptorDecryptAndDecodeAudioResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new DecryptorDecryptAndDecodeAudioResponseParams(arg8.readAndValidateDataHeader(DecryptorDecryptAndDecodeAudioResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.status = arg8.readInt(v0_1);
                Status.validate(v1.status);
                Decoder v2 = arg8.readPointer(16, false);
                DataHeader v4 = v2.readDataHeaderForPointerArray(-1);
                v1.audioBuffers = new AudioBuffer[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.audioBuffers[v5] = AudioBuffer.decode(v2.readPointer(v5 * 8 + v0_1, false));
                }
            }
            catch(Throwable v0) {
                goto label_36;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_36:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static DecryptorDecryptAndDecodeAudioResponseParams deserialize(ByteBuffer arg2) {
            return DecryptorDecryptAndDecodeAudioResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorDecryptAndDecodeAudioResponseParams deserialize(Message arg1) {
            return DecryptorDecryptAndDecodeAudioResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(DecryptorDecryptAndDecodeAudioResponseParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg6.encode(this.status, v1);
            int v2 = 16;
            if(this.audioBuffers == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.audioBuffers.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.audioBuffers.length; ++v0) {
                    arg6.encode(this.audioBuffers[v0], v0 * 8 + v1, false);
                }
            }
        }
    }

    class DecryptorDecryptAndDecodeAudioResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DecryptAndDecodeAudioResponse mCallback;

        DecryptorDecryptAndDecodeAudioResponseParamsForwardToCallback(DecryptAndDecodeAudioResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                DecryptorDecryptAndDecodeAudioResponseParams v5_1 = DecryptorDecryptAndDecodeAudioResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.status), v5_1.audioBuffers);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class DecryptorDecryptAndDecodeAudioResponseParamsProxyToResponder implements DecryptAndDecodeAudioResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        DecryptorDecryptAndDecodeAudioResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, AudioBuffer[] arg7) {
            DecryptorDecryptAndDecodeAudioResponseParams v0 = new DecryptorDecryptAndDecodeAudioResponseParams();
            v0.status = arg6.intValue();
            v0.audioBuffers = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((AudioBuffer[])arg2));
        }
    }

    final class DecryptorDecryptAndDecodeVideoParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public DecoderBuffer encrypted;

        static {
            DecryptorDecryptAndDecodeVideoParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorDecryptAndDecodeVideoParams.DEFAULT_STRUCT_INFO = DecryptorDecryptAndDecodeVideoParams.VERSION_ARRAY[0];
        }

        public DecryptorDecryptAndDecodeVideoParams() {
            this(0);
        }

        private DecryptorDecryptAndDecodeVideoParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorDecryptAndDecodeVideoParams decode(Decoder arg3) {
            DecryptorDecryptAndDecodeVideoParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorDecryptAndDecodeVideoParams(arg3.readAndValidateDataHeader(DecryptorDecryptAndDecodeVideoParams.VERSION_ARRAY).elementsOrVersion);
                v1.encrypted = DecoderBuffer.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorDecryptAndDecodeVideoParams deserialize(ByteBuffer arg2) {
            return DecryptorDecryptAndDecodeVideoParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorDecryptAndDecodeVideoParams deserialize(Message arg1) {
            return DecryptorDecryptAndDecodeVideoParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(DecryptorDecryptAndDecodeVideoParams.DEFAULT_STRUCT_INFO).encode(this.encrypted, 8, false);
        }
    }

    final class DecryptorDecryptAndDecodeVideoResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public FrameResourceReleaser releaser;
        public int status;
        public VideoFrame videoFrame;

        static {
            DecryptorDecryptAndDecodeVideoResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            DecryptorDecryptAndDecodeVideoResponseParams.DEFAULT_STRUCT_INFO = DecryptorDecryptAndDecodeVideoResponseParams.VERSION_ARRAY[0];
        }

        public DecryptorDecryptAndDecodeVideoResponseParams() {
            this(0);
        }

        private DecryptorDecryptAndDecodeVideoResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static DecryptorDecryptAndDecodeVideoResponseParams decode(Decoder arg4) {
            DecryptorDecryptAndDecodeVideoResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new DecryptorDecryptAndDecodeVideoResponseParams(arg4.readAndValidateDataHeader(DecryptorDecryptAndDecodeVideoResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg4.readInt(8);
                Status.validate(v1.status);
                v1.videoFrame = VideoFrame.decode(arg4.readPointer(16, true));
                v1.releaser = arg4.readServiceInterface(24, true, FrameResourceReleaser.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static DecryptorDecryptAndDecodeVideoResponseParams deserialize(ByteBuffer arg2) {
            return DecryptorDecryptAndDecodeVideoResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorDecryptAndDecodeVideoResponseParams deserialize(Message arg1) {
            return DecryptorDecryptAndDecodeVideoResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(DecryptorDecryptAndDecodeVideoResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.status, 8);
            arg5.encode(this.videoFrame, 16, true);
            arg5.encode(this.releaser, 24, true, FrameResourceReleaser.MANAGER);
        }
    }

    class DecryptorDecryptAndDecodeVideoResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DecryptAndDecodeVideoResponse mCallback;

        DecryptorDecryptAndDecodeVideoResponseParamsForwardToCallback(DecryptAndDecodeVideoResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(6, 2)) {
                    return 0;
                }

                DecryptorDecryptAndDecodeVideoResponseParams v5_1 = DecryptorDecryptAndDecodeVideoResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.status), v5_1.videoFrame, v5_1.releaser);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class DecryptorDecryptAndDecodeVideoResponseParamsProxyToResponder implements DecryptAndDecodeVideoResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        DecryptorDecryptAndDecodeVideoResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, VideoFrame arg6, FrameResourceReleaser arg7) {
            DecryptorDecryptAndDecodeVideoResponseParams v0 = new DecryptorDecryptAndDecodeVideoResponseParams();
            v0.status = arg5.intValue();
            v0.videoFrame = arg6;
            v0.releaser = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(6, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((VideoFrame)arg2), ((FrameResourceReleaser)arg3));
        }
    }

    final class DecryptorDecryptParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public DecoderBuffer encrypted;
        public int streamType;

        static {
            DecryptorDecryptParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            DecryptorDecryptParams.DEFAULT_STRUCT_INFO = DecryptorDecryptParams.VERSION_ARRAY[0];
        }

        public DecryptorDecryptParams() {
            this(0);
        }

        private DecryptorDecryptParams(int arg2) {
            super(24, arg2);
        }

        public static DecryptorDecryptParams decode(Decoder arg3) {
            DecryptorDecryptParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorDecryptParams(arg3.readAndValidateDataHeader(DecryptorDecryptParams.VERSION_ARRAY).elementsOrVersion);
                v1.streamType = arg3.readInt(8);
                StreamType.validate(v1.streamType);
                v1.encrypted = DecoderBuffer.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorDecryptParams deserialize(ByteBuffer arg2) {
            return DecryptorDecryptParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorDecryptParams deserialize(Message arg1) {
            return DecryptorDecryptParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(DecryptorDecryptParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.streamType, 8);
            arg4.encode(this.encrypted, 16, false);
        }
    }

    final class DecryptorDecryptResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public DecoderBuffer buffer;
        public int status;

        static {
            DecryptorDecryptResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            DecryptorDecryptResponseParams.DEFAULT_STRUCT_INFO = DecryptorDecryptResponseParams.VERSION_ARRAY[0];
        }

        public DecryptorDecryptResponseParams() {
            this(0);
        }

        private DecryptorDecryptResponseParams(int arg2) {
            super(24, arg2);
        }

        public static DecryptorDecryptResponseParams decode(Decoder arg3) {
            DecryptorDecryptResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorDecryptResponseParams(arg3.readAndValidateDataHeader(DecryptorDecryptResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg3.readInt(8);
                Status.validate(v1.status);
                v1.buffer = DecoderBuffer.decode(arg3.readPointer(16, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorDecryptResponseParams deserialize(ByteBuffer arg2) {
            return DecryptorDecryptResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorDecryptResponseParams deserialize(Message arg1) {
            return DecryptorDecryptResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(DecryptorDecryptResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.buffer, 16, true);
        }
    }

    class DecryptorDecryptResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DecryptResponse mCallback;

        DecryptorDecryptResponseParamsForwardToCallback(DecryptResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                DecryptorDecryptResponseParams v5_1 = DecryptorDecryptResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.status), v5_1.buffer);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class DecryptorDecryptResponseParamsProxyToResponder implements DecryptResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        DecryptorDecryptResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, DecoderBuffer arg7) {
            DecryptorDecryptResponseParams v0 = new DecryptorDecryptResponseParams();
            v0.status = arg6.intValue();
            v0.buffer = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((DecoderBuffer)arg2));
        }
    }

    final class DecryptorDeinitializeDecoderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int streamType;

        static {
            DecryptorDeinitializeDecoderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorDeinitializeDecoderParams.DEFAULT_STRUCT_INFO = DecryptorDeinitializeDecoderParams.VERSION_ARRAY[0];
        }

        public DecryptorDeinitializeDecoderParams() {
            this(0);
        }

        private DecryptorDeinitializeDecoderParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorDeinitializeDecoderParams decode(Decoder arg2) {
            DecryptorDeinitializeDecoderParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new DecryptorDeinitializeDecoderParams(arg2.readAndValidateDataHeader(DecryptorDeinitializeDecoderParams.VERSION_ARRAY).elementsOrVersion);
                v1.streamType = arg2.readInt(8);
                StreamType.validate(v1.streamType);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static DecryptorDeinitializeDecoderParams deserialize(ByteBuffer arg2) {
            return DecryptorDeinitializeDecoderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorDeinitializeDecoderParams deserialize(Message arg1) {
            return DecryptorDeinitializeDecoderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(DecryptorDeinitializeDecoderParams.DEFAULT_STRUCT_INFO).encode(this.streamType, 8);
        }
    }

    final class DecryptorInitializeAudioDecoderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AudioDecoderConfig config;

        static {
            DecryptorInitializeAudioDecoderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorInitializeAudioDecoderParams.DEFAULT_STRUCT_INFO = DecryptorInitializeAudioDecoderParams.VERSION_ARRAY[0];
        }

        public DecryptorInitializeAudioDecoderParams() {
            this(0);
        }

        private DecryptorInitializeAudioDecoderParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorInitializeAudioDecoderParams decode(Decoder arg3) {
            DecryptorInitializeAudioDecoderParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorInitializeAudioDecoderParams(arg3.readAndValidateDataHeader(DecryptorInitializeAudioDecoderParams.VERSION_ARRAY).elementsOrVersion);
                v1.config = AudioDecoderConfig.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorInitializeAudioDecoderParams deserialize(ByteBuffer arg2) {
            return DecryptorInitializeAudioDecoderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorInitializeAudioDecoderParams deserialize(Message arg1) {
            return DecryptorInitializeAudioDecoderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(DecryptorInitializeAudioDecoderParams.DEFAULT_STRUCT_INFO).encode(this.config, 8, false);
        }
    }

    final class DecryptorInitializeAudioDecoderResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            DecryptorInitializeAudioDecoderResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorInitializeAudioDecoderResponseParams.DEFAULT_STRUCT_INFO = DecryptorInitializeAudioDecoderResponseParams.VERSION_ARRAY[0];
        }

        public DecryptorInitializeAudioDecoderResponseParams() {
            this(0);
        }

        private DecryptorInitializeAudioDecoderResponseParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorInitializeAudioDecoderResponseParams decode(Decoder arg3) {
            DecryptorInitializeAudioDecoderResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorInitializeAudioDecoderResponseParams(arg3.readAndValidateDataHeader(DecryptorInitializeAudioDecoderResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorInitializeAudioDecoderResponseParams deserialize(ByteBuffer arg2) {
            return DecryptorInitializeAudioDecoderResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorInitializeAudioDecoderResponseParams deserialize(Message arg1) {
            return DecryptorInitializeAudioDecoderResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(DecryptorInitializeAudioDecoderResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class DecryptorInitializeAudioDecoderResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeAudioDecoderResponse mCallback;

        DecryptorInitializeAudioDecoderResponseParamsForwardToCallback(InitializeAudioDecoderResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(DecryptorInitializeAudioDecoderResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class DecryptorInitializeAudioDecoderResponseParamsProxyToResponder implements InitializeAudioDecoderResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        DecryptorInitializeAudioDecoderResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            DecryptorInitializeAudioDecoderResponseParams v0 = new DecryptorInitializeAudioDecoderResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class DecryptorInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public ConsumerHandle audioPipe;
        public ConsumerHandle decryptPipe;
        public ProducerHandle decryptedPipe;
        public ConsumerHandle videoPipe;

        static {
            DecryptorInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            DecryptorInitializeParams.DEFAULT_STRUCT_INFO = DecryptorInitializeParams.VERSION_ARRAY[0];
        }

        public DecryptorInitializeParams() {
            this(0);
        }

        private DecryptorInitializeParams(int arg2) {
            super(24, arg2);
            this.audioPipe = InvalidHandle.INSTANCE;
            this.videoPipe = InvalidHandle.INSTANCE;
            this.decryptPipe = InvalidHandle.INSTANCE;
            this.decryptedPipe = InvalidHandle.INSTANCE;
        }

        public static DecryptorInitializeParams decode(Decoder arg3) {
            DecryptorInitializeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorInitializeParams(arg3.readAndValidateDataHeader(DecryptorInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.audioPipe = arg3.readConsumerHandle(8, false);
                v1.videoPipe = arg3.readConsumerHandle(12, false);
                v1.decryptPipe = arg3.readConsumerHandle(16, false);
                v1.decryptedPipe = arg3.readProducerHandle(20, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorInitializeParams deserialize(ByteBuffer arg2) {
            return DecryptorInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorInitializeParams deserialize(Message arg1) {
            return DecryptorInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(DecryptorInitializeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.audioPipe, 8, false);
            arg4.encode(this.videoPipe, 12, false);
            arg4.encode(this.decryptPipe, 16, false);
            arg4.encode(this.decryptedPipe, 20, false);
        }
    }

    final class DecryptorInitializeVideoDecoderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public VideoDecoderConfig config;

        static {
            DecryptorInitializeVideoDecoderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorInitializeVideoDecoderParams.DEFAULT_STRUCT_INFO = DecryptorInitializeVideoDecoderParams.VERSION_ARRAY[0];
        }

        public DecryptorInitializeVideoDecoderParams() {
            this(0);
        }

        private DecryptorInitializeVideoDecoderParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorInitializeVideoDecoderParams decode(Decoder arg3) {
            DecryptorInitializeVideoDecoderParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorInitializeVideoDecoderParams(arg3.readAndValidateDataHeader(DecryptorInitializeVideoDecoderParams.VERSION_ARRAY).elementsOrVersion);
                v1.config = VideoDecoderConfig.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorInitializeVideoDecoderParams deserialize(ByteBuffer arg2) {
            return DecryptorInitializeVideoDecoderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorInitializeVideoDecoderParams deserialize(Message arg1) {
            return DecryptorInitializeVideoDecoderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(DecryptorInitializeVideoDecoderParams.DEFAULT_STRUCT_INFO).encode(this.config, 8, false);
        }
    }

    final class DecryptorInitializeVideoDecoderResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            DecryptorInitializeVideoDecoderResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorInitializeVideoDecoderResponseParams.DEFAULT_STRUCT_INFO = DecryptorInitializeVideoDecoderResponseParams.VERSION_ARRAY[0];
        }

        public DecryptorInitializeVideoDecoderResponseParams() {
            this(0);
        }

        private DecryptorInitializeVideoDecoderResponseParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorInitializeVideoDecoderResponseParams decode(Decoder arg3) {
            DecryptorInitializeVideoDecoderResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new DecryptorInitializeVideoDecoderResponseParams(arg3.readAndValidateDataHeader(DecryptorInitializeVideoDecoderResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static DecryptorInitializeVideoDecoderResponseParams deserialize(ByteBuffer arg2) {
            return DecryptorInitializeVideoDecoderResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorInitializeVideoDecoderResponseParams deserialize(Message arg1) {
            return DecryptorInitializeVideoDecoderResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(DecryptorInitializeVideoDecoderResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class DecryptorInitializeVideoDecoderResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeVideoDecoderResponse mCallback;

        DecryptorInitializeVideoDecoderResponseParamsForwardToCallback(InitializeVideoDecoderResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(DecryptorInitializeVideoDecoderResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class DecryptorInitializeVideoDecoderResponseParamsProxyToResponder implements InitializeVideoDecoderResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        DecryptorInitializeVideoDecoderResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            DecryptorInitializeVideoDecoderResponseParams v0 = new DecryptorInitializeVideoDecoderResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class DecryptorResetDecoderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int streamType;

        static {
            DecryptorResetDecoderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            DecryptorResetDecoderParams.DEFAULT_STRUCT_INFO = DecryptorResetDecoderParams.VERSION_ARRAY[0];
        }

        public DecryptorResetDecoderParams() {
            this(0);
        }

        private DecryptorResetDecoderParams(int arg2) {
            super(16, arg2);
        }

        public static DecryptorResetDecoderParams decode(Decoder arg2) {
            DecryptorResetDecoderParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new DecryptorResetDecoderParams(arg2.readAndValidateDataHeader(DecryptorResetDecoderParams.VERSION_ARRAY).elementsOrVersion);
                v1.streamType = arg2.readInt(8);
                StreamType.validate(v1.streamType);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static DecryptorResetDecoderParams deserialize(ByteBuffer arg2) {
            return DecryptorResetDecoderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static DecryptorResetDecoderParams deserialize(Message arg1) {
            return DecryptorResetDecoderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(DecryptorResetDecoderParams.DEFAULT_STRUCT_INFO).encode(this.streamType, 8);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.Decryptor$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void cancelDecrypt(int arg5) {
            DecryptorCancelDecryptParams v0 = new DecryptorCancelDecryptParams();
            v0.streamType = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void decrypt(int arg6, DecoderBuffer arg7, DecryptResponse arg8) {
            DecryptorDecryptParams v0 = new DecryptorDecryptParams();
            v0.streamType = arg6;
            v0.encrypted = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new DecryptorDecryptResponseParamsForwardToCallback(arg8));
        }

        public void decryptAndDecodeAudio(DecoderBuffer arg8, DecryptAndDecodeAudioResponse arg9) {
            DecryptorDecryptAndDecodeAudioParams v0 = new DecryptorDecryptAndDecodeAudioParams();
            v0.encrypted = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new DecryptorDecryptAndDecodeAudioResponseParamsForwardToCallback(arg9));
        }

        public void decryptAndDecodeVideo(DecoderBuffer arg8, DecryptAndDecodeVideoResponse arg9) {
            DecryptorDecryptAndDecodeVideoParams v0 = new DecryptorDecryptAndDecodeVideoParams();
            v0.encrypted = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6, 1, 0)), new DecryptorDecryptAndDecodeVideoResponseParamsForwardToCallback(arg9));
        }

        public void deinitializeDecoder(int arg5) {
            DecryptorDeinitializeDecoderParams v0 = new DecryptorDeinitializeDecoderParams();
            v0.streamType = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8)));
        }

        public void initialize(ConsumerHandle arg2, ConsumerHandle arg3, ConsumerHandle arg4, ProducerHandle arg5) {
            DecryptorInitializeParams v0 = new DecryptorInitializeParams();
            v0.audioPipe = arg2;
            v0.videoPipe = arg3;
            v0.decryptPipe = arg4;
            v0.decryptedPipe = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void initializeAudioDecoder(AudioDecoderConfig arg8, InitializeAudioDecoderResponse arg9) {
            DecryptorInitializeAudioDecoderParams v0 = new DecryptorInitializeAudioDecoderParams();
            v0.config = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new DecryptorInitializeAudioDecoderResponseParamsForwardToCallback(arg9));
        }

        public void initializeVideoDecoder(VideoDecoderConfig arg8, InitializeVideoDecoderResponse arg9) {
            DecryptorInitializeVideoDecoderParams v0 = new DecryptorInitializeVideoDecoderParams();
            v0.config = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new DecryptorInitializeVideoDecoderResponseParamsForwardToCallback(arg9));
        }

        public void resetDecoder(int arg5) {
            DecryptorResetDecoderParams v0 = new DecryptorResetDecoderParams();
            v0.streamType = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, Decryptor arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_42;
                }

                if(v1_1 == 0) {
                    goto label_33;
                }

                if(v1_1 == 2) {
                    goto label_27;
                }

                switch(v1_1) {
                    case 7: {
                        goto label_21;
                    }
                    case 8: {
                        goto label_15;
                    }
                }

                return 0;
            label_21:
                this.getImpl().resetDecoder(DecryptorResetDecoderParams.deserialize(v7_1.getPayload()).streamType);
                return 1;
            label_15:
                this.getImpl().deinitializeDecoder(DecryptorDeinitializeDecoderParams.deserialize(v7_1.getPayload()).streamType);
                return 1;
            label_27:
                this.getImpl().cancelDecrypt(DecryptorCancelDecryptParams.deserialize(v7_1.getPayload()).streamType);
                return 1;
            label_33:
                DecryptorInitializeParams v7_2 = DecryptorInitializeParams.deserialize(v7_1.getPayload());
                this.getImpl().initialize(v7_2.audioPipe, v7_2.videoPipe, v7_2.decryptPipe, v7_2.decryptedPipe);
                return 1;
            label_42:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Decryptor_Internal.MANAGER, v7_1);
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
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
                    goto label_64;
                }

                if(v3 == 1) {
                    goto label_53;
                }

                switch(v3) {
                    case 3: {
                        goto label_43;
                    }
                    case 4: {
                        goto label_33;
                    }
                    case 5: {
                        goto label_23;
                    }
                    case 6: {
                        goto label_13;
                    }
                }

                return 0;
            label_33:
                this.getImpl().initializeVideoDecoder(DecryptorInitializeVideoDecoderParams.deserialize(v10_1.getPayload()).config, new DecryptorInitializeVideoDecoderResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_23:
                this.getImpl().decryptAndDecodeAudio(DecryptorDecryptAndDecodeAudioParams.deserialize(v10_1.getPayload()).encrypted, new DecryptorDecryptAndDecodeAudioResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_43:
                this.getImpl().initializeAudioDecoder(DecryptorInitializeAudioDecoderParams.deserialize(v10_1.getPayload()).config, new DecryptorInitializeAudioDecoderResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_13:
                this.getImpl().decryptAndDecodeVideo(DecryptorDecryptAndDecodeVideoParams.deserialize(v10_1.getPayload()).encrypted, new DecryptorDecryptAndDecodeVideoResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_53:
                DecryptorDecryptParams v10_2 = DecryptorDecryptParams.deserialize(v10_1.getPayload());
                this.getImpl().decrypt(v10_2.streamType, v10_2.encrypted, new DecryptorDecryptResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_64:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Decryptor_Internal.MANAGER, v10_1, arg11);
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int CANCEL_DECRYPT_ORDINAL = 2;
    private static final int DECRYPT_AND_DECODE_AUDIO_ORDINAL = 5;
    private static final int DECRYPT_AND_DECODE_VIDEO_ORDINAL = 6;
    private static final int DECRYPT_ORDINAL = 1;
    private static final int DEINITIALIZE_DECODER_ORDINAL = 8;
    private static final int INITIALIZE_AUDIO_DECODER_ORDINAL = 3;
    private static final int INITIALIZE_ORDINAL = 0;
    private static final int INITIALIZE_VIDEO_DECODER_ORDINAL = 4;
    public static final Manager MANAGER = null;
    private static final int RESET_DECODER_ORDINAL = 7;

    static {
        Decryptor_Internal.MANAGER = new org.chromium.media.mojom.Decryptor_Internal$1();
    }

    Decryptor_Internal() {
        super();
    }
}

