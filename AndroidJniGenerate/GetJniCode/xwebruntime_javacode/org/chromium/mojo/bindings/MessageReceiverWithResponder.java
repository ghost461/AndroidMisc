package org.chromium.mojo.bindings;

public interface MessageReceiverWithResponder extends MessageReceiver {
    boolean acceptWithResponder(Message arg1, MessageReceiver arg2);
}

