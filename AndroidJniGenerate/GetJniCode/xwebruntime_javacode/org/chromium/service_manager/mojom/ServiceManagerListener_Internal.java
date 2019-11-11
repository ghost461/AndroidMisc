package org.chromium.service_manager.mojom;

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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class ServiceManagerListener_Internal {
    final class org.chromium.service_manager.mojom.ServiceManagerListener_Internal$1 extends Manager {
        org.chromium.service_manager.mojom.ServiceManagerListener_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public ServiceManagerListener[] buildArray(int arg1) {
            return new ServiceManagerListener[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceManagerListener)arg2));
        }

        public org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Stub buildStub(Core arg2, ServiceManagerListener arg3) {
            return new org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "service_manager::mojom::ServiceManagerListener";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Proxy extends AbstractProxy implements org.chromium.service_manager.mojom.ServiceManagerListener$Proxy {
        org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onInit(RunningServiceInfo[] arg5) {
            ServiceManagerListenerOnInitParams v0 = new ServiceManagerListenerOnInitParams();
            v0.runningServices = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onServiceCreated(RunningServiceInfo arg5) {
            ServiceManagerListenerOnServiceCreatedParams v0 = new ServiceManagerListenerOnServiceCreatedParams();
            v0.service = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onServiceFailedToStart(Identity arg5) {
            ServiceManagerListenerOnServiceFailedToStartParams v0 = new ServiceManagerListenerOnServiceFailedToStartParams();
            v0.identity = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void onServicePidReceived(Identity arg4, int arg5) {
            ServiceManagerListenerOnServicePidReceivedParams v0 = new ServiceManagerListenerOnServicePidReceivedParams();
            v0.identity = arg4;
            v0.pid = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }

        public void onServiceStarted(Identity arg4, int arg5) {
            ServiceManagerListenerOnServiceStartedParams v0 = new ServiceManagerListenerOnServiceStartedParams();
            v0.identity = arg4;
            v0.pid = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onServiceStopped(Identity arg5) {
            ServiceManagerListenerOnServiceStoppedParams v0 = new ServiceManagerListenerOnServiceStoppedParams();
            v0.identity = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }
    }

    final class ServiceManagerListenerOnInitParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public RunningServiceInfo[] runningServices;

        static {
            ServiceManagerListenerOnInitParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceManagerListenerOnInitParams.DEFAULT_STRUCT_INFO = ServiceManagerListenerOnInitParams.VERSION_ARRAY[0];
        }

        public ServiceManagerListenerOnInitParams() {
            this(0);
        }

        private ServiceManagerListenerOnInitParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceManagerListenerOnInitParams decode(Decoder arg8) {
            ServiceManagerListenerOnInitParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new ServiceManagerListenerOnInitParams(arg8.readAndValidateDataHeader(ServiceManagerListenerOnInitParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.runningServices = new RunningServiceInfo[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.runningServices[v5] = RunningServiceInfo.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static ServiceManagerListenerOnInitParams deserialize(ByteBuffer arg2) {
            return ServiceManagerListenerOnInitParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceManagerListenerOnInitParams deserialize(Message arg1) {
            return ServiceManagerListenerOnInitParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(ServiceManagerListenerOnInitParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.runningServices == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.runningServices.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.runningServices.length; ++v0) {
                    arg6.encode(this.runningServices[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    final class ServiceManagerListenerOnServiceCreatedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public RunningServiceInfo service;

        static {
            ServiceManagerListenerOnServiceCreatedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceManagerListenerOnServiceCreatedParams.DEFAULT_STRUCT_INFO = ServiceManagerListenerOnServiceCreatedParams.VERSION_ARRAY[0];
        }

        public ServiceManagerListenerOnServiceCreatedParams() {
            this(0);
        }

        private ServiceManagerListenerOnServiceCreatedParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceManagerListenerOnServiceCreatedParams decode(Decoder arg3) {
            ServiceManagerListenerOnServiceCreatedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceManagerListenerOnServiceCreatedParams(arg3.readAndValidateDataHeader(ServiceManagerListenerOnServiceCreatedParams.VERSION_ARRAY).elementsOrVersion);
                v1.service = RunningServiceInfo.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceManagerListenerOnServiceCreatedParams deserialize(ByteBuffer arg2) {
            return ServiceManagerListenerOnServiceCreatedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceManagerListenerOnServiceCreatedParams deserialize(Message arg1) {
            return ServiceManagerListenerOnServiceCreatedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceManagerListenerOnServiceCreatedParams.DEFAULT_STRUCT_INFO).encode(this.service, 8, false);
        }
    }

    final class ServiceManagerListenerOnServiceFailedToStartParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity identity;

        static {
            ServiceManagerListenerOnServiceFailedToStartParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceManagerListenerOnServiceFailedToStartParams.DEFAULT_STRUCT_INFO = ServiceManagerListenerOnServiceFailedToStartParams.VERSION_ARRAY[0];
        }

        public ServiceManagerListenerOnServiceFailedToStartParams() {
            this(0);
        }

        private ServiceManagerListenerOnServiceFailedToStartParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceManagerListenerOnServiceFailedToStartParams decode(Decoder arg3) {
            ServiceManagerListenerOnServiceFailedToStartParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceManagerListenerOnServiceFailedToStartParams(arg3.readAndValidateDataHeader(ServiceManagerListenerOnServiceFailedToStartParams.VERSION_ARRAY).elementsOrVersion);
                v1.identity = Identity.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceManagerListenerOnServiceFailedToStartParams deserialize(ByteBuffer arg2) {
            return ServiceManagerListenerOnServiceFailedToStartParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceManagerListenerOnServiceFailedToStartParams deserialize(Message arg1) {
            return ServiceManagerListenerOnServiceFailedToStartParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceManagerListenerOnServiceFailedToStartParams.DEFAULT_STRUCT_INFO).encode(this.identity, 8, false);
        }
    }

    final class ServiceManagerListenerOnServicePidReceivedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity identity;
        public int pid;

        static {
            ServiceManagerListenerOnServicePidReceivedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceManagerListenerOnServicePidReceivedParams.DEFAULT_STRUCT_INFO = ServiceManagerListenerOnServicePidReceivedParams.VERSION_ARRAY[0];
        }

        public ServiceManagerListenerOnServicePidReceivedParams() {
            this(0);
        }

        private ServiceManagerListenerOnServicePidReceivedParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceManagerListenerOnServicePidReceivedParams decode(Decoder arg3) {
            ServiceManagerListenerOnServicePidReceivedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceManagerListenerOnServicePidReceivedParams(arg3.readAndValidateDataHeader(ServiceManagerListenerOnServicePidReceivedParams.VERSION_ARRAY).elementsOrVersion);
                v1.identity = Identity.decode(arg3.readPointer(8, false));
                v1.pid = arg3.readInt(16);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceManagerListenerOnServicePidReceivedParams deserialize(ByteBuffer arg2) {
            return ServiceManagerListenerOnServicePidReceivedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceManagerListenerOnServicePidReceivedParams deserialize(Message arg1) {
            return ServiceManagerListenerOnServicePidReceivedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceManagerListenerOnServicePidReceivedParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.identity, 8, false);
            arg4.encode(this.pid, 16);
        }
    }

    final class ServiceManagerListenerOnServiceStartedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity identity;
        public int pid;

        static {
            ServiceManagerListenerOnServiceStartedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceManagerListenerOnServiceStartedParams.DEFAULT_STRUCT_INFO = ServiceManagerListenerOnServiceStartedParams.VERSION_ARRAY[0];
        }

        public ServiceManagerListenerOnServiceStartedParams() {
            this(0);
        }

        private ServiceManagerListenerOnServiceStartedParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceManagerListenerOnServiceStartedParams decode(Decoder arg3) {
            ServiceManagerListenerOnServiceStartedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceManagerListenerOnServiceStartedParams(arg3.readAndValidateDataHeader(ServiceManagerListenerOnServiceStartedParams.VERSION_ARRAY).elementsOrVersion);
                v1.identity = Identity.decode(arg3.readPointer(8, false));
                v1.pid = arg3.readInt(16);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceManagerListenerOnServiceStartedParams deserialize(ByteBuffer arg2) {
            return ServiceManagerListenerOnServiceStartedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceManagerListenerOnServiceStartedParams deserialize(Message arg1) {
            return ServiceManagerListenerOnServiceStartedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceManagerListenerOnServiceStartedParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.identity, 8, false);
            arg4.encode(this.pid, 16);
        }
    }

    final class ServiceManagerListenerOnServiceStoppedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity identity;

        static {
            ServiceManagerListenerOnServiceStoppedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceManagerListenerOnServiceStoppedParams.DEFAULT_STRUCT_INFO = ServiceManagerListenerOnServiceStoppedParams.VERSION_ARRAY[0];
        }

        public ServiceManagerListenerOnServiceStoppedParams() {
            this(0);
        }

        private ServiceManagerListenerOnServiceStoppedParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceManagerListenerOnServiceStoppedParams decode(Decoder arg3) {
            ServiceManagerListenerOnServiceStoppedParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceManagerListenerOnServiceStoppedParams(arg3.readAndValidateDataHeader(ServiceManagerListenerOnServiceStoppedParams.VERSION_ARRAY).elementsOrVersion);
                v1.identity = Identity.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceManagerListenerOnServiceStoppedParams deserialize(ByteBuffer arg2) {
            return ServiceManagerListenerOnServiceStoppedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceManagerListenerOnServiceStoppedParams deserialize(Message arg1) {
            return ServiceManagerListenerOnServiceStoppedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceManagerListenerOnServiceStoppedParams.DEFAULT_STRUCT_INFO).encode(this.identity, 8, false);
        }
    }

    final class org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Stub extends Stub {
        org.chromium.service_manager.mojom.ServiceManagerListener_Internal$Stub(Core arg1, ServiceManagerListener arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5_1 = arg5.asServiceMessage();
                MessageHeader v1 = v5_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_50;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_44;
                    }
                    case 1: {
                        goto label_38;
                    }
                    case 2: {
                        goto label_31;
                    }
                    case 3: {
                        goto label_24;
                    }
                    case 4: {
                        goto label_18;
                    }
                    case 5: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().onServiceFailedToStart(ServiceManagerListenerOnServiceFailedToStartParams.deserialize(v5_1.getPayload()).identity);
                return 1;
            label_38:
                this.getImpl().onServiceCreated(ServiceManagerListenerOnServiceCreatedParams.deserialize(v5_1.getPayload()).service);
                return 1;
            label_24:
                ServiceManagerListenerOnServicePidReceivedParams v5_2 = ServiceManagerListenerOnServicePidReceivedParams.deserialize(v5_1.getPayload());
                this.getImpl().onServicePidReceived(v5_2.identity, v5_2.pid);
                return 1;
            label_44:
                this.getImpl().onInit(ServiceManagerListenerOnInitParams.deserialize(v5_1.getPayload()).runningServices);
                return 1;
            label_12:
                this.getImpl().onServiceStopped(ServiceManagerListenerOnServiceStoppedParams.deserialize(v5_1.getPayload()).identity);
                return 1;
            label_31:
                ServiceManagerListenerOnServiceStartedParams v5_3 = ServiceManagerListenerOnServiceStartedParams.deserialize(v5_1.getPayload());
                this.getImpl().onServiceStarted(v5_3.identity, v5_3.pid);
                return 1;
            label_50:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceManagerListener_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceManagerListener_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_INIT_ORDINAL = 0;
    private static final int ON_SERVICE_CREATED_ORDINAL = 1;
    private static final int ON_SERVICE_FAILED_TO_START_ORDINAL = 4;
    private static final int ON_SERVICE_PID_RECEIVED_ORDINAL = 3;
    private static final int ON_SERVICE_STARTED_ORDINAL = 2;
    private static final int ON_SERVICE_STOPPED_ORDINAL = 5;

    static {
        ServiceManagerListener_Internal.MANAGER = new org.chromium.service_manager.mojom.ServiceManagerListener_Internal$1();
    }

    ServiceManagerListener_Internal() {
        super();
    }
}

