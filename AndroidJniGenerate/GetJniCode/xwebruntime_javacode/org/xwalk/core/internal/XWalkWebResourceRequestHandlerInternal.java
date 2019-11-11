package org.xwalk.core.internal;

import android.net.Uri;
import java.util.Map;

@XWalkAPI(createInternally=true, impl=XWalkWebResourceRequestInternal.class) public class XWalkWebResourceRequestHandlerInternal implements XWalkWebResourceRequestInternal {
    private final WebResourceRequestInner mRequest;

    XWalkWebResourceRequestHandlerInternal(WebResourceRequestInner arg1) {
        super();
        this.mRequest = arg1;
    }

    XWalkWebResourceRequestHandlerInternal() {
        super();
        this.mRequest = null;
    }

    @XWalkAPI public String getMethod() {
        return this.mRequest.method;
    }

    @XWalkAPI public Map getRequestHeaders() {
        return this.mRequest.requestHeaders;
    }

    @XWalkAPI public Uri getUrl() {
        return Uri.parse(this.mRequest.url);
    }

    @XWalkAPI public boolean hasGesture() {
        return this.mRequest.hasUserGesture;
    }

    @XWalkAPI public boolean isForMainFrame() {
        return this.mRequest.isMainFrame;
    }
}

