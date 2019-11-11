package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ReadFlags;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.ResultAnd;

class DataPipeConsumerHandleImpl extends HandleBase implements ConsumerHandle {
    DataPipeConsumerHandleImpl(CoreImpl arg1, int arg2) {
        super(arg1, arg2);
    }

    DataPipeConsumerHandleImpl(HandleBase arg1) {
        super(arg1);
    }

    public ByteBuffer beginReadData(int arg2, ReadFlags arg3) {
        return this.mCore.beginReadData(this, arg2, arg3);
    }

    public int discardData(int arg2, ReadFlags arg3) {
        return this.mCore.discardData(this, arg2, arg3);
    }

    public void endReadData(int arg2) {
        this.mCore.endReadData(this, arg2);
    }

    public ConsumerHandle pass() {
        return new DataPipeConsumerHandleImpl(((HandleBase)this));
    }

    public Handle pass() {
        return this.pass();
    }

    public ResultAnd readData(ByteBuffer arg2, ReadFlags arg3) {
        return this.mCore.readData(this, arg2, arg3);
    }
}

