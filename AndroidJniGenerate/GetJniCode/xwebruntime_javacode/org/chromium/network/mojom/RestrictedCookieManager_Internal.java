package org.chromium.network.mojom;

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
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.url.mojom.Url;

class RestrictedCookieManager_Internal {
    final class org.chromium.network.mojom.RestrictedCookieManager_Internal$1 extends Manager {
        org.chromium.network.mojom.RestrictedCookieManager_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public RestrictedCookieManager[] buildArray(int arg1) {
            return new RestrictedCookieManager[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.RestrictedCookieManager_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.RestrictedCookieManager_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((RestrictedCookieManager)arg2));
        }

        public org.chromium.network.mojom.RestrictedCookieManager_Internal$Stub buildStub(Core arg2, RestrictedCookieManager arg3) {
            return new org.chromium.network.mojom.RestrictedCookieManager_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::RestrictedCookieManager";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.RestrictedCookieManager_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.RestrictedCookieManager$Proxy {
        org.chromium.network.mojom.RestrictedCookieManager_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addChangeListener(Url arg3, Url arg4, CookieChangeListener arg5) {
            RestrictedCookieManagerAddChangeListenerParams v0 = new RestrictedCookieManagerAddChangeListenerParams();
            v0.url = arg3;
            v0.siteForCookies = arg4;
            v0.listener = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void getAllForUrl(Url arg6, Url arg7, CookieManagerGetOptions arg8, GetAllForUrlResponse arg9) {
            RestrictedCookieManagerGetAllForUrlParams v0 = new RestrictedCookieManagerGetAllForUrlParams();
            v0.url = arg6;
            v0.siteForCookies = arg7;
            v0.options = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new RestrictedCookieManagerGetAllForUrlResponseParamsForwardToCallback(arg9));
        }

        public void setCanonicalCookie(CanonicalCookie arg5, Url arg6, Url arg7, SetCanonicalCookieResponse arg8) {
            RestrictedCookieManagerSetCanonicalCookieParams v0 = new RestrictedCookieManagerSetCanonicalCookieParams();
            v0.cookie = arg5;
            v0.url = arg6;
            v0.siteForCookies = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new RestrictedCookieManagerSetCanonicalCookieResponseParamsForwardToCallback(arg8));
        }
    }

    final class RestrictedCookieManagerAddChangeListenerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public CookieChangeListener listener;
        public Url siteForCookies;
        public Url url;

        static {
            RestrictedCookieManagerAddChangeListenerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            RestrictedCookieManagerAddChangeListenerParams.DEFAULT_STRUCT_INFO = RestrictedCookieManagerAddChangeListenerParams.VERSION_ARRAY[0];
        }

        public RestrictedCookieManagerAddChangeListenerParams() {
            this(0);
        }

        private RestrictedCookieManagerAddChangeListenerParams(int arg2) {
            super(0x20, arg2);
        }

