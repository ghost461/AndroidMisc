package org.chromium.mojo.system.impl;

import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

class UntypedHandleImpl extends HandleBase implements UntypedHandle {
    UntypedHandleImpl(CoreImpl arg1, int arg2) {
        super(arg1, arg2);
    }

    UntypedHandleImpl(HandleBase arg1) {
        super(arg1);
    }

    public Handle pass() {
        return this.pass();
    }

    public UntypedHandle pass() {
        return new UntypedHandleImpl(((HandleBase)this));
    }

    public ConsumerHandle toDataPipeConsumerHandle() {
        return new DataPipeConsumerHandleImpl(((HandleBase)this));
    }

    public ProducerHandle toDataPipeProducerHandle() {
        return new DataPipeProducerHandleImpl(((HandleBase)this));
    }

    public MessagePipeHandle toMessagePipeHandle() {
        return new MessagePipeHandleImpl(((HandleBase)this));
    }

    public SharedBufferHandle toSharedBufferHandle() {
        return new SharedBufferHandleImpl(((HandleBase)this));
    }
}

