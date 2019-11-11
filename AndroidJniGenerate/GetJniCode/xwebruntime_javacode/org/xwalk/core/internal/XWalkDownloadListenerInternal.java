package org.xwalk.core.internal;

import android.content.Context;

@XWalkAPI(createExternally=true) public abstract class XWalkDownloadListenerInternal {
    @XWalkAPI public XWalkDownloadListenerInternal(Context arg1) {
        super();
    }

    @XWalkAPI public abstract void onDownloadStart(String arg1, String arg2, String arg3, String arg4, long arg5);
}

