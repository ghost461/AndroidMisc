package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.DataPipe$WriteFlags;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.ResultAnd;

class DataPipeProducerHandleImpl extends HandleBase implements ProducerHandle {
    DataPipeProducerHandleImpl(CoreImpl arg1, int arg2) {
        super(arg1, arg2);
    }

    DataPipeProducerHandleImpl(HandleBase arg1) {
        super(arg1);
    }

    public ByteBuffer beginWriteData(int arg2, WriteFlags arg3) {
        return this.mCore.beginWriteData(this, arg2, arg3);
    }

    public void endWriteData(int arg2) {
        this.mCore.endWriteData(this, arg2);
    }

    public ProducerHandle pass() {
        return new DataPipeProducerHandleImpl(((HandleBase)this));
    }

    public Handle pass() {
        return this.pass();
    }

    public ResultAnd writeData(ByteBuffer arg2, WriteFlags arg3) {
        return this.mCore.writeData(this, arg2, arg3);
    }
}

