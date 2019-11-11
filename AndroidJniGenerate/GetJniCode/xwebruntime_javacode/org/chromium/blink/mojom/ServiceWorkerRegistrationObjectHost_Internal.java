package org.chromium.blink.mojom;

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

class ServiceWorkerRegistrationObjectHost_Internal {
    final class org.chromium.blink.mojom.ServiceWorkerRegistrationObjectHost_Internal$1 extends Manager {
        org.chromium.blink.mojom.ServiceWorkerRegistrationObjectHost_Internal$1() {
            super();
        }

        public ServiceWorkerRegistrationObjectHost[] buildArray(int arg1) {
            return new ServiceWorkerRegistrationObjectHost[arg1];
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

        public Stub buildStub(Core arg2, ServiceWorkerRegistrationObjectHost arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((ServiceWorkerRegistrationObjectHost)arg2));
        }

        public String getName() {
            return "blink::mojom::ServiceWorkerRegistrationObjectHost";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.ServiceWorkerRegistrationObjectHost$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void enableNavigationPreload(boolean arg8, EnableNavigationPreloadResponse arg9) {
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams v0 = new ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams();
            v0.enable = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParamsForwardToCallback(arg9));
        }

        public void getNavigationPreloadState(GetNavigationPreloadStateResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParamsForwardToCallback(arg9));
        }

