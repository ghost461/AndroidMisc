package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ServiceMessage extends Message {
    private final MessageHeader mHeader;
    private Message mPayload;

    static {
    }

    ServiceMessage(Message arg2) {
        this(arg2, new MessageHeader(arg2));
    }

    public ServiceMessage(Message arg2, MessageHeader arg3) {
        super(arg2.getData(), arg2.getHandles());
        this.mHeader = arg3;
    }

    public ServiceMessage asServiceMessage() {
        return this;
    }

    public MessageHeader getHeader() {
        return this.mHeader;
    }

    public Message getPayload() {
        if(this.mPayload == null) {
            ByteBuffer v0 = this.getData().position(this.getHeader().getSize()).slice();
            v0.order(ByteOrder.LITTLE_ENDIAN);
            this.mPayload = new Message(v0, this.getHandles());
        }

        return this.mPayload;
    }

    void setRequestId(long arg3) {
        this.mHeader.setRequestId(this.getData(), arg3);
    }
}

