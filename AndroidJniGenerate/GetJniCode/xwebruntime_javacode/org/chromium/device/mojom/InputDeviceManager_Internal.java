package org.chromium.device.mojom;

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

class InputDeviceManager_Internal {
    final class org.chromium.device.mojom.InputDeviceManager_Internal$1 extends Manager {
        org.chromium.device.mojom.InputDeviceManager_Internal$1() {
            super();
        }

        public InputDeviceManager[] buildArray(int arg1) {
            return new InputDeviceManager[arg1];
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

        public Stub buildStub(Core arg2, InputDeviceManager arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((InputDeviceManager)arg2));
        }

        public String getName() {
            return "device::mojom::InputDeviceManager";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class InputDeviceManagerGetDevicesAndSetClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AssociatedInterfaceNotSupported client;

        static {
            InputDeviceManagerGetDevicesAndSetClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InputDeviceManagerGetDevicesAndSetClientParams.DEFAULT_STRUCT_INFO = InputDeviceManagerGetDevicesAndSetClientParams.VERSION_ARRAY[0];
        }

        public InputDeviceManagerGetDevicesAndSetClientParams() {
            this(0);
        }

        private InputDeviceManagerGetDevicesAndSetClientParams(int arg2) {
            super(16, arg2);
        }

        public static InputDeviceManagerGetDevicesAndSetClientParams decode(Decoder arg3) {
            InputDeviceManagerGetDevicesAndSetClientParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InputDeviceManagerGetDevicesAndSetClientParams(arg3.readAndValidateDataHeader(InputDeviceManagerGetDevicesAndSetClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg3.readAssociatedServiceInterfaceNotSupported(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InputDeviceManagerGetDevicesAndSetClientParams deserialize(ByteBuffer arg2) {
            return InputDeviceManagerGetDevicesAndSetClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InputDeviceManagerGetDevicesAndSetClientParams deserialize(Message arg1) {
            return InputDeviceManagerGetDevicesAndSetClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(InputDeviceManagerGetDevicesAndSetClientParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false);
        }
    }

    final class InputDeviceManagerGetDevicesAndSetClientResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InputDeviceInfo[] devices;

        static {
            InputDeviceManagerGetDevicesAndSetClientResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InputDeviceManagerGetDevicesAndSetClientResponseParams.DEFAULT_STRUCT_INFO = InputDeviceManagerGetDevicesAndSetClientResponseParams.VERSION_ARRAY[0];
        }

        public InputDeviceManagerGetDevicesAndSetClientResponseParams() {
            this(0);
        }

        private InputDeviceManagerGetDevicesAndSetClientResponseParams(int arg2) {
            super(16, arg2);
        }

        public static InputDeviceManagerGetDevicesAndSetClientResponseParams decode(Decoder arg8) {
            InputDeviceManagerGetDevicesAndSetClientResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new InputDeviceManagerGetDevicesAndSetClientResponseParams(arg8.readAndValidateDataHeader(InputDeviceManagerGetDevicesAndSetClientResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.devices = new InputDeviceInfo[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.devices[v5] = InputDeviceInfo.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static InputDeviceManagerGetDevicesAndSetClientResponseParams deserialize(ByteBuffer arg2) {
            return InputDeviceManagerGetDevicesAndSetClientResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InputDeviceManagerGetDevicesAndSetClientResponseParams deserialize(Message arg1) {
            return InputDeviceManagerGetDevicesAndSetClientResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(InputDeviceManagerGetDevicesAndSetClientResponseParams.DEFAULT_STRUCT_INFO);
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

    class InputDeviceManagerGetDevicesAndSetClientResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetDevicesAndSetClientResponse mCallback;

        InputDeviceManagerGetDevicesAndSetClientResponseParamsForwardToCallback(GetDevicesAndSetClientResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(InputDeviceManagerGetDevicesAndSetClientResponseParams.deserialize(v4.getPayload()).devices);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class InputDeviceManagerGetDevicesAndSetClientResponseParamsProxyToResponder implements GetDevicesAndSetClientResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        InputDeviceManagerGetDevicesAndSetClientResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((InputDeviceInfo[])arg1));
        }

        public void call(InputDeviceInfo[] arg7) {
            InputDeviceManagerGetDevicesAndSetClientResponseParams v0 = new InputDeviceManagerGetDevicesAndSetClientResponseParams();
            v0.devices = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class InputDeviceManagerGetDevicesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            InputDeviceManagerGetDevicesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            InputDeviceManagerGetDevicesParams.DEFAULT_STRUCT_INFO = InputDeviceManagerGetDevicesParams.VERSION_ARRAY[0];
        }

        public InputDeviceManagerGetDevicesParams() {
            this(0);
        }

        private InputDeviceManagerGetDevicesParams(int arg2) {
            super(8, arg2);
        }

        public static InputDeviceManagerGetDevicesParams decode(Decoder arg2) {
            InputDeviceManagerGetDevicesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new InputDeviceManagerGetDevicesParams(arg2.readAndValidateDataHeader(InputDeviceManagerGetDevicesParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static InputDeviceManagerGetDevicesParams deserialize(ByteBuffer arg2) {
            return InputDeviceManagerGetDevicesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InputDeviceManagerGetDevicesParams deserialize(Message arg1) {
            return InputDeviceManagerGetDevicesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(InputDeviceManagerGetDevicesParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class InputDeviceManagerGetDevicesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InputDeviceInfo[] devices;

        static {
            InputDeviceManagerGetDevicesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InputDeviceManagerGetDevicesResponseParams.DEFAULT_STRUCT_INFO = InputDeviceManagerGetDevicesResponseParams.VERSION_ARRAY[0];
        }

        public InputDeviceManagerGetDevicesResponseParams() {
            this(0);
        }

        private InputDeviceManagerGetDevicesResponseParams(int arg2) {
            super(16, arg2);
        }

        public static InputDeviceManagerGetDevicesResponseParams decode(Decoder arg8) {
            InputDeviceManagerGetDevicesResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new InputDeviceManagerGetDevicesResponseParams(arg8.readAndValidateDataHeader(InputDeviceManagerGetDevicesResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.devices = new InputDeviceInfo[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.devices[v5] = InputDeviceInfo.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static InputDeviceManagerGetDevicesResponseParams deserialize(ByteBuffer arg2) {
            return InputDeviceManagerGetDevicesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InputDeviceManagerGetDevicesResponseParams deserialize(Message arg1) {
            return InputDeviceManagerGetDevicesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(InputDeviceManagerGetDevicesResponseParams.DEFAULT_STRUCT_INFO);
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

    class InputDeviceManagerGetDevicesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetDevicesResponse mCallback;

        InputDeviceManagerGetDevicesResponseParamsForwardToCallback(GetDevicesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(InputDeviceManagerGetDevicesResponseParams.deserialize(v5.getPayload()).devices);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class InputDeviceManagerGetDevicesResponseParamsProxyToResponder implements GetDevicesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        InputDeviceManagerGetDevicesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((InputDeviceInfo[])arg1));
        }

        public void call(InputDeviceInfo[] arg7) {
            InputDeviceManagerGetDevicesResponseParams v0 = new InputDeviceManagerGetDevicesResponseParams();
            v0.devices = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.InputDeviceManager$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getDevices(GetDevicesResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new InputDeviceManagerGetDevicesParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new InputDeviceManagerGetDevicesResponseParamsForwardToCallback(arg8));
        }

        public void getDevicesAndSetClient(AssociatedInterfaceNotSupported arg8, GetDevicesAndSetClientResponse arg9) {
            InputDeviceManagerGetDevicesAndSetClientParams v0 = new InputDeviceManagerGetDevicesAndSetClientParams();
            v0.client = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new InputDeviceManagerGetDevicesAndSetClientResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, InputDeviceManager arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(InputDeviceManager_Internal.MANAGER, v4_1);
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
                        goto label_29;
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
                this.getImpl().getDevicesAndSetClient(InputDeviceManagerGetDevicesAndSetClientParams.deserialize(v9_1.getPayload()).client, new InputDeviceManagerGetDevicesAndSetClientResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_10:
                InputDeviceManagerGetDevicesParams.deserialize(v9_1.getPayload());
                this.getImpl().getDevices(new InputDeviceManagerGetDevicesResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_29:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), InputDeviceManager_Internal.MANAGER, v9_1, arg10);
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int GET_DEVICES_AND_SET_CLIENT_ORDINAL = 0;
    private static final int GET_DEVICES_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        InputDeviceManager_Internal.MANAGER = new org.chromium.device.mojom.InputDeviceManager_Internal$1();
    }

    InputDeviceManager_Internal() {
        super();
    }
}

