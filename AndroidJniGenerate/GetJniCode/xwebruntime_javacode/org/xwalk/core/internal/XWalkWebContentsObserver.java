package org.xwalk.core.internal;

import java.lang.ref.WeakReference;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsObserver;

public class XWalkWebContentsObserver extends WebContentsObserver {
    private final WeakReference mAwContents;
    private final WeakReference mAwContentsClient;
    private boolean mCommittedNavigation;
    private String mLastDidFinishLoadUrl;

    public XWalkWebContentsObserver(WebContents arg1, XWalkContent arg2, XWalkContentsClient arg3) {
        super(arg1);
        this.mAwContents = new WeakReference(arg2);
        this.mAwContentsClient = new WeakReference(arg3);
    }

    static WeakReference access$000(XWalkWebContentsObserver arg0) {
        return arg0.mAwContentsClient;
    }

    public boolean didEverCommitNavigation() {
        return this.mCommittedNavigation;
    }

    public void didFailLoad(boolean arg1, int arg2, String arg3, String arg4) {
        Object v3 = this.mAwContentsClient.get();
        if(v3 == null) {
            return;
        }

        if((arg1) && arg2 == -3) {
            ((XWalkContentsClient)v3).getCallbackHelper().postOnPageFinished(arg4);
        }
    }

    public void didFinishLoad(long arg1, String arg3, boolean arg4) {
        if((arg4) && this.getClientIfNeedToFireCallback(arg3) != null) {
            this.mLastDidFinishLoadUrl = arg3;
        }
    }

    public void didFinishNavigation(String arg1, boolean arg2, boolean arg3, boolean arg4, boolean arg5, boolean arg6, Integer arg7, int arg8, String arg9, int arg10) {
        if(arg8 != 0) {
            this.didFailLoad(arg2, arg8, arg9, arg1);
        }

        if(!arg4) {
            return;
        }

        arg3 = true;
        this.mCommittedNavigation = true;
        if(!arg2) {
            return;
        }

        Object v2 = this.mAwContentsClient.get();
        if((arg4) && v2 != null) {
            if(arg7 == null || (arg7.intValue() & 0xFF) != 8) {
                arg3 = false;
            }
            else {
            }

            ((XWalkContentsClient)v2).getCallbackHelper().postDoUpdateVisitedHistory(arg1, arg3);
        }

        if(!arg5) {
            ThreadUtils.postOnUiThread(new XWalkWebContentsObserver$$Lambda$0(this, arg1));
        }

        if(v2 != null && (arg6)) {
            ((XWalkContentsClient)v2).getCallbackHelper().postOnPageFinished(arg1);
        }
    }

    public void didStopLoading(String arg3) {
        if(arg3.length() == 0) {
            arg3 = "about:blank";
        }

        XWalkContentsClient v0 = this.getClientIfNeedToFireCallback(arg3);
        if(v0 != null && (arg3.equals(this.mLastDidFinishLoadUrl))) {
            v0.getCallbackHelper().postOnPageFinished(arg3);
            this.mLastDidFinishLoadUrl = null;
        }
    }

    private XWalkContentsClient getClientIfNeedToFireCallback(String arg1) {
        return this.mAwContentsClient.get();
    }

    final void lambda$didFinishNavigation$0$XWalkWebContentsObserver(String arg5) {
        Object v0 = this.mAwContents.get();
        if(v0 != null) {
            ((XWalkContent)v0).insertVisualStateCallback(0, new VisualStateCallback(arg5) {
                public void onComplete(long arg1) {
                    Object v1 = XWalkWebContentsObserver.this.mAwContentsClient.get();
                    if(v1 == null) {
                        return;
                    }

                    ((XWalkContentsClient)v1).onPageCommitVisible(this.val$url);
                }
            });
        }
    }

    public void titleWasSet(String arg3) {
        Object v0 = this.mAwContentsClient.get();
        if(v0 == null) {
            return;
        }

        ((XWalkContentsClient)v0).updateTitle(arg3, true);
    }
}

