package com.tencent.xweb.xprofile.processor;

import android.os.Message;
import com.tencent.xweb.xprofile.XProfileManager;

public class PageLoadProcessor extends MessageProcessor {
    public PageLoadProcessor() {
        super("PageLoadProcessor");
    }

    public boolean handleMessage(Message arg4) {
        Object v4;
        if(arg4.what == 3) {
            v4 = arg4.obj;
            XProfileManager.getInstance().notifyPageLoadStarted(((String)v4));
            this.logDebug("page load started: " + (((String)v4)));
            return 1;
        }

        if(arg4.what == 4) {
            v4 = arg4.obj;
            XProfileManager.getInstance().notifyPageLoadFinished(((String)v4));
            this.logDebug("page load finished: " + (((String)v4)));
            return 1;
        }

        return super.handleMessage(arg4);
    }
}

