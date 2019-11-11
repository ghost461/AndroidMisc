package org.chromium.content.browser.framehost;

public interface RenderFrameHostDelegate {
    void renderFrameCreated(RenderFrameHostImpl arg1);

    void renderFrameDeleted(RenderFrameHostImpl arg1);
}