        public static RestrictedCookieManagerAddChangeListenerParams decode(Decoder arg4) {
            RestrictedCookieManagerAddChangeListenerParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new RestrictedCookieManagerAddChangeListenerParams(arg4.readAndValidateDataHeader(RestrictedCookieManagerAddChangeListenerParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg4.readPointer(8, false));
                v1.siteForCookies = Url.decode(arg4.readPointer(16, false));
                v1.listener = arg4.readServiceInterface(24, false, CookieChangeListener.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static RestrictedCookieManagerAddChangeListenerParams deserialize(ByteBuffer arg2) {
            return RestrictedCookieManagerAddChangeListenerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RestrictedCookieManagerAddChangeListenerParams deserialize(Message arg1) {
            return RestrictedCookieManagerAddChangeListenerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(RestrictedCookieManagerAddChangeListenerParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.url, 8, false);
            arg5.encode(this.siteForCookies, 16, false);
            arg5.encode(this.listener, 24, false, CookieChangeListener.MANAGER);
        }
    }

    final class RestrictedCookieManagerGetAllForUrlParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public CookieManagerGetOptions options;
        public Url siteForCookies;
        public Url url;

        static {
            RestrictedCookieManagerGetAllForUrlParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            RestrictedCookieManagerGetAllForUrlParams.DEFAULT_STRUCT_INFO = RestrictedCookieManagerGetAllForUrlParams.VERSION_ARRAY[0];
        }

        public RestrictedCookieManagerGetAllForUrlParams() {
            this(0);
        }

        private RestrictedCookieManagerGetAllForUrlParams(int arg2) {
            super(0x20, arg2);
        }

        public static RestrictedCookieManagerGetAllForUrlParams decode(Decoder arg3) {
            RestrictedCookieManagerGetAllForUrlParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RestrictedCookieManagerGetAllForUrlParams(arg3.readAndValidateDataHeader(RestrictedCookieManagerGetAllForUrlParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
                v1.siteForCookies = Url.decode(arg3.readPointer(16, false));
                v1.options = CookieManagerGetOptions.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RestrictedCookieManagerGetAllForUrlParams deserialize(ByteBuffer arg2) {
            return RestrictedCookieManagerGetAllForUrlParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RestrictedCookieManagerGetAllForUrlParams deserialize(Message arg1) {
            return RestrictedCookieManagerGetAllForUrlParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(RestrictedCookieManagerGetAllForUrlParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.url, 8, false);
            arg4.encode(this.siteForCookies, 16, false);
            arg4.encode(this.options, 24, false);
        }
    }

    final class RestrictedCookieManagerGetAllForUrlResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CanonicalCookie[] cookies;

        static {
            RestrictedCookieManagerGetAllForUrlResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RestrictedCookieManagerGetAllForUrlResponseParams.DEFAULT_STRUCT_INFO = RestrictedCookieManagerGetAllForUrlResponseParams.VERSION_ARRAY[0];
        }

        public RestrictedCookieManagerGetAllForUrlResponseParams() {
            this(0);
        }

        private RestrictedCookieManagerGetAllForUrlResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RestrictedCookieManagerGetAllForUrlResponseParams decode(Decoder arg8) {
            RestrictedCookieManagerGetAllForUrlResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new RestrictedCookieManagerGetAllForUrlResponseParams(arg8.readAndValidateDataHeader(RestrictedCookieManagerGetAllForUrlResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.cookies = new CanonicalCookie[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.cookies[v5] = CanonicalCookie.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static RestrictedCookieManagerGetAllForUrlResponseParams deserialize(ByteBuffer arg2) {
            return RestrictedCookieManagerGetAllForUrlResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RestrictedCookieManagerGetAllForUrlResponseParams deserialize(Message arg1) {
            return RestrictedCookieManagerGetAllForUrlResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(RestrictedCookieManagerGetAllForUrlResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.cookies == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.cookies.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.cookies.length; ++v0) {
                    arg6.encode(this.cookies[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    class RestrictedCookieManagerGetAllForUrlResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetAllForUrlResponse mCallback;

        RestrictedCookieManagerGetAllForUrlResponseParamsForwardToCallback(GetAllForUrlResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(RestrictedCookieManagerGetAllForUrlResponseParams.deserialize(v4.getPayload()).cookies);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RestrictedCookieManagerGetAllForUrlResponseParamsProxyToResponder implements GetAllForUrlResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RestrictedCookieManagerGetAllForUrlResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((CanonicalCookie[])arg1));
        }

        public void call(CanonicalCookie[] arg7) {
            RestrictedCookieManagerGetAllForUrlResponseParams v0 = new RestrictedCookieManagerGetAllForUrlResponseParams();
            v0.cookies = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class RestrictedCookieManagerSetCanonicalCookieParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public CanonicalCookie cookie;
        public Url siteForCookies;
        public Url url;

        static {
            RestrictedCookieManagerSetCanonicalCookieParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            RestrictedCookieManagerSetCanonicalCookieParams.DEFAULT_STRUCT_INFO = RestrictedCookieManagerSetCanonicalCookieParams.VERSION_ARRAY[0];
        }

        public RestrictedCookieManagerSetCanonicalCookieParams() {
            this(0);
        }

        private RestrictedCookieManagerSetCanonicalCookieParams(int arg2) {
            super(0x20, arg2);
        }

        public static RestrictedCookieManagerSetCanonicalCookieParams decode(Decoder arg3) {
            RestrictedCookieManagerSetCanonicalCookieParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RestrictedCookieManagerSetCanonicalCookieParams(arg3.readAndValidateDataHeader(RestrictedCookieManagerSetCanonicalCookieParams.VERSION_ARRAY).elementsOrVersion);
                v1.cookie = CanonicalCookie.decode(arg3.readPointer(8, false));
                v1.url = Url.decode(arg3.readPointer(16, false));
                v1.siteForCookies = Url.decode(arg3.readPointer(24, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RestrictedCookieManagerSetCanonicalCookieParams deserialize(ByteBuffer arg2) {
            return RestrictedCookieManagerSetCanonicalCookieParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RestrictedCookieManagerSetCanonicalCookieParams deserialize(Message arg1) {
            return RestrictedCookieManagerSetCanonicalCookieParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(RestrictedCookieManagerSetCanonicalCookieParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.cookie, 8, false);
            arg4.encode(this.url, 16, false);
            arg4.encode(this.siteForCookies, 24, false);
        }
    }

    final class RestrictedCookieManagerSetCanonicalCookieResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            RestrictedCookieManagerSetCanonicalCookieResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            RestrictedCookieManagerSetCanonicalCookieResponseParams.DEFAULT_STRUCT_INFO = RestrictedCookieManagerSetCanonicalCookieResponseParams.VERSION_ARRAY[0];
        }

        public RestrictedCookieManagerSetCanonicalCookieResponseParams() {
            this(0);
        }

        private RestrictedCookieManagerSetCanonicalCookieResponseParams(int arg2) {
            super(16, arg2);
        }

        public static RestrictedCookieManagerSetCanonicalCookieResponseParams decode(Decoder arg3) {
            RestrictedCookieManagerSetCanonicalCookieResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new RestrictedCookieManagerSetCanonicalCookieResponseParams(arg3.readAndValidateDataHeader(RestrictedCookieManagerSetCanonicalCookieResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static RestrictedCookieManagerSetCanonicalCookieResponseParams deserialize(ByteBuffer arg2) {
            return RestrictedCookieManagerSetCanonicalCookieResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static RestrictedCookieManagerSetCanonicalCookieResponseParams deserialize(Message arg1) {
            return RestrictedCookieManagerSetCanonicalCookieResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(RestrictedCookieManagerSetCanonicalCookieResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class RestrictedCookieManagerSetCanonicalCookieResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetCanonicalCookieResponse mCallback;

        RestrictedCookieManagerSetCanonicalCookieResponseParamsForwardToCallback(SetCanonicalCookieResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(RestrictedCookieManagerSetCanonicalCookieResponseParams.deserialize(v5.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class RestrictedCookieManagerSetCanonicalCookieResponseParamsProxyToResponder implements SetCanonicalCookieResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        RestrictedCookieManagerSetCanonicalCookieResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg7) {
            RestrictedCookieManagerSetCanonicalCookieResponseParams v0 = new RestrictedCookieManagerSetCanonicalCookieResponseParams();
            v0.success = arg7.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class org.chromium.network.mojom.RestrictedCookieManager_Internal$Stub extends Stub {
        org.chromium.network.mojom.RestrictedCookieManager_Internal$Stub(Core arg1, RestrictedCookieManager arg2) {
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
                if(v1_1 != -2) {
                    if(v1_1 != 2) {
                        return 0;
                    }

                    RestrictedCookieManagerAddChangeListenerParams v5_2 = RestrictedCookieManagerAddChangeListenerParams.deserialize(v5_1.getPayload());
                    this.getImpl().addChangeListener(v5_2.url, v5_2.siteForCookies, v5_2.listener);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(RestrictedCookieManager_Internal.MANAGER, v5_1);
            }
            catch(DeserializationException v5) {
                System.err.println(v5.toString());
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
                        goto label_34;
                    }
                    case 0: {
                        goto label_22;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                return 0;
            label_34:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), RestrictedCookieManager_Internal.MANAGER, v11_1, arg12);
            label_22:
                RestrictedCookieManagerGetAllForUrlParams v11_2 = RestrictedCookieManagerGetAllForUrlParams.deserialize(v11_1.getPayload());
                this.getImpl().getAllForUrl(v11_2.url, v11_2.siteForCookies, v11_2.options, new RestrictedCookieManagerGetAllForUrlResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_10:
                RestrictedCookieManagerSetCanonicalCookieParams v11_3 = RestrictedCookieManagerSetCanonicalCookieParams.deserialize(v11_1.getPayload());
                this.getImpl().setCanonicalCookie(v11_3.cookie, v11_3.url, v11_3.siteForCookies, new RestrictedCookieManagerSetCanonicalCookieResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    private static final int ADD_CHANGE_LISTENER_ORDINAL = 2;
    private static final int GET_ALL_FOR_URL_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int SET_CANONICAL_COOKIE_ORDINAL = 1;

    static {
        RestrictedCookieManager_Internal.MANAGER = new org.chromium.network.mojom.RestrictedCookieManager_Internal$1();
    }

    RestrictedCookieManager_Internal() {
        super();
    }
}

