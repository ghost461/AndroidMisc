package org.xwalk.core.internal;

import android.view.KeyEvent;
import android.webkit.ConsoleMessage$MessageLevel;
import android.webkit.ConsoleMessage;
import org.chromium.content.browser.ContentVideoView;
import org.chromium.content.browser.ContentVideoViewEmbedder;

class XWalkWebContentsDelegateAdapter extends XWalkWebContentsDelegate {
    private static final String TAG = "org.xwalk.core.internal.XWalkWebContentsDelegateAdapter";
    private XWalkContentsClient mXWalkContentsClient;
    private XWalkViewInternal mXWalkView;

    static {
    }

    public XWalkWebContentsDelegateAdapter(XWalkContentsClient arg1, XWalkViewInternal arg2) {
        super();
        this.mXWalkContentsClient = arg1;
        this.mXWalkView = arg2;
    }

    public void activateContents() {
        if(this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onRequestFocus();
        }
    }

    public boolean addMessageToConsole(int arg3, String arg4, int arg5, String arg6) {
        if(this.mXWalkContentsClient == null) {
            return 0;
        }

        ConsoleMessage$MessageLevel v0 = ConsoleMessage$MessageLevel.DEBUG;
        switch(arg3) {
            case 0: {
                v0 = ConsoleMessage$MessageLevel.TIP;
                break;
            }
            case 1: {
                v0 = ConsoleMessage$MessageLevel.LOG;
                break;
            }
            case 2: {
                v0 = ConsoleMessage$MessageLevel.WARNING;
                break;
            }
            case 3: {
                v0 = ConsoleMessage$MessageLevel.ERROR;
                break;
            }
            default: {
                Log.w(XWalkWebContentsDelegateAdapter.TAG, "Unknown message level, defaulting to DEBUG");
                break;
            }
        }

        return this.mXWalkContentsClient.onConsoleMessage(new ConsoleMessage(arg4, arg6, arg5, v0));
    }

    public boolean addNewContents(boolean arg2, boolean arg3) {
        return this.mXWalkContentsClient.onCreateWindow(arg2, arg3);
    }

    public void closeContents() {
        if(this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onCloseWindow();
        }
    }

    public ContentVideoViewEmbedder getContentVideoViewEmbedder() {
        return this.mXWalkContentsClient.getContentVideoViewEmbedder();
    }

    public void handleKeyboardEvent(KeyEvent arg2) {
        if(this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onUnhandledKeyEvent(arg2);
        }
    }

    public boolean isFullscreen() {
        if(this.mXWalkContentsClient != null) {
            return this.mXWalkContentsClient.hasEnteredFullscreen();
        }

        return 0;
    }

    public void loadingStateChanged(boolean arg3) {
        this.mXWalkContentsClient.updateTitle(this.mXWalkView.getTitle(), false);
    }

    public void onLoadProgressChanged(int arg2) {
        if(this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onProgressChanged(arg2);
        }
    }

    public void rendererResponsive() {
        if(this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onRendererResponsive();
        }
    }

    public void rendererUnresponsive() {
        if(this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onRendererUnresponsive();
        }
    }

    public boolean shouldCreateWebContents(String arg2) {
        if(this.mXWalkContentsClient != null) {
            return this.mXWalkContentsClient.shouldCreateWebContents(arg2);
        }

        return super.shouldCreateWebContents(arg2);
    }

    public boolean shouldOverrideRunFileChooser(int arg8, int arg9, int arg10, String arg11, boolean arg12) {
        if(this.mXWalkContentsClient != null) {
            return this.mXWalkContentsClient.shouldOverrideRunFileChooser(arg8, arg9, arg10, arg11, arg12);
        }

        return 0;
    }

    public void toggleFullscreen(boolean arg3) {
        if(!arg3) {
            ContentVideoView v0 = ContentVideoView.getContentVideoView();
            if(v0 != null) {
                v0.exitFullscreen(false);
            }
        }

        if(this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onToggleFullscreen(arg3);
        }
    }
}

