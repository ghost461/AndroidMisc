package org.chromium.mojo.bindings;

public interface Router extends HandleOwner, MessageReceiverWithResponder {
    void setErrorHandler(ConnectionErrorHandler arg1);

    void setIncomingMessageReceiver(MessageReceiverWithResponder arg1);

    void start();
}

