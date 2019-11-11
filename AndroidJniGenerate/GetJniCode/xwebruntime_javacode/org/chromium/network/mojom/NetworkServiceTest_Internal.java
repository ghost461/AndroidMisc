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

class NetworkServiceTest_Internal {
    final class org.chromium.network.mojom.NetworkServiceTest_Internal$1 extends Manager {
        org.chromium.network.mojom.NetworkServiceTest_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public NetworkServiceTest[] buildArray(int arg1) {
            return new NetworkServiceTest[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.NetworkServiceTest_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.NetworkServiceTest_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((NetworkServiceTest)arg2));
        }

        public org.chromium.network.mojom.NetworkServiceTest_Internal$Stub buildStub(Core arg2, NetworkServiceTest arg3) {
            return new org.chromium.network.mojom.NetworkServiceTest_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::NetworkServiceTest";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class NetworkServiceTestAddRulesParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public Rule[] rules;

        static {
            NetworkServiceTestAddRulesParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceTestAddRulesParams.DEFAULT_STRUCT_INFO = NetworkServiceTestAddRulesParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestAddRulesParams() {
            this(0);
        }

        private NetworkServiceTestAddRulesParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceTestAddRulesParams decode(Decoder arg8) {
            NetworkServiceTestAddRulesParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestAddRulesParams(arg8.readAndValidateDataHeader(NetworkServiceTestAddRulesParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.rules = new Rule[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.rules[v5] = Rule.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static NetworkServiceTestAddRulesParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestAddRulesParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestAddRulesParams deserialize(Message arg1) {
            return NetworkServiceTestAddRulesParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(NetworkServiceTestAddRulesParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.rules == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.rules.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.rules.length; ++v0) {
                    arg6.encode(this.rules[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    final class NetworkServiceTestAddRulesResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkServiceTestAddRulesResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkServiceTestAddRulesResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceTestAddRulesResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestAddRulesResponseParams() {
            this(0);
        }

        private NetworkServiceTestAddRulesResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkServiceTestAddRulesResponseParams decode(Decoder arg2) {
            NetworkServiceTestAddRulesResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestAddRulesResponseParams(arg2.readAndValidateDataHeader(NetworkServiceTestAddRulesResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestAddRulesResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestAddRulesResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestAddRulesResponseParams deserialize(Message arg1) {
            return NetworkServiceTestAddRulesResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkServiceTestAddRulesResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkServiceTestAddRulesResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final AddRulesResponse mCallback;

        NetworkServiceTestAddRulesResponseParamsForwardToCallback(AddRulesResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg3) {
            try {
                if(!arg3.asServiceMessage().getHeader().validateHeader(0, 2)) {
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

    class NetworkServiceTestAddRulesResponseParamsProxyToResponder implements AddRulesResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceTestAddRulesResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkServiceTestAddRulesResponseParams().serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 40;
        private static final DataHeader[] VERSION_ARRAY;
        public X509Certificate cert;
        public String hostPattern;
        public int rv;
        public CertVerifyResult verifyResult;

        static {
            NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(40, 0)};
            NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.DEFAULT_STRUCT_INFO = NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams() {
            this(0);
        }

        private NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams(int arg2) {
            super(40, arg2);
        }

        public static NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams decode(Decoder arg3) {
            NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams(arg3.readAndValidateDataHeader(NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.VERSION_ARRAY).elementsOrVersion);
                v1.cert = X509Certificate.decode(arg3.readPointer(8, false));
                v1.hostPattern = arg3.readString(16, false);
                v1.verifyResult = CertVerifyResult.decode(arg3.readPointer(24, false));
                v1.rv = arg3.readInt(0x20);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams deserialize(Message arg1) {
            return NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.cert, 8, false);
            arg4.encode(this.hostPattern, 16, false);
            arg4.encode(this.verifyResult, 24, false);
            arg4.encode(this.rv, 0x20);
        }
    }

    final class NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams() {
            this(0);
        }

        private NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams decode(Decoder arg2) {
            NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams(arg2.readAndValidateDataHeader(NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams deserialize(Message arg1) {
            return NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final MockCertVerifierAddResultForCertAndHostResponse mCallback;

        NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParamsForwardToCallback(MockCertVerifierAddResultForCertAndHostResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(4, 2)) {
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

    class NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParamsProxyToResponder implements MockCertVerifierAddResultForCertAndHostResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParams().serializeWithHeader(this.mCore, new MessageHeader(4, 2, this.mRequestId)));
        }
    }

    final class NetworkServiceTestMockCertVerifierSetDefaultResultParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int defaultResult;

        static {
            NetworkServiceTestMockCertVerifierSetDefaultResultParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceTestMockCertVerifierSetDefaultResultParams.DEFAULT_STRUCT_INFO = NetworkServiceTestMockCertVerifierSetDefaultResultParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestMockCertVerifierSetDefaultResultParams() {
            this(0);
        }

        private NetworkServiceTestMockCertVerifierSetDefaultResultParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceTestMockCertVerifierSetDefaultResultParams decode(Decoder arg2) {
            NetworkServiceTestMockCertVerifierSetDefaultResultParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestMockCertVerifierSetDefaultResultParams(arg2.readAndValidateDataHeader(NetworkServiceTestMockCertVerifierSetDefaultResultParams.VERSION_ARRAY).elementsOrVersion);
                v1.defaultResult = arg2.readInt(8);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestMockCertVerifierSetDefaultResultParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestMockCertVerifierSetDefaultResultParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestMockCertVerifierSetDefaultResultParams deserialize(Message arg1) {
            return NetworkServiceTestMockCertVerifierSetDefaultResultParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NetworkServiceTestMockCertVerifierSetDefaultResultParams.DEFAULT_STRUCT_INFO).encode(this.defaultResult, 8);
        }
    }

    final class NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams() {
            this(0);
        }

        private NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams decode(Decoder arg2) {
            NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams(arg2.readAndValidateDataHeader(NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams deserialize(Message arg1) {
            return NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkServiceTestMockCertVerifierSetDefaultResultResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final MockCertVerifierSetDefaultResultResponse mCallback;

        NetworkServiceTestMockCertVerifierSetDefaultResultResponseParamsForwardToCallback(MockCertVerifierSetDefaultResultResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(3, 2)) {
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

    class NetworkServiceTestMockCertVerifierSetDefaultResultResponseParamsProxyToResponder implements MockCertVerifierSetDefaultResultResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceTestMockCertVerifierSetDefaultResultResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkServiceTestMockCertVerifierSetDefaultResultResponseParams().serializeWithHeader(this.mCore, new MessageHeader(3, 2, this.mRequestId)));
        }
    }

    final class NetworkServiceTestSetShouldRequireCtParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int required;

        static {
            NetworkServiceTestSetShouldRequireCtParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceTestSetShouldRequireCtParams.DEFAULT_STRUCT_INFO = NetworkServiceTestSetShouldRequireCtParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestSetShouldRequireCtParams() {
            this(0);
        }

        private NetworkServiceTestSetShouldRequireCtParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceTestSetShouldRequireCtParams decode(Decoder arg2) {
            NetworkServiceTestSetShouldRequireCtParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestSetShouldRequireCtParams(arg2.readAndValidateDataHeader(NetworkServiceTestSetShouldRequireCtParams.VERSION_ARRAY).elementsOrVersion);
                v1.required = arg2.readInt(8);
                ShouldRequireCt.validate(v1.required);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestSetShouldRequireCtParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestSetShouldRequireCtParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestSetShouldRequireCtParams deserialize(Message arg1) {
            return NetworkServiceTestSetShouldRequireCtParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NetworkServiceTestSetShouldRequireCtParams.DEFAULT_STRUCT_INFO).encode(this.required, 8);
        }
    }

    final class NetworkServiceTestSetShouldRequireCtResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkServiceTestSetShouldRequireCtResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkServiceTestSetShouldRequireCtResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceTestSetShouldRequireCtResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestSetShouldRequireCtResponseParams() {
            this(0);
        }

        private NetworkServiceTestSetShouldRequireCtResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkServiceTestSetShouldRequireCtResponseParams decode(Decoder arg2) {
            NetworkServiceTestSetShouldRequireCtResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestSetShouldRequireCtResponseParams(arg2.readAndValidateDataHeader(NetworkServiceTestSetShouldRequireCtResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestSetShouldRequireCtResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestSetShouldRequireCtResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestSetShouldRequireCtResponseParams deserialize(Message arg1) {
            return NetworkServiceTestSetShouldRequireCtResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkServiceTestSetShouldRequireCtResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkServiceTestSetShouldRequireCtResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SetShouldRequireCtResponse mCallback;

        NetworkServiceTestSetShouldRequireCtResponseParamsForwardToCallback(SetShouldRequireCtResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(5, 2)) {
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

    class NetworkServiceTestSetShouldRequireCtResponseParamsProxyToResponder implements SetShouldRequireCtResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceTestSetShouldRequireCtResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkServiceTestSetShouldRequireCtResponseParams().serializeWithHeader(this.mCore, new MessageHeader(5, 2, this.mRequestId)));
        }
    }

    final class NetworkServiceTestSimulateCrashParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkServiceTestSimulateCrashParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkServiceTestSimulateCrashParams.DEFAULT_STRUCT_INFO = NetworkServiceTestSimulateCrashParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestSimulateCrashParams() {
            this(0);
        }

        private NetworkServiceTestSimulateCrashParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkServiceTestSimulateCrashParams decode(Decoder arg2) {
            NetworkServiceTestSimulateCrashParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestSimulateCrashParams(arg2.readAndValidateDataHeader(NetworkServiceTestSimulateCrashParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestSimulateCrashParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestSimulateCrashParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestSimulateCrashParams deserialize(Message arg1) {
            return NetworkServiceTestSimulateCrashParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkServiceTestSimulateCrashParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class NetworkServiceTestSimulateNetworkChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public int type;

        static {
            NetworkServiceTestSimulateNetworkChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            NetworkServiceTestSimulateNetworkChangeParams.DEFAULT_STRUCT_INFO = NetworkServiceTestSimulateNetworkChangeParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestSimulateNetworkChangeParams() {
            this(0);
        }

        private NetworkServiceTestSimulateNetworkChangeParams(int arg2) {
            super(16, arg2);
        }

        public static NetworkServiceTestSimulateNetworkChangeParams decode(Decoder arg2) {
            NetworkServiceTestSimulateNetworkChangeParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestSimulateNetworkChangeParams(arg2.readAndValidateDataHeader(NetworkServiceTestSimulateNetworkChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.type = arg2.readInt(8);
                ConnectionType.validate(v1.type);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestSimulateNetworkChangeParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestSimulateNetworkChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestSimulateNetworkChangeParams deserialize(Message arg1) {
            return NetworkServiceTestSimulateNetworkChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg3) {
            arg3.getEncoderAtDataOffset(NetworkServiceTestSimulateNetworkChangeParams.DEFAULT_STRUCT_INFO).encode(this.type, 8);
        }
    }

    final class NetworkServiceTestSimulateNetworkChangeResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            NetworkServiceTestSimulateNetworkChangeResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            NetworkServiceTestSimulateNetworkChangeResponseParams.DEFAULT_STRUCT_INFO = NetworkServiceTestSimulateNetworkChangeResponseParams.VERSION_ARRAY[0];
        }

        public NetworkServiceTestSimulateNetworkChangeResponseParams() {
            this(0);
        }

        private NetworkServiceTestSimulateNetworkChangeResponseParams(int arg2) {
            super(8, arg2);
        }

        public static NetworkServiceTestSimulateNetworkChangeResponseParams decode(Decoder arg2) {
            NetworkServiceTestSimulateNetworkChangeResponseParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new NetworkServiceTestSimulateNetworkChangeResponseParams(arg2.readAndValidateDataHeader(NetworkServiceTestSimulateNetworkChangeResponseParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static NetworkServiceTestSimulateNetworkChangeResponseParams deserialize(ByteBuffer arg2) {
            return NetworkServiceTestSimulateNetworkChangeResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static NetworkServiceTestSimulateNetworkChangeResponseParams deserialize(Message arg1) {
            return NetworkServiceTestSimulateNetworkChangeResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(NetworkServiceTestSimulateNetworkChangeResponseParams.DEFAULT_STRUCT_INFO);
        }
    }

    class NetworkServiceTestSimulateNetworkChangeResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final SimulateNetworkChangeResponse mCallback;

        NetworkServiceTestSimulateNetworkChangeResponseParamsForwardToCallback(SimulateNetworkChangeResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                if(!arg4.asServiceMessage().getHeader().validateHeader(1, 2)) {
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

    class NetworkServiceTestSimulateNetworkChangeResponseParamsProxyToResponder implements SimulateNetworkChangeResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        NetworkServiceTestSimulateNetworkChangeResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call() {
            this.mMessageReceiver.accept(new NetworkServiceTestSimulateNetworkChangeResponseParams().serializeWithHeader(this.mCore, new MessageHeader(1, 2, this.mRequestId)));
        }
    }

    final class org.chromium.network.mojom.NetworkServiceTest_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.NetworkServiceTest$Proxy {
        org.chromium.network.mojom.NetworkServiceTest_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void addRules(Rule[] arg8, AddRulesResponse arg9) {
            NetworkServiceTestAddRulesParams v0 = new NetworkServiceTestAddRulesParams();
            v0.rules = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new NetworkServiceTestAddRulesResponseParamsForwardToCallback(arg9));
        }

        public void mockCertVerifierAddResultForCertAndHost(X509Certificate arg5, String arg6, CertVerifyResult arg7, int arg8, MockCertVerifierAddResultForCertAndHostResponse arg9) {
            NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams v0 = new NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams();
            v0.cert = arg5;
            v0.hostPattern = arg6;
            v0.verifyResult = arg7;
            v0.rv = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(4, 1, 0)), new NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParamsForwardToCallback(arg9));
        }

        public void mockCertVerifierSetDefaultResult(int arg8, MockCertVerifierSetDefaultResultResponse arg9) {
            NetworkServiceTestMockCertVerifierSetDefaultResultParams v0 = new NetworkServiceTestMockCertVerifierSetDefaultResultParams();
            v0.defaultResult = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3, 1, 0)), new NetworkServiceTestMockCertVerifierSetDefaultResultResponseParamsForwardToCallback(arg9));
        }

        public void setShouldRequireCt(int arg8, SetShouldRequireCtResponse arg9) {
            NetworkServiceTestSetShouldRequireCtParams v0 = new NetworkServiceTestSetShouldRequireCtParams();
            v0.required = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(5, 1, 0)), new NetworkServiceTestSetShouldRequireCtResponseParamsForwardToCallback(arg9));
        }

        public void simulateCrash() {
            this.getProxyHandler().getMessageReceiver().accept(new NetworkServiceTestSimulateCrashParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void simulateNetworkChange(int arg7, SimulateNetworkChangeResponse arg8) {
            NetworkServiceTestSimulateNetworkChangeParams v0 = new NetworkServiceTestSimulateNetworkChangeParams();
            v0.type = arg7;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1, 1, 0)), new NetworkServiceTestSimulateNetworkChangeResponseParamsForwardToCallback(arg8));
        }
    }

    final class org.chromium.network.mojom.NetworkServiceTest_Internal$Stub extends Stub {
        org.chromium.network.mojom.NetworkServiceTest_Internal$Stub(Core arg1, NetworkServiceTest arg2) {
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
                if(v1_1 != -2) {
                    if(v1_1 != 2) {
                        return 0;
                    }

                    NetworkServiceTestSimulateCrashParams.deserialize(v4_1.getPayload());
                    this.getImpl().simulateCrash();
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(NetworkServiceTest_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }

        public boolean acceptWithResponder(Message arg13, MessageReceiver arg14) {
            try {
                ServiceMessage v13_1 = arg13.asServiceMessage();
                MessageHeader v1 = v13_1.getHeader();
                if(!v1.validateHeader(1)) {
                    return 0;
                }

                switch(v1.getType()) {
                    case -1: {
                        goto label_64;
                    }
                    case 0: {
                        goto label_54;
                    }
                    case 1: {
                        goto label_44;
                    }
                    case 3: {
                        goto label_34;
                    }
                    case 4: {
                        goto label_20;
                    }
                    case 5: {
                        goto label_10;
                    }
                }

                return 0;
            label_34:
                this.getImpl().mockCertVerifierSetDefaultResult(NetworkServiceTestMockCertVerifierSetDefaultResultParams.deserialize(v13_1.getPayload()).defaultResult, new NetworkServiceTestMockCertVerifierSetDefaultResultResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_20:
                NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams v13_2 = NetworkServiceTestMockCertVerifierAddResultForCertAndHostParams.deserialize(v13_1.getPayload());
                this.getImpl().mockCertVerifierAddResultForCertAndHost(v13_2.cert, v13_2.hostPattern, v13_2.verifyResult, v13_2.rv, new NetworkServiceTestMockCertVerifierAddResultForCertAndHostResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_54:
                this.getImpl().addRules(NetworkServiceTestAddRulesParams.deserialize(v13_1.getPayload()).rules, new NetworkServiceTestAddRulesResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_10:
                this.getImpl().setShouldRequireCt(NetworkServiceTestSetShouldRequireCtParams.deserialize(v13_1.getPayload()).required, new NetworkServiceTestSetShouldRequireCtResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_44:
                this.getImpl().simulateNetworkChange(NetworkServiceTestSimulateNetworkChangeParams.deserialize(v13_1.getPayload()).type, new NetworkServiceTestSimulateNetworkChangeResponseParamsProxyToResponder(this.getCore(), arg14, v1.getRequestId()));
                return 1;
            label_64:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), NetworkServiceTest_Internal.MANAGER, v13_1, arg14);
            }
            catch(DeserializationException v13) {
                System.err.println(v13.toString());
                return 0;
            }
        }
    }

    private static final int ADD_RULES_ORDINAL = 0;
    public static final Manager MANAGER = null;
    private static final int MOCK_CERT_VERIFIER_ADD_RESULT_FOR_CERT_AND_HOST_ORDINAL = 4;
    private static final int MOCK_CERT_VERIFIER_SET_DEFAULT_RESULT_ORDINAL = 3;
    private static final int SET_SHOULD_REQUIRE_CT_ORDINAL = 5;
    private static final int SIMULATE_CRASH_ORDINAL = 2;
    private static final int SIMULATE_NETWORK_CHANGE_ORDINAL = 1;

    static {
        NetworkServiceTest_Internal.MANAGER = new org.chromium.network.mojom.NetworkServiceTest_Internal$1();
    }

    NetworkServiceTest_Internal() {
        super();
    }
}

