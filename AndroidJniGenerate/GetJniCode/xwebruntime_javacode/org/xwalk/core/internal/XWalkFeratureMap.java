package org.xwalk.core.internal;

import android.os.Build$VERSION;
import android.os.Build;

public class XWalkFeratureMap {
    class FeatureList {
        public static final int FEATRUE_LOAD_LOCAL_JS = 2001;
        public static final int FEATRUE_PAGE_COMMIT_VISIBLE = 2006;
        public static final int FEATRUE_SWITCH_ACTIVITY = 2003;
        public static final int FEATRUE_XWEB_SCRIPT = 2002;
        public static final int FEATRUE_XWEB_SCRIPT_SUPPORT_FALLBACK = 2004;
        public static final int FEATRUE_XWEB_SMOOTH_SCROLL = 2005;
        public static final int INTERFACE_CUSTOM_CONTEXT = 1020;
        public static final int INTERFACE_EXTEND_PLUGIN_MAP = 0x3F3;
        public static final int INTERFACE_EXTEND_PLUGIN_VIDEO = 1010;
        public static final int INTERFACE_GEO_LOCATION_PERMISSION = 1000;
        public static final int INTERFACE_MANUAL_PROFILE = 1030;
        public static final int INTERNAL_LONG_SCREENSHOT = 2;
        public static final int INTERNAL_ON_HTTP_ERROR = 3;
        public static final int INTERNAL_WEBVIEW_PAUSE_RESUME = 1;
        public static final int INTERNAL_XPROFILE;

        private FeatureList() {
            super();
        }
    }

    private static final int[] AVAILABLE_FEATURES;

    static {
        XWalkFeratureMap.AVAILABLE_FEATURES = new int[]{0, 1, 1000, 1010, 0x3F3, 2001, 2002, 1020, 2004, 2, 2003, 2005, 2006, 1030, 3};
    }

    public XWalkFeratureMap() {
        super();
    }

    public static boolean hasFeature(int arg5) {
        int v2;
        if(2002 == arg5 && ("true".equalsIgnoreCase(RuntimeToSdkChannel.getCmd("dis_xweb_script", "tools")))) {
            return 0;
        }

        if(1020 == arg5) {
            StringBuilder v1 = new StringBuilder();
            v1.append(Build.BRAND);
            v1.append("_");
            v1.append(Build.MODEL);
            v1.append("_");
            v1.append(Build.MANUFACTURER);
            String v1_1 = v1.toString().toUpperCase();
            v2 = Build$VERSION.SDK_INT;
            if(v1_1.indexOf("HUAWEI") != -1 && v2 == 23) {
                Log.w("XWalkFeratureMap", "err!!!!!!!!!!!!!  huawei 6.0 ,XWalkResource bug !!!!!!  device = " + v1_1);
                return 0;
            }
        }

        int[] v1_2 = XWalkFeratureMap.AVAILABLE_FEATURES;
        v2 = v1_2.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            if(arg5 == v1_2[v3]) {
                return 1;
            }
        }

        return 0;
    }
}

