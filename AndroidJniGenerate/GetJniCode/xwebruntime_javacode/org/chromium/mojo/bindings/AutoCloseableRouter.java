package org.chromium.mojo.bindings;

import java.util.concurrent.Executor;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;

class AutoCloseableRouter implements Router {
    private final Exception mAllocationException;
    private boolean mClosed;
    private final Executor mExecutor;
    private final Router mRouter;

    public AutoCloseableRouter(Core arg1, Router arg2) {
        super();
        this.mRouter = arg2;
        this.mExecutor = ExecutorFactory.getExecutorForCurrentThread(arg1);
        this.mAllocationException = new Exception("AutocloseableRouter allocated at:");
    }

    public boolean accept(Message arg2) {
        return this.mRouter.accept(arg2);
    }

    public boolean acceptWithResponder(Message arg2, MessageReceiver arg3) {
        return this.mRouter.acceptWithResponder(arg2, arg3);
    }

    public void close() {
        this.mRouter.close();
        this.mClosed = true;
    }

    protected void finalize() throws Throwable {
        if(!this.mClosed) {
            this.mExecutor.execute(new Runnable() {
                public void run() {
                    AutoCloseableRouter.this.close();
                }
            });
            throw new IllegalStateException("Warning: Router objects should be explicitly closed when no longer required otherwise you may leak handles.", this.mAllocationException);
        }

        super.finalize();
    }

    public Handle passHandle() {
        return this.passHandle();
    }

    public MessagePipeHandle passHandle() {
        return this.mRouter.passHandle();
    }

    public void setErrorHandler(ConnectionErrorHandler arg2) {
        this.mRouter.setErrorHandler(arg2);
    }

    public void setIncomingMessageReceiver(MessageReceiverWithResponder arg2) {
        this.mRouter.setIncomingMessageReceiver(arg2);
    }

    public void start() {
        this.mRouter.start();
    }
}

