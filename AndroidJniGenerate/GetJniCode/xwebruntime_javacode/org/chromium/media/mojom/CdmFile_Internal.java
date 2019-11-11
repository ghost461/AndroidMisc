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
import org.chromium.mojo_base.mojom.File;

class CdmFile_Internal {
    final class org.chromium.media.mojom.CdmFile_Internal$1 extends Manager {
        org.chromium.media.mojom.CdmFile_Internal$1() {
            super();
        }

        public CdmFile[] buildArray(int arg1) {
            return new CdmFile[arg1];
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

        public Stub buildStub(Core arg2, CdmFile arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CdmFile)arg2));
        }

        public String getName() {
            return "media::mojom::CdmFile";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CdmFileCommitWriteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            CdmFileCommitWriteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            CdmFileCommitWriteParams.DEFAULT_STRUCT_INFO = CdmFileCommitWriteParams.VERSION_ARRAY[0];
        }

        public CdmFileCommitWriteParams() {
            this(0);
        }

        private CdmFileCommitWriteParams(int arg2) {
            super(8, arg2);
        }

        public static CdmFileCommitWriteParams decode(Decoder arg2) {
            CdmFileCommitWriteParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CdmFileCommitWriteParams(arg2.readAndValidateDataHeader(CdmFileCommitWriteParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CdmFileCommitWriteParams deserialize(ByteBuffer arg2) {
            return CdmFileCommitWriteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmFileCommitWriteParams deserialize(Message arg1) {
            return CdmFileCommitWriteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(CdmFileCommitWriteParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class CdmFileCommitWriteResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public File updatedFileForReading;

        static {
            CdmFileCommitWriteResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CdmFileCommitWriteResponseParams.DEFAULT_STRUCT_INFO = CdmFileCommitWriteResponseParams.VERSION_ARRAY[0];
        }

        public CdmFileCommitWriteResponseParams() {
            this(0);
        }

        private CdmFileCommitWriteResponseParams(int arg2) {
            super(16, arg2);
        }

        public static CdmFileCommitWriteResponseParams decode(Decoder arg3) {
            CdmFileCommitWriteResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CdmFileCommitWriteResponseParams(arg3.readAndValidateDataHeader(CdmFileCommitWriteResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.updatedFileForReading = File.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CdmFileCommitWriteResponseParams deserialize(ByteBuffer arg2) {
            return CdmFileCommitWriteResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmFileCommitWriteResponseParams deserialize(Message arg1) {
            return CdmFileCommitWriteResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CdmFileCommitWriteResponseParams.DEFAULT_STRUCT_INFO).encode(this.updatedFileForReading, 8, true);
        }
    }

    class CdmFileCommitWriteResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final CommitWriteResponse mCallback;

        CdmFileCommitWriteResponseParamsForwardToCallback(CommitWriteResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(CdmFileCommitWriteResponseParams.deserialize(v5.getPayload()).updatedFileForReading);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CdmFileCommitWriteResponseParamsProxyToResponder implements CommitWriteResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CdmFileCommitWriteResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((File)arg1));
        }

        public void call(File arg7) {
            CdmFileCommitWriteResponseParams v0 = new CdmFileCommitWriteResponseParams();
            v0.updatedFileForReading = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class CdmFileOpenFileForWritingParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            CdmFileOpenFileForWritingParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            CdmFileOpenFileForWritingParams.DEFAULT_STRUCT_INFO = CdmFileOpenFileForWritingParams.VERSION_ARRAY[0];
        }

        public CdmFileOpenFileForWritingParams() {
            this(0);
        }

        private CdmFileOpenFileForWritingParams(int arg2) {
            super(8, arg2);
        }

        public static CdmFileOpenFileForWritingParams decode(Decoder arg2) {
            CdmFileOpenFileForWritingParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CdmFileOpenFileForWritingParams(arg2.readAndValidateDataHeader(CdmFileOpenFileForWritingParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CdmFileOpenFileForWritingParams deserialize(ByteBuffer arg2) {
            return CdmFileOpenFileForWritingParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmFileOpenFileForWritingParams deserialize(Message arg1) {
            return CdmFileOpenFileForWritingParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(CdmFileOpenFileForWritingParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class CdmFileOpenFileForWritingResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public File fileForWriting;

        static {
            CdmFileOpenFileForWritingResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CdmFileOpenFileForWritingResponseParams.DEFAULT_STRUCT_INFO = CdmFileOpenFileForWritingResponseParams.VERSION_ARRAY[0];
        }

        public CdmFileOpenFileForWritingResponseParams() {
            this(0);
        }

        private CdmFileOpenFileForWritingResponseParams(int arg2) {
            super(16, arg2);
        }

        public static CdmFileOpenFileForWritingResponseParams decode(Decoder arg3) {
            CdmFileOpenFileForWritingResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CdmFileOpenFileForWritingResponseParams(arg3.readAndValidateDataHeader(CdmFileOpenFileForWritingResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.fileForWriting = File.decode(arg3.readPointer(8, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CdmFileOpenFileForWritingResponseParams deserialize(ByteBuffer arg2) {
            return CdmFileOpenFileForWritingResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmFileOpenFileForWritingResponseParams deserialize(Message arg1) {
            return CdmFileOpenFileForWritingResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CdmFileOpenFileForWritingResponseParams.DEFAULT_STRUCT_INFO).encode(this.fileForWriting, 8, true);
        }
    }

    class CdmFileOpenFileForWritingResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OpenFileForWritingResponse mCallback;

        CdmFileOpenFileForWritingResponseParamsForwardToCallback(OpenFileForWritingResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(CdmFileOpenFileForWritingResponseParams.deserialize(v4.getPayload()).fileForWriting);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CdmFileOpenFileForWritingResponseParamsProxyToResponder implements OpenFileForWritingResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CdmFileOpenFileForWritingResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((File)arg1));
        }

        public void call(File arg7) {
            CdmFileOpenFileForWritingResponseParams v0 = new CdmFileOpenFileForWritingResponseParams();
            v0.fileForWriting = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.CdmFile$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void commitWrite(CommitWriteResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new CdmFileCommitWriteParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new CdmFileCommitWriteResponseParamsForwardToCallback(arg8));
        }

        public void openFileForWriting(OpenFileForWritingResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new CdmFileOpenFileForWritingParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new CdmFileOpenFileForWritingResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, CdmFile arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CdmFile_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
            try {
                ServiceMessage v8_1 = arg8.asServiceMessage();
                MessageHeader v1 = v8_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_28;
                    }
                    case 0: {
                        goto label_19;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                CdmFileOpenFileForWritingParams.deserialize(v8_1.getPayload());
                this.getImpl().openFileForWriting(new CdmFileOpenFileForWritingResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            label_10:
                CdmFileCommitWriteParams.deserialize(v8_1.getPayload());
                this.getImpl().commitWrite(new CdmFileCommitWriteResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            label_28:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CdmFile_Internal.MANAGER, v8_1, arg9);
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int COMMIT_WRITE_ORDINAL = 1;
    public static final Manager MANAGER;
    private static final int OPEN_FILE_FOR_WRITING_ORDINAL;

    static {
        CdmFile_Internal.MANAGER = new org.chromium.media.mojom.CdmFile_Internal$1();
    }

    CdmFile_Internal() {
        super();
    }
}

