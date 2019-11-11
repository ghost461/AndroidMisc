package org.xwalk.core.internal.videofullscreen;

public class XWebFullscreenVideoUtil {
    public static final String XWEB_FULLSCREEN_VIDEO_JAVASCRIPT_INTERFACE = "xwebToNative";
    public static final String XWEB_FULLSCREEN_VIDEO_JS_FUNCTION_EXITFULLSCREEN = "xwebVideoBridge.xwebToJS_Video_ExitFullscreen();";
    public static final String XWEB_FULLSCREEN_VIDEO_JS_FUNCTION_PLAY = "xwebVideoBridge.xwebToJS_Video_Play();";
    public static final String XWEB_FULLSCREEN_VIDEO_JS_FUNCTION_SEEK = "xwebVideoBridge.xwebToJS_Video_Seek(%f);";

    public XWebFullscreenVideoUtil() {
        super();
    }

    public static String AddDOMLoadedEventJS(String arg2) {
        return "window.addEventListener(\'DOMContentLoaded\', function() {" + arg2 + "});this.xwebReturn = function (){return \"1\";};this.xwebReturn()";
    }
}

