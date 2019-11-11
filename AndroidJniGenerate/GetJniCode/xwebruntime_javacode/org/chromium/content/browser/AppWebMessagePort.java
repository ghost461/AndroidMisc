package org.chromium.content.browser;

import android.os.Handler;
import android.os.Looper;
import org.chromium.base.Log;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.blink.mojom.CloneableMessage;
import org.chromium.blink.mojom.SerializedArrayBufferContents;
import org.chromium.blink.mojom.SerializedBlob;
import org.chromium.blink.mojom.TransferableMessage;
import org.chromium.content_public.browser.MessagePort$MessageCallback;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.mojo.bindings.Connector;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.MessageHeader;
import org.chromium.mojo.bindings.MessageReceiver;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle$CreateOptions;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.Pair;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo_base.BigBufferUtil;
import org.chromium.skia.mojom.Bitmap;

@JNINamespace(value="content") public class AppWebMessagePort implements MessagePort {
    class org.chromium.content.browser.AppWebMessagePort$1 {
    }

    class MessageHandler extends Handler implements MessageReceiver {
        class MessagePortMessage {
            public byte[] encodedMessage;
            public AppWebMessagePort[] ports;

            MessagePortMessage(org.chromium.content.browser.AppWebMessagePort$1 arg1) {
                this();
            }

            private MessagePortMessage() {
                super();
            }
        }

        private static final int MESSAGE_RECEIVED = 1;
        private final MessageCallback mMessageCallback;

        public MessageHandler(Looper arg1, MessageCallback arg2) {
            super(arg1);
            this.mMessageCallback = arg2;
        }

        public boolean accept(Message arg8) {
            org.chromium.content.browser.AppWebMessagePort$1 v5;
            try {
                TransferableMessage v8_1 = TransferableMessage.deserialize(arg8.asServiceMessage().getPayload());
                AppWebMessagePort[] v2 = new AppWebMessagePort[v8_1.ports.length];
                int v3;
                for(v3 = 0; true; ++v3) {
                    v5 = null;
                    if(v3 >= v2.length) {
                        break;
                    }

                    v2[v3] = new AppWebMessagePort(v8_1.ports[v3], v5);
                }

                MessagePortMessage v3_1 = new MessagePortMessage(v5);
                v3_1.encodedMessage = BigBufferUtil.getBytesFromBigBuffer(v8_1.message.encodedMessage);
                v3_1.ports = v2;
                this.sendMessage(this.obtainMessage(1, v3_1));
                return 1;
            }
            catch(DeserializationException v8) {
                Log.w("AppWebMessagePort", "Error deserializing message", new Object[]{v8});
                return 0;
            }
        }

        public void close() {
        }

        public void handleMessage(android.os.Message arg3) {
            if(arg3.what == 1) {
                Object v3 = arg3.obj;
                String v0 = AppWebMessagePort.nativeDecodeStringMessage(((MessagePortMessage)v3).encodedMessage);
                if(v0 == null) {
                    Log.w("AppWebMessagePort", "Undecodable message received, dropping message", new Object[0]);
                    return;
                }

                this.mMessageCallback.onMessage(v0, ((MessagePortMessage)v3).ports);
                return;
            }

            throw new IllegalStateException("undefined message");
        }
    }

    private static final MessageHeader MESSAGE_HEADER = null;
    private static final String TAG = "AppWebMessagePort";
    private boolean mClosed;
    private Connector mConnector;
    private Core mMojoCore;
    private boolean mStarted;
    private boolean mTransferred;
    private boolean mWatching;

    static {
        AppWebMessagePort.MESSAGE_HEADER = new MessageHeader(0);
    }

    private AppWebMessagePort(MessagePipeHandle arg2) {
        super();
        this.mMojoCore = arg2.getCore();
        this.mConnector = new Connector(arg2);
    }

    AppWebMessagePort(MessagePipeHandle arg1, org.chromium.content.browser.AppWebMessagePort$1 arg2) {
        this(arg1);
    }

