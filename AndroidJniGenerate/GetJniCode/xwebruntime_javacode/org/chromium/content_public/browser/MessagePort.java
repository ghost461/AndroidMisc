package org.chromium.content_public.browser;

import android.os.Handler;
import org.chromium.base.annotations.UsedByReflection;

@UsedByReflection(value="") public interface MessagePort {
    public interface MessageCallback {
        void onMessage(String arg1, MessagePort[] arg2);
    }

    void close();

    boolean isClosed();

    boolean isStarted();

    boolean isTransferred();

    void postMessage(String arg1, MessagePort[] arg2);

    void setMessageCallback(MessageCallback arg1, Handler arg2);
}

