package org.chromium.content.common;

public abstract class ContentSwitches {
    public static final String ACCESSIBILITY_JAVASCRIPT_URL = "accessibility-js-url";
    public static final String ADD_OFFICIAL_COMMAND_LINE = "add-official-command-line";
    public static final String DISABLE_GESTURE_REQUIREMENT_FOR_PRESENTATION = "disable-gesture-requirement-for-presentation";
    public static final String DISABLE_POPUP_BLOCKING = "disable-popup-blocking";
    public static final String ENABLE_TEST_INTENTS = "enable-test-intents";
    public static final String HOST_RESOLVER_RULES = "host-resolver-rules";
    public static final String IN_PROCESS_GPU = "in-process-gpu";
    public static final String LOG_FPS = "log-fps";
    public static final String NETWORK_COUNTRY_ISO = "network-country-iso";
    public static final String NETWORK_SANDBOX_TYPE = "network";
    public static final String RENDER_PROCESS_LIMIT = "renderer-process-limit";
    public static final String SWITCH_GPU_PROCESS = "gpu-process";
    public static final String SWITCH_PROCESS_TYPE = "type";
    public static final String SWITCH_RENDERER_PROCESS = "renderer";
    public static final String SWITCH_SERVICE_SANDBOX_TYPE = "service-sandbox-type";
    public static final String SWITCH_UTILITY_PROCESS = "utility";
    public static final String TOP_CONTROLS_HIDE_THRESHOLD = "top-controls-hide-threshold";
    public static final String TOP_CONTROLS_SHOW_THRESHOLD = "top-controls-show-threshold";
    public static final String USE_FAKE_DEVICE_FOR_MEDIA_STREAM = "use-fake-device-for-media-stream";
    public static final String USE_MOBILE_UA = "use-mobile-user-agent";

    private ContentSwitches() {
        super();
    }

    public static String getSwitchValue(String[] arg5, String arg6) {
        String v0 = null;
        if(arg5 != null) {
            if(arg6 == null) {
            }
            else {
                arg6 = "--" + arg6 + "=";
                int v1_1 = arg5.length;
                int v2;
                for(v2 = 0; v2 < v1_1; ++v2) {
                    String v3 = arg5[v2];
                    if(v3 != null && (v3.startsWith(arg6))) {
                        return v3.substring(arg6.length());
                    }
                }

                return v0;
            }
        }

        return v0;
    }
}

