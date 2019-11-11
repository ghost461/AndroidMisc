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
import org.chromium.mojo_base.mojom.TimeDelta;
import org.chromium.mojo_base.mojom.UnguessableToken;
import org.chromium.url.mojom.Url;

class Renderer_Internal {
    final class org.chromium.media.mojom.Renderer_Internal$1 extends Manager {
        org.chromium.media.mojom.Renderer_Internal$1() {
            super();
        }

        public Renderer[] buildArray(int arg1) {
            return new Renderer[arg1];
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

        public Stub buildStub(Core arg2, Renderer arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Renderer)arg2));
        }

        public String getName() {
            return "media::mojom::Renderer";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.Renderer$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void flush(FlushResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new RendererFlushParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new RendererFlushResponseParamsForwardToCallback(arg8));
        }

        public void initialize(AssociatedInterfaceNotSupported arg5, DemuxerStream[] arg6, Url arg7, Url arg8, InitializeResponse arg9) {
            RendererInitializeParams v0 = new RendererInitializeParams();
            v0.client = arg5;
            v0.streams = arg6;
            v0.mediaUrl = arg7;
            v0.firstPartyForCookies = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new RendererInitializeResponseParamsForwardToCallback(arg9));
        }

        public void initiateScopedSurfaceRequest(InitiateScopedSurfaceRequestResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new RendererInitiateScopedSurfaceRequestParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6, 1, 0)), new RendererInitiateScopedSurfaceRequestResponseParamsForwardToCallback(arg9));
        }

        public void setCdm(int arg8, SetCdmResponse arg9) {
            RendererSetCdmParams v0 = new RendererSetCdmParams();
            v0.cdmId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new RendererSetCdmResponseParamsForwardToCallback(arg9));
        }

        public void setPlaybackRate(double arg4) {
            RendererSetPlaybackRateParams v0 = new RendererSetPlaybackRateParams();
            v0.playbackRate = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void setVolume(float arg5) {
            RendererSetVolumeParams v0 = new RendererSetVolumeParams();
            v0.volume = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void startPlayingFrom(TimeDelta arg5) {
            RendererStartPlayingFromParams v0 = new RendererStartPlayingFromParams();
            v0.time = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }
    }

    final class RendererFlushParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            RendererFlushParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            RendererFlushParams.DEFAULT_STRUCT_INFO = RendererFlushParams.VERSION_ARRAY[0];
        }

        public RendererFlushParams() {
            this(0);
        }

        private RendererFlushParams(int arg2) {
            super(8, arg2);
        }

        public static RendererFlushParams decode(Decoder arg2) {
            RendererFlushParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererFlushParams(arg2.readAndValidateDataHeader(RendererFlushParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererFlushParams deserialize(ByteBuffer arg2) {
            return RendererFlushParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererFlushParams deserialize(Message arg1) {
            return RendererFlushParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(RendererFlushParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class RendererFlushResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            RendererFlushResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            RendererFlushResponseParams.DEFAULT_STRUCT_INFO = RendererFlushResponseParams.VERSION_ARRAY[0];
        }

        public RendererFlushResponseParams() {
            this(0);
        }

        private RendererFlushResponseParams(int arg2) {
            super(8, arg2);
        }

        public static RendererFlushResponseParams decode(Decoder arg2) {
            RendererFlushResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererFlushResponseParams(arg2.readAndValidateDataHeader(RendererFlushResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererFlushResponseParams deserialize(ByteBuffer arg2) {
            return RendererFlushResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererFlushResponseParams deserialize(Message arg1) {
            return RendererFlushResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(RendererFlushResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class RendererFlushResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final FlushResponse mCallback;

        RendererFlushResponseParamsForwardToCallback(FlushResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RendererFlushResponseParamsProxyToResponder implements FlushResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RendererFlushResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new RendererFlushResponseParams().serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class RendererInitializeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public AssociatedInterfaceNotSupported client;
        public Url firstPartyForCookies;
        public Url mediaUrl;
        public DemuxerStream[] streams;

        static {
            RendererInitializeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            RendererInitializeParams.DEFAULT_STRUCT_INFO = RendererInitializeParams.VERSION_ARRAY[0];
        }

        public RendererInitializeParams() {
            this(0);
        }

        private RendererInitializeParams(int arg2) {
            super(40, arg2);
        }

        public static RendererInitializeParams decode(Decoder arg5) {
            RendererInitializeParams v1;
            if(arg5 == null) {
                return null;
            }

            arg5.increaseStackDepth();
            try {
                v1 = new RendererInitializeParams(arg5.readAndValidateDataHeader(RendererInitializeParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg5.readAssociatedServiceInterfaceNotSupported(8, false);
                v1.streams = arg5.readServiceInterfaces(16, 1, -1, DemuxerStream.MANAGER);
                v1.mediaUrl = Url.decode(arg5.readPointer(24, true));
                v1.firstPartyForCookies = Url.decode(arg5.readPointer(0x20, true));
            }
            catch(Throwable v0) {
                arg5.decreaseStackDepth();
                throw v0;
            }

            arg5.decreaseStackDepth();
            return v1;
        }

        public static RendererInitializeParams deserialize(ByteBuffer arg2) {
            return RendererInitializeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererInitializeParams deserialize(Message arg1) {
            return RendererInitializeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg8) {
            arg8 = arg8.getEncoderAtDataOffset(RendererInitializeParams.DEFAULT_STRUCT_INFO);
            arg8.encode(this.client, 8, false);
            arg8.encode(this.streams, 16, 1, -1, DemuxerStream.MANAGER);
            arg8.encode(this.mediaUrl, 24, true);
            arg8.encode(this.firstPartyForCookies, 0x20, true);
        }
    }

    final class RendererInitializeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            RendererInitializeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererInitializeResponseParams.DEFAULT_STRUCT_INFO = RendererInitializeResponseParams.VERSION_ARRAY[0];
        }

        public RendererInitializeResponseParams() {
            this(0);
        }

        private RendererInitializeResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RendererInitializeResponseParams decode(Decoder arg3) {
            RendererInitializeResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererInitializeResponseParams(arg3.readAndValidateDataHeader(RendererInitializeResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererInitializeResponseParams deserialize(ByteBuffer arg2) {
            return RendererInitializeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererInitializeResponseParams deserialize(Message arg1) {
            return RendererInitializeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererInitializeResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class RendererInitializeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitializeResponse mCallback;

        RendererInitializeResponseParamsForwardToCallback(InitializeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(RendererInitializeResponseParams.deserialize(v4.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RendererInitializeResponseParamsProxyToResponder implements InitializeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RendererInitializeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            RendererInitializeResponseParams v0 = new RendererInitializeResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class RendererInitiateScopedSurfaceRequestParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            RendererInitiateScopedSurfaceRequestParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            RendererInitiateScopedSurfaceRequestParams.DEFAULT_STRUCT_INFO = RendererInitiateScopedSurfaceRequestParams.VERSION_ARRAY[0];
        }

        public RendererInitiateScopedSurfaceRequestParams() {
            this(0);
        }

        private RendererInitiateScopedSurfaceRequestParams(int arg2) {
            super(8, arg2);
        }

        public static RendererInitiateScopedSurfaceRequestParams decode(Decoder arg2) {
            RendererInitiateScopedSurfaceRequestParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererInitiateScopedSurfaceRequestParams(arg2.readAndValidateDataHeader(RendererInitiateScopedSurfaceRequestParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererInitiateScopedSurfaceRequestParams deserialize(ByteBuffer arg2) {
            return RendererInitiateScopedSurfaceRequestParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererInitiateScopedSurfaceRequestParams deserialize(Message arg1) {
            return RendererInitiateScopedSurfaceRequestParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(RendererInitiateScopedSurfaceRequestParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class RendererInitiateScopedSurfaceRequestResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public UnguessableToken requestToken;

        static {
            RendererInitiateScopedSurfaceRequestResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererInitiateScopedSurfaceRequestResponseParams.DEFAULT_STRUCT_INFO = RendererInitiateScopedSurfaceRequestResponseParams.VERSION_ARRAY[0];
        }

        public RendererInitiateScopedSurfaceRequestResponseParams() {
            this(0);
        }

        private RendererInitiateScopedSurfaceRequestResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RendererInitiateScopedSurfaceRequestResponseParams decode(Decoder arg3) {
            RendererInitiateScopedSurfaceRequestResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererInitiateScopedSurfaceRequestResponseParams(arg3.readAndValidateDataHeader(RendererInitiateScopedSurfaceRequestResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.requestToken = UnguessableToken.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererInitiateScopedSurfaceRequestResponseParams deserialize(ByteBuffer arg2) {
            return RendererInitiateScopedSurfaceRequestResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererInitiateScopedSurfaceRequestResponseParams deserialize(Message arg1) {
            return RendererInitiateScopedSurfaceRequestResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererInitiateScopedSurfaceRequestResponseParams.DEFAULT_STRUCT_INFO).encode(this.requestToken, 8, false);
        }
    }

    class RendererInitiateScopedSurfaceRequestResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InitiateScopedSurfaceRequestResponse mCallback;

        RendererInitiateScopedSurfaceRequestResponseParamsForwardToCallback(InitiateScopedSurfaceRequestResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(6, 2)) {
                    return 0;
                }

                this.mCallback.call(RendererInitiateScopedSurfaceRequestResponseParams.deserialize(v5.getPayload()).requestToken);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RendererInitiateScopedSurfaceRequestResponseParamsProxyToResponder implements InitiateScopedSurfaceRequestResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RendererInitiateScopedSurfaceRequestResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((UnguessableToken)arg1));
        }

        public void call(UnguessableToken arg7) {
            RendererInitiateScopedSurfaceRequestResponseParams v0 = new RendererInitiateScopedSurfaceRequestResponseParams();
            v0.requestToken = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(6, 2, this.mRequestId)));
        }
    }

    final class RendererSetCdmParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int cdmId;

        static {
            RendererSetCdmParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererSetCdmParams.DEFAULT_STRUCT_INFO = RendererSetCdmParams.VERSION_ARRAY[0];
        }

        public RendererSetCdmParams() {
            this(0);
        }

        private RendererSetCdmParams(int arg2) {
            super(16, arg2);
        }

        public static RendererSetCdmParams decode(Decoder arg2) {
            RendererSetCdmParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererSetCdmParams(arg2.readAndValidateDataHeader(RendererSetCdmParams.VERSION_ARRAY).elementsOrVersion);
                v1.cdmId = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererSetCdmParams deserialize(ByteBuffer arg2) {
            return RendererSetCdmParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererSetCdmParams deserialize(Message arg1) {
            return RendererSetCdmParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(RendererSetCdmParams.DEFAULT_STRUCT_INFO).encode(this.cdmId, 8);
        }
    }

    final class RendererSetCdmResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            RendererSetCdmResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererSetCdmResponseParams.DEFAULT_STRUCT_INFO = RendererSetCdmResponseParams.VERSION_ARRAY[0];
        }

        public RendererSetCdmResponseParams() {
            this(0);
        }

        private RendererSetCdmResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RendererSetCdmResponseParams decode(Decoder arg3) {
            RendererSetCdmResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererSetCdmResponseParams(arg3.readAndValidateDataHeader(RendererSetCdmResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererSetCdmResponseParams deserialize(ByteBuffer arg2) {
            return RendererSetCdmResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererSetCdmResponseParams deserialize(Message arg1) {
            return RendererSetCdmResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererSetCdmResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class RendererSetCdmResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetCdmResponse mCallback;

        RendererSetCdmResponseParamsForwardToCallback(SetCdmResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(RendererSetCdmResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RendererSetCdmResponseParamsProxyToResponder implements SetCdmResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RendererSetCdmResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            RendererSetCdmResponseParams v0 = new RendererSetCdmResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class RendererSetPlaybackRateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public double playbackRate;

        static {
            RendererSetPlaybackRateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererSetPlaybackRateParams.DEFAULT_STRUCT_INFO = RendererSetPlaybackRateParams.VERSION_ARRAY[0];
        }

        public RendererSetPlaybackRateParams() {
            this(0);
        }

        private RendererSetPlaybackRateParams(int arg2) {
            super(16, arg2);
        }

        public static RendererSetPlaybackRateParams decode(Decoder arg4) {
            RendererSetPlaybackRateParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new RendererSetPlaybackRateParams(arg4.readAndValidateDataHeader(RendererSetPlaybackRateParams.VERSION_ARRAY).elementsOrVersion);
                v1.playbackRate = arg4.readDouble(8);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static RendererSetPlaybackRateParams deserialize(ByteBuffer arg2) {
            return RendererSetPlaybackRateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererSetPlaybackRateParams deserialize(Message arg1) {
            return RendererSetPlaybackRateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererSetPlaybackRateParams.DEFAULT_STRUCT_INFO).encode(this.playbackRate, 8);
        }
    }

    final class RendererSetVolumeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public float volume;

        static {
            RendererSetVolumeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererSetVolumeParams.DEFAULT_STRUCT_INFO = RendererSetVolumeParams.VERSION_ARRAY[0];
        }

        public RendererSetVolumeParams() {
            this(0);
        }

        private RendererSetVolumeParams(int arg2) {
            super(16, arg2);
        }

        public static RendererSetVolumeParams decode(Decoder arg2) {
            RendererSetVolumeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RendererSetVolumeParams(arg2.readAndValidateDataHeader(RendererSetVolumeParams.VERSION_ARRAY).elementsOrVersion);
                v1.volume = arg2.readFloat(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RendererSetVolumeParams deserialize(ByteBuffer arg2) {
            return RendererSetVolumeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererSetVolumeParams deserialize(Message arg1) {
            return RendererSetVolumeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(RendererSetVolumeParams.DEFAULT_STRUCT_INFO).encode(this.volume, 8);
        }
    }

    final class RendererStartPlayingFromParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public TimeDelta time;

        static {
            RendererStartPlayingFromParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RendererStartPlayingFromParams.DEFAULT_STRUCT_INFO = RendererStartPlayingFromParams.VERSION_ARRAY[0];
        }

        public RendererStartPlayingFromParams() {
            this(0);
        }

        private RendererStartPlayingFromParams(int arg2) {
            super(16, arg2);
        }

        public static RendererStartPlayingFromParams decode(Decoder arg3) {
            RendererStartPlayingFromParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RendererStartPlayingFromParams(arg3.readAndValidateDataHeader(RendererStartPlayingFromParams.VERSION_ARRAY).elementsOrVersion);
                v1.time = TimeDelta.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RendererStartPlayingFromParams deserialize(ByteBuffer arg2) {
            return RendererStartPlayingFromParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RendererStartPlayingFromParams deserialize(Message arg1) {
            return RendererStartPlayingFromParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RendererStartPlayingFromParams.DEFAULT_STRUCT_INFO).encode(this.time, 8, false);
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, Renderer arg2) {
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
                    goto label_30;
                }

                switch(v1_1) {
                    case 2: {
                        goto label_24;
                    }
                    case 3: {
                        goto label_18;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().setPlaybackRate(RendererSetPlaybackRateParams.deserialize(v6_1.getPayload()).playbackRate);
                return 1;
            label_24:
                this.getImpl().startPlayingFrom(RendererStartPlayingFromParams.deserialize(v6_1.getPayload()).time);
                return 1;
            label_12:
                this.getImpl().setVolume(RendererSetVolumeParams.deserialize(v6_1.getPayload()).volume);
                return 1;
            label_30:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Renderer_Internal.MANAGER, v6_1);
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
                        goto label_52;
                    }
                    case 0: {
                        goto label_38;
                    }
                    case 1: {
                        goto label_29;
                    }
                    case 5: {
                        goto label_19;
                    }
                    case 6: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                this.getImpl().setCdm(RendererSetCdmParams.deserialize(v13_1.getPayload()).cdmId, new RendererSetCdmResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_52:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Renderer_Internal.MANAGER, v13_1, arg14);
            label_38:
                RendererInitializeParams v13_2 = RendererInitializeParams.deserialize(v13_1.getPayload());
                this.getImpl().initialize(v13_2.client, v13_2.streams, v13_2.mediaUrl, v13_2.firstPartyForCookies, new RendererInitializeResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_10:
                RendererInitiateScopedSurfaceRequestParams.deserialize(v13_1.getPayload());
                this.getImpl().initiateScopedSurfaceRequest(new RendererInitiateScopedSurfaceRequestResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_29:
                RendererFlushParams.deserialize(v13_1.getPayload());
                this.getImpl().flush(new RendererFlushResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v13) {
                System.err.println(v13.toString());
                return 0;
            }
        }
    }

    private static final int FLUSH_ORDINAL = 1;
    private static final int INITIALIZE_ORDINAL = 0;
    private static final int INITIATE_SCOPED_SURFACE_REQUEST_ORDINAL = 6;
    public static final Manager MANAGER = null;
    private static final int SET_CDM_ORDINAL = 5;
    private static final int SET_PLAYBACK_RATE_ORDINAL = 3;
    private static final int SET_VOLUME_ORDINAL = 4;
    private static final int START_PLAYING_FROM_ORDINAL = 2;

    static {
        Renderer_Internal.MANAGER = new org.chromium.media.mojom.Renderer_Internal$1();
    }

    Renderer_Internal() {
        super();
    }
}

