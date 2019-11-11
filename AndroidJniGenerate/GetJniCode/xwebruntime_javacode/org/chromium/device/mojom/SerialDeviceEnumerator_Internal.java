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

class SerialDeviceEnumerator_Internal {
    final class org.chromium.device.mojom.SerialDeviceEnumerator_Internal$1 extends Manager {
        org.chromium.device.mojom.SerialDeviceEnumerator_Internal$1() {
            super();
        }

        public SerialDeviceEnumerator[] buildArray(int arg1) {
            return new SerialDeviceEnumerator[arg1];
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

        public Stub buildStub(Core arg2, SerialDeviceEnumerator arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((SerialDeviceEnumerator)arg2));
        }

        public String getName() {
            return "device::mojom::SerialDeviceEnumerator";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.SerialDeviceEnumerator$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getDevices(GetDevicesResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new SerialDeviceEnumeratorGetDevicesParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new SerialDeviceEnumeratorGetDevicesResponseParamsForwardToCallback(arg9));
        }
    }

    final class SerialDeviceEnumeratorGetDevicesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            SerialDeviceEnumeratorGetDevicesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            SerialDeviceEnumeratorGetDevicesParams.DEFAULT_STRUCT_INFO = SerialDeviceEnumeratorGetDevicesParams.VERSION_ARRAY[0];
        }

        public SerialDeviceEnumeratorGetDevicesParams() {
            this(0);
        }

        private SerialDeviceEnumeratorGetDevicesParams(int arg2) {
            super(8, arg2);
        }

        public static SerialDeviceEnumeratorGetDevicesParams decode(Decoder arg2) {
            SerialDeviceEnumeratorGetDevicesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new SerialDeviceEnumeratorGetDevicesParams(arg2.readAndValidateDataHeader(SerialDeviceEnumeratorGetDevicesParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static SerialDeviceEnumeratorGetDevicesParams deserialize(ByteBuffer arg2) {
            return SerialDeviceEnumeratorGetDevicesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialDeviceEnumeratorGetDevicesParams deserialize(Message arg1) {
            return SerialDeviceEnumeratorGetDevicesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(SerialDeviceEnumeratorGetDevicesParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class SerialDeviceEnumeratorGetDevicesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public SerialDeviceInfo[] devices;

        static {
            SerialDeviceEnumeratorGetDevicesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            SerialDeviceEnumeratorGetDevicesResponseParams.DEFAULT_STRUCT_INFO = SerialDeviceEnumeratorGetDevicesResponseParams.VERSION_ARRAY[0];
        }

        public SerialDeviceEnumeratorGetDevicesResponseParams() {
            this(0);
        }

        private SerialDeviceEnumeratorGetDevicesResponseParams(int arg2) {
            super(16, arg2);
        }

        public static SerialDeviceEnumeratorGetDevicesResponseParams decode(Decoder arg8) {
            SerialDeviceEnumeratorGetDevicesResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new SerialDeviceEnumeratorGetDevicesResponseParams(arg8.readAndValidateDataHeader(SerialDeviceEnumeratorGetDevicesResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.devices = new SerialDeviceInfo[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.devices[v5] = SerialDeviceInfo.decode(v3.readPointer(v5 * 8 + v2, false));
                }
            }
            catch(Throwable v0) {
                goto label_31;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_31:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static SerialDeviceEnumeratorGetDevicesResponseParams deserialize(ByteBuffer arg2) {
            return SerialDeviceEnumeratorGetDevicesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static SerialDeviceEnumeratorGetDevicesResponseParams deserialize(Message arg1) {
            return SerialDeviceEnumeratorGetDevicesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(SerialDeviceEnumeratorGetDevicesResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.devices == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.devices.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.devices.length; ++v0) {
                    arg6.encode(this.devices[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    class SerialDeviceEnumeratorGetDevicesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetDevicesResponse mCallback;

        SerialDeviceEnumeratorGetDevicesResponseParamsForwardToCallback(GetDevicesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(SerialDeviceEnumeratorGetDevicesResponseParams.deserialize(v4.getPayload()).devices);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class SerialDeviceEnumeratorGetDevicesResponseParamsProxyToResponder implements GetDevicesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        SerialDeviceEnumeratorGetDevicesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((SerialDeviceInfo[])arg1));
        }

        public void call(SerialDeviceInfo[] arg7) {
            SerialDeviceEnumeratorGetDevicesResponseParams v0 = new SerialDeviceEnumeratorGetDevicesResponseParams();
            v0.devices = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, SerialDeviceEnumerator arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(SerialDeviceEnumerator_Internal.MANAGER, v4_1);
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
                        goto label_19;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_19:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), SerialDeviceEnumerator_Internal.MANAGER, v8_1, arg9);
            label_10:
                SerialDeviceEnumeratorGetDevicesParams.deserialize(v8_1.getPayload());
                this.getImpl().getDevices(new SerialDeviceEnumeratorGetDevicesResponseParamsProxyToResponder(this.getCore(), arg9, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v8) {
                System.err.println(v8.toString());
                return 0;
            }
        }
    }

    private static final int GET_DEVICES_ORDINAL;
    public static final Manager MANAGER;

    static {
        SerialDeviceEnumerator_Internal.MANAGER = new org.chromium.device.mojom.SerialDeviceEnumerator_Internal$1();
    }

    SerialDeviceEnumerator_Internal() {
        super();
    }
}

