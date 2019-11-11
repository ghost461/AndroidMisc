package org.chromium.content_public.browser;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;

public abstract class WebContentsObserver {
    protected WeakReference mWebContents;

    protected WebContentsObserver() {
        super();
    }

    public WebContentsObserver(WebContents arg2) {
        super();
        this.mWebContents = new WeakReference(arg2);
        arg2.addObserver(this);
    }

    public void destroy() {
        if(this.mWebContents == null) {
            return;
        }

        Object v0 = this.mWebContents.get();
        this.mWebContents = null;
        if(v0 == null) {
            return;
        }

        ((WebContents)v0).removeObserver(this);
    }

    public void didAttachInterstitialPage() {
    }

    public void didChangeThemeColor(int arg1) {
    }

    public void didDetachInterstitialPage() {
    }

    public void didFailLoad(boolean arg1, int arg2, String arg3, String arg4) {
    }

    public void didFinishLoad(long arg1, String arg3, boolean arg4) {
    }

    public void didFinishNavigation(String arg1, boolean arg2, boolean arg3, boolean arg4, boolean arg5, boolean arg6, @Nullable Integer arg7, int arg8, String arg9, int arg10) {
    }

    public void didFirstVisuallyNonEmptyPaint() {
    }

    public void didStartLoading(String arg1) {
    }

    public void didStartNavigation(String arg1, boolean arg2, boolean arg3, boolean arg4) {
    }

    public void didStopLoading(String arg1) {
    }

    public void documentAvailableInMainFrame() {
    }

    public void documentLoadedInFrame(long arg1, boolean arg3) {
    }

    public void hasEffectivelyFullscreenVideoChange(boolean arg1) {
    }

    public void navigationEntriesDeleted() {
    }

    public void navigationEntryCommitted() {
    }

    public void renderProcessGone(boolean arg1) {
    }

    public void renderViewReady() {
    }

    public void titleWasSet(String arg1) {
    }

    public void wasHidden() {
    }

    public void wasShown() {
    }
}

