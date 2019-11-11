package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;
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
import org.chromium.mojo.system.Core;

class DownloadedTempFile_Internal {
    final class org.chromium.network.mojom.DownloadedTempFile_Internal$1 extends Manager {
        org.chromium.network.mojom.DownloadedTempFile_Internal$1() {
            super();
        }

        public Interface[] buildArray(int arg1) {
            return this.buildArray(arg1);
        }

        public DownloadedTempFile[] buildArray(int arg1) {
            return new DownloadedTempFile[arg1];
        }

        public Proxy buildProxy(Core arg1, MessageReceiverWithResponder arg2) {
            return this.buildProxy(arg1, arg2);
        }

        public org.chromium.network.mojom.DownloadedTempFile_Internal$Proxy buildProxy(Core arg2, MessageReceiverWithResponder arg3) {
            return new org.chromium.network.mojom.DownloadedTempFile_Internal$Proxy(arg2, arg3);
        }

        public Stub buildStub(Core arg1, Interface arg2) {
            return this.buildStub(arg1, ((DownloadedTempFile)arg2));
        }

        public org.chromium.network.mojom.DownloadedTempFile_Internal$Stub buildStub(Core arg2, DownloadedTempFile arg3) {
            return new org.chromium.network.mojom.DownloadedTempFile_Internal$Stub(arg2, arg3);
        }

        public String getName() {
            return "network::mojom::DownloadedTempFile";
        }

        public int getVersion() {
            return 0;
        }
    }

    final class org.chromium.network.mojom.DownloadedTempFile_Internal$Proxy extends AbstractProxy implements org.chromium.network.mojom.DownloadedTempFile$Proxy {
        org.chromium.network.mojom.DownloadedTempFile_Internal$Proxy(Core arg1, MessageReceiverWithResponder arg2) {
            super(arg1, arg2);
        }
    }

    final class org.chromium.network.mojom.DownloadedTempFile_Internal$Stub extends Stub {
        org.chromium.network.mojom.DownloadedTempFile_Internal$Stub(Core arg1, DownloadedTempFile arg2) {
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

                return InterfaceControlMessagesHelper.handleRunOrClosePipe(DownloadedTempFile_Internal.MANAGER, v4_1);
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

                return InterfaceControlMessagesHelper.handleRun(this.getCore(), DownloadedTempFile_Internal.MANAGER, v4_1, arg5);
            }
            catch(DeserializationException v4) {
                System.err.println(v4.toString());
                return 0;
            }
        }
    }

    public static final Manager MANAGER;

    static {
        DownloadedTempFile_Internal.MANAGER = new org.chromium.network.mojom.DownloadedTempFile_Internal$1();
    }

    DownloadedTempFile_Internal() {
        super();
    }
}

