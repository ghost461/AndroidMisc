package org.xwalk.core.internal.extension.api.presentation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import org.xwalk.core.internal.XWalkUIClientInternal$LoadStatusInternal;
import org.xwalk.core.internal.XWalkUIClientInternal;
import org.xwalk.core.internal.XWalkViewInternal;

public class XWalkPresentationContent {
    public interface PresentationDelegate {
        void onContentClosed(XWalkPresentationContent arg1);

        void onContentLoaded(XWalkPresentationContent arg1);
    }

    public final int INVALID_PRESENTATION_ID;
    private WeakReference mActivity;
    private XWalkViewInternal mContentView;
    private Context mContext;
    private PresentationDelegate mDelegate;
    private int mPresentationId;

    public XWalkPresentationContent(Context arg2, WeakReference arg3, PresentationDelegate arg4) {
        super();
        this.INVALID_PRESENTATION_ID = -1;
        this.mPresentationId = -1;
        this.mContext = arg2;
        this.mActivity = arg3;
        this.mDelegate = arg4;
    }

    static int access$000(XWalkPresentationContent arg0) {
        return arg0.mPresentationId;
    }

    static int access$002(XWalkPresentationContent arg0, int arg1) {
        arg0.mPresentationId = arg1;
        return arg1;
    }

    static void access$100(XWalkPresentationContent arg0) {
        arg0.onContentClosed();
    }

    static XWalkViewInternal access$200(XWalkPresentationContent arg0) {
        return arg0.mContentView;
    }

    static void access$300(XWalkPresentationContent arg0) {
        arg0.onContentLoaded();
    }

    public void close() {
        this.mContentView.onDestroy();
        this.mPresentationId = -1;
        this.mContentView = null;
    }

    public View getContentView() {
        return this.mContentView;
    }

    public int getPresentationId() {
        return this.mPresentationId;
    }

    public void load(String arg4) {
        Object v0 = this.mActivity.get();
        if(v0 == null) {
            return;
        }

        if(this.mContentView == null) {
            this.mContentView = new XWalkViewInternal(this.mContext, ((Activity)v0));
            this.mContentView.setUIClient(new XWalkUIClientInternal(this.mContentView) {
                public void onJavascriptCloseWindow(XWalkViewInternal arg2) {
                    XWalkPresentationContent.this.mPresentationId = -1;
                    XWalkPresentationContent.this.onContentClosed();
                }

                public void onPageLoadStarted(XWalkViewInternal arg2, String arg3) {
                    XWalkPresentationContent.this.mPresentationId = XWalkPresentationContent.this.mContentView.getContentID();
                    arg3 = "navigator.presentation.session = new navigator.presentation.PresentationSession(" + XWalkPresentationContent.this.mPresentationId;
                    arg2.evaluateJavascript(arg3 + ");", null);
                }

                public void onPageLoadStopped(XWalkViewInternal arg1, String arg2, LoadStatusInternal arg3) {
                    if(arg3 == LoadStatusInternal.FINISHED) {
                        XWalkPresentationContent.this.onContentLoaded();
                    }
                }
            });
        }

        this.mContentView.loadUrl(arg4);
    }

    private void onContentClosed() {
        if(this.mDelegate != null) {
            this.mDelegate.onContentClosed(this);
        }
    }

    private void onContentLoaded() {
        if(this.mDelegate != null) {
            this.mDelegate.onContentLoaded(this);
        }
    }

    public void onPause() {
        this.mContentView.pauseTimers();
        this.mContentView.onHide();
    }

    public void onResume() {
        this.mContentView.resumeTimers();
        this.mContentView.onShow();
    }
}

