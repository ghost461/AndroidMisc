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
import org.chromium.mojo.bindings.InterfaceRequest;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.bindings.MessageReceiverWithResponder;
import org.chromium.mojo.bindings.ServiceMessage;
import org.chromium.mojo.bindings.SideEffectFreeCloseable;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;
import org.chromium.url.mojom.Url;

class CookieManager_Internal {
    final class org.chromium.network.mojom.CookieManager_Internal$1 extends Manager {
        org.chromium.network.mojom.CookieManager_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public CookieManager[] buildArray(int arg1) {
            return new CookieManager[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.CookieManager_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.CookieManager_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CookieManager)arg2));
        }

        public org.chromium.network.mojom.CookieManager_Internal$Stub buildStub(Core arg2, CookieManager arg3) {
            return new org.chromium.network.mojom.CookieManager_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::CookieManager";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CookieManagerAddCookieChangeListenerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 0x20;
        private static final DataHeader[] VERSION_ARRAY;
        public CookieChangeListener listener;
        public String name;
        public Url url;

        static {
            CookieManagerAddCookieChangeListenerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(0x20, 0)};
            CookieManagerAddCookieChangeListenerParams.DEFAULT_STRUCT_INFO = CookieManagerAddCookieChangeListenerParams.VERSION_ARRAY[0];
        }

        public CookieManagerAddCookieChangeListenerParams() {
            this(0);
        }

        private CookieManagerAddCookieChangeListenerParams(int arg2) {
            super(0x20, arg2);
        }

        public static CookieManagerAddCookieChangeListenerParams decode(Decoder arg4) {
            CookieManagerAddCookieChangeListenerParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CookieManagerAddCookieChangeListenerParams(arg4.readAndValidateDataHeader(CookieManagerAddCookieChangeListenerParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg4.readPointer(8, false));
                v1.name = arg4.readString(16, false);
                v1.listener = arg4.readServiceInterface(24, false, CookieChangeListener.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerAddCookieChangeListenerParams deserialize(ByteBuffer arg2) {
            return CookieManagerAddCookieChangeListenerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerAddCookieChangeListenerParams deserialize(Message arg1) {
            return CookieManagerAddCookieChangeListenerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5 = arg5.getEncoderAtDataOffset(CookieManagerAddCookieChangeListenerParams.DEFAULT_STRUCT_INFO);
            arg5.encode(this.url, 8, false);
            arg5.encode(this.name, 16, false);
            arg5.encode(this.listener, 24, false, CookieChangeListener.MANAGER);
        }
    }

    final class CookieManagerAddGlobalChangeListenerParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CookieChangeListener notificationPointer;

        static {
            CookieManagerAddGlobalChangeListenerParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CookieManagerAddGlobalChangeListenerParams.DEFAULT_STRUCT_INFO = CookieManagerAddGlobalChangeListenerParams.VERSION_ARRAY[0];
        }

        public CookieManagerAddGlobalChangeListenerParams() {
            this(0);
        }

        private CookieManagerAddGlobalChangeListenerParams(int arg2) {
            super(16, arg2);
        }

        public static CookieManagerAddGlobalChangeListenerParams decode(Decoder arg4) {
            CookieManagerAddGlobalChangeListenerParams v1;
            if(arg4 == null) {
                return null;
            }

            arg4.increaseStackDepth();
            try {
                v1 = new CookieManagerAddGlobalChangeListenerParams(arg4.readAndValidateDataHeader(CookieManagerAddGlobalChangeListenerParams.VERSION_ARRAY).elementsOrVersion);
                v1.notificationPointer = arg4.readServiceInterface(8, false, CookieChangeListener.MANAGER);
            }
            catch(Throwable v0) {
                arg4.decreaseStackDepth();
                throw v0;
            }

            arg4.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerAddGlobalChangeListenerParams deserialize(ByteBuffer arg2) {
            return CookieManagerAddGlobalChangeListenerParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerAddGlobalChangeListenerParams deserialize(Message arg1) {
            return CookieManagerAddGlobalChangeListenerParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg5) {
            arg5.getEncoderAtDataOffset(CookieManagerAddGlobalChangeListenerParams.DEFAULT_STRUCT_INFO).encode(this.notificationPointer, 8, false, CookieChangeListener.MANAGER);
        }
    }

    final class CookieManagerCloneInterfaceParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public InterfaceRequest newInterface;

        static {
            CookieManagerCloneInterfaceParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CookieManagerCloneInterfaceParams.DEFAULT_STRUCT_INFO = CookieManagerCloneInterfaceParams.VERSION_ARRAY[0];
        }

        public CookieManagerCloneInterfaceParams() {
            this(0);
        }

        private CookieManagerCloneInterfaceParams(int arg2) {
            super(16, arg2);
        }

        public static CookieManagerCloneInterfaceParams decode(Decoder arg3) {
            CookieManagerCloneInterfaceParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CookieManagerCloneInterfaceParams(arg3.readAndValidateDataHeader(CookieManagerCloneInterfaceParams.VERSION_ARRAY).elementsOrVersion);
                v1.newInterface = arg3.readInterfaceRequest(8, false);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerCloneInterfaceParams deserialize(ByteBuffer arg2) {
            return CookieManagerCloneInterfaceParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerCloneInterfaceParams deserialize(Message arg1) {
            return CookieManagerCloneInterfaceParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CookieManagerCloneInterfaceParams.DEFAULT_STRUCT_INFO).encode(this.newInterface, 8, false);
        }
    }

    final class CookieManagerDeleteCookiesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CookieDeletionFilter filter;

        static {
            CookieManagerDeleteCookiesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CookieManagerDeleteCookiesParams.DEFAULT_STRUCT_INFO = CookieManagerDeleteCookiesParams.VERSION_ARRAY[0];
        }

        public CookieManagerDeleteCookiesParams() {
            this(0);
        }

        private CookieManagerDeleteCookiesParams(int arg2) {
            super(16, arg2);
        }

        public static CookieManagerDeleteCookiesParams decode(Decoder arg3) {
            CookieManagerDeleteCookiesParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CookieManagerDeleteCookiesParams(arg3.readAndValidateDataHeader(CookieManagerDeleteCookiesParams.VERSION_ARRAY).elementsOrVersion);
                v1.filter = CookieDeletionFilter.decode(arg3.readPointer(8, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerDeleteCookiesParams deserialize(ByteBuffer arg2) {
            return CookieManagerDeleteCookiesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerDeleteCookiesParams deserialize(Message arg1) {
            return CookieManagerDeleteCookiesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CookieManagerDeleteCookiesParams.DEFAULT_STRUCT_INFO).encode(this.filter, 8, false);
        }
    }

    final class CookieManagerDeleteCookiesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int numDeleted;

        static {
            CookieManagerDeleteCookiesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CookieManagerDeleteCookiesResponseParams.DEFAULT_STRUCT_INFO = CookieManagerDeleteCookiesResponseParams.VERSION_ARRAY[0];
        }

        public CookieManagerDeleteCookiesResponseParams() {
            this(0);
        }

        private CookieManagerDeleteCookiesResponseParams(int arg2) {
            super(16, arg2);
        }

        public static CookieManagerDeleteCookiesResponseParams decode(Decoder arg2) {
            CookieManagerDeleteCookiesResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CookieManagerDeleteCookiesResponseParams(arg2.readAndValidateDataHeader(CookieManagerDeleteCookiesResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.numDeleted = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerDeleteCookiesResponseParams deserialize(ByteBuffer arg2) {
            return CookieManagerDeleteCookiesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerDeleteCookiesResponseParams deserialize(Message arg1) {
            return CookieManagerDeleteCookiesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(CookieManagerDeleteCookiesResponseParams.DEFAULT_STRUCT_INFO).encode(this.numDeleted, 8);
        }
    }

    class CookieManagerDeleteCookiesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final DeleteCookiesResponse mCallback;

        CookieManagerDeleteCookiesResponseParamsForwardToCallback(DeleteCookiesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(3, 2)) {
                    return 0;
                }

                this.mCallback.call(Integer.valueOf(CookieManagerDeleteCookiesResponseParams.deserialize(v5.getPayload()).numDeleted));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CookieManagerDeleteCookiesResponseParamsProxyToResponder implements DeleteCookiesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CookieManagerDeleteCookiesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Integer arg7) {
            CookieManagerDeleteCookiesResponseParams v0 = new CookieManagerDeleteCookiesResponseParams();
            v0.numDeleted = arg7.intValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Integer)arg1));
        }
    }

    final class CookieManagerFlushCookieStoreParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            CookieManagerFlushCookieStoreParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            CookieManagerFlushCookieStoreParams.DEFAULT_STRUCT_INFO = CookieManagerFlushCookieStoreParams.VERSION_ARRAY[0];
        }

        public CookieManagerFlushCookieStoreParams() {
            this(0);
        }

        private CookieManagerFlushCookieStoreParams(int arg2) {
            super(8, arg2);
        }

        public static CookieManagerFlushCookieStoreParams decode(Decoder arg2) {
            CookieManagerFlushCookieStoreParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CookieManagerFlushCookieStoreParams(arg2.readAndValidateDataHeader(CookieManagerFlushCookieStoreParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerFlushCookieStoreParams deserialize(ByteBuffer arg2) {
            return CookieManagerFlushCookieStoreParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerFlushCookieStoreParams deserialize(Message arg1) {
            return CookieManagerFlushCookieStoreParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(CookieManagerFlushCookieStoreParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class CookieManagerFlushCookieStoreResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            CookieManagerFlushCookieStoreResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            CookieManagerFlushCookieStoreResponseParams.DEFAULT_STRUCT_INFO = CookieManagerFlushCookieStoreResponseParams.VERSION_ARRAY[0];
        }

        public CookieManagerFlushCookieStoreResponseParams() {
            this(0);
        }

        private CookieManagerFlushCookieStoreResponseParams(int arg2) {
            super(8, arg2);
        }

        public static CookieManagerFlushCookieStoreResponseParams decode(Decoder arg2) {
            CookieManagerFlushCookieStoreResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CookieManagerFlushCookieStoreResponseParams(arg2.readAndValidateDataHeader(CookieManagerFlushCookieStoreResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerFlushCookieStoreResponseParams deserialize(ByteBuffer arg2) {
            return CookieManagerFlushCookieStoreResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerFlushCookieStoreResponseParams deserialize(Message arg1) {
            return CookieManagerFlushCookieStoreResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(CookieManagerFlushCookieStoreResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class CookieManagerFlushCookieStoreResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final FlushCookieStoreResponse mCallback;

        CookieManagerFlushCookieStoreResponseParamsForwardToCallback(FlushCookieStoreResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(7, 2)) {
                    return 0;
                }

                this.mCallback.call();
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CookieManagerFlushCookieStoreResponseParamsProxyToResponder implements FlushCookieStoreResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CookieManagerFlushCookieStoreResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new CookieManagerFlushCookieStoreResponseParams().serializeWithHeader(this.mCore, new MessageHeader(7, 2, this.mRequestId)));
        }
    }

    final class CookieManagerGetAllCookiesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            CookieManagerGetAllCookiesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            CookieManagerGetAllCookiesParams.DEFAULT_STRUCT_INFO = CookieManagerGetAllCookiesParams.VERSION_ARRAY[0];
        }

        public CookieManagerGetAllCookiesParams() {
            this(0);
        }

        private CookieManagerGetAllCookiesParams(int arg2) {
            super(8, arg2);
        }

        public static CookieManagerGetAllCookiesParams decode(Decoder arg2) {
            CookieManagerGetAllCookiesParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new CookieManagerGetAllCookiesParams(arg2.readAndValidateDataHeader(CookieManagerGetAllCookiesParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerGetAllCookiesParams deserialize(ByteBuffer arg2) {
            return CookieManagerGetAllCookiesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerGetAllCookiesParams deserialize(Message arg1) {
            return CookieManagerGetAllCookiesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(CookieManagerGetAllCookiesParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class CookieManagerGetAllCookiesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CanonicalCookie[] cookies;

        static {
            CookieManagerGetAllCookiesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CookieManagerGetAllCookiesResponseParams.DEFAULT_STRUCT_INFO = CookieManagerGetAllCookiesResponseParams.VERSION_ARRAY[0];
        }

        public CookieManagerGetAllCookiesResponseParams() {
            this(0);
        }

        private CookieManagerGetAllCookiesResponseParams(int arg2) {
            super(16, arg2);
        }

        public static CookieManagerGetAllCookiesResponseParams decode(Decoder arg8) {
            CookieManagerGetAllCookiesResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new CookieManagerGetAllCookiesResponseParams(arg8.readAndValidateDataHeader(CookieManagerGetAllCookiesResponseParams.VERSION_ARRAY).elementsOrVersion);
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

        public static CookieManagerGetAllCookiesResponseParams deserialize(ByteBuffer arg2) {
            return CookieManagerGetAllCookiesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerGetAllCookiesResponseParams deserialize(Message arg1) {
            return CookieManagerGetAllCookiesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(CookieManagerGetAllCookiesResponseParams.DEFAULT_STRUCT_INFO);
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

    class CookieManagerGetAllCookiesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetAllCookiesResponse mCallback;

        CookieManagerGetAllCookiesResponseParamsForwardToCallback(GetAllCookiesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(CookieManagerGetAllCookiesResponseParams.deserialize(v4.getPayload()).cookies);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CookieManagerGetAllCookiesResponseParamsProxyToResponder implements GetAllCookiesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CookieManagerGetAllCookiesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((CanonicalCookie[])arg1));
        }

        public void call(CanonicalCookie[] arg7) {
            CookieManagerGetAllCookiesResponseParams v0 = new CookieManagerGetAllCookiesResponseParams();
            v0.cookies = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class CookieManagerGetCookieListParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public CookieOptions cookieOptions;
        public Url url;

        static {
            CookieManagerGetCookieListParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CookieManagerGetCookieListParams.DEFAULT_STRUCT_INFO = CookieManagerGetCookieListParams.VERSION_ARRAY[0];
        }

        public CookieManagerGetCookieListParams() {
            this(0);
        }

        private CookieManagerGetCookieListParams(int arg2) {
            super(24, arg2);
        }

        public static CookieManagerGetCookieListParams decode(Decoder arg3) {
            CookieManagerGetCookieListParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CookieManagerGetCookieListParams(arg3.readAndValidateDataHeader(CookieManagerGetCookieListParams.VERSION_ARRAY).elementsOrVersion);
                v1.url = Url.decode(arg3.readPointer(8, false));
                v1.cookieOptions = CookieOptions.decode(arg3.readPointer(16, false));
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerGetCookieListParams deserialize(ByteBuffer arg2) {
            return CookieManagerGetCookieListParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerGetCookieListParams deserialize(Message arg1) {
            return CookieManagerGetCookieListParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(CookieManagerGetCookieListParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.url, 8, false);
            arg4.encode(this.cookieOptions, 16, false);
        }
    }

    final class CookieManagerGetCookieListResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public CanonicalCookie[] cookies;

        static {
            CookieManagerGetCookieListResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CookieManagerGetCookieListResponseParams.DEFAULT_STRUCT_INFO = CookieManagerGetCookieListResponseParams.VERSION_ARRAY[0];
        }

        public CookieManagerGetCookieListResponseParams() {
            this(0);
        }

        private CookieManagerGetCookieListResponseParams(int arg2) {
            super(16, arg2);
        }

        public static CookieManagerGetCookieListResponseParams decode(Decoder arg8) {
            CookieManagerGetCookieListResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new CookieManagerGetCookieListResponseParams(arg8.readAndValidateDataHeader(CookieManagerGetCookieListResponseParams.VERSION_ARRAY).elementsOrVersion);
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

        public static CookieManagerGetCookieListResponseParams deserialize(ByteBuffer arg2) {
            return CookieManagerGetCookieListResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerGetCookieListResponseParams deserialize(Message arg1) {
            return CookieManagerGetCookieListResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(CookieManagerGetCookieListResponseParams.DEFAULT_STRUCT_INFO);
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

    class CookieManagerGetCookieListResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final GetCookieListResponse mCallback;

        CookieManagerGetCookieListResponseParamsForwardToCallback(GetCookieListResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg5) {
            try {
                ServiceMessage v5 = arg5.asServiceMessage();
                if(!v5.getHeader().validateHeader(1, 2)) {
                    return 0;
                }

                this.mCallback.call(CookieManagerGetCookieListResponseParams.deserialize(v5.getPayload()).cookies);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CookieManagerGetCookieListResponseParamsProxyToResponder implements GetCookieListResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CookieManagerGetCookieListResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((CanonicalCookie[])arg1));
        }

        public void call(CanonicalCookie[] arg7) {
            CookieManagerGetCookieListResponseParams v0 = new CookieManagerGetCookieListResponseParams();
            v0.cookies = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class CookieManagerSetCanonicalCookieParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public CanonicalCookie cookie;
        public boolean modifyHttpOnly;
        public boolean secureSource;

        static {
            CookieManagerSetCanonicalCookieParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CookieManagerSetCanonicalCookieParams.DEFAULT_STRUCT_INFO = CookieManagerSetCanonicalCookieParams.VERSION_ARRAY[0];
        }

        public CookieManagerSetCanonicalCookieParams() {
            this(0);
        }

        private CookieManagerSetCanonicalCookieParams(int arg2) {
            super(24, arg2);
        }

        public static CookieManagerSetCanonicalCookieParams decode(Decoder arg3) {
            CookieManagerSetCanonicalCookieParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CookieManagerSetCanonicalCookieParams(arg3.readAndValidateDataHeader(CookieManagerSetCanonicalCookieParams.VERSION_ARRAY).elementsOrVersion);
                v1.cookie = CanonicalCookie.decode(arg3.readPointer(8, false));
                v1.secureSource = arg3.readBoolean(16, 0);
                v1.modifyHttpOnly = arg3.readBoolean(16, 1);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerSetCanonicalCookieParams deserialize(ByteBuffer arg2) {
            return CookieManagerSetCanonicalCookieParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerSetCanonicalCookieParams deserialize(Message arg1) {
            return CookieManagerSetCanonicalCookieParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(CookieManagerSetCanonicalCookieParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.cookie, 8, false);
            arg4.encode(this.secureSource, 16, 0);
            arg4.encode(this.modifyHttpOnly, 16, 1);
        }
    }

    final class CookieManagerSetCanonicalCookieResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean success;

        static {
            CookieManagerSetCanonicalCookieResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            CookieManagerSetCanonicalCookieResponseParams.DEFAULT_STRUCT_INFO = CookieManagerSetCanonicalCookieResponseParams.VERSION_ARRAY[0];
        }

        public CookieManagerSetCanonicalCookieResponseParams() {
            this(0);
        }

        private CookieManagerSetCanonicalCookieResponseParams(int arg2) {
            super(16, arg2);
        }

        public static CookieManagerSetCanonicalCookieResponseParams decode(Decoder arg3) {
            CookieManagerSetCanonicalCookieResponseParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CookieManagerSetCanonicalCookieResponseParams(arg3.readAndValidateDataHeader(CookieManagerSetCanonicalCookieResponseParams.VERSION_ARRAY).elementsOrVersion);
                v1.success = arg3.readBoolean(8, 0);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CookieManagerSetCanonicalCookieResponseParams deserialize(ByteBuffer arg2) {
            return CookieManagerSetCanonicalCookieResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieManagerSetCanonicalCookieResponseParams deserialize(Message arg1) {
            return CookieManagerSetCanonicalCookieResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4.getEncoderAtDataOffset(CookieManagerSetCanonicalCookieResponseParams.DEFAULT_STRUCT_INFO).encode(this.success, 8, 0);
        }
    }

    class CookieManagerSetCanonicalCookieResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetCanonicalCookieResponse mCallback;

        CookieManagerSetCanonicalCookieResponseParamsForwardToCallback(SetCanonicalCookieResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(2, 2)) {
                    return 0;
                }

                this.mCallback.call(Boolean.valueOf(CookieManagerSetCanonicalCookieResponseParams.deserialize(v4.getPayload()).success));
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class CookieManagerSetCanonicalCookieResponseParamsProxyToResponder implements SetCanonicalCookieResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        CookieManagerSetCanonicalCookieResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Boolean arg6) {
            CookieManagerSetCanonicalCookieResponseParams v0 = new CookieManagerSetCanonicalCookieResponseParams();
            v0.success = arg6.booleanValue();
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(2, 2, this.mRequestId)));
        }

        public void call(Object arg1) {
            this.call(((Boolean)arg1));
        }
    }

    final class org.chromium.network.mojom.CookieManager_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.CookieManager$Proxy {
        org.chromium.network.mojom.CookieManager_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addCookieChangeListener(Url arg3, String arg4, CookieChangeListener arg5) {
            CookieManagerAddCookieChangeListenerParams v0 = new CookieManagerAddCookieChangeListenerParams();
            v0.url = arg3;
            v0.name = arg4;
            v0.listener = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4)));
        }

        public void addGlobalChangeListener(CookieChangeListener arg5) {
            CookieManagerAddGlobalChangeListenerParams v0 = new CookieManagerAddGlobalChangeListenerParams();
            v0.notificationPointer = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5)));
        }

        public void cloneInterface(InterfaceRequest arg5) {
            CookieManagerCloneInterfaceParams v0 = new CookieManagerCloneInterfaceParams();
            v0.newInterface = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(6)));
        }

        public void deleteCookies(CookieDeletionFilter arg8, DeleteCookiesResponse arg9) {
            CookieManagerDeleteCookiesParams v0 = new CookieManagerDeleteCookiesParams();
            v0.filter = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new CookieManagerDeleteCookiesResponseParamsForwardToCallback(arg9));
        }

        public void flushCookieStore(FlushCookieStoreResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new CookieManagerFlushCookieStoreParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(7, 1, 0)), new CookieManagerFlushCookieStoreResponseParamsForwardToCallback(arg9));
        }

        public void getAllCookies(GetAllCookiesResponse arg9) {
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(new CookieManagerGetAllCookiesParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new CookieManagerGetAllCookiesResponseParamsForwardToCallback(arg9));
        }

        public void getCookieList(Url arg6, CookieOptions arg7, GetCookieListResponse arg8) {
            CookieManagerGetCookieListParams v0 = new CookieManagerGetCookieListParams();
            v0.url = arg6;
            v0.cookieOptions = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new CookieManagerGetCookieListResponseParamsForwardToCallback(arg8));
        }

        public void setCanonicalCookie(CanonicalCookie arg6, boolean arg7, boolean arg8, SetCanonicalCookieResponse arg9) {
            CookieManagerSetCanonicalCookieParams v0 = new CookieManagerSetCanonicalCookieParams();
            v0.cookie = arg6;
            v0.secureSource = arg7;
            v0.modifyHttpOnly = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2, 1, 0)), new CookieManagerSetCanonicalCookieResponseParamsForwardToCallback(arg9));
        }
    }

    final class org.chromium.network.mojom.CookieManager_Internal$Stub extends Stub {
        org.chromium.network.mojom.CookieManager_Internal$Stub(Core arg1, CookieManager arg2) {
            super(arg1, ((Interface)arg2));
        }

        public boolean accept(Message arg6) {
            try {
                ServiceMessage v6_1 = arg6.asServiceMessage();
                MessageHeader v1 = v6_1.getHeader();
                if(!v1.validateHeader(0)) {
                    return 0;
                }

                int v1_1 = v1.getType();
                if(v1_1 == -2) {
                    goto label_32;
                }

                switch(v1_1) {
                    case 4: {
                        goto label_24;
                    }
                    case 5: {
                        goto label_18;
                    }
                    case 6: {
                        goto label_12;
                    }
                }

                return 0;
            label_18:
                this.getImpl().addGlobalChangeListener(CookieManagerAddGlobalChangeListenerParams.deserialize(v6_1.getPayload()).notificationPointer);
                return 1;
            label_24:
                CookieManagerAddCookieChangeListenerParams v6_2 = CookieManagerAddCookieChangeListenerParams.deserialize(v6_1.getPayload());
                this.getImpl().addCookieChangeListener(v6_2.url, v6_2.name, v6_2.listener);
                return 1;
            label_12:
                this.getImpl().cloneInterface(CookieManagerCloneInterfaceParams.deserialize(v6_1.getPayload()).newInterface);
                return 1;
            label_32:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CookieManager_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
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

                int v3 = v1.getType();
                if(v3 == 7) {
                    goto label_58;
                }

                switch(v3) {
                    case -1: {
                        goto label_54;
                    }
                    case 0: {
                        goto label_45;
                    }
                    case 1: {
                        goto label_34;
                    }
                    case 2: {
                        goto label_22;
                    }
                    case 3: {
                        goto label_12;
                    }
                }

                return 0;
            label_34:
                CookieManagerGetCookieListParams v11_2 = CookieManagerGetCookieListParams.deserialize(v11_1.getPayload());
                this.getImpl().getCookieList(v11_2.url, v11_2.cookieOptions, new CookieManagerGetCookieListResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_54:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CookieManager_Internal.MANAGER, v11_1, arg12);
            label_22:
                CookieManagerSetCanonicalCookieParams v11_3 = CookieManagerSetCanonicalCookieParams.deserialize(v11_1.getPayload());
                this.getImpl().setCanonicalCookie(v11_3.cookie, v11_3.secureSource, v11_3.modifyHttpOnly, new CookieManagerSetCanonicalCookieResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_12:
                this.getImpl().deleteCookies(CookieManagerDeleteCookiesParams.deserialize(v11_1.getPayload()).filter, new CookieManagerDeleteCookiesResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_45:
                CookieManagerGetAllCookiesParams.deserialize(v11_1.getPayload());
                this.getImpl().getAllCookies(new CookieManagerGetAllCookiesResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            label_58:
                CookieManagerFlushCookieStoreParams.deserialize(v11_1.getPayload());
                this.getImpl().flushCookieStore(new CookieManagerFlushCookieStoreResponseParamsProxyToResponder(this.getCore(), arg12, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v11) {
                System.err.println(v11.toString());
                return 0;
            }
        }
    }

    private static final int ADD_COOKIE_CHANGE_LISTENER_ORDINAL = 4;
    private static final int ADD_GLOBAL_CHANGE_LISTENER_ORDINAL = 5;
    private static final int CLONE_INTERFACE_ORDINAL = 6;
    private static final int DELETE_COOKIES_ORDINAL = 3;
    private static final int FLUSH_COOKIE_STORE_ORDINAL = 7;
    private static final int GET_ALL_COOKIES_ORDINAL = 0;
    private static final int GET_COOKIE_LIST_ORDINAL = 1;
    public static final Manager MANAGER = null;
    private static final int SET_CANONICAL_COOKIE_ORDINAL = 2;

    static {
        CookieManager_Internal.MANAGER = new org.chromium.network.mojom.CookieManager_Internal$1();
    }

    CookieManager_Internal() {
        super();
    }
}

