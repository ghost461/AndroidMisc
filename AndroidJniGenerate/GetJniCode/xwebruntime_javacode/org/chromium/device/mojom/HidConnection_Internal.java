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

class HidConnection_Internal {
    final class org.chromium.device.mojom.HidConnection_Internal$1 extends Manager {
        org.chromium.device.mojom.HidConnection_Internal$1() {
            super();
        }

        public HidConnection[] buildArray(int arg1) {
            return new HidConnection[arg1];
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

        public Stub buildStub(Core arg2, HidConnection arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((HidConnection)arg2));
        }

        public String getName() {
            return "device::mojom::HidConnection";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class HidConnectionGetFeatureReportParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public byte reportId;

        static {
            HidConnectionGetFeatureReportParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidConnectionGetFeatureReportParams.DEFAULT_STRUCT_INFO = HidConnectionGetFeatureReportParams.VERSION_ARRAY[0];
        }

        public HidConnectionGetFeatureReportParams() {
            this(0);
        }

        private HidConnectionGetFeatureReportParams(int arg2) {
            super(16, arg2);
        }

        public static HidConnectionGetFeatureReportParams decode(Decoder arg2) {
            HidConnectionGetFeatureReportParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new HidConnectionGetFeatureReportParams(arg2.readAndValidateDataHeader(HidConnectionGetFeatureReportParams.VERSION_ARRAY).elementsOrVersion);
                v1.reportId = arg2.readByte(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionGetFeatureReportParams deserialize(ByteBuffer arg2) {
            return HidConnectionGetFeatureReportParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionGetFeatureReportParams deserialize(Message arg1) {
            return HidConnectionGetFeatureReportParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(HidConnectionGetFeatureReportParams.DEFAULT_STRUCT_INFO).encode(this.reportId, 8);
        }
    }

    final class HidConnectionGetFeatureReportResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] buffer;
        public boolean success;

        static {
            HidConnectionGetFeatureReportResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            HidConnectionGetFeatureReportResponseParams.DEFAULT_STRUCT_INFO = HidConnectionGetFeatureReportResponseParams.VERSION_ARRAY[0];
        }

        public HidConnectionGetFeatureReportResponseParams() {
            this(0);
        }

        private HidConnectionGetFeatureReportResponseParams(int arg2) {
            super(24, arg2);
        }

        public static HidConnectionGetFeatureReportResponseParams decode(Decoder arg4) {
            HidConnectionGetFeatureReportResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new HidConnectionGetFeatureReportResponseParams(arg4.readAndValidateDataHeader(HidConnectionGetFeatureReportResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg4.readBoolean(8, 0);
                v1.buffer = arg4.readBytes(16, 1, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionGetFeatureReportResponseParams deserialize(ByteBuffer arg2) {
            return HidConnectionGetFeatureReportResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionGetFeatureReportResponseParams deserialize(Message arg1) {
            return HidConnectionGetFeatureReportResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(HidConnectionGetFeatureReportResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.success, 8, 0);
            arg5.encode(this.buffer, 16, 1, -1);
        }
    }

    class HidConnectionGetFeatureReportResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetFeatureReportResponse mCallback;

        HidConnectionGetFeatureReportResponseParamsForwardToCallback(GetFeatureReportResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                HidConnectionGetFeatureReportResponseParams v4_1 = HidConnectionGetFeatureReportResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Boolean.valueOf(v4_1.success), v4_1.buffer);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class HidConnectionGetFeatureReportResponseParamsProxyToResponder implements GetFeatureReportResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        HidConnectionGetFeatureReportResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, byte[] arg6) {
            HidConnectionGetFeatureReportResponseParams v0 = new HidConnectionGetFeatureReportResponseParams();
            v0.success = arg5.booleanValue();
            v0.buffer = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Boolean)arg1), ((byte[])arg2));
        }
    }

    final class HidConnectionReadParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            HidConnectionReadParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            HidConnectionReadParams.DEFAULT_STRUCT_INFO = HidConnectionReadParams.VERSION_ARRAY[0];
        }

        public HidConnectionReadParams() {
            this(0);
        }

        private HidConnectionReadParams(int arg2) {
            super(8, arg2);
        }

