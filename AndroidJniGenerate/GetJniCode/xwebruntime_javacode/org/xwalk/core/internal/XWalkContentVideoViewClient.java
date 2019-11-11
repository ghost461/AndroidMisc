package org.xwalk.core.internal;

import android.view.View;
import android.widget.FrameLayout;
import org.chromium.content.browser.ContentVideoViewEmbedder;

public class XWalkContentVideoViewClient implements ContentVideoViewEmbedder {
    private XWalkContentsClient mContentsClient;
    private FrameLayout mCustomView;
    private XWalkViewInternal mView;

    public XWalkContentVideoViewClient(XWalkContentsClient arg1, XWalkViewInternal arg2, FrameLayout arg3) {
        super();
        this.mContentsClient = arg1;
        this.mView = arg2;
        this.mCustomView = arg3;
    }

    public void enterFullscreenVideo(View arg2, boolean arg3) {
        this.mView.setOverlayVideoMode(true);
        if(this.mCustomView == null) {
            return;
        }

        this.mCustomView.addView(arg2, 0);
    }

    public void exitFullscreenVideo() {
        this.mView.setOverlayVideoMode(false);
    }

    public void fullscreenVideoLoaded() {
    }

    @Deprecated public View getVideoLoadingProgressView() {
        return this.mContentsClient.getVideoLoadingProgressView();
    }

    public void setCustomView(FrameLayout arg1) {
        this.mCustomView = arg1;
    }

    public void setSystemUiVisibility(boolean arg1) {
    }
}

