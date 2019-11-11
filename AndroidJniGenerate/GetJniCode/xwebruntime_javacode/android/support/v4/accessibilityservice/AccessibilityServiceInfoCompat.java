package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;

public final class AccessibilityServiceInfoCompat {
    @RequiresApi(value=16) class AccessibilityServiceInfoApi16Impl extends AccessibilityServiceInfoBaseImpl {
        AccessibilityServiceInfoApi16Impl() {
            super();
        }

        public String loadDescription(AccessibilityServiceInfo arg1, PackageManager arg2) {
            return arg1.loadDescription(arg2);
        }
    }

    @RequiresApi(value=18) class AccessibilityServiceInfoApi18Impl extends AccessibilityServiceInfoApi16Impl {
        AccessibilityServiceInfoApi18Impl() {
            super();
        }

        public int getCapabilities(AccessibilityServiceInfo arg1) {
            return arg1.getCapabilities();
        }
    }

    class AccessibilityServiceInfoBaseImpl {
        AccessibilityServiceInfoBaseImpl() {
            super();
        }

        public int getCapabilities(AccessibilityServiceInfo arg1) {
            if(AccessibilityServiceInfoCompat.getCanRetrieveWindowContent(arg1)) {
                return 1;
            }

            return 0;
        }

        public String loadDescription(AccessibilityServiceInfo arg1, PackageManager arg2) {
            return null;
        }
    }

    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    @Deprecated public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 0x20;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 0x20;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoBaseImpl IMPL;

    static {
        if(Build$VERSION.SDK_INT >= 18) {
            AccessibilityServiceInfoCompat.IMPL = new AccessibilityServiceInfoApi18Impl();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            AccessibilityServiceInfoCompat.IMPL = new AccessibilityServiceInfoApi16Impl();
        }
        else {
            AccessibilityServiceInfoCompat.IMPL = new AccessibilityServiceInfoBaseImpl();
        }
    }

    private AccessibilityServiceInfoCompat() {
        super();
    }

    public static String capabilityToString(int arg1) {
        if(arg1 == 4) {
            return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        }

        if(arg1 == 8) {
            return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
        }

        switch(arg1) {
            case 1: {
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            }
            case 2: {
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            }
        }

        return "UNKNOWN";
    }

    public static String feedbackTypeToString(int arg4) {
        StringBuilder v0 = new StringBuilder();
        v0.append("[");
        while(arg4 > 0) {
            int v1 = 1 << Integer.numberOfTrailingZeros(arg4);
            arg4 &= v1 ^ -1;
            if(v0.length() > 1) {
                v0.append(", ");
            }

            if(v1 != 4) {
                if(v1 != 8) {
                    if(v1 != 16) {
                        switch(v1) {
                            case 1: {
                                goto label_25;
                            }
                            case 2: {
                                goto label_22;
                            }
                        }

                        continue;
                    label_22:
                        v0.append("FEEDBACK_HAPTIC");
                        continue;
                    label_25:
                        v0.append("FEEDBACK_SPOKEN");
                        continue;
                    }

                    v0.append("FEEDBACK_GENERIC");
                    continue;
                }

                v0.append("FEEDBACK_VISUAL");
                continue;
            }

            v0.append("FEEDBACK_AUDIBLE");
        }

        v0.append("]");
        return v0.toString();
    }

    public static String flagToString(int arg1) {
        if(arg1 == 4) {
            return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
        }

        if(arg1 == 8) {
            return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        }

        if(arg1 == 16) {
            return "FLAG_REPORT_VIEW_IDS";
        }

        if(arg1 == 0x20) {
            return "FLAG_REQUEST_FILTER_KEY_EVENTS";
        }

        switch(arg1) {
            case 1: {
                return "DEFAULT";
            }
            case 2: {
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            }
        }

        return null;
    }

    @Deprecated public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo arg0) {
        return arg0.getCanRetrieveWindowContent();
    }

    public static int getCapabilities(AccessibilityServiceInfo arg1) {
        return AccessibilityServiceInfoCompat.IMPL.getCapabilities(arg1);
    }

    @Deprecated public static String getDescription(AccessibilityServiceInfo arg0) {
        return arg0.getDescription();
    }

    @Deprecated public static String getId(AccessibilityServiceInfo arg0) {
        return arg0.getId();
    }

    @Deprecated public static ResolveInfo getResolveInfo(AccessibilityServiceInfo arg0) {
        return arg0.getResolveInfo();
    }

    @Deprecated public static String getSettingsActivityName(AccessibilityServiceInfo arg0) {
        return arg0.getSettingsActivityName();
    }

    public static String loadDescription(AccessibilityServiceInfo arg1, PackageManager arg2) {
        return AccessibilityServiceInfoCompat.IMPL.loadDescription(arg1, arg2);
    }
}

