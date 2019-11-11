package org.chromium.content_public.browser;

import org.chromium.content.browser.framehost.RenderFrameHostDelegate;
import org.chromium.content.browser.framehost.RenderFrameHostImpl;

public class WebContentsStatics {
    public WebContentsStatics() {
        super();
    }

    public static WebContents fromRenderFrameHost(RenderFrameHost arg1) {
        RenderFrameHostDelegate v1 = ((RenderFrameHostImpl)arg1).getRenderFrameHostDelegate();
        if(v1 != null) {
            if(!(v1 instanceof WebContents)) {
            }
            else {
                return ((WebContents)v1);
            }
        }

        return null;
    }
}

