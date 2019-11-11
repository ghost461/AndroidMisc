package org.xwalk.core.internal;

import org.chromium.content.browser.ContentViewRenderView;
import org.chromium.content.browser.ExtendCanvasCallback;
import org.chromium.content.browser.ExtendPluginManager;

@XWalkAPI(createExternally=true) public class XWalkExtendCanvasClientInternal implements ExtendCanvasCallback {
    private static final String TAG = "XWalkExtendPluginClientInternal";
    private ContentViewRenderView mRenderView;

    @XWalkAPI public XWalkExtendCanvasClientInternal(XWalkViewInternal arg1) {
        super();
    }

    public void onAsycResultCallback(int arg1, int arg2, String arg3) {
    }

    public void onCanvasTouch(String arg1, int arg2, String arg3) {
    }

    public void onSendJsonMessage(String arg1) {
    }

    public void sendAsyncMethods(String arg4, int arg5) {
        Log.d("XWalkExtendPluginClientInternal", "sendAsyncMethods called! methods:" + arg4 + ",reply_id:" + arg5);
        if(this.mRenderView != null) {
            ExtendPluginManager v0 = this.mRenderView.getExtendPluginManager();
            if(v0 != null) {
                v0.sendAsyncMethods(arg4, arg5);
            }
        }
    }

    public void setExtendPluginManager(ContentViewRenderView arg1) {
        this.mRenderView = arg1;
    }

    public void setPluginTextureScale(String arg4, int arg5, float arg6, float arg7) {
        Log.i("XWalkExtendPluginClientInternal", "setPluginTextureScale embed_type:" + arg4 + ",embed_id:" + arg5 + ",xScale:" + arg6 + ",yScale:" + arg7);
        if(this.mRenderView != null) {
            ExtendPluginManager v0 = this.mRenderView.getExtendPluginManager();
            if(v0 != null) {
                v0.setPluginTextureScale(arg4, arg5, arg6, arg7);
            }
        }
    }
}

