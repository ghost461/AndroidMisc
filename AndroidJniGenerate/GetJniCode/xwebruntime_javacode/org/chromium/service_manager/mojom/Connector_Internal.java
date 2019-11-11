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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.MessagePipeHandle;

class Connector_Internal {
    final class org.chromium.service_manager.mojom.Connector_Internal$1 extends Manager {
        org.chromium.service_manager.mojom.Connector_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public Connector[] buildArray(int arg1) {
            return new Connector[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.service_manager.mojom.Connector_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.service_manager.mojom.Connector_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((Connector)arg2));
        }

        public org.chromium.service_manager.mojom.Connector_Internal$Stub buildStub(Core arg2, Connector arg3) {
            return new org.chromium.service_manager.mojom.Connector_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "service_manager::mojom::Connector";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class ConnectorBindInterfaceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public String interfaceName;
        public MessagePipeHandle interfacePipe;
        public Identity target;

        static {
            ConnectorBindInterfaceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            ConnectorBindInterfaceParams.DEFAULT_STRUCT_INFO = ConnectorBindInterfaceParams.VERSION_ARRAY[0];
        }

        public ConnectorBindInterfaceParams() {
            this(0);
        }

        private ConnectorBindInterfaceParams(int arg2) {
            super(0x20, arg2);
            this.interfacePipe = InvalidHandle.INSTANCE;
        }

        public static ConnectorBindInterfaceParams decode(Decoder arg3) {
            ConnectorBindInterfaceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorBindInterfaceParams(arg3.readAndValidateDataHeader(ConnectorBindInterfaceParams.VERSION_ARRAY).elementsOrVersion);
                v1.target = Identity.decode(arg3.readPointer(8, false));
                v1.interfaceName = arg3.readString(16, false);
                v1.interfacePipe = arg3.readMessagePipeHandle(24, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorBindInterfaceParams deserialize(ByteBuffer arg2) {
            return ConnectorBindInterfaceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorBindInterfaceParams deserialize(Message arg1) {
            return ConnectorBindInterfaceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ConnectorBindInterfaceParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.target, 8, false);
            arg4.encode(this.interfaceName, 16, false);
            arg4.encode(this.interfacePipe, 24, false);
        }
    }

    final class ConnectorBindInterfaceResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;
        public Identity userId;

        static {
            ConnectorBindInterfaceResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ConnectorBindInterfaceResponseParams.DEFAULT_STRUCT_INFO = ConnectorBindInterfaceResponseParams.VERSION_ARRAY[0];
        }

        public ConnectorBindInterfaceResponseParams() {
            this(0);
        }

        private ConnectorBindInterfaceResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ConnectorBindInterfaceResponseParams decode(Decoder arg3) {
            ConnectorBindInterfaceResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorBindInterfaceResponseParams(arg3.readAndValidateDataHeader(ConnectorBindInterfaceResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                ConnectResult.validate(v1.result);
                v1.userId = Identity.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorBindInterfaceResponseParams deserialize(ByteBuffer arg2) {
            return ConnectorBindInterfaceResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorBindInterfaceResponseParams deserialize(Message arg1) {
            return ConnectorBindInterfaceResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ConnectorBindInterfaceResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.userId, 16, false);
        }
    }

    class ConnectorBindInterfaceResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final BindInterfaceResponse mCallback;

        ConnectorBindInterfaceResponseParamsForwardToCallback(BindInterfaceResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                ConnectorBindInterfaceResponseParams v4_1 = ConnectorBindInterfaceResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.result), v4_1.userId);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ConnectorBindInterfaceResponseParamsProxyToResponder implements BindInterfaceResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ConnectorBindInterfaceResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, Identity arg7) {
            ConnectorBindInterfaceResponseParams v0 = new ConnectorBindInterfaceResponseParams();
            v0.result = arg6.intValue();
            v0.userId = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((Identity)arg2));
        }
    }

    final class ConnectorCloneParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest request;

        static {
            ConnectorCloneParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ConnectorCloneParams.DEFAULT_STRUCT_INFO = ConnectorCloneParams.VERSION_ARRAY[0];
        }

