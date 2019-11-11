package org.chromium.installedapp.mojom;

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

class InstalledAppProvider_Internal {
    final class org.chromium.installedapp.mojom.InstalledAppProvider_Internal$1 extends Manager {
        org.chromium.installedapp.mojom.InstalledAppProvider_Internal$1() {
            super();
        }

        public InstalledAppProvider[] buildArray(int arg1) {
            return new InstalledAppProvider[arg1];
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

        public Stub buildStub(Core arg2, InstalledAppProvider arg3) {
            return new Stub(arg2, arg3);
        }

        public org.chromium.mojo.bindings.Interface$Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((InstalledAppProvider)arg2));
        }

        public String getName() {
            return "blink::mojom::InstalledAppProvider";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class InstalledAppProviderFilterInstalledAppsParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public RelatedApplication[] relatedApps;

        static {
            InstalledAppProviderFilterInstalledAppsParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InstalledAppProviderFilterInstalledAppsParams.DEFAULT_STRUCT_INFO = InstalledAppProviderFilterInstalledAppsParams.VERSION_ARRAY[0];
        }

        public InstalledAppProviderFilterInstalledAppsParams() {
            this(0);
        }

        private InstalledAppProviderFilterInstalledAppsParams(int arg2) {
            super(16, arg2);
        }

        public static InstalledAppProviderFilterInstalledAppsParams decode(Decoder arg8) {
            InstalledAppProviderFilterInstalledAppsParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new InstalledAppProviderFilterInstalledAppsParams(arg8.readAndValidateDataHeader(InstalledAppProviderFilterInstalledAppsParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.relatedApps = new RelatedApplication[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.relatedApps[v5] = RelatedApplication.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static InstalledAppProviderFilterInstalledAppsParams deserialize(ByteBuffer arg2) {
            return InstalledAppProviderFilterInstalledAppsParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InstalledAppProviderFilterInstalledAppsParams deserialize(Message arg1) {
            return InstalledAppProviderFilterInstalledAppsParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(InstalledAppProviderFilterInstalledAppsParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.relatedApps == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.relatedApps.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.relatedApps.length; ++v0) {
                    arg6.encode(this.relatedApps[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    final class InstalledAppProviderFilterInstalledAppsResponseParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 16;
        private static final DataHeader[] VERSION_ARRAY;
        public RelatedApplication[] installedApps;

        static {
            InstalledAppProviderFilterInstalledAppsResponseParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(16, 0)};
            InstalledAppProviderFilterInstalledAppsResponseParams.DEFAULT_STRUCT_INFO = InstalledAppProviderFilterInstalledAppsResponseParams.VERSION_ARRAY[0];
        }

        public InstalledAppProviderFilterInstalledAppsResponseParams() {
            this(0);
        }

        private InstalledAppProviderFilterInstalledAppsResponseParams(int arg2) {
            super(16, arg2);
        }

        public static InstalledAppProviderFilterInstalledAppsResponseParams decode(Decoder arg8) {
            InstalledAppProviderFilterInstalledAppsResponseParams v1;
            if(arg8 == null) {
                return null;
            }

            arg8.increaseStackDepth();
            try {
                v1 = new InstalledAppProviderFilterInstalledAppsResponseParams(arg8.readAndValidateDataHeader(InstalledAppProviderFilterInstalledAppsResponseParams.VERSION_ARRAY).elementsOrVersion);
                int v2 = 8;
                Decoder v3 = arg8.readPointer(v2, false);
                DataHeader v4 = v3.readDataHeaderForPointerArray(-1);
                v1.installedApps = new RelatedApplication[v4.elementsOrVersion];
                int v5;
                for(v5 = 0; v5 < v4.elementsOrVersion; ++v5) {
                    v1.installedApps[v5] = RelatedApplication.decode(v3.readPointer(v5 * 8 + v2, false));
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

        public static InstalledAppProviderFilterInstalledAppsResponseParams deserialize(ByteBuffer arg2) {
            return InstalledAppProviderFilterInstalledAppsResponseParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static InstalledAppProviderFilterInstalledAppsResponseParams deserialize(Message arg1) {
            return InstalledAppProviderFilterInstalledAppsResponseParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg6) {
            arg6 = arg6.getEncoderAtDataOffset(InstalledAppProviderFilterInstalledAppsResponseParams.DEFAULT_STRUCT_INFO);
            int v2 = 8;
            if(this.installedApps == null) {
                arg6.encodeNullPointer(v2, false);
            }
            else {
                arg6 = arg6.encodePointerArray(this.installedApps.length, v2, -1);
                int v0;
                for(v0 = 0; v0 < this.installedApps.length; ++v0) {
                    arg6.encode(this.installedApps[v0], v0 * 8 + v2, false);
                }
            }
        }
    }

    class InstalledAppProviderFilterInstalledAppsResponseParamsForwardToCallback extends SideEffectFreeCloseable implements MessageReceiver {
        private final FilterInstalledAppsResponse mCallback;

        InstalledAppProviderFilterInstalledAppsResponseParamsForwardToCallback(FilterInstalledAppsResponse arg1) {
            super();
            this.mCallback = arg1;
        }

        public boolean accept(Message arg4) {
            try {
                ServiceMessage v4 = arg4.asServiceMessage();
                if(!v4.getHeader().validateHeader(0, 2)) {
                    return 0;
                }

                this.mCallback.call(InstalledAppProviderFilterInstalledAppsResponseParams.deserialize(v4.getPayload()).installedApps);
                return 1;
            }
            catch(DeserializationException ) {
                return 0;
            }
        }
    }

    class InstalledAppProviderFilterInstalledAppsResponseParamsProxyToResponder implements FilterInstalledAppsResponse {
        private final Core mCore;
        private final MessageReceiver mMessageReceiver;
        private final long mRequestId;

        InstalledAppProviderFilterInstalledAppsResponseParamsProxyToResponder(Core arg1, MessageReceiver arg2, long arg3) {
            super();
            this.mCore = arg1;
            this.mMessageReceiver = arg2;
            this.mRequestId = arg3;
        }

        public void call(Object arg1) {
            this.call(((RelatedApplication[])arg1));
        }

        public void call(RelatedApplication[] arg7) {
            InstalledAppProviderFilterInstalledAppsResponseParams v0 = new InstalledAppProviderFilterInstalledAppsResponseParams();
            v0.installedApps = arg7;
            this.mMessageReceiver.accept(v0.serializeWithHeader(this.mCore, new MessageHeader(0, 2, this.mRequestId)));
        }
    }

    final class Proxy extends AbstractProxy implements org.chromium.installedapp.mojom.InstalledAppProvider$Proxy {
        Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void filterInstalledApps(RelatedApplication[] arg8, FilterInstalledAppsResponse arg9) {
            InstalledAppProviderFilterInstalledAppsParams v0 = new InstalledAppProviderFilterInstalledAppsParams();
            v0.relatedApps = arg8;
            this.getProxyHandler().getMessageReceiver().acceptWithResponder(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0, 1, 0)), new InstalledAppProviderFilterInstalledAppsResponseParamsForwardToCallback(arg9));
        }
    }

    final class Stub extends org.chromium.mojo.bindings.Interface$Stub {
        Stub(Core arg1, InstalledAppProvider arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(InstalledAppProvider_Internal.MANAGER, v4_1);
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
                        goto label_20;
                    }
                    case 0: {
                        goto label_10;
                    }
                }

                return 0;
            label_20:
                return InterfaceControlMessagesHelper.handleRun(this.getCore(), InstalledAppProvider_Internal.MANAGER, v9_1, arg10);
            label_10:
                this.getImpl().filterInstalledApps(InstalledAppProviderFilterInstalledAppsParams.deserialize(v9_1.getPayload()).relatedApps, new InstalledAppProviderFilterInstalledAppsResponseParamsProxyToResponder(this.getCore(), arg10, v1.getRequestId()));
                return 1;
            }
            catch(DeserializationException v9) {
                System.err.println(v9.toString());
                return 0;
            }
        }
    }

    private static final int FILTER_INSTALLED_APPS_ORDINAL;
    public static final Manager MANAGER;

    static {
        InstalledAppProvider_Internal.MANAGER = new org.chromium.installedapp.mojom.InstalledAppProvider_Internal$1();
    }

    InstalledAppProvider_Internal() {
        super();
    }
}

