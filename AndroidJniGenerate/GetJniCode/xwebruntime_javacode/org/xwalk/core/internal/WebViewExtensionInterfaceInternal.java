package org.xwalk.core.internal;

import java.util.List;

public abstract class WebViewExtensionInterfaceInternal {
    private XWalkContentsClientBridge mBridge;

    WebViewExtensionInterfaceInternal() {
        super();
        this.mBridge = null;
    }

    WebViewExtensionInterfaceInternal(XWalkContentsClientBridge arg1) {
        super();
        this.mBridge = arg1;
    }

    public abstract int getHostByName(String arg1, List arg2);

    public abstract Object onMiscCallBack(String arg1, Object[] arg2);
}

