package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.Core$HandleSignals;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle$ReadFlags;
import org.chromium.mojo.system.MessagePipeHandle$ReadMessageResult;
import org.chromium.mojo.system.MessagePipeHandle$WriteFlags;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.Watcher$Callback;
import org.chromium.mojo.system.Watcher;

public class Connector implements HandleOwner, MessageReceiver {
    class org.chromium.mojo.bindings.Connector$1 {
    }

    class WatcherCallback implements Callback {
        WatcherCallback(Connector arg1, org.chromium.mojo.bindings.Connector$1 arg2) {
            this(arg1);
        }

        private WatcherCallback(Connector arg1) {
            Connector.this = arg1;
            super();
        }

        public void onResult(int arg2) {
            Connector.this.onWatcherResult(arg2);
        }
    }

    private ConnectionErrorHandler mErrorHandler;
    private MessageReceiver mIncomingMessageReceiver;
    private final MessagePipeHandle mMessagePipeHandle;
    private final Watcher mWatcher;
    private final WatcherCallback mWatcherCallback;

    static {
    }

    public Connector(MessagePipeHandle arg2) {
        this(arg2, BindingsHelper.getWatcherForHandle(((Handle)arg2)));
    }

    public Connector(MessagePipeHandle arg3, Watcher arg4) {
        super();
        this.mWatcherCallback = new WatcherCallback(this, null);
        this.mMessagePipeHandle = arg3;
        this.mWatcher = arg4;
    }

    public boolean accept(Message arg4) {
        try {
            this.mMessagePipeHandle.writeMessage(arg4.getData(), arg4.getHandles(), WriteFlags.NONE);
            return 1;
        }
        catch(MojoException v4) {
            this.onError(v4);
            return 0;
        }
    }

    static void access$100(Connector arg0, int arg1) {
        arg0.onWatcherResult(arg1);
    }

    private void cancelIfActive() {
        this.mWatcher.cancel();
        this.mWatcher.destroy();
    }

    public void close() {
        this.cancelIfActive();
        this.mMessagePipeHandle.close();
        if(this.mIncomingMessageReceiver != null) {
            MessageReceiver v0 = this.mIncomingMessageReceiver;
            this.mIncomingMessageReceiver = null;
            v0.close();
        }
    }

    private void onError(MojoException arg2) {
        this.close();
        if(this.mErrorHandler != null) {
            this.mErrorHandler.onConnectionError(arg2);
        }
    }

    private void onWatcherResult(int arg2) {
        if(arg2 == 0) {
            this.readOutstandingMessages();
        }
        else {
            this.onError(new MojoException(arg2));
        }
    }

    public MessagePipeHandle passHandle() {
        this.cancelIfActive();
        MessagePipeHandle v0 = this.mMessagePipeHandle.pass();
        if(this.mIncomingMessageReceiver != null) {
            this.mIncomingMessageReceiver.close();
        }

        return v0;
    }

    public Handle passHandle() {
        return this.passHandle();
    }

    static ResultAnd readAndDispatchMessage(MessagePipeHandle arg3, MessageReceiver arg4) {
        ResultAnd v3 = arg3.readMessage(ReadFlags.NONE);
        if(v3.getMojoResult() != 0) {
            return new ResultAnd(v3.getMojoResult(), Boolean.valueOf(false));
        }

        Object v0 = v3.getValue();
        if(arg4 != null) {
            return new ResultAnd(v3.getMojoResult(), Boolean.valueOf(arg4.accept(new Message(ByteBuffer.wrap(((ReadMessageResult)v0).mData), ((ReadMessageResult)v0).mHandles))));
        }

        return new ResultAnd(v3.getMojoResult(), Boolean.valueOf(false));
    }

    private void readOutstandingMessages() {
        ResultAnd v0_1;
        try {
            do {
            label_0:
                v0_1 = Connector.readAndDispatchMessage(this.mMessagePipeHandle, this.mIncomingMessageReceiver);
                break;
            }
            while(true);
        }
        catch(MojoException v0) {
            goto label_15;
        }

        if(v0_1.getValue().booleanValue()) {
            goto label_0;
        }

        if(v0_1.getMojoResult() != 17) {
            this.onError(new MojoException(v0_1.getMojoResult()));
        }

        return;
    label_15:
        this.onError(v0);
    }

    public void setErrorHandler(ConnectionErrorHandler arg1) {
        this.mErrorHandler = arg1;
    }

    public void setIncomingMessageReceiver(MessageReceiver arg1) {
        this.mIncomingMessageReceiver = arg1;
    }

    public void start() {
        this.mWatcher.start(this.mMessagePipeHandle, HandleSignals.READABLE, this.mWatcherCallback);
    }
}

