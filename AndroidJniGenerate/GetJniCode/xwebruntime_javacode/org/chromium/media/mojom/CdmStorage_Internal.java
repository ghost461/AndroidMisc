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
import org.chromium.mojo_base.mojom.File;

class CdmStorage_Internal {
    final class org.chromium.media.mojom.CdmStorage_Internal$1 extends Manager {
        org.chromium.media.mojom.CdmStorage_Internal$1() {
            super();
        }

        public CdmStorage[] buildArray(int arg1) {
            return new CdmStorage[arg1];
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

        public Stub buildStub(Core arg2, CdmStorage arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CdmStorage)arg2));
        }

        public String getName() {
            return "media::mojom::CdmStorage";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CdmStorageOpenParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String fileName;

        static {
            CdmStorageOpenParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CdmStorageOpenParams.DEFAULT_STRUCT_INFO = CdmStorageOpenParams.VERSION_ARRAY[0];
        }

        public CdmStorageOpenParams() {
            this(0);
        }

        private CdmStorageOpenParams(int arg2) {
            super(16, arg2);
        }

        public static CdmStorageOpenParams decode(Decoder arg3) {
            CdmStorageOpenParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CdmStorageOpenParams(arg3.readAndValidateDataHeader(CdmStorageOpenParams.VERSION_ARRAY).elementsOrVersion);
                v1.fileName = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CdmStorageOpenParams deserialize(ByteBuffer arg2) {
            return CdmStorageOpenParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmStorageOpenParams deserialize(Message arg1) {
            return CdmStorageOpenParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CdmStorageOpenParams.DEFAULT_STRUCT_INFO).encode(this.fileName, 8, false);
        }
    }

    final class CdmStorageOpenResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public AssociatedInterfaceNotSupported cdmFile;
        public File fileForReading;
        public int status;

        static {
            CdmStorageOpenResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            CdmStorageOpenResponseParams.DEFAULT_STRUCT_INFO = CdmStorageOpenResponseParams.VERSION_ARRAY[0];
        }

        public CdmStorageOpenResponseParams() {
            this(0);
        }

        private CdmStorageOpenResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static CdmStorageOpenResponseParams decode(Decoder arg3) {
            CdmStorageOpenResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CdmStorageOpenResponseParams(arg3.readAndValidateDataHeader(CdmStorageOpenResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.status = arg3.readInt(8);
                Status.validate(v1.status);
                v1.fileForReading = File.decode(arg3.readPointer(16, true));
                v1.cdmFile = arg3.readAssociatedServiceInterfaceNotSupported(24, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CdmStorageOpenResponseParams deserialize(ByteBuffer arg2) {
            return CdmStorageOpenResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CdmStorageOpenResponseParams deserialize(Message arg1) {
            return CdmStorageOpenResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(CdmStorageOpenResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.status, 8);
            arg4.encode(this.fileForReading, 16, true);
            arg4.encode(this.cdmFile, 24, true);
        }
    }

    class CdmStorageOpenResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OpenResponse mCallback;

        CdmStorageOpenResponseParamsForwardToCallback(OpenResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                CdmStorageOpenResponseParams v5_1 = CdmStorageOpenResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.status), v5_1.fileForReading, v5_1.cdmFile);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CdmStorageOpenResponseParamsProxyToResponder implements OpenResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CdmStorageOpenResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, File arg6, AssociatedInterfaceNotSupported arg7) {
            CdmStorageOpenResponseParams v0 = new CdmStorageOpenResponseParams();
            v0.status = arg5.intValue();
            v0.fileForReading = arg6;
            v0.cdmFile = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((File)arg2), ((AssociatedInterfaceNotSupported)arg3));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.media.mojom.CdmStorage$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void open(String arg8, OpenResponse arg9) {
            CdmStorageOpenParams v0 = new CdmStorageOpenParams();
            v0.fileName = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new CdmStorageOpenResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, CdmStorage arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CdmStorage_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg9, MessageReceiver arg10) {
            try {
                ServiceMessage v9_1 = arg9.asServiceMessage();
                MessageHeader v1 = v9_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_20;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CdmStorage_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().open(CdmStorageOpenParams.deserialize(v9_1.getPayload()).fileName, new CdmStorageOpenResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int OPEN_ORDINAL;

    static {
        CdmStorage_Internal.MANAGER = new org.chromium.media.mojom.CdmStorage_Internal$1();
    }

    CdmStorage_Internal() {
        super();
    }
}

