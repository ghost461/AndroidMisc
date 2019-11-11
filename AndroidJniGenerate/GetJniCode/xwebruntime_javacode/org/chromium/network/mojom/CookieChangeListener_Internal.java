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
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.Core;

class CookieChangeListener_Internal {
    final class org.chromium.network.mojom.CookieChangeListener_Internal$1 extends Manager {
        org.chromium.network.mojom.CookieChangeListener_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public CookieChangeListener[] buildArray(int arg1) {
            return new CookieChangeListener[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.CookieChangeListener_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.CookieChangeListener_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((CookieChangeListener)arg2));
        }

        public org.chromium.network.mojom.CookieChangeListener_Internal$Stub buildStub(Core arg2, CookieChangeListener arg3) {
            return new org.chromium.network.mojom.CookieChangeListener_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::CookieChangeListener";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class CookieChangeListenerOnCookieChangeParams extends Struct {
        private static final DataHeader DEFAULT_STRUCT_INFO = null;
        private static final int STRUCT_SIZE = 24;
        private static final DataHeader[] VERSION_ARRAY;
        public int cause;
        public CanonicalCookie cookie;

        static {
            CookieChangeListenerOnCookieChangeParams.VERSION_ARRAY = new DataHeader[]{new DataHeader(24, 0)};
            CookieChangeListenerOnCookieChangeParams.DEFAULT_STRUCT_INFO = CookieChangeListenerOnCookieChangeParams.VERSION_ARRAY[0];
        }

        public CookieChangeListenerOnCookieChangeParams() {
            this(0);
        }

        private CookieChangeListenerOnCookieChangeParams(int arg2) {
            super(24, arg2);
        }

        public static CookieChangeListenerOnCookieChangeParams decode(Decoder arg3) {
            CookieChangeListenerOnCookieChangeParams v1;
            if(arg3 == null) {
                return null;
            }

            arg3.increaseStackDepth();
            try {
                v1 = new CookieChangeListenerOnCookieChangeParams(arg3.readAndValidateDataHeader(CookieChangeListenerOnCookieChangeParams.VERSION_ARRAY).elementsOrVersion);
                v1.cookie = CanonicalCookie.decode(arg3.readPointer(8, false));
                v1.cause = arg3.readInt(16);
                CookieChangeCause.validate(v1.cause);
            }
            catch(Throwable v0) {
                arg3.decreaseStackDepth();
                throw v0;
            }

            arg3.decreaseStackDepth();
            return v1;
        }

        public static CookieChangeListenerOnCookieChangeParams deserialize(ByteBuffer arg2) {
            return CookieChangeListenerOnCookieChangeParams.deserialize(new Message(arg2, new ArrayList()));
        }

        public static CookieChangeListenerOnCookieChangeParams deserialize(Message arg1) {
            return CookieChangeListenerOnCookieChangeParams.decode(new Decoder(arg1));
        }

        protected final void encode(Encoder arg4) {
            arg4 = arg4.getEncoderAtDataOffset(CookieChangeListenerOnCookieChangeParams.DEFAULT_STRUCT_INFO);
            arg4.encode(this.cookie, 8, false);
            arg4.encode(this.cause, 16);
        }
    }

    final class org.chromium.network.mojom.CookieChangeListener_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.CookieChangeListener$Proxy {
        org.chromium.network.mojom.CookieChangeListener_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }

        public void onCookieChange(CanonicalCookie arg4, int arg5) {
            CookieChangeListenerOnCookieChangeParams v0 = new CookieChangeListenerOnCookieChangeParams();
            v0.cookie = arg4;
            v0.cause = arg5;
            this.getProxyHandler().getMessageReceiver().accept(v0.serializeWithHeader(this.getProxyHandler().getCore(), new MessageHeader(0)));
        }
    }

    final class org.chromium.network.mojom.CookieChangeListener_Internal$Stub extends Stub {
        org.chromium.network.mojom.CookieChangeListener_Internal$Stub(Core arg1, CookieChangeListener arg2) {
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
                    if(v1_1 != 0) {
                        return 0;
                    }

                    CookieChangeListenerOnCookieChangeParams v4_2 = CookieChangeListenerOnCookieChangeParams.deserialize(v4_1.getPayload());
                    this.getImpl().onCookieChange(v4_2.cookie, v4_2.cause);
                    return 1;
                }

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(CookieChangeListener_Internal.MANAGER, v4_1);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), CookieChangeListener_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;
    private static final int ON_COOKIE_CHANGE_ORDINAL;

    static {
        CookieChangeListener_Internal.MANAGER = new org.chromium.network.mojom.CookieChangeListener_Internal$1();
    }

    CookieChangeListener_Internal() {
        super();
    }
}

