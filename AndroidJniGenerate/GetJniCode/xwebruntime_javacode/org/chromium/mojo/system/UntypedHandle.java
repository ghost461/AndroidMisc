package org.chromium.mojo.system;

public interface UntypedHandle extends Handle {
    UntypedHandle pass();

    ConsumerHandle toDataPipeConsumerHandle();

    ProducerHandle toDataPipeProducerHandle();

    MessagePipeHandle toMessagePipeHandle();

    SharedBufferHandle toSharedBufferHandle();
}

