package com.tencent.xweb.xprofile.processor;

import android.os.Message;
import org.xwalk.core.internal.Log;

public abstract class MessageProcessor implements IMessageProcessor {
    private String mName;

    public MessageProcessor(String arg1) {
        super();
        this.mName = arg1;
    }

    public boolean handleMessage(Message arg1) {
        return 0;
    }

    void logDebug(String arg2) {
        Log.d(this.mName, arg2);
    }

    void logError(String arg2) {
        Log.e(this.mName, arg2);
    }
}

