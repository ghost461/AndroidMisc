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

class SerialIoHandler_Internal {
    final class org.chromium.device.mojom.SerialIoHandler_Internal$1 extends Manager {
        org.chromium.device.mojom.SerialIoHandler_Internal$1() {
            super();
        }

        public SerialIoHandler[] buildArray(int arg1) {
            return new SerialIoHandler[arg1];
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

        public Stub buildStub(Core arg2, SerialIoHandler arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((SerialIoHandler)arg2));
        }

        public String getName() {
            return "device::mojom::SerialIoHandler";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.SerialIoHandler$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void cancelRead(int arg5) {
            SerialIoHandlerCancelReadParams v0 = new SerialIoHandlerCancelReadParams();
            v0.reason = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void cancelWrite(int arg5) {
            SerialIoHandlerCancelWriteParams v0 = new SerialIoHandlerCancelWriteParams();
            v0.reason = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void clearBreak(ClearBreakResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new SerialIoHandlerClearBreakParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(11, 1, 0)), new SerialIoHandlerClearBreakResponseParamsForwardToCallback(arg9));
        }

        public void configurePort(SerialConnectionOptions arg8, ConfigurePortResponse arg9) {
            SerialIoHandlerConfigurePortParams v0 = new SerialIoHandlerConfigurePortParams();
            v0.options = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(8, 1, 0)), new SerialIoHandlerConfigurePortResponseParamsForwardToCallback(arg9));
        }

