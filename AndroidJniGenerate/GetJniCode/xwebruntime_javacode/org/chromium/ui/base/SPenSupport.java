package org.chromium.ui.base;

import android.content.pm.FeatureInfo;
import android.os.Build;
import org.chromium.base.ContextUtils;

public final class SPenSupport {
    private static final int SPEN_ACTION_CANCEL = 0xD6;
    private static final int SPEN_ACTION_DOWN = 0xD3;
    private static final int SPEN_ACTION_MOVE = 0xD5;
    private static final int SPEN_ACTION_UP = 0xD4;
    private static Boolean sIsSPenSupported;

    public SPenSupport() {
        super();
    }

    public static int convertSPenEventAction(int arg1) {
        if(SPenSupport.sIsSPenSupported == null) {
            SPenSupport.initialize();
        }

        if(!SPenSupport.sIsSPenSupported.booleanValue()) {
            return arg1;
        }

        switch(arg1) {
            case 211: {
                return 0;
            }
            case 212: {
                return 1;
            }
            case 213: {
                return 2;
            }
            case 214: {
                return 3;
            }
        }

        return arg1;
    }

    private static void initialize() {
        if(SPenSupport.sIsSPenSupported != null) {
            return;
        }

        if(!"SAMSUNG".equalsIgnoreCase(Build.MANUFACTURER)) {
            SPenSupport.sIsSPenSupported = Boolean.valueOf(false);
            return;
        }

        FeatureInfo[] v0 = ContextUtils.getApplicationContext().getPackageManager().getSystemAvailableFeatures();
        int v2 = v0.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            if("com.sec.feature.spen_usp".equalsIgnoreCase(v0[v3].name)) {
                SPenSupport.sIsSPenSupported = Boolean.valueOf(true);
                return;
            }
        }

        SPenSupport.sIsSPenSupported = Boolean.valueOf(false);
    }
}

