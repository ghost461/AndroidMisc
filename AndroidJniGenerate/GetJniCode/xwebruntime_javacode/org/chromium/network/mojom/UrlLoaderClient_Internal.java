package org.chromium.network.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Interface$AbstractProxy;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface$Proxy;
import org.chromium.mojo.bindings.Interface$Stub;
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
import org.chromium.mojo.system.InvalidHandle;

class UrlLoaderClient_Internal {
    final class org.chromium.network.mojom.UrlLoaderClient_Internal$1 extends Manager {
        org.chromium.network.mojom.UrlLoaderClient_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public UrlLoaderClient[] buildArray(int arg1) {
            return new UrlLoaderClient[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.UrlLoaderClient_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.UrlLoaderClient_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((UrlLoaderClient)arg2));
        }

        public org.chromium.network.mojom.UrlLoaderClient_Internal$Stub buildStub(Core arg2, UrlLoaderClient arg3) {
            return new org.chromium.network.mojom.UrlLoaderClient_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::URLLoaderClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.UrlLoaderClient_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.UrlLoaderClient$Proxy {
        org.chromium.network.mojom.UrlLoaderClient_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onComplete(UrlLoaderCompletionStatus arg5) {
            UrlLoaderClientOnCompleteParams v0 = new UrlLoaderClientOnCompleteParams();
            v0.status = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7)));
        }