        public void flush(FlushResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new SerialIoHandlerFlushParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new SerialIoHandlerFlushResponseParamsForwardToCallback(arg9));
        }

        public void getControlSignals(GetControlSignalsResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new SerialIoHandlerGetControlSignalsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6, 1, 0)), new SerialIoHandlerGetControlSignalsResponseParamsForwardToCallback(arg9));
        }

        public void getPortInfo(GetPortInfoResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new SerialIoHandlerGetPortInfoParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(9, 1, 0)), new SerialIoHandlerGetPortInfoResponseParamsForwardToCallback(arg9));
        }

        public void open(String arg7, SerialConnectionOptions arg8, OpenResponse arg9) {
            SerialIoHandlerOpenParams v0 = new SerialIoHandlerOpenParams();
            v0.port = arg7;
            v0.options = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new SerialIoHandlerOpenResponseParamsForwardToCallback(arg9));
        }

        public void read(int arg7, ReadResponse arg8) {
            SerialIoHandlerReadParams v0 = new SerialIoHandlerReadParams();
            v0.bytes = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new SerialIoHandlerReadResponseParamsForwardToCallback(arg8));
        }

        public void setBreak(SetBreakResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new SerialIoHandlerSetBreakParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(10, 1, 0)), new SerialIoHandlerSetBreakResponseParamsForwardToCallback(arg9));
        }

        public void setControlSignals(SerialHostControlSignals arg8, SetControlSignalsResponse arg9) {
            SerialIoHandlerSetControlSignalsParams v0 = new SerialIoHandlerSetControlSignalsParams();
            v0.signals = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7, 1, 0)), new SerialIoHandlerSetControlSignalsResponseParamsForwardToCallback(arg9));
        }

        public void write(byte[] arg8, WriteResponse arg9) {
            SerialIoHandlerWriteParams v0 = new SerialIoHandlerWriteParams();
            v0.data = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new SerialIoHandlerWriteResponseParamsForwardToCallback(arg9));
        }
    }

    final class SerialIoHandlerCancelReadParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int reason;

        static {
            SerialIoHandlerCancelReadParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerCancelReadParams.DEFAULT_STRUCT_INFO = SerialIoHandlerCancelReadParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerCancelReadParams() {
            this(0);
        }

        private SerialIoHandlerCancelReadParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerCancelReadParams decode(Decoder arg2) {
            SerialIoHandlerCancelReadParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerCancelReadParams(arg2.readAndValidateDataHeader(SerialIoHandlerCancelReadParams.VERSION_ARRAY).elementsOrVersion);
                v1.reason = arg2.readInt(8);
                SerialReceiveError.validate(v1.reason);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerCancelReadParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerCancelReadParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerCancelReadParams deserialize(Message arg1) {
            return SerialIoHandlerCancelReadParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(SerialIoHandlerCancelReadParams.DEFAULT_STRUCT_INFO).encode(this.reason, 8);
        }
    }

    final class SerialIoHandlerCancelWriteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int reason;

        static {
            SerialIoHandlerCancelWriteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerCancelWriteParams.DEFAULT_STRUCT_INFO = SerialIoHandlerCancelWriteParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerCancelWriteParams() {
            this(0);
        }

        private SerialIoHandlerCancelWriteParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerCancelWriteParams decode(Decoder arg2) {
            SerialIoHandlerCancelWriteParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerCancelWriteParams(arg2.readAndValidateDataHeader(SerialIoHandlerCancelWriteParams.VERSION_ARRAY).elementsOrVersion);
                v1.reason = arg2.readInt(8);
                SerialSendError.validate(v1.reason);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerCancelWriteParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerCancelWriteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerCancelWriteParams deserialize(Message arg1) {
            return SerialIoHandlerCancelWriteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(SerialIoHandlerCancelWriteParams.DEFAULT_STRUCT_INFO).encode(this.reason, 8);
        }
    }

    final class SerialIoHandlerClearBreakParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SerialIoHandlerClearBreakParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SerialIoHandlerClearBreakParams.DEFAULT_STRUCT_INFO = SerialIoHandlerClearBreakParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerClearBreakParams() {
            this(0);
        }

        private SerialIoHandlerClearBreakParams(int arg2) {
            super(8, arg2);
        }

        public static SerialIoHandlerClearBreakParams decode(Decoder arg2) {
            SerialIoHandlerClearBreakParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerClearBreakParams(arg2.readAndValidateDataHeader(SerialIoHandlerClearBreakParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerClearBreakParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerClearBreakParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerClearBreakParams deserialize(Message arg1) {
            return SerialIoHandlerClearBreakParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SerialIoHandlerClearBreakParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SerialIoHandlerClearBreakResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            SerialIoHandlerClearBreakResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerClearBreakResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerClearBreakResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerClearBreakResponseParams() {
            this(0);
        }

        private SerialIoHandlerClearBreakResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerClearBreakResponseParams decode(Decoder arg3) {
            SerialIoHandlerClearBreakResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerClearBreakResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerClearBreakResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerClearBreakResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerClearBreakResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerClearBreakResponseParams deserialize(Message arg1) {
            return SerialIoHandlerClearBreakResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerClearBreakResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class SerialIoHandlerClearBreakResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ClearBreakResponse mCallback;

        SerialIoHandlerClearBreakResponseParamsForwardToCallback(ClearBreakResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(11, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(SerialIoHandlerClearBreakResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerClearBreakResponseParamsProxyToResponder implements ClearBreakResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerClearBreakResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            SerialIoHandlerClearBreakResponseParams v0 = new SerialIoHandlerClearBreakResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(11, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class SerialIoHandlerConfigurePortParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SerialConnectionOptions options;

        static {
            SerialIoHandlerConfigurePortParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerConfigurePortParams.DEFAULT_STRUCT_INFO = SerialIoHandlerConfigurePortParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerConfigurePortParams() {
            this(0);
        }

        private SerialIoHandlerConfigurePortParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerConfigurePortParams decode(Decoder arg3) {
            SerialIoHandlerConfigurePortParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerConfigurePortParams(arg3.readAndValidateDataHeader(SerialIoHandlerConfigurePortParams.VERSION_ARRAY).elementsOrVersion);
                v1.options = SerialConnectionOptions.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerConfigurePortParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerConfigurePortParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerConfigurePortParams deserialize(Message arg1) {
            return SerialIoHandlerConfigurePortParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerConfigurePortParams.DEFAULT_STRUCT_INFO).encode(this.options, 8, false);
        }
    }

    final class SerialIoHandlerConfigurePortResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            SerialIoHandlerConfigurePortResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerConfigurePortResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerConfigurePortResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerConfigurePortResponseParams() {
            this(0);
        }

        private SerialIoHandlerConfigurePortResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerConfigurePortResponseParams decode(Decoder arg3) {
            SerialIoHandlerConfigurePortResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerConfigurePortResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerConfigurePortResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerConfigurePortResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerConfigurePortResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerConfigurePortResponseParams deserialize(Message arg1) {
            return SerialIoHandlerConfigurePortResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerConfigurePortResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class SerialIoHandlerConfigurePortResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ConfigurePortResponse mCallback;

        SerialIoHandlerConfigurePortResponseParamsForwardToCallback(ConfigurePortResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(8, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(SerialIoHandlerConfigurePortResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerConfigurePortResponseParamsProxyToResponder implements ConfigurePortResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerConfigurePortResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            SerialIoHandlerConfigurePortResponseParams v0 = new SerialIoHandlerConfigurePortResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(8, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class SerialIoHandlerFlushParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SerialIoHandlerFlushParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SerialIoHandlerFlushParams.DEFAULT_STRUCT_INFO = SerialIoHandlerFlushParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerFlushParams() {
            this(0);
        }

        private SerialIoHandlerFlushParams(int arg2) {
            super(8, arg2);
        }

        public static SerialIoHandlerFlushParams decode(Decoder arg2) {
            SerialIoHandlerFlushParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerFlushParams(arg2.readAndValidateDataHeader(SerialIoHandlerFlushParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerFlushParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerFlushParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerFlushParams deserialize(Message arg1) {
            return SerialIoHandlerFlushParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SerialIoHandlerFlushParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SerialIoHandlerFlushResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            SerialIoHandlerFlushResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerFlushResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerFlushResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerFlushResponseParams() {
            this(0);
        }

        private SerialIoHandlerFlushResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerFlushResponseParams decode(Decoder arg3) {
            SerialIoHandlerFlushResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerFlushResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerFlushResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerFlushResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerFlushResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerFlushResponseParams deserialize(Message arg1) {
            return SerialIoHandlerFlushResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerFlushResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class SerialIoHandlerFlushResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final FlushResponse mCallback;

        SerialIoHandlerFlushResponseParamsForwardToCallback(FlushResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(5, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(SerialIoHandlerFlushResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerFlushResponseParamsProxyToResponder implements FlushResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerFlushResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            SerialIoHandlerFlushResponseParams v0 = new SerialIoHandlerFlushResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class SerialIoHandlerGetControlSignalsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SerialIoHandlerGetControlSignalsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SerialIoHandlerGetControlSignalsParams.DEFAULT_STRUCT_INFO = SerialIoHandlerGetControlSignalsParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerGetControlSignalsParams() {
            this(0);
        }

        private SerialIoHandlerGetControlSignalsParams(int arg2) {
            super(8, arg2);
        }

        public static SerialIoHandlerGetControlSignalsParams decode(Decoder arg2) {
            SerialIoHandlerGetControlSignalsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerGetControlSignalsParams(arg2.readAndValidateDataHeader(SerialIoHandlerGetControlSignalsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerGetControlSignalsParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerGetControlSignalsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerGetControlSignalsParams deserialize(Message arg1) {
            return SerialIoHandlerGetControlSignalsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SerialIoHandlerGetControlSignalsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SerialIoHandlerGetControlSignalsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SerialDeviceControlSignals signals;

        static {
            SerialIoHandlerGetControlSignalsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerGetControlSignalsResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerGetControlSignalsResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerGetControlSignalsResponseParams() {
            this(0);
        }

        private SerialIoHandlerGetControlSignalsResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerGetControlSignalsResponseParams decode(Decoder arg3) {
            SerialIoHandlerGetControlSignalsResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerGetControlSignalsResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerGetControlSignalsResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.signals = SerialDeviceControlSignals.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerGetControlSignalsResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerGetControlSignalsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerGetControlSignalsResponseParams deserialize(Message arg1) {
            return SerialIoHandlerGetControlSignalsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerGetControlSignalsResponseParams.DEFAULT_STRUCT_INFO).encode(this.signals, 8, false);
        }
    }

    class SerialIoHandlerGetControlSignalsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetControlSignalsResponse mCallback;

        SerialIoHandlerGetControlSignalsResponseParamsForwardToCallback(GetControlSignalsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(6, 2)) {
                    return 0;
                }

                this.mCallback.call(SerialIoHandlerGetControlSignalsResponseParams.deserialize(v5.getPayload()).signals);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerGetControlSignalsResponseParamsProxyToResponder implements GetControlSignalsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerGetControlSignalsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((SerialDeviceControlSignals)arg1));
        }

        public void call(SerialDeviceControlSignals arg7) {
            SerialIoHandlerGetControlSignalsResponseParams v0 = new SerialIoHandlerGetControlSignalsResponseParams();
            v0.signals = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(6, 2, this.mRequestId)));
        }
    }

    final class SerialIoHandlerGetPortInfoParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SerialIoHandlerGetPortInfoParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SerialIoHandlerGetPortInfoParams.DEFAULT_STRUCT_INFO = SerialIoHandlerGetPortInfoParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerGetPortInfoParams() {
            this(0);
        }

        private SerialIoHandlerGetPortInfoParams(int arg2) {
            super(8, arg2);
        }

        public static SerialIoHandlerGetPortInfoParams decode(Decoder arg2) {
            SerialIoHandlerGetPortInfoParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerGetPortInfoParams(arg2.readAndValidateDataHeader(SerialIoHandlerGetPortInfoParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerGetPortInfoParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerGetPortInfoParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerGetPortInfoParams deserialize(Message arg1) {
            return SerialIoHandlerGetPortInfoParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SerialIoHandlerGetPortInfoParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SerialIoHandlerGetPortInfoResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SerialConnectionInfo info;

        static {
            SerialIoHandlerGetPortInfoResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerGetPortInfoResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerGetPortInfoResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerGetPortInfoResponseParams() {
            this(0);
        }

        private SerialIoHandlerGetPortInfoResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerGetPortInfoResponseParams decode(Decoder arg3) {
            SerialIoHandlerGetPortInfoResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerGetPortInfoResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerGetPortInfoResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.info = SerialConnectionInfo.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerGetPortInfoResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerGetPortInfoResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerGetPortInfoResponseParams deserialize(Message arg1) {
            return SerialIoHandlerGetPortInfoResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerGetPortInfoResponseParams.DEFAULT_STRUCT_INFO).encode(this.info, 8, false);
        }
    }

    class SerialIoHandlerGetPortInfoResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetPortInfoResponse mCallback;

        SerialIoHandlerGetPortInfoResponseParamsForwardToCallback(GetPortInfoResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(9, 2)) {
                    return 0;
                }

                this.mCallback.call(SerialIoHandlerGetPortInfoResponseParams.deserialize(v5.getPayload()).info);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerGetPortInfoResponseParamsProxyToResponder implements GetPortInfoResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerGetPortInfoResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((SerialConnectionInfo)arg1));
        }

        public void call(SerialConnectionInfo arg7) {
            SerialIoHandlerGetPortInfoResponseParams v0 = new SerialIoHandlerGetPortInfoResponseParams();
            v0.info = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(9, 2, this.mRequestId)));
        }
    }

    final class SerialIoHandlerOpenParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public SerialConnectionOptions options;
        public String port;

        static {
            SerialIoHandlerOpenParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            SerialIoHandlerOpenParams.DEFAULT_STRUCT_INFO = SerialIoHandlerOpenParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerOpenParams() {
            this(0);
        }

        private SerialIoHandlerOpenParams(int arg2) {
            super(24, arg2);
        }

        public static SerialIoHandlerOpenParams decode(Decoder arg3) {
            SerialIoHandlerOpenParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerOpenParams(arg3.readAndValidateDataHeader(SerialIoHandlerOpenParams.VERSION_ARRAY).elementsOrVersion);
                v1.port = arg3.readString(8, false);
                v1.options = SerialConnectionOptions.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerOpenParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerOpenParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerOpenParams deserialize(Message arg1) {
            return SerialIoHandlerOpenParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(SerialIoHandlerOpenParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.port, 8, false);
            arg4.encode(this.options, 16, false);
        }
    }

    final class SerialIoHandlerOpenResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            SerialIoHandlerOpenResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerOpenResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerOpenResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerOpenResponseParams() {
            this(0);
        }

        private SerialIoHandlerOpenResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerOpenResponseParams decode(Decoder arg3) {
            SerialIoHandlerOpenResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerOpenResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerOpenResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerOpenResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerOpenResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerOpenResponseParams deserialize(Message arg1) {
            return SerialIoHandlerOpenResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerOpenResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class SerialIoHandlerOpenResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final OpenResponse mCallback;

        SerialIoHandlerOpenResponseParamsForwardToCallback(OpenResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(SerialIoHandlerOpenResponseParams.deserialize(v4.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerOpenResponseParamsProxyToResponder implements OpenResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerOpenResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            SerialIoHandlerOpenResponseParams v0 = new SerialIoHandlerOpenResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class SerialIoHandlerReadParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int bytes;

        static {
            SerialIoHandlerReadParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerReadParams.DEFAULT_STRUCT_INFO = SerialIoHandlerReadParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerReadParams() {
            this(0);
        }

        private SerialIoHandlerReadParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerReadParams decode(Decoder arg2) {
            SerialIoHandlerReadParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerReadParams(arg2.readAndValidateDataHeader(SerialIoHandlerReadParams.VERSION_ARRAY).elementsOrVersion);
                v1.bytes = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerReadParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerReadParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerReadParams deserialize(Message arg1) {
            return SerialIoHandlerReadParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(SerialIoHandlerReadParams.DEFAULT_STRUCT_INFO).encode(this.bytes, 8);
        }
    }

    final class SerialIoHandlerReadResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;
        public int error;

        static {
            SerialIoHandlerReadResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            SerialIoHandlerReadResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerReadResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerReadResponseParams() {
            this(0);
        }

        private SerialIoHandlerReadResponseParams(int arg2) {
            super(24, arg2);
        }

        public static SerialIoHandlerReadResponseParams decode(Decoder arg4) {
            SerialIoHandlerReadResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerReadResponseParams(arg4.readAndValidateDataHeader(SerialIoHandlerReadResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.data = arg4.readBytes(8, 0, -1);
                v1.error = arg4.readInt(16);
                SerialReceiveError.validate(v1.error);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerReadResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerReadResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerReadResponseParams deserialize(Message arg1) {
            return SerialIoHandlerReadResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(SerialIoHandlerReadResponseParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.data, 8, 0, -1);
            arg5.encode(this.error, 16);
        }
    }

    class SerialIoHandlerReadResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ReadResponse mCallback;

        SerialIoHandlerReadResponseParamsForwardToCallback(ReadResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                SerialIoHandlerReadResponseParams v5_1 = SerialIoHandlerReadResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(v5_1.data, Integer.valueOf(v5_1.error));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerReadResponseParamsProxyToResponder implements ReadResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerReadResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1, Object arg2) {
            this.call(((byte[])arg1), ((Integer)arg2));
        }

        public void call(byte[] arg6, Integer arg7) {
            SerialIoHandlerReadResponseParams v0 = new SerialIoHandlerReadResponseParams();
            v0.data = arg6;
            v0.error = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class SerialIoHandlerSetBreakParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SerialIoHandlerSetBreakParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SerialIoHandlerSetBreakParams.DEFAULT_STRUCT_INFO = SerialIoHandlerSetBreakParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerSetBreakParams() {
            this(0);
        }

        private SerialIoHandlerSetBreakParams(int arg2) {
            super(8, arg2);
        }

        public static SerialIoHandlerSetBreakParams decode(Decoder arg2) {
            SerialIoHandlerSetBreakParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerSetBreakParams(arg2.readAndValidateDataHeader(SerialIoHandlerSetBreakParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerSetBreakParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerSetBreakParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerSetBreakParams deserialize(Message arg1) {
            return SerialIoHandlerSetBreakParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SerialIoHandlerSetBreakParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SerialIoHandlerSetBreakResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            SerialIoHandlerSetBreakResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerSetBreakResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerSetBreakResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerSetBreakResponseParams() {
            this(0);
        }

        private SerialIoHandlerSetBreakResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerSetBreakResponseParams decode(Decoder arg3) {
            SerialIoHandlerSetBreakResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerSetBreakResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerSetBreakResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerSetBreakResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerSetBreakResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerSetBreakResponseParams deserialize(Message arg1) {
            return SerialIoHandlerSetBreakResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerSetBreakResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class SerialIoHandlerSetBreakResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetBreakResponse mCallback;

        SerialIoHandlerSetBreakResponseParamsForwardToCallback(SetBreakResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(10, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(SerialIoHandlerSetBreakResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerSetBreakResponseParamsProxyToResponder implements SetBreakResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerSetBreakResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            SerialIoHandlerSetBreakResponseParams v0 = new SerialIoHandlerSetBreakResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(10, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class SerialIoHandlerSetControlSignalsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SerialHostControlSignals signals;

        static {
            SerialIoHandlerSetControlSignalsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerSetControlSignalsParams.DEFAULT_STRUCT_INFO = SerialIoHandlerSetControlSignalsParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerSetControlSignalsParams() {
            this(0);
        }

        private SerialIoHandlerSetControlSignalsParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerSetControlSignalsParams decode(Decoder arg3) {
            SerialIoHandlerSetControlSignalsParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerSetControlSignalsParams(arg3.readAndValidateDataHeader(SerialIoHandlerSetControlSignalsParams.VERSION_ARRAY).elementsOrVersion);
                v1.signals = SerialHostControlSignals.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerSetControlSignalsParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerSetControlSignalsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerSetControlSignalsParams deserialize(Message arg1) {
            return SerialIoHandlerSetControlSignalsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerSetControlSignalsParams.DEFAULT_STRUCT_INFO).encode(this.signals, 8, false);
        }
    }

    final class SerialIoHandlerSetControlSignalsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            SerialIoHandlerSetControlSignalsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerSetControlSignalsResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerSetControlSignalsResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerSetControlSignalsResponseParams() {
            this(0);
        }

        private SerialIoHandlerSetControlSignalsResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerSetControlSignalsResponseParams decode(Decoder arg3) {
            SerialIoHandlerSetControlSignalsResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerSetControlSignalsResponseParams(arg3.readAndValidateDataHeader(SerialIoHandlerSetControlSignalsResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerSetControlSignalsResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerSetControlSignalsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerSetControlSignalsResponseParams deserialize(Message arg1) {
            return SerialIoHandlerSetControlSignalsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(SerialIoHandlerSetControlSignalsResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class SerialIoHandlerSetControlSignalsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetControlSignalsResponse mCallback;

        SerialIoHandlerSetControlSignalsResponseParamsForwardToCallback(SetControlSignalsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(7, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(SerialIoHandlerSetControlSignalsResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerSetControlSignalsResponseParamsProxyToResponder implements SetControlSignalsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerSetControlSignalsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            SerialIoHandlerSetControlSignalsResponseParams v0 = new SerialIoHandlerSetControlSignalsResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(7, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class SerialIoHandlerWriteParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public byte[] data;

        static {
            SerialIoHandlerWriteParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerWriteParams.DEFAULT_STRUCT_INFO = SerialIoHandlerWriteParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerWriteParams() {
            this(0);
        }

        private SerialIoHandlerWriteParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerWriteParams decode(Decoder arg4) {
            SerialIoHandlerWriteParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerWriteParams(arg4.readAndValidateDataHeader(SerialIoHandlerWriteParams.VERSION_ARRAY).elementsOrVersion);
                v1.data = arg4.readBytes(8, 0, -1);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerWriteParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerWriteParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerWriteParams deserialize(Message arg1) {
            return SerialIoHandlerWriteParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(SerialIoHandlerWriteParams.DEFAULT_STRUCT_INFO).encode(this.data, 8, 0, -1);
        }
    }

    final class SerialIoHandlerWriteResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int bytesWritten;
        public int error;

        static {
            SerialIoHandlerWriteResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialIoHandlerWriteResponseParams.DEFAULT_STRUCT_INFO = SerialIoHandlerWriteResponseParams.VERSION_ARRAY[0];
        }

        public SerialIoHandlerWriteResponseParams() {
            this(0);
        }

        private SerialIoHandlerWriteResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialIoHandlerWriteResponseParams decode(Decoder arg2) {
            SerialIoHandlerWriteResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialIoHandlerWriteResponseParams(arg2.readAndValidateDataHeader(SerialIoHandlerWriteResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.bytesWritten = arg2.readInt(8);
                v1.error = arg2.readInt(12);
                SerialSendError.validate(v1.error);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialIoHandlerWriteResponseParams deserialize(ByteBuffer arg2) {
            return SerialIoHandlerWriteResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialIoHandlerWriteResponseParams deserialize(Message arg1) {
            return SerialIoHandlerWriteResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3 = arg3.getEncoderAtDataOffset(SerialIoHandlerWriteResponseParams.DEFAULT_STRUCT_INFO);
            arg3.encode(this.bytesWritten, 8);
            arg3.encode(this.error, 12);
        }
    }

    class SerialIoHandlerWriteResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final WriteResponse mCallback;

        SerialIoHandlerWriteResponseParamsForwardToCallback(WriteResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                SerialIoHandlerWriteResponseParams v4_1 = SerialIoHandlerWriteResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.bytesWritten), Integer.valueOf(v4_1.error));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialIoHandlerWriteResponseParamsProxyToResponder implements WriteResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialIoHandlerWriteResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, Integer arg6) {
            SerialIoHandlerWriteResponseParams v0 = new SerialIoHandlerWriteResponseParams();
            v0.bytesWritten = arg5.intValue();
            v0.error = arg6.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((Integer)arg2));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, SerialIoHandler arg2) {
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
                    goto label_24;
                }

                switch(v1_1) {
                    case 3: {
                        goto label_18;
                    }
                    case 4: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().cancelRead(SerialIoHandlerCancelReadParams.deserialize(v4_1.getPayload()).reason);
                return 1;
            label_12:
                this.getImpl().cancelWrite(SerialIoHandlerCancelWriteParams.deserialize(v4_1.getPayload()).reason);
                return 1;
            label_24:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(SerialIoHandler_Internal.MANAGER, v4_1);
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
                        goto label_106;
                    }
                    case 0: {
                        goto label_95;
                    }
                    case 1: {
                        goto label_85;
                    }
                    case 2: {
                        goto label_75;
                    }
                    case 5: {
                        goto label_66;
                    }
                    case 6: {
                        goto label_57;
                    }
                    case 7: {
                        goto label_47;
                    }
                    case 8: {
                        goto label_37;
                    }
                    case 9: {
                        goto label_28;
                    }
                    case 10: {
                        goto label_19;
                    }
                    case 11: {
                        goto label_10;
                    }
                }

                return 0;
            label_66:
                SerialIoHandlerFlushParams.deserialize(v10_1.getPayload());
                this.getImpl().flush(new SerialIoHandlerFlushResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_19:
                SerialIoHandlerSetBreakParams.deserialize(v10_1.getPayload());
                this.getImpl().setBreak(new SerialIoHandlerSetBreakResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_85:
                this.getImpl().read(SerialIoHandlerReadParams.deserialize(v10_1.getPayload()).bytes, new SerialIoHandlerReadResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_37:
                this.getImpl().configurePort(SerialIoHandlerConfigurePortParams.deserialize(v10_1.getPayload()).options, new SerialIoHandlerConfigurePortResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_57:
                SerialIoHandlerGetControlSignalsParams.deserialize(v10_1.getPayload());
                this.getImpl().getControlSignals(new SerialIoHandlerGetControlSignalsResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_106:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), SerialIoHandler_Internal.MANAGER, v10_1, arg11);
            label_10:
                SerialIoHandlerClearBreakParams.deserialize(v10_1.getPayload());
                this.getImpl().clearBreak(new SerialIoHandlerClearBreakResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_75:
                this.getImpl().write(SerialIoHandlerWriteParams.deserialize(v10_1.getPayload()).data, new SerialIoHandlerWriteResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_28:
                SerialIoHandlerGetPortInfoParams.deserialize(v10_1.getPayload());
                this.getImpl().getPortInfo(new SerialIoHandlerGetPortInfoResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_95:
                SerialIoHandlerOpenParams v10_2 = SerialIoHandlerOpenParams.deserialize(v10_1.getPayload());
                this.getImpl().open(v10_2.port, v10_2.options, new SerialIoHandlerOpenResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_47:
                this.getImpl().setControlSignals(SerialIoHandlerSetControlSignalsParams.deserialize(v10_1.getPayload()).signals, new SerialIoHandlerSetControlSignalsResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int CANCEL_READ_ORDINAL = 3;
    private static final int CANCEL_WRITE_ORDINAL = 4;
    private static final int CLEAR_BREAK_ORDINAL = 11;
    private static final int CONFIGURE_PORT_ORDINAL = 8;
    private static final int FLUSH_ORDINAL = 5;
    private static final int GET_CONTROL_SIGNALS_ORDINAL = 6;
    private static final int GET_PORT_INFO_ORDINAL = 9;
    public static final Manager MANAGER = null;
    private static final int OPEN_ORDINAL = 0;
    private static final int READ_ORDINAL = 1;
    private static final int SET_BREAK_ORDINAL = 10;
    private static final int SET_CONTROL_SIGNALS_ORDINAL = 7;
    private static final int WRITE_ORDINAL = 2;

    static {
        SerialIoHandler_Internal.MANAGER = new org.chromium.device.mojom.SerialIoHandler_Internal$1();
    }

    SerialIoHandler_Internal() {
        super();
    }
}