    static String access$000(byte[] arg0) {
        return AppWebMessagePort.nativeDecodeStringMessage(arg0);
    }

    public void close() {
        if(this.mTransferred) {
            throw new IllegalStateException("Port is already transferred");
        }

        if(this.mClosed) {
            return;
        }

        this.mClosed = true;
        this.mConnector.close();
        this.mConnector = null;
    }

    public static AppWebMessagePort[] createPair() {
        Pair v0 = CoreImpl.getInstance().createMessagePipe(new CreateOptions());
        return new AppWebMessagePort[]{new AppWebMessagePort(v0.first), new AppWebMessagePort(v0.second)};
    }

    public boolean isClosed() {
        return this.mClosed;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public boolean isTransferred() {
        return this.mTransferred;
    }

    private static native String nativeDecodeStringMessage(byte[] arg0) {
    }

    private static native byte[] nativeEncodeStringMessage(String arg0) {
    }

    private MessagePipeHandle passHandle() {
        this.mTransferred = true;
        MessagePipeHandle v0 = this.mConnector.passHandle();
        this.mConnector = null;
        return v0;
    }

    public void postMessage(String arg7, MessagePort[] arg8) throws IllegalStateException {
        if(!this.isClosed()) {
            if(this.isTransferred()) {
            }
            else {
                int v1 = arg8 == null ? 0 : arg8.length;
                MessagePipeHandle[] v1_1 = new MessagePipeHandle[v1];
                if(arg8 != null) {
                    int v2 = arg8.length;
                    int v3 = 0;
                    while(true) {
                        if(v3 < v2) {
                            MessagePort v4 = arg8[v3];
                            if(v4.equals(this)) {
                                throw new IllegalStateException("Source port cannot be transferred");
                            }
                            else {
                                if(!v4.isClosed()) {
                                    if(v4.isTransferred()) {
                                    }
                                    else if(v4.isStarted()) {
                                        throw new IllegalStateException("Port is already started");
                                    }
                                    else {
                                        ++v3;
                                        continue;
                                    }
                                }

                                break;
                            }
                        }
                        else {
                            goto label_39;
                        }

                        goto label_47;
                    }

                    throw new IllegalStateException("Port is already closed or transferred");
                label_39:
                    for(v2 = 0; v2 < arg8.length; ++v2) {
                        v1_1[v2] = arg8[v2].passHandle();
                    }
                }

            label_47:
                this.mStarted = true;
                TransferableMessage v8 = new TransferableMessage();
                v8.message = new CloneableMessage();
                v8.message.encodedMessage = BigBufferUtil.createBigBufferFromBytes(AppWebMessagePort.nativeEncodeStringMessage(arg7));
                v8.message.blobs = new SerializedBlob[0];
                v8.arrayBufferContentsArray = new SerializedArrayBufferContents[0];
                v8.imageBitmapContentsArray = new Bitmap[0];
                v8.ports = v1_1;
                this.mConnector.accept(v8.serializeWithHeader(this.mMojoCore, AppWebMessagePort.MESSAGE_HEADER));
                return;
            }
        }

        throw new IllegalStateException("Port is already closed or transferred");
    }

    @CalledByNative private int releaseNativeHandle() {
        return this.passHandle().releaseNativeHandle();
    }

    public void setMessageCallback(MessageCallback arg4, Handler arg5) {
        if(!this.isClosed()) {
            if(this.isTransferred()) {
            }
            else {
                this.mStarted = true;
                if(arg4 == null) {
                    this.mConnector.setIncomingMessageReceiver(null);
                }
                else {
                    Connector v1 = this.mConnector;
                    Looper v5 = arg5 == null ? Looper.getMainLooper() : arg5.getLooper();
                    v1.setIncomingMessageReceiver(new MessageHandler(v5, arg4));
                }

                if(!this.mWatching) {
                    this.mConnector.start();
                    this.mWatching = true;
                }

                return;
            }
        }

        throw new IllegalStateException("Port is already closed or transferred");
    }
}