        public void setNavigationPreloadHeader(String arg8, SetNavigationPreloadHeaderResponse arg9) {
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams v0 = new ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams();
            v0.value = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParamsForwardToCallback(arg9));
        }

        public void unregister(UnregisterResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ServiceWorkerRegistrationObjectHostUnregisterParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new ServiceWorkerRegistrationObjectHostUnregisterResponseParamsForwardToCallback(arg8));
        }

        public void update(UpdateResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new ServiceWorkerRegistrationObjectHostUpdateParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ServiceWorkerRegistrationObjectHostUpdateResponseParamsForwardToCallback(arg9));
        }
    }

    final class ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean enable;

        static {
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.VERSION_ARRAY).elementsOrVersion);
                v1.enable = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.DEFAULT_STRUCT_INFO).encode(this.enable, 8, 0);
        }
    }

    final class ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public String errorMsg;

        static {
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                ServiceWorkerErrorType.validate(v1.error);
                v1.errorMsg = arg3.readString(16, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.errorMsg, 16, true);
        }
    }

    class ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final EnableNavigationPreloadResponse mCallback;

        ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParamsForwardToCallback(EnableNavigationPreloadResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams v4_1 = ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.error), v4_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParamsProxyToResponder implements EnableNavigationPreloadResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, String arg6) {
            ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams v0 = new ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParams();
            v0.error = arg5.intValue();
            v0.errorMsg = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((String)arg2));
        }
    }

    final class ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams decode(Decoder arg2) {
            ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams(arg2.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public String errorMsg;
        public NavigationPreloadState state;

        static {
            ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams(int arg2) {
            super(0x20, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                ServiceWorkerErrorType.validate(v1.error);
                v1.errorMsg = arg3.readString(16, true);
                v1.state = NavigationPreloadState.decode(arg3.readPointer(24, true));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.errorMsg, 16, true);
            arg4.encode(this.state, 24, true);
        }
    }

    class ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetNavigationPreloadStateResponse mCallback;

        ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParamsForwardToCallback(GetNavigationPreloadStateResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams v5_1 = ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.error), v5_1.errorMsg, v5_1.state);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParamsProxyToResponder implements GetNavigationPreloadStateResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, String arg6, NavigationPreloadState arg7) {
            ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams v0 = new ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParams();
            v0.error = arg5.intValue();
            v0.errorMsg = arg6;
            v0.state = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2, Object arg3) {
            this.call(((Integer)arg1), ((String)arg2), ((NavigationPreloadState)arg3));
        }
    }

    final class ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String value;

        static {
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams(int arg2) {
            super(16, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.VERSION_ARRAY).elementsOrVersion);
                v1.value = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.DEFAULT_STRUCT_INFO).encode(this.value, 8, false);
        }
    }

    final class ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public String errorMsg;

        static {
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                ServiceWorkerErrorType.validate(v1.error);
                v1.errorMsg = arg3.readString(16, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.errorMsg, 16, true);
        }
    }

    class ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetNavigationPreloadHeaderResponse mCallback;

        ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParamsForwardToCallback(SetNavigationPreloadHeaderResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(4, 2)) {
                    return 0;
                }

                ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams v5_1 = ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.error), v5_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParamsProxyToResponder implements SetNavigationPreloadHeaderResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, String arg7) {
            ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams v0 = new ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParams();
            v0.error = arg6.intValue();
            v0.errorMsg = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((String)arg2));
        }
    }

    final class ServiceWorkerRegistrationObjectHostUnregisterParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerRegistrationObjectHostUnregisterParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerRegistrationObjectHostUnregisterParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostUnregisterParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostUnregisterParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostUnregisterParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostUnregisterParams decode(Decoder arg2) {
            ServiceWorkerRegistrationObjectHostUnregisterParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostUnregisterParams(arg2.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostUnregisterParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostUnregisterParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostUnregisterParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostUnregisterParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostUnregisterParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostUnregisterParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ServiceWorkerRegistrationObjectHostUnregisterResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public String errorMsg;

        static {
            ServiceWorkerRegistrationObjectHostUnregisterResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerRegistrationObjectHostUnregisterResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostUnregisterResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostUnregisterResponseParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostUnregisterResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostUnregisterResponseParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectHostUnregisterResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostUnregisterResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostUnregisterResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                ServiceWorkerErrorType.validate(v1.error);
                v1.errorMsg = arg3.readString(16, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostUnregisterResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostUnregisterResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostUnregisterResponseParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostUnregisterResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostUnregisterResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.errorMsg, 16, true);
        }
    }

    class ServiceWorkerRegistrationObjectHostUnregisterResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final UnregisterResponse mCallback;

        ServiceWorkerRegistrationObjectHostUnregisterResponseParamsForwardToCallback(UnregisterResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                ServiceWorkerRegistrationObjectHostUnregisterResponseParams v5_1 = ServiceWorkerRegistrationObjectHostUnregisterResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.error), v5_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerRegistrationObjectHostUnregisterResponseParamsProxyToResponder implements UnregisterResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerRegistrationObjectHostUnregisterResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, String arg7) {
            ServiceWorkerRegistrationObjectHostUnregisterResponseParams v0 = new ServiceWorkerRegistrationObjectHostUnregisterResponseParams();
            v0.error = arg6.intValue();
            v0.errorMsg = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((String)arg2));
        }
    }

    final class ServiceWorkerRegistrationObjectHostUpdateParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            ServiceWorkerRegistrationObjectHostUpdateParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            ServiceWorkerRegistrationObjectHostUpdateParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostUpdateParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostUpdateParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostUpdateParams(int arg2) {
            super(8, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostUpdateParams decode(Decoder arg2) {
            ServiceWorkerRegistrationObjectHostUpdateParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostUpdateParams(arg2.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostUpdateParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostUpdateParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostUpdateParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostUpdateParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostUpdateParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostUpdateParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class ServiceWorkerRegistrationObjectHostUpdateResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int error;
        public String errorMsg;

        static {
            ServiceWorkerRegistrationObjectHostUpdateResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ServiceWorkerRegistrationObjectHostUpdateResponseParams.DEFAULT_STRUCT_INFO = ServiceWorkerRegistrationObjectHostUpdateResponseParams.VERSION_ARRAY[0];
        }

        public ServiceWorkerRegistrationObjectHostUpdateResponseParams() {
            this(0);
        }

        private ServiceWorkerRegistrationObjectHostUpdateResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ServiceWorkerRegistrationObjectHostUpdateResponseParams decode(Decoder arg3) {
            ServiceWorkerRegistrationObjectHostUpdateResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ServiceWorkerRegistrationObjectHostUpdateResponseParams(arg3.readAndValidateDataHeader(ServiceWorkerRegistrationObjectHostUpdateResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.error = arg3.readInt(8);
                ServiceWorkerErrorType.validate(v1.error);
                v1.errorMsg = arg3.readString(16, true);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ServiceWorkerRegistrationObjectHostUpdateResponseParams deserialize(ByteBuffer arg2) {
            return ServiceWorkerRegistrationObjectHostUpdateResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ServiceWorkerRegistrationObjectHostUpdateResponseParams deserialize(Message arg1) {
            return ServiceWorkerRegistrationObjectHostUpdateResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ServiceWorkerRegistrationObjectHostUpdateResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.error, 8);
            arg4.encode(this.errorMsg, 16, true);
        }
    }

    class ServiceWorkerRegistrationObjectHostUpdateResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final UpdateResponse mCallback;

        ServiceWorkerRegistrationObjectHostUpdateResponseParamsForwardToCallback(UpdateResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                ServiceWorkerRegistrationObjectHostUpdateResponseParams v4_1 = ServiceWorkerRegistrationObjectHostUpdateResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.error), v4_1.errorMsg);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ServiceWorkerRegistrationObjectHostUpdateResponseParamsProxyToResponder implements UpdateResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ServiceWorkerRegistrationObjectHostUpdateResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, String arg7) {
            ServiceWorkerRegistrationObjectHostUpdateResponseParams v0 = new ServiceWorkerRegistrationObjectHostUpdateResponseParams();
            v0.error = arg6.intValue();
            v0.errorMsg = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((String)arg2));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, ServiceWorkerRegistrationObjectHost arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(ServiceWorkerRegistrationObjectHost_Internal.MANAGER, v4_1);
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
                        goto label_57;
                    }
                    case 0: {
                        goto label_48;
                    }
                    case 1: {
                        goto label_39;
                    }
                    case 2: {
                        goto label_29;
                    }
                    case 3: {
                        goto label_20;
                    }
                    case 4: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateParams.deserialize(v9_1.getPayload());
                this.getImpl().getNavigationPreloadState(new ServiceWorkerRegistrationObjectHostGetNavigationPreloadStateResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_39:
                ServiceWorkerRegistrationObjectHostUnregisterParams.deserialize(v9_1.getPayload());
                this.getImpl().unregister(new ServiceWorkerRegistrationObjectHostUnregisterResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_57:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), ServiceWorkerRegistrationObjectHost_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().setNavigationPreloadHeader(ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderParams.deserialize(v9_1.getPayload()).value, new ServiceWorkerRegistrationObjectHostSetNavigationPreloadHeaderResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_29:
                this.getImpl().enableNavigationPreload(ServiceWorkerRegistrationObjectHostEnableNavigationPreloadParams.deserialize(v9_1.getPayload()).enable, new ServiceWorkerRegistrationObjectHostEnableNavigationPreloadResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            label_48:
                ServiceWorkerRegistrationObjectHostUpdateParams.deserialize(v9_1.getPayload());
                this.getImpl().update(new ServiceWorkerRegistrationObjectHostUpdateResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int ENABLE_NAVIGATION_PRELOAD_ORDINAL = 2;
    private static final int GET_NAVIGATION_PRELOAD_STATE_ORDINAL = 3;
    public static final Manager MANAGER = null;
    private static final int SET_NAVIGATION_PRELOAD_HEADER_ORDINAL = 4;
    private static final int UNREGISTER_ORDINAL = 1;
    private static final int UPDATE_ORDINAL;

    static {
        ServiceWorkerRegistrationObjectHost_Internal.MANAGER = new org.chromium.blink.mojom.ServiceWorkerRegistrationObjectHost_Internal$1();
    }

    ServiceWorkerRegistrationObjectHost_Internal() {
        super();
    }
}

