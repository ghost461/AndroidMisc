package com.ui.keyboard;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import com.util.RuntimeEnviroment;

public class KeyBoardUtil {
    protected static int CONTENT_HEIGHT = -1;
    public static final String KEYBORD_HEIGHT_PX_PREFS = "com.tencent.mm.compatible.util.keybord.height";
    private static int LAST_SAVE_KEYBORD_HEIGHT_PX = -1;
    private static int MAX_PANEL_HEIGHT_PX = -1;
    private static final int MAX_PANEL_HEIGH_DP = 380;
    private static int MIN_PANEL_HEIGHT_PX = -1;
    private static final int MIN_PANEL_HEIGH_DP = 230;
    private static final String TAG = "MicroMsg.KeyBordUtil";
    private static float density = -1f;
    private static boolean isFixedHeight = false;
    static Context sContext;

    static {
        KeyBoardUtil.sContext = RuntimeEnviroment.getContext();
    }

    public KeyBoardUtil() {
        super();
    }

    public static boolean checkContextIsValid() {
        if(KeyBoardUtil.sContext == null) {
            KeyBoardUtil.sContext = RuntimeEnviroment.getContext();
        }

        boolean v0 = KeyBoardUtil.sContext != null ? true : false;
        return v0;
    }

    private static final int dp2px(Context arg0, int arg1) {
        return KeyBoardUtil.fromDPToPix(arg0, arg1);
    }

    public static int fromDPToPix(Context arg0, int arg1) {
        return Math.round(KeyBoardUtil.getDensity(arg0) * (((float)arg1)));
    }

    public static int fromPixToDP(Context arg0, int arg1) {
        return Math.round((((float)arg1)) / KeyBoardUtil.getDensity(arg0));
    }

    public static SharedPreferences getDefaultPreference() {
        if(KeyBoardUtil.sContext != null) {
            return KeyBoardUtil.sContext.getSharedPreferences("xwalk_keyboard", 0);
        }

        return null;
    }

    public static float getDensity(Context arg2) {
        if(KeyBoardUtil.density < 0f) {
            KeyBoardUtil.density = arg2.getResources().getDisplayMetrics().density;
        }

        return KeyBoardUtil.density;
    }

    public static final int getKeyBordHeightPx(Context arg3) {
        if(!KeyBoardUtil.isFixedHeight) {
            if(!KeyBoardUtil.checkContextIsValid()) {
                return KeyBoardUtil.getDefaultPreference().getInt("com.tencent.mm.compatible.util.keybord.height", 690);
            }

            KeyBoardUtil.LAST_SAVE_KEYBORD_HEIGHT_PX = KeyBoardUtil.getDefaultPreference().getInt("com.tencent.mm.compatible.util.keybord.height", KeyBoardUtil.dp2px(arg3, 230));
            return KeyBoardUtil.LAST_SAVE_KEYBORD_HEIGHT_PX;
        }

        return KeyBoardUtil.getMinPanelHeightPx(arg3);
    }

    public static final int getKeyBordHeightPx(Context arg1, boolean arg2) {
        if(!KeyBoardUtil.isFixedHeight) {
            if(KeyBoardUtil.LAST_SAVE_KEYBORD_HEIGHT_PX > 0 && (arg2)) {
                return KeyBoardUtil.LAST_SAVE_KEYBORD_HEIGHT_PX;
            }

            return KeyBoardUtil.getKeyBordHeightPx(arg1);
        }

        return KeyBoardUtil.getMinPanelHeightPx(arg1);
    }

    public static final int getMaxPanelHeightPx(Context arg1) {
        if(!KeyBoardUtil.isFixedHeight) {
            if(KeyBoardUtil.MAX_PANEL_HEIGHT_PX > 0) {
                return KeyBoardUtil.MAX_PANEL_HEIGHT_PX;
            }

            if(!KeyBoardUtil.checkContextIsValid()) {
                return 1140;
            }

            int v1 = KeyBoardUtil.dp2px(arg1, 380);
            KeyBoardUtil.MAX_PANEL_HEIGHT_PX = v1;
            return v1;
        }

        return KeyBoardUtil.getMinPanelHeightPx(arg1);
    }

