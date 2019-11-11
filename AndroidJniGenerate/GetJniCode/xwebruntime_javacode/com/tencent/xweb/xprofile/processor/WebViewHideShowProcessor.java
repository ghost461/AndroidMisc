package com.tencent.xweb.xprofile.processor;

import android.os.Message;
import com.tencent.xweb.xprofile.XProfileManager;

public class WebViewHideShowProcessor extends MessageProcessor {
    private static final long INACTIVE_TIMEOUT = 240000;
    private int mShowingWebViewCount;

    public WebViewHideShowProcessor() {
        super("WebViewHideShowProcessor");
    }

    public boolean handleMessage(Message arg4) {
        if(arg4.what == 6) {
            this.handleWebViewHideOrShow(true);
            return 1;
        }

        if(arg4.what == 5) {
            this.handleWebViewHideOrShow(false);
            return 1;
        }

        if(arg4.what == 7) {
            XProfileManager.getInstance().notifyWebViewInactiveInProcess();
            return 1;
        }

        return super.handleMessage(arg4);
    }

    private void handleWebViewHideOrShow(boolean arg5) {
        if(arg5) {
            ++this.mShowingWebViewCount;
        }
        else {
            --this.mShowingWebViewCount;
        }

        int v0 = 7;
        if(this.mShowingWebViewCount == 0) {
            XProfileManager.getInstance().sendMessageDelayed(v0, null, 240000);
        }
        else {
            XProfileManager.getInstance().getHandler().removeMessages(v0);
        }
    }
}

