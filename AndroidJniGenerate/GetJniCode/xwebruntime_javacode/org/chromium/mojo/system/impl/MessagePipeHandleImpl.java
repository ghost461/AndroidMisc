package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle$ReadFlags;
import org.chromium.mojo.system.MessagePipeHandle$WriteFlags;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.ResultAnd;

class MessagePipeHandleImpl extends HandleBase implements MessagePipeHandle {
    MessagePipeHandleImpl(CoreImpl arg1, int arg2) {
        super(arg1, arg2);
    }

    MessagePipeHandleImpl(HandleBase arg1) {
        super(arg1);
    }

    public Handle pass() {
        return this.pass();
    }

    public MessagePipeHandle pass() {
        return new MessagePipeHandleImpl(((HandleBase)this));
    }

    public ResultAnd readMessage(ReadFlags arg2) {
        return this.mCore.readMessage(this, arg2);
    }

    public void writeMessage(ByteBuffer arg2, List arg3, WriteFlags arg4) {
        this.mCore.writeMessage(this, arg2, arg3, arg4);
    }
}

