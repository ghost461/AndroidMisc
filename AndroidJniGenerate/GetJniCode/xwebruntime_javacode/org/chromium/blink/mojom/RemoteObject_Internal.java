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

class RemoteObject_Internal {
    final class org.chromium.blink.mojom.RemoteObject_Internal$1 extends Manager {
        org.chromium.blink.mojom.RemoteObject_Internal$1() {
            super();
        }

        public RemoteObject[] buildArray(int arg1) {
            return new RemoteObject[arg1];
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

        public Stub buildStub(Core arg2, RemoteObject arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((RemoteObject)arg2));
        }

        public String getName() {
            return "blink::mojom::RemoteObject";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.blink.mojom.RemoteObject$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void getMethods(GetMethodsResponse arg8) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new RemoteObjectGetMethodsParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new RemoteObjectGetMethodsResponseParamsForwardToCallback(arg8));
        }

        public void hasMethod(String arg8, HasMethodResponse arg9) {
            RemoteObjectHasMethodParams v0 = new RemoteObjectHasMethodParams();
            v0.name = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new RemoteObjectHasMethodResponseParamsForwardToCallback(arg9));
        }

        public void invokeMethod(String arg7, RemoteInvocationArgument[] arg8, InvokeMethodResponse arg9) {
            RemoteObjectInvokeMethodParams v0 = new RemoteObjectInvokeMethodParams();
            v0.name = arg7;
            v0.arguments = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new RemoteObjectInvokeMethodResponseParamsForwardToCallback(arg9));
        }
    }

    final class RemoteObjectGetMethodsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            RemoteObjectGetMethodsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            RemoteObjectGetMethodsParams.DEFAULT_STRUCT_INFO = RemoteObjectGetMethodsParams.VERSION_ARRAY[0];
        }

        public RemoteObjectGetMethodsParams() {
            this(0);
        }

        private RemoteObjectGetMethodsParams(int arg2) {
            super(8, arg2);
        }

        public static RemoteObjectGetMethodsParams decode(Decoder arg2) {
            RemoteObjectGetMethodsParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new RemoteObjectGetMethodsParams(arg2.readAndValidateDataHeader(RemoteObjectGetMethodsParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static RemoteObjectGetMethodsParams deserialize(ByteBuffer arg2) {
            return RemoteObjectGetMethodsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RemoteObjectGetMethodsParams deserialize(Message arg1) {
            return RemoteObjectGetMethodsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(RemoteObjectGetMethodsParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class RemoteObjectGetMethodsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String[] methodNames;

        static {
            RemoteObjectGetMethodsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RemoteObjectGetMethodsResponseParams.DEFAULT_STRUCT_INFO = RemoteObjectGetMethodsResponseParams.VERSION_ARRAY[0];
        }

        public RemoteObjectGetMethodsResponseParams() {
            this(0);
        }

        private RemoteObjectGetMethodsResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RemoteObjectGetMethodsResponseParams decode(Decoder arg8) {
            RemoteObjectGetMethodsResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new RemoteObjectGetMethodsResponseParams(arg8.readAndValidateDataHeader(RemoteObjectGetMethodsResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.methodNames = new String[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.methodNames[v5] = v3.readString(v5 * 8 + v2, false);
                }
            }
            catch(Throwable v0) {
                goto label_30;
            }

            arg8.decreaseStackDepth();
            return v1;
        label_30:
            arg8.decreaseStackDepth();
            throw v0;
        }

        public static RemoteObjectGetMethodsResponseParams deserialize(ByteBuffer arg2) {
            return RemoteObjectGetMethodsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RemoteObjectGetMethodsResponseParams deserialize(Message arg1) {
            return RemoteObjectGetMethodsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(RemoteObjectGetMethodsResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.methodNames == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.methodNames.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.methodNames.length; ++v0) {
                    arg6.encode(this.methodNames[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    class RemoteObjectGetMethodsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetMethodsResponse mCallback;

        RemoteObjectGetMethodsResponseParamsForwardToCallback(GetMethodsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(RemoteObjectGetMethodsResponseParams.deserialize(v5.getPayload()).methodNames);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RemoteObjectGetMethodsResponseParamsProxyToResponder implements GetMethodsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RemoteObjectGetMethodsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((String[])arg1));
        }

        public void call(String[] arg7) {
            RemoteObjectGetMethodsResponseParams v0 = new RemoteObjectGetMethodsResponseParams();
            v0.methodNames = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class RemoteObjectHasMethodParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public String name;

        static {
            RemoteObjectHasMethodParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RemoteObjectHasMethodParams.DEFAULT_STRUCT_INFO = RemoteObjectHasMethodParams.VERSION_ARRAY[0];
        }

        public RemoteObjectHasMethodParams() {
            this(0);
        }

        private RemoteObjectHasMethodParams(int arg2) {
            super(16, arg2);
        }

        public static RemoteObjectHasMethodParams decode(Decoder arg3) {
            RemoteObjectHasMethodParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RemoteObjectHasMethodParams(arg3.readAndValidateDataHeader(RemoteObjectHasMethodParams.VERSION_ARRAY).elementsOrVersion);
                v1.name = arg3.readString(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RemoteObjectHasMethodParams deserialize(ByteBuffer arg2) {
            return RemoteObjectHasMethodParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RemoteObjectHasMethodParams deserialize(Message arg1) {
            return RemoteObjectHasMethodParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RemoteObjectHasMethodParams.DEFAULT_STRUCT_INFO).encode(this.name, 8, false);
        }
    }

    final class RemoteObjectHasMethodResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean methodExists;

        static {
            RemoteObjectHasMethodResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RemoteObjectHasMethodResponseParams.DEFAULT_STRUCT_INFO = RemoteObjectHasMethodResponseParams.VERSION_ARRAY[0];
        }

        public RemoteObjectHasMethodResponseParams() {
            this(0);
        }

        private RemoteObjectHasMethodResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RemoteObjectHasMethodResponseParams decode(Decoder arg3) {
            RemoteObjectHasMethodResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RemoteObjectHasMethodResponseParams(arg3.readAndValidateDataHeader(RemoteObjectHasMethodResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.methodExists = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RemoteObjectHasMethodResponseParams deserialize(ByteBuffer arg2) {
            return RemoteObjectHasMethodResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RemoteObjectHasMethodResponseParams deserialize(Message arg1) {
            return RemoteObjectHasMethodResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RemoteObjectHasMethodResponseParams.DEFAULT_STRUCT_INFO).encode(this.methodExists, 8, 0);
        }
    }

    class RemoteObjectHasMethodResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final HasMethodResponse mCallback;

        RemoteObjectHasMethodResponseParamsForwardToCallback(HasMethodResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(RemoteObjectHasMethodResponseParams.deserialize(v4.getPayload()).methodExists));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RemoteObjectHasMethodResponseParamsProxyToResponder implements HasMethodResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RemoteObjectHasMethodResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            RemoteObjectHasMethodResponseParams v0 = new RemoteObjectHasMethodResponseParams();
            v0.methodExists = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class RemoteObjectInvokeMethodParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public RemoteInvocationArgument[] arguments;
        public String name;

        static {
            RemoteObjectInvokeMethodParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            RemoteObjectInvokeMethodParams.DEFAULT_STRUCT_INFO = RemoteObjectInvokeMethodParams.VERSION_ARRAY[0];
        }

        public RemoteObjectInvokeMethodParams() {
            this(0);
        }

        private RemoteObjectInvokeMethodParams(int arg2) {
            super(24, arg2);
        }

        public static RemoteObjectInvokeMethodParams decode(Decoder arg7) {
            RemoteObjectInvokeMethodParams v1;
            if(arg7 == null) {
                return null;
            }

            arg7.increaseStackDepth();
            try {
                v1 = new RemoteObjectInvokeMethodParams(arg7.readAndValidateDataHeader(RemoteObjectInvokeMethodParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                int v2 = 0;
                v1.name = arg7.readString(v0_1, false);
                Decoder v3 = arg7.readPointer(16, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.arguments = new RemoteInvocationArgument[v4.elementsOrVersion];
                while(v2 < v4.elementsOrVersion) {
                    v1.arguments[v2] = RemoteInvocationArgument.decode(v3, v2 * 16 + v0_1);
                    ++v2;
                }
            }
            catch(Throwable v0) {
                goto label_32;
            }

            arg7.decreaseStackDepth();
            return v1;
        label_32:
            arg7.decreaseStackDepth();
            throw v0;
        }

        public static RemoteObjectInvokeMethodParams deserialize(ByteBuffer arg2) {
            return RemoteObjectInvokeMethodParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RemoteObjectInvokeMethodParams deserialize(Message arg1) {
            return RemoteObjectInvokeMethodParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(RemoteObjectInvokeMethodParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg6.encode(this.name, v1, false);
            int v3 = 16;
            if(this.arguments == null) {
                arg6.encodeNullPointer(v3, false);
            }
            else {
                arg6 = arg6.encodeUnionArray(this.arguments.length, v3, -1);
                int v0;
                for(v0 = 0; v0 < this.arguments.length; ++v0) {
                    arg6.encode(this.arguments[v0], v0 * 16 + v1, false);
                }
            }
        }
    }

    final class RemoteObjectInvokeMethodResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public RemoteInvocationResult result;

        static {
            RemoteObjectInvokeMethodResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RemoteObjectInvokeMethodResponseParams.DEFAULT_STRUCT_INFO = RemoteObjectInvokeMethodResponseParams.VERSION_ARRAY[0];
        }

        public RemoteObjectInvokeMethodResponseParams() {
            this(0);
        }

        private RemoteObjectInvokeMethodResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RemoteObjectInvokeMethodResponseParams decode(Decoder arg3) {
            RemoteObjectInvokeMethodResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RemoteObjectInvokeMethodResponseParams(arg3.readAndValidateDataHeader(RemoteObjectInvokeMethodResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.result = RemoteInvocationResult.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RemoteObjectInvokeMethodResponseParams deserialize(ByteBuffer arg2) {
            return RemoteObjectInvokeMethodResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RemoteObjectInvokeMethodResponseParams deserialize(Message arg1) {
            return RemoteObjectInvokeMethodResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RemoteObjectInvokeMethodResponseParams.DEFAULT_STRUCT_INFO).encode(this.result, 8, false);
        }
    }

    class RemoteObjectInvokeMethodResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final InvokeMethodResponse mCallback;

        RemoteObjectInvokeMethodResponseParamsForwardToCallback(InvokeMethodResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(RemoteObjectInvokeMethodResponseParams.deserialize(v4.getPayload()).result);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RemoteObjectInvokeMethodResponseParamsProxyToResponder implements InvokeMethodResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RemoteObjectInvokeMethodResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((RemoteInvocationResult)arg1));
        }

        public void call(RemoteInvocationResult arg6) {
            RemoteObjectInvokeMethodResponseParams v0 = new RemoteObjectInvokeMethodResponseParams();
            v0.result = arg6;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, RemoteObject arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(RemoteObject_Internal.MANAGER, v4_1);
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
                        goto label_40;
                    }
                    case 0: {
                        goto label_30;
                    }
                    case 1: {
                        goto label_21;
                    }
                    case 2: {
                        goto label_10;
                    }
                }

                return 0;
            label_21:
                RemoteObjectGetMethodsParams.deserialize(v10_1.getPayload());
                this.getImpl().getMethods(new RemoteObjectGetMethodsResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_40:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), RemoteObject_Internal.MANAGER, v10_1, arg11);
            label_10:
                RemoteObjectInvokeMethodParams v10_2 = RemoteObjectInvokeMethodParams.deserialize(v10_1.getPayload());
                this.getImpl().invokeMethod(v10_2.name, v10_2.arguments, new RemoteObjectInvokeMethodResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            label_30:
                this.getImpl().hasMethod(RemoteObjectHasMethodParams.deserialize(v10_1.getPayload()).name, new RemoteObjectHasMethodResponseParamsProxyToResponder(this.getCore(), arg11, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v10) {
                System.err.println(v10.toString());
                return 0;
            }
        }
    }

    private static final int GET_METHODS_ORDINAL = 1;
    private static final int HAS_METHOD_ORDINAL = 0;
    private static final int INVOKE_METHOD_ORDINAL = 2;
    public static final Manager MANAGER;

    static {
        RemoteObject_Internal.MANAGER = new org.chromium.blink.mojom.RemoteObject_Internal$1();
    }

    RemoteObject_Internal() {
        super();
    }
}