    public static final int getMinPanelHeightPx(Context arg1) {
        if(KeyBoardUtil.MIN_PANEL_HEIGHT_PX > 0) {
            return KeyBoardUtil.MIN_PANEL_HEIGHT_PX;
        }

        if(!KeyBoardUtil.checkContextIsValid()) {
            return KeyBoardUtil.MIN_PANEL_HEIGHT_PX * 3;
        }

        int v1 = KeyBoardUtil.dp2px(arg1, 230);
        KeyBoardUtil.MIN_PANEL_HEIGHT_PX = v1;
        return v1;
    }

    public static int getScreenOrientation(Context arg2) {
        int[] v2 = KeyBoardUtil.getScreenWH(arg2);
        int v1 = 1;
        if(v2[0] < v2[1]) {
        }
        else {
            v1 = 2;
        }

        return v1;
    }

    public static int[] getScreenWH(Context arg4) {
        if(arg4 == null) {
            arg4 = KeyBoardUtil.sContext;
        }

        int[] v0 = new int[2];
        if((arg4 instanceof Activity)) {
            DisplayMetrics v1 = new DisplayMetrics();
            ((Activity)arg4).getWindowManager().getDefaultDisplay().getMetrics(v1);
            v0[0] = v1.widthPixels;
            v0[1] = v1.heightPixels;
        }
        else {
            Display v4 = arg4.getSystemService("window").getDefaultDisplay();
            v0[0] = v4.getWidth();
            v0[1] = v4.getHeight();
        }

        return v0;
    }

    public static int getStatusHeight(Activity arg1) {
        Rect v0 = new Rect();
        arg1.getWindow().getDecorView().getWindowVisibleDisplayFrame(v0);
        return v0.top;
    }

    public static final int getValidPanelHeight(Context arg1) {
        return KeyBoardUtil.getValidPanelHeight(arg1, -1);
    }

    public static final int getValidPanelHeight(Context arg4, int arg5) {
        int v4;
        int v0 = KeyBoardUtil.getMinPanelHeightPx(arg4);
        if(!KeyBoardUtil.isPortOrientation(arg4)) {
            arg5 = ((int)((((double)v0)) / 1.5));
            v4 = KeyBoardUtil.getScreenWH(arg4)[0] / 2;
            if(arg5 > v4) {
            }
            else {
                v4 = arg5;
            }

            return v4;
        }

        if(arg5 <= 0) {
            arg5 = KeyBoardUtil.getKeyBordHeightPx(arg4, true);
        }

        v4 = KeyBoardUtil.getMaxPanelHeightPx(arg4);
        if(arg5 > v4) {
            return v4;
        }

        if(arg5 < v0) {
            return v0;
        }

        return arg5;
    }

    public static int getVisibleHeight(Activity arg1) {
        Rect v0 = new Rect();
        arg1.getWindow().getDecorView().getWindowVisibleDisplayFrame(v0);
        return v0.bottom - v0.top;
    }

    public static boolean isPortOrientation(Context arg1) {
        boolean v0 = true;
        if(KeyBoardUtil.getScreenOrientation(arg1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public static final boolean isValidPanelHeightDP(int arg1) {
        boolean v1 = arg1 > 380 || arg1 < 230 ? false : true;
        return v1;
    }

    public static final boolean saveKeyBordHeightPx(Context arg1, int arg2) {
        if(KeyBoardUtil.LAST_SAVE_KEYBORD_HEIGHT_PX == arg2) {
            return 1;
        }

        if(!KeyBoardUtil.checkContextIsValid()) {
            return 0;
        }

        if(arg2 < 0) {
            return 0;
        }

        KeyBoardUtil.LAST_SAVE_KEYBORD_HEIGHT_PX = arg2;
        return KeyBoardUtil.getDefaultPreference().edit().putInt("com.tencent.mm.compatible.util.keybord.height", arg2).commit();
    }

    private static final void setContext(Context arg0) {
        if(arg0 == null) {
            return;
        }

        KeyBoardUtil.sContext = arg0;
    }

    public static void setFixedHeight(boolean arg0) {
        KeyBoardUtil.isFixedHeight = arg0;
    }
}

