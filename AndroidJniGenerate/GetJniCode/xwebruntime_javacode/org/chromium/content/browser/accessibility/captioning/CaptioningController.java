package org.chromium.content.browser.accessibility.captioning;

import android.annotation.TargetApi;
import android.content.Context;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;

@JNINamespace(value="content") public class CaptioningController implements SystemCaptioningBridgeListener {
    private long mNativeCaptioningController;
    private SystemCaptioningBridge mSystemCaptioningBridge;

    public CaptioningController(WebContents arg1, Context arg2) {
        super();
        this.mSystemCaptioningBridge = CaptioningBridgeFactory.getSystemCaptioningBridge(arg2);
        this.mNativeCaptioningController = this.nativeInit(arg1);
    }

    private native long nativeInit(WebContents arg1) {
    }

    private native void nativeSetTextTrackSettings(long arg1, boolean arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9) {
    }

    @CalledByNative private void onDestroy() {
        this.mNativeCaptioningController = 0;
    }

    @CalledByNative private void onRenderProcessChange() {
        this.mSystemCaptioningBridge.syncToListener(((SystemCaptioningBridgeListener)this));
    }

    @TargetApi(value=19) public void onSystemCaptioningChanged(TextTrackSettings arg13) {
        if(this.mNativeCaptioningController == 0) {
            return;
        }

        this.nativeSetTextTrackSettings(this.mNativeCaptioningController, arg13.getTextTracksEnabled(), arg13.getTextTrackBackgroundColor(), arg13.getTextTrackFontFamily(), arg13.getTextTrackFontStyle(), arg13.getTextTrackFontVariant(), arg13.getTextTrackTextColor(), arg13.getTextTrackTextShadow(), arg13.getTextTrackTextSize());
    }

    public void startListening() {
        this.mSystemCaptioningBridge.addListener(((SystemCaptioningBridgeListener)this));
    }

    public void stopListening() {
        this.mSystemCaptioningBridge.removeListener(((SystemCaptioningBridgeListener)this));
    }
}

