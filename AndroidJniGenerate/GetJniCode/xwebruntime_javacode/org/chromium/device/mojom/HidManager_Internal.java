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

class HidManager_Internal {
    final class org.chromium.device.mojom.HidManager_Internal$1 extends Manager {
        org.chromium.device.mojom.HidManager_Internal$1() {
            super();
        }

        public HidManager[] buildArray(int arg1) {
            return new HidManager[arg1];
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

        public Stub buildStub(Core arg2, HidManager arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((HidManager)arg2));
        }

        public String getName() {
            return "device::mojom::HidManager";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class HidManagerConnectParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String deviceGuid;

        static {
            HidManagerConnectParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidManagerConnectParams.DEFAULT_STRUCT_INFO = HidManagerConnectParams.VERSION_ARRAY[0];
        }

        public HidManagerConnectParams() {
            this(0);
        }

        private HidManagerConnectParams(int arg2) {
            super(16, arg2);
        }

        public static HidManagerConnectParams decode(Decoder arg3) {
            HidManagerConnectParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new HidManagerConnectParams(arg3.readAndValidateDataHeader(HidManagerConnectParams.VERSION_ARRAY).elementsOrVersion);
                v1.deviceGuid = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static HidManagerConnectParams deserialize(ByteBuffer arg2) {
            return HidManagerConnectParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidManagerConnectParams deserialize(Message arg1) {
            return HidManagerConnectParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(HidManagerConnectParams.DEFAULT_STRUCT_INFO).encode(this.deviceGuid, 8, false);
        }
    }

    final class HidManagerConnectResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public HidConnection connection;

        static {
            HidManagerConnectResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidManagerConnectResponseParams.DEFAULT_STRUCT_INFO = HidManagerConnectResponseParams.VERSION_ARRAY[0];
        }

        public HidManagerConnectResponseParams() {
            this(0);
        }

        private HidManagerConnectResponseParams(int arg2) {
            super(16, arg2);
        }

        public static HidManagerConnectResponseParams decode(Decoder arg4) {
            HidManagerConnectResponseParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new HidManagerConnectResponseParams(arg4.readAndValidateDataHeader(HidManagerConnectResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.connection = arg4.readServiceInterface(8, true, HidConnection.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static HidManagerConnectResponseParams deserialize(ByteBuffer arg2) {
            return HidManagerConnectResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidManagerConnectResponseParams deserialize(Message arg1) {
            return HidManagerConnectResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(HidManagerConnectResponseParams.DEFAULT_STRUCT_INFO).encode(this.connection, 8, true, HidConnection.MANAGER);
        }
    }

    class HidManagerConnectResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final ConnectResponse mCallback;

        HidManagerConnectResponseParamsForwardToCallback(ConnectResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(HidManagerConnectResponseParams.deserialize(v4.getPayload()).connection);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class HidManagerConnectResponseParamsProxyToResponder implements ConnectResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        HidManagerConnectResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((HidConnection)arg1));
        }

        public void call(HidConnection arg6) {
            HidManagerConnectResponseParams v0 = new HidManagerConnectResponseParams();
            v0.connection = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class HidManagerGetDevicesAndSetClientParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public AssociatedInterfaceNotSupported client;

        static {
            HidManagerGetDevicesAndSetClientParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidManagerGetDevicesAndSetClientParams.DEFAULT_STRUCT_INFO = HidManagerGetDevicesAndSetClientParams.VERSION_ARRAY[0];
        }

        public HidManagerGetDevicesAndSetClientParams() {
            this(0);
        }

        private HidManagerGetDevicesAndSetClientParams(int arg2) {
            super(16, arg2);
        }

        public static HidManagerGetDevicesAndSetClientParams decode(Decoder arg3) {
            HidManagerGetDevicesAndSetClientParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new HidManagerGetDevicesAndSetClientParams(arg3.readAndValidateDataHeader(HidManagerGetDevicesAndSetClientParams.VERSION_ARRAY).elementsOrVersion);
                v1.client = arg3.readAssociatedServiceInterfaceNotSupported(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static HidManagerGetDevicesAndSetClientParams deserialize(ByteBuffer arg2) {
            return HidManagerGetDevicesAndSetClientParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidManagerGetDevicesAndSetClientParams deserialize(Message arg1) {
            return HidManagerGetDevicesAndSetClientParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(HidManagerGetDevicesAndSetClientParams.DEFAULT_STRUCT_INFO).encode(this.client, 8, false);
        }
    }

    final class HidManagerGetDevicesAndSetClientResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public HidDeviceInfo[] devices;

        static {
            HidManagerGetDevicesAndSetClientResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidManagerGetDevicesAndSetClientResponseParams.DEFAULT_STRUCT_INFO = HidManagerGetDevicesAndSetClientResponseParams.VERSION_ARRAY[0];
        }

        public HidManagerGetDevicesAndSetClientResponseParams() {
            this(0);
        }

        private HidManagerGetDevicesAndSetClientResponseParams(int arg2) {
            super(16, arg2);
        }

        public static HidManagerGetDevicesAndSetClientResponseParams decode(Decoder arg8) {
            HidManagerGetDevicesAndSetClientResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new HidManagerGetDevicesAndSetClientResponseParams(arg8.readAndValidateDataHeader(HidManagerGetDevicesAndSetClientResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.devices = new HidDeviceInfo[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.devices[v5] = HidDeviceInfo.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static HidManagerGetDevicesAndSetClientResponseParams deserialize(ByteBuffer arg2) {
            return HidManagerGetDevicesAndSetClientResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidManagerGetDevicesAndSetClientResponseParams deserialize(Message arg1) {
            return HidManagerGetDevicesAndSetClientResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(HidManagerGetDevicesAndSetClientResponseParams.DEFAULT_STRUCT_INFO);
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

    class HidManagerGetDevicesAndSetClientResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetDevicesAndSetClientResponse mCallback;

        HidManagerGetDevicesAndSetClientResponseParamsForwardToCallback(GetDevicesAndSetClientResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(HidManagerGetDevicesAndSetClientResponseParams.deserialize(v4.getPayload()).devices);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class HidManagerGetDevicesAndSetClientResponseParamsProxyToResponder implements GetDevicesAndSetClientResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        HidManagerGetDevicesAndSetClientResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((HidDeviceInfo[])arg1));
        }

        public void call(HidDeviceInfo[] arg7) {
            HidManagerGetDevicesAndSetClientResponseParams v0 = new HidManagerGetDevicesAndSetClientResponseParams();
            v0.devices = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class HidManagerGetDevicesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            HidManagerGetDevicesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            HidManagerGetDevicesParams.DEFAULT_STRUCT_INFO = HidManagerGetDevicesParams.VERSION_ARRAY[0];
        }

        public HidManagerGetDevicesParams() {
            this(0);
        }

        private HidManagerGetDevicesParams(int arg2) {
            super(8, arg2);
        }

        public static HidManagerGetDevicesParams decode(Decoder arg2) {
            HidManagerGetDevicesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new HidManagerGetDevicesParams(arg2.readAndValidateDataHeader(HidManagerGetDevicesParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static HidManagerGetDevicesParams deserialize(ByteBuffer arg2) {
            return HidManagerGetDevicesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidManagerGetDevicesParams deserialize(Message arg1) {
            return HidManagerGetDevicesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(HidManagerGetDevicesParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class HidManagerGetDevicesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public HidDeviceInfo[] devices;

        static {
            HidManagerGetDevicesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            HidManagerGetDevicesResponseParams.DEFAULT_STRUCT_INFO = HidManagerGetDevicesResponseParams.VERSION_ARRAY[0];
        }

        public HidManagerGetDevicesResponseParams() {
            this(0);
        }

        private HidManagerGetDevicesResponseParams(int arg2) {
            super(16, arg2);
        }

        public static HidManagerGetDevicesResponseParams decode(Decoder arg8) {
            HidManagerGetDevicesResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new HidManagerGetDevicesResponseParams(arg8.readAndValidateDataHeader(HidManagerGetDevicesResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.devices = new HidDeviceInfo[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.devices[v5] = HidDeviceInfo.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static HidManagerGetDevicesResponseParams deserialize(ByteBuffer arg2) {
            return HidManagerGetDevicesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static HidManagerGetDevicesResponseParams deserialize(Message arg1) {
            return HidManagerGetDevicesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(HidManagerGetDevicesResponseParams.DEFAULT_STRUCT_INFO);
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

    class HidManagerGetDevicesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetDevicesResponse mCallback;

        HidManagerGetDevicesResponseParamsForwardToCallback(GetDevicesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(HidManagerGetDevicesResponseParams.deserialize(v5.getPayload()).devices);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class HidManagerGetDevicesResponseParamsProxyToResponder implements GetDevicesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        HidManagerGetDevicesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((HidDeviceInfo[])arg1));
        }

        public void call(HidDeviceInfo[] arg7) {
            HidManagerGetDevicesResponseParams v0 = new HidManagerGetDevicesResponseParams();
            v0.devices = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.HidManager$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void connect(String arg8, ConnectResponse arg9) {
            HidManagerConnectParams v0 = new HidManagerConnectParams();
            v0.deviceGuid = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new HidManagerConnectResponseParamsForwardToCallback(arg9));
        }

        public void getDevices(GetDevicesResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new HidManagerGetDevicesParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new HidManagerGetDevicesResponseParamsForwardToCallback(arg8));
        }

        public void getDevicesAndSetClient(AssociatedInterfaceNotSupported arg8, GetDevicesAndSetClientResponse arg9) {
            HidManagerGetDevicesAndSetClientParams v0 = new HidManagerGetDevicesAndSetClientParams();
            v0.client = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new HidManagerGetDevicesAndSetClientResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, HidManager arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(HidManager_Internal.MANAGER, v4_1);
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
                        goto label_39;
                    }
                    case 0: {
                        goto label_29;
                    }
                    case 1: {
                        goto label_20;
                    }
                    case 2: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                HidManagerGetDevicesParams.deserialize(v9_1.getPayload());
                this.getImpl().getDevices(new HidManagerGetDevicesResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_39:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), HidManager_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().connect(HidManagerConnectParams.deserialize(v9_1.getPayload()).deviceGuid, new HidManagerConnectResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_29:
                this.getImpl().getDevicesAndSetClient(HidManagerGetDevicesAndSetClientParams.deserialize(v9_1.getPayload()).client, new HidManagerGetDevicesAndSetClientResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int CONNECT_ORDINAL = 2;
    private static final int GET_DEVICES_AND_SET_CLIENT_ORDINAL = 0;
    private static final int GET_DEVICES_ORDINAL = 1;
    public static final Manager MANAGER;

    static {
        HidManager_Internal.MANAGER = new org.chromium.device.mojom.HidManager_Internal$1();
    }

    HidManager_Internal() {
        super();
    }
}

