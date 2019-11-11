package org.chromium.mojo;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.Core$HandleSignalsState;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe$ConsumerHandle;
import org.chromium.mojo.system.DataPipe$ProducerHandle;
import org.chromium.mojo.system.DataPipe$ReadFlags;
import org.chromium.mojo.system.DataPipe$WriteFlags;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle$ReadMessageResult;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.SharedBufferHandle$DuplicateOptions;
import org.chromium.mojo.system.SharedBufferHandle$MapFlags;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;
import org.chromium.mojo.system.impl.CoreImpl;

public class HandleMock implements ConsumerHandle, ProducerHandle, MessagePipeHandle, SharedBufferHandle, UntypedHandle {
    public HandleMock() {
        super();
    }

    public ByteBuffer beginReadData(int arg1, ReadFlags arg2) {
        return null;
    }

    public ByteBuffer beginWriteData(int arg1, WriteFlags arg2) {
        return null;
    }

    public void close() {
    }

    public int discardData(int arg1, ReadFlags arg2) {
        return 0;
    }

    public SharedBufferHandle duplicate(DuplicateOptions arg1) {
        return null;
    }

    public void endReadData(int arg1) {
    }

    public void endWriteData(int arg1) {
    }

    public Core getCore() {
        return CoreImpl.getInstance();
    }

    public boolean isValid() {
        return 1;
    }

    public ByteBuffer map(long arg1, long arg3, MapFlags arg5) {
        return null;
    }

    public HandleMock pass() {
        return this;
    }

    public ConsumerHandle pass() {
        return this.pass();
    }

    public ProducerHandle pass() {
        return this.pass();
    }

    public Handle pass() {
        return this.pass();
    }

    public MessagePipeHandle pass() {
        return this.pass();
    }

    public SharedBufferHandle pass() {
        return this.pass();
    }

    public UntypedHandle pass() {
        return this.pass();
    }

    public HandleSignalsState querySignalsState() {
        return null;
    }

    public ResultAnd readData(ByteBuffer arg2, ReadFlags arg3) {
        return new ResultAnd(0, Integer.valueOf(0));
    }

    public ResultAnd readMessage(org.chromium.mojo.system.MessagePipeHandle$ReadFlags arg3) {
        return new ResultAnd(0, new ReadMessageResult());
    }

    public int releaseNativeHandle() {
        return 0;
    }

    public ConsumerHandle toDataPipeConsumerHandle() {
        return this;
    }

    public ProducerHandle toDataPipeProducerHandle() {
        return this;
    }

    public MessagePipeHandle toMessagePipeHandle() {
        return this;
    }

    public SharedBufferHandle toSharedBufferHandle() {
        return this;
    }

    public UntypedHandle toUntypedHandle() {
        return this;
    }

    public void unmap(ByteBuffer arg1) {
    }

    public ResultAnd writeData(ByteBuffer arg2, WriteFlags arg3) {
        return new ResultAnd(0, Integer.valueOf(0));
    }

    public void writeMessage(ByteBuffer arg1, List arg2, org.chromium.mojo.system.MessagePipeHandle$WriteFlags arg3) {
    }
}

