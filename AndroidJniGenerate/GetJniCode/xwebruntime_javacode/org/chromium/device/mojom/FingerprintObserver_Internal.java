package org.chromium.device.mojom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
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

class FingerprintObserver_Internal {
    final class org.chromium.device.mojom.FingerprintObserver_Internal$1 extends Manager {
        org.chromium.device.mojom.FingerprintObserver_Internal$1() {
            super();
        }

        public FingerprintObserver[] buildArray(int arg1) {
            return new FingerprintObserver[arg1];
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

        public Stub buildStub(Core arg2, FingerprintObserver arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((FingerprintObserver)arg2));
        }

        public String getName() {
            return "device::mojom::FingerprintObserver";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class FingerprintObserverOnAuthScanDoneParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public Map matches;
        public int scanResult;

        static {
            FingerprintObserverOnAuthScanDoneParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            FingerprintObserverOnAuthScanDoneParams.DEFAULT_STRUCT_INFO = FingerprintObserverOnAuthScanDoneParams.VERSION_ARRAY[0];
        }

        public FingerprintObserverOnAuthScanDoneParams() {
            this(0);
        }

        private FingerprintObserverOnAuthScanDoneParams(int arg2) {
            super(24, arg2);
        }

        public static FingerprintObserverOnAuthScanDoneParams decode(Decoder arg14) {
            FingerprintObserverOnAuthScanDoneParams v1;
            if(arg14 == null) {
                return null;
            }

            arg14.increaseStackDepth();
            try {
                v1 = new FingerprintObserverOnAuthScanDoneParams(arg14.readAndValidateDataHeader(FingerprintObserverOnAuthScanDoneParams.VERSION_ARRAY).elementsOrVersion);
                int v0_1 = 8;
                v1.scanResult = arg14.readInt(v0_1);
                int v2 = 16;
                int v3 = 0;
                Decoder v4 = arg14.readPointer(v2, false);
                v4.readDataHeaderForMap();
                Decoder v5 = v4.readPointer(v0_1, false);
                int v6 = -1;
                DataHeader v7 = v5.readDataHeaderForPointerArray(v6);
                String[] v8 = new String[v7.elementsOrVersion];
                int v9;
                for(v9 = 0; v9 < v7.elementsOrVersion; ++v9) {
                    v8[v9] = v5.readString(v9 * 8 + v0_1, false);
                }

                Decoder v2_1 = v4.readPointer(v2, false);
                DataHeader v4_1 = v2_1.readDataHeaderForPointerArray(v8.length);
                String[][] v5_1 = new String[v4_1.elementsOrVersion][];
                int v7_1;
                for(v7_1 = 0; v7_1 < v4_1.elementsOrVersion; ++v7_1) {
                    Decoder v9_1 = v2_1.readPointer(v7_1 * 8 + v0_1, false);
                    DataHeader v10 = v9_1.readDataHeaderForPointerArray(v6);
                    v5_1[v7_1] = new String[v10.elementsOrVersion];
                    int v11;
                    for(v11 = 0; v11 < v10.elementsOrVersion; ++v11) {
                        v5_1[v7_1][v11] = v9_1.readString(v11 * 8 + v0_1, false);
                    }
                }

                v1.matches = new HashMap();
                while(v3 < v8.length) {
                    v1.matches.put(v8[v3], v5_1[v3]);
                    ++v3;
                }
            }
            catch(Throwable v0) {
                goto label_71;
            }

            arg14.decreaseStackDepth();
            return v1;
        label_71:
            arg14.decreaseStackDepth();
            throw v0;
        }

        public static FingerprintObserverOnAuthScanDoneParams deserialize(ByteBuffer arg2) {
            return FingerprintObserverOnAuthScanDoneParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintObserverOnAuthScanDoneParams deserialize(Message arg1) {
            return FingerprintObserverOnAuthScanDoneParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg11) {
            arg11 = arg11.getEncoderAtDataOffset(FingerprintObserverOnAuthScanDoneParams.DEFAULT_STRUCT_INFO);
            int v1 = 8;
            arg11.encode(this.scanResult, v1);
            int v2 = 16;
            if(this.matches == null) {
                arg11.encodeNullPointer(v2, false);
            }
            else {
                arg11 = arg11.encoderForMap(v2);
                int v0 = this.matches.size();
                String[] v4 = new String[v0];
                String[][] v0_1 = new String[v0][];
                Iterator v5 = this.matches.entrySet().iterator();
                int v6;
                for(v6 = 0; v5.hasNext(); ++v6) {
                    Object v7 = v5.next();
                    v4[v6] = ((Map$Entry)v7).getKey();
                    v0_1[v6] = ((Map$Entry)v7).getValue();
                }

                v6 = -1;
                Encoder v5_1 = arg11.encodePointerArray(v4.length, v1, v6);
                int v7_1;
                for(v7_1 = 0; v7_1 < v4.length; ++v7_1) {
                    v5_1.encode(v4[v7_1], v7_1 * 8 + v1, false);
                }

                arg11 = arg11.encodePointerArray(v0_1.length, v2, v6);
                for(v2 = 0; v2 < v0_1.length; ++v2) {
                    if(v0_1[v2] == null) {
                        arg11.encodeNullPointer(v2 * 8 + v1, false);
                    }
                    else {
                        Encoder v4_1 = arg11.encodePointerArray(v0_1[v2].length, v2 * 8 + v1, v6);
                        int v5_2;
                        for(v5_2 = 0; v5_2 < v0_1[v2].length; ++v5_2) {
                            v4_1.encode(v0_1[v2][v5_2], v5_2 * 8 + v1, false);
                        }
                    }
                }
            }
        }
    }

    final class FingerprintObserverOnEnrollScanDoneParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public boolean isComplete;
        public int percentComplete;
        public int scanResult;

        static {
            FingerprintObserverOnEnrollScanDoneParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            FingerprintObserverOnEnrollScanDoneParams.DEFAULT_STRUCT_INFO = FingerprintObserverOnEnrollScanDoneParams.VERSION_ARRAY[0];
        }

        public FingerprintObserverOnEnrollScanDoneParams() {
            this(0);
        }

        private FingerprintObserverOnEnrollScanDoneParams(int arg2) {
            super(24, arg2);
        }

        public static FingerprintObserverOnEnrollScanDoneParams decode(Decoder arg3) {
            FingerprintObserverOnEnrollScanDoneParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new FingerprintObserverOnEnrollScanDoneParams(arg3.readAndValidateDataHeader(FingerprintObserverOnEnrollScanDoneParams.VERSION_ARRAY).elementsOrVersion);
                v1.scanResult = arg3.readInt(8);
                v1.isComplete = arg3.readBoolean(12, 0);
                v1.percentComplete = arg3.readInt(16);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static FingerprintObserverOnEnrollScanDoneParams deserialize(ByteBuffer arg2) {
            return FingerprintObserverOnEnrollScanDoneParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintObserverOnEnrollScanDoneParams deserialize(Message arg1) {
            return FingerprintObserverOnEnrollScanDoneParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(FingerprintObserverOnEnrollScanDoneParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.scanResult, 8);
            arg4.encode(this.isComplete, 12, 0);
            arg4.encode(this.percentComplete, 16);
        }
    }

    final class FingerprintObserverOnRestartedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            FingerprintObserverOnRestartedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            FingerprintObserverOnRestartedParams.DEFAULT_STRUCT_INFO = FingerprintObserverOnRestartedParams.VERSION_ARRAY[0];
        }

        public FingerprintObserverOnRestartedParams() {
            this(0);
        }

        private FingerprintObserverOnRestartedParams(int arg2) {
            super(8, arg2);
        }

        public static FingerprintObserverOnRestartedParams decode(Decoder arg2) {
            FingerprintObserverOnRestartedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintObserverOnRestartedParams(arg2.readAndValidateDataHeader(FingerprintObserverOnRestartedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintObserverOnRestartedParams deserialize(ByteBuffer arg2) {
            return FingerprintObserverOnRestartedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintObserverOnRestartedParams deserialize(Message arg1) {
            return FingerprintObserverOnRestartedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(FingerprintObserverOnRestartedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class FingerprintObserverOnSessionFailedParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 8;
        private static final DataHeader[] VERSION_ARRAY;

        static {
            FingerprintObserverOnSessionFailedParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(8, 0)};
            FingerprintObserverOnSessionFailedParams.DEFAULT_STRUCT_INFO = FingerprintObserverOnSessionFailedParams.VERSION_ARRAY[0];
        }

        public FingerprintObserverOnSessionFailedParams() {
            this(0);
        }

        private FingerprintObserverOnSessionFailedParams(int arg2) {
            super(8, arg2);
        }

        public static FingerprintObserverOnSessionFailedParams decode(Decoder arg2) {
            FingerprintObserverOnSessionFailedParams v1;
            if(arg2 == null) {
                return null;
            }

            arg2.increaseStackDepth();
            try {
                v1 = new FingerprintObserverOnSessionFailedParams(arg2.readAndValidateDataHeader(FingerprintObserverOnSessionFailedParams.VERSION_ARRAY).elementsOrVersion);
            }
            catch(Throwable v0) {
                arg2.decreaseStackDepth();
                throw v0;
            }

            arg2.decreaseStackDepth();
            return v1;
        }

        public static FingerprintObserverOnSessionFailedParams deserialize(ByteBuffer arg2) {
            return FingerprintObserverOnSessionFailedParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static FingerprintObserverOnSessionFailedParams deserialize(Message arg1) {
            return FingerprintObserverOnSessionFailedParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg2) {
            arg2.getEncoderAtDataOffset(FingerprintObserverOnSessionFailedParams.DEFAULT_STRUCT_INFO);
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.device.mojom.FingerprintObserver$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onAuthScanDone(int arg4, Map arg5) {
            FingerprintObserverOnAuthScanDoneParams v0 = new FingerprintObserverOnAuthScanDoneParams();
            v0.scanResult = arg4;
            v0.matches = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(2)));
        }

        public void onEnrollScanDone(int arg3, boolean arg4, int arg5) {
            FingerprintObserverOnEnrollScanDoneParams v0 = new FingerprintObserverOnEnrollScanDoneParams();
            v0.scanResult = arg3;
            v0.isComplete = arg4;
            v0.percentComplete = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(1)));
        }

        public void onRestarted() {
            this.getProxyHandler().getMessageReceiver().accept(new FingerprintObserverOnRestartedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }

        public void onSessionFailed() {
            this.getProxyHandler().getMessageReceiver().accept(new FingerprintObserverOnSessionFailedParams().serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(3)));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, FingerprintObserver arg2) {
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
                    goto label_37;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_32;
                    }
                    case 1: {
                        goto label_24;
                    }
                    case 2: {
                        goto label_17;
                    }
                    case 3: {
                        goto label_12;
                    }
                }

                return 0;
            label_17:
                FingerprintObserverOnAuthScanDoneParams v6_2 = FingerprintObserverOnAuthScanDoneParams.deserialize(v6_1.getPayload());
                this.getImpl().onAuthScanDone(v6_2.scanResult, v6_2.matches);
                return 1;
            label_24:
                FingerprintObserverOnEnrollScanDoneParams v6_3 = FingerprintObserverOnEnrollScanDoneParams.deserialize(v6_1.getPayload());
                this.getImpl().onEnrollScanDone(v6_3.scanResult, v6_3.isComplete, v6_3.percentComplete);
                return 1;
            label_12:
                FingerprintObserverOnSessionFailedParams.deserialize(v6_1.getPayload());
                this.getImpl().onSessionFailed();
                return 1;
            label_32:
                FingerprintObserverOnRestartedParams.deserialize(v6_1.getPayload());
                this.getImpl().onRestarted();
                return 1;
            label_37:
                return InterfaceControlMessagesHelper.handleRunOrClosePipe(FingerprintObserver_Internal.MANAGER, v6_1);
            }
            catch(DeserializationException v6) {
                System.err.println(v6.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), FingerprintObserver_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER = null;
    private static final int ON_AUTH_SCAN_DONE_ORDINAL = 2;
    private static final int ON_ENROLL_SCAN_DONE_ORDINAL = 1;
    private static final int ON_RESTARTED_ORDINAL = 0;
    private static final int ON_SESSION_FAILED_ORDINAL = 3;

    static {
        FingerprintObserver_Internal.MANAGER = new org.chromium.device.mojom.FingerprintObserver_Internal$1();
    }

    FingerprintObserver_Internal() {
        super();
    }
}

