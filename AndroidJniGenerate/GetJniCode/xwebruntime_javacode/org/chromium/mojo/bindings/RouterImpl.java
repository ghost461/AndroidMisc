package org.chromium.mojo.bindings;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.Watcher;

@SuppressLint(value={"UseSparseArrays"}) public class RouterImpl implements Router {
    class HandleIncomingMessageThunk implements MessageReceiver {
        HandleIncomingMessageThunk(RouterImpl arg1, org.chromium.mojo.bindings.RouterImpl$1 arg2) {
            this(arg1);
        }

        private HandleIncomingMessageThunk(RouterImpl arg1) {
            RouterImpl.this = arg1;
            super();
        }

        public boolean accept(Message arg2) {
            return RouterImpl.this.handleIncomingMessage(arg2);
        }

        public void close() {
            RouterImpl.this.handleConnectorClose();
        }
    }

    class ResponderThunk implements MessageReceiver {
        private boolean mAcceptWasInvoked;

        ResponderThunk(RouterImpl arg1) {
            RouterImpl.this = arg1;
            super();
        }

        public boolean accept(Message arg2) {
            this.mAcceptWasInvoked = true;
            return RouterImpl.this.accept(arg2);
        }

        public void close() {
            RouterImpl.this.close();
        }

        protected void finalize() throws Throwable {
            if(!this.mAcceptWasInvoked) {
                RouterImpl.this.closeOnHandleThread();
            }

            super.finalize();
        }
    }

    private final Connector mConnector;
    private final Executor mExecutor;
    private MessageReceiverWithResponder mIncomingMessageReceiver;
    private long mNextRequestId;
    private Map mResponders;

    static {
    }

    public RouterImpl(MessagePipeHandle arg2) {
        this(arg2, BindingsHelper.getWatcherForHandle(((Handle)arg2)));
    }

    public RouterImpl(MessagePipeHandle arg3, Watcher arg4) {
        super();
        this.mNextRequestId = 1;
        this.mResponders = new HashMap();
        this.mConnector = new Connector(arg3, arg4);
        org.chromium.mojo.bindings.RouterImpl$1 v1 = null;
        this.mConnector.setIncomingMessageReceiver(new HandleIncomingMessageThunk(this, v1));
        Core v3 = arg3.getCore();
        this.mExecutor = v3 != null ? ExecutorFactory.getExecutorForCurrentThread(v3) : ((Executor)v1);
    }

    public boolean accept(Message arg2) {
        return this.mConnector.accept(arg2);
    }

    public boolean acceptWithResponder(Message arg8, MessageReceiver arg9) {
        ServiceMessage v8 = arg8.asServiceMessage();
        long v0 = this.mNextRequestId;
        long v2 = 1;
        this.mNextRequestId = v0 + v2;
        if(v0 == 0) {
            v0 = this.mNextRequestId;
            this.mNextRequestId = v0 + v2;
        }

        if(this.mResponders.containsKey(Long.valueOf(v0))) {
            throw new IllegalStateException("Unable to find a new request identifier.");
        }

        v8.setRequestId(v0);
        if(!this.mConnector.accept(((Message)v8))) {
            return 0;
        }

        this.mResponders.put(Long.valueOf(v0), arg9);
        return 1;
    }

    static boolean access$000(RouterImpl arg0, Message arg1) {
        return arg0.handleIncomingMessage(arg1);
    }

    static void access$100(RouterImpl arg0) {
        arg0.handleConnectorClose();
    }

    static void access$200(RouterImpl arg0) {
        arg0.closeOnHandleThread();
    }

    public void close() {
        this.mConnector.close();
    }

    private void closeOnHandleThread() {
        if(this.mExecutor != null) {
            this.mExecutor.execute(new Runnable() {
                public void run() {
                    RouterImpl.this.close();
                }
            });
        }
    }

    private void handleConnectorClose() {
        if(this.mIncomingMessageReceiver != null) {
            this.mIncomingMessageReceiver.close();
        }
    }

    private boolean handleIncomingMessage(Message arg6) {
        MessageHeader v0 = arg6.asServiceMessage().getHeader();
        if(v0.hasFlag(1)) {
            if(this.mIncomingMessageReceiver != null) {
                return this.mIncomingMessageReceiver.acceptWithResponder(arg6, new ResponderThunk(this));
            }

            this.close();
            return 0;
        }

        if(v0.hasFlag(2)) {
            long v0_1 = v0.getRequestId();
            Object v3 = this.mResponders.get(Long.valueOf(v0_1));
            if(v3 == null) {
                return 0;
            }

            this.mResponders.remove(Long.valueOf(v0_1));
            return ((MessageReceiver)v3).accept(arg6);
        }

        if(this.mIncomingMessageReceiver != null) {
            return this.mIncomingMessageReceiver.accept(arg6);
        }

        return 0;
    }

    public Handle passHandle() {
        return this.passHandle();
    }

    public MessagePipeHandle passHandle() {
        return this.mConnector.passHandle();
    }

    public void setErrorHandler(ConnectionErrorHandler arg2) {
        this.mConnector.setErrorHandler(arg2);
    }

    public void setIncomingMessageReceiver(MessageReceiverWithResponder arg1) {
        this.mIncomingMessageReceiver = arg1;
    }

    public void start() {
        this.mConnector.start();
    }
}

