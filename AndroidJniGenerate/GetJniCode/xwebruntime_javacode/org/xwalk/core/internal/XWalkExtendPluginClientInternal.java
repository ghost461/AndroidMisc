package org.xwalk.core.internal;

import android.graphics.SurfaceTexture;
import android.view.MotionEvent;
import org.chromium.content.browser.ContentViewRenderView;
import org.chromium.content.browser.ExtendPluginCallback;
import org.chromium.content.browser.ExtendPluginManager;

@XWalkAPI(createExternally=true) public class XWalkExtendPluginClientInternal implements ExtendPluginCallback {
    private static final String TAG = "XWalkExtendPluginClientInternal";
    private ContentViewRenderView mRenderView;

    @XWalkAPI public XWalkExtendPluginClientInternal(XWalkViewInternal arg1) {
        super();
    }

    public void onPluginDestroy(String arg1, int arg2) {
    }

    public void onPluginReady(String arg1, int arg2, SurfaceTexture arg3) {
    }

    public void onPluginTouch(String arg1, int arg2, MotionEvent arg3) {
    }

    public void onPluginTouch(String arg1, int arg2, String arg3) {
    }

    public void onSendJsonMessage(String arg1) {
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