        public static HidConnectionReadParams decode(Decoder arg2) {
            HidConnectionReadParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new HidConnectionReadParams(arg2.readAndValidateDataHeader(HidConnectionReadParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionReadParams deserialize(ByteBuffer arg2) {
            return HidConnectionReadParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionReadParams deserialize(Message arg1) {
            return HidConnectionReadParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(HidConnectionReadParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class HidConnectionReadResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] buffer;
        public byte reportId;
        public boolean success;

        static {
            HidConnectionReadResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            HidConnectionReadResponseParams.DEFAULT_STRUCT_INFO = HidConnectionReadResponseParams.VERSION_ARRAY[0];
        }

        public HidConnectionReadResponseParams() {
            this(0);
        }

        private HidConnectionReadResponseParams(int arg2) {
            super(24, arg2);
        }

        public static HidConnectionReadResponseParams decode(Decoder arg4) {
            HidConnectionReadResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new HidConnectionReadResponseParams(arg4.readAndValidateDataHeader(HidConnectionReadResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg4.readBoolean(8, 0);
                v1.reportId = arg4.readByte(9);
                v1.buffer = arg4.readBytes(16, 1, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionReadResponseParams deserialize(ByteBuffer arg2) {
            return HidConnectionReadResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionReadResponseParams deserialize(Message arg1) {
            return HidConnectionReadResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(HidConnectionReadResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.success, 8, 0);
            arg5.encode(this.reportId, 9);
            arg5.encode(this.buffer, 16, 1, -1);
        }
    }

    class HidConnectionReadResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadResponse mCallback;

        HidConnectionReadResponseParamsForwardToCallback(ReadResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                HidConnectionReadResponseParams v5_1 = HidConnectionReadResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Boolean.valueOf(v5_1.success), Byte.valueOf(v5_1.reportId), v5_1.buffer);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class HidConnectionReadResponseParamsProxyToResponder implements ReadResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        HidConnectionReadResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg5, Byte arg6, byte[] arg7) {
            HidConnectionReadResponseParams v0 = new HidConnectionReadResponseParams();
            v0.success = arg5.booleanValue();
            v0.reportId = arg6.byteValue();
            v0.buffer = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Boolean)arg1), ((Byte)arg2), ((byte[])arg3));
        }
    }

    final class HidConnectionSendFeatureReportParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] buffer;
        public byte reportId;

        static {
            HidConnectionSendFeatureReportParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            HidConnectionSendFeatureReportParams.DEFAULT_STRUCT_INFO = HidConnectionSendFeatureReportParams.VERSION_ARRAY[0];
        }

        public HidConnectionSendFeatureReportParams() {
            this(0);
        }

        private HidConnectionSendFeatureReportParams(int arg2) {
            super(24, arg2);
        }

        public static HidConnectionSendFeatureReportParams decode(Decoder arg4) {
            HidConnectionSendFeatureReportParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new HidConnectionSendFeatureReportParams(arg4.readAndValidateDataHeader(HidConnectionSendFeatureReportParams.VERSION_ARRAY).elementsOrVersion);
                v1.reportId = arg4.readByte(8);
                v1.buffer = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionSendFeatureReportParams deserialize(ByteBuffer arg2) {
            return HidConnectionSendFeatureReportParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionSendFeatureReportParams deserialize(Message arg1) {
            return HidConnectionSendFeatureReportParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(HidConnectionSendFeatureReportParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.reportId, 8);
            arg5.encode(this.buffer, 16, 0, -1);
        }
    }

    final class HidConnectionSendFeatureReportResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            HidConnectionSendFeatureReportResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidConnectionSendFeatureReportResponseParams.DEFAULT_STRUCT_INFO = HidConnectionSendFeatureReportResponseParams.VERSION_ARRAY[0];
        }

        public HidConnectionSendFeatureReportResponseParams() {
            this(0);
        }

        private HidConnectionSendFeatureReportResponseParams(int arg2) {
            super(16, arg2);
        }

        public static HidConnectionSendFeatureReportResponseParams decode(Decoder arg3) {
            HidConnectionSendFeatureReportResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new HidConnectionSendFeatureReportResponseParams(arg3.readAndValidateDataHeader(HidConnectionSendFeatureReportResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionSendFeatureReportResponseParams deserialize(ByteBuffer arg2) {
            return HidConnectionSendFeatureReportResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionSendFeatureReportResponseParams deserialize(Message arg1) {
            return HidConnectionSendFeatureReportResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(HidConnectionSendFeatureReportResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class HidConnectionSendFeatureReportResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SendFeatureReportResponse mCallback;

        HidConnectionSendFeatureReportResponseParamsForwardToCallback(SendFeatureReportResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(HidConnectionSendFeatureReportResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class HidConnectionSendFeatureReportResponseParamsProxyToResponder implements SendFeatureReportResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        HidConnectionSendFeatureReportResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            HidConnectionSendFeatureReportResponseParams v0 = new HidConnectionSendFeatureReportResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class HidConnectionWriteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] buffer;
        public byte reportId;

        static {
            HidConnectionWriteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            HidConnectionWriteParams.DEFAULT_STRUCT_INFO = HidConnectionWriteParams.VERSION_ARRAY[0];
        }

        public HidConnectionWriteParams() {
            this(0);
        }

        private HidConnectionWriteParams(int arg2) {
            super(24, arg2);
        }

        public static HidConnectionWriteParams decode(Decoder arg4) {
            HidConnectionWriteParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new HidConnectionWriteParams(arg4.readAndValidateDataHeader(HidConnectionWriteParams.VERSION_ARRAY).elementsOrVersion);
                v1.reportId = arg4.readByte(8);
                v1.buffer = arg4.readBytes(16, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionWriteParams deserialize(ByteBuffer arg2) {
            return HidConnectionWriteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionWriteParams deserialize(Message arg1) {
            return HidConnectionWriteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(HidConnectionWriteParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.reportId, 8);
            arg5.encode(this.buffer, 16, 0, -1);
        }
    }

    final class HidConnectionWriteResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            HidConnectionWriteResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidConnectionWriteResponseParams.DEFAULT_STRUCT_INFO = HidConnectionWriteResponseParams.VERSION_ARRAY[0];
        }

        public HidConnectionWriteResponseParams() {
            this(0);
        }

        private HidConnectionWriteResponseParams(int arg2) {
            super(16, arg2);
        }

        public static HidConnectionWriteResponseParams decode(Decoder arg3) {
            HidConnectionWriteResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new HidConnectionWriteResponseParams(arg3.readAndValidateDataHeader(HidConnectionWriteResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static HidConnectionWriteResponseParams deserialize(ByteBuffer arg2) {
            return HidConnectionWriteResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidConnectionWriteResponseParams deserialize(Message arg1) {
            return HidConnectionWriteResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(HidConnectionWriteResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class HidConnectionWriteResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final WriteResponse mCallback;

        HidConnectionWriteResponseParamsForwardToCallback(WriteResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(HidConnectionWriteResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class HidConnectionWriteResponseParamsProxyToResponder implements WriteResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        HidConnectionWriteResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            HidConnectionWriteResponseParams v0 = new HidConnectionWriteResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.HidConnection$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getFeatureReport(byte arg8, GetFeatureReportResponse arg9) {
            HidConnectionGetFeatureReportParams v0 = new HidConnectionGetFeatureReportParams();
            v0.reportId = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new HidConnectionGetFeatureReportResponseParamsForwardToCallback(arg9));
        }

        public void read(ReadResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new HidConnectionReadParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new HidConnectionReadResponseParamsForwardToCallback(arg9));
        }

        public void sendFeatureReport(byte arg7, byte[] arg8, SendFeatureReportResponse arg9) {
            HidConnectionSendFeatureReportParams v0 = new HidConnectionSendFeatureReportParams();
            v0.reportId = arg7;
            v0.buffer = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new HidConnectionSendFeatureReportResponseParamsForwardToCallback(arg9));
        }

        public void write(byte arg6, byte[] arg7, WriteResponse arg8) {
            HidConnectionWriteParams v0 = new HidConnectionWriteParams();
            v0.reportId = arg6;
            v0.buffer = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new HidConnectionWriteResponseParamsForwardToCallback(arg8));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, HidConnection arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(HidConnection_Internal.MANAGER, v4_1);
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
                        goto label_51;
                    }
                    case 0: {
                        goto label_42;
                    }
                    case 1: {
                        goto label_31;
                    }
                    case 2: {
                        goto label_21;
                    }
                    case 3: {
                        goto label_10;
                    }
                }

                return 0;
            label_51:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), HidConnection_Internal.MANAGER, v10_1, arg11);
            label_21:
                this.getImpl().getFeatureReport(HidConnectionGetFeatureReportParams.deserialize(v10_1.getPayload()).reportId, new HidConnectionGetFeatureReportResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_42:
                HidConnectionReadParams.deserialize(v10_1.getPayload());
                this.getImpl().read(new HidConnectionReadResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_10:
                HidConnectionSendFeatureReportParams v10_2 = HidConnectionSendFeatureReportParams.deserialize(v10_1.getPayload());
                this.getImpl().sendFeatureReport(v10_2.reportId, v10_2.buffer, new HidConnectionSendFeatureReportResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_31:
                HidConnectionWriteParams v10_3 = HidConnectionWriteParams.deserialize(v10_1.getPayload());
                this.getImpl().write(v10_3.reportId, v10_3.buffer, new HidConnectionWriteResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int GET_FEATURE_REPORT_ORDINAL = 2;
    public static final Manager MANAGER = null;
    private static final int READ_ORDINAL = 0;
    private static final int SEND_FEATURE_REPORT_ORDINAL = 3;
    private static final int WRITE_ORDINAL = 1;

    static {
        HidConnection_Internal.MANAGER = new org.chromium.device.mojom.HidConnection_Internal$1();
    }

    HidConnection_Internal() {
        super();
    }
}