        public ConnectorCloneParams() {
            this(0);
        }

        private ConnectorCloneParams(int arg2) {
            super(16, arg2);
        }

        public static ConnectorCloneParams decode(Decoder arg3) {
            ConnectorCloneParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorCloneParams(arg3.readAndValidateDataHeader(ConnectorCloneParams.VERSION_ARRAY).elementsOrVersion);
                v1.request = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorCloneParams deserialize(ByteBuffer arg2) {
            return ConnectorCloneParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorCloneParams deserialize(Message arg1) {
            return ConnectorCloneParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ConnectorCloneParams.DEFAULT_STRUCT_INFO).encode(this.request, 8, false);
        }
    }

    final class ConnectorFilterInterfacesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity source;
        public InterfaceRequest sourceRequest;
        public String spec;
        public InterfaceProvider target;

        static {
            ConnectorFilterInterfacesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            ConnectorFilterInterfacesParams.DEFAULT_STRUCT_INFO = ConnectorFilterInterfacesParams.VERSION_ARRAY[0];
        }

        public ConnectorFilterInterfacesParams() {
            this(0);
        }

        private ConnectorFilterInterfacesParams(int arg2) {
            super(40, arg2);
        }

        public static ConnectorFilterInterfacesParams decode(Decoder arg4) {
            ConnectorFilterInterfacesParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new ConnectorFilterInterfacesParams(arg4.readAndValidateDataHeader(ConnectorFilterInterfacesParams.VERSION_ARRAY).elementsOrVersion);
                v1.spec = arg4.readString(8, false);
                v1.source = Identity.decode(arg4.readPointer(16, false));
                v1.sourceRequest = arg4.readInterfaceRequest(24, false);
                v1.target = arg4.readServiceInterface(28, false, InterfaceProvider.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static ConnectorFilterInterfacesParams deserialize(ByteBuffer arg2) {
            return ConnectorFilterInterfacesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorFilterInterfacesParams deserialize(Message arg1) {
            return ConnectorFilterInterfacesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(ConnectorFilterInterfacesParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.spec, 8, false);
            arg5.encode(this.source, 16, false);
            arg5.encode(this.sourceRequest, 24, false);
            arg5.encode(this.target, 28, false, InterfaceProvider.MANAGER);
        }
    }

    final class ConnectorQueryServiceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity target;

        static {
            ConnectorQueryServiceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ConnectorQueryServiceParams.DEFAULT_STRUCT_INFO = ConnectorQueryServiceParams.VERSION_ARRAY[0];
        }

        public ConnectorQueryServiceParams() {
            this(0);
        }

        private ConnectorQueryServiceParams(int arg2) {
            super(16, arg2);
        }

        public static ConnectorQueryServiceParams decode(Decoder arg3) {
            ConnectorQueryServiceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorQueryServiceParams(arg3.readAndValidateDataHeader(ConnectorQueryServiceParams.VERSION_ARRAY).elementsOrVersion);
                v1.target = Identity.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorQueryServiceParams deserialize(ByteBuffer arg2) {
            return ConnectorQueryServiceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorQueryServiceParams deserialize(Message arg1) {
            return ConnectorQueryServiceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ConnectorQueryServiceParams.DEFAULT_STRUCT_INFO).encode(this.target, 8, false);
        }
    }

    final class ConnectorQueryServiceResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int result;
        public String sandboxType;

        static {
            ConnectorQueryServiceResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ConnectorQueryServiceResponseParams.DEFAULT_STRUCT_INFO = ConnectorQueryServiceResponseParams.VERSION_ARRAY[0];
        }

        public ConnectorQueryServiceResponseParams() {
            this(0);
        }

        private ConnectorQueryServiceResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ConnectorQueryServiceResponseParams decode(Decoder arg3) {
            ConnectorQueryServiceResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorQueryServiceResponseParams(arg3.readAndValidateDataHeader(ConnectorQueryServiceResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                ConnectResult.validate(v1.result);
                v1.sandboxType = arg3.readString(16, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorQueryServiceResponseParams deserialize(ByteBuffer arg2) {
            return ConnectorQueryServiceResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorQueryServiceResponseParams deserialize(Message arg1) {
            return ConnectorQueryServiceResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ConnectorQueryServiceResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.sandboxType, 16, false);
        }
    }

    class ConnectorQueryServiceResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final QueryServiceResponse mCallback;

        ConnectorQueryServiceResponseParamsForwardToCallback(QueryServiceResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                ConnectorQueryServiceResponseParams v5_1 = ConnectorQueryServiceResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.result), v5_1.sandboxType);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ConnectorQueryServiceResponseParamsProxyToResponder implements QueryServiceResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ConnectorQueryServiceResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, String arg7) {
            ConnectorQueryServiceResponseParams v0 = new ConnectorQueryServiceResponseParams();
            v0.result = arg6.intValue();
            v0.sandboxType = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((String)arg2));
        }
    }

    final class ConnectorStartServiceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity target;

        static {
            ConnectorStartServiceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            ConnectorStartServiceParams.DEFAULT_STRUCT_INFO = ConnectorStartServiceParams.VERSION_ARRAY[0];
        }

        public ConnectorStartServiceParams() {
            this(0);
        }

        private ConnectorStartServiceParams(int arg2) {
            super(16, arg2);
        }

        public static ConnectorStartServiceParams decode(Decoder arg3) {
            ConnectorStartServiceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorStartServiceParams(arg3.readAndValidateDataHeader(ConnectorStartServiceParams.VERSION_ARRAY).elementsOrVersion);
                v1.target = Identity.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorStartServiceParams deserialize(ByteBuffer arg2) {
            return ConnectorStartServiceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorStartServiceParams deserialize(Message arg1) {
            return ConnectorStartServiceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(ConnectorStartServiceParams.DEFAULT_STRUCT_INFO).encode(this.target, 8, false);
        }
    }

    final class ConnectorStartServiceResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity identity;
        public int result;

        static {
            ConnectorStartServiceResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ConnectorStartServiceResponseParams.DEFAULT_STRUCT_INFO = ConnectorStartServiceResponseParams.VERSION_ARRAY[0];
        }

        public ConnectorStartServiceResponseParams() {
            this(0);
        }

        private ConnectorStartServiceResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ConnectorStartServiceResponseParams decode(Decoder arg3) {
            ConnectorStartServiceResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorStartServiceResponseParams(arg3.readAndValidateDataHeader(ConnectorStartServiceResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                ConnectResult.validate(v1.result);
                v1.identity = Identity.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorStartServiceResponseParams deserialize(ByteBuffer arg2) {
            return ConnectorStartServiceResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorStartServiceResponseParams deserialize(Message arg1) {
            return ConnectorStartServiceResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ConnectorStartServiceResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.identity, 16, false);
        }
    }

    class ConnectorStartServiceResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final StartServiceResponse mCallback;

        ConnectorStartServiceResponseParamsForwardToCallback(StartServiceResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                ConnectorStartServiceResponseParams v4_1 = ConnectorStartServiceResponseParams.deserialize(v4.getPayload());
                this.mCallback.call(Integer.valueOf(v4_1.result), v4_1.identity);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ConnectorStartServiceResponseParamsProxyToResponder implements StartServiceResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ConnectorStartServiceResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg5, Identity arg6) {
            ConnectorStartServiceResponseParams v0 = new ConnectorStartServiceResponseParams();
            v0.result = arg5.intValue();
            v0.identity = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((Identity)arg2));
        }
    }

    final class ConnectorStartServiceWithProcessParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest pidReceiverRequest;
        public MessagePipeHandle service;
        public Identity target;

        static {
            ConnectorStartServiceWithProcessParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ConnectorStartServiceWithProcessParams.DEFAULT_STRUCT_INFO = ConnectorStartServiceWithProcessParams.VERSION_ARRAY[0];
        }

        public ConnectorStartServiceWithProcessParams() {
            this(0);
        }

        private ConnectorStartServiceWithProcessParams(int arg2) {
            super(24, arg2);
            this.service = InvalidHandle.INSTANCE;
        }

        public static ConnectorStartServiceWithProcessParams decode(Decoder arg3) {
            ConnectorStartServiceWithProcessParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorStartServiceWithProcessParams(arg3.readAndValidateDataHeader(ConnectorStartServiceWithProcessParams.VERSION_ARRAY).elementsOrVersion);
                v1.target = Identity.decode(arg3.readPointer(8, false));
                v1.service = arg3.readMessagePipeHandle(16, false);
                v1.pidReceiverRequest = arg3.readInterfaceRequest(20, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorStartServiceWithProcessParams deserialize(ByteBuffer arg2) {
            return ConnectorStartServiceWithProcessParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorStartServiceWithProcessParams deserialize(Message arg1) {
            return ConnectorStartServiceWithProcessParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ConnectorStartServiceWithProcessParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.target, 8, false);
            arg4.encode(this.service, 16, false);
            arg4.encode(this.pidReceiverRequest, 20, false);
        }
    }

    final class ConnectorStartServiceWithProcessResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Identity identity;
        public int result;

        static {
            ConnectorStartServiceWithProcessResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            ConnectorStartServiceWithProcessResponseParams.DEFAULT_STRUCT_INFO = ConnectorStartServiceWithProcessResponseParams.VERSION_ARRAY[0];
        }

        public ConnectorStartServiceWithProcessResponseParams() {
            this(0);
        }

        private ConnectorStartServiceWithProcessResponseParams(int arg2) {
            super(24, arg2);
        }

        public static ConnectorStartServiceWithProcessResponseParams decode(Decoder arg3) {
            ConnectorStartServiceWithProcessResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new ConnectorStartServiceWithProcessResponseParams(arg3.readAndValidateDataHeader(ConnectorStartServiceWithProcessResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = arg3.readInt(8);
                ConnectResult.validate(v1.result);
                v1.identity = Identity.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static ConnectorStartServiceWithProcessResponseParams deserialize(ByteBuffer arg2) {
            return ConnectorStartServiceWithProcessResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static ConnectorStartServiceWithProcessResponseParams deserialize(Message arg1) {
            return ConnectorStartServiceWithProcessResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(ConnectorStartServiceWithProcessResponseParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.result, 8);
            arg4.encode(this.identity, 16, false);
        }
    }

    class ConnectorStartServiceWithProcessResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final StartServiceWithProcessResponse mCallback;

        ConnectorStartServiceWithProcessResponseParamsForwardToCallback(StartServiceWithProcessResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                ConnectorStartServiceWithProcessResponseParams v5_1 = ConnectorStartServiceWithProcessResponseParams.deserialize(v5.getPayload());
                this.mCallback.call(Integer.valueOf(v5_1.result), v5_1.identity);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class ConnectorStartServiceWithProcessResponseParamsProxyToResponder implements StartServiceWithProcessResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        ConnectorStartServiceWithProcessResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg6, Identity arg7) {
            ConnectorStartServiceWithProcessResponseParams v0 = new ConnectorStartServiceWithProcessResponseParams();
            v0.result = arg6.intValue();
            v0.identity = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1, Object arg2) {
            this.call(((Integer)arg1), ((Identity)arg2));
        }
    }

    final class org.chromium.service_manager.mojom.Connector_Internal$Proxy extends AbstractProxy implements org.chromium.service_manager.mojom.Connector$Proxy {
        org.chromium.service_manager.mojom.Connector_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void bindInterface(Identity arg6, String arg7, MessagePipeHandle arg8, BindInterfaceResponse arg9) {
            ConnectorBindInterfaceParams v0 = new ConnectorBindInterfaceParams();
            v0.target = arg6;
            v0.interfaceName = arg7;
            v0.interfacePipe = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new ConnectorBindInterfaceResponseParamsForwardToCallback(arg9));
        }

        public void clone(InterfaceRequest arg5) {
            ConnectorCloneParams v0 = new ConnectorCloneParams();
            v0.request = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void filterInterfaces(String arg2, Identity arg3, InterfaceRequest arg4, InterfaceProvider arg5) {
            ConnectorFilterInterfacesParams v0 = new ConnectorFilterInterfacesParams();
            v0.spec = arg2;
            v0.source = arg3;
            v0.sourceRequest = arg4;
            v0.target = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void queryService(Identity arg7, QueryServiceResponse arg8) {
            ConnectorQueryServiceParams v0 = new ConnectorQueryServiceParams();
            v0.target = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new ConnectorQueryServiceResponseParamsForwardToCallback(arg8));
        }

        public void startService(Identity arg8, StartServiceResponse arg9) {
            ConnectorStartServiceParams v0 = new ConnectorStartServiceParams();
            v0.target = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new ConnectorStartServiceResponseParamsForwardToCallback(arg9));
        }

        public void startServiceWithProcess(Identity arg6, MessagePipeHandle arg7, InterfaceRequest arg8, StartServiceWithProcessResponse arg9) {
            ConnectorStartServiceWithProcessParams v0 = new ConnectorStartServiceWithProcessParams();
            v0.target = arg6;
            v0.service = arg7;
            v0.pidReceiverRequest = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new ConnectorStartServiceWithProcessResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.service_manager.mojom.Connector_Internal$Stub extends Stub {
        org.chromium.service_manager.mojom.Connector_Internal$Stub(Core arg1, Connector arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg7) {
            try {
                ServiceMessage v7_1 = arg7.asServiceMessage();
                MessageHeader v1 = v7_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_27;
                }

                switch(v1_1) {
                    case 4: {
                        goto label_21;
                    }
                    case 5: {
                        goto label_12;
                    }
                }

                return 0;
            label_21:
                this.getImpl().clone(ConnectorCloneParams.deserialize(v7_1.getPayload()).request);
                return 1;
            label_12:
                ConnectorFilterInterfacesParams v7_2 = ConnectorFilterInterfacesParams.deserialize(v7_1.getPayload());
                this.getImpl().filterInterfaces(v7_2.spec, v7_2.source, v7_2.sourceRequest, v7_2.target);
                return 1;
            label_27:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(Connector_Internal.MANAGER, v7_1);
            }
            catch(DeserializationException v7) {
                System.err.println(v7.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg11, MessageReceiver arg12) {
            try {
                ServiceMessage v11_1 = arg11.asServiceMessage();
                MessageHeader v1 = v11_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_54;
                    }
                    case 0: {
                        goto label_42;
                    }
                    case 1: {
                        goto label_32;
                    }
                    case 2: {
                        goto label_22;
                    }
                    case 3: {
                        goto label_10;
                    }
                }

                return 0;
            label_54:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), Connector_Internal.MANAGER, v11_1, arg12);
            label_22:
                this.getImpl().startService(ConnectorStartServiceParams.deserialize(v11_1.getPayload()).target, new ConnectorStartServiceResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_42:
                ConnectorBindInterfaceParams v11_2 = ConnectorBindInterfaceParams.deserialize(v11_1.getPayload());
                this.getImpl().bindInterface(v11_2.target, v11_2.interfaceName, v11_2.interfacePipe, new ConnectorBindInterfaceResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_10:
                ConnectorStartServiceWithProcessParams v11_3 = ConnectorStartServiceWithProcessParams.deserialize(v11_1.getPayload());
                this.getImpl().startServiceWithProcess(v11_3.target, v11_3.service, v11_3.pidReceiverRequest, new ConnectorStartServiceWithProcessResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_32:
                this.getImpl().queryService(ConnectorQueryServiceParams.deserialize(v11_1.getPayload()).target, new ConnectorQueryServiceResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    private static final int BIND_INTERFACE_ORDINAL = 0;
    private static final int CLONE_ORDINAL = 4;
    private static final int FILTER_INTERFACES_ORDINAL = 5;
    public static final Manager MANAGER = null;
    private static final int QUERY_SERVICE_ORDINAL = 1;
    private static final int START_SERVICE_ORDINAL = 2;
    private static final int START_SERVICE_WITH_PROCESS_ORDINAL = 3;

    static {
        Connector_Internal.MANAGER = new org.chromium.service_manager.mojom.Connector_Internal$1();
    }

    Connector_Internal() {
        super();
    }
}

