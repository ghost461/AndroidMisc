package org.chromium.components.web_contents_delegate_android;

import android.view.KeyEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ContentVideoViewEmbedder;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;

@JNINamespace(value="web_contents_delegate_android") public class WebContentsDelegateAndroid {
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_LOG = 1;
    public static final int LOG_LEVEL_TIP = 0;
    public static final int LOG_LEVEL_WARNING = 2;
    private int mMostRecentProgress;

    public WebContentsDelegateAndroid() {
        super();
        this.mMostRecentProgress = 100;
    }

    @CalledByNative public void activateContents() {
    }

    @CalledByNative public boolean addMessageToConsole(int arg1, String arg2, int arg3, String arg4) {
        return 0;
    }

    @CalledByNative public void closeContents() {
    }

    @CalledByNative public boolean controlsResizeView() {
        return 0;
    }

    @CalledByNative public int getBottomControlsHeight() {
        return 0;
    }

    @CalledByNative public ContentVideoViewEmbedder getContentVideoViewEmbedder() {
        return null;
    }

    public int getMostRecentProgress() {
        return this.mMostRecentProgress;
    }

    @CalledByNative public int getTopControlsHeight() {
        return 0;
    }

    @CalledByNative public void handleKeyboardEvent(KeyEvent arg1) {
    }

    @CalledByNative public boolean isFullscreenForTabOrPending() {
        return 0;
    }

    @CalledByNative public void loadingStateChanged(boolean arg1) {
    }

    @CalledByNative public void navigationStateChanged(int arg1) {
    }

    @CalledByNative private final void notifyLoadProgressChanged(double arg3) {
        this.mMostRecentProgress = ((int)(arg3 * 100));
        this.onLoadProgressChanged(this.mMostRecentProgress);
    }

    public void onLoadProgressChanged(int arg1) {
    }

    @CalledByNative public void onUpdateUrl(String arg1) {
    }

    @CalledByNative public void openNewTab(String arg1, String arg2, ResourceRequestBody arg3, int arg4, boolean arg5) {
    }

    @CalledByNative public void rendererResponsive() {
    }

    @CalledByNative public void rendererUnresponsive() {
    }

    @CalledByNative public boolean shouldBlockMediaRequest(String arg1) {
        return 0;
    }

    @CalledByNative public boolean shouldCreateWebContents(String arg1) {
        return 1;
    }

    @CalledByNative public void showRepostFormWarningDialog() {
    }

    @CalledByNative public boolean takeFocus(boolean arg1) {
        return 0;
    }

    @CalledByNative public void toggleFullscreenModeForTab(boolean arg1) {
    }

    @CalledByNative public void visibleSSLStateChanged() {
    }

    @CalledByNative public void webContentsCreated(WebContents arg1, long arg2, long arg4, String arg6, String arg7, WebContents arg8) {
    }
}