        public void onDataDownloaded(long arg2, long arg4) {
            UrlLoaderClientOnDataDownloadedParams v0 = new UrlLoaderClientOnDataDownloadedParams();
            v0.dataLength = arg2;
            v0.encodedLength = arg4;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onReceiveCachedMetadata(byte[] arg5) {
            UrlLoaderClientOnReceiveCachedMetadataParams v0 = new UrlLoaderClientOnReceiveCachedMetadataParams();
            v0.data = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void onReceiveRedirect(UrlRequestRedirectInfo arg4, UrlResponseHead arg5) {
            UrlLoaderClientOnReceiveRedirectParams v0 = new UrlLoaderClientOnReceiveRedirectParams();
            v0.redirectInfo = arg4;
            v0.head = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onReceiveResponse(UrlResponseHead arg4, DownloadedTempFile arg5) {
            UrlLoaderClientOnReceiveResponseParams v0 = new UrlLoaderClientOnReceiveResponseParams();
            v0.head = arg4;
            v0.downloadedFile = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onStartLoadingResponseBody(ConsumerHandle arg5) {
            UrlLoaderClientOnStartLoadingResponseBodyParams v0 = new UrlLoaderClientOnStartLoadingResponseBodyParams();
            v0.body = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void onTransferSizeUpdated(int arg5) {
            UrlLoaderClientOnTransferSizeUpdatedParams v0 = new UrlLoaderClientOnTransferSizeUpdatedParams();
            v0.transferSizeDiff = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void onUploadProgress(long arg5, long arg7, OnUploadProgressResponse arg9) {
            UrlLoaderClientOnUploadProgressParams v0 = new UrlLoaderClientOnUploadProgressParams();
            v0.currentPosition = arg5;
            v0.totalSize = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new UrlLoaderClientOnUploadProgressResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.network.mojom.UrlLoaderClient_Internal$Stub extends Stub {
        org.chromium.network.mojom.UrlLoaderClient_Internal$Stub(Core arg1, UrlLoaderClient arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg8) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -2: {
                        goto label_55;
                    }
                    case 0: {
                        goto label_48;
                    }
                    case 1: {
                        goto label_41;
                    }
                    case 2: {
                        goto label_34;
                    }
                    case 4: {
                        goto label_28;
                    }
                    case 5: {
                        goto label_22;
                    }
                    case 6: {
                        goto label_16;
                    }
                    case 7: {
                        goto label_10;
                    }
                }

                return 0;
            label_34:
                UrlLoaderClientOnDataDownloadedParams v8_2 = UrlLoaderClientOnDataDownloadedParams.deserialize(v8_1.getPayload());
                this.getImpl().onDataDownloaded(v8_2.dataLength, v8_2.encodedLength);
                return 1;
            label_22:
                this.getImpl().onTransferSizeUpdated(UrlLoaderClientOnTransferSizeUpdatedParams.deserialize(v8_1.getPayload()).transferSizeDiff);
                return 1;
            label_55:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(UrlLoaderClient_Internal.MANAGER, v8_1);
            label_41:
                UrlLoaderClientOnReceiveRedirectParams v8_3 = UrlLoaderClientOnReceiveRedirectParams.deserialize(v8_1.getPayload());
                this.getImpl().onReceiveRedirect(v8_3.redirectInfo, v8_3.head);
                return 1;
            label_10:
                this.getImpl().onComplete(UrlLoaderClientOnCompleteParams.deserialize(v8_1.getPayload()).status);
                return 1;
            label_28:
                this.getImpl().onReceiveCachedMetadata(UrlLoaderClientOnReceiveCachedMetadataParams.deserialize(v8_1.getPayload()).data);
                return 1;
            label_48:
                UrlLoaderClientOnReceiveResponseParams v8_4 = UrlLoaderClientOnReceiveResponseParams.deserialize(v8_1.getPayload());
                this.getImpl().onReceiveResponse(v8_4.head, v8_4.downloadedFile);
                return 1;
            label_16:
                this.getImpl().onStartLoadingResponseBody(UrlLoaderClientOnStartLoadingResponseBodyParams.deserialize(v8_1.getPayload()).body);
                return 1;
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
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

                int v3 = v1.getType();
                if(v3 != -1) {
                    if(v3 != 3) {
                        return 0;
                    }

                    UrlLoaderClientOnUploadProgressParams v13_2 = UrlLoaderClientOnUploadProgressParams.deserialize(v13_1.getPayload());
                    this.getImpl().onUploadProgress(v13_2.currentPosition, v13_2.totalSize, new UrlLoaderClientOnUploadProgressResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), UrlLoaderClient_Internal.MANAGER, v13_1, arg14);
            }
            catch(DeserializationException v13) {
                System.err.println(v13.toString());
                return 0;
            }
        }
    }

    final class UrlLoaderClientOnCompleteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public UrlLoaderCompletionStatus status;

        static {
            UrlLoaderClientOnCompleteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UrlLoaderClientOnCompleteParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnCompleteParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnCompleteParams() {
            this(0);
        }

        private UrlLoaderClientOnCompleteParams(int arg2) {
            super(16, arg2);
        }

        public static UrlLoaderClientOnCompleteParams decode(Decoder arg3) {
            UrlLoaderClientOnCompleteParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnCompleteParams(arg3.readAndValidateDataHeader(UrlLoaderClientOnCompleteParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = UrlLoaderCompletionStatus.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnCompleteParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnCompleteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnCompleteParams deserialize(Message arg1) {
            return UrlLoaderClientOnCompleteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(UrlLoaderClientOnCompleteParams.DEFAULT_STRUCT_INFO).encode(this.status, 8, false);
        }
    }

    final class UrlLoaderClientOnDataDownloadedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public long dataLength;
        public long encodedLength;

        static {
            UrlLoaderClientOnDataDownloadedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UrlLoaderClientOnDataDownloadedParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnDataDownloadedParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnDataDownloadedParams() {
            this(0);
        }

        private UrlLoaderClientOnDataDownloadedParams(int arg2) {
            super(24, arg2);
        }

        public static UrlLoaderClientOnDataDownloadedParams decode(Decoder arg4) {
            UrlLoaderClientOnDataDownloadedParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnDataDownloadedParams(arg4.readAndValidateDataHeader(UrlLoaderClientOnDataDownloadedParams.VERSION_ARRAY).elementsOrVersion);
                v1.dataLength = arg4.readLong(8);
                v1.encodedLength = arg4.readLong(16);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnDataDownloadedParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnDataDownloadedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnDataDownloadedParams deserialize(Message arg1) {
            return UrlLoaderClientOnDataDownloadedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UrlLoaderClientOnDataDownloadedParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.dataLength, 8);
            arg4.encode(this.encodedLength, 16);
        }
    }

    final class UrlLoaderClientOnReceiveCachedMetadataParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;

        static {
            UrlLoaderClientOnReceiveCachedMetadataParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UrlLoaderClientOnReceiveCachedMetadataParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnReceiveCachedMetadataParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnReceiveCachedMetadataParams() {
            this(0);
        }

        private UrlLoaderClientOnReceiveCachedMetadataParams(int arg2) {
            super(16, arg2);
        }

        public static UrlLoaderClientOnReceiveCachedMetadataParams decode(Decoder arg4) {
            UrlLoaderClientOnReceiveCachedMetadataParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnReceiveCachedMetadataParams(arg4.readAndValidateDataHeader(UrlLoaderClientOnReceiveCachedMetadataParams.VERSION_ARRAY).elementsOrVersion);
                v1.data = arg4.readBytes(8, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnReceiveCachedMetadataParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnReceiveCachedMetadataParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnReceiveCachedMetadataParams deserialize(Message arg1) {
            return UrlLoaderClientOnReceiveCachedMetadataParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(UrlLoaderClientOnReceiveCachedMetadataParams.DEFAULT_STRUCT_INFO).encode(this.data, 8, 0, -1);
        }
    }

    final class UrlLoaderClientOnReceiveRedirectParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public UrlResponseHead head;
        public UrlRequestRedirectInfo redirectInfo;

        static {
            UrlLoaderClientOnReceiveRedirectParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UrlLoaderClientOnReceiveRedirectParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnReceiveRedirectParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnReceiveRedirectParams() {
            this(0);
        }

        private UrlLoaderClientOnReceiveRedirectParams(int arg2) {
            super(24, arg2);
        }

        public static UrlLoaderClientOnReceiveRedirectParams decode(Decoder arg3) {
            UrlLoaderClientOnReceiveRedirectParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnReceiveRedirectParams(arg3.readAndValidateDataHeader(UrlLoaderClientOnReceiveRedirectParams.VERSION_ARRAY).elementsOrVersion);
                v1.redirectInfo = UrlRequestRedirectInfo.decode(arg3.readPointer(8, false));
                v1.head = UrlResponseHead.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnReceiveRedirectParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnReceiveRedirectParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnReceiveRedirectParams deserialize(Message arg1) {
            return UrlLoaderClientOnReceiveRedirectParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UrlLoaderClientOnReceiveRedirectParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.redirectInfo, 8, false);
            arg4.encode(this.head, 16, false);
        }
    }

    final class UrlLoaderClientOnReceiveResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public DownloadedTempFile downloadedFile;
        public UrlResponseHead head;

        static {
            UrlLoaderClientOnReceiveResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UrlLoaderClientOnReceiveResponseParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnReceiveResponseParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnReceiveResponseParams() {
            this(0);
        }

        private UrlLoaderClientOnReceiveResponseParams(int arg2) {
            super(24, arg2);
        }

        public static UrlLoaderClientOnReceiveResponseParams decode(Decoder arg4) {
            UrlLoaderClientOnReceiveResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnReceiveResponseParams(arg4.readAndValidateDataHeader(UrlLoaderClientOnReceiveResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.head = UrlResponseHead.decode(arg4.readPointer(8, false));
                v1.downloadedFile = arg4.readServiceInterface(16, true, DownloadedTempFile.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnReceiveResponseParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnReceiveResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnReceiveResponseParams deserialize(Message arg1) {
            return UrlLoaderClientOnReceiveResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(UrlLoaderClientOnReceiveResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.head, 8, false);
            arg5.encode(this.downloadedFile, 16, true, DownloadedTempFile.MANAGER);
        }
    }

    final class UrlLoaderClientOnStartLoadingResponseBodyParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public ConsumerHandle body;

        static {
            UrlLoaderClientOnStartLoadingResponseBodyParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UrlLoaderClientOnStartLoadingResponseBodyParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnStartLoadingResponseBodyParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnStartLoadingResponseBodyParams() {
            this(0);
        }

        private UrlLoaderClientOnStartLoadingResponseBodyParams(int arg2) {
            super(16, arg2);
            this.body = InvalidHandle.INSTANCE;
        }

        public static UrlLoaderClientOnStartLoadingResponseBodyParams decode(Decoder arg3) {
            UrlLoaderClientOnStartLoadingResponseBodyParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnStartLoadingResponseBodyParams(arg3.readAndValidateDataHeader(UrlLoaderClientOnStartLoadingResponseBodyParams.VERSION_ARRAY).elementsOrVersion);
                v1.body = arg3.readConsumerHandle(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnStartLoadingResponseBodyParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnStartLoadingResponseBodyParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnStartLoadingResponseBodyParams deserialize(Message arg1) {
            return UrlLoaderClientOnStartLoadingResponseBodyParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(UrlLoaderClientOnStartLoadingResponseBodyParams.DEFAULT_STRUCT_INFO).encode(this.body, 8, false);
        }
    }

    final class UrlLoaderClientOnTransferSizeUpdatedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int transferSizeDiff;

        static {
            UrlLoaderClientOnTransferSizeUpdatedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            UrlLoaderClientOnTransferSizeUpdatedParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnTransferSizeUpdatedParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnTransferSizeUpdatedParams() {
            this(0);
        }

        private UrlLoaderClientOnTransferSizeUpdatedParams(int arg2) {
            super(16, arg2);
        }

        public static UrlLoaderClientOnTransferSizeUpdatedParams decode(Decoder arg2) {
            UrlLoaderClientOnTransferSizeUpdatedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnTransferSizeUpdatedParams(arg2.readAndValidateDataHeader(UrlLoaderClientOnTransferSizeUpdatedParams.VERSION_ARRAY).elementsOrVersion);
                v1.transferSizeDiff = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnTransferSizeUpdatedParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnTransferSizeUpdatedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnTransferSizeUpdatedParams deserialize(Message arg1) {
            return UrlLoaderClientOnTransferSizeUpdatedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(UrlLoaderClientOnTransferSizeUpdatedParams.DEFAULT_STRUCT_INFO).encode(this.transferSizeDiff, 8);
        }
    }

    final class UrlLoaderClientOnUploadProgressParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public long currentPosition;
        public long totalSize;

        static {
            UrlLoaderClientOnUploadProgressParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            UrlLoaderClientOnUploadProgressParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnUploadProgressParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnUploadProgressParams() {
            this(0);
        }

        private UrlLoaderClientOnUploadProgressParams(int arg2) {
            super(24, arg2);
        }

        public static UrlLoaderClientOnUploadProgressParams decode(Decoder arg4) {
            UrlLoaderClientOnUploadProgressParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnUploadProgressParams(arg4.readAndValidateDataHeader(UrlLoaderClientOnUploadProgressParams.VERSION_ARRAY).elementsOrVersion);
                v1.currentPosition = arg4.readLong(8);
                v1.totalSize = arg4.readLong(16);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnUploadProgressParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnUploadProgressParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnUploadProgressParams deserialize(Message arg1) {
            return UrlLoaderClientOnUploadProgressParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(UrlLoaderClientOnUploadProgressParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.currentPosition, 8);
            arg4.encode(this.totalSize, 16);
        }
    }

    final class UrlLoaderClientOnUploadProgressResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            UrlLoaderClientOnUploadProgressResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            UrlLoaderClientOnUploadProgressResponseParams.DEFAULT_STRUCT_INFO = UrlLoaderClientOnUploadProgressResponseParams.VERSION_ARRAY[0];
        }

        public UrlLoaderClientOnUploadProgressResponseParams() {
            this(0);
        }

        private UrlLoaderClientOnUploadProgressResponseParams(int arg2) {
            super(8, arg2);
        }

        public static UrlLoaderClientOnUploadProgressResponseParams decode(Decoder arg2) {
            UrlLoaderClientOnUploadProgressResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new UrlLoaderClientOnUploadProgressResponseParams(arg2.readAndValidateDataHeader(UrlLoaderClientOnUploadProgressResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static UrlLoaderClientOnUploadProgressResponseParams deserialize(ByteBuffer arg2) {
            return UrlLoaderClientOnUploadProgressResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static UrlLoaderClientOnUploadProgressResponseParams deserialize(Message arg1) {
            return UrlLoaderClientOnUploadProgressResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(UrlLoaderClientOnUploadProgressResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class UrlLoaderClientOnUploadProgressResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OnUploadProgressResponse mCallback;

        UrlLoaderClientOnUploadProgressResponseParamsForwardToCallback(OnUploadProgressResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(3, 2)) {
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

    class UrlLoaderClientOnUploadProgressResponseParamsProxyToResponder implements OnUploadProgressResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        UrlLoaderClientOnUploadProgressResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new UrlLoaderClientOnUploadProgressResponseParams().serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_COMPLETE_ORDINAL = 7;
    private static final int ON_DATA_DOWNLOADED_ORDINAL = 2;
    private static final int ON_RECEIVE_CACHED_METADATA_ORDINAL = 4;
    private static final int ON_RECEIVE_REDIRECT_ORDINAL = 1;
    private static final int ON_RECEIVE_RESPONSE_ORDINAL = 0;
    private static final int ON_START_LOADING_RESPONSE_BODY_ORDINAL = 6;
    private static final int ON_TRANSFER_SIZE_UPDATED_ORDINAL = 5;
    private static final int ON_UPLOAD_PROGRESS_ORDINAL = 3;

    static {
        UrlLoaderClient_Internal.MANAGER = new org.chromium.network.mojom.UrlLoaderClient_Internal$1();
    }

    UrlLoaderClient_Internal() {
        super();
    }
}

