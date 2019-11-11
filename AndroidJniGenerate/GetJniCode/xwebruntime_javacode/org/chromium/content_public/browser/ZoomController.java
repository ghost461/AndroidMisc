package org.chromium.content_public.browser;

import android.os.SystemClock;
import org.chromium.content.browser.RenderCoordinates;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.base.EventForwarder;

public class ZoomController {
    private static final float ZOOM_CONTROLS_EPSILON = 0.007f;

    public ZoomController() {
        super();
    }

    public static boolean canZoomIn(WebContents arg1) {
        RenderCoordinates v1 = ((WebContentsImpl)arg1).getRenderCoordinates();
        boolean v1_1 = v1.getMaxPageScaleFactor() - v1.getPageScaleFactor() > 0.007f ? true : false;
        return v1_1;
    }

    public static boolean canZoomOut(WebContents arg1) {
        RenderCoordinates v1 = ((WebContentsImpl)arg1).getRenderCoordinates();
        boolean v1_1 = v1.getPageScaleFactor() - v1.getMinPageScaleFactor() > 0.007f ? true : false;
        return v1_1;
    }

    public static boolean pinchByDelta(WebContents arg4, float arg5) {
        if(arg4 == null) {
            return 0;
        }

        EventForwarder v4 = arg4.getEventForwarder();
        long v0 = SystemClock.uptimeMillis();
        v4.onGestureEvent(12, v0, 0f);
        v4.onGestureEvent(13, v0, arg5);
        v4.onGestureEvent(14, v0, 0f);
        return 1;
    }

    public static boolean zoomIn(WebContents arg1) {
        return ZoomController.pinchByDelta(arg1, 1.25f);
    }

    public static boolean zoomOut(WebContents arg1) {
        return ZoomController.pinchByDelta(arg1, 0.8f);
    }

    public static boolean zoomReset(WebContents arg1) {
        return ZoomController.pinchByDelta(arg1, -1f);
    }
}

