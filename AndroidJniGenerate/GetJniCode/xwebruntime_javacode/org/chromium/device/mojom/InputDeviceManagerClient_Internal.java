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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class InputDeviceManagerClient_Internal {
    final class org.chromium.device.mojom.InputDeviceManagerClient_Internal$1 extends Manager {
        org.chromium.device.mojom.InputDeviceManagerClient_Internal$1() {
            super();
        }

        public InputDeviceManagerClient[] buildArray(int arg1) {
            return new InputDeviceManagerClient[arg1];
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

        public Stub buildStub(Core arg2, InputDeviceManagerClient arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((InputDeviceManagerClient)arg2));
        }

        public String getName() {
            return "device::mojom::InputDeviceManagerClient";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class InputDeviceManagerClientInputDeviceAddedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InputDeviceInfo deviceInfo;

        static {
            InputDeviceManagerClientInputDeviceAddedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InputDeviceManagerClientInputDeviceAddedParams.DEFAULT_STRUCT_INFO = InputDeviceManagerClientInputDeviceAddedParams.VERSION_ARRAY[0];
        }

        public InputDeviceManagerClientInputDeviceAddedParams() {
            this(0);
        }

        private InputDeviceManagerClientInputDeviceAddedParams(int arg2) {
            super(16, arg2);
        }

        public static InputDeviceManagerClientInputDeviceAddedParams decode(Decoder arg3) {
            InputDeviceManagerClientInputDeviceAddedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InputDeviceManagerClientInputDeviceAddedParams(arg3.readAndValidateDataHeader(InputDeviceManagerClientInputDeviceAddedParams.VERSION_ARRAY).elementsOrVersion);
                v1.deviceInfo = InputDeviceInfo.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InputDeviceManagerClientInputDeviceAddedParams deserialize(ByteBuffer arg2) {
            return InputDeviceManagerClientInputDeviceAddedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InputDeviceManagerClientInputDeviceAddedParams deserialize(Message arg1) {
            return InputDeviceManagerClientInputDeviceAddedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(InputDeviceManagerClientInputDeviceAddedParams.DEFAULT_STRUCT_INFO).encode(this.deviceInfo, 8, false);
        }
    }

    final class InputDeviceManagerClientInputDeviceRemovedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String id;

        static {
            InputDeviceManagerClientInputDeviceRemovedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InputDeviceManagerClientInputDeviceRemovedParams.DEFAULT_STRUCT_INFO = InputDeviceManagerClientInputDeviceRemovedParams.VERSION_ARRAY[0];
        }

        public InputDeviceManagerClientInputDeviceRemovedParams() {
            this(0);
        }

        private InputDeviceManagerClientInputDeviceRemovedParams(int arg2) {
            super(16, arg2);
        }

        public static InputDeviceManagerClientInputDeviceRemovedParams decode(Decoder arg3) {
            InputDeviceManagerClientInputDeviceRemovedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new InputDeviceManagerClientInputDeviceRemovedParams(arg3.readAndValidateDataHeader(InputDeviceManagerClientInputDeviceRemovedParams.VERSION_ARRAY).elementsOrVersion);
                v1.id = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static InputDeviceManagerClientInputDeviceRemovedParams deserialize(ByteBuffer arg2) {
            return InputDeviceManagerClientInputDeviceRemovedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InputDeviceManagerClientInputDeviceRemovedParams deserialize(Message arg1) {
            return InputDeviceManagerClientInputDeviceRemovedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(InputDeviceManagerClientInputDeviceRemovedParams.DEFAULT_STRUCT_INFO).encode(this.id, 8, false);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.InputDeviceManagerClient$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void inputDeviceAdded(InputDeviceInfo arg5) {
            InputDeviceManagerClientInputDeviceAddedParams v0 = new InputDeviceManagerClientInputDeviceAddedParams();
            v0.deviceInfo = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void inputDeviceRemoved(String arg5) {
            InputDeviceManagerClientInputDeviceRemovedParams v0 = new InputDeviceManagerClientInputDeviceRemovedParams();
            v0.id = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, InputDeviceManagerClient arg2) {
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
                    case 0: {
                        goto label_18;
                    }
                    case 1: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().inputDeviceAdded(InputDeviceManagerClientInputDeviceAddedParams.deserialize(v4_1.getPayload()).deviceInfo);
                return 1;
            label_12:
                this.getImpl().inputDeviceRemoved(InputDeviceManagerClientInputDeviceRemovedParams.deserialize(v4_1.getPayload()).id);
                return 1;
            label_24:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(InputDeviceManagerClient_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg4, MessageReceiver arg5) {
            try {
                ServiceMessage v4_1 = arg4.asServiceMessage();
                MessageHeader v1 = v4_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                if(v1.getType() != -1) {
                    return 0;
                }

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), InputDeviceManagerClient_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    private static final int INPUT_DEVICE_ADDED_ORDINAL = 0;
    private static final int INPUT_DEVICE_REMOVED_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        InputDeviceManagerClient_Internal.MANAGER = new org.chromium.device.mojom.InputDeviceManagerClient_Internal$1();
    }

    InputDeviceManagerClient_Internal() {
        super();
    }
}

