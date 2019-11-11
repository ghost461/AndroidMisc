package org.chromium.content.browser;

import android.graphics.SurfaceTexture;
import android.view.MotionEvent;
import android.view.View;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.json.JSONObject;
import org.xwalk.core.internal.reporter.XWebReporter;

@JNINamespace(value="content") public class ExtendPluginManager {
    private static final String DATA = "data";
    private static final String DATA_TYPE = "data-type";
    private static final String DATA_TYPE_SYNC_RESULT = "reply-method";
    private static final String DATA_TYPE_TOUCH = "touch-event";
    private static final String EMBED_ID = "embed-id";
    private static final String EMBED_TYPE = "embed-type";
    private static final String EMBED_TYPE_CANVAS = "canvas-1.0";
    private static final String SYNC_RESULT_ID = "reply-id";
    private static final String SYNC_RESULT_VALUE = "reply-value";
    private static final String TAG = "cr.ExtendPluginManager";
    private static final String TOUCH_TYPE_END = "end";
    private static final String TOUCH_TYPE_MOVE = "move";
    private static final String TOUCH_TYPE_START = "start";
    private static final String TYPE_CANVAS = "canvas";
    private static final String TYPE_MAP = "map";
    private static final String TYPE_VIDEO = "video";
    private ExtendCanvasCallback mCallbackCanvas;
    private ExtendPluginCallback mCallbackPlugin;
    private ContentViewRenderView mContentViewRenderView;
    private long mExtendPluginManager;

    private ExtendPluginManager(ContentViewRenderView arg1, long arg2, ExtendPluginCallback arg4, ExtendCanvasCallback arg5) {
        super();
        this.mContentViewRenderView = arg1;
        this.mExtendPluginManager = arg2;
        this.mCallbackPlugin = arg4;
        this.mCallbackCanvas = arg5;
    }

    @CalledByNative private static ExtendPluginManager createExtendPluginManager(ContentViewRenderView arg7, long arg8) {
        ThreadUtils.assertOnUiThread();
        ExtendPluginManager v6 = new ExtendPluginManager(arg7, arg8, arg7.getExtendPluginCallback(), arg7.getExtendCanvasCallback());
        arg7.setExtendPluginManager(v6);
        return v6;
    }

    @CalledByNative private void destroyExtendPluginManager(boolean arg4) {
        Log.i("cr.ExtendPluginManager", "destroyExtendPluginManager", new Object[0]);
        if(arg4) {
            this.mExtendPluginManager = 0;
        }

        this.mContentViewRenderView = null;
    }

    private native void nativeSendMethods(long arg1, String arg2, int arg3) {
    }

    private native void nativeSetExtendTextureScale(long arg1, String arg2, int arg3, float arg4, float arg5) {
    }

    @CalledByNative private void onPluginDestroy(String arg4, int arg5) {
        Log.i("cr.ExtendPluginManager", "onPluginDestroy embed_type:" + arg4 + ",embed_id:" + arg5, new Object[0]);
        if(this.mCallbackPlugin != null) {
            this.mCallbackPlugin.onPluginDestroy(arg4, arg5);
        }

        XWebReporter.onPluginDestroy();
    }

    @CalledByNative private void onPluginReady(String arg4, int arg5, SurfaceTexture arg6) {
        Log.i("cr.ExtendPluginManager", "onPluginReady embed_type:" + arg4 + ",embed_id:" + arg5, new Object[0]);
        if(this.mCallbackPlugin != null) {
            this.mCallbackPlugin.onPluginReady(arg4, arg5, arg6);
        }

        if("video".equals(arg4)) {
            XWebReporter.onPluginReadyForVideo();
        }
        else if("map".equals(arg4)) {
            XWebReporter.onPluginReadyForMap();
        }
        else if("canvas".equals(arg4)) {
            XWebReporter.onPluginReadyForCanvas();
        }

        XWebReporter.onPluginReady();
        XWebReporter.onPluginReadyForKV(arg4);
    }

    public static void onPluginTouch(String arg0, int arg1, MotionEvent arg2, ExtendPluginCallback arg3, ExtendCanvasCallback arg4, View arg5) {
        if("canvas-1.0".equals(arg0)) {
        }
        else if(arg3 != null) {
            arg3.onPluginTouch(arg0, arg1, arg2);
        }
    }

    @CalledByNative private void onSendJsonMessage(String arg6) {
        JSONObject v6 = this.parseDataStr(arg6);
        String v0 = v6.optString("embed-type");
        int v1 = v6.optInt("embed-id");
        String v2 = v6.optString("data");
        if(v6 != null) {
            String v3 = v6.optString("data-type");
            if("touch-event".equals(v3)) {
                if("canvas-1.0".equals(v0)) {
                    if(this.mCallbackCanvas != null) {
                        this.mCallbackCanvas.onCanvasTouch(v0, v1, v2);
                    }
                }
                else if(this.mCallbackPlugin != null) {
                    this.mCallbackPlugin.onPluginTouch(v0, v1, v2);
                }
            }
            else if("reply-method".equals(v3)) {
                int v0_1 = v6.optInt("reply-id");
                arg6 = v6.optString("reply-value");
                if(this.mCallbackCanvas != null) {
                    this.mCallbackCanvas.onAsycResultCallback(v1, v0_1, arg6);
                }
            }

            return;
        }

        Log.e("cr.ExtendPluginManager", "parseDataStr ret = null", new Object[0]);
    }

    @CalledByNative private void onSendTouchEvent(String arg14, int arg15, int arg16, int arg17, int arg18, int arg19, int arg20) {
        ExtendPluginManager v0 = this;
        if(v0.mContentViewRenderView != null) {
            v0.mContentViewRenderView.mWebContents.getEventForwarder().setLastMotionEventExtendPlugin(arg14, arg15, arg16, arg17, arg18, arg19, arg20, v0.mCallbackPlugin, v0.mCallbackCanvas, v0.mContentViewRenderView);
        }
    }

    private JSONObject parseDataStr(String arg3) {
        if(arg3 != null) {
            try {
                if(arg3.isEmpty()) {
                label_6:
                    arg3 = "{}";
                }

            label_7:
                return new JSONObject(arg3);
            label_5:
                goto label_10;
            }
            catch(Exception v3) {
                goto label_5;
            }
        }
        else {
            goto label_6;
        }

        goto label_7;
    label_10:
        Log.e("cr.ExtendPluginManager", v3.getMessage(), new Object[0]);
        return null;
    }

    public void sendAsyncMethods(String arg6, int arg7) {
        Log.d("cr.ExtendPluginManager", "sendAsyncMethods called! methods:" + arg6);
        if(this.mExtendPluginManager != 0) {
            this.nativeSendMethods(this.mExtendPluginManager, arg6, arg7);
        }
    }

    public void setPluginTextureScale(String arg9, int arg10, float arg11, float arg12) {
        Log.d("cr.ExtendPluginManager", "setExtendTextureScale called!");
        if(!ThreadUtils.runningOnUiThread()) {
            ThreadUtils.postOnUiThread(new Runnable(arg9, arg10, arg11, arg12) {
                public void run() {
                    ExtendPluginManager.this.setPluginTextureScale(this.val$embed_type, this.val$embed_id, this.val$xScale, this.val$yScale);
                }
            });
            return;
        }

        if(this.mExtendPluginManager != 0) {
            this.nativeSetExtendTextureScale(this.mExtendPluginManager, arg9, arg10, arg11, arg12);
        }
    }
}

