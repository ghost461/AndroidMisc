package org.xwalk.core.internal;

import org.chromium.base.ThreadUtils;

@XWalkAPI(createInternally=true, impl=XWalkJavascriptResultInternal.class) public class XWalkJavascriptResultHandlerInternal implements XWalkJavascriptResultInternal {
    private XWalkContentsClientBridge mBridge;
    private final int mId;

    XWalkJavascriptResultHandlerInternal(XWalkContentsClientBridge arg1, int arg2) {
        super();
        this.mBridge = arg1;
        this.mId = arg2;
    }

    XWalkJavascriptResultHandlerInternal() {
        super();
        this.mBridge = null;
        this.mId = -1;
    }

    static XWalkContentsClientBridge access$000(XWalkJavascriptResultHandlerInternal arg0) {
        return arg0.mBridge;
    }

    static XWalkContentsClientBridge access$002(XWalkJavascriptResultHandlerInternal arg0, XWalkContentsClientBridge arg1) {
        arg0.mBridge = arg1;
        return arg1;
    }

    static int access$100(XWalkJavascriptResultHandlerInternal arg0) {
        return arg0.mId;
    }

    @XWalkAPI public void cancel() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                if(XWalkJavascriptResultHandlerInternal.this.mBridge != null) {
                    XWalkJavascriptResultHandlerInternal.this.mBridge.cancelJsResult(XWalkJavascriptResultHandlerInternal.this.mId);
                }

                XWalkJavascriptResultHandlerInternal.this.mBridge = null;
            }
        });
    }

    @XWalkAPI public void confirm() {
        this.confirmWithResult(null);
    }

    @XWalkAPI public void confirmWithResult(String arg2) {
        ThreadUtils.runOnUiThread(new Runnable(arg2) {
            public void run() {
                if(XWalkJavascriptResultHandlerInternal.this.mBridge != null) {
                    XWalkJavascriptResultHandlerInternal.this.mBridge.confirmJsResult(XWalkJavascriptResultHandlerInternal.this.mId, this.val$promptResult);
                }

                XWalkJavascriptResultHandlerInternal.this.mBridge = null;
            }
        });
    }
}

