package org.chromium.mojo.system;

import java.nio.ByteBuffer;
import java.util.List;

public class InvalidHandle implements ConsumerHandle, ProducerHandle, MessagePipeHandle, SharedBufferHandle, UntypedHandle {
    public static final InvalidHandle INSTANCE;

    static {
        InvalidHandle.INSTANCE = new InvalidHandle();
    }

    private InvalidHandle() {
        super();
    }

    public ByteBuffer beginReadData(int arg1, ReadFlags arg2) {
        throw new MojoException(3);
    }

    public ByteBuffer beginWriteData(int arg1, WriteFlags arg2) {
        throw new MojoException(3);
    }

    public void close() {
    }

    public int discardData(int arg1, ReadFlags arg2) {
        throw new MojoException(3);
    }

    public SharedBufferHandle duplicate(DuplicateOptions arg2) {
        throw new MojoException(3);
    }

    public void endReadData(int arg2) {
        throw new MojoException(3);
    }

    public void endWriteData(int arg2) {
        throw new MojoException(3);
    }

    public Core getCore() {
        return null;
    }

    public boolean isValid() {
        return 0;
    }

    public ByteBuffer map(long arg1, long arg3, MapFlags arg5) {
        throw new MojoException(3);
    }

    public ConsumerHandle pass() {
        return this.pass();
    }

    public InvalidHandle pass() {
        return this;
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
        throw new MojoException(3);
    }

    public ResultAnd readData(ByteBuffer arg1, ReadFlags arg2) {
        throw new MojoException(3);
    }

    public ResultAnd readMessage(org.chromium.mojo.system.MessagePipeHandle$ReadFlags arg2) {
        throw new MojoException(3);
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

    public void unmap(ByteBuffer arg2) {
        throw new MojoException(3);
    }

    public ResultAnd writeData(ByteBuffer arg1, WriteFlags arg2) {
        throw new MojoException(3);
    }

    public void writeMessage(ByteBuffer arg1, List arg2, org.chromium.mojo.system.MessagePipeHandle$WriteFlags arg3) {
        throw new MojoException(3);
    }
}

