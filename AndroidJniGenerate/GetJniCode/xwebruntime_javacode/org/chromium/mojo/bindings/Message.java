package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.util.List;

public class Message {
    private final ByteBuffer mBuffer;
    private final List mHandles;
    private ServiceMessage mWithHeader;

    public Message(ByteBuffer arg1, List arg2) {
        super();
        this.mBuffer = arg1;
        this.mHandles = arg2;
    }

    public ServiceMessage asServiceMessage() {
        if(this.mWithHeader == null) {
            this.mWithHeader = new ServiceMessage(this);
        }

        return this.mWithHeader;
    }

    public ByteBuffer getData() {
        return this.mBuffer;
    }

    public List getHandles() {
        return this.mHandles;
    }
}

