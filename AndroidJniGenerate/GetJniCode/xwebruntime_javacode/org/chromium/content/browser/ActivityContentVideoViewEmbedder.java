package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build$VERSION;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;

public class ActivityContentVideoViewEmbedder implements ContentVideoViewEmbedder {
    private final Activity mActivity;
    private View mView;

    public ActivityContentVideoViewEmbedder(Activity arg1) {
        super();
        this.mActivity = arg1;
    }

    public void enterFullscreenVideo(View arg4, boolean arg5) {
        this.mActivity.getWindow().getDecorView().addView(arg4, 0, new FrameLayout$LayoutParams(-1, -1, 17));
        this.setSystemUiVisibility(true);
        this.mView = arg4;
    }

    public void exitFullscreenVideo() {
        this.mActivity.getWindow().getDecorView().removeView(this.mView);
        this.setSystemUiVisibility(false);
        this.mView = null;
    }

    public void fullscreenVideoLoaded() {
    }

    @SuppressLint(value={"InlinedApi"}) public void setSystemUiVisibility(boolean arg4) {
        View v0 = this.mActivity.getWindow().getDecorView();
        int v1 = 0x400;
        if(arg4) {
            this.mActivity.getWindow().setFlags(v1, v1);
        }
        else {
            this.mActivity.getWindow().clearFlags(v1);
        }

        if(Build$VERSION.SDK_INT < 19) {
            return;
        }

        v1 = v0.getSystemUiVisibility();
        int v4 = arg4 ? v1 | 0x1606 : v1 & 0xFFFFE9F9;
        v0.setSystemUiVisibility(v4);
    }
}

