package org.xwalk.core.internal.extension.api.launchscreen;

import android.content.Context;
import android.content.Intent;
import org.xwalk.core.internal.XWalkExtensionInternal;
import org.xwalk.core.internal.XWalkLaunchScreenManager;

public class LaunchScreenExtension extends XWalkExtensionInternal {
    private static final String CMD_HIDE_LAUNCH_SCREEN = "hideLaunchScreen";
    public static final String JS_API_PATH = "jsapi/launch_screen_api.js";
    private static final String[] JS_ENTRY_POINTS = null;
    private static final String NAME = "xwalk.launchscreen";
    private Context mContext;

    static {
        LaunchScreenExtension.JS_ENTRY_POINTS = new String[]{"window.screen.show"};
    }

    public LaunchScreenExtension(String arg3, Context arg4) {
        super("xwalk.launchscreen", arg3, LaunchScreenExtension.JS_ENTRY_POINTS);
        this.mContext = arg4;
    }

    private void hideLaunchScreen() {
        this.mContext.sendBroadcast(new Intent(XWalkLaunchScreenManager.getHideLaunchScreenFilterStr()));
    }

    public void onMessage(int arg1, String arg2) {
        if(arg2.equals("hideLaunchScreen")) {
            this.hideLaunchScreen();
        }
    }

    public String onSyncMessage(int arg1, String arg2) {
        return null;
    }
}

