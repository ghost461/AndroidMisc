package com.tencent.xweb.xprofile.processor;

import android.os.Message;
import com.tencent.xweb.xprofile.XProfileManager;

public class TracingConfigProcessor extends MessageProcessor {
    public TracingConfigProcessor() {
        super("TracingConfigProcessor");
    }

    public boolean handleMessage(Message arg3) {
        if(arg3.what == 2) {
            XProfileManager.getInstance().setConfig(arg3.obj);
            return 1;
        }

        return super.handleMessage(arg3);
    }
}

